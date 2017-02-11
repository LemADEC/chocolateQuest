/*  1:   */ package com.chocolate.chocolateQuest.packets;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import com.chocolate.chocolateQuest.entity.npc.EntityHumanDummy;
/*  5:   */ import io.netty.buffer.ByteBuf;
/*  6:   */ import java.io.PrintStream;
/*  7:   */ import net.minecraft.entity.SharedMonsterAttributes;
/*  8:   */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  9:   */ import net.minecraft.entity.player.EntityPlayer;
/* 10:   */ 
/* 11:   */ public class PacketUpdateHumanDummyData
/* 12:   */   extends PacketUpdateHumanData
/* 13:   */ {
/* 14:11 */   double maxHealth = 1.0D;
/* 15:   */   float dropRight;
/* 16:   */   float dropHelmet;
/* 17:   */   float dropBody;
/* 18:   */   float dropLegs;
/* 19:   */   float dropFeet;
/* 20:   */   float dropLeft;
/* 21:   */   
/* 22:   */   public PacketUpdateHumanDummyData() {}
/* 23:   */   
/* 24:   */   public PacketUpdateHumanDummyData(EntityHumanDummy e)
/* 25:   */   {
/* 26:18 */     super(e);
/* 27:19 */     EntityHumanDummy human = e;
/* 28:20 */     this.maxHealth = human.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue();
/* 29:21 */     this.dropLeft = human.leftHandDropChances;
/* 30:22 */     this.dropRight = e.getEquipmentDropChances(0);
/* 31:23 */     this.dropHelmet = e.getEquipmentDropChances(4);
/* 32:24 */     this.dropBody = e.getEquipmentDropChances(3);
/* 33:25 */     this.dropLegs = e.getEquipmentDropChances(2);
/* 34:26 */     this.dropFeet = e.getEquipmentDropChances(1);
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void readBytes(ByteBuf inputStream)
/* 38:   */   {
/* 39:32 */     super.readBytes(inputStream);
/* 40:33 */     this.maxHealth = inputStream.readDouble();
/* 41:   */     
/* 42:35 */     this.dropLeft = inputStream.readFloat();
/* 43:36 */     this.dropRight = inputStream.readFloat();
/* 44:37 */     this.dropHelmet = inputStream.readFloat();
/* 45:38 */     this.dropBody = inputStream.readFloat();
/* 46:39 */     this.dropLegs = inputStream.readFloat();
/* 47:40 */     this.dropFeet = inputStream.readFloat();
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void writeBytes(ByteBuf outputStream)
/* 51:   */   {
/* 52:45 */     super.writeBytes(outputStream);
/* 53:46 */     outputStream.writeDouble(this.maxHealth);
/* 54:   */     
/* 55:48 */     outputStream.writeFloat(this.dropLeft);
/* 56:49 */     outputStream.writeFloat(this.dropRight);
/* 57:50 */     outputStream.writeFloat(this.dropHelmet);
/* 58:51 */     outputStream.writeFloat(this.dropBody);
/* 59:52 */     outputStream.writeFloat(this.dropLegs);
/* 60:53 */     outputStream.writeFloat(this.dropFeet);
/* 61:   */   }
/* 62:   */   
/* 63:   */   public void execute(EntityPlayer player)
/* 64:   */   {
/* 65:57 */     super.execute(player);
/* 66:58 */     if (this.human != null)
/* 67:   */     {
/* 68:59 */       this.human.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(this.maxHealth);
/* 69:   */       
/* 70:61 */       EntityHumanDummy dummy = (EntityHumanDummy)this.human;
/* 71:62 */       dummy.leftHandDropChances = this.dropLeft;
/* 72:63 */       dummy.setEquipmentDropChances(0, this.dropRight);
/* 73:64 */       dummy.setEquipmentDropChances(4, this.dropHelmet);
/* 74:65 */       dummy.setEquipmentDropChances(3, this.dropBody);
/* 75:66 */       dummy.setEquipmentDropChances(2, this.dropLegs);
/* 76:67 */       dummy.setEquipmentDropChances(1, this.dropFeet);
/* 77:68 */       System.out.println(dummy.leftHandDropChances);
/* 78:   */     }
/* 79:   */   }
/* 80:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.packets.PacketUpdateHumanDummyData
 * JD-Core Version:    0.7.1
 */