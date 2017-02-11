/*  1:   */ package com.chocolate.chocolateQuest.client.itemsRender;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
/*  4:   */ import net.minecraft.item.ItemStack;
/*  5:   */ import org.lwjgl.opengl.GL11;
/*  6:   */ 
/*  7:   */ public class RenderItemStaff
/*  8:   */   extends RenderItemBase
/*  9:   */ {
/* 10:   */   protected void renderEquipped(EntityLivingBase par1EntityLiving, ItemStack itemstack)
/* 11:   */   {
/* 12:14 */     GL11.glTranslatef(0.2F, -0.32F, -0.0F);
/* 13:   */     
/* 14:16 */     GL11.glTranslatef(-0.5F, 0.0F, 0.0F);
/* 15:17 */     GL11.glScalef(1.8F, 1.8F, 1.8F);
/* 16:   */     
/* 17:   */ 
/* 18:20 */     doRenderItem(itemstack);
/* 19:   */   }
/* 20:   */   
/* 21:   */   protected void renderFirstPerson(EntityLivingBase player, ItemStack itemstack)
/* 22:   */   {
/* 23:25 */     GL11.glTranslatef(0.0F, -0.5F, 0.4F);
/* 24:26 */     GL11.glScalef(1.5F, 2.5F, 1.5F);
/* 25:27 */     doRenderItem(itemstack);
/* 26:   */   }
/* 27:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.itemsRender.RenderItemStaff
 * JD-Core Version:    0.7.1
 */