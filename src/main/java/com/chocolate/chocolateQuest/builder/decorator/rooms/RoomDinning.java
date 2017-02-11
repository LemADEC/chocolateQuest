/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class RoomDinning
/*  9:   */   extends RoomBase
/* 10:   */ {
/* 11:   */   public void addRoomDecoration(Random random, World world, int posX, int posY, int posZ)
/* 12:   */   {
/* 13:13 */     int sx = Math.max(1, this.sizeX - 3);
/* 14:14 */     int sz = Math.max(1, this.sizeZ - 3);
/* 15:15 */     for (int i = 2; i < sx; i++) {
/* 16:17 */       for (int k = 2; k < sz; k++) {
/* 17:19 */         world.setBlock(posX + i, posY, posZ + k, ChocolateQuest.table);
/* 18:   */       }
/* 19:   */     }
/* 20:   */   }
/* 21:   */   
/* 22:   */   public int getType()
/* 23:   */   {
/* 24:25 */     return 400;
/* 25:   */   }
/* 26:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomDinning
 * JD-Core Version:    0.7.1
 */