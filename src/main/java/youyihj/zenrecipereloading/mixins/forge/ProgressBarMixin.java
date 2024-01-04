package youyihj.zenrecipereloading.mixins.forge;

import net.minecraftforge.fml.common.ProgressManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import youyihj.zenrecipereloading.client.RuntimeProgressBarRender;

@Mixin(value = ProgressManager.ProgressBar.class, remap = false)
public abstract class ProgressBarMixin {
    @Inject(method = "step(Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/FMLCommonHandler;processWindowMessages()V"), cancellable = true)
    private void renderBarNow(String message, CallbackInfo ci) {
        if (RuntimeProgressBarRender.shouldRender()) {
            RuntimeProgressBarRender.render();
            ci.cancel();
        }
    }
}
