package nichrosia.advancedmending.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import nichrosia.advancedmending.content.GameRules;
import nichrosia.advancedmending.enchantment.AdvancedMendingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Map;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity {
    @Inject(at = @At("TAIL"), method = "tick()V")
    private void tick(CallbackInfo info) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        PlayerInventory inventory = player.getInventory();
        ArrayList<ItemStack> combinedInventory = new ArrayList<>();

        if (GameRules.getRawAdvancedMendingHealsAllItems()) {
            combinedInventory.addAll(inventory.main.stream().toList());
        } else {
            combinedInventory.add(inventory.getMainHandStack());
        }

        combinedInventory.addAll(inventory.armor.stream().toList());
        combinedInventory.addAll(inventory.offHand.stream().toList());

        for (ItemStack itemStack : combinedInventory) {
            for (Map.Entry<Enchantment, Integer> enchantment : EnchantmentHelper.fromNbt(itemStack.getEnchantments()).entrySet()) {
                if (!(enchantment.getKey() instanceof AdvancedMendingEnchantment)) continue;

                ((AdvancedMendingEnchantment)enchantment.getKey()).update(itemStack, player, enchantment.getValue());
            }
        }
    }
}