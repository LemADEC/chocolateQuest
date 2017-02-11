/*  1:   */ package com.chocolate.chocolateQuest.entity.mob.registry;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ 
/*  5:   */ public class RegisterDungeonMobs
/*  6:   */ {
/*  7:   */   public static ArrayList<DungeonMonstersBase> mobList;
/*  8:   */   
/*  9:   */   public static void addMob(DungeonMonstersBase mob)
/* 10:   */   {
/* 11:11 */     if (mobList == null) {
/* 12:13 */       mobList = new ArrayList();
/* 13:   */     }
/* 14:16 */     mobList.add(mob);
/* 15:17 */     mob.setID(mobList.indexOf(mob));
/* 16:   */   }
/* 17:   */   
/* 18:   */   public static void addMob(DungeonMonstersBase mob, int position)
/* 19:   */   {
/* 20:22 */     if (mobList == null) {
/* 21:24 */       mobList = new ArrayList();
/* 22:   */     }
/* 23:27 */     mobList.add(mob);
/* 24:28 */     mob.setID(mobList.indexOf(mob));
/* 25:   */   }
/* 26:   */   
/* 27:   */   public static int getMonsterId(String s)
/* 28:   */   {
/* 29:33 */     int id = 0;
/* 30:34 */     for (DungeonMonstersBase mob : mobList) {
/* 31:36 */       if (mob.getEntityName().equals(s)) {
/* 32:37 */         id = mob.getID();
/* 33:   */       }
/* 34:   */     }
/* 35:39 */     return id;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public static int getMonster(String s)
/* 39:   */   {
/* 40:44 */     int id = 1;
/* 41:45 */     for (DungeonMonstersBase mob : mobList) {
/* 42:47 */       if (mob.getEntityName().equals(s)) {
/* 43:48 */         id = mob.getID();
/* 44:   */       }
/* 45:   */     }
/* 46:50 */     return id;
/* 47:   */   }
/* 48:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.registry.RegisterDungeonMobs
 * JD-Core Version:    0.7.1
 */