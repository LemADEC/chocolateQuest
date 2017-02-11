/*  1:   */ package com.chocolate.chocolateQuest.entity.projectile;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.utils.HelperDamageSource;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ import net.minecraft.util.MovingObjectPosition;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class ProjectileVapiricBall
/* 11:   */   extends ProjectileBase
/* 12:   */ {
/* 13:   */   Random rand;
/* 14:   */   
/* 15:   */   public ProjectileVapiricBall(EntityBaseBall entity)
/* 16:   */   {
/* 17:15 */     super(entity);
/* 18:16 */     this.rand = new Random();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public int getTextureIndex()
/* 22:   */   {
/* 23:21 */     return 228;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void onImpact(MovingObjectPosition mop)
/* 27:   */   {
/* 28:27 */     if (!this.entity.worldObj.isRemote) {
/* 29:29 */       if (mop.entityHit != null)
/* 30:   */       {
/* 31:31 */         if ((mop.entityHit instanceof EntityLivingBase)) {
/* 32:33 */           if ((!mop.entityHit.isEntityEqual(this.entity.shootingEntity)) && (mop.entityHit != this.entity.shootingEntity.riddenByEntity) && (mop.entityHit != this.entity.shootingEntity.ridingEntity))
/* 33:   */           {
/* 34:35 */             float damage = (2 + this.entity.getlvl()) * this.entity.getDamageMultiplier();
/* 35:36 */             if (mop.entityHit.attackEntityFrom(HelperDamageSource.getBulletDamage(this.entity, this.entity.getThrower()), damage))
/* 36:   */             {
/* 37:38 */               if (this.entity.getThrower() != null) {
/* 38:40 */                 if (!this.entity.worldObj.isRemote)
/* 39:   */                 {
/* 40:42 */                   EntityBaseBall ball = new EntityBaseBall(this.entity.worldObj, this.entity.getThrower(), 9, 1 + this.entity.getlvl() / 2);
/* 41:43 */                   ball.setPosition(this.entity.posX, this.entity.posY, this.entity.posZ);
/* 42:44 */                   this.entity.worldObj.spawnEntityInWorld(ball);
/* 43:   */                 }
/* 44:   */               }
/* 45:48 */               this.entity.setDead();
/* 46:   */             }
/* 47:   */           }
/* 48:   */         }
/* 49:   */       }
/* 50:   */       else {
/* 51:55 */         this.entity.setDead();
/* 52:   */       }
/* 53:   */     }
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void onUpdateInAir()
/* 57:   */   {
/* 58:63 */     for (int i = 0; i < 1 + this.entity.getlvl(); i++) {
/* 59:65 */       this.entity.worldObj.spawnParticle("portal", this.entity.posX + this.rand.nextFloat() - 0.5D, this.entity.posY + this.rand.nextFloat() - 0.5D, this.entity.posZ + this.rand.nextFloat() - 0.5D, this.rand.nextFloat() - 0.5F, this.rand.nextFloat() - 0.5F, this.rand.nextFloat() - 0.5F);
/* 60:   */     }
/* 61:   */   }
/* 62:   */   
/* 63:   */   public float getSize()
/* 64:   */   {
/* 65:72 */     return 0.5F;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public boolean canBounce()
/* 69:   */   {
/* 70:78 */     return false;
/* 71:   */   }
/* 72:   */   
/* 73:   */   public void onSpawn()
/* 74:   */   {
/* 75:83 */     this.entity.worldObj.playSoundEffect((int)this.entity.posX, (int)this.entity.posY, (int)this.entity.posZ, "random.bow", 4.0F, (1.0F + (this.entity.worldObj.rand.nextFloat() - this.entity.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
/* 76:   */   }
/* 77:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileVapiricBall
 * JD-Core Version:    0.7.1
 */