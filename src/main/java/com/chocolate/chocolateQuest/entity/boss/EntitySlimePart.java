/*   1:    */ package com.chocolate.chocolateQuest.entity.boss;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*   7:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*   8:    */ import net.minecraft.entity.monster.EntitySlime;
/*   9:    */ import net.minecraft.potion.Potion;
/*  10:    */ import net.minecraft.potion.PotionEffect;
/*  11:    */ import net.minecraft.scoreboard.Team;
/*  12:    */ import net.minecraft.util.DamageSource;
/*  13:    */ import net.minecraft.world.World;
/*  14:    */ 
/*  15:    */ public class EntitySlimePart
/*  16:    */   extends EntitySlime
/*  17:    */ {
/*  18:    */   EntityLivingBase owner;
/*  19:    */   
/*  20:    */   public EntitySlimePart(World par1World)
/*  21:    */   {
/*  22: 18 */     super(par1World);
/*  23:    */   }
/*  24:    */   
/*  25:    */   public EntitySlimePart(World par1World, EntityLivingBase entity, int size)
/*  26:    */   {
/*  27: 22 */     super(par1World);
/*  28: 23 */     this.owner = entity;
/*  29: 24 */     setSlimeSize(size);
/*  30: 25 */     float health = 3.0F * size;
/*  31: 26 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(health);
/*  32: 27 */     setHealth(health);
/*  33: 28 */     this.experienceValue = 0;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void onUpdate()
/*  37:    */   {
/*  38: 33 */     super.onUpdate();
/*  39: 34 */     if (!this.worldObj.isRemote) {
/*  40: 35 */       if (this.owner != null)
/*  41:    */       {
/*  42: 36 */         this.rotationYaw = ((float)(-Math.atan2(this.owner.posX - this.posX, this.owner.posZ - this.posZ) * 180.0D / 3.141592653589793D));
/*  43: 37 */         if (!this.owner.isEntityAlive()) {
/*  44: 38 */           setDead();
/*  45:    */         }
/*  46:    */       }
/*  47:    */       else
/*  48:    */       {
/*  49: 40 */         setDead();
/*  50:    */       }
/*  51:    */     }
/*  52:    */   }
/*  53:    */   
/*  54:    */   protected void applyEntityAttributes()
/*  55:    */   {
/*  56: 47 */     super.applyEntityAttributes();
/*  57: 48 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.4D);
/*  58: 49 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
/*  59: 50 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(15.0D);
/*  60:    */   }
/*  61:    */   
/*  62:    */   public boolean attackEntityAsMob(Entity par1Entity)
/*  63:    */   {
/*  64: 55 */     return super.attackEntityAsMob(par1Entity);
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void applyEntityCollision(Entity par1Entity)
/*  68:    */   {
/*  69: 60 */     if (((par1Entity instanceof EntitySlimeBoss)) || (par1Entity == this.owner))
/*  70:    */     {
/*  71: 61 */       if (this.ticksExisted > 10)
/*  72:    */       {
/*  73: 62 */         float healScale = 1.0F;
/*  74: 63 */         if ((this.owner instanceof EntitySlimeBoss)) {
/*  75: 64 */           healScale = 2 + getSlimeSize() / 2;
/*  76:    */         }
/*  77: 65 */         ((EntityLivingBase)par1Entity).heal(getSlimeSize() * healScale);
/*  78: 66 */         setDead();
/*  79:    */       }
/*  80:    */     }
/*  81: 68 */     else if (((par1Entity instanceof EntityLivingBase)) && (par1Entity.getClass() != getClass()))
/*  82:    */     {
/*  83: 69 */       EntityLivingBase el = (EntityLivingBase)par1Entity;
/*  84: 70 */       if (!el.isOnSameTeam(this)) {
/*  85: 71 */         ((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1));
/*  86:    */       }
/*  87:    */     }
/*  88: 73 */     super.applyEntityCollision(par1Entity);
/*  89:    */   }
/*  90:    */   
/*  91:    */   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
/*  92:    */   {
/*  93: 78 */     if ((par1DamageSource.getEntity() instanceof EntitySlimeBoss)) {
/*  94: 79 */       return false;
/*  95:    */     }
/*  96: 80 */     return super.attackEntityFrom(par1DamageSource, par2);
/*  97:    */   }
/*  98:    */   
/*  99:    */   public boolean canAttackClass(Class par1Class)
/* 100:    */   {
/* 101: 85 */     return (super.canAttackClass(par1Class)) && (par1Class != getClass()) && (par1Class != EntitySlimePart.class);
/* 102:    */   }
/* 103:    */   
/* 104:    */   protected void dropFewItems(boolean par1, int par2) {}
/* 105:    */   
/* 106:    */   public void setDead()
/* 107:    */   {
/* 108: 94 */     if ((!this.worldObj.isRemote) && (!this.isDead) && (getHealth() <= 0.0F) && 
/* 109: 95 */       (getSlimeSize() > 1))
/* 110:    */     {
/* 111: 96 */       int size = Math.max(1, getSlimeSize() / 2);
/* 112: 97 */       for (int a = 0; a < 2; a++)
/* 113:    */       {
/* 114: 98 */         EntitySlimePart part = new EntitySlimePart(this.worldObj, this.owner, size);
/* 115: 99 */         part.setPosition(this.posX, this.posY + 1.0D, this.posZ);
/* 116:100 */         part.motionX = this.rand.nextGaussian();
/* 117:101 */         part.motionZ = this.rand.nextGaussian();
/* 118:102 */         this.worldObj.spawnEntityInWorld(part);
/* 119:    */       }
/* 120:    */     }
/* 121:106 */     this.isDead = true;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public Team getTeam()
/* 125:    */   {
/* 126:112 */     if (this.owner != null) {
/* 127:113 */       return this.owner.getTeam();
/* 128:    */     }
/* 129:114 */     return super.getTeam();
/* 130:    */   }
/* 131:    */   
/* 132:    */   protected int getAttackStrength()
/* 133:    */   {
/* 134:119 */     return 0;
/* 135:    */   }
/* 136:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.EntitySlimePart
 * JD-Core Version:    0.7.1
 */