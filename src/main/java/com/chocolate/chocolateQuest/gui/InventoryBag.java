/*   1:    */ package com.chocolate.chocolateQuest.gui;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.items.gun.ILoadableGun;
/*   5:    */ import java.util.Random;
/*   6:    */ import net.minecraft.entity.player.EntityPlayer;
/*   7:    */ import net.minecraft.entity.player.InventoryPlayer;
/*   8:    */ import net.minecraft.inventory.IInventory;
/*   9:    */ import net.minecraft.item.ItemStack;
/*  10:    */ import net.minecraft.nbt.NBTTagCompound;
/*  11:    */ import net.minecraft.nbt.NBTTagList;
/*  12:    */ 
/*  13:    */ public class InventoryBag
/*  14:    */   implements IInventory
/*  15:    */ {
/*  16:    */   ItemStack[] cargoItems;
/*  17:    */   ItemStack container;
/*  18:    */   EntityPlayer player;
/*  19:    */   int tempid;
/*  20:    */   
/*  21:    */   public InventoryBag(ItemStack items, EntityPlayer player)
/*  22:    */   {
/*  23: 23 */     this.player = player;
/*  24: 24 */     this.container = items;
/*  25: 25 */     this.cargoItems = new ItemStack[getSizeInventory()];
/*  26: 27 */     if (items.stackTagCompound == null) {
/*  27: 29 */       items.stackTagCompound = new NBTTagCompound();
/*  28:    */     }
/*  29: 32 */     NBTTagList nbttaglist = items.stackTagCompound.getTagList("Items", items.stackTagCompound.getId());
/*  30: 34 */     if (nbttaglist != null)
/*  31:    */     {
/*  32: 36 */       for (int i = 0; i < nbttaglist.tagCount(); i++)
/*  33:    */       {
/*  34: 38 */         NBTTagCompound slotnbttagcompound = nbttaglist.getCompoundTagAt(i);
/*  35: 39 */         int j = slotnbttagcompound.getByte("Slot") & 0xFF;
/*  36: 41 */         if ((j >= 0) && (j < this.cargoItems.length)) {
/*  37: 43 */           this.cargoItems[j] = ItemStack.loadItemStackFromNBT(slotnbttagcompound);
/*  38:    */         }
/*  39:    */       }
/*  40:    */     }
/*  41:    */     else
/*  42:    */     {
/*  43: 49 */       this.cargoItems = new ItemStack[getSizeInventory()];
/*  44: 50 */       markDirty();
/*  45:    */     }
/*  46: 52 */     this.tempid = new Random().nextInt();
/*  47: 53 */     this.container.stackTagCompound.setInteger("tempid", this.tempid);
/*  48:    */   }
/*  49:    */   
/*  50:    */   public static ItemStack[] getCargo(ItemStack is)
/*  51:    */   {
/*  52: 57 */     if (is.stackTagCompound == null) {
/*  53: 59 */       is.stackTagCompound = new NBTTagCompound();
/*  54:    */     }
/*  55: 61 */     ItemStack[] cargoItems = new ItemStack[getSizeInventory(is)];
/*  56: 62 */     NBTTagList nbttaglist = is.stackTagCompound.getTagList("Items", is.stackTagCompound.getId());
/*  57: 63 */     if (nbttaglist != null) {
/*  58: 65 */       for (int i = 0; i < nbttaglist.tagCount(); i++)
/*  59:    */       {
/*  60: 67 */         NBTTagCompound slotnbttagcompound = nbttaglist.getCompoundTagAt(i);
/*  61: 68 */         int j = slotnbttagcompound.getByte("Slot") & 0xFF;
/*  62: 69 */         if ((j >= 0) && (j < cargoItems.length))
/*  63:    */         {
/*  64: 71 */           cargoItems[j] = ItemStack.loadItemStackFromNBT(slotnbttagcompound);
/*  65: 72 */           if ((cargoItems[j] == null) && (!slotnbttagcompound.hasNoTags())) {
/*  66: 73 */             cargoItems[j] = new ItemStack(ChocolateQuest.spell, 1, slotnbttagcompound.getShort("Damage"));
/*  67:    */           }
/*  68:    */         }
/*  69:    */       }
/*  70:    */     } else {
/*  71: 79 */       cargoItems = new ItemStack[getSizeInventory(is)];
/*  72:    */     }
/*  73: 81 */     return cargoItems;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public static void saveCargo(ItemStack container, ItemStack[] cargoItems)
/*  77:    */   {
/*  78: 85 */     NBTTagList nbttaglist = new NBTTagList();
/*  79: 86 */     for (int i = 0; i < cargoItems.length; i++) {
/*  80: 88 */       if (cargoItems[i] != null)
/*  81:    */       {
/*  82: 90 */         NBTTagCompound slotnbttagcompound = new NBTTagCompound();
/*  83: 91 */         slotnbttagcompound.setByte("Slot", (byte)i);
/*  84: 92 */         cargoItems[i].writeToNBT(slotnbttagcompound);
/*  85: 93 */         nbttaglist.appendTag(slotnbttagcompound);
/*  86:    */       }
/*  87:    */     }
/*  88: 96 */     container.stackTagCompound.setTag("Items", nbttaglist);
/*  89:    */   }
/*  90:    */   
/*  91:    */   public static int getSizeInventory(ItemStack is)
/*  92:    */   {
/*  93: 99 */     if ((is.getItem() instanceof ILoadableGun)) {
/*  94:100 */       return ((ILoadableGun)is.getItem()).getAmmoLoaderAmmount(is);
/*  95:    */     }
/*  96:102 */     return is.getItemDamage() * 9 + 9;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public int getSizeInventory()
/* 100:    */   {
/* 101:108 */     return getSizeInventory(this.container);
/* 102:    */   }
/* 103:    */   
/* 104:    */   public ItemStack getStackInSlot(int i)
/* 105:    */   {
/* 106:114 */     return this.cargoItems[i];
/* 107:    */   }
/* 108:    */   
/* 109:    */   public ItemStack decrStackSize(int i, int j)
/* 110:    */   {
/* 111:120 */     if (this.cargoItems[i] != null)
/* 112:    */     {
/* 113:122 */       if (this.cargoItems[i].stackSize <= j)
/* 114:    */       {
/* 115:124 */         ItemStack itemstack = this.cargoItems[i];
/* 116:125 */         this.cargoItems[i] = null;
/* 117:126 */         return itemstack;
/* 118:    */       }
/* 119:129 */       ItemStack itemstack1 = this.cargoItems[i].splitStack(j);
/* 120:131 */       if (this.cargoItems[i].stackSize == 0) {
/* 121:133 */         this.cargoItems[i] = null;
/* 122:    */       }
/* 123:136 */       markDirty();
/* 124:137 */       return itemstack1;
/* 125:    */     }
/* 126:141 */     return null;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public ItemStack getStackInSlotOnClosing(int i)
/* 130:    */   {
/* 131:148 */     if (this.cargoItems[i] != null) {
/* 132:150 */       if (this.cargoItems[i].getItem() == this.container.getItem())
/* 133:    */       {
/* 134:152 */         ItemStack temp = this.cargoItems[i];
/* 135:153 */         this.cargoItems[i] = null;
/* 136:154 */         saveToNBT(this.container);
/* 137:155 */         return temp;
/* 138:    */       }
/* 139:    */     }
/* 140:159 */     return null;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public void setInventorySlotContents(int i, ItemStack itemstack)
/* 144:    */   {
/* 145:165 */     this.cargoItems[i] = itemstack;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public boolean hasCustomInventoryName()
/* 149:    */   {
/* 150:170 */     return false;
/* 151:    */   }
/* 152:    */   
/* 153:    */   public String getInventoryName()
/* 154:    */   {
/* 155:175 */     return "Bag";
/* 156:    */   }
/* 157:    */   
/* 158:    */   public int getInventoryStackLimit()
/* 159:    */   {
/* 160:181 */     return 64;
/* 161:    */   }
/* 162:    */   
/* 163:    */   public void markDirty()
/* 164:    */   {
/* 165:187 */     closeInventory();
/* 166:188 */     saveToNBT(this.container);
/* 167:    */   }
/* 168:    */   
/* 169:    */   public boolean isUseableByPlayer(EntityPlayer entityplayer)
/* 170:    */   {
/* 171:194 */     if (entityplayer.inventory.getCurrentItem() == null) {
/* 172:196 */       return false;
/* 173:    */     }
/* 174:198 */     if (entityplayer.inventory.getItemStack() != null) {
/* 175:200 */       if (entityplayer.inventory.getItemStack() == this.container) {
/* 176:202 */         return false;
/* 177:    */       }
/* 178:    */     }
/* 179:206 */     return this.container.isItemEqual(entityplayer.inventory.getCurrentItem());
/* 180:    */   }
/* 181:    */   
/* 182:    */   public void openInventory() {}
/* 183:    */   
/* 184:    */   public void closeInventory()
/* 185:    */   {
/* 186:215 */     ItemStack bag = getBag();
/* 187:216 */     saveToNBT(bag);
/* 188:    */   }
/* 189:    */   
/* 190:    */   public void saveToNBT(ItemStack container)
/* 191:    */   {
/* 192:221 */     if (container == null) {
/* 193:222 */       return;
/* 194:    */     }
/* 195:223 */     if ((container.stackTagCompound == null) || (container.stackTagCompound.getInteger("tempid") != this.tempid)) {
/* 196:225 */       return;
/* 197:    */     }
/* 198:227 */     saveCargo(container, this.cargoItems);
/* 199:    */   }
/* 200:    */   
/* 201:    */   public ItemStack getBag()
/* 202:    */   {
/* 203:232 */     ItemStack container = null;
/* 204:233 */     for (int i = 0; i < this.player.inventory.getSizeInventory(); i++)
/* 205:    */     {
/* 206:235 */       ItemStack currentItemStack = this.player.inventory.getStackInSlot(i);
/* 207:236 */       if ((currentItemStack != null) && (currentItemStack.stackTagCompound != null)) {
/* 208:238 */         if (currentItemStack.stackTagCompound.getInteger("tempid") == this.tempid)
/* 209:    */         {
/* 210:240 */           container = currentItemStack;
/* 211:241 */           break;
/* 212:    */         }
/* 213:    */       }
/* 214:    */     }
/* 215:245 */     if (container == null)
/* 216:    */     {
/* 217:247 */       ItemStack currentItemStack = this.player.inventory.getItemStack();
/* 218:248 */       if ((currentItemStack != null) && (currentItemStack.stackTagCompound != null)) {
/* 219:250 */         if (currentItemStack.stackTagCompound.getInteger("tempid") == this.tempid) {
/* 220:252 */           container = currentItemStack;
/* 221:    */         }
/* 222:    */       }
/* 223:    */     }
/* 224:256 */     return container;
/* 225:    */   }
/* 226:    */   
/* 227:    */   public boolean isItemValidForSlot(int i, ItemStack itemstack)
/* 228:    */   {
/* 229:263 */     return true;
/* 230:    */   }
/* 231:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.InventoryBag
 * JD-Core Version:    0.7.1
 */