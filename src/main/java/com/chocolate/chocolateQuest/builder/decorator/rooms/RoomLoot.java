/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.builder.BuilderHelper;
/*  4:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class RoomLoot
/*  9:   */   extends RoomBase
/* 10:   */ {
/* 11:   */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 12:   */   {
/* 13:13 */     if (side == 5)
/* 14:   */     {
/* 15:15 */       if (random.nextInt(10) == 0) {
/* 16:17 */         BuilderHelper.addTreasure(random, world, x, y, z);
/* 17:19 */       } else if (random.nextInt(3) == 0) {
/* 18:21 */         BuilderHelper.addWeaponChest(random, world, x, y, z);
/* 19:23 */       } else if (random.nextInt(3) == 0) {
/* 20:25 */         BuilderHelper.addChest(random, world, x, y, z);
/* 21:   */       } else {
/* 22:29 */         BuilderHelper.addMineralChest(random, world, x, y, z);
/* 23:   */       }
/* 24:   */     }
/* 25:33 */     else if (random.nextInt(30) == 0)
/* 26:   */     {
/* 27:35 */       BuilderHelper.addTreasure(random, world, x, y, z);
/* 28:36 */       BuilderHelper.addWeaponChest(random, world, x, y, z);
/* 29:37 */       BuilderHelper.addChest(random, world, x, y, z);
/* 30:38 */       BuilderHelper.addMineralChest(random, world, x, y, z);
/* 31:   */     }
/* 32:   */     else
/* 33:   */     {
/* 34:42 */       decorateFullMonsterRoom(random, world, x, y, z, side);
/* 35:   */     }
/* 36:   */   }
/* 37:   */   
/* 38:   */   public int getType()
/* 39:   */   {
/* 40:47 */     return 403;
/* 41:   */   }
/* 42:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomLoot
 * JD-Core Version:    0.7.1
 */