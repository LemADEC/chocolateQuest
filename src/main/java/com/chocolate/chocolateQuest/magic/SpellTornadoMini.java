/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import net.minecraft.item.ItemStack;
/*  4:   */ 
/*  5:   */ public class SpellTornadoMini
/*  6:   */   extends SpellProjectile
/*  7:   */ {
/*  8:   */   public int getType()
/*  9:   */   {
/* 10:11 */     return 10;
/* 11:   */   }
/* 12:   */   
/* 13:   */   public int getCoolDown()
/* 14:   */   {
/* 15:16 */     return 40;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public int getRange(ItemStack itemstack)
/* 19:   */   {
/* 20:21 */     return 10;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public int getCastingTime()
/* 24:   */   {
/* 25:25 */     return 30;
/* 26:   */   }
/* 27:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.SpellTornadoMini
 * JD-Core Version:    0.7.1
 */