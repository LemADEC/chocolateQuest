/*  1:   */ package com.chocolate.chocolateQuest.client;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBiped;
/*  4:   */ import net.minecraft.client.renderer.entity.RenderBiped;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ import net.minecraft.util.ResourceLocation;
/*  8:   */ import org.lwjgl.opengl.GL11;
/*  9:   */ 
/* 10:   */ public class RenderSummonedUndead
/* 11:   */   extends RenderBiped
/* 12:   */ {
/* 13:22 */   public static final ResourceLocation skeletonTextures = new ResourceLocation("textures/entity/skeleton/skeleton.png");
/* 14:   */   
/* 15:   */   public RenderSummonedUndead(ModelBiped model, float f)
/* 16:   */   {
/* 17:25 */     super(model, f);
/* 18:   */   }
/* 19:   */   
/* 20:   */   protected ResourceLocation getEntityTexture(Entity par1Entity)
/* 21:   */   {
/* 22:30 */     return skeletonTextures;
/* 23:   */   }
/* 24:   */   
/* 25:   */   protected void preRenderCallback(EntityLivingBase entityliving, float f)
/* 26:   */   {
/* 27:36 */     super.preRenderCallback(entityliving, f);
/* 28:   */   }
/* 29:   */   
/* 30:   */   protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
/* 31:   */   {
/* 32:41 */     GL11.glPushMatrix();
/* 33:42 */     GL11.glTranslatef(0.0F, -0.3F, 0.6F);
/* 34:43 */     GL11.glScalef(2.0F, 2.0F, 2.0F);
/* 35:44 */     GL11.glRotatef(40.0F, 1.3F, -1.8F, 1.4F);
/* 36:45 */     super.renderEquippedItems(par1EntityLivingBase, par2);
/* 37:46 */     GL11.glPopMatrix();
/* 38:   */   }
/* 39:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.RenderSummonedUndead
 * JD-Core Version:    0.7.1
 */