/*  1:   */ package com.chocolate.chocolateQuest.entity.mob.registry;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanWalker;
/*  4:   */ import com.chocolate.chocolateQuest.entity.mob.EntityWalkerBoss;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class MobWalker
/*  9:   */   extends DungeonMonstersBase
/* 10:   */ {
/* 11:   */   public String getEntityName()
/* 12:   */   {
/* 13:14 */     return "walker";
/* 14:   */   }
/* 15:   */   
/* 16:   */   public int getFlagId()
/* 17:   */   {
/* 18:19 */     return 10;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public String getRegisteredEntityName()
/* 22:   */   {
/* 23:24 */     return "chocolateQuest.abyssWalker";
/* 24:   */   }
/* 25:   */   
/* 26:   */   public Entity getBoss(World world, int x, int y, int z)
/* 27:   */   {
/* 28:29 */     return new EntityWalkerBoss(world);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public Entity getEntity(World world, int x, int y, int z)
/* 32:   */   {
/* 33:33 */     return new EntityHumanWalker(world);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public String getTeamName()
/* 37:   */   {
/* 38:37 */     return "walker";
/* 39:   */   }
/* 40:   */   
/* 41:   */   public double getHealth()
/* 42:   */   {
/* 43:40 */     return 30.0D;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public double getAttack()
/* 47:   */   {
/* 48:43 */     return 2.0D;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public double getRange()
/* 52:   */   {
/* 53:46 */     return 30.0D;
/* 54:   */   }
/* 55:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.registry.MobWalker
 * JD-Core Version:    0.7.1
 */