/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelRenderer;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ 
/*  6:   */ public class ModelOgre
/*  7:   */   extends ModelHuman
/*  8:   */ {
/*  9:   */   public ModelRenderer bipedBody2;
/* 10:   */   
/* 11:   */   public ModelOgre()
/* 12:   */   {
/* 13:21 */     this(0.0F);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public ModelOgre(float f)
/* 17:   */   {
/* 18:27 */     super(f);
/* 19:28 */     this.bipedBody2 = new ModelRenderer(this, 16, 22);
/* 20:29 */     this.bipedBody2.addBox(-4.0F, 0.0F, -2.0F, 8, 6, 4, 0.0F);
/* 21:30 */     this.bipedBody2.setRotationPoint(0.0F, 6.0F, -3.0F);
/* 22:31 */     this.bipedBody.addChild(this.bipedBody2);
/* 23:   */     
/* 24:33 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/* 25:34 */     this.bipedHead.addBox(-4.0F, -5.0F, -7.0F, 8, 8, 8, f);
/* 26:35 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 27:36 */     this.bipedHeadwear = new ModelRenderer(this, 32, 0);
/* 28:37 */     this.bipedHeadwear.addBox(-4.0F, -5.0F, -7.0F, 8, 8, 8, f + 0.5F);
/* 29:38 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/* 33:   */   {
/* 34:44 */     this.bipedRightLeg.setRotationPoint(-2.5F, 0.0F, 0.0F);
/* 35:45 */     this.bipedLeftLeg.setRotationPoint(2.5F, 0.0F, 0.0F);
/* 36:46 */     super.render(par1Entity, par2, par3, par4, par5, par6, par7);
/* 37:   */   }
/* 38:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelOgre
 * JD-Core Version:    0.7.1
 */