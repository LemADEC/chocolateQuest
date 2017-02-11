/*  1:   */ package com.chocolate.chocolateQuest.API;
/*  2:   */ 
/*  3:   */ import java.util.Properties;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.world.World;
/*  6:   */ 
/*  7:   */ public abstract class BuilderBase
/*  8:   */ {
/*  9:   */   public abstract void generate(Random paramRandom, World paramWorld, int paramInt1, int paramInt2, int paramInt3);
/* 10:   */   
/* 11:   */   public abstract void generate(Random paramRandom, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/* 12:   */   
/* 13:   */   public abstract String getName();
/* 14:   */   
/* 15:   */   public BuilderBase load(Properties prop)
/* 16:   */   {
/* 17:28 */     return this;
/* 18:   */   }
/* 19:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.API.BuilderBase
 * JD-Core Version:    0.7.1
 */