/*  1:   */ package com.chocolate.chocolateQuest.client.model.golemWeapon;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBase;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ 
/*  7:   */ public class ModelGun
/*  8:   */   extends ModelBase
/*  9:   */ {
/* 10:   */   public ModelRenderer bipedGun;
/* 11:   */   public ModelRenderer cannon;
/* 12:   */   
/* 13:   */   public ModelGun()
/* 14:   */   {
/* 15:13 */     float f = 0.0F;
/* 16:14 */     this.bipedGun = new ModelRenderer(this, 0, 0);
/* 17:15 */     this.bipedGun.addBox(0.0F, 0.0F, 0.0F, 4, 4, 6, f);
/* 18:   */     
/* 19:17 */     this.cannon = new ModelRenderer(this, 12, 21);
/* 20:18 */     this.cannon.addBox(0.5F, 0.5F, 5.5F, 3, 3, 1, f);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 24:   */   {
/* 25:24 */     this.bipedGun.render(f5);
/* 26:25 */     this.cannon.render(f5);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void render(float f5)
/* 30:   */   {
/* 31:30 */     this.bipedGun.render(0.0625F);
/* 32:   */   }
/* 33:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.golemWeapon.ModelGun
 * JD-Core Version:    0.7.1
 */