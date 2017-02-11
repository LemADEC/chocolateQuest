/*  1:   */ package com.chocolate.chocolateQuest.particles;
/*  2:   */ 
/*  3:   */ import net.minecraft.util.ResourceLocation;
/*  4:   */ import net.minecraft.world.World;
/*  5:   */ 
/*  6:   */ public class EffectFlame
/*  7:   */   extends EffectBase
/*  8:   */ {
/*  9: 9 */   private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
/* 10:   */   double mx;
/* 11:   */   double my;
/* 12:   */   double mz;
/* 13:   */   
/* 14:   */   public EffectFlame(World par1World, double posX, double posY, double posZ, double par8, double par10, double par12)
/* 15:   */   {
/* 16:13 */     super(par1World, posX, posY, posZ, par8, par10, par12);
/* 17:14 */     this.particleMaxAge = 10;
/* 18:15 */     this.worldObj = par1World;
/* 19:16 */     this.particleTextureIndexX = 3;
/* 20:17 */     this.particleTextureIndexY = 15;
/* 21:18 */     this.particleScale = 1.5F;
/* 22:19 */     this.motionX = (this.mx = par8);
/* 23:20 */     this.motionY = (this.my = par10);
/* 24:21 */     this.motionZ = (this.mz = par12);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void onUpdate()
/* 28:   */   {
/* 29:30 */     this.motionX = this.mx;
/* 30:31 */     this.motionZ = this.mz;
/* 31:32 */     this.ticksExisted += 1;
/* 32:33 */     this.particleTextureIndexX = (3 + this.ticksExisted / 2 % 3);
/* 33:34 */     super.onUpdate();
/* 34:   */   }
/* 35:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.particles.EffectFlame
 * JD-Core Version:    0.7.1
 */