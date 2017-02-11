/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   5:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   6:    */ import com.chocolate.chocolateQuest.packets.PacketSpawnParticlesAround;
/*   7:    */ import java.util.Random;
/*   8:    */ import net.minecraft.entity.EntityLiving;
/*   9:    */ import net.minecraft.entity.EntityLivingBase;
/*  10:    */ import net.minecraft.entity.ai.EntityLookHelper;
/*  11:    */ import net.minecraft.entity.ai.EntitySenses;
/*  12:    */ import net.minecraft.item.ItemStack;
/*  13:    */ import net.minecraft.pathfinding.PathNavigate;
/*  14:    */ import net.minecraft.util.AxisAlignedBB;
/*  15:    */ import net.minecraft.util.MathHelper;
/*  16:    */ import net.minecraft.world.World;
/*  17:    */ 
/*  18:    */ public class AIHumanAttackHeal
/*  19:    */   extends AIHumanAttackAggressive
/*  20:    */ {
/*  21:    */   private int pathFindingCooldown;
/*  22:    */   
/*  23:    */   public AIHumanAttackHeal(EntityHumanBase par1EntityLiving, Class par2Class, float par3, boolean par4, World world)
/*  24:    */   {
/*  25: 18 */     this(par1EntityLiving, par3, par4);
/*  26: 19 */     this.classTarget = par2Class;
/*  27: 20 */     this.worldObj = world;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public AIHumanAttackHeal(EntityHumanBase par1EntityLiving, float par2, boolean par3)
/*  31:    */   {
/*  32: 25 */     super(par1EntityLiving, par2, par3);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public boolean shouldExecute()
/*  36:    */   {
/*  37: 30 */     EntityLivingBase var1 = this.owner.getAttackTarget();
/*  38: 31 */     if (var1 == null) {
/*  39: 33 */       return false;
/*  40:    */     }
/*  41: 35 */     if (!this.owner.isSuitableTargetAlly(var1)) {
/*  42: 37 */       return false;
/*  43:    */     }
/*  44: 39 */     if ((var1.getHealth() >= var1.getMaxHealth()) || (var1 == this.owner))
/*  45:    */     {
/*  46: 41 */       this.owner.setAttackTarget(null);
/*  47: 42 */       return false;
/*  48:    */     }
/*  49: 46 */     this.entityTarget = var1;
/*  50: 47 */     this.entityPathEntity = this.owner.getNavigator().getPathToEntityLiving(this.entityTarget);
/*  51: 48 */     return (this.entityPathEntity != null) || (this.owner.ridingEntity != null);
/*  52:    */   }
/*  53:    */   
/*  54:    */   public boolean continueExecuting()
/*  55:    */   {
/*  56: 57 */     EntityLivingBase target = this.owner.getAttackTarget();
/*  57: 59 */     if (target == null) {
/*  58: 60 */       return false;
/*  59:    */     }
/*  60: 63 */     if (!this.owner.isSuitableTargetAlly(target)) {
/*  61: 65 */       return false;
/*  62:    */     }
/*  63: 68 */     return !this.requireSight ? false : !this.owner.getNavigator().noPath() ? true : !this.entityTarget.isEntityAlive() ? false : target == null ? false : this.owner.isWithinHomeDistance(MathHelper.floor_double(this.entityTarget.posX), MathHelper.floor_double(this.entityTarget.posY), MathHelper.floor_double(this.entityTarget.posZ));
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void startExecuting()
/*  67:    */   {
/*  68: 76 */     this.owner.getNavigator().setPath(this.entityPathEntity, this.moveSpeed);
/*  69: 77 */     this.pathFindingCooldown = 0;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void resetTask()
/*  73:    */   {
/*  74: 85 */     this.entityTarget = null;
/*  75: 86 */     this.owner.getNavigator().clearPathEntity();
/*  76: 87 */     this.owner.setDefending(false);
/*  77:    */   }
/*  78:    */   
/*  79:    */   public void updateTask()
/*  80:    */   {
/*  81: 95 */     this.owner.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);
/*  82: 97 */     if (((this.requireSight) || (this.owner.getEntitySenses().canSee(this.entityTarget))) && (--this.pathFindingCooldown <= 0))
/*  83:    */     {
/*  84: 99 */       this.pathFindingCooldown = (4 + this.owner.getRNG().nextInt(7));
/*  85:100 */       if ((this.owner.ridingEntity instanceof EntityLiving)) {
/*  86:102 */         ((EntityLiving)this.owner.ridingEntity).getNavigator().tryMoveToEntityLiving(this.entityTarget, 1.200000047683716D);
/*  87:    */       }
/*  88:104 */       this.owner.getNavigator().tryMoveToEntityLiving(this.entityTarget, this.moveSpeed);
/*  89:    */     }
/*  90:107 */     this.owner.attackTime = Math.max(this.owner.attackTime - 1, 0);
/*  91:108 */     double bounds = getMinDistanceToInteract();
/*  92:109 */     double dist = this.owner.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ);
/*  93:111 */     if ((this.owner.isDefending()) && (dist <= bounds * 2.0D)) {
/*  94:113 */       this.owner.setDefending(false);
/*  95:    */     }
/*  96:116 */     if (dist <= bounds) {
/*  97:118 */       if (this.owner.attackTime <= 0)
/*  98:    */       {
/*  99:120 */         this.owner.attackTime = this.owner.getAttackSpeed();
/* 100:122 */         if ((this.owner.getHeldItem() != null) && (this.owner.getHeldItem().getItem() == ChocolateQuest.staffHeal)) {
/* 101:124 */           this.owner.swingItem();
/* 102:126 */         } else if ((this.owner.leftHandItem != null) && (this.owner.leftHandItem.getItem() == ChocolateQuest.staffHeal)) {
/* 103:128 */           this.owner.swingLeftHand();
/* 104:    */         }
/* 105:131 */         this.entityTarget.heal(2.0F);
/* 106:132 */         if (!this.owner.worldObj.isRemote)
/* 107:    */         {
/* 108:134 */           this.entityTarget.worldObj.playSoundEffect((int)this.entityTarget.posX, (int)this.entityTarget.posY, (int)this.entityTarget.posZ, "chocolateQuest:magic", 1.0F, (1.0F + (this.entityTarget.worldObj.rand.nextFloat() - this.entityTarget.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
/* 109:    */           
/* 110:136 */           PacketSpawnParticlesAround packet = new PacketSpawnParticlesAround((byte)0, this.entityTarget.posX, this.entityTarget.posY + 1.6D, this.entityTarget.posZ);
/* 111:137 */           ChocolateQuest.channel.sendToAllAround(this.entityTarget, packet, 64);
/* 112:    */         }
/* 113:    */       }
/* 114:    */     }
/* 115:142 */     if (this.entityTarget.getHealth() >= this.entityTarget.getMaxHealth()) {
/* 116:144 */       this.owner.setAttackTarget(null);
/* 117:    */     }
/* 118:    */   }
/* 119:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIHumanAttackHeal
 * JD-Core Version:    0.7.1
 */