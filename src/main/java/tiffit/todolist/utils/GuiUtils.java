package tiffit.todolist.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import tiffit.todolist.gui.EnumTheme;

public class GuiUtils {
	
	public static void drawBackground(EnumTheme theme, int width, int height) {
		Minecraft mc = Minecraft.getMinecraft();
		GlStateManager.disableLighting();
		GlStateManager.disableFog();
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		mc.getTextureManager().bindTexture(theme.RESOURCE);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		float f = 32.0F;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		vertexbuffer.pos(0.0D, (double) height, 0.0D).tex(0.0D, (double) ((float) height / 32.0F + 0)).color(64, 64, 64, 255).endVertex();
		vertexbuffer.pos((double) width, (double) height, 0.0D).tex((double) ((float) width / 32.0F), (double) ((float) height / 32.0F)).color(64, 64, 64, 255).endVertex();
		vertexbuffer.pos((double) width, 0.0D, 0.0D).tex((double) ((float) width / 32.0F), 0).color(64, 64, 64, 255).endVertex();
		vertexbuffer.pos(0.0D, 0.0D, 0.0D).tex(0.0D, 0).color(64, 64, 64, 255).endVertex();
		tessellator.draw();
	}
	
}
