/*  1:   */ package com.chocolate.chocolateQuest.particles;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.magic.Elements;
/*  4:   */ import net.minecraft.client.Minecraft;
/*  5:   */ import net.minecraft.client.particle.EffectRenderer;
/*  6:   */ import net.minecraft.client.settings.GameSettings;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ 
/*  9:   */ public class EffectManager
/* 10:   */ {
/* 11:   */   public static final int slimeFog = 0;
/* 12:   */   public static final int bubble = 1;
/* 13:   */   public static final int liquid_Drop = 2;
/* 14:   */   public static final int flame = 3;
/* 15:   */   public static final int dust = 4;
/* 16:   */   public static final int cloud = 5;
/* 17:   */   public static final int vanilla_cloud = 6;
/* 18:   */   public static final int dust_walker = 7;
/* 19:   */   public static final int element = 0;
/* 20:   */   public static final int element_tornado = 1;
/* 21:   */   public static final int element_smoke = 2;
/* 22:   */   
/* 23:   */   public static void spawnParticle(int particle, World worldObj, double x, double y, double z)
/* 24:   */   {
/* 25:14 */     spawnParticle(particle, worldObj, x, y, z, 0.0D, 0.0D, 0.0D);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public static void spawnParticle(int particle, World worldObj, double x, double y, double z, double mx, double my, double mz)
/* 29:   */   {
/* 30:18 */     switch (particle)
/* 31:   */     {
/* 32:   */     case 0: 
/* 33:19 */       Minecraft.getMinecraft().effectRenderer.addEffect(new EffectFog(worldObj, x, y, z, mx, my, mz));return;
/* 34:   */     case 1: 
/* 35:20 */       Minecraft.getMinecraft().effectRenderer.addEffect(new EffectBubble(worldObj, x, y, z, mx, my, mz));return;
/* 36:   */     case 2: 
/* 37:21 */       Minecraft.getMinecraft().effectRenderer.addEffect(new EffectLiquidDrip(worldObj, x, y, z, mx, my, mz));return;
/* 38:   */     case 3: 
/* 39:22 */       Minecraft.getMinecraft().effectRenderer.addEffect(new EffectFlame(worldObj, x, y, z, mx, my, mz));return;
/* 40:   */     case 4: 
/* 41:23 */       Minecraft.getMinecraft().effectRenderer.addEffect(new ColoredDust(worldObj, x, y, z, mx, my, mz));return;
/* 42:   */     case 5: 
/* 43:24 */       Minecraft.getMinecraft().effectRenderer.addEffect(new EffectCloud(worldObj, x, y, z, mx, my, mz));return;
/* 44:   */     case 6: 
/* 45:25 */       Minecraft.getMinecraft().effectRenderer.addEffect(new EffectSmoke(worldObj, x, y, z, mx, my, mz));return;
/* 46:   */     case 7: 
/* 47:26 */       if (shouldSpawnParticle()) {
/* 48:26 */         Minecraft.getMinecraft().effectRenderer.addEffect(new ColoredDust(worldObj, x, y, z, mx, my, mz));
/* 49:   */       }
/* 50:26 */       return;
/* 51:   */     }
/* 52:   */   }
/* 53:   */   
/* 54:   */   public static void spawnElementParticle(int particle, World worldObj, double x, double y, double z, double mx, double my, double mz, Elements element)
/* 55:   */   {
/* 56:34 */     int elementType = element.ordinal();
/* 57:35 */     switch (particle)
/* 58:   */     {
/* 59:   */     case 1: 
/* 60:36 */       Minecraft.getMinecraft().effectRenderer.addEffect(new EffectElementTornado(worldObj, x, y, z, mx, my, mz, elementType));return;
/* 61:   */     case 2: 
/* 62:37 */       Minecraft.getMinecraft().effectRenderer.addEffect(new EffectSmokeElement(worldObj, x, y, z, mx, my, mz, element));return;
/* 63:   */     }
/* 64:38 */     Minecraft.getMinecraft().effectRenderer.addEffect(new EffectElement(worldObj, x, y, z, mx, my, mz, elementType));
/* 65:   */   }
/* 66:   */   
/* 67:   */   public static boolean shouldSpawnParticle()
/* 68:   */   {
/* 69:45 */     return Minecraft.getMinecraft().gameSettings.particleSetting == 0;
/* 70:   */   }
/* 71:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.particles.EffectManager
 * JD-Core Version:    0.7.1
 */