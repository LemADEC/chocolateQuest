/*  1:   */ package com.chocolate.chocolateQuest.client.model.golemWeapon;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBase;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ 
/*  7:   */ public class ModelBubbleCannon
/*  8:   */   extends ModelBase
/*  9:   */ {
/* 10:   */   public ModelRenderer bipedGun;
/* 11:   */   public ModelRenderer rifle;
/* 12:   */   
/* 13:   */   public ModelBubbleCannon()
/* 14:   */   {
/* 15:13 */     float f = 0.0F;
/* 16:14 */     this.bipedGun = new ModelRenderer(this, 0, 0);
/* 17:15 */     this.bipedGun.addBox(0.0F, 0.0F, 0.0F, 4, 4, 6, f);
/* 18:   */     
/* 19:17 */     this.rifle = new ModelRenderer(this, 25, 10);
/* 20:18 */     this.rifle.addBox(0.5F, 0.5F, 6.0F, 3, 3, 6, f);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 24:   */   {
/* 25:25 */     this.bipedGun.render(f5);
/* 26:26 */     this.rifle.render(f5);
/* 27:   */   }
/* 28:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.golemWeapon.ModelBubbleCannon
 * JD-Core Version:    0.7.1
 */