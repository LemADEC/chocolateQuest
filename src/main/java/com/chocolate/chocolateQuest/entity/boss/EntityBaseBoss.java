/*   1:    */ package com.chocolate.chocolateQuest.entity.boss;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.entity.EntityReferee;
/*   5:    */ import com.chocolate.chocolateQuest.magic.ElementsHelper;
/*   6:    */ import com.chocolate.chocolateQuest.magic.IElementWeak;
/*   7:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   8:    */ import com.chocolate.chocolateQuest.packets.PacketEntityAnimation;
/*   9:    */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*  10:    */ import io.netty.buffer.ByteBuf;
/*  11:    */ import net.minecraft.entity.DataWatcher;
/*  12:    */ import net.minecraft.entity.Entity;
/*  13:    */ import net.minecraft.entity.EntityCreature;
/*  14:    */ import net.minecraft.entity.EntityLivingBase;
/*  15:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  16:    */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*  17:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  18:    */ import net.minecraft.entity.boss.IBossDisplayData;
/*  19:    */ import net.minecraft.entity.monster.EntityCreeper;
/*  20:    */ import net.minecraft.entity.monster.IMob;
/*  21:    */ import net.minecraft.entity.player.EntityPlayer;
/*  22:    */ import net.minecraft.nbt.NBTTagCompound;
/*  23:    */ import net.minecraft.util.AxisAlignedBB;
/*  24:    */ import net.minecraft.util.DamageSource;
/*  25:    */ import net.minecraft.util.EntityDamageSource;
/*  26:    */ import net.minecraft.world.World;
/*  27:    */ 
/*  28:    */ public class EntityBaseBoss
/*  29:    */   extends EntityCreature
/*  30:    */   implements IBossDisplayData, IMob, IEntityAdditionalSpawnData, IElementWeak
/*  31:    */ {
/*  32: 28 */   private boolean firstTick = true;
/*  33: 31 */   float lvl = 1.0F;
/*  34: 32 */   float size = 1.0F;
/*  35: 33 */   protected boolean ridableBB = true;
/*  36: 34 */   float rotSpeed = 3.0F;
/*  37: 35 */   boolean limitRotation = false;
/*  38: 37 */   int rage = 0;
/*  39:    */   
/*  40:    */   public EntityBaseBoss(World par1World)
/*  41:    */   {
/*  42: 40 */     super(par1World);
/*  43: 41 */     addAITasks();
/*  44: 42 */     setMonsterScale(this.lvl);
/*  45:    */   }
/*  46:    */   
/*  47:    */   protected void entityInit()
/*  48:    */   {
/*  49: 48 */     super.entityInit();
/*  50: 49 */     this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
/*  51:    */   }
/*  52:    */   
/*  53:    */   protected void applyEntityAttributes()
/*  54:    */   {
/*  55: 55 */     super.applyEntityAttributes();
/*  56: 56 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
/*  57:    */     
/*  58: 58 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
/*  59: 59 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0D);
/*  60:    */   }
/*  61:    */   
/*  62:    */   protected void scaleAttributes() {}
/*  63:    */   
/*  64:    */   protected boolean isAIEnabled()
/*  65:    */   {
/*  66: 69 */     return true;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void initBody()
/*  70:    */   {
/*  71: 81 */     this.firstTick = false;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void setPart(EntityPart entityPart, int partID)
/*  75:    */   {
/*  76: 85 */     this.firstTick = false;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public void setMonsterScale(float size)
/*  80:    */   {
/*  81: 89 */     if (size < 0.0F) {
/*  82: 90 */       size = 1.0F;
/*  83:    */     }
/*  84: 91 */     this.lvl = size;
/*  85: 92 */     this.size = (size * getSizeVariation() + getMinSize());
/*  86: 93 */     resize();
/*  87: 94 */     scaleAttributes();
/*  88: 95 */     heal(getMaxHealth());
/*  89:    */   }
/*  90:    */   
/*  91:    */   public float getMinSize()
/*  92:    */   {
/*  93: 98 */     return 1.0F;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public float getSizeVariation()
/*  97:    */   {
/*  98:101 */     return 1.0F;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public float getMonsterDificulty()
/* 102:    */   {
/* 103:105 */     return this.lvl;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public void onLivingUpdate()
/* 107:    */   {
/* 108:111 */     if (this.firstTick) {
/* 109:113 */       initBody();
/* 110:    */     }
/* 111:115 */     super.onLivingUpdate();
/* 112:117 */     if (this.rage > 0) {
/* 113:118 */       this.rage -= 1;
/* 114:    */     }
/* 115:119 */     updateArmSwingProgress();
/* 116:120 */     if ((this.ticksExisted % 50 == 0) && (getHealth() < getMaxHealth())) {
/* 117:122 */       if (getAttackTarget() != null)
/* 118:    */       {
/* 119:124 */         if (this.ticksExisted % 500 == 0) {
/* 120:125 */           heal(1.0F);
/* 121:    */         }
/* 122:    */       }
/* 123:    */       else {
/* 124:127 */         heal(1.0F);
/* 125:    */       }
/* 126:    */     }
/* 127:129 */     this.rotationYawHead = this.rotationYaw;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public void setRotationYawHead(float par1)
/* 131:    */   {
/* 132:134 */     if (!limitRotation()) {
/* 133:136 */       super.setRotationYawHead(par1);
/* 134:    */     }
/* 135:    */   }
/* 136:    */   
/* 137:    */   public void moveEntityWithHeading(float par1, float par2)
/* 138:    */   {
/* 139:143 */     if (limitRotation()) {
/* 140:145 */       if (!this.worldObj.isRemote) {
/* 141:146 */         if (this.rotationYaw - this.prevRotationYaw > this.rotSpeed) {
/* 142:147 */           this.rotationYaw = (this.prevRotationYaw + this.rotSpeed);
/* 143:148 */         } else if (this.rotationYaw - this.prevRotationYaw < -this.rotSpeed) {
/* 144:149 */           this.rotationYaw = (this.prevRotationYaw - this.rotSpeed);
/* 145:    */         }
/* 146:    */       }
/* 147:    */     }
/* 148:151 */     super.moveEntityWithHeading(par1, par2);
/* 149:    */   }
/* 150:    */   
/* 151:    */   protected boolean limitRotation()
/* 152:    */   {
/* 153:155 */     return true;
/* 154:    */   }
/* 155:    */   
/* 156:    */   public void moveAsBoss() {}
/* 157:    */   
/* 158:    */   public void attackEntity(Entity par1Entity, float par2)
/* 159:    */   {
/* 160:165 */     super.attackEntity(par1Entity, par2);
/* 161:    */   }
/* 162:    */   
/* 163:    */   public boolean attackEntityAsMob(Entity par1Entity)
/* 164:    */   {
/* 165:169 */     return attackEntityAsMob(par1Entity, 1.0F);
/* 166:    */   }
/* 167:    */   
/* 168:    */   public boolean attackEntityAsMob(Entity par1Entity, float damageScale)
/* 169:    */   {
/* 170:172 */     float damage = (float)getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
/* 171:173 */     DamageSource ds = new EntityDamageSource("mob", this);
/* 172:174 */     if ((par1Entity instanceof EntityCreeper)) {
/* 173:175 */       damage = 40.0F;
/* 174:    */     }
/* 175:176 */     return par1Entity.attackEntityFrom(ds, damage * damageScale);
/* 176:    */   }
/* 177:    */   
/* 178:    */   public void addAITasks() {}
/* 179:    */   
/* 180:    */   protected boolean canDespawn()
/* 181:    */   {
/* 182:185 */     return false;
/* 183:    */   }
/* 184:    */   
/* 185:    */   public boolean isAttacking()
/* 186:    */   {
/* 187:190 */     return getAnimFlag(0);
/* 188:    */   }
/* 189:    */   
/* 190:    */   public void setAttacking(boolean attacking)
/* 191:    */   {
/* 192:195 */     setAnimFlag(0, attacking);
/* 193:    */   }
/* 194:    */   
/* 195:    */   protected boolean getAnimFlag(int index)
/* 196:    */   {
/* 197:200 */     return (this.dataWatcher.getWatchableObjectByte(17) & 1 << index) != 0;
/* 198:    */   }
/* 199:    */   
/* 200:    */   protected void setAnimFlag(int index, boolean result)
/* 201:    */   {
/* 202:205 */     byte b = this.dataWatcher.getWatchableObjectByte(17);
/* 203:207 */     if (result) {
/* 204:209 */       this.dataWatcher.updateObject(17, Byte.valueOf((byte)(b | 1 << index)));
/* 205:    */     } else {
/* 206:213 */       this.dataWatcher.updateObject(17, Byte.valueOf((byte)(b & (1 << index ^ 0xFFFFFFFF))));
/* 207:    */     }
/* 208:    */   }
/* 209:    */   
/* 210:    */   public boolean attackEntityFrom(DamageSource ds, float damage)
/* 211:    */   {
/* 212:220 */     float td = damage;
/* 213:221 */     damage = ElementsHelper.getAmmountDecreased(this, damage, ds);
/* 214:222 */     boolean ret = super.attackEntityFrom(ds, damage);
/* 215:223 */     this.rage = ((int)(this.rage + damage));
/* 216:224 */     if ((ret) && (!this.worldObj.isRemote) && ((ds.getSourceOfDamage() instanceof EntityLivingBase))) {
/* 217:225 */       setAttackTarget((EntityLivingBase)ds.getSourceOfDamage());
/* 218:    */     }
/* 219:227 */     return ret;
/* 220:    */   }
/* 221:    */   
/* 222:    */   public boolean attackFromPart(DamageSource par1DamageSource, float par2, EntityPart part)
/* 223:    */   {
/* 224:232 */     return attackEntityFrom(par1DamageSource, par2);
/* 225:    */   }
/* 226:    */   
/* 227:    */   public void swingItem()
/* 228:    */   {
/* 229:237 */     if ((!this.isSwingInProgress) || (this.swingProgressInt < 0))
/* 230:    */     {
/* 231:239 */       this.swingProgressInt = -1;
/* 232:240 */       this.isSwingInProgress = true;
/* 233:241 */       if (!this.worldObj.isRemote)
/* 234:    */       {
/* 235:242 */         PacketEntityAnimation packet = new PacketEntityAnimation(getEntityId(), (byte)0);
/* 236:243 */         ChocolateQuest.channel.sendToAllAround(this, packet, 64);
/* 237:    */       }
/* 238:    */     }
/* 239:    */   }
/* 240:    */   
/* 241:    */   public float getScaleSize()
/* 242:    */   {
/* 243:249 */     return this.size;
/* 244:    */   }
/* 245:    */   
/* 246:    */   public void readSpawnData(ByteBuf additionalData)
/* 247:    */   {
/* 248:254 */     this.size = additionalData.readFloat();
/* 249:255 */     resize();
/* 250:256 */     onSpawn();
/* 251:    */   }
/* 252:    */   
/* 253:    */   public void writeSpawnData(ByteBuf buffer)
/* 254:    */   {
/* 255:261 */     buffer.writeFloat(this.size);
/* 256:262 */     onSpawn();
/* 257:    */   }
/* 258:    */   
/* 259:    */   public void readEntityFromNBT(NBTTagCompound nbt)
/* 260:    */   {
/* 261:267 */     super.readEntityFromNBT(nbt);
/* 262:268 */     this.size = nbt.getFloat("size");
/* 263:269 */     resize();
/* 264:    */   }
/* 265:    */   
/* 266:    */   public void writeEntityToNBT(NBTTagCompound nbt)
/* 267:    */   {
/* 268:274 */     super.writeEntityToNBT(nbt);
/* 269:275 */     nbt.setFloat("size", this.size);
/* 270:    */   }
/* 271:    */   
/* 272:    */   public boolean shouldMoveToEntity(double d1, Entity target)
/* 273:    */   {
/* 274:282 */     float sizeBB = this.width + target.width + this.size / 2.0F;
/* 275:283 */     return (d1 > sizeBB) && (!attackInProgress());
/* 276:    */   }
/* 277:    */   
/* 278:    */   protected void resize()
/* 279:    */   {
/* 280:287 */     setSize(this.size, this.size);
/* 281:    */   }
/* 282:    */   
/* 283:    */   public void knockBack(Entity entity, float par2, double par3, double par5) {}
/* 284:    */   
/* 285:    */   public AxisAlignedBB getBoundingBox()
/* 286:    */   {
/* 287:298 */     if (!this.ridableBB) {
/* 288:299 */       return null;
/* 289:    */     }
/* 290:300 */     return this.boundingBox;
/* 291:    */   }
/* 292:    */   
/* 293:    */   public AxisAlignedBB getCollisionBox(Entity entity)
/* 294:    */   {
/* 295:309 */     if ((entity.isEntityEqual(this)) || (!this.ridableBB)) {
/* 296:310 */       return null;
/* 297:    */     }
/* 298:311 */     return entity.boundingBox;
/* 299:    */   }
/* 300:    */   
/* 301:    */   public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {}
/* 302:    */   
/* 303:    */   public boolean canBePushed()
/* 304:    */   {
/* 305:329 */     return true;
/* 306:    */   }
/* 307:    */   
/* 308:    */   public void applyEntityCollision(Entity par1Entity)
/* 309:    */   {
/* 310:334 */     par1Entity.motionX += (par1Entity.posX - this.posX) / this.width;
/* 311:335 */     par1Entity.motionZ += (par1Entity.posZ - this.posZ) / this.width;
/* 312:    */   }
/* 313:    */   
/* 314:    */   public void onSpawn() {}
/* 315:    */   
/* 316:    */   public void animationBoss(byte animType) {}
/* 317:    */   
/* 318:    */   public void attackToXYZ(byte arm, double x, double y, double z) {}
/* 319:    */   
/* 320:    */   public boolean attackInProgress()
/* 321:    */   {
/* 322:353 */     return false;
/* 323:    */   }
/* 324:    */   
/* 325:    */   public boolean isRaging()
/* 326:    */   {
/* 327:357 */     return getHealth() < getMaxHealth() / 5.0F;
/* 328:    */   }
/* 329:    */   
/* 330:361 */   protected int physicDefense = 0;
/* 331:361 */   protected int magicDefense = 0;
/* 332:361 */   protected int blastDefense = 0;
/* 333:361 */   protected int fireDefense = 0;
/* 334:361 */   protected int projectileDefense = 0;
/* 335:    */   
/* 336:    */   public int getPhysicDefense()
/* 337:    */   {
/* 338:364 */     return this.physicDefense;
/* 339:    */   }
/* 340:    */   
/* 341:    */   public int getMagicDefense()
/* 342:    */   {
/* 343:367 */     return this.magicDefense;
/* 344:    */   }
/* 345:    */   
/* 346:    */   public int getBlastDefense()
/* 347:    */   {
/* 348:370 */     return this.blastDefense;
/* 349:    */   }
/* 350:    */   
/* 351:    */   public int getFireDefense()
/* 352:    */   {
/* 353:373 */     return this.fireDefense;
/* 354:    */   }
/* 355:    */   
/* 356:    */   public int getProjectileDefense()
/* 357:    */   {
/* 358:376 */     return this.projectileDefense;
/* 359:    */   }
/* 360:    */   
/* 361:    */   public boolean canAttackClass(Class par1Class)
/* 362:    */   {
/* 363:381 */     return (super.canAttackClass(par1Class)) && (par1Class != EntityReferee.class);
/* 364:    */   }
/* 365:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.EntityBaseBoss
 * JD-Core Version:    0.7.1
 */