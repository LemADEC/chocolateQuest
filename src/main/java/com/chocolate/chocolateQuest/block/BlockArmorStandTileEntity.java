/*   1:    */ package com.chocolate.chocolateQuest.block;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.entity.player.EntityPlayer;
/*   6:    */ import net.minecraft.inventory.IInventory;
/*   7:    */ import net.minecraft.item.ItemStack;
/*   8:    */ import net.minecraft.nbt.NBTTagCompound;
/*   9:    */ import net.minecraft.network.NetworkManager;
/*  10:    */ import net.minecraft.network.Packet;
/*  11:    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*  12:    */ import net.minecraft.tileentity.TileEntity;
/*  13:    */ import net.minecraft.world.World;
/*  14:    */ 
/*  15:    */ public class BlockArmorStandTileEntity
/*  16:    */   extends TileEntity
/*  17:    */   implements IInventory
/*  18:    */ {
/*  19:    */   public ItemStack[] cargoItems;
/*  20: 17 */   public int rotation = 0;
/*  21:    */   
/*  22:    */   public BlockArmorStandTileEntity()
/*  23:    */   {
/*  24: 20 */     this.cargoItems = new ItemStack[6];
/*  25:    */   }
/*  26:    */   
/*  27:    */   public boolean anyPlayerInRange()
/*  28:    */   {
/*  29: 28 */     return this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, 16.0D) != null;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void updateEntity()
/*  33:    */   {
/*  34: 37 */     super.updateEntity();
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void readFromNBT(NBTTagCompound nbttagcompound)
/*  38:    */   {
/*  39: 45 */     super.readFromNBT(nbttagcompound);
/*  40: 46 */     this.rotation = nbttagcompound.getInteger("rot");
/*  41: 47 */     for (int i = 0; i < this.cargoItems.length; i++)
/*  42:    */     {
/*  43: 49 */       NBTTagCompound itemNBT = (NBTTagCompound)nbttagcompound.getTag("CItem" + i);
/*  44: 50 */       if (itemNBT != null) {
/*  45: 51 */         this.cargoItems[i] = ItemStack.loadItemStackFromNBT(itemNBT);
/*  46:    */       }
/*  47:    */     }
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void writeToNBT(NBTTagCompound nbttagcompound)
/*  51:    */   {
/*  52: 60 */     super.writeToNBT(nbttagcompound);
/*  53: 61 */     nbttagcompound.setInteger("rot", this.rotation);
/*  54: 62 */     for (int i = 0; i < this.cargoItems.length; i++) {
/*  55: 64 */       if (this.cargoItems[i] != null)
/*  56:    */       {
/*  57: 66 */         NBTTagCompound itemNBT = new NBTTagCompound();
/*  58: 67 */         nbttagcompound.setTag("CItem" + i, this.cargoItems[i].writeToNBT(itemNBT));
/*  59:    */       }
/*  60:    */     }
/*  61:    */   }
/*  62:    */   
/*  63:    */   public Packet getDescriptionPacket()
/*  64:    */   {
/*  65: 75 */     NBTTagCompound data = new NBTTagCompound();
/*  66:    */     
/*  67: 77 */     data.setInteger("rot", this.rotation);
/*  68: 78 */     for (int i = 0; i < this.cargoItems.length; i++) {
/*  69: 80 */       if (this.cargoItems[i] != null)
/*  70:    */       {
/*  71: 82 */         NBTTagCompound itemNBT = new NBTTagCompound();
/*  72: 83 */         data.setTag("CItem" + i, this.cargoItems[i].writeToNBT(itemNBT));
/*  73:    */       }
/*  74:    */     }
/*  75: 86 */     return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, data);
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*  79:    */   {
/*  80: 92 */     NBTTagCompound tag = packet.func_148857_g();
/*  81: 93 */     this.rotation = tag.getInteger("rot");
/*  82: 94 */     for (int i = 0; i < this.cargoItems.length; i++)
/*  83:    */     {
/*  84: 96 */       NBTTagCompound item = (NBTTagCompound)tag.getTag("CItem" + i);
/*  85: 97 */       if (item != null) {
/*  86: 98 */         this.cargoItems[i] = ItemStack.loadItemStackFromNBT(item);
/*  87:    */       }
/*  88:    */     }
/*  89:    */   }
/*  90:    */   
/*  91:    */   @SideOnly(Side.CLIENT)
/*  92:    */   public double getMaxRenderDistanceSquared()
/*  93:    */   {
/*  94:106 */     return 6400.0D;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public int getSizeInventory()
/*  98:    */   {
/*  99:114 */     return this.cargoItems.length;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void updateInventory() {}
/* 103:    */   
/* 104:    */   public ItemStack getStackInSlot(int i)
/* 105:    */   {
/* 106:124 */     return this.cargoItems[i];
/* 107:    */   }
/* 108:    */   
/* 109:    */   public ItemStack decrStackSize(int i, int j)
/* 110:    */   {
/* 111:129 */     if (this.cargoItems[i] != null)
/* 112:    */     {
/* 113:131 */       if (this.cargoItems[i].stackSize <= j)
/* 114:    */       {
/* 115:133 */         ItemStack itemstack = this.cargoItems[i];
/* 116:134 */         this.cargoItems[i] = null;
/* 117:135 */         return itemstack;
/* 118:    */       }
/* 119:138 */       ItemStack itemstack1 = this.cargoItems[i].splitStack(j);
/* 120:140 */       if (this.cargoItems[i].stackSize == 0) {
/* 121:142 */         this.cargoItems[i] = null;
/* 122:    */       }
/* 123:144 */       return itemstack1;
/* 124:    */     }
/* 125:148 */     return null;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public ItemStack getStackInSlotOnClosing(int i)
/* 129:    */   {
/* 130:154 */     if (this.cargoItems[i] != null)
/* 131:    */     {
/* 132:156 */       ItemStack is = this.cargoItems[i];
/* 133:157 */       this.cargoItems[i] = null;
/* 134:158 */       return is;
/* 135:    */     }
/* 136:162 */     return null;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public void setInventorySlotContents(int i, ItemStack itemstack)
/* 140:    */   {
/* 141:168 */     this.cargoItems[i] = itemstack;
/* 142:169 */     if ((itemstack != null) && (itemstack.stackSize > getInventoryStackLimit())) {
/* 143:171 */       itemstack.stackSize = getInventoryStackLimit();
/* 144:    */     }
/* 145:173 */     updateInventory();
/* 146:    */   }
/* 147:    */   
/* 148:    */   public int getInventoryStackLimit()
/* 149:    */   {
/* 150:178 */     return 64;
/* 151:    */   }
/* 152:    */   
/* 153:    */   public boolean isUseableByPlayer(EntityPlayer entityplayer)
/* 154:    */   {
/* 155:183 */     return true;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public boolean isItemValidForSlot(int i, ItemStack itemstack)
/* 159:    */   {
/* 160:187 */     return true;
/* 161:    */   }
/* 162:    */   
/* 163:    */   public void closeInventory()
/* 164:    */   {
/* 165:191 */     updateInventory();
/* 166:    */   }
/* 167:    */   
/* 168:    */   public String getInventoryName()
/* 169:    */   {
/* 170:195 */     return "NPC inventory";
/* 171:    */   }
/* 172:    */   
/* 173:    */   public boolean hasCustomInventoryName()
/* 174:    */   {
/* 175:199 */     return false;
/* 176:    */   }
/* 177:    */   
/* 178:    */   public void markDirty() {}
/* 179:    */   
/* 180:    */   public void openInventory() {}
/* 181:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockArmorStandTileEntity
 * JD-Core Version:    0.7.1
 */