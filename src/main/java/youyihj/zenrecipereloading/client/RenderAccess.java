package youyihj.zenrecipereloading.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;

import java.util.List;

public class RenderAccess {
    private static class GuiAccess extends GuiScreen {
        @Override
        public void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
            super.drawGradientRect(left, top, right, bottom, startColor, endColor);
        }

        @Override
        public void drawHorizontalLine(int startX, int endX, int y, int color) {
            super.drawHorizontalLine(startX, endX, y, color);
        }

        @Override
        public void drawVerticalLine(int x, int startY, int endY, int color) {
            super.drawVerticalLine(x, startY, endY, color);
        }

        @Override
        public void drawHoveringText(List<String> textLines, int x, int y) {
            super.drawHoveringText(textLines, x, y);
        }

        @Override
        public void drawHoveringText(List<String> textLines, int x, int y, FontRenderer font) {
            super.drawHoveringText(textLines, x, y, font);
        }

        @Override
        public void renderToolTip(ItemStack stack, int x, int y) {
            super.renderToolTip(stack, x, y);
        }
    }

    private static final GuiAccess GUI_ACCESS = new GuiAccess();

    public static void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
		try{
			GUI_ACCESS.drawCenteredString(fontRendererIn, text, x, y, color);
		}catch(Exception e){}
	}

    public static void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
		try{
			GUI_ACCESS.drawGradientRect(left, top, right, bottom, startColor, endColor);
		}catch(Exception e){}
	}

    public static void drawHorizontalLine(int startX, int endX, int y, int color) {
		try{
			GUI_ACCESS.drawHorizontalLine(startX, endX, y, color);
		}catch(Exception e){}
	}

    public static void drawString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
		try{
			GUI_ACCESS.drawString(fontRendererIn, text, x, y, color);
		}catch(Exception e){}
	}

    public static void drawTexturedModalRect(float xCoord, float yCoord, int minU, int minV, int maxU, int maxV) {
		try{
			GUI_ACCESS.drawTexturedModalRect(xCoord, yCoord, minU, minV, maxU, maxV);
		}catch(Exception e){}
	}

    public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
		try{
			GUI_ACCESS.drawTexturedModalRect(x, y, textureX, textureY, width, height);
		}catch(Exception e){}
	}

    public static void drawTexturedModalRect(int xCoord, int yCoord, TextureAtlasSprite textureSprite, int widthIn, int heightIn) {
		try{
			GUI_ACCESS.drawTexturedModalRect(xCoord, yCoord, textureSprite, widthIn, heightIn);
		}catch(Exception e){}
	}

    public static void drawVerticalLine(int x, int startY, int endY, int color) {
		try{
			GUI_ACCESS.drawVerticalLine(x, startY, endY, color);
		}catch(Exception e){}
	}

    public static void drawScaledCustomSizeModalRect(int x, int y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight) {
        try{
			Gui.drawScaledCustomSizeModalRect(x, y, u, v, uWidth, vHeight, width, height, tileWidth, tileHeight);
		}catch(Exception e){}
	}

    public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        try{
			Gui.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, textureWidth, textureHeight);
		}catch(Exception e){}
    }

    public static void drawRect(int left, int top, int right, int bottom, int color) {
        try{
			Gui.drawRect(left, top, right, bottom, color);
		}catch(Exception e){}
    }

    public static void drawHoveringText(List<String> textLines, int x, int y) {
		try{
			GUI_ACCESS.drawHoveringText(textLines, x, y);
		}catch(Exception e){}
	}

    public static void drawHoveringText(List<String> textLines, int x, int y, FontRenderer font) {
        try{
			GUI_ACCESS.drawHoveringText(textLines, x, y, font);
		}catch(Exception e){}
	}

    public static void renderToolTip(ItemStack stack, int x, int y) {
        try{
			GUI_ACCESS.renderToolTip(stack, x, y);
		}catch(Exception e){}
	}

    public static void drawBackground(int width, int height) {
		try{
			GUI_ACCESS.setWorldAndResolution(Minecraft.getMinecraft(), width, height);
			GUI_ACCESS.drawBackground(0);
		}catch(Exception e){}
    }
}
