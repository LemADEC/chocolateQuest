/*   1:    */ package com.chocolate.chocolateQuest.block;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase;
/*   5:    */ import com.chocolate.chocolateQuest.entity.mob.registry.RegisterDungeonMobs;
/*   6:    */ import com.chocolate.chocolateQuest.misc.EquipementHelper;
/*   7:    */ import java.io.PrintStream;
/*   8:    */ import java.util.ArrayList;
/*   9:    */ import java.util.Random;
/*  10:    */ import net.minecraft.entity.Entity;
/*  11:    */ import net.minecraft.entity.EntityList;
/*  12:    */ import net.minecraft.entity.EntityLiving;
/*  13:    */ import net.minecraft.entity.EntityLivingBase;
/*  14:    */ import net.minecraft.entity.monster.EntitySkeleton;
/*  15:    */ import net.minecraft.entity.monster.EntitySpider;
/*  16:    */ import net.minecraft.entity.monster.EntityZombie;
/*  17:    */ import net.minecraft.entity.player.EntityPlayer;
/*  18:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*  19:    */ import net.minecraft.nbt.NBTTagCompound;
/*  20:    */ import net.minecraft.nbt.NBTTagList;
/*  21:    */ import net.minecraft.tileentity.TileEntity;
/*  22:    */ import net.minecraft.world.World;
/*  23:    */ 
/*  24:    */ public class BlockMobSpawnerTileEntity
/*  25:    */   extends TileEntity
/*  26:    */ {
/*  27:    */   public int delay;
/*  28:    */   public int mob;
/*  29:    */   public int metadata;
/*  30:    */   public double yaw;
/*  31:    */   public double yaw2;
/*  32: 34 */   public NBTTagCompound mobNBT = null;
/*  33:    */   
/*  34:    */   public BlockMobSpawnerTileEntity()
/*  35:    */   {
/*  36: 38 */     this.delay = -1;
/*  37: 39 */     this.yaw2 = 0.0D;
/*  38: 40 */     this.delay = 20;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public boolean anyPlayerInRange()
/*  42:    */   {
/*  43: 48 */     EntityPlayer player = this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, 16.0D);
/*  44: 49 */     if (player != null) {
/*  45: 51 */       if (!player.capabilities.isCreativeMode) {
/*  46: 52 */         return true;
/*  47:    */       }
/*  48:    */     }
/*  49: 54 */     return false;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public void updateEntity()
/*  53:    */   {
/*  54: 64 */     if (!anyPlayerInRange()) {
/*  55: 66 */       return;
/*  56:    */     }
/*  57: 68 */     if (!this.worldObj.isRemote)
/*  58:    */     {
/*  59: 70 */       if (this.delay == -1) {
/*  60: 72 */         updateDelay();
/*  61:    */       }
/*  62: 75 */       if (this.delay > 0)
/*  63:    */       {
/*  64: 77 */         this.delay -= 1;
/*  65: 78 */         return;
/*  66:    */       }
/*  67: 80 */       spawnEntity();
/*  68: 81 */       this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
/*  69:    */     }
/*  70: 83 */     super.updateEntity();
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void spawnEntity()
/*  74:    */   {
/*  75: 87 */     int tmob = this.mob;
/*  76:    */     
/*  77: 89 */     Entity entity = getEntity(this.metadata, tmob, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
/*  78: 90 */     if (entity == null) {
/*  79: 92 */       entity = new EntityZombie(this.worldObj);
/*  80:    */     }
/*  81: 94 */     if (this.mobNBT != null)
/*  82:    */     {
/*  83: 96 */       editEntityStats(this.mobNBT);
/*  84: 97 */       Entity e = EntityList.createEntityFromNBT(this.mobNBT, this.worldObj);
/*  85: 98 */       if (e != null) {
/*  86: 99 */         entity = e;
/*  87:    */       }
/*  88:100 */       if ((entity instanceof EntityHumanBase))
/*  89:    */       {
/*  90:101 */         EntityHumanBase human = (EntityHumanBase)entity;
/*  91:102 */         human.readEntityFromSpawnerNBT(this.mobNBT, this.xCoord, this.yCoord, this.zCoord);
/*  92:104 */         if (this.mobNBT.getTag("Riding") != null)
/*  93:    */         {
/*  94:105 */           NBTTagCompound ridingNBT = (NBTTagCompound)this.mobNBT.getTag("Riding");
/*  95:106 */           Entity riding = EntityList.createEntityFromNBT(ridingNBT, this.worldObj);
/*  96:107 */           if (riding != null)
/*  97:    */           {
/*  98:108 */             riding.setPosition(this.xCoord + 0.5D, this.yCoord, this.zCoord + 0.5D);
/*  99:109 */             this.worldObj.spawnEntityInWorld(riding);
/* 100:110 */             human.mountEntity(riding);
/* 101:111 */             if ((riding instanceof EntityHumanBase)) {
/* 102:112 */               ((EntityHumanBase)riding).entityTeam = human.entityTeam;
/* 103:    */             }
/* 104:    */           }
/* 105:    */         }
/* 106:    */       }
/* 107:    */     }
/* 108:117 */     entity.setLocationAndAngles(this.xCoord + 0.5D, this.yCoord, this.zCoord + 0.5D, this.worldObj.rand.nextFloat() * 360.0F, 0.0F);
/* 109:118 */     this.worldObj.spawnEntityInWorld(entity);
/* 110:119 */     this.worldObj.playAuxSFX(2004, this.xCoord, this.yCoord, this.zCoord, 0);
/* 111:120 */     if ((this.worldObj.isRemote) && ((entity instanceof EntityLiving))) {
/* 112:121 */       ((EntityLiving)entity).spawnExplosionParticle();
/* 113:    */     }
/* 114:    */   }
/* 115:    */   
/* 116:    */   private void updateDelay()
/* 117:    */   {
/* 118:130 */     this.delay = (200 + this.worldObj.rand.nextInt(600));
/* 119:    */   }
/* 120:    */   
/* 121:    */   public void readFromNBT(NBTTagCompound par1NBTTagCompound)
/* 122:    */   {
/* 123:139 */     super.readFromNBT(par1NBTTagCompound);
/* 124:140 */     this.mob = par1NBTTagCompound.getInteger("mob");
/* 125:141 */     this.metadata = par1NBTTagCompound.getInteger("typeMob");
/* 126:142 */     this.mobNBT = ((NBTTagCompound)par1NBTTagCompound.getTag("mobData"));
/* 127:143 */     if ((this.mobNBT != null) && (this.mobNBT.hasNoTags())) {
/* 128:144 */       this.mobNBT = null;
/* 129:    */     }
/* 130:    */   }
/* 131:    */   
/* 132:    */   public void writeToNBT(NBTTagCompound par1NBTTagCompound)
/* 133:    */   {
/* 134:153 */     super.writeToNBT(par1NBTTagCompound);
/* 135:154 */     par1NBTTagCompound.setInteger("mob", this.mob);
/* 136:155 */     par1NBTTagCompound.setInteger("typeMob", this.metadata);
/* 137:156 */     if (this.mobNBT != null) {
/* 138:157 */       par1NBTTagCompound.setTag("mobData", this.mobNBT);
/* 139:    */     }
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void editEntityStats(NBTTagCompound eTag)
/* 143:    */   {
/* 144:162 */     DungeonMonstersBase mobType = null;
/* 145:163 */     if (this.mob >= 0) {
/* 146:164 */       mobType = (DungeonMonstersBase)RegisterDungeonMobs.mobList.get(this.mob);
/* 147:    */     }
/* 148:165 */     if ((mobType != null) && (
/* 149:166 */       (eTag.getString("id").equals("chocolateQuest.dummy")) || (eTag.getString("id").equals(""))))
/* 150:    */     {
/* 151:167 */       NBTTagList list = eTag.getTagList("Party", eTag.getId());
/* 152:168 */       for (int i = 0; i < list.tagCount(); i++) {
/* 153:169 */         editEntityStats(list.getCompoundTagAt(i));
/* 154:    */       }
/* 155:171 */       eTag.setString("id", mobType.getRegisteredEntityName());
/* 156:172 */       eTag.setString("team", mobType.getTeamName());
/* 157:173 */       NBTTagList attributes = eTag.getTagList("Attributes", eTag.getId());
/* 158:174 */       for (int a = 0; a < attributes.tagCount(); a++)
/* 159:    */       {
/* 160:175 */         NBTTagCompound tag = attributes.getCompoundTagAt(a);
/* 161:176 */         String name = tag.getString("Name");
/* 162:177 */         if (name.equals("generic.maxHealth"))
/* 163:    */         {
/* 164:178 */           double base = tag.getDouble("Base");
/* 165:179 */           tag.setDouble("Base", base * mobType.getHealth());
/* 166:180 */           eTag.setShort("Health", (short)(int)(base * mobType.getHealth()));
/* 167:181 */           eTag.setFloat("HealF", (float)(base * mobType.getHealth()));
/* 168:    */         }
/* 169:183 */         if (name.equals("generic.attackDamage"))
/* 170:    */         {
/* 171:184 */           double base = tag.getDouble("Base");
/* 172:185 */           tag.setDouble("generic.attackDamage", mobType.getAttack());
/* 173:    */         }
/* 174:187 */         if (name.equals("generic.followRange"))
/* 175:    */         {
/* 176:188 */           double base = tag.getDouble("Base");
/* 177:189 */           tag.setDouble("generic.followRange", mobType.getRange());
/* 178:    */         }
/* 179:    */       }
/* 180:    */     }
/* 181:    */   }
/* 182:    */   
/* 183:    */   public static Entity getEntity(int metadata, int mob, World world, int x, int y, int z)
/* 184:    */   {
/* 185:198 */     System.out.println(metadata);
/* 186:199 */     Entity e = null;
/* 187:200 */     DungeonMonstersBase mobType = null;
/* 188:201 */     if (mob >= 0) {
/* 189:202 */       mobType = (DungeonMonstersBase)RegisterDungeonMobs.mobList.get(mob);
/* 190:    */     }
/* 191:203 */     if (mobType != null)
/* 192:    */     {
/* 193:205 */       if (metadata <= 14) {
/* 194:207 */         e = mobType.getEntity(world, x, y, z);
/* 195:    */       } else {
/* 196:211 */         e = mobType.getBoss(world, x, y, z);
/* 197:    */       }
/* 198:213 */       if ((e instanceof EntityHumanBase)) {
/* 199:214 */         EquipementHelper.equipEntity((EntityLivingBase)e, metadata % 5);
/* 200:    */       }
/* 201:    */     }
/* 202:219 */     else if (metadata < 5)
/* 203:    */     {
/* 204:221 */       e = new EntityZombie(world);
/* 205:    */     }
/* 206:223 */     else if (metadata <= 9)
/* 207:    */     {
/* 208:225 */       e = new EntitySkeleton(world);
/* 209:    */     }
/* 210:    */     else
/* 211:    */     {
/* 212:229 */       e = new EntitySpider(world);
/* 213:    */     }
/* 214:232 */     return e;
/* 215:    */   }
/* 216:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockMobSpawnerTileEntity
 * JD-Core Version:    0.7.1
 */