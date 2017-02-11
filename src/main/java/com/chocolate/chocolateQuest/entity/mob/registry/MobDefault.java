/*  1:   */ package com.chocolate.chocolateQuest.entity.mob.registry;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanSkeleton;
/*  5:   */ import com.chocolate.chocolateQuest.entity.mob.EntityNecromancer;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.util.ChunkCoordinates;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class MobDefault
/* 11:   */   extends DungeonMonstersBase
/* 12:   */ {
/* 13:   */   public String getEntityName()
/* 14:   */   {
/* 15:14 */     return "default";
/* 16:   */   }
/* 17:   */   
/* 18:   */   public int getFlagId()
/* 19:   */   {
/* 20:19 */     return 8;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public String getRegisteredEntityName()
/* 24:   */   {
/* 25:24 */     return "chocolateQuest.armoredSkeleton";
/* 26:   */   }
/* 27:   */   
/* 28:   */   public Entity getBoss(World world, int x, int y, int z)
/* 29:   */   {
/* 30:29 */     return new EntityNecromancer(world);
/* 31:   */   }
/* 32:   */   
/* 33:   */   public Entity getEntity(World world, int x, int y, int z)
/* 34:   */   {
/* 35:33 */     return new EntityHumanSkeleton(world);
/* 36:   */   }
/* 37:   */   
/* 38:   */   public String getTeamName()
/* 39:   */   {
/* 40:37 */     return "undead";
/* 41:   */   }
/* 42:   */   
/* 43:   */   public DungeonMonstersBase getDungeonMonster(World world, int x, int y, int z)
/* 44:   */   {
/* 45:41 */     double dist = Math.sqrt((world.getSpawnPoint().posX - x) * (world.getSpawnPoint().posX - x) + (world.getSpawnPoint().posZ - z) * (world.getSpawnPoint().posZ - z));
/* 46:43 */     if (dist < 1000.0D) {
/* 47:44 */       return ChocolateQuest.skeleton;
/* 48:   */     }
/* 49:45 */     if (dist < 2000.0D) {
/* 50:46 */       return ChocolateQuest.zombie;
/* 51:   */     }
/* 52:47 */     if (dist < 3000.0D) {
/* 53:48 */       return ChocolateQuest.specter;
/* 54:   */     }
/* 55:49 */     if (dist < 4000.0D) {
/* 56:50 */       return ChocolateQuest.pigZombie;
/* 57:   */     }
/* 58:51 */     if (dist < 5000.0D) {
/* 59:52 */       return ChocolateQuest.minotaur;
/* 60:   */     }
/* 61:54 */     return ChocolateQuest.skeleton;
/* 62:   */   }
/* 63:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.registry.MobDefault
 * JD-Core Version:    0.7.1
 */