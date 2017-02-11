/*  1:   */ package com.chocolate.chocolateQuest.entity.projectile;
/*  2:   */ 
/*  3:   */ public class ProjectileBulletGolem
/*  4:   */   extends ProjectileBulletPistol
/*  5:   */ {
/*  6:   */   public ProjectileBulletGolem(EntityBaseBall entity)
/*  7:   */   {
/*  8: 8 */     super(entity);
/*  9:   */   }
/* 10:   */   
/* 11:   */   protected int getBulletBaseDamage()
/* 12:   */   {
/* 13:12 */     return 8;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public float getSize()
/* 17:   */   {
/* 18:18 */     if (this.entity.getlvl() >= 4) {
/* 19:19 */       return 0.6F;
/* 20:   */     }
/* 21:20 */     return 0.3F;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public boolean canBounce()
/* 25:   */   {
/* 26:26 */     return false;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public float getBulletPitch()
/* 30:   */   {
/* 31:31 */     return 0.8F;
/* 32:   */   }
/* 33:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileBulletGolem
 * JD-Core Version:    0.7.1
 */