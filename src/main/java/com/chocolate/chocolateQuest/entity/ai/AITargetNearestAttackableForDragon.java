/*  1:   */ package com.chocolate.chocolateQuest.entity.ai;
/*  2:   */ 
/*  3:   */ import java.util.Collections;
/*  4:   */ import java.util.List;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.command.IEntitySelector;
/*  7:   */ import net.minecraft.entity.EntityCreature;
/*  8:   */ import net.minecraft.entity.EntityLivingBase;
/*  9:   */ import net.minecraft.entity.ai.EntityAITarget;
/* 10:   */ import net.minecraft.util.AxisAlignedBB;
/* 11:   */ import net.minecraft.world.World;
/* 12:   */ 
/* 13:   */ public class AITargetNearestAttackableForDragon
/* 14:   */   extends EntityAITarget
/* 15:   */ {
/* 16:   */   private final Class targetClass;
/* 17:   */   private final int targetChance;
/* 18:   */   private final NearestAttackableTargetSorter theNearestAttackableTargetSorter;
/* 19:   */   private final IEntitySelector targetEntitySelector;
/* 20:   */   private EntityLivingBase targetEntity;
/* 21:   */   
/* 22:   */   public AITargetNearestAttackableForDragon(EntityCreature par1EntityCreature, Class par2Class, int par3, boolean par4)
/* 23:   */   {
/* 24:30 */     this(par1EntityCreature, par2Class, par3, par4, false);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public AITargetNearestAttackableForDragon(EntityCreature par1EntityCreature, Class par2Class, int par3, boolean par4, boolean par5)
/* 28:   */   {
/* 29:35 */     this(par1EntityCreature, par2Class, par3, par4, par5, null);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public AITargetNearestAttackableForDragon(EntityCreature par1EntityCreature, Class par2Class, int par3, boolean par4, boolean par5, IEntitySelector par6IEntitySelector)
/* 33:   */   {
/* 34:40 */     super(par1EntityCreature, par4, par5);
/* 35:41 */     this.targetClass = par2Class;
/* 36:42 */     this.targetChance = par3;
/* 37:43 */     this.theNearestAttackableTargetSorter = new NearestAttackableTargetSorter(par1EntityCreature);
/* 38:44 */     setMutexBits(1);
/* 39:45 */     this.targetEntitySelector = new SelectorLiving(par1EntityCreature, this, par6IEntitySelector);
/* 40:   */   }
/* 41:   */   
/* 42:   */   public boolean isSuitableTarget(EntityLivingBase par1EntityLivingBase, boolean par2)
/* 43:   */   {
/* 44:51 */     return super.isSuitableTarget(par1EntityLivingBase, par2);
/* 45:   */   }
/* 46:   */   
/* 47:   */   public boolean shouldExecute()
/* 48:   */   {
/* 49:58 */     if ((this.targetChance > 0) && (this.taskOwner.getRNG().nextInt(this.targetChance) != 0)) {
/* 50:60 */       return false;
/* 51:   */     }
/* 52:64 */     double d0 = getTargetDistance();
/* 53:65 */     List list = this.taskOwner.worldObj.selectEntitiesWithinAABB(this.targetClass, this.taskOwner.boundingBox.expand(d0, 10.0D, d0), this.targetEntitySelector);
/* 54:66 */     Collections.sort(list, this.theNearestAttackableTargetSorter);
/* 55:68 */     if (list.isEmpty()) {
/* 56:70 */       return false;
/* 57:   */     }
/* 58:74 */     this.targetEntity = ((EntityLivingBase)list.get(0));
/* 59:75 */     return true;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void startExecuting()
/* 63:   */   {
/* 64:85 */     this.taskOwner.setAttackTarget(this.targetEntity);
/* 65:86 */     super.startExecuting();
/* 66:   */   }
/* 67:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AITargetNearestAttackableForDragon
 * JD-Core Version:    0.7.1
 */