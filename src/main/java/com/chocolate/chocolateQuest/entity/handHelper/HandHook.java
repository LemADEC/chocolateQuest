/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import com.chocolate.chocolateQuest.entity.projectile.EntityHookShoot;
/*  5:   */ import com.chocolate.chocolateQuest.items.ItemHookShoot;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class HandHook
/* 11:   */   extends HandRanged
/* 12:   */ {
/* 13:   */   EntityHookShoot web;
/* 14:   */   ItemHookShoot is;
/* 15:   */   
/* 16:   */   public HandHook(EntityHumanBase owner, ItemStack itemStack)
/* 17:   */   {
/* 18:15 */     super(owner, itemStack);
/* 19:16 */     this.is = ((ItemHookShoot)itemStack.getItem());
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void doRangeAttack(Entity target, float f)
/* 23:   */   {
/* 24:19 */     if (this.web == null)
/* 25:   */     {
/* 26:21 */       int pos = 0;
/* 27:22 */       if (this.web != null)
/* 28:   */       {
/* 29:23 */         this.web.setDead();
/* 30:24 */         this.web = null;
/* 31:   */       }
/* 32:26 */       this.web = new EntityHookShoot(this.owner.worldObj, this.owner, this.is.getHookType());
/* 33:27 */       this.web.setThrowableHeading(target.posX - this.owner.posX, target.posY - this.owner.posY, target.posZ - this.owner.posZ, 1.0F, 0.0F);
/* 34:28 */       this.owner.worldObj.spawnEntityInWorld(this.web);
/* 35:   */     }
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void onUpdate()
/* 39:   */   {
/* 40:33 */     if (this.web != null) {
/* 41:35 */       if (this.web.isDead)
/* 42:   */       {
/* 43:36 */         this.web = null;
/* 44:   */       }
/* 45:37 */       else if (this.web.isReeling())
/* 46:   */       {
/* 47:38 */         boolean setHookDead = false;
/* 48:39 */         double webDist = this.owner.getDistanceSqToEntity(this.web);
/* 49:40 */         float width = this.owner.width + 1.0F;
/* 50:41 */         if (this.web.hookedEntity != null) {
/* 51:42 */           width += this.web.hookedEntity.width;
/* 52:43 */         } else if ((this.owner.getAttackTarget() != null) && 
/* 53:44 */           (webDist < this.owner.getDistanceSqToEntity(this.owner.getAttackTarget()))) {
/* 54:45 */           setHookDead = true;
/* 55:   */         }
/* 56:48 */         if (webDist < width * width) {
/* 57:49 */           setHookDead = true;
/* 58:   */         }
/* 59:50 */         if ((this.web.ticksExisted > 100) || (setHookDead))
/* 60:   */         {
/* 61:51 */           this.web.setDead();
/* 62:52 */           this.web = null;
/* 63:   */         }
/* 64:   */       }
/* 65:   */     }
/* 66:56 */     super.onUpdate();
/* 67:   */   }
/* 68:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandHook
 * JD-Core Version:    0.7.1
 */