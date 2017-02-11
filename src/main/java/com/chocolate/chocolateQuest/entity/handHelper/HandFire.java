/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ 
/*  7:   */ public class HandFire
/*  8:   */   extends HandHelper
/*  9:   */ {
/* 10:   */   public HandFire(EntityHumanBase owner, ItemStack itemStack)
/* 11:   */   {
/* 12:10 */     super(owner, itemStack);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void onUpdate()
/* 16:   */   {
/* 17:14 */     super.onUpdate();
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void attackEntity(Entity entity)
/* 21:   */   {
/* 22:18 */     entity.setFire(4);
/* 23:19 */     super.attackEntity(entity);
/* 24:   */   }
/* 25:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandFire
 * JD-Core Version:    0.7.1
 */