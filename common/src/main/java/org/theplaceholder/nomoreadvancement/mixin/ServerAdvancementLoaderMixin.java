package org.theplaceholder.nomoreadvancement.mixin;

import com.google.gson.JsonElement;
import net.minecraft.advancement.Advancement;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Mixin(ServerAdvancementLoader.class)
public class ServerAdvancementLoaderMixin {

    @Inject(at = @At("HEAD"), method = "get", cancellable = true)
    private void get(Identifier id, CallbackInfoReturnable<Advancement> cir) {
        cir.setReturnValue(null);
    }

    @Inject(at = @At("HEAD"), method = "getAdvancements", cancellable = true)
    private void getAdvancements(CallbackInfoReturnable<Collection<Advancement>> cir) {
        cir.setReturnValue(Collections.emptyList());
    }

    @Inject(at = @At("HEAD"), method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", cancellable = true)
    private void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        ci.cancel();
    }
}
