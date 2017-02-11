/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.items.swords.ItemBaseDagger;
/*  4:   */ import net.minecraft.item.ItemStack;
/*  5:   */ 
/*  6:   */ public class AwakementDagger
/*  7:   */   extends Awakements
/*  8:   */ {
/*  9:   */   public AwakementDagger(String name, int icon)
/* 10:   */   {
/* 11:10 */     super(name, icon);
/* 12:   */   }
/* 13:   */   
/* 14:   */   public boolean canBeUsedOnItem(ItemStack is)
/* 15:   */   {
/* 16:15 */     return is.getItem() instanceof ItemBaseDagger;
/* 17:   */   }
/* 18:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.AwakementDagger
 * JD-Core Version:    0.7.1
 */