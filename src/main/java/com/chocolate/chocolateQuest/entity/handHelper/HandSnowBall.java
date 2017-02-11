/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.SharedMonsterAttributes;
/*  7:   */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  8:   */ import net.minecraft.entity.projectile.EntitySnowball;
/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ import net.minecraft.util.MathHelper;
/* 11:   */ import net.minecraft.world.World;
/* 12:   */ 
/* 13:   */ public class HandSnowBall
/* 14:   */   extends HandRanged
/* 15:   */ {
/* 16:   */   public HandSnowBall(EntityHumanBase owner, ItemStack itemStack)
/* 17:   */   {
/* 18:13 */     super(owner, itemStack);
/* 19:14 */     this.range = 4096.0F;
/* 20:15 */     this.aimDelay = 4;
/* 21:16 */     this.isMeleWeapon = false;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void doRangeAttack(Entity target, float f)
/* 25:   */   {
/* 26:19 */     EntitySnowball arrow = new EntitySnowball(this.owner.worldObj, this.owner);
/* 27:20 */     arrow.setPosition(this.owner.posX, this.owner.posY + this.owner.getEyeHeight(), this.owner.posZ);
/* 28:21 */     if (!this.owner.worldObj.isRemote)
/* 29:   */     {
/* 30:23 */       float distFactor = f / 10.0F;
/* 31:24 */       double arrowMotionX = target.posX - this.owner.posX + target.motionX * distFactor;
/* 32:25 */       double arrowMotionZ = target.posZ - this.owner.posZ + target.motionZ * distFactor;
/* 33:26 */       double d2 = target.posY + target.getEyeHeight() - 0.699999988079071D - arrow.posY;
/* 34:27 */       float dist = MathHelper.sqrt_float(f);
/* 35:28 */       float hFact = dist;
/* 36:29 */       if (dist < 16.0F) {
/* 37:30 */         hFact *= 0.28F;
/* 38:   */       }
/* 39:31 */       this.owner.worldObj.playSoundAtEntity(this.owner, "random.bow", 1.0F, 1.0F / (this.owner.getRNG().nextFloat() * 0.4F + 0.8F));
/* 40:32 */       float damage = (float)this.owner.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue() * 2.0F;
/* 41:33 */       arrow.setThrowableHeading(arrowMotionX, d2 + hFact, arrowMotionZ, 1.0F, this.owner.accuracy);
/* 42:34 */       arrow.motionX *= Math.max(1.0F, hFact / 18.0F);
/* 43:35 */       arrow.motionZ *= Math.max(1.0F, hFact / 18.0F);
/* 44:36 */       this.owner.worldObj.spawnEntityInWorld(arrow);
/* 45:   */     }
/* 46:   */   }
/* 47:   */   
/* 48:   */   public double getDistanceToStopAdvancing()
/* 49:   */   {
/* 50:41 */     return getRange();
/* 51:   */   }
/* 52:   */   
/* 53:   */   public double getRange()
/* 54:   */   {
/* 55:45 */     return this.range;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public int getAimTime(Entity target)
/* 59:   */   {
/* 60:49 */     return 10;
/* 61:   */   }
/* 62:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandSnowBall
 * JD-Core Version:    0.7.1
 */