/*  1:   */ package com.chocolate.chocolateQuest.client.rendererHuman;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.model.ModelHumanCustomArmor;
/*  4:   */ import net.minecraft.client.model.ModelBiped;
/*  5:   */ import net.minecraft.util.ResourceLocation;
/*  6:   */ 
/*  7:   */ public class RenderHumanCustomArmor
/*  8:   */   extends RenderHuman
/*  9:   */ {
/* 10:   */   public RenderHumanCustomArmor(ModelBiped modelbase, float f, ResourceLocation r)
/* 11:   */   {
/* 12:22 */     super(modelbase, f, r);
/* 13:   */   }
/* 14:   */   
/* 15:   */   protected void func_82421_b()
/* 16:   */   {
/* 17:28 */     this.field_82423_g = new ModelHumanCustomArmor(1.0F);
/* 18:29 */     this.field_82425_h = new ModelHumanCustomArmor(0.5F);
/* 19:   */   }
/* 20:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.rendererHuman.RenderHumanCustomArmor
 * JD-Core Version:    0.7.1
 */