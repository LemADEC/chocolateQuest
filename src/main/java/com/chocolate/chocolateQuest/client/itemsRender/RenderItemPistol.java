/*  1:   */ package com.chocolate.chocolateQuest.client.itemsRender;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.Minecraft;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ import org.lwjgl.opengl.GL11;
/*  7:   */ 
/*  8:   */ public class RenderItemPistol
/*  9:   */   extends RenderItemBase
/* 10:   */ {
/* 11:   */   protected void renderEquipped(EntityLivingBase par1EntityLiving, ItemStack itemstack)
/* 12:   */   {
/* 13:15 */     float par1 = 0.0F;float f1 = 0.0F;
/* 14:16 */     GL11.glTranslatef(0.4F, 0.2F, -0.4F);
/* 15:17 */     GL11.glRotatef(-75.0F, 0.0F, 1.0F, 0.0F);
/* 16:18 */     GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
/* 17:19 */     GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
/* 18:20 */     Minecraft mc = Minecraft.getMinecraft();
/* 19:21 */     doRenderItem(itemstack);
/* 20:   */   }
/* 21:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.itemsRender.RenderItemPistol
 * JD-Core Version:    0.7.1
 */