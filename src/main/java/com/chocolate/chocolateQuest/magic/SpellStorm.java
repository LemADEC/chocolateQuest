/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  4:   */ import com.chocolate.chocolateQuest.utils.HelperPlayer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityLiving;
/*  7:   */ import net.minecraft.entity.EntityLivingBase;
/*  8:   */ import net.minecraft.entity.player.EntityPlayer;
/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ import net.minecraft.util.MovingObjectPosition;
/* 11:   */ import net.minecraft.util.Vec3;
/* 12:   */ import net.minecraft.world.World;
/* 13:   */ 
/* 14:   */ public class SpellStorm
/* 15:   */   extends SpellBase
/* 16:   */ {
/* 17:   */   public void onShoot(EntityLivingBase shooter, Elements element, ItemStack is, int chargeTime)
/* 18:   */   {
/* 19:19 */     World world = shooter.worldObj;
/* 20:20 */     if (!world.isRemote) {
/* 21:21 */       if ((shooter instanceof EntityPlayer))
/* 22:   */       {
/* 23:23 */         MovingObjectPosition mop = HelperPlayer.getBlockMovingObjectPositionFromPlayer(shooter.worldObj, (EntityPlayer)shooter, 60.0D, true);
/* 24:24 */         if (mop != null)
/* 25:   */         {
/* 26:25 */           shootBallAt(shooter, element, mop.blockX, mop.blockY, mop.blockZ, is);
/* 27:   */         }
/* 28:   */         else
/* 29:   */         {
/* 30:27 */           Vec3 look = shooter.getLookVec();
/* 31:28 */           double dist = 10.0D;
/* 32:29 */           shootBallAt(shooter, element, shooter.posX + look.xCoord * dist, shooter.posY, shooter.posZ + look.zCoord * dist, is);
/* 33:   */         }
/* 34:   */       }
/* 35:   */       else
/* 36:   */       {
/* 37:32 */         Vec3 look = shooter.getLookVec();
/* 38:33 */         double dist = 10.0D;
/* 39:34 */         Entity target = ((EntityLiving)shooter).getAttackTarget();
/* 40:35 */         if (target != null) {
/* 41:36 */           dist = shooter.getDistanceToEntity(target);
/* 42:   */         }
/* 43:37 */         shootBallAt(shooter, element, shooter.posX + look.xCoord * dist, shooter.posY, shooter.posZ + look.zCoord * dist, is);
/* 44:   */       }
/* 45:   */     }
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void shootBallAt(EntityLivingBase shooter, Elements element, double x, double y, double z, ItemStack is)
/* 49:   */   {
/* 50:43 */     World world = shooter.worldObj;
/* 51:44 */     int type = getType();
/* 52:45 */     EntityBaseBall ball = new EntityBaseBall(world, shooter, type, getExpansion(is), element);
/* 53:46 */     ball.posX = x;
/* 54:47 */     ball.posY = y;
/* 55:48 */     ball.posZ = z;
/* 56:49 */     ball.setDamageMultiplier(1.0F + getDamage(is) * 0.25F);
/* 57:50 */     world.spawnEntityInWorld(ball);
/* 58:   */   }
/* 59:   */   
/* 60:   */   public int getCoolDown()
/* 61:   */   {
/* 62:55 */     return 300;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public int getRange(ItemStack itemstack)
/* 66:   */   {
/* 67:60 */     return 32;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public int getCastingTime()
/* 71:   */   {
/* 72:65 */     return 40;
/* 73:   */   }
/* 74:   */   
/* 75:   */   public int getType()
/* 76:   */   {
/* 77:68 */     return 104;
/* 78:   */   }
/* 79:   */   
/* 80:   */   public boolean isProjectile()
/* 81:   */   {
/* 82:72 */     return true;
/* 83:   */   }
/* 84:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.SpellStorm
 * JD-Core Version:    0.7.1
 */