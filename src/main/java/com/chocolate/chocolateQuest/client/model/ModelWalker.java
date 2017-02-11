/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelRenderer;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import org.lwjgl.opengl.GL11;
/*  6:   */ 
/*  7:   */ public class ModelWalker
/*  8:   */   extends ModelHuman
/*  9:   */ {
/* 10:   */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/* 11:   */   {
/* 12:20 */     setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
/* 13:22 */     if (this.isChild)
/* 14:   */     {
/* 15:24 */       float var8 = 2.0F;
/* 16:25 */       GL11.glPushMatrix();
/* 17:26 */       GL11.glScalef(1.5F / var8, 1.5F / var8, 1.5F / var8);
/* 18:27 */       GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
/* 19:28 */       this.bipedHead.render(par7);
/* 20:29 */       GL11.glPopMatrix();
/* 21:30 */       GL11.glPushMatrix();
/* 22:31 */       GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
/* 23:32 */       GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
/* 24:33 */       this.bipedBody.render(par7);
/* 25:34 */       this.bipedRightArm.render(par7);
/* 26:35 */       this.bipedLeftArm.render(par7);
/* 27:36 */       this.bipedRightLeg.render(par7);
/* 28:37 */       this.bipedLeftLeg.render(par7);
/* 29:38 */       this.bipedHeadwear.render(par7);
/* 30:39 */       GL11.glPopMatrix();
/* 31:   */     }
/* 32:   */     else
/* 33:   */     {
/* 34:43 */       this.bipedHead.render(par7);
/* 35:44 */       this.bipedBody.render(par7);
/* 36:45 */       this.bipedRightArm.render(par7);
/* 37:46 */       this.bipedLeftArm.render(par7);
/* 38:47 */       this.bipedRightLeg.render(par7);
/* 39:48 */       this.bipedLeftLeg.render(par7);
/* 40:   */       
/* 41:   */ 
/* 42:   */ 
/* 43:   */ 
/* 44:53 */       this.bipedHeadwear.render(par7);
/* 45:   */     }
/* 46:   */   }
/* 47:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelWalker
 * JD-Core Version:    0.7.1
 */