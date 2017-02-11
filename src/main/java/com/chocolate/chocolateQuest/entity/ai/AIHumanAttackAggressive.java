/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.ai.EntityLookHelper;
/*   7:    */ import net.minecraft.entity.ai.EntitySenses;
/*   8:    */ import net.minecraft.entity.player.EntityPlayer;
/*   9:    */ import net.minecraft.pathfinding.PathEntity;
/*  10:    */ import net.minecraft.pathfinding.PathNavigate;
/*  11:    */ import net.minecraft.util.AxisAlignedBB;
/*  12:    */ import net.minecraft.world.World;
/*  13:    */ 
/*  14:    */ public class AIHumanAttackAggressive
/*  15:    */   extends AIInteractBase
/*  16:    */ {
/*  17:    */   protected World worldObj;
/*  18:    */   protected int attackTick;
/*  19:    */   protected float moveSpeed;
/*  20:    */   protected boolean requireSight;
/*  21: 20 */   protected boolean isPlayerTarget = false;
/*  22:    */   PathEntity entityPathEntity;
/*  23:    */   Class classTarget;
/*  24:    */   private int pathFindingCooldown;
/*  25:    */   
/*  26:    */   public AIHumanAttackAggressive(EntityHumanBase par1EntityLiving, Class par2Class, float speed, boolean requiresSight)
/*  27:    */   {
/*  28: 30 */     this(par1EntityLiving, speed, requiresSight);
/*  29: 31 */     this.classTarget = par2Class;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public AIHumanAttackAggressive(EntityHumanBase par1EntityLiving, float speed, boolean requireSight)
/*  33:    */   {
/*  34: 36 */     super(par1EntityLiving);
/*  35: 37 */     this.owner = par1EntityLiving;
/*  36: 38 */     this.worldObj = par1EntityLiving.worldObj;
/*  37: 39 */     this.moveSpeed = speed;
/*  38: 40 */     this.requireSight = requireSight;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public boolean shouldExecute()
/*  42:    */   {
/*  43: 49 */     if (super.shouldExecute())
/*  44:    */     {
/*  45: 51 */       EntityLivingBase var1 = this.owner.getAttackTarget();
/*  46: 52 */       this.entityTarget = var1;
/*  47: 53 */       return true;
/*  48:    */     }
/*  49: 55 */     return false;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public boolean continueExecuting()
/*  53:    */   {
/*  54: 64 */     EntityLivingBase var1 = this.owner.getAttackTarget();
/*  55: 65 */     return var1 != null;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void startExecuting()
/*  59:    */   {
/*  60: 75 */     if ((this.entityTarget instanceof EntityPlayer)) {
/*  61: 76 */       this.isPlayerTarget = true;
/*  62:    */     }
/*  63: 77 */     this.pathFindingCooldown = 0;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void resetTask()
/*  67:    */   {
/*  68: 85 */     super.resetTask();
/*  69: 86 */     this.owner.setDefending(false);
/*  70: 87 */     this.entityPathEntity = null;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void updateTask()
/*  74:    */   {
/*  75: 94 */     double dist = this.owner.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ);
/*  76: 95 */     this.owner.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);
/*  77: 96 */     boolean canSeeTarget = this.owner.getEntitySenses().canSee(this.entityTarget);
/*  78: 97 */     boolean shouldStayAway = false;
/*  79: 98 */     boolean havePath = this.owner.onGround;
/*  80: 99 */     if ((dist > this.owner.getDistanceToAttack()) || (!canSeeTarget))
/*  81:    */     {
/*  82:101 */       if (--this.pathFindingCooldown <= 0)
/*  83:    */       {
/*  84:103 */         int inteligenceLevel = this.owner.getInteligence();
/*  85:104 */         this.pathFindingCooldown = (inteligenceLevel + this.owner.getRNG().nextInt(inteligenceLevel + 2));
/*  86:105 */         if ((this.owner.canSprint()) && (dist > 16.0D)) {
/*  87:107 */           this.owner.startSprinting();
/*  88:    */         }
/*  89:109 */         if (!tryToMoveToEntity()) {
/*  90:112 */           shouldStayAway = true;
/*  91:    */         }
/*  92:113 */         if (!shouldStayAway) {}
/*  93:    */       }
/*  94:    */     }
/*  95:    */     else {
/*  96:126 */       getNavigator().clearPathEntity();
/*  97:    */     }
/*  98:127 */     attackTarget(dist);
/*  99:    */   }
/* 100:    */   
/* 101:    */   public boolean tryToMoveToEntity()
/* 102:    */   {
/* 103:132 */     return tryMoveToXYZ(this.entityTarget.posX, this.entityTarget.posY, this.entityTarget.posZ, 1.0F);
/* 104:    */   }
/* 105:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIHumanAttackAggressive
 * JD-Core Version:    0.7.1
 */