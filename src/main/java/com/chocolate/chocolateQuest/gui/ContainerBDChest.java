/*   1:    */ package com.chocolate.chocolateQuest.gui;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.player.EntityPlayer;
/*   5:    */ import net.minecraft.entity.player.InventoryPlayer;
/*   6:    */ import net.minecraft.inventory.Container;
/*   7:    */ import net.minecraft.inventory.IInventory;
/*   8:    */ import net.minecraft.inventory.Slot;
/*   9:    */ import net.minecraft.item.ItemStack;
/*  10:    */ 
/*  11:    */ public class ContainerBDChest
/*  12:    */   extends Container
/*  13:    */ {
/*  14:    */   protected EntityPlayer player;
/*  15:    */   protected IInventory chest;
/*  16:    */   
/*  17:    */   public ContainerBDChest(IInventory playerInventory, IInventory chestInventory)
/*  18:    */   {
/*  19: 17 */     this.chest = chestInventory;
/*  20: 18 */     this.player = ((InventoryPlayer)playerInventory).player;
/*  21: 19 */     chestInventory.openInventory();
/*  22: 20 */     layoutContainer(playerInventory, chestInventory);
/*  23:    */   }
/*  24:    */   
/*  25:    */   public boolean canInteractWith(EntityPlayer player)
/*  26:    */   {
/*  27: 26 */     return this.chest.isUseableByPlayer(player);
/*  28:    */   }
/*  29:    */   
/*  30:    */   public void onContainerClosed(EntityPlayer entityplayer)
/*  31:    */   {
/*  32: 32 */     this.chest.closeInventory();
/*  33: 33 */     super.onContainerClosed(entityplayer);
/*  34:    */   }
/*  35:    */   
/*  36:    */   protected void layoutContainer(IInventory playerInventory, IInventory chestInventory)
/*  37:    */   {
/*  38: 38 */     layoutPlayerInventory(playerInventory);
/*  39: 39 */     layoutInventory(chestInventory);
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void layoutPlayerInventory(IInventory playerInventory)
/*  43:    */   {
/*  44: 44 */     int leftCol = 8;
/*  45: 45 */     int yPos = getPlayerInventoryY();
/*  46: 47 */     for (int row = 0; row < 3; row++) {
/*  47: 49 */       for (int col = 0; col < 9; col++) {
/*  48: 51 */         addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, leftCol + col * 18, 0 + row * 18 + yPos));
/*  49:    */       }
/*  50:    */     }
/*  51: 55 */     for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
/*  52: 57 */       addSlotToContainer(new Slot(playerInventory, hotbarSlot, leftCol + hotbarSlot * 18, 58 + yPos));
/*  53:    */     }
/*  54:    */   }
/*  55:    */   
/*  56:    */   public int getPlayerInventoryY()
/*  57:    */   {
/*  58: 61 */     return 60;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void layoutInventory(IInventory chestInventory)
/*  62:    */   {
/*  63: 66 */     for (int chestRow = 0; chestRow < getRowCount(); chestRow++) {
/*  64: 68 */       for (int chestCol = 0; chestCol < getRowLength(); chestCol++)
/*  65:    */       {
/*  66: 70 */         int index = chestCol + chestRow * getRowLength();
/*  67: 71 */         addSlotToContainer(new Slot(chestInventory, index, 8 + chestCol * 18, 18 + chestRow * 18));
/*  68:    */       }
/*  69:    */     }
/*  70:    */   }
/*  71:    */   
/*  72:    */   public int getRowLength()
/*  73:    */   {
/*  74: 79 */     return 9;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public int getRowCount()
/*  78:    */   {
/*  79: 84 */     return 0;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public ItemStack transferStackInSlot(EntityPlayer player, int index)
/*  83:    */   {
/*  84: 94 */     ItemStack slotItemStackCopy = null;
/*  85: 95 */     Slot slot = (Slot)this.inventorySlots.get(index);
/*  86: 96 */     if ((slot != null) && (slot.getHasStack()))
/*  87:    */     {
/*  88: 98 */       ItemStack slotItemStack = slot.getStack();
/*  89: 99 */       int playerInventorySize = this.inventorySlots.size() - this.chest.getSizeInventory();
/*  90:100 */       if (index < playerInventorySize) {
/*  91:102 */         for (int i = 0; i < this.inventorySlots.size() - playerInventorySize; i++)
/*  92:    */         {
/*  93:103 */           Slot chestSlot = (Slot)this.inventorySlots.get(playerInventorySize + i);
/*  94:104 */           if (chestSlot.isItemValid(slotItemStack))
/*  95:    */           {
/*  96:105 */             if (chestSlot.getStack() == null)
/*  97:    */             {
/*  98:107 */               int restingItems = slotItemStack.stackSize - this.chest.getInventoryStackLimit();
/*  99:108 */               if (restingItems > 0) {
/* 100:109 */                 slotItemStackCopy = slotItemStack.splitStack(restingItems);
/* 101:    */               } else {
/* 102:111 */                 slotItemStackCopy = chestSlot.getStack();
/* 103:    */               }
/* 104:112 */               chestSlot.putStack(slotItemStack);
/* 105:113 */               slot.putStack(slotItemStackCopy);
/* 106:114 */               slot.onSlotChanged();
/* 107:115 */               chestSlot.onSlotChanged();
/* 108:116 */               return null;
/* 109:    */             }
/* 110:117 */             if ((chestSlot.getStack().isStackable()) && (chestSlot.getStack().isItemEqual(slotItemStack)) && (chestSlot.getStack().stackSize < this.chest.getInventoryStackLimit()))
/* 111:    */             {
/* 112:121 */               int chestEmptyStacks = this.chest.getInventoryStackLimit() - chestSlot.getStack().stackSize;
/* 113:122 */               int restingItems = slotItemStack.stackSize - chestEmptyStacks;
/* 114:123 */               if (restingItems > 0)
/* 115:    */               {
/* 116:124 */                 slotItemStackCopy = slotItemStack.splitStack(restingItems);
/* 117:125 */                 chestSlot.getStack().stackSize += slotItemStack.stackSize;
/* 118:    */               }
/* 119:    */               else
/* 120:    */               {
/* 121:128 */                 chestSlot.getStack().stackSize += slotItemStack.stackSize;
/* 122:129 */                 slotItemStackCopy = null;
/* 123:    */               }
/* 124:131 */               slot.putStack(slotItemStackCopy);
/* 125:    */               
/* 126:133 */               slot.onSlotChanged();
/* 127:134 */               chestSlot.onSlotChanged();
/* 128:135 */               return null;
/* 129:    */             }
/* 130:    */           }
/* 131:    */         }
/* 132:140 */       } else if (!mergeItemStack(slotItemStack, 0, playerInventorySize, true)) {
/* 133:142 */         return null;
/* 134:    */       }
/* 135:145 */       if (slotItemStack.stackSize == 0)
/* 136:    */       {
/* 137:147 */         slot.onPickupFromSlot(player, slotItemStack);
/* 138:148 */         slot.putStack(null);
/* 139:    */       }
/* 140:    */       else
/* 141:    */       {
/* 142:152 */         slot.onSlotChanged();
/* 143:    */       }
/* 144:154 */       return null;
/* 145:    */     }
/* 146:156 */     return slotItemStackCopy;
/* 147:    */   }
/* 148:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.ContainerBDChest
 * JD-Core Version:    0.7.1
 */