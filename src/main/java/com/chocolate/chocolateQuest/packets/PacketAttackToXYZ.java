/*  1:   */ package com.chocolate.chocolateQuest.packets;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.boss.EntityBaseBoss;
/*  4:   */ import io.netty.buffer.ByteBuf;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.player.EntityPlayer;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ 
/*  9:   */ public class PacketAttackToXYZ
/* 10:   */   extends PacketBase
/* 11:   */ {
/* 12:   */   byte type;
/* 13:   */   int entityID;
/* 14:   */   double x;
/* 15:   */   double y;
/* 16:   */   double z;
/* 17:   */   
/* 18:   */   public PacketAttackToXYZ() {}
/* 19:   */   
/* 20:   */   public PacketAttackToXYZ(int entityID, byte animType, double x, double y, double z)
/* 21:   */   {
/* 22:18 */     this.entityID = entityID;
/* 23:19 */     this.type = animType;
/* 24:20 */     this.x = x;
/* 25:21 */     this.y = y;
/* 26:22 */     this.z = z;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void execute(EntityPlayer player)
/* 30:   */   {
/* 31:27 */     Entity entity = player.worldObj.getEntityByID(this.entityID);
/* 32:28 */     if ((entity instanceof EntityBaseBoss))
/* 33:   */     {
/* 34:29 */       EntityBaseBoss g = (EntityBaseBoss)entity;
/* 35:30 */       g.attackToXYZ(this.type, this.x, this.y, this.z);
/* 36:   */     }
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void readBytes(ByteBuf bytes)
/* 40:   */   {
/* 41:36 */     this.type = bytes.readByte();
/* 42:37 */     this.entityID = bytes.readInt();
/* 43:38 */     this.x = bytes.readDouble();
/* 44:39 */     this.y = bytes.readDouble();
/* 45:40 */     this.z = bytes.readDouble();
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void writeBytes(ByteBuf bytes)
/* 49:   */   {
/* 50:45 */     bytes.writeByte(this.type);
/* 51:46 */     bytes.writeInt(this.entityID);
/* 52:47 */     bytes.writeDouble(this.x);
/* 53:48 */     bytes.writeDouble(this.y);
/* 54:49 */     bytes.writeDouble(this.z);
/* 55:   */   }
/* 56:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.packets.PacketAttackToXYZ
 * JD-Core Version:    0.7.1
 */