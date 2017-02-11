/*   1:    */ package com.chocolate.chocolateQuest.builder.decorator;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomAlchemy;
/*   4:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomArmory;
/*   5:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomBedRoom;
/*   6:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomBlackSmith;
/*   7:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomDinning;
/*   8:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomEnchantment;
/*   9:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomEndPortal;
/*  10:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomFlag;
/*  11:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomJail;
/*  12:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomKitchen;
/*  13:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomLibrary;
/*  14:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomLibraryBig;
/*  15:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomNetherPortal;
/*  16:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomStairs;
/*  17:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomStorage;
/*  18:    */ import java.util.Random;
/*  19:    */ import net.minecraft.world.World;
/*  20:    */ 
/*  21:    */ public class RoomsHelper
/*  22:    */ {
/*  23:    */   static final int NORTH = 0;
/*  24:    */   static final int EAST = 2;
/*  25:    */   static final int SOUTH = 1;
/*  26:    */   static final int WEST = 3;
/*  27:    */   
/*  28:    */   public static RoomBase getRoom(Random random, int sizeX, int sizeZ, BuildingProperties data)
/*  29:    */   {
/*  30: 30 */     RoomBase room = getRandomRoomType(random);
/*  31: 31 */     room.configure(sizeX, sizeZ, data);
/*  32: 32 */     return room;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public static RoomBase getRandomRoomType(Random random)
/*  36:    */   {
/*  37: 37 */     if (random.nextInt(400) == 0) {
/*  38: 38 */       return new RoomEndPortal();
/*  39:    */     }
/*  40: 39 */     if (random.nextInt(100) == 0) {
/*  41: 40 */       return new RoomNetherPortal();
/*  42:    */     }
/*  43: 43 */     if (random.nextInt(60) == 0) {
/*  44: 44 */       return new RoomLibraryBig();
/*  45:    */     }
/*  46: 45 */     if (random.nextInt(60) == 0) {
/*  47: 46 */       return new RoomAlchemy();
/*  48:    */     }
/*  49: 47 */     if (random.nextInt(60) == 0) {
/*  50: 48 */       return new RoomBlackSmith();
/*  51:    */     }
/*  52: 49 */     if (random.nextInt(60) == 0) {
/*  53: 50 */       return new RoomEnchantment();
/*  54:    */     }
/*  55: 51 */     if (random.nextInt(60) == 0) {
/*  56: 52 */       return new RoomFlag();
/*  57:    */     }
/*  58: 53 */     if (random.nextInt(60) == 0) {
/*  59: 54 */       return new RoomArmory();
/*  60:    */     }
/*  61: 55 */     if (random.nextInt(60) == 0) {
/*  62: 56 */       return new RoomStorage();
/*  63:    */     }
/*  64: 57 */     if (random.nextInt(60) == 0) {
/*  65: 58 */       return new RoomJail();
/*  66:    */     }
/*  67: 59 */     if (random.nextInt(60) == 0) {
/*  68: 60 */       return new RoomDinning();
/*  69:    */     }
/*  70: 62 */     switch (random.nextInt(5))
/*  71:    */     {
/*  72:    */     case 0: 
/*  73: 63 */       return new RoomBedRoom();
/*  74:    */     case 1: 
/*  75: 64 */       return new RoomKitchen();
/*  76:    */     case 2: 
/*  77: 65 */       return new RoomLibrary();
/*  78:    */     }
/*  79: 68 */     return new RoomBase();
/*  80:    */   }
/*  81:    */   
/*  82:    */   public static void buildRooms(World world, Random random, RoomBase[][] rooms, int x, int y, int z, BuildingProperties props)
/*  83:    */   {
/*  84: 73 */     int roomsX = rooms.length;
/*  85: 74 */     int roomsZ = rooms[0].length;
/*  86: 75 */     int roomSizeX = rooms[0][0].sizeX;
/*  87: 76 */     int roomSizeZ = rooms[0][0].sizeZ;
/*  88: 77 */     int floorHeight = props.floorHeight;
/*  89: 78 */     for (int currentRoomX = 0; currentRoomX < roomsX; currentRoomX++) {
/*  90: 80 */       for (int currentRoomZ = 0; currentRoomZ < roomsZ; currentRoomZ++)
/*  91:    */       {
/*  92: 82 */         int posX = currentRoomX * roomSizeX + x;
/*  93: 83 */         int posZ = currentRoomZ * roomSizeZ + z;
/*  94: 84 */         RoomBase room = rooms[currentRoomX][currentRoomZ];
/*  95: 85 */         if (room != null) {
/*  96: 86 */           room.decorate(random, world, posX, y, posZ);
/*  97:    */         }
/*  98:    */       }
/*  99:    */     }
/* 100:    */   }
/* 101:    */   
/* 102:    */   public static RoomBase[][] getRoomsArray(RoomBase[][] rooms, BuildingProperties data, Random random, int height, int sizeX, int sizeZ, boolean addStairs)
/* 103:    */   {
/* 104: 94 */     int roomsX = rooms.length;
/* 105: 95 */     int roomsZ = rooms[0].length;
/* 106: 97 */     for (int i = 0; i < roomsX; i++) {
/* 107: 99 */       for (int k = 0; k < roomsZ; k++)
/* 108:    */       {
/* 109:101 */         rooms[i][k] = getRoom(random, sizeX, sizeZ, data);
/* 110:102 */         if (i == 0) {
/* 111:104 */           rooms[i][k].wallEast = false;
/* 112:    */         }
/* 113:106 */         if (i == roomsX - 1) {
/* 114:108 */           rooms[i][k].wallWest = false;
/* 115:    */         }
/* 116:110 */         if (k == 0) {
/* 117:112 */           rooms[i][k].wallNorth = false;
/* 118:    */         }
/* 119:114 */         if (k == roomsZ - 1) {
/* 120:116 */           rooms[i][k].wallSouth = false;
/* 121:    */         }
/* 122:    */       }
/* 123:    */     }
/* 124:121 */     for (int i = 0; i < roomsX; i++) {
/* 125:123 */       for (int k = 0; k < roomsZ; k++)
/* 126:    */       {
/* 127:125 */         if ((i > 0) && 
/* 128:126 */           (rooms[i][k].getType() == rooms[(i - 1)][k].getType()))
/* 129:    */         {
/* 130:127 */           rooms[i][k].decorateEast = false;
/* 131:128 */           rooms[i][k].wallEast = false;
/* 132:    */         }
/* 133:131 */         if ((i < roomsX - 1) && 
/* 134:132 */           (rooms[i][k].getType() == rooms[(i + 1)][k].getType()))
/* 135:    */         {
/* 136:133 */           rooms[i][k].decorateWest = false;
/* 137:134 */           rooms[i][k].wallWest = false;
/* 138:    */         }
/* 139:137 */         if ((k > 0) && 
/* 140:138 */           (rooms[i][k].getType() == rooms[i][(k - 1)].getType()))
/* 141:    */         {
/* 142:139 */           rooms[i][k].decorateNorth = false;
/* 143:140 */           rooms[i][k].wallNorth = false;
/* 144:    */         }
/* 145:143 */         if ((k < roomsZ - 1) && 
/* 146:144 */           (rooms[i][k].getType() == rooms[i][(k + 1)].getType()))
/* 147:    */         {
/* 148:145 */           rooms[i][k].decorateSouth = false;
/* 149:146 */           rooms[i][k].wallSouth = false;
/* 150:    */         }
/* 151:    */       }
/* 152:    */     }
/* 153:151 */     addDoorToRoom(rooms, 0, 0, random);
/* 154:    */     
/* 155:153 */     int x = random.nextInt(roomsX);
/* 156:154 */     int z = random.nextInt(roomsZ);
/* 157:155 */     if (addStairs) {
/* 158:156 */       rooms[x][z] = new RoomStairs().copyDataFrom(rooms[x][z]);
/* 159:    */     }
/* 160:158 */     return rooms;
/* 161:    */   }
/* 162:    */   
/* 163:    */   public static boolean addDoorToRoom(RoomBase[][] rooms, int x, int z, Random random)
/* 164:    */   {
/* 165:165 */     int side = random.nextInt(4);
/* 166:166 */     int rotation = random.nextBoolean() ? 1 : -1;
/* 167:167 */     rooms[x][z].doorSet = true;
/* 168:    */     
/* 169:169 */     int cont = 0;
/* 170:170 */     while (cont < 4)
/* 171:    */     {
/* 172:172 */       if (addDoorToSide(rooms, x, z, side))
/* 173:    */       {
/* 174:174 */         int nx = x;int nz = z;
/* 175:175 */         if (side == 0)
/* 176:    */         {
/* 177:177 */           nz = z - 1;
/* 178:178 */           rooms[x][z].doorNorth = true;
/* 179:179 */           rooms[x][(z - 1)].doorSouth = true;
/* 180:    */         }
/* 181:180 */         else if (side == 1)
/* 182:    */         {
/* 183:182 */           nz = z + 1;
/* 184:183 */           rooms[x][z].doorSouth = true;
/* 185:184 */           rooms[x][(z + 1)].doorNorth = true;
/* 186:    */         }
/* 187:185 */         else if (side == 2)
/* 188:    */         {
/* 189:187 */           nx = x - 1;
/* 190:188 */           rooms[x][z].doorEast = true;
/* 191:189 */           rooms[(x - 1)][z].doorWest = true;
/* 192:    */         }
/* 193:190 */         else if (side == 3)
/* 194:    */         {
/* 195:192 */           nx = x + 1;
/* 196:193 */           rooms[x][z].doorWest = true;
/* 197:194 */           rooms[(x + 1)][z].doorEast = true;
/* 198:    */         }
/* 199:196 */         addDoorToRoom(rooms, nx, nz, random);
/* 200:    */       }
/* 201:198 */       side += rotation;
/* 202:199 */       if (side < 0) {
/* 203:200 */         side = 3;
/* 204:    */       }
/* 205:201 */       if (side > 3) {
/* 206:202 */         side = 0;
/* 207:    */       }
/* 208:203 */       cont++;
/* 209:    */     }
/* 210:205 */     return true;
/* 211:    */   }
/* 212:    */   
/* 213:    */   public static boolean addDoorToSide(RoomBase[][] rooms, int x, int z, int side)
/* 214:    */   {
/* 215:212 */     if (((z == 0) && (side == 0)) || ((z == rooms[0].length - 1) && (side == 1)) || ((x == 0) && (side == 2)) || ((x == rooms.length - 1) && (side == 3))) {
/* 216:214 */       return false;
/* 217:    */     }
/* 218:215 */     int nx = x;int nz = z;
/* 219:216 */     if (side == 0) {
/* 220:218 */       nz = z - 1;
/* 221:219 */     } else if (side == 1) {
/* 222:221 */       nz = z + 1;
/* 223:222 */     } else if (side == 2) {
/* 224:224 */       nx = x - 1;
/* 225:225 */     } else if (side == 3) {
/* 226:227 */       nx = x + 1;
/* 227:    */     }
/* 228:229 */     return !rooms[nx][nz].doorSet;
/* 229:    */   }
/* 230:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.RoomsHelper
 * JD-Core Version:    0.7.1
 */