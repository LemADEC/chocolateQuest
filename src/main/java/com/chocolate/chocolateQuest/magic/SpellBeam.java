/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.projectile.EntityProjectileBeam;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.entity.player.EntityPlayer;
/*  6:   */ import net.minecraft.item.ItemStack;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ 
/*  9:   */ public class SpellBeam
/* 10:   */   extends SpellBase
/* 11:   */ {
/* 12:   */   public void onUpdate(EntityLivingBase shooter, Elements element, ItemStack is, int angle) {}
/* 13:   */   
/* 14:   */   public void onCastStart(EntityLivingBase shooter, Elements element, ItemStack is)
/* 15:   */   {
/* 16:15 */     float dist = 0.3F;
/* 17:16 */     float height = -0.1F;
/* 18:17 */     if (!(shooter instanceof EntityPlayer)) {
/* 19:18 */       height = shooter.height;
/* 20:   */     }
/* 21:20 */     EntityProjectileBeam e = new EntityProjectileBeam(shooter.worldObj, shooter, 90.0F, dist, height, element);
/* 22:21 */     shooter.worldObj.spawnEntityInWorld(e);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void onShoot(EntityLivingBase shooter, Elements element, ItemStack is, int chargeTime) {}
/* 26:   */   
/* 27:   */   public boolean isProjectile()
/* 28:   */   {
/* 29:29 */     return true;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public int getRange(ItemStack itemstack)
/* 33:   */   {
/* 34:33 */     return 16;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public boolean shouldUpdate()
/* 38:   */   {
/* 39:37 */     return true;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public int getCastingTime()
/* 43:   */   {
/* 44:41 */     return 30;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public int getCoolDown()
/* 48:   */   {
/* 49:45 */     return 60;
/* 50:   */   }
/* 51:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.SpellBeam
 * JD-Core Version:    0.7.1
 */