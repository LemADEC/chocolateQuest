/*  1:   */ package com.chocolate.chocolateQuest.particles;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.world.World;
/*  5:   */ 
/*  6:   */ public class EffectElementTornado
/*  7:   */   extends EffectElement
/*  8:   */ {
/*  9:   */   public EffectElementTornado(World par1World, double posX, double posY, double posZ, double par8, double par10, double par12, int element)
/* 10:   */   {
/* 11:10 */     super(par1World, posX, posY, posZ, par8, par10, par12, element);
/* 12:11 */     int extraAge = this.rand.nextInt(10 + (int)par8);
/* 13:12 */     this.ticksExisted = extraAge;
/* 14:13 */     this.particleMaxAge = ((int)(15.0D + par8) + extraAge);
/* 15:14 */     this.motionY = ((10.0D + par8) / 200.0D);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void onUpdate()
/* 19:   */   {
/* 20:22 */     this.motionX = (Math.sin(this.ticksExisted / 2) / 2.0D * this.ticksExisted / 20.0D);
/* 21:23 */     this.motionZ = (Math.cos(this.ticksExisted / 2) / 2.0D * this.ticksExisted / 20.0D);
/* 22:24 */     super.onUpdate();
/* 23:   */   }
/* 24:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.particles.EffectElementTornado
 * JD-Core Version:    0.7.1
 */