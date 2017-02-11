/*   1:    */ package com.chocolate.chocolateQuest.entity.projectile;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.magic.Elements;
/*   4:    */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*   5:    */ import java.util.List;
/*   6:    */ import java.util.Random;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.entity.EntityLivingBase;
/*   9:    */ import net.minecraft.init.Blocks;
/*  10:    */ import net.minecraft.util.AxisAlignedBB;
/*  11:    */ import net.minecraft.util.DamageSource;
/*  12:    */ import net.minecraft.util.MovingObjectPosition;
/*  13:    */ import net.minecraft.world.World;
/*  14:    */ 
/*  15:    */ public class ProjectileMagicArea
/*  16:    */   extends ProjectileMagic
/*  17:    */ {
/*  18: 19 */   int lifeTime = 10;
/*  19: 20 */   int deathTime = 100;
/*  20:    */   Random rand;
/*  21:    */   
/*  22:    */   public ProjectileMagicArea(EntityBaseBall entity)
/*  23:    */   {
/*  24: 25 */     super(entity);
/*  25: 26 */     this.rand = new Random();
/*  26:    */   }
/*  27:    */   
/*  28:    */   public int getTextureIndex()
/*  29:    */   {
/*  30: 31 */     return -3;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void onImpact(MovingObjectPosition par1MovingObjectPosition) {}
/*  34:    */   
/*  35:    */   public void onUpdateInAir()
/*  36:    */   {
/*  37: 42 */     this.lifeTime += 1;
/*  38: 44 */     if (this.lifeTime >= this.deathTime) {
/*  39: 46 */       this.entity.setDead();
/*  40:    */     }
/*  41: 49 */     double dist = this.lifeTime / 10.0F;
/*  42: 50 */     AxisAlignedBB var3 = this.entity.boundingBox.expand(dist, 0.1D, dist);
/*  43: 51 */     List<Entity> list = this.entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, var3);
/*  44: 52 */     for (Entity e : list) {
/*  45: 54 */       if (((e instanceof EntityLivingBase)) && (e != this.entity.getThrower())) {
/*  46: 56 */         if (((int)e.getDistanceToEntity(this.entity) == this.lifeTime / 10) && (e.onGround)) {
/*  47: 58 */           if (!this.entity.worldObj.isRemote)
/*  48:    */           {
/*  49: 60 */             Elements element = this.entity.getElement();
/*  50: 61 */             DamageSource ds = getDamageSource();
/*  51: 62 */             float damage = 3.0F * this.entity.getDamageMultiplier();
/*  52: 63 */             damage = element.onHitEntity(this.entity.getThrower(), e, damage);
/*  53: 64 */             e.attackEntityFrom(ds, damage);
/*  54:    */           }
/*  55:    */         }
/*  56:    */       }
/*  57:    */     }
/*  58: 70 */     if (this.entity.worldObj.isRemote)
/*  59:    */     {
/*  60: 73 */       double x = Math.sin(this.lifeTime / 10.0F);
/*  61: 74 */       double z = Math.cos(this.lifeTime / 10.0F);
/*  62: 75 */       double step = 0.3926990816987241D;
/*  63: 77 */       for (int i = 0; i < 16; i++)
/*  64:    */       {
/*  65: 79 */         x = Math.sin(this.lifeTime / 10.0F + step * i) * (this.lifeTime / 10.0F);
/*  66: 80 */         z = Math.cos(this.lifeTime / 10.0F + step * i) * (this.lifeTime / 10.0F);
/*  67: 81 */         EffectManager.spawnElementParticle(0, this.entity.worldObj, this.entity.posX + x, this.entity.posY - 0.5D + this.rand.nextFloat() - 0.5D, this.entity.posZ + z, 0.0D, 0.1D, 0.0D, this.entity.getElement());
/*  68:    */       }
/*  69:    */     }
/*  70: 86 */     this.entity.motionX = (this.entity.motionZ = this.entity.motionY = 0.0D);
/*  71: 87 */     if (!this.entity.onGround)
/*  72:    */     {
/*  73: 88 */       if (this.entity.worldObj.getBlock((int)this.entity.posX, (int)this.entity.posY, (int)this.entity.posZ) != Blocks.air) {
/*  74: 89 */         this.entity.onGround = true;
/*  75:    */       }
/*  76: 90 */       this.entity.motionY -= 0.1D;
/*  77:    */     }
/*  78:    */   }
/*  79:    */   
/*  80:    */   public float getSize()
/*  81:    */   {
/*  82: 97 */     return 0.1F;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public boolean canBounce()
/*  86:    */   {
/*  87:103 */     return false;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void onSpawn()
/*  91:    */   {
/*  92:108 */     this.deathTime = (40 + this.entity.getlvl() * 20);
/*  93:109 */     this.entity.posY = (this.entity.shootingEntity.boundingBox.minY + 0.5D);
/*  94:110 */     this.entity.setInmuneToFire(true);
/*  95:111 */     this.entity.motionX = (this.entity.motionZ = this.entity.motionY = 0.0D);
/*  96:    */   }
/*  97:    */   
/*  98:    */   public boolean longRange()
/*  99:    */   {
/* 100:115 */     return false;
/* 101:    */   }
/* 102:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileMagicArea
 * JD-Core Version:    0.7.1
 */