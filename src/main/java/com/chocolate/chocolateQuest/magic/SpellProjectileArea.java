/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import net.minecraft.item.ItemStack;
/*  4:   */ 
/*  5:   */ public class SpellProjectileArea
/*  6:   */   extends SpellProjectile
/*  7:   */ {
/*  8:   */   public int getType()
/*  9:   */   {
/* 10:10 */     return 102;
/* 11:   */   }
/* 12:   */   
/* 13:   */   public int getCoolDown()
/* 14:   */   {
/* 15:15 */     return 100;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public int getCastingTime()
/* 19:   */   {
/* 20:19 */     return 30;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public int getRange(ItemStack itemstack)
/* 24:   */   {
/* 25:24 */     return 8;
/* 26:   */   }
/* 27:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.SpellProjectileArea
 * JD-Core Version:    0.7.1
 */