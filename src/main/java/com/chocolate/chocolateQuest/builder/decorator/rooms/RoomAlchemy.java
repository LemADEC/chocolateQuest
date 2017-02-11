/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.init.Blocks;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class RoomAlchemy
/*  9:   */   extends RoomBase
/* 10:   */ {
/* 11:   */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 12:   */   {
/* 13:13 */     if ((side == 5) || (random.nextInt(5) == 0)) {
/* 14:15 */       world.setBlock(x, y, z, Blocks.brewing_stand, 0, 3);
/* 15:17 */     } else if (random.nextInt(5) == 0) {
/* 16:18 */       world.setBlock(x, y, z, Blocks.cauldron, random.nextInt(3), 3);
/* 17:   */     } else {
/* 18:20 */       decorateFullMonsterRoom(random, world, x, y, z, side);
/* 19:   */     }
/* 20:   */   }
/* 21:   */   
/* 22:   */   public int getType()
/* 23:   */   {
/* 24:24 */     return 201;
/* 25:   */   }
/* 26:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomAlchemy
 * JD-Core Version:    0.7.1
 */