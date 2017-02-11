/*  1:   */ package com.chocolate.chocolateQuest.client.model.golemWeapon;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBase;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ 
/*  7:   */ public class ModelMachineGun
/*  8:   */   extends ModelBase
/*  9:   */ {
/* 10:   */   public ModelRenderer bipedGun;
/* 11:   */   public ModelRenderer rifle;
/* 12:   */   public ModelRenderer cannon0;
/* 13:   */   public ModelRenderer cannon1;
/* 14:   */   public ModelRenderer cannon2;
/* 15:   */   public ModelRenderer cannon3;
/* 16:   */   
/* 17:   */   public ModelMachineGun()
/* 18:   */   {
/* 19:14 */     float f = 0.0F;
/* 20:15 */     this.bipedGun = new ModelRenderer(this, 0, 10);
/* 21:16 */     this.bipedGun.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, f);
/* 22:   */     
/* 23:18 */     this.rifle = new ModelRenderer(this, 11, 25);
/* 24:19 */     this.rifle.addBox(-1.5F, -1.5F, 9.4F, 3, 3, 1, f);
/* 25:20 */     this.rifle.setRotationPoint(2.0F, 2.0F, 0.0F);
/* 26:   */     
/* 27:22 */     f = -0.1F;
/* 28:23 */     this.cannon0 = new ModelRenderer(this, 9, 11);
/* 29:24 */     this.cannon0.addBox(-1.7F, -1.7F, 3.8F, 1, 1, 7, f);
/* 30:25 */     this.cannon0.setRotationPoint(2.0F, 2.0F, 0.0F);
/* 31:26 */     this.cannon1 = new ModelRenderer(this, 9, 11);
/* 32:27 */     this.cannon1.addBox(-1.7F, 0.7F, 3.8F, 1, 1, 7, f);
/* 33:28 */     this.cannon1.setRotationPoint(2.0F, 2.0F, 0.0F);
/* 34:29 */     this.cannon2 = new ModelRenderer(this, 9, 11);
/* 35:30 */     this.cannon2.addBox(0.7F, -1.7F, 3.8F, 1, 1, 7, f);
/* 36:31 */     this.cannon2.setRotationPoint(2.0F, 2.0F, 0.0F);
/* 37:32 */     this.cannon3 = new ModelRenderer(this, 9, 11);
/* 38:33 */     this.cannon3.addBox(0.7F, 0.7F, 3.8F, 1, 1, 7, f);
/* 39:34 */     this.cannon3.setRotationPoint(2.0F, 2.0F, 0.0F);
/* 40:35 */     float rotFactor = 0.04F;
/* 41:36 */     this.cannon0.rotateAngleX = (-rotFactor);
/* 42:37 */     this.cannon0.rotateAngleY = rotFactor;
/* 43:38 */     this.cannon1.rotateAngleX = rotFactor;
/* 44:39 */     this.cannon1.rotateAngleY = rotFactor;
/* 45:40 */     this.cannon2.rotateAngleX = (-rotFactor);
/* 46:41 */     this.cannon2.rotateAngleY = (-rotFactor);
/* 47:42 */     this.cannon3.rotateAngleX = rotFactor;
/* 48:43 */     this.cannon3.rotateAngleY = (-rotFactor);
/* 49:   */   }
/* 50:   */   
/* 51:   */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 52:   */   {
/* 53:56 */     this.bipedGun.render(f5);
/* 54:57 */     this.rifle.render(f5);
/* 55:58 */     this.cannon0.render(f5);
/* 56:59 */     this.cannon1.render(f5);
/* 57:60 */     this.cannon2.render(f5);
/* 58:61 */     this.cannon3.render(f5);
/* 59:   */   }
/* 60:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.golemWeapon.ModelMachineGun
 * JD-Core Version:    0.7.1
 */