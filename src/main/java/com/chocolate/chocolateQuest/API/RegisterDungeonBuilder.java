/*  1:   */ package com.chocolate.chocolateQuest.API;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ 
/*  5:   */ public class RegisterDungeonBuilder
/*  6:   */ {
/*  7:   */   public static ArrayList<BuilderBase> builderList;
/*  8:   */   
/*  9:   */   public static void addDungeonBuilder(BuilderBase mob)
/* 10:   */   {
/* 11:11 */     if (builderList == null) {
/* 12:13 */       builderList = new ArrayList();
/* 13:   */     }
/* 14:15 */     builderList.add(mob);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public static void addMob(BuilderBase mob, int position)
/* 18:   */   {
/* 19:20 */     if (builderList == null) {
/* 20:22 */       builderList = new ArrayList();
/* 21:   */     }
/* 22:24 */     builderList.add(mob);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public static BuilderBase getBuilderByName(String s)
/* 26:   */   {
/* 27:   */     try
/* 28:   */     {
/* 29:30 */       for (BuilderBase builder : builderList) {
/* 30:32 */         if (builder.getName().equals(s)) {
/* 31:33 */           return (BuilderBase)builder.getClass().newInstance();
/* 32:   */         }
/* 33:   */       }
/* 34:   */     }
/* 35:   */     catch (InstantiationException e)
/* 36:   */     {
/* 37:36 */       e.printStackTrace();
/* 38:   */     }
/* 39:   */     catch (IllegalAccessException e)
/* 40:   */     {
/* 41:38 */       e.printStackTrace();
/* 42:   */     }
/* 43:40 */     return null;
/* 44:   */   }
/* 45:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.API.RegisterDungeonBuilder
 * JD-Core Version:    0.7.1
 */