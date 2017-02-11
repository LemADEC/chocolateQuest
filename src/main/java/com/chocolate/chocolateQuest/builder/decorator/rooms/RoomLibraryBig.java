/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.builder.decorator.BuildingProperties;
/*  4:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.init.Blocks;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ 
/*  9:   */ public class RoomLibraryBig
/* 10:   */   extends RoomBase
/* 11:   */ {
/* 12:   */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 13:   */   {
/* 14:13 */     if ((side == 5) && (random.nextInt(3) == 0)) {
/* 15:14 */       return;
/* 16:   */     }
/* 17:15 */     for (int i = 0; i < this.properties.floorHeight; i++) {
/* 18:17 */       world.setBlock(x, y + i, z, Blocks.bookshelf);
/* 19:   */     }
/* 20:   */   }
/* 21:   */   
/* 22:   */   public int getType()
/* 23:   */   {
/* 24:22 */     return 200;
/* 25:   */   }
/* 26:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomLibraryBig
 * JD-Core Version:    0.7.1
 */