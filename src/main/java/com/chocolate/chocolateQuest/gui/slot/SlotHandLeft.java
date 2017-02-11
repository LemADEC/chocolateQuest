/*  1:   */ package com.chocolate.chocolateQuest.gui.slot;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.API.ITwoHandedItem;
/*  4:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  5:   */ import com.chocolate.chocolateQuest.items.swords.ItemBaseSwordDefensive;
/*  6:   */ import net.minecraft.entity.player.EntityPlayer;
/*  7:   */ import net.minecraft.inventory.IInventory;
/*  8:   */ import net.minecraft.inventory.Slot;
/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ 
/* 11:   */ public class SlotHandLeft
/* 12:   */   extends Slot
/* 13:   */ {
/* 14:   */   Slot rightHand;
/* 15:   */   
/* 16:   */   public SlotHandLeft(IInventory arg0, int id, int x, int y, Slot rightHand)
/* 17:   */   {
/* 18:15 */     super(arg0, id, x, y);
/* 19:16 */     this.rightHand = rightHand;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean isItemValid(ItemStack par1ItemStack)
/* 23:   */   {
/* 24:20 */     if (isItemTwoHanded(par1ItemStack)) {
/* 25:21 */       return false;
/* 26:   */     }
/* 27:23 */     if ((this.rightHand.getHasStack()) && 
/* 28:24 */       (isItemTwoHanded(this.rightHand.getStack()))) {
/* 29:25 */       return false;
/* 30:   */     }
/* 31:27 */     return super.isItemValid(par1ItemStack);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public boolean isItemTwoHanded(ItemStack is)
/* 35:   */   {
/* 36:31 */     if (((is.getItem() instanceof ITwoHandedItem)) || ((is.getItem() instanceof ItemBaseSwordDefensive))) {
/* 37:32 */       return true;
/* 38:   */     }
/* 39:33 */     return false;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public boolean canTakeStack(EntityPlayer par1EntityPlayer)
/* 43:   */   {
/* 44:37 */     if ((getStack() != null) && 
/* 45:38 */       (getStack().getItem() == ChocolateQuest.shield)) {
/* 46:39 */       return this.rightHand.getStack() == null;
/* 47:   */     }
/* 48:42 */     return super.canTakeStack(par1EntityPlayer);
/* 49:   */   }
/* 50:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.slot.SlotHandLeft
 * JD-Core Version:    0.7.1
 */