/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ 
/*  7:   */ public class HandDagger
/*  8:   */   extends HandHelper
/*  9:   */ {
/* 10:   */   public HandDagger(EntityHumanBase owner, ItemStack itemStack)
/* 11:   */   {
/* 12:10 */     super(owner, itemStack);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void onUpdate()
/* 16:   */   {
/* 17:15 */     super.onUpdate();
/* 18:16 */     if (this.owner.getAttackTarget() != null)
/* 19:   */     {
/* 20:17 */       EntityLivingBase target = this.owner.getAttackTarget();
/* 21:   */       
/* 22:19 */       float angle = this.owner.rotationYawHead - target.rotationYawHead;
/* 23:20 */       while (angle > 360.0F) {
/* 24:20 */         angle -= 360.0F;
/* 25:   */       }
/* 26:21 */       while (angle < 0.0F) {
/* 27:21 */         angle += 360.0F;
/* 28:   */       }
/* 29:22 */       angle = Math.abs(angle - 180.0F);
/* 30:23 */       if (Math.abs(angle) > 130.0F) {
/* 31:24 */         this.owner.setSneaking(true);
/* 32:   */       } else {
/* 33:26 */         this.owner.setSneaking(false);
/* 34:   */       }
/* 35:   */     }
/* 36:   */   }
/* 37:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandDagger
 * JD-Core Version:    0.7.1
 */