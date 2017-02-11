/*   1:    */ package com.chocolate.chocolateQuest.client.model;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.boss.AttackKick;
/*   4:    */ import com.chocolate.chocolateQuest.entity.boss.EntitySlimeBoss;
/*   5:    */ import net.minecraft.client.model.ModelQuadruped;
/*   6:    */ import net.minecraft.client.model.ModelRenderer;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ import org.lwjgl.opengl.GL11;
/*  10:    */ 
/*  11:    */ public class ModelSlimeBoss
/*  12:    */   extends ModelQuadruped
/*  13:    */ {
/*  14:    */   ModelRenderer foot2;
/*  15:    */   ModelRenderer foot1;
/*  16:    */   ModelRenderer headBT;
/*  17:    */   ModelRenderer core;
/*  18:    */   ModelRenderer eyer;
/*  19:    */   ModelRenderer eyel;
/*  20:    */   ModelSlimeBoss goomy;
/*  21:    */   boolean isMain;
/*  22:    */   
/*  23:    */   public ModelSlimeBoss()
/*  24:    */   {
/*  25: 31 */     this(0.0F, 0, true);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public ModelSlimeBoss(float s, int to, boolean isMain)
/*  29:    */   {
/*  30: 35 */     super(12, s);
/*  31: 36 */     this.isMain = isMain;
/*  32: 37 */     this.textureWidth = 64;
/*  33: 38 */     this.textureHeight = 64;
/*  34:    */     
/*  35: 40 */     this.body = new ModelRenderer(this, 0, to);
/*  36: 41 */     this.body.addBox(-5.0F, -6.0F, -2.0F, 10, 6, 12, s);
/*  37: 42 */     this.body.setRotationPoint(0.0F, 19.0F, -4.0F);
/*  38: 43 */     this.body.setTextureSize(this.textureWidth, this.textureHeight);
/*  39:    */     
/*  40: 45 */     this.leg1 = new ModelRenderer(this, 0, to);
/*  41: 46 */     this.leg1.addBox(0.0F, -1.0F, -1.0F, 3, 9, 3, s);
/*  42: 47 */     this.leg1.setRotationPoint(5.0F, -3.0F, 8.0F);
/*  43: 48 */     this.leg1.setTextureSize(this.textureWidth, this.textureHeight);
/*  44: 49 */     this.foot1 = new ModelRenderer(this, 56, to);
/*  45: 50 */     this.foot1.addBox(-1.0F, -10.0F, -2.0F, 2, 10, 2, s);
/*  46: 51 */     this.foot1.setRotationPoint(1.0F, 8.0F, 0.0F);
/*  47: 52 */     this.foot1.setTextureSize(this.textureWidth, this.textureHeight);
/*  48: 53 */     this.body.addChild(this.leg1);
/*  49: 54 */     this.leg1.addChild(this.foot1);
/*  50: 55 */     setRotation(this.leg1, -1.047198F, 0.0F, 0.0F);
/*  51: 56 */     setRotation(this.foot1, -1.047198F, 0.0F, 0.0F);
/*  52:    */     
/*  53: 58 */     this.leg2 = new ModelRenderer(this, 0, to);
/*  54: 59 */     this.leg2.addBox(-0.0F, -1.0F, -1.0F, 3, 9, 3, s);
/*  55: 60 */     this.leg2.setRotationPoint(-8.0F, -3.0F, 8.0F);
/*  56: 61 */     this.leg2.setTextureSize(this.textureWidth, this.textureHeight);
/*  57: 62 */     this.foot2 = new ModelRenderer(this, 56, to);
/*  58: 63 */     this.foot2.addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2, s);
/*  59: 64 */     this.foot2.setRotationPoint(1.0F, 8.0F, 0.0F);
/*  60: 65 */     this.foot2.setTextureSize(this.textureWidth, this.textureHeight);
/*  61: 66 */     this.body.addChild(this.leg2);
/*  62: 67 */     this.leg2.addChild(this.foot2);
/*  63: 68 */     setRotation(this.leg2, -1.047198F, 0.0F, 0.0F);
/*  64: 69 */     setRotation(this.foot2, -1.047198F, 0.0F, 0.0F);
/*  65:    */     
/*  66: 71 */     this.leg4 = new ModelRenderer(this, 56, to + 12);
/*  67: 72 */     this.leg4.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, s);
/*  68: 73 */     this.leg4.setRotationPoint(4.0F, 0.0F, -1.0F);
/*  69: 74 */     this.leg4.setTextureSize(this.textureWidth, this.textureHeight);
/*  70: 75 */     this.leg3 = new ModelRenderer(this, 56, to + 12);
/*  71: 76 */     this.leg3.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, s);
/*  72: 77 */     this.leg3.setRotationPoint(-4.0F, 0.0F, -1.0F);
/*  73: 78 */     this.leg3.setTextureSize(this.textureWidth, this.textureHeight);
/*  74: 79 */     this.body.addChild(this.leg4);
/*  75: 80 */     this.body.addChild(this.leg3);
/*  76:    */     
/*  77: 82 */     this.head = new ModelRenderer(this, 0, to + 18);
/*  78: 83 */     this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 6, 8, s);
/*  79: 84 */     this.head.setRotationPoint(0.0F, -6.0F, -1.0F);
/*  80: 85 */     this.head.setTextureSize(this.textureWidth, this.textureHeight);
/*  81: 86 */     this.body.addChild(this.head);
/*  82: 87 */     this.headBT = new ModelRenderer(this, 32, to + 20);
/*  83: 88 */     this.headBT.addBox(-4.0F, 2.0F, -10.0F, 8, 4, 8, s);
/*  84: 89 */     this.headBT.setRotationPoint(0.0F, -2.0F, 4.0F);
/*  85: 90 */     this.headBT.setTextureSize(this.textureWidth, this.textureHeight);
/*  86: 91 */     this.head.addChild(this.headBT);
/*  87: 93 */     if (isMain)
/*  88:    */     {
/*  89: 94 */       this.core = new ModelRenderer(this, 32, 0);
/*  90: 95 */       this.core.addBox(-3.0F, -1.0F, -2.0F, 6, 6, 6);
/*  91: 96 */       this.core.setRotationPoint(0.0F, -1.5F, -3.0F);
/*  92: 97 */       this.core.setTextureSize(this.textureWidth, this.textureHeight);
/*  93: 98 */       setRotation(this.core, 0.4F, 0.0F, 0.0F);
/*  94: 99 */       this.head.addChild(this.core);
/*  95:    */       
/*  96:101 */       this.eyer = new ModelRenderer(this, 0, 18);
/*  97:102 */       this.eyer.addBox(0.0F, -1.0F, -1.0F, 2, 2, 2);
/*  98:103 */       this.eyer.setRotationPoint(3.0F, -1.0F, -1.0F);
/*  99:104 */       this.eyer.setTextureSize(this.textureWidth, this.textureHeight);
/* 100:105 */       this.eyel = new ModelRenderer(this, 0, 18);
/* 101:106 */       this.eyel.addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2);
/* 102:107 */       this.eyel.setRotationPoint(-3.0F, -1.0F, -1.0F);
/* 103:108 */       this.eyel.setTextureSize(this.textureWidth, this.textureHeight);
/* 104:109 */       this.head.addChild(this.eyer);
/* 105:110 */       this.head.addChild(this.eyel);
/* 106:111 */       this.goomy = new ModelSlimeBoss(1.2F, 32, false);
/* 107:    */     }
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 111:    */   {
/* 112:117 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 113:118 */     this.body.render(f5);
/* 114:119 */     this.goomy.copyRotations(this);
/* 115:120 */     GL11.glEnable(3042);
/* 116:121 */     GL11.glBlendFunc(770, 771);
/* 117:122 */     this.goomy.body.render(f5);
/* 118:123 */     GL11.glDisable(3042);
/* 119:    */   }
/* 120:    */   
/* 121:    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 122:    */   {
/* 123:128 */     model.rotateAngleX = x;
/* 124:129 */     model.rotateAngleY = y;
/* 125:130 */     model.rotateAngleZ = z;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public void copyRotations(ModelSlimeBoss model)
/* 129:    */   {
/* 130:134 */     setRotation(this.body, model.body.rotateAngleX, model.body.rotateAngleY, model.body.rotateAngleZ);
/* 131:135 */     setRotation(this.leg1, model.leg1.rotateAngleX, model.leg1.rotateAngleY, model.leg1.rotateAngleZ);
/* 132:136 */     setRotation(this.leg2, model.leg2.rotateAngleX, model.leg2.rotateAngleY, model.leg2.rotateAngleZ);
/* 133:137 */     setRotation(this.leg3, model.leg3.rotateAngleX, model.leg3.rotateAngleY, model.leg3.rotateAngleZ);
/* 134:138 */     setRotation(this.leg4, model.leg4.rotateAngleX, model.leg4.rotateAngleY, model.leg4.rotateAngleZ);
/* 135:139 */     setRotation(this.foot1, model.foot1.rotateAngleX, model.foot1.rotateAngleY, model.foot1.rotateAngleZ);
/* 136:140 */     setRotation(this.foot2, model.foot2.rotateAngleX, model.foot2.rotateAngleY, model.foot2.rotateAngleZ);
/* 137:141 */     setRotation(this.head, model.head.rotateAngleX, model.head.rotateAngleY, model.head.rotateAngleZ);
/* 138:142 */     setRotation(this.headBT, model.headBT.rotateAngleX, model.headBT.rotateAngleY, model.headBT.rotateAngleZ);
/* 139:    */   }
/* 140:    */   
/* 141:    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 142:    */   {
/* 143:147 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 144:148 */     setRotation(this.body, 0.191986F, 0.0F, 0.0F);
/* 145:149 */     float ankleRot = -1.047198F;
/* 146:150 */     float ankleRotY = 0.7F;
/* 147:151 */     this.leg2.rotateAngleX += ankleRot;
/* 148:152 */     this.leg1.rotateAngleX += ankleRot;
/* 149:153 */     this.leg1.rotateAngleY = (-ankleRotY);
/* 150:154 */     this.leg2.rotateAngleY = ankleRotY;
/* 151:155 */     this.headBT.rotateAngleX = 0.0F;
/* 152:156 */     setRotation(this.foot2, -1.047198F, 0.0F, 0.0F);
/* 153:157 */     setRotation(this.foot1, -1.047198F, 0.0F, 0.0F);
/* 154:    */     
/* 155:    */ 
/* 156:160 */     EntitySlimeBoss e = (EntitySlimeBoss)entity;
/* 157:161 */     AttackKick kick = e.kickHelper;
/* 158:162 */     if (kick.kickTime > 0)
/* 159:    */     {
/* 160:164 */       this.leg4.rotateAngleX = (this.leg3.rotateAngleX = 0.0F);
/* 161:165 */       int maxAttackAnimTime = kick.kickSpeed;
/* 162:166 */       int attackAnim = maxAttackAnimTime - kick.kickTime - maxAttackAnimTime / 10;
/* 163:167 */       float animProgress = (attackAnim + 1 + attackAnim * f5) / maxAttackAnimTime * 6.283184F;
/* 164:168 */       if (kick.kickType == 1)
/* 165:    */       {
/* 166:169 */         this.leg1.rotateAngleX = (MathHelper.sin(animProgress) * 1.4F - 0.2F);
/* 167:170 */         this.leg1.rotateAngleY += MathHelper.cos(animProgress) * 0.4F;
/* 168:171 */         this.foot1.rotateAngleX = (-MathHelper.sin(animProgress / 2.0F) * 3.0F - 1.0F);
/* 169:    */       }
/* 170:172 */       else if (kick.kickType == 3)
/* 171:    */       {
/* 172:173 */         this.leg2.rotateAngleX = (MathHelper.sin(animProgress) * 1.4F - 0.2F);
/* 173:174 */         this.leg2.rotateAngleY -= MathHelper.cos(animProgress) * 0.4F;
/* 174:175 */         this.foot2.rotateAngleX = (-MathHelper.sin(animProgress / 2.0F) * 3.0F - 1.0F);
/* 175:    */       }
/* 176:176 */       else if (kick.kickType == 2)
/* 177:    */       {
/* 178:177 */         this.leg2.rotateAngleX += -MathHelper.sin(animProgress) * 1.4F + 0.2F;
/* 179:178 */         this.leg2.rotateAngleY += -MathHelper.cos(animProgress) * 0.4F;
/* 180:    */       }
/* 181:179 */       else if (kick.kickType == 4)
/* 182:    */       {
/* 183:180 */         this.leg1.rotateAngleX += -MathHelper.sin(animProgress) * 1.4F + 0.2F;
/* 184:181 */         this.leg1.rotateAngleY += MathHelper.cos(animProgress) * 0.4F;
/* 185:    */       }
/* 186:    */     }
/* 187:184 */     if (e.isAttacking())
/* 188:    */     {
/* 189:185 */       this.head.rotateAngleX = -0.8F;
/* 190:186 */       this.headBT.rotateAngleX = 0.8F;
/* 191:    */     }
/* 192:188 */     if (this.onGround > 0.0F)
/* 193:    */     {
/* 194:190 */       this.head.rotateAngleX = (MathHelper.sin(this.onGround * 3.141593F) * -0.4F);
/* 195:191 */       this.headBT.rotateAngleX = (MathHelper.sin(this.onGround * 3.141593F) * 0.4F);
/* 196:    */     }
/* 197:193 */     if (e.slimePoolAttackTime > 0)
/* 198:    */     {
/* 199:194 */       int slimePoolAttackTimeMax = e.slimePoolAttackTimeMax;
/* 200:195 */       float i = e.slimePoolAttackTime - slimePoolAttackTimeMax + e.slimePoolChargeTime;
/* 201:196 */       this.head.rotateAngleX = ((1.0F - Math.max(0.0F, i / slimePoolAttackTimeMax)) * 2.0F);
/* 202:    */     }
/* 203:    */   }
/* 204:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelSlimeBoss
 * JD-Core Version:    0.7.1
 */