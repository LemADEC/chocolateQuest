/*  1:   */ package com.chocolate.chocolateQuest.particles;
/*  2:   */ 
/*  3:   */ import net.minecraft.util.ResourceLocation;
/*  4:   */ import net.minecraft.world.World;
/*  5:   */ 
/*  6:   */ public class EffectElement
/*  7:   */   extends EffectBase
/*  8:   */ {
/*  9: 9 */   private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
/* 10:   */   public static final int FIRE = 0;
/* 11:   */   public static final int PHYSIC = 1;
/* 12:   */   public static final int BLAST = 2;
/* 13:   */   public static final int MAGIC = 3;
/* 14:   */   double mx;
/* 15:   */   double my;
/* 16:   */   double mz;
/* 17:   */   
/* 18:   */   public EffectElement(World par1World, double posX, double posY, double posZ, double par8, double par10, double par12, int element)
/* 19:   */   {
/* 20:13 */     super(par1World, posX, posY, posZ, par8, par10, par12);
/* 21:14 */     this.particleMaxAge = 10;
/* 22:15 */     this.worldObj = par1World;
/* 23:16 */     this.iconsLength = 32.0F;
/* 24:17 */     this.iconScale = 8.0F;
/* 25:18 */     this.particleTextureIndexX = element;
/* 26:19 */     this.particleTextureIndexY = 23;
/* 27:20 */     this.motionX = (this.mx = par8);
/* 28:21 */     this.motionY = (this.my = par10);
/* 29:22 */     this.motionZ = (this.mz = par12);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void onUpdate()
/* 33:   */   {
/* 34:31 */     this.ticksExisted += 1;
/* 35:32 */     super.onUpdate();
/* 36:   */   }
/* 37:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.particles.EffectElement
 * JD-Core Version:    0.7.1
 */