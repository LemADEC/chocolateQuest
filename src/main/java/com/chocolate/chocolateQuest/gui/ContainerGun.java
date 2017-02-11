/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.gui.slot.SlotAmmo;
/*  4:   */ import com.chocolate.chocolateQuest.items.gun.ILoadableGun;
/*  5:   */ import java.util.List;
/*  6:   */ import net.minecraft.entity.player.EntityPlayer;
/*  7:   */ import net.minecraft.inventory.IInventory;
/*  8:   */ import net.minecraft.inventory.Slot;
/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ 
/* 11:   */ public class ContainerGun
/* 12:   */   extends ContainerBDChest
/* 13:   */ {
/* 14:   */   ItemStack itemstackGun;
/* 15:   */   
/* 16:   */   public ContainerGun(IInventory playerInventory, IInventory chestInventory, ItemStack is)
/* 17:   */   {
/* 18:14 */     super(playerInventory, chestInventory);
/* 19:15 */     this.itemstackGun = is;
/* 20:16 */     layoutInventory(chestInventory);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void layoutInventory(IInventory chestInventory)
/* 24:   */   {
/* 25:21 */     if (this.itemstackGun == null) {
/* 26:22 */       return;
/* 27:   */     }
/* 28:23 */     ILoadableGun gun = (ILoadableGun)this.itemstackGun.getItem();
/* 29:24 */     for (int i = 0; i < chestInventory.getSizeInventory(); i++) {
/* 30:25 */       addSlotToContainer(new SlotAmmo(chestInventory, i, 10 + i * 16, 10, gun));
/* 31:   */     }
/* 32:   */   }
/* 33:   */   
/* 34:   */   public ItemStack transferStackInSlot(EntityPlayer player, int index)
/* 35:   */   {
/* 36:31 */     Slot slot = (Slot)this.inventorySlots.get(index);
/* 37:32 */     if ((slot != null) && (slot.getHasStack()))
/* 38:   */     {
/* 39:34 */       ItemStack slotItemStack = slot.getStack();
/* 40:35 */       if (slotItemStack == this.itemstackGun) {
/* 41:36 */         return null;
/* 42:   */       }
/* 43:   */     }
/* 44:39 */     return super.transferStackInSlot(player, index);
/* 45:   */   }
/* 46:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.ContainerGun
 * JD-Core Version:    0.7.1
 */