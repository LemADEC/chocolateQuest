/*  1:   */ package com.chocolate.chocolateQuest.builder;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.API.BuilderBase;
/*  4:   */ import com.chocolate.chocolateQuest.API.HelperReadConfig;
/*  5:   */ import com.chocolate.chocolateQuest.builder.schematic.Schematic;
/*  6:   */ import java.util.Properties;
/*  7:   */ import java.util.Random;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class BuilderTemplate
/* 11:   */   extends BuilderBase
/* 12:   */ {
/* 13:   */   String folderName;
/* 14:   */   int posY;
/* 15:   */   
/* 16:   */   public BuilderBase load(Properties prop)
/* 17:   */   {
/* 18:21 */     this.folderName = prop.getProperty("folder").trim();
/* 19:22 */     if (this.folderName == null) {
/* 20:23 */       return null;
/* 21:   */     }
/* 22:24 */     this.posY = HelperReadConfig.getIntegerProperty(prop, "posY", 64);
/* 23:25 */     return this;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public String getName()
/* 27:   */   {
/* 28:29 */     return "templateFloating";
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void generate(Random random, World world, int x, int z, int mob)
/* 32:   */   {
/* 33:34 */     this.posY = Math.max(1, this.posY);
/* 34:35 */     Schematic schematic = BuilderHelper.getRandomNBTMap(this.folderName, random);
/* 35:36 */     int maxX = schematic.width;
/* 36:37 */     int maxY = schematic.length;
/* 37:38 */     generate(random, schematic, world, x - maxX / 2, this.posY, z - maxY / 2, mob);
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void generate(Random random, World world, int i, int j, int k, int idMob)
/* 41:   */   {
/* 42:43 */     Schematic schematic = BuilderHelper.getRandomNBTMap(this.folderName, random);
/* 43:44 */     generate(random, schematic, world, i, j, k, idMob);
/* 44:   */   }
/* 45:   */   
/* 46:   */   public void generate(Random random, Schematic schematic, World world, int i, int j, int k, int idMob)
/* 47:   */   {
/* 48:49 */     BuilderHelper builderHelper = BuilderHelper.builderHelper;
/* 49:50 */     builderHelper.initialize();
/* 50:51 */     builderHelper.putSchematicInWorld(random, world, schematic, i, j, k, idMob);
/* 51:52 */     builderHelper.flush(world);
/* 52:   */   }
/* 53:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.BuilderTemplate
 * JD-Core Version:    0.7.1
 */