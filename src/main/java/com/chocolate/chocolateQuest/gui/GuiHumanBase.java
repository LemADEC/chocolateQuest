/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  4:   */ import net.minecraft.client.Minecraft;
/*  5:   */ import net.minecraft.client.renderer.texture.TextureManager;
/*  6:   */ import net.minecraft.entity.player.EntityPlayer;
/*  7:   */ import net.minecraft.inventory.IInventory;
/*  8:   */ 
/*  9:   */ public class GuiHumanBase
/* 10:   */   extends GuiPlayerInventory
/* 11:   */ {
/* 12:   */   public GuiHumanBase(ContainerBDChest container, IInventory par1IInventory, EntityPlayer playerInventory)
/* 13:   */   {
/* 14:13 */     super(container, par1IInventory, playerInventory);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public GuiHumanBase(IInventory par1IInventory, EntityPlayer player)
/* 18:   */   {
/* 19:17 */     this(new ContainerHumanInventory(player.inventory, par1IInventory), par1IInventory, player);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void initGui()
/* 23:   */   {
/* 24:23 */     super.initGui();
/* 25:   */   }
/* 26:   */   
/* 27:   */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
/* 28:   */   {
/* 29:28 */     super.drawGuiContainerBackgroundLayer(par1, par2, par3);
/* 30:   */     
/* 31:30 */     drawEquipementPanel();
/* 32:   */   }
/* 33:   */   
/* 34:   */   protected void drawEquipementPanel()
/* 35:   */   {
/* 36:34 */     this.mc.renderEngine.bindTexture(BDHelper.guiButtonsTexture);
/* 37:35 */     int width = (this.width - this.xSize) / 2;
/* 38:36 */     int height = this.height - this.height / 2 - 86;
/* 39:37 */     drawTexturedModalRect(width - 10, height, 0, 64, 56, 74);
/* 40:   */   }
/* 41:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiHumanBase
 * JD-Core Version:    0.7.1
 */