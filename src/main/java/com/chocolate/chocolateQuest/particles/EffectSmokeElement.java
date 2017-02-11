/*  1:   */ package com.chocolate.chocolateQuest.particles;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.magic.Elements;
/*  4:   */ import net.minecraft.world.World;
/*  5:   */ 
/*  6:   */ public class EffectSmokeElement
/*  7:   */   extends EffectSmoke
/*  8:   */ {
/*  9:   */   public EffectSmokeElement(World par1World, double posX, double posY, double posZ, double par8, double par10, double par12, Elements element)
/* 10:   */   {
/* 11:12 */     super(par1World, posX, posY, posZ, par8, par10, par12);
/* 12:13 */     this.particleRed = element.getColorX();
/* 13:14 */     this.particleGreen = element.getColorY();
/* 14:15 */     this.particleBlue = element.getColorZ();
/* 15:16 */     this.particleMaxAge *= 3;
/* 16:17 */     this.animationTicks = 3;
/* 17:   */   }
/* 18:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.particles.EffectSmokeElement
 * JD-Core Version:    0.7.1
 */