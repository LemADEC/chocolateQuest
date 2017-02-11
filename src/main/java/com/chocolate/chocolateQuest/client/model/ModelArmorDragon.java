/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBiped;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ 
/*  7:   */ public class ModelArmorDragon
/*  8:   */   extends ModelBiped
/*  9:   */ {
/* 10:   */   ModelRenderer mouthUp;
/* 11:   */   ModelRenderer mouthDown;
/* 12:   */   ModelRenderer nose;
/* 13:   */   ModelRenderer hornLeft;
/* 14:   */   ModelRenderer hornRight;
/* 15:   */   
/* 16:   */   public ModelArmorDragon()
/* 17:   */   {
/* 18:17 */     this(1.6F);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public ModelArmorDragon(float size)
/* 22:   */   {
/* 23:22 */     int textureSizeX = 128;int textureSizeY = 64;
/* 24:   */     
/* 25:24 */     this.bipedHead = new ModelRenderer(this, 74, 0).setTextureSize(textureSizeX, textureSizeY);
/* 26:25 */     this.bipedHead.addBox(-2.5F, -3.0F, -4.0F, 5, 5, 8, size);
/* 27:   */     
/* 28:27 */     this.mouthUp = new ModelRenderer(this, 106, 0).setTextureSize(textureSizeX, textureSizeY);
/* 29:28 */     this.mouthUp.addBox(-2.0F, -2.0F, -11.0F, 4, 3, 7, size);
/* 30:29 */     this.mouthDown = new ModelRenderer(this, 92, 0).setTextureSize(textureSizeX, textureSizeY);
/* 31:30 */     this.mouthDown.addBox(-2.0F, 1.0F, -10.0F, 4, 1, 6, size);
/* 32:31 */     this.nose = new ModelRenderer(this, 107, 11).setTextureSize(textureSizeX, textureSizeY);
/* 33:32 */     this.nose.addBox(-2.0F, -3.0F, -11.0F, 4, 1, 1, size);
/* 34:33 */     this.hornLeft = new ModelRenderer(this, 94, 8).setTextureSize(textureSizeX, textureSizeY);
/* 35:34 */     this.hornLeft.addBox(1.5F, -2.0F, 3.0F, 1, 1, 8, size);
/* 36:35 */     this.hornLeft.rotateAngleX = 0.5F;
/* 37:36 */     this.hornLeft.rotateAngleY = 0.098F;
/* 38:37 */     this.hornRight = new ModelRenderer(this, 94, 8).setTextureSize(textureSizeX, textureSizeY);
/* 39:38 */     this.hornRight.addBox(-2.5F, -2.0F, 3.0F, 1, 1, 8, size);
/* 40:39 */     this.hornRight.rotateAngleX = 0.5F;
/* 41:40 */     this.hornRight.rotateAngleY = -0.298F;
/* 42:   */     
/* 43:42 */     this.bipedHead.addChild(this.mouthUp);
/* 44:43 */     this.bipedHead.addChild(this.mouthDown);
/* 45:44 */     this.bipedHead.addChild(this.nose);
/* 46:45 */     this.bipedHead.addChild(this.hornLeft);
/* 47:46 */     this.bipedHead.addChild(this.hornRight);
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 51:   */   {
/* 52:53 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 53:   */     
/* 54:   */ 
/* 55:56 */     this.bipedHead.setRotationPoint(0.0F, -4.0F, 0.0F);
/* 56:57 */     this.bipedHead.render(f5);
/* 57:   */   }
/* 58:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelArmorDragon
 * JD-Core Version:    0.7.1
 */