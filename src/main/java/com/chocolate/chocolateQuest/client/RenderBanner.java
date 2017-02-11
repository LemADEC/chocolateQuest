/*  1:   */ package com.chocolate.chocolateQuest.client;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.client.itemsRender.RenderItemBase;
/*  5:   */ import com.chocolate.chocolateQuest.entity.EntityCursor;
/*  6:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  7:   */ import net.minecraft.client.renderer.Tessellator;
/*  8:   */ import net.minecraft.client.renderer.entity.Render;
/*  9:   */ import net.minecraft.client.renderer.entity.RenderManager;
/* 10:   */ import net.minecraft.client.renderer.texture.TextureManager;
/* 11:   */ import net.minecraft.entity.Entity;
/* 12:   */ import net.minecraft.item.ItemStack;
/* 13:   */ import net.minecraft.util.ResourceLocation;
/* 14:   */ import org.lwjgl.opengl.GL11;
/* 15:   */ 
/* 16:   */ public class RenderBanner
/* 17:   */   extends Render
/* 18:   */ {
/* 19:   */   private float field_40269_a;
/* 20:   */   
/* 21:   */   public RenderBanner(float f)
/* 22:   */   {
/* 23:32 */     this.field_40269_a = f;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void doRender(Entity entity, double x, double y, double z, float f, float f1)
/* 27:   */   {
/* 28:38 */     EntityCursor e = (EntityCursor)entity;
/* 29:39 */     int flag = 0;
/* 30:40 */     if (e.item != null) {
/* 31:41 */       flag = e.item.getItemDamage() % 16;
/* 32:   */     }
/* 33:42 */     renderBanner(x, y, z, entity.rotationYaw, flag, this.renderManager.renderEngine);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void renderBanner(double x, double y, double z, float rotation, int spriteIndex, TextureManager renderEngine)
/* 37:   */   {
/* 38:47 */     GL11.glPushMatrix();
/* 39:48 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 40:   */     
/* 41:50 */     GL11.glRotatef(-rotation, 0.0F, 1.0F, 0.0F);
/* 42:51 */     GL11.glTranslatef(-0.5F, 0.0F, 0.0F);
/* 43:52 */     float f2 = 1.0F;
/* 44:53 */     GL11.glScalef(f2, f2 * 2.0F, f2);
/* 45:   */     
/* 46:55 */     ItemStack is = new ItemStack(ChocolateQuest.banner, 1, 0);
/* 47:56 */     RenderItemBase.doRenderItem(is.getIconIndex(), is, 0, false);
/* 48:   */     
/* 49:58 */     renderEngine.bindTexture(BDHelper.getItemTexture());
/* 50:   */     
/* 51:   */ 
/* 52:61 */     GL11.glDisable(2884);
/* 53:   */     
/* 54:63 */     float i1 = (spriteIndex % 16 * 16 + 0) / 256.0F;
/* 55:64 */     float i2 = (spriteIndex % 16 * 16 + 16) / 256.0F;
/* 56:65 */     float i3 = 0.125F;
/* 57:66 */     float i4 = 0.25F;
/* 58:67 */     float f5 = 1.0F;
/* 59:68 */     Tessellator tessellator = Tessellator.instance;
/* 60:69 */     tessellator.startDrawingQuads();
/* 61:70 */     tessellator.addVertexWithUV(0.0D, 0.1000000014901161D, 0.001D, i1, i4);
/* 62:71 */     tessellator.addVertexWithUV(f5, 0.1000000014901161D, 0.001D, i2, i4);
/* 63:72 */     tessellator.addVertexWithUV(f5, 1.0D, 0.001D, i2, i3);
/* 64:73 */     tessellator.addVertexWithUV(0.0D, 1.0D, 0.001D, i1, i3);
/* 65:74 */     tessellator.draw();
/* 66:   */     
/* 67:76 */     GL11.glEnable(2884);
/* 68:   */     
/* 69:78 */     GL11.glPopMatrix();
/* 70:   */   }
/* 71:   */   
/* 72:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 73:   */   {
/* 74:83 */     return null;
/* 75:   */   }
/* 76:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.RenderBanner
 * JD-Core Version:    0.7.1
 */