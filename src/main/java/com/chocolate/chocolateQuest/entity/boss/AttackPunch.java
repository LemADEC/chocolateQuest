/*   1:    */ package com.chocolate.chocolateQuest.entity.boss;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.util.AxisAlignedBB;
/*   6:    */ import net.minecraft.util.MathHelper;
/*   7:    */ import net.minecraft.util.Vec3;
/*   8:    */ import net.minecraft.world.World;
/*   9:    */ 
/*  10:    */ public class AttackPunch
/*  11:    */ {
/*  12: 12 */   public double[] swingDest = new double[3];
/*  13: 13 */   public double[] swingStart = new double[3];
/*  14: 14 */   public int attackTime = 0;
/*  15: 15 */   public boolean isAttacking = false;
/*  16:    */   byte handFlag;
/*  17:    */   EntityBaseBoss owner;
/*  18: 20 */   int attackSpeed = 20;
/*  19: 22 */   private float shoulderHeight = 1.0F;
/*  20: 22 */   private float armLength = 1.0F;
/*  21: 22 */   private float distanceToBody = 0.4F;
/*  22:    */   private int angleOffset;
/*  23: 23 */   private int heightOffset = -1;
/*  24:    */   public double posX;
/*  25:    */   public double posY;
/*  26:    */   public double posZ;
/*  27:    */   public double prevPosX;
/*  28:    */   public double prevPosY;
/*  29:    */   public double prevPosZ;
/*  30:    */   
/*  31:    */   public AttackPunch(EntityBaseBoss owner, byte handFlag)
/*  32:    */   {
/*  33: 26 */     this.owner = owner;
/*  34: 27 */     this.handFlag = handFlag;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public AttackPunch(EntityBaseBoss owner, byte handFlag, float shoulderHeight, float armScale)
/*  38:    */   {
/*  39: 31 */     this(owner, handFlag);
/*  40: 32 */     this.shoulderHeight = shoulderHeight;
/*  41: 33 */     this.armLength = armScale;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public int getSpeed()
/*  45:    */   {
/*  46: 37 */     return this.attackSpeed;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void setAngle(int angle, int height, float distanceToBody)
/*  50:    */   {
/*  51: 41 */     this.angleOffset = angle;
/*  52: 42 */     this.heightOffset = height;
/*  53: 43 */     this.distanceToBody = distanceToBody;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void swingArmTo(double x, double y, double z)
/*  57:    */   {
/*  58: 48 */     this.attackTime = getSpeed();
/*  59: 49 */     this.swingStart[0] = this.swingDest[0];
/*  60: 50 */     this.swingStart[1] = this.swingDest[1];
/*  61: 51 */     this.swingStart[2] = this.swingDest[2];
/*  62: 52 */     this.swingDest[0] = x;
/*  63: 53 */     this.swingDest[1] = y;
/*  64: 54 */     this.swingDest[2] = z;
/*  65: 55 */     this.isAttacking = true;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void attackTarget(Entity target)
/*  69:    */   {
/*  70: 59 */     double posY = this.owner.posY + getShoulderHeight();
/*  71: 60 */     double targetY = target.posY + target.height / 2.0F;
/*  72: 61 */     double dx = target.posX - this.owner.posX;
/*  73: 62 */     double dy = targetY - posY;
/*  74: 63 */     double dz = target.posZ - this.owner.posZ;
/*  75: 64 */     Vec3 v = Vec3.createVectorHelper(dx, dy, dz);
/*  76: 65 */     v = v.normalize();
/*  77: 66 */     double distToHead = this.owner.getDistance(target.posX, targetY - getShoulderHeight(), target.posZ);
/*  78: 67 */     double scale = Math.min(getArmLength(), distToHead);
/*  79: 68 */     float armSpeed = 5.0F;
/*  80: 69 */     dx = v.xCoord * scale;
/*  81: 70 */     dy = v.yCoord * scale;
/*  82: 71 */     dz = v.zCoord * scale;
/*  83: 72 */     this.owner.attackToXYZ(this.handFlag, dx, dy, dz);
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void doPunchDamage()
/*  87:    */   {
/*  88: 76 */     double d = this.owner.size / 10.0F;
/*  89: 77 */     double posX = this.owner.posX + this.posX;
/*  90: 78 */     double posY = this.owner.posY + getShoulderHeight() + this.posY;
/*  91: 79 */     double posZ = this.owner.posZ + this.posZ;
/*  92: 80 */     List list = this.owner.worldObj.getEntitiesWithinAABBExcludingEntity(this.owner, AxisAlignedBB.getBoundingBox(posX - d, posY - d, posZ - d, posX + d, posY + d, posZ + d));
/*  93: 81 */     for (int j = 0; j < list.size(); j++)
/*  94:    */     {
/*  95: 83 */       Entity entity1 = (Entity)list.get(j);
/*  96: 84 */       if ((entity1.canBeCollidedWith()) && (!this.owner.isEntityEqual(entity1))) {
/*  97: 87 */         if (this.owner.attackEntityAsMob(entity1))
/*  98:    */         {
/*  99: 88 */           entity1.motionX += this.swingDest[0] / 4.0D;
/* 100: 89 */           entity1.motionY += this.swingDest[1] / 4.0D;
/* 101: 90 */           entity1.motionZ += this.swingDest[2] / 4.0D;
/* 102:    */         }
/* 103:    */       }
/* 104:    */     }
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void onUpdate()
/* 108:    */   {
/* 109: 96 */     moveHands(this.swingDest, this.swingStart, this.attackTime);
/* 110: 97 */     boolean forceDefensive = false;
/* 111: 98 */     if (this.attackTime > 0)
/* 112:    */     {
/* 113: 99 */       this.attackTime -= 1;
/* 114:100 */       doPunchDamage();
/* 115:101 */       if (this.isAttacking)
/* 116:    */       {
/* 117:102 */         if (this.attackTime == 0)
/* 118:    */         {
/* 119:103 */           Vec3 v = getDefaultPosition();
/* 120:104 */           float scale = (float)(getArmLength() * this.distanceToBody);
/* 121:105 */           swingArmTo(v.xCoord * scale, v.yCoord * scale, v.zCoord * scale);
/* 122:106 */           this.isAttacking = false;
/* 123:    */         }
/* 124:    */       }
/* 125:    */       else {
/* 126:109 */         forceDefensive = true;
/* 127:    */       }
/* 128:    */     }
/* 129:    */     else
/* 130:    */     {
/* 131:111 */       forceDefensive = true;
/* 132:    */     }
/* 133:112 */     if (forceDefensive)
/* 134:    */     {
/* 135:113 */       Vec3 v = getDefaultPosition();
/* 136:114 */       float scale = (float)(getArmLength() * this.distanceToBody);
/* 137:115 */       this.swingDest[0] = (v.xCoord * scale);
/* 138:116 */       this.swingDest[1] = (v.yCoord * scale);
/* 139:117 */       this.swingDest[2] = (v.zCoord * scale);
/* 140:    */     }
/* 141:    */   }
/* 142:    */   
/* 143:    */   public void moveHands(double[] dest, double[] start, int armSwing)
/* 144:    */   {
/* 145:124 */     this.posX = this.owner.posX;
/* 146:125 */     this.posY = (this.owner.posY + getShoulderHeight());
/* 147:126 */     this.posZ = this.owner.posZ;
/* 148:    */     
/* 149:128 */     int swing = getSpeed() - armSwing;
/* 150:129 */     double dx = start[0] + (dest[0] - start[0]) / getSpeed() * swing;
/* 151:130 */     double dy = start[1] + (dest[1] - start[1]) / getSpeed() * swing;
/* 152:131 */     double dz = start[2] + (dest[2] - start[2]) / getSpeed() * swing;
/* 153:132 */     setPosition(dx, dy, dz);
/* 154:    */   }
/* 155:    */   
/* 156:    */   public float getShoulderHeight()
/* 157:    */   {
/* 158:136 */     return this.owner.size * this.shoulderHeight;
/* 159:    */   }
/* 160:    */   
/* 161:    */   public double getArmLength()
/* 162:    */   {
/* 163:139 */     return this.owner.size * this.armLength;
/* 164:    */   }
/* 165:    */   
/* 166:    */   public void setPosition(double x, double y, double z)
/* 167:    */   {
/* 168:145 */     this.prevPosX = this.posX;
/* 169:146 */     this.posX = x;
/* 170:147 */     this.prevPosY = this.posY;
/* 171:148 */     this.posY = y;
/* 172:149 */     this.prevPosZ = this.posZ;
/* 173:150 */     this.posZ = z;
/* 174:    */   }
/* 175:    */   
/* 176:    */   public boolean attackInProgress()
/* 177:    */   {
/* 178:155 */     return this.attackTime > 0;
/* 179:    */   }
/* 180:    */   
/* 181:    */   protected Vec3 getDefaultPosition()
/* 182:    */   {
/* 183:159 */     double posX = -Math.sin(Math.toRadians(this.owner.rotationYawHead + this.angleOffset));
/* 184:160 */     double posY = this.heightOffset + MathHelper.cos(this.owner.ticksExisted / 20.0F) / 4.0F;
/* 185:161 */     double posZ = Math.cos(Math.toRadians(this.owner.rotationYawHead + this.angleOffset));
/* 186:162 */     return Vec3.createVectorHelper(posX, posY, posZ).normalize();
/* 187:    */   }
/* 188:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.AttackPunch
 * JD-Core Version:    0.7.1
 */