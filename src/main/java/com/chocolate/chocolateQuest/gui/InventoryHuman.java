/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ 
/*  7:   */ public class InventoryHuman
/*  8:   */   extends InventoryCargo
/*  9:   */ {
/* 10:   */   EntityHumanBase human;
/* 11:   */   int tempid;
/* 12:   */   
/* 13:   */   public InventoryHuman(EntityHumanBase human)
/* 14:   */   {
/* 15:15 */     this.cargoItems = new ItemStack[getSizeInventory()];
/* 16:16 */     for (int i = 0; i < 5; i++) {
/* 17:17 */       this.cargoItems[i] = human.getEquipmentInSlot(i);
/* 18:   */     }
/* 19:18 */     this.cargoItems[5] = human.leftHandItem;
/* 20:19 */     this.human = human;
/* 21:20 */     if (human.potionCount > 0) {
/* 22:21 */       this.cargoItems[6] = new ItemStack(ChocolateQuest.potion, human.potionCount, 0);
/* 23:   */     }
/* 24:   */   }
/* 25:   */   
/* 26:   */   public int getSizeInventory()
/* 27:   */   {
/* 28:27 */     return 7;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void updateInventory()
/* 32:   */   {
/* 33:31 */     for (int i = 0; i < 5; i++) {
/* 34:32 */       this.human.setCurrentItemOrArmor(i, this.cargoItems[i]);
/* 35:   */     }
/* 36:33 */     this.human.leftHandItem = this.cargoItems[5];
/* 37:35 */     if (this.cargoItems[6] != null) {
/* 38:36 */       this.human.potionCount = this.cargoItems[6].stackSize;
/* 39:   */     } else {
/* 40:38 */       this.human.potionCount = 0;
/* 41:   */     }
/* 42:   */   }
/* 43:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.InventoryHuman
 * JD-Core Version:    0.7.1
 */