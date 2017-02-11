/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ public enum EnumAiState
/*  4:   */ {
/*  5: 6 */   FOLLOW("ai.follow.name"),  FORMATION("ai.formation.name"),  WARD("ai.ward.name"),  PATH("ai.path.name"),  SIT("ai.sit.name");
/*  6:   */   
/*  7:   */   public int id;
/*  8:   */   public String ainame;
/*  9:   */   
/* 10:   */   private EnumAiState(String name)
/* 11:   */   {
/* 12:16 */     this.ainame = name;
/* 13:   */   }
/* 14:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.EnumAiState
 * JD-Core Version:    0.7.1
 */