/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*  6:   */ import net.minecraft.pathfinding.PathNavigate;
/*  7:   */ import net.minecraft.util.Vec3;
/*  8:   */ 
/*  9:   */ public class AIHumanFlee
/* 10:   */   extends AIInteractBase
/* 11:   */ {
/* 12:   */   private int attackCooldown;
/* 13:   */   float moveSpeed;
/* 14:   */   int x;
/* 15:   */   int y;
/* 16:   */   int z;
/* 17:14 */   int pathCD = 0;
/* 18:   */   
/* 19:   */   public AIHumanFlee(EntityHumanBase par1EntityLiving, float speed)
/* 20:   */   {
/* 21:18 */     super(par1EntityLiving);
/* 22:19 */     this.moveSpeed = speed;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public boolean shouldExecute()
/* 26:   */   {
/* 27:28 */     return super.shouldExecute();
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void startExecuting()
/* 31:   */   {
/* 32:36 */     super.startExecuting();
/* 33:37 */     this.attackCooldown = 0;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void resetTask()
/* 37:   */   {
/* 38:45 */     super.resetTask();
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void updateTask()
/* 42:   */   {
/* 43:52 */     double dist = this.owner.getDistanceSqToEntity(this.entityTarget);
/* 44:53 */     if (this.pathCD > 0) {
/* 45:54 */       this.pathCD -= 1;
/* 46:   */     }
/* 47:55 */     if (dist < 128.0D)
/* 48:   */     {
/* 49:56 */       if (this.pathCD == 0)
/* 50:   */       {
/* 51:57 */         Vec3 direction = Vec3.createVectorHelper(this.owner.posX + (this.owner.posX - this.entityTarget.posX), this.owner.posY + (this.owner.posY - this.entityTarget.posY), this.owner.posZ + (this.owner.posZ - this.entityTarget.posZ));
/* 52:   */         
/* 53:59 */         Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockTowards(this.owner, 10, 10, direction);
/* 54:60 */         if (vec3 != null)
/* 55:   */         {
/* 56:61 */           getNavigator().tryMoveToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord, 1.2D);
/* 57:62 */           this.pathCD = 10;
/* 58:   */         }
/* 59:   */       }
/* 60:   */     }
/* 61:   */     else {
/* 62:66 */       stayInFormation();
/* 63:   */     }
/* 64:   */   }
/* 65:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIHumanFlee
 * JD-Core Version:    0.7.1
 */