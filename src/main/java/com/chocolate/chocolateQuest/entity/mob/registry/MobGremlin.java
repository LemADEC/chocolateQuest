/*  1:   */ package com.chocolate.chocolateQuest.entity.mob.registry;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanWalker;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.world.World;
/*  6:   */ 
/*  7:   */ public class MobGremlin
/*  8:   */   extends DungeonMonstersBase
/*  9:   */ {
/* 10:   */   public String getEntityName()
/* 11:   */   {
/* 12:13 */     return "gremlin";
/* 13:   */   }
/* 14:   */   
/* 15:   */   public int getFlagId()
/* 16:   */   {
/* 17:18 */     return 11;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public String getRegisteredEntityName()
/* 21:   */   {
/* 22:23 */     return "chocolateQuest.gremlin";
/* 23:   */   }
/* 24:   */   
/* 25:   */   public Entity getBoss(World world, int x, int y, int z)
/* 26:   */   {
/* 27:28 */     return null;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public Entity getEntity(World world, int x, int y, int z)
/* 31:   */   {
/* 32:32 */     return new EntityHumanWalker(world);
/* 33:   */   }
/* 34:   */   
/* 35:   */   public String getTeamName()
/* 36:   */   {
/* 37:36 */     return "gremlin";
/* 38:   */   }
/* 39:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.registry.MobGremlin
 * JD-Core Version:    0.7.1
 */