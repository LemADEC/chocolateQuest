/*  1:   */ package com.chocolate.chocolateQuest.client.rendererHuman;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.model.ModelOgre;
/*  4:   */ import net.minecraft.util.ResourceLocation;
/*  5:   */ 
/*  6:   */ public class RenderHumanOrc
/*  7:   */   extends RenderHuman
/*  8:   */ {
/*  9:   */   public RenderHumanOrc(float f, ResourceLocation r)
/* 10:   */   {
/* 11:21 */     super(new ModelOgre(), f, r);
/* 12:   */   }
/* 13:   */   
/* 14:   */   protected void func_82421_b()
/* 15:   */   {
/* 16:27 */     this.field_82423_g = new ModelOgre(1.0F);
/* 17:28 */     this.field_82425_h = new ModelOgre(0.5F);
/* 18:   */   }
/* 19:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.rendererHuman.RenderHumanOrc
 * JD-Core Version:    0.7.1
 */