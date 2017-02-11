/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class SpellProjectile
/*  9:   */   extends SpellBase
/* 10:   */ {
/* 11:   */   public void onShoot(EntityLivingBase shooter, Elements element, ItemStack is, int chargeTime)
/* 12:   */   {
/* 13:13 */     World world = shooter.worldObj;
/* 14:14 */     if (!world.isRemote)
/* 15:   */     {
/* 16:15 */       int type = getType();
/* 17:16 */       EntityBaseBall ball = new EntityBaseBall(world, shooter, type, getExpansion(is), element);
/* 18:17 */       ball.setDamageMultiplier(1.0F + getDamage(is) * 0.25F);
/* 19:18 */       world.spawnEntityInWorld(ball);
/* 20:   */     }
/* 21:   */   }
/* 22:   */   
/* 23:   */   public int getType()
/* 24:   */   {
/* 25:31 */     return 100;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public boolean isProjectile()
/* 29:   */   {
/* 30:35 */     return true;
/* 31:   */   }
/* 32:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.SpellProjectile
 * JD-Core Version:    0.7.1
 */