/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBase;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.util.MathHelper;
/*  7:   */ 
/*  8:   */ public class ModelMage
/*  9:   */   extends ModelBase
/* 10:   */ {
/* 11:   */   public ModelRenderer field_40340_a;
/* 12:   */   public ModelRenderer field_40338_b;
/* 13:   */   public ModelRenderer field_40339_c;
/* 14:   */   public ModelRenderer field_40336_d;
/* 15:   */   public ModelRenderer field_40337_e;
/* 16:   */   public int field_40334_f;
/* 17:   */   public int field_40335_g;
/* 18:   */   public boolean field_40341_n;
/* 19:   */   public boolean field_40342_o;
/* 20:   */   
/* 21:   */   public ModelMage()
/* 22:   */   {
/* 23:29 */     this(0.0F);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public ModelMage(float f)
/* 27:   */   {
/* 28:34 */     this(f, 0.0F);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public ModelMage(float f, float f1)
/* 32:   */   {
/* 33:39 */     this.field_40334_f = 0;
/* 34:40 */     this.field_40335_g = 0;
/* 35:41 */     this.field_40341_n = false;
/* 36:42 */     this.field_40342_o = false;
/* 37:43 */     byte byte0 = 64;
/* 38:44 */     byte byte1 = 64;
/* 39:45 */     this.field_40340_a = new ModelRenderer(this).setTextureSize(byte0, byte1);
/* 40:46 */     this.field_40340_a.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/* 41:47 */     this.field_40340_a.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, f);
/* 42:48 */     this.field_40340_a.setTextureOffset(24, 0).addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2, f);
/* 43:49 */     this.field_40338_b = new ModelRenderer(this).setTextureSize(byte0, byte1);
/* 44:50 */     this.field_40338_b.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/* 45:51 */     this.field_40338_b.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, f);
/* 46:52 */     this.field_40338_b.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, f + 0.5F);
/* 47:53 */     this.field_40339_c = new ModelRenderer(this).setTextureSize(byte0, byte1);
/* 48:54 */     this.field_40339_c.setRotationPoint(0.0F, 0.0F + f1 + 2.0F, 0.0F);
/* 49:55 */     this.field_40339_c.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, f);
/* 50:56 */     this.field_40339_c.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, f);
/* 51:57 */     this.field_40339_c.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, f);
/* 52:58 */     this.field_40336_d = new ModelRenderer(this, 0, 22).setTextureSize(byte0, byte1);
/* 53:59 */     this.field_40336_d.setRotationPoint(-2.0F, 12.0F + f1, 0.0F);
/* 54:60 */     this.field_40336_d.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
/* 55:61 */     this.field_40337_e = new ModelRenderer(this, 0, 22).setTextureSize(byte0, byte1);
/* 56:62 */     this.field_40337_e.mirror = true;
/* 57:63 */     this.field_40337_e.setRotationPoint(2.0F, 12.0F + f1, 0.0F);
/* 58:64 */     this.field_40337_e.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
/* 59:   */   }
/* 60:   */   
/* 61:   */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 62:   */   {
/* 63:69 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 64:70 */     this.field_40340_a.render(f5);
/* 65:71 */     this.field_40338_b.render(f5);
/* 66:72 */     this.field_40336_d.render(f5);
/* 67:73 */     this.field_40337_e.render(f5);
/* 68:74 */     this.field_40339_c.render(f5);
/* 69:   */   }
/* 70:   */   
/* 71:   */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
/* 72:   */   {
/* 73:79 */     this.field_40340_a.rotateAngleY = (f3 / 57.295776F);
/* 74:80 */     this.field_40340_a.rotateAngleX = (f4 / 57.295776F);
/* 75:81 */     this.field_40339_c.rotationPointY = 3.0F;
/* 76:82 */     this.field_40339_c.rotationPointZ = -1.0F;
/* 77:83 */     this.field_40339_c.rotateAngleX = -0.75F;
/* 78:84 */     this.field_40336_d.rotateAngleX = (MathHelper.cos(f * 0.6662F) * 1.4F * f1 * 0.5F);
/* 79:85 */     this.field_40337_e.rotateAngleX = (MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1 * 0.5F);
/* 80:86 */     this.field_40336_d.rotateAngleY = 0.0F;
/* 81:87 */     this.field_40337_e.rotateAngleY = 0.0F;
/* 82:   */   }
/* 83:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelMage
 * JD-Core Version:    0.7.1
 */