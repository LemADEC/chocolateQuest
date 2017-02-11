/*  1:   */ package com.chocolate.chocolateQuest.client.blockRender;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.client.ClientProxy;
/*  5:   */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*  6:   */ import net.minecraft.block.Block;
/*  7:   */ import net.minecraft.client.renderer.RenderBlocks;
/*  8:   */ import net.minecraft.client.renderer.Tessellator;
/*  9:   */ import net.minecraft.init.Blocks;
/* 10:   */ import net.minecraft.util.IIcon;
/* 11:   */ import net.minecraft.world.IBlockAccess;
/* 12:   */ 
/* 13:   */ public class RenderBlockTableItem
/* 14:   */   implements ISimpleBlockRenderingHandler
/* 15:   */ {
/* 16:   */   public int getRenderId()
/* 17:   */   {
/* 18:20 */     return ClientProxy.tableRenderID;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
/* 22:   */   {
/* 23:25 */     Tessellator tessellator = Tessellator.instance;
/* 24:26 */     tessellator.startDrawingQuads();
/* 25:27 */     renderBlock();
/* 26:28 */     tessellator.draw();
/* 27:   */   }
/* 28:   */   
/* 29:   */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/* 30:   */   {
/* 31:33 */     ChocolateQuest.table.setBlockBoundsBasedOnState(world, x, y, z);
/* 32:34 */     renderBlock();
/* 33:35 */     return false;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public boolean shouldRender3DInInventory(int modelId)
/* 37:   */   {
/* 38:40 */     return true;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void renderBlock()
/* 42:   */   {
/* 43:45 */     Tessellator tessellator = Tessellator.instance;
/* 44:46 */     IIcon icon = Blocks.planks.getBlockTextureFromSide(0);
/* 45:47 */     double tx0 = icon.getMinU();
/* 46:48 */     double tx1 = icon.getMaxU();
/* 47:49 */     double ty0 = icon.getMinV();
/* 48:50 */     double ty1 = icon.getMaxV();
/* 49:51 */     double y0 = 0.9D;
/* 50:52 */     double y1 = 1.0D;
/* 51:53 */     double x0 = ChocolateQuest.table.getBlockBoundsMinX();
/* 52:54 */     double x1 = ChocolateQuest.table.getBlockBoundsMaxX();
/* 53:55 */     double z0 = ChocolateQuest.table.getBlockBoundsMinZ();
/* 54:56 */     double z1 = ChocolateQuest.table.getBlockBoundsMaxZ();
/* 55:57 */     drawBox(tessellator, x0, x1, y0, y1, z0, z1, tx0, tx1, ty0, ty1, 1.0F);
/* 56:58 */     drawBox(tessellator, 0.4D, 0.6D, 0.0D, 0.92D, 0.4D, 0.6D, tx0, tx1, ty0, ty1, 1.0F);
/* 57:   */   }
/* 58:   */   
/* 59:   */   public void drawBox(Tessellator tessellator, double x0, double x1, double y0, double y1, double z0, double z1, double tx0, double tx1, double ty0, double ty1, float b)
/* 60:   */   {
/* 61:63 */     tessellator.setNormal(0.0F, 0.0F, 1.0F);
/* 62:64 */     tessellator.addVertexWithUV(x1, y0, z0, tx0, ty0);
/* 63:65 */     tessellator.addVertexWithUV(x0, y0, z0, tx1, ty0);
/* 64:66 */     tessellator.addVertexWithUV(x0, y1, z0, tx1, ty1);
/* 65:67 */     tessellator.addVertexWithUV(x1, y1, z0, tx0, ty1);
/* 66:   */     
/* 67:69 */     tessellator.setNormal(0.0F, 0.0F, -1.0F);
/* 68:70 */     tessellator.addVertexWithUV(x0, y0, z1, tx0, ty0);
/* 69:71 */     tessellator.addVertexWithUV(x1, y0, z1, tx1, ty0);
/* 70:72 */     tessellator.addVertexWithUV(x1, y1, z1, tx1, ty1);
/* 71:73 */     tessellator.addVertexWithUV(x0, y1, z1, tx0, ty1);
/* 72:   */     
/* 73:75 */     tessellator.setNormal(-1.0F, 0.0F, 0.0F);
/* 74:76 */     tessellator.addVertexWithUV(x1, y1, z0, tx1, ty0);
/* 75:77 */     tessellator.addVertexWithUV(x1, y1, z1, tx0, ty0);
/* 76:78 */     tessellator.addVertexWithUV(x1, y0, z1, tx0, ty1);
/* 77:79 */     tessellator.addVertexWithUV(x1, y0, z0, tx1, ty1);
/* 78:   */     
/* 79:81 */     tessellator.setNormal(1.0F, 0.0F, 0.0F);
/* 80:82 */     tessellator.addVertexWithUV(x0, y1, z0, tx0, ty0);
/* 81:83 */     tessellator.addVertexWithUV(x0, y0, z0, tx0, ty1);
/* 82:84 */     tessellator.addVertexWithUV(x0, y0, z1, tx1, ty1);
/* 83:85 */     tessellator.addVertexWithUV(x0, y1, z1, tx1, ty0);
/* 84:   */     
/* 85:87 */     tessellator.setNormal(0.0F, -1.0F, 0.0F);
/* 86:88 */     tessellator.addVertexWithUV(x0, y0, z1, tx1, ty1);
/* 87:89 */     tessellator.addVertexWithUV(x1, y0, z0, tx0, ty1);
/* 88:90 */     tessellator.addVertexWithUV(x1, y0, z0, tx0, ty0);
/* 89:91 */     tessellator.addVertexWithUV(x0, y0, z1, tx1, ty0);
/* 90:   */     
/* 91:93 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 92:94 */     tessellator.addVertexWithUV(x0, y1, z1, tx0, ty0);
/* 93:95 */     tessellator.addVertexWithUV(x1, y1, z1, tx0, ty1);
/* 94:96 */     tessellator.addVertexWithUV(x1, y1, z0, tx1, ty1);
/* 95:97 */     tessellator.addVertexWithUV(x0, y1, z0, tx1, ty0);
/* 96:   */   }
/* 97:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.blockRender.RenderBlockTableItem
 * JD-Core Version:    0.7.1
 */