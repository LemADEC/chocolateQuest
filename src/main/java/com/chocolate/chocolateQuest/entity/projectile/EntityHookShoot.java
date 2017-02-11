/*   1:    */ package com.chocolate.chocolateQuest.entity.projectile;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.items.IHookLauncher;
/*   5:    */ import com.chocolate.chocolateQuest.items.ItemArmorHeavy;
/*   6:    */ import com.chocolate.chocolateQuest.items.ItemHookShoot;
/*   7:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   8:    */ import com.chocolate.chocolateQuest.packets.PacketHookImpact;
/*   9:    */ import cpw.mods.fml.common.registry.IThrowableEntity;
/*  10:    */ import cpw.mods.fml.relauncher.Side;
/*  11:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  12:    */ import java.util.Random;
/*  13:    */ import net.minecraft.entity.DataWatcher;
/*  14:    */ import net.minecraft.entity.Entity;
/*  15:    */ import net.minecraft.entity.EntityLivingBase;
/*  16:    */ import net.minecraft.entity.player.EntityPlayer;
/*  17:    */ import net.minecraft.entity.player.InventoryPlayer;
/*  18:    */ import net.minecraft.entity.projectile.EntityThrowable;
/*  19:    */ import net.minecraft.item.ItemStack;
/*  20:    */ import net.minecraft.nbt.NBTTagCompound;
/*  21:    */ import net.minecraft.util.AxisAlignedBB;
/*  22:    */ import net.minecraft.util.MathHelper;
/*  23:    */ import net.minecraft.util.MovingObjectPosition;
/*  24:    */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*  25:    */ import net.minecraft.util.Vec3;
/*  26:    */ import net.minecraft.world.World;
/*  27:    */ 
/*  28:    */ public class EntityHookShoot
/*  29:    */   extends EntityThrowable
/*  30:    */   implements IThrowableEntity
/*  31:    */ {
/*  32: 27 */   public ItemStack item = null;
/*  33: 28 */   int lifeTime = 0;
/*  34:    */   EntityLivingBase shootingEntity;
/*  35: 31 */   public double radio = 0.0D;
/*  36: 33 */   public boolean returning = false;
/*  37: 34 */   public boolean reeling = false;
/*  38:    */   public static final byte hookManual = 2;
/*  39:    */   public static final byte hookSpider = 3;
/*  40:    */   public static final byte hookWeapon = 4;
/*  41:    */   public static final byte hookSpiderBoss = 5;
/*  42:    */   public Entity hookedEntity;
/*  43:    */   public double hookedAtHeight;
/*  44:    */   public double hookedAtDistance;
/*  45:    */   public double hookedAtAngle;
/*  46:    */   public int blockX;
/*  47:    */   public int blockY;
/*  48:    */   public int blockZ;
/*  49:    */   
/*  50:    */   public EntityHookShoot(World par1World)
/*  51:    */   {
/*  52: 43 */     super(par1World);
/*  53:    */   }
/*  54:    */   
/*  55:    */   public EntityHookShoot(World world, EntityLivingBase entityliving, int type)
/*  56:    */   {
/*  57: 48 */     super(world, entityliving);
/*  58: 49 */     this.shootingEntity = entityliving;
/*  59: 50 */     setHookType(type);
/*  60: 51 */     float s = 0.6F;
/*  61: 52 */     setSize(s, s);
/*  62: 53 */     this.worldObj.playSoundEffect((int)this.posX, (int)this.posY, (int)this.posZ, "random.bow", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
/*  63:    */   }
/*  64:    */   
/*  65:    */   public EntityHookShoot(World world, EntityLivingBase entityliving, int type, ItemStack item)
/*  66:    */   {
/*  67: 58 */     this(world, entityliving, type);
/*  68: 59 */     this.item = item;
/*  69:    */   }
/*  70:    */   
/*  71:    */   protected void entityInit()
/*  72:    */   {
/*  73: 65 */     this.dataWatcher.addObject(10, Byte.valueOf((byte)0));
/*  74: 66 */     this.dataWatcher.addObject(11, Byte.valueOf((byte)0));
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void setReeling(boolean b)
/*  78:    */   {
/*  79: 71 */     this.dataWatcher.updateObject(10, Byte.valueOf((byte)(b ? 1 : 0)));
/*  80:    */   }
/*  81:    */   
/*  82:    */   public boolean isReeling()
/*  83:    */   {
/*  84: 76 */     return this.dataWatcher.getWatchableObjectByte(10) == 1;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void setHookType(int par)
/*  88:    */   {
/*  89: 81 */     this.dataWatcher.updateObject(11, Byte.valueOf((byte)par));
/*  90:    */   }
/*  91:    */   
/*  92:    */   public byte getHookType()
/*  93:    */   {
/*  94: 86 */     return this.dataWatcher.getWatchableObjectByte(11);
/*  95:    */   }
/*  96:    */   
/*  97:    */   @SideOnly(Side.CLIENT)
/*  98:    */   public boolean isInRangeToRenderDist(double par1)
/*  99:    */   {
/* 100: 96 */     double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
/* 101: 97 */     d1 *= 64.0D;
/* 102: 98 */     return par1 < d1 * d1;
/* 103:    */   }
/* 104:    */   
/* 105:    */   protected void onImpact(MovingObjectPosition mop)
/* 106:    */   {
/* 107:105 */     if (!isReeling())
/* 108:    */     {
/* 109:106 */       if ((mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) && (!this.worldObj.isRemote))
/* 110:    */       {
/* 111:108 */         this.blockX = mop.blockX;
/* 112:109 */         this.blockY = mop.blockY;
/* 113:110 */         this.blockZ = mop.blockZ;
/* 114:111 */         PacketHookImpact packet = new PacketHookImpact(getEntityId(), mop.blockX, mop.blockY, mop.blockZ);
/* 115:112 */         ChocolateQuest.channel.sendToAllAround(this, packet);
/* 116:113 */         setPosition(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);
/* 117:114 */         this.onGround = true;
/* 118:    */       }
/* 119:117 */       if (((mop.entityHit instanceof Entity)) && (!this.worldObj.isRemote))
/* 120:    */       {
/* 121:119 */         if ((mop.entityHit instanceof EntityThrowable)) {
/* 122:120 */           return;
/* 123:    */         }
/* 124:121 */         if (!mop.entityHit.isEntityEqual(getThrower()))
/* 125:    */         {
/* 126:123 */           this.hookedEntity = mop.entityHit;
/* 127:124 */           this.hookedAtHeight = Math.min(this.hookedEntity.height, this.posY - this.hookedEntity.posY);
/* 128:125 */           this.hookedAtAngle = (Math.atan2(this.hookedEntity.posZ - this.posZ, this.hookedEntity.posX - this.posX) * 180.0D / 3.141592653589793D + 90.0D);
/* 129:126 */           this.hookedAtAngle -= this.hookedEntity.rotationYaw;
/* 130:127 */           this.hookedAtDistance = Math.min(this.hookedEntity.width, Math.sqrt(Math.abs(this.hookedEntity.posX - this.posX + this.hookedEntity.posZ - this.posZ)));
/* 131:128 */           PacketHookImpact packet = new PacketHookImpact(getEntityId(), this.hookedEntity.getEntityId(), this.hookedAtAngle, this.hookedAtDistance, this.hookedAtHeight);
/* 132:129 */           ChocolateQuest.channel.sendToAllAround(this, packet);
/* 133:130 */           this.returning = true;
/* 134:131 */           this.onGround = true;
/* 135:    */         }
/* 136:    */       }
/* 137:134 */       if ((!this.worldObj.isRemote) && (this.onGround))
/* 138:    */       {
/* 139:135 */         setReeling(true);
/* 140:136 */         onImpact();
/* 141:    */       }
/* 142:    */     }
/* 143:    */   }
/* 144:    */   
/* 145:    */   public void onImpact()
/* 146:    */   {
/* 147:142 */     this.radio += 1.0D;
/* 148:    */   }
/* 149:    */   
/* 150:    */   public void onUpdate()
/* 151:    */   {
/* 152:147 */     if (((getThrower() != null) && (getThrower().isDead)) || (!this.worldObj.blockExists((int)this.posX, (int)this.posY, (int)this.posZ)))
/* 153:    */     {
/* 154:149 */       setDead();
/* 155:    */     }
/* 156:    */     else
/* 157:    */     {
/* 158:153 */       super.onUpdate();
/* 159:154 */       if (this.hookedEntity != null)
/* 160:    */       {
/* 161:156 */         float angle = (float)((this.hookedAtAngle + this.hookedEntity.rotationYaw) * 3.141599893569946D / 180.0D);
/* 162:157 */         this.posX = (this.hookedEntity.posX - MathHelper.sin(angle) * this.hookedAtDistance);
/* 163:158 */         this.posY = (this.hookedEntity.posY + this.hookedAtHeight);
/* 164:159 */         this.posZ = (this.hookedEntity.posZ + MathHelper.cos(angle) * this.hookedAtDistance);
/* 165:160 */         if (!this.hookedEntity.isEntityAlive())
/* 166:    */         {
/* 167:161 */           this.hookedEntity = null;
/* 168:162 */           setReeling(false);
/* 169:    */         }
/* 170:    */       }
/* 171:165 */       if (isReeling())
/* 172:    */       {
/* 173:167 */         this.motionX = 0.0D;
/* 174:168 */         this.motionY = 0.0D;
/* 175:169 */         this.motionZ = 0.0D;
/* 176:    */       }
/* 177:172 */       this.lifeTime += 1;
/* 178:173 */       if (getThrower() != null)
/* 179:    */       {
/* 180:175 */         EntityLivingBase shooting = getThrower();
/* 181:176 */         double dist = getDistanceToEntity(getThrower());
/* 182:178 */         if (dist > this.radio) {
/* 183:179 */           getThrower().fallDistance = 0.0F;
/* 184:    */         }
/* 185:180 */         if ((shooting instanceof EntityPlayer))
/* 186:    */         {
/* 187:181 */           if ((shooting.isSneaking()) && (this.radio > 0.0D))
/* 188:    */           {
/* 189:183 */             this.radio -= 0.4D;
/* 190:184 */             if ((isSpiderHook()) && (isReeling()) && ((this.shootingEntity instanceof EntityPlayer)))
/* 191:    */             {
/* 192:186 */               this.radio -= 1.0D;
/* 193:187 */               if (dist >= this.radio)
/* 194:    */               {
/* 195:188 */                 double distY = this.posY - shooting.posY;
/* 196:189 */                 if (distY > 0.0D) {
/* 197:190 */                   shooting.motionY += Math.max(0.02D, distY * 0.1D);
/* 198:    */                 }
/* 199:191 */                 double maxMotion = Math.min(1.4D, Math.max(1.1D, distY / 10.0D));
/* 200:192 */                 if (shooting.motionY > maxMotion) {
/* 201:193 */                   shooting.motionY = maxMotion;
/* 202:    */                 }
/* 203:    */               }
/* 204:    */             }
/* 205:    */           }
/* 206:    */         }
/* 207:    */         else {
/* 208:198 */           this.radio -= 1.2D;
/* 209:    */         }
/* 210:200 */         if (((shooting.swingProgress > 0.0F) || (shooting.isSprinting())) && (this.radio < getMaxRadius())) {
/* 211:202 */           if ((shooting.getEquipmentInSlot(0) != null) && ((shooting.getEquipmentInSlot(0).getItem() instanceof ItemHookShoot))) {
/* 212:203 */             this.radio += 0.5D;
/* 213:    */           }
/* 214:    */         }
/* 215:206 */         if (isReeling())
/* 216:    */         {
/* 217:208 */           if (!this.worldObj.isRemote) {
/* 218:210 */             if ((this.worldObj.isAirBlock(this.blockX, this.blockY, this.blockZ)) && (this.hookedEntity == null)) {
/* 219:213 */               setReeling(false);
/* 220:    */             }
/* 221:    */           }
/* 222:216 */           boolean pullToShooter = false;
/* 223:217 */           Entity ride = this.hookedEntity;
/* 224:218 */           if (ride != null)
/* 225:    */           {
/* 226:220 */             if ((ride instanceof EntityPlayer)) {
/* 227:222 */               if (ride.isSneaking()) {
/* 228:223 */                 setDead();
/* 229:    */               }
/* 230:    */             }
/* 231:225 */             float ridenSize = ride.width * 2.0F + ride.height;
/* 232:226 */             float shootingSize = shooting.width * 2.0F + shooting.height + 0.2F;
/* 233:227 */             for (int i = 1; i <= 4; i++)
/* 234:    */             {
/* 235:229 */               ItemStack boots = shooting.getEquipmentInSlot(i);
/* 236:230 */               if ((boots != null) && (boots.getItem() != null)) {
/* 237:232 */                 if ((boots.getItem() instanceof ItemArmorHeavy)) {
/* 238:233 */                   shootingSize += 5.0F;
/* 239:    */                 }
/* 240:    */               }
/* 241:    */             }
/* 242:236 */             if ((shootingSize > ridenSize) && (this.hookedEntity.canBePushed())) {
/* 243:238 */               pullToShooter = true;
/* 244:    */             }
/* 245:    */           }
/* 246:241 */           double pullSpeed = 0.0D;
/* 247:242 */           if (manualPull())
/* 248:    */           {
/* 249:244 */             double playerSpeed = Math.sqrt(shooting.motionX * shooting.motionX + shooting.motionZ * shooting.motionZ + shooting.motionY * shooting.motionY);
/* 250:245 */             playerSpeed *= Math.max(1.0D, getDistanceSqToEntity(shooting) / (this.radio * this.radio));
/* 251:246 */             playerSpeed /= 10.0D;
/* 252:248 */             if ((playerSpeed > shooting.jumpMovementFactor) && (!pullToShooter)) {
/* 253:250 */               shooting.jumpMovementFactor = ((float)Math.min(playerSpeed, 0.14D));
/* 254:    */             }
/* 255:    */           }
/* 256:    */           else
/* 257:    */           {
/* 258:255 */             float max = shooting.height;
/* 259:256 */             if ((shooting instanceof EntityPlayer)) {
/* 260:257 */               max = 0.0F;
/* 261:    */             }
/* 262:258 */             this.radio = Math.max(shooting.height, this.radio - Math.min(0.6D, this.radio / 30.0D));
/* 263:    */           }
/* 264:261 */           pullSpeed = dist - 0.2D;
/* 265:262 */           pullSpeed -= this.radio;
/* 266:263 */           if (pullSpeed < 0.0D) {
/* 267:264 */             pullSpeed = 0.0D;
/* 268:    */           }
/* 269:265 */           if (pullSpeed > 2.0D) {
/* 270:266 */             pullSpeed = 2.0D;
/* 271:    */           }
/* 272:267 */           pullSpeed *= 0.03D;
/* 273:    */           
/* 274:269 */           Vec3 fc = Vec3.createVectorHelper(shooting.posX - this.posX, shooting.posY - this.posY, shooting.posZ - this.posZ);
/* 275:270 */           fc.normalize();
/* 276:271 */           fc.xCoord *= pullSpeed;
/* 277:272 */           fc.zCoord *= pullSpeed;
/* 278:273 */           fc.yCoord *= pullSpeed;
/* 279:274 */           if (pullToShooter)
/* 280:    */           {
/* 281:276 */             if ((dist > this.radio) || (this.radio < 1.0D))
/* 282:    */             {
/* 283:278 */               double s = 1.0D;
/* 284:279 */               ride.motionX = fc.xCoord;
/* 285:280 */               ride.motionY = fc.yCoord;
/* 286:281 */               ride.motionZ = fc.zCoord;
/* 287:282 */               this.motionX = fc.xCoord;
/* 288:283 */               this.motionY = fc.yCoord;
/* 289:284 */               this.motionZ = fc.zCoord;
/* 290:    */             }
/* 291:    */           }
/* 292:    */           else
/* 293:    */           {
/* 294:289 */             shooting.motionX -= fc.xCoord;
/* 295:290 */             shooting.motionZ -= fc.zCoord;
/* 296:291 */             shooting.motionY -= fc.yCoord;
/* 297:    */           }
/* 298:    */         }
/* 299:    */         else
/* 300:    */         {
/* 301:295 */           if (dist > getMaxRadius()) {
/* 302:297 */             this.returning = true;
/* 303:    */           }
/* 304:299 */           if (this.returning)
/* 305:    */           {
/* 306:301 */             this.radio = dist;
/* 307:302 */             Vec3 fc = Vec3.createVectorHelper(shooting.posX - this.posX, shooting.posY + 1.4D - this.posY, shooting.posZ - this.posZ);
/* 308:303 */             fc.normalize();
/* 309:304 */             double s = 0.2D;
/* 310:305 */             if (getHookType() == 5) {
/* 311:306 */               s = 0.05D;
/* 312:    */             }
/* 313:307 */             boolean flag = true;
/* 314:308 */             this.motionX = (fc.xCoord * s);
/* 315:309 */             this.motionY = (fc.yCoord * s);
/* 316:310 */             this.motionZ = (fc.zCoord * s);
/* 317:    */           }
/* 318:    */           else
/* 319:    */           {
/* 320:314 */             this.radio = (dist < getMaxRadius() ? dist : getMaxRadius());
/* 321:    */           }
/* 322:    */         }
/* 323:317 */         if ((this.returning) && (!isReeling())) {
/* 324:319 */           if (dist <= shooting.width + shooting.height) {
/* 325:321 */             setDead();
/* 326:    */           }
/* 327:    */         }
/* 328:    */       }
/* 329:    */     }
/* 330:    */   }
/* 331:    */   
/* 332:    */   protected float getGravityVelocity()
/* 333:    */   {
/* 334:334 */     return 0.0F;
/* 335:    */   }
/* 336:    */   
/* 337:    */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
/* 338:    */   
/* 339:    */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
/* 340:    */   {
/* 341:349 */     setDead();
/* 342:    */   }
/* 343:    */   
/* 344:    */   public boolean canBeCollidedWith()
/* 345:    */   {
/* 346:358 */     return true;
/* 347:    */   }
/* 348:    */   
/* 349:    */   public ItemStack getHookShoot(EntityPlayer ep)
/* 350:    */   {
/* 351:366 */     if (ep != null) {
/* 352:368 */       for (int i = 0; i < ep.inventory.getSizeInventory(); i++)
/* 353:    */       {
/* 354:370 */         ItemStack is = ep.inventory.getStackInSlot(i);
/* 355:371 */         if (is != null)
/* 356:    */         {
/* 357:373 */           int id = getEntityId();
/* 358:374 */           if (((is.getItem() instanceof IHookLauncher)) && (((IHookLauncher)is.getItem()).getHookID(is) == id)) {
/* 359:376 */             return is;
/* 360:    */           }
/* 361:    */         }
/* 362:    */       }
/* 363:    */     }
/* 364:382 */     return null;
/* 365:    */   }
/* 366:    */   
/* 367:    */   public void setDead()
/* 368:    */   {
/* 369:388 */     if ((getThrower() instanceof EntityPlayer))
/* 370:    */     {
/* 371:390 */       ItemStack is = getHookShoot((EntityPlayer)getThrower());
/* 372:392 */       if (is != null) {
/* 373:394 */         ((IHookLauncher)is.getItem()).setHookID(is, 0);
/* 374:    */       }
/* 375:    */     }
/* 376:397 */     super.setDead();
/* 377:    */   }
/* 378:    */   
/* 379:    */   public double getRadius()
/* 380:    */   {
/* 381:402 */     return this.radio;
/* 382:    */   }
/* 383:    */   
/* 384:    */   public int getRopeColor()
/* 385:    */   {
/* 386:410 */     byte type = getHookType();
/* 387:411 */     return (type == 3) || (type == 5) ? 16777215 : 4473924;
/* 388:    */   }
/* 389:    */   
/* 390:    */   public int getMaxRadius()
/* 391:    */   {
/* 392:416 */     if (getHookType() == 3) {
/* 393:418 */       return 40;
/* 394:    */     }
/* 395:420 */     if (getHookType() == 2) {
/* 396:422 */       return 30;
/* 397:    */     }
/* 398:424 */     if (getHookType() == 1) {
/* 399:426 */       return 25;
/* 400:    */     }
/* 401:428 */     if (getHookType() == 0) {
/* 402:430 */       return 15;
/* 403:    */     }
/* 404:433 */     return 40;
/* 405:    */   }
/* 406:    */   
/* 407:    */   public EntityLivingBase getThrower()
/* 408:    */   {
/* 409:439 */     return this.shootingEntity;
/* 410:    */   }
/* 411:    */   
/* 412:    */   public void setThrower(Entity entity)
/* 413:    */   {
/* 414:445 */     if ((entity instanceof EntityLivingBase)) {
/* 415:447 */       this.shootingEntity = ((EntityLivingBase)entity);
/* 416:    */     }
/* 417:    */   }
/* 418:    */   
/* 419:    */   protected boolean isSpiderHook()
/* 420:    */   {
/* 421:452 */     int hookType = getHookType();
/* 422:453 */     return (hookType == 3) || (hookType == 4);
/* 423:    */   }
/* 424:    */   
/* 425:    */   private boolean manualPull()
/* 426:    */   {
/* 427:456 */     int hookType = getHookType();
/* 428:457 */     return (hookType == 2) || (hookType == 3) || (hookType == 4);
/* 429:    */   }
/* 430:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.EntityHookShoot
 * JD-Core Version:    0.7.1
 */