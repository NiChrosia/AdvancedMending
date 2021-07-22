package nichrosia.advancedmending

import net.fabricmc.api.ModInitializer
import nichrosia.advancedmending.content.Enchantments
import nichrosia.advancedmending.content.GameRules

open class AdvancedMending : ModInitializer {
    companion object {
        var advancedMendingEnabled = true
    }

    override fun onInitialize() {
        Enchantments.load()
        GameRules.load()
    }
}