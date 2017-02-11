/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import net.minecraft.command.IEntitySelector;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityCreature;
/*   6:    */ import net.minecraft.entity.EntityLivingBase;
/*   7:    */ 
/*   8:    */ class SelectorLiving
/*   9:    */   implements IEntitySelector
/*  10:    */ {
/*  11:    */   EntityCreature taskOwner;
/*  12:    */   AITargetNearestAttackableForDragon ownerAI;
/*  13:    */   IEntitySelector selector;
/*  14:    */   
/*  15:    */   public SelectorLiving(EntityCreature par1EntityCreature, AITargetNearestAttackableForDragon par2, IEntitySelector parSelector)
/*  16:    */   {
/*  17: 97 */     this.taskOwner = par1EntityCreature;
/*  18: 98 */     this.ownerAI = par2;
/*  19: 99 */     this.selector = parSelector;
/*  20:    */   }
/*  21:    */   
/*  22:    */   public boolean isEntityApplicable(Entity entity)
/*  23:    */   {
/*  24:104 */     boolean flag = true;
/*  25:105 */     if (this.selector != null) {
/*  26:106 */       flag = this.selector.isEntityApplicable(entity);
/*  27:    */     }
/*  28:107 */     if (((entity instanceof EntityLivingBase)) && (flag)) {
/*  29:108 */       return this.ownerAI.isSuitableTarget((EntityLivingBase)entity, false);
/*  30:    */     }
/*  31:109 */     return false;
/*  32:    */   }
/*  33:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.SelectorLiving
 * JD-Core Version:    0.7.1
 */