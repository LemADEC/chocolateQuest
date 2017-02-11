/*   1:    */ package com.chocolate.chocolateQuest.builder;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.BuilderBase;
/*   4:    */ import com.chocolate.chocolateQuest.API.BuilderBlockData;
/*   5:    */ import com.chocolate.chocolateQuest.API.HelperReadConfig;
/*   6:    */ import java.util.Properties;
/*   7:    */ import java.util.Random;
/*   8:    */ import net.minecraft.block.Block;
/*   9:    */ import net.minecraft.block.material.Material;
/*  10:    */ import net.minecraft.init.Blocks;
/*  11:    */ import net.minecraft.world.World;
/*  12:    */ 
/*  13:    */ public class BuilderEmptyCave
/*  14:    */   extends BuilderBase
/*  15:    */ {
/*  16:    */   boolean[][][] map;
/*  17:    */   boolean[][][] map2;
/*  18: 18 */   BuilderBlockData caveBlock = new BuilderBlockData(Blocks.air);
/*  19: 19 */   int initChance = 50;
/*  20: 20 */   int size = 48;
/*  21: 21 */   int height = 28;
/*  22:    */   int posY;
/*  23: 23 */   int boss = 0;
/*  24: 24 */   boolean spawnBoss = false;
/*  25:    */   World world;
/*  26:    */   
/*  27:    */   public BuilderBase load(Properties prop)
/*  28:    */   {
/*  29: 30 */     this.caveBlock = HelperReadConfig.getBlock(prop, "caveBlock", this.caveBlock);
/*  30: 31 */     this.initChance = HelperReadConfig.getIntegerProperty(prop, "initChance", 50);
/*  31: 32 */     this.size = HelperReadConfig.getIntegerProperty(prop, "size", 32);
/*  32: 33 */     this.height = HelperReadConfig.getIntegerProperty(prop, "height", 28);
/*  33: 34 */     this.posY = HelperReadConfig.getIntegerProperty(prop, "posY", 20);
/*  34:    */     
/*  35: 36 */     this.boss = HelperReadConfig.getIntegerProperty(prop, "boss", 3);
/*  36: 37 */     this.spawnBoss = HelperReadConfig.getBooleanProperty(prop, "spawnBoss", false);
/*  37:    */     
/*  38: 39 */     return this;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public void generate(Random random, World world, int i, int j, int mob)
/*  42:    */   {
/*  43: 44 */     generate(random, world, i, this.posY, j, mob);
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void generateWithSize(Random random, World world, int i, int j, int k, int size)
/*  47:    */   {
/*  48: 49 */     this.size = size;
/*  49: 50 */     generate(random, world, i, j, k, 1);
/*  50:    */   }
/*  51:    */   
/*  52:    */   public String getName()
/*  53:    */   {
/*  54: 55 */     return "emptyCave";
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void generate(Random random, World world, int i, int j, int k, int idMob)
/*  58:    */   {
/*  59: 61 */     int width = 32;
/*  60: 62 */     int rooms = Math.max(1, width / 8);
/*  61: 63 */     int[][] roomsDecoration = new int[rooms][rooms];
/*  62: 64 */     int wallBlock = 1;
/*  63:    */     
/*  64: 66 */     int wallX = random.nextInt(width / rooms - 1) + 4;
/*  65: 67 */     int currentWallX = 0;
/*  66: 68 */     int roomWidth = width / rooms;
/*  67: 69 */     int doorX = 0;
/*  68: 70 */     int doorZ = 0;
/*  69:    */     
/*  70:    */ 
/*  71:    */ 
/*  72:    */ 
/*  73:    */ 
/*  74:    */ 
/*  75:    */ 
/*  76:    */ 
/*  77:    */ 
/*  78:    */ 
/*  79:    */ 
/*  80:    */ 
/*  81:    */ 
/*  82:    */ 
/*  83:    */ 
/*  84:    */ 
/*  85:    */ 
/*  86:    */ 
/*  87:    */ 
/*  88:    */ 
/*  89:    */ 
/*  90:    */ 
/*  91:    */ 
/*  92:    */ 
/*  93:    */ 
/*  94: 96 */     Perlin3D p = new Perlin3D(world.getSeed(), 8, random);
/*  95: 97 */     Perlin3D p2 = new Perlin3D(world.getSeed(), 32, random);
/*  96: 98 */     int wallSize = Math.max(1, width / 15);
/*  97: 99 */     int freq = 2;
/*  98:100 */     for (int x = 0; x < this.size; x++) {
/*  99:102 */       for (int y = 0; y < this.height; y++) {
/* 100:104 */         for (int z = 0; z < this.size; z++)
/* 101:    */         {
/* 102:106 */           float noiseVar = Math.max(0.0F, 2.0F - y / 2.0F);
/* 103:107 */           noiseVar += Math.max(0.0F, 3.0F - (this.height - y) / 2.0F);
/* 104:    */           
/* 105:109 */           noiseVar += Math.max(0, wallSize - x / freq);
/* 106:110 */           noiseVar += Math.max(0, wallSize - (this.size - x - 1) / freq);
/* 107:    */           
/* 108:112 */           noiseVar += Math.max(0, wallSize - z / freq);
/* 109:113 */           noiseVar += Math.max(0, wallSize - (this.size - z - 1) / freq);
/* 110:116 */           if (p.getNoiseAt(x + i, y + j, z + k) * p2.getNoiseAt(x + i, y + j, z + k) * noiseVar < 0.5D) {
/* 111:117 */             world.setBlock(i + x, j + y, k + z, this.caveBlock.id);
/* 112:    */           }
/* 113:    */         }
/* 114:    */       }
/* 115:    */     }
/* 116:121 */     int y = 0;
/* 117:122 */     int half = this.size / 2;
/* 118:123 */     while ((world.getBlock(i + half, j + y, k + half) != Blocks.air) && (world.getBlock(i + half, j + y, k + half).getMaterial() != Material.water)) {
/* 119:125 */       y++;
/* 120:    */     }
/* 121:    */   }
/* 122:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.BuilderEmptyCave
 * JD-Core Version:    0.7.1
 */