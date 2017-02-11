/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ 
/*  7:   */ public class HandEmpty
/*  8:   */   extends HandHelper
/*  9:   */ {
/* 10:   */   public HandEmpty(EntityHumanBase owner, ItemStack itemStack)
/* 11:   */   {
/* 12:10 */     super(owner, itemStack);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void onUpdate() {}
/* 16:   */   
/* 17:   */   public void attackEntity(Entity entity) {}
/* 18:   */   
/* 19:   */   public boolean attackWithRange(Entity target, float f)
/* 20:   */   {
/* 21:23 */     return false;
/* 22:   */   }
/* 23:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandEmpty
 * JD-Core Version:    0.7.1
 */