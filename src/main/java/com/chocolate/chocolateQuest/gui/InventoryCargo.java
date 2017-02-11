/*   1:    */ package com.chocolate.chocolateQuest.gui;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.player.EntityPlayer;
/*   4:    */ import net.minecraft.inventory.IInventory;
/*   5:    */ import net.minecraft.item.ItemStack;
/*   6:    */ 
/*   7:    */ public class InventoryCargo
/*   8:    */   implements IInventory
/*   9:    */ {
/*  10:    */   ItemStack[] cargoItems;
/*  11:    */   
/*  12:    */   public int getSizeInventory()
/*  13:    */   {
/*  14: 17 */     return 6;
/*  15:    */   }
/*  16:    */   
/*  17:    */   public void updateInventory() {}
/*  18:    */   
/*  19:    */   public ItemStack getStackInSlot(int i)
/*  20:    */   {
/*  21: 27 */     return this.cargoItems[i];
/*  22:    */   }
/*  23:    */   
/*  24:    */   public ItemStack decrStackSize(int i, int j)
/*  25:    */   {
/*  26: 32 */     if (this.cargoItems[i] != null)
/*  27:    */     {
/*  28: 34 */       if (this.cargoItems[i].stackSize <= j)
/*  29:    */       {
/*  30: 36 */         ItemStack itemstack = this.cargoItems[i];
/*  31: 37 */         this.cargoItems[i] = null;
/*  32: 38 */         return itemstack;
/*  33:    */       }
/*  34: 41 */       ItemStack itemstack1 = this.cargoItems[i].splitStack(j);
/*  35: 43 */       if (this.cargoItems[i].stackSize == 0) {
/*  36: 45 */         this.cargoItems[i] = null;
/*  37:    */       }
/*  38: 47 */       return itemstack1;
/*  39:    */     }
/*  40: 51 */     return null;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public ItemStack getStackInSlotOnClosing(int i)
/*  44:    */   {
/*  45: 57 */     if (this.cargoItems[i] != null)
/*  46:    */     {
/*  47: 59 */       ItemStack is = this.cargoItems[i];
/*  48: 60 */       this.cargoItems[i] = null;
/*  49: 61 */       return is;
/*  50:    */     }
/*  51: 65 */     return null;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void setInventorySlotContents(int i, ItemStack itemstack)
/*  55:    */   {
/*  56: 71 */     this.cargoItems[i] = itemstack;
/*  57: 72 */     if ((itemstack != null) && (itemstack.stackSize > getInventoryStackLimit())) {
/*  58: 74 */       itemstack.stackSize = getInventoryStackLimit();
/*  59:    */     }
/*  60: 76 */     updateInventory();
/*  61:    */   }
/*  62:    */   
/*  63:    */   public int getInventoryStackLimit()
/*  64:    */   {
/*  65: 81 */     return 64;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public boolean isUseableByPlayer(EntityPlayer entityplayer)
/*  69:    */   {
/*  70: 86 */     return true;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public boolean isItemValidForSlot(int i, ItemStack itemstack)
/*  74:    */   {
/*  75: 90 */     return true;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void closeInventory()
/*  79:    */   {
/*  80: 94 */     updateInventory();
/*  81:    */   }
/*  82:    */   
/*  83:    */   public String getInventoryName()
/*  84:    */   {
/*  85: 98 */     return "NPC inventory";
/*  86:    */   }
/*  87:    */   
/*  88:    */   public boolean hasCustomInventoryName()
/*  89:    */   {
/*  90:102 */     return false;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public void markDirty() {}
/*  94:    */   
/*  95:    */   public void openInventory() {}
/*  96:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.InventoryCargo
 * JD-Core Version:    0.7.1
 */