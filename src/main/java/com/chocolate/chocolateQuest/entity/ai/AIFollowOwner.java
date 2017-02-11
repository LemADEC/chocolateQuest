/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.Entity;
/*   4:    */ import net.minecraft.entity.EntityLiving;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.IEntityOwnable;
/*   7:    */ import net.minecraft.entity.ai.EntityAIBase;
/*   8:    */ import net.minecraft.entity.ai.EntityLookHelper;
/*   9:    */ import net.minecraft.pathfinding.PathNavigate;
/*  10:    */ import net.minecraft.util.AxisAlignedBB;
/*  11:    */ import net.minecraft.util.MathHelper;
/*  12:    */ import net.minecraft.world.World;
/*  13:    */ 
/*  14:    */ public class AIFollowOwner
/*  15:    */   extends EntityAIBase
/*  16:    */ {
/*  17:    */   protected EntityLiving theEntity;
/*  18:    */   protected EntityLivingBase target;
/*  19:    */   World theWorld;
/*  20:    */   private float moveSpeed;
/*  21:    */   private PathNavigate ownerPathfinder;
/*  22:    */   private int field_75343_h;
/*  23:    */   float maxDist;
/*  24:    */   float minDist;
/*  25:    */   private boolean avoidsWater;
/*  26: 25 */   private boolean teleports = false;
/*  27:    */   
/*  28:    */   public AIFollowOwner(EntityLiving par1EntityTameable, float speed, float minDist, float maxDist, boolean teleport)
/*  29:    */   {
/*  30: 29 */     this.theEntity = par1EntityTameable;
/*  31: 30 */     this.theWorld = par1EntityTameable.worldObj;
/*  32: 31 */     this.moveSpeed = speed;
/*  33: 32 */     this.ownerPathfinder = par1EntityTameable.getNavigator();
/*  34: 33 */     this.minDist = minDist;
/*  35: 34 */     this.maxDist = maxDist;
/*  36: 35 */     setMutexBits(4);
/*  37: 36 */     this.teleports = teleport;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public boolean shouldExecute()
/*  41:    */   {
/*  42: 44 */     EntityLivingBase entityliving = getOwner();
/*  43: 45 */     if (entityliving == null) {
/*  44: 47 */       return false;
/*  45:    */     }
/*  46: 49 */     double dist = this.theEntity.getDistanceSqToEntity(entityliving);
/*  47: 50 */     double minDist = this.minDist * this.minDist;
/*  48: 51 */     if (this.theEntity.getAttackTarget() == null ? dist < minDist : dist < minDist * 3.0D) {
/*  49: 53 */       return false;
/*  50:    */     }
/*  51: 56 */     this.target = entityliving;
/*  52: 57 */     return true;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public EntityLivingBase getOwner()
/*  56:    */   {
/*  57: 62 */     return (EntityLivingBase)((IEntityOwnable)this.theEntity).getOwner();
/*  58:    */   }
/*  59:    */   
/*  60:    */   public boolean continueExecuting()
/*  61:    */   {
/*  62: 70 */     return (!this.ownerPathfinder.noPath()) && (this.theEntity.getDistanceSqToEntity(this.target) > this.maxDist * this.maxDist);
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void startExecuting()
/*  66:    */   {
/*  67: 78 */     this.field_75343_h = 0;
/*  68: 79 */     this.avoidsWater = this.theEntity.getNavigator().getAvoidsWater();
/*  69: 80 */     this.theEntity.getNavigator().setAvoidsWater(false);
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void resetTask()
/*  73:    */   {
/*  74: 88 */     this.target = null;
/*  75: 89 */     this.ownerPathfinder.clearPathEntity();
/*  76: 90 */     this.theEntity.getNavigator().setAvoidsWater(this.avoidsWater);
/*  77:    */   }
/*  78:    */   
/*  79:    */   public void updateTask()
/*  80:    */   {
/*  81: 98 */     this.theEntity.getLookHelper().setLookPositionWithEntity(this.target, 10.0F, this.theEntity.getVerticalFaceSpeed());
/*  82:100 */     if (--this.field_75343_h <= 0)
/*  83:    */     {
/*  84:102 */       this.field_75343_h = 10;
/*  85:104 */       if ((this.theEntity.ridingEntity instanceof EntityLiving)) {
/*  86:106 */         ((EntityLiving)this.theEntity.ridingEntity).getNavigator().tryMoveToEntityLiving(this.target, this.moveSpeed);
/*  87:108 */       } else if (!this.ownerPathfinder.tryMoveToEntityLiving(this.target, 2.0D)) {
/*  88:110 */         if ((this.theEntity.getDistanceSqToEntity(this.target) >= 288.0D) && (this.teleports))
/*  89:    */         {
/*  90:112 */           int i = MathHelper.floor_double(this.target.posX) - 2;
/*  91:113 */           int j = MathHelper.floor_double(this.target.posZ) - 2;
/*  92:114 */           int k = MathHelper.floor_double(this.target.boundingBox.minY);
/*  93:116 */           for (int l = 0; l <= 4; l++) {
/*  94:118 */             for (int i1 = 0; i1 <= 4; i1++) {
/*  95:120 */               if (((l < 1) || (i1 < 1) || (l > 3) || (i1 > 3)) && (World.doesBlockHaveSolidTopSurface(this.theWorld, i + l, k - 1, j + i1)) && (this.theWorld.isAirBlock(i + l, k, j + i1)) && (this.theWorld.isAirBlock(i + l, k + 1, j + i1)))
/*  96:    */               {
/*  97:122 */                 if (this.theEntity.ridingEntity != null) {
/*  98:123 */                   this.theEntity.ridingEntity.setLocationAndAngles(i + l + 0.5F, k, j + i1 + 0.5F, this.theEntity.rotationYaw, this.theEntity.rotationPitch);
/*  99:    */                 }
/* 100:124 */                 this.theEntity.setLocationAndAngles(i + l + 0.5F, k, j + i1 + 0.5F, this.theEntity.rotationYaw, this.theEntity.rotationPitch);
/* 101:125 */                 this.ownerPathfinder.clearPathEntity();
/* 102:126 */                 return;
/* 103:    */               }
/* 104:    */             }
/* 105:    */           }
/* 106:    */         }
/* 107:    */       }
/* 108:    */     }
/* 109:    */   }
/* 110:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIFollowOwner
 * JD-Core Version:    0.7.1
 */