/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ import org.lwjgl.opengl.GL11;
/*  5:   */ 
/*  6:   */ public class ModelElemental
/*  7:   */   extends ModelHuman
/*  8:   */ {
/*  9:   */   public ModelElemental()
/* 10:   */   {
/* 11:20 */     this(0.0F);
/* 12:   */   }
/* 13:   */   
/* 14:   */   public ModelElemental(float f)
/* 15:   */   {
/* 16:26 */     super(f);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/* 20:   */   {
/* 21:33 */     GL11.glDisable(2896);
/* 22:34 */     GL11.glMatrixMode(5890);
/* 23:35 */     GL11.glPushMatrix();
/* 24:36 */     float desp = (float)System.nanoTime() * 1.0E-010F;
/* 25:37 */     GL11.glTranslatef(desp, desp, 0.0F);
/* 26:38 */     GL11.glMatrixMode(5888);
/* 27:   */     
/* 28:40 */     super.render(par1Entity, par2, par3, par4, par5, par6, par7);
/* 29:   */     
/* 30:42 */     GL11.glMatrixMode(5890);
/* 31:43 */     GL11.glPopMatrix();
/* 32:44 */     GL11.glMatrixMode(5888);
/* 33:45 */     GL11.glEnable(2896);
/* 34:   */   }
/* 35:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelElemental
 * JD-Core Version:    0.7.1
 */