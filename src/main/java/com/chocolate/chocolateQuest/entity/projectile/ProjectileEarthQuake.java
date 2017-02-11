/*   1:    */ package com.chocolate.chocolateQuest.entity.projectile;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.utils.HelperDamageSource;
/*   4:    */ import java.util.List;
/*   5:    */ import java.util.Random;
/*   6:    */ import net.minecraft.block.Block;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.entity.EntityLiving;
/*   9:    */ import net.minecraft.entity.EntityLivingBase;
/*  10:    */ import net.minecraft.init.Blocks;
/*  11:    */ import net.minecraft.util.AxisAlignedBB;
/*  12:    */ import net.minecraft.util.MovingObjectPosition;
/*  13:    */ import net.minecraft.world.World;
/*  14:    */ 
/*  15:    */ public class ProjectileEarthQuake
/*  16:    */   extends ProjectileBase
/*  17:    */ {
/*  18: 19 */   int lifeTime = 60;
/*  19:    */   Random rand;
/*  20:    */   
/*  21:    */   public ProjectileEarthQuake(EntityBaseBall entity)
/*  22:    */   {
/*  23: 23 */     super(entity);
/*  24: 24 */     this.rand = new Random();
/*  25:    */   }
/*  26:    */   
/*  27:    */   public int getTextureIndex()
/*  28:    */   {
/*  29: 29 */     return -3;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void onImpact(MovingObjectPosition par1MovingObjectPosition)
/*  33:    */   {
/*  34: 35 */     if (!this.entity.worldObj.isRemote) {
/*  35: 37 */       if (!(par1MovingObjectPosition.entityHit instanceof EntityLiving)) {
/*  36: 42 */         this.entity.motionY = 0.0D;
/*  37:    */       }
/*  38:    */     }
/*  39:    */   }
/*  40:    */   
/*  41:    */   public void onUpdateInAir()
/*  42:    */   {
/*  43: 50 */     this.lifeTime -= 1;
/*  44: 52 */     if (this.lifeTime <= 0) {
/*  45: 54 */       this.entity.setDead();
/*  46:    */     }
/*  47: 56 */     Block id = this.entity.worldObj.getBlock((int)this.entity.posX, (int)this.entity.posY - 1, (int)this.entity.posZ);
/*  48: 57 */     if ((id == null) || (id == Blocks.air)) {
/*  49: 59 */       id = Blocks.glass;
/*  50:    */     }
/*  51: 61 */     double dist = 1.0D;
/*  52: 62 */     AxisAlignedBB var3 = this.entity.boundingBox.expand(dist, 2.0D, dist);
/*  53: 63 */     List<Entity> list = this.entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, var3);
/*  54: 65 */     for (Entity e : list) {
/*  55: 67 */       if (((e instanceof EntityLivingBase)) && (e != this.entity.getThrower()) && (!this.entity.worldObj.isRemote) && (e.onGround))
/*  56:    */       {
/*  57: 69 */         e.motionY = 0.3D;
/*  58: 70 */         e.attackEntityFrom(HelperDamageSource.causeIndirectMagicDamage(this.entity, this.entity.getThrower()), 1.0F);
/*  59:    */       }
/*  60:    */     }
/*  61: 73 */     if (this.entity.worldObj.isRemote) {
/*  62: 75 */       for (int i = 0; i < 8; i++) {
/*  63: 77 */         this.entity.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(id) + "_" + 0, this.entity.posX + this.rand.nextFloat() - 0.5D, this.entity.posY + this.rand.nextFloat() - 0.5D, this.entity.posZ + this.rand.nextFloat() - 0.5D, this.rand.nextFloat() - 0.5F, this.rand.nextFloat(), this.rand.nextFloat() - 0.5F);
/*  64:    */       }
/*  65:    */     }
/*  66:    */   }
/*  67:    */   
/*  68:    */   public float getSize()
/*  69:    */   {
/*  70: 85 */     return 1.5F;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public boolean canBounce()
/*  74:    */   {
/*  75: 91 */     return false;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void onSpawn()
/*  79:    */   {
/*  80: 96 */     this.entity.posY -= 1.0D;
/*  81: 97 */     this.entity.motionX /= 2.5D;
/*  82: 98 */     this.entity.motionY = -1.0D;
/*  83: 99 */     this.entity.motionZ /= 2.5D;
/*  84:100 */     this.entity.setInmuneToFire(true);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public boolean longRange()
/*  88:    */   {
/*  89:104 */     return false;
/*  90:    */   }
/*  91:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileEarthQuake
 * JD-Core Version:    0.7.1
 */