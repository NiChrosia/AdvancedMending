package nichrosia.advancedmending.enchantment

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.enchantment.InfinityEnchantment
import net.minecraft.enchantment.MendingEnchantment
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import nichrosia.advancedmending.AdvancedMending
import nichrosia.advancedmending.math.clamp
import kotlin.math.roundToInt

open class AdvancedMendingEnchantment : Enchantment(
    Rarity.VERY_RARE,
    EnchantmentTarget.BREAKABLE,
    EquipmentSlot.values()
), UpdatableEnchantment {
    open fun getHealAmount(level: Int, damage: Int): Int {
        return clamp((level / 1.25).roundToInt(), max = damage)
    }

    open fun getRepairCost(healAmount: Int): Int {
        return clamp(healAmount / 2, 1)
    }

    override fun getMinPower(level: Int): Int {
        return level * 35
    }

    override fun getMaxPower(level: Int): Int {
        return level * 40
    }

    override fun isTreasure(): Boolean {
        return true
    }

    /** Efficiency scales with level. */
    override fun getMaxLevel(): Int {
        return 5
    }

    override fun canAccept(other: Enchantment?): Boolean {
        return other !is MendingEnchantment && other !is InfinityEnchantment
    }

    override fun update(itemStack: ItemStack, player: PlayerEntity, enchantmentLevel: Int) {
        val item = itemStack.item
        val xp = player.totalExperience

        if (!AdvancedMending.advancedMendingEnabled ||
            !item.isDamageable ||
            !itemStack.isDamaged ||
            xp <= 0
        ) return

        println("Damage is ${itemStack.damage}")

        val healAmount = getHealAmount(enchantmentLevel, itemStack.damage)
        val xpUsed = getRepairCost(healAmount)

        println("Durability mended will be $healAmount")
        println("XP required for mending is $xpUsed")

        if (healAmount > 0) {
            player.addExperience(-xpUsed)
            itemStack.damage = clamp(itemStack.damage - healAmount)
        }
    }
}