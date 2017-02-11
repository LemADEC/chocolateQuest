/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.pathfinding.PathNavigate;
/*  6:   */ import net.minecraft.util.MathHelper;
/*  7:   */ import net.minecraft.util.Vec3;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class AIControlledFormation
/* 11:   */   extends AIControlledBase
/* 12:   */ {
/* 13:14 */   double z = 0.0D;
/* 14:14 */   double y = 0.0D;
/* 15:14 */   double x = 0.0D;
/* 16:   */   protected int attackCooldown;
/* 17:   */   protected World worldObj;
/* 18:   */   
/* 19:   */   public AIControlledFormation(EntityHumanBase par1EntityLiving)
/* 20:   */   {
/* 21:18 */     super(par1EntityLiving);
/* 22:19 */     this.worldObj = par1EntityLiving.worldObj;
/* 23:20 */     setMutexBits(3);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public boolean shouldExecute()
/* 27:   */   {
/* 28:29 */     if (this.owner.getLeader() == null) {
/* 29:30 */       return false;
/* 30:   */     }
/* 31:32 */     EntityLivingBase leader = this.owner.getLeader();
/* 32:33 */     Vec3 absPosition = leader.getLookVec();
/* 33:34 */     float angle = this.owner.partyPositionAngle * 3.1416F / 180.0F;
/* 34:35 */     double cos = MathHelper.cos(angle);
/* 35:36 */     double sin = MathHelper.sin(angle);
/* 36:37 */     int dist = this.owner.partyDistanceToLeader;
/* 37:38 */     this.x = (leader.posX + (absPosition.xCoord * cos - absPosition.zCoord * sin) * dist);
/* 38:39 */     this.y = leader.posY;
/* 39:40 */     this.z = (leader.posZ + (absPosition.zCoord * cos + absPosition.xCoord * sin) * dist);
/* 40:41 */     return this.owner.getDistanceSq(this.x, this.y, this.z) > 1.0D;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void startExecuting()
/* 44:   */   {
/* 45:46 */     this.owner.getNavigator().clearPathEntity();
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void resetTask()
/* 49:   */   {
/* 50:51 */     if (this.owner.getLeader() != null)
/* 51:   */     {
/* 52:52 */       this.owner.rotationYaw = this.owner.getLeader().rotationYawHead;
/* 53:53 */       this.owner.rotationYawHead = this.owner.getLeader().rotationYawHead;
/* 54:   */     }
/* 55:55 */     this.owner.getNavigator().clearPathEntity();
/* 56:   */   }
/* 57:   */   
/* 58:   */   public void updateTask()
/* 59:   */   {
/* 60:60 */     tryMoveToXYZ(this.x, this.y, this.z, 1.15F);
/* 61:   */   }
/* 62:   */   
/* 63:   */   public boolean stayInFormation()
/* 64:   */   {
/* 65:64 */     return false;
/* 66:   */   }
/* 67:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIControlledFormation
 * JD-Core Version:    0.7.1
 */