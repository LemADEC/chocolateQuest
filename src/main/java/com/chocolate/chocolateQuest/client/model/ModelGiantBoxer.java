/*   1:    */ package com.chocolate.chocolateQuest.client.model;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.boss.AttackKick;
/*   4:    */ import com.chocolate.chocolateQuest.entity.boss.AttackPunch;
/*   5:    */ import com.chocolate.chocolateQuest.entity.boss.EntityGiantBoxer;
/*   6:    */ import net.minecraft.client.model.ModelBiped;
/*   7:    */ import net.minecraft.client.model.ModelRenderer;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.entity.EntityLivingBase;
/*  10:    */ import net.minecraft.util.MathHelper;
/*  11:    */ 
/*  12:    */ public class ModelGiantBoxer
/*  13:    */   extends ModelBiped
/*  14:    */ {
/*  15:    */   ModelRenderer rightarm1;
/*  16:    */   ModelRenderer leftarm1;
/*  17:    */   ModelRenderer earR;
/*  18:    */   ModelRenderer earL;
/*  19:    */   ModelRenderer mouth;
/*  20:    */   ModelRenderer tail;
/*  21:    */   boolean attacking;
/*  22:    */   int attackAnim;
/*  23:    */   int maxAttackAnimTime;
/*  24:    */   int attackType;
/*  25:    */   
/*  26:    */   public ModelGiantBoxer()
/*  27:    */   {
/*  28: 34 */     this(0.0F);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public ModelGiantBoxer(float s)
/*  32:    */   {
/*  33: 38 */     this.textureWidth = 64;
/*  34: 39 */     this.textureHeight = 32;
/*  35: 40 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  36: 41 */     this.bipedHead.addBox(-4.0F, -5.0F, -6.0F, 8, 8, 8, s);
/*  37: 42 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  38: 43 */     this.bipedHead.setTextureSize(64, 32);
/*  39: 44 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/*  40: 45 */     this.bipedBody.addBox(-4.0F, 3.0F, -5.0F, 8, 12, 4, s);
/*  41: 46 */     this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  42: 47 */     this.bipedBody.setTextureSize(64, 32);
/*  43: 48 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/*  44: 49 */     this.bipedRightLeg.addBox(-2.0F, 0.0F, -3.0F, 4, 12, 4, s);
/*  45: 50 */     this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
/*  46: 51 */     this.bipedRightLeg.setTextureSize(64, 32);
/*  47: 52 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/*  48: 53 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -3.0F, 4, 12, 4, s);
/*  49: 54 */     this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
/*  50: 55 */     this.bipedLeftLeg.setTextureSize(64, 32);
/*  51: 56 */     this.bipedLeftLeg.mirror = true;
/*  52:    */     
/*  53: 58 */     this.bipedRightArm = new ModelRenderer(this, 40, 16);
/*  54: 59 */     this.bipedRightArm.addBox(-3.0F, -1.0F, -4.0F, 4, 12, 4, s);
/*  55: 60 */     this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/*  56: 61 */     this.bipedRightArm.setTextureSize(64, 32);
/*  57: 62 */     this.rightarm1 = new ModelRenderer(this, 40, 16);
/*  58: 63 */     this.rightarm1.addBox(-2.0F, 1.0F, -4.0F, 4, 10, 4, s);
/*  59: 64 */     this.rightarm1.setRotationPoint(-1.0F, 10.0F, 0.0F);
/*  60: 65 */     this.rightarm1.setTextureSize(64, 32);
/*  61: 66 */     this.bipedRightArm.addChild(this.rightarm1);
/*  62:    */     
/*  63: 68 */     this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/*  64: 69 */     this.bipedLeftArm.addBox(-1.0F, -1.0F, -4.0F, 4, 12, 4, s);
/*  65: 70 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/*  66: 71 */     this.bipedLeftArm.setTextureSize(64, 32);
/*  67: 72 */     this.bipedLeftArm.mirror = true;
/*  68: 73 */     this.leftarm1 = new ModelRenderer(this, 40, 16);
/*  69: 74 */     this.leftarm1.addBox(-2.0F, 1.0F, -4.0F, 4, 10, 4);
/*  70: 75 */     this.leftarm1.setRotationPoint(1.0F, 10.0F, 0.0F);
/*  71: 76 */     this.leftarm1.setTextureSize(64, 32);
/*  72: 77 */     this.leftarm1.mirror = true;
/*  73: 78 */     this.bipedLeftArm.addChild(this.leftarm1);
/*  74:    */     
/*  75: 80 */     this.earR = new ModelRenderer(this, 0, 0);
/*  76: 81 */     this.earR.addBox(4.0F, -2.0F, -3.0F, 2, 2, 1);
/*  77: 82 */     this.earR.mirror = true;
/*  78: 83 */     this.earL = new ModelRenderer(this, 0, 0);
/*  79: 84 */     this.earL.addBox(-6.0F, -2.0F, -3.0F, 2, 2, 1);
/*  80: 85 */     this.mouth = new ModelRenderer(this, 25, 0);
/*  81: 86 */     this.mouth.addBox(-2.0F, 0.0F, -8.0F, 4, 3, 3);
/*  82: 87 */     this.bipedHead.addChild(this.earR);
/*  83: 88 */     this.bipedHead.addChild(this.earL);
/*  84: 89 */     this.bipedHead.addChild(this.mouth);
/*  85: 90 */     this.tail = new ModelRenderer(this, 0, 20);
/*  86: 91 */     this.tail.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
/*  87: 92 */     this.tail.setRotationPoint(0.0F, -3.0F, 14.0F);
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*  91:    */   {
/*  92: 97 */     this.isSneak = true;
/*  93: 98 */     super.render(entity, f, f1, f2, f3, f4, f5);
/*  94: 99 */     float px = -0.5F;
/*  95:100 */     float py = 13.0F;
/*  96:101 */     float pz = 5.5F;
/*  97:103 */     for (int i = 0; i < 15; i++)
/*  98:    */     {
/*  99:105 */       float f10 = (float)Math.cos((15.0D - i / 15.0D + f / 10.0F) * 3.141592653589793D * 2.0D);
/* 100:106 */       float AnimOnTime = (float)Math.cos((i / 20.0D + f2 / 50.0F) * 3.141592653589793D * 2.0D);
/* 101:107 */       this.tail.rotateAngleX = (3.86F + f10 / 10.0F);
/* 102:108 */       this.tail.rotateAngleY = AnimOnTime;
/* 103:109 */       this.tail.rotateAngleZ = 0.0F;
/* 104:110 */       this.tail.rotationPointX = px;
/* 105:111 */       this.tail.rotationPointY = py;
/* 106:112 */       this.tail.rotationPointZ = pz;
/* 107:113 */       px = (float)(px - Math.sin(this.tail.rotateAngleY) * Math.cos(this.tail.rotateAngleX) * 0.8D);
/* 108:114 */       py = (float)(py + Math.sin(this.tail.rotateAngleX) * 0.8D);
/* 109:115 */       pz = (float)(pz - Math.cos(this.tail.rotateAngleY) * Math.cos(this.tail.rotateAngleX) * 0.8D);
/* 110:116 */       this.tail.render(f5);
/* 111:    */     }
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void setLivingAnimations(EntityLivingBase entityliving, float f, float f1, float f2)
/* 115:    */   {
/* 116:124 */     super.setLivingAnimations(entityliving, f, f1, f2);
/* 117:    */   }
/* 118:    */   
/* 119:    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 120:    */   {
/* 121:129 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 122:130 */     this.bipedRightLeg.rotationPointY = 13.0F;
/* 123:131 */     this.bipedLeftLeg.rotationPointY = 13.0F;
/* 124:132 */     EntityGiantBoxer e = (EntityGiantBoxer)entity;
/* 125:133 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 126:134 */     this.bipedRightArm.rotateAngleX = 1.570796F;
/* 127:135 */     if (e.rightHand != null)
/* 128:    */     {
/* 129:137 */       double x = e.posX;double y = e.posY + e.rightHand.getShoulderHeight();double z = e.posZ;
/* 130:138 */       float dist = (float)e.getDistance(e.rightHand.posX + x, e.rightHand.posY - e.getScaleSize() + y, e.rightHand.posZ + z) / 2.0F;
/* 131:139 */       float yDist = (float)-(e.rightHand.getShoulderHeight() + e.rightHand.posY);
/* 132:140 */       float armLength = (float)(e.getArmLength() / 2.0D);
/* 133:141 */       float armAngle = 1.570796F;
/* 134:142 */       float AH = dist / armLength;
/* 135:143 */       float entityRot = e.rotationYaw * 3.141592F / 180.0F + 0.2F;
/* 136:144 */       if (AH <= 1.0F) {
/* 137:145 */         armAngle = (float)Math.asin(AH);
/* 138:    */       }
/* 139:146 */       float shoulderAngle = (float)getAngleBetweenEntities(e, e.rightHand, f5) - entityRot;
/* 140:147 */       this.bipedRightArm.rotateAngleY = (shoulderAngle + (float)(1.570796326794897D - armAngle));
/* 141:148 */       this.rightarm1.rotateAngleZ = (3.141592F - armAngle * 2.0F);
/* 142:    */       
/* 143:150 */       this.bipedRightArm.rotateAngleX = (-(yDist - yDist / e.getScaleSize()) / armLength);
/* 144:    */       
/* 145:152 */       x = e.posX;y = e.posY + e.leftHand.getShoulderHeight();z = e.posZ;
/* 146:153 */       dist = (float)e.getDistance(e.leftHand.posX + x, e.leftHand.posY - e.getScaleSize() + y, e.leftHand.posZ + z) / 2.0F;
/* 147:154 */       AH = dist / armLength;
/* 148:155 */       entityRot = e.rotationYaw * 3.141592F / 180.0F - 0.2F;
/* 149:156 */       if (AH <= 1.0F) {
/* 150:157 */         armAngle = (float)Math.asin(AH);
/* 151:    */       }
/* 152:158 */       yDist = (float)-(e.leftHand.getShoulderHeight() + e.leftHand.posY);
/* 153:159 */       shoulderAngle = (float)getAngleBetweenEntities(e, e.leftHand, f5) - entityRot;
/* 154:160 */       this.bipedLeftArm.rotateAngleY = (shoulderAngle + (float)(-1.570796326794897D + armAngle));
/* 155:161 */       this.leftarm1.rotateAngleZ = (3.141592F + armAngle * 2.0F);
/* 156:    */       
/* 157:163 */       this.bipedLeftArm.rotateAngleX = (-(yDist - yDist / e.getScaleSize()) / armLength);
/* 158:    */     }
/* 159:166 */     AttackKick kick = e.kickHelper;
/* 160:167 */     if (kick.kickTime > 0)
/* 161:    */     {
/* 162:169 */       this.bipedRightLeg.rotateAngleX = 0.0F;
/* 163:170 */       this.bipedLeftLeg.rotateAngleX = 0.0F;
/* 164:171 */       int maxAttackAnimTime = kick.kickSpeed;
/* 165:172 */       int attackAnim = maxAttackAnimTime - kick.kickTime - maxAttackAnimTime / 10;
/* 166:173 */       float animProgress = (attackAnim + 1 + attackAnim * f5) / maxAttackAnimTime * 6.283184F;
/* 167:174 */       float dir = (kick.kickType == 2) || (kick.kickType == 4) ? -1.0F : 1.0F;
/* 168:175 */       if ((kick.kickType == 2) || (kick.kickType == 1))
/* 169:    */       {
/* 170:176 */         this.bipedLeftLeg.rotateAngleX = (dir * MathHelper.sin(animProgress) * 1.2F - dir * 0.2F);
/* 171:177 */         this.bipedLeftLeg.rotateAngleY = (dir * MathHelper.cos(animProgress) * 0.4F);
/* 172:    */       }
/* 173:    */       else
/* 174:    */       {
/* 175:179 */         this.bipedRightLeg.rotateAngleX = (dir * MathHelper.sin(animProgress) * 1.2F - dir * 0.2F);
/* 176:180 */         this.bipedRightLeg.rotateAngleY = (dir * -MathHelper.cos(animProgress) * 0.4F);
/* 177:    */       }
/* 178:    */     }
/* 179:184 */     if (e.airSmashInProgress)
/* 180:    */     {
/* 181:186 */       this.bipedBody.rotateAngleX -= 0.2F;
/* 182:    */       
/* 183:    */ 
/* 184:189 */       this.bipedRightLeg.rotationPointY += 2.0F;
/* 185:190 */       this.bipedLeftLeg.rotationPointY += 2.0F;
/* 186:191 */       this.bipedRightLeg.rotateAngleX = -0.9424778F;
/* 187:192 */       this.bipedLeftLeg.rotateAngleX = -0.9424778F;
/* 188:193 */       this.bipedRightLeg.rotateAngleY = 0.3141593F;
/* 189:194 */       this.bipedLeftLeg.rotateAngleY = -0.3141593F;
/* 190:    */     }
/* 191:    */   }
/* 192:    */   
/* 193:    */   public double getAngleBetweenEntities(Entity entity, AttackPunch part, float tickTime)
/* 194:    */   {
/* 195:201 */     double d = -part.posX;
/* 196:202 */     double d2 = -part.posZ;
/* 197:203 */     double angle = Math.atan2(d, d2);
/* 198:204 */     return -angle;
/* 199:    */   }
/* 200:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelGiantBoxer
 * JD-Core Version:    0.7.1
 */