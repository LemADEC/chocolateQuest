/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  5:   */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*  6:   */ import java.io.PrintStream;
/*  7:   */ import java.util.List;
/*  8:   */ import java.util.Random;
/*  9:   */ import net.minecraft.entity.Entity;
/* 10:   */ import net.minecraft.entity.EntityLivingBase;
/* 11:   */ import net.minecraft.entity.player.EntityPlayer;
/* 12:   */ import net.minecraft.item.ItemStack;
/* 13:   */ import net.minecraft.util.AxisAlignedBB;
/* 14:   */ import net.minecraft.util.MathHelper;
/* 15:   */ import net.minecraft.util.Vec3;
/* 16:   */ import net.minecraft.world.World;
/* 17:   */ 
/* 18:   */ public class SpellRepel
/* 19:   */   extends SpellBase
/* 20:   */ {
/* 21:   */   public void onShoot(EntityLivingBase shooter, Elements element, ItemStack is, int chargeTime)
/* 22:   */   {
/* 23:21 */     System.out.println(shooter);
/* 24:22 */     World world = shooter.worldObj;
/* 25:23 */     float rotationYaw = (float)MathHelper.wrapAngleTo180_double(shooter.rotationYawHead);
/* 26:   */     
/* 27:25 */     double armDist = 0.5D;
/* 28:26 */     double offsetY = 0.2D;
/* 29:27 */     if ((shooter instanceof EntityHumanBase)) {
/* 30:28 */       offsetY = 1.4D;
/* 31:   */     }
/* 32:29 */     double posX = shooter.posX - Math.sin(Math.toRadians(rotationYaw)) * armDist;
/* 33:30 */     double posY = shooter.posY + offsetY;
/* 34:31 */     double posZ = shooter.posZ + Math.cos(Math.toRadians(rotationYaw)) * armDist;
/* 36:   */     double x;
/* 37:   */     double y;
/* 38:   */     double z;
/* 39:35 */     if ((shooter instanceof EntityPlayer))
/* 40:   */     {
/* 41:36 */       Vec3 v = shooter.getLookVec();
/* 42:37 */       x = v.xCoord;
/* 43:38 */       y = v.yCoord;
/* 44:39 */       z = v.zCoord;
/* 45:   */     }
/* 46:   */     else
/* 47:   */     {
/* 48:41 */       x = (float)-Math.sin(Math.toRadians(rotationYaw));
/* 49:42 */       y = -Math.sin(Math.toRadians(shooter.rotationPitch));
/* 50:43 */       z = (float)Math.cos(Math.toRadians(rotationYaw));
/* 51:   */     }
/* 52:45 */     x *= (1.0D - Math.abs(y));
/* 53:46 */     z *= (1.0D - Math.abs(y));
/* 54:47 */     int velocity = 4;
/* 55:48 */     if (world.isRemote)
/* 56:   */     {
/* 57:50 */       Random random = shooter.getRNG();
/* 58:51 */       for (int i = 0; i < 8; i++) {
/* 59:52 */         EffectManager.spawnElementParticle(0, world, posX, posY, posZ, (x * velocity + random.nextFloat() - 0.5D) / 3.0D, (y + random.nextFloat() - 0.5D) / 8.0D, (z * velocity + random.nextFloat() - 0.5D) / 3.0D, element);
/* 60:   */       }
/* 61:   */     }
/* 62:   */     else
/* 63:   */     {
/* 64:57 */       int dist = 5;
/* 65:58 */       List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(shooter, shooter.boundingBox.addCoord(shooter.getLookVec().xCoord * dist, shooter.getLookVec().yCoord * dist, shooter.getLookVec().zCoord * dist).expand(1.0D, 1.0D, 1.0D));
/* 66:59 */       for (Entity e : list) {
/* 67:61 */         if (((e instanceof EntityLivingBase)) && (e != shooter.riddenByEntity))
/* 68:   */         {
/* 69:63 */           e.addVelocity(x * velocity, y * velocity / 2.0D, z * velocity);
/* 70:   */         }
/* 71:64 */         else if ((e instanceof EntityBaseBall))
/* 72:   */         {
/* 73:65 */           e.addVelocity(x * velocity, y * velocity, z * velocity);
/* 74:66 */           ((EntityBaseBall)e).setThrower(shooter);
/* 75:   */         }
/* 76:   */       }
/* 77:   */     }
/* 78:   */   }
/* 79:   */   
/* 80:   */   public int getRange(ItemStack itemstack)
/* 81:   */   {
/* 82:74 */     return 5;
/* 83:   */   }
/* 84:   */   
/* 85:   */   public boolean isProjectile()
/* 86:   */   {
/* 87:78 */     return true;
/* 88:   */   }
/* 89:   */   
/* 90:   */   public int getCastingTime()
/* 91:   */   {
/* 92:82 */     return 4;
/* 93:   */   }
/* 94:   */   
/* 95:   */   public int getCoolDown()
/* 96:   */   {
/* 97:86 */     return 5;
/* 98:   */   }
/* 99:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.magic.SpellRepel

 * JD-Core Version:    0.7.1

 */
