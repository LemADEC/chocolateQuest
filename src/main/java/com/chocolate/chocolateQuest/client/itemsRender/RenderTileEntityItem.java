/*  1:   */ package com.chocolate.chocolateQuest.client.itemsRender;
/*  2:   */ 
import net.minecraftforge.client.IItemRenderer;
/*  3:   */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*  4:   */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ import net.minecraft.tileentity.TileEntity;
/*  9:   */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/* 10:   */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/* 11:   */ import org.lwjgl.opengl.GL11;
/* 12:   */ 
/* 13:   */ public class RenderTileEntityItem
/* 14:   */   extends RenderItemBase
/* 15:   */ {
/* 16:   */   TileEntitySpecialRenderer render;
/* 17:   */   TileEntity entity;
/* 18:   */   
/* 19:   */   public RenderTileEntityItem(TileEntity e, TileEntitySpecialRenderer render)
/* 20:   */   {
/* 21:19 */     this.entity = e;
/* 22:20 */     this.render = render;
/* 23:   */     
/* 24:22 */     render.func_147497_a(TileEntityRendererDispatcher.instance);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/* 28:   */   {
/* 29:28 */     if ((type == IItemRenderer.ItemRenderType.EQUIPPED) || (type == IItemRenderer.ItemRenderType.INVENTORY) || (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) || (type == IItemRenderer.ItemRenderType.ENTITY)) {
/* 30:30 */       return true;
/* 31:   */     }
/* 32:33 */     return false;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/* 36:   */   {
/* 37:39 */     return false;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/* 41:   */   {
/* 42:45 */     if (type == IItemRenderer.ItemRenderType.EQUIPPED)
/* 43:   */     {
/* 44:47 */       EntityLivingBase p = (EntityLivingBase)data[1];
/* 45:48 */       renderEquipped(p, item);
/* 46:   */     }
/* 47:50 */     if (type == IItemRenderer.ItemRenderType.ENTITY)
/* 48:   */     {
/* 49:52 */       Entity p = (Entity)data[1];
/* 50:53 */       render(p, item, 0);
/* 51:   */     }
/* 52:55 */     if (type == IItemRenderer.ItemRenderType.INVENTORY) {
/* 53:58 */       renderInventory(item);
/* 54:   */     }
/* 55:60 */     if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)
/* 56:   */     {
/* 57:62 */       EntityLivingBase p = (EntityLivingBase)data[1];
/* 58:63 */       renderEquipped(p, item);
/* 59:   */     }
/* 60:   */   }
/* 61:   */   
/* 62:   */   protected void renderInventory(ItemStack itemstack)
/* 63:   */   {
/* 64:69 */     GL11.glScalef(10.0F, -10.0F, 10.0F);
/* 65:70 */     GL11.glTranslatef(0.5F, -1.4F, 0.0F);
/* 66:71 */     GL11.glRotatef(-30.0F, -0.6F, 1.0F, 0.0F);
/* 67:72 */     this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F);
/* 68:   */   }
/* 69:   */   
/* 70:   */   protected void render(Entity entity, ItemStack itemstack, int par3)
/* 71:   */   {
/* 72:85 */     GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
/* 73:86 */     this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F);
/* 74:   */   }
/* 75:   */   
/* 76:   */   protected void renderEquipped(EntityLivingBase entity, ItemStack itemstack)
/* 77:   */   {
/* 78:92 */     GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
/* 79:93 */     this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F);
/* 80:   */   }
/* 81:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.client.itemsRender.RenderTileEntityItem

 * JD-Core Version:    0.7.1

 */
