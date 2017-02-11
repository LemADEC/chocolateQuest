/*  1:   */ package com.chocolate.chocolateQuest.client.itemsRender;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
/*  4:   */ import net.minecraft.item.ItemStack;
/*  5:   */ import net.minecraftforge.client.IItemRenderer;
/*  6:   */ import org.lwjgl.opengl.GL11;
/*  7:   */ 
/*  8:   */ public class RenderItemTwoHandedSword
/*  9:   */   extends RenderItemBase
/* 10:   */   implements IItemRenderer
/* 11:   */ {
/* 12:   */   protected void renderEquipped(EntityLivingBase par1EntityLiving, ItemStack itemstack)
/* 13:   */   {
/* 14:15 */     GL11.glTranslatef(-0.9F, -0.08F, 0.14F);
/* 15:16 */     GL11.glScalef(2.0F, 2.0F, 2.0F);
/* 16:17 */     doRenderItem(itemstack);
/* 17:   */   }
/* 18:   */   
/* 19:   */   protected void renderFirstPerson(EntityLivingBase player, ItemStack itemstack)
/* 20:   */   {
/* 21:45 */     GL11.glTranslatef(0.0F, -0.3F, 0.6F);
/* 22:46 */     GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
/* 23:   */     
/* 24:48 */     GL11.glScalef(2.0F, 2.0F, 2.0F);
/* 25:49 */     doRenderItem(itemstack);
/* 26:   */   }
/* 27:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.itemsRender.RenderItemTwoHandedSword
 * JD-Core Version:    0.7.1
 */