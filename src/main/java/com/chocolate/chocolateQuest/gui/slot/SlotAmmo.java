/*  1:   */ package com.chocolate.chocolateQuest.gui.slot;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.items.gun.ILoadableGun;
/*  4:   */ import net.minecraft.inventory.IInventory;
/*  5:   */ import net.minecraft.inventory.Slot;
/*  6:   */ import net.minecraft.item.ItemStack;
/*  7:   */ 
/*  8:   */ public class SlotAmmo
/*  9:   */   extends Slot
/* 10:   */ {
/* 11:   */   ILoadableGun gun;
/* 12:   */   
/* 13:   */   public SlotAmmo(IInventory par1iInventory, int par2, int par3, int par4, ILoadableGun gun)
/* 14:   */   {
/* 15:12 */     super(par1iInventory, par2, par3, par4);
/* 16:13 */     this.gun = gun;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public boolean isItemValid(ItemStack is)
/* 20:   */   {
/* 21:18 */     return this.gun.isValidAmmo(is);
/* 22:   */   }
/* 23:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.slot.SlotAmmo
 * JD-Core Version:    0.7.1
 */