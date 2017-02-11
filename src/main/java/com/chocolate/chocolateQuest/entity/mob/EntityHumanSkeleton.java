/*  1:   */ package com.chocolate.chocolateQuest.entity.mob;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase;
/*  5:   */ import net.minecraft.entity.EnumCreatureAttribute;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class EntityHumanSkeleton
/*  9:   */   extends EntityHumanMob
/* 10:   */ {
/* 11:   */   public EntityHumanSkeleton(World world)
/* 12:   */   {
/* 13:12 */     super(world);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public DungeonMonstersBase getMonsterType()
/* 17:   */   {
/* 18:16 */     return ChocolateQuest.skeleton;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public EnumCreatureAttribute getCreatureAttribute()
/* 22:   */   {
/* 23:21 */     return EnumCreatureAttribute.UNDEAD;
/* 24:   */   }
/* 25:   */   
/* 26:   */   protected String getLivingSound()
/* 27:   */   {
/* 28:25 */     return "mob.skeleton.say";
/* 29:   */   }
/* 30:   */   
/* 31:   */   protected String getHurtSound()
/* 32:   */   {
/* 33:29 */     return "mob.skeleton.hurt";
/* 34:   */   }
/* 35:   */   
/* 36:   */   protected String getDeathSound()
/* 37:   */   {
/* 38:33 */     return "mob.skeleton.death";
/* 39:   */   }
/* 40:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.EntityHumanSkeleton
 * JD-Core Version:    0.7.1
 */