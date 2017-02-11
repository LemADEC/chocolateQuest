/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelRenderer;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ 
/*  6:   */ public class ModelArmorHeavyPlate
/*  7:   */   extends ModelArmor
/*  8:   */ {
/*  9:   */   public ModelArmorHeavyPlate()
/* 10:   */   {
/* 11:18 */     this(0.0F);
/* 12:   */   }
/* 13:   */   
/* 14:22 */   boolean renderHead = false;
/* 15:   */   
/* 16:   */   public ModelArmorHeavyPlate(float f, boolean head)
/* 17:   */   {
/* 18:25 */     this(f);
/* 19:26 */     this.renderHead = head;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public ModelArmorHeavyPlate(float f)
/* 23:   */   {
/* 24:31 */     super(f, 1);
/* 25:32 */     float scale = 1.5F;
/* 26:33 */     this.bipedRightArm = new ModelRenderer(this, 40, 16);
/* 27:34 */     this.bipedRightArm.addBox(-4.0F, -2.0F, -2.0F, 5, 10, 5, scale);
/* 28:35 */     this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/* 29:36 */     this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/* 30:37 */     this.bipedLeftArm.mirror = true;
/* 31:38 */     this.bipedLeftArm.addBox(-0.0F, -2.0F, -2.0F, 5, 10, 5, scale);
/* 32:39 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/* 33:   */     
/* 34:   */ 
/* 35:42 */     this.bipedHeadwear = new ModelRenderer(this, 32, 0);
/* 36:43 */     this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 1.1F);
/* 37:44 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/* 41:   */   {
/* 42:48 */     setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
/* 43:49 */     if (this.renderHead)
/* 44:   */     {
/* 45:51 */       this.bipedHeadwear.setRotationPoint(0.0F, -1.5F, 0.0F);
/* 46:52 */       this.bipedHeadwear.render(par7);
/* 47:   */     }
/* 48:55 */     this.bipedBody.render(par7);
/* 49:56 */     this.bipedRightArm.render(par7);
/* 50:57 */     this.bipedLeftArm.render(par7);
/* 51:   */   }
/* 52:   */   
/* 53:   */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 54:   */   {
/* 55:63 */     model.rotateAngleX = x;
/* 56:64 */     model.rotateAngleY = y;
/* 57:65 */     model.rotateAngleZ = z;
/* 58:   */   }
/* 59:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelArmorHeavyPlate
 * JD-Core Version:    0.7.1
 */