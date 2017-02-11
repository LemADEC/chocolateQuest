/*  1:   */ package com.chocolate.chocolateQuest.client;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.projectile.EntityProjectileBeam;
/*  4:   */ import com.chocolate.chocolateQuest.magic.Elements;
/*  5:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  6:   */ import net.minecraft.client.renderer.Tessellator;
/*  7:   */ import net.minecraft.client.renderer.entity.Render;
/*  8:   */ import net.minecraft.entity.Entity;
/*  9:   */ import net.minecraft.util.ResourceLocation;
/* 10:   */ import org.lwjgl.opengl.GL11;
/* 11:   */ 
/* 12:   */ public class RenderBeam
/* 13:   */   extends Render
/* 14:   */ {
/* 15:25 */   private static final ResourceLocation arrowTextures = new ResourceLocation("textures/blocks/water_flow.png");
/* 16:   */   
/* 17:   */   public void doRender(Entity entity, double x, double y, double z, float f, float f1)
/* 18:   */   {
/* 19:28 */     Tessellator tessellator = Tessellator.instance;
/* 20:29 */     GL11.glPushMatrix();
/* 21:30 */     bindTexture(BDHelper.getItemTexture());
/* 22:31 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 23:32 */     GL11.glRotatef(-entity.rotationYaw, 0.0F, 1.0F, 0.0F);
/* 24:33 */     GL11.glRotatef(entity.rotationPitch, 1.0F, 0.0F, 0.0F);
/* 25:34 */     EntityProjectileBeam beam = (EntityProjectileBeam)entity;
/* 26:35 */     float length = beam.range;
/* 27:36 */     float width = 0.05F;
/* 28:37 */     Elements element = beam.getElement();
/* 29:38 */     GL11.glDisable(3553);
/* 30:39 */     GL11.glColor3f(element.getColorX(), element.getColorY(), element.getColorZ());
/* 31:40 */     drawBox(tessellator, -width, width, -width, width, length, 0.0D, 0.9375D, 1.0D, 0.9375D, 1.0D, 0.0F);
/* 32:41 */     GL11.glEnable(3553);
/* 33:42 */     GL11.glPopMatrix();
/* 34:   */   }
/* 35:   */   
/* 36:   */   public RenderBeam(float f) {}
/* 37:   */   
/* 38:   */   public void drawBox(Tessellator tessellator, double x0, double x1, double y0, double y1, double z0, double z1, double tx0, double tx1, double ty0, double ty1, float b)
/* 39:   */   {
/* 40:50 */     tessellator.startDrawingQuads();
/* 41:   */     
/* 42:52 */     tessellator.setNormal(0.0F, 0.0F, 1.0F);
/* 43:53 */     tessellator.addVertexWithUV(x1, y0, z0, tx0, ty0);
/* 44:54 */     tessellator.addVertexWithUV(x0, y0, z0, tx1, ty0);
/* 45:55 */     tessellator.addVertexWithUV(x0, y1, z0, tx1, ty1);
/* 46:56 */     tessellator.addVertexWithUV(x1, y1, z0, tx0, ty1);
/* 47:   */     
/* 48:58 */     tessellator.setNormal(0.0F, 0.0F, -1.0F);
/* 49:59 */     tessellator.addVertexWithUV(x0, y1, z1, tx0, ty1);
/* 50:60 */     tessellator.addVertexWithUV(x1, y1, z1, tx1, ty1);
/* 51:61 */     tessellator.addVertexWithUV(x1, y0, z1, tx1, ty0);
/* 52:62 */     tessellator.addVertexWithUV(x0, y0, z1, tx0, ty0);
/* 53:   */     
/* 54:64 */     tessellator.setNormal(-1.0F, 0.0F, 0.0F);
/* 55:65 */     tessellator.addVertexWithUV(x1, y0, z0, tx1, ty1);
/* 56:66 */     tessellator.addVertexWithUV(x1, y0, z1, tx0, ty1);
/* 57:67 */     tessellator.addVertexWithUV(x1, y1, z1, tx0, ty0);
/* 58:68 */     tessellator.addVertexWithUV(x1, y1, z0, tx1, ty0);
/* 59:   */     
/* 60:70 */     tessellator.setNormal(1.0F, 0.0F, 0.0F);
/* 61:71 */     tessellator.addVertexWithUV(x0, y1, z1, tx1, ty0);
/* 62:72 */     tessellator.addVertexWithUV(x0, y0, z1, tx1, ty1);
/* 63:73 */     tessellator.addVertexWithUV(x0, y0, z0, tx0, ty1);
/* 64:74 */     tessellator.addVertexWithUV(x0, y1, z0, tx0, ty0);
/* 65:   */     
/* 66:76 */     tessellator.setNormal(0.0F, -1.0F, 0.0F);
/* 67:77 */     tessellator.addVertexWithUV(x1, y0, z1, tx1, ty0);
/* 68:78 */     tessellator.addVertexWithUV(x1, y0, z0, tx0, ty0);
/* 69:79 */     tessellator.addVertexWithUV(x0, y0, z0, tx0, ty1);
/* 70:80 */     tessellator.addVertexWithUV(x0, y0, z1, tx1, ty1);
/* 71:   */     
/* 72:82 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 73:83 */     tessellator.addVertexWithUV(x0, y1, z0, tx1, ty0);
/* 74:84 */     tessellator.addVertexWithUV(x1, y1, z0, tx1, ty1);
/* 75:85 */     tessellator.addVertexWithUV(x1, y1, z1, tx0, ty1);
/* 76:86 */     tessellator.addVertexWithUV(x0, y1, z1, tx0, ty0);
/* 77:87 */     tessellator.draw();
/* 78:   */   }
/* 79:   */   
/* 80:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 81:   */   {
/* 82:92 */     return null;
/* 83:   */   }
/* 84:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.RenderBeam
 * JD-Core Version:    0.7.1
 */