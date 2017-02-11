/*  1:   */ package com.chocolate.chocolateQuest.entity.projectile;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*  4:   */ import java.io.PrintStream;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.util.Vec3;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ 
/*  9:   */ public class ProjectileMagicAimed
/* 10:   */   extends ProjectileMagic
/* 11:   */ {
/* 12:   */   protected Entity aimedTo;
/* 13:   */   int ticksToStartAim;
/* 14:   */   
/* 15:   */   public ProjectileMagicAimed(EntityBaseBall entity)
/* 16:   */   {
/* 17:14 */     super(entity);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public ProjectileMagicAimed(EntityBaseBall entity, Entity aimedTo)
/* 21:   */   {
/* 22:19 */     this(entity, aimedTo, 0);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public ProjectileMagicAimed(EntityBaseBall entity, Entity aimedTo, int ticksToStartAim)
/* 26:   */   {
/* 27:23 */     super(entity);
/* 28:24 */     this.aimedTo = aimedTo;
/* 29:25 */     this.ticksToStartAim = ticksToStartAim;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public int getTextureIndex()
/* 33:   */   {
/* 34:32 */     return 247 + this.type - (this.entity.ticksExisted / 4 % 2 == 0 ? 0 : 16);
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void onUpdateInAir()
/* 38:   */   {
/* 39:38 */     super.onUpdateInAir();
/* 40:39 */     if ((this.aimedTo != null) && (this.entity.ticksExisted >= this.ticksToStartAim))
/* 41:   */     {
/* 42:40 */       double x = this.aimedTo.posX - this.entity.posX;
/* 43:41 */       double y = this.aimedTo.posY + this.aimedTo.height - 0.4D - this.entity.posY;
/* 44:42 */       double z = this.aimedTo.posZ - this.entity.posZ;
/* 45:43 */       Vec3 v = Vec3.createVectorHelper(x, y, z);
/* 46:44 */       v = v.normalize();
/* 47:45 */       double xM = v.xCoord;
/* 48:46 */       double yM = v.yCoord;
/* 49:47 */       double zM = v.zCoord;
/* 50:48 */       float desp = 0.1F;
/* 51:49 */       this.entity.motionX += xM * desp;
/* 52:50 */       this.entity.motionY += yM * desp;
/* 53:51 */       this.entity.motionZ += zM * desp;
/* 54:52 */       desp *= 40.0F;
/* 55:53 */       if (yM > 0.0D) {
/* 56:54 */         this.entity.motionY = Math.min(this.entity.motionY, yM * desp);
/* 57:   */       } else {
/* 58:56 */         this.entity.motionY = Math.max(this.entity.motionY, yM * desp);
/* 59:   */       }
/* 60:57 */       if (xM > 0.0D) {
/* 61:58 */         this.entity.motionX = Math.min(this.entity.motionX, xM * desp);
/* 62:   */       } else {
/* 63:60 */         this.entity.motionX = Math.max(this.entity.motionX, xM * desp);
/* 64:   */       }
/* 65:61 */       if (zM > 0.0D) {
/* 66:62 */         this.entity.motionZ = Math.min(this.entity.motionZ, zM * desp);
/* 67:   */       } else {
/* 68:64 */         this.entity.motionZ = Math.max(this.entity.motionZ, zM * desp);
/* 69:   */       }
/* 70:   */     }
/* 71:66 */     if (this.entity.worldObj.isRemote)
/* 72:   */     {
/* 73:67 */       System.out.println("asd");
/* 74:68 */       EffectManager.spawnElementParticle(2, this.entity.worldObj, this.entity.posX, this.entity.posY, this.entity.posZ, 0.0D, 0.4D, 0.0D, this.entity.getElement());
/* 75:   */     }
/* 76:   */   }
/* 77:   */   
/* 78:   */   public float getSize()
/* 79:   */   {
/* 80:77 */     return 0.8F;
/* 81:   */   }
/* 82:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileMagicAimed
 * JD-Core Version:    0.7.1
 */