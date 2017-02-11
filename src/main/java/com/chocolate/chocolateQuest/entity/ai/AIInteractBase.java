/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import com.chocolate.chocolateQuest.entity.handHelper.HandHelper;
/*   5:    */ import java.util.List;
/*   6:    */ import net.minecraft.block.Block;
/*   7:    */ import net.minecraft.block.material.Material;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.entity.EntityLivingBase;
/*  10:    */ import net.minecraft.entity.ai.EntityLookHelper;
/*  11:    */ import net.minecraft.entity.player.EntityPlayer;
/*  12:    */ import net.minecraft.util.AxisAlignedBB;
/*  13:    */ import net.minecraft.util.MathHelper;
/*  14:    */ import net.minecraft.util.MovingObjectPosition;
/*  15:    */ import net.minecraft.util.Vec3;
/*  16:    */ import net.minecraft.world.World;
/*  17:    */ 
/*  18:    */ public class AIInteractBase
/*  19:    */   extends AIControlledBase
/*  20:    */ {
/*  21:    */   protected EntityLivingBase entityTarget;
/*  22:    */   
/*  23:    */   public AIInteractBase(EntityHumanBase par1EntityLiving)
/*  24:    */   {
/*  25: 22 */     super(par1EntityLiving);
/*  26: 23 */     setMutexBits(3);
/*  27:    */   }
/*  28:    */   
/*  29:    */   public boolean shouldExecute()
/*  30:    */   {
/*  31: 28 */     EntityLivingBase entityliving = this.owner.getAttackTarget();
/*  32: 29 */     if (entityliving == null) {
/*  33: 31 */       return false;
/*  34:    */     }
/*  35: 33 */     if (!entityliving.isEntityAlive())
/*  36:    */     {
/*  37: 35 */       this.owner.setAttackTarget(null);
/*  38: 36 */       return false;
/*  39:    */     }
/*  40: 38 */     if (this.owner.isSuitableTargetAlly(entityliving)) {
/*  41: 40 */       return false;
/*  42:    */     }
/*  43: 42 */     this.entityTarget = entityliving;
/*  44: 43 */     return true;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public void resetTask()
/*  48:    */   {
/*  49: 48 */     this.entityTarget = null;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public void updateTask()
/*  53:    */   {
/*  54: 53 */     double x = 0.0D;double y = 0.0D;double z = 0.0D;
/*  55: 54 */     EntityLivingBase leader = this.owner.getLeader();
/*  56: 55 */     Vec3 absPosition = leader.getLookVec();
/*  57: 56 */     float angle = this.owner.partyPositionAngle * 3.1416F / 180.0F;
/*  58: 57 */     double cos = MathHelper.cos(angle);
/*  59: 58 */     double sin = MathHelper.sin(angle);
/*  60: 59 */     int dist = this.owner.partyDistanceToLeader;
/*  61: 60 */     x = leader.posX + (absPosition.xCoord * cos - absPosition.zCoord * sin) * dist;
/*  62: 61 */     y = leader.posY;
/*  63: 62 */     z = leader.posZ + (absPosition.zCoord * cos + absPosition.xCoord * sin) * dist;
/*  64: 63 */     tryMoveToXYZ(x, y, z, 1.2F);
/*  65:    */   }
/*  66:    */   
/*  67:    */   public boolean attackTarget(double distance)
/*  68:    */   {
/*  69: 69 */     this.owner.attackTime = Math.max(this.owner.attackTime - 1, 0);
/*  70: 70 */     double sumLengthBB = getMinDistanceToInteract() + this.owner.getAttackRangeBonus();
/*  71: 71 */     if (this.owner.haveShied())
/*  72:    */     {
/*  73: 72 */       double distToStopDefending = sumLengthBB;
/*  74: 73 */       if ((this.entityTarget instanceof EntityPlayer)) {
/*  75: 74 */         distToStopDefending *= 2.0D;
/*  76:    */       } else {
/*  77: 76 */         distToStopDefending = 0.0D;
/*  78:    */       }
/*  79: 78 */       if ((this.owner.isDefending()) && (distance <= distToStopDefending) && (this.owner.attackTime <= 10)) {
/*  80: 80 */         this.owner.setDefending(false);
/*  81:    */       }
/*  82:    */     }
/*  83: 83 */     if (distance <= sumLengthBB)
/*  84:    */     {
/*  85: 85 */       this.owner.attackEntity(this.entityTarget);
/*  86:    */     }
/*  87: 88 */     else if (this.owner.isRanged())
/*  88:    */     {
/*  89: 90 */       EntityLivingBase target = getFrontTarget();
/*  90: 91 */       if ((target != null) && (this.owner.isSuitableTargetAlly(target)) && (target != this.owner.ridingEntity))
/*  91:    */       {
/*  92: 93 */         float moveStrafing = -MathHelper.sin(this.owner.partyPositionAngle) / 4.0F;
/*  93: 94 */         double ry = -Math.toRadians(this.owner.rotationYaw + this.owner.moveStrafing > 0.0F ? 90.0D : -90.0D);
/*  94: 95 */         int x = MathHelper.floor_double(this.owner.posX - Math.sin(ry) * 6.0D);
/*  95: 96 */         int z = MathHelper.floor_double(this.owner.posZ + Math.cos(ry) * 6.0D);
/*  96: 97 */         Material mat = this.owner.worldObj.getBlock(x, MathHelper.floor_double(this.owner.posY) - 1, z).getMaterial();
/*  97: 98 */         boolean move = false;
/*  98: 99 */         if ((mat != Material.air) && (mat != Material.lava) && (mat.isSolid()))
/*  99:    */         {
/* 100:101 */           move = true;
/* 101:    */         }
/* 102:    */         else
/* 103:    */         {
/* 104:104 */           mat = this.owner.worldObj.getBlock(x, MathHelper.floor_double(this.owner.posY) - 2, z).getMaterial();
/* 105:105 */           if (mat.isSolid()) {
/* 106:106 */             move = true;
/* 107:    */           }
/* 108:    */         }
/* 109:108 */         if (move) {
/* 110:110 */           this.owner.moveStrafing = moveStrafing;
/* 111:    */         } else {
/* 112:113 */           this.owner.moveStrafing = 0.0F;
/* 113:    */         }
/* 114:    */       }
/* 115:    */       else
/* 116:    */       {
/* 117:116 */         this.owner.moveStrafing = 0.0F;
/* 118:    */         
/* 119:118 */         this.owner.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);
/* 120:119 */         if (!this.owner.attackEntityWithRangedAttack(this.entityTarget, (float)distance)) {
/* 121:122 */           return false;
/* 122:    */         }
/* 123:    */       }
/* 124:    */     }
/* 125:129 */     return true;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public double getMinDistanceToInteract()
/* 129:    */   {
/* 130:134 */     double attackerBB = this.owner.width * 2.2F;
/* 131:135 */     if (this.owner.ridingEntity != null) {
/* 132:136 */       attackerBB += this.owner.ridingEntity.width * 2.0F;
/* 133:    */     }
/* 134:138 */     double targetBB = this.entityTarget.width * 2.2F;
/* 135:139 */     if (this.entityTarget.ridingEntity != null) {
/* 136:140 */       targetBB += this.entityTarget.ridingEntity.width;
/* 137:    */     }
/* 138:143 */     return attackerBB * targetBB + this.owner.rightHand.getAttackRangeBonus();
/* 139:    */   }
/* 140:    */   
/* 141:    */   public EntityLivingBase getFrontTarget()
/* 142:    */   {
/* 143:147 */     EntityLivingBase target = null;
/* 144:148 */     double arrowMotionX = this.entityTarget.posX - this.owner.posX;
/* 145:149 */     double arrowMotionZ = this.entityTarget.posZ - this.owner.posZ;
/* 146:150 */     float tRot = this.owner.rotationYaw;
/* 147:151 */     this.owner.rotationYaw = ((float)(Math.atan2(arrowMotionZ, arrowMotionX) * 180.0D / 3.141592653589793D) - 90.0F);
/* 148:    */     
/* 149:153 */     MovingObjectPosition mop = null;
/* 150:154 */     double dist = 30.0D;
/* 151:155 */     float yOffset = 0.0F;
/* 152:    */     
/* 153:157 */     Vec3 playerPos = Vec3.createVectorHelper(this.owner.posX, this.owner.posY - yOffset, this.owner.posZ);
/* 154:158 */     Vec3 look = this.owner.getLookVec();
/* 155:159 */     Vec3 playerView = playerPos.addVector(look.xCoord * dist, look.yCoord * dist, look.zCoord * dist);
/* 156:160 */     List list = this.owner.worldObj.getEntitiesWithinAABBExcludingEntity(this.owner, this.owner.boundingBox.addCoord(look.xCoord * dist, look.yCoord * dist, look.zCoord * dist).expand(1.0D, 1.0D, 1.0D));
/* 157:    */     
/* 158:162 */     dist *= dist;
/* 159:163 */     for (int j = 0; j < list.size(); j++)
/* 160:    */     {
/* 161:165 */       Entity entity1 = (Entity)list.get(j);
/* 162:166 */       if ((entity1 instanceof EntityLivingBase)) {
/* 163:168 */         if (entity1.canBeCollidedWith())
/* 164:    */         {
/* 165:173 */           float f2 = 0.4F;
/* 166:174 */           AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(entity1.width, entity1.height, entity1.width);
/* 167:175 */           MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(playerPos, playerView);
/* 168:177 */           if (movingobjectposition1 != null)
/* 169:    */           {
/* 170:182 */             double tDist = this.owner.getDistanceSqToEntity(entity1);
/* 171:183 */             if (tDist < dist)
/* 172:    */             {
/* 173:185 */               dist = tDist;
/* 174:186 */               target = (EntityLivingBase)entity1;
/* 175:    */             }
/* 176:    */           }
/* 177:    */         }
/* 178:    */       }
/* 179:    */     }
/* 180:191 */     return target;
/* 181:    */   }
/* 182:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIInteractBase
 * JD-Core Version:    0.7.1
 */