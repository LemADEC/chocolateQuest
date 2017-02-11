/*  1:   */ package com.chocolate.chocolateQuest.client.itemsRender;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.entity.player.EntityPlayer;
/*  6:   */ import net.minecraft.item.ItemStack;
/*  7:   */ import org.lwjgl.opengl.GL11;
/*  8:   */ 
/*  9:   */ public class RenderItemSpear
/* 10:   */   extends RenderItemBase
/* 11:   */ {
/* 12:   */   protected void renderEquipped(EntityLivingBase player, ItemStack itemstack)
/* 13:   */   {
/* 14:17 */     GL11.glTranslatef(-0.6F, -0.2F, 0.0F);
/* 15:18 */     GL11.glScalef(2.0F, 2.0F, 2.0F);
/* 16:19 */     if ((player instanceof EntityPlayer))
/* 17:   */     {
/* 18:20 */       if (((EntityPlayer)player).getItemInUse() == itemstack)
/* 19:   */       {
/* 20:22 */         GL11.glTranslatef(0.7F, 1.0F, 0.2F);
/* 21:23 */         GL11.glRotatef(-104.0F, 0.0F, 0.0F, 1.0F);
/* 22:24 */         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
/* 23:   */       }
/* 24:   */     }
/* 25:27 */     else if (((player instanceof EntityHumanBase)) && 
/* 26:28 */       (((EntityHumanBase)player).isAiming()))
/* 27:   */     {
/* 28:30 */       GL11.glTranslatef(0.7F, 1.0F, 0.3F);
/* 29:31 */       GL11.glRotatef(-104.0F, 0.0F, 0.0F, 1.0F);
/* 30:32 */       GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
/* 31:   */     }
/* 32:35 */     doRender(player, itemstack);
/* 33:   */   }
/* 34:   */   
/* 35:   */   protected void renderFirstPerson(EntityLivingBase player, ItemStack itemstack)
/* 36:   */   {
/* 37:41 */     GL11.glScalef(2.5F, 2.5F, 2.5F);
/* 38:42 */     GL11.glTranslatef(0.12F, 0.05F, 0.8F);
/* 39:43 */     GL11.glRotatef(-48.0F, 1.0F, 0.0F, 0.0F);
/* 40:44 */     GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
/* 41:45 */     if (((player instanceof EntityPlayer)) && (
/* 42:46 */       (((EntityPlayer)player).getItemInUse() == itemstack) || (player.isSwingInProgress)))
/* 43:   */     {
/* 44:48 */       GL11.glTranslatef(0.0F, 1.0F, 0.0F);
/* 45:49 */       GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
/* 46:   */     }
/* 47:52 */     doRender(player, itemstack);
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void doRender(EntityLivingBase par1EntityLiving, ItemStack itemstack)
/* 51:   */   {
/* 52:56 */     doRenderItem(itemstack, 0);
/* 53:   */   }
/* 54:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.itemsRender.RenderItemSpear
 * JD-Core Version:    0.7.1
 */