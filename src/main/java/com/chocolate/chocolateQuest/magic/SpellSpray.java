/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*  5:   */ import java.util.List;
/*  6:   */ import java.util.Random;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.entity.EntityLivingBase;
/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ import net.minecraft.util.AxisAlignedBB;
/* 11:   */ import net.minecraft.util.DamageSource;
/* 12:   */ import net.minecraft.util.MathHelper;
/* 13:   */ import net.minecraft.util.Vec3;
/* 14:   */ import net.minecraft.world.World;
/* 15:   */ 
/* 16:   */ public class SpellSpray
/* 17:   */   extends SpellBase
/* 18:   */ {
/* 19:   */   public void onUpdate(EntityLivingBase shooter, Elements element, ItemStack is, int angle)
/* 20:   */   {
/* 21:19 */     World world = shooter.worldObj;
/* 22:20 */     float rotationYaw = (float)MathHelper.wrapAngleTo180_double(shooter.rotationYawHead);
/* 23:   */     
/* 24:22 */     double armDist = 0.5D;
/* 25:23 */     double offsetY = 0.2D;
/* 26:24 */     if ((shooter instanceof EntityHumanBase)) {
/* 27:25 */       offsetY = 1.4D;
/* 28:   */     }
/* 29:26 */     double posX = shooter.posX - Math.sin(Math.toRadians(rotationYaw + angle)) * armDist;
/* 30:27 */     double posY = shooter.posY + offsetY;
/* 31:28 */     double posZ = shooter.posZ + Math.cos(Math.toRadians(rotationYaw + angle)) * armDist;
/* 32:29 */     float x = (float)-Math.sin(Math.toRadians(rotationYaw));
/* 33:30 */     float z = (float)Math.cos(Math.toRadians(rotationYaw));
/* 34:31 */     double y = -Math.sin(Math.toRadians(shooter.rotationPitch));
/* 35:32 */     x = (float)(x * (1.0D - Math.abs(y)));
/* 36:33 */     z = (float)(z * (1.0D - Math.abs(y)));
/* 37:34 */     if (world.isRemote)
/* 38:   */     {
/* 39:36 */       Random random = shooter.getRNG();
/* 40:37 */       for (int i = 0; i < 4; i++) {
/* 41:38 */         EffectManager.spawnElementParticle(0, world, posX, posY, posZ, (x + random.nextFloat() - 0.5D) / 3.0D, (y + random.nextFloat() - 0.5D) / 8.0D, (z + random.nextFloat() - 0.5D) / 3.0D, element);
/* 42:   */       }
/* 43:   */     }
/* 44:   */     else
/* 45:   */     {
/* 46:43 */       int dist = 5;
/* 47:44 */       List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(shooter, shooter.boundingBox.addCoord(shooter.getLookVec().xCoord * dist, shooter.getLookVec().yCoord * dist, shooter.getLookVec().zCoord * dist).expand(1.0D, 1.0D, 1.0D));
/* 48:45 */       for (Entity e : list) {
/* 49:47 */         if (((e instanceof EntityLivingBase)) && (e != shooter.riddenByEntity))
/* 50:   */         {
/* 51:49 */           double d = posX - e.posX;
/* 52:50 */           double d2 = posZ - e.posZ;
/* 53:51 */           double rotDiff = Math.atan2(d, d2);
/* 54:52 */           rotDiff = rotDiff * 180.0D / 3.141592D;
/* 55:53 */           rotDiff = -MathHelper.wrapAngleTo180_double(rotDiff - 180.0D);
/* 56:54 */           rotDiff -= rotationYaw;
/* 57:55 */           if (Math.abs(rotDiff) < 30.0D)
/* 58:   */           {
/* 59:56 */             float damage = 1.0F + getDamage(is) * 0.25F;
/* 60:57 */             damage = element.onHitEntity(shooter, e, damage);
/* 61:58 */             DamageSource ds = element.getDamageSource(shooter);
/* 62:59 */             e.attackEntityFrom(ds, damage);
/* 63:   */           }
/* 64:   */         }
/* 65:   */       }
/* 66:   */     }
/* 67:   */   }
/* 68:   */   
/* 69:   */   public int getRange(ItemStack itemstack)
/* 70:   */   {
/* 71:67 */     return 5;
/* 72:   */   }
/* 73:   */   
/* 74:   */   public boolean shouldUpdate()
/* 75:   */   {
/* 76:71 */     return true;
/* 77:   */   }
/* 78:   */   
/* 79:   */   public int getCastingTime()
/* 80:   */   {
/* 81:75 */     return 25;
/* 82:   */   }
/* 83:   */   
/* 84:   */   public int getCoolDown()
/* 85:   */   {
/* 86:79 */     return 60;
/* 87:   */   }
/* 88:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.SpellSpray
 * JD-Core Version:    0.7.1
 */