/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.command.IEntitySelector;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ 
/*  8:   */ public class HumanSelector
/*  9:   */   implements IEntitySelector
/* 10:   */ {
/* 11:   */   EntityHumanBase taskOwner;
/* 12:   */   
/* 13:   */   public HumanSelector(EntityHumanBase owner)
/* 14:   */   {
/* 15:14 */     this.taskOwner = owner;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public boolean isEntityApplicable(Entity parEntity)
/* 19:   */   {
/* 20:18 */     if (!(parEntity instanceof EntityLivingBase)) {
/* 21:19 */       return false;
/* 22:   */     }
/* 23:20 */     EntityLivingBase entity = (EntityLivingBase)parEntity;
/* 24:21 */     if (this.taskOwner.isSuitableTargetAlly(entity)) {
/* 25:23 */       return false;
/* 26:   */     }
/* 27:25 */     return this.taskOwner.canSee(entity);
/* 28:   */   }
/* 29:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.HumanSelector
 * JD-Core Version:    0.7.1
 */