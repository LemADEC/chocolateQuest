/*   1:    */ package com.chocolate.chocolateQuest.block;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.player.EntityPlayer;
/*   4:    */ import net.minecraft.inventory.IInventory;
/*   5:    */ import net.minecraft.item.ItemStack;
/*   6:    */ import net.minecraft.nbt.NBTTagCompound;
/*   7:    */ import net.minecraft.nbt.NBTTagList;
/*   8:    */ import net.minecraft.network.NetworkManager;
/*   9:    */ import net.minecraft.network.Packet;
/*  10:    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*  11:    */ import net.minecraft.tileentity.TileEntity;
/*  12:    */ 
/*  13:    */ public class BlockDungeonChestTileEntity
/*  14:    */   extends TileEntity
/*  15:    */   implements IInventory
/*  16:    */ {
/*  17:    */   ItemStack[] container;
/*  18:    */   
/*  19:    */   public BlockDungeonChestTileEntity()
/*  20:    */   {
/*  21: 18 */     this.container = new ItemStack[getSizeInventory()];
/*  22:    */   }
/*  23:    */   
/*  24:    */   public Packet getDescriptionPacket()
/*  25:    */   {
/*  26: 23 */     NBTTagCompound data = new NBTTagCompound();
/*  27: 24 */     writeInventoryToNBT(data);
/*  28: 25 */     return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, data);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*  32:    */   {
/*  33: 30 */     NBTTagCompound nbt = pkt.func_148857_g();
/*  34: 31 */     readInventoryFromNBT(nbt);
/*  35: 32 */     super.onDataPacket(net, pkt);
/*  36:    */   }
/*  37:    */   
/*  38:    */   public void readFromNBT(NBTTagCompound nbt)
/*  39:    */   {
/*  40: 37 */     super.readFromNBT(nbt);
/*  41: 38 */     this.container = readInventoryFromNBT(nbt);
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void writeToNBT(NBTTagCompound nbt)
/*  45:    */   {
/*  46: 43 */     super.writeToNBT(nbt);
/*  47: 44 */     writeInventoryToNBT(nbt);
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void writeInventoryToNBT(NBTTagCompound nbt)
/*  51:    */   {
/*  52: 49 */     NBTTagList list = new NBTTagList();
/*  53: 50 */     for (int i = 0; i < this.container.length; i++) {
/*  54: 52 */       if (this.container[i] != null)
/*  55:    */       {
/*  56: 54 */         NBTTagCompound slotnbttagcompound = new NBTTagCompound();
/*  57: 55 */         slotnbttagcompound.setByte("Slot", (byte)i);
/*  58: 56 */         this.container[i].writeToNBT(slotnbttagcompound);
/*  59: 57 */         list.appendTag(slotnbttagcompound);
/*  60:    */       }
/*  61:    */     }
/*  62: 60 */     nbt.setTag("Items", list);
/*  63:    */   }
/*  64:    */   
/*  65:    */   public ItemStack[] readInventoryFromNBT(NBTTagCompound nbt)
/*  66:    */   {
/*  67: 64 */     ItemStack[] container = new ItemStack[getSizeInventory()];
/*  68: 65 */     NBTTagList list = nbt.getTagList("Items", nbt.getId());
/*  69: 66 */     if (list != null) {
/*  70: 68 */       for (int i = 0; i < list.tagCount(); i++)
/*  71:    */       {
/*  72: 70 */         NBTTagCompound slotnbttagcompound = list.getCompoundTagAt(i);
/*  73: 71 */         int j = slotnbttagcompound.getByte("Slot") & 0xFF;
/*  74: 72 */         if ((j >= 0) && (j < container.length)) {
/*  75: 74 */           container[j] = ItemStack.loadItemStackFromNBT(slotnbttagcompound);
/*  76:    */         }
/*  77:    */       }
/*  78:    */     }
/*  79: 78 */     return container;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void closeInventory() {}
/*  83:    */   
/*  84:    */   public ItemStack decrStackSize(int slot, int amount)
/*  85:    */   {
/*  86: 90 */     if (this.container[slot] != null)
/*  87:    */     {
/*  88: 92 */       if (this.container[slot].stackSize <= amount)
/*  89:    */       {
/*  90: 93 */         ItemStack itemstack = this.container[slot];
/*  91: 94 */         this.container[slot] = null;
/*  92: 95 */         return itemstack;
/*  93:    */       }
/*  94: 98 */       ItemStack itemstack1 = this.container[slot].splitStack(amount);
/*  95:100 */       if (this.container[slot].stackSize == 0) {
/*  96:102 */         this.container[slot] = null;
/*  97:    */       }
/*  98:105 */       return itemstack1;
/*  99:    */     }
/* 100:109 */     return null;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public String getInventoryName()
/* 104:    */   {
/* 105:115 */     return "Chest name";
/* 106:    */   }
/* 107:    */   
/* 108:    */   public int getInventoryStackLimit()
/* 109:    */   {
/* 110:120 */     return 64;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public int getSizeInventory()
/* 114:    */   {
/* 115:125 */     return 27;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public ItemStack getStackInSlot(int slot)
/* 119:    */   {
/* 120:130 */     return this.container[slot];
/* 121:    */   }
/* 122:    */   
/* 123:    */   public ItemStack getStackInSlotOnClosing(int slot)
/* 124:    */   {
/* 125:135 */     return null;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public boolean hasCustomInventoryName()
/* 129:    */   {
/* 130:140 */     return false;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public boolean isItemValidForSlot(int slot, ItemStack stack)
/* 134:    */   {
/* 135:145 */     return false;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public boolean isUseableByPlayer(EntityPlayer player)
/* 139:    */   {
/* 140:150 */     return true;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public void openInventory() {}
/* 144:    */   
/* 145:    */   public void setInventorySlotContents(int slot, ItemStack stack)
/* 146:    */   {
/* 147:160 */     this.container[slot] = stack;
/* 148:161 */     if ((stack != null) && (stack.stackSize > getInventoryStackLimit())) {
/* 149:163 */       stack.stackSize = getInventoryStackLimit();
/* 150:    */     }
/* 151:    */   }
/* 152:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockDungeonChestTileEntity
 * JD-Core Version:    0.7.1
 */