/*   1:    */ package com.chocolate.chocolateQuest.entity.projectile;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.utils.HelperDamageSource;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.block.Block;
/*   6:    */ import net.minecraft.block.material.Material;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.entity.EntityLivingBase;
/*   9:    */ import net.minecraft.util.DamageSource;
/*  10:    */ import net.minecraft.util.MathHelper;
/*  11:    */ import net.minecraft.util.MovingObjectPosition;
/*  12:    */ import net.minecraft.world.World;
/*  13:    */ 
/*  14:    */ public class ProjectileBubble
/*  15:    */   extends ProjectileBase
/*  16:    */ {
/*  17:    */   Random rand;
/*  18: 16 */   int mountTime = 0;
/*  19:    */   
/*  20:    */   public ProjectileBubble(EntityBaseBall entity)
/*  21:    */   {
/*  22: 19 */     super(entity);
/*  23: 20 */     this.rand = new Random();
/*  24:    */   }
/*  25:    */   
/*  26:    */   public int getTextureIndex()
/*  27:    */   {
/*  28: 25 */     return 240;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void onImpact(MovingObjectPosition par1MovingObjectPosition)
/*  32:    */   {
/*  33: 31 */     this.entity.worldObj.playSoundEffect((int)this.entity.posX, (int)this.entity.posY, (int)this.entity.posZ, "sounds.bubble", 4.0F, (1.0F + (this.entity.worldObj.rand.nextFloat() - this.entity.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
/*  34: 33 */     if (!this.entity.worldObj.isRemote) {
/*  35: 35 */       if ((par1MovingObjectPosition.entityHit instanceof EntityLivingBase))
/*  36:    */       {
/*  37: 37 */         if (par1MovingObjectPosition.entityHit.ridingEntity == null)
/*  38:    */         {
/*  39: 39 */           par1MovingObjectPosition.entityHit.attackEntityFrom(HelperDamageSource.getBulletDamage(this.entity, this.entity.getThrower()), 4.0F);
/*  40: 40 */           par1MovingObjectPosition.entityHit.mountEntity(this.entity);
/*  41:    */         }
/*  42:    */       }
/*  43:    */       else {
/*  44: 45 */         this.entity.setDead();
/*  45:    */       }
/*  46:    */     }
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void onUpdateInAir()
/*  50:    */   {
/*  51: 53 */     if (this.entity.riddenByEntity != null)
/*  52:    */     {
/*  53: 55 */       this.entity.motionX = 0.0D;
/*  54: 56 */       this.entity.motionZ = 0.0D;
/*  55: 57 */       this.entity.motionY = 0.1D;
/*  56: 58 */       this.mountTime += 1;
/*  57: 60 */       if ((this.mountTime >= 70) || ((this.entity.riddenByEntity.isCollidedVertically) && (!this.entity.riddenByEntity.onGround))) {
/*  58: 62 */         this.entity.setDead();
/*  59:    */       }
/*  60:    */     }
/*  61:    */     else
/*  62:    */     {
/*  63: 67 */       Material mat = this.entity.worldObj.getBlock(MathHelper.floor_double(this.entity.posX), MathHelper.floor_double(this.entity.posY), MathHelper.floor_double(this.entity.posZ)).getMaterial();
/*  64: 68 */       if (mat == Material.fire)
/*  65:    */       {
/*  66: 70 */         this.entity.worldObj.setBlockToAir(MathHelper.floor_double(this.entity.posX), MathHelper.floor_double(this.entity.posY), MathHelper.floor_double(this.entity.posZ));
/*  67: 71 */         this.entity.setDead();
/*  68:    */       }
/*  69:    */     }
/*  70: 75 */     for (int i = 0; i < 1 + this.entity.getlvl(); i++) {
/*  71: 77 */       this.entity.worldObj.spawnParticle("splash", this.entity.posX + this.rand.nextFloat() - 0.5D, this.entity.posY + this.rand.nextFloat() - 0.5D, this.entity.posZ + this.rand.nextFloat() - 0.5D, 0.0D, -1.0D, 0.0D);
/*  72:    */     }
/*  73:    */   }
/*  74:    */   
/*  75:    */   public float getSize()
/*  76:    */   {
/*  77: 84 */     if (this.entity.riddenByEntity != null) {
/*  78: 85 */       return this.entity.riddenByEntity.width + this.entity.riddenByEntity.height;
/*  79:    */     }
/*  80: 86 */     return 1.5F;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public boolean canBounce()
/*  84:    */   {
/*  85: 92 */     return false;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public void onSpawn()
/*  89:    */   {
/*  90: 98 */     this.entity.worldObj.playSoundEffect((int)this.entity.posX, (int)this.entity.posY, (int)this.entity.posZ, "liquid.swim", 4.0F, (1.0F + (this.entity.worldObj.rand.nextFloat() - this.entity.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
/*  91:    */   }
/*  92:    */   
/*  93:    */   public void attackFrom(DamageSource d, float damage)
/*  94:    */   {
/*  95:105 */     this.mountTime += 10;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public boolean longRange()
/*  99:    */   {
/* 100:109 */     return false;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public int getMaxLifeTime()
/* 104:    */   {
/* 105:113 */     return 150;
/* 106:    */   }
/* 107:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileBubble
 * JD-Core Version:    0.7.1
 */