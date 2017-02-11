/*  1:   */ package com.chocolate.chocolateQuest.particles;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.util.ResourceLocation;
/*  5:   */ import net.minecraft.world.World;
/*  6:   */ 
/*  7:   */ public class EffectCloud
/*  8:   */   extends EffectFog
/*  9:   */ {
/* 10: 8 */   private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
/* 11:   */   
/* 12:   */   public EffectCloud(World par1World, double posX, double posY, double posZ, double par8, double par10, double par12)
/* 13:   */   {
/* 14:12 */     super(par1World, posX, posY, posZ, par8, par10, par12);
/* 15:13 */     this.particleMaxAge = 16;
/* 16:14 */     this.particleScale = (32.0F + this.rand.nextFloat() * 16.0F);
/* 17:   */   }
/* 18:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.particles.EffectCloud
 * JD-Core Version:    0.7.1
 */