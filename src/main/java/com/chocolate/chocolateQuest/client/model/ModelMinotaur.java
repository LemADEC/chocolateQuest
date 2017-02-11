/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelRenderer;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ 
/*  6:   */ public class ModelMinotaur
/*  7:   */   extends ModelHuman
/*  8:   */ {
/*  9:   */   ModelRenderer horn22;
/* 10:   */   ModelRenderer horn21;
/* 11:   */   ModelRenderer horn2;
/* 12:   */   ModelRenderer horn12;
/* 13:   */   ModelRenderer horn1;
/* 14:   */   ModelRenderer horn11;
/* 15:   */   ModelRenderer mouth;
/* 16:   */   
/* 17:   */   public ModelMinotaur()
/* 18:   */   {
/* 19:28 */     this(0.0F);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public ModelMinotaur(float f)
/* 23:   */   {
/* 24:34 */     super(f);
/* 25:35 */     this.horn1 = new ModelRenderer(this, 0, 0);
/* 26:36 */     this.horn1.addBox(-4.0F, -8.0F, -4.0F, 2, 2, 2);
/* 27:37 */     this.horn1.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 28:38 */     this.horn1.mirror = true;
/* 29:39 */     setRotation(this.horn1, -0.0569039F, 0.0F, 0.0F);
/* 30:40 */     this.horn11 = new ModelRenderer(this, 0, 0);
/* 31:41 */     this.horn11.addBox(-5.0F, -9.0F, -5.0F, 2, 2, 2);
/* 32:42 */     this.horn11.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 33:43 */     this.horn11.setTextureSize(64, 32);
/* 34:44 */     this.horn11.mirror = true;
/* 35:45 */     setRotation(this.horn11, -0.0569039F, 0.0F, 0.0F);
/* 36:46 */     this.horn12 = new ModelRenderer(this, 0, 0);
/* 37:47 */     this.horn12.addBox(-5.0F, -9.0F, -8.0F, 1, 1, 3);
/* 38:48 */     this.horn12.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 39:49 */     this.horn12.mirror = true;
/* 40:50 */     setRotation(this.horn12, -0.0569039F, 0.0F, 0.0F);
/* 41:51 */     this.horn22 = new ModelRenderer(this, 0, 0);
/* 42:52 */     this.horn22.addBox(4.0F, -9.0F, -8.0F, 1, 1, 3);
/* 43:53 */     this.horn22.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 44:54 */     this.horn22.mirror = true;
/* 45:55 */     setRotation(this.horn22, -0.0569039F, 0.0F, 0.0F);
/* 46:56 */     this.horn21 = new ModelRenderer(this, 0, 0);
/* 47:57 */     this.horn21.addBox(3.0F, -9.0F, -5.0F, 2, 2, 2);
/* 48:58 */     this.horn21.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 49:59 */     this.horn21.mirror = true;
/* 50:60 */     setRotation(this.horn21, -0.0569039F, 0.0F, 0.0F);
/* 51:61 */     this.horn2 = new ModelRenderer(this, 0, 0);
/* 52:62 */     this.horn2.addBox(2.0F, -8.0F, -4.0F, 2, 2, 2);
/* 53:63 */     this.horn2.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 54:64 */     this.horn2.mirror = true;
/* 55:65 */     setRotation(this.horn2, -0.0569039F, 0.0F, 0.0F);
/* 56:66 */     this.bipedHead.addChild(this.horn1);
/* 57:67 */     this.bipedHead.addChild(this.horn11);
/* 58:68 */     this.bipedHead.addChild(this.horn12);
/* 59:69 */     this.bipedHead.addChild(this.horn2);
/* 60:70 */     this.bipedHead.addChild(this.horn21);
/* 61:71 */     this.bipedHead.addChild(this.horn22);
/* 62:   */     
/* 63:73 */     this.mouth = new ModelRenderer(this, 25, 0);
/* 64:74 */     this.mouth.addBox(-2.0F, -3.0F, -6.0F, 4, 3, 3);
/* 65:75 */     this.bipedHead.addChild(this.mouth);
/* 66:   */   }
/* 67:   */   
/* 68:   */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/* 69:   */   {
/* 70:81 */     super.render(par1Entity, par2, par3, par4, par5, par6, par7);
/* 71:   */   }
/* 72:   */   
/* 73:   */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 74:   */   {
/* 75:87 */     model.rotateAngleX = x;
/* 76:88 */     model.rotateAngleY = y;
/* 77:89 */     model.rotateAngleZ = z;
/* 78:   */   }
/* 79:   */   
/* 80:   */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 81:   */   {
/* 82:94 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 83:   */   }
/* 84:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelMinotaur
 * JD-Core Version:    0.7.1
 */