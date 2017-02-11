/*  1:   */ package com.chocolate.chocolateQuest.client.model.golemWeapon;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBase;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ 
/*  7:   */ public class ModelFlameThrower
/*  8:   */   extends ModelBase
/*  9:   */ {
/* 10:   */   public ModelRenderer bipedGun;
/* 11:   */   public ModelRenderer rifle;
/* 12:   */   public ModelRenderer mouth;
/* 13:   */   public ModelRenderer spark;
/* 14:   */   
/* 15:   */   public ModelFlameThrower()
/* 16:   */   {
/* 17:15 */     float f = 0.0F;
/* 18:16 */     this.bipedGun = new ModelRenderer(this, 0, 10);
/* 19:17 */     this.bipedGun.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, f);
/* 20:   */     
/* 21:19 */     this.rifle = new ModelRenderer(this, 0, 18);
/* 22:20 */     this.rifle.addBox(0.5F, 0.2F, 4.0F, 3, 3, 3, f);
/* 23:   */     
/* 24:22 */     this.mouth = new ModelRenderer(this, 0, 0);
/* 25:23 */     this.mouth.addBox(1.5F, 1.2F, 7.0F, 1, 1, 2, f);
/* 26:   */     
/* 27:25 */     this.spark = new ModelRenderer(this, 14, 0);
/* 28:26 */     this.spark.addBox(1.5F, 0.0F, 0.0F, 1, 1, 3, -0.38F);
/* 29:27 */     this.spark.setRotationPoint(0.0F, 0.0F, 6.9F);
/* 30:28 */     this.spark.rotateAngleX = -0.2618F;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 34:   */   {
/* 35:34 */     this.bipedGun.render(f5);
/* 36:35 */     this.rifle.render(f5);
/* 37:36 */     this.mouth.render(f5);
/* 38:37 */     this.spark.render(f5);
/* 39:   */   }
/* 40:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.golemWeapon.ModelFlameThrower
 * JD-Core Version:    0.7.1
 */