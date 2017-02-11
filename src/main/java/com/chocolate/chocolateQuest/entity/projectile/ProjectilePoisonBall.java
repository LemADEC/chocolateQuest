/*  1:   */ package com.chocolate.chocolateQuest.entity.projectile;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*  4:   */ import com.chocolate.chocolateQuest.utils.HelperDamageSource;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ import net.minecraft.entity.player.EntityPlayer;
/*  8:   */ import net.minecraft.entity.player.PlayerCapabilities;
/*  9:   */ import net.minecraft.potion.Potion;
/* 10:   */ import net.minecraft.potion.PotionEffect;
/* 11:   */ import net.minecraft.util.MovingObjectPosition;
/* 12:   */ import net.minecraft.world.World;
/* 13:   */ 
/* 14:   */ public class ProjectilePoisonBall
/* 15:   */   extends ProjectileBase
/* 16:   */ {
/* 17:15 */   int particleEffect = 4;
/* 18:   */   
/* 19:   */   public ProjectilePoisonBall(EntityBaseBall entity)
/* 20:   */   {
/* 21:18 */     super(entity);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public int getTextureIndex()
/* 25:   */   {
/* 26:24 */     return 246;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void onImpact(MovingObjectPosition par1MovingObjectPosition)
/* 30:   */   {
/* 31:30 */     if (!this.entity.worldObj.isRemote)
/* 32:   */     {
/* 33:32 */       Entity e = par1MovingObjectPosition.entityHit;
/* 34:34 */       if (e != null)
/* 35:   */       {
/* 36:36 */         if (((e instanceof EntityLivingBase)) && (e != this.entity.shootingEntity))
/* 37:   */         {
/* 38:38 */           if (((e instanceof EntityPlayer)) && 
/* 39:39 */             (((EntityPlayer)e).capabilities.disableDamage)) {
/* 40:40 */             return;
/* 41:   */           }
/* 42:42 */           e.attackEntityFrom(HelperDamageSource.causeProjectilePhysicalDamage(this.entity, this.entity.getThrower()), 4.0F);
/* 43:43 */           ((EntityLivingBase)e).addPotionEffect(new PotionEffect(Potion.poison.id, 120, 1));
/* 44:   */         }
/* 45:   */       }
/* 46:   */       else {
/* 47:48 */         this.entity.setDead();
/* 48:   */       }
/* 49:   */     }
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void onUpdateInAir()
/* 53:   */   {
/* 54:55 */     if (this.entity.worldObj.isRemote) {
/* 55:56 */       EffectManager.spawnParticle(2, this.entity.worldObj, this.entity.posX, this.entity.posY + 1.0D, this.entity.posZ, 0.0D, 0.4D, 0.0D);
/* 56:   */     }
/* 57:60 */     super.onUpdateInAir();
/* 58:   */   }
/* 59:   */   
/* 60:   */   public float getGravityVelocity()
/* 61:   */   {
/* 62:65 */     return 0.0F;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public float getSize()
/* 66:   */   {
/* 67:71 */     return 0.8F;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public boolean longRange()
/* 71:   */   {
/* 72:75 */     return false;
/* 73:   */   }
/* 74:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectilePoisonBall
 * JD-Core Version:    0.7.1
 */