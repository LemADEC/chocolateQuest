/*   1:    */ package com.chocolate.chocolateQuest.client;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*   4:    */ import com.chocolate.chocolateQuest.entity.projectile.ProjectileBase;
/*   5:    */ import com.chocolate.chocolateQuest.entity.projectile.ProjectileMagicStormProjectile;
/*   6:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   7:    */ import java.util.Random;
/*   8:    */ import net.minecraft.client.renderer.Tessellator;
/*   9:    */ import net.minecraft.client.renderer.entity.Render;
/*  10:    */ import net.minecraft.client.renderer.entity.RenderManager;
/*  11:    */ import net.minecraft.entity.Entity;
/*  12:    */ import net.minecraft.entity.EntityLivingBase;
/*  13:    */ import net.minecraft.util.MathHelper;
/*  14:    */ import net.minecraft.util.ResourceLocation;
/*  15:    */ import net.minecraft.util.Vec3;
/*  16:    */ import org.lwjgl.opengl.GL11;
/*  17:    */ 
/*  18:    */ public class RenderBallProjectile
/*  19:    */   extends Render
/*  20:    */ {
/*  21:    */   public RenderBallProjectile(float f) {}
/*  22:    */   
/*  23:    */   public void doRenderFireball(EntityBaseBall entityball, double x, double y, double z, float f, float f1)
/*  24:    */   {
/*  25: 39 */     int spriteIndex = entityball.getTextureIndex();
/*  26: 40 */     Tessellator tessellator = Tessellator.instance;
/*  27: 42 */     if (spriteIndex >= 0)
/*  28:    */     {
/*  29: 45 */       GL11.glPushMatrix();
/*  30: 46 */       GL11.glTranslatef((float)x, (float)y, (float)z);
/*  31: 47 */       GL11.glEnable(32826);
/*  32: 48 */       GL11.glBlendFunc(770, 771);
/*  33: 49 */       GL11.glEnable(3042);
/*  34: 50 */       float f2 = entityball.getBallSize();
/*  35: 51 */       GL11.glScalef(f2, f2, f2);
/*  36: 52 */       bindTexture(BDHelper.texture);
/*  37: 53 */       float i1 = (spriteIndex % 16 * 16 + 0) / 256.0F;
/*  38: 54 */       float i2 = (spriteIndex % 16 * 16 + 16) / 256.0F;
/*  39: 55 */       float i3 = (spriteIndex / 16 * 16 + 0) / 256.0F;
/*  40: 56 */       float i4 = (spriteIndex / 16 * 16 + 16) / 256.0F;
/*  41: 57 */       float f5 = 1.0F;
/*  42: 58 */       float f6 = 0.5F;
/*  43: 59 */       float f7 = 0.25F;
/*  44: 60 */       GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  45: 61 */       GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/*  46: 62 */       GL11.glTranslatef(0.0F, 0.0F, entityball.getBallData().getZOffset());
/*  47: 63 */       tessellator.startDrawingQuads();
/*  48: 64 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  49: 65 */       tessellator.addVertexWithUV(0.0F - f6, 0.0F - f7, 0.0D, i1, i4);
/*  50: 66 */       tessellator.addVertexWithUV(f5 - f6, 0.0F - f7, 0.0D, i2, i4);
/*  51: 67 */       tessellator.addVertexWithUV(f5 - f6, 1.0F - f7, 0.0D, i2, i3);
/*  52: 68 */       tessellator.addVertexWithUV(0.0F - f6, 1.0F - f7, 0.0D, i1, i3);
/*  53: 69 */       tessellator.draw();
/*  54: 70 */       GL11.glDisable(32826);
/*  55: 71 */       GL11.glPopMatrix();
/*  56:    */     }
/*  57: 74 */     else if ((spriteIndex == -1) || (spriteIndex == -2))
/*  58:    */     {
/*  59: 76 */       float swingProgress = entityball.getThrower().getSwingProgress(f1);
/*  60: 77 */       float f10 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.141593F);
/*  61: 78 */       Vec3 vec3 = Vec3.createVectorHelper(-0.5D, 0.03D, 0.8D);
/*  62: 79 */       vec3.rotateAroundX(-(entityball.getThrower().prevRotationPitch + (entityball.getThrower().rotationPitch - entityball.getThrower().prevRotationPitch) * f1) * 3.141593F / 180.0F);
/*  63: 80 */       vec3.rotateAroundY(-(entityball.getThrower().prevRotationYaw + (entityball.getThrower().rotationYaw - entityball.getThrower().prevRotationYaw) * f1) * 3.141593F / 180.0F);
/*  64: 81 */       vec3.rotateAroundY(f10 * 0.5F);
/*  65: 82 */       vec3.rotateAroundX(-f10 * 0.7F);
/*  66: 83 */       double endX = entityball.getThrower().prevPosX + (entityball.getThrower().posX - entityball.getThrower().prevPosX) * f1 + vec3.xCoord;
/*  67: 84 */       double endY = entityball.getThrower().prevPosY + (entityball.getThrower().posY - entityball.getThrower().prevPosY) * f1 + vec3.yCoord;
/*  68: 85 */       double endZ = entityball.getThrower().prevPosZ + (entityball.getThrower().posZ - entityball.getThrower().prevPosZ) * f1 + vec3.zCoord;
/*  69: 87 */       if (spriteIndex == -2)
/*  70:    */       {
/*  71: 88 */         ProjectileMagicStormProjectile ballData = (ProjectileMagicStormProjectile)entityball.getBallData();
/*  72: 89 */         endX = ballData.x;
/*  73: 90 */         endY = ballData.y;
/*  74: 91 */         endZ = ballData.z;
/*  75:    */       }
/*  76: 95 */       double startX = entityball.prevPosX + (entityball.posX - entityball.prevPosX) * f1;
/*  77: 96 */       double startY = entityball.prevPosY + (entityball.posY - entityball.prevPosY) * f1 + 0.25D;
/*  78: 97 */       double startZ = entityball.prevPosZ + (entityball.posZ - entityball.prevPosZ) * f1;
/*  79:    */       
/*  80: 99 */       double despX = (float)(endX - startX);
/*  81:100 */       double despY = (float)(endY - startY);
/*  82:101 */       double despZ = (float)(endZ - startZ);
/*  83:102 */       short totalSteps = (short)(entityball.ticksExisted * 2);
/*  84:    */       
/*  85:104 */       int color = entityball.getBallData().getRopeColor();
/*  86:105 */       float red = 0.4F;
/*  87:106 */       float green = 0.4F;
/*  88:107 */       float blue = 1.0F;
/*  89:108 */       tessellator.setColorRGBA_F(red, green, blue, 0.2F);
/*  90:109 */       GL11.glDisable(2884);
/*  91:110 */       GL11.glDisable(3553);
/*  92:111 */       GL11.glDisable(2896);
/*  93:112 */       GL11.glDisable(3008);
/*  94:113 */       GL11.glEnable(3042);
/*  95:114 */       GL11.glBlendFunc(770, 1);
/*  96:115 */       float f2 = 0.5F;
/*  97:116 */       tessellator.setColorRGBA_F(0.9F * f2, 0.9F * f2, 1.0F * f2, 0.3F);
/*  98:117 */       Random rnd = new Random();
/*  99:118 */       int startingPos = Math.max(0, totalSteps - 60);
/* 100:120 */       for (int step = 1; step < 4; step++)
/* 101:    */       {
/* 102:122 */         tessellator.startDrawing(5);
/* 103:123 */         tessellator.setColorRGBA_F(red, green, blue, 0.2F);
/* 104:124 */         rnd.setSeed(entityball.getEntityId());
/* 105:125 */         double xdesp = Math.cos(-Math.toRadians(this.renderManager.playerViewY)) * step * 0.02D;
/* 106:126 */         double zdesp = Math.sin(Math.toRadians(this.renderManager.playerViewY)) * step * 0.02D;
/* 107:128 */         for (int i = startingPos; i <= totalSteps; i++)
/* 108:    */         {
/* 109:130 */           float stepVariation = i / totalSteps;
/* 110:131 */           double varY = 1.0D;
/* 111:132 */           double diffx = (rnd.nextDouble() - 0.5D) * varY;
/* 112:133 */           double diffz = (rnd.nextDouble() - 0.5D) * varY;
/* 113:134 */           double diffy = (rnd.nextDouble() - 0.5D) * varY;
/* 114:135 */           if (i == startingPos)
/* 115:    */           {
/* 116:136 */             stepVariation = 0.0F;
/* 117:137 */             diffx = 0.0D;
/* 118:138 */             diffz = 0.0D;
/* 119:139 */             diffy = 0.0D;
/* 120:    */           }
/* 121:141 */           tessellator.addVertex(x + despX - despX * stepVariation + diffx - xdesp, y + despY - despY * stepVariation + diffy, z + despZ - despZ * stepVariation + diffz + zdesp);
/* 122:    */           
/* 123:    */ 
/* 124:    */ 
/* 125:145 */           tessellator.addVertex(x + despX - despX * stepVariation + diffx + xdesp, y + despY - despY * stepVariation + diffy, z + despZ - despZ * stepVariation + diffz - zdesp);
/* 126:    */         }
/* 127:150 */         tessellator.draw();
/* 128:    */       }
/* 129:152 */       GL11.glEnable(3553);
/* 130:153 */       GL11.glEnable(2896);
/* 131:    */     }
/* 132:    */   }
/* 133:    */   
/* 134:    */   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
/* 135:    */   {
/* 136:159 */     doRenderFireball((EntityBaseBall)entity, d, d1, d2, f, f1);
/* 137:    */   }
/* 138:    */   
/* 139:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 140:    */   {
/* 141:164 */     return null;
/* 142:    */   }
/* 143:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.RenderBallProjectile
 * JD-Core Version:    0.7.1
 */