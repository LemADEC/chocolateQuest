/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import java.util.Comparator;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ 
/*   7:    */ class NearestAttackableTargetSorter
/*   8:    */   implements Comparator<Entity>
/*   9:    */ {
/*  10:    */   private EntityLivingBase targetEntity;
/*  11:    */   
/*  12:    */   public NearestAttackableTargetSorter(EntityLivingBase owner)
/*  13:    */   {
/*  14:115 */     this.targetEntity = owner;
/*  15:    */   }
/*  16:    */   
/*  17:    */   public int compare(Entity o1, Entity o2)
/*  18:    */   {
/*  19:120 */     double dist1 = o1.getDistanceSqToEntity(this.targetEntity);
/*  20:121 */     double dist2 = o2.getDistanceSqToEntity(this.targetEntity);
/*  21:122 */     return dist1 == dist2 ? 0 : dist1 < dist2 ? -1 : 1;
/*  22:    */   }
/*  23:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.NearestAttackableTargetSorter
 * JD-Core Version:    0.7.1
 */