/*   1:    */ package com.chocolate.chocolateQuest.entity.projectile;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.magic.Elements;
/*   4:    */ import cpw.mods.fml.common.registry.IThrowableEntity;
/*   5:    */ import cpw.mods.fml.relauncher.Side;
/*   6:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   7:    */ import java.util.Random;
/*   8:    */ import net.minecraft.entity.DataWatcher;
/*   9:    */ import net.minecraft.entity.Entity;
/*  10:    */ import net.minecraft.entity.EntityLivingBase;
/*  11:    */ import net.minecraft.entity.projectile.EntityThrowable;
/*  12:    */ import net.minecraft.item.ItemStack;
/*  13:    */ import net.minecraft.nbt.NBTTagCompound;
/*  14:    */ import net.minecraft.util.AxisAlignedBB;
/*  15:    */ import net.minecraft.util.DamageSource;
/*  16:    */ import net.minecraft.util.MovingObjectPosition;
/*  17:    */ import net.minecraft.util.Vec3;
/*  18:    */ import net.minecraft.world.World;
/*  19:    */ 
/*  20:    */ public class EntityBaseBall
/*  21:    */   extends EntityThrowable
/*  22:    */   implements IThrowableEntity
/*  23:    */ {
/*  24:    */   private ProjectileBase ballData;
/*  25: 24 */   public ItemStack item = null;
/*  26: 25 */   int ticksInAir = 0;
/*  27:    */   EntityLivingBase shootingEntity;
/*  28: 27 */   float damageMultiplier = 1.0F;
/*  29:    */   
/*  30:    */   public EntityBaseBall(World par1World)
/*  31:    */   {
/*  32: 30 */     super(par1World);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public EntityBaseBall(World world, EntityLivingBase entityliving)
/*  36:    */   {
/*  37: 35 */     this(world, entityliving, 0);
/*  38:    */   }
/*  39:    */   
/*  40:    */   public EntityBaseBall(World world, EntityLivingBase entityliving, int type)
/*  41:    */   {
/*  42: 39 */     this(world, entityliving, type, 0);
/*  43:    */   }
/*  44:    */   
/*  45:    */   public EntityBaseBall(World world, EntityLivingBase entityliving, int type, int lvl)
/*  46:    */   {
/*  47: 44 */     this(world, entityliving, type, lvl, Elements.physic);
/*  48:    */   }
/*  49:    */   
/*  50:    */   public EntityBaseBall(World world, EntityLivingBase entityliving, int type, int lvl, Elements element)
/*  51:    */   {
/*  52: 49 */     super(world, entityliving);
/*  53: 50 */     setlvl(lvl);
/*  54: 51 */     setElement(element);
/*  55: 52 */     this.shootingEntity = entityliving;
/*  56: 53 */     setType(type);
/*  57: 54 */     float s = getBallData().getSizeBB();
/*  58: 55 */     setSize(s, s);
/*  59: 56 */     playShootSound();
/*  60: 57 */     getBallData().onSpawn();
/*  61:    */   }
/*  62:    */   
/*  63:    */   public EntityBaseBall(World world, EntityLivingBase entityliving, int type, ItemStack item, int lvl)
/*  64:    */   {
/*  65: 62 */     this(world, entityliving, type, lvl);
/*  66: 63 */     this.item = item;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public EntityBaseBall(World world, EntityLivingBase entityliving, double x, double y, double z, int type, int lvl)
/*  70:    */   {
/*  71: 68 */     this(world, entityliving, x, y, z, type, lvl, 0.0F);
/*  72:    */   }
/*  73:    */   
/*  74:    */   public EntityBaseBall(World world, EntityLivingBase entityliving, double x, double y, double z, int type, int lvl, float accuracy)
/*  75:    */   {
/*  76: 73 */     this(world, entityliving, type, lvl);
/*  77: 74 */     setThrowableHeading(x, y, z, 1.0F, accuracy);
/*  78:    */   }
/*  79:    */   
/*  80: 76 */   final int TYPE = 10;
/*  81: 76 */   final int LEVEL = 11;
/*  82: 76 */   final int ELEMENT = 12;
/*  83:    */   
/*  84:    */   protected void entityInit()
/*  85:    */   {
/*  86: 79 */     this.dataWatcher.addObject(10, Byte.valueOf((byte)0));
/*  87: 80 */     this.dataWatcher.addObject(11, Byte.valueOf((byte)0));
/*  88: 81 */     this.dataWatcher.addObject(12, Byte.valueOf((byte)0));
/*  89:    */   }
/*  90:    */   
/*  91:    */   public void playShootSound()
/*  92:    */   {
/*  93: 86 */     if (!this.worldObj.isRemote) {
/*  94: 88 */       getBallData().onSpawn();
/*  95:    */     }
/*  96:    */   }
/*  97:    */   
/*  98:    */   public void setType(int par)
/*  99:    */   {
/* 100: 94 */     this.dataWatcher.updateObject(10, Byte.valueOf((byte)par));
/* 101:    */   }
/* 102:    */   
/* 103:    */   public byte getType()
/* 104:    */   {
/* 105: 99 */     return this.dataWatcher.getWatchableObjectByte(10);
/* 106:    */   }
/* 107:    */   
/* 108:    */   public void setlvl(int par)
/* 109:    */   {
/* 110:104 */     this.dataWatcher.updateObject(11, Byte.valueOf((byte)par));
/* 111:    */   }
/* 112:    */   
/* 113:    */   public byte getlvl()
/* 114:    */   {
/* 115:109 */     return this.dataWatcher.getWatchableObjectByte(11);
/* 116:    */   }
/* 117:    */   
/* 118:    */   public void setElement(Elements par)
/* 119:    */   {
/* 120:113 */     this.dataWatcher.updateObject(12, Byte.valueOf((byte)par.ordinal()));
/* 121:    */   }
/* 122:    */   
/* 123:    */   public Elements getElement()
/* 124:    */   {
/* 125:117 */     return Elements.values()[this.dataWatcher.getWatchableObjectByte(12)];
/* 126:    */   }
/* 127:    */   
/* 128:    */   @SideOnly(Side.CLIENT)
/* 129:    */   public boolean isInRangeToRenderDist(double par1)
/* 130:    */   {
/* 131:128 */     double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
/* 132:129 */     d1 *= 64.0D;
/* 133:130 */     return par1 < d1 * d1;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public void onUpdate()
/* 137:    */   {
/* 138:138 */     if (getBallData().longRange())
/* 139:    */     {
/* 140:140 */       this.motionX *= 1.01D;
/* 141:141 */       this.motionZ *= 1.01D;
/* 142:    */     }
/* 143:143 */     if ((getThrower() != null) && (getThrower().isDead))
/* 144:    */     {
/* 145:145 */       setDead();
/* 146:    */     }
/* 147:    */     else
/* 148:    */     {
/* 149:149 */       if (this.ticksInAir++ > getBallData().getMaxLifeTime()) {
/* 150:151 */         setDead();
/* 151:    */       }
/* 152:153 */       super.onUpdate();
/* 153:154 */       getBallData().onUpdateInAir();
/* 154:    */     }
/* 155:    */   }
/* 156:    */   
/* 157:    */   protected float getGravityVelocity()
/* 158:    */   {
/* 159:161 */     return getBallData().getGravityVelocity();
/* 160:    */   }
/* 161:    */   
/* 162:    */   protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
/* 163:    */   {
/* 164:167 */     getBallData().onImpact(par1MovingObjectPosition);
/* 165:    */   }
/* 166:    */   
/* 167:    */   public double getMountedYOffset()
/* 168:    */   {
/* 169:175 */     return -0.4000000119209289D;
/* 170:    */   }
/* 171:    */   
/* 172:    */   public double getYOffset()
/* 173:    */   {
/* 174:181 */     return this.ballData.getYOffset();
/* 175:    */   }
/* 176:    */   
/* 177:    */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
/* 178:    */   {
/* 179:188 */     par1NBTTagCompound.setByte("Type", getType());
/* 180:189 */     par1NBTTagCompound.setByte("lvl", getlvl());
/* 181:    */   }
/* 182:    */   
/* 183:    */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
/* 184:    */   {
/* 185:197 */     setType(par1NBTTagCompound.getByte("Type"));
/* 186:198 */     setlvl(par1NBTTagCompound.getByte("lvl"));
/* 187:199 */     setDead();
/* 188:    */   }
/* 189:    */   
/* 190:    */   public boolean canBeCollidedWith()
/* 191:    */   {
/* 192:207 */     return true;
/* 193:    */   }
/* 194:    */   
/* 195:    */   public boolean attackEntityFrom(DamageSource source, float damage)
/* 196:    */   {
/* 197:216 */     getBallData().attackFrom(source, damage);
/* 198:217 */     setBeenAttacked();
/* 199:218 */     if ((source.getEntity() != null) && (getBallData().canBounce()))
/* 200:    */     {
/* 201:220 */       Vec3 var3 = source.getEntity().getLookVec();
/* 202:222 */       if (var3 != null)
/* 203:    */       {
/* 204:224 */         this.motionX = var3.xCoord;
/* 205:225 */         this.motionY = var3.yCoord;
/* 206:226 */         this.motionZ = var3.zCoord;
/* 207:    */       }
/* 208:229 */       return true;
/* 209:    */     }
/* 210:233 */     return false;
/* 211:    */   }
/* 212:    */   
/* 213:    */   public Random getRNG()
/* 214:    */   {
/* 215:238 */     return this.rand;
/* 216:    */   }
/* 217:    */   
/* 218:    */   public void setDead()
/* 219:    */   {
/* 220:244 */     getBallData().onDead();
/* 221:245 */     super.setDead();
/* 222:    */   }
/* 223:    */   
/* 224:    */   public void setDamageMultiplier(float damage)
/* 225:    */   {
/* 226:249 */     this.damageMultiplier = damage;
/* 227:    */   }
/* 228:    */   
/* 229:    */   public float getDamageMultiplier()
/* 230:    */   {
/* 231:252 */     return this.damageMultiplier;
/* 232:    */   }
/* 233:    */   
/* 234:    */   public int getTextureIndex()
/* 235:    */   {
/* 236:257 */     return getBallData().getTextureIndex();
/* 237:    */   }
/* 238:    */   
/* 239:    */   public float getBallSize()
/* 240:    */   {
/* 241:262 */     return getBallData().getSize();
/* 242:    */   }
/* 243:    */   
/* 244:    */   public ProjectileBase getBallData()
/* 245:    */   {
/* 246:267 */     if ((this.ballData == null) || ((this.ballData instanceof ProjectileDummy)))
/* 247:    */     {
/* 248:269 */       this.ballData = ProjectileBase.getBallData(this);
/* 249:270 */       setSize(this.ballData.getSizeBB(), this.ballData.getSizeBB());
/* 250:    */     }
/* 251:273 */     return this.ballData;
/* 252:    */   }
/* 253:    */   
/* 254:    */   public void setBallData(ProjectileBase data)
/* 255:    */   {
/* 256:276 */     this.ballData = data;
/* 257:    */   }
/* 258:    */   
/* 259:    */   public void setInmuneToFire(boolean fire)
/* 260:    */   {
/* 261:281 */     this.isImmuneToFire = fire;
/* 262:    */   }
/* 263:    */   
/* 264:    */   public EntityLivingBase getThrower()
/* 265:    */   {
/* 266:288 */     return this.shootingEntity;
/* 267:    */   }
/* 268:    */   
/* 269:    */   public void setThrower(Entity entity)
/* 270:    */   {
/* 271:294 */     if ((entity instanceof EntityLivingBase)) {
/* 272:296 */       this.shootingEntity = ((EntityLivingBase)entity);
/* 273:    */     }
/* 274:    */   }
/* 275:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall
 * JD-Core Version:    0.7.1
 */