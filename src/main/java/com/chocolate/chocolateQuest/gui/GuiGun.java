/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.items.gun.ILoadableGun;
/*  4:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  5:   */ import net.minecraft.client.Minecraft;
/*  6:   */ import net.minecraft.client.renderer.texture.TextureManager;
/*  7:   */ import net.minecraft.entity.player.EntityPlayer;
/*  8:   */ import net.minecraft.inventory.IInventory;
/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ 
/* 11:   */ public class GuiGun
/* 12:   */   extends GuiPlayerInventory
/* 13:   */ {
/* 14:   */   private static final int ICON_SIZE = 16;
/* 15:   */   private static final int ICONS_PER_ROW = 16;
/* 16:   */   ItemStack gunItemStack;
/* 17:   */   
/* 18:   */   public GuiGun(ItemStack is, IInventory par1IInventory, EntityPlayer player)
/* 19:   */   {
/* 20:16 */     super(new ContainerGun(player.inventory, par1IInventory, is), par1IInventory, player);
/* 21:17 */     this.gunItemStack = is;
/* 22:   */   }
/* 23:   */   
/* 24:   */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
/* 25:   */   {
/* 26:22 */     super.drawGuiContainerBackgroundLayer(par1, par2, par3);
/* 27:23 */     this.mc.renderEngine.bindTexture(BDHelper.guiButtonsTexture);
/* 28:24 */     int width = (this.width - this.xSize) / 2;
/* 29:25 */     int height = (this.height - this.ySize) / 2;
/* 30:26 */     int offset = 1;
/* 31:27 */     drawTexturedModalRect(width, height + offset - 3, 64, 96, this.xSize, 32);
/* 32:   */     
/* 33:29 */     ILoadableGun gun = (ILoadableGun)this.gunItemStack.getItem();
/* 34:30 */     for (int i = 0; i < gun.getAmmoLoaderAmmount(this.gunItemStack); i++) {
/* 35:32 */       drawIcon(85, 135 + i * 16, height + 9);
/* 36:   */     }
/* 37:   */   }
/* 38:   */   
/* 39:   */   public int getPlayerInventoryOffset()
/* 40:   */   {
/* 41:38 */     return 46;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public void drawIcon(int icon, int xPos, int yPos)
/* 45:   */   {
/* 46:42 */     drawTexturedModalRect(xPos, yPos, icon % 16 * 16, icon / 16 * 16, 16, 16);
/* 47:   */   }
/* 48:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiGun
 * JD-Core Version:    0.7.1
 */