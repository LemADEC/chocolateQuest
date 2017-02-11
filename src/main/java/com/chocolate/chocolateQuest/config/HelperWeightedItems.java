/*  1:   */ package com.chocolate.chocolateQuest.config;
/*  2:   */ 
/*  3:   */ import net.minecraft.init.Items;
/*  4:   */ import net.minecraft.item.Item;
/*  5:   */ 
/*  6:   */ public class HelperWeightedItems
/*  7:   */ {
/*  8: 8 */   public Item[] items = { Items.apple };
/*  9: 9 */   public int[] number = { 1 };
/* 10:10 */   public int[] damage = { 0 };
/* 11:11 */   public int[] weight = { 1 };
/* 12:   */   
/* 13:   */   public HelperWeightedItems(Item[] items, int[] number, int[] damage, int[] weight)
/* 14:   */   {
/* 15:15 */     this.items = items;
/* 16:16 */     this.number = number;
/* 17:17 */     this.damage = damage;
/* 18:18 */     this.weight = weight;
/* 19:   */   }
/* 20:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.config.HelperWeightedItems
 * JD-Core Version:    0.7.1
 */