/*   1:    */ package com.chocolate.chocolateQuest.entity;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.ai.AIFollowOwner;
/*   4:    */ import com.chocolate.chocolateQuest.entity.ai.AITargetOwner;
/*   5:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*   6:    */ import java.util.Random;
/*   7:    */ import net.minecraft.entity.DataWatcher;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.entity.EntityCreature;
/*  10:    */ import net.minecraft.entity.EntityLivingBase;
/*  11:    */ import net.minecraft.entity.EnumCreatureAttribute;
/*  12:    */ import net.minecraft.entity.IEntityOwnable;
/*  13:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  14:    */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*  15:    */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*  16:    */ import net.minecraft.entity.ai.EntityAISwimming;
/*  17:    */ import net.minecraft.entity.ai.EntityAITasks;
/*  18:    */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*  19:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  20:    */ import net.minecraft.entity.passive.IAnimals;
/*  21:    */ import net.minecraft.entity.player.EntityPlayer;
/*  22:    */ import net.minecraft.util.DamageSource;
/*  23:    */ import net.minecraft.world.World;
/*  24:    */ 
/*  25:    */ public class EntitySummonedUndead
/*  26:    */   extends EntityCreature
/*  27:    */   implements IEntityOwnable, IAnimals
/*  28:    */ {
/*  29:    */   EntityLivingBase summoner;
/*  30: 29 */   int lifeTime = 0;
/*  31: 30 */   protected boolean isReanimatedCreature = true;
/*  32:    */   
/*  33:    */   public EntitySummonedUndead(World par1World)
/*  34:    */   {
/*  35: 33 */     super(par1World);
/*  36: 34 */     this.experienceValue = 0;
/*  37: 35 */     initTasks();
/*  38:    */   }
/*  39:    */   
/*  40:    */   protected void applyEntityAttributes()
/*  41:    */   {
/*  42: 41 */     super.applyEntityAttributes();
/*  43: 42 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
/*  44:    */     
/*  45: 44 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
/*  46: 45 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
/*  47: 46 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23D);
/*  48:    */   }
/*  49:    */   
/*  50:    */   public EntitySummonedUndead(World world, EntityLivingBase summoner, int lvl)
/*  51:    */   {
/*  52: 51 */     this(world);
/*  53: 52 */     this.summoner = summoner;
/*  54: 53 */     setlvl((byte)lvl);
/*  55: 55 */     if (!(summoner instanceof EntityPlayer)) {
/*  56: 57 */       this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  57:    */     }
/*  58:    */   }
/*  59:    */   
/*  60:    */   protected void initTasks()
/*  61:    */   {
/*  62: 63 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  63: 64 */     this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
/*  64: 65 */     this.tasks.addTask(2, new AIFollowOwner(this, 1.0F, 5.0F, 50.0F, true));
/*  65:    */     
/*  66: 67 */     this.targetTasks.addTask(1, new AITargetOwner(this));
/*  67:    */   }
/*  68:    */   
/*  69:    */   protected void entityInit()
/*  70:    */   {
/*  71: 73 */     super.entityInit();
/*  72: 74 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/*  73:    */   }
/*  74:    */   
/*  75:    */   public int getlvl()
/*  76:    */   {
/*  77: 80 */     return this.dataWatcher.getWatchableObjectByte(16);
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void setlvl(byte lvl)
/*  81:    */   {
/*  82: 85 */     this.dataWatcher.updateObject(16, Byte.valueOf(lvl));
/*  83:    */   }
/*  84:    */   
/*  85:    */   public boolean isAIEnabled()
/*  86:    */   {
/*  87: 91 */     return true;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void onLivingUpdate()
/*  91:    */   {
/*  92: 97 */     if (this.isReanimatedCreature)
/*  93:    */     {
/*  94: 99 */       if (this.summoner != null) {
/*  95:102 */         if (this.summoner.isDead) {
/*  96:104 */           setDead();
/*  97:    */         }
/*  98:    */       }
/*  99:108 */       if (this.lifeTime > 1200) {
/* 100:110 */         setDead();
/* 101:    */       }
/* 102:    */     }
/* 103:114 */     this.lifeTime += 1;
/* 104:115 */     super.onLivingUpdate();
/* 105:    */   }
/* 106:    */   
/* 107:    */   protected String getLivingSound()
/* 108:    */   {
/* 109:120 */     return "mob.skeleton";
/* 110:    */   }
/* 111:    */   
/* 112:    */   protected String getHurtSound()
/* 113:    */   {
/* 114:126 */     return "mob.skeleton.hurt";
/* 115:    */   }
/* 116:    */   
/* 117:    */   protected String getDeathSound()
/* 118:    */   {
/* 119:131 */     return "mob.skeleton.death";
/* 120:    */   }
/* 121:    */   
/* 122:    */   public boolean attackEntityAsMob(Entity entity)
/* 123:    */   {
/* 124:137 */     if ((entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue()) == true) && (!this.worldObj.isRemote))
/* 125:    */     {
/* 126:139 */       swingItem();
/* 127:140 */       if ((this.summoner != null) && (this.isReanimatedCreature))
/* 128:    */       {
/* 129:142 */         EntityBaseBall ball = new EntityBaseBall(this.worldObj, this.summoner, 9);
/* 130:143 */         ball.setlvl(getlvl());
/* 131:144 */         ball.setPosition(this.posX, this.posY + this.height, this.posZ);
/* 132:145 */         this.worldObj.spawnEntityInWorld(ball);
/* 133:    */       }
/* 134:147 */       return true;
/* 135:    */     }
/* 136:149 */     return false;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public boolean attackEntityFrom(DamageSource damagesource, int i)
/* 140:    */   {
/* 141:154 */     if (damagesource.getEntity() != this.summoner) {
/* 142:156 */       if (super.attackEntityFrom(damagesource, i))
/* 143:    */       {
/* 144:158 */         Entity entity = damagesource.getEntity();
/* 145:160 */         if ((this.riddenByEntity == entity) || (this.ridingEntity == entity)) {
/* 146:162 */           return true;
/* 147:    */         }
/* 148:165 */         if ((entity instanceof EntityPlayer)) {
/* 149:167 */           this.entityToAttack = entity;
/* 150:    */         }
/* 151:170 */         if (getHealth() <= 0.0F) {
/* 152:172 */           setDead();
/* 153:    */         }
/* 154:175 */         return true;
/* 155:    */       }
/* 156:    */     }
/* 157:179 */     return false;
/* 158:    */   }
/* 159:    */   
/* 160:    */   public void setDead()
/* 161:    */   {
/* 162:185 */     if ((this.worldObj.isRemote) && (this.isReanimatedCreature)) {
/* 163:187 */       for (int r = 0; r < 30; r++) {
/* 164:189 */         this.worldObj.spawnParticle("smoke", this.posX + this.rand.nextFloat() - 0.5D, this.posY + this.rand.nextFloat() * 2.0F, this.posZ + this.rand.nextFloat() - 0.5D, 0.0D, 0.0D, 0.0D);
/* 165:    */       }
/* 166:    */     }
/* 167:193 */     super.setDead();
/* 168:    */   }
/* 169:    */   
/* 170:    */   public EnumCreatureAttribute getCreatureAttribute()
/* 171:    */   {
/* 172:198 */     return EnumCreatureAttribute.UNDEAD;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public EntityLivingBase getOwner()
/* 176:    */   {
/* 177:204 */     return this.summoner;
/* 178:    */   }
/* 179:    */   
/* 180:    */   public boolean isEntityEqual(Entity par1Entity)
/* 181:    */   {
/* 182:209 */     return (getOwner() == par1Entity) || (super.isEntityEqual(par1Entity));
/* 183:    */   }
/* 184:    */   
/* 185:    */   public String func_152113_b()
/* 186:    */   {
/* 187:214 */     return null;
/* 188:    */   }
/* 189:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.EntitySummonedUndead
 * JD-Core Version:    0.7.1
 */