/*   1:    */ package com.chocolate.chocolateQuest.entity.projectile;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.magic.Elements;
/*   4:    */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*   5:    */ import com.chocolate.chocolateQuest.utils.HelperPlayer;
/*   6:    */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*   7:    */ import io.netty.buffer.ByteBuf;
/*   8:    */ import java.util.List;
/*   9:    */ import java.util.Random;
/*  10:    */ import net.minecraft.entity.Entity;
/*  11:    */ import net.minecraft.entity.EntityLivingBase;
/*  12:    */ import net.minecraft.entity.player.EntityPlayer;
/*  13:    */ import net.minecraft.nbt.NBTTagCompound;
/*  14:    */ import net.minecraft.util.AxisAlignedBB;
/*  15:    */ import net.minecraft.util.DamageSource;
/*  16:    */ import net.minecraft.util.MathHelper;
/*  17:    */ import net.minecraft.util.MovingObjectPosition;
/*  18:    */ import net.minecraft.util.Vec3;
/*  19:    */ import net.minecraft.world.World;
/*  20:    */ 
/*  21:    */ public class EntityProjectileBeam
/*  22:    */   extends Entity
/*  23:    */   implements IEntityAdditionalSpawnData
/*  24:    */ {
/*  25:    */   Elements element;
/*  26:    */   int elementID;
/*  27:    */   EntityLivingBase mainBody;
/*  28: 29 */   public float rotationYawOffset = 0.0F;
/*  29: 30 */   public float distanceToMainBody = 0.0F;
/*  30: 31 */   public float heightOffset = 0.0F;
/*  31: 32 */   int ownerID = 0;
/*  32: 34 */   public float maxRange = 16.0F;
/*  33: 35 */   public float range = 16.0F;
/*  34:    */   
/*  35:    */   public EntityProjectileBeam(World world)
/*  36:    */   {
/*  37: 37 */     super(world);
/*  38: 38 */     this.isImmuneToFire = true;
/*  39: 39 */     setSize(1.0F, 1.0F);
/*  40:    */   }
/*  41:    */   
/*  42:    */   public EntityProjectileBeam(World world, EntityLivingBase main, float rotationYawOffset, float distanceToMainBody, Elements element)
/*  43:    */   {
/*  44: 42 */     this(world);
/*  45: 43 */     this.rotationYawOffset = rotationYawOffset;
/*  46: 44 */     this.distanceToMainBody = distanceToMainBody;
/*  47: 45 */     if (main != null) {
/*  48: 46 */       setPosition(main.posX, main.posY, main.posZ);
/*  49:    */     }
/*  50: 47 */     this.mainBody = main;
/*  51: 48 */     this.element = element;
/*  52: 49 */     this.elementID = element.ordinal();
/*  53:    */   }
/*  54:    */   
/*  55:    */   public EntityProjectileBeam(World world, EntityLivingBase main, float rotationYawOffset, float distanceToMainBody, float heightOffset, Elements element)
/*  56:    */   {
/*  57: 52 */     this(world, main, rotationYawOffset, distanceToMainBody, element);
/*  58: 53 */     this.heightOffset = heightOffset;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void setSize(float par1, float par2)
/*  62:    */   {
/*  63: 58 */     super.setSize(par1, par2);
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void onUpdate()
/*  67:    */   {
/*  68: 64 */     Elements element = getElement();
/*  69: 65 */     super.onUpdate();
/*  70: 66 */     if (this.mainBody != null)
/*  71:    */     {
/*  72: 67 */       double hx = -Math.sin(Math.toRadians(this.mainBody.rotationYawHead + this.rotationYawOffset)) * this.distanceToMainBody;
/*  73: 68 */       double hz = Math.cos(Math.toRadians(this.mainBody.rotationYawHead + this.rotationYawOffset)) * this.distanceToMainBody;
/*  74: 69 */       setPositionAndRotation(this.mainBody.posX + hx + this.motionX, this.mainBody.posY + this.heightOffset + this.motionY, this.mainBody.posZ + hz + this.motionZ, this.mainBody.rotationYawHead, this.mainBody.rotationPitch);
/*  75: 73 */       if ((this.mainBody instanceof EntityPlayer))
/*  76:    */       {
/*  77: 74 */         EntityPlayer player = (EntityPlayer)this.mainBody;
/*  78: 75 */         if (!player.isUsingItem()) {
/*  79: 76 */           setDead();
/*  80:    */         }
/*  81:    */       }
/*  82: 78 */       else if (this.ticksExisted > 30)
/*  83:    */       {
/*  84: 79 */         setDead();
/*  85:    */       }
/*  86: 81 */       EntityLivingBase shooter = this.mainBody;
/*  87: 82 */       MovingObjectPosition mop = getMovingObjectPositionFromPlayer(shooter, shooter.worldObj, this.maxRange, 0.0D);
/*  88: 84 */       if (mop != null)
/*  89:    */       {
/*  90: 85 */         double x = mop.blockX;
/*  91: 86 */         double y = mop.blockY;
/*  92: 87 */         double z = mop.blockZ;
/*  93: 88 */         double dist = getDistance(x, y, z);
/*  94: 89 */         this.range = ((float)dist);
/*  95: 91 */         if (mop.entityHit != null)
/*  96:    */         {
/*  97: 92 */           float damage = 2.0F;
/*  98: 93 */           damage = element.onHitEntity(this.mainBody, mop.entityHit, damage);
/*  99: 94 */           mop.entityHit.attackEntityFrom(element.getDamageSource(this.mainBody).setProjectile(), damage);
/* 100:    */         }
/* 101: 97 */         if (this.worldObj.isRemote)
/* 102:    */         {
/* 103: 99 */           switch (mop.sideHit)
/* 104:    */           {
/* 105:    */           case 2: 
/* 106:101 */             x += 0.5D;
/* 107:102 */             break;
/* 108:    */           case 3: 
/* 109:104 */             z += 1.5D;
/* 110:105 */             break;
/* 111:    */           case 4: 
/* 112:107 */             x += -0.5D;
/* 113:108 */             break;
/* 114:    */           case 5: 
/* 115:110 */             z += 0.5D;
/* 116:111 */             x += 1.2D;
/* 117:    */           }
/* 118:114 */           float s = 0.2F;
/* 119:115 */           double ry = Math.cos(Math.toRadians(this.rotationPitch));
/* 120:116 */           double rx = -Math.sin(Math.toRadians(this.rotationYaw)) * dist * ry;
/* 121:117 */           double rz = Math.cos(Math.toRadians(this.rotationYaw)) * dist * ry;
/* 122:118 */           ry = -Math.sin(Math.toRadians(this.rotationPitch)) * dist;
/* 123:119 */           EffectManager.spawnElementParticle(0, this.worldObj, this.posX + rx, this.posY + ry, this.posZ + rz, (this.rand.nextFloat() - 0.5F) * s, 0.1D, (this.rand.nextFloat() - 0.5F) * s, element);
/* 124:    */         }
/* 125:    */       }
/* 126:    */       else
/* 127:    */       {
/* 128:122 */         this.range = this.maxRange;
/* 129:    */       }
/* 130:    */     }
/* 131:    */     else
/* 132:    */     {
/* 133:124 */       setDead();
/* 134:    */     }
/* 135:    */   }
/* 136:    */   
/* 137:    */   public boolean canBeCollidedWith()
/* 138:    */   {
/* 139:130 */     return false;
/* 140:    */   }
/* 141:    */   
/* 142:    */   protected void readEntityFromNBT(NBTTagCompound var1)
/* 143:    */   {
/* 144:136 */     setDead();
/* 145:    */   }
/* 146:    */   
/* 147:    */   protected void writeEntityToNBT(NBTTagCompound var1) {}
/* 148:    */   
/* 149:    */   public void readSpawnData(ByteBuf additionalData)
/* 150:    */   {
/* 151:146 */     if (additionalData.readBoolean()) {
/* 152:147 */       return;
/* 153:    */     }
/* 154:148 */     int id = additionalData.readInt();
/* 155:149 */     Entity e = this.worldObj.getEntityByID(id);
/* 156:150 */     if ((e instanceof EntityLivingBase)) {
/* 157:151 */       this.mainBody = ((EntityLivingBase)e);
/* 158:    */     }
/* 159:152 */     this.distanceToMainBody = additionalData.readFloat();
/* 160:153 */     this.rotationYawOffset = additionalData.readFloat();
/* 161:154 */     this.heightOffset = additionalData.readFloat();
/* 162:155 */     this.elementID = additionalData.readInt();
/* 163:156 */     this.maxRange = additionalData.readInt();
/* 164:    */   }
/* 165:    */   
/* 166:    */   public void writeSpawnData(ByteBuf buffer)
/* 167:    */   {
/* 168:161 */     buffer.writeBoolean(this.mainBody == null);
/* 169:162 */     if (this.mainBody != null)
/* 170:    */     {
/* 171:163 */       buffer.writeInt(this.mainBody.getEntityId());
/* 172:164 */       buffer.writeFloat(this.distanceToMainBody);
/* 173:165 */       buffer.writeFloat(this.rotationYawOffset);
/* 174:166 */       buffer.writeFloat(this.heightOffset);
/* 175:167 */       buffer.writeInt(this.elementID);
/* 176:168 */       buffer.writeInt((int)this.maxRange);
/* 177:    */     }
/* 178:    */   }
/* 179:    */   
/* 180:    */   protected void entityInit() {}
/* 181:    */   
/* 182:    */   public static MovingObjectPosition getMovingObjectPositionFromPlayer(EntityLivingBase ep, World world, double dist, double removeMe)
/* 183:    */   {
/* 184:176 */     MovingObjectPosition mop = null;
/* 185:177 */     float yOffset = ep.getEyeHeight();
/* 186:178 */     Vec3 playerPos = Vec3.createVectorHelper(ep.posX, ep.posY + yOffset, ep.posZ);
/* 187:179 */     Vec3 look = ep.getLookVec();
/* 188:180 */     if ((ep instanceof EntityPlayer)) {
/* 189:182 */       mop = HelperPlayer.getBlockMovingObjectPositionFromPlayer(world, ep, dist, true);
/* 190:    */     } else {
/* 191:184 */       mop = HelperPlayer.getBlockMovingObjectPositionFromPlayer(world, ep, dist, true);
/* 192:    */     }
/* 193:186 */     if (mop != null)
/* 194:    */     {
/* 195:188 */       Vec3 v = Vec3.createVectorHelper(ep.posX - mop.blockX, ep.posY - mop.blockY, ep.posZ - mop.blockZ);
/* 196:189 */       dist = v.lengthVector();
/* 197:    */     }
/* 198:192 */     Vec3 playerView = playerPos.addVector(look.xCoord * dist, look.yCoord * dist, look.zCoord * dist);
/* 199:193 */     List list = world.getEntitiesWithinAABBExcludingEntity(ep, ep.boundingBox.addCoord(ep.getLookVec().xCoord * dist, ep.getLookVec().yCoord * dist, ep.getLookVec().zCoord * dist).expand(1.0D, 1.0D, 1.0D));
/* 200:    */     
/* 201:195 */     MovingObjectPosition tempMop = null;
/* 202:196 */     double prevDist = dist * dist;
/* 203:197 */     for (int j = 0; j < list.size(); j++)
/* 204:    */     {
/* 205:199 */       Entity entity1 = (Entity)list.get(j);
/* 206:201 */       if ((entity1.canBeCollidedWith()) && (entity1 != ep.ridingEntity) && (ep != ep.riddenByEntity)) {
/* 207:206 */         if ((entity1 instanceof EntityLivingBase))
/* 208:    */         {
/* 209:210 */           float f2 = 0.4F;
/* 210:211 */           AxisAlignedBB axisalignedbb = entity1.boundingBox;
/* 211:212 */           MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(playerPos, playerView);
/* 212:214 */           if (movingobjectposition1 != null)
/* 213:    */           {
/* 214:218 */             movingobjectposition1.entityHit = entity1;
/* 215:219 */             movingobjectposition1.blockX = MathHelper.floor_double(entity1.posX);
/* 216:220 */             movingobjectposition1.blockY = MathHelper.floor_double(entity1.posY + entity1.height / 2.0F);
/* 217:221 */             movingobjectposition1.blockZ = MathHelper.floor_double(entity1.posZ);
/* 218:222 */             double entityDist = entity1.getDistanceSqToEntity(ep);
/* 219:223 */             if (entityDist < prevDist)
/* 220:    */             {
/* 221:224 */               tempMop = movingobjectposition1;
/* 222:225 */               prevDist = entityDist;
/* 223:    */             }
/* 224:    */           }
/* 225:    */         }
/* 226:    */       }
/* 227:    */     }
/* 228:228 */     if (tempMop != null) {
/* 229:229 */       return tempMop;
/* 230:    */     }
/* 231:231 */     return mop;
/* 232:    */   }
/* 233:    */   
/* 234:    */   public Elements getElement()
/* 235:    */   {
/* 236:234 */     if (this.element == null) {
/* 237:235 */       this.element = Elements.values()[this.elementID];
/* 238:    */     }
/* 239:237 */     return this.element;
/* 240:    */   }
/* 241:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.EntityProjectileBeam
 * JD-Core Version:    0.7.1
 */