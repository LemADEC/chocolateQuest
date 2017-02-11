/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.player.EntityPlayer;
/*  4:   */ import net.minecraft.init.Items;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ 
/*  7:   */ public class InventoryQuiver
/*  8:   */   extends InventoryBag
/*  9:   */ {
/* 10:   */   public InventoryQuiver(ItemStack items, EntityPlayer player)
/* 11:   */   {
/* 12:11 */     super(items, player);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public int getSizeInventory()
/* 16:   */   {
/* 17:17 */     return 9;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public String getInventoryName()
/* 21:   */   {
/* 22:23 */     return "Quiver";
/* 23:   */   }
/* 24:   */   
/* 25:   */   public ItemStack getStackInSlotOnClosing(int i)
/* 26:   */   {
/* 27:29 */     if (this.cargoItems[i] != null) {
/* 28:31 */       if (this.cargoItems[i].getItem() != Items.arrow)
/* 29:   */       {
/* 30:33 */         ItemStack temp = this.cargoItems[i];
/* 31:34 */         this.cargoItems[i] = null;
/* 32:35 */         return temp;
/* 33:   */       }
/* 34:   */     }
/* 35:39 */     return null;
/* 36:   */   }
/* 37:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.InventoryQuiver
 * JD-Core Version:    0.7.1
 */