/*   1:    */ package com.chocolate.chocolateQuest.builder.decorator;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.BuilderBlockData;
/*   4:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   5:    */ import com.chocolate.chocolateQuest.block.BlockAltarTileEntity;
/*   6:    */ import com.chocolate.chocolateQuest.block.BlockArmorStandTileEntity;
/*   7:    */ import com.chocolate.chocolateQuest.block.BlockMobSpawnerTileEntity;
/*   8:    */ import com.chocolate.chocolateQuest.builder.BuilderHelper;
/*   9:    */ import com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase;
/*  10:    */ import com.chocolate.chocolateQuest.entity.mob.registry.RegisterDungeonMobs;
/*  11:    */ import com.chocolate.chocolateQuest.misc.EquipementHelper;
/*  12:    */ import java.util.ArrayList;
/*  13:    */ import java.util.Random;
/*  14:    */ import net.minecraft.block.Block;
/*  15:    */ import net.minecraft.entity.item.EntityItemFrame;
/*  16:    */ import net.minecraft.entity.item.EntityPainting;
/*  17:    */ import net.minecraft.init.Blocks;
/*  18:    */ import net.minecraft.init.Items;
/*  19:    */ import net.minecraft.item.ItemStack;
/*  20:    */ import net.minecraft.tileentity.TileEntity;
/*  21:    */ import net.minecraft.tileentity.TileEntityFurnace;
/*  22:    */ import net.minecraft.world.World;
/*  23:    */ import net.minecraft.world.storage.MapData;
/*  24:    */ import net.minecraftforge.common.util.ForgeDirection;
/*  25:    */ 
/*  26:    */ public class RoomBase
/*  27:    */ {
/*  28: 39 */   public boolean wallSouth = true;
/*  29: 39 */   public boolean wallEast = true;
/*  30: 39 */   public boolean wallWest = true;
/*  31: 39 */   public boolean wallNorth = true;
/*  32: 44 */   public boolean doorEast = false;
/*  33: 44 */   public boolean doorWest = false;
/*  34: 44 */   public boolean doorNorth = false;
/*  35: 44 */   public boolean doorSouth = false;
/*  36: 49 */   public boolean decorateNorth = true;
/*  37: 49 */   public boolean decorateSouth = true;
/*  38: 49 */   public boolean decorateEast = true;
/*  39: 49 */   public boolean decorateWest = true;
/*  40: 55 */   public boolean doorSet = false;
/*  41: 57 */   public int stairsHeight = 0;
/*  42:    */   public static final int CENTER = 5;
/*  43:    */   public static final int BEDROOM = 0;
/*  44:    */   public static final int KITCHEN = 1;
/*  45:    */   public static final int LIBRARY = 2;
/*  46:    */   public static final int MONSTER_ROOM = 3;
/*  47:    */   public static final int OTHER_MONSTER_ROOM = 4;
/*  48:    */   public static final int BIG_LIBRARY = 200;
/*  49:    */   public static final int ALCHEMY = 201;
/*  50:    */   public static final int BLACKSMITH = 202;
/*  51:    */   public static final int ENCHANTMENT_ROOM = 203;
/*  52:    */   public static final int FLAG_ROOM = 204;
/*  53:    */   public static final int MAGIC_ROOM = 205;
/*  54:    */   public static final int STORAGE = 206;
/*  55:    */   public static final int PORTAL = 208;
/*  56:    */   public static final int STAIRS = 300;
/*  57:    */   public static final int LADDERS = 301;
/*  58:    */   public static final int FULL_MONSTER_ROOM = 302;
/*  59:    */   public static final int BOSS_ROOM = 303;
/*  60:    */   public static final int ARCHERS = 304;
/*  61:    */   public static final int DINNING_ROOM = 400;
/*  62:    */   public static final int JAIL = 401;
/*  63:    */   public static final int SPIDER_NEST = 402;
/*  64:    */   public static final int TREASURE = 403;
/*  65:    */   public static final int GARDEN = 500;
/*  66:    */   public int sizeX;
/*  67:    */   public int sizeZ;
/*  68:    */   public BuildingProperties properties;
/*  69:    */   
/*  70:    */   public RoomBase() {}
/*  71:    */   
/*  72:    */   public RoomBase(int sizeX, int sizeZ, int height, int roomType, BuildingProperties data)
/*  73:    */   {
/*  74: 67 */     configure(sizeX, sizeZ, data);
/*  75:    */   }
/*  76:    */   
/*  77:    */   public RoomBase copyDataFrom(RoomBase room)
/*  78:    */   {
/*  79: 72 */     configure(room.sizeX, room.sizeZ, room.properties);
/*  80: 73 */     this.wallNorth = room.wallNorth;
/*  81: 74 */     this.wallSouth = room.wallSouth;
/*  82: 75 */     this.wallEast = room.wallEast;
/*  83: 76 */     this.wallWest = room.wallWest;
/*  84:    */     
/*  85: 78 */     this.doorNorth = room.doorNorth;
/*  86: 79 */     this.doorSouth = room.doorSouth;
/*  87: 80 */     this.doorEast = room.doorEast;
/*  88: 81 */     this.doorWest = room.doorWest;
/*  89:    */     
/*  90: 83 */     this.decorateNorth = room.decorateNorth;
/*  91: 84 */     this.decorateSouth = room.decorateSouth;
/*  92: 85 */     this.decorateEast = room.decorateEast;
/*  93: 86 */     this.decorateWest = room.decorateWest;
/*  94: 87 */     return this;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void configure(int sizeX, int sizeZ, BuildingProperties data)
/*  98:    */   {
/*  99: 92 */     this.sizeX = sizeX;
/* 100: 93 */     this.sizeZ = sizeZ;
/* 101: 94 */     this.properties = data;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public int getType()
/* 105:    */   {
/* 106: 98 */     return -1;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public RoomBase clearWalls()
/* 110:    */   {
/* 111:102 */     this.wallEast = false;
/* 112:103 */     this.decorateEast = false;
/* 113:104 */     this.wallNorth = false;
/* 114:105 */     this.decorateNorth = false;
/* 115:106 */     this.wallSouth = false;
/* 116:107 */     this.decorateSouth = false;
/* 117:108 */     this.wallWest = false;
/* 118:109 */     this.decorateWest = false;
/* 119:110 */     return this;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public void decorate(Random random, World world, int posX, int posY, int posZ)
/* 123:    */   {
/* 124:115 */     int roomCenterX = posX + this.sizeX / 2;
/* 125:116 */     int roomCenterZ = posZ + this.sizeZ / 2;
/* 126:117 */     int floorHeight = this.properties.floorHeight;
/* 127:119 */     for (int roomPos = 0; roomPos <= this.sizeZ; roomPos++)
/* 128:    */     {
/* 129:121 */       if (this.wallEast) {
/* 130:122 */         for (int h = 0; h < this.properties.floorHeight; h++) {
/* 131:123 */           world.setBlock(posX, posY + h, posZ + roomPos, this.properties.wallBlock.id, this.properties.wallBlock.metadata, 3);
/* 132:    */         }
/* 133:    */       }
/* 134:124 */       if (this.wallWest) {
/* 135:125 */         for (int h = 0; h < this.properties.floorHeight; h++) {
/* 136:126 */           world.setBlock(posX + this.sizeX, posY + h, posZ + roomPos, this.properties.wallBlock.id, this.properties.wallBlock.metadata, 3);
/* 137:    */         }
/* 138:    */       }
/* 139:    */     }
/* 140:128 */     for (int roomPos = 0; roomPos <= this.sizeX; roomPos++)
/* 141:    */     {
/* 142:130 */       if (this.wallNorth) {
/* 143:131 */         for (int h = 0; h < this.properties.floorHeight; h++) {
/* 144:132 */           world.setBlock(posX + roomPos, posY + h, posZ, this.properties.wallBlock.id, this.properties.wallBlock.metadata, 3);
/* 145:    */         }
/* 146:    */       }
/* 147:133 */       if (this.wallSouth) {
/* 148:134 */         for (int h = 0; h < this.properties.floorHeight; h++) {
/* 149:135 */           world.setBlock(posX + roomPos, posY + h, posZ + this.sizeZ, this.properties.wallBlock.id, this.properties.wallBlock.metadata, 3);
/* 150:    */         }
/* 151:    */       }
/* 152:    */     }
/* 153:138 */     addRoomDecoration(random, world, posX, posY, posZ);
/* 154:    */     
/* 155:140 */     world.setBlock(roomCenterX, posY + floorHeight - 1, roomCenterZ, Blocks.redstone_lamp);
/* 156:141 */     world.setBlock(roomCenterX, posY + floorHeight - 2, roomCenterZ, Blocks.lever, 0, 3);
/* 157:    */     
/* 158:143 */     decorateDoors(random, world, posX, posY, posZ);
/* 159:145 */     for (int i = 0; i < this.properties.mobRatio; i++)
/* 160:    */     {
/* 161:146 */       int x = posX + random.nextInt(this.sizeX);
/* 162:147 */       int y = posY;
/* 163:148 */       int z = posZ + random.nextInt(this.sizeZ);
/* 164:149 */       if (world.isAirBlock(x, y, z)) {
/* 165:150 */         addMob(random, world, x, y, z);
/* 166:    */       }
/* 167:    */     }
/* 168:    */   }
/* 169:    */   
/* 170:    */   public void addRoomDecoration(Random random, World world, int posX, int posY, int posZ)
/* 171:    */   {
/* 172:161 */     int roomCenterX = posX + this.sizeX / 2;
/* 173:162 */     int roomCenterZ = posZ + this.sizeZ / 2;
/* 174:163 */     for (int roomPos = 1; roomPos < this.sizeZ; roomPos++)
/* 175:    */     {
/* 176:165 */       if (this.decorateEast) {
/* 177:166 */         placeDecorationBlock(random, world, posX + 1, posY, posZ + roomPos, 1);
/* 178:    */       }
/* 179:167 */       if (this.decorateWest) {
/* 180:168 */         placeDecorationBlock(random, world, posX + this.sizeX - 1, posY, posZ + roomPos, 2);
/* 181:    */       }
/* 182:    */     }
/* 183:170 */     for (int roomPos = 1; roomPos < this.sizeX; roomPos++)
/* 184:    */     {
/* 185:172 */       if (this.decorateNorth) {
/* 186:173 */         placeDecorationBlock(random, world, posX + roomPos, posY, posZ + 1, 3);
/* 187:    */       }
/* 188:174 */       if (this.decorateSouth) {
/* 189:175 */         placeDecorationBlock(random, world, posX + roomPos, posY, posZ + this.sizeZ - 1, 4);
/* 190:    */       }
/* 191:    */     }
/* 192:178 */     placeDecorationBlock(random, world, roomCenterX, posY, roomCenterZ, 5);
/* 193:    */   }
/* 194:    */   
/* 195:    */   public void decorateDoors(Random random, World world, int posX, int posY, int posZ)
/* 196:    */   {
/* 197:185 */     if ((this.wallWest) && (this.doorWest)) {
/* 198:186 */       this.properties.doors.generate(random, world, posX + this.sizeX, posY, posZ + this.sizeZ / 2, ForgeDirection.WEST);
/* 199:    */     }
/* 200:187 */     if ((this.wallSouth) && (this.doorSouth)) {
/* 201:188 */       this.properties.doors.generate(random, world, posX + this.sizeX / 2, posY, posZ + this.sizeZ, ForgeDirection.SOUTH);
/* 202:    */     }
/* 203:189 */     if ((this.wallEast) && (this.doorEast)) {
/* 204:190 */       this.properties.doors.generate(random, world, posX, posY, posZ + this.sizeZ / 2, ForgeDirection.EAST);
/* 205:    */     }
/* 206:191 */     if ((this.wallNorth) && (this.doorNorth)) {
/* 207:192 */       this.properties.doors.generate(random, world, posX + this.sizeX / 2, posY, posZ, ForgeDirection.NORTH);
/* 208:    */     }
/* 209:    */   }
/* 210:    */   
/* 211:    */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 212:    */   {
/* 213:198 */     if (random.nextInt(100) == 0) {
/* 214:200 */       placePainting(random, world, x, y + 1, z, side);
/* 215:    */     }
/* 216:202 */     if (side != 5) {
/* 217:204 */       placeRandomDecorationBlock(random, world, x, y, z, side);
/* 218:    */     }
/* 219:    */   }
/* 220:    */   
/* 221:    */   public void placeRandomDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 222:    */   {
/* 223:208 */     if (random.nextInt(10) == 0) {
/* 224:210 */       if (random.nextInt(4000) == 0)
/* 225:    */       {
/* 226:212 */         world.setBlock(x, y, z, Blocks.ender_chest);
/* 227:    */       }
/* 228:214 */       else if (random.nextInt(2000) == 0)
/* 229:    */       {
/* 230:216 */         world.setBlock(x, y, z, Blocks.end_portal_frame);
/* 231:    */       }
/* 232:218 */       else if (random.nextInt(800) == 0)
/* 233:    */       {
/* 234:220 */         world.setBlock(x, y, z, Blocks.anvil);
/* 235:    */       }
/* 236:222 */       else if (random.nextInt(400) == 0)
/* 237:    */       {
/* 238:224 */         world.setBlock(x, y, z, Blocks.jukebox);
/* 239:    */       }
/* 240:226 */       else if (random.nextInt(500) == 0)
/* 241:    */       {
/* 242:228 */         world.setBlockToAir(x, y, z);
/* 243:229 */         world.setBlock(x, y, z, Blocks.brewing_stand, 0, 3);
/* 244:    */       }
/* 245:231 */       else if (random.nextInt(400) == 0)
/* 246:    */       {
/* 247:233 */         world.setBlock(x, y, z, Blocks.enchanting_table);
/* 248:    */       }
/* 249:235 */       else if (random.nextInt(100) == 0)
/* 250:    */       {
/* 251:237 */         world.setBlock(x, y + random.nextInt(this.properties.floorHeight - 2) + 1, z, Blocks.web);
/* 252:    */       }
/* 253:239 */       else if (random.nextInt(100) == 0)
/* 254:    */       {
/* 255:241 */         placeCake(random, world, x, y, z);
/* 256:    */       }
/* 257:243 */       else if (random.nextInt(100) == 0)
/* 258:    */       {
/* 259:245 */         placeBed(random, world, x, y, z, side);
/* 260:    */       }
/* 261:247 */       else if (random.nextInt(50) == 0)
/* 262:    */       {
/* 263:249 */         world.setBlock(x, y, z, Blocks.cauldron, random.nextInt(3), 3);
/* 264:    */       }
/* 265:251 */       else if (random.nextInt(50) == 0)
/* 266:    */       {
/* 267:253 */         placeFrame(random, world, x, y + 1, z, side, new ItemStack(Items.clock));
/* 268:    */       }
/* 269:259 */       else if (random.nextInt(50) == 0)
/* 270:    */       {
/* 271:261 */         placeShied(random, world, x, y + 1, z, side);
/* 272:    */       }
/* 273:263 */       else if (random.nextInt(50) == 0)
/* 274:    */       {
/* 275:265 */         placeFurnace(random, world, x, y, z, side);
/* 276:    */       }
/* 277:267 */       else if (random.nextInt(20) == 0)
/* 278:    */       {
/* 279:269 */         world.setBlock(x, y, z, Blocks.bookshelf);
/* 280:    */       }
/* 281:271 */       else if (random.nextInt(15) == 0)
/* 282:    */       {
/* 283:273 */         placeFlowerPot(random, world, x, y, z);
/* 284:    */       }
/* 285:275 */       else if (random.nextInt(20) == 0)
/* 286:    */       {
/* 287:277 */         world.setBlock(x, y, z, Blocks.crafting_table);
/* 288:    */       }
/* 289:    */     }
/* 290:    */   }
/* 291:    */   
/* 292:    */   public void decorateFullMonsterRoom(Random random, World world, int x, int y, int z, int side)
/* 293:    */   {
/* 294:284 */     if (side == 5)
/* 295:    */     {
/* 296:286 */       addMob(random, world, x + 1, y, z + 1);
/* 297:287 */       if (random.nextInt(8) == 0) {
/* 298:288 */         addMob(random, world, x + 1, y, z);
/* 299:    */       }
/* 300:289 */       if (random.nextInt(8) == 0) {
/* 301:290 */         addMob(random, world, x - 1, y, z);
/* 302:    */       }
/* 303:291 */       if (random.nextInt(8) == 0) {
/* 304:292 */         addMob(random, world, x, y, z + 1);
/* 305:    */       }
/* 306:293 */       if (random.nextInt(8) == 0) {
/* 307:294 */         addMob(random, world, x, y, z - 1);
/* 308:    */       }
/* 309:295 */       if (random.nextInt(8) == 0) {
/* 310:296 */         addMob(random, world, x + 1, y, z + 1);
/* 311:    */       }
/* 312:297 */       if (random.nextInt(8) == 0) {
/* 313:298 */         addMob(random, world, x - 1, y, z - 1);
/* 314:    */       }
/* 315:299 */       if (random.nextInt(8) == 0) {
/* 316:300 */         addMob(random, world, x - 1, y, z + 1);
/* 317:    */       }
/* 318:301 */       if (random.nextInt(8) == 0) {
/* 319:302 */         addMob(random, world, x + 1, y, z - 1);
/* 320:    */       }
/* 321:303 */       if (random.nextInt(40) == 0) {
/* 322:304 */         BuilderHelper.addSpawner(random, world, x, y - 1, z, this.properties.mobID);
/* 323:    */       }
/* 324:    */     }
/* 325:306 */     else if (random.nextInt(15) == 0)
/* 326:    */     {
/* 327:308 */       addMob(random, world, x, y, z);
/* 328:    */     }
/* 329:    */   }
/* 330:    */   
/* 331:    */   public void addMob(Random random, World world, int x, int y, int z)
/* 332:    */   {
/* 333:313 */     int lvl = random.nextInt(5);
/* 334:314 */     if (random.nextInt(5) == 0) {
/* 335:315 */       lvl += 5;
/* 336:    */     }
/* 337:316 */     if (random.nextInt(5) == 0) {
/* 338:317 */       lvl += 5;
/* 339:    */     }
/* 340:319 */     world.setBlock(x, y, z, ChocolateQuest.spawner, 0, 3);
/* 341:    */     
/* 342:321 */     TileEntity te = world.getTileEntity(x, y, z);
/* 343:322 */     if ((te instanceof BlockMobSpawnerTileEntity))
/* 344:    */     {
/* 345:324 */       BlockMobSpawnerTileEntity spawner = (BlockMobSpawnerTileEntity)te;
/* 346:325 */       spawner.mob = this.properties.mobID;
/* 347:326 */       spawner.metadata = lvl;
/* 348:    */     }
/* 349:    */   }
/* 350:    */   
/* 351:    */   public void addWeaponChest(Random random, World world, int x, int y, int z, int side)
/* 352:    */   {
/* 353:331 */     BuilderHelper.addWeaponChest(random, world, x, y, z);
/* 354:    */   }
/* 355:    */   
/* 356:    */   public void placeMap(Random random, World world, int x, int y, int z, int side)
/* 357:    */   {
/* 358:335 */     ItemStack is = new ItemStack(Items.map, 1, world.getUniqueDataId("map"));
/* 359:336 */     String s = "map_" + is.getItemDamage();
/* 360:337 */     MapData mapdata = new MapData(s);
/* 361:338 */     world.setItemData(s, mapdata);
/* 362:339 */     mapdata.scale = ((byte)random.nextInt(5));
/* 363:340 */     mapdata.xCenter = x;
/* 364:341 */     mapdata.zCenter = z;
/* 365:    */     
/* 366:343 */     placeFrame(random, world, x, y, z, side, is);
/* 367:    */   }
/* 368:    */   
/* 369:    */   public void placeShied(Random random, World world, int x, int y, int z, int side)
/* 370:    */   {
/* 371:347 */     DungeonMonstersBase mobType = (DungeonMonstersBase)RegisterDungeonMobs.mobList.get(this.properties.mobID);
/* 372:348 */     if (mobType != null) {
/* 373:350 */       placeFrame(random, world, x, y, z, side, new ItemStack(ChocolateQuest.shield, 1, mobType.getFlagId()));
/* 374:    */     }
/* 375:    */   }
/* 376:    */   
/* 377:    */   public void placeArmorStand(Random random, World world, int x, int y, int z, int side)
/* 378:    */   {
/* 379:355 */     int rotation = 0;
/* 380:356 */     switch (side)
/* 381:    */     {
/* 382:    */     case 0: 
/* 383:358 */       rotation = random.nextInt(360);
/* 384:    */     case 1: 
/* 385:359 */       rotation = -90; break;
/* 386:    */     case 2: 
/* 387:360 */       rotation = 90; break;
/* 388:    */     case 4: 
/* 389:361 */       rotation = 180;
/* 390:    */     }
/* 391:363 */     world.setBlock(x, y, z, ChocolateQuest.armorStand);
/* 392:364 */     BlockArmorStandTileEntity stand = (BlockArmorStandTileEntity)ChocolateQuest.armorStand.createTileEntity(world, 0);
/* 393:365 */     if (stand != null)
/* 394:    */     {
/* 395:367 */       stand.rotation = rotation;
/* 396:368 */       int lvl = 0;
/* 397:369 */       while ((lvl < 5) && (random.nextInt(Math.max(2, lvl)) == 0)) {
/* 398:369 */         lvl++;
/* 399:    */       }
/* 400:370 */       for (int i = 0; i < 4; i++) {
/* 401:371 */         stand.cargoItems[i] = EquipementHelper.getArmor(random, i + 1, lvl);
/* 402:    */       }
/* 403:372 */       setTileEntity(world, x, y, z, stand);
/* 404:    */     }
/* 405:    */   }
/* 406:    */   
/* 407:    */   public void placeTable(Random random, World world, int x, int y, int z, ItemStack is)
/* 408:    */   {
/* 409:377 */     world.setBlock(x, y, z, ChocolateQuest.table);
/* 410:378 */     BlockAltarTileEntity table = (BlockAltarTileEntity)ChocolateQuest.table.createTileEntity(world, 0);
/* 411:379 */     table.item = is;
/* 412:380 */     setTileEntity(world, x, y, z, table);
/* 413:    */   }
/* 414:    */   
/* 415:    */   public void placeFrame(Random random, World world, int x, int y, int z, int side, ItemStack is)
/* 416:    */   {
/* 417:384 */     switch (side)
/* 418:    */     {
/* 419:    */     case 1: 
/* 420:387 */       x--;
/* 421:388 */       side = 3;
/* 422:389 */       break;
/* 423:    */     case 2: 
/* 424:392 */       x++;
/* 425:393 */       side = 1;
/* 426:394 */       break;
/* 427:    */     case 3: 
/* 428:397 */       z--;
/* 429:398 */       side = 0;
/* 430:399 */       break;
/* 431:    */     case 4: 
/* 432:402 */       z++;
/* 433:403 */       side = 2;
/* 434:    */     }
/* 435:406 */     EntityItemFrame e = new EntityItemFrame(world, x, y, z, side);
/* 436:407 */     if (e.onValidSurface())
/* 437:    */     {
/* 438:409 */       e.setDisplayedItem(is);
/* 439:410 */       if (!world.isRemote) {
/* 440:412 */         world.spawnEntityInWorld(e);
/* 441:    */       }
/* 442:    */     }
/* 443:    */   }
/* 444:    */   
/* 445:    */   public void placePainting(Random random, World world, int x, int y, int z, int side)
/* 446:    */   {
/* 447:418 */     switch (side)
/* 448:    */     {
/* 449:    */     case 1: 
/* 450:421 */       x--;
/* 451:422 */       side = 3;
/* 452:423 */       break;
/* 453:    */     case 2: 
/* 454:426 */       x++;
/* 455:427 */       side = 1;
/* 456:428 */       break;
/* 457:    */     case 3: 
/* 458:431 */       z--;
/* 459:432 */       side = 0;
/* 460:433 */       break;
/* 461:    */     case 4: 
/* 462:436 */       z++;
/* 463:437 */       side = 2;
/* 464:    */     }
/* 465:440 */     EntityPainting e = new EntityPainting(world, x, y, z, side);
/* 466:441 */     if (e.onValidSurface()) {
/* 467:443 */       if (!world.isRemote) {
/* 468:445 */         world.spawnEntityInWorld(e);
/* 469:    */       }
/* 470:    */     }
/* 471:    */   }
/* 472:    */   
/* 473:    */   public void placeBed(Random random, World world, int x, int y, int z, int side)
/* 474:    */   {
/* 475:451 */     if (side > 4) {
/* 476:453 */       return;
/* 477:    */     }
/* 478:456 */     int xo = 0;int zo = 0;
/* 479:457 */     int md = 0;
/* 480:459 */     switch (side)
/* 481:    */     {
/* 482:    */     case 1: 
/* 483:462 */       xo = 1;
/* 484:463 */       md = 1;
/* 485:464 */       break;
/* 486:    */     case 2: 
/* 487:467 */       xo = -1;
/* 488:468 */       md = 3;
/* 489:469 */       break;
/* 490:    */     case 3: 
/* 491:472 */       zo = 1;
/* 492:473 */       md = 2;
/* 493:474 */       break;
/* 494:    */     case 4: 
/* 495:477 */       zo = -1;
/* 496:478 */       md = 0;
/* 497:    */     }
/* 498:482 */     world.setBlock(x + xo, y, z + zo, Blocks.bed, md, 3);
/* 499:483 */     world.setBlock(x, y, z, Blocks.bed, md + 8, 3);
/* 500:    */   }
/* 501:    */   
/* 502:    */   public void placeCake(Random random, World world, int x, int y, int z)
/* 503:    */   {
/* 504:487 */     world.setBlock(x, y, z, Blocks.planks, random.nextInt(5), 3);
/* 505:488 */     int md = random.nextInt(5);
/* 506:    */     
/* 507:490 */     world.setBlock(x, y + 1, z, Blocks.cake, md, 3);
/* 508:    */   }
/* 509:    */   
/* 510:    */   public void placeFlowerPot(Random random, World world, int x, int y, int z)
/* 511:    */   {
/* 512:494 */     world.setBlock(x, y, z, Blocks.planks, random.nextInt(5), 3);
/* 513:495 */     int md = random.nextInt(16);
/* 514:496 */     world.setBlock(x, y + 1, z, Blocks.flower_pot, md, 3);
/* 515:    */   }
/* 516:    */   
/* 517:    */   public void placeFurnace(Random random, World world, int x, int y, int z, int side)
/* 518:    */   {
/* 519:500 */     world.setBlockToAir(x, y, z);
/* 520:501 */     world.setBlock(x, y, z, Blocks.furnace);
/* 521:502 */     TileEntityFurnace furnace = (TileEntityFurnace)world.getTileEntity(x, y, z);
/* 522:503 */     if (furnace != null)
/* 523:    */     {
/* 524:505 */       if (random.nextInt(4) == 0) {
/* 525:507 */         furnace.setInventorySlotContents(1, new ItemStack(Items.coal, 1 + random.nextInt(15)));
/* 526:509 */       } else if (random.nextInt(4) == 0) {
/* 527:511 */         furnace.setInventorySlotContents(1, new ItemStack(Items.coal, 1 + random.nextInt(15), 1));
/* 528:513 */       } else if (random.nextInt(20) == 0) {
/* 529:515 */         furnace.setInventorySlotContents(1, new ItemStack(Items.lava_bucket, 1, 1));
/* 530:    */       }
/* 531:518 */       if (random.nextInt(5) == 0) {
/* 532:520 */         furnace.setInventorySlotContents(2, new ItemStack(Items.iron_ingot, 1 + random.nextInt(3)));
/* 533:522 */       } else if (random.nextInt(7) == 0) {
/* 534:524 */         furnace.setInventorySlotContents(2, new ItemStack(Items.gold_ingot, 1 + random.nextInt(3)));
/* 535:526 */       } else if (random.nextInt(15) == 0) {
/* 536:528 */         furnace.setInventorySlotContents(2, new ItemStack(Items.diamond, 1 + random.nextInt(3)));
/* 537:    */       }
/* 538:    */     }
/* 539:    */   }
/* 540:    */   
/* 541:    */   public void placeFoodFurnace(Random random, World world, int x, int y, int z, int side)
/* 542:    */   {
/* 543:534 */     world.setBlockToAir(x, y, z);
/* 544:535 */     world.setBlock(x, y, z, Blocks.furnace, 0, 3);
/* 545:536 */     TileEntity te = world.getTileEntity(x, y, z);
/* 546:537 */     if (te != null)
/* 547:    */     {
/* 548:539 */       TileEntityFurnace furnace = (TileEntityFurnace)te;
/* 549:540 */       if (random.nextInt(4) == 0) {
/* 550:542 */         furnace.setInventorySlotContents(1, new ItemStack(Items.coal, 1 + random.nextInt(15)));
/* 551:544 */       } else if (random.nextInt(4) == 0) {
/* 552:546 */         furnace.setInventorySlotContents(1, new ItemStack(Items.coal, 1 + random.nextInt(15), 1));
/* 553:548 */       } else if (random.nextInt(20) == 0) {
/* 554:550 */         furnace.setInventorySlotContents(1, new ItemStack(Items.lava_bucket));
/* 555:    */       }
/* 556:553 */       if (random.nextInt(5) == 0) {
/* 557:555 */         furnace.setInventorySlotContents(2, new ItemStack(Items.porkchop, 1 + random.nextInt(3)));
/* 558:557 */       } else if (random.nextInt(5) == 0) {
/* 559:559 */         furnace.setInventorySlotContents(2, new ItemStack(Items.fish, 1 + random.nextInt(3)));
/* 560:561 */       } else if (random.nextInt(5) == 0) {
/* 561:563 */         furnace.setInventorySlotContents(2, new ItemStack(Items.beef, 1 + random.nextInt(3)));
/* 562:    */       }
/* 563:    */     }
/* 564:    */   }
/* 565:    */   
/* 566:    */   public void setTileEntity(World world, int x, int y, int z, TileEntity t)
/* 567:    */   {
/* 568:569 */     world.setTileEntity(x, y, z, t);
/* 569:    */   }
/* 570:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.RoomBase
 * JD-Core Version:    0.7.1
 */