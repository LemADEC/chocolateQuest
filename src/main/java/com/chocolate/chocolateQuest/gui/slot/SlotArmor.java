/*  1:   */ package com.chocolate.chocolateQuest.gui.slot;
/*  2:   */ 
/*  3:   */ import net.minecraft.inventory.IInventory;
/*  4:   */ import net.minecraft.inventory.Slot;
/*  5:   */ import net.minecraft.item.Item;
/*  6:   */ import net.minecraft.item.ItemArmor;
/*  7:   */ import net.minecraft.item.ItemBlock;
/*  8:   */ import net.minecraft.item.ItemStack;
/*  9:   */ 
/* 10:   */ public class SlotArmor
/* 11:   */   extends Slot
/* 12:   */ {
/* 13:   */   int armorSlot;
/* 14:   */   
/* 15:   */   public SlotArmor(IInventory par1iInventory, int par2, int par3, int par4, int armorSlot)
/* 16:   */   {
/* 17:12 */     super(par1iInventory, par2, par3, par4);
/* 18:13 */     this.armorSlot = armorSlot;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public boolean isItemValid(ItemStack par1ItemStack)
/* 22:   */   {
/* 23:18 */     if (this.armorSlot == 0) {
/* 24:19 */       return (par1ItemStack.getItem().isValidArmor(par1ItemStack, this.armorSlot, null)) || ((par1ItemStack.getItem() instanceof ItemBlock));
/* 25:   */     }
/* 26:21 */     if ((par1ItemStack.getItem() instanceof ItemArmor)) {
/* 27:22 */       return ((ItemArmor)par1ItemStack.getItem()).armorType == this.armorSlot;
/* 28:   */     }
/* 29:24 */     return false;
/* 30:   */   }
/* 31:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.slot.SlotArmor
 * JD-Core Version:    0.7.1
 */