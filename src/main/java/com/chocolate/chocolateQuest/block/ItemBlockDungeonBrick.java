/*  1:   */ package com.chocolate.chocolateQuest.block;
/*  2:   */ 
/*  3:   */ import net.minecraft.block.Block;
/*  4:   */ import net.minecraft.item.ItemBlock;
/*  5:   */ 
/*  6:   */ public class ItemBlockDungeonBrick
/*  7:   */   extends ItemBlock
/*  8:   */ {
/*  9:   */   public ItemBlockDungeonBrick(Block block)
/* 10:   */   {
/* 11:10 */     super(block);
/* 12:11 */     setHasSubtypes(true);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public int getMetadata(int damageValue)
/* 16:   */   {
/* 17:17 */     return damageValue;
/* 18:   */   }
/* 19:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.ItemBlockDungeonBrick
 * JD-Core Version:    0.7.1
 */