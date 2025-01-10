package org.theplaceholder.nomoreadvancement.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mixin(GameOptions.class)
public class GameOptionsMixin {

    @Mutable @Shadow @Final public KeyBinding[] allKeys;

    @Shadow @Final public KeyBinding advancementsKey;

    @Inject(at = @At(value = "RETURN"), method = "<init>")
    private void init(MinecraftClient minecraft, File file, CallbackInfo ci) {
        List<KeyBinding> allKeys = new ArrayList<>(List.of(this.allKeys));
        allKeys.removeIf(keyMapping -> this.advancementsKey.equals(keyMapping));
        this.allKeys = allKeys.toArray(new KeyBinding[0]);
    }
}
