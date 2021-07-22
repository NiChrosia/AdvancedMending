package nichrosia.advancedmending.content

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry
import net.minecraft.text.TranslatableText
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import net.minecraft.world.GameRules

open class GameRules {
    companion object {
        @JvmStatic
        var rawAdvancedMendingHealsAllItems = false

        fun load() {
            advancedMendingHealsAllItems = GameRuleRegistry.register(
                "advancedMendingHealsFullInventory",
                GameRules.Category.PLAYER,
                GameRuleFactory.createBooleanRule(true) { server, rule ->
                    rawAdvancedMendingHealsAllItems = rule.get()
                }
            )
        }

        val advancedMendingCategory = CustomGameRuleCategory(
            Identifier("advancedmending:advancedmending"),
            TranslatableText("gamerule.category.advancedmending")
                .formatted(Formatting.BOLD)
                .formatted(Formatting.AQUA))

        lateinit var advancedMendingHealsAllItems: GameRules.Key<GameRules.BooleanRule>
    }
}