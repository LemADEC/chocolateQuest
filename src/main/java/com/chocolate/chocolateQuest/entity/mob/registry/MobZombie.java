/*  1:   */ package com.chocolate.chocolateQuest.entity.mob.registry;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanZombie;
/*  4:   */ import com.chocolate.chocolateQuest.entity.mob.EntityLich;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class MobZombie
/*  9:   */   extends DungeonMonstersBase
/* 10:   */ {
/* 11:   */   public String getEntityName()
/* 12:   */   {
/* 13:14 */     return "zombie";
/* 14:   */   }
/* 15:   */   
/* 16:   */   public int getFlagId()
/* 17:   */   {
/* 18:19 */     return 7;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public String getRegisteredEntityName()
/* 22:   */   {
/* 23:24 */     return "chocolateQuest.armoredZombie";
/* 24:   */   }
/* 25:   */   
/* 26:   */   public Entity getBoss(World world, int x, int y, int z)
/* 27:   */   {
/* 28:29 */     return new EntityLich(world);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public Entity getEntity(World world, int x, int y, int z)
/* 32:   */   {
/* 33:33 */     return new EntityHumanZombie(world);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public String getTeamName()
/* 37:   */   {
/* 38:37 */     return "undead";
/* 39:   */   }
/* 40:   */   
/* 41:   */   public double getHealth()
/* 42:   */   {
/* 43:41 */     return 25.0D;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public double getAttack()
/* 47:   */   {
/* 48:44 */     return 1.0D;
/* 49:   */   }
/* 50:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.registry.MobZombie
 * JD-Core Version:    0.7.1
 */