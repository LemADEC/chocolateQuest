/*   1:    */ package com.chocolate.chocolateQuest.entity.npc;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   5:    */ import com.chocolate.chocolateQuest.entity.ai.AIHumanGoToPoint;
/*   6:    */ import com.chocolate.chocolateQuest.entity.ai.AITargetOwner;
/*   7:    */ import com.chocolate.chocolateQuest.entity.ai.HumanSelector;
/*   8:    */ import com.chocolate.chocolateQuest.entity.handHelper.HandEmpty;
/*   9:    */ import com.chocolate.chocolateQuest.entity.handHelper.HandGolem;
/*  10:    */ import com.chocolate.chocolateQuest.entity.handHelper.HandHelper;
/*  11:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  12:    */ import com.chocolate.chocolateQuest.entity.projectile.ProjectileRocket;
/*  13:    */ import com.chocolate.chocolateQuest.items.gun.ItemGolemWeapon;
/*  14:    */ import com.chocolate.chocolateQuest.magic.Elements;
/*  15:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*  16:    */ import com.chocolate.chocolateQuest.packets.PacketSpawnParticlesAround;
/*  17:    */ import cpw.mods.fml.common.registry.IThrowableEntity;
/*  18:    */ import java.util.List;
/*  19:    */ import net.minecraft.entity.DataWatcher;
/*  20:    */ import net.minecraft.entity.Entity;
/*  21:    */ import net.minecraft.entity.EntityLivingBase;
/*  22:    */ import net.minecraft.entity.INpc;
/*  23:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  24:    */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*  25:    */ import net.minecraft.entity.ai.EntityAILookIdle;
/*  26:    */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*  27:    */ import net.minecraft.entity.ai.EntityAITasks;
/*  28:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  29:    */ import net.minecraft.entity.monster.IMob;
/*  30:    */ import net.minecraft.entity.player.EntityPlayer;
/*  31:    */ import net.minecraft.entity.projectile.EntityArrow;
/*  32:    */ import net.minecraft.item.ItemStack;
/*  33:    */ import net.minecraft.util.AxisAlignedBB;
/*  34:    */ import net.minecraft.util.DamageSource;
/*  35:    */ import net.minecraft.util.MathHelper;
/*  36:    */ import net.minecraft.world.World;
/*  37:    */ 
/*  38:    */ public class EntityGolemMecha
/*  39:    */   extends EntityHumanBase
/*  40:    */   implements INpc
/*  41:    */ {
/*  42:    */   public static final byte SHIELD_WATCHER = 17;
/*  43: 42 */   int gunCD = 0;
/*  44:    */   private int CDshield;
/*  45:    */   private int shieldAmmount;
/*  46:    */   
/*  47:    */   public EntityGolemMecha(World world)
/*  48:    */   {
/*  49: 47 */     super(world);
/*  50: 48 */     this.fireDefense = 20;
/*  51: 49 */     this.blastDefense = 20;
/*  52: 50 */     this.magicDefense = -10;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public EntityGolemMecha(World world, EntityLivingBase owner)
/*  56:    */   {
/*  57: 54 */     this(world);
/*  58: 55 */     setOwner(owner);
/*  59:    */   }
/*  60:    */   
/*  61:    */   protected void entityInit()
/*  62:    */   {
/*  63: 62 */     super.entityInit();
/*  64: 63 */     this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
/*  65:    */   }
/*  66:    */   
/*  67:    */   protected void applyEntityAttributes()
/*  68:    */   {
/*  69: 68 */     super.applyEntityAttributes();
/*  70: 69 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.24D);
/*  71: 70 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
/*  72: 71 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
/*  73: 72 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
/*  74:    */   }
/*  75:    */   
/*  76:    */   protected void addAITasks()
/*  77:    */   {
/*  78: 76 */     this.tasks.addTask(1, new AIHumanGoToPoint(this));
/*  79: 77 */     this.tasks.addTask(8, new EntityAILookIdle(this));
/*  80: 78 */     setAIForCurrentMode();
/*  81:    */     
/*  82: 80 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
/*  83: 81 */     this.targetTasks.addTask(3, new AITargetOwner(this));
/*  84: 82 */     this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, new HumanSelector(this)));
/*  85: 83 */     this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityHumanBase.class, 0, true, false, new HumanSelector(this)));
/*  86: 84 */     this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, IMob.class, 0, true, false, null));
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void onLivingUpdate()
/*  90:    */   {
/*  91: 90 */     setSize(0.8F, 2.9F);
/*  92: 91 */     if ((this.riddenByEntity instanceof EntityPlayer))
/*  93:    */     {
/*  94: 93 */       EntityPlayer ridden = (EntityPlayer)this.riddenByEntity;
/*  95: 94 */       this.moveForwardHuman = 0.0F;
/*  96: 95 */       if (this.gunCD > 0) {
/*  97: 96 */         this.gunCD -= 1;
/*  98:    */       }
/*  99: 97 */       if ((ridden.isSwingInProgress) && (this.gunCD < 40))
/* 100:    */       {
/* 101: 99 */         ridden.swingProgress = 0.0F;
/* 102:100 */         ridden.swingProgressInt = 0;
/* 103:101 */         ridden.isSwingInProgress = false;
/* 104:103 */         if ((this.leftHand instanceof HandGolem))
/* 105:    */         {
/* 106:104 */           HandGolem hand = (HandGolem)this.leftHand;
/* 107:105 */           hand.onClick();
/* 108:    */         }
/* 109:    */       }
/* 110:    */     }
/* 111:109 */     if (!this.worldObj.isRemote)
/* 112:    */     {
/* 113:110 */       if ((hasElectricField()) && (this.ticksExisted % 18 - getUpgradeLevel(1) * 3 == 0))
/* 114:    */       {
/* 115:111 */         double expand = 1.5D;
/* 116:112 */         List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(expand, 1.0D, expand));
/* 117:113 */         for (int j = 0; j < list.size(); j++)
/* 118:    */         {
/* 119:115 */           Entity entity1 = (Entity)list.get(j);
/* 120:116 */           if (((entity1 instanceof EntityLivingBase)) && 
/* 121:    */           
/* 122:118 */             (entity1.canBeCollidedWith()) && (!isEntityEqual(entity1)) && (entity1 != this.riddenByEntity) && 
/* 123:    */             
/* 124:    */ 
/* 125:121 */             (!isOnSameTeam((EntityLivingBase)entity1)) && 
/* 126:122 */             (entity1.attackEntityFrom(Elements.blast.getDamageSource(this), 2.0F))) {
/* 127:    */             break;
/* 128:    */           }
/* 129:    */         }
/* 130:    */       }
/* 131:128 */       int rocketLevel = getUpgradeLevel(3);
/* 132:129 */       if ((rocketLevel > 0) && (this.ticksExisted % (100 - 20 * rocketLevel) == 0))
/* 133:    */       {
/* 134:131 */         double expand = 25.0D;
/* 135:132 */         List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(expand, 1.0D, expand));
/* 136:133 */         for (int j = 0; j < list.size(); j++)
/* 137:    */         {
/* 138:135 */           Entity entity1 = (Entity)list.get(j);
/* 139:136 */           if ((entity1 instanceof EntityLivingBase)) {
/* 140:138 */             if ((entity1.canBeCollidedWith()) && (!isEntityEqual(entity1)) && (entity1 != this.riddenByEntity)) {
/* 141:141 */               if ((((EntityLivingBase)entity1).getTeam() != null) && (!isOnSameTeam((EntityLivingBase)entity1)))
/* 142:    */               {
/* 143:142 */                 if (this.worldObj.isRemote) {
/* 144:    */                   break;
/* 145:    */                 }
/* 146:143 */                 EntityBaseBall ball = new EntityBaseBall(this.worldObj, this, 11, 0);
/* 147:144 */                 ball.setBallData(new ProjectileRocket(ball, entity1, 10));
/* 148:145 */                 ball.posY += 1.0D;
/* 149:146 */                 ball.motionY = 1.0D;
/* 150:147 */                 ball.motionX = (ball.motionZ = 0.0D);
/* 151:148 */                 this.worldObj.spawnEntityInWorld(ball);
/* 152:149 */                 break;
/* 153:    */               }
/* 154:    */             }
/* 155:    */           }
/* 156:    */         }
/* 157:    */       }
/* 158:156 */       int shieldLevel = getUpgradeLevel(2);
/* 159:157 */       if (hasElectricShield())
/* 160:    */       {
/* 161:158 */         double expand = 1.5D;
/* 162:159 */         List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(expand, 1.0D, expand));
/* 163:160 */         for (int j = 0; j < list.size(); j++)
/* 164:    */         {
/* 165:162 */           Entity entity1 = (Entity)list.get(j);
/* 166:163 */           boolean isProjectile = false;
/* 167:164 */           if ((entity1 instanceof IThrowableEntity)) {
/* 168:166 */             if (((IThrowableEntity)entity1).getThrower() != this)
/* 169:    */             {
/* 170:167 */               damageShield(20);
/* 171:168 */               isProjectile = true;
/* 172:    */             }
/* 173:    */           }
/* 174:171 */           if ((entity1 instanceof EntityArrow))
/* 175:    */           {
/* 176:173 */             damageShield(30);
/* 177:174 */             isProjectile = true;
/* 178:    */           }
/* 179:176 */           if ((isProjectile) && (!this.worldObj.isRemote))
/* 180:    */           {
/* 181:178 */             entity1.setDead();
/* 182:179 */             PacketSpawnParticlesAround packet = new PacketSpawnParticlesAround((byte)5, entity1.posX, entity1.posY, entity1.posZ);
/* 183:180 */             ChocolateQuest.channel.sendToAllAround(this, packet, 64);
/* 184:    */           }
/* 185:    */         }
/* 186:    */       }
/* 187:184 */       if (this.CDshield > 0)
/* 188:    */       {
/* 189:185 */         this.CDshield -= 1;
/* 190:186 */         if (this.CDshield == 0) {
/* 191:187 */           setShieldON(true);
/* 192:    */         }
/* 193:189 */         if (this.shieldAmmount < 50 + shieldLevel * 50) {
/* 194:190 */           this.shieldAmmount += shieldLevel;
/* 195:    */         }
/* 196:    */       }
/* 197:192 */       if ((this.shieldAmmount < 50 + shieldLevel * 50) && (this.ticksExisted % 2 == 0)) {
/* 198:193 */         this.shieldAmmount += 1;
/* 199:    */       }
/* 200:    */     }
/* 201:196 */     super.onLivingUpdate();
/* 202:    */   }
/* 203:    */   
/* 204:    */   public void updateHands()
/* 205:    */   {
/* 206:200 */     if ((getEquipmentInSlot(0) != null) && ((getEquipmentInSlot(0).getItem() instanceof ItemGolemWeapon))) {
/* 207:201 */       this.rightHand = new HandGolem(this, getEquipmentInSlot(0));
/* 208:    */     } else {
/* 209:203 */       this.rightHand = new HandHelper(this, getEquipmentInSlot(0));
/* 210:    */     }
/* 211:205 */     if ((getLeftHandItem() != null) && ((getLeftHandItem().getItem() instanceof ItemGolemWeapon))) {
/* 212:206 */       this.leftHand = new HandGolem(this, getLeftHandItem());
/* 213:    */     } else {
/* 214:208 */       this.leftHand = new HandEmpty(this, getLeftHandItem());
/* 215:    */     }
/* 216:    */   }
/* 217:    */   
/* 218:    */   public boolean isSitting()
/* 219:    */   {
/* 220:212 */     return false;
/* 221:    */   }
/* 222:    */   
/* 223:    */   public double getMountedYOffset()
/* 224:    */   {
/* 225:217 */     if ((this.riddenByEntity instanceof EntityPlayer)) {
/* 226:218 */       return 1.88D;
/* 227:    */     }
/* 228:219 */     return 1.1D;
/* 229:    */   }
/* 230:    */   
/* 231:    */   public float getSizeModifier()
/* 232:    */   {
/* 233:223 */     return 1.6F;
/* 234:    */   }
/* 235:    */   
/* 236:    */   public boolean attackEntityFrom(DamageSource damagesource, float i)
/* 237:    */   {
/* 238:227 */     Entity entity = damagesource.getEntity();
/* 239:228 */     if ((this.riddenByEntity != null) && ((damagesource.getEntity() instanceof EntityLivingBase)))
/* 240:    */     {
/* 241:230 */       EntityLivingBase e = (EntityLivingBase)entity;
/* 242:231 */       if (e == this.riddenByEntity) {
/* 243:232 */         return false;
/* 244:    */       }
/* 245:233 */       if (!isSuitableTargetAlly(e)) {
/* 246:234 */         setAttackTarget((EntityLivingBase)entity);
/* 247:    */       }
/* 248:    */     }
/* 249:236 */     if (hasElectricShield())
/* 250:    */     {
/* 251:237 */       if (damagesource.isProjectile())
/* 252:    */       {
/* 253:238 */         damageShield(30);
/* 254:239 */         return false;
/* 255:    */       }
/* 256:241 */       if ((entity != null) && 
/* 257:242 */         (getDistanceSqToEntity(entity) > 6.0D))
/* 258:    */       {
/* 259:243 */         if (((entity instanceof EntityPlayer)) && 
/* 260:244 */           (((EntityPlayer)entity).swingProgress != 0.0F)) {
/* 261:245 */           return false;
/* 262:    */         }
/* 263:247 */         damageShield((int)(i * 3.0F));
/* 264:248 */         return true;
/* 265:    */       }
/* 266:    */     }
/* 267:252 */     return super.attackEntityFrom(damagesource, i);
/* 268:    */   }
/* 269:    */   
/* 270:    */   protected boolean isAIEnabled()
/* 271:    */   {
/* 272:256 */     return true;
/* 273:    */   }
/* 274:    */   
/* 275:    */   public void moveEntityWithHeading(float par1, float par2)
/* 276:    */   {
/* 277:261 */     if (par2 < 0.0F) {
/* 278:262 */       par2 /= 3.0F;
/* 279:    */     }
/* 280:263 */     if ((this.riddenByEntity instanceof EntityPlayer))
/* 281:    */     {
/* 282:265 */       this.prevRotationYaw = (this.rotationYaw = this.riddenByEntity.rotationYaw);
/* 283:266 */       this.rotationPitch = (this.riddenByEntity.rotationPitch * 0.5F);
/* 284:267 */       setRotation(this.rotationYaw, this.rotationPitch);
/* 285:268 */       this.rotationYawHead = (this.renderYawOffset = this.rotationYaw);
/* 286:269 */       par1 = ((EntityLivingBase)this.riddenByEntity).moveStrafing;
/* 287:270 */       par1 /= 2.0F;
/* 288:271 */       par2 = ((EntityLivingBase)this.riddenByEntity).moveForward;
/* 289:272 */       if (par2 < 0.0F) {
/* 290:273 */         par2 /= 2.0F;
/* 291:    */       }
/* 292:274 */       float moveSpeed = (float)getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
/* 293:275 */       setAIMoveSpeed(moveSpeed / 2.0F);
/* 294:    */       
/* 295:277 */       this.stepHeight = 1.0F;
/* 296:278 */       if (!this.worldObj.isRemote) {
/* 297:280 */         super.moveEntityWithHeading(par1, par2);
/* 298:    */       }
/* 299:283 */       this.prevLimbSwingAmount = this.limbSwingAmount;
/* 300:284 */       double d0 = this.posX - this.prevPosX;
/* 301:285 */       double d1 = this.posZ - this.prevPosZ;
/* 302:286 */       float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;
/* 303:288 */       if (f4 > 1.0F) {
/* 304:290 */         f4 = 1.0F;
/* 305:    */       }
/* 306:293 */       this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
/* 307:294 */       this.limbSwing += this.limbSwingAmount;
/* 308:    */     }
/* 309:    */     else
/* 310:    */     {
/* 311:298 */       super.moveEntityWithHeading(par1, par2);
/* 312:    */     }
/* 313:    */   }
/* 314:    */   
/* 315:    */   public boolean interact(EntityPlayer entityPlayer)
/* 316:    */   {
/* 317:304 */     if (this.riddenByEntity == null)
/* 318:    */     {
/* 319:306 */       if (entityPlayer.isSneaking()) {
/* 320:307 */         return super.interact(entityPlayer);
/* 321:    */       }
/* 322:309 */       setAttackTarget(null);
/* 323:310 */       if (!this.worldObj.isRemote) {
/* 324:311 */         entityPlayer.mountEntity(this);
/* 325:    */       }
/* 326:    */     }
/* 327:312 */     else if ((this.rightHand instanceof HandGolem))
/* 328:    */     {
/* 329:313 */       HandGolem hand = (HandGolem)this.rightHand;
/* 330:314 */       hand.onClick();
/* 331:    */     }
/* 332:316 */     return false;
/* 333:    */   }
/* 334:    */   
/* 335:    */   protected String getLivingSound()
/* 336:    */   {
/* 337:321 */     return "none";
/* 338:    */   }
/* 339:    */   
/* 340:    */   protected String getHurtSound()
/* 341:    */   {
/* 342:324 */     return "mob.irongolem.hit";
/* 343:    */   }
/* 344:    */   
/* 345:    */   protected String getDeathSound()
/* 346:    */   {
/* 347:327 */     return "mob.irongolem.death";
/* 348:    */   }
/* 349:    */   
/* 350:    */   public boolean canRiderInteract()
/* 351:    */   {
/* 352:330 */     return true;
/* 353:    */   }
/* 354:    */   
/* 355:    */   public float getEyeHeight()
/* 356:    */   {
/* 357:334 */     return super.getEyeHeight();
/* 358:    */   }
/* 359:    */   
/* 360:    */   public boolean isTwoHanded()
/* 361:    */   {
/* 362:337 */     return false;
/* 363:    */   }
/* 364:    */   
/* 365:    */   public boolean canSprint()
/* 366:    */   {
/* 367:340 */     return false;
/* 368:    */   }
/* 369:    */   
/* 370:    */   protected boolean canDespawn()
/* 371:    */   {
/* 372:343 */     return false;
/* 373:    */   }
/* 374:    */   
/* 375:    */   public boolean canAimBeCanceled()
/* 376:    */   {
/* 377:346 */     return false;
/* 378:    */   }
/* 379:    */   
/* 380:    */   public boolean isInvisible()
/* 381:    */   {
/* 382:350 */     return false;
/* 383:    */   }
/* 384:    */   
/* 385:    */   public boolean isPushedByWater()
/* 386:    */   {
/* 387:354 */     return false;
/* 388:    */   }
/* 389:    */   
/* 390:    */   public void setFire(int par1) {}
/* 391:    */   
/* 392:    */   public int getTotalArmorValue()
/* 393:    */   {
/* 394:360 */     return getUpgradeLevel(0) * 5;
/* 395:    */   }
/* 396:    */   
/* 397:    */   public boolean hasElectricField()
/* 398:    */   {
/* 399:363 */     return getUpgradeLevel(1) > 0;
/* 400:    */   }
/* 401:    */   
/* 402:    */   public boolean hasElectricShield()
/* 403:    */   {
/* 404:366 */     return (this.CDshield == 0) && (getUpgradeLevel(2) > 0);
/* 405:    */   }
/* 406:    */   
/* 407:    */   public void setShieldON(boolean mode)
/* 408:    */   {
/* 409:370 */     this.dataWatcher.updateObject(17, Byte.valueOf((byte)(mode ? 0 : 1)));
/* 410:    */   }
/* 411:    */   
/* 412:    */   public boolean shieldON()
/* 413:    */   {
/* 414:373 */     return this.dataWatcher.getWatchableObjectByte(17) == 0;
/* 415:    */   }
/* 416:    */   
/* 417:    */   public void damageShield(int damage)
/* 418:    */   {
/* 419:377 */     this.shieldAmmount -= damage;
/* 420:378 */     if ((this.shieldAmmount <= 0) && (!this.worldObj.isRemote))
/* 421:    */     {
/* 422:379 */       this.shieldAmmount = 0;
/* 423:380 */       this.CDshield = 60;
/* 424:381 */       setShieldON(false);
/* 425:    */     }
/* 426:    */   }
/* 427:    */   
/* 428:    */   public int getUpgradeLevel(int upgrade)
/* 429:    */   {
/* 430:386 */     int armor = 0;
/* 431:387 */     for (int i = 1; i <= 4; i++)
/* 432:    */     {
/* 433:388 */       ItemStack is = getEquipmentInSlot(i);
/* 434:389 */       if ((is != null) && 
/* 435:390 */         (is.getItem() == ChocolateQuest.golemUpgrade) && 
/* 436:391 */         (is.getItemDamage() == upgrade)) {
/* 437:392 */         armor++;
/* 438:    */       }
/* 439:    */     }
/* 440:397 */     return armor;
/* 441:    */   }
/* 442:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.npc.EntityGolemMecha
 * JD-Core Version:    0.7.1
 */