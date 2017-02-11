/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import java.util.List;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ import net.minecraft.potion.Potion;
/*  9:   */ import net.minecraft.potion.PotionEffect;
/* 10:   */ import net.minecraft.util.AxisAlignedBB;
/* 11:   */ import net.minecraft.world.World;
/* 12:   */ 
/* 13:   */ public class HandSupport
/* 14:   */   extends HandHelper
/* 15:   */ {
/* 16:   */   public HandSupport(EntityHumanBase owner, ItemStack itemStack)
/* 17:   */   {
/* 18:15 */     super(owner, itemStack);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void onUpdate()
/* 22:   */   {
/* 23:20 */     if (this.owner.ticksExisted % 100 == 0)
/* 24:   */     {
/* 25:21 */       List list = this.owner.worldObj.getEntitiesWithinAABBExcludingEntity(this.owner, this.owner.boundingBox.expand(10.0D, 4.0D, 10.0D));
/* 26:22 */       for (int j = 0; j < list.size(); j++)
/* 27:   */       {
/* 28:24 */         Entity entity1 = (Entity)list.get(j);
/* 29:25 */         if (((entity1 instanceof EntityLivingBase)) && 
/* 30:26 */           (this.owner.isOnSameTeam((EntityLivingBase)entity1))) {
/* 31:27 */           ((EntityLivingBase)entity1).addPotionEffect(getPotionEffect());
/* 32:   */         }
/* 33:   */       }
/* 34:   */     }
/* 35:   */   }
/* 36:   */   
/* 37:   */   public PotionEffect getPotionEffect()
/* 38:   */   {
/* 39:35 */     return new PotionEffect(Potion.resistance.id, 101, 0);
/* 40:   */   }
/* 41:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandSupport
 * JD-Core Version:    0.7.1
 */