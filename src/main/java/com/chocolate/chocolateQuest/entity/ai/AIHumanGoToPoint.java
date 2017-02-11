/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import com.chocolate.chocolateQuest.utils.Vec4I;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ 
/*  7:   */ public class AIHumanGoToPoint
/*  8:   */   extends AIControlledBase
/*  9:   */ {
/* 10:   */   public Vec4I currentPos;
/* 11:   */   
/* 12:   */   public AIHumanGoToPoint(EntityHumanBase owner)
/* 13:   */   {
/* 14: 9 */     super(owner);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public boolean shouldExecute()
/* 18:   */   {
/* 19:16 */     if (this.owner.currentPos != null) {}
/* 20:17 */     this.currentPos = this.owner.currentPos;
/* 21:18 */     return this.currentPos != null;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public boolean continueExecuting()
/* 25:   */   {
/* 26:24 */     return this.owner.currentPos == this.currentPos;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void resetTask()
/* 30:   */   {
/* 31:28 */     this.currentPos = null;
/* 32:29 */     super.resetTask();
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void updateTask()
/* 36:   */   {
/* 37:35 */     Vec4I p = this.currentPos;
/* 38:36 */     double dist = 0.0D;
/* 39:37 */     float width = 0.0F;
/* 40:38 */     if (this.owner.ridingEntity == null)
/* 41:   */     {
/* 42:39 */       dist = this.owner.getDistanceSq(p.xCoord, p.yCoord, p.zCoord);
/* 43:40 */       width = this.owner.width + 1.0F;
/* 44:   */     }
/* 45:   */     else
/* 46:   */     {
/* 47:43 */       dist = this.owner.ridingEntity.getDistanceSq(p.xCoord, p.yCoord, p.zCoord);
/* 48:44 */       width = this.owner.ridingEntity.width;
/* 49:   */     }
/* 50:46 */     width *= width;
/* 51:47 */     if (dist > width + 1.0F) {
/* 52:49 */       tryMoveToXYZ(p.xCoord, p.yCoord, p.zCoord, 1.0F);
/* 53:51 */     } else if (this.owner.currentPos == this.currentPos) {
/* 54:52 */       this.owner.currentPos = null;
/* 55:   */     }
/* 56:55 */     super.updateTask();
/* 57:   */   }
/* 58:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIHumanGoToPoint
 * JD-Core Version:    0.7.1
 */