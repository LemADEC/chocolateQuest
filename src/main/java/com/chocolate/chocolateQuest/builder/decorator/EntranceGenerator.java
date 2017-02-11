/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator;
/*  2:   */ 
/*  3:   */ import net.minecraft.init.Blocks;
/*  4:   */ import net.minecraft.world.World;
/*  5:   */ import net.minecraftforge.common.util.ForgeDirection;
/*  6:   */ 
/*  7:   */ public class EntranceGenerator
/*  8:   */ {
/*  9:   */   private BuildingProperties properties;
/* 10:   */   
/* 11:   */   public EntranceGenerator(BuildingProperties properties)
/* 12:   */   {
/* 13:12 */     this.properties = properties;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public void generateEntance(World world, int xCenter, int yCenter, int zCenter, ForgeDirection direction)
/* 17:   */   {
/* 18:16 */     int dirX = direction.offsetX;
/* 19:17 */     int dirZ = direction.offsetZ;
/* 20:18 */     int width = 6;
/* 21:19 */     int height = 5;
/* 22:20 */     int length = 2;
/* 23:21 */     xCenter -= width / 2 * dirZ;
/* 24:22 */     zCenter -= width / 2 * dirX;
/* 25:23 */     for (int w = 0; w < width; w++) {
/* 26:24 */       for (int l = -2; l < length; l++) {
/* 27:25 */         for (int h = 0; h < height; h++)
/* 28:   */         {
/* 29:26 */           int x = xCenter + w * dirZ + l * dirX;
/* 30:27 */           int y = yCenter + h;
/* 31:28 */           int z = zCenter + w * dirX + l * dirZ;
/* 32:29 */           if (h == 0) {
/* 33:30 */             this.properties.floor.generateFloor(world, x, y, z);
/* 34:32 */           } else if (l < 0) {
/* 35:33 */             world.setBlockToAir(x, y, z);
/* 36:35 */           } else if (h == height - 1) {
/* 37:36 */             this.properties.setWallBlock(world, x, y, z);
/* 38:38 */           } else if ((w == 0) || (w == width - 1)) {
/* 39:39 */             world.setBlock(x, y, z, Blocks.fence);
/* 40:   */           } else {
/* 41:42 */             world.setBlockToAir(x, y, z);
/* 42:   */           }
/* 43:   */         }
/* 44:   */       }
/* 45:   */     }
/* 46:   */   }
/* 47:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.EntranceGenerator
 * JD-Core Version:    0.7.1
 */