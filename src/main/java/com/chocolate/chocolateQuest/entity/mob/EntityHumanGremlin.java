/*  1:   */ package com.chocolate.chocolateQuest.entity.mob;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase;
/*  5:   */ import net.minecraft.world.World;
/*  6:   */ 
/*  7:   */ public class EntityHumanGremlin
/*  8:   */   extends EntityHumanMob
/*  9:   */ {
/* 10:   */   public EntityHumanGremlin(World world)
/* 11:   */   {
/* 12:11 */     super(world);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public DungeonMonstersBase getMonsterType()
/* 16:   */   {
/* 17:16 */     return ChocolateQuest.gremlin;
/* 18:   */   }
/* 19:   */   
/* 20:   */   protected String getLivingSound()
/* 21:   */   {
/* 22:20 */     return "chocolatequest:goblin_speak";
/* 23:   */   }
/* 24:   */   
/* 25:   */   protected String getHurtSound()
/* 26:   */   {
/* 27:23 */     return "chocolatequest:goblin_hurt";
/* 28:   */   }
/* 29:   */   
/* 30:   */   protected String getDeathSound()
/* 31:   */   {
/* 32:26 */     return "chocolatequest:goblin_death";
/* 33:   */   }
/* 34:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.EntityHumanGremlin
 * JD-Core Version:    0.7.1
 */