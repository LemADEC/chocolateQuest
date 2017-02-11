/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.API.IRangedWeapon;
/*  4:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.projectile.EntitySmallFireball;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ import net.minecraft.util.Vec3;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class HandFireChange
/* 12:   */   extends HandRanged
/* 13:   */ {
/* 14:   */   public HandFireChange(EntityHumanBase owner, ItemStack itemStack)
/* 15:   */   {
/* 16:12 */     super(owner, itemStack);
/* 17:13 */     this.range = 4096.0F;
/* 18:14 */     this.aimDelay = 3;
/* 19:15 */     this.isMeleWeapon = false;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void doRangeAttack(Entity target, float f)
/* 23:   */   {
/* 24:18 */     Vec3 vec = this.owner.getLookVec();
/* 25:19 */     EntitySmallFireball arrow = new EntitySmallFireball(this.owner.worldObj, this.owner, vec.xCoord, vec.yCoord, vec.zCoord);
/* 26:20 */     arrow.setPosition(this.owner.posX, this.owner.posY + this.owner.getEyeHeight(), this.owner.posZ);
/* 27:21 */     arrow.motionX = vec.xCoord;
/* 28:22 */     arrow.motionY = vec.yCoord;
/* 29:23 */     arrow.motionZ = vec.zCoord;
/* 30:24 */     arrow.accelerationX = (vec.xCoord * 0.01D);
/* 31:25 */     arrow.accelerationY = (vec.yCoord * 0.01D);
/* 32:26 */     arrow.accelerationZ = (vec.zCoord * 0.01D);
/* 33:27 */     this.owner.worldObj.spawnEntityInWorld(arrow);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public double getDistanceToStopAdvancing()
/* 37:   */   {
/* 38:31 */     return getRange();
/* 39:   */   }
/* 40:   */   
/* 41:   */   public double getRange()
/* 42:   */   {
/* 43:35 */     return this.range;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public int getAimTime(Entity target)
/* 47:   */   {
/* 48:39 */     if (this.rangedWeapon != null) {
/* 49:40 */       return this.rangedWeapon.startAiming(this.currentItem, this.owner, target);
/* 50:   */     }
/* 51:41 */     return this.owner.getAttackSpeed();
/* 52:   */   }
/* 53:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandFireChange
 * JD-Core Version:    0.7.1
 */