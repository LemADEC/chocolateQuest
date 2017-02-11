/*  1:   */ package com.chocolate.chocolateQuest.misc;
/*  2:   */ 
/*  3:   */ import net.minecraft.creativetab.CreativeTabs;
/*  4:   */ import net.minecraft.item.Item;
/*  5:   */ 
/*  6:   */ public class DungeonsItemsTab
/*  7:   */   extends CreativeTabs
/*  8:   */ {
/*  9:   */   Item item;
/* 10:   */   
/* 11:   */   public DungeonsItemsTab(String label)
/* 12:   */   {
/* 13:11 */     super(label);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public void setItemIcon(Item parItem)
/* 17:   */   {
/* 18:16 */     this.item = parItem;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public Item getTabIconItem()
/* 22:   */   {
/* 23:21 */     return this.item;
/* 24:   */   }
/* 25:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.misc.DungeonsItemsTab
 * JD-Core Version:    0.7.1
 */