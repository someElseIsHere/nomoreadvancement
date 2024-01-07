package org.theplaceholder.nomoreadvancement.mixin;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(PauseScreen.class)
public class PauseScreenMixin {
    @Shadow @Final private static Component ADVANCEMENTS;

    @Inject(at = @At(value = "RETURN"), method = "openScreenButton", cancellable = true)
    private void openScreenButton(Component component, Supplier<Screen> supplier, CallbackInfoReturnable<Button> cir) {
        if (component != ADVANCEMENTS)
            return;

        Button button = Button.builder(Component.empty(), (b) -> {})
                .width(98)
                .build();
        button.visible = false;
        cir.setReturnValue(button);
    }
}
