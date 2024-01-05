package youyihj.zenrecipereloading.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.common.ProgressManager;
import youyihj.zenrecipereloading.mixins.vanilla.MinecraftAccessor;

import java.util.Iterator;

public class RuntimeProgressBarRender {
    private static final ThreadLocal<Long> lastUpdate = ThreadLocal.withInitial(() -> 0L);
    private static final ThreadLocal<String> renderTitle = new ThreadLocal<>();

    public static void start(String title) {
        renderTitle.set(title);
        ((MinecraftAccessor) Minecraft.getMinecraft()).setIsGamePaused(true);
    }

    public static void end() {
        renderTitle.remove();
        ((MinecraftAccessor) Minecraft.getMinecraft()).setIsGamePaused(false);
    }

    public static boolean shouldRender() {
        return renderTitle.get() != null;
    }

    public static void render() {
        if (Minecraft.getSystemTime() - lastUpdate.get() < 33) { // 30 FPS
            return;
        }
        lastUpdate.set(Minecraft.getSystemTime());

        if (ProgressManager.barIterator().hasNext()) {
            Iterator<ProgressManager.ProgressBar> barIterator = ProgressManager.barIterator();
            Minecraft mc = Minecraft.getMinecraft();
            mc.getFramebuffer().unbindFramebuffer();
            GlStateManager.pushMatrix();
            ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
            /*
             * If you're not familiar, this call switches the rendering mode from
             * 3D perspective to 2D orthogonal.
             */
            mc.entityRenderer.setupOverlayRendering();
            // Draw the dirt background and status text...
            RenderAccess.drawBackground(res.getScaledWidth(), res.getScaledHeight());
            RenderAccess.drawCenteredString(mc.fontRenderer, renderTitle.get(), res.getScaledWidth() / 2, res.getScaledHeight() / 2 - 60, -1);

            int heightOffset = 0;
            while (barIterator.hasNext()) {
                ProgressManager.ProgressBar bar = barIterator.next();
                GlStateManager.pushMatrix();
                RenderAccess.drawCenteredString(mc.fontRenderer, bar.getTitle() + " " + bar.getMessage(), res.getScaledWidth() / 2, res.getScaledHeight() / 2 + heightOffset - 24, -1);
                RenderAccess.drawRect(res.getScaledWidth() / 2 - 50, res.getScaledHeight() / 2 - 1 + heightOffset, res.getScaledWidth() / 2 + 50, res.getScaledHeight() / 2 + 1 + heightOffset, 0xFF001100);
                RenderAccess.drawRect(res.getScaledWidth() / 2 - 50, res.getScaledHeight() / 2 - 1 + heightOffset, (res.getScaledWidth() / 2 - 50) + (bar.getStep() * 100 / bar.getSteps()), res.getScaledHeight() / 2 + 1 + heightOffset, 0xFF55FF55);
                GlStateManager.popMatrix();

                heightOffset += 40;
            }
            GlStateManager.popMatrix();
            mc.updateDisplay();
            /*
             * While OpenGL itself is double-buffered, Minecraft is actually *triple*-buffered.
             * This is to allow shaders to work, as shaders are only available in "modern" GL.
             * Minecraft uses "legacy" GL, so it renders using a separate GL context to this
             * third buffer, which is then flipped to the back buffer with this call.
             */
            mc.getFramebuffer().bindFramebuffer(false);

        }
    }
}

