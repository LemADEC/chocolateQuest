/*   1:    */ package com.chocolate.chocolateQuest.packets;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.block.BlockEditorTileEntity;
/*   5:    */ import com.chocolate.chocolateQuest.builder.schematic.Schematic;
/*   6:    */ import io.netty.buffer.ByteBuf;
/*   7:    */ import java.io.File;
/*   8:    */ import java.util.List;
/*   9:    */ import net.minecraft.client.Minecraft;
/*  10:    */ import net.minecraft.entity.Entity;
/*  11:    */ import net.minecraft.entity.passive.EntityBat;
/*  12:    */ import net.minecraft.entity.player.EntityPlayer;
/*  13:    */ import net.minecraft.tileentity.TileEntity;
/*  14:    */ import net.minecraft.util.AxisAlignedBB;
/*  15:    */ import net.minecraft.world.World;
/*  16:    */ 
/*  17:    */ public class PacketEditorGUIClose
/*  18:    */   extends PacketBase
/*  19:    */ {
/*  20:    */   int x;
/*  21:    */   int y;
/*  22:    */   int z;
/*  23:    */   int width;
/*  24:    */   int height;
/*  25:    */   int length;
/*  26:    */   String text;
/*  27:    */   byte action;
/*  28:    */   
/*  29:    */   public PacketEditorGUIClose() {}
/*  30:    */   
/*  31:    */   public PacketEditorGUIClose(int posX, int posY, int posZ, int width, int length, int height, String name, byte action)
/*  32:    */   {
/*  33: 31 */     this.x = posX;
/*  34: 32 */     this.y = posY;
/*  35: 33 */     this.z = posZ;
/*  36: 34 */     this.width = width;
/*  37: 35 */     this.length = length;
/*  38: 36 */     this.height = height;
/*  39: 37 */     this.text = name;
/*  40: 38 */     this.action = action;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void readBytes(ByteBuf inputStream)
/*  44:    */   {
/*  45: 44 */     this.x = inputStream.readInt();
/*  46: 45 */     this.y = inputStream.readInt();
/*  47: 46 */     this.z = inputStream.readInt();
/*  48: 47 */     this.width = inputStream.readInt();
/*  49: 48 */     this.length = inputStream.readInt();
/*  50: 49 */     this.height = inputStream.readInt();
/*  51: 50 */     this.text = readString(inputStream);
/*  52: 51 */     this.action = inputStream.readByte();
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void writeBytes(ByteBuf outputStream)
/*  56:    */   {
/*  57: 56 */     outputStream.writeInt(this.x);
/*  58: 57 */     outputStream.writeInt(this.y);
/*  59: 58 */     outputStream.writeInt(this.z);
/*  60: 59 */     outputStream.writeInt(this.width);
/*  61: 60 */     outputStream.writeInt(this.length);
/*  62: 61 */     outputStream.writeInt(this.height);
/*  63: 62 */     writeString(outputStream, this.text);
/*  64: 63 */     outputStream.writeByte(this.action);
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void execute(EntityPlayer player)
/*  68:    */   {
/*  69: 67 */     World world = player.worldObj;
/*  70: 68 */     if (world.getBlock(this.x, this.y, this.z) == ChocolateQuest.exporter)
/*  71:    */     {
/*  72: 70 */       BlockEditorTileEntity eb = (BlockEditorTileEntity)world.getTileEntity(this.x, this.y, this.z);
/*  73: 71 */       eb.red = this.width;
/*  74: 72 */       eb.yellow = this.length;
/*  75: 73 */       eb.height = this.height;
/*  76: 74 */       eb.name = this.text;
/*  77:    */     }
/*  78: 76 */     if (this.action == 1) {
/*  79: 78 */       copy(world, this.x, this.y, this.z, this.width, this.height, this.length, this.text);
/*  80:    */     }
/*  81:    */   }
/*  82:    */   
/*  83:    */   public static void copy(World world, int i, int j, int k, int sx, int sy, int sz, String name)
/*  84:    */   {
/*  85: 87 */     i++;
/*  86: 88 */     k++;
/*  87:    */     
/*  88: 90 */     Schematic data = new Schematic(sx, sy, sz, i, j, k, name);
/*  89: 91 */     int cont = 0;
/*  90: 93 */     for (int iX = 0; iX < sx; iX++) {
/*  91: 95 */       for (int iY = 0; iY < sy; iY++) {
/*  92: 97 */         for (int iZ = 0; iZ < sz; iZ++)
/*  93:    */         {
/*  94: 99 */           int x = iX + i;
/*  95:100 */           int y = iY + j;
/*  96:101 */           int z = iZ + k;
/*  97:    */           
/*  98:103 */           data.setBlock(x, y, z, world.getBlock(x, y, z));
/*  99:104 */           data.setBlockMetadata(x, y, z, (byte)world.getBlockMetadata(x, y, z));
/* 100:    */           
/* 101:106 */           TileEntity te = world.getTileEntity(x, y, z);
/* 102:107 */           if (te != null) {
/* 103:109 */             data.addTileEntity(te);
/* 104:    */           }
/* 105:    */         }
/* 106:    */       }
/* 107:    */     }
/* 108:116 */     List<Entity> li = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(i, j, k, i + sx, j + sy, k + sz));
/* 109:117 */     for (int r = 0; r < li.size(); r++) {
/* 110:119 */       if ((!(li.get(r) instanceof EntityPlayer)) && (!(li.get(r) instanceof EntityBat)))
/* 111:    */       {
/* 112:121 */         Entity e = (Entity)li.get(r);
/* 113:122 */         data.addEntity(e);
/* 114:    */       }
/* 115:    */     }
/* 116:127 */     File file = new File(Minecraft.getMinecraft().mcDataDir, "config/Chocolate/Building/test/" + name + ".schematic");
/* 117:128 */     data.save(file);
/* 118:    */   }
/* 119:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.packets.PacketEditorGUIClose
 * JD-Core Version:    0.7.1
 */