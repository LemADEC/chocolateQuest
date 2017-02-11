/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.builder.BuilderHelper;
/*  4:   */ import com.chocolate.chocolateQuest.builder.decorator.BuildingProperties;
/*  5:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  6:   */ import com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase;
/*  7:   */ import com.chocolate.chocolateQuest.entity.mob.registry.RegisterDungeonMobs;
/*  8:   */ import java.util.ArrayList;
/*  9:   */ import java.util.Random;
/* 10:   */ import net.minecraft.entity.Entity;
/* 11:   */ import net.minecraft.world.World;
/* 12:   */ 
/* 13:   */ public class RoomBoss
/* 14:   */   extends RoomBase
/* 15:   */ {
/* 16:   */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 17:   */   {
/* 18:16 */     if (side == 5)
/* 19:   */     {
/* 20:18 */       DungeonMonstersBase mob = (DungeonMonstersBase)RegisterDungeonMobs.mobList.get(this.properties.mobID);
/* 21:19 */       if (mob != null)
/* 22:   */       {
/* 23:21 */         Entity e = mob.getBoss(world, x, y, z);
/* 24:22 */         if (e != null)
/* 25:   */         {
/* 26:23 */           e.setPosition(x, y, z);
/* 27:24 */           world.spawnEntityInWorld(e);
/* 28:26 */           if (e.ridingEntity != null) {
/* 29:27 */             world.spawnEntityInWorld(e.ridingEntity);
/* 30:   */           }
/* 31:   */         }
/* 32:   */       }
/* 33:   */     }
/* 34:32 */     else if (random.nextInt(30) == 0)
/* 35:   */     {
/* 36:34 */       BuilderHelper.addTreasure(random, world, x, y, z);
/* 37:35 */       BuilderHelper.addWeaponChest(random, world, x, y, z);
/* 38:36 */       BuilderHelper.addChest(random, world, x, y, z);
/* 39:37 */       BuilderHelper.addMineralChest(random, world, x, y, z);
/* 40:   */     }
/* 41:   */     else
/* 42:   */     {
/* 43:41 */       decorateFullMonsterRoom(random, world, x, y, z, side);
/* 44:   */     }
/* 45:   */   }
/* 46:   */   
/* 47:   */   public int getType()
/* 48:   */   {
/* 49:46 */     return 403;
/* 50:   */   }
/* 51:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomBoss
 * JD-Core Version:    0.7.1
 */