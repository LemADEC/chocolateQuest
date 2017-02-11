/*  1:   */ package com.chocolate.chocolateQuest.client.itemsRender;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.items.swords.ItemBaseSpear;
/*  4:   */ import net.minecraft.client.Minecraft;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.entity.player.EntityPlayer;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ 
/*  9:   */ public class RenderItemSpearFire
/* 10:   */   extends RenderItemSpear
/* 11:   */ {
/* 12:   */   public void doRender(EntityLivingBase par1EntityLiving, ItemStack itemstack)
/* 13:   */   {
/* 14:16 */     if ((par1EntityLiving instanceof EntityPlayer))
/* 15:   */     {
/* 16:18 */       Minecraft mc = Minecraft.getMinecraft();
/* 17:19 */       EntityPlayer entityplayer = (EntityPlayer)par1EntityLiving;
/* 18:20 */       if (entityplayer.getItemInUse() == itemstack)
/* 19:   */       {
/* 20:22 */         if (itemstack.getMaxItemUseDuration() - entityplayer.getItemInUseCount() <= ((ItemBaseSpear)itemstack.getItem()).cooldown) {
/* 21:24 */           doRenderItem(itemstack, 16737894, true);
/* 22:   */         } else {
/* 23:28 */           doRenderItem(itemstack);
/* 24:   */         }
/* 25:   */       }
/* 26:   */       else {
/* 27:33 */         doRenderItem(itemstack);
/* 28:   */       }
/* 29:   */     }
/* 30:   */     else
/* 31:   */     {
/* 32:37 */       doRenderItem(itemstack);
/* 33:   */     }
/* 34:   */   }
/* 35:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.itemsRender.RenderItemSpearFire
 * JD-Core Version:    0.7.1
 */