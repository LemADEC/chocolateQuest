/*  1:   */ package com.chocolate.chocolateQuest.entity.projectile;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.magic.Elements;
/*  4:   */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*  5:   */ import java.util.List;
/*  6:   */ import java.util.Random;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.entity.EntityLivingBase;
/*  9:   */ import net.minecraft.util.AxisAlignedBB;
/* 10:   */ import net.minecraft.util.MovingObjectPosition;
/* 11:   */ import net.minecraft.world.World;
/* 12:   */ 
/* 13:   */ public class ProjectileMagicShield
/* 14:   */   extends ProjectileMagicAimed
/* 15:   */ {
/* 16:16 */   int deathTime = 0;
/* 17:17 */   final int MAX_HEALTH = 600;
/* 18:17 */   final int HEALTH_PER_LEVEL = 200;
/* 19:   */   
/* 20:   */   public ProjectileMagicShield(EntityBaseBall entity)
/* 21:   */   {
/* 22:20 */     super(entity);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public ProjectileMagicShield(EntityBaseBall entity, Entity aimedTo)
/* 26:   */   {
/* 27:25 */     this(entity, aimedTo, 0);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public ProjectileMagicShield(EntityBaseBall entity, Entity aimedTo, int ticksToStartAim)
/* 31:   */   {
/* 32:29 */     super(entity, aimedTo);
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void onUpdateInAir()
/* 36:   */   {
/* 37:35 */     this.deathTime += 1;
/* 38:36 */     this.entity.motionX = (this.entity.motionZ = this.entity.motionY = 0.0D);
/* 39:37 */     if ((this.aimedTo != null) && (this.entity.ticksExisted > this.ticksToStartAim) && 
/* 40:38 */       (this.aimedTo != null) && (!this.entity.worldObj.isRemote))
/* 41:   */     {
/* 42:40 */       int lifeTime = this.entity.ticksExisted;
/* 43:41 */       double x = this.aimedTo.posX + Math.cos(lifeTime / 20.0F) * 2.0D;
/* 44:42 */       double z = this.aimedTo.posZ + Math.sin(lifeTime / 20.0F) * 2.0D;
/* 45:43 */       this.entity.setPosition(x, this.aimedTo.posY, z);
/* 46:   */       
/* 47:45 */       double dist = 0.3D;
/* 48:46 */       AxisAlignedBB var3 = this.entity.boundingBox.expand(dist, dist, dist);
/* 49:47 */       List<Entity> list = this.entity.worldObj.getEntitiesWithinAABB(Entity.class, var3);
/* 50:48 */       for (Entity et : list) {
/* 51:50 */         if ((et instanceof EntityLivingBase))
/* 52:   */         {
/* 53:52 */           EntityLivingBase e = (EntityLivingBase)et;
/* 54:53 */           if (e != this.entity.getThrower())
/* 55:   */           {
/* 56:55 */             if ((this.entity.getThrower() != null) && 
/* 57:56 */               (this.entity.getThrower().getTeam() != null) && 
/* 58:57 */               (this.entity.getThrower().isOnSameTeam(e))) {
/* 59:58 */               return;
/* 60:   */             }
/* 61:60 */             Elements element = this.entity.getElement();
/* 62:61 */             float damage = 4.0F * this.entity.getDamageMultiplier();
/* 63:62 */             damage = element.onHitEntity(this.entity.getThrower(), e, damage);
/* 64:63 */             e.attackEntityFrom(element.getDamageSourceIndirect(this.entity, this.entity.getThrower()), damage);
/* 65:64 */             this.deathTime += 30;
/* 66:   */           }
/* 67:   */         }
/* 68:   */       }
/* 69:   */     }
/* 70:70 */     if (this.deathTime > getMaxLifeTime()) {
/* 71:71 */       this.entity.setDead();
/* 72:   */     }
/* 73:74 */     if (this.entity.worldObj.isRemote)
/* 74:   */     {
/* 75:76 */       Random rand = this.entity.worldObj.rand;
/* 76:77 */       EffectManager.spawnElementParticle(0, this.entity.worldObj, this.entity.posX + rand.nextFloat() - 0.5D, this.entity.posY + rand.nextFloat() - 0.5D, this.entity.posZ + rand.nextFloat() - 0.5D, 0.0D, 0.0D, 0.0D, this.entity.getElement());
/* 77:   */     }
/* 78:   */   }
/* 79:   */   
/* 80:   */   public int getMaxLifeTime()
/* 81:   */   {
/* 82:84 */     return 600 + 200 * this.entity.getlvl();
/* 83:   */   }
/* 84:   */   
/* 85:   */   public void onImpact(MovingObjectPosition par1MovingObjectPosition) {}
/* 86:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileMagicShield
 * JD-Core Version:    0.7.1
 */