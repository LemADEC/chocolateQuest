/*  1:   */ package com.chocolate.chocolateQuest.API;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ 
/*  5:   */ public class DungeonRegister
/*  6:   */ {
/*  7:   */   public static ArrayList<DungeonBase> dungeonList;
/*  8:   */   
/*  9:   */   public static void addDungeon(DungeonBase dungeon)
/* 10:   */   {
/* 11:12 */     if (dungeonList == null) {
/* 12:14 */       dungeonList = new ArrayList();
/* 13:   */     }
/* 14:17 */     dungeonList.add(dungeon);
/* 15:   */   }
/* 16:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.API.DungeonRegister
 * JD-Core Version:    0.7.1
 */