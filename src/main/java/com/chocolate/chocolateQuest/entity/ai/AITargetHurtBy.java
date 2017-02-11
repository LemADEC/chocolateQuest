/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import java.util.Iterator;
/*  4:   */ import java.util.List;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityCreature;
/*  7:   */ import net.minecraft.entity.EntityLiving;
/*  8:   */ import net.minecraft.entity.ai.EntityAITarget;
/*  9:   */ import net.minecraft.util.AxisAlignedBB;
/* 10:   */ import net.minecraft.world.World;
/* 11:   */ 
/* 12:   */ public class AITargetHurtBy
/* 13:   */   extends EntityAITarget
/* 14:   */ {
/* 15:   */   boolean alertCompanions;
/* 16:   */   
/* 17:   */   public AITargetHurtBy(EntityCreature par1EntityLiving, boolean par2)
/* 18:   */   {
/* 19:18 */     super(par1EntityLiving, false);
/* 20:19 */     this.alertCompanions = par2;
/* 21:20 */     setMutexBits(1);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public boolean shouldExecute()
/* 25:   */   {
/* 26:28 */     return isSuitableTarget(this.taskOwner.getAITarget(), true);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void startExecuting()
/* 30:   */   {
/* 31:36 */     this.taskOwner.setAttackTarget(this.taskOwner.getAITarget());
/* 32:38 */     if (this.alertCompanions)
/* 33:   */     {
/* 34:40 */       double targetDistance = getTargetDistance();
/* 35:41 */       List list = this.taskOwner.worldObj.getEntitiesWithinAABB(this.taskOwner.getClass(), AxisAlignedBB.getBoundingBox(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D).expand(targetDistance, 4.0D, targetDistance));
/* 36:42 */       Iterator iterator = list.iterator();
/* 37:45 */       while (iterator.hasNext())
/* 38:   */       {
/* 39:50 */         Entity entity = (Entity)iterator.next();
/* 40:51 */         EntityLiving entityliving = (EntityLiving)entity;
/* 41:53 */         if ((this.taskOwner != entityliving) && (entityliving.getAttackTarget() == null)) {
/* 42:55 */           entityliving.setAttackTarget(this.taskOwner.getAITarget());
/* 43:   */         }
/* 44:   */       }
/* 45:   */     }
/* 46:61 */     super.startExecuting();
/* 47:   */   }
/* 48:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AITargetHurtBy
 * JD-Core Version:    0.7.1
 */