/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.boss.EntityBaseBoss;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*   6:    */ import net.minecraft.entity.ai.EntityAIBase;
/*   7:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*   8:    */ import net.minecraft.util.ChunkCoordinates;
/*   9:    */ import net.minecraft.util.MathHelper;
/*  10:    */ import net.minecraft.world.World;
/*  11:    */ 
/*  12:    */ public class AIFlyRoam
/*  13:    */   extends EntityAIBase
/*  14:    */ {
/*  15:    */   private EntityBaseBoss owner;
/*  16:    */   protected int defaultFlyHeight;
/*  17: 13 */   int roamDistance = 60;
/*  18:    */   float distanceMoved;
/*  19:    */   int destX;
/*  20:    */   int destY;
/*  21:    */   int destZ;
/*  22:    */   
/*  23:    */   public AIFlyRoam(EntityBaseBoss par1EntityLiving, int flyHeight)
/*  24:    */   {
/*  25: 16 */     this.owner = par1EntityLiving;
/*  26: 17 */     setMutexBits(4);
/*  27: 18 */     this.defaultFlyHeight = flyHeight;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public boolean shouldExecute()
/*  31:    */   {
/*  32: 24 */     return this.owner.getAttackTarget() == null;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public boolean continueExecuting()
/*  36:    */   {
/*  37: 30 */     return null == this.owner.getAttackTarget();
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void startExecuting()
/*  41:    */   {
/*  42: 35 */     changeDest();
/*  43: 36 */     super.startExecuting();
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void updateTask()
/*  47:    */   {
/*  48: 44 */     double px = this.owner.posX - this.destX;
/*  49: 45 */     double pz = this.owner.posZ - this.destZ;
/*  50: 46 */     double py = this.owner.posY - 90.0D;
/*  51:    */     
/*  52: 48 */     double angleEntityHome = -MathHelper.wrapAngleTo180_double(Math.toDegrees(Math.atan2(px, pz)) - 180.0D);
/*  53: 49 */     double rotDiff = angleEntityHome - MathHelper.wrapAngleTo180_double(this.owner.rotationYaw);
/*  54:    */     
/*  55: 51 */     float rotAngle = 4.0F;
/*  56: 52 */     float rotSpeed = 4.1F;
/*  57: 53 */     if ((rotDiff > rotAngle) || (rotDiff < -rotAngle)) {
/*  58: 55 */       this.owner.rotationYaw = (this.owner.prevRotationYaw + rotSpeed);
/*  59:    */     }
/*  60: 59 */     double moveSpeed = this.owner.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
/*  61: 60 */     double ry = Math.toRadians(this.owner.rotationYaw - 180.0F);
/*  62: 61 */     this.owner.motionX = (Math.sin(ry) * moveSpeed);
/*  63: 62 */     this.owner.motionZ = (-Math.cos(ry) * moveSpeed);
/*  64:    */     
/*  65:    */ 
/*  66: 65 */     handleMotionY();
/*  67: 68 */     if ((this.owner.posX <= this.destX + 5) && (this.owner.posX >= this.destX - 5) && (this.owner.posZ <= this.destZ + 5) && (this.owner.posZ >= this.destZ - 5)) {
/*  68: 70 */       changeDest();
/*  69:    */     }
/*  70:    */   }
/*  71:    */   
/*  72:    */   protected void handleMotionY()
/*  73:    */   {
/*  74: 76 */     float d = MathHelper.cos(this.distanceMoved / 5.0F) * 2.0F;
/*  75: 77 */     int blockX = MathHelper.floor_double(this.owner.posX + this.owner.motionX * 10.0D);
/*  76: 78 */     int blockZ = MathHelper.floor_double(this.owner.posZ + this.owner.motionZ * 10.0D);
/*  77: 79 */     int flyHeight = this.defaultFlyHeight;
/*  78: 81 */     if (this.owner.posY > this.owner.worldObj.getTopSolidOrLiquidBlock(MathHelper.floor_double(this.owner.posX), MathHelper.floor_double(this.owner.posZ))) {
/*  79: 82 */       flyHeight = this.owner.worldObj.getTopSolidOrLiquidBlock(blockX, blockZ) + 8;
/*  80:    */     }
/*  81: 83 */     double extraMotionY = 0.0D;
/*  82: 84 */     if (this.owner.posY < flyHeight)
/*  83:    */     {
/*  84: 86 */       if (this.owner.rotationPitch > -0.3D) {
/*  85: 87 */         this.owner.rotationPitch += 0.01F;
/*  86:    */       }
/*  87: 88 */       extraMotionY += 0.4000000059604645D;
/*  88: 89 */       this.owner.motionX /= 2.0D;
/*  89: 90 */       this.owner.motionZ /= 2.0D;
/*  90:    */     }
/*  91: 92 */     else if (this.owner.posY > flyHeight + 5)
/*  92:    */     {
/*  93: 94 */       if (this.owner.rotationPitch < 0.3D) {
/*  94: 95 */         this.owner.rotationPitch -= 0.01F;
/*  95:    */       }
/*  96: 96 */       extraMotionY -= 0.4000000059604645D;
/*  97: 97 */       this.owner.motionX /= 2.0D;
/*  98: 98 */       this.owner.motionZ /= 2.0D;
/*  99:    */     }
/* 100:    */     else
/* 101:    */     {
/* 102:101 */       this.owner.rotationPitch = ((float)Math.toDegrees(d));
/* 103:    */     }
/* 104:102 */     double moveSpeed = this.owner.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
/* 105:103 */     this.owner.motionY = (-Math.sin(this.owner.rotationPitch) * moveSpeed / 5.0D + extraMotionY);
/* 106:104 */     this.distanceMoved = ((float)(this.distanceMoved + (Math.abs(this.owner.motionX) + Math.abs(this.owner.motionZ))));
/* 107:    */   }
/* 108:    */   
/* 109:    */   protected void changeDest()
/* 110:    */   {
/* 111:109 */     if (this.owner.hasHome())
/* 112:    */     {
/* 113:111 */       this.destX = (this.owner.getHomePosition().posX + this.owner.getRNG().nextInt(this.roamDistance * 2) - this.roamDistance);
/* 114:112 */       this.destZ = (this.owner.getHomePosition().posZ + this.owner.getRNG().nextInt(this.roamDistance * 2) - this.roamDistance);
/* 115:    */     }
/* 116:    */     else
/* 117:    */     {
/* 118:116 */       this.destX = (MathHelper.floor_double(this.owner.posX) + this.owner.getRNG().nextInt(this.roamDistance * 2) - this.roamDistance);
/* 119:117 */       this.destZ = (MathHelper.floor_double(this.owner.posZ) + this.owner.getRNG().nextInt(this.roamDistance * 2) - this.roamDistance);
/* 120:    */     }
/* 121:    */   }
/* 122:    */   
/* 123:    */   public void resetTask()
/* 124:    */   {
/* 125:123 */     this.destX = this.owner.getHomePosition().posX;
/* 126:124 */     this.destZ = this.owner.getHomePosition().posZ;
/* 127:125 */     super.resetTask();
/* 128:    */   }
/* 129:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIFlyRoam
 * JD-Core Version:    0.7.1
 */