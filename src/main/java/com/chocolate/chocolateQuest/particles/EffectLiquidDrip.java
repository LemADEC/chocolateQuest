/*  1:   */ package com.chocolate.chocolateQuest.particles;
/*  2:   */ 
/*  3:   */ import net.minecraft.util.ResourceLocation;
/*  4:   */ import net.minecraft.world.World;
/*  5:   */ 
/*  6:   */ public class EffectLiquidDrip
/*  7:   */   extends EffectBase
/*  8:   */ {
/*  9: 9 */   private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
/* 10:   */   
/* 11:   */   public EffectLiquidDrip(World par1World, double posX, double posY, double posZ, double par8, double par10, double par12)
/* 12:   */   {
/* 13:13 */     super(par1World, posX, posY, posZ, par8, par10, par12);
/* 14:14 */     this.particleMaxAge = 30;
/* 15:15 */     this.worldObj = par1World;
/* 16:16 */     this.iconsLength = 32.0F;
/* 17:17 */     this.iconScale = 8.0F;
/* 18:18 */     this.particleTextureIndexX = 5;
/* 19:19 */     this.particleTextureIndexY = 22;
/* 20:20 */     this.motionX = (this.motionZ = this.motionY = 0.0D);
/* 21:21 */     this.particleRed = ((float)par8);
/* 22:22 */     this.particleGreen = ((float)par10);
/* 23:23 */     this.particleBlue = ((float)par12);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void onUpdate()
/* 27:   */   {
/* 28:31 */     this.motionY = -0.6D;
/* 29:32 */     this.ticksExisted += 1;
/* 30:33 */     if ((this.ticksExisted > 1) && (this.fallDistance == 0.0F) && (this.particleTextureIndexX < 8) && (this.ticksExisted % 2 == 0)) {
/* 31:34 */       this.particleTextureIndexX += 1;
/* 32:   */     }
/* 33:35 */     super.onUpdate();
/* 34:   */   }
/* 35:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.particles.EffectLiquidDrip
 * JD-Core Version:    0.7.1
 */