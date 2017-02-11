/*   1:    */ package com.chocolate.chocolateQuest.entity.boss;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.entity.ai.AIBossAttack;
/*   5:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   6:    */ import com.chocolate.chocolateQuest.packets.PacketEntityAnimation;
/*   7:    */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*   8:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   9:    */ import java.util.List;
/*  10:    */ import java.util.Random;
/*  11:    */ import net.minecraft.entity.Entity;
/*  12:    */ import net.minecraft.entity.EntityLivingBase;
/*  13:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  14:    */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*  15:    */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*  16:    */ import net.minecraft.entity.ai.EntityAITasks;
/*  17:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  18:    */ import net.minecraft.entity.player.EntityPlayer;
/*  19:    */ import net.minecraft.util.AxisAlignedBB;
/*  20:    */ import net.minecraft.util.DamageSource;
/*  21:    */ import net.minecraft.util.MathHelper;
/*  22:    */ import net.minecraft.world.World;
/*  23:    */ 
/*  24:    */ public class EntitySlimeBoss
/*  25:    */   extends EntityBaseBoss
/*  26:    */ {
/*  27: 23 */   public AttackKick kickHelper = new AttackKick(this);
/*  28:    */   public int slimePoolAttackTime;
/*  29: 25 */   public int slimePoolChargeTime = 30;
/*  30: 25 */   public int slimePoolAttackTimeMax = 100;
/*  31: 29 */   final byte SLIMEPOOL = 5;
/*  32:    */   
/*  33:    */   public EntitySlimeBoss(World par1World)
/*  34:    */   {
/*  35: 31 */     super(par1World);
/*  36: 32 */     this.kickHelper.setSpeed(16 + (int)(this.size * 4.0F));
/*  37:    */     
/*  38: 34 */     this.projectileDefense = 30;
/*  39: 35 */     this.physicDefense = 10;
/*  40: 36 */     this.blastDefense = -20;
/*  41: 37 */     this.limitRotation = true;
/*  42:    */   }
/*  43:    */   
/*  44:    */   protected void applyEntityAttributes()
/*  45:    */   {
/*  46: 43 */     super.applyEntityAttributes();
/*  47: 44 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(25.0D);
/*  48:    */   }
/*  49:    */   
/*  50:    */   protected void scaleAttributes()
/*  51:    */   {
/*  52: 48 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D + this.lvl * 0.01D);
/*  53: 49 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0D + this.lvl * 0.6D);
/*  54: 50 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0D + this.lvl * 150.0D);
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void addAITasks()
/*  58:    */   {
/*  59: 55 */     this.tasks.addTask(1, new AIBossAttack(this, 1.0F, false));
/*  60:    */     
/*  61: 57 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
/*  62: 58 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true));
/*  63: 59 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  64:    */   }
/*  65:    */   
/*  66:    */   public float getMinSize()
/*  67:    */   {
/*  68: 63 */     return 1.4F;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public float getSizeVariation()
/*  72:    */   {
/*  73: 67 */     return 0.5F;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void animationBoss(byte animType)
/*  77:    */   {
/*  78: 72 */     switch (animType)
/*  79:    */     {
/*  80:    */     case 5: 
/*  81: 73 */       startPoolAttack();
/*  82:    */     }
/*  83: 74 */     this.kickHelper.kick(animType);
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void onLivingUpdate()
/*  87:    */   {
/*  88: 81 */     if (this.worldObj.isRemote) {
/*  89: 82 */       if (this.slimePoolAttackTime > 0)
/*  90:    */       {
/*  91: 83 */         double width = this.width / 2.0F;
/*  92: 84 */         if (this.slimePoolAttackTime < this.slimePoolAttackTimeMax - this.slimePoolChargeTime) {
/*  93: 85 */           for (int i = 0; i < 3; i++) {
/*  94: 86 */             EffectManager.spawnParticle(0, this.worldObj, this.posX + this.rand.nextGaussian() * width, this.posY + 0.3D, this.posZ + this.rand.nextGaussian() * width, 0.2D + this.rand.nextFloat() / 8.0F, 0.6D + this.rand.nextFloat() / 4.0F, 0.2D + this.rand.nextFloat() / 8.0F);
/*  95:    */           }
/*  96:    */         }
/*  97: 90 */         float desp = this.size / 2.0F;
/*  98: 91 */         EffectManager.spawnParticle(1, this.worldObj, this.posX + this.rand.nextGaussian() * desp, this.posY + 0.2D, this.posZ + this.rand.nextGaussian() * desp, 0.2D + this.rand.nextFloat() / 8.0F, 0.6D + this.rand.nextFloat() / 4.0F, 0.2D + this.rand.nextFloat() / 8.0F);
/*  99:    */       }
/* 100:    */       else
/* 101:    */       {
/* 102: 95 */         float desp = this.size / 2.0F;
/* 103: 96 */         EffectManager.spawnParticle(2, this.worldObj, this.posX + this.rand.nextGaussian() * desp, this.posY + 0.2D, this.posZ + this.rand.nextGaussian() * desp, 0.2D + this.rand.nextFloat() / 8.0F, 0.6D + this.rand.nextFloat() / 4.0F, 0.2D + this.rand.nextFloat() / 8.0F);
/* 104:    */       }
/* 105:    */     }
/* 106:101 */     if ((getHealth() < getMaxHealth() / 10.0F) && (this.ticksExisted % 20 == 0))
/* 107:    */     {
/* 108:102 */       List list = this.worldObj.getEntitiesWithinAABB(EntitySlimePart.class, this.boundingBox.expand(16.0D, 4.0D, 16.0D));
/* 109:103 */       for (int j = 0; j < list.size(); j++)
/* 110:    */       {
/* 111:105 */         Entity entity1 = (Entity)list.get(j);
/* 112:106 */         setAttackTarget((EntityLivingBase)entity1);
/* 113:    */       }
/* 114:    */     }
/* 115:109 */     if (!this.isDead)
/* 116:    */     {
/* 117:110 */       this.kickHelper.onUpdate();
/* 118:111 */       if (isAttacking()) {
/* 119:112 */         if (this.onGround) {
/* 120:113 */           setAttacking(false);
/* 121:    */         } else {
/* 122:114 */           damageNearby(1.0D);
/* 123:    */         }
/* 124:    */       }
/* 125:117 */       if (this.slimePoolAttackTime > 0)
/* 126:    */       {
/* 127:118 */         if ((this.slimePoolAttackTime > 40) && (this.slimePoolAttackTime % 30 == 0)) {
/* 128:119 */           this.worldObj.playSoundEffect((int)this.posX, (int)this.posY, (int)this.posZ, "chocolatequest:bubble_explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
/* 129:    */         }
/* 130:120 */         this.slimePoolAttackTime -= 1;
/* 131:121 */         if (this.slimePoolAttackTime < this.slimePoolAttackTimeMax - this.slimePoolChargeTime) {
/* 132:122 */           damagePool();
/* 133:    */         }
/* 134:    */       }
/* 135:    */     }
/* 136:126 */     super.onLivingUpdate();
/* 137:    */   }
/* 138:    */   
/* 139:    */   public boolean attackInProgress()
/* 140:    */   {
/* 141:130 */     return (this.kickHelper.isAttackInProgress()) || (this.slimePoolAttackTime > 0);
/* 142:    */   }
/* 143:    */   
/* 144:    */   public void attackEntity(Entity entity, float f)
/* 145:    */   {
/* 146:136 */     if (this.slimePoolAttackTime > 0) {
/* 147:137 */       return;
/* 148:    */     }
/* 149:138 */     if ((f > 64.0F * this.width / 2.0F + this.width * this.width) && (this.onGround))
/* 150:    */     {
/* 151:139 */       int angle = (int)MathHelper.wrapAngleTo180_double(BDHelper.getAngleBetweenEntities(this, entity) - this.rotationYaw);
/* 152:140 */       if (Math.abs(angle) < 2) {
/* 153:141 */         jumpToTarget(entity);
/* 154:    */       }
/* 155:    */     }
/* 156:144 */     float width = this.width + entity.width;
/* 157:145 */     width *= width;
/* 158:147 */     if (entity.getClass() == EntitySlimePart.class)
/* 159:    */     {
/* 160:148 */       if (f < width)
/* 161:    */       {
/* 162:149 */         entity.applyEntityCollision(this);
/* 163:150 */         swingItem();
/* 164:    */       }
/* 165:152 */       return;
/* 166:    */     }
/* 167:155 */     if ((this.ticksExisted % 20 == 0) && (!this.worldObj.isRemote))
/* 168:    */     {
/* 169:157 */       boolean targetUp = (this.posY - entity.posY + this.height <= 0.0D) && (f < this.size * this.size * 2.0F);
/* 170:158 */       if (((this.rand.nextInt(6) == 0) || (targetUp)) && (f < width * 2.0F))
/* 171:    */       {
/* 172:159 */         startPoolAttack();
/* 173:160 */         return;
/* 174:    */       }
/* 175:162 */       if (f < width * 1.5D) {
/* 176:163 */         this.kickHelper.attackTarget(entity);
/* 177:    */       }
/* 178:    */     }
/* 179:166 */     super.attackEntity(entity, f);
/* 180:    */   }
/* 181:    */   
/* 182:    */   public void startPoolAttack()
/* 183:    */   {
/* 184:170 */     if (!this.worldObj.isRemote)
/* 185:    */     {
/* 186:172 */       PacketEntityAnimation packet = new PacketEntityAnimation(getEntityId(), (byte)5);
/* 187:173 */       ChocolateQuest.channel.sendToAllAround(this, packet, 64);
/* 188:    */     }
/* 189:175 */     if (this.slimePoolAttackTime == 0) {
/* 190:176 */       this.slimePoolAttackTime = this.slimePoolAttackTimeMax;
/* 191:    */     }
/* 192:    */   }
/* 193:    */   
/* 194:    */   public boolean jumpToTarget(Entity entity)
/* 195:    */   {
/* 196:182 */     if (!isAttacking())
/* 197:    */     {
/* 198:183 */       float rotation = this.rotationYaw * 3.141592F / 180.0F;
/* 199:184 */       double vx = entity.posX - this.posX;
/* 200:185 */       double vy = entity.posY - this.posY;
/* 201:186 */       double vz = entity.posZ - this.posZ;
/* 202:187 */       this.motionX = (vx / 4.0D);
/* 203:188 */       this.motionY = (vy / 5.0D + entity.height / 6.0F);
/* 204:189 */       this.motionZ = (vz / 4.0D);
/* 205:190 */       setAttacking(true);
/* 206:191 */       return true;
/* 207:    */     }
/* 208:193 */     return false;
/* 209:    */   }
/* 210:    */   
/* 211:    */   public void damageNearby(double expand)
/* 212:    */   {
/* 213:198 */     List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(expand, expand, expand));
/* 214:199 */     for (int j = 0; j < list.size(); j++)
/* 215:    */     {
/* 216:201 */       Entity entity1 = (Entity)list.get(j);
/* 217:202 */       if ((entity1.canBeCollidedWith()) && (!isEntityEqual(entity1)) && (entity1 != this.riddenByEntity))
/* 218:    */       {
/* 219:205 */         attackEntityAsMob(entity1);
/* 220:206 */         setAttacking(false);
/* 221:    */       }
/* 222:    */     }
/* 223:    */   }
/* 224:    */   
/* 225:    */   public void damagePool()
/* 226:    */   {
/* 227:211 */     double expand = this.width * 0.7D;
/* 228:212 */     List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(expand, expand, expand));
/* 229:213 */     for (int j = 0; j < list.size(); j++)
/* 230:    */     {
/* 231:215 */       Entity entity1 = (Entity)list.get(j);
/* 232:216 */       if ((entity1 instanceof EntityLivingBase)) {
/* 233:218 */         if ((entity1.canBeCollidedWith()) && (!isEntityEqual(entity1)) && (entity1 != this.riddenByEntity))
/* 234:    */         {
/* 235:221 */           attackEntityAsMob(entity1, 0.6F);
/* 236:222 */           setAttacking(false);
/* 237:    */         }
/* 238:    */       }
/* 239:    */     }
/* 240:    */   }
/* 241:    */   
/* 242:    */   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
/* 243:    */   {
/* 244:229 */     boolean flag = super.attackEntityFrom(par1DamageSource, par2);
/* 245:230 */     if ((!this.worldObj.isRemote) && (flag) && (getHealth() > 0.0F) && (this.hurtTime != 0) && (
/* 246:231 */       (this.rand.nextInt(4) == 0) || ((par2 >= 4.0F) && (this.rand.nextInt(2) == 0))))
/* 247:    */     {
/* 248:232 */       EntitySlimePart part = new EntitySlimePart(this.worldObj, this, (int)Math.max(1.0F, Math.min(this.size * 2.0F, par2 / 4.0F)));
/* 249:233 */       part.setPosition(this.posX, this.posY + 1.0D, this.posZ);
/* 250:234 */       part.motionX = this.rand.nextGaussian();
/* 251:235 */       part.motionZ = this.rand.nextGaussian();
/* 252:236 */       this.worldObj.spawnEntityInWorld(part);
/* 253:    */     }
/* 254:239 */     return flag;
/* 255:    */   }
/* 256:    */   
/* 257:    */   protected void fall(float par1) {}
/* 258:    */   
/* 259:    */   protected String getHurtSound()
/* 260:    */   {
/* 261:245 */     return "mob.slime.big";
/* 262:    */   }
/* 263:    */   
/* 264:    */   protected String getDeathSound()
/* 265:    */   {
/* 266:248 */     return "mob.slime.big";
/* 267:    */   }
/* 268:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.EntitySlimeBoss
 * JD-Core Version:    0.7.1
 */