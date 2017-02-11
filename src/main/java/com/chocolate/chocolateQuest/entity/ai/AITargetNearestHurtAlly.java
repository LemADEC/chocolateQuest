/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*  6:   */ 
/*  7:   */ public class AITargetNearestHurtAlly
/*  8:   */   extends EntityAINearestAttackableTarget
/*  9:   */ {
/* 10:   */   public AITargetNearestHurtAlly(EntityHumanBase owner, Class targetClass)
/* 11:   */   {
/* 12:14 */     super(owner, targetClass, 0, true, false, new SelectorHurtAlly(owner));
/* 13:   */   }
/* 14:   */   
/* 15:   */   public boolean isSuitableTarget(EntityLivingBase par1EntityLivingBase, boolean par2)
/* 16:   */   {
/* 17:21 */     return true;
/* 18:   */   }
/* 19:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AITargetNearestHurtAlly
 * JD-Core Version:    0.7.1
 */