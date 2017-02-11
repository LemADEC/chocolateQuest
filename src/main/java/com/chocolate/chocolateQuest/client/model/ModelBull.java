/*   1:    */ package com.chocolate.chocolateQuest.client.model;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.boss.AttackKick;
/*   4:    */ import com.chocolate.chocolateQuest.entity.boss.AttackKickQuadruped;
/*   5:    */ import com.chocolate.chocolateQuest.entity.boss.EntityBull;
/*   6:    */ import net.minecraft.client.model.ModelQuadruped;
/*   7:    */ import net.minecraft.client.model.ModelRenderer;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.util.MathHelper;
/*  10:    */ 
/*  11:    */ public class ModelBull
/*  12:    */   extends ModelQuadruped
/*  13:    */ {
/*  14:    */   ModelRenderer horn22;
/*  15:    */   ModelRenderer horn21;
/*  16:    */   ModelRenderer horn2;
/*  17:    */   ModelRenderer horn12;
/*  18:    */   ModelRenderer horn1;
/*  19:    */   ModelRenderer horn11;
/*  20:    */   ModelRenderer sideLeft;
/*  21:    */   ModelRenderer sideRight;
/*  22:    */   ModelRenderer sideLeftBleeding;
/*  23:    */   ModelRenderer sideRightBleeding;
/*  24:    */   ModelRenderer mouth;
/*  25:    */   ModelRenderer tail;
/*  26:    */   ModelRenderer tailEnd;
/*  27:    */   ModelRenderer tailEndM;
/*  28:    */   
/*  29:    */   public ModelBull()
/*  30:    */   {
/*  31: 35 */     super(12, 0.0F);
/*  32:    */     
/*  33: 37 */     this.textureWidth = 64;
/*  34: 38 */     this.textureHeight = 64;
/*  35: 39 */     float ho = 3.0F;
/*  36: 40 */     this.head = new ModelRenderer(this, 0, 0);
/*  37: 41 */     this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 6);
/*  38: 42 */     this.head.setRotationPoint(0.0F, 6.0F, -6.0F);
/*  39:    */     
/*  40: 44 */     this.body = new ModelRenderer(this, 0, 36);
/*  41: 45 */     this.body.addBox(-6.0F, -10.0F, -18.0F, 12, 10, 18);
/*  42: 46 */     this.body.setRotationPoint(0.0F, 12.0F, 9.0F);
/*  43: 47 */     this.body.setTextureSize(this.textureWidth, this.textureHeight);
/*  44:    */     
/*  45: 49 */     this.leg1 = new ModelRenderer(this, 48, 0);
/*  46: 50 */     this.leg1.addBox(-3.0F, 0.0F + ho, -2.0F, 4, 12, 4);
/*  47: 51 */     this.leg1.setRotationPoint(-3.0F, 8.0F, 7.0F);
/*  48: 52 */     this.leg1.setTextureSize(this.textureWidth, this.textureHeight);
/*  49: 53 */     this.leg1.mirror = true;
/*  50: 54 */     this.leg2 = new ModelRenderer(this, 48, 0);
/*  51: 55 */     this.leg2.addBox(-1.0F, 0.0F + ho, -2.0F, 4, 12, 4);
/*  52: 56 */     this.leg2.setRotationPoint(3.0F, 8.0F, 7.0F);
/*  53: 57 */     this.leg2.setTextureSize(this.textureWidth, this.textureHeight);
/*  54: 58 */     this.leg2.mirror = true;
/*  55: 59 */     this.leg3 = new ModelRenderer(this, 48, 0);
/*  56: 60 */     this.leg3.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
/*  57: 61 */     this.leg3.setRotationPoint(-5.0F, 1.0F, -16.0F);
/*  58: 62 */     this.leg3.setTextureSize(this.textureWidth, this.textureHeight);
/*  59: 63 */     this.leg3.mirror = true;
/*  60: 64 */     this.leg4 = new ModelRenderer(this, 48, 0);
/*  61: 65 */     this.leg4.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
/*  62: 66 */     this.leg4.setRotationPoint(3.0F, 1.0F, -16.0F);
/*  63: 67 */     this.leg4.setTextureSize(this.textureWidth, this.textureHeight);
/*  64: 68 */     this.leg4.mirror = true;
/*  65: 69 */     this.body.addChild(this.leg3);
/*  66: 70 */     this.body.addChild(this.leg4);
/*  67:    */     
/*  68: 72 */     this.horn1 = new ModelRenderer(this, 22, 0);
/*  69: 73 */     this.horn1.addBox(-4.0F, -3.0F, -5.0F, 2, 2, 2);
/*  70: 74 */     this.horn1.setRotationPoint(0.0F, -1.0F, -3.0F);
/*  71: 75 */     this.horn1.setTextureSize(this.textureWidth, this.textureHeight);
/*  72: 76 */     this.horn1.mirror = true;
/*  73: 77 */     setRotation(this.horn1, -0.0569039F, 0.0F, 0.0F);
/*  74: 78 */     this.horn11 = new ModelRenderer(this, 22, 0);
/*  75: 79 */     this.horn11.addBox(-5.0F, -4.0F, -6.0F, 2, 2, 2);
/*  76: 80 */     this.horn11.setRotationPoint(0.0F, -1.0F, -3.0F);
/*  77: 81 */     this.horn11.setTextureSize(this.textureWidth, this.textureHeight);
/*  78: 82 */     this.horn11.mirror = true;
/*  79: 83 */     setRotation(this.horn11, -0.0569039F, 0.0F, 0.0F);
/*  80: 84 */     this.horn12 = new ModelRenderer(this, 22, 0);
/*  81: 85 */     this.horn12.addBox(-5.0F, -4.0F, -9.0F, 1, 1, 3);
/*  82: 86 */     this.horn12.setRotationPoint(0.0F, -1.0F, -3.0F);
/*  83: 87 */     this.horn12.setTextureSize(this.textureWidth, this.textureHeight);
/*  84: 88 */     this.horn12.mirror = true;
/*  85: 89 */     setRotation(this.horn12, -0.0569039F, 0.0F, 0.0F);
/*  86: 90 */     this.horn22 = new ModelRenderer(this, 22, 0);
/*  87: 91 */     this.horn22.addBox(4.0F, -4.0F, -9.0F, 1, 1, 3);
/*  88: 92 */     this.horn22.setRotationPoint(0.0F, -1.0F, -3.0F);
/*  89: 93 */     this.horn22.setTextureSize(this.textureWidth, this.textureHeight);
/*  90: 94 */     this.horn22.mirror = true;
/*  91: 95 */     setRotation(this.horn22, -0.0569039F, 0.0F, 0.0F);
/*  92: 96 */     this.horn21 = new ModelRenderer(this, 22, 0);
/*  93: 97 */     this.horn21.addBox(3.0F, -4.0F, -6.0F, 2, 2, 2);
/*  94: 98 */     this.horn21.setRotationPoint(0.0F, -1.0F, -3.0F);
/*  95: 99 */     this.horn21.setTextureSize(this.textureWidth, this.textureHeight);
/*  96:100 */     this.horn21.mirror = true;
/*  97:101 */     setRotation(this.horn21, -0.0569039F, 0.0F, 0.0F);
/*  98:102 */     this.horn2 = new ModelRenderer(this, 22, 0);
/*  99:103 */     this.horn2.addBox(2.0F, -3.0F, -5.0F, 2, 2, 2);
/* 100:104 */     this.horn2.setRotationPoint(0.0F, -1.0F, -3.0F);
/* 101:105 */     this.horn2.setTextureSize(this.textureWidth, this.textureHeight);
/* 102:106 */     this.horn2.mirror = true;
/* 103:107 */     setRotation(this.horn2, -0.0569039F, 0.0F, 0.0F);
/* 104:108 */     this.head.addChild(this.horn1);
/* 105:109 */     this.head.addChild(this.horn11);
/* 106:110 */     this.head.addChild(this.horn12);
/* 107:111 */     this.head.addChild(this.horn2);
/* 108:112 */     this.head.addChild(this.horn21);
/* 109:113 */     this.head.addChild(this.horn22);
/* 110:114 */     this.head.setRotationPoint(0.0F, -6.0F, -16.0F);
/* 111:115 */     this.body.addChild(this.head);
/* 112:    */     
/* 113:117 */     this.sideLeft = new ModelRenderer(this, 30, 0);
/* 114:118 */     this.sideLeft.addBox(0.0F, 0.0F, 0.0F, 1, 9, 16);
/* 115:119 */     this.sideLeft.setRotationPoint(6.0F, -9.0F, -17.0F);
/* 116:120 */     this.sideRight = new ModelRenderer(this, 30, 0);
/* 117:121 */     this.sideRight.addBox(0.0F, 0.0F, 0.0F, 1, 9, 16);
/* 118:122 */     this.sideRight.setRotationPoint(-7.0F, -9.0F, -17.0F);
/* 119:123 */     this.body.addChild(this.sideLeft);
/* 120:124 */     this.body.addChild(this.sideRight);
/* 121:125 */     this.sideLeftBleeding = new ModelRenderer(this, 12, 11);
/* 122:126 */     this.sideLeftBleeding.addBox(0.0F, 0.0F, 0.0F, 1, 9, 16);
/* 123:127 */     this.sideLeftBleeding.setRotationPoint(6.0F, -9.0F, -17.0F);
/* 124:128 */     this.sideRightBleeding = new ModelRenderer(this, 12, 11);
/* 125:129 */     this.sideRightBleeding.addBox(0.0F, 0.0F, 0.0F, 1, 9, 16);
/* 126:130 */     this.sideRightBleeding.setRotationPoint(-7.0F, -9.0F, -17.0F);
/* 127:131 */     this.body.addChild(this.sideLeftBleeding);
/* 128:132 */     this.body.addChild(this.sideRightBleeding);
/* 129:    */     
/* 130:134 */     this.mouth = new ModelRenderer(this, 30, 0);
/* 131:135 */     this.mouth.addBox(-2.0F, 1.0F, -9.0F, 4, 3, 2);
/* 132:136 */     this.head.addChild(this.mouth);
/* 133:    */     
/* 134:138 */     this.tail = new ModelRenderer(this, 0, 14);
/* 135:139 */     this.tail.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 6);
/* 136:140 */     this.tail.setRotationPoint(0.0F, -9.0F, 0.0F);
/* 137:141 */     this.body.addChild(this.tail);
/* 138:    */     
/* 139:143 */     this.tailEnd = new ModelRenderer(this, 46, 24);
/* 140:144 */     this.tailEnd.addBox(0.0F, -1.5F, 0.0F, 0, 4, 8);
/* 141:145 */     this.tailEnd.setRotationPoint(0.0F, 0.0F, 6.0F);
/* 142:146 */     this.tailEndM = new ModelRenderer(this, 46, 24);
/* 143:147 */     this.tailEndM.addBox(0.5F, -2.0F, 0.0F, 0, 4, 8);
/* 144:148 */     this.tailEndM.setRotationPoint(0.0F, 0.0F, 6.0F);
/* 145:149 */     this.tailEndM.rotateAngleZ = 1.5708F;
/* 146:150 */     this.tail.addChild(this.tailEnd);
/* 147:151 */     this.tail.addChild(this.tailEndM);
/* 148:    */   }
/* 149:    */   
/* 150:    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 151:    */   {
/* 152:156 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 153:157 */     this.body.render(f5);
/* 154:158 */     this.leg1.render(f5);
/* 155:159 */     this.leg2.render(f5);
/* 156:    */   }
/* 157:    */   
/* 158:    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 159:    */   {
/* 160:165 */     model.rotateAngleX = x;
/* 161:166 */     model.rotateAngleY = y;
/* 162:167 */     model.rotateAngleZ = z;
/* 163:    */   }
/* 164:    */   
/* 165:    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 166:    */   {
/* 167:172 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 168:173 */     this.body.rotateAngleX = 0.0F;
/* 169:174 */     this.leg1.rotateAngleY = (this.leg2.rotateAngleY = this.leg3.rotateAngleY = this.leg4.rotateAngleY = 0.0F);
/* 170:175 */     this.tail.rotateAngleX = -1.4F;
/* 171:176 */     EntityBull e = (EntityBull)entity;
/* 172:177 */     if (e.charge)
/* 173:    */     {
/* 174:179 */       ModelRenderer tmp83_80 = this.head;tmp83_80.rotateAngleX = ((float)(tmp83_80.rotateAngleX + (0.3D - (float)Math.cos(e.chargeTime / e.chargeTimeMax * 24.0F) * 0.4F)));
/* 175:180 */       this.tail.rotateAngleX += e.chargeTime / e.chargeTimeMax * 1.4F;
/* 176:    */     }
/* 177:    */     else
/* 178:    */     {
/* 179:182 */       this.head.offsetY = 0.0F;
/* 180:    */     }
/* 181:183 */     AttackKickQuadruped kick = e.kickHelper;
/* 182:184 */     if ((kick.kickTime > 0) || (kick.kickTimeBack > 0))
/* 183:    */     {
/* 184:186 */       this.leg4.rotateAngleX = (this.leg3.rotateAngleX = this.leg2.rotateAngleX = this.leg1.rotateAngleX = 0.0F);
/* 185:187 */       int maxAttackAnimTime = kick.kickSpeed;
/* 186:188 */       int attackAnim = maxAttackAnimTime - kick.kickTime - maxAttackAnimTime / 10;
/* 187:189 */       float animProgress = (attackAnim + 1 + attackAnim * f5) / maxAttackAnimTime * 6.283184F;
/* 188:190 */       if (kick.kickType == 1)
/* 189:    */       {
/* 190:191 */         this.leg4.rotateAngleX = (MathHelper.sin(animProgress) * 1.4F - 0.2F);
/* 191:192 */         this.leg4.rotateAngleY = (MathHelper.cos(animProgress) * 0.4F);
/* 192:    */       }
/* 193:193 */       else if (kick.kickType == 3)
/* 194:    */       {
/* 195:194 */         this.leg3.rotateAngleX = (MathHelper.sin(animProgress) * 1.4F - 0.2F);
/* 196:195 */         this.leg3.rotateAngleY = (-MathHelper.cos(animProgress) * 0.4F);
/* 197:    */       }
/* 198:198 */       attackAnim = maxAttackAnimTime - kick.kickTimeBack - maxAttackAnimTime / 10;
/* 199:199 */       animProgress = (attackAnim + 1 + attackAnim * f5) / maxAttackAnimTime * 6.283184F;
/* 200:200 */       if (kick.kickTypeBack == 2)
/* 201:    */       {
/* 202:201 */         this.leg2.rotateAngleX = (-MathHelper.sin(animProgress) * 1.4F + 0.2F);
/* 203:202 */         this.leg2.rotateAngleY = (-MathHelper.cos(animProgress) * 0.4F);
/* 204:    */       }
/* 205:203 */       else if (kick.kickTypeBack == 4)
/* 206:    */       {
/* 207:204 */         this.leg1.rotateAngleX = (-MathHelper.sin(animProgress) * 1.4F + 0.2F);
/* 208:205 */         this.leg1.rotateAngleY = (MathHelper.cos(animProgress) * 0.4F);
/* 209:    */       }
/* 210:    */     }
/* 211:208 */     if (e.smashTime > 0)
/* 212:    */     {
/* 213:209 */       e.getClass();int maxAttackAnimTime = 30;
/* 214:210 */       int attackAnim = maxAttackAnimTime - e.smashTime - maxAttackAnimTime / 10;
/* 215:211 */       float animProgress = (attackAnim + 1 + attackAnim * f5) / maxAttackAnimTime * 6.283184F;
/* 216:212 */       float rot = -MathHelper.sin(animProgress / 2.0F) * 0.5F;
/* 217:213 */       this.body.rotateAngleX = rot;
/* 218:214 */       this.leg4.rotateAngleX = rot;
/* 219:215 */       this.leg3.rotateAngleX = rot;
/* 220:    */     }
/* 221:217 */     this.sideLeft.isHidden = e.isHurtLeft();
/* 222:218 */     this.sideLeftBleeding.isHidden = (!e.isHurtLeft());
/* 223:219 */     this.sideRight.isHidden = e.isHurtRight();
/* 224:220 */     this.sideRightBleeding.isHidden = (!e.isHurtRight());
/* 225:    */   }
/* 226:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelBull
 * JD-Core Version:    0.7.1
 */