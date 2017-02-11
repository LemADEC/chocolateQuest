/*  1:   */ package com.chocolate.chocolateQuest.builder;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.API.BuilderBase;
/*  4:   */ import com.chocolate.chocolateQuest.API.HelperReadConfig;
/*  5:   */ import com.chocolate.chocolateQuest.WorldGeneratorNew;
/*  6:   */ import com.chocolate.chocolateQuest.builder.schematic.Schematic;
/*  7:   */ import com.chocolate.chocolateQuest.builder.support.SupportStructureBuilder;
/*  8:   */ import java.util.Properties;
/*  9:   */ import java.util.Random;
/* 10:   */ import net.minecraft.world.World;
/* 11:   */ 
/* 12:   */ public class BuilderTemplateSurface
/* 13:   */   extends BuilderBase
/* 14:   */ {
/* 15:   */   String folderName;
/* 16:   */   int underGroundOffset;
/* 17:   */   SupportStructureBuilder supportStructure;
/* 18:   */   
/* 19:   */   public BuilderBase load(Properties prop)
/* 20:   */   {
/* 21:23 */     this.folderName = prop.getProperty("folder").trim();
/* 22:24 */     if (this.folderName == null) {
/* 23:25 */       return null;
/* 24:   */     }
/* 25:27 */     this.underGroundOffset = HelperReadConfig.getIntegerProperty(prop, "underGroundOffset", 10);
/* 26:29 */     if (HelperReadConfig.getBooleanProperty(prop, "supportStructure", false))
/* 27:   */     {
/* 28:31 */       this.supportStructure = new SupportStructureBuilder();
/* 29:32 */       this.supportStructure.load(prop);
/* 30:   */     }
/* 31:34 */     return this;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public String getName()
/* 35:   */   {
/* 36:38 */     return "templateSurface";
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void generate(Random random, World world, int i, int k, int mob)
/* 40:   */   {
/* 41:43 */     Schematic schematic = BuilderHelper.getRandomNBTMap(this.folderName, random);
/* 42:44 */     int maxX = schematic.width;
/* 43:45 */     int maxY = schematic.length;
/* 44:46 */     i -= maxX / 2;
/* 45:47 */     k -= maxY / 2;
/* 46:48 */     int cont = 0;
/* 47:49 */     int cant = 0;
/* 48:50 */     int media = 0;
/* 49:51 */     for (int x = 0; x < maxX; x++) {
/* 50:52 */       for (int y = 0; y < maxY; y++)
/* 51:   */       {
/* 52:53 */         int h = world.getTopSolidOrLiquidBlock(i + x, y + k);
/* 53:54 */         media += h;
/* 54:55 */         cant++;
/* 55:   */       }
/* 56:   */     }
/* 57:58 */     int height = media / cant;
/* 58:   */     
/* 59:60 */     generate(random, world, i, height, k, mob);
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void generate(Random random, World world, int i, int j, int k, int idMob)
/* 63:   */   {
/* 64:65 */     Schematic schematic = BuilderHelper.getRandomNBTMap(this.folderName, random);
/* 65:66 */     generate(random, schematic, world, i, j, k, idMob);
/* 66:   */   }
/* 67:   */   
/* 68:   */   public void generate(Random random, Schematic schematic, World world, int i, int j, int k, int idMob)
/* 69:   */   {
/* 70:71 */     WorldGeneratorNew.createChunks(world, i, k, schematic.width, schematic.length);
/* 71:72 */     if (this.supportStructure != null)
/* 72:   */     {
/* 73:75 */       int maxX = schematic.width;
/* 74:76 */       int maxY = schematic.length;
/* 75:77 */       this.supportStructure.generate(random, world, i, j - 2, k, maxX, maxY);
/* 76:   */     }
/* 77:79 */     j -= this.underGroundOffset;
/* 78:80 */     j = Math.max(1, j);
/* 79:   */     
/* 80:82 */     BuilderHelper builderHelper = BuilderHelper.builderHelper;
/* 81:83 */     builderHelper.initialize();
/* 82:84 */     builderHelper.putSchematicInWorld(random, world, schematic, i, j, k, idMob);
/* 83:85 */     builderHelper.flush(world);
/* 84:   */   }
/* 85:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.BuilderTemplateSurface
 * JD-Core Version:    0.7.1
 */