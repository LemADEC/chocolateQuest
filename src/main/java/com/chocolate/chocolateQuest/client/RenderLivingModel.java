/*  1:   */ package com.chocolate.chocolateQuest.client;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.model.ModelMage;
/*  4:   */ import net.minecraft.client.model.ModelBase;
/*  5:   */ import net.minecraft.client.renderer.entity.RenderLiving;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.util.ResourceLocation;
/*  8:   */ 
/*  9:   */ public class RenderLivingModel
/* 10:   */   extends RenderLiving
/* 11:   */ {
/* 12:   */   protected ModelBase model;
/* 13:22 */   private ResourceLocation texture = new ResourceLocation("chocolatequest:textures/entity/necromancer.png");
/* 14:   */   
/* 15:   */   public RenderLivingModel(ResourceLocation r)
/* 16:   */   {
/* 17:26 */     super(new ModelMage(0.0F), 0.5F);
/* 18:27 */     this.texture = r;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public RenderLivingModel(ModelBase model, float f, ResourceLocation r)
/* 22:   */   {
/* 23:32 */     super(model, f);
/* 24:33 */     this.texture = r;
/* 25:   */   }
/* 26:   */   
/* 27:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 28:   */   {
/* 29:39 */     return this.texture;
/* 30:   */   }
/* 31:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.RenderLivingModel
 * JD-Core Version:    0.7.1
 */