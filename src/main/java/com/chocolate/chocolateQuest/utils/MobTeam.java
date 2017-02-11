/*  1:   */ package com.chocolate.chocolateQuest.utils;
/*  2:   */ 
/*  3:   */ import net.minecraft.scoreboard.Team;
/*  4:   */ 
/*  5:   */ public class MobTeam
/*  6:   */   extends Team
/*  7:   */ {
/*  8:   */   String teamName;
/*  9:   */   
/* 10:   */   public MobTeam(String teamName)
/* 11:   */   {
/* 12:10 */     this.teamName = teamName;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public boolean isSameTeam(Team team)
/* 16:   */   {
/* 17:15 */     return team == null ? false : getRegisteredName().equals(team.getRegisteredName());
/* 18:   */   }
/* 19:   */   
/* 20:   */   public boolean func_98297_h()
/* 21:   */   {
/* 22:20 */     return false;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public boolean getAllowFriendlyFire()
/* 26:   */   {
/* 27:25 */     return false;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public String formatString(String var1)
/* 31:   */   {
/* 32:29 */     return this.teamName;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public String getRegisteredName()
/* 36:   */   {
/* 37:33 */     return this.teamName;
/* 38:   */   }
/* 39:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.utils.MobTeam
 * JD-Core Version:    0.7.1
 */