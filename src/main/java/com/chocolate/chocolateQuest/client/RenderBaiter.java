/*  1:   */ package com.chocolate.chocolateQuest.client;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityBaiter;
/*  4:   */ import net.minecraft.client.entity.AbstractClientPlayer;
/*  5:   */ import net.minecraft.client.model.ModelBiped;
/*  6:   */ import net.minecraft.client.renderer.entity.RenderBiped;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.util.ResourceLocation;
/*  9:   */ 
/* 10:   */ public class RenderBaiter
/* 11:   */   extends RenderBiped
/* 12:   */ {
/* 13:   */   public RenderBaiter(ModelBiped par1ModelBiped, float par2)
/* 14:   */   {
/* 15:16 */     super(par1ModelBiped, par2);
/* 16:   */   }
/* 17:   */   
/* 18:19 */   ResourceLocation texture = new ResourceLocation("chocolatequest:textures/entity/pirateBoss.png");
/* 19:   */   
/* 20:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 21:   */   {
/* 22:23 */     EntityBaiter e = (EntityBaiter)entity;
/* 23:24 */     if ((e.getThrower() instanceof AbstractClientPlayer))
/* 24:   */     {
/* 25:26 */       AbstractClientPlayer player = (AbstractClientPlayer)e.getThrower();
/* 26:27 */       return player.getLocationSkin();
/* 27:   */     }
/* 28:29 */     return this.texture;
/* 29:   */   }
/* 30:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.RenderBaiter
 * JD-Core Version:    0.7.1
 */