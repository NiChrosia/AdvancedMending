package nichrosia.advancedmending.enchantment

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack

interface UpdatableEnchantment {
    fun update(itemStack: ItemStack, player: PlayerEntity, enchantmentLevel: Int)
}