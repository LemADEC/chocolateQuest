/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelRenderer;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.util.MathHelper;
/*  6:   */ 
/*  7:   */ public class ModelNaga
/*  8:   */   extends ModelHuman
/*  9:   */ {
/* 10:   */   ModelRenderer[] mouth;
/* 11:   */   ModelRenderer[] tail;
/* 12:   */   
/* 13:   */   public ModelNaga()
/* 14:   */   {
/* 15:24 */     this.mouth = new ModelRenderer[4];
/* 16:25 */     for (int i = 0; i < this.mouth.length; i++)
/* 17:   */     {
/* 18:27 */       this.mouth[i] = new ModelRenderer(this, 24, 0);
/* 19:28 */       this.mouth[i].addBox(-2.0F + i, -3.0F, -7.0F, 1, 1, 4);
/* 20:29 */       this.bipedHead.addChild(this.mouth[i]);
/* 21:   */     }
/* 22:32 */     this.tail = new ModelRenderer[4];
/* 23:33 */     this.tail[0] = new ModelRenderer(this, 34, 0);
/* 24:34 */     this.tail[0].addBox(-6.0F, 0.0F, -2.0F, 8, 4, 4);
/* 25:35 */     this.tail[0].setRotationPoint(0.0F, -3.0F, 14.0F);
/* 26:   */     
/* 27:37 */     this.tail[1] = new ModelRenderer(this, 34, 8);
/* 28:38 */     this.tail[1].addBox(-5.0F, 0.0F, -2.0F, 6, 4, 4);
/* 29:39 */     this.tail[1].setRotationPoint(0.0F, -3.0F, 14.0F);
/* 30:   */     
/* 31:41 */     this.tail[2] = new ModelRenderer(this, 1, 17);
/* 32:42 */     this.tail[2].addBox(-4.0F, 0.0F, -2.0F, 4, 3, 3);
/* 33:43 */     this.tail[2].setRotationPoint(0.0F, -3.0F, 14.0F);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/* 37:   */   {
/* 38:49 */     setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
/* 39:50 */     this.bipedHead.render(par7);
/* 40:51 */     this.bipedBody.render(par7);
/* 41:52 */     this.bipedRightArm.render(par7);
/* 42:53 */     this.bipedLeftArm.render(par7);
/* 43:58 */     for (int i = 0; i < this.mouth.length; i++) {
/* 44:60 */       this.mouth[i].rotateAngleX = ((float)Math.sin(par4 / 10.0F + i * 20) / 6.0F + 0.5F);
/* 45:   */     }
/* 46:64 */     float px = 1.5F;
/* 47:65 */     float py = 10.0F;
/* 48:66 */     float pz = 2.0F;
/* 49:67 */     float dist = 3.0F;
/* 50:69 */     for (int i = 0; i < 5; i++)
/* 51:   */     {
/* 52:71 */       py += i;
/* 53:72 */       pz -= (float)Math.sin(i + 1);
/* 54:73 */       px += MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
/* 55:   */       
/* 56:75 */       int tailPos = 0;
/* 57:76 */       if (i >= 4) {
/* 58:77 */         tailPos = 2;
/* 59:   */       }
/* 60:78 */       if (i > 2) {
/* 61:79 */         tailPos = 1;
/* 62:   */       }
/* 63:80 */       this.tail[tailPos].rotationPointX = px;
/* 64:81 */       this.tail[tailPos].rotationPointY = py;
/* 65:82 */       this.tail[tailPos].rotationPointZ = pz;
/* 66:83 */       this.tail[tailPos].render(par7);
/* 67:   */     }
/* 68:85 */     for (int i = 0; i < 3; i++)
/* 69:   */     {
/* 70:87 */       px += MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
/* 71:88 */       this.tail[2].rotationPointX = px;
/* 72:89 */       this.tail[2].rotationPointY = py;
/* 73:90 */       this.tail[2].rotationPointZ = (pz + i * dist);
/* 74:91 */       this.tail[2].render(par7);
/* 75:   */     }
/* 76:   */   }
/* 77:   */   
/* 78:   */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 79:   */   {
/* 80:98 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 81:   */   }
/* 82:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelNaga
 * JD-Core Version:    0.7.1
 */