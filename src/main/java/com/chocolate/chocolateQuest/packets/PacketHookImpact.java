/*   1:    */ package com.chocolate.chocolateQuest.packets;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityHookShoot;
/*   4:    */ import io.netty.buffer.ByteBuf;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.entity.player.EntityPlayer;
/*   7:    */ import net.minecraft.world.World;
/*   8:    */ 
/*   9:    */ public class PacketHookImpact
/*  10:    */   extends PacketBase
/*  11:    */ {
/*  12:    */   byte type;
/*  13:    */   int hookID;
/*  14:    */   int entityID;
/*  15:    */   int posX;
/*  16:    */   int posY;
/*  17:    */   int posZ;
/*  18:    */   int angle;
/*  19:    */   int distance;
/*  20:    */   int height;
/*  21:    */   public static final byte ENTITY = 0;
/*  22:    */   public static final byte BLOCK = 1;
/*  23: 21 */   int offsetScale = 100000;
/*  24:    */   
/*  25:    */   public PacketHookImpact() {}
/*  26:    */   
/*  27:    */   public PacketHookImpact(int hookID, int entityID, double angle, double dist, double height)
/*  28:    */   {
/*  29: 27 */     this.type = 0;
/*  30: 28 */     this.hookID = hookID;
/*  31: 29 */     this.entityID = entityID;
/*  32: 30 */     this.angle = ((int)angle);
/*  33: 31 */     this.distance = ((int)(dist * this.offsetScale));
/*  34: 32 */     this.height = ((int)(height * this.offsetScale));
/*  35:    */   }
/*  36:    */   
/*  37:    */   public PacketHookImpact(int hookID, int posX, int posY, int posZ)
/*  38:    */   {
/*  39: 37 */     this.type = 1;
/*  40: 38 */     this.hookID = hookID;
/*  41: 39 */     this.posX = posX;
/*  42: 40 */     this.posY = posY;
/*  43: 41 */     this.posZ = posZ;
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void execute(EntityPlayer player)
/*  47:    */   {
/*  48: 47 */     Entity entity = player.worldObj.getEntityByID(this.hookID);
/*  49: 48 */     if ((entity instanceof EntityHookShoot))
/*  50:    */     {
/*  51: 49 */       EntityHookShoot hook = (EntityHookShoot)entity;
/*  52: 50 */       switch (this.type)
/*  53:    */       {
/*  54:    */       case 0: 
/*  55: 53 */         entity = player.worldObj.getEntityByID(this.entityID);
/*  56: 54 */         if (entity != null)
/*  57:    */         {
/*  58: 56 */           double offX = this.angle;
/*  59: 57 */           double distance = this.distance / this.offsetScale;
/*  60: 58 */           double height = this.height / this.offsetScale;
/*  61: 59 */           hook.hookedEntity = entity;
/*  62: 60 */           hook.hookedAtHeight = height;
/*  63: 61 */           hook.hookedAtAngle = this.angle;
/*  64: 62 */           hook.hookedAtDistance = distance;
/*  65:    */         }
/*  66: 63 */         break;
/*  67:    */       case 1: 
/*  68: 67 */         hook.blockX = this.posX;
/*  69: 68 */         hook.blockY = this.posY;
/*  70: 69 */         hook.blockZ = this.posZ;
/*  71:    */       }
/*  72: 72 */       hook.onImpact();
/*  73:    */     }
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void readBytes(ByteBuf bytes)
/*  77:    */   {
/*  78: 78 */     this.type = bytes.readByte();
/*  79: 79 */     this.hookID = bytes.readInt();
/*  80: 80 */     switch (this.type)
/*  81:    */     {
/*  82:    */     case 0: 
/*  83: 83 */       this.entityID = bytes.readInt();
/*  84: 84 */       this.angle = bytes.readInt();
/*  85: 85 */       this.distance = bytes.readInt();
/*  86: 86 */       this.height = bytes.readInt();
/*  87: 87 */       break;
/*  88:    */     case 1: 
/*  89: 90 */       this.posX = bytes.readInt();
/*  90: 91 */       this.posY = bytes.readInt();
/*  91: 92 */       this.posZ = bytes.readInt();
/*  92:    */     }
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void writeBytes(ByteBuf bytes)
/*  96:    */   {
/*  97: 99 */     bytes.writeByte(this.type);
/*  98:100 */     bytes.writeInt(this.hookID);
/*  99:101 */     switch (this.type)
/* 100:    */     {
/* 101:    */     case 0: 
/* 102:104 */       bytes.writeInt(this.entityID);
/* 103:105 */       bytes.writeInt(this.angle);
/* 104:106 */       bytes.writeInt(this.distance);
/* 105:107 */       bytes.writeInt(this.height);
/* 106:108 */       break;
/* 107:    */     case 1: 
/* 108:111 */       bytes.writeInt(this.posX);
/* 109:112 */       bytes.writeInt(this.posY);
/* 110:113 */       bytes.writeInt(this.posZ);
/* 111:    */     }
/* 112:    */   }
/* 113:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.packets.PacketHookImpact
 * JD-Core Version:    0.7.1
 */