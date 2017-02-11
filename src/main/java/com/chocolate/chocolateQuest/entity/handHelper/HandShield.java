/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ 
/*  7:   */ public class HandShield
/*  8:   */   extends HandHelper
/*  9:   */ {
/* 10:   */   public HandShield(EntityHumanBase owner, ItemStack itemStack)
/* 11:   */   {
/* 12:10 */     super(owner, itemStack);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public boolean canBlock()
/* 16:   */   {
/* 17:15 */     return true;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void onUpdate() {}
/* 21:   */   
/* 22:   */   public void attackEntity(Entity entity) {}
/* 23:   */   
/* 24:   */   public boolean attackWithRange(Entity target, float f)
/* 25:   */   {
/* 26:28 */     return false;
/* 27:   */   }
/* 28:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandShield
 * JD-Core Version:    0.7.1
 */