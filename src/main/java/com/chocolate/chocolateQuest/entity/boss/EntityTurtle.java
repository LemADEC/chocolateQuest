/*   1:    */ package com.chocolate.chocolateQuest.entity.boss;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.entity.ai.AIBossAttack;
/*   5:    */ import com.chocolate.chocolateQuest.entity.ai.AITargetHurtBy;
/*   6:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*   7:    */ import java.util.List;
/*   8:    */ import java.util.Random;
/*   9:    */ import net.minecraft.entity.DataWatcher;
/*  10:    */ import net.minecraft.entity.Entity;
/*  11:    */ import net.minecraft.entity.EntityLivingBase;
/*  12:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  13:    */ import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
/*  14:    */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*  15:    */ import net.minecraft.entity.ai.EntityAISwimming;
/*  16:    */ import net.minecraft.entity.ai.EntityAITasks;
/*  17:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  18:    */ import net.minecraft.entity.item.EntityItem;
/*  19:    */ import net.minecraft.entity.monster.EntityMob;
/*  20:    */ import net.minecraft.entity.player.EntityPlayer;
/*  21:    */ import net.minecraft.init.Items;
/*  22:    */ import net.minecraft.item.ItemStack;
/*  23:    */ import net.minecraft.util.AxisAlignedBB;
/*  24:    */ import net.minecraft.util.DamageSource;
/*  25:    */ import net.minecraft.util.MathHelper;
/*  26:    */ import net.minecraft.world.EnumSkyBlock;
/*  27:    */ import net.minecraft.world.World;
/*  28:    */ 
/*  29:    */ public class EntityTurtle
/*  30:    */   extends EntityBaseBoss
/*  31:    */ {
/*  32:    */   private EntityTurtlePart[] body;
/*  33:    */   double tempRYaw;
/*  34:    */   double tempRPich;
/*  35: 33 */   int rotAccel = 0;
/*  36: 34 */   boolean rotDir = true;
/*  37: 35 */   boolean bubbleAttack = false;
/*  38: 36 */   int bubbleCD = 0;
/*  39:    */   boolean hurt;
/*  40:    */   int[] healPart;
/*  41: 39 */   boolean hurtOnPartSoundFlag = false;
/*  42:    */   double tempmx;
/*  43:    */   double tempmz;
/*  44:    */   double tempmy;
/*  45:    */   
/*  46:    */   public EntityTurtle(World par1World)
/*  47:    */   {
/*  48: 43 */     super(par1World);
/*  49: 44 */     this.experienceValue = 5;
/*  50: 45 */     this.body = new EntityTurtlePart[5];
/*  51: 46 */     this.fireDefense = 20;
/*  52: 47 */     this.blastDefense = 20;
/*  53: 48 */     this.limitRotation = true;
/*  54:    */   }
/*  55:    */   
/*  56:    */   protected void scaleAttributes()
/*  57:    */   {
/*  58: 52 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D + this.lvl * 0.005D);
/*  59: 53 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0F + this.lvl);
/*  60: 54 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400.0D);
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void addAITasks()
/*  64:    */   {
/*  65: 59 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  66: 60 */     this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 1.0F));
/*  67: 61 */     this.tasks.addTask(2, new AIBossAttack(this, 1.0F, false));
/*  68: 62 */     this.targetTasks.addTask(1, new AITargetHurtBy(this, false));
/*  69: 63 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  70: 64 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true));
/*  71:    */   }
/*  72:    */   
/*  73:    */   public float getMinSize()
/*  74:    */   {
/*  75: 68 */     return 2.0F;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public float getSizeVariation()
/*  79:    */   {
/*  80: 72 */     return 0.4F;
/*  81:    */   }
/*  82:    */   
/*  83:    */   protected void resize()
/*  84:    */   {
/*  85: 76 */     setSize(this.size, this.size * 0.4F);
/*  86:    */   }
/*  87:    */   
/*  88:    */   public void initBody()
/*  89:    */   {
/*  90: 81 */     super.initBody();
/*  91: 82 */     float dist = this.size - this.size / 4.0F;
/*  92: 84 */     if (!this.worldObj.isRemote)
/*  93:    */     {
/*  94: 86 */       this.body[0] = new EntityTurtlePart(this.worldObj, 0, 0.0F, this.size - this.size / 3.0F, this.size / 10.0F);
/*  95: 87 */       this.body[0].setMainBody(this);
/*  96: 88 */       this.body[0].setHead();
/*  97: 89 */       scalePart(this.body[0]);
/*  98: 91 */       if (!this.worldObj.isRemote) {
/*  99: 93 */         this.worldObj.spawnEntityInWorld(this.body[0]);
/* 100:    */       }
/* 101: 95 */       int[] pos = { 45, -45, 135, -135 };
/* 102: 96 */       for (int i = 1; i <= 4; i++)
/* 103:    */       {
/* 104: 98 */         this.body[i] = new EntityTurtlePart(this.worldObj, i, pos[(i - 1)], dist);
/* 105: 99 */         this.body[i].setMainBody(this);
/* 106:100 */         this.body[i].setPosition(this.posX, this.posY, this.posZ);
/* 107:101 */         this.worldObj.spawnEntityInWorld(this.body[i]);
/* 108:    */       }
/* 109:    */     }
/* 110:    */   }
/* 111:    */   
/* 112:    */   protected boolean isAIEnabled()
/* 113:    */   {
/* 114:109 */     return (!isAttacking()) && (!isHealing());
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void onUpdate()
/* 118:    */   {
/* 119:116 */     if ((!this.worldObj.isRemote) && 
/* 120:117 */       (getHealth() < 50.0F) && (!isHealing()) && (this.body[0] != null))
/* 121:    */     {
/* 122:119 */       for (int i = 0; i < 5; i++) {
/* 123:121 */         if (this.body[i] != null) {
/* 124:123 */           this.body[i].setHeartsLife(this.body[i].getMaxHealth());
/* 125:    */         }
/* 126:    */       }
/* 127:126 */       setHealing(true);
/* 128:    */     }
/* 129:129 */     for (int i = 0; i < this.body.length; i++) {
/* 130:130 */       if ((this.body[i] != null) && (!this.body[i].isEntityAlive())) {
/* 131:131 */         this.body[i] = null;
/* 132:    */       }
/* 133:    */     }
/* 134:135 */     if (isHealing())
/* 135:    */     {
/* 136:137 */       if (this.worldObj.isRemote) {
/* 137:139 */         this.worldObj.spawnParticle("heart", this.posX + this.rand.nextDouble() * 2.0D - 1.0D, this.posY + 1.0D + this.rand.nextDouble() * 2.0D, this.posZ + this.rand.nextDouble() * 2.0D - 1.0D, 0.0D, 1.0D, 0.0D);
/* 138:    */       }
/* 139:141 */       heal(1.0F);
/* 140:142 */       if (getHealth() >= getMaxHealth()) {
/* 141:144 */         setHealing(false);
/* 142:    */       }
/* 143:    */     }
/* 144:148 */     if (getAttackTarget() != null)
/* 145:    */     {
/* 146:150 */       if ((this.inWater) || (handleLavaMovement())) {
/* 147:152 */         if ((this.posY + 3.0D < getAttackTarget().posY) && (this.motionY < 0.2D)) {
/* 148:154 */           this.motionY += 0.03D;
/* 149:156 */         } else if ((this.posY + 3.0D > getAttackTarget().posY) && (this.motionY > -0.2D)) {
/* 150:158 */           this.motionY -= 0.03D;
/* 151:    */         }
/* 152:    */       }
/* 153:    */     }
/* 154:162 */     else if ((this.inWater) && (this.motionY < 0.1D)) {
/* 155:164 */       this.motionY -= 0.03D;
/* 156:    */     }
/* 157:167 */     super.onUpdate();
/* 158:170 */     if (isAttacking()) {
/* 159:172 */       doAttack();
/* 160:    */     }
/* 161:175 */     if (this.bubbleAttack) {
/* 162:177 */       bubbleRay();
/* 163:    */     }
/* 164:180 */     if ((isAttacking()) || (isHealing()))
/* 165:    */     {
/* 166:182 */       hidePartsInShell();
/* 167:183 */       hideHeadInShell();
/* 168:    */     }
/* 169:    */     else
/* 170:    */     {
/* 171:185 */       for (int i = 0; i < 5; i++) {
/* 172:187 */         if (this.body[i] != null) {
/* 173:189 */           this.body[i].staticPart = true;
/* 174:    */         }
/* 175:    */       }
/* 176:    */     }
/* 177:    */   }
/* 178:    */   
/* 179:    */   private void hidePartsInShell()
/* 180:    */   {
/* 181:196 */     for (int i = 1; i < 5; i++) {
/* 182:198 */       if (this.body[i] != null)
/* 183:    */       {
/* 184:200 */         this.body[i].staticPart = false;
/* 185:201 */         this.body[i].setPositionAndRotation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
/* 186:    */       }
/* 187:    */     }
/* 188:    */   }
/* 189:    */   
/* 190:    */   private void hideHeadInShell()
/* 191:    */   {
/* 192:206 */     if (this.body[0] != null)
/* 193:    */     {
/* 194:208 */       this.body[0].staticPart = false;
/* 195:209 */       this.body[0].setPositionAndRotation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
/* 196:    */     }
/* 197:    */   }
/* 198:    */   
/* 199:    */   public void attackEntity(Entity entity, float f)
/* 200:    */   {
/* 201:216 */     if ((!isAttacking()) && (!this.bubbleAttack) && (!isHealing()))
/* 202:    */     {
/* 203:218 */       float width = this.size * 0.3F;
/* 204:219 */       width = width * width + this.width * this.width;
/* 205:220 */       int attackChance = (int)(f - width - entity.width * entity.width);
/* 206:221 */       attackChance = attackChance > 80 ? 80 : attackChance < 40 ? 40 : attackChance;
/* 207:222 */       if (this.rand.nextInt(attackChance) == 0) {
/* 208:224 */         if (this.rand.nextInt(3) == 0)
/* 209:    */         {
/* 210:226 */           setAttacking(true);
/* 211:227 */           this.rotDir = true;
/* 212:    */         }
/* 213:    */         else
/* 214:    */         {
/* 215:231 */           double d = this.posX - entity.posX;
/* 216:232 */           double d1 = this.posY - (entity.posY + entity.height / 2.0F);
/* 217:233 */           double d2 = this.posZ - entity.posZ;
/* 218:234 */           float angle = (float)Math.atan2(d, d2);
/* 219:    */           
/* 220:236 */           angle = this.rotationYaw - entity.rotationYaw;
/* 221:238 */           while (angle > 360.0F) {
/* 222:238 */             angle -= 360.0F;
/* 223:    */           }
/* 224:240 */           while (angle < 0.0F) {
/* 225:240 */             angle += 360.0F;
/* 226:    */           }
/* 227:242 */           angle = Math.abs(angle - 180.0F);
/* 228:244 */           if (angle < 60.0F) {
/* 229:246 */             startBubble();
/* 230:    */           }
/* 231:    */         }
/* 232:    */       }
/* 233:    */     }
/* 234:    */   }
/* 235:    */   
/* 236:    */   protected boolean limitRotation()
/* 237:    */   {
/* 238:255 */     return !isAttacking();
/* 239:    */   }
/* 240:    */   
/* 241:    */   public void doAttack()
/* 242:    */   {
/* 243:261 */     if (getAttackTarget() == null) {
/* 244:262 */       return;
/* 245:    */     }
/* 246:263 */     if (this.rotDir)
/* 247:    */     {
/* 248:265 */       this.rotAccel += 1;
/* 249:    */     }
/* 250:    */     else
/* 251:    */     {
/* 252:269 */       this.rotAccel -= 1;
/* 253:271 */       if (this.rotAccel > 80)
/* 254:    */       {
/* 255:273 */         this.motionX = this.tempmx;
/* 256:274 */         this.motionZ = this.tempmz;
/* 257:275 */         this.motionY = this.tempmy;
/* 258:    */       }
/* 259:    */     }
/* 260:279 */     this.rotationYaw += this.rotAccel / 2;
/* 261:281 */     if (this.rotAccel >= 100) {
/* 262:283 */       this.rotDir = false;
/* 263:    */     }
/* 264:286 */     if ((this.rotAccel == 50) && (getHealth() < 200.0F)) {
/* 265:288 */       startBubble();
/* 266:    */     }
/* 267:291 */     if (this.rotAccel == 80)
/* 268:    */     {
/* 269:294 */       Entity e = getAttackTarget();
/* 270:295 */       double px = this.posX - e.posX;
/* 271:296 */       double pz = this.posZ - e.posZ;
/* 272:297 */       double py = this.posY - (e.posY + e.height / 2.0F);
/* 273:298 */       this.tempRYaw = Math.atan2(px, pz);
/* 274:299 */       this.tempRPich = Math.atan2(py, MathHelper.sqrt_double(px * px + pz * pz));
/* 275:300 */       this.tempmy = (-Math.sin(this.tempRPich));
/* 276:301 */       this.tempmx = (-Math.sin(this.tempRYaw));
/* 277:302 */       this.tempmz = (-Math.cos(this.tempRYaw));
/* 278:    */     }
/* 279:305 */     if (this.rotAccel >= 20)
/* 280:    */     {
/* 281:307 */       List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(1.0D, 1.0D, 1.0D));
/* 282:308 */       double d = 0.0D;
/* 283:310 */       for (int j = 0; j < list.size(); j++)
/* 284:    */       {
/* 285:312 */         Entity entity1 = (Entity)list.get(j);
/* 286:314 */         if ((entity1.canBeCollidedWith()) && (!entity1.isEntityEqual(this)) && (entity1 != this.riddenByEntity))
/* 287:    */         {
/* 288:319 */           AxisAlignedBB axisalignedbb = entity1.boundingBox;
/* 289:320 */           attackEntityAsMob(entity1);
/* 290:    */         }
/* 291:    */       }
/* 292:    */     }
/* 293:324 */     if (this.rotAccel == 0) {
/* 294:326 */       setAttacking(false);
/* 295:    */     }
/* 296:    */   }
/* 297:    */   
/* 298:    */   public void startBubble()
/* 299:    */   {
/* 300:332 */     Entity e = getAttackTarget();
/* 301:333 */     double px = this.posX - e.posX;
/* 302:334 */     double pz = this.posZ - e.posZ;
/* 303:335 */     double py = this.posY - e.posY;
/* 304:336 */     this.tempRYaw = Math.atan2(px, pz);
/* 305:337 */     this.tempRPich = Math.atan2(py, MathHelper.sqrt_double(px * px + pz * pz));
/* 306:338 */     this.tempmy = (-Math.sin(this.tempRPich));
/* 307:339 */     this.tempmx = (-Math.sin(this.tempRYaw));
/* 308:340 */     this.tempmz = (-Math.cos(this.tempRYaw));
/* 309:341 */     this.bubbleAttack = true;
/* 310:    */   }
/* 311:    */   
/* 312:    */   public void bubbleRay()
/* 313:    */   {
/* 314:346 */     if (this.bubbleCD >= 20)
/* 315:    */     {
/* 316:348 */       this.bubbleCD = 0;
/* 317:349 */       this.worldObj.spawnEntityInWorld(new EntityBaseBall(this.worldObj, this, 7));
/* 318:350 */       this.bubbleAttack = false;
/* 319:351 */       this.bubbleCD = 0;
/* 320:353 */       if ((!isAttacking()) && (this.rand.nextInt(3) == 0)) {
/* 321:355 */         this.bubbleAttack = true;
/* 322:    */       }
/* 323:    */     }
/* 324:    */     else
/* 325:    */     {
/* 326:360 */       this.bubbleCD += 1;
/* 327:    */     }
/* 328:    */   }
/* 329:    */   
/* 330:    */   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
/* 331:    */   {
/* 332:368 */     boolean flag = super.attackEntityFrom(par1DamageSource, par2);
/* 333:369 */     if (this.body != null) {
/* 334:371 */       for (int i = 0; i < 5; i++) {
/* 335:373 */         if (this.body[i] != null)
/* 336:    */         {
/* 337:375 */           if (this.body[i].isDead)
/* 338:    */           {
/* 339:377 */             this.hurt = true;
/* 340:378 */             this.body[i] = null;
/* 341:    */           }
/* 342:    */         }
/* 343:    */         else {
/* 344:383 */           this.hurt = true;
/* 345:    */         }
/* 346:    */       }
/* 347:    */     }
/* 348:388 */     return flag;
/* 349:    */   }
/* 350:    */   
/* 351:    */   public boolean attackFromPart(DamageSource par1DamageSource, float par2, EntityPart part)
/* 352:    */   {
/* 353:393 */     this.hurtOnPartSoundFlag = true;
/* 354:394 */     return attackEntityFrom(par1DamageSource, par2);
/* 355:    */   }
/* 356:    */   
/* 357:    */   public int getTotalArmorValue()
/* 358:    */   {
/* 359:400 */     return 0;
/* 360:    */   }
/* 361:    */   
/* 362:    */   protected void dropFewItems(boolean flag, int i)
/* 363:    */   {
/* 364:405 */     super.dropFewItems(flag, i);
/* 365:407 */     if (!this.worldObj.isRemote)
/* 366:    */     {
/* 367:409 */       int scalesDropped = 2;
/* 368:410 */       int partsLeft = 0;
/* 369:411 */       if (this.body[0] == null)
/* 370:    */       {
/* 371:413 */         scalesDropped += 6;
/* 372:414 */         partsLeft++;
/* 373:    */       }
/* 374:416 */       for (int k = 1; k < this.body.length; k++) {
/* 375:418 */         if (this.body[k] == null)
/* 376:    */         {
/* 377:420 */           scalesDropped += 4;
/* 378:421 */           partsLeft++;
/* 379:    */         }
/* 380:    */       }
/* 381:424 */       this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(ChocolateQuest.material, scalesDropped, 1)));
/* 382:    */     }
/* 383:439 */     if ((flag) && ((this.rand.nextInt(5) == 0) || (this.rand.nextInt(1 + i) > 0))) {
/* 384:441 */       dropItem(Items.diamond, 2);
/* 385:    */     }
/* 386:    */   }
/* 387:    */   
/* 388:    */   protected boolean canDespawn()
/* 389:    */   {
/* 390:448 */     return false;
/* 391:    */   }
/* 392:    */   
/* 393:    */   public boolean isEntityEqual(Entity par1Entity)
/* 394:    */   {
/* 395:453 */     boolean flag = false;
/* 396:455 */     if (this.body != null) {
/* 397:457 */       for (int i = 0; (i < this.body.length) && (!flag); i++) {
/* 398:459 */         flag = this.body[i] == par1Entity;
/* 399:    */       }
/* 400:    */     }
/* 401:463 */     return (this == par1Entity) || (flag);
/* 402:    */   }
/* 403:    */   
/* 404:    */   public boolean canBreatheUnderwater()
/* 405:    */   {
/* 406:469 */     return true;
/* 407:    */   }
/* 408:    */   
/* 409:    */   protected boolean isValidLightLevel()
/* 410:    */   {
/* 411:474 */     int i = MathHelper.floor_double(this.posX);
/* 412:475 */     int j = MathHelper.floor_double(this.boundingBox.minY);
/* 413:476 */     int k = MathHelper.floor_double(this.posZ);
/* 414:478 */     if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) > this.rand.nextInt(32)) {
/* 415:480 */       return false;
/* 416:    */     }
/* 417:484 */     int l = this.worldObj.getBlockLightValue(i, j, k);
/* 418:486 */     if (this.worldObj.isThundering())
/* 419:    */     {
/* 420:488 */       int i1 = this.worldObj.skylightSubtracted;
/* 421:489 */       this.worldObj.skylightSubtracted = 10;
/* 422:490 */       l = this.worldObj.getBlockLightValue(i, j, k);
/* 423:491 */       this.worldObj.skylightSubtracted = i1;
/* 424:    */     }
/* 425:494 */     return l <= this.rand.nextInt(8);
/* 426:    */   }
/* 427:    */   
/* 428:    */   protected String getHurtSound()
/* 429:    */   {
/* 430:501 */     if (this.hurtOnPartSoundFlag)
/* 431:    */     {
/* 432:503 */       this.hurtOnPartSoundFlag = false;
/* 433:504 */       return super.getHurtSound();
/* 434:    */     }
/* 435:506 */     return "mob.blaze.hit";
/* 436:    */   }
/* 437:    */   
/* 438:    */   protected void entityInit()
/* 439:    */   {
/* 440:512 */     super.entityInit();
/* 441:513 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/* 442:    */   }
/* 443:    */   
/* 444:    */   public boolean isHealing()
/* 445:    */   {
/* 446:518 */     return this.dataWatcher.getWatchableObjectByte(16) == 0;
/* 447:    */   }
/* 448:    */   
/* 449:    */   public void setHealing(boolean healing)
/* 450:    */   {
/* 451:523 */     this.dataWatcher.updateObject(16, healing ? Byte.valueOf((byte)0) : Byte.valueOf((byte)1));
/* 452:    */   }
/* 453:    */   
/* 454:    */   public void setPart(EntityPart entityTurtlePart, int partID)
/* 455:    */   {
/* 456:529 */     this.body[partID] = ((EntityTurtlePart)entityTurtlePart);
/* 457:530 */     scalePart(entityTurtlePart);
/* 458:    */   }
/* 459:    */   
/* 460:    */   protected void scalePart(EntityPart part)
/* 461:    */   {
/* 462:534 */     float scale = this.size * 0.3F;
/* 463:535 */     part.setSize(scale, scale);
/* 464:536 */     part.setPosition(this.posX, this.posY, this.posZ);
/* 465:    */   }
/* 466:    */   
/* 467:    */   public boolean canBePushed()
/* 468:    */   {
/* 469:542 */     return false;
/* 470:    */   }
/* 471:    */   
/* 472:    */   public boolean shouldMoveToEntity(double d1, Entity target)
/* 473:    */   {
/* 474:547 */     return !isAttacking();
/* 475:    */   }
/* 476:    */   
/* 477:    */   public EntityTurtlePart[] getBossParts()
/* 478:    */   {
/* 479:551 */     return this.body;
/* 480:    */   }
/* 481:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.EntityTurtle
 * JD-Core Version:    0.7.1
 */