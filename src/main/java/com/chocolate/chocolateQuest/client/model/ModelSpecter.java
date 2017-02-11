/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelRenderer;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import org.lwjgl.opengl.GL11;
/*  6:   */ 
/*  7:   */ public class ModelSpecter
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
/* 34:43 */       if ((this.onGround == 0.0F) || (drawAsGosht()))
/* 35:   */       {
/* 36:45 */         GL11.glEnable(3042);
/* 37:46 */         GL11.glBlendFunc(1, 1);
/* 38:   */       }
/* 39:48 */       this.bipedHead.render(par7);
/* 40:49 */       this.bipedBody.render(par7);
/* 41:50 */       this.bipedRightArm.render(par7);
/* 42:51 */       this.bipedLeftArm.render(par7);
/* 43:52 */       this.bipedRightLeg.render(par7);
/* 44:53 */       this.bipedLeftLeg.render(par7);
/* 45:54 */       GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 46:55 */       this.bipedHeadwear.render(par7);
/* 47:56 */       GL11.glDisable(3042);
/* 48:57 */       GL11.glEnable(2896);
/* 49:   */     }
/* 50:   */   }
/* 51:   */   
/* 52:   */   public boolean drawAsGosht()
/* 53:   */   {
/* 54:62 */     return true;
/* 55:   */   }
/* 56:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelSpecter
 * JD-Core Version:    0.7.1
 */