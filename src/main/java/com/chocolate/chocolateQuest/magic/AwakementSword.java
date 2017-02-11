/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.items.swords.ItemBaseSwordDefensive;
/*  4:   */ import net.minecraft.item.ItemStack;
/*  5:   */ 
/*  6:   */ public class AwakementSword
/*  7:   */   extends Awakements
/*  8:   */ {
/*  9:   */   public AwakementSword(String name, int icon)
/* 10:   */   {
/* 11: 9 */     super(name, icon);
/* 12:   */   }
/* 13:   */   
/* 14:   */   public boolean canBeUsedOnItem(ItemStack is)
/* 15:   */   {
/* 16:13 */     return is.getItem() instanceof ItemBaseSwordDefensive;
/* 17:   */   }
/* 18:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.AwakementSword
 * JD-Core Version:    0.7.1
 */