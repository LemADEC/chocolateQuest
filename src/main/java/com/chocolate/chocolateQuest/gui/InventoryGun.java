/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.items.gun.ILoadableGun;
/*  4:   */ import net.minecraft.entity.player.EntityPlayer;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ 
/*  7:   */ public class InventoryGun
/*  8:   */   extends InventoryBag
/*  9:   */ {
/* 10:   */   ILoadableGun gun;
/* 11:   */   
/* 12:   */   public InventoryGun(ItemStack item, EntityPlayer player)
/* 13:   */   {
/* 14:13 */     super(item, player);
/* 15:14 */     this.gun = ((ILoadableGun)item.getItem());
/* 16:   */   }
/* 17:   */   
/* 18:   */   public int getSizeInventory()
/* 19:   */   {
/* 20:20 */     if (this.gun == null) {
/* 21:21 */       return super.getSizeInventory();
/* 22:   */     }
/* 23:22 */     return this.gun.getAmmoLoaderAmmount(this.container);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public String getInventoryName()
/* 27:   */   {
/* 28:28 */     return "Gun";
/* 29:   */   }
/* 30:   */   
/* 31:   */   public ItemStack getStackInSlotOnClosing(int i)
/* 32:   */   {
/* 33:44 */     return null;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public int getInventoryStackLimit()
/* 37:   */   {
/* 38:50 */     return this.gun.getAmmoLoaderStackSize(this.container);
/* 39:   */   }
/* 40:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.InventoryGun
 * JD-Core Version:    0.7.1
 */