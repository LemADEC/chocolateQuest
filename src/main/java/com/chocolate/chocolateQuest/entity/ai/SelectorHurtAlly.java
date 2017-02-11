/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.command.IEntitySelector;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ 
/*  8:   */ class SelectorHurtAlly
/*  9:   */   implements IEntitySelector
/* 10:   */ {
/* 11:   */   EntityHumanBase taskOwner;
/* 12:   */   
/* 13:   */   public SelectorHurtAlly(EntityHumanBase owner)
/* 14:   */   {
/* 15:30 */     this.taskOwner = owner;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public boolean isEntityApplicable(Entity parEntity)
/* 19:   */   {
/* 20:34 */     if ((!(parEntity instanceof EntityLivingBase)) || (parEntity == this.taskOwner)) {
/* 21:35 */       return false;
/* 22:   */     }
/* 23:36 */     EntityLivingBase entity = (EntityLivingBase)parEntity;
/* 24:37 */     if (this.taskOwner.isSuitableTargetAlly(entity)) {
/* 25:39 */       return entity.getHealth() < entity.getMaxHealth();
/* 26:   */     }
/* 27:41 */     return false;
/* 28:   */   }
/* 29:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.SelectorHurtAlly
 * JD-Core Version:    0.7.1
 */