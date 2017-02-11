/*   1:    */ package com.chocolate.chocolateQuest.client.model;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import com.chocolate.chocolateQuest.entity.handHelper.HandHelper;
/*   5:    */ import net.minecraft.client.model.ModelBiped;
/*   6:    */ import net.minecraft.client.model.ModelRenderer;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.item.ItemStack;
/*   9:    */ import net.minecraft.util.MathHelper;
/*  10:    */ 
/*  11:    */ public class ModelArmor
/*  12:    */   extends ModelBiped
/*  13:    */ {
/*  14: 21 */   public int type = 0;
/*  15:    */   ItemStack cachedItem;
/*  16:    */   
/*  17:    */   public ModelArmor(int type)
/*  18:    */   {
/*  19: 25 */     this(1.0F, type);
/*  20:    */   }
/*  21:    */   
/*  22:    */   public ModelArmor(float f, int type)
/*  23:    */   {
/*  24: 31 */     super(f);
/*  25: 32 */     this.type = type;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public ModelArmor setItem(ItemStack is)
/*  29:    */   {
/*  30: 36 */     this.cachedItem = is;
/*  31: 37 */     return this;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*  35:    */   {
/*  36: 42 */     this.type = ((int)par2);
/*  37: 43 */     if (this.type == 0)
/*  38:    */     {
/*  39: 45 */       this.bipedHead.render(par7);
/*  40: 46 */       this.bipedHeadwear.render(par7);
/*  41:    */     }
/*  42: 48 */     else if (this.type == 1)
/*  43:    */     {
/*  44: 50 */       this.bipedBody.render(par7);
/*  45: 51 */       this.bipedRightArm.render(par7);
/*  46: 52 */       this.bipedLeftArm.render(par7);
/*  47:    */     }
/*  48: 54 */     else if (this.type == 2)
/*  49:    */     {
/*  50: 56 */       this.bipedBody.render(par7);
/*  51: 57 */       this.bipedRightLeg.render(par7);
/*  52: 58 */       this.bipedLeftLeg.render(par7);
/*  53:    */     }
/*  54: 60 */     else if (this.type == 3)
/*  55:    */     {
/*  56: 62 */       this.bipedRightLeg.render(par7);
/*  57: 63 */       this.bipedLeftLeg.render(par7);
/*  58:    */     }
/*  59: 65 */     renderArmor(par1Entity, 0.0F, par3, par4, par5, par6, par7);
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void renderArmor(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*  63:    */   {
/*  64: 69 */     if (this.type == 0)
/*  65:    */     {
/*  66: 71 */       this.bipedHead.render(par7);
/*  67: 72 */       this.bipedHeadwear.render(par7);
/*  68:    */     }
/*  69: 74 */     else if (this.type == 1)
/*  70:    */     {
/*  71: 76 */       this.bipedBody.render(par7);
/*  72: 77 */       this.bipedRightArm.render(par7);
/*  73: 78 */       this.bipedLeftArm.render(par7);
/*  74:    */     }
/*  75: 80 */     else if (this.type == 2)
/*  76:    */     {
/*  77: 82 */       this.bipedBody.render(par7);
/*  78: 83 */       this.bipedRightLeg.render(par7);
/*  79: 84 */       this.bipedLeftLeg.render(par7);
/*  80:    */     }
/*  81: 86 */     else if (this.type == 3)
/*  82:    */     {
/*  83: 88 */       this.bipedRightLeg.render(par7);
/*  84: 89 */       this.bipedLeftLeg.render(par7);
/*  85:    */     }
/*  86:    */   }
/*  87:    */   
/*  88:    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*  89:    */   {
/*  90: 96 */     model.rotateAngleX = x;
/*  91: 97 */     model.rotateAngleY = y;
/*  92: 98 */     model.rotateAngleZ = z;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
/*  96:    */   {
/*  97:104 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
/*  98:105 */     if ((e instanceof EntityHumanBase))
/*  99:    */     {
/* 100:106 */       setHumanRotationAngles(f, f1, f2, f3, f4, f5, (EntityHumanBase)e);
/* 101:    */     }
/* 102:107 */     else if ((e != null) && 
/* 103:108 */       (e.isSneaking()))
/* 104:    */     {
/* 105:110 */       this.bipedBody.rotateAngleX = 0.5F;
/* 106:111 */       this.bipedRightLeg.rotateAngleX -= 0.0F;
/* 107:112 */       this.bipedLeftLeg.rotateAngleX -= 0.0F;
/* 108:113 */       this.bipedRightArm.rotateAngleX += 0.4F;
/* 109:114 */       this.bipedLeftArm.rotateAngleX += 0.4F;
/* 110:115 */       this.bipedRightLeg.rotationPointZ = 4.0F;
/* 111:116 */       this.bipedLeftLeg.rotationPointZ = 4.0F;
/* 112:117 */       this.bipedRightLeg.rotationPointY = 9.0F;
/* 113:118 */       this.bipedLeftLeg.rotationPointY = 9.0F;
/* 114:119 */       this.bipedHead.rotationPointY = 1.0F;
/* 115:    */     }
/* 116:    */   }
/* 117:    */   
/* 118:    */   public void setHumanRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, EntityHumanBase e)
/* 119:    */   {
/* 120:128 */     if (e.isSitting())
/* 121:    */     {
/* 122:130 */       this.bipedRightArm.rotateAngleX += -0.6283186F;
/* 123:131 */       this.bipedLeftArm.rotateAngleX += -0.6283186F;
/* 124:132 */       this.bipedRightLeg.rotateAngleX = -1.570796F;
/* 125:133 */       this.bipedLeftLeg.rotateAngleX = -1.570796F;
/* 126:134 */       this.bipedRightLeg.rotateAngleY = 0.3141593F;
/* 127:135 */       this.bipedLeftLeg.rotateAngleY = -0.3141593F;
/* 128:    */     }
/* 129:    */     else
/* 130:    */     {
/* 131:139 */       if (e.isSneaking())
/* 132:    */       {
/* 133:141 */         this.bipedBody.rotateAngleX = 0.5F;
/* 134:142 */         this.bipedRightLeg.rotateAngleX -= 0.0F;
/* 135:143 */         this.bipedLeftLeg.rotateAngleX -= 0.0F;
/* 136:144 */         this.bipedRightArm.rotateAngleX += 0.4F;
/* 137:145 */         this.bipedLeftArm.rotateAngleX += 0.4F;
/* 138:146 */         this.bipedRightLeg.rotationPointZ = 4.0F;
/* 139:147 */         this.bipedLeftLeg.rotationPointZ = 4.0F;
/* 140:148 */         this.bipedRightLeg.rotationPointY = 9.0F;
/* 141:149 */         this.bipedLeftLeg.rotationPointY = 9.0F;
/* 142:150 */         this.bipedHead.rotationPointY = 1.0F;
/* 143:    */       }
/* 144:153 */       if (e.isTwoHanded()) {
/* 145:154 */         setTwoHandedAngles(f2);
/* 146:    */       }
/* 147:157 */       if (e.isAiming())
/* 148:    */       {
/* 149:159 */         float rotX = this.bipedRightArm.rotateAngleX;
/* 150:160 */         float rotY = this.bipedRightArm.rotateAngleY;
/* 151:161 */         if (e.rightHand.isTwoHanded())
/* 152:    */         {
/* 153:163 */           if (e.rightHand.isAiming()) {
/* 154:164 */             setAimingAngles(f2);
/* 155:    */           }
/* 156:    */         }
/* 157:168 */         else if (e.rightHand.isAiming()) {
/* 158:169 */           setAimingAnglesRight(f2);
/* 159:    */         }
/* 160:170 */         if (e.leftHand.isAiming()) {
/* 161:171 */           setAimingAnglesLeft(f2);
/* 162:    */         }
/* 163:173 */         if ((e.rightHand.isAiming()) && (this.onGround > 0.0F))
/* 164:    */         {
/* 165:174 */           this.bipedRightArm.rotateAngleY += rotY;
/* 166:175 */           this.bipedRightArm.rotateAngleX += rotX + 0.3F;
/* 167:    */         }
/* 168:    */       }
/* 169:180 */       if (e.leftHandSwing > 0)
/* 170:    */       {
/* 171:182 */         int attackAnim = e.leftHandSwing;
/* 172:183 */         int maxAttackAnimTime = 10;
/* 173:184 */         if (e.haveShied())
/* 174:    */         {
/* 175:186 */           setShiedRotation(f2);
/* 176:187 */           float animProgress = (attackAnim + (attackAnim - 1) * f5) / maxAttackAnimTime * 4.82F;
/* 177:188 */           this.bipedLeftArm.rotateAngleY += MathHelper.cos(animProgress) * 1.8F - 0.8F;
/* 178:189 */           this.bipedLeftArm.rotateAngleX -= MathHelper.cos(animProgress) * 0.6F;
/* 179:    */         }
/* 180:    */         else
/* 181:    */         {
/* 182:193 */           float animProgress = (float)((attackAnim + (attackAnim - 1) * f5) / maxAttackAnimTime * 3.141592653589793D);
/* 183:194 */           this.bipedLeftArm.rotateAngleY += MathHelper.cos(animProgress) * 0.5F;
/* 184:195 */           this.bipedLeftArm.rotateAngleX -= MathHelper.sin(animProgress) * 1.2F;
/* 185:    */         }
/* 186:    */       }
/* 187:200 */       else if (e.isDefending())
/* 188:    */       {
/* 189:202 */         setShiedRotation(f2);
/* 190:    */       }
/* 191:    */     }
/* 192:    */   }
/* 193:    */   
/* 194:    */   public void setTwoHandedAngles(float time)
/* 195:    */   {
/* 196:209 */     float f7 = 0.0F;
/* 197:    */     
/* 198:211 */     float rotationYaw = 0.0F;
/* 199:212 */     float swing = MathHelper.sin(this.onGround * 3.141593F);
/* 200:213 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 201:214 */     this.bipedLeftArm.rotateAngleZ = -0.3F;
/* 202:215 */     this.bipedRightArm.rotateAngleY = (rotationYaw - 0.6F);
/* 203:216 */     this.bipedLeftArm.rotateAngleY = (rotationYaw + 0.6F);
/* 204:217 */     this.bipedRightArm.rotateAngleX = (-0.8F + swing);
/* 205:218 */     this.bipedLeftArm.rotateAngleX = (-0.8F + swing);
/* 206:    */     
/* 207:    */ 
/* 208:221 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(time * 0.09F) * 0.05F + 0.05F;
/* 209:222 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(time * 0.09F) * 0.05F + 0.05F;
/* 210:223 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(time * 0.067F) * 0.05F;
/* 211:224 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(time * 0.067F) * 0.05F;
/* 212:    */     
/* 213:226 */     float f6 = 1.0F - this.onGround;
/* 214:227 */     f6 *= f6;
/* 215:228 */     f6 *= f6;
/* 216:229 */     f6 = 1.0F - f6;
/* 217:230 */     f7 = MathHelper.sin(f6 * 3.141593F);
/* 218:231 */     float f8 = MathHelper.sin(this.onGround * 3.141593F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
/* 219:232 */     this.bipedRightArm.rotateAngleX = ((float)(this.bipedRightArm.rotateAngleX - (f7 * 1.2D + f8)));
/* 220:233 */     this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
/* 221:234 */     this.bipedRightArm.rotateAngleZ = (MathHelper.sin(this.onGround * 3.141593F) * -0.4F);
/* 222:    */     
/* 223:    */ 
/* 224:237 */     this.bipedLeftArm.rotateAngleX = ((float)(this.bipedRightArm.rotateAngleX - (f7 * 1.2D + f8)));
/* 225:238 */     this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
/* 226:239 */     this.bipedLeftArm.rotateAngleZ = (MathHelper.sin(this.onGround * 3.141593F) * -0.4F);
/* 227:    */   }
/* 228:    */   
/* 229:    */   public void setAimingAngles(float time)
/* 230:    */   {
/* 231:244 */     float f7 = 0.0F;
/* 232:245 */     float f9 = 0.0F;
/* 233:246 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 234:247 */     this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 235:248 */     this.bipedRightArm.rotateAngleY = (-(0.1F - f7 * 0.6F) + this.bipedHead.rotateAngleY);
/* 236:249 */     this.bipedLeftArm.rotateAngleY = (0.1F - f7 * 0.6F + this.bipedHead.rotateAngleY + 0.4F);
/* 237:250 */     this.bipedRightArm.rotateAngleX = (-1.570796F + this.bipedHead.rotateAngleX);
/* 238:251 */     this.bipedLeftArm.rotateAngleX = (-1.570796F + this.bipedHead.rotateAngleX);
/* 239:252 */     this.bipedRightArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
/* 240:253 */     this.bipedLeftArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
/* 241:254 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(time * 0.09F) * 0.05F + 0.05F;
/* 242:255 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(time * 0.09F) * 0.05F + 0.05F;
/* 243:256 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(time * 0.067F) * 0.05F;
/* 244:257 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(time * 0.067F) * 0.05F;
/* 245:    */   }
/* 246:    */   
/* 247:    */   public void setAimingAnglesRight(float time)
/* 248:    */   {
/* 249:262 */     float f7 = 0.0F;
/* 250:263 */     float f9 = 0.0F;
/* 251:264 */     this.bipedRightArm.rotateAngleX = (-1.570796F + this.bipedHead.rotateAngleX);
/* 252:265 */     this.bipedRightArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
/* 253:266 */     this.bipedRightArm.rotateAngleX -= MathHelper.sin(time * 0.067F) * 0.05F;
/* 254:267 */     this.bipedRightArm.rotateAngleY = (-0.06F + this.bipedHead.rotateAngleY);
/* 255:268 */     this.bipedRightArm.rotateAngleZ = (MathHelper.cos(time * 0.09F) * 0.05F + 0.05F);
/* 256:    */   }
/* 257:    */   
/* 258:    */   public void setAimingAnglesLeft(float time)
/* 259:    */   {
/* 260:273 */     float f7 = 0.0F;
/* 261:274 */     float f9 = 0.0F;
/* 262:275 */     this.bipedLeftArm.rotateAngleX = (-1.570796F + this.bipedHead.rotateAngleX);
/* 263:276 */     this.bipedLeftArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
/* 264:277 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(time * 0.067F) * 0.05F;
/* 265:278 */     this.bipedLeftArm.rotateAngleY = (0.1F - f7 * 0.6F + this.bipedHead.rotateAngleY);
/* 266:279 */     this.bipedLeftArm.rotateAngleZ = (-MathHelper.cos(time * 0.09F) * 0.05F + 0.05F);
/* 267:    */   }
/* 268:    */   
/* 269:    */   public void setShiedRotation(float time)
/* 270:    */   {
/* 271:284 */     float f7 = 0.0F;
/* 272:285 */     float f9 = 0.0F;
/* 273:286 */     this.bipedLeftArm.rotateAngleZ = -0.7F;
/* 274:287 */     this.bipedLeftArm.rotateAngleY = 1.2F;
/* 275:288 */     this.bipedLeftArm.rotateAngleX = (-1.570796F + this.bipedHead.rotateAngleX);
/* 276:289 */     this.bipedLeftArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
/* 277:290 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(time * 0.09F) * 0.05F + 0.05F;
/* 278:291 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(time * 0.067F) * 0.05F;
/* 279:    */   }
/* 280:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelArmor
 * JD-Core Version:    0.7.1
 */