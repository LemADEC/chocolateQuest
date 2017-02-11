/*   1:    */ package com.chocolate.chocolateQuest.client;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityHookShoot;
/*   4:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   5:    */ import net.minecraft.client.Minecraft;
/*   6:    */ import net.minecraft.client.renderer.Tessellator;
/*   7:    */ import net.minecraft.client.renderer.entity.Render;
/*   8:    */ import net.minecraft.client.renderer.entity.RenderManager;
/*   9:    */ import net.minecraft.client.settings.GameSettings;
/*  10:    */ import net.minecraft.entity.Entity;
/*  11:    */ import net.minecraft.entity.EntityLivingBase;
/*  12:    */ import net.minecraft.util.MathHelper;
/*  13:    */ import net.minecraft.util.ResourceLocation;
/*  14:    */ import net.minecraft.util.Vec3;
/*  15:    */ import org.lwjgl.opengl.GL11;
/*  16:    */ 
/*  17:    */ public class RenderHookShoot
/*  18:    */   extends Render
/*  19:    */ {
/*  20: 28 */   private static final ResourceLocation arrowTextures = new ResourceLocation("textures/entity/arrow.png");
/*  21:    */   
/*  22:    */   public RenderHookShoot(float f) {}
/*  23:    */   
/*  24:    */   public void doRenderFireball(EntityHookShoot entity, double x, double y, double z, float f, float f1)
/*  25:    */   {
/*  26: 36 */     Tessellator tessellator = Tessellator.instance;
/*  27: 37 */     byte type = entity.getHookType();
/*  28: 38 */     if ((type == 3) || (type == 5))
/*  29:    */     {
/*  30: 40 */       GL11.glPushMatrix();
/*  31: 41 */       GL11.glTranslatef((float)x, (float)y, (float)z);
/*  32: 42 */       GL11.glEnable(32826);
/*  33: 43 */       GL11.glBlendFunc(770, 771);
/*  34: 44 */       GL11.glEnable(3042);
/*  35: 45 */       float f2 = 0.8F;
/*  36: 46 */       GL11.glScalef(f2, f2, f2);
/*  37: 47 */       bindTexture(BDHelper.getItemTexture());
/*  38: 48 */       int spriteIndex = 241;
/*  39: 49 */       float i1 = (spriteIndex % 16 * 16 + 0) / 256.0F;
/*  40: 50 */       float i2 = (spriteIndex % 16 * 16 + 16) / 256.0F;
/*  41: 51 */       float i3 = (spriteIndex / 16 * 16 + 0) / 256.0F;
/*  42: 52 */       float i4 = (spriteIndex / 16 * 16 + 16) / 256.0F;
/*  43: 53 */       float f5 = 1.0F;
/*  44: 54 */       float f6 = 0.5F;
/*  45: 55 */       float f7 = 0.25F;
/*  46: 56 */       GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  47: 57 */       GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/*  48: 58 */       tessellator.startDrawingQuads();
/*  49: 59 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  50: 60 */       tessellator.addVertexWithUV(0.0F - f6, 0.0F - f7, 0.0D, i1, i4);
/*  51: 61 */       tessellator.addVertexWithUV(f5 - f6, 0.0F - f7, 0.0D, i2, i4);
/*  52: 62 */       tessellator.addVertexWithUV(f5 - f6, 1.0F - f7, 0.0D, i2, i3);
/*  53: 63 */       tessellator.addVertexWithUV(0.0F - f6, 1.0F - f7, 0.0D, i1, i3);
/*  54: 64 */       tessellator.draw();
/*  55: 65 */       GL11.glDisable(32826);
/*  56: 66 */       GL11.glPopMatrix();
/*  57:    */     }
/*  58:    */     else
/*  59:    */     {
/*  60: 70 */       ResourceLocation r = arrowTextures;
/*  61: 71 */       bindTexture(r);
/*  62: 72 */       GL11.glPushMatrix();
/*  63: 73 */       GL11.glTranslated(x, y, z);
/*  64: 74 */       GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * f1 - 90.0F, 0.0F, 1.0F, 0.0F);
/*  65: 75 */       GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * f1, 0.0F, 0.0F, 1.0F);
/*  66: 76 */       byte var11 = 0;
/*  67: 77 */       float txmin = 0.0F;
/*  68: 78 */       float txmax = 0.15625F;
/*  69: 79 */       float tymin = (5 + var11 * 10) / 32.0F;
/*  70: 80 */       float tymax = (10 + var11 * 10) / 32.0F;
/*  71: 81 */       float var20 = 0.05625F;
/*  72: 82 */       GL11.glEnable(32826);
/*  73:    */       
/*  74: 84 */       GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
/*  75: 85 */       GL11.glScalef(var20, var20, var20);
/*  76: 86 */       GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
/*  77: 87 */       GL11.glNormal3f(var20, 0.0F, 0.0F);
/*  78: 88 */       tessellator.startDrawingQuads();
/*  79: 89 */       tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, txmin, tymin);
/*  80: 90 */       tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, txmax, tymin);
/*  81: 91 */       tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, txmax, tymax);
/*  82: 92 */       tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, txmin, tymax);
/*  83: 93 */       tessellator.draw();
/*  84: 94 */       GL11.glNormal3f(-var20, 0.0F, 0.0F);
/*  85: 95 */       tessellator.startDrawingQuads();
/*  86: 96 */       tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, txmin, tymin);
/*  87: 97 */       tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, txmax, tymin);
/*  88: 98 */       tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, txmax, tymax);
/*  89: 99 */       tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, txmin, tymax);
/*  90:100 */       tessellator.draw();
/*  91:101 */       txmin = 0.0F;
/*  92:102 */       txmax = 0.5F;
/*  93:103 */       tymin = (0 + var11 * 10) / 32.0F;
/*  94:104 */       tymax = (5 + var11 * 10) / 32.0F;
/*  95:106 */       for (int var23 = 0; var23 < 4; var23++)
/*  96:    */       {
/*  97:108 */         GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  98:109 */         GL11.glNormal3f(0.0F, 0.0F, var20);
/*  99:110 */         tessellator.startDrawingQuads();
/* 100:111 */         tessellator.addVertexWithUV(-8.0D, -2.0D, 0.0D, txmin, tymin);
/* 101:112 */         tessellator.addVertexWithUV(8.0D, -2.0D, 0.0D, txmax, tymin);
/* 102:113 */         tessellator.addVertexWithUV(8.0D, 2.0D, 0.0D, txmax, tymax);
/* 103:114 */         tessellator.addVertexWithUV(-8.0D, 2.0D, 0.0D, txmin, tymax);
/* 104:115 */         tessellator.draw();
/* 105:    */       }
/* 106:118 */       GL11.glDisable(32826);
/* 107:119 */       GL11.glPopMatrix();
/* 108:    */     }
/* 109:123 */     if (entity.getThrower() != null)
/* 110:    */     {
/* 111:125 */       float f9 = entity.getThrower().getSwingProgress(f1);
/* 112:126 */       float f10 = MathHelper.sin(MathHelper.sqrt_float(f9) * 3.141593F);
/* 113:127 */       Vec3 vec3 = Vec3.createVectorHelper(-0.5D, 0.03D, 0.8D);
/* 114:128 */       vec3.rotateAroundX(-(entity.getThrower().prevRotationPitch + (entity.getThrower().rotationPitch - entity.getThrower().prevRotationPitch) * f1) * 3.141593F / 180.0F);
/* 115:129 */       vec3.rotateAroundY(-(entity.getThrower().prevRotationYaw + (entity.getThrower().rotationYaw - entity.getThrower().prevRotationYaw) * f1) * 3.141593F / 180.0F);
/* 116:130 */       vec3.rotateAroundY(f10 * 0.5F);
/* 117:131 */       vec3.rotateAroundX(-f10 * 0.7F);
/* 118:132 */       double endX = entity.getThrower().prevPosX + (entity.getThrower().posX - entity.getThrower().prevPosX) * f1 + vec3.xCoord;
/* 119:133 */       double endY = entity.getThrower().prevPosY + (entity.getThrower().posY - entity.getThrower().prevPosY) * f1 + vec3.yCoord;
/* 120:134 */       double endZ = entity.getThrower().prevPosZ + (entity.getThrower().posZ - entity.getThrower().prevPosZ) * f1 + vec3.zCoord;
/* 121:135 */       double d6 = entity.getThrower() != Minecraft.getMinecraft().thePlayer ? entity.getThrower().getEyeHeight() : 0.0D;
/* 122:137 */       if ((this.renderManager.options.thirdPersonView > 0) || (entity.getThrower() != Minecraft.getMinecraft().thePlayer))
/* 123:    */       {
/* 124:139 */         float f11 = (entity.getThrower().prevRenderYawOffset + (entity.getThrower().renderYawOffset - entity.getThrower().prevRenderYawOffset) * f1) * 3.141593F / 180.0F;
/* 125:140 */         double d7 = MathHelper.sin(f11);
/* 126:141 */         double d8 = MathHelper.cos(f11);
/* 127:142 */         endX = entity.getThrower().prevPosX + (entity.getThrower().posX - entity.getThrower().prevPosX) * f1 - d8 * 0.35D - d7 * 0.85D;
/* 128:143 */         endY = entity.getThrower().prevPosY + d6 + (entity.getThrower().posY - entity.getThrower().prevPosY) * f1 - 0.45D;
/* 129:144 */         endZ = entity.getThrower().prevPosZ + (entity.getThrower().posZ - entity.getThrower().prevPosZ) * f1 - d7 * 0.35D + d8 * 0.85D;
/* 130:    */       }
/* 131:147 */       double startX = entity.prevPosX + (entity.posX - entity.prevPosX) * f1;
/* 132:148 */       double startY = entity.prevPosY + (entity.posY - entity.prevPosY) * f1 + 0.25D;
/* 133:149 */       double startZ = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * f1;
/* 134:150 */       double despX = (float)(endX - startX);
/* 135:151 */       double despY = (float)(endY - startY);
/* 136:152 */       double despZ = (float)(endZ - startZ);
/* 137:153 */       GL11.glDisable(3553);
/* 138:154 */       GL11.glDisable(2896);
/* 139:155 */       byte steps = 16;
/* 140:156 */       double hang = entity.getRadius() - entity.getDistanceToEntity(entity.getThrower());
/* 141:158 */       if (hang < 0.2D) {
/* 142:160 */         hang = 0.0D;
/* 143:    */       }
/* 144:164 */       tessellator.startDrawing(3);
/* 145:165 */       tessellator.setColorOpaque_I(entity.getRopeColor());
/* 146:167 */       for (int i = 0; i <= steps; i++)
/* 147:    */       {
/* 148:169 */         float stepVariation = i / steps;
/* 149:170 */         double varY = -Math.sin(i * 3.141592653589793D / steps) * hang;
/* 150:171 */         tessellator.addVertex(x + despX * stepVariation, y + despY * stepVariation + varY, z + despZ * stepVariation);
/* 151:    */       }
/* 152:176 */       tessellator.draw();
/* 153:177 */       GL11.glEnable(2896);
/* 154:178 */       GL11.glEnable(3553);
/* 155:    */     }
/* 156:    */   }
/* 157:    */   
/* 158:    */   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
/* 159:    */   {
/* 160:185 */     doRenderFireball((EntityHookShoot)entity, d, d1, d2, f, f1);
/* 161:    */   }
/* 162:    */   
/* 163:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 164:    */   {
/* 165:190 */     return null;
/* 166:    */   }
/* 167:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.RenderHookShoot
 * JD-Core Version:    0.7.1
 */