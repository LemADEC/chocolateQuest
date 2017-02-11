/*  1:   */ package com.chocolate.chocolateQuest.entity.projectile;
/*  2:   */ 
/*  3:   */ import net.minecraft.util.MovingObjectPosition;
/*  4:   */ 
/*  5:   */ public class ProjectileDummy
/*  6:   */   extends ProjectileBase
/*  7:   */ {
/*  8:   */   EntityBaseBall entity;
/*  9:   */   
/* 10:   */   public ProjectileDummy(EntityBaseBall entity)
/* 11:   */   {
/* 12:11 */     super(entity);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public int getTextureIndex()
/* 16:   */   {
/* 17:15 */     return 0;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void onImpact(MovingObjectPosition par1MovingObjectPosition) {}
/* 21:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileDummy
 * JD-Core Version:    0.7.1
 */