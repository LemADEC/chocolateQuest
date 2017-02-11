/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.API.BuilderBlockData;
/*  4:   */ import com.chocolate.chocolateQuest.builder.decorator.BuildingProperties;
/*  5:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  6:   */ import java.util.Random;
/*  7:   */ import net.minecraft.block.Block;
/*  8:   */ import net.minecraft.init.Blocks;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class RoomStairs
/* 12:   */   extends RoomBase
/* 13:   */ {
/* 14:   */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 15:   */   {
/* 16:14 */     int widthStairs = Math.max(2, Math.min(this.sizeX, this.sizeZ) / 4);
/* 17:15 */     int roomHeight = this.properties.floorHeight;
/* 18:16 */     if (side == 5)
/* 19:   */     {
/* 20:18 */       for (int j = 0; j <= roomHeight; j++)
/* 21:   */       {
/* 22:20 */         for (int i = -widthStairs + 1; i < widthStairs; i++) {
/* 23:22 */           for (int k = -widthStairs + 1; k < widthStairs; k++) {
/* 24:24 */             world.setBlockToAir(x + i, y + j, z + k);
/* 25:   */           }
/* 26:   */         }
/* 27:27 */         world.setBlock(x, y + j, z, this.properties.wallBlock.id);
/* 28:   */       }
/* 29:29 */       Block stairBlock = Blocks.stone_brick_stairs;
/* 30:30 */       for (int j = 0; j <= roomHeight; j++)
/* 31:   */       {
/* 32:32 */         int step = 1;
/* 33:33 */         int direction = (y + j) % 4;
/* 34:34 */         if (direction == 0) {
/* 35:36 */           for (step = 1; step < widthStairs; step++)
/* 36:   */           {
/* 37:37 */             world.setBlock(x, y + j, z + step, stairBlock, 0, 3);
/* 38:38 */             for (int step1 = 1; step1 < widthStairs; step1++) {
/* 39:39 */               world.setBlock(x + step, y + j, z + step1, this.properties.wallBlock.id);
/* 40:   */             }
/* 41:   */           }
/* 42:   */         }
/* 43:43 */         if (direction == 1) {
/* 44:45 */           for (step = 1; step < widthStairs; step++)
/* 45:   */           {
/* 46:46 */             world.setBlock(x + step, y + j, z, stairBlock, 3, 3);
/* 47:47 */             for (int step1 = 1; step1 < widthStairs; step1++) {
/* 48:48 */               world.setBlock(x + step1, y + j, z - step, this.properties.wallBlock.id);
/* 49:   */             }
/* 50:   */           }
/* 51:   */         }
/* 52:51 */         if (direction == 2) {
/* 53:53 */           for (step = 1; step < widthStairs; step++)
/* 54:   */           {
/* 55:54 */             world.setBlock(x, y + j, z - step, stairBlock, 1, 3);
/* 56:55 */             for (int step1 = 1; step1 < widthStairs; step1++) {
/* 57:56 */               world.setBlock(x - step, y + j, z - step1, this.properties.wallBlock.id);
/* 58:   */             }
/* 59:   */           }
/* 60:   */         }
/* 61:59 */         if (direction == 3) {
/* 62:61 */           for (step = 1; step < widthStairs; step++)
/* 63:   */           {
/* 64:62 */             world.setBlock(x - step, y + j, z, stairBlock, 2, 3);
/* 65:63 */             for (int step1 = 1; step1 < widthStairs; step1++) {
/* 66:64 */               world.setBlock(x - step1, y + j, z + step, this.properties.wallBlock.id);
/* 67:   */             }
/* 68:   */           }
/* 69:   */         }
/* 70:   */       }
/* 71:   */     }
/* 72:   */     else
/* 73:   */     {
/* 74:70 */       decorateFullMonsterRoom(random, world, x, y, z, side);
/* 75:   */     }
/* 76:   */   }
/* 77:   */   
/* 78:   */   public int getType()
/* 79:   */   {
/* 80:74 */     return 202;
/* 81:   */   }
/* 82:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomStairs
 * JD-Core Version:    0.7.1
 */