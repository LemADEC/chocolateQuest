/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.world.World;
/*  6:   */ 
/*  7:   */ public class RoomArmory
/*  8:   */   extends RoomBase
/*  9:   */ {
/* 10:   */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 11:   */   {
/* 12:12 */     if (side == 5) {
/* 13:14 */       placeArmorStand(random, world, x, y, z, side);
/* 14:16 */     } else if (random.nextInt(20) == 0) {
/* 15:18 */       placeArmorStand(random, world, x, y, z, side);
/* 16:20 */     } else if (random.nextInt(8) == 0) {
/* 17:22 */       placeShied(random, world, x, y + 1, z, side);
/* 18:24 */     } else if (random.nextInt(16) == 0) {
/* 19:26 */       addWeaponChest(random, world, x, y, z, side);
/* 20:   */     } else {
/* 21:29 */       decorateFullMonsterRoom(random, world, x, y, z, side);
/* 22:   */     }
/* 23:   */   }
/* 24:   */   
/* 25:   */   public int getType()
/* 26:   */   {
/* 27:33 */     return 202;
/* 28:   */   }
/* 29:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomArmory
 * JD-Core Version:    0.7.1
 */