/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  4:   */ import com.chocolate.chocolateQuest.entity.projectile.ProjectileMagicAimed;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.item.ItemStack;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ 
/*  9:   */ public class SpellProjectileBoomerang
/* 10:   */   extends SpellProjectile
/* 11:   */ {
/* 12:   */   public int getCoolDown()
/* 13:   */   {
/* 14:15 */     return 40;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void onShoot(EntityLivingBase shooter, Elements element, ItemStack is, int chargeTime)
/* 18:   */   {
/* 19:21 */     World world = shooter.worldObj;
/* 20:22 */     if (!world.isRemote)
/* 21:   */     {
/* 22:23 */       int type = getType();
/* 23:24 */       EntityBaseBall ball = new EntityBaseBall(world, shooter, type, getExpansion(is), element);
/* 24:25 */       ball.setBallData(new ProjectileMagicAimed(ball, shooter, 10));
/* 25:26 */       ball.setDamageMultiplier(1.0F + getDamage(is) * 0.25F);
/* 26:27 */       world.spawnEntityInWorld(ball);
/* 27:   */     }
/* 28:   */   }
/* 29:   */   
/* 30:   */   public int getType()
/* 31:   */   {
/* 32:32 */     return 101;
/* 33:   */   }
/* 34:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.SpellProjectileBoomerang
 * JD-Core Version:    0.7.1
 */