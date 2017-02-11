/*   1:    */ package com.chocolate.chocolateQuest.client.itemsRender;
/*   2:    */ 
import net.minecraftforge.client.IItemRenderer;
/*   3:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   4:    */ import net.minecraft.client.Minecraft;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.texture.TextureManager;
/*   7:    */ import net.minecraft.client.settings.GameSettings;
/*   8:    */ import net.minecraft.entity.EntityLivingBase;
/*   9:    */ import net.minecraft.item.ItemStack;
/*  10:    */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*  11:    */ import org.lwjgl.opengl.GL11;
/*  12:    */ 
/*  13:    */ public class RenderItemBanner
/*  14:    */   extends RenderItemBase
/*  15:    */ {
/*  16:    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*  17:    */   {
/*  18: 19 */     if ((type == IItemRenderer.ItemRenderType.EQUIPPED) || (type == IItemRenderer.ItemRenderType.INVENTORY) || (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) || (type == IItemRenderer.ItemRenderType.ENTITY)) {
/*  19: 21 */       return true;
/*  20:    */     }
/*  21: 24 */     return false;
/*  22:    */   }
/*  23:    */   
/*  24:    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*  25:    */   {
/*  26: 30 */     super.renderItem(type, item, data);
/*  27:    */   }
/*  28:    */   
/*  29:    */   protected void renderInventory(ItemStack itemstack)
/*  30:    */   {
/*  31: 36 */     GL11.glBlendFunc(770, 771);
/*  32: 37 */     GL11.glEnable(3042);
/*  33: 38 */     Minecraft.getMinecraft().renderEngine.bindTexture(BDHelper.getItemTexture());
/*  34: 39 */     int spriteIndex = itemstack.getItemDamage();
/*  35: 40 */     float i1 = (spriteIndex % 16 * 16 + 0) / 256.0F;
/*  36: 41 */     float i2 = (spriteIndex % 16 * 16 + 16) / 256.0F;
/*  37: 42 */     float ty0 = 0.125F;
/*  38: 43 */     float ty1 = 0.25F;
/*  39: 44 */     float size = 16.0F;
/*  40: 45 */     Tessellator tessellator = Tessellator.instance;
/*  41: 46 */     tessellator.startDrawingQuads();
/*  42: 47 */     tessellator.addVertexWithUV(0.0D, size, 0.0D, i1, ty1);
/*  43: 48 */     tessellator.addVertexWithUV(size, size, 0.0D, i2, ty1);
/*  44: 49 */     tessellator.addVertexWithUV(size, 0.0D, 0.0D, i2, ty0);
/*  45: 50 */     tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, i1, ty0);
/*  46: 51 */     tessellator.draw();
/*  47:    */     
/*  48: 53 */     GL11.glDisable(3042);
/*  49:    */   }
/*  50:    */   
/*  51:    */   protected void renderEquipped(EntityLivingBase entity, ItemStack itemstack)
/*  52:    */   {
/*  53: 59 */     GL11.glTranslatef(-0.1F, 0.12F, 0.0F);
/*  54: 62 */     if ((entity == Minecraft.getMinecraft().thePlayer) && (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0))
/*  55:    */     {
/*  56: 64 */       GL11.glTranslatef(0.0F, -0.5F, 0.3F);
/*  57: 65 */       GL11.glScalef(2.0F, 4.0F, 2.0F);
/*  58:    */     }
/*  59:    */     else
/*  60:    */     {
/*  61: 69 */       GL11.glTranslatef(-0.2F, 0.0F, 0.0F);
/*  62: 70 */       GL11.glScalef(2.0F, 4.0F, 2.0F);
/*  63:    */     }
/*  64: 72 */     renderItem(itemstack);
/*  65:    */   }
/*  66:    */   
/*  67:    */   protected void renderItem(ItemStack is)
/*  68:    */   {
/*  69: 78 */     doRenderItem(is);
/*  70:    */     
/*  71: 80 */     GL11.glEnable(32826);
/*  72: 81 */     GL11.glBlendFunc(770, 771);
/*  73: 82 */     GL11.glEnable(3042);
/*  74: 83 */     GL11.glDisable(2884);
/*  75:    */     
/*  76: 85 */     Minecraft.getMinecraft().renderEngine.bindTexture(BDHelper.getItemTexture());
/*  77: 86 */     int spriteIndex = is.getItemDamage();
/*  78: 87 */     float i1 = (spriteIndex % 16 * 16 + 0) / 256.0F;
/*  79: 88 */     float i2 = (spriteIndex % 16 * 16 + 16) / 256.0F;
/*  80: 89 */     float i3 = 0.125F;
/*  81: 90 */     float i4 = 0.25F;
/*  82: 91 */     float f5 = 1.0F;
/*  83: 92 */     Tessellator tessellator = Tessellator.instance;
/*  84: 93 */     tessellator.startDrawingQuads();
/*  85: 94 */     tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, i1, i4);
/*  86: 95 */     tessellator.addVertexWithUV(f5, 0.0D, 0.0D, i2, i4);
/*  87: 96 */     tessellator.addVertexWithUV(f5, f5, 0.0D, i2, i3);
/*  88: 97 */     tessellator.addVertexWithUV(0.0D, f5, 0.0D, i1, i3);
/*  89: 98 */     tessellator.draw();
/*  90: 99 */     GL11.glEnable(2884);
/*  91:100 */     GL11.glDisable(32826);
/*  92:101 */     GL11.glDisable(3042);
/*  93:    */   }
/*  94:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.client.itemsRender.RenderItemBanner

 * JD-Core Version:    0.7.1

 */
