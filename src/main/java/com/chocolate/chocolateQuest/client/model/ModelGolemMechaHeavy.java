/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelRenderer;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ 
/*  6:   */ public class ModelGolemMechaHeavy
/*  7:   */   extends ModelGolemMecha
/*  8:   */ {
/*  9:   */   public ModelGolemMechaHeavy()
/* 10:   */   {
/* 11:19 */     this(0.0F);
/* 12:   */   }
/* 13:   */   
/* 14:   */   public ModelGolemMechaHeavy(float f)
/* 15:   */   {
/* 16:25 */     super(f);
/* 17:26 */     this.textureHeight = 64;
/* 18:27 */     this.textureWidth = 64;
/* 19:   */     
/* 20:29 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/* 21:30 */     this.bipedHead.addBox(-9.0F, -7.0F, -7.0F, 18, 17, 14, f);
/* 22:31 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 23:   */     
/* 24:33 */     this.bipedBody = new ModelRenderer(this, 0, 38);
/* 25:34 */     this.bipedBody.addBox(-4.0F, 10.01F, -2.0F, 8, 6, 4, f);
/* 26:35 */     this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 27:   */     
/* 28:   */ 
/* 29:38 */     this.bipedHeadwear = new ModelRenderer(this, 0, 0);
/* 30:39 */     this.bipedHeadwear.addBox(-3.0F, 5.0F, 7.0F, 6, 2, 2, f);
/* 31:40 */     this.bipedHeadwear.setRotationPoint(0.0F, 2.0F, 0.0F);
/* 32:   */     
/* 33:42 */     this.bipedRightArm = new ModelRenderer(this, 24, 42);
/* 34:43 */     this.bipedRightArm.addBox(-6.0F, -2.0F, -2.0F, 4, 12, 4, f);
/* 35:44 */     this.bipedRightArm.setRotationPoint(-8.0F, 2.0F, 0.0F);
/* 36:45 */     this.bipedLeftArm = new ModelRenderer(this, 24, 42);
/* 37:46 */     this.bipedLeftArm.addBox(2.0F, -2.0F, -2.0F, 4, 12, 4, f);
/* 38:47 */     this.bipedLeftArm.setRotationPoint(8.0F, 2.0F, 0.0F);
/* 39:48 */     this.bipedLeftArm.mirror = true;
/* 40:   */     
/* 41:50 */     this.bipedRightLeg = new ModelRenderer(this, 0, 50);
/* 42:51 */     this.bipedRightLeg.addBox(-4.0F, 2.0F, -2.0F, 4, 10, 4, f);
/* 43:52 */     this.bipedRightLeg.setRotationPoint(-4.0F, 12.0F + f, 0.0F);
/* 44:53 */     this.bipedRightLeg.setTextureSize(64, 64);
/* 45:54 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 50);
/* 46:55 */     this.bipedLeftLeg.mirror = true;
/* 47:56 */     this.bipedLeftLeg.addBox(0.0F, 2.0F, -2.0F, 4, 10, 4, f);
/* 48:57 */     this.bipedLeftLeg.setRotationPoint(4.0F, 12.0F + f, 0.0F);
/* 49:58 */     this.bipedLeftLeg.setTextureSize(64, 64);
/* 50:   */     
/* 51:60 */     this.antenna = new ModelRenderer(this, 0, 4);
/* 52:61 */     this.antenna.addBox(-1.0F, -3.0F, 0.0F, 3, 3, 1, f);
/* 53:62 */     this.antenna.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 54:   */     
/* 55:64 */     this.antennaStick = new ModelRenderer(this, 36, 0);
/* 56:65 */     this.antennaStick.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, f);
/* 57:66 */     this.antennaStick.setRotationPoint(8.0F, -8.0F, 6.0F);
/* 58:67 */     this.antennaStick.setTextureSize(64, 64);
/* 59:68 */     this.antennaStick.addChild(this.antenna);
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 63:   */   {
/* 64:73 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 65:74 */     this.bipedRightArm.setRotationPoint(-7.0F, 5.0F, 0.0F);
/* 66:75 */     this.bipedLeftArm.setRotationPoint(7.0F, 5.0F, 0.0F);
/* 67:   */   }
/* 68:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelGolemMechaHeavy
 * JD-Core Version:    0.7.1
 */