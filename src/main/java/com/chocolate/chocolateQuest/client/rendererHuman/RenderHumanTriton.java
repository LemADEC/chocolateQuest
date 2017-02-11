/*  1:   */ package com.chocolate.chocolateQuest.client.rendererHuman;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.model.ModelNaga;
/*  4:   */ import net.minecraft.client.model.ModelBiped;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.util.ResourceLocation;
/*  7:   */ 
/*  8:   */ public class RenderHumanTriton
/*  9:   */   extends RenderHuman
/* 10:   */ {
/* 11:   */   public RenderHumanTriton(float f, ResourceLocation r)
/* 12:   */   {
/* 13:23 */     super(new ModelNaga(), f, r);
/* 14:24 */     this.featherY = -0.3F;
/* 15:   */   }
/* 16:   */   
/* 17:   */   protected void func_82421_b()
/* 18:   */   {
/* 19:30 */     this.field_82423_g = new ModelBiped(1.0F);
/* 20:31 */     this.field_82425_h = new ModelBiped(0.5F);
/* 21:   */   }
/* 22:   */   
/* 23:   */   protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
/* 24:   */   {
/* 25:37 */     return (par2 != 2) && (par2 != 3) ? super.shouldRenderPass(par1EntityLivingBase, par2, par3) : 0;
/* 26:   */   }
/* 27:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.rendererHuman.RenderHumanTriton
 * JD-Core Version:    0.7.1
 */