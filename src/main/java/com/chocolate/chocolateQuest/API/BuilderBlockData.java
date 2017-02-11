/*   1:    */ package com.chocolate.chocolateQuest.API;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.block.Block;
/*   5:    */ import net.minecraft.init.Blocks;
/*   6:    */ import net.minecraft.world.biome.BiomeGenBase;
/*   7:    */ import net.minecraft.world.biome.BiomeGenBeach;
/*   8:    */ import net.minecraft.world.biome.BiomeGenDesert;
/*   9:    */ import net.minecraft.world.biome.BiomeGenHell;
/*  10:    */ import net.minecraft.world.biome.BiomeGenMushroomIsland;
/*  11:    */ import net.minecraft.world.biome.BiomeGenOcean;
/*  12:    */ import net.minecraft.world.biome.BiomeGenRiver;
/*  13:    */ import net.minecraft.world.biome.BiomeGenSwamp;
/*  14:    */ 
/*  15:    */ public class BuilderBlockData
/*  16:    */ {
/*  17:    */   public Block id;
/*  18: 19 */   public int metadata = 0;
/*  19:    */   
/*  20:    */   public BuilderBlockData(Block block)
/*  21:    */   {
/*  22: 22 */     this.id = block;
/*  23: 23 */     this.metadata = 0;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public BuilderBlockData(Block id, int metadata)
/*  27:    */   {
/*  28: 27 */     this.id = id;
/*  29: 28 */     this.metadata = metadata;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public Block getID(Random random)
/*  33:    */   {
/*  34: 32 */     if (this.id == Blocks.cobblestone)
/*  35:    */     {
/*  36: 34 */       int chance = random.nextInt(10);
/*  37: 35 */       if (chance == 0) {
/*  38: 36 */         return Blocks.mossy_cobblestone;
/*  39:    */       }
/*  40:    */     }
/*  41: 38 */     return this.id;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public int getMetadata(Random random)
/*  45:    */   {
/*  46: 43 */     if ((this.id == Blocks.stonebrick) && (this.metadata != 0))
/*  47:    */     {
/*  48: 45 */       int chance = random.nextInt(16);
/*  49: 46 */       if (chance == 0) {
/*  50: 47 */         return 1;
/*  51:    */       }
/*  52: 48 */       if (chance == 1) {
/*  53: 49 */         return 2;
/*  54:    */       }
/*  55:    */     }
/*  56: 51 */     return this.metadata;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public static BuilderBlockData getRoadBlockForBiome(BiomeGenBase biome)
/*  60:    */   {
/*  61: 56 */     if (((biome instanceof BiomeGenOcean)) || ((biome instanceof BiomeGenRiver)) || ((biome instanceof BiomeGenSwamp)) || ((biome instanceof BiomeGenBeach))) {
/*  62: 57 */       return new BuilderBlockData(Blocks.planks);
/*  63:    */     }
/*  64: 59 */     return new BuilderBlockData(Blocks.gravel);
/*  65:    */   }
/*  66:    */   
/*  67:    */   public static BuilderBlockData getGroundBlockForBiome(BiomeGenBase biome)
/*  68:    */   {
/*  69: 63 */     if ((biome instanceof BiomeGenDesert)) {
/*  70: 64 */       return new BuilderBlockData(Blocks.sand);
/*  71:    */     }
/*  72: 65 */     if ((biome instanceof BiomeGenHell)) {
/*  73: 66 */       return new BuilderBlockData(Blocks.netherrack);
/*  74:    */     }
/*  75: 67 */     if ((biome instanceof BiomeGenMushroomIsland)) {
/*  76: 68 */       return new BuilderBlockData(Blocks.mycelium);
/*  77:    */     }
/*  78: 70 */     return new BuilderBlockData(Blocks.grass);
/*  79:    */   }
/*  80:    */   
/*  81:    */   public static BuilderBlockData getWallBlockForBiome(BiomeGenBase biome)
/*  82:    */   {
/*  83: 75 */     if ((biome instanceof BiomeGenDesert)) {
/*  84: 76 */       return new BuilderBlockData(Blocks.sandstone);
/*  85:    */     }
/*  86: 77 */     if ((biome instanceof BiomeGenHell)) {
/*  87: 78 */       return new BuilderBlockData(Blocks.nether_brick);
/*  88:    */     }
/*  89: 80 */     return new BuilderBlockData(Blocks.stonebrick);
/*  90:    */   }
/*  91:    */   
/*  92:    */   public static BuilderBlockData getRandomWallBlock(Random random)
/*  93:    */   {
/*  94: 85 */     Block ret = Blocks.stonebrick;
/*  95: 87 */     if (random.nextInt(500) == 0) {
/*  96: 89 */       ret = Blocks.quartz_block;
/*  97:    */     }
/*  98: 92 */     if (random.nextInt(20) == 0) {
/*  99: 94 */       ret = Blocks.nether_brick;
/* 100: 96 */     } else if (random.nextInt(10) == 0) {
/* 101: 98 */       ret = Blocks.sandstone;
/* 102:100 */     } else if (random.nextInt(8) == 0) {
/* 103:102 */       ret = Blocks.brick_block;
/* 104:    */     }
/* 105:105 */     if (random.nextInt(10) == 0) {
/* 106:107 */       ret = Blocks.cobblestone;
/* 107:    */     }
/* 108:109 */     if (random.nextInt(10) == 0) {
/* 109:111 */       ret = Blocks.log;
/* 110:    */     }
/* 111:114 */     return new BuilderBlockData(ret, 0);
/* 112:    */   }
/* 113:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.API.BuilderBlockData
 * JD-Core Version:    0.7.1
 */