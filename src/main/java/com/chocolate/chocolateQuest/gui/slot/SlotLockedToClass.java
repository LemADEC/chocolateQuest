/*  1:   */ package com.chocolate.chocolateQuest.gui.slot;
/*  2:   */ 
/*  3:   */ import net.minecraft.inventory.IInventory;
/*  4:   */ import net.minecraft.inventory.Slot;
/*  5:   */ import net.minecraft.item.Item;
/*  6:   */ import net.minecraft.item.ItemStack;
/*  7:   */ 
/*  8:   */ public class SlotLockedToClass
/*  9:   */   extends Slot
/* 10:   */ {
/* 11:   */   Item item;
/* 12:   */   
/* 13:   */   public SlotLockedToClass(IInventory par1iInventory, int par2, int par3, int par4, Item item)
/* 14:   */   {
/* 15:12 */     super(par1iInventory, par2, par3, par4);
/* 16:13 */     this.item = item;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public boolean isItemValid(ItemStack is)
/* 20:   */   {
/* 21:18 */     return is.getItem() == this.item;
/* 22:   */   }
/* 23:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.slot.SlotLockedToClass
 * JD-Core Version:    0.7.1
 */