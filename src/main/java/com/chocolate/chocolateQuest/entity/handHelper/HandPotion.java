/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.entity.projectile.EntityPotion;
/*  6:   */ import net.minecraft.item.ItemStack;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ 
/*  9:   */ public class HandPotion
/* 10:   */   extends HandRanged
/* 11:   */ {
/* 12:   */   public HandPotion(EntityHumanBase owner, ItemStack itemStack)
/* 13:   */   {
/* 14:11 */     super(owner, itemStack);
/* 15:12 */     this.range = 64.0F;
/* 16:13 */     this.aimDelay = 50;
/* 17:14 */     this.aimingTime = 30;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void onUpdate()
/* 21:   */   {
/* 22:18 */     super.onUpdate();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void doRangeAttack(Entity target, float f)
/* 26:   */   {
/* 27:22 */     if (!this.owner.worldObj.isRemote)
/* 28:   */     {
/* 29:23 */       EntityPotion e = new EntityPotion(this.owner.worldObj, this.owner, this.currentItem);
/* 30:24 */       e.motionX *= 1.2D;
/* 31:25 */       e.motionZ *= 1.2D;
/* 32:26 */       this.owner.worldObj.spawnEntityInWorld(e);
/* 33:27 */       this.owner.swingHand(this);
/* 34:   */     }
/* 35:   */   }
/* 36:   */   
/* 37:   */   public double getRange()
/* 38:   */   {
/* 39:32 */     return this.range;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public ItemStack getItem()
/* 43:   */   {
/* 44:36 */     if (!this.owner.isAiming()) {
/* 45:37 */       return null;
/* 46:   */     }
/* 47:38 */     return super.getItem();
/* 48:   */   }
/* 49:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandPotion
 * JD-Core Version:    0.7.1
 */