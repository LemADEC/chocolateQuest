/*  1:   */ package com.chocolate.chocolateQuest.packets;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import com.chocolate.chocolateQuest.entity.boss.EntityBaseBoss;
/*  5:   */ import io.netty.buffer.ByteBuf;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.entity.EntityLivingBase;
/*  8:   */ import net.minecraft.entity.player.EntityPlayer;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class PacketEntityAnimation
/* 12:   */   extends PacketBase
/* 13:   */ {
/* 14:   */   byte type;
/* 15:   */   int entityID;
/* 16:   */   public static final byte SWING_ITEM = 0;
/* 17:   */   public static final byte LEFT_HAND_SWING = 1;
/* 18:   */   public static final byte AIM_RIGHT = 2;
/* 19:   */   public static final byte AIM_LEFT = 3;
/* 20:   */   
/* 21:   */   public PacketEntityAnimation() {}
/* 22:   */   
/* 23:   */   public PacketEntityAnimation(int entityID, byte animType)
/* 24:   */   {
/* 25:23 */     this.entityID = entityID;
/* 26:24 */     this.type = animType;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void execute(EntityPlayer player)
/* 30:   */   {
/* 31:29 */     Entity entity = player.worldObj.getEntityByID(this.entityID);
/* 32:30 */     if (entity != null)
/* 33:   */     {
/* 34:31 */       if (this.type == 0)
/* 35:   */       {
/* 36:33 */         if ((entity instanceof EntityLivingBase)) {
/* 37:34 */           ((EntityLivingBase)entity).swingItem();
/* 38:   */         }
/* 39:35 */         return;
/* 40:   */       }
/* 41:38 */       if ((entity instanceof EntityHumanBase)) {
/* 42:40 */         switch (this.type)
/* 43:   */         {
/* 44:   */         case 1: 
/* 45:43 */           ((EntityHumanBase)entity).swingLeftHand();return;
/* 46:   */         case 2: 
/* 47:45 */           ((EntityHumanBase)entity).toogleAimRight();return;
/* 48:   */         case 3: 
/* 49:47 */           ((EntityHumanBase)entity).toogleAimLeft();return;
/* 50:   */         }
/* 51:   */       }
/* 52:50 */       if ((entity instanceof EntityBaseBoss))
/* 53:   */       {
/* 54:52 */         ((EntityBaseBoss)entity).animationBoss(this.type);return;
/* 55:   */       }
/* 56:   */     }
/* 57:   */   }
/* 58:   */   
/* 59:   */   public void readBytes(ByteBuf bytes)
/* 60:   */   {
/* 61:59 */     this.type = bytes.readByte();
/* 62:60 */     this.entityID = bytes.readInt();
/* 63:   */   }
/* 64:   */   
/* 65:   */   public void writeBytes(ByteBuf bytes)
/* 66:   */   {
/* 67:65 */     bytes.writeByte(this.type);
/* 68:66 */     bytes.writeInt(this.entityID);
/* 69:   */   }
/* 70:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.packets.PacketEntityAnimation
 * JD-Core Version:    0.7.1
 */