/*  1:   */ package com.chocolate.chocolateQuest.entity.boss;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ 
/*  5:   */ public class AttackBase
/*  6:   */ {
/*  7:   */   EntityBaseBoss owner;
/*  8:   */   
/*  9:   */   public AttackBase(EntityBaseBoss owner)
/* 10:   */   {
/* 11: 9 */     this.owner = owner;
/* 12:   */   }
/* 13:   */   
/* 14:   */   public void onUpdate() {}
/* 15:   */   
/* 16:   */   public boolean isAttackInProgress()
/* 17:   */   {
/* 18:13 */     return false;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public boolean attackTarget(Entity target)
/* 22:   */   {
/* 23:14 */     return false;
/* 24:   */   }
/* 25:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.AttackBase
 * JD-Core Version:    0.7.1
 */