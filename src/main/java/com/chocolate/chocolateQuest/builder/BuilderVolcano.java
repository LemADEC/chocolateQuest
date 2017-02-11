/*  1:   */ package com.chocolate.chocolateQuest.builder;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.API.BuilderBase;
/*  4:   */ import com.chocolate.chocolateQuest.API.BuilderBlockData;
/*  5:   */ import com.chocolate.chocolateQuest.API.HelperReadConfig;
/*  6:   */ import java.util.Properties;
/*  7:   */ import java.util.Random;
/*  8:   */ import net.minecraft.init.Blocks;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class BuilderVolcano
/* 12:   */   extends BuilderBase
/* 13:   */ {
/* 14:   */   int numTiles;
/* 15:17 */   static int filer = 1;
/* 16:   */   BuilderBlockData blockWalls;
/* 17:   */   BuilderBlockData blockPath;
/* 18:22 */   int oreChance = 8;
/* 19:23 */   String folder = "dungeons";
/* 20:24 */   int dungeonRooms = 25;
/* 21:26 */   boolean generatePath = true;
/* 22:27 */   boolean generateDungeon = true;
/* 23:29 */   static int vacio = -1;
/* 24:30 */   int counter = 120;
/* 25:   */   
/* 26:   */   public BuilderBase load(Properties prop)
/* 27:   */   {
/* 28:38 */     this.oreChance = HelperReadConfig.getIntegerProperty(prop, "oreChance", 16);
/* 29:39 */     this.blockWalls = HelperReadConfig.getBlock(prop, "blockWalls", new BuilderBlockData(Blocks.stone));
/* 30:40 */     this.blockPath = HelperReadConfig.getBlock(prop, "blockPath", new BuilderBlockData(Blocks.netherrack));
/* 31:41 */     this.dungeonRooms = HelperReadConfig.getIntegerProperty(prop, "dungeonRooms", 25);
/* 32:42 */     this.generatePath = HelperReadConfig.getBooleanProperty(prop, "generatePath", true);
/* 33:43 */     this.generateDungeon = HelperReadConfig.getBooleanProperty(prop, "generateDungeon", false);
/* 34:44 */     return this;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public String getName()
/* 38:   */   {
/* 39:49 */     return "volcano";
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void generate(Random random, World world, int x, int z, int mob)
/* 43:   */   {
/* 44:54 */     int y = 5;
/* 45:55 */     generate(random, world, x, y, z, mob);
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void generate(Random random, World world, int i, int j, int k, int mob) {}
/* 49:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.BuilderVolcano
 * JD-Core Version:    0.7.1
 */