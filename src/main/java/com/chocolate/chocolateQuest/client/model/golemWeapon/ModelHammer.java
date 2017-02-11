/*  1:   */ package com.chocolate.chocolateQuest.client.model.golemWeapon;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBase;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ 
/*  7:   */ public class ModelHammer
/*  8:   */   extends ModelBase
/*  9:   */ {
/* 10:   */   public ModelRenderer bipedGun;
/* 11:   */   public ModelRenderer cannon;
/* 12:   */   public ModelRenderer cannonMouth;
/* 13:   */   public ModelRenderer cannonMouth1;
/* 14:   */   
/* 15:   */   public ModelHammer()
/* 16:   */   {
/* 17:15 */     float f = 0.0F;
/* 18:16 */     this.bipedGun = new ModelRenderer(this, 0, 0);
/* 19:17 */     this.bipedGun.addBox(0.0F, 0.0F, 0.0F, 4, 4, 6, f);
/* 20:   */     
/* 21:19 */     this.cannon = new ModelRenderer(this, 0, 0);
/* 22:20 */     this.cannon.addBox(0.0F, 0.0F, 6.0F, 4, 4, 6, f);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 26:   */   {
/* 27:27 */     this.bipedGun.render(f5);
/* 28:28 */     this.cannon.render(f5);
/* 29:   */   }
/* 30:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.golemWeapon.ModelHammer
 * JD-Core Version:    0.7.1
 */