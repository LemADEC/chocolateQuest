/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.block.Block;
/*   6:    */ import net.minecraft.block.material.Material;
/*   7:    */ import net.minecraft.entity.ai.EntityAIBase;
/*   8:    */ import net.minecraft.entity.ai.EntityLookHelper;
/*   9:    */ import net.minecraft.entity.ai.EntitySenses;
/*  10:    */ import net.minecraft.pathfinding.PathNavigate;
/*  11:    */ import net.minecraft.util.MathHelper;
/*  12:    */ import net.minecraft.world.World;
/*  13:    */ 
/*  14:    */ public class AIHumanPotion
/*  15:    */   extends EntityAIBase
/*  16:    */ {
/*  17:    */   protected World worldObj;
/*  18:    */   protected EntityHumanBase owner;
/*  19:    */   protected int attackCooldown;
/*  20:    */   
/*  21:    */   public AIHumanPotion(EntityHumanBase par1EntityLiving)
/*  22:    */   {
/*  23: 18 */     this.owner = par1EntityLiving;
/*  24: 19 */     this.worldObj = par1EntityLiving.worldObj;
/*  25: 20 */     setMutexBits(3);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public boolean shouldExecute()
/*  29:    */   {
/*  30: 29 */     return (this.owner.potionCount > 0) && (this.owner.getHealth() <= Math.max(this.owner.getMaxHealth() * 0.1D, 6.0D));
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void startExecuting()
/*  34:    */   {
/*  35: 34 */     this.owner.getNavigator().clearPathEntity();
/*  36: 35 */     this.attackCooldown = 0;
/*  37: 36 */     this.owner.setDefending(true);
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void resetTask()
/*  41:    */   {
/*  42: 41 */     this.owner.getNavigator().clearPathEntity();
/*  43: 42 */     this.owner.setDefending(false);
/*  44: 43 */     this.owner.moveForwardHuman = 0.0F;
/*  45: 44 */     this.owner.setEating(false);
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void updateTask()
/*  49:    */   {
/*  50: 49 */     boolean flag = true;
/*  51: 50 */     int timeTillPotion = 90;
/*  52:    */     double dist;
/*  53: 51 */     if ((this.owner.getAttackTarget() != null) && (this.attackCooldown < timeTillPotion))
/*  54:    */     {
/*  55: 53 */       this.owner.getLookHelper().setLookPositionWithEntity(this.owner.getAttackTarget(), 30.0F, 30.0F);
/*  56: 54 */       this.owner.rotationYaw = this.owner.rotationYawHead;
/*  57:    */       
/*  58: 56 */       double ry = Math.toRadians(this.owner.rotationYaw - 180.0F);
/*  59: 57 */       int x = MathHelper.floor_double(this.owner.posX - Math.sin(ry) * 3.0D);
/*  60: 58 */       int z = MathHelper.floor_double(this.owner.posZ + Math.cos(ry) * 3.0D);
/*  61:    */       
/*  62: 60 */       Material mat = this.owner.worldObj.getBlock(x, MathHelper.floor_double(this.owner.posY) - 1, z).getMaterial();
/*  63: 61 */       boolean move = false;
/*  64: 62 */       if ((mat != Material.air) && (mat != Material.lava) && (mat.isSolid()))
/*  65:    */       {
/*  66: 64 */         move = true;
/*  67:    */       }
/*  68:    */       else
/*  69:    */       {
/*  70: 67 */         mat = this.owner.worldObj.getBlock(x, MathHelper.floor_double(this.owner.posY) - 2, z).getMaterial();
/*  71: 68 */         if (mat.isSolid()) {
/*  72: 69 */           move = true;
/*  73:    */         }
/*  74:    */       }
/*  75: 71 */       if (move) {
/*  76: 72 */         this.owner.moveForwardHuman = -0.3F;
/*  77:    */       } else {
/*  78: 74 */         this.owner.moveForwardHuman = 0.0F;
/*  79:    */       }
/*  80: 76 */       if ((this.owner.getDistanceSqToEntity(this.owner.getAttackTarget()) > 100.0D) || (!this.owner.getEntitySenses().canSee(this.owner.getAttackTarget())))
/*  81:    */       {
/*  82: 77 */         this.attackCooldown = timeTillPotion;
/*  83: 78 */         this.owner.moveForwardHuman = 0.0F;
/*  84:    */       }
/*  85: 80 */       if (this.owner.isCollidedHorizontally) {
/*  86: 81 */         this.attackCooldown += 5;
/*  87:    */       }
/*  88: 83 */       dist = this.owner.getDistanceSqToEntity(this.owner.getAttackTarget());
/*  89:    */     }
/*  90: 85 */     this.attackCooldown += 1;
/*  91: 86 */     if (this.attackCooldown > timeTillPotion)
/*  92:    */     {
/*  93: 88 */       if (this.owner.onGround)
/*  94:    */       {
/*  95: 90 */         this.owner.motionX = 0.0D;
/*  96: 91 */         this.owner.motionZ = 0.0D;
/*  97:    */       }
/*  98: 93 */       if (!this.owner.isEating())
/*  99:    */       {
/* 100: 95 */         this.owner.setEating(true);
/* 101: 96 */         if (this.owner.isDefending()) {
/* 102: 97 */           this.owner.toogleBlocking();
/* 103:    */         }
/* 104:    */       }
/* 105: 99 */       this.owner.swingItem();
/* 106:100 */       if (this.attackCooldown > timeTillPotion + 50)
/* 107:    */       {
/* 108:102 */         this.owner.worldObj.playSoundAtEntity(this.owner, "random.burp", 0.5F, this.owner.worldObj.rand.nextFloat() * 0.1F + 0.9F);
/* 109:103 */         this.owner.heal(20.0F);
/* 110:104 */         this.owner.potionCount -= 1;
/* 111:105 */         this.owner.setEating(false);
/* 112:    */       }
/* 113:    */       else
/* 114:    */       {
/* 115:108 */         this.owner.worldObj.playSoundAtEntity(this.owner, "random.drink", 0.5F, this.owner.worldObj.rand.nextFloat() * 0.1F + 0.9F);
/* 116:    */       }
/* 117:    */     }
/* 118:    */   }
/* 119:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIHumanPotion
 * JD-Core Version:    0.7.1
 */