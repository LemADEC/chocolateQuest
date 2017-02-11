/*  1:   */ package com.chocolate.chocolateQuest.utils;
/*  2:   */ 
/*  3:   */ public class Vec3I
/*  4:   */ {
/*  5:   */   public int xCoord;
/*  6:   */   public int yCoord;
/*  7:   */   public int zCoord;
/*  8:   */   
/*  9:   */   public Vec3I(int x, int y, int z)
/* 10:   */   {
/* 11: 8 */     this.xCoord = x;
/* 12: 9 */     this.yCoord = y;
/* 13:10 */     this.zCoord = z;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public Vec3I(Vec3I vec)
/* 17:   */   {
/* 18:14 */     this.xCoord = vec.xCoord;
/* 19:15 */     this.yCoord = vec.yCoord;
/* 20:16 */     this.zCoord = vec.zCoord;
/* 21:   */   }
/* 22:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.utils.Vec3I
 * JD-Core Version:    0.7.1
 */