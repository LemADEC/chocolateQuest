/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityCreature;
/*  4:   */ import net.minecraft.entity.EntityLiving;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.entity.IEntityOwnable;
/*  7:   */ import net.minecraft.entity.ai.EntityAITarget;
/*  8:   */ 
/*  9:   */ public class AITargetOwner
/* 10:   */   extends EntityAITarget
/* 11:   */ {
/* 12:   */   EntityLiving theEntity;
/* 13:   */   EntityLivingBase theTarget;
/* 14:   */   
/* 15:   */   public AITargetOwner(EntityCreature par1EntityTameable)
/* 16:   */   {
/* 17:16 */     super(par1EntityTameable, false);
/* 18:17 */     this.theEntity = par1EntityTameable;
/* 19:18 */     setMutexBits(1);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean shouldExecute()
/* 23:   */   {
/* 24:27 */     this.theTarget = getTarget();
/* 25:29 */     if (this.theTarget != null)
/* 26:   */     {
/* 27:31 */       if (this.theTarget == this.theEntity.ridingEntity) {
/* 28:33 */         return false;
/* 29:   */       }
/* 30:35 */       return isSuitableTarget(this.theTarget, false);
/* 31:   */     }
/* 32:38 */     return false;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public EntityLivingBase getTarget()
/* 36:   */   {
/* 37:43 */     EntityLivingBase entityliving = (EntityLivingBase)((IEntityOwnable)this.theEntity).getOwner();
/* 38:47 */     if (entityliving == null) {
/* 39:49 */       return null;
/* 40:   */     }
/* 41:53 */     boolean flag = false;
/* 42:54 */     if (entityliving.getAITarget() != null) {
/* 43:56 */       return entityliving.getAITarget();
/* 44:   */     }
/* 45:58 */     if (entityliving.getLastAttacker() != null) {
/* 46:60 */       return entityliving.getLastAttacker();
/* 47:   */     }
/* 48:63 */     return null;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public void startExecuting()
/* 52:   */   {
/* 53:71 */     this.taskOwner.setAttackTarget(this.theTarget);
/* 54:72 */     super.startExecuting();
/* 55:   */   }
/* 56:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AITargetOwner
 * JD-Core Version:    0.7.1
 */