/*  1:   */ package com.chocolate.chocolateQuest.entity.projectile;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.magic.Elements;
/*  4:   */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ import net.minecraft.util.AxisAlignedBB;
/*  8:   */ import net.minecraft.util.MovingObjectPosition;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class ProjectileMagicStorm
/* 12:   */   extends ProjectileBase
/* 13:   */ {
/* 14:13 */   int deathTime = 300;
/* 15:   */   Random rand;
/* 16:   */   
/* 17:   */   public ProjectileMagicStorm(EntityBaseBall entity)
/* 18:   */   {
/* 19:18 */     super(entity);
/* 20:19 */     this.rand = new Random();
/* 21:   */   }
/* 22:   */   
/* 23:   */   public int getTextureIndex()
/* 24:   */   {
/* 25:24 */     return -3;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void onImpact(MovingObjectPosition par1MovingObjectPosition) {}
/* 29:   */   
/* 30:   */   public void onUpdateInAir()
/* 31:   */   {
/* 32:35 */     this.entity.motionX = (this.entity.motionZ = this.entity.motionY = 0.0D);
/* 33:36 */     int level = this.entity.getlvl() + 1;
/* 34:37 */     int dist = 10 + 5 * level;
/* 35:38 */     int height = 5 + dist / 2;
/* 36:39 */     double posX = this.entity.posX;
/* 37:40 */     double posY = this.entity.posY + height;
/* 38:41 */     double posZ = this.entity.posZ;
/* 39:42 */     if (this.entity.worldObj.isRemote)
/* 40:   */     {
/* 41:44 */       Elements element = this.entity.getElement();
/* 42:45 */       for (int i = 0; i < level; i++) {
/* 43:46 */         EffectManager.spawnParticle(5, this.entity.worldObj, posX + (this.rand.nextFloat() - 0.5F) * dist, posY + (this.rand.nextFloat() - 0.5F) * 2.0F, posZ + (this.rand.nextFloat() - 0.5F) * dist, element.getColorX(), element.getColorY(), element.getColorZ());
/* 44:   */       }
/* 45:   */     }
/* 46:48 */     else if (this.entity.ticksExisted >= this.deathTime)
/* 47:   */     {
/* 48:49 */       this.entity.setDead();
/* 49:   */     }
/* 50:51 */     for (int i = 0; i < level; i++) {
/* 51:52 */       if ((!this.entity.worldObj.isRemote) && (this.entity.ticksExisted % 3 == 0))
/* 52:   */       {
/* 53:53 */         EntityBaseBall ball = new EntityBaseBall(this.entity.worldObj, this.entity.getThrower(), 105, 0, this.entity.getElement());
/* 54:54 */         ball.setDamageMultiplier(this.entity.getDamageMultiplier());
/* 55:55 */         ball.setPosition(posX + (this.rand.nextFloat() - 0.5F) * dist, posY, posZ + (this.rand.nextFloat() - 0.5F) * dist);
/* 56:   */         
/* 57:57 */         ball.setThrowableHeading(this.rand.nextFloat() / 10.0F, -1.0D, this.rand.nextFloat() / 10.0F, 1.0F, 1.0F);
/* 58:58 */         this.entity.worldObj.spawnEntityInWorld(ball);
/* 59:   */       }
/* 60:   */     }
/* 61:   */   }
/* 62:   */   
/* 63:   */   public float getSize()
/* 64:   */   {
/* 65:65 */     return 0.1F;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public void onSpawn()
/* 69:   */   {
/* 70:71 */     this.entity.motionX = (this.entity.motionZ = this.entity.motionY = 0.0D);
/* 71:72 */     this.deathTime = (300 + this.entity.getlvl() * 100);
/* 72:73 */     this.entity.posY = (this.entity.shootingEntity.boundingBox.minY + 0.5D);
/* 73:74 */     this.entity.setInmuneToFire(true);
/* 74:   */   }
/* 75:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileMagicStorm
 * JD-Core Version:    0.7.1
 */