/*  1:   */ package com.chocolate.chocolateQuest.packets;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import io.netty.buffer.ByteBuf;
/*  5:   */ import java.io.PrintStream;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.entity.player.EntityPlayer;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class PacketUpdateHumanData
/* 11:   */   extends PacketBase
/* 12:   */ {
/* 13:   */   int distance;
/* 14:   */   int angle;
/* 15:   */   int id;
/* 16:   */   int aiMode;
/* 17:   */   int aiCombatMode;
/* 18:   */   boolean updateOwner;
/* 19:   */   EntityHumanBase human;
/* 20:   */   
/* 21:   */   public PacketUpdateHumanData() {}
/* 22:   */   
/* 23:   */   public PacketUpdateHumanData(EntityHumanBase e)
/* 24:   */   {
/* 25:22 */     this.id = e.getEntityId();
/* 26:23 */     this.angle = e.partyPositionAngle;
/* 27:24 */     this.distance = e.partyDistanceToLeader;
/* 28:25 */     this.aiMode = e.AIMode;
/* 29:26 */     this.aiCombatMode = e.AICombatMode;
/* 30:27 */     this.updateOwner = e.updateOwner;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void readBytes(ByteBuf inputStream)
/* 34:   */   {
/* 35:33 */     this.id = inputStream.readInt();
/* 36:34 */     this.angle = inputStream.readInt();
/* 37:35 */     this.distance = inputStream.readInt();
/* 38:36 */     this.aiMode = inputStream.readInt();
/* 39:37 */     this.aiCombatMode = inputStream.readInt();
/* 40:38 */     this.updateOwner = inputStream.readBoolean();
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void writeBytes(ByteBuf outputStream)
/* 44:   */   {
/* 45:43 */     outputStream.writeInt(this.id);
/* 46:44 */     outputStream.writeInt(this.angle);
/* 47:45 */     outputStream.writeInt(this.distance);
/* 48:46 */     outputStream.writeInt(this.aiMode);
/* 49:47 */     outputStream.writeInt(this.aiCombatMode);
/* 50:48 */     outputStream.writeBoolean(this.updateOwner);
/* 51:   */   }
/* 52:   */   
/* 53:   */   public void execute(EntityPlayer player)
/* 54:   */   {
/* 55:52 */     World world = player.worldObj;
/* 56:53 */     Entity e = world.getEntityByID(this.id);
/* 57:54 */     if ((e instanceof EntityHumanBase))
/* 58:   */     {
/* 59:56 */       this.human = ((EntityHumanBase)e);
/* 60:57 */       this.human.partyPositionAngle = this.angle;
/* 61:58 */       this.human.partyDistanceToLeader = this.distance;
/* 62:59 */       this.human.partyPositionPersistance = true;
/* 63:60 */       this.human.AIMode = this.aiMode;
/* 64:61 */       this.human.AICombatMode = this.aiCombatMode;
/* 65:62 */       this.human.setAIForCurrentMode();
/* 66:63 */       if ((this.human.playerSpeakingTo != null) && (this.updateOwner))
/* 67:   */       {
/* 68:64 */         if (this.human.getOwner() == this.human.playerSpeakingTo)
/* 69:   */         {
/* 70:65 */           this.human.setOwner(null);
/* 71:66 */           System.out.println("a " + this.human.getOwner());
/* 72:   */         }
/* 73:   */         else
/* 74:   */         {
/* 75:69 */           this.human.setOwner(this.human.playerSpeakingTo);
/* 76:   */         }
/* 77:70 */         this.human.updateOwner = false;
/* 78:   */       }
/* 79:72 */       this.human.playerSpeakingTo = null;
/* 80:   */     }
/* 81:   */   }
/* 82:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.packets.PacketUpdateHumanData
 * JD-Core Version:    0.7.1
 */