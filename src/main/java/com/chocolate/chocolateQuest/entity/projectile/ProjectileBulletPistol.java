/*   1:    */ package com.chocolate.chocolateQuest.entity.projectile;
/*   2:    */
/*   3:    */ import com.chocolate.chocolateQuest.magic.Elements;
/*   4:    */ import com.chocolate.chocolateQuest.utils.HelperDamageSource;
/*   5:    */ import java.util.Random;
/*   6:    */ import net.minecraft.block.Block;
/*   7:    */ import net.minecraft.block.material.Material;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.entity.EntityLivingBase;
/*  10:    */ import net.minecraft.util.DamageSource;
/*  11:    */ import net.minecraft.util.MovingObjectPosition;
/*  12:    */ import net.minecraft.world.World;
/*  13:    */
/*  14:    */ public class ProjectileBulletPistol
/*  15:    */   extends ProjectileBase
/*  16:    */ {
/*  17:    */   public static final int IRON = 0;
/*  18:    */   public static final int GOLD = 1;
/*  19:    */   public static final int MAGIC = 2;
/*  20:    */   public static final int FIRE = 3;
/*  21:    */   public static final int CANNON = 4;
/*  22:    */   Random rand;
/*  23:    */
/*  24:    */   public ProjectileBulletPistol(EntityBaseBall entity)
/*  25:    */   {
/*  26: 19 */     super(entity);
/*  27: 20 */     this.rand = new Random();
/*  28:    */   }
/*  29:    */
/*  30:    */   public int getTextureIndex()
/*  31:    */   {
/*  32: 25 */     if (this.entity.getlvl() == 1) {
/*  33: 26 */       return 225;
/*  34:    */     }
/*  35: 27 */     if (this.entity.getlvl() == 2) {
/*  36: 28 */       return 226;
/*  37:    */     }
/*  38: 29 */     if (this.entity.getlvl() == 3) {
/*  39: 30 */       return 227;
/*  40:    */     }
/*  41: 31 */     return 224;
/*  42:    */   }
/*  43:    */
/*  44:    */   public void onImpact(MovingObjectPosition mop)
/*  45:    */   {
/*  46: 37 */     if (!this.entity.worldObj.isRemote) {
/*  47: 39 */       if (mop.entityHit != null)
/*  48:    */       {
/*  49: 41 */         if ((mop.entityHit != this.entity.shootingEntity.riddenByEntity) && (mop.entityHit != this.entity.shootingEntity.ridingEntity))
/*  50:    */         {





/*  51: 43 */           float damage = getBulletBaseDamage();
/*  52: 44 */           int bulletType = this.entity.getlvl();



/*  53: 45 */           if (bulletType == 1) {
/*  54: 46 */             damage += damage * 0.66F;
/*  55:    */           }
/*  56: 47 */           if (bulletType == 4) {
/*  57: 48 */             damage *= 2.0F;
/*  58:    */           }



/*  59: 49 */           damage *= this.entity.damageMultiplier;
/*  60:    */           DamageSource ds;




/*  62: 51 */           if (bulletType == 3)
/*  63:    */           {
/*  64: 52 */             ds = Elements.fire.getDamageSourceIndirect(this.entity.getThrower(), this.entity);
/*  65:    */           }
/*  66:    */           else
/*  67:    */           {
/*  69: 53 */             if (bulletType == 2) {
/*  70: 54 */               ds = Elements.magic.getDamageSourceIndirect(this.entity.getThrower(), this.entity);
/*  71:    */             } else {
/*  72: 56 */               ds = Elements.blast.getDamageSourceIndirect(this.entity.getThrower(), this.entity);
/*  73:    */             }
/*  74:    */           }
/*  75: 57 */           ds.setProjectile();
/*  76: 58 */           if (HelperDamageSource.attackEntityWithoutKnockBack(mop.entityHit, ds, damage))
/*  77:    */           {
/*  78: 59 */             if (bulletType != 4) {
/*  79: 60 */               this.entity.setDead();
/*  80:    */             }
/*  81: 61 */             if ((mop.entityHit instanceof EntityLivingBase)) {
/*  82: 62 */               ((EntityLivingBase)mop.entityHit).hurtResistantTime = 1;
/*  83:    */             }
/*  84: 63 */             mop.entityHit.motionX = (mop.entityHit.motionZ = 0.0D);
/*  85:    */           }









/*  86:    */         }
/*  87:    */       }
/*  88:    */       else
/*  89:    */       {
/*  90: 69 */         Material mat = this.entity.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial();
/*  91: 75 */         if ((mat != Material.air) && (mat != Material.fire) && (mat != Material.plants) && (mat != Material.vine) && (mat != Material.web)) {
/*  92: 77 */           this.entity.setDead();
/*  93:    */         }
/*  94:    */       }
/*  95:    */     }
/*  96:    */   }
/*  97:    */
/*  98:    */   protected int getBulletBaseDamage()
/*  99:    */   {
/* 100: 84 */     return 6;
/* 101:    */   }
/* 102:    */
/* 103:    */   public void onSpawn()
/* 104:    */   {
/* 105: 89 */     this.entity.worldObj.playSoundAtEntity(this.entity, "chocolateQuest:handgun", 0.2F, getBulletPitch());
/* 106:    */   }
/* 107:    */
/* 108:    */   public float getBulletPitch()
/* 109:    */   {
/* 110: 92 */     return 1.0F;
/* 111:    */   }
/* 112:    */
/* 113:    */   public void onUpdateInAir()
/* 114:    */   {
/* 115: 97 */     if (this.entity.worldObj.isRemote) {
/* 116: 99 */       this.entity.worldObj.spawnParticle("smoke", this.entity.posX, this.entity.posY, this.entity.posZ, 0.0D, 0.0D, 0.0D);
/* 117:    */     }
/* 118:    */   }
/* 119:    */
/* 120:    */   public float getSize()
/* 121:    */   {
/* 122:106 */     if (this.entity.getlvl() >= 4) {
/* 123:107 */       return 0.4F;
/* 124:    */     }
/* 125:108 */     return 0.1F;
/* 126:    */   }
/* 127:    */
/* 128:    */   public boolean canBounce()
/* 129:    */   {
/* 130:114 */     return false;
/* 131:    */   }
/* 132:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileBulletPistol
 * JD-Core Version:    0.7.1
 */




