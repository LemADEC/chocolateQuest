/*   1:    */ package com.chocolate.chocolateQuest.entity.projectile;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.utils.HelperDamageSource;
/*   4:    */ import java.util.List;
/*   5:    */ import java.util.Random;
/*   6:    */ import net.minecraft.block.Block;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.entity.EntityLivingBase;
/*   9:    */ import net.minecraft.init.Blocks;
/*  10:    */ import net.minecraft.util.AxisAlignedBB;
/*  11:    */ import net.minecraft.util.MathHelper;
/*  12:    */ import net.minecraft.util.MovingObjectPosition;
/*  13:    */ import net.minecraft.world.World;
/*  14:    */ 
/*  15:    */ public class ProjectileEarthQuakeArea
/*  16:    */   extends ProjectileBase
/*  17:    */ {
/*  18: 19 */   int lifeTime = 10;
/*  19: 20 */   int deathTime = 100;
/*  20:    */   Random rand;
/*  21:    */   
/*  22:    */   public ProjectileEarthQuakeArea(EntityBaseBall entity)
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
/*  42: 50 */     AxisAlignedBB var3 = this.entity.boundingBox.expand(dist, 0.0D, dist);
/*  43: 51 */     List<Entity> list = this.entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, var3);
/*  44: 53 */     for (Entity e : list) {
/*  45: 55 */       if (((e instanceof EntityLivingBase)) && (e != this.entity.getThrower())) {
/*  46: 57 */         if (((int)e.getDistanceToEntity(this.entity) == this.lifeTime / 10) && (e.onGround)) {
/*  47: 59 */           if (!this.entity.worldObj.isRemote) {
/*  48: 61 */             e.attackEntityFrom(HelperDamageSource.causeIndirectMagicDamage(this.entity, this.entity.getThrower()), 1.0F);
/*  49:    */           }
/*  50:    */         }
/*  51:    */       }
/*  52:    */     }
/*  53: 67 */     if (this.entity.worldObj.isRemote)
/*  54:    */     {
/*  55: 69 */       double x = Math.sin(this.lifeTime / 10.0F);
/*  56: 70 */       double z = Math.cos(this.lifeTime / 10.0F);
/*  57: 71 */       double step = 0.3926990816987241D;
/*  58: 73 */       for (int i = 0; i < 16; i++)
/*  59:    */       {
/*  60: 75 */         x = Math.sin(this.lifeTime / 10.0F + step * i) * (this.lifeTime / 10.0F);
/*  61: 76 */         z = Math.cos(this.lifeTime / 10.0F + step * i) * (this.lifeTime / 10.0F);
/*  62: 77 */         int blockX = MathHelper.floor_double(this.entity.posX + x);
/*  63: 78 */         int blockY = MathHelper.floor_double(this.entity.posY) - 1;
/*  64: 79 */         int blockZ = MathHelper.floor_double(this.entity.posZ + z);
/*  65: 80 */         Block id = this.entity.worldObj.getBlock(blockX, blockY, blockZ);
/*  66: 81 */         int md = this.entity.worldObj.getBlockMetadata(blockX, blockY, blockZ);
/*  67: 83 */         if ((id == null) || (id == Blocks.air)) {
/*  68: 85 */           id = Blocks.glass;
/*  69:    */         }
/*  70: 87 */         for (int a = 0; a < 5; a++) {
/*  71: 89 */           this.entity.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(id) + "_" + md, this.entity.posX + x, this.entity.posY - 0.5D + this.rand.nextFloat() - 0.5D, this.entity.posZ + z, 0.0D, 1.0D, 0.0D);
/*  72:    */         }
/*  73:    */       }
/*  74:    */     }
/*  75: 93 */     this.entity.motionX = (this.entity.motionZ = this.entity.motionY = 0.0D);
/*  76: 94 */     if (!this.entity.onGround)
/*  77:    */     {
/*  78: 95 */       if (this.entity.worldObj.getBlock((int)this.entity.posX, (int)this.entity.posY, (int)this.entity.posZ) != Blocks.air) {
/*  79: 96 */         this.entity.onGround = true;
/*  80:    */       }
/*  81: 97 */       this.entity.motionY -= 0.1D;
/*  82:    */     }
/*  83:    */   }
/*  84:    */   
/*  85:    */   public float getSize()
/*  86:    */   {
/*  87:104 */     return 0.1F;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public boolean canBounce()
/*  91:    */   {
/*  92:110 */     return false;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void onSpawn()
/*  96:    */   {
/*  97:115 */     this.deathTime = (50 + this.entity.getlvl() * 50);
/*  98:116 */     this.entity.posY = (this.entity.shootingEntity.boundingBox.minY + 0.5D);
/*  99:117 */     this.entity.setInmuneToFire(true);
/* 100:    */   }
/* 101:    */   
/* 102:    */   public boolean longRange()
/* 103:    */   {
/* 104:121 */     return false;
/* 105:    */   }
/* 106:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileEarthQuakeArea
 * JD-Core Version:    0.7.1
 */