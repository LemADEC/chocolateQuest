/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelRenderer;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ 
/*  6:   */ public class ModelGolemSmall
/*  7:   */   extends ModelHuman
/*  8:   */ {
/*  9:   */   ModelRenderer mouth;
/* 10:   */   public ModelRenderer bipedBody2;
/* 11:   */   
/* 12:   */   public ModelGolemSmall()
/* 13:   */   {
/* 14:23 */     this(0.0F);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public ModelGolemSmall(float f)
/* 18:   */   {
/* 19:29 */     super(f);
/* 20:30 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/* 21:31 */     this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 6, 4, f);
/* 22:32 */     this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 23:   */     
/* 24:34 */     this.bipedBody2 = new ModelRenderer(this, 16, 22);
/* 25:35 */     this.bipedBody2.addBox(-3.0F, 6.0F, -2.0F, 6, 6, 4, f);
/* 26:36 */     this.bipedBody2.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 27:37 */     this.bipedBody.addChild(this.bipedBody2);
/* 28:   */     
/* 29:   */ 
/* 30:   */ 
/* 31:41 */     this.mouth = new ModelRenderer(this, 25, 0);
/* 32:42 */     this.mouth.addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2);
/* 33:43 */     this.bipedHead.addChild(this.mouth);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/* 37:   */   {
/* 38:50 */     this.bipedRightLeg.setRotationPoint(-3.0F, 0.0F, 0.0F);
/* 39:51 */     this.bipedLeftLeg.setRotationPoint(3.0F, 0.0F, 0.0F);
/* 40:52 */     super.render(par1Entity, par2, par3, par4, par5, par6, par7);
/* 41:   */   }
/* 42:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelGolemSmall
 * JD-Core Version:    0.7.1
 */