/*  1:   */ package com.chocolate.chocolateQuest.config;
/*  2:   */ 
/*  3:   */ import net.minecraftforge.common.config.Configuration;
/*  4:   */ import net.minecraftforge.common.config.Property;
/*  5:   */ 
/*  6:   */ public class Configurations
/*  7:   */ {
/*  8:   */   public int dungeonSeparation;
/*  9:   */   public boolean dungeonsInFlat;
/* 10:   */   public int distToDespawn;
/* 11:   */   
/* 12:   */   public void load(Configuration config)
/* 13:   */   {
/* 14:14 */     config.load();
/* 15:15 */     Property prop = config.get("general", "dungeonSeparation", 10);
/* 16:16 */     prop.comment = "Distance in chunks(16 blocks) from dungeon to dungeon";
/* 17:17 */     this.dungeonSeparation = prop.getInt(10);
/* 18:   */     
/* 19:19 */     prop = config.get("general", "dungeonsInFlat", false);
/* 20:20 */     prop.comment = "Generate dungeons in flat maps";
/* 21:21 */     this.dungeonsInFlat = prop.getBoolean(false);
/* 22:   */     
/* 23:23 */     prop = config.get("general", "distanceToDespawn", 64);
/* 24:24 */     prop.comment = "Distance in blocks to the closest player to despawn mobs";
/* 25:25 */     this.distToDespawn = prop.getInt(64);
/* 26:   */     
/* 27:   */ 
/* 28:   */ 
/* 29:   */ 
/* 30:   */ 
/* 31:   */ 
/* 32:   */ 
/* 33:   */ 
/* 34:   */ 
/* 35:   */ 
/* 36:36 */     config.save();
/* 37:   */   }
/* 38:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.config.Configurations
 * JD-Core Version:    0.7.1
 */