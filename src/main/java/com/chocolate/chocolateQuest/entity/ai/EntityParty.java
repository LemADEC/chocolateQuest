/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   5:    */ import com.chocolate.chocolateQuest.items.swords.ItemBDSword;
/*   6:    */ import net.minecraft.entity.Entity;
/*   7:    */ import net.minecraft.entity.EntityList;
/*   8:    */ import net.minecraft.entity.EntityLivingBase;
/*   9:    */ import net.minecraft.item.Item;
/*  10:    */ import net.minecraft.item.ItemArmor;
/*  11:    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*  12:    */ import net.minecraft.item.ItemStack;
/*  13:    */ import net.minecraft.item.ItemSword;
/*  14:    */ import net.minecraft.nbt.NBTTagCompound;
/*  15:    */ import net.minecraft.nbt.NBTTagList;
/*  16:    */ import net.minecraft.util.MathHelper;
/*  17:    */ import net.minecraft.world.World;
/*  18:    */ 
/*  19:    */ public class EntityParty
/*  20:    */ {
/*  21:    */   public static final int FRONT = 0;
/*  22:    */   public static final int RIGHT = 90;
/*  23:    */   public static final int BACK = 180;
/*  24:    */   public static final int LEFT = -90;
/*  25:    */   public static final int FRONT_RIGHT = 45;
/*  26:    */   public static final int FRONT_LEFT = -45;
/*  27:    */   public static final int BACK_RIGHT = 135;
/*  28:    */   public static final int BACK_LEFT = -135;
/*  29: 36 */   final int distanceToCaptain = 2;
/*  30: 38 */   EntityHumanBase[] members = new EntityHumanBase[8];
/*  31:    */   EntityHumanBase leader;
/*  32: 40 */   EntityLivingBase[] potentialTargets = new EntityLivingBase[4];
/*  33: 41 */   int[] targetsAggro = new int[4];
/*  34: 43 */   NBTTagCompound partyTag = null;
/*  35:    */   
/*  36:    */   public void update()
/*  37:    */   {
/*  38: 46 */     if (this.partyTag != null)
/*  39:    */     {
/*  40: 47 */       loadFromNBT(this.partyTag);
/*  41: 48 */       this.partyTag = null;
/*  42:    */     }
/*  43: 51 */     for (int i = 0; i < getMembersLength(); i++)
/*  44:    */     {
/*  45: 52 */       EntityHumanBase e = getMember(i);
/*  46: 53 */       if ((e != null) && 
/*  47: 54 */         (e.isDead)) {
/*  48: 55 */         removeMember(e);
/*  49:    */       }
/*  50:    */     }
/*  51: 59 */     for (int i = 0; i < this.targetsAggro.length; i++) {
/*  52: 60 */       if (this.potentialTargets[i] != null)
/*  53:    */       {
/*  54: 62 */         if (this.targetsAggro[i] > 1) {
/*  55: 63 */           this.targetsAggro[i] -= 1;
/*  56:    */         }
/*  57: 64 */         if (!this.potentialTargets[i].isEntityAlive())
/*  58:    */         {
/*  59: 65 */           setAggro(null, 0, i);
/*  60: 66 */           sortTargets();
/*  61:    */         }
/*  62:    */       }
/*  63:    */     }
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void addAggroToTarget(EntityLivingBase e, int aggro)
/*  67:    */   {

int i = 0;
/*  68: 75 */     for (i = 0; i < this.potentialTargets.length; i++) {
/*  69: 76 */       if (this.potentialTargets[i] == e)
/*  70:    */       {
/*  71: 77 */         this.targetsAggro[i] += aggro;
/*  72: 78 */         sortTargets();
/*  73: 79 */         return;
/*  74:    */       }
/*  75:    */     }
/*  76: 82 */     for (i = 0; i < this.potentialTargets.length; i++) {
/*  77: 83 */       if (this.potentialTargets[i] == null)
/*  78:    */       {
/*  79: 84 */         setAggro(e, aggro, i);
/*  80: 85 */         sortTargets();
/*  81: 86 */         return;
/*  82:    */       }
/*  83:    */     }
/*  84: 89 */     for (i = this.potentialTargets.length - 1; i >= 0; i--) {
/*  85: 90 */       if (this.potentialTargets[i] != null)
/*  86:    */       {
/*  87: 91 */         if (aggro < this.targetsAggro[i])
/*  88:    */         {
/*  89: 92 */           setAggro(e, aggro, i);
/*  90: 93 */           sortTargets();
/*  91:    */         }
/*  92: 95 */         return;
/*  93:    */       }
/*  94:    */     }
/*  95: 98 */     sortTargets();
/*  96:    */   }
/*  97:    */   
/*  98:    */   public void setAggro(EntityLivingBase e, int aggro, int i)
/*  99:    */   {
/* 100:103 */     this.potentialTargets[i] = e;
/* 101:104 */     this.targetsAggro[i] = aggro;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public void sortTargets()
/* 105:    */   {
/* 106:109 */     double[] targetsAggro = new double[this.potentialTargets.length];
/* 107:110 */     for (int i = 0; i < this.potentialTargets.length; i++) {
/* 108:111 */       if (this.potentialTargets[i] != null) {
/* 109:112 */         targetsAggro[i] = getLeader().getDistanceSqToEntity(this.potentialTargets[i]);
/* 110:    */       }
/* 111:    */     }
/* 112:114 */     for (int i = 0; i < this.potentialTargets.length; i++)
/* 113:    */     {
/* 114:115 */       double max = 0.0D;
/* 115:116 */       int index = i;
/* 116:117 */       for (int c = i; c < this.potentialTargets.length; c++) {
/* 117:118 */         if ((targetsAggro[c] > max) && (this.potentialTargets[c] != null))
/* 118:    */         {
/* 119:119 */           max = targetsAggro[c];
/* 120:120 */           index = c;
/* 121:    */         }
/* 122:    */       }
/* 123:123 */       EntityLivingBase currentEntity = this.potentialTargets[index];
/* 124:124 */       double currentAggro = targetsAggro[index];
/* 125:    */       
/* 126:126 */       this.potentialTargets[index] = this.potentialTargets[i];
/* 127:127 */       targetsAggro[index] = targetsAggro[i];
/* 128:    */       
/* 129:129 */       this.potentialTargets[i] = currentEntity;
/* 130:130 */       targetsAggro[i] = currentAggro;
/* 131:    */     }
/* 132:    */   }
/* 133:    */   
/* 134:    */   public EntityLivingBase getTarget()
/* 135:    */   {
/* 136:161 */     if ((this.potentialTargets[0] == null) || (!this.potentialTargets[0].isEntityAlive())) {
/* 137:162 */       sortTargets();
/* 138:    */     }
/* 139:164 */     if (this.potentialTargets[0] == null) {
/* 140:165 */       return this.leader.getAttackTarget();
/* 141:    */     }
/* 142:166 */     return this.potentialTargets[0];
/* 143:    */   }
/* 144:    */   
/* 145:    */   public void removeMember(EntityHumanBase entity)
/* 146:    */   {
/* 147:171 */     entity.party = null;
/* 148:172 */     if (entity == getLeader())
/* 149:    */     {
/* 150:174 */       this.leader = null;
/* 151:175 */       entity.setCaptain(false);
/* 152:176 */       int newPos = getNewLeaderFromParty();
/* 153:177 */       EntityHumanBase e = getMember(newPos);
/* 154:178 */       if (e != null)
/* 155:    */       {
/* 156:180 */         this.members[newPos] = null;
/* 157:181 */         setLeader(e);
/* 158:    */       }
/* 159:    */     }
/* 160:    */     else
/* 161:    */     {
/* 162:186 */       for (int i = 0; i < getMembersLength(); i++) {
/* 163:187 */         if (entity.isEntityEqual(getMember(i)))
/* 164:    */         {
/* 165:189 */           removeMember(i);
/* 166:190 */           break;
/* 167:    */         }
/* 168:    */       }
/* 169:    */     }
/* 170:194 */     if (!hasMembers())
/* 171:    */     {
/* 172:195 */       EntityHumanBase leader = getLeader();
/* 173:196 */       if (leader != null)
/* 174:    */       {
/* 175:197 */         leader.setOutOfParty();
/* 176:198 */         leader.setCaptain(false);
/* 177:    */       }
/* 178:    */     }
/* 179:    */   }
/* 180:    */   
/* 181:    */   public void cleanParty()
/* 182:    */   {
/* 183:204 */     if (!this.leader.isEntityAlive())
/* 184:    */     {
/* 185:205 */       this.leader = null;
/* 186:206 */       EntityHumanBase newLeader = getMember(getNewLeaderFromParty());
/* 187:207 */       if (newLeader != null) {
/* 188:209 */         setLeader(newLeader);
/* 189:    */       }
/* 190:    */     }
/* 191:    */   }
/* 192:    */   
/* 193:    */   protected int getNewLeaderFromParty()
/* 194:    */   {
/* 195:216 */     int bestEntityIndex = 0;
/* 196:217 */     int maxLevel = -9999;
/* 197:218 */     for (int i = 0; i < getMembersLength(); i++) {
/* 198:219 */       if (getMember(i) != null)
/* 199:    */       {
/* 200:221 */         int currentLevel = getEntityLevel(getMember(i));
/* 201:222 */         if (currentLevel > maxLevel)
/* 202:    */         {
/* 203:223 */           maxLevel = currentLevel;
/* 204:224 */           bestEntityIndex = i;
/* 205:    */         }
/* 206:    */       }
/* 207:    */     }
/* 208:228 */     return bestEntityIndex;
/* 209:    */   }
/* 210:    */   
/* 211:    */   public boolean tryToAddNewMember(EntityHumanBase newMember)
/* 212:    */   {
/* 213:233 */     return tryToAddNewMember(newMember, false);
/* 214:    */   }
/* 215:    */   
/* 216:    */   public boolean tryToAddNewMember(EntityHumanBase newMember, boolean replaceLeader)
/* 217:    */   {
/* 218:237 */     if ((!replaceLeader) && (isFull())) {
/* 219:238 */       return false;
/* 220:    */     }
/* 221:239 */     if (getLeader() == null)
/* 222:    */     {
/* 223:240 */       setLeader(newMember);
/* 224:241 */       return true;
/* 225:    */     }
/* 226:243 */     if (newMember == getLeader()) {
/* 227:244 */       return false;
/* 228:    */     }
/* 229:245 */     for (int i = 0; i < getMembersLength(); i++) {
/* 230:247 */       if (newMember.isEntityEqual(getMember(i))) {
/* 231:248 */         return false;
/* 232:    */       }
/* 233:    */     }
/* 234:250 */     if (newMember.party != null) {
/* 235:251 */       newMember.party.removeMember(newMember);
/* 236:    */     }
/* 237:253 */     int newLevel = getEntityLevel(newMember);
/* 238:254 */     int leaderLevel = getEntityLevel(this.leader);
/* 239:255 */     if ((replaceLeader) && (newLevel > leaderLevel))
/* 240:    */     {
/* 241:256 */       setLeader(newMember);
/* 242:257 */       return true;
/* 243:    */     }
/* 244:260 */     int skipThisOne = getSuggestedPosition(newMember);
/* 245:261 */     if (tryAddToSlot(skipThisOne, newMember, newLevel)) {
/* 246:262 */       return true;
/* 247:    */     }
/* 248:263 */     for (int i = 0; i < getMembersLength(); i++) {
/* 249:265 */       if (i != skipThisOne) {
/* 250:267 */         if (tryAddToSlot(i, newMember, newLevel)) {
/* 251:268 */           return true;
/* 252:    */         }
/* 253:    */       }
/* 254:    */     }
/* 255:271 */     return false;
/* 256:    */   }
/* 257:    */   
/* 258:    */   private boolean isFull()
/* 259:    */   {
/* 260:275 */     for (int i = 0; i < getMembersLength(); i++) {
/* 261:276 */       if (getMember(i) == null) {
/* 262:277 */         return false;
/* 263:    */       }
/* 264:    */     }
/* 265:279 */     return true;
/* 266:    */   }
/* 267:    */   
/* 268:    */   private boolean hasMembers()
/* 269:    */   {
/* 270:283 */     for (int i = 0; i < getMembersLength(); i++) {
/* 271:284 */       if (getMember(i) != null) {
/* 272:285 */         return true;
/* 273:    */       }
/* 274:    */     }
/* 275:287 */     return false;
/* 276:    */   }
/* 277:    */   
/* 278:    */   protected int getSuggestedPosition(EntityHumanBase e)
/* 279:    */   {
/* 280:291 */     if ((e.isHealer()) || (e.isRanged()))
/* 281:    */     {
/* 282:293 */       if (getMember(6) == null) {
/* 283:294 */         return 6;
/* 284:    */       }
/* 285:295 */       if (getMember(5) == null) {
/* 286:296 */         return 5;
/* 287:    */       }
/* 288:297 */       if (getMember(7) == null) {
/* 289:298 */         return 7;
/* 290:    */       }
/* 291:299 */       if (getMember(3) == null) {
/* 292:300 */         return 3;
/* 293:    */       }
/* 294:301 */       if (getMember(4) == null) {
/* 295:302 */         return 4;
/* 296:    */       }
/* 297:303 */       return 6;
/* 298:    */     }
/* 299:305 */     if (getMember(1) == null) {
/* 300:306 */       return 1;
/* 301:    */     }
/* 302:307 */     if (getMember(3) == null) {
/* 303:308 */       return 3;
/* 304:    */     }
/* 305:309 */     if (getMember(4) == null) {
/* 306:310 */       return 4;
/* 307:    */     }
/* 308:311 */     if (getMember(0) == null) {
/* 309:312 */       return 0;
/* 310:    */     }
/* 311:313 */     if (getMember(2) == null) {
/* 312:314 */       return 2;
/* 313:    */     }
/* 314:315 */     if (getMember(6) == null) {
/* 315:316 */       return 6;
/* 316:    */     }
/* 317:317 */     if (getMember(5) == null) {
/* 318:318 */       return 5;
/* 319:    */     }
/* 320:319 */     if (getMember(7) == null) {
/* 321:320 */       return 7;
/* 322:    */     }
/* 323:321 */     return 7;
/* 324:    */   }
/* 325:    */   
/* 326:    */   public int getAngleForSlot(int i)
/* 327:    */   {
/* 328:326 */     switch (i)
/* 329:    */     {
/* 330:    */     case 0: 
/* 331:328 */       return -45;
/* 332:    */     case 1: 
/* 333:330 */       return 0;
/* 334:    */     case 2: 
/* 335:332 */       return 45;
/* 336:    */     case 3: 
/* 337:334 */       return -90;
/* 338:    */     case 4: 
/* 339:336 */       return 90;
/* 340:    */     case 5: 
/* 341:338 */       return 135;
/* 342:    */     case 6: 
/* 343:340 */       return 180;
/* 344:    */     case 7: 
/* 345:342 */       return -135;
/* 346:    */     }
/* 347:345 */     return 0;
/* 348:    */   }
/* 349:    */   
/* 350:    */   public int getMembersLength()
/* 351:    */   {
/* 352:351 */     return this.members.length;
/* 353:    */   }
/* 354:    */   
/* 355:    */   protected void setMember(int index, EntityHumanBase newMember)
/* 356:    */   {
/* 357:355 */     newMember.setInParty(this, getAngleForSlot(index), 2);
/* 358:356 */     this.members[index] = newMember;
/* 359:    */   }
/* 360:    */   
/* 361:    */   public EntityHumanBase getMember(int i)
/* 362:    */   {
/* 363:360 */     return this.members[i];
/* 364:    */   }
/* 365:    */   
/* 366:    */   protected void removeMember(int i)
/* 367:    */   {
/* 368:364 */     this.members[i].setOutOfParty();
/* 369:365 */     this.members[i] = null;
/* 370:    */   }
/* 371:    */   
/* 372:    */   protected void setLeader(EntityHumanBase newLeader)
/* 373:    */   {
/* 374:370 */     EntityHumanBase oldLeader = this.leader;
/* 375:371 */     this.leader = newLeader;
/* 376:372 */     this.leader.setInParty(this, 0, 2);
/* 377:373 */     newLeader.setCaptain(true);
/* 378:374 */     if (oldLeader != null)
/* 379:    */     {
/* 380:376 */       oldLeader.setCaptain(false);
/* 381:377 */       oldLeader.setOutOfParty();
/* 382:378 */       tryToAddNewMember(oldLeader);
/* 383:    */     }
/* 384:    */   }
/* 385:    */   
/* 386:    */   public EntityHumanBase getLeader()
/* 387:    */   {
/* 388:383 */     if (this.leader == null) {
/* 389:384 */       this.leader = getMember(getNewLeaderFromParty());
/* 390:    */     }
/* 391:386 */     return this.leader;
/* 392:    */   }
/* 393:    */   
/* 394:    */   protected boolean tryAddToSlot(int index, EntityHumanBase newMember, int newLevel)
/* 395:    */   {
/* 396:390 */     EntityHumanBase current = getMember(index);
/* 397:391 */     if (current != null)
/* 398:    */     {
/* 399:393 */       int memberLevel = getEntityLevel(current);
/* 400:394 */       if (newLevel > memberLevel)
/* 401:    */       {
/* 402:395 */         current.setOutOfParty();
/* 403:396 */         setMember(index, newMember);
/* 404:397 */         tryToAddNewMember(current);
/* 405:398 */         return true;
/* 406:    */       }
/* 407:    */     }
/* 408:    */     else
/* 409:    */     {
/* 410:402 */       setMember(index, newMember);
/* 411:403 */       return true;
/* 412:    */     }
/* 413:405 */     return false;
/* 414:    */   }
/* 415:    */   
/* 416:    */   public int getEntityLevel(EntityHumanBase entity)
/* 417:    */   {
/* 418:409 */     int value = getItemValue(entity.leftHandItem);
/* 419:410 */     for (int i = 0; i < 5; i++) {
/* 420:411 */       value += getItemValue(entity.getEquipmentInSlot(i));
/* 421:    */     }
/* 422:412 */     value += entity.getLeadershipValue();
/* 423:413 */     return value;
/* 424:    */   }
/* 425:    */   
/* 426:    */   public static int getItemValue(ItemStack itemstack)
/* 427:    */   {
/* 428:418 */     if (itemstack == null) {
/* 429:418 */       return 0;
/* 430:    */     }
/* 431:419 */     Item item = itemstack.getItem();
/* 432:420 */     if (item == ChocolateQuest.staffHeal) {
/* 433:421 */       return -20;
/* 434:    */     }
/* 435:422 */     if ((item instanceof ItemArmor)) {
/* 436:424 */       return ((ItemArmor)item).getArmorMaterial().getDamageReductionAmount(2);
/* 437:    */     }
/* 438:426 */     if ((item instanceof ItemBDSword)) {
/* 439:428 */       return 4;
/* 440:    */     }
/* 441:430 */     if ((item instanceof ItemSword)) {
/* 442:432 */       return 3;
/* 443:    */     }
/* 444:434 */     if (item == ChocolateQuest.banner) {
/* 445:435 */       return 10;
/* 446:    */     }
/* 447:436 */     return 0;
/* 448:    */   }
/* 449:    */   
/* 450:    */   public String toString()
/* 451:    */   {
/* 452:440 */     String name = "Leader: ";
/* 453:441 */     if (this.leader != null) {
/* 454:442 */       name = name + this.leader.getEntityId();
/* 455:    */     } else {
/* 456:443 */       name = name + "null";
/* 457:    */     }
/* 458:444 */     for (int i = 0; i < getMembersLength(); i++)
/* 459:    */     {
/* 460:445 */       name = name + ", " + i + ": ";
/* 461:446 */       if (getMember(i) != null) {
/* 462:447 */         name = name + getMember(i).getEntityId();
/* 463:    */       } else {
/* 464:448 */         name = name + "null";
/* 465:    */       }
/* 466:    */     }
/* 467:450 */     return name;
/* 468:    */   }
/* 469:    */   
/* 470:    */   public void saveToNBT(NBTTagCompound par1nbtTagCompound)
/* 471:    */   {
/* 472:454 */     int x = MathHelper.floor_double(getLeader().posX);
/* 473:455 */     int y = MathHelper.floor_double(getLeader().posY);
/* 474:456 */     int z = MathHelper.floor_double(getLeader().posZ);
/* 475:457 */     NBTTagList list = new NBTTagList();
/* 476:458 */     for (int i = 0; i < getMembersLength(); i++)
/* 477:    */     {
/* 478:459 */       EntityHumanBase e = getMember(i);
/* 479:460 */       if ((e != null) && 
/* 480:461 */         (!e.isDead))
/* 481:    */       {
/* 482:462 */         NBTTagCompound eTag = new NBTTagCompound();
/* 483:463 */         eTag.setString("id", EntityList.getEntityString(e));
/* 484:464 */         e.writeEntityToSpawnerNBT(eTag, x, y, z);
/* 485:    */         
/* 486:    */ 
/* 487:    */ 
/* 488:    */ 
/* 489:    */ 
/* 490:    */ 
/* 491:471 */         list.appendTag(eTag);
/* 492:    */       }
/* 493:    */     }
/* 494:475 */     par1nbtTagCompound.setTag("Party", list);
/* 495:    */   }
/* 496:    */   
/* 497:    */   public void readFromNBT(NBTTagCompound nbttagcompound)
/* 498:    */   {
/* 499:479 */     this.partyTag = nbttagcompound;
/* 500:    */   }
/* 501:    */   
/* 502:    */   private void loadFromNBT(NBTTagCompound nbttagcompound)
/* 503:    */   {
/* 504:482 */     int x = MathHelper.floor_double(getLeader().posX);
/* 505:483 */     int y = MathHelper.floor_double(getLeader().posY);
/* 506:484 */     int z = MathHelper.floor_double(getLeader().posZ);
/* 507:485 */     NBTTagList list = nbttagcompound.getTagList("Party", nbttagcompound.getId());
/* 508:486 */     World world = getLeader().worldObj;
/* 509:487 */     for (int i = 0; i < list.tagCount(); i++)
/* 510:    */     {
/* 511:488 */       NBTTagCompound eTag = list.getCompoundTagAt(i);
/* 512:489 */       Entity e = EntityList.createEntityFromNBT(eTag, world);
/* 513:490 */       if ((e instanceof EntityHumanBase))
/* 514:    */       {
/* 515:491 */         EntityHumanBase human = (EntityHumanBase)EntityList.createEntityFromNBT(eTag, world);
/* 516:492 */         human.readEntityFromSpawnerNBT(eTag, x, y, z);
/* 517:493 */         human.setPosition(human.posX, human.posY, human.posZ);
/* 518:494 */         getLeader().tryPutIntoPArty(human);
/* 519:495 */         world.spawnEntityInWorld(human);
/* 520:497 */         if (eTag.getTag("Riding") != null)
/* 521:    */         {
/* 522:498 */           NBTTagCompound ridingNBT = (NBTTagCompound)eTag.getTag("Riding");
/* 523:499 */           Entity riding = EntityList.createEntityFromNBT(ridingNBT, world);
/* 524:500 */           if (riding != null)
/* 525:    */           {
/* 526:501 */             if ((riding instanceof EntityHumanBase)) {
/* 527:502 */               ((EntityHumanBase)riding).entityTeam = human.entityTeam;
/* 528:    */             }
/* 529:503 */             riding.setPosition(human.posX, human.posY, human.posZ);
/* 530:504 */             world.spawnEntityInWorld(riding);
/* 531:505 */             human.mountEntity(riding);
/* 532:    */           }
/* 533:    */         }
/* 534:    */       }
/* 535:    */     }
/* 536:    */   }
/* 537:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.EntityParty

 * JD-Core Version:    0.7.1

 */
