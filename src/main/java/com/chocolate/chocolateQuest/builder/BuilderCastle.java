/*   1:    */ package com.chocolate.chocolateQuest.builder;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.BuilderBase;
/*   4:    */ import com.chocolate.chocolateQuest.API.BuilderBlockData;
/*   5:    */ import com.chocolate.chocolateQuest.API.HelperReadConfig;
/*   6:    */ import com.chocolate.chocolateQuest.WorldGeneratorNew;
/*   7:    */ import com.chocolate.chocolateQuest.builder.decorator.BuildingProperties;
/*   8:    */ import com.chocolate.chocolateQuest.builder.decorator.DecoratorDoor;
/*   9:    */ import com.chocolate.chocolateQuest.builder.decorator.DecoratorFloor;
/*  10:    */ import com.chocolate.chocolateQuest.builder.decorator.DecoratorRoof;
/*  11:    */ import com.chocolate.chocolateQuest.builder.decorator.DecoratorWindow;
/*  12:    */ import com.chocolate.chocolateQuest.builder.decorator.EntranceGenerator;
/*  13:    */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  14:    */ import com.chocolate.chocolateQuest.builder.decorator.RoomsHelper;
/*  15:    */ import com.chocolate.chocolateQuest.builder.decorator.TowerRound;
/*  16:    */ import com.chocolate.chocolateQuest.builder.decorator.TowerSquare;
/*  17:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomBoss;
/*  18:    */ import java.util.Properties;
/*  19:    */ import java.util.Random;
/*  20:    */ import net.minecraft.block.Block;
/*  21:    */ import net.minecraft.init.Blocks;
/*  22:    */ import net.minecraft.world.World;
/*  23:    */ import net.minecraftforge.common.util.ForgeDirection;
/*  24:    */ 
/*  25:    */ public class BuilderCastle
/*  26:    */   extends BuilderBase
/*  27:    */ {
/*  28: 24 */   final int MIN_ROOM_SIZE = 5;
/*  29: 25 */   int roomSize = 10;
/*  30: 26 */   int maxSize = 60;
/*  31: 28 */   int posY = 64;
/*  32:    */   BuildingProperties properties;
/*  33:    */   
/*  34:    */   public BuilderBase load(Properties prop)
/*  35:    */   {
/*  36: 35 */     this.maxSize = Math.max(30, HelperReadConfig.getIntegerProperty(prop, "maxSize", 60));
/*  37: 36 */     this.roomSize = Math.max(6, HelperReadConfig.getIntegerProperty(prop, "roomSize", 8));
/*  38:    */     
/*  39: 38 */     this.properties = new BuildingProperties();
/*  40: 39 */     this.properties.load(prop);
/*  41: 40 */     return this;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void generate(Random random, World world, int x, int z, int idMob)
/*  45:    */   {
/*  46: 45 */     x -= this.maxSize / 2;
/*  47: 46 */     z -= this.maxSize / 2;
/*  48: 47 */     generate(random, world, x, this.posY, z, idMob);
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void generate(Random random, World world, int x, int y, int z, int idMob)
/*  52:    */   {
/*  53: 53 */     WorldGeneratorNew.createChunks(world, x, z, this.maxSize, this.maxSize);
/*  54: 54 */     this.properties.initialize(random);
/*  55: 55 */     int offset = 4;
/*  56: 56 */     BuilderHelper.clearArea(random, world, x - offset, y, z - offset, this.maxSize + offset * 2, this.maxSize + offset * 2);
/*  57: 59 */     for (int h = 0; h < 4; h++)
/*  58:    */     {
/*  59: 60 */       int posY = y - 1 - h;
/*  60: 61 */       for (int sx = -1; sx <= this.maxSize + 1; sx++) {
/*  61: 62 */         for (int l = 0; l < offset; l++)
/*  62:    */         {
/*  63: 63 */           int posX = x + sx;
/*  64: 64 */           int posZ = z;
/*  65: 65 */           Block block = Blocks.air;
/*  66: 66 */           if (h > 1) {
/*  67: 67 */             block = Blocks.water;
/*  68:    */           }
/*  69: 68 */           world.setBlock(posX, posY, posZ - l, block);
/*  70:    */           
/*  71: 70 */           posZ = z + this.maxSize;
/*  72: 71 */           world.setBlock(posX, posY, posZ + l, block);
/*  73:    */           
/*  74: 73 */           posX = x;
/*  75: 74 */           posZ = z + sx;
/*  76: 75 */           world.setBlock(posX - l, posY, posZ, block);
/*  77: 76 */           posX = x + this.maxSize;
/*  78: 77 */           world.setBlock(posX + l, posY, posZ, block);
/*  79:    */         }
/*  80:    */       }
/*  81:    */     }
/*  82: 83 */     generateCastleStructure(random, world, x, y, z, this.maxSize, this.maxSize);
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void generateCastleStructure(Random random, World world, int x, int y, int z, int maxSizeX, int maxSizeZ)
/*  86:    */   {
/*  87: 87 */     int sizeQuartX = maxSizeX / 4;
/*  88: 88 */     int sizeQuartZ = maxSizeZ / 4;
/*  89: 89 */     int sizeX = sizeQuartX + random.nextInt(sizeQuartX * 3);
/*  90: 90 */     int sizeZ = sizeQuartZ + random.nextInt(sizeQuartZ * 3);
/*  91: 91 */     int offsetX = random.nextInt(sizeQuartX);
/*  92: 92 */     int offsetZ = random.nextInt(sizeQuartZ);
/*  93: 93 */     sizeX = Math.max(this.roomSize, sizeX);
/*  94: 94 */     sizeZ = Math.max(this.roomSize, sizeZ);
/*  95: 95 */     x += offsetX;
/*  96: 96 */     z += offsetZ;
/*  97: 97 */     boolean walkableRoof = false;
/*  98: 98 */     if (Math.min(sizeX, sizeZ) > this.roomSize)
/*  99:    */     {
/* 100: 99 */       generateCastleStructure(random, world, x, y + (this.properties.floorHeight + 1) * 2, z, sizeX, sizeZ);
/* 101:100 */       walkableRoof = true;
/* 102:    */     }
/* 103:104 */     generateSquaredStructure(random, world, x, y, z, sizeX, sizeZ, 1, walkableRoof, false);
/* 104:    */     
/* 105:106 */     generateStructureAtSide(random, world, x + sizeX, y, z, sizeX, offsetZ, 1, ForgeDirection.NORTH);
/* 106:107 */     generateStructureAtSide(random, world, x, y, z + sizeZ, sizeX, maxSizeZ - sizeZ - offsetZ, 1, ForgeDirection.SOUTH);
/* 107:108 */     generateStructureAtSide(random, world, x, y, z + sizeZ, offsetX, sizeZ, 1, ForgeDirection.WEST);
/* 108:109 */     generateStructureAtSide(random, world, x + sizeX, y, z, maxSizeX - sizeX - offsetX, sizeZ, 1, ForgeDirection.EAST);
/* 109:    */     
/* 110:111 */     EntranceGenerator entranceGenerator = new EntranceGenerator(this.properties);
/* 111:112 */     entranceGenerator.generateEntance(world, x + sizeX / 2, y, z, ForgeDirection.NORTH);
/* 112:113 */     entranceGenerator.generateEntance(world, x + sizeX / 2, y, z + sizeZ, ForgeDirection.SOUTH);
/* 113:114 */     entranceGenerator.generateEntance(world, x, y, z + sizeZ / 2, ForgeDirection.WEST);
/* 114:115 */     entranceGenerator.generateEntance(world, x + sizeX, y, z + sizeZ / 2, ForgeDirection.EAST);
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void generateStructureAtSide(Random random, World world, int x, int y, int z, int maxSizeX, int maxSizeZ, int floors, ForgeDirection side)
/* 118:    */   {
/* 119:137 */     if ((maxSizeX <= 0) || (maxSizeZ <= 0)) {
/* 120:138 */       return;
/* 121:    */     }
/* 122:139 */     int sizeX = Math.max(random.nextInt(maxSizeX), this.roomSize);
/* 123:140 */     int sizeZ = Math.max(random.nextInt(maxSizeZ), this.roomSize);
/* 124:141 */     boolean addStairs = false;
/* 125:    */     
/* 126:143 */     int emptyAreaLength = 0;
/* 127:144 */     if (side.offsetX != 0) {
/* 128:145 */       emptyAreaLength = maxSizeX;
/* 129:146 */     } else if (side.offsetZ != 0) {
/* 130:147 */       emptyAreaLength = maxSizeZ;
/* 131:    */     }
/* 132:148 */     if (emptyAreaLength > this.roomSize + this.roomSize / 2)
/* 133:    */     {
/* 134:151 */       generateSquaredStructure(random, world, x, y, z, sizeX, sizeZ, floors, false, false, addStairs);
/* 135:    */       
/* 136:153 */       int doorOffsetX = side.offsetZ * sizeZ / 2;
/* 137:154 */       int doorOffsetZ = side.offsetX * sizeX / 2;
/* 138:155 */       for (int h = 0; h <= floors; h++) {
/* 139:156 */         this.properties.doors.generate(random, world, x + doorOffsetX, y + 1 + (this.properties.floorHeight + 1) * h, z + doorOffsetZ, side);
/* 140:    */       }
/* 141:157 */       this.properties.doors.generateSquared(random, world, x + doorOffsetX, y + 1 + (this.properties.floorHeight + 1) * (floors + 1), z + doorOffsetZ, side);
/* 142:    */       
/* 143:159 */       emptyAreaLength = 0;
/* 144:160 */       if (side.offsetX != 0) {
/* 145:161 */         emptyAreaLength = maxSizeX - sizeX;
/* 146:162 */       } else if (side.offsetZ != 0) {
/* 147:163 */         emptyAreaLength = maxSizeZ - sizeZ;
/* 148:    */       }
/* 149:164 */       if (side.offsetX < 0) {
/* 150:165 */         x += sizeX;
/* 151:    */       }
/* 152:166 */       if (side.offsetZ < 0) {
/* 153:167 */         z += sizeZ;
/* 154:    */       }
/* 155:169 */       int offsetX = side.offsetX * sizeX;
/* 156:170 */       int offsetZ = side.offsetZ * sizeZ;
/* 157:171 */       if (side.offsetX != 0) {
/* 158:172 */         maxSizeX -= sizeX;
/* 159:174 */       } else if (side.offsetZ != 0) {
/* 160:175 */         maxSizeZ -= sizeZ;
/* 161:    */       }
/* 162:178 */       if (emptyAreaLength > this.roomSize)
/* 163:    */       {
/* 164:180 */         generateStructureAtSide(random, world, x + offsetX, y, z + offsetZ, sizeX, sizeZ, floors, side);
/* 165:    */       }
/* 166:181 */       else if (emptyAreaLength > 5)
/* 167:    */       {
/* 168:182 */         if (side.offsetX != 0) {
/* 169:183 */           maxSizeZ = sizeZ;
/* 170:185 */         } else if (side.offsetZ != 0) {
/* 171:186 */           maxSizeX = sizeX;
/* 172:    */         }
/* 173:188 */         generateTowersAtSide(random, world, x + offsetX, y, z + offsetZ, maxSizeX, maxSizeZ, floors, side);
/* 174:    */       }
/* 175:    */     }
/* 176:191 */     else if (emptyAreaLength > 5)
/* 177:    */     {
/* 178:193 */       generateTowersAtSide(random, world, x, y, z, maxSizeX, maxSizeZ, floors, side);
/* 179:    */     }
/* 180:    */     else {}
/* 181:    */   }
/* 182:    */   
/* 183:    */   public void generateTowersAtSide(Random random, World world, int x, int y, int z, int maxSizeX, int maxSizeZ, int floors, ForgeDirection side)
/* 184:    */   {
/* 185:201 */     int emptyAreaLength = 0;
/* 186:202 */     if (side.offsetX != 0) {
/* 187:203 */       emptyAreaLength = maxSizeX;
/* 188:204 */     } else if (side.offsetZ != 0) {
/* 189:205 */       emptyAreaLength = maxSizeZ;
/* 190:    */     }
/* 191:207 */     int width = 0;
/* 192:208 */     if (side.offsetZ != 0) {
/* 193:209 */       width = maxSizeX;
/* 194:210 */     } else if (side.offsetX != 0) {
/* 195:211 */       width = maxSizeZ;
/* 196:    */     }
/* 197:213 */     if (width < this.roomSize * 2)
/* 198:    */     {
/* 199:215 */       width = Math.max(5, Math.min(emptyAreaLength, width));
/* 200:216 */       TowerSquare tower = random.nextBoolean() ? new TowerSquare(this.properties) : new TowerRound(this.properties);
/* 201:217 */       tower.configure(floors, width);
/* 202:218 */       int offsetX = side.offsetZ * maxSizeX / 2;
/* 203:219 */       int offsetZ = side.offsetX * maxSizeZ / 2;
/* 204:220 */       tower.buildTower(random, world, x + offsetX, y, z + offsetZ, side);
/* 205:    */     }
/* 206:    */     else
/* 207:    */     {
/* 208:223 */       width = Math.max(6, Math.min(emptyAreaLength, width));
/* 209:    */       
/* 210:    */ 
/* 211:226 */       int offsetX = side.offsetZ * (maxSizeX - 3);
/* 212:227 */       int offsetZ = side.offsetX * (maxSizeZ - 3);
/* 213:228 */       TowerSquare tower = random.nextBoolean() ? new TowerSquare(this.properties) : new TowerRound(this.properties);
/* 214:229 */       tower.configure(floors, width);
/* 215:230 */       tower.buildTower(random, world, x + offsetX, y, z + offsetZ, side);
/* 216:    */       
/* 217:232 */       offsetX = side.offsetZ * 3;
/* 218:233 */       offsetZ = side.offsetX * 3;
/* 219:234 */       tower = random.nextBoolean() ? new TowerSquare(this.properties) : new TowerRound(this.properties);
/* 220:235 */       tower.configure(floors, width);
/* 221:236 */       tower.buildTower(random, world, x + offsetX, y, z + offsetZ, side);
/* 222:    */     }
/* 223:    */   }
/* 224:    */   
/* 225:    */   public void generatePagoda(Random random, World world, int x, int y, int z, int sizeX, int sizeZ)
/* 226:    */   {
/* 227:242 */     int stepSize = 4;int stepSize2 = 2;
/* 228:    */     
/* 229:244 */     int chapelFloors = 3;
/* 230:245 */     int xPos = x;
/* 231:246 */     int yPos = y;
/* 232:247 */     int zPos = z;
/* 233:248 */     int floorHeight = this.properties.floorHeight;
/* 234:249 */     boolean boss = true;
/* 235:250 */     for (int i = chapelFloors; i >= 0; i--) {
/* 236:252 */       if ((i <= 1) || ((i * stepSize <= sizeX - this.roomSize) && (i * stepSize <= sizeZ - this.roomSize)))
/* 237:    */       {
/* 238:254 */         int X = xPos + stepSize2 * i;
/* 239:255 */         int Y = yPos + (floorHeight + 1) * i;
/* 240:256 */         int Z = zPos + stepSize2 * i;
/* 241:257 */         int sX = sizeX - stepSize * i;
/* 242:258 */         int sZ = sizeZ - stepSize * i;
/* 243:259 */         generateSquaredStructure(random, world, X, Y, Z, sX, sZ, 0, !boss, boss);
/* 244:260 */         boss = false;
/* 245:261 */         this.properties.roof.roofPyramid(world, X - stepSize2, Y + floorHeight - 1, Z - stepSize2, sX + stepSize, sZ + stepSize, stepSize2);
/* 246:    */       }
/* 247:    */     }
/* 248:    */   }
/* 249:    */   
/* 250:    */   public void generateSquaredStructure(Random random, World world, int x, int y, int z, int sizeX, int sizeZ, int floors, boolean walkableRoof, boolean boss)
/* 251:    */   {
/* 252:267 */     generateSquaredStructure(random, world, x, y, z, sizeX, sizeZ, floors, walkableRoof, boss, true);
/* 253:    */   }
/* 254:    */   
/* 255:    */   public void generateSquaredStructure(Random random, World world, int x, int y, int z, int sizeX, int sizeZ, int floors, boolean walkableRoof, boolean boss, boolean addStairs)
/* 256:    */   {
/* 257:271 */     int roomsX = Math.max(1, sizeX / this.roomSize);
/* 258:272 */     int roomsZ = Math.max(1, sizeZ / this.roomSize);
/* 259:273 */     int roomSizeX = sizeX / roomsX;
/* 260:274 */     int roomSizeZ = sizeZ / roomsZ;
/* 261:275 */     int lastRoomOffsetX = sizeX - roomsX * roomSizeX;
/* 262:276 */     int lastRoomOffsetZ = sizeZ - roomsZ * roomSizeZ;
/* 263:277 */     int currentY = y;
/* 264:278 */     int floorHeight = this.properties.floorHeight;
/* 265:280 */     for (int i = 0; i <= sizeX; i++) {
/* 266:282 */       for (int j = 0; j < sizeZ; j++) {
/* 267:284 */         this.properties.floor.generateFloor(world, x + i, currentY, z + j);
/* 268:    */       }
/* 269:    */     }
/* 270:287 */     for (int currentfloor = 0; currentfloor <= floors; currentfloor++)
/* 271:    */     {
/* 272:289 */       for (int i = 0; i <= sizeX; i++) {
/* 273:291 */         for (int j = 0; j < sizeZ; j++)
/* 274:    */         {
/* 275:293 */           world.setBlock(x + i, currentY + floorHeight, z + j, this.properties.wallBlock.id);
/* 276:294 */           if (currentfloor != floors) {
/* 277:295 */             this.properties.floor.generateFloor(world, x + i, currentY + floorHeight + 1, z + j);
/* 278:    */           } else {
/* 279:297 */             world.setBlock(x + i, currentY + floorHeight + 1, z + j, this.properties.wallBlock.id, 0, 3);
/* 280:    */           }
/* 281:    */         }
/* 282:    */       }
/* 283:300 */       currentY++;
/* 284:302 */       for (int i = 0; i <= sizeX; i++)
/* 285:    */       {
/* 286:304 */         for (int j = -1; j <= floorHeight; j++)
/* 287:    */         {
/* 288:306 */           world.setBlock(x + i, currentY + j, z, this.properties.wallBlock.id);
/* 289:307 */           world.setBlock(x + i, currentY + j, z + sizeZ, this.properties.wallBlock.id);
/* 290:    */         }
/* 291:309 */         if ((i > 0) && (i < sizeX))
/* 292:    */         {
/* 293:311 */           this.properties.window.generateWindowX(world, x + i, currentY, z);
/* 294:312 */           this.properties.window.generateWindowX(world, x + i, currentY, z + sizeZ);
/* 295:    */         }
/* 296:    */       }
/* 297:315 */       for (int i = 0; i <= sizeZ; i++)
/* 298:    */       {
/* 299:317 */         for (int j = -1; j <= floorHeight; j++)
/* 300:    */         {
/* 301:319 */           world.setBlock(x, currentY + j, z + i, this.properties.wallBlock.id);
/* 302:320 */           world.setBlock(x + sizeX, currentY + j, z + i, this.properties.wallBlock.id);
/* 303:    */         }
/* 304:322 */         if ((i > 0) && (i < sizeZ))
/* 305:    */         {
/* 306:324 */           this.properties.window.generateWindowZ(world, x, currentY, z + i);
/* 307:325 */           this.properties.window.generateWindowZ(world, x + sizeX, currentY, z + i);
/* 308:    */         }
/* 309:    */       }
/* 310:329 */       RoomBase[][] roomsArray = new RoomBase[roomsX][roomsZ];
/* 311:330 */       roomsArray = RoomsHelper.getRoomsArray(roomsArray, this.properties, random, floorHeight, roomSizeX, roomSizeZ, addStairs);
/* 312:331 */       for (int i = 0; i < roomsArray.length; i++)
/* 313:    */       {
/* 314:332 */         RoomBase room = roomsArray[i][(roomsArray[0].length - 1)];
/* 315:333 */         if (room != null) {
/* 316:334 */           room.sizeZ += lastRoomOffsetZ;
/* 317:    */         }
/* 318:    */       }
/* 319:337 */       for (int i = 0; i < roomsArray[0].length; i++)
/* 320:    */       {
/* 321:338 */         RoomBase room = roomsArray[(roomsArray.length - 1)][i];
/* 322:339 */         if (room != null) {
/* 323:340 */           room.sizeX += lastRoomOffsetX;
/* 324:    */         }
/* 325:    */       }
/* 326:342 */       if ((currentfloor == floors) && (boss)) {
/* 327:343 */         roomsArray[0][0] = new RoomBoss().copyDataFrom(roomsArray[0][0]);
/* 328:    */       }
/* 329:344 */       RoomsHelper.buildRooms(world, random, roomsArray, x, currentY, z, this.properties);
/* 330:    */       
/* 331:346 */       currentY += floorHeight;
/* 332:    */     }
/* 333:349 */     this.properties.roof.generateRoof(world, x, currentY + 1, z, sizeX, sizeZ, walkableRoof);
/* 334:    */   }
/* 335:    */   
/* 336:    */   public String getName()
/* 337:    */   {
/* 338:354 */     return "castle";
/* 339:    */   }
/* 340:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.BuilderCastle
 * JD-Core Version:    0.7.1
 */