/*  1:   */ package com.chocolate.chocolateQuest.client;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.model.ModelGiantBoxer;
/*  4:   */ import com.chocolate.chocolateQuest.entity.boss.EntityGiantBoxer;
/*  5:   */ import net.minecraft.client.model.ModelBiped;
/*  6:   */ import net.minecraft.client.renderer.entity.RenderBiped;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.entity.EntityLivingBase;
/*  9:   */ import net.minecraft.util.ResourceLocation;
/* 10:   */ import org.lwjgl.opengl.GL11;
/* 11:   */ 
/* 12:   */ public class RenderBossBiped
/* 13:   */   extends RenderBiped
/* 14:   */ {
/* 15:   */   public RenderBossBiped(ModelBiped par1ModelBiped, float par2)
/* 16:   */   {
/* 17:18 */     super(par1ModelBiped, par2);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public RenderBossBiped(ModelBiped par1ModelBiped, float par2, ResourceLocation texture)
/* 21:   */   {
/* 22:23 */     super(par1ModelBiped, par2);
/* 23:24 */     this.texture = texture;
/* 24:   */   }
/* 25:   */   
/* 26:   */   protected void func_82421_b()
/* 27:   */   {
/* 28:30 */     this.field_82423_g = new ModelGiantBoxer(1.0F);
/* 29:31 */     this.field_82425_h = new ModelGiantBoxer(0.5F);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
/* 33:   */   {
/* 34:38 */     super.doRender(entity, d, d1, d2, f, f1);
/* 35:   */   }
/* 36:   */   
/* 37:   */   protected void preRenderCallback(EntityLivingBase entity, float par2)
/* 38:   */   {
/* 39:44 */     EntityGiantBoxer e = (EntityGiantBoxer)entity;
/* 40:45 */     float scale = e.getScaleSize() * 0.76F;
/* 41:46 */     GL11.glScalef(scale, scale, scale);
/* 42:47 */     GL11.glTranslatef(0.0F, 0.0F, -0.1F);
/* 43:48 */     super.preRenderCallback(entity, par2);
/* 44:   */   }
/* 45:   */   
/* 46:52 */   ResourceLocation texture = new ResourceLocation("chocolatequest:textures/entity/mandril.png");
/* 47:   */   
/* 48:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 49:   */   {
/* 50:55 */     return this.texture;
/* 51:   */   }
/* 52:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.RenderBossBiped
 * JD-Core Version:    0.7.1
 */