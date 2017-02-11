/*   1:    */ package com.chocolate.chocolateQuest.client.model;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import com.chocolate.chocolateQuest.entity.handHelper.HandHelper;
/*   5:    */ import net.minecraft.client.model.ModelBiped;
/*   6:    */ import net.minecraft.client.model.ModelRenderer;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ 
/*  10:    */ public class ModelHuman
/*  11:    */   extends ModelBiped
/*  12:    */ {
/*  13:    */   public ModelHuman()
/*  14:    */   {
/*  15: 22 */     this(0.0F);
/*  16:    */   }
/*  17:    */   
/*  18:    */   public ModelHuman(float f)
/*  19:    */   {
/*  20: 27 */     this(f, 0.0F);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public ModelHuman(float f, float f1)
/*  24:    */   {
/*  25: 32 */     this.heldItemLeft = 0;
/*  26: 33 */     this.heldItemRight = 0;
/*  27: 34 */     this.aimedBow = false;
/*  28: 35 */     this.bipedCloak = new ModelRenderer(this, 0, 0);
/*  29: 36 */     this.bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, f);
/*  30: 37 */     this.bipedEars = new ModelRenderer(this, 25, 0);
/*  31: 38 */     this.bipedEars.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/*  32: 39 */     this.bipedEars.addBox(-7.0F, -7.0F, -1.0F, 3, 4, 0, f);
/*  33: 40 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  34: 41 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f);
/*  35: 42 */     this.bipedHead.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/*  36: 43 */     this.bipedHeadwear = new ModelRenderer(this, 32, 0);
/*  37: 44 */     this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f + 0.5F);
/*  38: 45 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/*  39: 46 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/*  40: 47 */     this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, f);
/*  41: 48 */     this.bipedBody.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/*  42: 49 */     this.bipedRightArm = new ModelRenderer(this, 40, 16);
/*  43: 50 */     this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, f);
/*  44: 51 */     this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + f1, 0.0F);
/*  45: 52 */     this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/*  46: 53 */     this.bipedLeftArm.mirror = true;
/*  47: 54 */     this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, f);
/*  48: 55 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + f1, 0.0F);
/*  49: 56 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/*  50: 57 */     this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
/*  51: 58 */     this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F + f1, 0.0F);
/*  52: 59 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/*  53: 60 */     this.bipedLeftLeg.mirror = true;
/*  54: 61 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
/*  55: 62 */     this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F + f1, 0.0F);
/*  56:    */     
/*  57: 64 */     this.bipedEars = new ModelRenderer(this, 25, 1);
/*  58: 65 */     this.bipedEars.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  59: 66 */     this.bipedEars.addBox(-7.0F, -7.0F, -1.0F, 3, 4, 0, 0.0F);
/*  60:    */   }
/*  61:    */   
/*  62:    */   public ModelHuman(float par1, float par2, int par3, int par4)
/*  63:    */   {
/*  64: 71 */     super(par1, par2, par3, par4);
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*  68:    */   {
/*  69: 77 */     if (entity == null) {
/*  70: 78 */       return;
/*  71:    */     }
/*  72: 79 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/*  73:    */     
/*  74: 81 */     EntityHumanBase human = (EntityHumanBase)entity;
/*  75: 82 */     setHumanRotationAngles(f, f1, f2, f3, f4, f5, human);
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void setHumanRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, EntityHumanBase e)
/*  79:    */   {
/*  80: 87 */     if (e.isSitting())
/*  81:    */     {
/*  82: 89 */       this.bipedRightArm.rotateAngleX += -0.6283186F;
/*  83: 90 */       this.bipedLeftArm.rotateAngleX += -0.6283186F;
/*  84: 91 */       this.bipedRightLeg.rotateAngleX = -1.570796F;
/*  85: 92 */       this.bipedLeftLeg.rotateAngleX = -1.570796F;
/*  86: 93 */       this.bipedRightLeg.rotateAngleY = 0.3141593F;
/*  87: 94 */       this.bipedLeftLeg.rotateAngleY = -0.3141593F;
/*  88:    */     }
/*  89:    */     else
/*  90:    */     {
/*  91: 98 */       if (e.isSneaking())
/*  92:    */       {
/*  93:100 */         this.bipedBody.rotateAngleX = 0.5F;
/*  94:101 */         this.bipedRightLeg.rotateAngleX -= 0.0F;
/*  95:102 */         this.bipedLeftLeg.rotateAngleX -= 0.0F;
/*  96:103 */         this.bipedRightArm.rotateAngleX += 0.4F;
/*  97:104 */         this.bipedLeftArm.rotateAngleX += 0.4F;
/*  98:105 */         this.bipedRightLeg.rotationPointZ = 4.0F;
/*  99:106 */         this.bipedLeftLeg.rotationPointZ = 4.0F;
/* 100:107 */         this.bipedRightLeg.rotationPointY = 9.0F;
/* 101:108 */         this.bipedLeftLeg.rotationPointY = 9.0F;
/* 102:109 */         this.bipedHead.rotationPointY = 1.0F;
/* 103:    */       }
/* 104:112 */       if (e.isTwoHanded()) {
/* 105:113 */         setTwoHandedAngles(f2);
/* 106:    */       }
/* 107:116 */       if (e.isAiming())
/* 108:    */       {
/* 109:118 */         float rotX = this.bipedRightArm.rotateAngleX;
/* 110:119 */         float rotY = this.bipedRightArm.rotateAngleY;
/* 111:120 */         if (e.rightHand.isTwoHanded())
/* 112:    */         {
/* 113:122 */           if (e.rightHand.isAiming()) {
/* 114:123 */             setAimingAngles(f2);
/* 115:    */           }
/* 116:    */         }
/* 117:    */         else
/* 118:    */         {
/* 119:127 */           if (e.rightHand.isAiming()) {
/* 120:128 */             setAimingAnglesRight(f2);
/* 121:    */           }
/* 122:129 */           if (e.leftHand.isAiming()) {
/* 123:130 */             setAimingAnglesLeft(f2);
/* 124:131 */           } else if (!e.rightHand.isAiming()) {
/* 125:132 */             setAimingAngles(f2);
/* 126:    */           }
/* 127:    */         }
/* 128:135 */         if ((e.rightHand.isAiming()) && (this.onGround > 0.0F))
/* 129:    */         {
/* 130:136 */           this.bipedRightArm.rotateAngleY += rotY;
/* 131:137 */           this.bipedRightArm.rotateAngleX += rotX + 0.3F;
/* 132:    */         }
/* 133:    */       }
/* 134:142 */       if (e.leftHandSwing > 0)
/* 135:    */       {
/* 136:144 */         int attackAnim = e.leftHandSwing;
/* 137:145 */         int maxAttackAnimTime = 10;
/* 138:146 */         if (e.haveShied())
/* 139:    */         {
/* 140:148 */           setShiedRotation(f2);
/* 141:149 */           float animProgress = (attackAnim + (attackAnim - 1) * f5) / maxAttackAnimTime * 4.82F;
/* 142:150 */           this.bipedLeftArm.rotateAngleY += MathHelper.cos(animProgress) * 1.8F - 0.8F;
/* 143:151 */           this.bipedLeftArm.rotateAngleX -= MathHelper.cos(animProgress) * 0.6F;
/* 144:    */         }
/* 145:    */         else
/* 146:    */         {
/* 147:155 */           float animProgress = (float)((attackAnim + (attackAnim - 1) * f5) / maxAttackAnimTime * 3.141592653589793D);
/* 148:156 */           this.bipedLeftArm.rotateAngleY += MathHelper.cos(animProgress) * 0.5F;
/* 149:157 */           this.bipedLeftArm.rotateAngleX -= MathHelper.sin(animProgress) * 1.2F;
/* 150:    */         }
/* 151:    */       }
/* 152:162 */       else if (e.isDefending())
/* 153:    */       {
/* 154:164 */         setShiedRotation(f2);
/* 155:    */       }
/* 156:    */     }
/* 157:    */   }
/* 158:    */   
/* 159:    */   public void render(Entity z, float f, float f1, float f2, float f3, float f4, float f5)
/* 160:    */   {
/* 161:173 */     setRotationAngles(f, f1, f2, f3, f4, f5, z);
/* 162:174 */     this.bipedHead.render(f5);
/* 163:175 */     this.bipedBody.render(f5);
/* 164:176 */     this.bipedRightArm.render(f5);
/* 165:177 */     this.bipedLeftArm.render(f5);
/* 166:178 */     this.bipedRightLeg.render(f5);
/* 167:179 */     this.bipedLeftLeg.render(f5);
/* 168:180 */     this.bipedHeadwear.render(f5);
/* 169:    */   }
/* 170:    */   
/* 171:    */   public void renderEars(float f)
/* 172:    */   {
/* 173:187 */     this.bipedEars.rotationPointX = 0.0F;
/* 174:188 */     this.bipedEars.rotationPointZ = (this.bipedHead.rotationPointZ + 1.0F);
/* 175:189 */     this.bipedEars.rotateAngleY = this.bipedHead.rotateAngleY;
/* 176:190 */     this.bipedEars.rotateAngleX = this.bipedHead.rotateAngleX;
/* 177:191 */     this.bipedEars.render(f);
/* 178:    */     
/* 179:193 */     this.bipedEars.rotateAngleX = (-this.bipedHead.rotateAngleX);
/* 180:194 */     this.bipedEars.rotateAngleY = ((float)(this.bipedHead.rotateAngleY - 3.141592653589793D));
/* 181:195 */     this.bipedEars.rotationPointY = this.bipedHead.rotationPointY;
/* 182:196 */     this.bipedEars.rotationPointZ = (this.bipedHead.rotationPointZ - 1.0F);
/* 183:197 */     this.bipedEars.render(f);
/* 184:    */   }
/* 185:    */   
/* 186:    */   public void renderCloak(float f)
/* 187:    */   {
/* 188:203 */     this.bipedCloak.render(f);
/* 189:    */   }
/* 190:    */   
/* 191:    */   public void setTwoHandedAngles(float time)
/* 192:    */   {
/* 193:208 */     float f7 = 0.0F;
/* 194:    */     
/* 195:210 */     float rotationYaw = 0.0F;
/* 196:211 */     float swing = MathHelper.sin(this.onGround * 3.141593F);
/* 197:212 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 198:213 */     this.bipedLeftArm.rotateAngleZ = -0.3F;
/* 199:214 */     this.bipedRightArm.rotateAngleY = (rotationYaw - 0.6F);
/* 200:215 */     this.bipedLeftArm.rotateAngleY = (rotationYaw + 0.6F);
/* 201:216 */     this.bipedRightArm.rotateAngleX = (-0.8F + swing);
/* 202:217 */     this.bipedLeftArm.rotateAngleX = (-0.8F + swing);
/* 203:    */     
/* 204:    */ 
/* 205:220 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(time * 0.09F) * 0.05F + 0.05F;
/* 206:221 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(time * 0.09F) * 0.05F + 0.05F;
/* 207:222 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(time * 0.067F) * 0.05F;
/* 208:223 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(time * 0.067F) * 0.05F;
/* 209:    */     
/* 210:225 */     float f6 = 1.0F - this.onGround;
/* 211:226 */     f6 *= f6;
/* 212:227 */     f6 *= f6;
/* 213:228 */     f6 = 1.0F - f6;
/* 214:229 */     f7 = MathHelper.sin(f6 * 3.141593F);
/* 215:230 */     float f8 = MathHelper.sin(this.onGround * 3.141593F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
/* 216:231 */     this.bipedRightArm.rotateAngleX = ((float)(this.bipedRightArm.rotateAngleX - (f7 * 1.2D + f8)));
/* 217:232 */     this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
/* 218:233 */     this.bipedRightArm.rotateAngleZ = (MathHelper.sin(this.onGround * 3.141593F) * -0.4F);
/* 219:    */     
/* 220:    */ 
/* 221:236 */     this.bipedLeftArm.rotateAngleX = ((float)(this.bipedRightArm.rotateAngleX - (f7 * 1.2D + f8)));
/* 222:237 */     this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
/* 223:238 */     this.bipedLeftArm.rotateAngleZ = (MathHelper.sin(this.onGround * 3.141593F) * -0.4F);
/* 224:    */   }
/* 225:    */   
/* 226:    */   public void setAimingAngles(float time)
/* 227:    */   {
/* 228:243 */     float f7 = 0.0F;
/* 229:244 */     float f9 = 0.0F;
/* 230:245 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 231:246 */     this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 232:247 */     this.bipedRightArm.rotateAngleY = (-(0.1F - f7 * 0.6F) + this.bipedHead.rotateAngleY);
/* 233:248 */     this.bipedLeftArm.rotateAngleY = (0.1F - f7 * 0.6F + this.bipedHead.rotateAngleY + 0.4F);
/* 234:249 */     this.bipedRightArm.rotateAngleX = (-1.570796F + this.bipedHead.rotateAngleX);
/* 235:250 */     this.bipedLeftArm.rotateAngleX = (-1.570796F + this.bipedHead.rotateAngleX);
/* 236:251 */     this.bipedRightArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
/* 237:252 */     this.bipedLeftArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
/* 238:253 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(time * 0.09F) * 0.05F + 0.05F;
/* 239:254 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(time * 0.09F) * 0.05F + 0.05F;
/* 240:255 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(time * 0.067F) * 0.05F;
/* 241:256 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(time * 0.067F) * 0.05F;
/* 242:    */   }
/* 243:    */   
/* 244:    */   public void setAimingAnglesRight(float time)
/* 245:    */   {
/* 246:261 */     float f7 = 0.0F;
/* 247:262 */     float f9 = 0.0F;
/* 248:263 */     this.bipedRightArm.rotateAngleX = (-1.570796F + this.bipedHead.rotateAngleX);
/* 249:264 */     this.bipedRightArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
/* 250:265 */     this.bipedRightArm.rotateAngleX -= MathHelper.sin(time * 0.067F) * 0.05F;
/* 251:266 */     this.bipedRightArm.rotateAngleY = (-0.06F + this.bipedHead.rotateAngleY);
/* 252:267 */     this.bipedRightArm.rotateAngleZ = (MathHelper.cos(time * 0.09F) * 0.05F + 0.05F);
/* 253:    */   }
/* 254:    */   
/* 255:    */   public void setAimingAnglesLeft(float time)
/* 256:    */   {
/* 257:272 */     float f7 = 0.0F;
/* 258:273 */     float f9 = 0.0F;
/* 259:274 */     this.bipedLeftArm.rotateAngleX = (-1.570796F + this.bipedHead.rotateAngleX);
/* 260:275 */     this.bipedLeftArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
/* 261:276 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(time * 0.067F) * 0.05F;
/* 262:277 */     this.bipedLeftArm.rotateAngleY = (0.1F - f7 * 0.6F + this.bipedHead.rotateAngleY);
/* 263:278 */     this.bipedLeftArm.rotateAngleZ = (-MathHelper.cos(time * 0.09F) * 0.05F + 0.05F);
/* 264:    */   }
/* 265:    */   
/* 266:    */   public void setShiedRotation(float time)
/* 267:    */   {
/* 268:283 */     float f7 = 0.0F;
/* 269:284 */     float f9 = 0.0F;
/* 270:285 */     this.bipedLeftArm.rotateAngleZ = -0.7F;
/* 271:286 */     this.bipedLeftArm.rotateAngleY = 1.2F;
/* 272:287 */     this.bipedLeftArm.rotateAngleX = (-1.570796F + this.bipedHead.rotateAngleX);
/* 273:288 */     this.bipedLeftArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
/* 274:289 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(time * 0.09F) * 0.05F + 0.05F;
/* 275:290 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(time * 0.067F) * 0.05F;
/* 276:    */   }
/* 277:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelHuman
 * JD-Core Version:    0.7.1
 */