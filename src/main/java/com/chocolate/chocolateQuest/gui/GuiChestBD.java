/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.Minecraft;
/*  4:   */ import net.minecraft.client.gui.FontRenderer;
/*  5:   */ import net.minecraft.client.gui.inventory.GuiContainer;
/*  6:   */ import net.minecraft.client.renderer.texture.TextureManager;
/*  7:   */ import net.minecraft.inventory.IInventory;
/*  8:   */ import net.minecraft.util.ResourceLocation;
/*  9:   */ import net.minecraft.util.StatCollector;
/* 10:   */ import org.lwjgl.opengl.GL11;
/* 11:   */ 
/* 12:   */ public class GuiChestBD
/* 13:   */   extends GuiContainer
/* 14:   */ {
/* 15:13 */   private static final ResourceLocation texture = new ResourceLocation("textures/gui/container/generic_54.png");
/* 16:   */   private IInventory upperChestInventory;
/* 17:   */   private IInventory lowerChestInventory;
/* 18:20 */   private int inventoryRows = 0;
/* 19:   */   
/* 20:   */   public GuiChestBD(IInventory playerInventory, IInventory chestInventory)
/* 21:   */   {
/* 22:24 */     super(new ContainerBDChest(playerInventory, chestInventory));
/* 23:25 */     this.upperChestInventory = chestInventory;
/* 24:26 */     this.lowerChestInventory = playerInventory;
/* 25:27 */     this.allowUserInput = false;
/* 26:28 */     short var3 = 222;
/* 27:29 */     int var4 = var3 - 108;
/* 28:30 */     this.inventoryRows = (chestInventory.getSizeInventory() / 9);
/* 29:31 */     this.ySize = (var4 + this.inventoryRows * 18);
/* 30:   */   }
/* 31:   */   
/* 32:   */   protected void drawGuiContainerForegroundLayer()
/* 33:   */   {
/* 34:39 */     this.fontRendererObj.drawString(StatCollector.translateToLocal(this.lowerChestInventory.getInventoryName()), 8, 6, 4210752);
/* 35:40 */     this.fontRendererObj.drawString(StatCollector.translateToLocal(this.upperChestInventory.getInventoryName()), 8, this.ySize - 96 + 2, 4210752);
/* 36:   */   }
/* 37:   */   
/* 38:   */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
/* 39:   */   {
/* 40:48 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 41:49 */     this.mc.renderEngine.bindTexture(texture);
/* 42:50 */     int width = (this.width - this.xSize) / 2;
/* 43:51 */     int height = (this.height - this.ySize) / 2;
/* 44:52 */     drawTexturedModalRect(width, height, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
/* 45:53 */     drawTexturedModalRect(width, height + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
/* 46:   */   }
/* 47:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiChestBD
 * JD-Core Version:    0.7.1
 */