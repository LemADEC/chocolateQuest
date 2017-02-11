/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.entity.ai.EntityLookHelper;
/*  6:   */ import net.minecraft.entity.ai.EntitySenses;
/*  7:   */ import net.minecraft.pathfinding.PathNavigate;
/*  8:   */ import net.minecraft.util.AxisAlignedBB;
/*  9:   */ 
/* 10:   */ public class AIHumanMount
/* 11:   */   extends AIInteractBase
/* 12:   */ {
/* 13:   */   boolean requireSight;
/* 14:   */   
/* 15:   */   public AIHumanMount(EntityHumanBase par1EntityLiving, float moveSpeed, boolean par3)
/* 16:   */   {
/* 17:12 */     super(par1EntityLiving);
/* 18:13 */     setMutexBits(3);
/* 19:14 */     this.requireSight = par3;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean shouldExecute()
/* 23:   */   {
/* 24:22 */     EntityLivingBase var1 = this.owner.getAttackTarget();
/* 25:23 */     if (var1 == null) {
/* 26:24 */       return false;
/* 27:   */     }
/* 28:25 */     boolean suitableMount = this.owner.isSuitableMount(var1);
/* 29:26 */     if ((!this.owner.isSuitableMount(var1)) || (this.owner.ridingEntity != null) || (var1.riddenByEntity != null))
/* 30:   */     {
/* 31:28 */       if (suitableMount) {
/* 32:29 */         this.owner.setAttackTarget(null);
/* 33:   */       }
/* 34:30 */       return false;
/* 35:   */     }
/* 36:34 */     this.entityTarget = var1;
/* 37:35 */     return true;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public boolean continueExecuting()
/* 41:   */   {
/* 42:44 */     EntityLivingBase target = this.owner.getAttackTarget();
/* 43:46 */     if ((!this.owner.isSuitableMount(target)) || (this.owner.ridingEntity != null) || (this.entityTarget.riddenByEntity != null)) {
/* 44:48 */       return false;
/* 45:   */     }
/* 46:51 */     return target != null;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public void resetTask()
/* 50:   */   {
/* 51:59 */     this.owner.setAttackTarget(null);
/* 52:60 */     this.entityTarget = null;
/* 53:61 */     this.owner.getNavigator().clearPathEntity();
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void updateTask()
/* 57:   */   {
/* 58:69 */     this.owner.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);
/* 59:71 */     if ((this.requireSight) || (this.owner.getEntitySenses().canSee(this.entityTarget))) {
/* 60:73 */       this.owner.getNavigator().tryMoveToEntityLiving(this.entityTarget, 1.0D);
/* 61:   */     }
/* 62:76 */     this.owner.attackTime = Math.max(this.owner.attackTime - 1, 0);
/* 63:77 */     double bounds = this.owner.width * 2.0F * this.entityTarget.width * 2.0F;
/* 64:78 */     double dist = this.owner.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ);
/* 65:80 */     if (dist <= bounds)
/* 66:   */     {
/* 67:82 */       this.owner.mountEntity(this.entityTarget);
/* 68:83 */       this.owner.setMountAI();
/* 69:84 */       this.owner.setAttackTarget(null);
/* 70:   */     }
/* 71:   */   }
/* 72:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIHumanMount
 * JD-Core Version:    0.7.1
 */