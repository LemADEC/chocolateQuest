/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.block.Block;
/*  5:   */ import net.minecraft.init.Blocks;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class DecoratorWindow
/*  9:   */ {
/* 10:11 */   int floorType = 0;
/* 11:12 */   Block block = Blocks.stonebrick;
/* 12:14 */   Block window = Blocks.glass_pane;
/* 13:16 */   public int height = 1;
/* 14:17 */   public int width = 1;
/* 15:18 */   int separation = 3;
/* 16:   */   
/* 17:   */   public DecoratorWindow(Random random)
/* 18:   */   {
/* 19:22 */     this.floorType = random.nextInt(4);
/* 20:23 */     if (random.nextInt(4) == 0) {
/* 21:24 */       this.window = Blocks.glass;
/* 22:25 */     } else if (random.nextInt(4) == 0) {
/* 23:26 */       this.window = Blocks.iron_bars;
/* 24:   */     }
/* 25:28 */     this.width = (1 + random.nextInt(4));
/* 26:29 */     this.separation = (1 + random.nextInt(5));
/* 27:30 */     this.height = (1 + random.nextInt(3));
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void generateWindowX(World world, int x, int y, int z)
/* 31:   */   {
/* 32:34 */     generateWindow(world, x, y, z, true);
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void generateWindowZ(World world, int x, int y, int z)
/* 36:   */   {
/* 37:38 */     generateWindow(world, x, y, z, false);
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void generateWindow(World world, int x, int y, int z, boolean aroundX)
/* 41:   */   {
/* 42:42 */     switch (this.floorType)
/* 43:   */     {
/* 44:   */     case 1: 
/* 45:44 */       if (this.width < 2) {
/* 46:45 */         simple(world, x, y, z, aroundX);
/* 47:   */       } else {
/* 48:47 */         framed(world, x, y, z, aroundX);
/* 49:   */       }
/* 50:48 */       break;
/* 51:   */     case 2: 
/* 52:50 */       open(world, x, y, z, aroundX);
/* 53:51 */       break;
/* 54:   */     default: 
/* 55:53 */       simple(world, x, y, z, aroundX);
/* 56:   */     }
/* 57:   */   }
/* 58:   */   
/* 59:   */   public void simple(World world, int x, int y, int z, boolean aroundX)
/* 60:   */   {
/* 61:60 */     int pos = Math.abs(aroundX ? x : z);
/* 62:61 */     int posX = 0;int posZ = 0;
/* 63:62 */     pos %= (this.separation + this.width);
/* 64:63 */     for (int i = 1; i < 1 + this.height; i++) {
/* 65:65 */       if (pos < this.width) {
/* 66:67 */         world.setBlock(x, y + i, z, this.window);
/* 67:   */       }
/* 68:   */     }
/* 69:   */   }
/* 70:   */   
/* 71:   */   public void open(World world, int x, int y, int z, boolean aroundX)
/* 72:   */   {
/* 73:74 */     int pos = Math.abs(aroundX ? x : z);
/* 74:75 */     int posX = 0;int posZ = 0;
/* 75:76 */     pos %= (this.separation + this.width);
/* 76:77 */     if (pos < this.width)
/* 77:   */     {
/* 78:79 */       world.setBlockToAir(x, y + 1, z);
/* 79:80 */       world.setBlock(x, y + 2, z, this.window);
/* 80:   */     }
/* 81:   */   }
/* 82:   */   
/* 83:   */   public void framed(World world, int x, int y, int z, boolean aroundX)
/* 84:   */   {
/* 85:86 */     int pos = Math.abs(aroundX ? x : z);
/* 86:87 */     int posX = 0;int posZ = 0;
/* 87:88 */     pos %= (this.separation + this.width);
/* 88:89 */     for (int i = 1; i < 1 + this.height; i++) {
/* 89:91 */       if ((pos == this.width) || (pos == 0)) {
/* 90:93 */         world.setBlock(x, y + i, z, this.block);
/* 91:95 */       } else if (pos < this.width) {
/* 92:97 */         world.setBlock(x, y + i, z, this.window);
/* 93:   */       }
/* 94:   */     }
/* 95:   */   }
/* 96:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.DecoratorWindow
 * JD-Core Version:    0.7.1
 */