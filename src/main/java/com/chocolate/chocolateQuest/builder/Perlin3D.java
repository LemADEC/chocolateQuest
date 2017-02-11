/*  1:   */ package com.chocolate.chocolateQuest.builder;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ 
/*  5:   */ public class Perlin3D
/*  6:   */ {
/*  7:   */   private long seed;
/*  8:   */   private Random rand;
/*  9:   */   private int frequency;
/* 10:   */   
/* 11:   */   public Perlin3D(long seed, int octave, Random random)
/* 12:   */   {
/* 13:13 */     this.seed = seed;
/* 14:14 */     this.frequency = octave;
/* 15:15 */     this.rand = random;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public double getNoiseAt(int x, int y, int z)
/* 19:   */   {
/* 20:20 */     int ymin = (int)Math.floor(y / this.frequency);
/* 21:21 */     int ymax = ymin + 1;
/* 22:22 */     return cosineInterpolate((float)getNoiseLevelAtPosition(x, ymin, z), (float)getNoiseLevelAtPosition(x, ymax, z), (y - ymin * this.frequency) / this.frequency);
/* 23:   */   }
/* 24:   */   
/* 25:   */   private double getNoiseLevelAtPosition(int x, int y, int z)
/* 26:   */   {
/* 27:28 */     int xmin = (int)Math.floor(x / this.frequency);
/* 28:29 */     int xmax = xmin + 1;
/* 29:30 */     int zmin = (int)Math.floor(z / this.frequency);
/* 30:31 */     int zmax = zmin + 1;
/* 31:32 */     return cosineInterpolate(cosineInterpolate((float)getRandomAtPosition(xmin, y, zmin), (float)getRandomAtPosition(xmax, y, zmin), (x - xmin * this.frequency) / this.frequency), cosineInterpolate((float)getRandomAtPosition(xmin, y, zmax), (float)getRandomAtPosition(xmax, y, zmax), (x - xmin * this.frequency) / this.frequency), (z - zmin * this.frequency) / this.frequency);
/* 32:   */   }
/* 33:   */   
/* 34:   */   private float cosineInterpolate(float a, float b, float x)
/* 35:   */   {
/* 36:42 */     float f = (float)((1.0D - Math.cos(x * 3.141592653589793D)) * 0.5D);
/* 37:43 */     return a * (1.0F - f) + b * f;
/* 38:   */   }
/* 39:   */   
/* 40:   */   private float linearInterpolate(float a, float b, float x)
/* 41:   */   {
/* 42:48 */     return a * (1.0F - x) + b * x;
/* 43:   */   }
/* 44:   */   
/* 45:   */   private double getRandomAtPosition(int x, int y, int z)
/* 46:   */   {
long tryingMyBest = Math.round((10000.0D * (Math.sin(x) + Math.cos(z) + Math.cos(y) + Math.tan(this.seed))));
/* 47:52 */     this.rand.setSeed(tryingMyBest);
/* 48:53 */     return this.rand.nextDouble();
/* 49:   */   }
/* 50:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.builder.Perlin3D

 * JD-Core Version:    0.7.1

 */
