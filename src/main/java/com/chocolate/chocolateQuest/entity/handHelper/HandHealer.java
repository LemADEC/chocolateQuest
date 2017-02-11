/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import java.util.List;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ import net.minecraft.util.AxisAlignedBB;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class HandHealer
/* 12:   */   extends HandHelper
/* 13:   */ {
/* 14:   */   public HandHealer(EntityHumanBase owner, ItemStack itemStack)
/* 15:   */   {
/* 16:13 */     super(owner, itemStack);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void onUpdate()
/* 20:   */   {
/* 21:17 */     List<Entity> list = this.owner.worldObj.getEntitiesWithinAABBExcludingEntity(this.owner, this.owner.boundingBox.expand(16.0D, 1.0D, 16.0D));
/* 22:18 */     EntityLivingBase closest = null;
/* 23:19 */     double dist = 324.0D;
/* 24:20 */     for (Entity e : list) {
/* 25:21 */       if (((e instanceof EntityLivingBase)) && (isEntityApplicable(e)))
/* 26:   */       {
/* 27:22 */         double tDist = this.owner.getDistanceSqToEntity(e);
/* 28:23 */         if (tDist < dist)
/* 29:   */         {
/* 30:24 */           closest = (EntityLivingBase)e;
/* 31:25 */           dist = tDist;
/* 32:   */         }
/* 33:   */       }
/* 34:   */     }
/* 35:29 */     if (closest != null) {
/* 36:30 */       this.owner.setAttackTarget(closest);
/* 37:   */     }
/* 38:   */   }
/* 39:   */   
/* 40:   */   public boolean isHealer()
/* 41:   */   {
/* 42:35 */     return true;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public boolean isEntityApplicable(Entity parEntity)
/* 46:   */   {
/* 47:39 */     if ((!(parEntity instanceof EntityLivingBase)) || (parEntity == this.owner)) {
/* 48:40 */       return false;
/* 49:   */     }
/* 50:41 */     EntityLivingBase entity = (EntityLivingBase)parEntity;
/* 51:42 */     if (this.owner.isSuitableTargetAlly(entity)) {
/* 52:44 */       return entity.getHealth() < entity.getMaxHealth();
/* 53:   */     }
/* 54:46 */     return false;
/* 55:   */   }
/* 56:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandHealer
 * JD-Core Version:    0.7.1
 */