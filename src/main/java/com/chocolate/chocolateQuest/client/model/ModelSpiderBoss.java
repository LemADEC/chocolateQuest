/*   1:    */ package com.chocolate.chocolateQuest.client.model;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.boss.AttackPunch;
/*   4:    */ import com.chocolate.chocolateQuest.entity.boss.EntityBaseBoss;
/*   5:    */ import com.chocolate.chocolateQuest.entity.boss.EntitySpiderBoss;
/*   6:    */ import net.minecraft.client.model.ModelBase;
/*   7:    */ import net.minecraft.client.model.ModelRenderer;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.util.MathHelper;
/*  10:    */ 
/*  11:    */ public class ModelSpiderBoss
/*  12:    */   extends ModelBase
/*  13:    */ {
/*  14:    */   public ModelRenderer spiderHead;
/*  15:    */   public ModelRenderer spiderNeck;
/*  16:    */   public ModelRenderer spiderBody;
/*  17:    */   public ModelRenderer[] spiderLeg;
/*  18:    */   public ModelRenderer[] spiderLegPart;
/*  19:    */   public ModelRenderer spiderMouthLeft;
/*  20:    */   public ModelRenderer spiderMouthRight;
/*  21:    */   ModelRenderer rightarm1;
/*  22:    */   ModelRenderer leftarm1;
/*  23:    */   ModelRenderer bipedRightArm;
/*  24:    */   ModelRenderer bipedLeftArm;
/*  25:    */   ModelRenderer leftNeedle;
/*  26:    */   ModelRenderer rightNeedle;
/*  27:    */   
/*  28:    */   public ModelSpiderBoss()
/*  29:    */   {
/*  30: 40 */     float f = 0.0F;
/*  31: 41 */     int i = 15;
/*  32: 42 */     this.textureWidth = 64;
/*  33: 43 */     this.textureHeight = 64;
/*  34: 44 */     this.spiderHead = new ModelRenderer(this, 32, 4);
/*  35: 45 */     this.spiderHead.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, f);
/*  36: 46 */     this.spiderHead.setRotationPoint(0.0F, 0 + i, -3.0F);
/*  37: 47 */     this.spiderHead.setTextureSize(this.textureWidth, this.textureHeight);
/*  38: 48 */     this.spiderNeck = new ModelRenderer(this, 0, 0);
/*  39: 49 */     this.spiderNeck.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, f);
/*  40: 50 */     this.spiderNeck.setRotationPoint(0.0F, i, 0.0F);
/*  41: 51 */     this.spiderNeck.setTextureSize(this.textureWidth, this.textureHeight);
/*  42: 52 */     this.spiderBody = new ModelRenderer(this, 0, 12);
/*  43: 53 */     this.spiderBody.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, f);
/*  44: 54 */     this.spiderBody.setRotationPoint(0.0F, 0 + i, 9.0F);
/*  45: 55 */     this.spiderBody.setTextureSize(this.textureWidth, this.textureHeight);
/*  46: 56 */     this.spiderLeg = new ModelRenderer[8];
/*  47: 57 */     this.spiderLeg[0] = new ModelRenderer(this, 18, 0);
/*  48: 58 */     this.spiderLeg[0].addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  49: 59 */     this.spiderLeg[0].setRotationPoint(-4.0F, 0 + i, 2.0F);
/*  50: 60 */     this.spiderLeg[1] = new ModelRenderer(this, 18, 0);
/*  51: 61 */     this.spiderLeg[1].addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  52: 62 */     this.spiderLeg[1].setRotationPoint(4.0F, 0 + i, 2.0F);
/*  53: 63 */     this.spiderLeg[2] = new ModelRenderer(this, 18, 0);
/*  54: 64 */     this.spiderLeg[2].addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  55: 65 */     this.spiderLeg[2].setRotationPoint(-4.0F, 0 + i, 1.0F);
/*  56: 66 */     this.spiderLeg[3] = new ModelRenderer(this, 18, 0);
/*  57: 67 */     this.spiderLeg[3].addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  58: 68 */     this.spiderLeg[3].setRotationPoint(4.0F, 0 + i, 1.0F);
/*  59: 69 */     this.spiderLeg[4] = new ModelRenderer(this, 18, 0);
/*  60: 70 */     this.spiderLeg[4].addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  61: 71 */     this.spiderLeg[4].setRotationPoint(-4.0F, 0 + i, 0.0F);
/*  62: 72 */     this.spiderLeg[5] = new ModelRenderer(this, 18, 0);
/*  63: 73 */     this.spiderLeg[5].addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  64: 74 */     this.spiderLeg[5].setRotationPoint(4.0F, 0 + i, 0.0F);
/*  65: 75 */     this.spiderLeg[6] = new ModelRenderer(this, 18, 0);
/*  66: 76 */     this.spiderLeg[6].addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  67: 77 */     this.spiderLeg[6].setRotationPoint(-4.0F, 0 + i, -1.0F);
/*  68: 78 */     this.spiderLeg[7] = new ModelRenderer(this, 18, 0);
/*  69: 79 */     this.spiderLeg[7].addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  70: 80 */     this.spiderLeg[7].setRotationPoint(4.0F, 0 + i, -1.0F);
/*  71: 81 */     for (int h = 0; h < this.spiderLeg.length; h++) {
/*  72: 82 */       this.spiderLeg[h].setTextureSize(this.textureWidth, this.textureHeight);
/*  73:    */     }
/*  74: 84 */     this.spiderLegPart = new ModelRenderer[8];
/*  75: 85 */     for (int h = 0; h < this.spiderLegPart.length; h++)
/*  76:    */     {
/*  77: 87 */       this.spiderLegPart[h] = new ModelRenderer(this, 18, 32);
/*  78: 88 */       this.spiderLegPart[h].addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  79: 89 */       this.spiderLegPart[h].setRotationPoint(h % 2 == 0 ? -15.0F : 15.0F, 0.0F, 0.0F);
/*  80: 90 */       setRotation(this.spiderLegPart[h], 0.0F, 0.0F, 1.6F);
/*  81: 91 */       this.spiderLegPart[h].setTextureSize(this.textureWidth, this.textureHeight);
/*  82:    */     }
/*  83: 94 */     this.spiderLeg[0].addChild(this.spiderLegPart[0]);
/*  84: 95 */     this.spiderLeg[1].addChild(this.spiderLegPart[1]);
/*  85: 96 */     this.spiderLeg[2].addChild(this.spiderLegPart[2]);
/*  86: 97 */     this.spiderLeg[3].addChild(this.spiderLegPart[3]);
/*  87: 98 */     this.spiderLeg[4].addChild(this.spiderLegPart[4]);
/*  88: 99 */     this.spiderLeg[5].addChild(this.spiderLegPart[5]);
/*  89:100 */     this.spiderLeg[6].addChild(this.spiderLegPart[6]);
/*  90:101 */     this.spiderLeg[7].addChild(this.spiderLegPart[7]);
/*  91:102 */     this.spiderMouthLeft = new ModelRenderer(this, 46, 20);
/*  92:103 */     this.spiderMouthLeft.addBox(0.0F, -1.0F, -3.0F, 2, 2, 4, f);
/*  93:104 */     this.spiderMouthLeft.setRotationPoint(-3.0F, 3.0F, -8.0F);
/*  94:105 */     this.spiderMouthLeft.setTextureSize(this.textureWidth, this.textureHeight);
/*  95:106 */     this.spiderHead.addChild(this.spiderMouthLeft);
/*  96:107 */     this.spiderMouthRight = new ModelRenderer(this, 46, 20);
/*  97:108 */     this.spiderMouthRight.addBox(0.0F, -1.0F, -3.0F, 2, 2, 4, f);
/*  98:109 */     this.spiderMouthRight.setRotationPoint(1.0F, 3.0F, -8.0F);
/*  99:110 */     this.spiderMouthRight.setTextureSize(this.textureWidth, this.textureHeight);
/* 100:111 */     this.spiderHead.addChild(this.spiderMouthRight);
/* 101:    */     
/* 102:    */ 
/* 103:114 */     this.bipedRightArm = new ModelRenderer(this, 0, 32);
/* 104:115 */     this.bipedRightArm.addBox(-1.0F, -1.0F, -4.0F, 2, 16, 2);
/* 105:116 */     this.bipedRightArm.setRotationPoint(-4.0F, 16.0F, -4.0F);
/* 106:117 */     this.bipedRightArm.setTextureSize(this.textureWidth, this.textureHeight);
/* 107:118 */     this.rightarm1 = new ModelRenderer(this, 8, 36);
/* 108:119 */     this.rightarm1.addBox(-0.5F, 0.0F, -0.5F, 3, 16, 3);
/* 109:120 */     this.rightarm1.setRotationPoint(0.0F, 16.0F, -2.0F);
/* 110:121 */     this.rightarm1.setTextureSize(this.textureWidth, this.textureHeight);
/* 111:122 */     this.bipedRightArm.addChild(this.rightarm1);
/* 112:    */     
/* 113:124 */     this.bipedLeftArm = new ModelRenderer(this, 0, 32);
/* 114:125 */     this.bipedLeftArm.addBox(-1.0F, -1.0F, -4.0F, 2, 16, 2);
/* 115:126 */     this.bipedLeftArm.setRotationPoint(4.0F, 16.0F, -4.0F);
/* 116:127 */     this.bipedLeftArm.setTextureSize(this.textureWidth, this.textureHeight);
/* 117:128 */     this.bipedLeftArm.mirror = true;
/* 118:129 */     this.leftarm1 = new ModelRenderer(this, 8, 36);
/* 119:130 */     this.leftarm1.addBox(-0.5F, 0.0F, -0.5F, 3, 16, 3);
/* 120:131 */     this.leftarm1.setRotationPoint(-1.0F, 16.0F, -2.0F);
/* 121:132 */     this.leftarm1.setTextureSize(this.textureWidth, this.textureHeight);
/* 122:133 */     this.leftarm1.mirror = true;
/* 123:134 */     this.bipedLeftArm.addChild(this.leftarm1);
/* 124:    */     
/* 125:136 */     this.leftNeedle = new ModelRenderer(this, 8, 32);
/* 126:137 */     this.leftNeedle.addBox(0.5F, 0.0F, 0.5F, 1, 3, 1);
/* 127:138 */     this.leftNeedle.setRotationPoint(0.0F, 16.0F, 0.0F);
/* 128:139 */     this.leftNeedle.setTextureSize(this.textureWidth, this.textureHeight);
/* 129:140 */     this.leftarm1.addChild(this.leftNeedle);
/* 130:    */     
/* 131:142 */     this.rightNeedle = new ModelRenderer(this, 8, 32);
/* 132:143 */     this.rightNeedle.addBox(0.5F, 0.0F, 0.5F, 1, 3, 1);
/* 133:144 */     this.rightNeedle.setRotationPoint(0.0F, 16.0F, 0.0F);
/* 134:145 */     this.rightNeedle.setTextureSize(this.textureWidth, this.textureHeight);
/* 135:146 */     this.rightarm1.addChild(this.rightNeedle);
/* 136:    */   }
/* 137:    */   
/* 138:    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 139:    */   {
/* 140:151 */     model.rotateAngleX = x;
/* 141:152 */     model.rotateAngleY = y;
/* 142:153 */     model.rotateAngleZ = z;
/* 143:    */   }
/* 144:    */   
/* 145:    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 146:    */   {
/* 147:158 */     this.textureHeight = 64;
/* 148:159 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 149:160 */     this.leftarm1.setRotationPoint(-1.0F, 14.0F, -2.0F);
/* 150:    */     
/* 151:    */ 
/* 152:163 */     this.spiderLeg[2].render(f5);
/* 153:164 */     this.spiderLeg[3].render(f5);
/* 154:165 */     this.spiderLeg[4].render(f5);
/* 155:166 */     this.spiderLeg[5].render(f5);
/* 156:167 */     this.spiderLeg[6].render(f5);
/* 157:168 */     this.spiderLeg[7].render(f5);
/* 158:169 */     this.spiderBody.render(f5);
/* 159:170 */     this.spiderHead.render(f5);
/* 160:171 */     this.spiderNeck.render(f5);
/* 161:    */   }
/* 162:    */   
/* 163:    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 164:    */   {
/* 165:176 */     this.spiderHead.rotateAngleY = (f3 / 57.295776F);
/* 166:177 */     this.spiderHead.rotateAngleX = (f4 / 57.295776F);
/* 167:178 */     float f6 = 2.66F;
/* 168:    */     
/* 169:    */ 
/* 170:181 */     this.spiderLeg[2].rotateAngleZ = (-f6);
/* 171:182 */     this.spiderLeg[3].rotateAngleZ = f6;
/* 172:183 */     this.spiderLeg[4].rotateAngleZ = (-f6);
/* 173:184 */     this.spiderLeg[5].rotateAngleZ = f6;
/* 174:185 */     this.spiderLeg[6].rotateAngleZ = (-f6);
/* 175:186 */     this.spiderLeg[7].rotateAngleZ = f6;
/* 176:187 */     float f7 = 3.0F;
/* 177:188 */     float f8 = 0.3926991F;
/* 178:    */     
/* 179:    */ 
/* 180:191 */     this.spiderLeg[2].rotateAngleY = (f8 * 1.0F + f7);
/* 181:192 */     this.spiderLeg[3].rotateAngleY = (-f8 * 1.0F - f7);
/* 182:193 */     this.spiderLeg[4].rotateAngleY = (-f8 * 1.0F + f7);
/* 183:194 */     this.spiderLeg[5].rotateAngleY = (f8 * 1.0F - f7);
/* 184:195 */     this.spiderLeg[6].rotateAngleY = (-f8 * 2.0F + f7);
/* 185:196 */     this.spiderLeg[7].rotateAngleY = (f8 * 2.0F - f7);
/* 186:    */     
/* 187:198 */     float f10 = -(MathHelper.cos(f * 0.6662F * 2.0F + 3.141593F) * 0.4F) * f1;
/* 188:199 */     float f11 = -(MathHelper.cos(f * 0.6662F * 2.0F + 1.570796F) * 0.4F) * f1;
/* 189:200 */     float f12 = -(MathHelper.cos(f * 0.6662F * 2.0F + 4.712389F) * 0.4F) * f1;
/* 190:201 */     float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
/* 191:202 */     float f14 = Math.abs(MathHelper.sin(f * 0.6662F + 3.141593F) * 0.4F) * f1;
/* 192:203 */     float f15 = Math.abs(MathHelper.sin(f * 0.6662F + 1.570796F) * 0.4F) * f1;
/* 193:204 */     float f16 = Math.abs(MathHelper.sin(f * 0.6662F + 4.712389F) * 0.4F) * f1;
/* 194:    */     
/* 195:    */ 
/* 196:207 */     this.spiderLeg[2].rotateAngleY += f10;
/* 197:208 */     this.spiderLeg[3].rotateAngleY += -f10;
/* 198:209 */     this.spiderLeg[4].rotateAngleY += f11;
/* 199:210 */     this.spiderLeg[5].rotateAngleY += -f11;
/* 200:211 */     this.spiderLeg[6].rotateAngleY += f12;
/* 201:212 */     this.spiderLeg[7].rotateAngleY += -f12;
/* 202:    */     
/* 203:    */ 
/* 204:215 */     this.spiderLeg[2].rotateAngleZ += f14;
/* 205:216 */     this.spiderLeg[3].rotateAngleZ += -f14;
/* 206:217 */     this.spiderLeg[4].rotateAngleZ += f15;
/* 207:218 */     this.spiderLeg[5].rotateAngleZ += -f15;
/* 208:219 */     this.spiderLeg[6].rotateAngleZ += f16;
/* 209:220 */     this.spiderLeg[7].rotateAngleZ += -f16;
/* 210:221 */     float animTime = ((EntityBaseBoss)entity).swingProgress;
/* 211:222 */     this.spiderMouthLeft.rotateAngleY = animTime;
/* 212:223 */     this.spiderMouthRight.rotateAngleY = (-animTime);
/* 213:    */     
/* 214:225 */     EntitySpiderBoss e = (EntitySpiderBoss)entity;
/* 215:226 */     if (e.rightHand != null)
/* 216:    */     {
/* 217:228 */       this.bipedRightArm.rotateAngleX = 1.15F;
/* 218:229 */       this.bipedRightArm.rotateAngleZ = 3.141592F;
/* 219:230 */       this.bipedLeftArm.rotateAngleX = 1.15F;
/* 220:231 */       this.bipedLeftArm.rotateAngleZ = 3.141592F;
/* 221:    */       
/* 222:233 */       float dist = (float)e.getDistance(e.posX + e.rightHand.posX, e.rightHand.posY - e.getScaleSize() / 2.0F + e.posY + e.rightHand.getShoulderHeight(), e.rightHand.posZ + e.posZ);
/* 223:234 */       float yDist = (float)-(e.rightHand.getShoulderHeight() + e.rightHand.posY);
/* 224:235 */       float HA = (float)(dist / e.rightHand.getArmLength());
/* 225:236 */       float pitch = (float)(yDist / e.rightHand.getArmLength() * 3.14159D);
/* 226:237 */       this.bipedRightArm.rotateAngleX += 1.0F + pitch / 2.0F - MathHelper.cos(HA);
/* 227:238 */       this.rightarm1.rotateAngleX = (3.1416F - MathHelper.sin(HA) * 3.14159F + Math.max(pitch / 8.0F, 0.0F));
/* 228:    */       
/* 229:240 */       float entityRot = e.rotationYaw * 3.141592F / 180.0F + 0.2F;
/* 230:241 */       float shoulderAngle = (float)getAngleBetweenEntities(e, e.rightHand, f5) - entityRot;
/* 231:242 */       this.bipedRightArm.rotateAngleY = (-shoulderAngle);
/* 232:    */       
/* 233:244 */       dist = (float)e.getDistance(e.posX + e.leftHand.posX, e.leftHand.posY - e.getScaleSize() / 2.0F + e.posY + e.leftHand.getShoulderHeight(), e.leftHand.posZ + e.posZ);
/* 234:245 */       yDist = (float)-(e.leftHand.getShoulderHeight() + e.leftHand.posY);
/* 235:246 */       HA = (float)(dist / e.leftHand.getArmLength());
/* 236:247 */       pitch = (float)(yDist / e.leftHand.getArmLength() * 3.14159D);
/* 237:248 */       this.bipedLeftArm.rotateAngleX += 1.0F + pitch / 2.0F - MathHelper.cos(HA);
/* 238:249 */       this.leftarm1.rotateAngleX = (3.1416F - MathHelper.sin(HA) * 3.14159F + Math.max(pitch / 8.0F, 0.0F));
/* 239:250 */       entityRot = e.rotationYaw * 3.141592F / 180.0F;
/* 240:251 */       shoulderAngle = (float)getAngleBetweenEntities(e, e.leftHand, f5) - entityRot;
/* 241:252 */       this.bipedLeftArm.rotateAngleY = (-shoulderAngle);
/* 242:    */     }
/* 243:254 */     this.bipedLeftArm.render(f5);
/* 244:255 */     this.bipedRightArm.render(f5);
/* 245:    */   }
/* 246:    */   
/* 247:    */   public double getAngleBetweenEntities(Entity entity, AttackPunch part, float tickTime)
/* 248:    */   {
/* 249:261 */     double d = -part.posX;
/* 250:262 */     double d2 = -part.posZ;
/* 251:263 */     double angle = Math.atan2(d, d2);
/* 252:264 */     return -angle;
/* 253:    */   }
/* 254:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelSpiderBoss
 * JD-Core Version:    0.7.1
 */