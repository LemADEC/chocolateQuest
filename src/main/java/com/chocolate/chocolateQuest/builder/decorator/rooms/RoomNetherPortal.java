/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.block.Block;
/*  6:   */ import net.minecraft.init.Blocks;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ 
/*  9:   */ public class RoomNetherPortal
/* 10:   */   extends RoomBase
/* 11:   */ {
/* 12:   */   public void addRoomDecoration(Random random, World world, int posX, int posY, int posZ)
/* 13:   */   {
/* 14:15 */     int x = posX + this.sizeX / 2;
/* 15:16 */     int y = posY;
/* 16:17 */     int z = posZ + this.sizeZ / 2;
/* 17:18 */     z--;
/* 18:19 */     y--;
/* 19:20 */     Block block = Blocks.obsidian;
/* 20:21 */     world.setBlock(x, y, z, block);
/* 21:22 */     world.setBlock(x + 1, y, z, block);
/* 22:23 */     world.setBlock(x, y + 4, z, block);
/* 23:24 */     world.setBlock(x + 1, y + 4, z, block);
/* 24:25 */     for (int i = 0; i <= 4; i++)
/* 25:   */     {
/* 26:27 */       world.setBlock(x - 1, y + i, z, block);
/* 27:28 */       world.setBlock(x + 2, y + i, z, block);
/* 28:   */     }
/* 29:30 */     if (random.nextInt(3) == 0) {
/* 30:32 */       for (int i = 1; i <= 3; i++)
/* 31:   */       {
/* 32:34 */         world.setBlock(x, y + i, z, Blocks.portal);
/* 33:35 */         world.setBlock(x + 1, y + i, z, Blocks.portal);
/* 34:   */       }
/* 35:   */     }
/* 36:   */   }
/* 37:   */   
/* 38:   */   public int getType()
/* 39:   */   {
/* 40:41 */     return 208;
/* 41:   */   }
/* 42:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomNetherPortal
 * JD-Core Version:    0.7.1
 */