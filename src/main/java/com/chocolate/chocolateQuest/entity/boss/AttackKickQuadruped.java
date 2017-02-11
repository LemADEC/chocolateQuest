/*  1:   */ package com.chocolate.chocolateQuest.entity.boss;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*  5:   */ import com.chocolate.chocolateQuest.packets.PacketEntityAnimation;
/*  6:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.util.MathHelper;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class AttackKickQuadruped
/* 12:   */   extends AttackKick
/* 13:   */ {
/* 14:   */   public int kickTimeBack;
/* 15:   */   public byte kickTypeBack;
/* 16:17 */   float legOffset = 0.5F;
/* 17:   */   
/* 18:   */   public AttackKickQuadruped(EntityBaseBoss owner)
/* 19:   */   {
/* 20:19 */     super(owner);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void onUpdate()
/* 24:   */   {
/* 25:23 */     if (this.kickTime > 0)
/* 26:   */     {
/* 27:24 */       this.kickTime -= 1;
/* 28:25 */       if (this.kickTime == getSpeed() / 4) {
/* 29:26 */         doKickDamage(this.kickType);
/* 30:   */       }
/* 31:   */     }
/* 32:29 */     if (this.kickTimeBack > 0)
/* 33:   */     {
/* 34:30 */       this.kickTimeBack -= 1;
/* 35:31 */       if (this.kickTimeBack == getSpeed() / 4) {
/* 36:32 */         doKickDamage(this.kickTypeBack);
/* 37:   */       }
/* 38:   */     }
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void attackTarget(Entity entity)
/* 42:   */   {
/* 43:38 */     int angle = (int)MathHelper.wrapAngleTo180_double(BDHelper.getAngleBetweenEntities(this.owner, entity) - this.owner.rotationYaw);
/* 44:39 */     byte type = -1;
/* 45:40 */     if ((angle < 90) && (angle > -90))
/* 46:   */     {
/* 47:41 */       if (this.kickTime > 0) {
/* 48:42 */         return;
/* 49:   */       }
/* 50:43 */       if ((angle < 80) && (angle > 0)) {
/* 51:44 */         type = 3;
/* 52:45 */       } else if ((angle > -80) && (angle < 0)) {
/* 53:46 */         type = 1;
/* 54:   */       }
/* 55:   */     }
/* 56:   */     else
/* 57:   */     {
/* 58:48 */       if (this.kickTimeBack > 0) {
/* 59:49 */         return;
/* 60:   */       }
/* 61:50 */       if (angle < -100) {
/* 62:51 */         type = 2;
/* 63:52 */       } else if (angle > 100) {
/* 64:53 */         type = 4;
/* 65:   */       }
/* 66:   */     }
/* 67:55 */     if (type != -1) {
/* 68:56 */       kick(type);
/* 69:   */     }
/* 70:   */   }
/* 71:   */   
/* 72:   */   public void kick(byte type)
/* 73:   */   {
/* 74:60 */     if (!this.owner.worldObj.isRemote)
/* 75:   */     {
/* 76:62 */       PacketEntityAnimation packet = new PacketEntityAnimation(this.owner.getEntityId(), type);
/* 77:63 */       ChocolateQuest.channel.sendToAllAround(this.owner, packet, 64);
/* 78:   */     }
/* 79:65 */     if ((type == 1) || (type == 3))
/* 80:   */     {
/* 81:66 */       this.kickTime = getSpeed();
/* 82:67 */       this.kickType = type;
/* 83:   */     }
/* 84:   */     else
/* 85:   */     {
/* 86:69 */       this.kickTimeBack = getSpeed();
/* 87:70 */       this.kickTypeBack = type;
/* 88:   */     }
/* 89:   */   }
/* 90:   */   
/* 91:   */   public boolean isAttackInProgress()
/* 92:   */   {
/* 93:76 */     return (this.kickTime > 0) || (this.kickTimeBack > 0);
/* 94:   */   }
/* 95:   */   
/* 96:   */   public double getAngleBetweenEntities(Entity entity, Entity target, byte kickType)
/* 97:   */   {
/* 98:82 */     float dist = this.owner.width * this.legOffset;
/* 99:83 */     float rotationYaw = this.owner.rotationYaw * 3.141592F / 180.0F;
/* :0:84 */     double despX = -Math.sin(rotationYaw) * dist;
/* :1:85 */     double despZ = Math.cos(rotationYaw) * dist;
/* :2:86 */     if ((kickType == 4) || (kickType == 2))
/* :3:   */     {
/* :4:87 */       despX = -despX;
/* :5:88 */       despZ = -despZ;
/* :6:   */     }
/* :7:90 */     double d = entity.posX + despX - target.posX;
/* :8:   */     
/* :9:92 */     double d2 = entity.posZ + despZ - target.posZ;
/* ;0:93 */     double angle = Math.atan2(d, d2);
/* ;1:94 */     angle = angle * 180.0D / 3.141592D;
/* ;2:   */     
/* ;3:96 */     angle = -MathHelper.wrapAngleTo180_double(angle - 180.0D);
/* ;4:   */     
/* ;5:98 */     return angle;
/* ;6:   */   }
/* ;7:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.AttackKickQuadruped
 * JD-Core Version:    0.7.1
 */