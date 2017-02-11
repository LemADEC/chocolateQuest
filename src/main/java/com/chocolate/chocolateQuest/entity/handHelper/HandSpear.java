/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.item.ItemStack;
/*  5:   */ 
/*  6:   */ public class HandSpear
/*  7:   */   extends HandHelper
/*  8:   */ {
/*  9:   */   public HandSpear(EntityHumanBase owner, ItemStack itemStack)
/* 10:   */   {
/* 11: 9 */     super(owner, itemStack);
/* 12:   */   }
/* 13:   */   
/* 14:   */   public double getAttackRangeBonus()
/* 15:   */   {
/* 16:13 */     return 1.0D;
/* 17:   */   }
/* 18:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandSpear
 * JD-Core Version:    0.7.1
 */