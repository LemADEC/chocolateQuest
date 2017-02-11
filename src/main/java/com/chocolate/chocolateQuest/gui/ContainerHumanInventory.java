/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.gui.slot.SlotArmor;
/*  4:   */ import com.chocolate.chocolateQuest.gui.slot.SlotHandLeft;
/*  5:   */ import com.chocolate.chocolateQuest.gui.slot.SlotHandRight;
/*  6:   */ import net.minecraft.inventory.IInventory;
/*  7:   */ import net.minecraft.inventory.Slot;
/*  8:   */ 
/*  9:   */ public class ContainerHumanInventory
/* 10:   */   extends ContainerBDChest
/* 11:   */ {
/* 12:   */   public ContainerHumanInventory(IInventory playerInventory, IInventory chestInventory)
/* 13:   */   {
/* 14:15 */     super(playerInventory, chestInventory);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void layoutInventory(IInventory chestInventory)
/* 18:   */   {
/* 19:21 */     int yPos = 14;
/* 20:22 */     SlotHandRight rightHandSlot = new SlotHandRight(chestInventory, 0, -8, yPos + 28);
/* 21:23 */     addSlotToContainer(rightHandSlot);
/* 22:24 */     for (int i = 0; i < 4; i++) {
/* 23:25 */       addSlotToContainer(new SlotArmor(chestInventory, 4 - i, 10, yPos + 10 + i * 18, i));
/* 24:   */     }
/* 25:27 */     Slot leftHandSlot = new SlotHandLeft(chestInventory, 5, 28, yPos + 28, rightHandSlot);
/* 26:28 */     addSlotToContainer(leftHandSlot);
/* 27:29 */     rightHandSlot.setOpossedHandSlot(leftHandSlot);
/* 28:   */     
/* 29:31 */     Slot potions = new Slot(chestInventory, 6, 28, yPos + 28 + 16);
/* 30:32 */     addSlotToContainer(potions);
/* 31:   */   }
/* 32:   */   
/* 33:   */   public int getPlayerInventoryY()
/* 34:   */   {
/* 35:36 */     return 190 + (getRowCount() - 4) * 18;
/* 36:   */   }
/* 37:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.ContainerHumanInventory
 * JD-Core Version:    0.7.1
 */