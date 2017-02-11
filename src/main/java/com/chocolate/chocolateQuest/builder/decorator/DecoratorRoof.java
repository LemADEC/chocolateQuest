/*   1:    */ package com.chocolate.chocolateQuest.builder.decorator;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.BuilderBlockData;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.block.Block;
/*   6:    */ import net.minecraft.init.Blocks;
/*   7:    */ import net.minecraft.world.World;
/*   8:    */ 
/*   9:    */ public class DecoratorRoof
/*  10:    */ {
/*  11: 13 */   public final int FENCE_IRON_FLOOR = 2;
/*  12: 13 */   public final int TRIANGLE = 5;
/*  13: 13 */   public final int PYRAMID = 0;
/*  14: 14 */   public int floorType = 0;
/*  15: 15 */   public BuilderBlockData block = new BuilderBlockData(Blocks.stonebrick);
/*  16: 16 */   public BuilderBlockData fence = new BuilderBlockData(Blocks.fence);
/*  17: 18 */   public Block stairs = Blocks.birch_stairs;
/*  18: 19 */   public Block roofBlock = Blocks.brick_block;
/*  19: 20 */   int texasType = 0;
/*  20: 21 */   int walkableType = 0;
/*  21:    */   Random random;
/*  22:    */   
/*  23:    */   public DecoratorRoof(Random random)
/*  24:    */   {
/*  25: 25 */     this.random = random;
/*  26: 26 */     this.floorType = random.nextInt(6);
/*  27: 27 */     this.texasType = random.nextInt(2);
/*  28: 28 */     this.walkableType = random.nextInt(4);
/*  29: 29 */     int i = random.nextInt(9);
/*  30: 30 */     switch (i)
/*  31:    */     {
/*  32:    */     case 0: 
/*  33: 32 */       this.stairs = Blocks.birch_stairs;
/*  34: 33 */       this.roofBlock = Blocks.brick_block;
/*  35: 34 */       break;
/*  36:    */     case 1: 
/*  37: 36 */       this.stairs = Blocks.jungle_stairs;
/*  38: 37 */       this.roofBlock = Blocks.planks;
/*  39: 38 */       break;
/*  40:    */     case 2: 
/*  41: 40 */       this.stairs = Blocks.oak_stairs;
/*  42: 41 */       this.roofBlock = Blocks.planks;
/*  43: 42 */       break;
/*  44:    */     case 3: 
/*  45: 44 */       this.stairs = Blocks.birch_stairs;
/*  46: 45 */       this.roofBlock = Blocks.planks;
/*  47: 46 */       break;
/*  48:    */     case 4: 
/*  49: 48 */       this.stairs = Blocks.spruce_stairs;
/*  50: 49 */       this.roofBlock = Blocks.planks;
/*  51: 50 */       break;
/*  52:    */     case 5: 
/*  53: 52 */       this.stairs = Blocks.stone_stairs;
/*  54: 53 */       this.roofBlock = Blocks.cobblestone;
/*  55: 54 */       break;
/*  56:    */     case 6: 
/*  57: 56 */       this.stairs = Blocks.brick_stairs;
/*  58: 57 */       this.roofBlock = Blocks.brick_block;
/*  59: 58 */       break;
/*  60:    */     case 7: 
/*  61: 60 */       this.stairs = Blocks.sandstone_stairs;
/*  62: 61 */       this.roofBlock = Blocks.sandstone;
/*  63: 62 */       break;
/*  64:    */     case 8: 
/*  65: 64 */       this.stairs = Blocks.stone_brick_stairs;
/*  66: 65 */       this.roofBlock = Blocks.stonebrick;
/*  67:    */     }
/*  68: 68 */     if (random.nextInt(100) == 0)
/*  69:    */     {
/*  70: 69 */       this.stairs = Blocks.quartz_stairs;
/*  71: 70 */       this.roofBlock = Blocks.quartz_block;
/*  72:    */     }
/*  73: 73 */     if (this.floorType == 2) {
/*  74: 74 */       this.fence = new BuilderBlockData(Blocks.iron_bars);
/*  75:    */     }
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void generateRoof(World world, int x, int y, int z, int sizeX, int sizeZ, boolean walkable)
/*  79:    */   {
/*  80: 79 */     if (!walkable) {
/*  81: 81 */       if (this.random.nextBoolean())
/*  82:    */       {
/*  83: 83 */         if (this.texasType == 0) {
/*  84: 84 */           roofPyramid(world, x, y, z, sizeX, sizeZ, 1000);
/*  85:    */         } else {
/*  86: 86 */           roofTriangle(world, x, y, z, sizeX, sizeZ);
/*  87:    */         }
/*  88: 87 */         return;
/*  89:    */       }
/*  90:    */     }
/*  91: 90 */     switch (this.floorType)
/*  92:    */     {
/*  93:    */     case 1: 
/*  94: 92 */       fence(world, x, y, z, sizeX, sizeZ, Blocks.fence);
/*  95: 93 */       break;
/*  96:    */     case 2: 
/*  97: 95 */       fence(world, x, y, z, sizeX, sizeZ, Blocks.iron_bars);
/*  98: 96 */       break;
/*  99:    */     case 3: 
/* 100: 98 */       roofDoubleSlabsDecorator(world, x, y, z, sizeX, sizeZ, 2);
/* 101: 99 */       break;
/* 102:    */     case 4: 
/* 103:101 */       roofDoubleSlabsDecorator(world, x, y, z, sizeX, sizeZ, 1);
/* 104:102 */       break;
/* 105:    */     case 5: 
/* 106:103 */       if (!walkable) {
/* 107:104 */         roofTriangle(world, x, y, z, sizeX, sizeZ);
/* 108:    */       } else {
/* 109:106 */         addClearRoof(world, x, y, z, sizeX, sizeZ);
/* 110:    */       }
/* 111:107 */       break;
/* 112:    */     default: 
/* 113:109 */       if (!walkable) {
/* 114:110 */         roofPyramid(world, x, y, z, sizeX, sizeZ, 1000);
/* 115:    */       } else {
/* 116:112 */         addClearRoof(world, x, y, z, sizeX, sizeZ);
/* 117:    */       }
/* 118:    */       break;
/* 119:    */     }
/* 120:    */   }
/* 121:    */   
/* 122:    */   public void setRoofBlock(World world, int x, int y, int z)
/* 123:    */   {
/* 124:119 */     if ((this.floorType == 1) || (this.floorType == 2))
/* 125:    */     {
/* 126:121 */       world.setBlock(x, y, z, this.fence.id);
/* 127:    */     }
/* 128:    */     else
/* 129:    */     {
/* 130:125 */       int ax = Math.abs(x);
/* 131:126 */       int az = Math.abs(z);
/* 132:127 */       if (ax % 2 == az % 2) {
/* 133:128 */         world.setBlock(x, y, z, this.block.id);
/* 134:    */       }
/* 135:    */     }
/* 136:    */   }
/* 137:    */   
/* 138:    */   private void addClearRoof(World world, int x, int y, int z, int sizeX, int sizeZ)
/* 139:    */   {
/* 140:135 */     switch (this.walkableType)
/* 141:    */     {
/* 142:    */     case 1: 
/* 143:137 */       fence(world, x, y, z, sizeX, sizeZ, Blocks.fence);
/* 144:138 */       break;
/* 145:    */     case 2: 
/* 146:140 */       fence(world, x, y, z, sizeX, sizeZ, Blocks.iron_bars);
/* 147:141 */       break;
/* 148:    */     case 3: 
/* 149:143 */       roofDoubleSlabsDecorator(world, x, y, z, sizeX, sizeZ, 2);
/* 150:144 */       break;
/* 151:    */     default: 
/* 152:146 */       roofDoubleSlabsDecorator(world, x, y, z, sizeX, sizeZ, 1);
/* 153:    */     }
/* 154:    */   }
/* 155:    */   
/* 156:    */   private void fence(World world, int x, int y, int z, int sizeX, int sizeZ, Block block)
/* 157:    */   {
/* 158:153 */     for (int i = 0; i <= sizeX; i++)
/* 159:    */     {
/* 160:155 */       world.setBlock(x + i, y, z, block);
/* 161:156 */       world.setBlock(x + i, y, z + sizeZ, block);
/* 162:    */     }
/* 163:158 */     for (int k = 0; k <= sizeZ; k++)
/* 164:    */     {
/* 165:160 */       world.setBlock(x, y, z + k, block);
/* 166:161 */       world.setBlock(x + sizeX, y, z + k, block);
/* 167:    */     }
/* 168:    */   }
/* 169:    */   
/* 170:    */   public void roofPyramid(World world, int x, int y, int z, int sizeX, int sizeZ, int height)
/* 171:    */   {
/* 172:169 */     int minSize = Math.min(sizeX, sizeZ) / 2 + 1;
/* 173:    */     
/* 174:171 */     minSize = Math.min(height, minSize);
/* 175:    */     
/* 176:173 */     minSize -= 1 - minSize % 2;
/* 177:174 */     for (int j = 0; j <= minSize; j++)
/* 178:    */     {
/* 179:176 */       world.setBlock(x + j - 1, y + j, z + j - 1, this.stairs, 2, 3);
/* 180:177 */       world.setBlock(x + sizeX - j + 1, y + j, z + j - 1, this.stairs, 2, 3);
/* 181:178 */       world.setBlock(x + j - 1, y + j, z + sizeZ - j + 1, this.stairs, 3, 3);
/* 182:179 */       world.setBlock(x + sizeX - j + 1, y + j, z + sizeZ - j + 1, this.stairs, 3, 3);
/* 183:180 */       for (int i = j; i <= sizeX - j; i++)
/* 184:    */       {
/* 185:182 */         world.setBlock(x + i, y + j, z + j, this.block.id);
/* 186:183 */         world.setBlock(x + i, y + j, z + j - 1, this.stairs, 2, 3);
/* 187:    */         
/* 188:185 */         world.setBlock(x + i, y + j, z + sizeZ - j, this.block.id);
/* 189:186 */         world.setBlock(x + i, y + j, z + sizeZ - j + 1, this.stairs, 3, 3);
/* 190:    */       }
/* 191:188 */       for (int k = j; k <= sizeZ - j; k++)
/* 192:    */       {
/* 193:190 */         world.setBlock(x + j, y + j, z + k, this.block.id);
/* 194:191 */         world.setBlock(x + j - 1, y + j, z + k, this.stairs);
/* 195:    */         
/* 196:193 */         world.setBlock(x + sizeX - j, y + j, z + k, this.block.id);
/* 197:194 */         world.setBlock(x + sizeX - j + 1, y + j, z + k, this.stairs, 1, 3);
/* 198:    */       }
/* 199:    */     }
/* 200:    */   }
/* 201:    */   
/* 202:    */   private void roofTriangle(World world, int x, int y, int z, int sizeX, int sizeZ)
/* 203:    */   {
/* 204:200 */     if (sizeX < sizeZ)
/* 205:    */     {
/* 206:202 */       int minSize = sizeX / 2;
/* 207:204 */       for (int j = 0; j <= minSize; j++)
/* 208:    */       {
/* 209:206 */         for (int k = 0; k <= sizeZ; k++)
/* 210:    */         {
/* 211:208 */           world.setBlock(x + j, y + j, z + k, this.stairs, 0, 3);
/* 212:209 */           world.setBlock(x + sizeX - j, y + j, z + k, this.stairs, 1, 3);
/* 213:    */         }
/* 214:211 */         for (int k = j + 1; k < sizeX - j; k++)
/* 215:    */         {
/* 216:213 */           world.setBlock(x + k, y + j, z, this.block.id);
/* 217:214 */           world.setBlock(x + k, y + j, z + sizeZ, this.block.id);
/* 218:    */         }
/* 219:    */       }
/* 220:    */     }
/* 221:    */     else
/* 222:    */     {
/* 223:219 */       int minSize = sizeZ / 2;
/* 224:221 */       for (int j = 0; j <= minSize; j++)
/* 225:    */       {
/* 226:223 */         for (int k = 0; k <= sizeX; k++)
/* 227:    */         {
/* 228:225 */           world.setBlock(x + k, y + j, z + j, this.stairs, 2, 3);
/* 229:226 */           world.setBlock(x + k, y + j, z + sizeZ - j, this.stairs, 3, 3);
/* 230:    */         }
/* 231:228 */         for (int k = j + 1; k < sizeZ - j; k++)
/* 232:    */         {
/* 233:230 */           world.setBlock(x, y + j, z + k, this.block.id);
/* 234:231 */           world.setBlock(x + sizeX, y + j, z + k, this.block.id);
/* 235:    */         }
/* 236:    */       }
/* 237:    */     }
/* 238:    */   }
/* 239:    */   
/* 240:    */   private void roofDoubleSlabsDecorator(World world, int x, int y, int z, int sizeX, int sizeZ, int width)
/* 241:    */   {
/* 242:238 */     for (int i = 0; i < sizeX; i += width * 2) {
/* 243:240 */       for (int w = 0; w < width; w++)
/* 244:    */       {
/* 245:242 */         world.setBlock(x + i + w, y, z, this.block.id);
/* 246:243 */         world.setBlock(x + i + w, y, z + sizeZ, this.block.id);
/* 247:    */       }
/* 248:    */     }
/* 249:246 */     for (int k = 0; k <= sizeZ; k += width * 2) {
/* 250:248 */       for (int w = 0; w < width; w++)
/* 251:    */       {
/* 252:250 */         world.setBlock(x, y, z + k + w, this.block.id);
/* 253:251 */         world.setBlock(x + sizeX, y, z + k + w, this.block.id);
/* 254:    */       }
/* 255:    */     }
/* 256:    */   }
/* 257:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.DecoratorRoof
 * JD-Core Version:    0.7.1
 */