/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import com.chocolate.chocolateQuest.items.gun.ItemGolemWeapon;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.item.ItemStack;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ 
/*  9:   */ public class HandGolem
/* 10:   */   extends HandRanged
/* 11:   */ {
/* 12:   */   public HandGolem(EntityHumanBase owner, ItemStack itemStack)
/* 13:   */   {
/* 14:11 */     super(owner, itemStack);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void doRangeAttack(Entity target, float f)
/* 18:   */   {
/* 19:15 */     if ((this.currentItem.getItem() instanceof ItemGolemWeapon)) {
/* 20:16 */       ((ItemGolemWeapon)this.currentItem.getItem()).shootFromGolem(this.owner, this.currentItem, this.owner.getHandAngle(this), target);
/* 21:   */     }
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void onUpdate()
/* 25:   */   {
/* 26:22 */     if ((isAiming()) && 
/* 27:23 */       (!this.owner.worldObj.isRemote) && 
/* 28:24 */       (this.owner.getAttackTarget() == null) && (this.owner.riddenByEntity == null)) {
/* 29:25 */       this.owner.setAiming(this, false);
/* 30:   */     }
/* 31:29 */     super.onUpdate();
/* 32:   */   }
/* 33:   */   
/* 34:   */   public boolean attackWithRange(Entity target, float f)
/* 35:   */   {
/* 36:33 */     if (this.owner.riddenByEntity == null) {
/* 37:34 */       return super.attackWithRange(target, f);
/* 38:   */     }
/* 39:35 */     return true;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void attackEntity(Entity entity)
/* 43:   */   {
/* 44:39 */     this.attackTime = 10;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public boolean isRanged()
/* 48:   */   {
/* 49:43 */     return true;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void onClick()
/* 53:   */   {
/* 54:46 */     if (this.aimDelayTime <= 0)
/* 55:   */     {
/* 56:47 */       if ((this.currentItem.getItem() instanceof ItemGolemWeapon)) {
/* 57:48 */         ((ItemGolemWeapon)this.currentItem.getItem()).shootFromGolem(this.owner, this.currentItem, this.owner.getHandAngle(this), null);
/* 58:   */       }
/* 59:50 */       this.aiming = true;
/* 60:51 */       this.aimingTime = (this.owner.getAttackSpeed() + 20);
/* 61:52 */       this.aimDelayTime = ((ItemGolemWeapon)this.currentItem.getItem()).getCooldown(this.currentItem);
/* 62:   */     }
/* 63:   */   }
/* 64:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandGolem
 * JD-Core Version:    0.7.1
 */