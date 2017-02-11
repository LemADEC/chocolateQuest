/*  1:   */ package com.chocolate.chocolateQuest.client;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBiped;
/*  4:   */ import net.minecraft.client.renderer.entity.RenderBiped;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.util.ResourceLocation;
/*  7:   */ 
/*  8:   */ public class RenderBipedCQ
/*  9:   */   extends RenderBiped
/* 10:   */ {
/* 11:   */   protected ModelBiped model;
/* 12:11 */   private ResourceLocation texture = new ResourceLocation("chocolatequest:textures/entity/necromancer.png");
/* 13:   */   
/* 14:   */   public RenderBipedCQ(ResourceLocation r)
/* 15:   */   {
/* 16:15 */     super(new ModelBiped(0.0F), 0.5F);
/* 17:16 */     this.texture = r;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public RenderBipedCQ(ModelBiped model, float f, ResourceLocation r)
/* 21:   */   {
/* 22:21 */     super(model, f);
/* 23:22 */     this.texture = r;
/* 24:   */   }
/* 25:   */   
/* 26:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 27:   */   {
/* 28:28 */     return this.texture;
/* 29:   */   }
/* 30:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.RenderBipedCQ
 * JD-Core Version:    0.7.1
 */