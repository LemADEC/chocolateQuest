/*   1:    */ package com.chocolate.chocolateQuest.entity.projectile;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*   4:    */ import com.chocolate.chocolateQuest.utils.HelperDamageSource;
/*   5:    */ import java.util.List;
/*   6:    */ import java.util.Random;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.entity.EntityLivingBase;
/*   9:    */ import net.minecraft.init.Blocks;
/*  10:    */ import net.minecraft.util.AxisAlignedBB;
/*  11:    */ import net.minecraft.util.MovingObjectPosition;
/*  12:    */ import net.minecraft.world.GameRules;
/*  13:    */ import net.minecraft.world.World;
/*  14:    */ 
/*  15:    */ public class ProjectileFireFalling
/*  16:    */   extends ProjectileBase
/*  17:    */ {
/*  18:    */   Random rand;
/*  19:    */   
/*  20:    */   public ProjectileFireFalling(EntityBaseBall entity)
/*  21:    */   {
/*  22: 19 */     super(entity);
/*  23: 20 */     this.rand = new Random();
/*  24:    */   }
/*  25:    */   
/*  26:    */   public int getTextureIndex()
/*  27:    */   {
/*  28: 25 */     int i = (int)(this.entity.ticksExisted * 0.2D % 8.0D);
/*  29: 26 */     if (i >= 4) {
/*  30: 27 */       i = 7 - i;
/*  31:    */     }
/*  32: 28 */     return this.entity.ticksExisted * 0.2D % 2.0D == 0.0D ? 242 : 243;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void onImpact(MovingObjectPosition mop)
/*  36:    */   {
/*  37: 34 */     if (!this.entity.worldObj.isRemote)
/*  38:    */     {
/*  39: 36 */       Entity e = mop.entityHit;
/*  40: 37 */       if (e != null)
/*  41:    */       {
/*  42: 39 */         if (!(e instanceof EntityLivingBase)) {}
/*  43:    */       }
/*  44:    */       else
/*  45:    */       {
/*  46: 46 */         if ((this.entity.getlvl() >= 1) && (this.entity.worldObj.isAirBlock(mop.blockX, mop.blockY + 1, mop.blockZ)) && (this.entity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))) {
/*  47: 50 */           this.entity.worldObj.setBlock(mop.blockX, mop.blockY + 1, mop.blockZ, Blocks.fire);
/*  48:    */         }
/*  49: 52 */         this.entity.setDead();
/*  50:    */       }
/*  51:    */     }
/*  52:    */   }
/*  53:    */   
/*  54:    */   public float getGravityVelocity()
/*  55:    */   {
/*  56: 59 */     return 0.03F;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void onUpdateInAir()
/*  60:    */   {
/*  61: 64 */     this.entity.motionX *= 0.8D;
/*  62: 65 */     this.entity.motionZ *= 0.8D;
/*  63: 66 */     float x = (float)Math.sin(Math.toRadians(this.entity.rotationYaw));
/*  64: 67 */     float z = (float)Math.cos(Math.toRadians(this.entity.rotationYaw));
/*  65: 68 */     double y = -Math.sin(Math.toRadians(this.entity.rotationPitch));
/*  66: 70 */     if (this.entity.shootingEntity != null)
/*  67:    */     {
/*  68: 71 */       int dist = 2;
/*  69: 72 */       List<Entity> list = this.entity.worldObj.getEntitiesWithinAABBExcludingEntity(this.entity.shootingEntity, this.entity.boundingBox.expand(dist, dist, dist));
/*  70: 73 */       for (Entity e : list) {
/*  71: 75 */         if ((e instanceof EntityLivingBase))
/*  72:    */         {
/*  73: 77 */           e.setFire(4);
/*  74: 78 */           e.attackEntityFrom(HelperDamageSource.causeFireProjectileDamage(this.entity, this.entity.shootingEntity), 1.0F);
/*  75:    */         }
/*  76:    */       }
/*  77:    */     }
/*  78: 82 */     if (this.entity.worldObj.isRemote) {
/*  79: 83 */       EffectManager.spawnParticle(3, this.entity.worldObj, this.entity.posX, this.entity.posY + 1.0D, this.entity.posZ, this.entity.motionX + (this.rand.nextFloat() - 0.5D) / 8.0D, this.entity.motionY + (this.rand.nextFloat() - 0.5D) / 8.0D + 0.4D, this.entity.motionZ + (this.rand.nextFloat() - 0.5D) / 8.0D);
/*  80:    */     }
/*  81:    */   }
/*  82:    */   
/*  83:    */   public float getSize()
/*  84:    */   {
/*  85: 94 */     return 1.2F;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public boolean canBounce()
/*  89:    */   {
/*  90:100 */     return false;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public boolean longRange()
/*  94:    */   {
/*  95:105 */     return false;
/*  96:    */   }
/*  97:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileFireFalling
 * JD-Core Version:    0.7.1
 */