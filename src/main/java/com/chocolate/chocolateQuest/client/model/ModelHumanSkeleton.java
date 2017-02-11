/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelRenderer;
/*  4:   */ 
/*  5:   */ public class ModelHumanSkeleton
/*  6:   */   extends ModelHuman
/*  7:   */ {
/*  8:   */   public ModelHumanSkeleton()
/*  9:   */   {
/* 10:17 */     this(0.0F);
/* 11:   */   }
/* 12:   */   
/* 13:   */   public ModelHumanSkeleton(float f)
/* 14:   */   {
/* 15:22 */     this(f, 0.0F);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public ModelHumanSkeleton(float f, float f1)
/* 19:   */   {
/* 20:27 */     this.heldItemLeft = 0;
/* 21:28 */     this.heldItemRight = 0;
/* 22:29 */     this.isSneak = false;
/* 23:30 */     this.aimedBow = false;
/* 24:31 */     this.bipedCloak = new ModelRenderer(this, 0, 0);
/* 25:32 */     this.bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, f);
/* 26:33 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/* 27:34 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f);
/* 28:35 */     this.bipedHead.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/* 29:36 */     this.bipedHeadwear = new ModelRenderer(this, 32, 0);
/* 30:37 */     this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f + 0.5F);
/* 31:38 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/* 32:39 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/* 33:40 */     this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, f);
/* 34:41 */     this.bipedBody.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
/* 35:42 */     this.bipedRightArm = new ModelRenderer(this, 40, 16);
/* 36:43 */     this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, f);
/* 37:44 */     this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/* 38:45 */     this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/* 39:46 */     this.bipedLeftArm.mirror = true;
/* 40:47 */     this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, f);
/* 41:48 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/* 42:49 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/* 43:50 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, f);
/* 44:51 */     this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
/* 45:52 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/* 46:53 */     this.bipedLeftLeg.mirror = true;
/* 47:54 */     this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, f);
/* 48:55 */     this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
/* 49:   */   }
/* 50:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelHumanSkeleton
 * JD-Core Version:    0.7.1
 */