/*  1:   */ package com.chocolate.chocolateQuest.entity.projectile;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.util.MovingObjectPosition;
/*  7:   */ import net.minecraft.util.Vec3;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class ProjectileHealBall
/* 11:   */   extends ProjectileBase
/* 12:   */ {
/* 13:   */   Random rand;
/* 14:   */   
/* 15:   */   public ProjectileHealBall(EntityBaseBall entity)
/* 16:   */   {
/* 17:14 */     super(entity);
/* 18:15 */     this.rand = new Random();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public int getTextureIndex()
/* 22:   */   {
/* 23:20 */     return 229;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void onImpact(MovingObjectPosition par1MovingObjectPosition)
/* 27:   */   {
/* 28:26 */     if (!this.entity.worldObj.isRemote) {
/* 29:28 */       if (par1MovingObjectPosition.entityHit != null) {
/* 30:30 */         if (par1MovingObjectPosition.entityHit == this.entity.getThrower())
/* 31:   */         {
/* 32:32 */           this.entity.setDead();
/* 33:33 */           this.entity.getThrower().heal(this.entity.getlvl());
/* 34:   */         }
/* 35:   */       }
/* 36:   */     }
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void onUpdateInAir()
/* 40:   */   {
/* 41:42 */     Entity shooting = this.entity.getThrower();
/* 42:44 */     if (shooting != null)
/* 43:   */     {
/* 44:46 */       Vec3 fc = Vec3.createVectorHelper(shooting.posX - this.entity.posX, shooting.posY + 0.4D - this.entity.posY, shooting.posZ - this.entity.posZ);
/* 45:48 */       if (fc.lengthVector() < 1.0D)
/* 46:   */       {
/* 47:50 */         this.entity.setDead();
/* 48:51 */         this.entity.getThrower().heal(this.entity.getlvl());
/* 49:   */       }
/* 50:54 */       fc = fc.normalize();
/* 51:55 */       double s = 0.2D;
/* 52:56 */       this.entity.motionX = (fc.xCoord * s);
/* 53:57 */       this.entity.motionY = (fc.yCoord * s);
/* 54:58 */       this.entity.motionZ = (fc.zCoord * s);
/* 55:60 */       if (shooting.isDead) {
/* 56:62 */         this.entity.setDead();
/* 57:   */       }
/* 58:   */     }
/* 59:   */     else
/* 60:   */     {
/* 61:67 */       this.entity.setDead();
/* 62:   */     }
/* 63:   */   }
/* 64:   */   
/* 65:   */   public float getSize()
/* 66:   */   {
/* 67:74 */     return 0.4F;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public boolean canBounce()
/* 71:   */   {
/* 72:80 */     return false;
/* 73:   */   }
/* 74:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileHealBall
 * JD-Core Version:    0.7.1
 */