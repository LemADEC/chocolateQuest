/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  4:   */ import com.chocolate.chocolateQuest.entity.projectile.ProjectileMagicShield;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.item.ItemStack;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ 
/*  9:   */ public class SpellProjectileShield
/* 10:   */   extends SpellProjectile
/* 11:   */ {
/* 12:   */   public int getCoolDown()
/* 13:   */   {
/* 14:15 */     return 100;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void onShoot(EntityLivingBase shooter, Elements element, ItemStack is, int chargeTime)
/* 18:   */   {
/* 19:21 */     World world = shooter.worldObj;
/* 20:22 */     if (!world.isRemote)
/* 21:   */     {
/* 22:23 */       int type = getType();
/* 23:24 */       EntityBaseBall ball = new EntityBaseBall(world, shooter, type, getExpansion(is), element);
/* 24:25 */       ball.setBallData(new ProjectileMagicShield(ball, shooter));
/* 25:26 */       ball.posX += 1.5D;
/* 26:27 */       ball.setDamageMultiplier(1.0F + getDamage(is) * 0.25F);
/* 27:28 */       world.spawnEntityInWorld(ball);
/* 28:   */     }
/* 29:   */   }
/* 30:   */   
/* 31:   */   public int getType()
/* 32:   */   {
/* 33:33 */     return 103;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public int getCastingTime()
/* 37:   */   {
/* 38:37 */     return 10;
/* 39:   */   }
/* 40:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.SpellProjectileShield
 * JD-Core Version:    0.7.1
 */