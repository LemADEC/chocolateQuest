/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.boss.EntityWyvern;
/*   4:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   5:    */ import java.util.Random;
/*   6:    */ import net.minecraft.entity.EntityLivingBase;
/*   7:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*   8:    */ import net.minecraft.entity.ai.EntityAIBase;
/*   9:    */ import net.minecraft.entity.ai.EntityLookHelper;
/*  10:    */ import net.minecraft.entity.ai.EntitySenses;
/*  11:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  12:    */ import net.minecraft.pathfinding.PathNavigate;
/*  13:    */ import net.minecraft.util.AxisAlignedBB;
/*  14:    */ import net.minecraft.util.MathHelper;
/*  15:    */ import net.minecraft.world.World;
/*  16:    */ 
/*  17:    */ public class AIFly
/*  18:    */   extends EntityAIBase
/*  19:    */ {
/*  20:    */   private EntityWyvern owner;
/*  21:    */   protected EntityLivingBase entityTarget;
/*  22: 17 */   int timeFollowedByEntity = 0;
/*  23: 18 */   int randomCounter = 0;
/*  24: 20 */   int fireBreathTime = 0;
/*  25: 21 */   int fireballTime = 0;
/*  26:    */   int chargeTime;
/*  27:    */   
/*  28:    */   public AIFly(EntityWyvern par1EntityLiving)
/*  29:    */   {
/*  30: 25 */     this.owner = par1EntityLiving;
/*  31: 26 */     setMutexBits(4);
/*  32: 27 */     par1EntityLiving.getNavigator().setCanSwim(true);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public boolean shouldExecute()
/*  36:    */   {
/*  37: 33 */     EntityLivingBase var1 = this.owner.getAttackTarget();
/*  38: 35 */     if (var1 == null) {
/*  39: 37 */       return false;
/*  40:    */     }
/*  41: 39 */     if (!var1.isEntityAlive())
/*  42:    */     {
/*  43: 41 */       this.owner.setAttackTarget(null);
/*  44: 42 */       return false;
/*  45:    */     }
/*  46: 44 */     if (var1 == this.owner.riddenByEntity)
/*  47:    */     {
/*  48: 46 */       this.owner.setAttackTarget(null);
/*  49: 47 */       return false;
/*  50:    */     }
/*  51: 56 */     this.entityTarget = var1;
/*  52:    */     
/*  53: 58 */     return true;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public boolean continueExecuting()
/*  57:    */   {
/*  58: 68 */     EntityLivingBase var1 = this.owner.getAttackTarget();
/*  59: 69 */     return !this.entityTarget.isEntityAlive() ? false : var1 != this.entityTarget ? false : var1 == null ? false : this.owner.isWithinHomeDistance(MathHelper.floor_double(this.entityTarget.posX), MathHelper.floor_double(this.entityTarget.posY), MathHelper.floor_double(this.entityTarget.posZ));
/*  60:    */   }
/*  61:    */   
/*  62:    */   public int getChargeTime()
/*  63:    */   {
/*  64: 79 */     return this.chargeTime;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void updateTask()
/*  68:    */   {
/*  69: 86 */     this.owner.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);
/*  70: 87 */     double dist = this.owner.getDistanceToEntity(this.entityTarget);
/*  71: 88 */     boolean canSee = this.owner.getEntitySenses().canSee(this.entityTarget);
/*  72:    */     
/*  73: 90 */     double angle = Math.abs(BDHelper.getAngleBetweenEntities(this.owner, this.entityTarget));
/*  74: 91 */     double rotDif = Math.abs(angle - Math.abs(MathHelper.wrapAngleTo180_double(this.owner.rotationYaw)));
/*  75: 92 */     if (!this.owner.onGround)
/*  76:    */     {
/*  77: 94 */       this.owner.getNavigator().clearPathEntity();
/*  78: 95 */       if (rotDif < 15.0D + dist) {
/*  79: 96 */         this.chargeTime = Math.min(this.chargeTime + 4, 120);
/*  80:    */       } else {
/*  81: 99 */         this.chargeTime = Math.max(this.chargeTime - 1, 0);
/*  82:    */       }
/*  83:102 */       int maxHeight = 8;
/*  84:103 */       if ((this.entityTarget.posY + this.entityTarget.height - 1.0D + Math.min(8.0D, dist / 3.0D) - this.owner.posY > 0.0D) || ((this.owner.isCollidedHorizontally) && (!this.owner.onGround)))
/*  85:    */       {
/*  86:105 */         int blockX = MathHelper.floor_double(this.owner.posX + this.owner.motionX * 10.0D);
/*  87:106 */         int blockY = MathHelper.floor_double(this.owner.posY);
/*  88:107 */         int blockZ = MathHelper.floor_double(this.owner.posZ + this.owner.motionZ * 10.0D);
/*  89:108 */         if (((this.owner.worldObj.canBlockSeeTheSky(blockX, blockY, blockZ)) || (this.owner.worldObj.isAirBlock(blockX, blockY + 4, blockZ))) && (this.owner.posY < 250.0D))
/*  90:    */         {
/*  91:110 */           if (this.owner.rotationPitch > -0.3F) {
/*  92:111 */             this.owner.rotationPitch -= 0.001F;
/*  93:    */           }
/*  94:112 */           this.owner.motionY = 0.3D;
/*  95:    */         }
/*  96:    */         else
/*  97:    */         {
/*  98:116 */           if (this.owner.rotationPitch < 0.3F) {
/*  99:117 */             this.owner.rotationPitch += 0.001F;
/* 100:    */           }
/* 101:118 */           this.owner.motionY = -0.3D;
/* 102:    */         }
/* 103:    */       }
/* 104:    */       else
/* 105:    */       {
/* 106:123 */         this.owner.rotationPitch = 0.0F;
/* 107:124 */         this.owner.motionY = -0.3D;
/* 108:    */       }
/* 109:126 */       double ry = Math.toRadians(this.owner.rotationYaw - 180.0F);
/* 110:    */       
/* 111:128 */       int currentSpeed = Math.max(this.chargeTime / 2, 10);
/* 112:129 */       float moveSpeed = (float)this.owner.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
/* 113:130 */       this.owner.motionX = (Math.sin(ry) * currentSpeed / 30.0D * moveSpeed);
/* 114:131 */       this.owner.motionZ = (-Math.cos(ry) * currentSpeed / 30.0D * moveSpeed);
/* 115:    */       
/* 116:133 */       float maxRot = (float)(180.0D - rotDif) / 30.0F;
/* 117:134 */       if (this.owner.rotationYawHead + maxRot > this.owner.rotationYaw) {
/* 118:136 */         this.owner.rotationYaw += maxRot;
/* 119:138 */       } else if (this.owner.rotationYawHead - maxRot < this.owner.rotationYaw) {
/* 120:140 */         this.owner.rotationYaw -= maxRot;
/* 121:    */       }
/* 122:143 */       if ((dist > 20.0D) && (this.owner.getRNG().nextInt(50) == 0)) {
/* 123:145 */         shootFireballAtTarget();
/* 124:    */       }
/* 125:    */     }
/* 126:    */     else
/* 127:    */     {
/* 128:150 */       this.owner.rotationPitch = 0.0F;
/* 129:151 */       if ((dist > 4.9D) && (this.owner.getNavigator().noPath())) {
/* 130:152 */         this.owner.getNavigator().tryMoveToEntityLiving(this.entityTarget, 1.0D);
/* 131:    */       }
/* 132:    */     }
/* 133:156 */     if (rotDif > 30.0D)
/* 134:    */     {
/* 135:158 */       this.timeFollowedByEntity += 1;
/* 136:159 */       if (this.timeFollowedByEntity > 120) {
/* 137:161 */         if (dist < 8.0D) {
/* 138:162 */           fireBreath();
/* 139:163 */         } else if (this.owner.getRNG().nextInt(50) == 0) {
/* 140:164 */           shootFireballAtTarget();
/* 141:    */         }
/* 142:    */       }
/* 143:    */     }
/* 144:    */     else
/* 145:    */     {
/* 146:168 */       this.timeFollowedByEntity = 0;
/* 147:    */     }
/* 148:170 */     if ((dist < 8.0D) && (rotDif < 20.0D))
/* 149:    */     {
/* 150:172 */       this.randomCounter += 1;
/* 151:173 */       if (this.randomCounter > 20) {
/* 152:175 */         fireBreath();
/* 153:    */       }
/* 154:    */     }
/* 155:    */     else
/* 156:    */     {
/* 157:179 */       this.randomCounter = 0;
/* 158:    */     }
/* 159:181 */     if ((dist < 4.9D) && (canSee)) {
/* 160:182 */       if (this.owner.attackTime <= 0)
/* 161:    */       {
/* 162:184 */         this.owner.attackEntityAsMob(this.entityTarget);
/* 163:185 */         this.owner.swingItem();
/* 164:186 */         this.owner.attackTime = 40;
/* 165:    */       }
/* 166:188 */       else if (this.owner.attackTime > 0)
/* 167:    */       {
/* 168:190 */         this.owner.attackTime -= 1;
/* 169:    */       }
/* 170:    */     }
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void fireBreath()
/* 174:    */   {
/* 175:197 */     this.owner.openMouth();
/* 176:198 */     World world = this.owner.worldObj;
/* 177:199 */     double x = this.entityTarget.posX - this.owner.posX;
/* 178:200 */     double y = this.entityTarget.boundingBox.minY + this.entityTarget.height / 2.0F - (this.owner.posY + this.owner.height / 2.0F);
/* 179:201 */     double z = this.entityTarget.posZ - this.owner.posZ;
/* 180:202 */     this.owner.fireBreath(x, y, z);
/* 181:203 */     world.playSoundEffect((int)this.owner.posX, (int)this.owner.posY, (int)this.owner.posZ, "fire.fire", 4.0F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
/* 182:    */   }
/* 183:    */   
/* 184:    */   public void shootFireballAtTarget()
/* 185:    */   {
/* 186:208 */     this.owner.openMouth();
/* 187:209 */     World world = this.owner.worldObj;
/* 188:210 */     double x = this.entityTarget.posX - this.owner.posX;
/* 189:211 */     double y = this.entityTarget.boundingBox.minY + this.entityTarget.height / 2.0F - (this.owner.posY + 2.0D);
/* 190:212 */     double z = this.entityTarget.posZ - this.owner.posZ;
/* 191:    */     
/* 192:214 */     this.owner.shootFireball(x, y, z);
/* 193:215 */     world.playSoundEffect((int)this.owner.posX, (int)this.owner.posY, (int)this.owner.posZ, "mob.ghast.fireball", 4.0F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
/* 194:    */   }
/* 195:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIFly
 * JD-Core Version:    0.7.1
 */