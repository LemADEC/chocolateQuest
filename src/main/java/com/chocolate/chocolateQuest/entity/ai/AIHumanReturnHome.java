/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import com.chocolate.chocolateQuest.utils.Vec3I;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.pathfinding.PathNavigate;
/*  7:   */ import net.minecraft.util.ChunkCoordinates;
/*  8:   */ 
/*  9:   */ public class AIHumanReturnHome
/* 10:   */   extends AIControlledBase
/* 11:   */ {
/* 12:   */   Vec3I position;
/* 13:   */   
/* 14:   */   public AIHumanReturnHome(EntityHumanBase owner)
/* 15:   */   {
/* 16:11 */     super(owner);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public boolean shouldExecute()
/* 20:   */   {
/* 21:16 */     if (this.owner.getAttackTarget() != null) {
/* 22:18 */       if (this.owner.getAttackTarget().isEntityAlive()) {
/* 23:19 */         return false;
/* 24:   */       }
/* 25:   */     }
/* 26:21 */     if (this.owner.getOwner() != null) {
/* 27:22 */       return false;
/* 28:   */     }
/* 29:23 */     return !this.owner.isWithinHomeDistanceCurrentPosition();
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void startExecuting()
/* 33:   */   {
/* 34:29 */     this.owner.motionY += 1.0D;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void resetTask()
/* 38:   */   {
/* 39:35 */     this.owner.getNavigator().clearPathEntity();
/* 40:36 */     super.resetTask();
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void updateTask()
/* 44:   */   {
/* 45:42 */     tryMoveToXYZ(this.owner.getHomePosition().posX, this.owner.getHomePosition().posY, this.owner.getHomePosition().posZ, 1.0F);
/* 46:43 */     super.updateTask();
/* 47:   */   }
/* 48:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIHumanReturnHome
 * JD-Core Version:    0.7.1
 */