/*  1:   */ package com.chocolate.chocolateQuest.particles;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  4:   */ import net.minecraft.client.Minecraft;
/*  5:   */ import net.minecraft.client.particle.EntityFX;
/*  6:   */ import net.minecraft.client.renderer.Tessellator;
/*  7:   */ import net.minecraft.client.renderer.texture.TextureManager;
/*  8:   */ import net.minecraft.util.ResourceLocation;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class EffectBase
/* 12:   */   extends EntityFX
/* 13:   */ {
/* 14:13 */   private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
/* 15:15 */   protected float iconsLength = 16.0F;
/* 16:16 */   protected float iconScale = 16.0F;
/* 17:   */   
/* 18:   */   public EffectBase(World par1World, double posX, double posY, double posZ, double par8, double par10, double par12)
/* 19:   */   {
/* 20:19 */     super(par1World, posX, posY, posZ, par8, par10, par12);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
/* 24:   */   {
/* 25:24 */     int iconX = this.particleTextureIndexX;
/* 26:25 */     int iconY = this.particleTextureIndexY;
/* 27:26 */     Tessellator tessellator1 = new Tessellator();
/* 28:27 */     tessellator1.startDrawingQuads();
/* 29:28 */     tessellator1.setBrightness(getBrightnessForRender(par2));
/* 30:29 */     Minecraft.getMinecraft().renderEngine.bindTexture(BDHelper.getItemTexture());
/* 31:30 */     float iconWidth = this.iconScale / 256.0F;
/* 32:31 */     float minX = iconX / this.iconsLength;
/* 33:32 */     float maxX = minX + iconWidth;
/* 34:33 */     float minY = iconY / this.iconsLength;
/* 35:34 */     float maxY = minY + iconWidth;
/* 36:35 */     float scale = 0.1F * this.particleScale;
/* 37:36 */     float x = (float)(this.prevPosX + (this.posX - this.prevPosX) * par2 - interpPosX);
/* 38:37 */     float y = (float)(this.prevPosY + (this.posY - this.prevPosY) * par2 - interpPosY);
/* 39:38 */     float z = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - interpPosZ);
/* 40:39 */     float f8 = 1.0F;
/* 41:40 */     tessellator1.setColorRGBA_F(this.particleRed * f8, this.particleGreen * f8, this.particleBlue * f8, 1.0F);
/* 42:41 */     tessellator1.addVertexWithUV(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, maxX, maxY);
/* 43:42 */     tessellator1.addVertexWithUV(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, maxX, minY);
/* 44:43 */     tessellator1.addVertexWithUV(x + par3 * scale + par6 * scale, y + par4 * scale, z + par5 * scale + par7 * scale, minX, minY);
/* 45:44 */     tessellator1.addVertexWithUV(x + par3 * scale - par6 * scale, y - par4 * scale, z + par5 * scale - par7 * scale, minX, maxY);
/* 46:   */     
/* 47:46 */     tessellator1.draw();
/* 48:47 */     Minecraft.getMinecraft().renderEngine.bindTexture(particleTextures);
/* 49:   */   }
/* 50:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.particles.EffectBase
 * JD-Core Version:    0.7.1
 */