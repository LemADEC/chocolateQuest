/*  1:   */ package com.chocolate.chocolateQuest.block;
/*  2:   */ 
/*  3:   */ import net.minecraft.block.Block;
/*  4:   */ import net.minecraft.item.ItemBlock;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ 
/*  7:   */ public class ItemBlockDecoration
/*  8:   */   extends ItemBlock
/*  9:   */ {
/* 10:   */   public ItemBlockDecoration(Block block)
/* 11:   */   {
/* 12:11 */     super(block);
/* 13:12 */     setHasSubtypes(true);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public int getMetadata(int damageValue)
/* 17:   */   {
/* 18:17 */     return damageValue;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public String getItemStackDisplayName(ItemStack itemstack)
/* 22:   */   {
/* 23:37 */     return ((BlockDecoration)this.field_150939_a).getBlockName(itemstack.getItemDamage());
/* 24:   */   }
/* 25:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.ItemBlockDecoration
 * JD-Core Version:    0.7.1
 */