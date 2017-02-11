/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.entity.ai.EntityAITarget;
/*  6:   */ 
/*  7:   */ public class AITargetParty
/*  8:   */   extends EntityAITarget
/*  9:   */ {
/* 10:   */   EntityHumanBase owner;
/* 11:   */   EntityLivingBase theTarget;
/* 12:   */   
/* 13:   */   public AITargetParty(EntityHumanBase human)
/* 14:   */   {
/* 15:15 */     super(human, false);
/* 16:16 */     this.owner = human;
/* 17:17 */     setMutexBits(1);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public boolean shouldExecute()
/* 21:   */   {
/* 22:26 */     EntityLivingBase var1 = getTarget();
/* 23:27 */     if (this.owner.isSuitableMount(var1)) {
/* 24:28 */       return false;
/* 25:   */     }
/* 26:29 */     this.theTarget = var1;
/* 27:30 */     return this.theTarget != null;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public EntityLivingBase getTarget()
/* 31:   */   {
/* 32:35 */     if (this.owner.party != null) {
/* 33:37 */       return this.owner.party.getTarget();
/* 34:   */     }
/* 35:39 */     return null;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void startExecuting()
/* 39:   */   {
/* 40:48 */     this.owner.setAttackTarget(this.theTarget);
/* 41:49 */     super.startExecuting();
/* 42:   */   }
/* 43:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AITargetParty
 * JD-Core Version:    0.7.1
 */