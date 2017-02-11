/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.gui.slot.SlotLockedToClass;
/*  5:   */ import net.minecraft.inventory.IInventory;
/*  6:   */ import net.minecraft.inventory.Slot;
/*  7:   */ 
/*  8:   */ public class ContainerGolemInventory
/*  9:   */   extends ContainerHumanInventory
/* 10:   */ {
/* 11:   */   public ContainerGolemInventory(IInventory playerInventory, IInventory chestInventory)
/* 12:   */   {
/* 13:12 */     super(playerInventory, chestInventory);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public void layoutInventory(IInventory chestInventory)
/* 17:   */   {
/* 18:18 */     int yPos = 14;
/* 19:19 */     addSlotToContainer(new Slot(chestInventory, 0, -8, yPos + 28));
/* 20:20 */     for (int i = 0; i < 4; i++) {
/* 21:21 */       addSlotToContainer(new SlotLockedToClass(chestInventory, 4 - i, 10, yPos + 10 + i * 18, ChocolateQuest.golemUpgrade));
/* 22:   */     }
/* 23:23 */     addSlotToContainer(new Slot(chestInventory, 5, 28, yPos + 28));
/* 24:   */   }
/* 25:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.ContainerGolemInventory
 * JD-Core Version:    0.7.1
 */