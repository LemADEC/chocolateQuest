/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import net.minecraft.block.Block;
/*   5:    */ import net.minecraft.block.material.Material;
/*   6:    */ import net.minecraft.entity.EntityLivingBase;
/*   7:    */ import net.minecraft.entity.ai.EntityLookHelper;
/*   8:    */ import net.minecraft.entity.ai.EntitySenses;
/*   9:    */ import net.minecraft.pathfinding.PathNavigate;
/*  10:    */ import net.minecraft.util.AxisAlignedBB;
/*  11:    */ import net.minecraft.util.MathHelper;
/*  12:    */ import net.minecraft.world.World;
/*  13:    */ 
/*  14:    */ public class AIHumanAttackEvasive
/*  15:    */   extends AIInteractBase
/*  16:    */ {
/*  17:    */   World worldObj;
/*  18:    */   
/*  19:    */   public AIHumanAttackEvasive(EntityHumanBase par1EntityLiving, float par2)
/*  20:    */   {
/*  21: 17 */     super(par1EntityLiving);
/*  22: 18 */     this.owner = par1EntityLiving;
/*  23: 19 */     this.worldObj = par1EntityLiving.worldObj;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public boolean shouldExecute()
/*  27:    */   {
/*  28: 27 */     if (super.shouldExecute())
/*  29:    */     {
/*  30: 29 */       EntityLivingBase entityliving = this.owner.getAttackTarget();
/*  31: 30 */       if ((this.owner.party != null) && 
/*  32: 31 */         (this.owner.party.getLeader() != this.owner))
/*  33:    */       {
/*  34: 32 */         double distToleader = this.owner.getDistanceSqToEntity(this.owner.party.getLeader());
/*  35: 33 */         if (distToleader > this.owner.partyDistanceToLeader * this.owner.partyDistanceToLeader * Math.max(1, 16 - this.owner.partyDistanceToLeader)) {
/*  36: 34 */           return false;
/*  37:    */         }
/*  38:    */       }
/*  39: 37 */       this.entityTarget = entityliving;
/*  40: 38 */       return true;
/*  41:    */     }
/*  42: 40 */     return false;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public boolean continueExecuting()
/*  46:    */   {
/*  47: 49 */     return (shouldExecute()) && (super.continueExecuting());
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void resetTask()
/*  51:    */   {
/*  52: 58 */     super.resetTask();
/*  53: 59 */     this.owner.getNavigator().clearPathEntity();
/*  54: 60 */     this.owner.moveForwardHuman = 0.0F;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void updateTask()
/*  58:    */   {
/*  59: 68 */     double distance = this.owner.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ);
/*  60: 69 */     boolean canSee = this.owner.getEntitySenses().canSee(this.entityTarget);
/*  61: 70 */     this.owner.getLookHelper().setLookPositionWithEntity(this.entityTarget, 0.0F, 0.0F);
/*  62: 71 */     this.owner.rotationYaw = this.owner.rotationYawHead;
/*  63: 72 */     this.owner.moveForwardHuman = -1.0E-004F;
/*  64: 73 */     boolean stayInFormation = false;
/*  65: 74 */     if ((canSee) && (distance < 64.0D))
/*  66:    */     {
/*  67: 76 */       double ry = Math.toRadians(this.owner.rotationYaw - 180.0F);
/*  68: 77 */       int x = MathHelper.floor_double(this.owner.posX - Math.sin(ry) * 6.0D);
/*  69: 78 */       int z = MathHelper.floor_double(this.owner.posZ + Math.cos(ry) * 6.0D);
/*  70: 79 */       Material mat = this.owner.worldObj.getBlock(x, MathHelper.floor_double(this.owner.posY) - 1, z).getMaterial();
/*  71: 80 */       boolean move = false;
/*  72: 81 */       if ((mat != Material.air) && (mat != Material.lava) && (mat.isSolid()))
/*  73:    */       {
/*  74: 83 */         move = true;
/*  75:    */       }
/*  76:    */       else
/*  77:    */       {
/*  78: 86 */         mat = this.owner.worldObj.getBlock(x, MathHelper.floor_double(this.owner.posY) - 2, z).getMaterial();
/*  79: 87 */         if (mat.isSolid()) {
/*  80: 88 */           move = true;
/*  81:    */         }
/*  82:    */       }
/*  83: 90 */       if (move) {
/*  84: 91 */         this.owner.moveForwardHuman = -0.1F;
/*  85:    */       }
/*  86:    */     }
/*  87: 93 */     else if (this.owner.party != null)
/*  88:    */     {
/*  89: 94 */       stayInFormation();
/*  90: 95 */       stayInFormation = true;
/*  91:    */     }
/*  92: 98 */     if (!attackTarget(distance)) {
/*  93: 99 */       if (!stayInFormation) {
/*  94:100 */         tryMoveToXYZ(this.entityTarget.posX, this.entityTarget.posY, this.entityTarget.posZ, 1.0F);
/*  95:102 */       } else if (!stayInFormation) {
/*  96:103 */         getNavigator().clearPathEntity();
/*  97:    */       }
/*  98:    */     }
/*  99:    */   }
/* 100:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIHumanAttackEvasive
 * JD-Core Version:    0.7.1
 */