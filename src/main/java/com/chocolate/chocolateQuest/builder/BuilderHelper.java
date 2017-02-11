/*   1:    */ package com.chocolate.chocolateQuest.builder;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.RegisterChestItem;
/*   4:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   5:    */ import com.chocolate.chocolateQuest.block.BlockBannerStandTileEntity;
/*   6:    */ import com.chocolate.chocolateQuest.block.BlockMobSpawnerTileEntity;
/*   7:    */ import com.chocolate.chocolateQuest.builder.schematic.Schematic;
/*   8:    */ import com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase;
/*   9:    */ import com.chocolate.chocolateQuest.entity.mob.registry.RegisterDungeonMobs;
/*  10:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  11:    */ import java.io.File;
/*  12:    */ import java.util.ArrayList;
/*  13:    */ import java.util.Iterator;
/*  14:    */ import java.util.List;
/*  15:    */ import java.util.Random;
/*  16:    */ import net.minecraft.block.Block;
/*  17:    */ import net.minecraft.block.BlockContainer;
/*  18:    */ import net.minecraft.entity.Entity;
/*  19:    */ import net.minecraft.init.Blocks;
/*  20:    */ import net.minecraft.init.Items;
/*  21:    */ import net.minecraft.inventory.IInventory;
/*  22:    */ import net.minecraft.item.Item;
/*  23:    */ import net.minecraft.item.ItemDoor;
/*  24:    */ import net.minecraft.item.ItemStack;
/*  25:    */ import net.minecraft.nbt.NBTTagCompound;
/*  26:    */ import net.minecraft.nbt.NBTTagList;
/*  27:    */ import net.minecraft.tileentity.MobSpawnerBaseLogic;
/*  28:    */ import net.minecraft.tileentity.TileEntity;
/*  29:    */ import net.minecraft.tileentity.TileEntityChest;
/*  30:    */ import net.minecraft.tileentity.TileEntityDispenser;
/*  31:    */ import net.minecraft.tileentity.TileEntityFurnace;
/*  32:    */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*  33:    */ import net.minecraft.world.World;
/*  34:    */ import net.minecraft.world.biome.BiomeGenBase;
/*  35:    */ import net.minecraftforge.common.DungeonHooks;
/*  36:    */ 
/*  37:    */ public class BuilderHelper
/*  38:    */ {
/*  39: 40 */   public static BuilderHelper builderHelper = new BuilderHelper();
/*  40:    */   static final byte DEFAULT = 0;
/*  41:    */   static final byte FOOD = 1;
/*  42:    */   static final byte WEAPONS = 2;
/*  43:    */   static final byte MINERALS = 3;
/*  44:    */   static final byte TREASURE = 4;
/*  45:    */   List<BlockData> specialBlocks;
/*  46:    */   List<TileEntity> tileEntities;
/*  47:    */   
/*  48:    */   public void initialize()
/*  49:    */   {
/*  50: 47 */     if (this.specialBlocks == null) {
/*  51: 48 */       this.specialBlocks = new ArrayList();
/*  52:    */     }
/*  53: 49 */     this.specialBlocks.clear();
/*  54: 51 */     if (this.tileEntities == null) {
/*  55: 52 */       this.tileEntities = new ArrayList();
/*  56:    */     }
/*  57: 53 */     this.tileEntities.clear();
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void flush(World world)
/*  61:    */   {
/*  62: 57 */     copyTileEntities(world);
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void putSchematicInWorld(Random random, World world, Schematic schematic, int i, int j, int k, int idMob)
/*  66:    */   {
/*  67: 63 */     schematic.setPosition(i, j, k);
/*  68: 64 */     int sx = schematic.width;
/*  69: 65 */     int sy = schematic.height;
/*  70: 66 */     int sz = schematic.length;
/*  71: 67 */     for (int y = 0; y < sy; y++) {
/*  72: 68 */       for (int x = 0; x < sx; x++) {
/*  73: 69 */         for (int z = 0; z < sz; z++)
/*  74:    */         {
/*  75: 70 */           Block block = schematic.getBlock(x, y, z);
/*  76: 71 */           int metadata = schematic.getBlockMetadata(x, y, z);
/*  77: 72 */           int posX = i + x;
/*  78: 73 */           int posY = j + y;
/*  79: 74 */           int posZ = k + z;
/*  80: 75 */           if (!checkIfPlacedOnFirstPass(block)) {
/*  81: 76 */             this.specialBlocks.add(new BlockData(posX, posY, posZ, block, metadata));
/*  82: 79 */           } else if (block != ChocolateQuest.emptyBlock) {
/*  83: 81 */             if (block == ChocolateQuest.exporterChest)
/*  84:    */             {
/*  85: 82 */               if (metadata == 4)
/*  86:    */               {
/*  87: 83 */                 world.setBlockToAir(posX, posY, posZ);
/*  88: 84 */                 Entity boss = ((DungeonMonstersBase)RegisterDungeonMobs.mobList.get(idMob)).getBoss(world, posX, posY, posZ);
/*  89: 85 */                 if (boss != null)
/*  90:    */                 {
/*  91: 86 */                   boss.setPosition(posX, posY, posZ);
/*  92: 87 */                   world.spawnEntityInWorld(boss);
/*  93: 88 */                   if (boss.ridingEntity != null)
/*  94:    */                   {
/*  95: 89 */                     boss.ridingEntity.setPosition(posX, posY, posZ);
/*  96: 90 */                     world.spawnEntityInWorld(boss.ridingEntity);
/*  97:    */                   }
/*  98:    */                 }
/*  99:    */               }
/* 100: 93 */               else if (metadata == 1)
/* 101:    */               {
/* 102: 94 */                 addFoodChest(random, world, posX, posY, posZ);
/* 103:    */               }
/* 104: 95 */               else if (metadata == 0)
/* 105:    */               {
/* 106: 96 */                 addTreasure(random, world, posX, posY, posZ);
/* 107:    */               }
/* 108: 97 */               else if (metadata == 3)
/* 109:    */               {
/* 110: 98 */                 addMineralChest(random, world, posX, posY, posZ);
/* 111:    */               }
/* 112: 99 */               else if (metadata == 2)
/* 113:    */               {
/* 114:100 */                 addWeaponChest(random, world, posX, posY, posZ);
/* 115:    */               }
/* 116:    */             }
/* 117:102 */             else if (block == Blocks.chest)
/* 118:    */             {
/* 119:103 */               addChest(random, world, posX, posY, posZ);
/* 120:    */             }
/* 121:104 */             else if (block == Blocks.furnace)
/* 122:    */             {
/* 123:106 */               world.setBlock(posX, posY, posZ, block, metadata, 3);
/* 124:107 */               TileEntityFurnace tef = (TileEntityFurnace)world.getTileEntity(posX, posY, posZ);
/* 125:109 */               if (random.nextInt(15) == 0) {
/* 126:111 */                 tef.setInventorySlotContents(0, new ItemStack(Items.gold_ingot, random.nextInt(3) + 1));
/* 127:    */               }
/* 128:114 */               if (random.nextInt(3) == 0) {
/* 129:116 */                 tef.setInventorySlotContents(1, new ItemStack(Items.coal, random.nextInt(45) + 1));
/* 130:    */               }
/* 131:118 */               world.setBlockMetadataWithNotify(posX, posY, posZ, metadata, 3);
/* 132:    */             }
/* 133:119 */             else if (block == Blocks.dispenser)
/* 134:    */             {
/* 135:121 */               world.setBlock(posX, posY, posZ, block, metadata, 0);
/* 136:122 */               TileEntityDispenser ted = (TileEntityDispenser)world.getTileEntity(posX, posY, posZ);
/* 137:123 */               for (int v = 0; v < 9; v++) {
/* 138:125 */                 if (random.nextInt(20) == 0)
/* 139:    */                 {
/* 140:127 */                   if (random.nextInt(50) == 0) {
/* 141:129 */                     ted.setInventorySlotContents(v, new ItemStack(Items.experience_bottle, random.nextInt(64) + 1));
/* 142:    */                   }
/* 143:132 */                   ted.setInventorySlotContents(v, new ItemStack(Items.fire_charge, random.nextInt(64) + 1));
/* 144:    */                 }
/* 145:    */                 else
/* 146:    */                 {
/* 147:136 */                   ted.setInventorySlotContents(v, new ItemStack(Items.arrow, random.nextInt(64) + 1));
/* 148:    */                 }
/* 149:    */               }
/* 150:139 */               world.setBlockMetadataWithNotify(posX, posY, posZ, metadata, 3);
/* 151:    */             }
/* 152:140 */             else if (block == Blocks.dropper)
/* 153:    */             {
/* 154:142 */               world.setBlock(posX, posY, posZ, block, metadata, 3);
/* 155:143 */               world.setBlockMetadataWithNotify(posX, posY, posZ, metadata, 3);
/* 156:    */             }
/* 157:    */             else
/* 158:    */             {
/* 159:146 */               world.setBlock(posX, posY, posZ, block, metadata, 3);
/* 160:    */             }
/* 161:    */           }
/* 162:    */         }
/* 163:    */       }
/* 164:    */     }
/* 165:155 */     copySpecialBlocks(world);
/* 166:    */     
/* 167:    */ 
/* 168:    */ 
/* 169:    */ 
/* 170:    */ 
/* 171:    */ 
/* 172:    */ 
/* 173:    */ 
/* 174:    */ 
/* 175:    */ 
/* 176:166 */     List<TileEntity> list = schematic.getTileEntities();
/* 177:167 */     NBTTagList tags = schematic.getTileEntitiesTag();
/* 178:168 */     int tagCount = 0;
/* 179:169 */     for (TileEntity te : list)
/* 180:    */     {
/* 181:170 */       Block block = world.getBlock(te.xCoord, te.yCoord, te.zCoord);
/* 182:171 */       if ((block instanceof BlockContainer))
/* 183:    */       {
/* 184:172 */         TileEntity tempEntity = ((BlockContainer)block).createNewTileEntity(world, world.getBlockMetadata(te.xCoord, te.yCoord, te.zCoord));
/* 185:173 */         NBTTagCompound tag = tags.getCompoundTagAt(tagCount);
/* 186:174 */         tempEntity.readFromNBT(tag);
/* 187:175 */         boolean putTileEntity = true;
/* 188:176 */         if ((tempEntity instanceof BlockMobSpawnerTileEntity))
/* 189:    */         {
/* 190:177 */           BlockMobSpawnerTileEntity spawner = (BlockMobSpawnerTileEntity)tempEntity;
/* 191:178 */           spawner.mob = idMob;
/* 192:    */         }
/* 193:180 */         if ((tempEntity instanceof BlockBannerStandTileEntity))
/* 194:    */         {
/* 195:181 */           BlockBannerStandTileEntity stand = (BlockBannerStandTileEntity)tempEntity;
/* 196:182 */           int id = ((DungeonMonstersBase)RegisterDungeonMobs.mobList.get(idMob)).getFlagId();
/* 197:183 */           stand.item = new ItemStack(ChocolateQuest.banner, 1, id);
/* 198:    */         }
/* 199:185 */         if ((tempEntity instanceof TileEntityMobSpawner))
/* 200:    */         {
/* 201:186 */           TileEntityMobSpawner spawner = (TileEntityMobSpawner)tempEntity;
/* 202:187 */           String name = spawner.func_145881_a().getEntityNameToSpawn();
/* 203:188 */           if (name.equals("Pig")) {
/* 204:189 */             setMobForSpawner(spawner, idMob, te.xCoord, te.yCoord, te.zCoord, random);
/* 205:    */           }
/* 206:    */         }
/* 207:192 */         if (block == Blocks.chest) {
/* 208:193 */           putTileEntity = false;
/* 209:    */         }
/* 210:195 */         if ((block == Blocks.furnace) && 
/* 211:196 */           (isInventoryEmpty((TileEntityFurnace)tempEntity))) {
/* 212:197 */           putTileEntity = false;
/* 213:    */         }
/* 214:199 */         if ((block == Blocks.dispenser) && 
/* 215:200 */           (isInventoryEmpty((TileEntityDispenser)tempEntity))) {
/* 216:201 */           putTileEntity = false;
/* 217:    */         }
/* 218:203 */         if (putTileEntity) {
/* 219:204 */           addTileEntity(te.xCoord, te.yCoord, te.zCoord, tempEntity);
/* 220:    */         }
/* 221:    */       }
/* 222:207 */       tagCount++;
/* 223:    */     }
/* 224:209 */     List<Entity> listEntity = schematic.getEntities(world);
/* 225:210 */     for (Entity e : listEntity) {
/* 226:211 */       world.spawnEntityInWorld(e);
/* 227:    */     }
/* 228:    */   }
/* 229:    */   
/* 230:    */   public void addTileEntity(int x, int y, int z, TileEntity tileEntity)
/* 231:    */   {
/* 232:217 */     tileEntity.xCoord = x;
/* 233:218 */     tileEntity.yCoord = y;
/* 234:219 */     tileEntity.zCoord = z;
/* 235:220 */     this.tileEntities.add(tileEntity);
/* 236:    */   }
/* 237:    */   
/* 238:    */   public void copyTileEntities(World world)
/* 239:    */   {
/* 240:    */     Iterator i;
/* 241:225 */     if (this.tileEntities.size() > 0) {
/* 242:227 */       for (i = this.tileEntities.iterator(); i.hasNext();)
/* 243:    */       {
/* 244:229 */         TileEntity tempEntity = (TileEntity)i.next();
/* 245:230 */         world.setTileEntity(tempEntity.xCoord, tempEntity.yCoord, tempEntity.zCoord, tempEntity);
/* 246:    */       }
/* 247:    */     }
/* 248:    */   }
/* 249:    */   
/* 250:    */   public boolean checkIfPlacedOnFirstPass(Block id)
/* 251:    */   {
/* 252:238 */     return (id != Blocks.redstone_wire) && (id != Blocks.redstone_torch) && (id != Blocks.stone_button) && (id != Blocks.wooden_button) && (id != Blocks.bed) && (id != Blocks.torch) && (id != Blocks.ladder) && (id != Blocks.wooden_door) && (id != Blocks.iron_door) && (id != Blocks.lever) && (id != Blocks.torch) && (id != Blocks.ladder) && (id != Blocks.bed) && (id != Blocks.tripwire_hook) && (id != Blocks.wall_sign) && (id != Blocks.piston) && (id != Blocks.piston_head);
/* 253:    */   }
/* 254:    */   
/* 255:    */   public void copySpecialBlocks(World world)
/* 256:    */   {
/* 257:250 */     BlockData b = null;
/* 258:251 */     Random rand = new Random();
/* 259:252 */     if (this.specialBlocks.size() > 0)
/* 260:    */     {
/* 261:254 */       for (Iterator i = this.specialBlocks.iterator(); i.hasNext();)
/* 262:    */       {
/* 263:256 */         b = (BlockData)i.next();
/* 264:257 */         if ((b.block == Blocks.wooden_door) || (b.block == Blocks.iron_door))
/* 265:    */         {
/* 266:259 */           if (b.blockMetadata < 8) {
/* 267:261 */             ItemDoor.placeDoorBlock(world, b.x, b.y, b.z, b.blockMetadata, b.block == Blocks.wooden_door ? Blocks.wooden_door : Blocks.iron_door);
/* 268:    */           } else {
/* 269:263 */             world.setBlock(b.x, b.y, b.z, b.block, b.blockMetadata, 0);
/* 270:    */           }
/* 271:    */         }
/* 272:265 */         else if (b.block == Blocks.redstone_torch) {
/* 273:267 */           world.setBlock(b.x, b.y, b.z, b.block, b.blockMetadata, 3);
/* 274:268 */         } else if (b.block == Blocks.piston_head) {
/* 275:270 */           world.setBlock(b.x, b.y, b.z, b.block, b.blockMetadata, 0);
/* 276:    */         } else {
/* 277:273 */           world.setBlock(b.x, b.y, b.z, b.block, b.blockMetadata, 3);
/* 278:    */         }
/* 279:    */       }
/* 280:277 */       this.specialBlocks.clear();
/* 281:    */     }
/* 282:    */   }
/* 283:    */   
/* 284:    */   public boolean isInventoryEmpty(IInventory inv)
/* 285:    */   {
/* 286:282 */     for (int i = 0; i < inv.getSizeInventory(); i++) {
/* 287:283 */       if (inv.getStackInSlot(i) != null) {
/* 288:284 */         return false;
/* 289:    */       }
/* 290:    */     }
/* 291:286 */     return true;
/* 292:    */   }
/* 293:    */   
/* 294:    */   public static void clearArea(Random random, World world, int i, int j, int k, int sizeX, int sizeZ)
/* 295:    */   {
/* 296:291 */     Perlin3D p = new Perlin3D(world.getSeed(), 8, random);
/* 297:292 */     Perlin3D p2 = new Perlin3D(world.getSeed(), 32, random);
/* 298:    */     
/* 299:294 */     int wallSize = 8;
/* 300:295 */     int size = sizeX + wallSize * 2;
/* 301:296 */     int height = 32;
/* 302:    */     
/* 303:298 */     i -= wallSize;
/* 304:299 */     k -= wallSize;
/* 305:301 */     for (int x = 0; x < size; x++) {
/* 306:303 */       for (int z = 0; z < size; z++)
/* 307:    */       {
/* 308:305 */         int maxHeight = world.getTopSolidOrLiquidBlock(x + i, z + k) - 1 - j;
/* 309:306 */         for (int y = 0; y <= maxHeight; y++) {
/* 310:308 */           if ((x > wallSize) && (z > wallSize) && (x < size - wallSize) && (z < size - wallSize))
/* 311:    */           {
/* 312:309 */             world.setBlockToAir(x + i, j + y, z + k);
/* 313:    */           }
/* 314:    */           else
/* 315:    */           {
/* 316:312 */             float noiseVar = (maxHeight - y) / (Math.max(1, maxHeight) * 1.5F);
/* 317:    */             
/* 318:314 */             int tWallSize = wallSize;
/* 319:315 */             noiseVar += Math.max(0.0F, (tWallSize - x) / 8.0F);
/* 320:316 */             noiseVar += Math.max(0.0F, (tWallSize - (size - x)) / 8.0F);
/* 321:    */             
/* 322:318 */             noiseVar += Math.max(0.0F, (tWallSize - z) / 8.0F);
/* 323:319 */             noiseVar += Math.max(0.0F, (tWallSize - (size - z)) / 8.0F);
/* 324:320 */             double value = (p.getNoiseAt(x + i, y, z + k) + p2.getNoiseAt(x + i, y, z + k) + noiseVar) / 3.0D;
/* 325:321 */             if (value < 0.5D) {
/* 326:322 */               world.setBlockToAir(i + x, j + y, k + z);
/* 327:    */             }
/* 328:    */           }
/* 329:    */         }
/* 330:325 */         maxHeight = world.getTopSolidOrLiquidBlock(x + i, z + k);
/* 331:326 */         BiomeGenBase biome = world.getBiomeGenForCoords(x + i, z + k);
/* 332:327 */         world.setBlock(i + x, maxHeight - 1, k + z, biome.topBlock);
/* 333:    */       }
/* 334:    */     }
/* 335:    */   }
/* 336:    */   
/* 337:    */   public static void addSpawner(Random random, World world, int x, int y, int z, int idMob)
/* 338:    */   {
/* 339:335 */     world.setBlock(x, y, z, Blocks.mob_spawner, 0, 0);
/* 340:336 */     TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(x, y, z);
/* 341:337 */     if (tileentitymobspawner != null) {
/* 342:339 */       setMobForSpawner(tileentitymobspawner, idMob, x, y, z, random);
/* 343:    */     }
/* 344:    */   }
/* 345:    */   
/* 346:    */   public static void setMobForSpawner(TileEntityMobSpawner spawner, int idMob, int x, int y, int z, Random random)
/* 347:    */   {
/* 348:344 */     String mob = ((DungeonMonstersBase)RegisterDungeonMobs.mobList.get(idMob)).getSpawnerName(x, y, z, random);
/* 349:345 */     if (mob != null) {
/* 350:347 */       spawner.func_145881_a().setEntityName(mob);
/* 351:    */     } else {
/* 352:351 */       spawner.func_145881_a().setEntityName(DungeonHooks.getRandomDungeonMob(random));
/* 353:    */     }
/* 354:    */   }
/* 355:    */   
/* 356:    */   public static boolean addTreasure(Random random, World world, int x, int y, int z)
/* 357:    */   {
/* 358:359 */     world.setBlock(x, y, z, Blocks.chest, 0, 0);
/* 359:360 */     TileEntityChest tileentitychest = new TileEntityChest();
/* 360:    */     
/* 361:362 */     int itemsCount = random.nextInt(4) + 2;
/* 362:    */     
/* 363:364 */     ItemStack itemstack = null;
/* 364:366 */     for (int f = 1; f < itemsCount + 1; f++)
/* 365:    */     {
/* 366:368 */       itemstack = RegisterChestItem.getRandomItemStack(RegisterChestItem.treasureList, random);
/* 367:369 */       int slot = random.nextInt(tileentitychest.getSizeInventory());
/* 368:370 */       tileentitychest.setInventorySlotContents(slot, itemstack);
/* 369:    */     }
/* 370:372 */     if (random.nextInt(3) == 0)
/* 371:    */     {
/* 372:    */       Item record;
/* 373:375 */       switch (random.nextInt(10))
/* 374:    */       {
/* 375:    */       case 0: 
/* 376:376 */         record = Items.record_blocks; break;
/* 377:    */       case 1: 
/* 378:377 */         record = Items.record_cat; break;
/* 379:    */       case 2: 
/* 380:378 */         record = Items.record_chirp; break;
/* 381:    */       case 3: 
/* 382:379 */         record = Items.record_far; break;
/* 383:    */       case 4: 
/* 384:380 */         record = Items.record_mall; break;
/* 385:    */       case 5: 
/* 386:381 */         record = Items.record_mellohi; break;
/* 387:    */       case 6: 
/* 388:382 */         record = Items.record_stal; break;
/* 389:    */       case 7: 
/* 390:383 */         record = Items.record_strad; break;
/* 391:    */       case 8: 
/* 392:384 */         record = Items.record_wait; break;
/* 393:    */       case 9: 
/* 394:385 */         record = Items.record_ward; break;
/* 395:    */       default: 
/* 396:386 */         record = Items.record_blocks;
/* 397:    */       }
/* 398:388 */       int slot = random.nextInt(tileentitychest.getSizeInventory());
/* 399:389 */       tileentitychest.setInventorySlotContents(slot, new ItemStack(record));
/* 400:    */     }
/* 401:391 */     world.setTileEntity(x, y, z, tileentitychest);
/* 402:392 */     return true;
/* 403:    */   }
/* 404:    */   
/* 405:    */   public static boolean addFoodChest(Random random, World world, int x, int y, int z)
/* 406:    */   {
/* 407:396 */     world.setBlock(x, y, z, Blocks.chest, 0, 0);
/* 408:397 */     TileEntityChest tileentitychest = new TileEntityChest();
/* 409:398 */     int itemsCount = random.nextInt(6) + 2;
/* 410:401 */     for (int f = 0; f < itemsCount; f++)
/* 411:    */     {
/* 412:404 */       ItemStack itemstack = RegisterChestItem.getRandomItemStack(RegisterChestItem.foodList, random);
/* 413:405 */       int slot = random.nextInt(tileentitychest.getSizeInventory());
/* 414:406 */       tileentitychest.setInventorySlotContents(slot, itemstack);
/* 415:    */     }
/* 416:409 */     world.setTileEntity(x, y, z, tileentitychest);
/* 417:410 */     return true;
/* 418:    */   }
/* 419:    */   
/* 420:    */   public static boolean addMineralChest(Random random, World world, int x, int y, int z)
/* 421:    */   {
/* 422:414 */     world.setBlock(x, y, z, Blocks.chest, 0, 0);
/* 423:415 */     TileEntityChest tileentitychest = new TileEntityChest();
/* 424:    */     
/* 425:417 */     int itemsCount = random.nextInt(6) + 2;
/* 426:    */     
/* 427:419 */     ItemStack itemstack = null;
/* 428:421 */     for (int f = 0; f < itemsCount; f++)
/* 429:    */     {
/* 430:423 */       itemstack = RegisterChestItem.getRandomItemStack(RegisterChestItem.mineralList, random);
/* 431:424 */       int slot = random.nextInt(tileentitychest.getSizeInventory());
/* 432:425 */       tileentitychest.setInventorySlotContents(slot, itemstack);
/* 433:    */     }
/* 434:427 */     world.setTileEntity(x, y, z, tileentitychest);
/* 435:    */     
/* 436:429 */     return true;
/* 437:    */   }
/* 438:    */   
/* 439:    */   public static boolean addWeaponChest(Random random, World world, int x, int y, int z)
/* 440:    */   {
/* 441:433 */     world.setBlock(x, y, z, Blocks.chest, 0, 0);
/* 442:434 */     TileEntityChest tileentitychest = new TileEntityChest();
/* 443:435 */     int itemsCount = random.nextInt(6) + 1;
/* 444:    */     
/* 445:437 */     ItemStack itemstack = null;
/* 446:439 */     for (int f = 0; f < itemsCount; f++)
/* 447:    */     {
/* 448:441 */       itemstack = RegisterChestItem.getRandomItemStack(RegisterChestItem.weaponList, random);
/* 449:443 */       if (itemstack != null) {
/* 450:445 */         BDHelper.EnchantItemRandomly(itemstack, random);
/* 451:    */       }
/* 452:447 */       int slot = random.nextInt(tileentitychest.getSizeInventory());
/* 453:448 */       tileentitychest.setInventorySlotContents(slot, itemstack);
/* 454:    */     }
/* 455:450 */     world.setTileEntity(x, y, z, tileentitychest);
/* 456:451 */     return true;
/* 457:    */   }
/* 458:    */   
/* 459:    */   public static boolean addChest(Random random, World world, int x, int y, int z)
/* 460:    */   {
/* 461:455 */     world.setBlock(x, y, z, Blocks.chest, 0, 3);
/* 462:456 */     int itemsCount = random.nextInt(8) + 1;
/* 463:457 */     TileEntityChest tileentitychest = new TileEntityChest();
/* 464:    */     
/* 465:459 */     int f = 0;
/* 466:462 */     if (random.nextInt(150) == 0)
/* 467:    */     {
/* 468:464 */       Item record = random.nextBoolean() ? Items.record_11 : Items.record_13;
/* 469:465 */       tileentitychest.setInventorySlotContents(f, new ItemStack(record));
/* 470:466 */       f++;
/* 471:467 */       itemsCount++;
/* 472:    */     }
/* 473:470 */     for (; f < itemsCount; f++)
/* 474:    */     {
/* 475:472 */       ItemStack itemstack = RegisterChestItem.getRandomItemStack(RegisterChestItem.chestList, random);
/* 476:473 */       int slot = random.nextInt(tileentitychest.getSizeInventory());
/* 477:474 */       tileentitychest.setInventorySlotContents(slot, itemstack);
/* 478:    */     }
/* 479:476 */     world.setTileEntity(x, y, z, tileentitychest);
/* 480:477 */     return true;
/* 481:    */   }
/* 482:    */   
/* 483:    */   public static Schematic getNBTMap(String mapDir)
/* 484:    */   {
/* 485:483 */     return getNBTMap(new File(BDHelper.getAppDir(), BDHelper.getInfoDir() + "Building/" + mapDir));
/* 486:    */   }
/* 487:    */   
/* 488:    */   public static Schematic getRandomNBTMap(String d, Random random)
/* 489:    */   {
/* 490:488 */     File dir = new File(BDHelper.getAppDir(), BDHelper.getInfoDir() + "Building/" + d);
/* 491:489 */     File[] file = dir.listFiles();
/* 492:490 */     int s = 0;
/* 493:491 */     if (file.length > 1) {
/* 494:492 */       s = random.nextInt(file.length);
/* 495:    */     }
/* 496:494 */     return getNBTMap(file[s]);
/* 497:    */   }
/* 498:    */   
/* 499:    */   public static Schematic getNBTMap(File file)
/* 500:    */   {
/* 501:499 */     return new Schematic(file);
/* 502:    */   }
/* 503:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.BuilderHelper
 * JD-Core Version:    0.7.1
 */