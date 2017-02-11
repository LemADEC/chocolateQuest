/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.builder.decorator.BuildingProperties;
/*  4:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.block.Block;
/*  7:   */ import net.minecraft.init.Blocks;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class RoomStorage
/* 11:   */   extends RoomBase
/* 12:   */ {
/* 13:   */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 14:   */   {
/* 15:14 */     if ((side == 5) || (random.nextInt(8) == 0))
/* 16:   */     {
/* 17:16 */       decorateFullMonsterRoom(random, world, x, y, z, side);
/* 18:   */     }
/* 19:   */     else
/* 20:   */     {
/* 21:20 */       Block id = Blocks.pumpkin;
/* 22:21 */       int md = 0;
/* 23:22 */       if (side == 1) {
/* 24:23 */         id = Blocks.melon_block;
/* 25:   */       }
/* 26:24 */       if (side == 2) {
/* 27:25 */         id = Blocks.hay_block;
/* 28:   */       }
/* 29:26 */       if (side == 3)
/* 30:   */       {
/* 31:27 */         id = Blocks.red_mushroom_block;
/* 32:28 */         md = 15;
/* 33:   */       }
/* 34:30 */       int roomHeight = random.nextInt(this.properties.floorHeight);
/* 35:31 */       for (int i = 0; i < roomHeight; i++) {
/* 36:32 */         world.setBlock(x, y + i, z, id, md, 3);
/* 37:   */       }
/* 38:   */     }
/* 39:   */   }
/* 40:   */   
/* 41:   */   public int getType()
/* 42:   */   {
/* 43:37 */     return 206;
/* 44:   */   }
/* 45:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomStorage
 * JD-Core Version:    0.7.1
 */