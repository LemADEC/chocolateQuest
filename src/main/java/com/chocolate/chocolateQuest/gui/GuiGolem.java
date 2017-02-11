/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  5:   */ import net.minecraft.client.Minecraft;
/*  6:   */ import net.minecraft.client.renderer.texture.TextureManager;
/*  7:   */ import net.minecraft.entity.player.EntityPlayer;
/*  8:   */ import net.minecraft.inventory.IInventory;
/*  9:   */ 
/* 10:   */ public class GuiGolem
/* 11:   */   extends GuiHuman
/* 12:   */ {
/* 13:   */   public GuiGolem(EntityHumanBase human, IInventory par1IInventory, EntityPlayer playerInventory)
/* 14:   */   {
/* 15:12 */     super(new ContainerGolemInventory(playerInventory.inventory, par1IInventory), human, par1IInventory, playerInventory);
/* 16:   */   }
/* 17:   */   
/* 18:   */   protected void drawEquipementPanel()
/* 19:   */   {
/* 20:16 */     this.mc.renderEngine.bindTexture(BDHelper.guiButtonsTexture);
/* 21:17 */     int width = (this.width - this.xSize) / 2;
/* 22:18 */     int height = this.height - this.height / 2 - 86;
/* 23:19 */     drawTexturedModalRect(width - 10, height, 0, 144, 56, 74);
/* 24:   */   }
/* 25:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiGolem
 * JD-Core Version:    0.7.1
 */