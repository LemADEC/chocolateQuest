/*   1:    */ package com.chocolate.chocolateQuest.builder;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.BuilderBase;
/*   4:    */ import com.chocolate.chocolateQuest.API.BuilderBlockData;
/*   5:    */ import com.chocolate.chocolateQuest.API.HelperReadConfig;
/*   6:    */ import java.util.Properties;
/*   7:    */ import java.util.Random;
/*   8:    */ import net.minecraft.init.Blocks;
/*   9:    */ import net.minecraft.world.World;
/*  10:    */ 
/*  11:    */ public class BuilderNether
/*  12:    */   extends BuilderBase
/*  13:    */ {
/*  14: 15 */   String folder = "pigcity";
/*  15: 16 */   int rows = 4;
/*  16: 17 */   int posY = 31;
/*  17:    */   BuilderBlockData floorBlock;
/*  18: 19 */   BuilderBlockData lavaBlock = new BuilderBlockData(Blocks.lava);
/*  19:    */   
/*  20:    */   public BuilderBase load(Properties prop)
/*  21:    */   {
/*  22: 26 */     this.rows = HelperReadConfig.getIntegerProperty(prop, "rows", 4);
/*  23: 27 */     if (this.rows < 1) {
/*  24: 28 */       this.rows = 1;
/*  25:    */     }
/*  26: 29 */     if (this.rows > 20) {
/*  27: 30 */       this.rows = 20;
/*  28:    */     }
/*  29: 32 */     this.posY = HelperReadConfig.getIntegerProperty(prop, "posY", 31);
/*  30: 33 */     this.floorBlock = HelperReadConfig.getBlock(prop, "floorBlock", new BuilderBlockData(Blocks.nether_brick));
/*  31: 34 */     this.lavaBlock = HelperReadConfig.getBlock(prop, "lavaBlock", this.lavaBlock);
/*  32:    */     
/*  33: 36 */     this.folder = prop.getProperty("folder");
/*  34: 37 */     if (this.folder == null) {
/*  35: 38 */       this.folder = "pigcity";
/*  36:    */     }
/*  37: 39 */     return super.load(prop);
/*  38:    */   }
/*  39:    */   
/*  40:    */   public String getName()
/*  41:    */   {
/*  42: 44 */     return "lavaCity";
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void generate(Random random, World world, int x, int z, int mob)
/*  46:    */   {
/*  47: 49 */     generate(random, world, x, this.posY, z, mob);
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void generate(Random random, World world, int i, int j, int k, int mob)
/*  51:    */   {
/*  52: 54 */     Perlin3D p = new Perlin3D(world.getSeed(), 8, random);
/*  53: 55 */     Perlin3D p2 = new Perlin3D(world.getSeed(), 32, random);
/*  54:    */     
/*  55: 57 */     int rowSize = 21;
/*  56: 58 */     int size = this.rows * rowSize;
/*  57: 59 */     int height = 32;
/*  58: 60 */     int wallSize = 4;
/*  59: 61 */     for (int x = 0; x < size; x++) {
/*  60: 63 */       for (int y = 0; y < height; y++) {
/*  61: 65 */         for (int z = 0; z < size; z++)
/*  62:    */         {
/*  63: 67 */           float noiseVar = Math.max(0.0F, 2.0F - (height - y) / 4.0F);
/*  64:    */           
/*  65: 69 */           noiseVar += Math.max(0.0F, wallSize - x / 2.0F);
/*  66: 70 */           noiseVar += Math.max(0.0F, wallSize - (size - x) / 2.0F);
/*  67:    */           
/*  68: 72 */           noiseVar += Math.max(0.0F, wallSize - z / 2.0F);
/*  69: 73 */           noiseVar += Math.max(0.0F, wallSize - (size - z) / 2.0F);
/*  70: 76 */           if (p.getNoiseAt(x + i, y + j, z + k) * p2.getNoiseAt(x + i, y + j, z + k) * noiseVar < 0.5D) {
/*  71: 77 */             world.setBlockToAir(i + x, j + y, k + z);
/*  72:    */           }
/*  73:    */         }
/*  74:    */       }
/*  75:    */     }
/*  76: 86 */     BuilderHelper b = BuilderHelper.builderHelper;
/*  77: 87 */     b.initialize();
/*  78:    */     
/*  79: 89 */     int[][][] map = new int[size][1][size];
/*  80: 95 */     for (int x = 0; x < map.length; x++) {
/*  81: 97 */       for (int z = 0; z < map.length; z++) {
/*  82: 99 */         if (((x % 21 <= 2) || (x % 21 >= 18) || (z % 21 <= 2) || (z % 21 >= 18)) && ((x % 21 <= 8) || (x % 21 >= 12)) && ((z % 21 <= 8) || (z % 21 >= 12))) {
/*  83:102 */           world.setBlock(i + x, j, k + z, this.lavaBlock.id);
/*  84:    */         } else {
/*  85:106 */           world.setBlock(i + x, j, k + z, this.floorBlock.getID(random), this.floorBlock.getMetadata(random), 3);
/*  86:    */         }
/*  87:    */       }
/*  88:    */     }
/*  89:113 */     for (int x = 0; x < map.length; x += 21) {
/*  90:115 */       for (int z = 0; z < map.length; z += 21) {
/*  91:117 */         b.putSchematicInWorld(random, world, BuilderHelper.getRandomNBTMap(this.folder, random), x + 3 + i, j + 1, z + 3 + k, mob);
/*  92:    */       }
/*  93:    */     }
/*  94:121 */     b.flush(world);
/*  95:    */   }
/*  96:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.BuilderNether
 * JD-Core Version:    0.7.1
 */