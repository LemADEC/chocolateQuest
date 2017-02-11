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
/* 11:   */ public class SlotHandRight
/* 12:   */   extends Slot
/* 13:   */ {
/* 14:   */   Slot oppositeHand;
/* 15:   */   
/* 16:   */   public SlotHandRight(IInventory arg0, int id, int x, int y)
/* 17:   */   {
/* 18:15 */     super(arg0, id, x, y);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void setOpossedHandSlot(Slot slot)
/* 22:   */   {
/* 23:19 */     this.oppositeHand = slot;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public boolean isItemValid(ItemStack is)
/* 27:   */   {
/* 28:24 */     if ((this.oppositeHand.getHasStack()) && (
/* 29:25 */       ((is.getItem() instanceof ITwoHandedItem)) || ((is.getItem() instanceof ItemBaseSwordDefensive)))) {
/* 30:26 */       return false;
/* 31:   */     }
/* 32:28 */     return super.isItemValid(is);
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack is)
/* 36:   */   {
/* 37:32 */     if ((is.getItem() instanceof ItemBaseSwordDefensive)) {
/* 38:33 */       this.oppositeHand.putStack(null);
/* 39:   */     }
/* 40:34 */     super.onPickupFromSlot(par1EntityPlayer, is);
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void putStack(ItemStack is)
/* 44:   */   {
/* 45:39 */     if ((is != null) && 
/* 46:40 */       ((is.getItem() instanceof ItemBaseSwordDefensive))) {
/* 47:41 */       this.oppositeHand.putStack(new ItemStack(ChocolateQuest.shield, 0, ((ItemBaseSwordDefensive)is.getItem()).getShieldID(is)));
/* 48:   */     }
/* 49:43 */     super.putStack(is);
/* 50:   */   }
/* 51:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.slot.SlotHandRight
 * JD-Core Version:    0.7.1
 */