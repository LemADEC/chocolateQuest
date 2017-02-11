/*   1:    */ package com.chocolate.chocolateQuest.client.blockRender;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.block.BlockAltarTileEntity;
/*   5:    */ import com.chocolate.chocolateQuest.client.RenderBanner;
/*   6:    */ import net.minecraft.block.Block;
/*   7:    */ import net.minecraft.client.renderer.Tessellator;
/*   8:    */ import net.minecraft.client.renderer.entity.RenderManager;
/*   9:    */ import net.minecraft.client.renderer.texture.TextureMap;
/*  10:    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*  11:    */ import net.minecraft.entity.item.EntityItem;
/*  12:    */ import net.minecraft.init.Blocks;
/*  13:    */ import net.minecraft.tileentity.TileEntity;
/*  14:    */ import net.minecraft.util.IIcon;
/*  15:    */ import org.lwjgl.opengl.GL11;
/*  16:    */ 
/*  17:    */ public class RenderBlockTable
/*  18:    */   extends TileEntitySpecialRenderer
/*  19:    */ {
/*  20: 20 */   RenderBanner render = new RenderBanner(0.0F);
/*  21:    */   EntityItem entityitem;
/*  22:    */   
/*  23:    */   public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
/*  24:    */   {
/*  25: 25 */     GL11.glPushMatrix();
/*  26: 26 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  27: 27 */     bindTexture(TextureMap.locationBlocksTexture);
/*  28: 28 */     BlockAltarTileEntity te = (BlockAltarTileEntity)tileentity;
/*  29:    */     
/*  30: 30 */     ChocolateQuest.table.setBlockBoundsBasedOnState(tileentity.getWorldObj(), tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
/*  31: 31 */     Tessellator tessellator = Tessellator.instance;
/*  32: 32 */     IIcon icon = Blocks.planks.getBlockTextureFromSide(0);
/*  33: 33 */     double tx0 = icon.getMinU();
/*  34: 34 */     double tx1 = icon.getMaxU();
/*  35: 35 */     double ty0 = icon.getMinV();
/*  36: 36 */     double ty1 = icon.getMaxV();
/*  37: 37 */     double y0 = 0.9D;
/*  38: 38 */     double y1 = 1.0D;
/*  39: 39 */     double x0 = ChocolateQuest.table.getBlockBoundsMinX();
/*  40: 40 */     double x1 = ChocolateQuest.table.getBlockBoundsMaxX();
/*  41: 41 */     double z0 = ChocolateQuest.table.getBlockBoundsMinZ();
/*  42: 42 */     double z1 = ChocolateQuest.table.getBlockBoundsMaxZ();
/*  43: 43 */     drawBox(tessellator, x0, x1, y0, y1, z0, z1, tx0, tx1, ty0, ty1, 1.0F);
/*  44: 44 */     if (ChocolateQuest.table.getBlockBoundsMinY() < 0.1D) {
/*  45: 45 */       drawBox(tessellator, 0.375D, 0.625D, 0.0D, 0.92D, 0.375D, 0.625D, tx0, tx1, ty0, ty1, 1.0F);
/*  46:    */     }
/*  47: 46 */     if (te.item != null)
/*  48:    */     {
/*  49: 48 */       if (this.entityitem == null)
/*  50:    */       {
/*  51: 50 */         this.entityitem = new EntityItem(tileentity.getWorldObj(), 0.0D, 0.0D, 0.0D, te.item);
/*  52: 51 */         this.entityitem.hoverStart = 0.3F;
/*  53:    */       }
/*  54:    */       else
/*  55:    */       {
/*  56: 55 */         this.entityitem.rotationYaw = te.rotation;
/*  57: 56 */         this.entityitem.setEntityItemStack(te.item);
/*  58:    */       }
/*  59: 58 */       RenderManager.instance.renderEntityWithPosYaw(this.entityitem, 0.5D, 1.0D, 0.5D, 0.0F, 0.0F);
/*  60:    */     }
/*  61: 61 */     GL11.glPopMatrix();
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void drawBox(Tessellator tessellator, double x0, double x1, double y0, double y1, double z0, double z1, double tx0, double tx1, double ty0, double ty1, float b)
/*  65:    */   {
/*  66: 66 */     tessellator.startDrawingQuads();
/*  67:    */     
/*  68: 68 */     tessellator.setNormal(0.0F, 0.0F, 1.0F);
/*  69: 69 */     tessellator.addVertexWithUV(x1, y0, z0, tx0, ty0);
/*  70: 70 */     tessellator.addVertexWithUV(x0, y0, z0, tx1, ty0);
/*  71: 71 */     tessellator.addVertexWithUV(x0, y1, z0, tx1, ty1);
/*  72: 72 */     tessellator.addVertexWithUV(x1, y1, z0, tx0, ty1);
/*  73:    */     
/*  74: 74 */     tessellator.setNormal(0.0F, 0.0F, -1.0F);
/*  75: 75 */     tessellator.addVertexWithUV(x0, y0, z1, tx0, ty0);
/*  76: 76 */     tessellator.addVertexWithUV(x1, y0, z1, tx1, ty0);
/*  77: 77 */     tessellator.addVertexWithUV(x1, y1, z1, tx1, ty1);
/*  78: 78 */     tessellator.addVertexWithUV(x0, y1, z1, tx0, ty1);
/*  79:    */     
/*  80: 80 */     tessellator.setNormal(-1.0F, 0.0F, 0.0F);
/*  81: 81 */     tessellator.addVertexWithUV(x1, y1, z0, tx1, ty0);
/*  82: 82 */     tessellator.addVertexWithUV(x1, y1, z1, tx0, ty0);
/*  83: 83 */     tessellator.addVertexWithUV(x1, y0, z1, tx0, ty1);
/*  84: 84 */     tessellator.addVertexWithUV(x1, y0, z0, tx1, ty1);
/*  85:    */     
/*  86: 86 */     tessellator.setNormal(1.0F, 0.0F, 0.0F);
/*  87: 87 */     tessellator.addVertexWithUV(x0, y1, z0, tx0, ty0);
/*  88: 88 */     tessellator.addVertexWithUV(x0, y0, z0, tx0, ty1);
/*  89: 89 */     tessellator.addVertexWithUV(x0, y0, z1, tx1, ty1);
/*  90: 90 */     tessellator.addVertexWithUV(x0, y1, z1, tx1, ty0);
/*  91:    */     
/*  92: 92 */     tessellator.setNormal(0.0F, -1.0F, 0.0F);
/*  93: 93 */     tessellator.addVertexWithUV(x0, y0, z1, tx1, ty1);
/*  94: 94 */     tessellator.addVertexWithUV(x0, y0, z0, tx0, ty1);
/*  95: 95 */     tessellator.addVertexWithUV(x1, y0, z0, tx0, ty0);
/*  96: 96 */     tessellator.addVertexWithUV(x1, y0, z1, tx1, ty0);
/*  97:    */     
/*  98: 98 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  99: 99 */     tessellator.addVertexWithUV(x0, y1, z1, tx0, ty0);
/* 100:100 */     tessellator.addVertexWithUV(x1, y1, z1, tx0, ty1);
/* 101:101 */     tessellator.addVertexWithUV(x1, y1, z0, tx1, ty1);
/* 102:102 */     tessellator.addVertexWithUV(x0, y1, z0, tx1, ty0);
/* 103:103 */     tessellator.draw();
/* 104:    */   }
/* 105:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.blockRender.RenderBlockTable
 * JD-Core Version:    0.7.1
 */