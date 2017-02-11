/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelRenderer;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ 
/*  6:   */ public class ModelHumanCustomArmor
/*  7:   */   extends ModelHuman
/*  8:   */ {
/*  9:   */   public ModelHumanCustomArmor(float f)
/* 10:   */   {
/* 11:17 */     super(f);
/* 12:   */   }
/* 13:   */   
/* 14:   */   public void render(Entity z, float f, float f1, float f2, float f3, float f4, float f5)
/* 15:   */   {
/* 16:23 */     setRotationAngles(f, f1, f2, f3, f4, f5, z);
/* 17:24 */     this.bipedHead.render(f5);
/* 18:25 */     this.bipedBody.render(f5);
/* 19:26 */     this.bipedRightArm.render(f5);
/* 20:27 */     this.bipedLeftArm.render(f5);
/* 21:28 */     this.bipedHeadwear.render(f5);
/* 22:   */   }
/* 23:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelHumanCustomArmor
 * JD-Core Version:    0.7.1
 */