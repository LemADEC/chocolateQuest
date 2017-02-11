/*  1:   */ package com.chocolate.chocolateQuest.client;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBase;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.util.ResourceLocation;
/*  6:   */ import org.lwjgl.opengl.GL11;
/*  7:   */ 
/*  8:   */ public class RenderLivingModelFly
/*  9:   */   extends RenderLivingBossModel
/* 10:   */ {
/* 11:   */   public RenderLivingModelFly(ModelBase model, float f, ResourceLocation r)
/* 12:   */   {
/* 13:22 */     super(model, f, r);
/* 14:   */   }
/* 15:   */   
/* 16:   */   protected void preRenderCallback(EntityLivingBase entity, float par2)
/* 17:   */   {
/* 18:28 */     GL11.glRotatef(entity.rotationPitch, 1.0F, 0.0F, 0.0F);
/* 19:29 */     super.preRenderCallback(entity, par2);
/* 20:   */   }
/* 21:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.RenderLivingModelFly
 * JD-Core Version:    0.7.1
 */