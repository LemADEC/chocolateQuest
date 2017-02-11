/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.SharedMonsterAttributes;
/*  7:   */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  8:   */ import net.minecraft.entity.projectile.EntityArrow;
/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ import net.minecraft.util.MathHelper;
/* 11:   */ import net.minecraft.world.World;
/* 12:   */ 
/* 13:   */ public class HandBow
/* 14:   */   extends HandRanged
/* 15:   */ {
/* 16:   */   public HandBow(EntityHumanBase owner, ItemStack itemStack)
/* 17:   */   {
/* 18:13 */     super(owner, itemStack);
/* 19:14 */     this.range = 4096.0F;
/* 20:15 */     this.aimDelay = 10;
/* 21:16 */     this.isMeleWeapon = false;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void doRangeAttack(Entity target, float f)
/* 25:   */   {
/* 26:19 */     EntityArrow arrow = new EntityArrow(this.owner.worldObj, this.owner, 0.0F);
/* 27:20 */     arrow.setPosition(this.owner.posX, this.owner.posY + this.owner.getEyeHeight(), this.owner.posZ);
/* 28:21 */     if (!this.owner.worldObj.isRemote)
/* 29:   */     {
/* 30:23 */       if (target.posY < this.owner.posY) {
/* 31:25 */         arrow.setIsCritical(true);
/* 32:   */       }
/* 33:27 */       float distFactor = f / 10.0F;
/* 34:28 */       double arrowMotionX = target.posX - this.owner.posX + target.motionX * distFactor;
/* 35:29 */       double arrowMotionZ = target.posZ - this.owner.posZ + target.motionZ * distFactor;
/* 36:30 */       double d2 = target.posY + target.getEyeHeight() - 0.699999988079071D - arrow.posY;
/* 37:31 */       float dist = MathHelper.sqrt_float(f);
/* 38:32 */       float hFact = dist;
/* 39:33 */       if (dist < 16.0F) {
/* 40:34 */         hFact *= 0.38F;
/* 41:   */       }
/* 42:35 */       this.owner.worldObj.playSoundAtEntity(this.owner, "random.bow", 1.0F, 1.0F / (this.owner.getRNG().nextFloat() * 0.4F + 0.8F));
/* 43:36 */       float damage = (float)this.owner.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue() * 2.0F;
/* 44:37 */       arrow.setThrowableHeading(arrowMotionX, d2 + hFact, arrowMotionZ, 1.0F, this.owner.accuracy);
/* 45:38 */       arrow.setDamage(damage);
/* 46:39 */       arrow.motionX *= Math.max(1.0F, hFact / 18.0F);
/* 47:40 */       arrow.motionZ *= Math.max(1.0F, hFact / 18.0F);
/* 48:41 */       this.owner.worldObj.spawnEntityInWorld(arrow);
/* 49:   */     }
/* 50:   */   }
/* 51:   */   
/* 52:   */   public double getDistanceToStopAdvancing()
/* 53:   */   {
/* 54:47 */     return getRange();
/* 55:   */   }
/* 56:   */   
/* 57:   */   public double getRange()
/* 58:   */   {
/* 59:51 */     return this.range;
/* 60:   */   }
/* 61:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandBow
 * JD-Core Version:    0.7.1
 */