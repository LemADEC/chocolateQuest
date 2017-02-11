/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.API.ICooldownTracker;
/*  4:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ 
/*  7:   */ public class HandRangedAdvanced
/*  8:   */   extends HandRanged
/*  9:   */ {
/* 10:   */   ICooldownTracker itemTracker;
/* 11:11 */   public Object cooldownTracker = null;
/* 12:   */   
/* 13:   */   public HandRangedAdvanced(EntityHumanBase owner, ItemStack itemStack)
/* 14:   */   {
/* 15:13 */     super(owner, itemStack);
/* 16:15 */     if ((itemStack.getItem() instanceof ICooldownTracker))
/* 17:   */     {
/* 18:16 */       this.itemTracker = ((ICooldownTracker)itemStack.getItem());
/* 19:17 */       this.cooldownTracker = this.itemTracker.getCooldownTracker(itemStack, owner);
/* 20:   */     }
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void onUpdate()
/* 24:   */   {
/* 25:22 */     if (this.cooldownTracker != null) {
/* 26:23 */       this.itemTracker.onUpdateCooldown(this.currentItem, this.owner, this.cooldownTracker);
/* 27:   */     }
/* 28:25 */     super.onUpdate();
/* 29:   */   }
/* 30:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandRangedAdvanced
 * JD-Core Version:    0.7.1
 */