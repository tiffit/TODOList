package tiffit.todolist.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import tiffit.todolist.TODOList;
import tiffit.todolist.TODOListMod;
import tiffit.todolist.items.TODOTask;

public class ListSelectionList extends GuiListExtended {
	
	List<ListEntry> lists = new ArrayList<ListEntry>();
	
	public ListSelectionList(Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
		super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
		update();
	}
	
	public void update(){
		lists.clear();
		for(int i = 0; i < TODOListMod.lists.size(); i++){
			lists.add(new ListEntry(i, this));
		}
	}

	@Override
	public IGuiListEntry getListEntry(int index) {
		return lists.get(index);
	}

	@Override
	protected int getSize() {
		return lists.size();
	}
	
	@Override
    protected void overlayBackground(int startY, int endY, int startAlpha, int endAlpha){
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        this.mc.getTextureManager().bindTexture(TODOListMod.config.getTheme().RESOURCE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        vertexbuffer.pos((double)this.left, (double)endY, 0.0D).tex(0.0D, (double)((float)endY / 32.0F)).color(64, 64, 64, endAlpha).endVertex();
        vertexbuffer.pos((double)(this.left + this.width), (double)endY, 0.0D).tex((double)((float)this.width / 32.0F), (double)((float)endY / 32.0F)).color(64, 64, 64, endAlpha).endVertex();
        vertexbuffer.pos((double)(this.left + this.width), (double)startY, 0.0D).tex((double)((float)this.width / 32.0F), (double)((float)startY / 32.0F)).color(64, 64, 64, startAlpha).endVertex();
        vertexbuffer.pos((double)this.left, (double)startY, 0.0D).tex(0.0D, (double)((float)startY / 32.0F)).color(64, 64, 64, startAlpha).endVertex();
        tessellator.draw();
    }
    
	@Override
	protected void drawContainerBackground(Tessellator tessellator) {
		VertexBuffer buffer = tessellator.getBuffer();
		this.mc.getTextureManager().bindTexture(TODOListMod.config.getTheme().RESOURCE);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		float f = 32.0F;
		buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		buffer.pos((double) this.left, (double) this.bottom, 0.0D).tex((double) ((float) this.left / f), (double) ((float) (this.bottom + (int) this.amountScrolled) / f)).color(32, 32, 32, 255).endVertex();
		buffer.pos((double) this.right, (double) this.bottom, 0.0D).tex((double) ((float) this.right / f), (double) ((float) (this.bottom + (int) this.amountScrolled) / f)).color(32, 32, 32, 255).endVertex();
		buffer.pos((double) this.right, (double) this.top, 0.0D).tex((double) ((float) this.right / f), (double) ((float) (this.top + (int) this.amountScrolled) / f)).color(32, 32, 32, 255).endVertex();
		buffer.pos((double) this.left, (double) this.top, 0.0D).tex((double) ((float) this.left / f), (double) ((float) (this.top + (int) this.amountScrolled) / f)).color(32, 32, 32, 255).endVertex();
		tessellator.draw();
	}
	
	public ListEntry getSelected(){
		for(int i = 0; i < lists.size(); i++){
			ListEntry entry = lists.get(i);
			if(entry.selected){
				return entry;
			}
		}
		return null;
	}
	
	public void remove(int index){
		TODOListMod.lists.remove(index);
		update();
	}

}
