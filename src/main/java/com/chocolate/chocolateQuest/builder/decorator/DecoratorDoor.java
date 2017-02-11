/*   1:    */ package com.chocolate.chocolateQuest.builder.decorator;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.block.Block;
/*   5:    */ import net.minecraft.init.Blocks;
/*   6:    */ import net.minecraft.world.World;
/*   7:    */ import net.minecraftforge.common.util.ForgeDirection;
/*   8:    */ 
/*   9:    */ public class DecoratorDoor
/*  10:    */ {
/*  11: 13 */   public int height = 3;
/*  12: 13 */   public int width = 2;
/*  13: 15 */   int type = 0;
/*  14: 17 */   final Block BLOCK_FRAME = Blocks.brick_block;
/*  15:    */   public static final int WEST = 0;
/*  16:    */   public static final int SOUTH = 1;
/*  17:    */   public static final int EAST = 2;
/*  18:    */   public static final int NORTH = 3;
/*  19:    */   
/*  20:    */   public DecoratorDoor(int width, int height)
/*  21:    */   {
/*  22: 19 */     this.width = width;
/*  23: 20 */     this.height = height;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public void setRandomType(Random random)
/*  27:    */   {
/*  28: 25 */     this.type = random.nextInt(4);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void generate(Random random, World world, int x, int y, int z, ForgeDirection side)
/*  32:    */   {
/*  33: 30 */     if (this.width == 1)
/*  34:    */     {
/*  35: 31 */       generateSquared(random, world, x, y, z, side);
/*  36: 32 */       return;
/*  37:    */     }
/*  38: 34 */     switch (this.type)
/*  39:    */     {
/*  40:    */     case 0: 
/*  41: 36 */       generateSquared(random, world, x, y, z, side);
/*  42: 37 */       break;
/*  43:    */     case 1: 
/*  44: 39 */       generateArch(random, world, x, y, z, side);
/*  45: 40 */       break;
/*  46:    */     case 2: 
/*  47: 42 */       generateTriangle(random, world, x, y, z, side);
/*  48: 43 */       break;
/*  49:    */     case 3: 
/*  50: 45 */       generateFramed(random, world, x, y, z, side);
/*  51: 46 */       break;
/*  52:    */     default: 
/*  53: 49 */       generateSquared(random, world, x, y, z, side);
/*  54:    */     }
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void generateArch(Random random, World world, int x, int y, int z, ForgeDirection side)
/*  58:    */   {
/*  59: 56 */     int width = this.width / 2;
/*  60: 57 */     int uwidth = width - 1 + this.width % 2;
/*  61: 58 */     int archHeight = Math.max(1, this.height - width);
/*  62: 59 */     for (int k = -width; k <= uwidth; k++) {
/*  63: 61 */       for (int e = -1; e <= 1; e++)
/*  64:    */       {
/*  65: 63 */         if (k < 0) {
/*  66: 65 */           if (k % 2 != 0) {
/*  67: 66 */             archHeight++;
/*  68:    */           }
/*  69:    */         }
/*  70: 68 */         for (int j = 0; j <= this.height; j++)
/*  71:    */         {
/*  72: 70 */           int xPos = 0;
/*  73: 71 */           int zPos = 0;
/*  74: 73 */           if ((side == ForgeDirection.WEST) || (side == ForgeDirection.EAST))
/*  75:    */           {
/*  76: 75 */             xPos = x + e;
/*  77: 76 */             zPos = z + k;
/*  78:    */           }
/*  79:    */           else
/*  80:    */           {
/*  81: 80 */             xPos = x + k;
/*  82: 81 */             zPos = z + e;
/*  83:    */           }
/*  84: 83 */           if (j <= archHeight) {
/*  85: 84 */             setBlockToAir(world, xPos, y + j, zPos);
/*  86:    */           }
/*  87:    */         }
/*  88: 86 */         if (k > 0) {
/*  89: 88 */           if (k % 2 != 0) {
/*  90: 89 */             archHeight--;
/*  91:    */           }
/*  92:    */         }
/*  93:    */       }
/*  94:    */     }
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void generateTriangle(Random random, World world, int x, int y, int z, ForgeDirection side)
/*  98:    */   {
/*  99: 96 */     int width = this.width / 2;
/* 100: 97 */     int uwidth = width + this.width % 2;
/* 101: 98 */     int archHeight = Math.max(1, this.height - width);
/* 102:100 */     for (int e = -1; e <= 1; e++) {
/* 103:102 */       for (int k = -width; k <= uwidth; k++)
/* 104:    */       {
/* 105:104 */         for (int j = 0; j <= this.height; j++)
/* 106:    */         {
/* 107:106 */           int xPos = 0;
/* 108:107 */           int zPos = 0;
/* 109:109 */           if ((side == ForgeDirection.WEST) || (side == ForgeDirection.EAST))
/* 110:    */           {
/* 111:111 */             xPos = x + e;
/* 112:112 */             zPos = z + k;
/* 113:    */           }
/* 114:    */           else
/* 115:    */           {
/* 116:116 */             xPos = x + k;
/* 117:117 */             zPos = z + e;
/* 118:    */           }
/* 119:119 */           if (j <= archHeight) {
/* 120:120 */             setBlockToAir(world, xPos, y + j, zPos);
/* 121:    */           }
/* 122:    */         }
/* 123:122 */         if (k < 0) {
/* 124:123 */           archHeight++;
/* 125:    */         } else {
/* 126:125 */           archHeight--;
/* 127:    */         }
/* 128:    */       }
/* 129:    */     }
/* 130:    */   }
/* 131:    */   
/* 132:    */   public void generateSquared(Random random, World world, int x, int y, int z, ForgeDirection side)
/* 133:    */   {
/* 134:131 */     int width = this.width / 2;
/* 135:132 */     int uwidth = width - 1 + this.width % 2;
/* 136:133 */     for (int e = -1; e <= 1; e++) {
/* 137:135 */       for (int k = -width; k <= uwidth; k++) {
/* 138:137 */         for (int j = 0; j <= this.height; j++)
/* 139:    */         {
/* 140:139 */           int xPos = 0;
/* 141:140 */           int zPos = 0;
/* 142:142 */           if ((side == ForgeDirection.WEST) || (side == ForgeDirection.EAST))
/* 143:    */           {
/* 144:144 */             xPos = x + e;
/* 145:145 */             zPos = z + k;
/* 146:    */           }
/* 147:    */           else
/* 148:    */           {
/* 149:149 */             xPos = x + k;
/* 150:150 */             zPos = z + e;
/* 151:    */           }
/* 152:152 */           setBlockToAir(world, xPos, y + j, zPos);
/* 153:    */         }
/* 154:    */       }
/* 155:    */     }
/* 156:156 */     if (this.width == 1)
/* 157:    */     {
/* 158:158 */       int doorSide = 0;
/* 159:159 */       if (side == ForgeDirection.SOUTH) {
/* 160:160 */         doorSide = 1;
/* 161:161 */       } else if (side == ForgeDirection.EAST) {
/* 162:162 */         doorSide = 2;
/* 163:163 */       } else if (side == ForgeDirection.NORTH) {
/* 164:164 */         doorSide = 3;
/* 165:    */       }
/* 166:165 */       int orientation = doorSide - 1;
/* 167:166 */       boolean open = false;
/* 168:168 */       if (open)
/* 169:    */       {
/* 170:170 */         world.setBlock(x, y, z, Blocks.wooden_door, orientation, 2);
/* 171:171 */         world.setBlock(x, y + 1, z, Blocks.wooden_door, orientation ^ 0x8, 2);
/* 172:    */       }
/* 173:    */       else
/* 174:    */       {
/* 175:175 */         world.setBlock(x, y, z, Blocks.wooden_door, orientation ^ 0x4, 2);
/* 176:176 */         world.setBlock(x, y + 1, z, Blocks.wooden_door, orientation ^ 0x4 ^ 0x8, 2);
/* 177:    */       }
/* 178:    */     }
/* 179:    */   }
/* 180:    */   
/* 181:    */   public void generateFramed(Random random, World world, int x, int y, int z, ForgeDirection side)
/* 182:    */   {
/* 183:182 */     int width = this.width / 2;
/* 184:183 */     int uwidth = width + this.width % 2;
/* 185:184 */     for (int e = -1; e <= 1; e++) {
/* 186:186 */       for (int k = -width - 1; k <= uwidth + 1; k++) {
/* 187:188 */         for (int j = 0; j <= this.height; j++)
/* 188:    */         {
/* 189:190 */           int xPos = 0;
/* 190:191 */           int zPos = 0;
/* 191:193 */           if ((side == ForgeDirection.WEST) || (side == ForgeDirection.EAST))
/* 192:    */           {
/* 193:195 */             xPos = x + e;
/* 194:196 */             zPos = z + k;
/* 195:    */           }
/* 196:    */           else
/* 197:    */           {
/* 198:200 */             xPos = x + k;
/* 199:201 */             zPos = z + e;
/* 200:    */           }
/* 201:203 */           if ((j == this.height) || (k < -width) || (k > width))
/* 202:    */           {
/* 203:205 */             if (e == 0) {
/* 204:206 */               world.setBlock(xPos, y + j, zPos, this.BLOCK_FRAME);
/* 205:    */             }
/* 206:    */           }
/* 207:    */           else {
/* 208:209 */             setBlockToAir(world, xPos, y + j, zPos);
/* 209:    */           }
/* 210:    */         }
/* 211:    */       }
/* 212:    */     }
/* 213:    */   }
/* 214:    */   
/* 215:    */   private void setBlockToAir(World world, int x, int y, int z)
/* 216:    */   {
/* 217:218 */     world.setBlock(x, y, z, Blocks.air);
/* 218:    */   }
/* 219:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.DecoratorDoor
 * JD-Core Version:    0.7.1
 */