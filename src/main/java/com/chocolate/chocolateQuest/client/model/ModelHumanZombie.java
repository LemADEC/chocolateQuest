/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ 
/*  5:   */ public class ModelHumanZombie
/*  6:   */   extends ModelHuman
/*  7:   */ {
/*  8:   */   public ModelHumanZombie()
/*  9:   */   {
/* 10:18 */     this(0.0F, false);
/* 11:   */   }
/* 12:   */   
/* 13:   */   protected ModelHumanZombie(float par1, float par2, int par3, int par4)
/* 14:   */   {
/* 15:23 */     super(par1, par2, par3, par4);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public ModelHumanZombie(float par1, boolean par2)
/* 19:   */   {
/* 20:28 */     super(par1, 0.0F, 64, par2 ? 32 : 64);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void setHumanRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, EntityHumanBase e)
/* 24:   */   {
/* 25:49 */     super.setHumanRotationAngles(f, f1, f2, f3, f4, f5, e);
/* 26:   */   }
/* 27:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelHumanZombie
 * JD-Core Version:    0.7.1
 */