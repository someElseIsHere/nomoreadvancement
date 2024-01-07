package org.theplaceholder.nomoreadvancement.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mixin(Options.class)
public class OptionsMixin {

    @Shadow public KeyMapping[] keyMappings;

    @Shadow @Final public KeyMapping keyAdvancements;

    @Inject(at = @At(value = "RETURN"), method = "<init>")
    private void init(Minecraft minecraft, File file, CallbackInfo ci) {
        List<KeyMapping> keyMappings = new ArrayList<>(List.of(this.keyMappings));
        keyMappings.removeIf(keyMapping -> this.keyAdvancements.equals(keyMapping));
        this.keyMappings = keyMappings.toArray(new KeyMapping[0]);
    }
}
