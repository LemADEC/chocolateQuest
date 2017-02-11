/*  1:   */ package com.chocolate.chocolateQuest.packets;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.common.network.FMLEmbeddedChannel;
/*  4:   */ import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
/*  5:   */ import cpw.mods.fml.common.network.FMLOutboundHandler;
/*  6:   */ import cpw.mods.fml.common.network.FMLOutboundHandler.OutboundTarget;
/*  7:   */ import cpw.mods.fml.common.network.NetworkRegistry;
/*  8:   */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*  9:   */ import cpw.mods.fml.relauncher.Side;
/* 10:   */ import cpw.mods.fml.relauncher.SideOnly;
/* 11:   */ import io.netty.buffer.ByteBuf;
/* 12:   */ import io.netty.channel.Channel;
/* 13:   */ import io.netty.channel.ChannelHandlerContext;
/* 14:   */ import io.netty.util.Attribute;
/* 15:   */ import java.util.EnumMap;
/* 16:   */ import net.minecraft.client.Minecraft;
/* 17:   */ import net.minecraft.entity.Entity;
/* 18:   */ import net.minecraft.entity.player.EntityPlayer;
/* 19:   */ import net.minecraft.network.INetHandler;
/* 20:   */ import net.minecraft.network.NetHandlerPlayServer;
/* 21:   */ 
/* 22:   */ public class ChannelHandler
/* 23:   */   extends FMLIndexedMessageToMessageCodec<PacketBase>
/* 24:   */ {
/* 25:   */   EnumMap<Side, FMLEmbeddedChannel> channels;
/* 26:   */   
/* 27:   */   public ChannelHandler()
/* 28:   */   {
/* 29:31 */     this.channels = NetworkRegistry.INSTANCE.newChannel("chocolateQuest", new io.netty.channel.ChannelHandler[] { this });
/* 30:32 */     addDiscriminator(0, PacketEditorGUIClose.class);
/* 31:33 */     addDiscriminator(1, PacketEntityAnimation.class);
/* 32:34 */     addDiscriminator(2, PacketUpdateHumanData.class);
/* 33:35 */     addDiscriminator(3, PacketUpdateHumanDummyData.class);
/* 34:36 */     addDiscriminator(4, PacketAttackToXYZ.class);
/* 35:37 */     addDiscriminator(5, PacketSpawnParticlesAround.class);
/* 36:38 */     addDiscriminator(6, PacketHookImpact.class);
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void encodeInto(ChannelHandlerContext ctx, PacketBase packet, ByteBuf data)
/* 40:   */     throws Exception
/* 41:   */   {
/* 42:43 */     packet.writeBytes(data);
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void decodeInto(ChannelHandlerContext ctx, ByteBuf data, PacketBase packet)
/* 46:   */   {
/* 47:48 */     packet.readBytes(data);
/* 48:49 */     EntityPlayer player = null;
/* 49:50 */     switch (cpw.mods.fml.common.FMLCommonHandler.instance().getEffectiveSide())
/* 50:   */     {
/* 51:   */     case CLIENT: 
/* 52:52 */       player = getClientPlayer();
/* 53:53 */       break;
/* 54:   */     case SERVER: 
/* 55:56 */       INetHandler netHandler = (INetHandler)ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
/* 56:57 */       player = ((NetHandlerPlayServer)netHandler).playerEntity;
/* 57:58 */       break;
/* 58:   */     }
/* 59:61 */     packet.execute(player);
/* 60:   */   }
/* 61:   */   
/* 62:   */   @SideOnly(Side.CLIENT)
/* 63:   */   public EntityPlayer getClientPlayer()
/* 64:   */   {
/* 65:65 */     return Minecraft.getMinecraft().thePlayer;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public void sendPaquetToServer(PacketBase packet)
/* 69:   */   {
/* 70:70 */     ((FMLEmbeddedChannel)this.channels.get(Side.CLIENT)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
/* 71:71 */     ((FMLEmbeddedChannel)this.channels.get(Side.CLIENT)).writeOutbound(new Object[] { packet });
/* 72:   */   }
/* 73:   */   
/* 74:   */   public void sendToAllAround(Entity entity, PacketBase packet)
/* 75:   */   {
/* 76:76 */     sendToAllAround(entity, packet, 32);
/* 77:   */   }
/* 78:   */   
/* 79:   */   public void sendToAllAround(Entity entity, PacketBase packet, int range)
/* 80:   */   {
/* 81:80 */     sendToAllAround(packet, new NetworkRegistry.TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, range));
/* 82:   */   }
/* 83:   */   
/* 84:   */   public void sendToAllAround(PacketBase packet, NetworkRegistry.TargetPoint point)
/* 85:   */   {
/* 86:83 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
/* 87:84 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
/* 88:85 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).writeAndFlush(packet);
/* 89:   */   }
/* 90:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.packets.ChannelHandler
 * JD-Core Version:    0.7.1
 */