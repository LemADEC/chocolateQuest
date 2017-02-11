/*   1:    */ package com.chocolate.chocolateQuest.entity.projectile;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.magic.Elements;
/*   4:    */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*   5:    */ import java.util.List;
/*   6:    */ import java.util.Random;
/*   7:    */ import net.minecraft.block.Block;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.entity.EntityLivingBase;
/*  10:    */ import net.minecraft.entity.effect.EntityLightningBolt;
/*  11:    */ import net.minecraft.init.Blocks;
/*  12:    */ import net.minecraft.item.ItemStack;
/*  13:    */ import net.minecraft.util.AxisAlignedBB;
/*  14:    */ import net.minecraft.util.MovingObjectPosition;
/*  15:    */ import net.minecraft.util.Vec3;
/*  16:    */ import net.minecraft.world.World;
/*  17:    */ 
/*  18:    */ public class ProjectileWindBall
/*  19:    */   extends ProjectileBase
/*  20:    */ {
/*  21: 20 */   int maxLifeTime = 80;
/*  22:    */   Random rand;
/*  23:    */   
/*  24:    */   public ProjectileWindBall(EntityBaseBall entity)
/*  25:    */   {
/*  26: 24 */     super(entity);
/*  27: 25 */     this.rand = new Random();
/*  28:    */   }
/*  29:    */   
/*  30:    */   public int getTextureIndex()
/*  31:    */   {
/*  32: 30 */     return this.entity.ticksExisted % 2 == 0 ? 235 : 236;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void onImpact(MovingObjectPosition par1MovingObjectPosition)
/*  36:    */   {
/*  37: 36 */     if (!this.entity.worldObj.isRemote) {
/*  38: 38 */       if (!(par1MovingObjectPosition.entityHit instanceof EntityLivingBase)) {}
/*  39:    */     }
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void onUpdateInAir()
/*  43:    */   {
/*  44: 47 */     if (this.entity.ticksExisted % 30 == 0) {
/*  45: 48 */       this.entity.worldObj.playSoundAtEntity(this.entity, "chocolatequest:wind", 1.0F, 1.0F);
/*  46:    */     }
/*  47: 49 */     if (this.entity.ticksExisted >= this.maxLifeTime) {
/*  48: 51 */       this.entity.setDead();
/*  49:    */     }
/*  50: 53 */     this.entity.motionX *= 0.9D;
/*  51: 54 */     this.entity.motionY *= 0.9D;
/*  52: 55 */     this.entity.motionZ *= 0.9D;
/*  53:    */     
/*  54: 57 */     double dist = 5 + this.entity.getlvl();
/*  55: 58 */     AxisAlignedBB var3 = this.entity.boundingBox.expand(dist - 1.0D, 2.0D + dist / 10.0D, dist - 1.0D);
/*  56: 59 */     List<Entity> list = this.entity.worldObj.getEntitiesWithinAABB(Entity.class, var3);
/*  57: 61 */     for (Entity e : list) {
/*  58: 63 */       if ((e != this.entity) && (e != this.entity.getThrower()))
/*  59:    */       {
/*  60: 65 */         Vec3 d = Vec3.createVectorHelper(this.entity.posX - e.posX, this.entity.posY - e.posY, this.entity.posZ - e.posZ);
/*  61: 66 */         double distToEntity = Math.max(15.0D, (d.xCoord * d.xCoord + d.zCoord * d.zCoord) * 10.0D);
/*  62: 67 */         d.normalize();
/*  63: 68 */         double x = d.xCoord / distToEntity * dist / 5.0D;
/*  64: 69 */         double y = d.yCoord / Math.max(15.0D, d.yCoord * d.yCoord * 10.0D);
/*  65: 70 */         double z = d.zCoord / distToEntity * dist / 5.0D;
/*  66: 71 */         if (this.rand.nextInt(10) == 0) {
/*  67: 72 */           y += (dist - distToEntity) / 40.0D;
/*  68:    */         }
/*  69: 73 */         if ((e instanceof EntityLivingBase))
/*  70:    */         {
/*  71: 75 */           EntityLivingBase eLiving = (EntityLivingBase)e;
/*  72: 76 */           ItemStack boots = eLiving.getEquipmentInSlot(1);
/*  73: 77 */           boolean canBePushed = true;
/*  74: 78 */           if (((boots == null) || (boots.getItem() == null)) || (
/*  75:    */           
/*  76:    */ 
/*  77:    */ 
/*  78: 82 */             (e.canBePushed()) && (canBePushed))) {
/*  79: 83 */             e.addVelocity(x, y, z);
/*  80:    */           }
/*  81: 85 */           if ((this.entity.getDistanceSqToEntity(e) <= 3.0D) && (!this.entity.worldObj.isRemote))
/*  82:    */           {
/*  83: 87 */             float damage = 1.0F * this.entity.getDamageMultiplier();
/*  84: 88 */             damage = this.entity.getElement().onHitEntity(this.entity.getThrower(), e, damage);
/*  85: 89 */             e.attackEntityFrom(this.entity.getElement().getDamageSourceIndirect(this.entity, this.entity.getThrower()), damage);
/*  86:    */           }
/*  87: 92 */           e.fallDistance = 0.0F;
/*  88:    */         }
/*  89: 94 */         else if (((e instanceof EntityBaseBall)) && (e != this.entity) && (((EntityBaseBall)e).getType() == 10))
/*  90:    */         {
/*  91: 96 */           if (((EntityBaseBall)e).getThrower() != this.entity.getThrower())
/*  92:    */           {
/*  93: 98 */             if ((!this.entity.worldObj.isRemote) && (this.rand.nextInt(30) == 0)) {}
/*  94:100 */             EntityLightningBolt lightning = new EntityLightningBolt(this.entity.worldObj, this.entity.posX, this.entity.posY - 1.0D, this.entity.posZ);
/*  95:101 */             this.entity.worldObj.spawnEntityInWorld(lightning);
/*  96:    */             
/*  97:103 */             e.addVelocity(x, y, z);
/*  98:    */           }
/*  99:107 */           else if (this.entity.ticksExisted > e.ticksExisted)
/* 100:    */           {
/* 101:109 */             this.entity.setDead();
/* 102:    */           }
/* 103:    */           else
/* 104:    */           {
/* 105:112 */             this.entity.ticksExisted -= e.ticksExisted / 2;
/* 106:    */           }
/* 107:    */         }
/* 108:    */         else
/* 109:    */         {
/* 110:117 */           e.addVelocity(x, y, z);
/* 111:    */         }
/* 112:    */       }
/* 113:    */     }
/* 114:122 */     if (this.entity.worldObj.isRemote)
/* 115:    */     {
/* 116:124 */       Block id = this.entity.worldObj.getBlock((int)this.entity.posX, (int)this.entity.posY - 2, (int)this.entity.posZ);
/* 117:125 */       if (id == Blocks.air) {
/* 118:127 */         id = Blocks.glass;
/* 119:    */       }
/* 120:129 */       for (int i = 0; i < 4; i++)
/* 121:    */       {
/* 122:131 */         EffectManager.spawnElementParticle(1, this.entity.worldObj, this.entity.posX + this.rand.nextFloat() - 0.5D, this.entity.posY + this.rand.nextFloat() - 0.5D, this.entity.posZ + this.rand.nextFloat() - 0.5D, dist, 0.0D, 0.0D, this.entity.getElement());
/* 123:    */         
/* 124:133 */         this.entity.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(id) + "_" + 0, this.entity.posX + this.rand.nextFloat() - 0.5D, this.entity.posY + this.rand.nextFloat() - 0.5D, this.entity.posZ + this.rand.nextFloat() - 0.5D, this.rand.nextFloat() - 0.5F, 2.0F + this.rand.nextFloat() * 2.0F, this.rand.nextFloat() - 0.5F);
/* 125:    */       }
/* 126:    */     }
/* 127:    */   }
/* 128:    */   
/* 129:    */   public float getSize()
/* 130:    */   {
/* 131:141 */     return 0.6F;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public float getSizeBB()
/* 135:    */   {
/* 136:146 */     return 0.01F;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public boolean canBounce()
/* 140:    */   {
/* 141:152 */     return false;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public void onSpawn()
/* 145:    */   {
/* 146:157 */     this.entity.setInmuneToFire(false);
/* 147:    */   }
/* 148:    */   
/* 149:    */   public boolean longRange()
/* 150:    */   {
/* 151:161 */     return false;
/* 152:    */   }
/* 153:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileWindBall
 * JD-Core Version:    0.7.1
 */