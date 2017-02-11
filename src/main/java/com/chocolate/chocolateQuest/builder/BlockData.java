/*   1:    */ package com.chocolate.chocolateQuest.builder;
/*   2:    */ 
/*   3:    */ import net.minecraft.block.Block;
/*   4:    */ 
/*   5:    */ class BlockData
/*   6:    */ {
/*   7:    */   public int x;
/*   8:    */   public int y;
/*   9:    */   public int z;
/*  10:    */   public Block block;
/*  11:    */   public int blockMetadata;
/*  12:    */   
/*  13:    */   public BlockData(int x, int y, int z, Block block, int blockMetada)
/*  14:    */   {
/*  15:516 */     this.x = x;
/*  16:517 */     this.y = y;
/*  17:518 */     this.z = z;
/*  18:519 */     this.block = block;
/*  19:520 */     this.blockMetadata = blockMetada;
/*  20:    */   }
/*  21:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.BlockData
 * JD-Core Version:    0.7.1
 */