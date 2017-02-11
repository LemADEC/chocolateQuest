/*  1:   */ package com.chocolate.chocolateQuest.client;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBase;
/*  4:   */ import net.minecraft.client.renderer.entity.Render;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.util.ResourceLocation;
/*  7:   */ import org.lwjgl.opengl.GL11;
/*  8:   */ 
/*  9:   */ public class RenderGeneric
/* 10:   */   extends Render
/* 11:   */ {
/* 12:   */   protected ModelBase model;
/* 13:21 */   private ResourceLocation texture = new ResourceLocation("chocolatequest:textures/entity/dragonbd.png");
/* 14:   */   
/* 15:   */   public RenderGeneric(ModelBase par1ModelBase, float par2)
/* 16:   */   {
/* 17:26 */     this.model = par1ModelBase;
/* 18:27 */     this.shadowSize = par2;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public RenderGeneric(ModelBase par1ModelBase, float par2, ResourceLocation texture)
/* 22:   */   {
/* 23:31 */     this(par1ModelBase, par2);
/* 24:32 */     this.texture = texture;
/* 25:   */   }
/* 26:   */   
/* 27:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 28:   */   {
/* 29:37 */     return this.texture;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1)
/* 33:   */   {
/* 34:42 */     GL11.glPushMatrix();
/* 35:43 */     GL11.glTranslatef((float)d0, (float)d1, (float)d2);
/* 36:44 */     GL11.glRotatef(-180.0F - f, 0.0F, 1.0F, 0.0F);
/* 37:   */     
/* 38:   */ 
/* 39:47 */     bindTexture(this.texture);
/* 40:48 */     GL11.glScalef(-1.0F, -1.0F, 1.0F);
/* 41:49 */     this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/* 42:50 */     GL11.glPopMatrix();
/* 43:   */   }
/* 44:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.RenderGeneric
 * JD-Core Version:    0.7.1
 */