/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.entity.EntityLiving;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*  8:   */ import net.minecraft.pathfinding.PathNavigate;
/*  9:   */ 
/* 10:   */ public class AIHumanIdleTalkClosest
/* 11:   */   extends EntityAIWatchClosest
/* 12:   */ {
/* 13:   */   EntityHumanBase owner;
/* 14:   */   
/* 15:   */   public AIHumanIdleTalkClosest(EntityHumanBase par1EntityLiving, Class par2Class, float par3)
/* 16:   */   {
/* 17:15 */     super(par1EntityLiving, par2Class, par3);
/* 18:16 */     this.owner = par1EntityLiving;
/* 19:17 */     setMutexBits(2);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean shouldExecute()
/* 23:   */   {
/* 24:24 */     boolean flag = super.shouldExecute();
/* 25:25 */     if ((this.owner.getNavigator().getPath() != null) || (this.owner.getAttackTarget() != null) || (!flag)) {
/* 26:26 */       return false;
/* 27:   */     }
/* 28:28 */     if ((this.owner.isSuitableTargetAlly((EntityLiving)this.closestEntity)) && (this.owner.getDistanceToEntity(this.closestEntity) < 5.0F)) {
/* 29:30 */       return true;
/* 30:   */     }
/* 31:34 */     return false;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void startExecuting()
/* 35:   */   {
/* 36:40 */     super.startExecuting();
/* 37:41 */     this.owner.setSpeaking(true);
/* 38:42 */     handShake(60);
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void handShake(int chance)
/* 42:   */   {
/* 43:47 */     if (this.closestEntity == null) {
/* 44:48 */       return;
/* 45:   */     }
/* 46:49 */     int rnd = this.owner.getRNG().nextInt(chance);
/* 47:50 */     if (rnd == 0) {
/* 48:51 */       this.owner.swingItem();
/* 49:52 */     } else if (rnd == 1) {
/* 50:53 */       this.owner.swingItem();
/* 51:54 */     } else if (rnd > chance - 10) {
/* 52:56 */       if (this.owner.getDistanceToEntity(this.closestEntity) < 3.0F)
/* 53:   */       {
/* 54:58 */         this.owner.swingItem();
/* 55:59 */         ((EntityLivingBase)this.closestEntity).swingItem();
/* 56:   */       }
/* 57:   */     }
/* 58:   */   }
/* 59:   */   
/* 60:   */   public void resetTask()
/* 61:   */   {
/* 62:69 */     super.resetTask();
/* 63:70 */     this.owner.setSpeaking(false);
/* 64:71 */     handShake(40);
/* 65:   */   }
/* 66:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIHumanIdleTalkClosest
 * JD-Core Version:    0.7.1
 */