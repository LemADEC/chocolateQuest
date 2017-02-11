/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.entity.ai.EntityLookHelper;
/*  6:   */ import net.minecraft.entity.ai.EntitySenses;
/*  7:   */ import net.minecraft.pathfinding.PathNavigate;
/*  8:   */ import net.minecraft.util.AxisAlignedBB;
/*  9:   */ import net.minecraft.world.EnumDifficulty;
/* 10:   */ import net.minecraft.world.World;
/* 11:   */ 
/* 12:   */ public class AIHumanAttackDefensive
/* 13:   */   extends AIInteractBase
/* 14:   */ {
/* 15:   */   private int attackCooldown;
/* 16:   */   float moveSpeed;
/* 17:   */   
/* 18:   */   public AIHumanAttackDefensive(EntityHumanBase par1EntityLiving, float speed)
/* 19:   */   {
/* 20:14 */     super(par1EntityLiving);
/* 21:15 */     this.moveSpeed = speed;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public boolean shouldExecute()
/* 25:   */   {
/* 26:24 */     return super.shouldExecute();
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void startExecuting()
/* 30:   */   {
/* 31:32 */     super.startExecuting();
/* 32:33 */     this.attackCooldown = 0;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void resetTask()
/* 36:   */   {
/* 37:41 */     super.resetTask();
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void updateTask()
/* 41:   */   {
/* 42:48 */     boolean canSeeTarget = this.owner.getEntitySenses().canSee(this.entityTarget);
/* 43:49 */     this.owner.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);
/* 44:50 */     double dist = this.owner.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ);
/* 45:51 */     boolean goToTarget = true;
/* 46:52 */     if (this.owner.getLeader() != null)
/* 47:   */     {
/* 48:53 */       if (this.owner.getLeader().getDistanceToEntity(this.entityTarget) > this.owner.partyDistanceToLeader * this.owner.partyDistanceToLeader)
/* 49:   */       {
/* 50:54 */         stayInFormation();
/* 51:55 */         goToTarget = false;
/* 52:   */       }
/* 53:   */     }
/* 54:58 */     else if (!this.owner.isWithinHomeDistanceCurrentPosition()) {
/* 55:59 */       goToTarget = false;
/* 56:   */     }
/* 57:61 */     if (goToTarget)
/* 58:   */     {
/* 59:62 */       boolean havePath = this.owner.onGround;
/* 60:63 */       if ((canSeeTarget) && (--this.attackCooldown <= 0))
/* 61:   */       {
/* 62:65 */         if ((this.owner.worldObj.difficultySetting.getDifficultyId() >= EnumDifficulty.HARD.getDifficultyId()) && (dist > 16.0D)) {
/* 63:67 */           this.owner.startSprinting();
/* 64:   */         }
/* 65:69 */         getNavigator().tryMoveToEntityLiving(this.entityTarget, this.moveSpeed);
/* 66:   */       }
/* 67:   */     }
/* 68:72 */     if (canSeeTarget) {
/* 69:73 */       attackTarget(dist);
/* 70:   */     }
/* 71:   */   }
/* 72:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIHumanAttackDefensive
 * JD-Core Version:    0.7.1
 */