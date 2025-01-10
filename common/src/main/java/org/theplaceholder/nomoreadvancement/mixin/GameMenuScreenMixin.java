package org.theplaceholder.nomoreadvancement.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin {

    @Shadow @Final private static Text ADVANCEMENTS_TEXT;

    @Inject(at = @At(value = "HEAD"), method = "createButton", cancellable = true)
    private void openScreenButton(Text text, Supplier<Screen> screenSupplier, CallbackInfoReturnable<ButtonWidget> cir) {
        if (text != ADVANCEMENTS_TEXT)
            return;

        ButtonWidget button = ButtonWidget.builder(Text.empty(), (b) -> {}).width(98).build();
        button.active = false;
        button.visible = false;
        cir.setReturnValue(button);
    }
}
