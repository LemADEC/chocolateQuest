/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ public enum EnumAiCombat
/*  4:   */ {
/*  5: 6 */   OFFENSIVE("ai.offensive.name"),  DEFENSIVE("ai.defensive.name"),  EVASIVE("ai.evasive.name"),  FLEE("ai.flee.name"),  BACKSTAB("ai.backstab.name");
/*  6:   */   
/*  7:   */   public String ainame;
/*  8:   */   
/*  9:   */   private EnumAiCombat(String name)
/* 10:   */   {
/* 11:15 */     this.ainame = name;
/* 12:   */   }
/* 13:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.EnumAiCombat
 * JD-Core Version:    0.7.1
 */