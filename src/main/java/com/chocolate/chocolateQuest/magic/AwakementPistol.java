/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.items.gun.ItemPistol;
/*  4:   */ import net.minecraft.item.ItemStack;
/*  5:   */ 
/*  6:   */ public class AwakementPistol
/*  7:   */   extends Awakements
/*  8:   */ {
/*  9: 8 */   int maxLevel = 4;
/* 10:   */   
/* 11:   */   public AwakementPistol(String name, int icon)
/* 12:   */   {
/* 13:10 */     this(name, icon, 4);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public int getMaxLevel()
/* 17:   */   {
/* 18:14 */     return this.maxLevel;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public AwakementPistol(String name, int icon, int maxLevel)
/* 22:   */   {
/* 23:17 */     super(name, icon);
/* 24:18 */     this.maxLevel = maxLevel;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public boolean canBeUsedOnItem(ItemStack is)
/* 28:   */   {
/* 29:23 */     return is.getItem() instanceof ItemPistol;
/* 30:   */   }
/* 31:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.AwakementPistol
 * JD-Core Version:    0.7.1
 */