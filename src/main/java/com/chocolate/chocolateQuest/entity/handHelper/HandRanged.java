/*   1:    */ package com.chocolate.chocolateQuest.entity.handHelper;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.IRangedWeapon;
/*   4:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   5:    */ import java.util.Random;
/*   6:    */ import net.minecraft.entity.Entity;
/*   7:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*   8:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*   9:    */ import net.minecraft.entity.projectile.EntityArrow;
/*  10:    */ import net.minecraft.item.Item;
/*  11:    */ import net.minecraft.item.ItemStack;
/*  12:    */ import net.minecraft.util.MathHelper;
/*  13:    */ import net.minecraft.world.World;
/*  14:    */ 
/*  15:    */ public class HandRanged
/*  16:    */   extends HandHelper
/*  17:    */ {
/*  18: 13 */   boolean aiming = false;
/*  19:    */   boolean isMeleWeapon;
/*  20:    */   protected float range;
/*  21:    */   protected int aimDelay;
/*  22:    */   protected int aimingTime;
/*  23:    */   protected int aimDelayTime;
/*  24:    */   IRangedWeapon rangedWeapon;
/*  25:    */   
/*  26:    */   public HandRanged(EntityHumanBase owner, ItemStack itemStack)
/*  27:    */   {
/*  28: 21 */     super(owner, itemStack);
/*  29: 22 */     if ((this.currentItem.getItem() instanceof IRangedWeapon))
/*  30:    */     {
/*  31: 23 */       this.rangedWeapon = ((IRangedWeapon)this.currentItem.getItem());
/*  32: 24 */       this.range = this.rangedWeapon.getRange(owner, itemStack);
/*  33: 25 */       this.aimDelay = this.rangedWeapon.getCooldown(owner, itemStack);
/*  34: 26 */       this.isMeleWeapon = this.rangedWeapon.isMeleeWeapon(owner, itemStack);
/*  35:    */     }
/*  36:    */     else
/*  37:    */     {
/*  38: 28 */       this.range = 4096.0F;
/*  39: 29 */       this.aimDelay = 10;
/*  40: 30 */       this.isMeleWeapon = false;
/*  41:    */     }
/*  42:    */   }
/*  43:    */   
/*  44:    */   public boolean attackWithRange(Entity target, float f)
/*  45:    */   {
/*  46: 36 */     if (f < getRange())
/*  47:    */     {
/*  48: 38 */       if (isAiming())
/*  49:    */       {
/*  50: 40 */         if (this.aimingTime <= 0)
/*  51:    */         {
/*  52: 42 */           doRangeAttack(target, f);
/*  53: 43 */           this.owner.setAiming(this, false);
/*  54: 44 */           this.aimDelayTime = this.aimDelay;
/*  55:    */         }
/*  56:    */       }
/*  57: 47 */       else if (this.aimDelayTime <= 0)
/*  58:    */       {
/*  59: 49 */         int aimTime = getAimTime(target);
/*  60: 50 */         if (aimTime >= 0)
/*  61:    */         {
/*  62: 51 */           this.owner.setAiming(this, true);
/*  63: 52 */           this.aimingTime = aimTime;
/*  64:    */         }
/*  65:    */       }
/*  66: 55 */       return true;
/*  67:    */     }
/*  68: 57 */     return false;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public int getAimTime(Entity target)
/*  72:    */   {
/*  73: 61 */     if (this.rangedWeapon != null) {
/*  74: 62 */       return this.rangedWeapon.startAiming(this.currentItem, this.owner, target);
/*  75:    */     }
/*  76: 63 */     return this.owner.getAttackSpeed();
/*  77:    */   }
/*  78:    */   
/*  79:    */   public void doRangeAttack(Entity target, float f)
/*  80:    */   {
/*  81: 67 */     if ((this.currentItem.getItem() instanceof IRangedWeapon))
/*  82:    */     {
/*  83: 69 */       ((IRangedWeapon)this.currentItem.getItem()).shootFromEntity(this.owner, this.currentItem, this.owner.getHandAngle(this), target);
/*  84:    */     }
/*  85:    */     else
/*  86:    */     {
/*  87: 72 */       EntityArrow arrow = new EntityArrow(this.owner.worldObj, this.owner, 0.0F);
/*  88: 73 */       arrow.setPosition(this.owner.posX, this.owner.posY + this.owner.getEyeHeight(), this.owner.posZ);
/*  89: 74 */       if (!this.owner.worldObj.isRemote)
/*  90:    */       {
/*  91: 76 */         if (target.posY < this.owner.posY) {
/*  92: 78 */           arrow.setIsCritical(true);
/*  93:    */         }
/*  94: 80 */         float distFactor = f / 10.0F;
/*  95: 81 */         double arrowMotionX = target.posX - this.owner.posX + target.motionX * distFactor;
/*  96: 82 */         double arrowMotionZ = target.posZ - this.owner.posZ + target.motionZ * distFactor;
/*  97: 83 */         double d2 = target.posY + target.getEyeHeight() - 0.699999988079071D - arrow.posY;
/*  98: 84 */         float dist = MathHelper.sqrt_float(f);
/*  99: 85 */         float hFact = dist;
/* 100: 86 */         if (dist < 16.0F) {
/* 101: 87 */           hFact *= 0.38F;
/* 102:    */         }
/* 103: 88 */         this.owner.worldObj.playSoundAtEntity(this.owner, "random.bow", 1.0F, 1.0F / (this.owner.getRNG().nextFloat() * 0.4F + 0.8F));
/* 104: 89 */         float damage = (float)this.owner.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue() * 2.0F;
/* 105: 90 */         arrow.setThrowableHeading(arrowMotionX, d2 + hFact, arrowMotionZ, 1.0F, this.owner.accuracy);
/* 106: 91 */         arrow.setDamage(damage);
/* 107: 92 */         arrow.motionX *= Math.max(1.0F, hFact / 18.0F);
/* 108: 93 */         arrow.motionZ *= Math.max(1.0F, hFact / 18.0F);
/* 109: 94 */         this.owner.worldObj.spawnEntityInWorld(arrow);
/* 110:    */       }
/* 111:    */     }
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void onUpdate()
/* 115:    */   {
/* 116:101 */     if ((isAiming()) && 
/* 117:102 */       (this.rangedWeapon != null) && 
/* 118:103 */       (this.rangedWeapon.shouldUpdate(this.owner))) {
/* 119:104 */       this.currentItem.getItem().onUpdate(this.currentItem, this.owner.worldObj, this.owner, this.owner.getHandAngle(this), false);
/* 120:    */     }
/* 121:107 */     if (this.aimDelayTime > 0) {
/* 122:108 */       this.aimDelayTime -= 1;
/* 123:    */     }
/* 124:109 */     if (this.aimingTime >= -10) {
/* 125:110 */       this.aimingTime -= 1;
/* 126:111 */     } else if ((isAiming()) && 
/* 127:112 */       (!this.owner.worldObj.isRemote)) {
/* 128:113 */       this.owner.setAiming(this, false);
/* 129:    */     }
/* 130:115 */     super.onUpdate();
/* 131:    */   }
/* 132:    */   
/* 133:    */   public void setAiming(boolean aim)
/* 134:    */   {
/* 135:119 */     this.aiming = aim;
/* 136:120 */     this.aimingTime = 0;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public boolean isAiming()
/* 140:    */   {
/* 141:125 */     return this.aiming;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public boolean isRanged()
/* 145:    */   {
/* 146:129 */     return true;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public double getDistanceToStopAdvancing()
/* 150:    */   {
/* 151:134 */     return this.isMeleWeapon ? super.getDistanceToStopAdvancing() : getRange();
/* 152:    */   }
/* 153:    */   
/* 154:    */   public double getMaxRangeForAttack()
/* 155:    */   {
/* 156:139 */     return getRange();
/* 157:    */   }
/* 158:    */   
/* 159:    */   public double getRange()
/* 160:    */   {
/* 161:143 */     if (this.rangedWeapon != null) {
/* 162:144 */       return this.rangedWeapon.getRange(this.owner, this.currentItem);
/* 163:    */     }
/* 164:145 */     return this.range;
/* 165:    */   }
/* 166:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandRanged
 * JD-Core Version:    0.7.1
 */