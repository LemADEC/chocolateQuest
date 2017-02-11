/*  1:   */ package com.chocolate.chocolateQuest.particles;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.util.ResourceLocation;
/*  5:   */ import net.minecraft.world.World;
/*  6:   */ 
/*  7:   */ public class EffectFog
/*  8:   */   extends EffectBase
/*  9:   */ {
/* 10: 8 */   private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
/* 11:   */   
/* 12:   */   public EffectFog(World par1World, double posX, double posY, double posZ, double par8, double par10, double par12)
/* 13:   */   {
/* 14:12 */     super(par1World, posX, posY, posZ, par8, par10, par12);
/* 15:13 */     this.particleMaxAge = 16;
/* 16:14 */     this.worldObj = par1World;
/* 17:15 */     setParticleTextureIndex(192);
/* 18:16 */     this.motionX = (this.motionZ = this.motionY = 0.0D);
/* 19:17 */     this.particleScale = (6.0F + this.rand.nextFloat() * 6.0F);
/* 20:18 */     this.particleRed = ((float)par8);
/* 21:19 */     this.particleGreen = ((float)par10);
/* 22:20 */     this.particleBlue = ((float)par12);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void onUpdate()
/* 26:   */   {
/* 27:28 */     super.onUpdate();
/* 28:29 */     this.particleTextureIndexX += 1;
/* 29:   */   }
/* 30:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.particles.EffectFog
 * JD-Core Version:    0.7.1
 */