/*  1:   */ package com.chocolate.chocolateQuest.particles;
/*  2:   */ 
/*  3:   */ import net.minecraft.world.World;
/*  4:   */ 
/*  5:   */ public class EffectBubble
/*  6:   */   extends EffectBase
/*  7:   */ {
/*  8:   */   public EffectBubble(World par1World, double posX, double posY, double posZ, double par8, double par10, double par12)
/*  9:   */   {
/* 10:10 */     super(par1World, posX, posY, posZ, par8, par10, par12);
/* 11:11 */     this.particleMaxAge = 20;
/* 12:12 */     this.worldObj = par1World;
/* 13:13 */     this.iconsLength = 32.0F;
/* 14:14 */     this.iconScale = 8.0F;
/* 15:15 */     this.particleTextureIndexX = 0;
/* 16:16 */     this.particleTextureIndexY = 22;
/* 17:17 */     this.motionX = (this.motionZ = this.motionY = 0.0D);
/* 18:18 */     this.particleRed = ((float)par8);
/* 19:19 */     this.particleGreen = ((float)par10);
/* 20:20 */     this.particleBlue = ((float)par12);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void onUpdate()
/* 24:   */   {
/* 25:28 */     if ((this.ticksExisted > 12) && (this.ticksExisted % 2 == 0)) {
/* 26:29 */       this.particleTextureIndexX += 1;
/* 27:   */     } else {
/* 28:31 */       this.motionY += 0.01D;
/* 29:   */     }
/* 30:32 */     this.ticksExisted += 1;
/* 31:33 */     super.onUpdate();
/* 32:   */   }
/* 33:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.particles.EffectBubble
 * JD-Core Version:    0.7.1
 */