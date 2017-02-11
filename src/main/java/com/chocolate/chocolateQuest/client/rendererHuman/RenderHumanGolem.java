/*  1:   */ package com.chocolate.chocolateQuest.client.rendererHuman;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.model.ModelGolemSmall;
/*  4:   */ import net.minecraft.util.ResourceLocation;
/*  5:   */ 
/*  6:   */ public class RenderHumanGolem
/*  7:   */   extends RenderHuman
/*  8:   */ {
/*  9:   */   public RenderHumanGolem(float f, ResourceLocation r)
/* 10:   */   {
/* 11:21 */     super(new ModelGolemSmall(), f, r);
/* 12:   */   }
/* 13:   */   
/* 14:   */   protected void func_82421_b()
/* 15:   */   {
/* 16:27 */     this.field_82423_g = new ModelGolemSmall(1.0F);
/* 17:28 */     this.field_82425_h = new ModelGolemSmall(0.5F);
/* 18:   */   }
/* 19:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.rendererHuman.RenderHumanGolem
 * JD-Core Version:    0.7.1
 */