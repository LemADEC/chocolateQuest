/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.pathfinding.PathNavigate;
/*  5:   */ 
/*  6:   */ public class AIControlledSit
/*  7:   */   extends AIControlledBase
/*  8:   */ {
/*  9:   */   public AIControlledSit(EntityHumanBase owner)
/* 10:   */   {
/* 11: 8 */     super(owner);
/* 12:   */   }
/* 13:   */   
/* 14:   */   public boolean shouldExecute()
/* 15:   */   {
/* 16:13 */     return true;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void updateTask()
/* 20:   */   {
/* 21:18 */     if (!this.owner.isSitting()) {
/* 22:19 */       this.owner.setSitting(true);
/* 23:   */     }
/* 24:20 */     this.owner.getNavigator().clearPathEntity();
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void resetTask()
/* 28:   */   {
/* 29:25 */     this.owner.setSitting(false);
/* 30:26 */     super.resetTask();
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void startExecuting()
/* 34:   */   {
/* 35:31 */     this.owner.setSitting(true);
/* 36:32 */     super.startExecuting();
/* 37:   */   }
/* 38:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIControlledSit
 * JD-Core Version:    0.7.1
 */