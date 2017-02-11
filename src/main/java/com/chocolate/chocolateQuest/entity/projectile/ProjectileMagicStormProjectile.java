/*  1:   */ package com.chocolate.chocolateQuest.entity.projectile;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.magic.Elements;
/*  4:   */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*  5:   */ import com.chocolate.chocolateQuest.utils.HelperDamageSource;
/*  6:   */ import java.util.List;
/*  7:   */ import java.util.Random;
/*  8:   */ import net.minecraft.entity.Entity;
/*  9:   */ import net.minecraft.entity.EntityLivingBase;
/* 10:   */ import net.minecraft.util.AxisAlignedBB;
/* 11:   */ import net.minecraft.util.DamageSource;
/* 12:   */ import net.minecraft.world.World;
/* 13:   */ 
/* 14:   */ public class ProjectileMagicStormProjectile
/* 15:   */   extends ProjectileMagic
/* 16:   */ {
/* 17:   */   public double x;
/* 18:   */   public double y;
/* 19:18 */   public double z = 0.0D;
/* 20:19 */   boolean isFirstTick = true;
/* 21:   */   
/* 22:   */   public ProjectileMagicStormProjectile(EntityBaseBall entity)
/* 23:   */   {
/* 24:22 */     super(entity);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public int getTextureIndex()
/* 28:   */   {
/* 29:28 */     if (this.type == 2) {
/* 30:29 */       return -2;
/* 31:   */     }
/* 32:30 */     return super.getTextureIndex();
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void onUpdateInAir()
/* 36:   */   {
/* 37:35 */     if (this.isFirstTick)
/* 38:   */     {
/* 39:36 */       this.isFirstTick = false;
/* 40:37 */       this.x = this.entity.posX;
/* 41:38 */       this.y = this.entity.posY;
/* 42:39 */       this.z = this.entity.posZ;
/* 43:   */     }
/* 44:41 */     super.onUpdateInAir();
/* 45:   */     
/* 46:43 */     this.entity.motionY -= 0.01D;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public void onDead()
/* 50:   */   {
/* 51:48 */     if (this.entity.worldObj.isRemote)
/* 52:   */     {
/* 53:49 */       Random rand = this.entity.getRNG();
/* 54:50 */       float desp = 0.3F;
/* 55:51 */       for (int i = 0; i < 5; i++) {
/* 56:52 */         EffectManager.spawnElementParticle(0, this.entity.worldObj, this.entity.posX, this.entity.posY + rand.nextFloat() - 0.5D, this.entity.posZ, (rand.nextFloat() - 0.5F) * desp, 0.1000000014901161D, (rand.nextFloat() - 0.5F) * desp, this.entity.getElement());
/* 57:   */       }
/* 58:   */     }
/* 59:   */     else
/* 60:   */     {
/* 61:58 */       double dist = 2.0D;
/* 62:59 */       AxisAlignedBB var3 = this.entity.boundingBox.expand(dist, 0.0D, dist);
/* 63:60 */       List<Entity> list = this.entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, var3);
/* 64:62 */       for (Entity e : list) {
/* 65:64 */         if (((e instanceof EntityLivingBase)) && (e != this.entity.getThrower()))
/* 66:   */         {
/* 67:66 */           Elements element = this.entity.getElement();
/* 68:67 */           DamageSource ds = getDamageSource().setProjectile();
/* 69:68 */           float damage = 4.0F * this.entity.getDamageMultiplier();
/* 70:69 */           damage = element.onHitEntity(this.entity.getThrower(), e, damage);
/* 71:70 */           HelperDamageSource.attackEntityWithoutKnockBack(e, ds, damage);
/* 72:   */         }
/* 73:   */       }
/* 74:   */     }
/* 75:   */   }
/* 76:   */   
/* 77:   */   public float getSize()
/* 78:   */   {
/* 79:79 */     return 0.8F;
/* 80:   */   }
/* 81:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileMagicStormProjectile
 * JD-Core Version:    0.7.1
 */