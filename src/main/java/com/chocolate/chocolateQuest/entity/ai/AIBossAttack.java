/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.boss.EntityBaseBoss;
/*   4:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.ai.EntityAIBase;
/*   7:    */ import net.minecraft.entity.ai.EntityJumpHelper;
/*   8:    */ import net.minecraft.entity.ai.EntityLookHelper;
/*   9:    */ import net.minecraft.entity.ai.EntityMoveHelper;
/*  10:    */ import net.minecraft.pathfinding.PathEntity;
/*  11:    */ import net.minecraft.pathfinding.PathNavigate;
/*  12:    */ import net.minecraft.util.AxisAlignedBB;
/*  13:    */ import net.minecraft.util.MathHelper;
/*  14:    */ import net.minecraft.world.World;
/*  15:    */ 
/*  16:    */ public class AIBossAttack
/*  17:    */   extends EntityAIBase
/*  18:    */ {
/*  19:    */   World worldObj;
/*  20:    */   EntityBaseBoss attacker;
/*  21:    */   EntityLivingBase entityTarget;
/*  22:    */   float moveSpeed;
/*  23:    */   boolean needsVision;
/*  24:    */   PathEntity pathEntity;
/*  25:    */   private int attackTimer;
/*  26:    */   
/*  27:    */   public AIBossAttack(EntityBaseBoss par1EntityLiving, float speed, boolean par3)
/*  28:    */   {
/*  29: 24 */     this.attacker = par1EntityLiving;
/*  30: 25 */     this.worldObj = par1EntityLiving.worldObj;
/*  31: 26 */     this.moveSpeed = speed;
/*  32: 27 */     this.needsVision = par3;
/*  33: 28 */     setMutexBits(3);
/*  34:    */   }
/*  35:    */   
/*  36:    */   public boolean shouldExecute()
/*  37:    */   {
/*  38: 36 */     EntityLivingBase entityliving = this.attacker.getAttackTarget();
/*  39: 37 */     if (entityliving == null) {
/*  40: 39 */       return false;
/*  41:    */     }
/*  42: 43 */     this.entityTarget = entityliving;
/*  43:    */     
/*  44: 45 */     return true;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public boolean continueExecuting()
/*  48:    */   {
/*  49: 54 */     EntityLivingBase entityliving = this.attacker.getAttackTarget();
/*  50: 55 */     if (entityliving == null) {
/*  51: 57 */       return false;
/*  52:    */     }
/*  53: 59 */     if ((!entityliving.isEntityEqual(this.entityTarget)) && 
/*  54: 60 */       (this.attacker.getDistanceSqToEntity(entityliving) < this.attacker.getDistanceSqToEntity(this.entityTarget))) {
/*  55: 61 */       return false;
/*  56:    */     }
/*  57: 63 */     if (!this.entityTarget.isEntityAlive()) {
/*  58: 65 */       return false;
/*  59:    */     }
/*  60: 67 */     return this.attacker.isWithinHomeDistance(MathHelper.floor_double(this.entityTarget.posX), MathHelper.floor_double(this.entityTarget.posY), MathHelper.floor_double(this.entityTarget.posZ));
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void startExecuting()
/*  64:    */   {
/*  65: 75 */     if (!this.attacker.isAttacking())
/*  66:    */     {
/*  67: 77 */       this.attacker.getNavigator().setPath(this.pathEntity, this.moveSpeed);
/*  68: 78 */       this.attackTimer = 0;
/*  69:    */     }
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void resetTask()
/*  73:    */   {
/*  74: 87 */     this.entityTarget = null;
/*  75: 88 */     this.attacker.getNavigator().clearPathEntity();
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void updateTask()
/*  79:    */   {
/*  80: 97 */     if (!this.attacker.attackInProgress()) {
/*  81: 98 */       this.attacker.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);
/*  82:    */     }
/*  83: 99 */     int angle = (int)MathHelper.wrapAngleTo180_double(BDHelper.getAngleBetweenEntities(this.attacker, this.entityTarget) - this.attacker.rotationYaw);
/*  84:100 */     double d1 = this.attacker.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ);
/*  85:102 */     if (this.attacker.shouldMoveToEntity(d1, this.entityTarget))
/*  86:    */     {
/*  87:109 */       float speed = this.moveSpeed;
/*  88:110 */       if (angle != 0) {
/*  89:111 */         speed = 0.0F;
/*  90:    */       }
/*  91:112 */       this.attacker.getMoveHelper().setMoveTo(this.entityTarget.posX, this.entityTarget.posY, this.entityTarget.posZ, speed);
/*  92:113 */       if ((this.attacker.isCollidedHorizontally) && (this.attacker.onGround))
/*  93:    */       {
/*  94:114 */         this.attacker.getJumpHelper().setJumping();
/*  95:115 */         this.attacker.motionY = (this.attacker.getScaleSize() / 20.0F);
/*  96:    */       }
/*  97:    */     }
/*  98:    */     else
/*  99:    */     {
/* 100:120 */       this.attacker.getNavigator().clearPathEntity();
/* 101:    */     }
/* 102:123 */     this.attacker.attackEntity(this.entityTarget, (float)d1);
/* 103:    */   }
/* 104:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIBossAttack
 * JD-Core Version:    0.7.1
 */