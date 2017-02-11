/*  1:   */ package com.chocolate.chocolateQuest.entity.projectile;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.utils.HelperDamageSource;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ import net.minecraft.util.MovingObjectPosition;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class ProjectileFireBall
/* 11:   */   extends ProjectileBase
/* 12:   */ {
/* 13:   */   Random rand;
/* 14:   */   
/* 15:   */   public ProjectileFireBall(EntityBaseBall entity)
/* 16:   */   {
/* 17:16 */     super(entity);
/* 18:17 */     this.rand = new Random();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public int getTextureIndex()
/* 22:   */   {
/* 23:22 */     return this.entity.ticksExisted * 0.33D % 2.0D == 0.0D ? 234 : 250;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void onImpact(MovingObjectPosition mop)
/* 27:   */   {
/* 28:28 */     if (!this.entity.worldObj.isRemote)
/* 29:   */     {
/* 30:30 */       Entity e = mop.entityHit;
/* 31:32 */       if (e != null)
/* 32:   */       {
/* 33:34 */         if (((e instanceof EntityLivingBase)) && (!e.isEntityEqual(this.entity.shootingEntity))) {
/* 34:36 */           if ((mop.entityHit != this.entity.shootingEntity.riddenByEntity) && (mop.entityHit != this.entity.shootingEntity.ridingEntity))
/* 35:   */           {
/* 36:38 */             e.attackEntityFrom(HelperDamageSource.causeFireProjectileDamage(this.entity, this.entity.shootingEntity), 3.0F);
/* 37:39 */             e.setFire(8);
/* 38:40 */             this.entity.worldObj.playSoundEffect((int)e.posX, (int)e.posY, (int)e.posZ, "fire.fire", 4.0F, (1.0F + (this.entity.worldObj.rand.nextFloat() - this.entity.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
/* 39:41 */             this.entity.setDead();
/* 40:   */           }
/* 41:   */         }
/* 42:   */       }
/* 43:   */       else {
/* 44:47 */         this.entity.setDead();
/* 45:   */       }
/* 46:   */     }
/* 47:   */   }
/* 48:   */   
/* 49:   */   public void onUpdateInAir()
/* 50:   */   {
/* 51:55 */     if (this.entity.worldObj.isRemote) {
/* 52:57 */       for (int i = 0; i < 1 + this.entity.getlvl(); i++) {
/* 53:59 */         this.entity.worldObj.spawnParticle("fire", this.entity.posX + this.rand.nextFloat() - 0.5D, this.entity.posY + this.rand.nextFloat() - 0.5D, this.entity.posZ + this.rand.nextFloat() - 0.5D, this.rand.nextFloat() - 0.5F, this.rand.nextFloat() - 0.5F, this.rand.nextFloat() - 0.5F);
/* 54:   */       }
/* 55:   */     }
/* 56:   */   }
/* 57:   */   
/* 58:   */   public float getSize()
/* 59:   */   {
/* 60:67 */     return 0.5F;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public boolean canBounce()
/* 64:   */   {
/* 65:73 */     return false;
/* 66:   */   }
/* 67:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileFireBall
 * JD-Core Version:    0.7.1
 */