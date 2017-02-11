/*  1:   */ package com.chocolate.chocolateQuest.client.blockRender;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.block.BlockBannerStandTileEntity;
/*  4:   */ import com.chocolate.chocolateQuest.client.RenderBanner;
/*  5:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  6:   */ import net.minecraft.client.Minecraft;
/*  7:   */ import net.minecraft.client.renderer.Tessellator;
/*  8:   */ import net.minecraft.client.renderer.texture.TextureManager;
/*  9:   */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/* 10:   */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/* 11:   */ import net.minecraft.item.ItemStack;
/* 12:   */ import net.minecraft.tileentity.TileEntity;
/* 13:   */ import net.minecraft.util.ResourceLocation;
/* 14:   */ import org.lwjgl.opengl.GL11;
/* 15:   */ 
/* 16:   */ public class RenderBlockBanner
/* 17:   */   extends TileEntitySpecialRenderer
/* 18:   */ {
/* 19:17 */   private static final ResourceLocation enderPortalEndSkyTextures = new ResourceLocation("textures/environment/end_sky.png");
/* 20:18 */   private static final ResourceLocation endPortalTextures = new ResourceLocation("textures/entity/end_portal.png");
/* 21:20 */   RenderBanner render = new RenderBanner(0.0F);
/* 22:   */   
/* 23:   */   public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
/* 24:   */   {
/* 25:24 */     BlockBannerStandTileEntity te = (BlockBannerStandTileEntity)tileentity;
/* 26:25 */     if (te.item != null) {
/* 27:27 */       if (te.hasFlag)
/* 28:   */       {
/* 29:28 */         this.render.renderBanner(x + 0.5D, y, z + 0.5D, te.rotation, te.item.getItemDamage(), this.field_147501_a.field_147553_e);
/* 30:   */       }
/* 31:   */       else
/* 32:   */       {
/* 33:31 */         GL11.glPushMatrix();
/* 34:32 */         GL11.glEnable(2884);
/* 35:33 */         this.field_147501_a.field_147553_e.bindTexture(BDHelper.getItemTexture());
/* 36:34 */         GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
/* 37:35 */         GL11.glRotatef(-te.rotation, 0.0F, 1.0F, 0.0F);
/* 38:36 */         GL11.glTranslatef(-0.5F, 0.0F, 0.0F);
/* 39:   */         
/* 40:38 */         GL11.glDisable(2896);
/* 41:39 */         GL11.glEnable(3042);
/* 42:40 */         for (int c = 0; c < 6; c++)
/* 43:   */         {
/* 44:42 */           GL11.glMatrixMode(5890);
/* 45:43 */           GL11.glPushMatrix();
/* 46:44 */           if (c == 0)
/* 47:   */           {
/* 48:46 */             bindTexture(enderPortalEndSkyTextures);
/* 49:47 */             GL11.glBlendFunc(770, 771);
/* 50:48 */             GL11.glTexGeni(8193, 9472, 9216);
/* 51:49 */             GL11.glTexGeni(8192, 9472, 9216);
/* 52:50 */             GL11.glEnable(3168);
/* 53:51 */             GL11.glEnable(3169);
/* 54:52 */             float scale = 4.0F - (float)(Minecraft.getSystemTime() % 10000L) / 10000.0F * 4.0F;
/* 55:53 */             GL11.glScalef(scale, scale, scale);
/* 56:54 */             GL11.glRotatef(scale, 0.0F, 1.0F, 0.0F);
/* 57:   */           }
/* 58:   */           else
/* 59:   */           {
/* 60:59 */             bindTexture(endPortalTextures);
/* 61:60 */             GL11.glBlendFunc(1, 1);
/* 62:61 */             GL11.glScalef(c, c, c);
/* 63:62 */             GL11.glTranslatef(c * 0.8F, (float)(Minecraft.getSystemTime() % 70000L) / 7000.0F * c, 0.0F);
/* 64:   */           }
/* 65:64 */           Tessellator tessellator = Tessellator.instance;
/* 66:65 */           tessellator.startDrawingQuads();
/* 67:66 */           tessellator.addVertexWithUV(0.0D, 0.1000000014901161D, 0.001D, 0.0D, 0.0D);
/* 68:67 */           tessellator.addVertexWithUV(1.0D, 0.1000000014901161D, 0.001D, 1.0D, 0.0D);
/* 69:68 */           tessellator.addVertexWithUV(1.0D, 2.0D, 0.001D, 1.0D, 1.0D);
/* 70:69 */           tessellator.addVertexWithUV(0.0D, 2.0D, 0.001D, 0.0D, 1.0D);
/* 71:70 */           tessellator.draw();
/* 72:71 */           GL11.glPopMatrix();
/* 73:72 */           GL11.glMatrixMode(5888);
/* 74:   */           
/* 75:74 */           GL11.glDisable(3168);
/* 76:75 */           GL11.glDisable(3169);
/* 77:   */         }
/* 78:77 */         GL11.glDisable(3042);
/* 79:78 */         GL11.glPopMatrix();
/* 80:   */       }
/* 81:   */     }
/* 82:   */   }
/* 83:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.blockRender.RenderBlockBanner
 * JD-Core Version:    0.7.1
 */