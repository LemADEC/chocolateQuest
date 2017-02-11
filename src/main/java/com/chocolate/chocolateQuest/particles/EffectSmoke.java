/*  1:   */ package com.chocolate.chocolateQuest.particles;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.renderer.Tessellator;
/*  4:   */ import net.minecraft.world.World;
/*  5:   */ 
/*  6:   */ public class EffectSmoke
/*  7:   */   extends EffectBase
/*  8:   */ {
/*  9: 9 */   int animationTicks = 1;
/* 10:   */   
/* 11:   */   public EffectSmoke(World par1World, double posX, double posY, double posZ, double par8, double par10, double par12)
/* 12:   */   {
/* 13:12 */     super(par1World, posX, posY, posZ, par8, par10, par12);
/* 14:13 */     this.particleMaxAge = 8;
/* 15:14 */     this.worldObj = par1World;
/* 16:15 */     this.iconsLength = 128.0F;
/* 17:16 */     this.iconScale = 8.0F;
/* 18:17 */     this.particleTextureIndexX = 8;
/* 19:18 */     this.particleTextureIndexY = 0;
/* 20:19 */     this.motionX = (this.motionZ = this.motionY = 0.0D);
/* 21:20 */     this.particleRed = ((float)par8);
/* 22:21 */     this.particleGreen = ((float)par10);
/* 23:22 */     this.particleBlue = ((float)par12);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
/* 27:   */   {
/* 28:28 */     int iconX = this.particleTextureIndexX;
/* 29:29 */     int iconY = this.particleTextureIndexY;
/* 30:30 */     Tessellator tessellator1 = new Tessellator();
/* 31:31 */     tessellator1.startDrawingQuads();
/* 32:32 */     tessellator1.setBrightness(getBrightnessForRender(par2));
/* 33:   */     
/* 34:34 */     float iconWidth = this.iconScale / this.iconsLength;
/* 35:35 */     float minX = iconX / this.iconsLength * this.iconScale;
/* 36:36 */     float maxX = minX + iconWidth;
/* 37:37 */     float minY = iconY / this.iconsLength * this.iconScale;
/* 38:38 */     float maxY = minY + iconWidth;
/* 39:39 */     float scale = 0.1F * this.particleScale;
/* 40:40 */     float x = (float)(this.prevPosX + (this.posX - this.prevPosX) * par2 - interpPosX);
/* 41:41 */     float y = (float)(this.prevPosY + (this.posY - this.prevPosY) * par2 - interpPosY);
/* 42:42 */     float z = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - interpPosZ);
/* 43:43 */     float f8 = 1.0F;
/* 44:44 */     tessellator1.setColorRGBA_F(this.particleRed * f8, this.particleGreen * f8, this.particleBlue * f8, 1.0F);
/* 45:45 */     tessellator1.addVertexWithUV(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, maxX, maxY);
/* 46:46 */     tessellator1.addVertexWithUV(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, maxX, minY);
/* 47:47 */     tessellator1.addVertexWithUV(x + par3 * scale + par6 * scale, y + par4 * scale, z + par5 * scale + par7 * scale, minX, minY);
/* 48:48 */     tessellator1.addVertexWithUV(x + par3 * scale - par6 * scale, y - par4 * scale, z + par5 * scale - par7 * scale, minX, maxY);
/* 49:49 */     tessellator1.draw();
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void onUpdate()
/* 53:   */   {
/* 54:58 */     if (this.ticksExisted % this.animationTicks == 0) {
/* 55:59 */       this.particleTextureIndexX -= 1;
/* 56:   */     }
/* 57:60 */     this.motionY += 0.005D;
/* 58:61 */     this.ticksExisted += 1;
/* 59:62 */     super.onUpdate();
/* 60:   */   }
/* 61:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.particles.EffectSmoke
 * JD-Core Version:    0.7.1
 */