/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBiped;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.util.MathHelper;
/*  7:   */ 
/*  8:   */ public class ModelSkeletonSummoned
/*  9:   */   extends ModelBiped
/* 10:   */ {
/* 11:   */   public ModelSkeletonSummoned()
/* 12:   */   {
/* 13:20 */     this(0.0F);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public ModelSkeletonSummoned(float f)
/* 17:   */   {
/* 18:25 */     this(f, 0.0F);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public ModelSkeletonSummoned(float f, float f1)
/* 22:   */   {
/* 23:30 */     this.heldItemLeft = 0;
/* 24:31 */     this.heldItemRight = 0;
/* 25:32 */     this.isSneak = false;
/* 26:33 */     this.aimedBow = false;
/* 27:34 */     this.bipedCloak = new ModelRenderer(this, 0, 0);
/* 28:35 */     this.bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, f);
/* 29:36 */     this.bipedEars = new ModelRenderer(this, 24, 0);
/* 30:37 */     this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, f);
/* 31:38 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/* 32:39 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f);
/* 33:40 */     this.bipedHead.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/* 34:41 */     this.bipedHeadwear = new ModelRenderer(this, 32, 0);
/* 35:42 */     this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f + 0.5F);
/* 36:43 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/* 37:44 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/* 38:45 */     this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, f);
/* 39:46 */     this.bipedBody.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/* 40:47 */     this.bipedRightArm = new ModelRenderer(this, 40, 16);
/* 41:48 */     this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, f);
/* 42:49 */     this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/* 43:50 */     this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/* 44:51 */     this.bipedLeftArm.mirror = true;
/* 45:52 */     this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, f);
/* 46:53 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/* 47:54 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/* 48:55 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, f);
/* 49:56 */     this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
/* 50:57 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/* 51:58 */     this.bipedLeftLeg.mirror = true;
/* 52:59 */     this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, f);
/* 53:60 */     this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
/* 57:   */   {
/* 58:70 */     super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
/* 59:71 */     float f6 = MathHelper.sin(this.onGround * 3.141593F);
/* 60:72 */     float f7 = MathHelper.sin((1.0F - (1.0F - this.onGround) * (1.0F - this.onGround)) * 3.141593F);
/* 61:73 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 62:74 */     this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 63:75 */     this.bipedRightArm.rotateAngleY = (-(0.1F - f6 * 0.6F));
/* 64:76 */     this.bipedLeftArm.rotateAngleY = (0.1F - f6 * 0.6F);
/* 65:77 */     this.bipedRightArm.rotateAngleX = -1.570796F;
/* 66:78 */     this.bipedLeftArm.rotateAngleX = -1.570796F;
/* 67:79 */     this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
/* 68:80 */     this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
/* 69:81 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
/* 70:82 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
/* 71:83 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
/* 72:84 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
/* 73:   */   }
/* 74:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelSkeletonSummoned
 * JD-Core Version:    0.7.1
 */