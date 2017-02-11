/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.entity.ai.EntityAIBase;
/*  6:   */ import net.minecraft.pathfinding.PathNavigate;
/*  7:   */ 
/*  8:   */ public class AIHumanIdleSit
/*  9:   */   extends EntityAIBase
/* 10:   */ {
/* 11: 9 */   int iddleTime = 0;
/* 12:   */   EntityHumanBase owner;
/* 13:   */   
/* 14:   */   public AIHumanIdleSit(EntityHumanBase owner)
/* 15:   */   {
/* 16:13 */     this.owner = owner;
/* 17:14 */     setMutexBits(1);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public boolean shouldExecute()
/* 21:   */   {
/* 22:20 */     if ((this.owner.getAttackTarget() != null) || (this.owner.ridingEntity != null)) {
/* 23:22 */       return false;
/* 24:   */     }
/* 25:24 */     if (this.owner.getOwner() != null) {
/* 26:26 */       if ((this.owner.getDistanceToEntity(this.owner.getOwner()) > 15.0F) || (this.owner.getAttackTarget() != null)) {
/* 27:28 */         return false;
/* 28:   */       }
/* 29:   */     }
/* 30:31 */     if ((this.owner.getNavigator().getPath() != null) || (this.owner.getAttackTarget() != null)) {
/* 31:32 */       return false;
/* 32:   */     }
/* 33:33 */     return true;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public boolean continueExecuting()
/* 37:   */   {
/* 38:39 */     return (super.continueExecuting()) && (this.iddleTime > 0);
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void startExecuting()
/* 42:   */   {
/* 43:46 */     boolean flag = false;
/* 44:47 */     if ((this.owner.getOwner() instanceof EntityHumanBase)) {
/* 45:49 */       if (((EntityHumanBase)this.owner.getOwner()).isSitting()) {
/* 46:50 */         flag = true;
/* 47:   */       }
/* 48:   */     }
/* 49:52 */     if ((this.owner.getRNG().nextInt(50) == 0) || (flag))
/* 50:   */     {
/* 51:54 */       this.iddleTime = (500 + this.owner.getRNG().nextInt(1000));
/* 52:55 */       this.owner.setSitting(true);
/* 53:   */     }
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void resetTask()
/* 57:   */   {
/* 58:61 */     this.owner.setSitting(false);
/* 59:   */   }
/* 60:   */   
/* 61:   */   public void updateTask()
/* 62:   */   {
/* 63:66 */     if (this.iddleTime > 0) {
/* 64:67 */       this.iddleTime -= 1;
/* 65:   */     }
/* 66:   */   }
/* 67:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIHumanIdleSit
 * JD-Core Version:    0.7.1
 */