/*  1:   */ package com.chocolate.chocolateQuest.entity.mob.registry;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanSkeleton;
/*  4:   */ import com.chocolate.chocolateQuest.entity.mob.EntityNecromancer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class MobSkeleton
/*  9:   */   extends DungeonMonstersBase
/* 10:   */ {
/* 11:   */   public String getEntityName()
/* 12:   */   {
/* 13:13 */     return "skeleton";
/* 14:   */   }
/* 15:   */   
/* 16:   */   public int getFlagId()
/* 17:   */   {
/* 18:18 */     return 8;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public String getRegisteredEntityName()
/* 22:   */   {
/* 23:23 */     return "chocolateQuest.armoredSkeleton";
/* 24:   */   }
/* 25:   */   
/* 26:   */   public Entity getBoss(World world, int x, int y, int z)
/* 27:   */   {
/* 28:28 */     return new EntityNecromancer(world);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public Entity getEntity(World world, int x, int y, int z)
/* 32:   */   {
/* 33:32 */     return new EntityHumanSkeleton(world);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public String getTeamName()
/* 37:   */   {
/* 38:36 */     return "undead";
/* 39:   */   }
/* 40:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.registry.MobSkeleton
 * JD-Core Version:    0.7.1
 */