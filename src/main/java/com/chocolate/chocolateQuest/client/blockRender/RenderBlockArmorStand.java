/*  1:   */ package com.chocolate.chocolateQuest.client.blockRender;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.block.BlockArmorStandTileEntity;
/*  4:   */ import com.chocolate.chocolateQuest.client.RenderBanner;
/*  5:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  6:   */ import net.minecraft.client.Minecraft;
/*  7:   */ import net.minecraft.client.renderer.Tessellator;
/*  8:   */ import net.minecraft.client.renderer.entity.RenderManager;
/*  9:   */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/* 10:   */ import net.minecraft.tileentity.TileEntity;
/* 11:   */ import org.lwjgl.opengl.GL11;
/* 12:   */ 
/* 13:   */ public class RenderBlockArmorStand
/* 14:   */   extends TileEntitySpecialRenderer
/* 15:   */ {
/* 16:17 */   RenderBanner render = new RenderBanner(0.0F);
/* 17:   */   EntityHumanBase entity;
/* 18:   */   
/* 19:   */   public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
/* 20:   */   {
/* 21:22 */     BlockArmorStandTileEntity te = (BlockArmorStandTileEntity)tileentity;
/* 22:23 */     GL11.glPushMatrix();
/* 23:24 */     GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
/* 24:25 */     GL11.glRotatef(-te.rotation, 0.0F, 1.0F, 0.0F);
/* 25:26 */     if (te.cargoItems != null)
/* 26:   */     {
/* 27:28 */       if (this.entity == null)
/* 28:   */       {
/* 29:30 */         this.entity = new EntityHumanBase(Minecraft.getMinecraft().theWorld);
/* 30:31 */         this.entity.setCurrentItemOrArmor(0, null);
/* 31:   */       }
/* 32:33 */       else if (this.entity.worldObj != Minecraft.getMinecraft().theWorld)
/* 33:   */       {
/* 34:35 */         this.entity = new EntityHumanBase(Minecraft.getMinecraft().theWorld);
/* 35:36 */         this.entity.setCurrentItemOrArmor(0, null);
/* 36:   */       }
/* 37:39 */       for (int i = 0; i < 4; i++) {
/* 38:41 */         this.entity.setCurrentItemOrArmor(i + 1, te.cargoItems[i]);
/* 39:   */       }
/* 40:43 */       this.entity.setCurrentItemOrArmor(0, te.cargoItems[4]);
/* 41:44 */       RenderManager.instance.renderEntityWithPosYaw(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/* 42:   */     }
/* 43:54 */     GL11.glPopMatrix();
/* 44:   */   }
/* 45:   */   
/* 46:   */   public void drawBox(double x0, double x1, double y0, double y1, double z0, double z1, double tx0, double tx1, double ty0, double ty1)
/* 47:   */   {
/* 48:59 */     Tessellator tessellator = Tessellator.instance;
/* 49:60 */     tessellator.startDrawingQuads();
/* 50:   */     
/* 51:62 */     tessellator.addVertexWithUV(x1, y0, z0, tx0, ty0);
/* 52:63 */     tessellator.addVertexWithUV(x0, y0, z0, tx1, ty0);
/* 53:64 */     tessellator.addVertexWithUV(x0, y1, z0, tx1, ty1);
/* 54:65 */     tessellator.addVertexWithUV(x1, y1, z0, tx0, ty1);
/* 55:   */     
/* 56:67 */     tessellator.addVertexWithUV(x0, y0, z1, tx0, ty0);
/* 57:68 */     tessellator.addVertexWithUV(x1, y0, z1, tx1, ty0);
/* 58:69 */     tessellator.addVertexWithUV(x1, y1, z1, tx1, ty1);
/* 59:70 */     tessellator.addVertexWithUV(x0, y1, z1, tx0, ty1);
/* 60:   */     
/* 61:72 */     tessellator.addVertexWithUV(x1, y1, z0, tx1, ty1);
/* 62:73 */     tessellator.addVertexWithUV(x1, y1, z1, tx0, ty1);
/* 63:74 */     tessellator.addVertexWithUV(x1, y0, z1, tx0, ty0);
/* 64:75 */     tessellator.addVertexWithUV(x1, y0, z0, tx1, ty0);
/* 65:   */     
/* 66:77 */     tessellator.addVertexWithUV(x0, y1, z1, tx1, ty1);
/* 67:78 */     tessellator.addVertexWithUV(x0, y1, z0, tx0, ty1);
/* 68:79 */     tessellator.addVertexWithUV(x0, y0, z0, tx0, ty0);
/* 69:80 */     tessellator.addVertexWithUV(x0, y0, z1, tx1, ty0);
/* 70:   */     
/* 71:82 */     tessellator.addVertexWithUV(x0, y0, z1, tx1, ty1);
/* 72:83 */     tessellator.addVertexWithUV(x0, y0, z0, tx0, ty1);
/* 73:84 */     tessellator.addVertexWithUV(x1, y0, z0, tx0, ty0);
/* 74:85 */     tessellator.addVertexWithUV(x1, y0, z1, tx1, ty0);
/* 75:   */     
/* 76:87 */     tessellator.addVertexWithUV(x0, y1, z1, tx1, ty1);
/* 77:88 */     tessellator.addVertexWithUV(x1, y1, z1, tx1, ty0);
/* 78:89 */     tessellator.addVertexWithUV(x1, y1, z0, tx0, ty0);
/* 79:90 */     tessellator.addVertexWithUV(x0, y1, z0, tx0, ty1);
/* 80:91 */     tessellator.draw();
/* 81:   */   }
/* 82:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.blockRender.RenderBlockArmorStand
 * JD-Core Version:    0.7.1
 */