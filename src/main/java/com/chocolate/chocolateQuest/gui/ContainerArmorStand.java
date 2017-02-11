/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.gui.slot.SlotArmor;
/*  4:   */ import net.minecraft.inventory.IInventory;
/*  5:   */ import net.minecraft.inventory.Slot;
/*  6:   */ 
/*  7:   */ public class ContainerArmorStand
/*  8:   */   extends ContainerBDChest
/*  9:   */ {
/* 10:   */   public ContainerArmorStand(IInventory playerInventory, IInventory chestInventory)
/* 11:   */   {
/* 12:10 */     super(playerInventory, chestInventory);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void layoutInventory(IInventory chestInventory)
/* 16:   */   {
/* 17:15 */     int yPos = 14;
/* 18:16 */     for (int i = 0; i < 4; i++) {
/* 19:17 */       addSlotToContainer(new SlotArmor(chestInventory, 3 - i, 10, yPos + 10 + i * 18, i));
/* 20:   */     }
/* 21:19 */     addSlotToContainer(new Slot(chestInventory, 4, -8, yPos + 28));
/* 22:20 */     addSlotToContainer(new Slot(chestInventory, 5, 28, yPos + 28));
/* 23:   */   }
/* 24:   */   
/* 25:   */   public int getPlayerInventoryY()
/* 26:   */   {
/* 27:24 */     return 190 + (getRowCount() - 4) * 18;
/* 28:   */   }
/* 29:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.ContainerArmorStand
 * JD-Core Version:    0.7.1
 */