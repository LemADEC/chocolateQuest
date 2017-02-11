/*   1:    */ package com.chocolate.chocolateQuest.entity;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.ai.AITargetOwner;
/*   4:    */ import cpw.mods.fml.common.registry.IThrowableEntity;
/*   5:    */ import java.io.PrintStream;
/*   6:    */ import java.util.List;
/*   7:    */ import java.util.Random;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.entity.EntityCreature;
/*  10:    */ import net.minecraft.entity.EntityLiving;
/*  11:    */ import net.minecraft.entity.EntityLivingBase;
/*  12:    */ import net.minecraft.entity.IEntityOwnable;
/*  13:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  14:    */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*  15:    */ import net.minecraft.entity.ai.EntityAISwimming;
/*  16:    */ import net.minecraft.entity.ai.EntityAITasks;
/*  17:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  18:    */ import net.minecraft.util.AxisAlignedBB;
/*  19:    */ import net.minecraft.util.DamageSource;
/*  20:    */ import net.minecraft.world.World;
/*  21:    */ 
/*  22:    */ public class EntityBaiter
/*  23:    */   extends EntityCreature
/*  24:    */   implements IThrowableEntity, IEntityOwnable
/*  25:    */ {
/*  26: 24 */   int lifeTime = 0;
/*  27:    */   EntityLivingBase summoner;
/*  28: 27 */   boolean firstTick = true;
/*  29:    */   
/*  30:    */   public EntityBaiter(World par1World)
/*  31:    */   {
/*  32: 30 */     super(par1World);
/*  33: 31 */     this.experienceValue = 0;
/*  34: 32 */     initTasks();
/*  35: 34 */     for (int i = 0; i < this.equipmentDropChances.length; i++) {
/*  36: 36 */       this.equipmentDropChances[i] = 0.0F;
/*  37:    */     }
/*  38:    */   }
/*  39:    */   
/*  40:    */   public EntityBaiter(World world, EntityLivingBase summoner)
/*  41:    */   {
/*  42: 42 */     this(world);
/*  43: 43 */     this.summoner = summoner;
/*  44: 44 */     for (int i = 0; i < 5; i++) {
/*  45: 46 */       setCurrentItemOrArmor(i, summoner.getEquipmentInSlot(i));
/*  46:    */     }
/*  47: 48 */     this.posX = summoner.posX;
/*  48: 49 */     this.posY = summoner.posY;
/*  49: 50 */     this.posZ = summoner.posZ;
/*  50: 51 */     setPosition(this.posX, this.posY, this.posZ);
/*  51: 53 */     if (summoner.getLastAttacker() != null) {
/*  52: 54 */       setAttackTarget(summoner.getLastAttacker());
/*  53:    */     }
/*  54: 55 */     if ((summoner instanceof EntityLiving)) {
/*  55: 56 */       setAttackTarget(((EntityLiving)summoner).getAttackTarget());
/*  56:    */     }
/*  57: 58 */     setHealth(summoner.getHealth());
/*  58:    */   }
/*  59:    */   
/*  60:    */   protected void applyEntityAttributes()
/*  61:    */   {
/*  62: 64 */     super.applyEntityAttributes();
/*  63: 65 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(this.summoner == null ? 20.0D : this.summoner.getMaxHealth());
/*  64: 66 */     getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
/*  65: 67 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23D);
/*  66:    */   }
/*  67:    */   
/*  68:    */   protected void initTasks()
/*  69:    */   {
/*  70: 72 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  71: 73 */     this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLiving.class, 1.0D, true));
/*  72: 74 */     this.targetTasks.addTask(1, new AITargetOwner(this));
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void onLivingUpdate()
/*  76:    */   {
/*  77: 80 */     if (getThrower() != null)
/*  78:    */     {
/*  79: 82 */       if (this.firstTick)
/*  80:    */       {
/*  81: 85 */         double dist = 40.0D;
/*  82: 86 */         AxisAlignedBB var3 = this.boundingBox.expand(dist, 0.0D, dist);
/*  83: 87 */         List<Entity> list = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, var3);
/*  84: 89 */         for (Entity e : list) {
/*  85: 91 */           if ((e instanceof EntityLiving))
/*  86:    */           {
/*  87: 93 */             EntityLiving el = (EntityLiving)e;
/*  88: 94 */             if ((el.getAttackTarget() == getThrower()) || (el.getAITarget() == getThrower()))
/*  89:    */             {
/*  90: 96 */               el.setAttackTarget(this);
/*  91: 97 */               el.setRevengeTarget(this);
/*  92:    */             }
/*  93:    */           }
/*  94:    */         }
/*  95:102 */         this.firstTick = false;
/*  96:    */       }
/*  97:105 */       if (getThrower().isDead) {
/*  98:107 */         setDead();
/*  99:    */       }
/* 100:    */     }
/* 101:    */     else
/* 102:    */     {
/* 103:110 */       setDead();
/* 104:    */     }
/* 105:112 */     if (this.lifeTime > 600) {
/* 106:114 */       setDead();
/* 107:    */     }
/* 108:117 */     super.onLivingUpdate();
/* 109:    */   }
/* 110:    */   
/* 111:    */   public void setDead()
/* 112:    */   {
/* 113:123 */     if (this.worldObj.isRemote) {
/* 114:125 */       for (int r = 0; r < 30; r++) {
/* 115:127 */         this.worldObj.spawnParticle("smoke", this.posX + this.rand.nextFloat() - 0.5D, this.posY + this.rand.nextFloat() * 2.0F, this.posZ + this.rand.nextFloat() - 0.5D, 0.0D, 0.0D, 0.0D);
/* 116:    */       }
/* 117:    */     }
/* 118:130 */     super.setDead();
/* 119:    */   }
/* 120:    */   
/* 121:    */   public boolean attackEntityAsMob(Entity entity)
/* 122:    */   {
/* 123:136 */     this.lifeTime += 10;
/* 124:    */     
/* 125:    */ 
/* 126:    */ 
/* 127:    */ 
/* 128:141 */     return false;
/* 129:    */   }
/* 130:    */   
/* 131:    */   public boolean isAIEnabled()
/* 132:    */   {
/* 133:147 */     return true;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public String getCommandSenderName()
/* 137:    */   {
/* 138:153 */     if (getThrower() != null) {
/* 139:155 */       return getThrower().getCommandSenderName();
/* 140:    */     }
/* 141:157 */     return "Bait";
/* 142:    */   }
/* 143:    */   
/* 144:    */   public Entity getThrower()
/* 145:    */   {
/* 146:162 */     return this.summoner;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public void setThrower(Entity entity)
/* 150:    */   {
/* 151:167 */     this.summoner = ((EntityLivingBase)entity);
/* 152:    */   }
/* 153:    */   
/* 154:    */   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
/* 155:    */   {
/* 156:174 */     System.out.println(par1DamageSource.damageType);
/* 157:175 */     this.lifeTime += 100;
/* 158:176 */     super.attackEntityFrom(par1DamageSource, par2);
/* 159:177 */     return false;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public EntityLivingBase getOwner()
/* 163:    */   {
/* 164:182 */     return this.summoner;
/* 165:    */   }
/* 166:    */   
/* 167:    */   public String func_152113_b()
/* 168:    */   {
/* 169:188 */     return this.summoner.getCommandSenderName();
/* 170:    */   }
/* 171:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.EntityBaiter
 * JD-Core Version:    0.7.1
 */