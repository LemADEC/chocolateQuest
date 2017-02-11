/*   1:    */ package com.chocolate.chocolateQuest.packets;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.magic.Elements;
/*   4:    */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*   5:    */ import io.netty.buffer.ByteBuf;
/*   6:    */ import java.util.Random;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.entity.player.EntityPlayer;
/*   9:    */ import net.minecraft.world.World;
/*  10:    */ 
/*  11:    */ public class PacketSpawnParticlesAround
/*  12:    */   extends PacketBase
/*  13:    */ {
/*  14:    */   public static final byte HEARTS = 0;
/*  15:    */   public static final byte CRIT = 1;
/*  16:    */   public static final byte FLAME_LAUNCH = 2;
/*  17:    */   public static final byte FLAMES = 3;
/*  18:    */   public static final byte SPARK = 4;
/*  19:    */   public static final byte SMOKE = 5;
/*  20:    */   public static final byte WITCH_MAGIC = 6;
/*  21:    */   public static final byte FIRE = 100;
/*  22:    */   public static final byte BLAST = 101;
/*  23:    */   public static final byte MAGIC = 102;
/*  24:    */   public static final byte PHYSIC = 103;
/*  25:    */   public static final byte LIGHT = 104;
/*  26:    */   public static final byte DARK = 105;
/*  27:    */   byte type;
/*  28:    */   double x;
/*  29:    */   double y;
/*  30:    */   double z;
/*  31:    */   
/*  32:    */   public PacketSpawnParticlesAround() {}
/*  33:    */   
/*  34:    */   public PacketSpawnParticlesAround(byte animType, double x, double y, double z)
/*  35:    */   {
/*  36: 71 */     this.type = animType;
/*  37: 72 */     this.x = x;
/*  38: 73 */     this.y = y;
/*  39: 74 */     this.z = z;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void execute(EntityPlayer player)
/*  43:    */   {
/*  44: 79 */     if (this.type == 2)
/*  45:    */     {
/*  46: 80 */       Entity e = player.worldObj.getEntityByID((int)this.x);
/*  47: 81 */       Random itemRand = new Random();
/*  48: 82 */       if (e != null)
/*  49:    */       {
/*  50: 83 */         float x = (float)-Math.sin(Math.toRadians(e.rotationYaw));
/*  51: 84 */         float z = (float)Math.cos(Math.toRadians(e.rotationYaw));
/*  52: 85 */         float y = (float)-Math.sin(Math.toRadians(e.rotationPitch));
/*  53: 86 */         x *= (1.0F - Math.abs(y));
/*  54: 87 */         z *= (1.0F - Math.abs(y));
/*  55: 88 */         for (int i = 0; i < 50; i++)
/*  56:    */         {
/*  57: 90 */           double flameRandomMotion = itemRand.nextDouble() + 0.2D;
/*  58: 91 */           float height = e.height * 0.7F;
/*  59: 92 */           if ((e instanceof EntityPlayer)) {
/*  60: 93 */             height = 0.0F;
/*  61:    */           }
/*  62: 95 */           EffectManager.spawnParticle(3, e.worldObj, e.posX, e.posY + height, e.posZ, (x + (itemRand.nextDouble() - 0.5D) / 3.0D) * flameRandomMotion, (y + (itemRand.nextDouble() - 0.5D) / 3.0D) * flameRandomMotion, (z + (itemRand.nextDouble() - 0.5D) / 3.0D) * flameRandomMotion);
/*  63:    */         }
/*  64:    */       }
/*  65:102 */       return;
/*  66:    */     }
/*  67:105 */     if (this.type >= 100)
/*  68:    */     {
/*  69:106 */       Random rand = player.worldObj.rand;
/*  70:107 */       double motion = 0.05D;
/*  71:108 */       double posVar = 3.0D;
/*  72:109 */       Elements element = Elements.fire;
/*  73:110 */       if (this.type == 101) {
/*  74:111 */         element = Elements.blast;
/*  75:    */       }
/*  76:112 */       if (this.type == 102) {
/*  77:113 */         element = Elements.magic;
/*  78:    */       }
/*  79:114 */       if (this.type == 103) {
/*  80:115 */         element = Elements.physic;
/*  81:    */       }
/*  82:116 */       if (this.type == 104) {
/*  83:117 */         element = Elements.light;
/*  84:    */       }
/*  85:118 */       if (this.type == 105) {
/*  86:119 */         element = Elements.darkness;
/*  87:    */       }
/*  88:120 */       for (int i = 0; i < 8; i++) {
/*  89:121 */         EffectManager.spawnElementParticle(0, player.worldObj, this.x + rand.nextGaussian() / posVar, this.y + rand.nextGaussian() / posVar, this.z + rand.nextGaussian() / posVar, rand.nextGaussian() * motion, rand.nextGaussian() * motion, rand.nextGaussian() * motion, element);
/*  90:    */       }
/*  91:    */     }
/*  92:    */     else
/*  93:    */     {
/*  94:124 */       String part = null;
/*  95:125 */       int ammount = 8;
/*  96:126 */       double motion = 0.25D;
/*  97:127 */       double posVar = 3.0D;
/*  98:128 */       switch (this.type)
/*  99:    */       {
/* 100:    */       case 0: 
/* 101:130 */         part = "heart";
/* 102:131 */         ammount = 4; break;
/* 103:    */       case 1: 
/* 104:133 */         part = "crit";
/* 105:134 */         break;
/* 106:    */       case 3: 
/* 107:136 */         part = "flame";
/* 108:137 */         motion = 0.08D;
/* 109:138 */         break;
/* 110:    */       case 6: 
/* 111:140 */         part = "witchMagic";
/* 112:141 */         break;
/* 113:    */       case 5: 
/* 114:143 */         part = "smoke";
/* 115:144 */         motion = 0.02D;
/* 116:145 */         posVar = 6.0D;
/* 117:146 */         break;
/* 118:    */       case 4: 
/* 119:148 */         part = "fireworksSpark";
/* 120:149 */         break;
/* 121:    */       case 2: 
/* 122:    */       default: 
/* 123:151 */         part = "crit";
/* 124:    */       }
/* 125:153 */       Random rand = player.worldObj.rand;
/* 126:154 */       for (int i = 0; i < ammount; i++) {
/* 127:155 */         player.worldObj.spawnParticle(part, this.x + rand.nextGaussian() / posVar, this.y + rand.nextGaussian() / posVar, this.z + rand.nextGaussian() / posVar, rand.nextGaussian() * motion, rand.nextGaussian() * motion, rand.nextGaussian() * motion);
/* 128:    */       }
/* 129:    */     }
/* 130:    */   }
/* 131:    */   
/* 132:    */   public void readBytes(ByteBuf bytes)
/* 133:    */   {
/* 134:162 */     this.type = bytes.readByte();
/* 135:163 */     this.x = bytes.readDouble();
/* 136:164 */     this.y = bytes.readDouble();
/* 137:165 */     this.z = bytes.readDouble();
/* 138:    */   }
/* 139:    */   
/* 140:    */   public void writeBytes(ByteBuf bytes)
/* 141:    */   {
/* 142:170 */     bytes.writeByte(this.type);
/* 143:171 */     bytes.writeDouble(this.x);
/* 144:172 */     bytes.writeDouble(this.y);
/* 145:173 */     bytes.writeDouble(this.z);
/* 146:    */   }
/* 147:    */   
/* 148:    */   public static byte getParticleFromName(String name)
/* 149:    */   {
/* 150:177 */     if (name.equals("smoke")) {
/* 151:178 */       return 101;
/* 152:    */     }
/* 153:179 */     if (name.equals("flame")) {
/* 154:180 */       return 3;
/* 155:    */     }
/* 156:181 */     if (name.equals("witchMagic")) {
/* 157:182 */       return 102;
/* 158:    */     }
/* 159:183 */     if (name.equals("fireworksSpark")) {
/* 160:184 */       return 103;
/* 161:    */     }
/* 162:185 */     if (name.equals("crit")) {
/* 163:186 */       return 1;
/* 164:    */     }
/* 165:187 */     if (name.equals("heart")) {
/* 166:188 */       return 0;
/* 167:    */     }
/* 168:190 */     if (name.equals("fire")) {
/* 169:191 */       return 100;
/* 170:    */     }
/* 171:192 */     if (name.equals("blast")) {
/* 172:193 */       return 101;
/* 173:    */     }
/* 174:194 */     if (name.equals("magic")) {
/* 175:195 */       return 102;
/* 176:    */     }
/* 177:196 */     if (name.equals("physic")) {
/* 178:197 */       return 103;
/* 179:    */     }
/* 180:198 */     if (name.equals("light")) {
/* 181:199 */       return 104;
/* 182:    */     }
/* 183:200 */     if (name.equals("dark")) {
/* 184:201 */       return 105;
/* 185:    */     }
/* 186:203 */     if (name.equals("fireE")) {
/* 187:204 */       return 100;
/* 188:    */     }
/* 189:205 */     if (name.equals("blastE")) {
/* 190:206 */       return 101;
/* 191:    */     }
/* 192:207 */     if (name.equals("magicE")) {
/* 193:208 */       return 102;
/* 194:    */     }
/* 195:209 */     if (name.equals("physicE")) {
/* 196:210 */       return 103;
/* 197:    */     }
/* 198:212 */     return 0;
/* 199:    */   }
/* 200:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.packets.PacketSpawnParticlesAround
 * JD-Core Version:    0.7.1
 */