/*   1:    */ package com.chocolate.chocolateQuest.builder.decorator;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.block.Block;
/*   5:    */ import net.minecraft.init.Blocks;
/*   6:    */ import net.minecraft.world.World;
/*   7:    */ 
/*   8:    */ public class DecoratorFloor
/*   9:    */ {
/*  10:    */   int decoBlock1;
/*  11:    */   int decoBlock2;
/*  12: 13 */   int floorType = 0;
/*  13: 14 */   public Block block = Blocks.stained_hardened_clay;
/*  14:    */   
/*  15:    */   public DecoratorFloor(Random random)
/*  16:    */   {
/*  17: 17 */     this.decoBlock1 = random.nextInt(16);
/*  18: 18 */     this.decoBlock2 = random.nextInt(16);
/*  19: 19 */     if (this.decoBlock1 == this.decoBlock2)
/*  20:    */     {
/*  21: 21 */       this.decoBlock2 += 1;
/*  22: 22 */       if (this.decoBlock2 > 15) {
/*  23: 23 */         this.decoBlock2 = 0;
/*  24:    */       }
/*  25:    */     }
/*  26: 25 */     this.floorType = random.nextInt(6);
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void generateFloor(World world, int x, int y, int z)
/*  30:    */   {
/*  31: 30 */     switch (this.floorType)
/*  32:    */     {
/*  33:    */     case 1: 
/*  34: 32 */       cross(world, x, y, z);
/*  35: 33 */       break;
/*  36:    */     case 2: 
/*  37: 35 */       diamond(world, x, y, z);
/*  38: 36 */       break;
/*  39:    */     case 3: 
/*  40: 38 */       cross(world, x, y, z);
/*  41: 39 */       break;
/*  42:    */     case 4: 
/*  43: 41 */       diamond(world, x, y, z);
/*  44: 42 */       break;
/*  45:    */     case 5: 
/*  46: 44 */       flat(world, x, y, z);
/*  47: 45 */       break;
/*  48:    */     default: 
/*  49: 48 */       chess(world, x, y, z);
/*  50:    */     }
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void flat(World world, int x, int y, int z)
/*  54:    */   {
/*  55: 54 */     world.setBlock(x, y, z, this.block, this.decoBlock1, 3);
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void chess(World world, int x, int y, int z)
/*  59:    */   {
/*  60: 62 */     int ax = Math.abs(x);
/*  61: 63 */     int az = Math.abs(z);
/*  62: 64 */     if (ax % 2 == az % 2) {
/*  63: 66 */       world.setBlock(x, y, z, this.block, this.decoBlock1, 3);
/*  64:    */     } else {
/*  65: 68 */       world.setBlock(x, y, z, this.block, this.decoBlock2, 3);
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void square(World world, int x, int y, int z)
/*  70:    */   {
/*  71: 77 */     if (x % 2 == z % 2) {
/*  72: 79 */       world.setBlock(x, y, z, this.block, this.decoBlock1, 3);
/*  73:    */     } else {
/*  74: 81 */       world.setBlock(x, y, z, this.block, this.decoBlock2, 3);
/*  75:    */     }
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void cross(World world, int x, int y, int z)
/*  79:    */   {
/*  80: 90 */     int ax = Math.abs(x);
/*  81: 91 */     int az = Math.abs(z);
/*  82: 92 */     if ((ax % 3 == 1) || (az % 3 == 1)) {
/*  83: 94 */       world.setBlock(x, y, z, this.block, this.decoBlock1, 3);
/*  84:    */     } else {
/*  85: 96 */       world.setBlock(x, y, z, this.block, this.decoBlock2, 3);
/*  86:    */     }
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void diamond(World world, int x, int y, int z)
/*  90:    */   {
/*  91:106 */     int ax = Math.abs(x);
/*  92:107 */     int az = Math.abs(z);
/*  93:108 */     if ((ax % 4 + az % 4 == 4) || (ax % 4 == az % 4)) {
/*  94:110 */       world.setBlock(x, y, z, this.block, this.decoBlock1, 3);
/*  95:    */     } else {
/*  96:112 */       world.setBlock(x, y, z, this.block, this.decoBlock2, 3);
/*  97:    */     }
/*  98:    */   }
/*  99:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.DecoratorFloor
 * JD-Core Version:    0.7.1
 */