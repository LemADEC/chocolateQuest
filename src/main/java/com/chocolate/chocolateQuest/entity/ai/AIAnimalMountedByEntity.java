/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   5:    */ import net.minecraft.block.Block;
/*   6:    */ import net.minecraft.block.BlockSlab;
/*   7:    */ import net.minecraft.entity.EntityLiving;
/*   8:    */ import net.minecraft.entity.EntityLivingBase;
/*   9:    */ import net.minecraft.entity.ai.EntityAIControlledByPlayer;
/*  10:    */ import net.minecraft.entity.ai.EntityJumpHelper;
/*  11:    */ import net.minecraft.pathfinding.PathFinder;
/*  12:    */ import net.minecraft.pathfinding.PathNavigate;
/*  13:    */ import net.minecraft.pathfinding.PathPoint;
/*  14:    */ import net.minecraft.util.MathHelper;
/*  15:    */ import net.minecraft.world.World;
/*  16:    */ 
/*  17:    */ public class AIAnimalMountedByEntity
/*  18:    */   extends EntityAIControlledByPlayer
/*  19:    */ {
/*  20:    */   private EntityLiving entityHost;
/*  21:    */   private final float maxSpeed;
/*  22: 20 */   private float currentSpeed = 0.0F;
/*  23: 22 */   float acceleration = 0.02F;
/*  24:    */   EntityHumanBase rider;
/*  25:    */   
/*  26:    */   public AIAnimalMountedByEntity(EntityLiving par1EntityLiving, float maxSpeed)
/*  27:    */   {
/*  28: 28 */     super(par1EntityLiving, maxSpeed);
/*  29: 29 */     this.entityHost = par1EntityLiving;
/*  30: 30 */     this.maxSpeed = maxSpeed;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public boolean shouldExecute()
/*  34:    */   {
/*  35: 35 */     if (!(this.entityHost.riddenByEntity instanceof EntityHumanBase)) {
/*  36: 36 */       return false;
/*  37:    */     }
/*  38: 38 */     EntityHumanBase e = (EntityHumanBase)this.entityHost.riddenByEntity;
/*  39: 39 */     if (e.currentPos != null) {
/*  40: 40 */       return false;
/*  41:    */     }
/*  42: 42 */     this.rider = e;
/*  43:    */     
/*  44:    */ 
/*  45: 45 */     return false;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void resetTask()
/*  49:    */   {
/*  50: 50 */     this.currentSpeed = 0.0F;
/*  51:    */   }
/*  52:    */   
/*  53: 52 */   float prevRotation = 0.0F;
/*  54:    */   
/*  55:    */   public void updateTask()
/*  56:    */   {
/*  57: 56 */     if (this.entityHost.getNavigator().getPath() != null) {
/*  58: 58 */       this.entityHost.getNavigator().clearPathEntity();
/*  59:    */     }
/*  60: 61 */     float rotVariation = MathHelper.wrapAngleTo180_float(this.rider.rotationYawHead - this.prevRotation);
/*  61: 62 */     float minVariation = 5.0F;
/*  62: 63 */     if (rotVariation > minVariation) {
/*  63: 64 */       rotVariation = minVariation;
/*  64:    */     }
/*  65: 66 */     if (rotVariation < -minVariation) {
/*  66: 67 */       rotVariation = -minVariation;
/*  67:    */     }
/*  68: 69 */     this.entityHost.rotationYaw = MathHelper.wrapAngleTo180_float(this.prevRotation + rotVariation);
/*  69: 70 */     this.rider.rotationYaw = this.entityHost.rotationYaw;
/*  70: 71 */     this.prevRotation = this.entityHost.rotationYaw;
/*  71:    */     
/*  72: 73 */     updateAcceleration();
/*  73: 74 */     this.currentSpeed += this.acceleration;
/*  74: 75 */     this.currentSpeed = Math.max(this.currentSpeed, 0.01F);
/*  75: 76 */     this.currentSpeed = Math.min(this.currentSpeed, this.maxSpeed);
/*  76: 77 */     checkToJump(this.currentSpeed);
/*  77: 78 */     this.entityHost.moveEntityWithHeading(0.0F, this.currentSpeed);
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void updateAcceleration()
/*  81:    */   {
/*  82: 83 */     EntityHumanBase rider = (EntityHumanBase)this.entityHost.riddenByEntity;
/*  83: 85 */     if (rider.getAttackTarget() != null)
/*  84:    */     {
/*  85: 88 */       EntityLivingBase target = rider.getAttackTarget();
/*  86:    */       
/*  87: 90 */       double rotDiff = BDHelper.getAngleBetweenEntities(this.entityHost, target);
/*  88: 91 */       double rot = MathHelper.wrapAngleTo180_double(rotDiff - this.entityHost.rotationYaw);
/*  89: 92 */       rot = Math.abs(rot);
/*  90: 93 */       if (rot > 20.0D)
/*  91:    */       {
/*  92: 95 */         this.acceleration = -0.01F;
/*  93:    */       }
/*  94:    */       else
/*  95:    */       {
/*  96: 98 */         this.acceleration = 0.025F;
/*  97: 99 */         boostSpeed();
/*  98:    */       }
/*  99:    */     }
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void checkToJump(float currentSpeedTemp)
/* 103:    */   {
/* 104:106 */     int i = MathHelper.floor_double(this.entityHost.posX);
/* 105:107 */     int j = MathHelper.floor_double(this.entityHost.posY);
/* 106:108 */     int k = MathHelper.floor_double(this.entityHost.posZ);
/* 107:    */     
/* 108:110 */     float despX = MathHelper.sin(this.entityHost.rotationYaw * 3.141593F / 180.0F);
/* 109:111 */     float despZ = MathHelper.cos(this.entityHost.rotationYaw * 3.141593F / 180.0F);
/* 110:112 */     float f7 = this.entityHost.getAIMoveSpeed() * currentSpeedTemp / Math.max(currentSpeedTemp, 1.0F);
/* 111:113 */     currentSpeedTemp *= f7;
/* 112:114 */     despX = -(currentSpeedTemp * despX);
/* 113:115 */     despZ = currentSpeedTemp * despZ;
/* 114:116 */     if (MathHelper.abs(despX) > MathHelper.abs(despZ))
/* 115:    */     {
/* 116:118 */       if (despX < 0.0F) {
/* 117:120 */         despX -= this.entityHost.width / 2.0F;
/* 118:    */       }
/* 119:123 */       if (despX > 0.0F) {
/* 120:125 */         despX += this.entityHost.width / 2.0F;
/* 121:    */       }
/* 122:128 */       despZ = 0.0F;
/* 123:    */     }
/* 124:    */     else
/* 125:    */     {
/* 126:132 */       despX = 0.0F;
/* 127:134 */       if (despZ < 0.0F) {
/* 128:136 */         despZ -= this.entityHost.width / 2.0F;
/* 129:    */       }
/* 130:139 */       if (despZ > 0.0F) {
/* 131:141 */         despZ += this.entityHost.width / 2.0F;
/* 132:    */       }
/* 133:    */     }
/* 134:145 */     int xNext = MathHelper.floor_double(this.entityHost.posX + despX);
/* 135:146 */     int zNext = MathHelper.floor_double(this.entityHost.posZ + despZ);
/* 136:147 */     PathPoint pathpoint = new PathPoint(MathHelper.floor_float(this.entityHost.width + 1.0F), MathHelper.floor_float(this.entityHost.height + this.rider.height + 1.0F), MathHelper.floor_float(this.entityHost.width + 1.0F));
/* 137:148 */     if ((i != xNext) || (k != zNext))
/* 138:    */     {
/* 139:150 */       Block block = this.entityHost.worldObj.getBlock(i, j, k);
/* 140:151 */       Block nextBlockID = this.entityHost.worldObj.getBlock(i, j - 1, k);
/* 141:152 */       boolean flag = (isBlockHalf(block)) && (isBlockHalf(nextBlockID));
/* 142:154 */       if ((!flag) && (PathFinder.func_82565_a(this.entityHost, xNext, j, zNext, pathpoint, false, false, true) == 0) && (PathFinder.func_82565_a(this.entityHost, i, j + 1, k, pathpoint, false, false, true) == 1) && (PathFinder.func_82565_a(this.entityHost, xNext, j + 1, zNext, pathpoint, false, false, true) == 1)) {
/* 143:158 */         this.entityHost.getJumpHelper().setJumping();
/* 144:    */       }
/* 145:    */     }
/* 146:    */   }
/* 147:    */   
/* 148:    */   private boolean isBlockHalf(Block par1)
/* 149:    */   {
/* 150:165 */     return (par1.getRenderType() == 10) || ((par1 instanceof BlockSlab));
/* 151:    */   }
/* 152:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIAnimalMountedByEntity
 * JD-Core Version:    0.7.1
 */