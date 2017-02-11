/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.Minecraft;
/*  4:   */ import net.minecraft.client.gui.inventory.GuiContainer;
/*  5:   */ import net.minecraft.client.renderer.texture.TextureManager;
/*  6:   */ import net.minecraft.entity.player.EntityPlayer;
/*  7:   */ import net.minecraft.inventory.IInventory;
/*  8:   */ import net.minecraft.inventory.Slot;
/*  9:   */ import net.minecraft.util.ResourceLocation;
/* 10:   */ import org.lwjgl.opengl.GL11;
/* 11:   */ 
/* 12:   */ public class GuiPlayerInventory
/* 13:   */   extends GuiContainer
/* 14:   */ {
/* 15:13 */   private static final ResourceLocation texture = new ResourceLocation("textures/gui/container/generic_54.png");
/* 16:   */   private IInventory upperChestInventory;
/* 17:   */   private IInventory lowerChestInventory;
/* 18:   */   protected EntityPlayer player;
/* 19:   */   
/* 20:   */   public GuiPlayerInventory(ContainerBDChest container, IInventory par1IInventory, EntityPlayer player)
/* 21:   */   {
/* 22:21 */     super(container);
/* 23:22 */     this.player = player;
/* 24:23 */     this.upperChestInventory = par1IInventory;
/* 25:24 */     this.lowerChestInventory = player.inventory;
/* 26:25 */     this.allowUserInput = false;
/* 27:26 */     short var3 = 222;
/* 28:27 */     int var4 = var3 - 108;
/* 29:28 */     int inventoryRows = this.lowerChestInventory.getSizeInventory() / 9;
/* 30:29 */     this.ySize = (var4 + inventoryRows * 18 + 28);
/* 31:   */   }
/* 32:   */   
/* 33:   */   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
/* 34:   */   {
/* 35:34 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 36:35 */     this.mc.renderEngine.bindTexture(texture);
/* 37:36 */     int width = (this.width - this.xSize) / 2;
/* 38:37 */     int height = (this.height - this.ySize) / 2;
/* 39:38 */     int offset = getPlayerInventoryOffset();
/* 40:39 */     drawTexturedModalRect(width, height + offset - 3, 0, 0, this.xSize, 17);
/* 41:40 */     drawTexturedModalRect(width, height + 0 + offset, 0, 126, this.xSize, 96);
/* 42:   */   }
/* 43:   */   
/* 44:   */   public int getPlayerInventoryOffset()
/* 45:   */   {
/* 46:44 */     return 104;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public void initGui()
/* 50:   */   {
/* 51:49 */     super.initGui();
/* 52:   */   }
/* 53:   */   
/* 54:   */   protected void handleMouseClick(Slot slot, int slotID, int x, int y)
/* 55:   */   {
/* 56:55 */     super.handleMouseClick(slot, slotID, x, y);
/* 57:   */   }
/* 58:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiPlayerInventory
 * JD-Core Version:    0.7.1
 */