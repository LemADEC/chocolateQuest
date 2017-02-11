/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  4:   */ import com.chocolate.chocolateQuest.entity.projectile.ProjectileMagicAimed;
/*  5:   */ import com.chocolate.chocolateQuest.utils.HelperPlayer;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.entity.EntityLiving;
/*  8:   */ import net.minecraft.entity.EntityLivingBase;
/*  9:   */ import net.minecraft.entity.player.EntityPlayer;
/* 10:   */ import net.minecraft.item.ItemStack;
/* 11:   */ import net.minecraft.util.MovingObjectPosition;
/* 12:   */ import net.minecraft.world.World;
/* 13:   */ 
/* 14:   */ public class SpellProjectileAimed
/* 15:   */   extends SpellProjectile
/* 16:   */ {
/* 17:   */   public int getRange(ItemStack itemstack)
/* 18:   */   {
/* 19:19 */     return 32;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public int getCoolDown()
/* 23:   */   {
/* 24:23 */     return 12;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void onShoot(EntityLivingBase shooter, Elements element, ItemStack is, int chargeTime)
/* 28:   */   {
/* 29:29 */     World world = shooter.worldObj;
/* 30:30 */     Entity target = null;
/* 31:31 */     if ((shooter instanceof EntityPlayer))
/* 32:   */     {
/* 33:32 */       MovingObjectPosition mop = HelperPlayer.getMovingObjectPositionFromPlayer((EntityPlayer)shooter, world, 60.0D, 2.0D);
/* 34:33 */       if (mop != null) {
/* 35:34 */         target = mop.entityHit;
/* 36:   */       }
/* 37:   */     }
/* 38:   */     else
/* 39:   */     {
/* 40:37 */       target = ((EntityLiving)shooter).getAttackTarget();
/* 41:   */     }
/* 42:39 */     if (!world.isRemote)
/* 43:   */     {
/* 44:40 */       int type = getType();
/* 45:41 */       EntityBaseBall ball = new EntityBaseBall(world, shooter, type, getExpansion(is), element);
/* 46:42 */       if (target != null) {
/* 47:43 */         ball.setBallData(new ProjectileMagicAimed(ball, target));
/* 48:   */       }
/* 49:45 */       ball.setDamageMultiplier(1.0F + getDamage(is) * 0.25F);
/* 50:46 */       world.spawnEntityInWorld(ball);
/* 51:   */     }
/* 52:   */   }
/* 53:   */   
/* 54:   */   public int getType()
/* 55:   */   {
/* 56:51 */     return 101;
/* 57:   */   }
/* 58:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.SpellProjectileAimed
 * JD-Core Version:    0.7.1
 */