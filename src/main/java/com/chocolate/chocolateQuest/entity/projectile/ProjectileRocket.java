/*  1:   */ package com.chocolate.chocolateQuest.entity.projectile;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.util.Vec3;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ 
/*  9:   */ public class ProjectileRocket
/* 10:   */   extends ProjectileBulletPistol
/* 11:   */ {
/* 12:   */   protected Entity aimedTo;
/* 13:   */   int ticksToStartAim;
/* 14:   */   
/* 15:   */   public ProjectileRocket(EntityBaseBall entity)
/* 16:   */   {
/* 17:14 */     super(entity);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public ProjectileRocket(EntityBaseBall entity, Entity aimedTo)
/* 21:   */   {
/* 22:19 */     this(entity, aimedTo, 0);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public ProjectileRocket(EntityBaseBall entity, Entity aimedTo, int ticksToStartAim)
/* 26:   */   {
/* 27:23 */     super(entity);
/* 28:24 */     this.aimedTo = aimedTo;
/* 29:25 */     this.ticksToStartAim = ticksToStartAim;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void onUpdateInAir()
/* 33:   */   {
/* 34:31 */     if ((this.aimedTo != null) && (this.entity.ticksExisted > this.ticksToStartAim))
/* 35:   */     {
/* 36:32 */       double x = this.aimedTo.posX - this.entity.posX;
/* 37:33 */       double y = this.aimedTo.posY - this.entity.posY;
/* 38:34 */       double z = this.aimedTo.posZ - this.entity.posZ;
/* 39:35 */       Vec3 v = Vec3.createVectorHelper(x, y, z);
/* 40:36 */       v = v.normalize();
/* 41:37 */       double xM = v.xCoord;
/* 42:38 */       double yM = v.yCoord;
/* 43:39 */       double zM = v.zCoord;
/* 44:40 */       float desp = 0.2F;
/* 45:41 */       this.entity.motionX += xM * desp;
/* 46:42 */       this.entity.motionY += yM * desp;
/* 47:43 */       this.entity.motionZ += zM * desp;
/* 48:44 */       desp *= 20.0F;
/* 49:45 */       if (yM > 0.0D) {
/* 50:46 */         this.entity.motionY = Math.min(this.entity.motionY, yM * desp);
/* 51:   */       } else {
/* 52:48 */         this.entity.motionY = Math.max(this.entity.motionY, yM * desp);
/* 53:   */       }
/* 54:49 */       if (xM > 0.0D) {
/* 55:50 */         this.entity.motionX = Math.min(this.entity.motionX, xM * desp);
/* 56:   */       } else {
/* 57:52 */         this.entity.motionX = Math.max(this.entity.motionX, xM * desp);
/* 58:   */       }
/* 59:53 */       if (zM > 0.0D) {
/* 60:54 */         this.entity.motionZ = Math.min(this.entity.motionZ, zM * desp);
/* 61:   */       } else {
/* 62:56 */         this.entity.motionZ = Math.max(this.entity.motionZ, zM * desp);
/* 63:   */       }
/* 64:   */     }
/* 65:59 */     if (this.entity.worldObj.isRemote)
/* 66:   */     {
/* 67:60 */       float s = 0.2F;
/* 68:61 */       float m = 0.0F;
/* 69:62 */       int maxCount = 2;
/* 70:63 */       for (int i = 0; i <= maxCount; i++)
/* 71:   */       {
/* 72:64 */         m = i / maxCount;
/* 73:65 */         EffectManager.spawnParticle(6, this.entity.worldObj, this.entity.posX + (this.rand.nextFloat() - 0.5F) * s - this.entity.motionX * m, this.entity.posY + (this.rand.nextFloat() - 0.5F) * s - this.entity.motionY * m, this.entity.posZ + (this.rand.nextFloat() - 0.5F) * s - this.entity.motionZ * m, 1.0D, 1.0D, 1.0D);
/* 74:   */       }
/* 75:   */     }
/* 76:   */   }
/* 77:   */   
/* 78:   */   protected int getBulletBaseDamage()
/* 79:   */   {
/* 80:73 */     return 10;
/* 81:   */   }
/* 82:   */   
/* 83:   */   public float getBulletPitch()
/* 84:   */   {
/* 85:77 */     return 0.4F;
/* 86:   */   }
/* 87:   */   
/* 88:   */   public float getSize()
/* 89:   */   {
/* 90:82 */     return 0.2F;
/* 91:   */   }
/* 92:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileRocket
 * JD-Core Version:    0.7.1
 */