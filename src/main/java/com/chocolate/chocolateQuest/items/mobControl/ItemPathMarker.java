/*   1:    */ package com.chocolate.chocolateQuest.items.mobControl;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityCursor;
/*   4:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   5:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   6:    */ import com.chocolate.chocolateQuest.utils.HelperPlayer;
/*   7:    */ import com.chocolate.chocolateQuest.utils.Vec4I;
/*   8:    */ import cpw.mods.fml.relauncher.Side;
/*   9:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  10:    */ import java.util.ArrayList;
/*  11:    */ import java.util.Random;
/*  12:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  13:    */ import net.minecraft.entity.Entity;
/*  14:    */ import net.minecraft.entity.player.EntityPlayer;
/*  15:    */ import net.minecraft.entity.player.InventoryPlayer;
/*  16:    */ import net.minecraft.item.Item;
/*  17:    */ import net.minecraft.item.ItemStack;
/*  18:    */ import net.minecraft.nbt.NBTTagCompound;
/*  19:    */ import net.minecraft.util.ChatComponentText;
/*  20:    */ import net.minecraft.util.MovingObjectPosition;
/*  21:    */ import net.minecraft.world.World;
/*  22:    */ 
/*  23:    */ public class ItemPathMarker
/*  24:    */   extends Item
/*  25:    */ {
/*  26:    */   @SideOnly(Side.CLIENT)
/*  27:    */   public void registerIcons(IIconRegister par1IconRegister)
/*  28:    */   {
/*  29: 34 */     this.itemIcon = par1IconRegister.registerIcon("chocolatequest:pathMap");
/*  30:    */   }
/*  31:    */   
/*  32:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  33:    */   {
/*  34: 40 */     if ((entityPlayer.isSneaking()) && (itemstack.stackTagCompound != null))
/*  35:    */     {
/*  36: 42 */       spawnCursors(world, itemstack);
/*  37: 43 */       removePoint(itemstack, entityPlayer);
/*  38:    */     }
/*  39:    */     else
/*  40:    */     {
/*  41: 46 */       Entity target = HelperPlayer.getTarget(entityPlayer, world, 6.0D);
/*  42: 47 */       MovingObjectPosition mop = HelperPlayer.getMovingObjectPositionFromPlayer(entityPlayer, world, 50.0D);
/*  43: 48 */       if (mop != null) {
/*  44: 50 */         if (mop.entityHit == null)
/*  45:    */         {
/*  46: 52 */           if (itemstack.stackTagCompound == null)
/*  47:    */           {
/*  48: 54 */             itemstack.stackTagCompound = new NBTTagCompound();
/*  49: 55 */             itemstack.setItemDamage(itemRand.nextInt(16));
/*  50: 56 */             return itemstack;
/*  51:    */           }
/*  52: 58 */           if (addPoint(itemstack, entityPlayer, mop.blockX, mop.blockY, mop.blockZ)) {
/*  53: 60 */             if (world.isRemote) {
/*  54: 61 */               spawnCursors(world, itemstack);
/*  55:    */             }
/*  56:    */           }
/*  57:    */         }
/*  58:    */       }
/*  59:    */     }
/*  60: 67 */     return super.onItemRightClick(itemstack, world, entityPlayer);
/*  61:    */   }
/*  62:    */   
/*  63: 70 */   ArrayList<EntityCursor> cursors = new ArrayList();
/*  64:    */   ItemStack currentItem;
/*  65:    */   
/*  66:    */   public void onUpdate(ItemStack itemstack, World world, Entity par3Entity, int par4, boolean par5)
/*  67:    */   {
/*  68: 75 */     if (world.isRemote) {
/*  69: 77 */       if ((par3Entity instanceof EntityPlayer))
/*  70:    */       {
/*  71: 79 */         boolean itemChanged = false;
/*  72: 80 */         EntityPlayer player = (EntityPlayer)par3Entity;
/*  73: 81 */         if (player.inventory.getCurrentItem() != null)
/*  74:    */         {
/*  75: 83 */           ItemStack playerCurrentItem = player.inventory.getCurrentItem();
/*  76: 84 */           if (playerCurrentItem.getItem() == this)
/*  77:    */           {
/*  78: 86 */             if ((this.currentItem == null) || (!playerCurrentItem.isItemEqual(this.currentItem)))
/*  79:    */             {
/*  80: 88 */               this.currentItem = playerCurrentItem;
/*  81: 89 */               itemChanged = true;
/*  82:    */             }
/*  83:    */           }
/*  84:    */           else
/*  85:    */           {
/*  86: 93 */             itemChanged = true;
/*  87: 94 */             this.currentItem = null;
/*  88:    */           }
/*  89:    */         }
/*  90:    */         else
/*  91:    */         {
/*  92: 97 */           this.currentItem = null;
/*  93:    */         }
/*  94: 98 */         if (itemChanged) {
/*  95:100 */           if (this.currentItem != null) {
/*  96:101 */             spawnCursors(world, this.currentItem);
/*  97:    */           }
/*  98:    */         }
/*  99:    */       }
/* 100:    */     }
/* 101:105 */     super.onUpdate(itemstack, world, par3Entity, par4, par5);
/* 102:    */   }
/* 103:    */   
/* 104:    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
/* 105:    */   {
/* 106:110 */     if (((entity instanceof EntityHumanBase)) && (stack.stackTagCompound != null))
/* 107:    */     {
/* 108:112 */       int points = stack.stackTagCompound.getInteger("pos");
/* 109:113 */       if (points < 2) {
/* 110:114 */         return false;
/* 111:    */       }
/* 112:115 */       Vec4I[] path = new Vec4I[points];
/* 113:116 */       for (int p = 0; p < points; p++) {
/* 114:118 */         path[p] = new Vec4I(stack.stackTagCompound.getInteger("x" + p), stack.stackTagCompound.getInteger("y" + p), stack.stackTagCompound.getInteger("z" + p), 0);
/* 115:    */       }
/* 116:123 */       ((EntityHumanBase)entity).path = path;
/* 117:124 */       if (player.worldObj.isRemote) {
/* 118:125 */         player.addChatMessage(new ChatComponentText("New path for " + BDHelper.StringColor("2") + entity.getCommandSenderName()));
/* 119:    */       }
/* 120:    */     }
/* 121:131 */     return true;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public boolean hasEffect(ItemStack par1ItemStack)
/* 125:    */   {
/* 126:137 */     return par1ItemStack.stackTagCompound != null;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public boolean addPoint(ItemStack itemstack, EntityPlayer entityPlayer, int blockX, int blockY, int blockZ)
/* 130:    */   {
/* 131:142 */     int point = itemstack.stackTagCompound.getInteger("pos");
/* 132:144 */     if (point >= 50)
/* 133:    */     {
/* 134:146 */       entityPlayer.addChatMessage(new ChatComponentText("Can't add more points"));
/* 135:147 */       return false;
/* 136:    */     }
/* 137:150 */     if (point > 0)
/* 138:    */     {
/* 139:152 */       int x = itemstack.stackTagCompound.getInteger("x" + (point - 1));
/* 140:153 */       int y = itemstack.stackTagCompound.getInteger("y" + (point - 1));
/* 141:154 */       int z = itemstack.stackTagCompound.getInteger("z" + (point - 1));
/* 142:155 */       x -= blockX;
/* 143:156 */       y -= blockY;
/* 144:157 */       z -= blockZ;
/* 145:158 */       double dist = Math.sqrt(x * x + y * y + z * z);
/* 146:159 */       if (dist > 30.0D)
/* 147:    */       {
/* 148:161 */         entityPlayer.addChatMessage(new ChatComponentText("Too far from previous point"));
/* 149:162 */         return false;
/* 150:    */       }
/* 151:    */     }
/* 152:165 */     itemstack.stackTagCompound.setInteger("x" + point, blockX);
/* 153:166 */     itemstack.stackTagCompound.setInteger("y" + point, blockY);
/* 154:167 */     itemstack.stackTagCompound.setInteger("z" + point, blockZ);
/* 155:168 */     itemstack.stackTagCompound.setInteger("pos", point + 1);
/* 156:    */     
/* 157:170 */     return true;
/* 158:    */   }
/* 159:    */   
/* 160:    */   public boolean removePoint(ItemStack itemstack, EntityPlayer entityPlayer)
/* 161:    */   {
/* 162:174 */     if (itemstack.stackTagCompound != null)
/* 163:    */     {
/* 164:176 */       int currentPoint = Math.max(0, itemstack.stackTagCompound.getInteger("pos") - 1);
/* 165:177 */       itemstack.stackTagCompound.setInteger("pos", currentPoint);
/* 166:178 */       if (entityPlayer.worldObj.isRemote)
/* 167:    */       {
/* 168:180 */         EntityCursor e = (EntityCursor)this.cursors.get(currentPoint);
/* 169:181 */         if (e != null)
/* 170:    */         {
/* 171:182 */           e.item = null;
/* 172:183 */           e.setDead();
/* 173:    */         }
/* 174:    */       }
/* 175:    */     }
/* 176:187 */     return true;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public void spawnCursors(World world, ItemStack itemstack)
/* 180:    */   {
/* 181:191 */     if (world.isRemote) {
/* 182:193 */       if (itemstack.stackTagCompound != null)
/* 183:    */       {
/* 184:195 */         for (EntityCursor e : this.cursors)
/* 185:    */         {
/* 186:197 */           e.item = null;
/* 187:198 */           e.setDead();
/* 188:    */         }
/* 189:200 */         this.cursors.clear();
/* 190:201 */         int points = itemstack.stackTagCompound.getInteger("pos");
/* 191:202 */         for (int p = 0; p < points; p++)
/* 192:    */         {
/* 193:204 */           EntityCursor c = new EntityCursor(world, itemstack.stackTagCompound.getInteger("x" + p) + 0.5D, itemstack.stackTagCompound.getInteger("y" + p) + 1, itemstack.stackTagCompound.getInteger("z" + p) + 0.5D, 0.0F, itemstack);
/* 194:208 */           if (p >= 1) {
/* 195:209 */             ((EntityCursor)this.cursors.get(p - 1)).next = c;
/* 196:    */           }
/* 197:210 */           this.cursors.add(c);
/* 198:211 */           world.spawnEntityInWorld(c);
/* 199:    */         }
/* 200:    */       }
/* 201:    */     }
/* 202:    */   }
/* 203:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.mobControl.ItemPathMarker
 * JD-Core Version:    0.7.1
 */