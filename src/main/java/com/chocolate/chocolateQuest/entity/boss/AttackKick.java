/*   1:    */ package com.chocolate.chocolateQuest.entity.boss;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   5:    */ import com.chocolate.chocolateQuest.packets.PacketEntityAnimation;
/*   6:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   7:    */ import java.util.List;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.util.AxisAlignedBB;
/*  10:    */ import net.minecraft.util.MathHelper;
/*  11:    */ import net.minecraft.world.World;
/*  12:    */ 
/*  13:    */ public class AttackKick
/*  14:    */ {
/*  15:    */   public int kickTime;
/*  16:    */   public byte kickType;
/*  17: 17 */   public int kickSpeed = 30;
/*  18:    */   EntityBaseBoss owner;
/*  19:    */   
/*  20:    */   public AttackKick(EntityBaseBoss owner)
/*  21:    */   {
/*  22: 22 */     this.owner = owner;
/*  23:    */   }
/*  24:    */   
/*  25:    */   public void setSpeed(int speed)
/*  26:    */   {
/*  27: 26 */     this.kickSpeed = speed;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public int getSpeed()
/*  31:    */   {
/*  32: 29 */     return this.kickSpeed;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void onUpdate()
/*  36:    */   {
/*  37: 34 */     if (this.kickTime > 0)
/*  38:    */     {
/*  39: 35 */       this.kickTime -= 1;
/*  40: 36 */       if (this.kickTime == getSpeed() / 3) {
/*  41: 37 */         doKickDamage(this.kickType);
/*  42:    */       }
/*  43:    */     }
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void attackTarget(Entity entity)
/*  47:    */   {
/*  48: 43 */     int angle = (int)MathHelper.wrapAngleTo180_double(BDHelper.getAngleBetweenEntities(this.owner, entity) - this.owner.rotationYaw);
/*  49: 44 */     byte type = 0;
/*  50: 45 */     if (angle > 0)
/*  51:    */     {
/*  52: 46 */       if (angle < 90) {
/*  53: 47 */         type = 3;
/*  54:    */       } else {
/*  55: 49 */         type = 4;
/*  56:    */       }
/*  57:    */     }
/*  58: 51 */     else if (angle > -90) {
/*  59: 52 */       type = 1;
/*  60:    */     } else {
/*  61: 54 */       type = 2;
/*  62:    */     }
/*  63: 56 */     kick(type);
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void kick(byte type)
/*  67:    */   {
/*  68: 60 */     if (!this.owner.worldObj.isRemote)
/*  69:    */     {
/*  70: 62 */       PacketEntityAnimation packet = new PacketEntityAnimation(this.owner.getEntityId(), type);
/*  71: 63 */       ChocolateQuest.channel.sendToAllAround(this.owner, packet, 64);
/*  72:    */     }
/*  73: 65 */     if (this.kickTime == 0)
/*  74:    */     {
/*  75: 66 */       this.kickTime = getSpeed();
/*  76: 67 */       this.kickType = type;
/*  77:    */     }
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void doKickDamage(byte kickType)
/*  81:    */   {
/*  82: 72 */     double d = Math.max(2.5D, this.owner.width * 0.8D);
/*  83: 73 */     List list = this.owner.worldObj.getEntitiesWithinAABBExcludingEntity(this.owner, this.owner.boundingBox.expand(d, 0.0D, d).addCoord(0.0D, -2.0D, 0.0D));
/*  84: 74 */     int min = -180;int max = 180;
/*  85: 75 */     switch (kickType)
/*  86:    */     {
/*  87:    */     case 3: 
/*  88: 77 */       min = -5;max = 70; break;
/*  89:    */     case 4: 
/*  90: 79 */       min = 70;max = 180; break;
/*  91:    */     case 1: 
/*  92: 81 */       min = -70;max = 5; break;
/*  93:    */     case 2: 
/*  94: 83 */       min = -180;max = -70;
/*  95:    */     }
/*  96: 85 */     for (int j = 0; j < list.size(); j++)
/*  97:    */     {
/*  98: 87 */       Entity entity1 = (Entity)list.get(j);
/*  99: 88 */       if ((entity1.canBeCollidedWith()) && (!this.owner.isEntityEqual(entity1)) && (entity1 != this.owner.riddenByEntity))
/* 100:    */       {
/* 101: 91 */         int angle = (int)MathHelper.wrapAngleTo180_double(getAngleBetweenEntities(this.owner, entity1, kickType) - this.owner.rotationYaw);
/* 102: 93 */         if ((angle >= min) && (angle <= max) && 
/* 103: 94 */           (this.owner.attackEntityAsMob(entity1)))
/* 104:    */         {
/* 105: 95 */           float rotation = this.owner.rotationYaw;
/* 106: 96 */           if ((kickType == 2) || (kickType == 4)) {
/* 107: 97 */             rotation -= 180.0F;
/* 108:    */           }
/* 109: 99 */           rotation = rotation * 3.141592F / 180.0F;
/* 110:100 */           float dist = Math.max(1.5F, this.owner.width / 3.0F);
/* 111:101 */           double dx = -Math.sin(rotation) * dist;
/* 112:102 */           double dy = this.owner.size / 20.0F;
/* 113:103 */           double dz = Math.cos(rotation) * dist;
/* 114:104 */           entity1.motionX += dx;
/* 115:105 */           entity1.motionY += dy;
/* 116:106 */           entity1.motionZ += dz;
/* 117:    */         }
/* 118:    */       }
/* 119:    */     }
/* 120:    */   }
/* 121:    */   
/* 122:    */   public double getAngleBetweenEntities(Entity entity, Entity target, byte kickType)
/* 123:    */   {
/* 124:114 */     double d = entity.posX - target.posX;
/* 125:    */     
/* 126:116 */     double d2 = entity.posZ - target.posZ;
/* 127:117 */     double angle = Math.atan2(d, d2);
/* 128:118 */     angle = angle * 180.0D / 3.141592D;
/* 129:    */     
/* 130:120 */     angle = -MathHelper.wrapAngleTo180_double(angle - 180.0D);
/* 131:    */     
/* 132:122 */     return angle;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public boolean isAttackInProgress()
/* 136:    */   {
/* 137:127 */     return this.kickTime > 0;
/* 138:    */   }
/* 139:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.AttackKick
 * JD-Core Version:    0.7.1
 */