/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.items.swords.ItemBaseBroadSword;
/*  4:   */ import net.minecraft.item.ItemStack;
/*  5:   */ 
/*  6:   */ public class AwakementBigSword
/*  7:   */   extends Awakements
/*  8:   */ {
/*  9:   */   public AwakementBigSword(String name, int icon)
/* 10:   */   {
/* 11:12 */     super(name, icon);
/* 12:   */   }
/* 13:   */   
/* 14:   */   public boolean canBeUsedOnItem(ItemStack is)
/* 15:   */   {
/* 16:17 */     return is.getItem() instanceof ItemBaseBroadSword;
/* 17:   */   }
/* 18:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.AwakementBigSword
 * JD-Core Version:    0.7.1
 */