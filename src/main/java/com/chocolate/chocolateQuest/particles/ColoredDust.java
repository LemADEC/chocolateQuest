/*  1:   */ package com.chocolate.chocolateQuest.particles;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.particle.EntityFX;
/*  4:   */ import net.minecraft.client.renderer.Tessellator;
/*  5:   */ import net.minecraft.util.ResourceLocation;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class ColoredDust
/*  9:   */   extends EntityFX
/* 10:   */ {
/* 11:11 */   private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
/* 12:   */   private int coolDown;
/* 13:   */   
/* 14:   */   public ColoredDust(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
/* 15:   */   {
/* 16:16 */     super(par1World, par2, par4, par6, par8, par10, par12);
/* 17:17 */     this.particleMaxAge = 12;
/* 18:18 */     this.worldObj = par1World;
/* 19:19 */     this.coolDown = 0;
/* 20:20 */     this.motionX *= 0.1D;
/* 21:21 */     this.motionZ *= 0.1D;
/* 22:22 */     this.motionY *= 0.2D;
/* 23:23 */     this.particleTextureIndexX = 0;
/* 24:24 */     this.particleTextureIndexY = 0;
/* 25:   */     
/* 26:26 */     this.particleRed = ((float)par8);
/* 27:27 */     this.particleGreen = ((float)par10);
/* 28:28 */     this.particleBlue = ((float)par12);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
/* 32:   */   {
/* 33:33 */     int iconX = this.particleTextureIndexX;
/* 34:34 */     int iconY = this.particleTextureIndexY;
/* 35:35 */     if (this.particleAge < 6) {
/* 36:36 */       iconX += this.particleAge;
/* 37:   */     } else {
/* 38:38 */       iconX += 10 - this.particleAge;
/* 39:   */     }
/* 40:39 */     Tessellator tessellator1 = new Tessellator();
/* 41:40 */     tessellator1.startDrawingQuads();
/* 42:41 */     tessellator1.setBrightness(getBrightnessForRender(par2));
/* 43:   */     
/* 44:43 */     float minX = iconX % 16 / 16.0F;
/* 45:44 */     float maxX = minX + 0.0624375F;
/* 46:45 */     float minY = iconY % 16 / 16.0F;
/* 47:46 */     float maxY = minY + 0.0624375F;
/* 48:47 */     float scale = 0.1F * this.particleScale;
/* 49:48 */     float x = (float)(this.prevPosX + (this.posX - this.prevPosX) * par2 - interpPosX);
/* 50:49 */     float y = (float)(this.prevPosY + (this.posY - this.prevPosY) * par2 - interpPosY);
/* 51:50 */     float z = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - interpPosZ);
/* 52:51 */     float f8 = 1.0F;
/* 53:52 */     tessellator1.setColorOpaque_F(this.particleRed * f8, this.particleGreen * f8, this.particleBlue * f8);
/* 54:53 */     tessellator1.addVertexWithUV(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, maxX, maxY);
/* 55:54 */     tessellator1.addVertexWithUV(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, maxX, minY);
/* 56:55 */     tessellator1.addVertexWithUV(x + par3 * scale + par6 * scale, y + par4 * scale, z + par5 * scale + par7 * scale, minX, minY);
/* 57:56 */     tessellator1.addVertexWithUV(x + par3 * scale - par6 * scale, y - par4 * scale, z + par5 * scale - par7 * scale, minX, maxY);
/* 58:   */     
/* 59:58 */     tessellator1.draw();
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void onUpdate()
/* 63:   */   {
/* 64:68 */     super.onUpdate();
/* 65:   */   }
/* 66:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.particles.ColoredDust
 * JD-Core Version:    0.7.1
 */