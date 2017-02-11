/*  1:   */ package com.chocolate.chocolateQuest.packets;
/*  2:   */ 
/*  3:   */ import io.netty.buffer.ByteBuf;
/*  4:   */ import net.minecraft.entity.player.EntityPlayer;
/*  5:   */ 
/*  6:   */ public abstract class PacketBase
/*  7:   */ {
/*  8:   */   public void setData(byte[] bs) {}
/*  9:   */   
/* 10:   */   public abstract void readBytes(ByteBuf paramByteBuf);
/* 11:   */   
/* 12:   */   public abstract void writeBytes(ByteBuf paramByteBuf);
/* 13:   */   
/* 14:   */   public abstract void execute(EntityPlayer paramEntityPlayer);
/* 15:   */   
/* 16:   */   protected void writeString(ByteBuf bytes, String string)
/* 17:   */   {
/* 18:21 */     byte size = (byte)(string.length() > 255 ? 255 : string.length());
/* 19:22 */     bytes.writeByte(string.length());
/* 20:23 */     for (byte i = 0; i < size; i = (byte)(i + 1)) {
/* 21:25 */       bytes.writeChar(string.charAt(i));
/* 22:   */     }
/* 23:   */   }
/* 24:   */   
/* 25:   */   protected String readString(ByteBuf bytes)
/* 26:   */   {
/* 27:30 */     String string = "";
/* 28:31 */     byte size = bytes.readByte();
/* 29:32 */     for (byte i = 0; i < size; i = (byte)(i + 1)) {
/* 30:34 */       string = string + bytes.readChar();
/* 31:   */     }
/* 32:36 */     return string;
/* 33:   */   }
/* 34:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.packets.PacketBase
 * JD-Core Version:    0.7.1
 */