/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.util.MathHelper;
/*  6:   */ 
/*  7:   */ public class AIHumanAttackAggressiveBackstab
/*  8:   */   extends AIHumanAttackAggressive
/*  9:   */ {
/* 10:   */   public AIHumanAttackAggressiveBackstab(EntityHumanBase par1EntityLiving, float speed, boolean requireSight)
/* 11:   */   {
/* 12:11 */     super(par1EntityLiving, speed, requireSight);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public boolean tryToMoveToEntity()
/* 16:   */   {
/* 17:17 */     float targetAngle = (this.entityTarget.rotationYawHead - 180.0F) * 3.1416F / 180.0F;
/* 18:18 */     double cos = MathHelper.cos(targetAngle);
/* 19:19 */     double sin = MathHelper.sin(targetAngle);
/* 20:   */     
/* 21:21 */     float angle = this.owner.rotationYawHead - this.entityTarget.rotationYawHead;
/* 22:22 */     while (angle > 360.0F) {
/* 23:22 */       angle -= 360.0F;
/* 24:   */     }
/* 25:23 */     while (angle < 0.0F) {
/* 26:23 */       angle += 360.0F;
/* 27:   */     }
/* 28:24 */     angle = 180.0F - Math.abs(angle - 180.0F);
/* 29:   */     
/* 30:26 */     double dist = Math.min(2.5D, Math.abs(angle) / 60.0F);
/* 31:   */     
/* 32:28 */     double x = this.entityTarget.posX + -sin * dist;
/* 33:29 */     double y = this.entityTarget.posY;
/* 34:30 */     double z = this.entityTarget.posZ + cos * dist;
/* 35:   */     
/* 36:32 */     return tryMoveToXYZ(x, y, z, 1.0F);
/* 37:   */   }
/* 38:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIHumanAttackAggressiveBackstab
 * JD-Core Version:    0.7.1
 */