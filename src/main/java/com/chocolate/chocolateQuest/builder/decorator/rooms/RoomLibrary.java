/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.init.Blocks;
/*  6:   */ import net.minecraft.init.Items;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class RoomLibrary
/* 11:   */   extends RoomBase
/* 12:   */ {
/* 13:   */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 14:   */   {
/* 15:15 */     if ((side == 5) || (random.nextInt(3) == 0)) {
/* 16:16 */       world.setBlock(x, y, z, Blocks.bookshelf);
/* 17:18 */     } else if (random.nextInt(50) == 0) {
/* 18:20 */       placeFrame(random, world, x, y + 1, z, side, new ItemStack(Items.clock));
/* 19:26 */     } else if (random.nextInt(100) == 0) {
/* 20:28 */       placePainting(random, world, x, y + 1, z, side);
/* 21:   */     } else {
/* 22:31 */       decorateFullMonsterRoom(random, world, x, y, z, side);
/* 23:   */     }
/* 24:   */   }
/* 25:   */   
/* 26:   */   public int getType()
/* 27:   */   {
/* 28:36 */     return 2;
/* 29:   */   }
/* 30:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomLibrary
 * JD-Core Version:    0.7.1
 */