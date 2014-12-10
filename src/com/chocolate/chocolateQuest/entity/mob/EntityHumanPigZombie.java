/*  1:   */ package com.chocolate.chocolateQuest.entity.mob;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase;
/*  5:   */ import net.minecraft.entity.EnumCreatureAttribute;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class EntityHumanPigZombie
/*  9:   */   extends EntityHumanMob
/* 10:   */ {
/* 11:   */   public EntityHumanPigZombie(World world)
/* 12:   */   {
/* 13:12 */     super(world);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public DungeonMonstersBase getMonsterType()
/* 17:   */   {
/* 18:17 */     return ChocolateQuest.pigZombie;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public EnumCreatureAttribute getCreatureAttribute()
/* 22:   */   {
/* 23:22 */     return EnumCreatureAttribute.UNDEAD;
/* 24:   */   }
/* 25:   */   
/* 26:   */   protected String getLivingSound()
/* 27:   */   {
/* 28:26 */     return "mob.zombiepig.zpig";
/* 29:   */   }
/* 30:   */   
/* 31:   */   protected String getHurtSound()
/* 32:   */   {
/* 33:30 */     return "mob.zombiepig.zpighurt";
/* 34:   */   }
/* 35:   */   
/* 36:   */   protected String getDeathSound()
/* 37:   */   {
/* 38:34 */     return "mob.zombiepig.zpigdeath";
/* 39:   */   }
/* 40:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.EntityHumanPigZombie
 * JD-Core Version:    0.7.1
 */