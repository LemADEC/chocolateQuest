/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.IEntityOwnable;
/*   7:    */ import net.minecraft.entity.ai.EntityLookHelper;
/*   8:    */ import net.minecraft.pathfinding.PathNavigate;
/*   9:    */ import net.minecraft.util.AxisAlignedBB;
/*  10:    */ import net.minecraft.util.MathHelper;
/*  11:    */ import net.minecraft.world.World;
/*  12:    */ 
/*  13:    */ public class AIControlledFollowOwner
/*  14:    */   extends AIControlledBase
/*  15:    */ {
/*  16:    */   protected EntityLivingBase target;
/*  17:    */   World theWorld;
/*  18:    */   private PathNavigate ownerPathfinder;
/*  19:    */   float maxDist;
/*  20:    */   float minDist;
/*  21: 21 */   private boolean teleports = false;
/*  22:    */   private int pathFindingCooldown;
/*  23:    */   
/*  24:    */   public AIControlledFollowOwner(EntityHumanBase par1EntityTameable, float minDist, float maxDist)
/*  25:    */   {
/*  26: 26 */     super(par1EntityTameable);
/*  27: 27 */     this.theWorld = par1EntityTameable.worldObj;
/*  28: 28 */     this.ownerPathfinder = par1EntityTameable.getNavigator();
/*  29: 29 */     this.minDist = minDist;
/*  30: 30 */     this.maxDist = maxDist;
/*  31: 31 */     setMutexBits(4);
/*  32: 32 */     this.teleports = false;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public boolean shouldExecute()
/*  36:    */   {
/*  37: 40 */     EntityLivingBase entityliving = getOwner();
/*  38: 41 */     if (entityliving == null) {
/*  39: 43 */       return false;
/*  40:    */     }
/*  41: 45 */     double dist = this.owner.getDistanceSqToEntity(entityliving);
/*  42: 46 */     double minDist = this.minDist * this.minDist;
/*  43: 47 */     if (this.owner.getAttackTarget() == null ? dist < minDist : dist < minDist * 3.0D) {
/*  44: 49 */       return false;
/*  45:    */     }
/*  46: 52 */     this.target = entityliving;
/*  47: 53 */     return true;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public EntityLivingBase getOwner()
/*  51:    */   {
/*  52: 58 */     return (EntityLivingBase)this.owner.getOwner();
/*  53:    */   }
/*  54:    */   
/*  55:    */   public boolean continueExecuting()
/*  56:    */   {
/*  57: 66 */     return (!this.ownerPathfinder.noPath()) && (this.owner.getDistanceSqToEntity(this.target) > this.maxDist * this.maxDist);
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void startExecuting()
/*  61:    */   {
/*  62: 74 */     this.pathFindingCooldown = 0;
/*  63: 75 */     this.owner.getNavigator().setAvoidsWater(false);
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void resetTask()
/*  67:    */   {
/*  68: 83 */     this.target = null;
/*  69: 84 */     this.ownerPathfinder.clearPathEntity();
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void updateTask()
/*  73:    */   {
/*  74: 92 */     this.owner.getLookHelper().setLookPositionWithEntity(this.target, 10.0F, this.owner.getVerticalFaceSpeed());
/*  75: 94 */     if (--this.pathFindingCooldown <= 0)
/*  76:    */     {
/*  77: 96 */       this.pathFindingCooldown = 10;
/*  78: 97 */       if (!tryMoveToXYZ(this.target.posX, this.target.posY, this.target.posZ, 2.0F)) {
/*  79: 99 */         if ((this.owner.getDistanceSqToEntity(this.target) >= 288.0D) && (this.teleports))
/*  80:    */         {
/*  81:101 */           int i = MathHelper.floor_double(this.target.posX) - 2;
/*  82:102 */           int j = MathHelper.floor_double(this.target.posZ) - 2;
/*  83:103 */           int k = MathHelper.floor_double(this.target.boundingBox.minY);
/*  84:105 */           for (int l = 0; l <= 4; l++) {
/*  85:107 */             for (int i1 = 0; i1 <= 4; i1++) {
/*  86:109 */               if (((l < 1) || (i1 < 1) || (l > 3) || (i1 > 3)) && (World.doesBlockHaveSolidTopSurface(this.theWorld, i + l, k - 1, j + i1)) && (!this.theWorld.isBlockNormalCubeDefault(i + l, k, j + i1, true)) && (!this.theWorld.isBlockNormalCubeDefault(i + l, k + 1, j + i1, true)))
/*  87:    */               {
/*  88:111 */                 if (this.owner.ridingEntity != null) {
/*  89:112 */                   this.owner.ridingEntity.setLocationAndAngles(i + l + 0.5F, k, j + i1 + 0.5F, this.owner.rotationYaw, this.owner.rotationPitch);
/*  90:    */                 }
/*  91:113 */                 this.owner.setLocationAndAngles(i + l + 0.5F, k, j + i1 + 0.5F, this.owner.rotationYaw, this.owner.rotationPitch);
/*  92:114 */                 this.ownerPathfinder.clearPathEntity();
/*  93:115 */                 return;
/*  94:    */               }
/*  95:    */             }
/*  96:    */           }
/*  97:    */         }
/*  98:    */       }
/*  99:    */     }
/* 100:    */   }
/* 101:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIControlledFollowOwner
 * JD-Core Version:    0.7.1
 */