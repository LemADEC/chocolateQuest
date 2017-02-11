/*  1:   */ package com.chocolate.chocolateQuest.utils;
/*  2:   */ 
/*  3:   */ public class Vec4I
/*  4:   */ {
/*  5:   */   public int xCoord;
/*  6:   */   public int yCoord;
/*  7:   */   public int zCoord;
/*  8:   */   public int rot;
/*  9:   */   
/* 10:   */   public Vec4I(int x, int y, int z, int rot)
/* 11:   */   {
/* 12: 8 */     this.xCoord = x;
/* 13: 9 */     this.yCoord = y;
/* 14:10 */     this.zCoord = z;
/* 15:11 */     this.rot = rot;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public Vec4I(Vec4I vec)
/* 19:   */   {
/* 20:15 */     this.xCoord = vec.xCoord;
/* 21:16 */     this.yCoord = vec.yCoord;
/* 22:17 */     this.zCoord = vec.zCoord;
/* 23:18 */     this.rot = vec.rot;
/* 24:   */   }
/* 25:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.utils.Vec4I
 * JD-Core Version:    0.7.1
 */