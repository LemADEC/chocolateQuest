/*  1:   */ package com.chocolate.chocolateQuest.entity.mob.registry;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.world.World;
/*  6:   */ 
/*  7:   */ public abstract class DungeonMonstersBase
/*  8:   */ {
/*  9:   */   int id;
/* 10:   */   
/* 11:   */   public void setID(int id)
/* 12:   */   {
/* 13:13 */     this.id = id;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public int getID()
/* 17:   */   {
/* 18:16 */     return this.id;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public abstract Entity getBoss(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
/* 22:   */   
/* 23:   */   public abstract Entity getEntity(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
/* 24:   */   
/* 25:   */   public String getSpawnerName(int x, int y, int z, Random random)
/* 26:   */   {
/* 27:37 */     return null;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public abstract String getEntityName();
/* 31:   */   
/* 32:   */   public abstract String getRegisteredEntityName();
/* 33:   */   
/* 34:   */   public String getTeamName()
/* 35:   */   {
/* 36:52 */     return "npc";
/* 37:   */   }
/* 38:   */   
/* 39:   */   public double getHealth()
/* 40:   */   {
/* 41:56 */     return 20.0D;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public double getAttack()
/* 45:   */   {
/* 46:59 */     return 1.0D;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public double getRange()
/* 50:   */   {
/* 51:62 */     return 20.0D;
/* 52:   */   }
/* 53:   */   
/* 54:   */   public int getWeight()
/* 55:   */   {
/* 56:71 */     return 100;
/* 57:   */   }
/* 58:   */   
/* 59:   */   public int getFlagId()
/* 60:   */   {
/* 61:78 */     return 0;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public DungeonMonstersBase getDungeonMonster(World world, int x, int y, int z)
/* 65:   */   {
/* 66:82 */     return this;
/* 67:   */   }
/* 68:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase
 * JD-Core Version:    0.7.1
 */