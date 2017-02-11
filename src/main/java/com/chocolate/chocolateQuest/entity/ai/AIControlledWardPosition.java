/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import com.chocolate.chocolateQuest.utils.Vec4I;
/*  5:   */ import net.minecraft.pathfinding.PathNavigate;
/*  6:   */ 
/*  7:   */ public class AIControlledWardPosition
/*  8:   */   extends AIControlledBase
/*  9:   */ {
/* 10:   */   public AIControlledWardPosition(EntityHumanBase owner)
/* 11:   */   {
/* 12: 9 */     super(owner);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public boolean shouldExecute()
/* 16:   */   {
/* 17:14 */     if (this.owner.standingPosition == null) {
/* 18:15 */       return false;
/* 19:   */     }
/* 20:16 */     Vec4I p = this.owner.standingPosition;
/* 21:17 */     return (this.owner.getDistanceSq(p.xCoord + 0.5D, p.yCoord + 0.5D, p.zCoord + 0.5D) > this.owner.width) || (this.owner.rotationYaw != p.rot);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void resetTask()
/* 25:   */   {
/* 26:22 */     this.owner.getNavigator().clearPathEntity();
/* 27:23 */     this.owner.rotationYaw = this.owner.standingPosition.rot;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void updateTask()
/* 31:   */   {
/* 32:28 */     Vec4I p = this.owner.standingPosition;
/* 33:29 */     double dist = this.owner.getDistanceSq(p.xCoord + 0.5D, p.yCoord, p.zCoord + 0.5D);
/* 34:30 */     if (dist > this.owner.width + 1.0F)
/* 35:   */     {
/* 36:32 */       tryMoveToXYZ(p.xCoord + 0.5D, p.yCoord + 0.5D, p.zCoord + 0.5D, 1.0F);
/* 37:   */     }
/* 38:   */     else
/* 39:   */     {
/* 40:35 */       this.owner.getNavigator().clearPathEntity();
/* 41:36 */       this.owner.rotationYaw = p.rot;
/* 42:   */     }
/* 43:38 */     super.updateTask();
/* 44:   */   }
/* 45:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIControlledWardPosition
 * JD-Core Version:    0.7.1
 */