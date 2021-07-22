package nichrosia.advancedmending.content

import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import nichrosia.advancedmending.enchantment.AdvancedMendingEnchantment

open class Enchantments {
    companion object {
        fun load() {
            advancedMending = Registry.register(
                Registry.ENCHANTMENT,
                Identifier("advanced-mending", "advanced-mending"),
                AdvancedMendingEnchantment()
            )
        }

        lateinit var advancedMending: AdvancedMendingEnchantment
    }
}