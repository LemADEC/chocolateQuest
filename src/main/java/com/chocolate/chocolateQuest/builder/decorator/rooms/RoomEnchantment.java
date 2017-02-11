/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.builder.decorator.BuildingProperties;
/*  4:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.init.Blocks;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ 
/*  9:   */ public class RoomEnchantment
/* 10:   */   extends RoomBase
/* 11:   */ {
/* 12:   */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 13:   */   {
/* 14:14 */     if (side == 5) {
/* 15:15 */       world.setBlock(x, y, z, Blocks.enchanting_table);
/* 16:   */     } else {
/* 17:18 */       for (int i = 0; i < this.properties.floorHeight; i++) {
/* 18:20 */         world.setBlock(x, y + i, z, Blocks.bookshelf);
/* 19:   */       }
/* 20:   */     }
/* 21:   */   }
/* 22:   */   
/* 23:   */   public int getType()
/* 24:   */   {
/* 25:26 */     return 203;
/* 26:   */   }
/* 27:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomEnchantment
 * JD-Core Version:    0.7.1
 */