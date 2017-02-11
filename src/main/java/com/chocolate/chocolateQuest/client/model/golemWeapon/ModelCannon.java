/*  1:   */ package com.chocolate.chocolateQuest.client.model.golemWeapon;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBase;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ 
/*  7:   */ public class ModelCannon
/*  8:   */   extends ModelBase
/*  9:   */ {
/* 10:   */   public ModelRenderer bipedGun;
/* 11:   */   public ModelRenderer cannon;
/* 12:   */   public ModelRenderer cannonMouth;
/* 13:   */   
/* 14:   */   public ModelCannon()
/* 15:   */   {
/* 16:14 */     float f = 0.0F;
/* 17:15 */     this.bipedGun = new ModelRenderer(this, 0, 0);
/* 18:16 */     this.bipedGun.addBox(0.0F, 0.0F, 0.0F, 4, 4, 6, f);
/* 19:   */     
/* 20:18 */     this.cannon = new ModelRenderer(this, 20, 0);
/* 21:19 */     this.cannon.addBox(0.0F, 0.0F, 6.0F, 4, 4, 6, f);
/* 22:   */     
/* 23:21 */     this.cannonMouth = new ModelRenderer(this, 16, 25);
/* 24:22 */     this.cannonMouth.addBox(1.0F, 1.0F, 7.0F, 2, 2, 5, f);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 28:   */   {
/* 29:28 */     this.bipedGun.render(f5);
/* 30:29 */     this.cannon.render(f5);
/* 31:30 */     this.cannonMouth.render(f5);
/* 32:   */   }
/* 33:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.golemWeapon.ModelCannon
 * JD-Core Version:    0.7.1
 */