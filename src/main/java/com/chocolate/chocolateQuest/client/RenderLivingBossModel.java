/*  1:   */ package com.chocolate.chocolateQuest.client;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.boss.EntityBaseBoss;
/*  4:   */ import net.minecraft.client.model.ModelBase;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.util.ResourceLocation;
/*  7:   */ import org.lwjgl.opengl.GL11;
/*  8:   */ 
/*  9:   */ public class RenderLivingBossModel
/* 10:   */   extends RenderLivingModel
/* 11:   */ {
/* 12:   */   public RenderLivingBossModel(ModelBase model, float f, ResourceLocation r)
/* 13:   */   {
/* 14:24 */     super(model, f, r);
/* 15:   */   }
/* 16:   */   
/* 17:   */   protected void preRenderCallback(EntityLivingBase entity, float par2)
/* 18:   */   {
/* 19:30 */     super.preRenderCallback(entity, par2);
/* 20:31 */     float scale = ((EntityBaseBoss)entity).getScaleSize();
/* 21:32 */     GL11.glScalef(scale, scale, scale);
/* 22:   */   }
/* 23:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.RenderLivingBossModel
 * JD-Core Version:    0.7.1
 */