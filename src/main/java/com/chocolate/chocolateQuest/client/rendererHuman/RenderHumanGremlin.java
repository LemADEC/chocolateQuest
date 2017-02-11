/*  1:   */ package com.chocolate.chocolateQuest.client.rendererHuman;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.model.ModelGremlin;
/*  4:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.util.ResourceLocation;
/*  7:   */ import org.lwjgl.opengl.GL11;
/*  8:   */ 
/*  9:   */ public class RenderHumanGremlin
/* 10:   */   extends RenderHuman
/* 11:   */ {
/* 12:   */   public RenderHumanGremlin(float f, ResourceLocation r)
/* 13:   */   {
/* 14:25 */     super(new ModelGremlin(), f, r);
/* 15:26 */     this.featherY = -0.3F;
/* 16:   */   }
/* 17:   */   
/* 18:   */   protected void func_82421_b()
/* 19:   */   {
/* 20:32 */     this.field_82423_g = new ModelGremlin(1.0F);
/* 21:33 */     this.field_82425_h = new ModelGremlin(0.5F);
/* 22:   */   }
/* 23:   */   
/* 24:   */   protected void preRenderCallback(EntityLivingBase entityliving, float f)
/* 25:   */   {
/* 26:39 */     super.preRenderCallback(entityliving, f);
/* 27:40 */     GL11.glTranslatef(0.0F, 0.7F, 0.0F);
/* 28:   */   }
/* 29:   */   
/* 30:   */   protected void renderCape(EntityHumanBase e)
/* 31:   */   {
/* 32:46 */     GL11.glPushMatrix();
/* 33:47 */     GL11.glTranslatef(0.0F, -0.07F, -0.1F);
/* 34:48 */     super.renderCape(e);
/* 35:49 */     GL11.glPopMatrix();
/* 36:   */   }
/* 37:   */   
/* 38:   */   protected void setSitOffset()
/* 39:   */   {
/* 40:54 */     GL11.glTranslatef(0.0F, 0.1F, 0.0F);
/* 41:   */   }
/* 42:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.rendererHuman.RenderHumanGremlin
 * JD-Core Version:    0.7.1
 */