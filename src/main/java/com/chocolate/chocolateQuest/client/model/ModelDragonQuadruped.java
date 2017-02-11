/*   1:    */ package com.chocolate.chocolateQuest.client.model;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.boss.EntityWyvern;
/*   4:    */ import net.minecraft.client.model.ModelBase;
/*   5:    */ import net.minecraft.client.model.ModelRenderer;
/*   6:    */ import net.minecraft.entity.Entity;
/*   7:    */ import net.minecraft.util.MathHelper;
/*   8:    */ 
/*   9:    */ public class ModelDragonQuadruped
/*  10:    */   extends ModelBase
/*  11:    */ {
/*  12:    */   ModelRenderer rightWing;
/*  13:    */   ModelRenderer rightWingPart;
/*  14:    */   ModelRenderer rightWingArm;
/*  15:    */   ModelRenderer rightWingArmPart;
/*  16:    */   ModelRenderer leftWing;
/*  17:    */   ModelRenderer leftWingPart;
/*  18:    */   ModelRenderer leftWingArm;
/*  19:    */   ModelRenderer leftWingArmPart;
/*  20:    */   ModelRenderer body1;
/*  21:    */   ModelRenderer body2;
/*  22:    */   ModelRenderer body3;
/*  23:    */   ModelRenderer[] portal;
/*  24:    */   ModelRenderer neck;
/*  25:    */   ModelRenderer tail;
/*  26:    */   ModelRenderer tail2;
/*  27:    */   ModelRenderer tailEnd;
/*  28:    */   ModelRenderer head;
/*  29:    */   ModelRenderer mouthUp;
/*  30:    */   ModelRenderer mouthDown;
/*  31:    */   ModelRenderer nose;
/*  32:    */   ModelRenderer hornLeft;
/*  33:    */   ModelRenderer hornRight;
/*  34:    */   ModelRenderer[] leg;
/*  35:    */   ModelRenderer[] leg1;
/*  36:    */   ModelRenderer[] foot;
/*  37:    */   
/*  38:    */   public ModelDragonQuadruped()
/*  39:    */   {
/*  40: 24 */     int textureSizeX = 128;int textureSizeY = 64;
/*  41:    */     
/*  42: 26 */     this.body1 = new ModelRenderer(this, 0, 0).setTextureSize(textureSizeX, textureSizeY);
/*  43: 27 */     this.body1.addBox(-7.0F, -7.0F, -7.0F, 14, 14, 14);
/*  44: 28 */     this.body1.setRotationPoint(0.0F, 0.0F, -10.0F);
/*  45: 29 */     this.body1.rotateAngleX = 45.0F;
/*  46: 30 */     this.body1.rotateAngleY = 0.0F;
/*  47: 31 */     this.body1.rotateAngleZ = 0.0F;
/*  48:    */     
/*  49: 33 */     this.body2 = new ModelRenderer(this, 25, 40).setTextureSize(textureSizeX, textureSizeY);
/*  50: 34 */     this.body2.addBox(-5.0F, -4.0F, -8.0F, 10, 8, 16);
/*  51: 35 */     this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  52: 36 */     this.body2.rotateAngleX = 0.0F;
/*  53: 37 */     this.body2.rotateAngleY = 0.0F;
/*  54: 38 */     this.body2.rotateAngleZ = 0.0F;
/*  55:    */     
/*  56: 40 */     this.body3 = new ModelRenderer(this, 0, 28).setTextureSize(textureSizeX, textureSizeY);
/*  57: 41 */     this.body3.addBox(-5.0F, -5.0F, -5.0F, 10, 10, 10);
/*  58: 42 */     this.body3.setRotationPoint(0.0F, 0.0F, 10.0F);
/*  59: 43 */     this.body3.rotateAngleX = 45.0F;
/*  60: 44 */     this.body3.rotateAngleY = 0.0F;
/*  61: 45 */     this.body3.rotateAngleZ = 0.0F;
/*  62:    */     
/*  63: 47 */     this.neck = new ModelRenderer(this, 0, 51).setTextureSize(textureSizeX, textureSizeY);
/*  64: 48 */     this.neck.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
/*  65: 49 */     this.neck.setRotationPoint(0.0F, -6.0F, -14.0F);
/*  66: 50 */     this.neck.rotateAngleX = 0.0F;
/*  67: 51 */     this.neck.rotateAngleY = 0.0F;
/*  68: 52 */     this.neck.rotateAngleZ = 0.0F;
/*  69:    */     
/*  70: 54 */     this.tail = new ModelRenderer(this, 40, 28).setTextureSize(textureSizeX, textureSizeY);
/*  71: 55 */     this.tail.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4);
/*  72: 56 */     this.tail.setRotationPoint(0.0F, -3.0F, 14.0F);
/*  73: 57 */     this.tail.rotateAngleX = 0.0F;
/*  74: 58 */     this.tail.rotateAngleY = 0.0F;
/*  75: 59 */     this.tail.rotateAngleZ = 0.0F;
/*  76:    */     
/*  77: 61 */     this.tail2 = new ModelRenderer(this, 42, 30).setTextureSize(textureSizeX, textureSizeY);
/*  78: 62 */     this.tail2.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
/*  79: 63 */     this.tail2.setRotationPoint(0.0F, -3.0F, 14.0F);
/*  80:    */     
/*  81: 65 */     this.tailEnd = new ModelRenderer(this, 44, 0).setTextureSize(textureSizeX, textureSizeY);
/*  82: 66 */     this.tailEnd.addBox(0.0F, -2.0F, -2.0F, 0, 4, 4);
/*  83:    */     
/*  84: 68 */     this.leg = new ModelRenderer[4];
/*  85: 69 */     this.leg1 = new ModelRenderer[4];
/*  86: 70 */     this.foot = new ModelRenderer[4];
/*  87: 71 */     for (int i = 0; i < 4; i++)
/*  88:    */     {
/*  89: 73 */       this.leg[i] = new ModelRenderer(this, 56, 0).setTextureSize(textureSizeX, textureSizeY);
/*  90: 74 */       this.leg[i].addBox(-1.0F, -4.0F, -2.0F, 4, 14, 4);
/*  91:    */       
/*  92: 76 */       this.leg1[i] = new ModelRenderer(this, 56, 19).setTextureSize(textureSizeX, textureSizeY);
/*  93: 77 */       this.leg1[i].addBox(-1.5F, 1.0F, -1.5F, 3, 10, 3);
/*  94: 78 */       this.leg[i].addChild(this.leg1[i]);
/*  95: 79 */       this.leg1[i].setRotationPoint(1.0F, 7.0F, -1.0F);
/*  96:    */       
/*  97: 81 */       this.foot[i] = new ModelRenderer(this, 52, 32).setTextureSize(textureSizeX, textureSizeY);
/*  98: 82 */       this.foot[i].addBox(-1.5F, -1.0F, -4.0F, 3, 2, 5);
/*  99: 83 */       this.leg1[i].addChild(this.foot[i]);
/* 100: 84 */       this.foot[i].setRotationPoint(0.0F, 11.0F, 0.0F);
/* 101:    */     }
/* 102: 87 */     this.leg[0].setRotationPoint(8.0F, 5.0F, -10.0F);
/* 103: 88 */     this.leg[1].setRotationPoint(-10.0F, 5.0F, -10.0F);
/* 104: 89 */     this.leg[2].setRotationPoint(6.0F, 5.0F, 10.0F);
/* 105: 90 */     this.leg[3].setRotationPoint(-8.0F, 5.0F, 10.0F);
/* 106:    */     
/* 107:    */ 
/* 108: 93 */     this.head = new ModelRenderer(this, 74, 0).setTextureSize(textureSizeX, textureSizeY);
/* 109: 94 */     this.head.addBox(-2.5F, -3.0F, -4.0F, 5, 5, 8);
/* 110:    */     
/* 111: 96 */     this.mouthUp = new ModelRenderer(this, 106, 0).setTextureSize(textureSizeX, textureSizeY);
/* 112: 97 */     this.mouthUp.addBox(-2.0F, -2.0F, -11.0F, 4, 3, 7);
/* 113: 98 */     this.mouthDown = new ModelRenderer(this, 92, 0).setTextureSize(textureSizeX, textureSizeY);
/* 114: 99 */     this.mouthDown.addBox(-2.0F, 1.0F, -10.0F, 4, 1, 6);
/* 115:100 */     this.nose = new ModelRenderer(this, 107, 11).setTextureSize(textureSizeX, textureSizeY);
/* 116:101 */     this.nose.addBox(-2.0F, -3.0F, -11.0F, 4, 1, 1);
/* 117:102 */     this.hornLeft = new ModelRenderer(this, 94, 8).setTextureSize(textureSizeX, textureSizeY);
/* 118:103 */     this.hornLeft.addBox(1.5F, -2.0F, 3.0F, 1, 1, 8);
/* 119:104 */     this.hornLeft.rotateAngleX = 0.5F;
/* 120:105 */     this.hornLeft.rotateAngleY = 0.098F;
/* 121:106 */     this.hornRight = new ModelRenderer(this, 94, 8).setTextureSize(textureSizeX, textureSizeY);
/* 122:107 */     this.hornRight.addBox(-2.5F, -2.0F, 3.0F, 1, 1, 8);
/* 123:108 */     this.hornRight.rotateAngleX = 0.5F;
/* 124:109 */     this.hornRight.rotateAngleY = -0.298F;
/* 125:    */     
/* 126:111 */     this.head.addChild(this.mouthUp);
/* 127:112 */     this.head.addChild(this.mouthDown);
/* 128:113 */     this.head.addChild(this.nose);
/* 129:114 */     this.head.addChild(this.hornLeft);
/* 130:115 */     this.head.addChild(this.hornRight);
/* 131:    */     
/* 132:    */ 
/* 133:118 */     this.rightWing = new ModelRenderer(this, 58, 43).setTextureSize(textureSizeX, textureSizeY);
/* 134:119 */     this.rightWing.addBox(0.0F, 0.0F, 0.0F, 14, 0, 21);
/* 135:120 */     this.rightWing.setRotationPoint(6.0F, -4.0F, -10.0F);
/* 136:121 */     this.rightWingPart = new ModelRenderer(this, 46, 21).setTextureSize(textureSizeX, textureSizeY);
/* 137:122 */     this.rightWingPart.addBox(0.0F, 0.0F, 0.0F, 30, 0, 22);
/* 138:123 */     this.rightWingPart.setRotationPoint(14.0F, 0.0F, 0.0F);
/* 139:124 */     this.rightWingArm = new ModelRenderer(this, 82, 17).setTextureSize(textureSizeX, textureSizeY);
/* 140:125 */     this.rightWingArm.addBox(0.0F, -1.0F, 0.0F, 14, 2, 2);
/* 141:126 */     this.rightWingArmPart = new ModelRenderer(this, 64, 18).setTextureSize(textureSizeX, textureSizeY);
/* 142:127 */     this.rightWingArmPart.addBox(0.0F, -1.0F, 0.0F, 30, 1, 1);
/* 143:    */     
/* 144:129 */     this.rightWing.addChild(this.rightWingPart);
/* 145:130 */     this.rightWing.addChild(this.rightWingArm);
/* 146:131 */     this.rightWingPart.addChild(this.rightWingArmPart);
/* 147:    */     
/* 148:    */ 
/* 149:134 */     this.leftWing = new ModelRenderer(this, 58, 43).setTextureSize(textureSizeX, textureSizeY);
/* 150:135 */     this.leftWing.addBox(0.0F, 0.0F, 0.0F, 14, 0, 21);
/* 151:136 */     this.leftWing.setRotationPoint(-6.0F, -4.0F, -10.0F);
/* 152:137 */     this.leftWingPart = new ModelRenderer(this, 46, 21).setTextureSize(textureSizeX, textureSizeY);
/* 153:138 */     this.leftWingPart.addBox(0.0F, 0.0F, 0.0F, 30, 0, 22);
/* 154:139 */     this.leftWingPart.setRotationPoint(14.0F, 0.0F, 0.0F);
/* 155:140 */     this.leftWingArm = new ModelRenderer(this, 82, 17).setTextureSize(textureSizeX, textureSizeY);
/* 156:141 */     this.leftWingArm.addBox(0.0F, -1.0F, 0.0F, 14, 2, 2);
/* 157:142 */     this.leftWingArmPart = new ModelRenderer(this, 64, 18).setTextureSize(textureSizeX, textureSizeY);
/* 158:143 */     this.leftWingArmPart.addBox(0.0F, -1.0F, 0.0F, 30, 1, 1);
/* 159:    */     
/* 160:145 */     this.leftWing.addChild(this.leftWingPart);
/* 161:146 */     this.leftWing.addChild(this.leftWingArm);
/* 162:147 */     this.leftWingPart.addChild(this.leftWingArmPart);
/* 163:    */   }
/* 164:    */   
/* 165:    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 166:    */   {
/* 167:153 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 168:154 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 169:    */     
/* 170:    */ 
/* 171:    */ 
/* 172:158 */     this.rightWing.render(f5);
/* 173:159 */     this.leftWing.render(f5);
/* 174:    */     
/* 175:161 */     this.body1.render(f5);
/* 176:162 */     this.body2.render(f5);
/* 177:163 */     this.body3.render(f5);
/* 178:164 */     for (int i = 0; i < 4; i++) {
/* 179:166 */       this.leg[i].render(f5);
/* 180:    */     }
/* 181:170 */     float px = 0.0F;float py = -6.0F;float pz = -14.0F;
/* 182:    */     
/* 183:172 */     float rotX = MathHelper.cos(f / 1.919108F) * 0.261799F * f1;
/* 184:173 */     for (int i = 0; i < 4; i++)
/* 185:    */     {
/* 186:175 */       float f10 = (float)Math.cos(i * 0.45F) * 0.15F;
/* 187:176 */       this.neck.rotateAngleX = (i / 4.0F - 0.4F + this.head.rotateAngleX);
/* 188:177 */       this.neck.rotateAngleY = (this.head.rotateAngleY * (i / 1.8F));
/* 189:178 */       this.neck.rotateAngleZ = (this.head.rotateAngleY / 10.0F);
/* 190:179 */       this.neck.rotationPointX = px;
/* 191:180 */       this.neck.rotationPointY = py;
/* 192:181 */       this.neck.rotationPointZ = pz;
/* 193:182 */       px = (float)(px - Math.sin(this.neck.rotateAngleY) * Math.cos(this.neck.rotateAngleX) * 6.0D);
/* 194:183 */       py = (float)(py + Math.sin(this.neck.rotateAngleX) * 6.0D);
/* 195:184 */       pz = (float)(pz - Math.cos(this.neck.rotateAngleY) * Math.cos(this.neck.rotateAngleX) * 6.0D);
/* 196:185 */       this.neck.render(f5);
/* 197:    */     }
/* 198:188 */     this.head.rotationPointX = px;
/* 199:189 */     this.head.rotationPointY = (py - 0.4F);
/* 200:190 */     this.head.rotationPointZ = pz;
/* 201:191 */     this.head.rotateAngleY = this.neck.rotateAngleY;
/* 202:    */     
/* 203:193 */     this.head.render(f5);
/* 204:    */     
/* 205:195 */     px = 0.0F;
/* 206:196 */     py = -3.0F;
/* 207:197 */     pz = 14.0F;
/* 208:198 */     for (int i = 0; i < 10; i++)
/* 209:    */     {
/* 210:200 */       float rotXVar = (float)Math.cos((i / 10.0D + f / 10.0F) * 3.141592653589793D * 2.0D) * 0.5F;
/* 211:201 */       float AnimOnTime = (float)Math.cos((i / 10.0D + f2 / 50.0F) * 3.141592653589793D * 2.0D);
/* 212:202 */       this.tail.rotateAngleX = (3.16F + rotXVar);
/* 213:203 */       this.tail.rotateAngleY = AnimOnTime;
/* 214:204 */       this.tail.rotateAngleZ = 0.0F;
/* 215:205 */       this.tail.rotationPointX = px;
/* 216:206 */       this.tail.rotationPointY = py;
/* 217:207 */       this.tail.rotationPointZ = pz;
/* 218:208 */       px = (float)(px - Math.sin(this.tail.rotateAngleY) * Math.cos(this.tail.rotateAngleX) * 4.0D);
/* 219:209 */       py = (float)(py + Math.sin(this.tail.rotateAngleX) * 4.0D);
/* 220:210 */       pz = (float)(pz - Math.cos(this.tail.rotateAngleY) * Math.cos(this.tail.rotateAngleX) * 4.0D);
/* 221:211 */       this.tail.render(f5);
/* 222:    */     }
/* 223:214 */     px += 2.0F;
/* 224:215 */     pz -= 2.0F;
/* 225:216 */     for (int i = 0; i < 10; i++)
/* 226:    */     {
/* 227:218 */       float f10 = (float)Math.cos((i / 10.0D + f / 10.0F) * 3.141592653589793D * 2.0D);
/* 228:219 */       float AnimOnTime = (float)Math.cos((i / 10.0D + f2 / 40.0F) * 3.141592653589793D * 2.0D);
/* 229:220 */       this.tail2.rotateAngleX = (3.16F + f10);
/* 230:221 */       this.tail2.rotateAngleY = AnimOnTime;
/* 231:222 */       this.tail2.rotateAngleZ = 0.0F;
/* 232:223 */       this.tail2.rotationPointX = px;
/* 233:224 */       this.tail2.rotationPointY = py;
/* 234:225 */       this.tail2.rotationPointZ = pz;
/* 235:226 */       px = (float)(px - Math.sin(this.tail2.rotateAngleY) * Math.cos(this.tail.rotateAngleX) * 2.0D);
/* 236:227 */       py = (float)(py + Math.sin(this.tail2.rotateAngleX) * 2.0D);
/* 237:228 */       pz = (float)(pz - Math.cos(this.tail2.rotateAngleY) * Math.cos(this.tail.rotateAngleX) * 2.0D);
/* 238:229 */       this.tail2.render(f5);
/* 239:    */     }
/* 240:231 */     this.tailEnd.rotationPointX = px;
/* 241:232 */     this.tailEnd.rotationPointY = py;
/* 242:233 */     this.tailEnd.rotationPointZ = pz;
/* 243:    */     
/* 244:235 */     this.tailEnd.render(f5);
/* 245:    */   }
/* 246:    */   
/* 247:    */   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/* 248:    */   {
/* 249:245 */     this.head.rotateAngleX = (par5 / 57.295776F);
/* 250:246 */     this.head.rotateAngleY = (par4 / 57.295776F);
/* 251:    */     
/* 252:248 */     float rx = MathHelper.cos(par1 * 0.4662F) * 1.4F * par2;
/* 253:249 */     for (int i = 0; i < 4; i++)
/* 254:    */     {
/* 255:251 */       float sign = i % 2 == 0 ? 1.0F : -1.0F;
/* 256:    */       
/* 257:253 */       this.leg[i].rotateAngleX = (rx * sign);
/* 258:254 */       this.leg1[i].rotateAngleX = (-0.436F - rx * 2.0F * sign);
/* 259:255 */       this.foot[i].rotateAngleX = (0.576F - rx * -0.3F * sign);
/* 260:    */     }
/* 261:257 */     float yOffsetSit = 14.0F;
/* 262:    */     
/* 263:    */ 
/* 264:260 */     this.rightWing.rotateAngleZ = -1.3F;
/* 265:261 */     this.rightWingPart.rotateAngleY = -1.5F;
/* 266:262 */     this.rightWingPart.rotateAngleZ = 0.0F;
/* 267:280 */     if (!entity.onGround)
/* 268:    */     {
/* 269:282 */       for (int i = 0; i < 4; i++)
/* 270:    */       {
/* 271:284 */         this.leg[i].rotateAngleX = -0.9F;
/* 272:285 */         this.leg1[i].rotateAngleX = 2.4F;
/* 273:    */       }
/* 274:287 */       float motionOnTime = MathHelper.cos((par3 + par1) * 0.1F);
/* 275:288 */       float wingRotation = motionOnTime;
/* 276:289 */       this.rightWing.rotateAngleZ = wingRotation;
/* 277:290 */       this.rightWingPart.rotateAngleZ = (wingRotation / 2.0F);
/* 278:291 */       this.rightWingPart.rotateAngleY = -0.25F;
/* 279:    */       
/* 280:293 */       this.leftWing.rotateAngleZ = (-wingRotation);
/* 281:294 */       this.leftWingPart.rotateAngleZ = (-wingRotation / 2.0F);
/* 282:295 */       this.leftWingPart.rotateAngleY = -0.25F;
/* 283:    */     }
/* 284:298 */     if (this.onGround > 0.0F)
/* 285:    */     {
/* 286:300 */       this.leg[1].rotateAngleX = (MathHelper.sin(this.onGround * 3.141593F) * -1.4F);
/* 287:301 */       this.leg[1].rotateAngleZ = (MathHelper.sin(this.onGround * 3.141593F) * -1.0F);
/* 288:    */       
/* 289:303 */       this.leg[0].rotateAngleX = (MathHelper.sin(this.onGround * 3.141593F) * -1.4F);
/* 290:304 */       this.leg[0].rotateAngleZ = MathHelper.sin(this.onGround * 3.141593F);
/* 291:    */     }
/* 292:306 */     this.leftWing.rotateAngleZ = (3.1416F - this.rightWing.rotateAngleZ);
/* 293:307 */     this.leftWingPart.rotateAngleZ = (-this.rightWingPart.rotateAngleZ);
/* 294:308 */     this.leftWingPart.rotateAngleY = this.rightWingPart.rotateAngleY;
/* 295:    */     
/* 296:310 */     EntityWyvern e = (EntityWyvern)entity;
/* 297:311 */     this.mouthDown.rotateAngleX = 0.0F;
/* 298:312 */     this.mouthDown.rotateAngleY = 0.0F;
/* 299:313 */     if (e.openMouthTime > 0)
/* 300:    */     {
/* 301:315 */       e.getClass();float animProgress = (float)((e.openMouthTime + (e.openMouthTime - 1) * par6) / 10.0F * 3.141592653589793D);
/* 302:316 */       this.mouthDown.rotateAngleX += MathHelper.sin(animProgress) * 0.6F;
/* 303:    */     }
/* 304:    */   }
/* 305:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelDragonQuadruped
 * JD-Core Version:    0.7.1
 */