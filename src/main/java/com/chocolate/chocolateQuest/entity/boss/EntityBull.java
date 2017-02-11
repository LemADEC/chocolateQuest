/*   1:    */ package com.chocolate.chocolateQuest.entity.boss;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.entity.ai.AIBossAttack;
/*   5:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*   6:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   7:    */ import com.chocolate.chocolateQuest.packets.PacketEntityAnimation;
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
/*  24:    */ public class EntityBull
/*  25:    */   extends EntityBaseBoss
/*  26:    */ {
/*  27:    */   public int smashTime;
/*  28: 25 */   public final int smashSpeed = 30;
/*  29: 27 */   final byte CHARGE_ANIM = 5;
/*  30: 27 */   final byte SMASH = 6;
/*  31:    */   public boolean charge;
/*  32: 29 */   public int chargeTime = 0;
/*  33: 30 */   public int chargeTimeMax = 50;
/*  34:    */   private float damageRight;
/*  35:    */   private float damageLeft;
/*  36: 34 */   public AttackKickQuadruped kickHelper = new AttackKickQuadruped(this);
/*  37:    */   
/*  38:    */   public EntityBull(World par1World)
/*  39:    */   {
/*  40: 37 */     super(par1World);
/*  41: 38 */     this.experienceValue = 5;
/*  42: 39 */     this.size = (this.rand.nextFloat() * 4.0F + 1.0F);
/*  43: 40 */     this.stepHeight = 2.0F;
/*  44: 41 */     resize();
/*  45: 42 */     this.kickHelper.setSpeed(16 + (int)(this.size * 5.0F));
/*  46: 43 */     this.limitRotation = true;
/*  47:    */   }
/*  48:    */   
/*  49:    */   protected void scaleAttributes()
/*  50:    */   {
/*  51: 48 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D + 0.01D * this.lvl);
/*  52: 49 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(14.0D + 0.8D * this.lvl);
/*  53: 50 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0D + 200.0D * this.lvl);
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void addAITasks()
/*  57:    */   {
/*  58: 56 */     this.tasks.addTask(1, new AIBossAttack(this, 1.0F, false));
/*  59:    */     
/*  60: 58 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
/*  61: 59 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true));
/*  62: 60 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  63:    */   }
/*  64:    */   
/*  65:    */   public float getMinSize()
/*  66:    */   {
/*  67: 64 */     return 0.6F;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public float getSizeVariation()
/*  71:    */   {
/*  72: 68 */     return 0.4F;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void animationBoss(byte animType)
/*  76:    */   {
/*  77: 73 */     switch (animType)
/*  78:    */     {
/*  79:    */     case 5: 
/*  80: 74 */       startCharging();return;
/*  81:    */     case 6: 
/*  82: 75 */       startSmashing();return;
/*  83:    */     }
/*  84: 76 */     this.kickHelper.kick(animType);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void onLivingUpdate()
/*  88:    */   {
/*  89: 82 */     super.onLivingUpdate();
/*  90: 83 */     if (!this.isDead)
/*  91:    */     {
/*  92: 84 */       updateCharge();
/*  93: 85 */       updateSmash();
/*  94: 86 */       this.kickHelper.onUpdate();
/*  95:    */     }
/*  96:    */   }
/*  97:    */   
/*  98:    */   public boolean attackInProgress()
/*  99:    */   {
/* 100: 91 */     if ((this.kickHelper.isAttackInProgress()) || (this.smashTime > 0)) {
/* 101: 92 */       return true;
/* 102:    */     }
/* 103: 93 */     return false;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public boolean shouldMoveToEntity(double d1, Entity target)
/* 107:    */   {
/* 108: 97 */     if ((attackInProgress()) || (this.charge)) {
/* 109: 98 */       return false;
/* 110:    */     }
/* 111:100 */     return super.shouldMoveToEntity(d1, target);
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void attackEntity(Entity entity, float f)
/* 115:    */   {
/* 116:105 */     float width = this.width + entity.width;
/* 117:106 */     boolean targetUp = (this.posY - entity.posY + this.height <= 0.0D) && (f < this.size * this.size * 2.0F);
/* 118:107 */     if (!isAttacking()) {
/* 119:111 */       if ((f > (this.width + 8.0F) * (this.width + 8.0F)) || (targetUp))
/* 120:    */       {
/* 121:113 */         int angle = (int)MathHelper.wrapAngleTo180_double(BDHelper.getAngleBetweenEntities(this, entity) - this.rotationYaw);
/* 122:114 */         if (((angle < 1) && (angle > -1)) || (targetUp)) {
/* 123:116 */           startCharging();
/* 124:    */         }
/* 125:    */       }
/* 126:    */     }
/* 127:119 */     if ((this.ticksExisted % 30 == 0) && (!this.worldObj.isRemote) && (!this.charge) && (this.smashTime == 0) && 
/* 128:120 */       (f < (width + 2.0F) * (width + 2.0F))) {
/* 129:121 */       if ((this.rand.nextInt(4) == 0) && (this.kickHelper.kickTime == 0)) {
/* 130:122 */         startSmashing();
/* 131:    */       } else {
/* 132:124 */         this.kickHelper.attackTarget(entity);
/* 133:    */       }
/* 134:    */     }
/* 135:127 */     super.attackEntity(entity, f);
/* 136:    */   }
/* 137:    */   
/* 138:    */   public void updateSmash()
/* 139:    */   {
/* 140:132 */     if (this.smashTime > 0)
/* 141:    */     {
/* 142:133 */       this.smashTime -= 1;
/* 143:134 */       if (this.smashTime == 1)
/* 144:    */       {
/* 145:136 */         float rotation = this.rotationYawHead * 3.141592F / 180.0F;
/* 146:137 */         double d = this.size;
/* 147:138 */         List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(d, 0.0D, d).addCoord(0.0D, -2.0D, 0.0D));
/* 148:139 */         int min = -60;int max = 60;
/* 149:140 */         for (int j = 0; j < list.size(); j++)
/* 150:    */         {
/* 151:142 */           Entity entity1 = (Entity)list.get(j);
/* 152:143 */           if (((entity1 instanceof EntityLivingBase)) && 
/* 153:144 */             (entity1.canBeCollidedWith()) && (!isEntityEqual(entity1)) && (entity1 != this.riddenByEntity))
/* 154:    */           {
/* 155:147 */             int angle = (int)MathHelper.wrapAngleTo180_double(BDHelper.getAngleBetweenEntities(this, entity1) - this.rotationYaw);
/* 156:148 */             if ((angle >= min) && (angle <= max) && 
/* 157:149 */               (attackEntityAsMob(entity1)))
/* 158:    */             {
/* 159:150 */               float dist = 1.0F;
/* 160:151 */               double x = -Math.sin(rotation) * dist;
/* 161:152 */               double z = Math.cos(rotation) * dist;
/* 162:153 */               entity1.motionX += x;
/* 163:154 */               entity1.motionZ += z;
/* 164:    */             }
/* 165:    */           }
/* 166:    */         }
/* 167:159 */         if (!this.worldObj.isRemote)
/* 168:    */         {
/* 169:160 */           EntityBaseBall ball = new EntityBaseBall(this.worldObj, this, 4, 2);
/* 170:161 */           float legAngle = 0.5F;
/* 171:162 */           double x = this.posX - MathHelper.sin(rotation + 0.5F) * this.width;
/* 172:163 */           double z = this.posZ + MathHelper.cos(rotation + 0.5F) * this.width;
/* 173:164 */           ball.posX = x;
/* 174:165 */           ball.posZ = z;
/* 175:166 */           this.worldObj.spawnEntityInWorld(ball);
/* 176:    */           
/* 177:168 */           ball = new EntityBaseBall(this.worldObj, this, 4, 2);
/* 178:169 */           x = this.posX - MathHelper.sin(rotation - 0.5F) * this.width;
/* 179:170 */           z = this.posZ + MathHelper.cos(rotation - 0.5F) * this.width;
/* 180:171 */           ball.posX = x;
/* 181:172 */           ball.posZ = z;
/* 182:173 */           this.worldObj.spawnEntityInWorld(ball);
/* 183:    */         }
/* 184:    */       }
/* 185:    */     }
/* 186:    */   }
/* 187:    */   
/* 188:    */   public void startSmashing()
/* 189:    */   {
/* 190:179 */     if (!this.worldObj.isRemote)
/* 191:    */     {
/* 192:181 */       PacketEntityAnimation packet = new PacketEntityAnimation(getEntityId(), (byte)6);
/* 193:182 */       ChocolateQuest.channel.sendToAllAround(this, packet, 64);
/* 194:    */     }
/* 195:184 */     if (this.smashTime == 0) {
/* 196:185 */       this.smashTime = 30;
/* 197:    */     }
/* 198:    */   }
/* 199:    */   
/* 200:    */   public void updateCharge()
/* 201:    */   {
/* 202:190 */     if (this.charge)
/* 203:    */     {
/* 204:191 */       this.chargeTime += 1;
/* 205:192 */       float speed = Math.min(0.5F, 0.1F + this.chargeTime / this.chargeTimeMax);
/* 206:193 */       float rotation = this.rotationYaw * 3.141592F / 180.0F;
/* 207:194 */       double dx = -MathHelper.sin(rotation) * speed;
/* 208:195 */       double dz = MathHelper.cos(rotation) * speed;
/* 209:196 */       this.motionX = dx;
/* 210:197 */       this.motionZ = dz;
/* 211:198 */       if (this.chargeTime >= this.chargeTimeMax)
/* 212:    */       {
/* 213:199 */         this.chargeTime = 0;
/* 214:200 */         this.charge = false;
/* 215:    */       }
/* 216:203 */       List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.2D, 0.2D, 0.2D));
/* 217:204 */       for (int j = 0; j < list.size(); j++)
/* 218:    */       {
/* 219:206 */         Entity entity1 = (Entity)list.get(j);
/* 220:207 */         if ((entity1.canBeCollidedWith()) && (!isEntityEqual(entity1)) && (entity1 != this.riddenByEntity)) {
/* 221:210 */           if (attackEntityAsMob(entity1))
/* 222:    */           {
/* 223:211 */             float dist = 1.0F;
/* 224:212 */             double x = -Math.sin(rotation) * dist;
/* 225:213 */             double z = Math.cos(rotation) * dist;
/* 226:214 */             entity1.motionX += x;
/* 227:215 */             entity1.motionZ += z;
/* 228:    */           }
/* 229:    */         }
/* 230:    */       }
/* 231:    */     }
/* 232:    */   }
/* 233:    */   
/* 234:    */   public void startCharging()
/* 235:    */   {
/* 236:222 */     if (!this.worldObj.isRemote)
/* 237:    */     {
/* 238:224 */       PacketEntityAnimation packet = new PacketEntityAnimation(getEntityId(), (byte)5);
/* 239:225 */       ChocolateQuest.channel.sendToAllAround(this, packet, 64);
/* 240:    */     }
/* 241:227 */     if (this.chargeTime == 0) {
/* 242:228 */       this.charge = true;
/* 243:    */     }
/* 244:    */   }
/* 245:    */   
/* 246:    */   protected boolean isAIEnabled()
/* 247:    */   {
/* 248:237 */     return true;
/* 249:    */   }
/* 250:    */   
/* 251:    */   protected void dropFewItems(boolean par1, int par2) {}
/* 252:    */   
/* 253:    */   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
/* 254:    */   {
/* 255:248 */     boolean flag = super.attackEntityFrom(par1DamageSource, par2);
/* 256:249 */     if ((!par1DamageSource.isProjectile()) && (par1DamageSource.getEntity() != null) && (flag))
/* 257:    */     {
/* 258:250 */       Entity attacker = par1DamageSource.getEntity();
/* 259:251 */       double dist = getDistanceSqToEntity(attacker);
/* 260:252 */       if ((!this.worldObj.isRemote) && (this.smashTime <= 0) && (!this.charge) && (dist < this.width * this.width * 2.0F * 2.0F)) {
/* 261:253 */         this.kickHelper.attackTarget(attacker);
/* 262:    */       }
/* 263:255 */       int angle = (int)MathHelper.wrapAngleTo180_double(BDHelper.getAngleBetweenEntities(this, attacker) - this.rotationYaw);
/* 264:256 */       if ((angle > 45) && (angle < 135))
/* 265:    */       {
/* 266:257 */         this.damageRight += par2;
/* 267:258 */         if (this.damageRight > getMaxHealth() / 4.0F) {
/* 268:259 */           setHurtRight(true);
/* 269:    */         }
/* 270:    */       }
/* 271:262 */       if ((angle < -45) && (angle > -135))
/* 272:    */       {
/* 273:263 */         this.damageLeft += par2;
/* 274:264 */         if (this.damageLeft > getMaxHealth() / 4.0F) {
/* 275:265 */           setHurtLeft(true);
/* 276:    */         }
/* 277:    */       }
/* 278:    */     }
/* 279:269 */     return flag;
/* 280:    */   }
/* 281:    */   
/* 282:    */   protected void resize()
/* 283:    */   {
/* 284:274 */     setSize(this.size, this.size * 1.4F);
/* 285:    */   }
/* 286:    */   
/* 287:    */   public boolean isHurtLeft()
/* 288:    */   {
/* 289:278 */     return getAnimFlag(1);
/* 290:    */   }
/* 291:    */   
/* 292:    */   public void setHurtLeft(boolean attacking)
/* 293:    */   {
/* 294:283 */     setAnimFlag(1, attacking);
/* 295:    */   }
/* 296:    */   
/* 297:    */   public boolean isHurtRight()
/* 298:    */   {
/* 299:287 */     return getAnimFlag(2);
/* 300:    */   }
/* 301:    */   
/* 302:    */   public void setHurtRight(boolean attacking)
/* 303:    */   {
/* 304:292 */     setAnimFlag(2, attacking);
/* 305:    */   }
/* 306:    */   
/* 307:    */   protected String getLivingSound()
/* 308:    */   {
/* 309:296 */     return "chocolatequest:bull_speak";
/* 310:    */   }
/* 311:    */   
/* 312:    */   protected String getHurtSound()
/* 313:    */   {
/* 314:299 */     return "chocolatequest:bull_hurt";
/* 315:    */   }
/* 316:    */   
/* 317:    */   protected String getDeathSound()
/* 318:    */   {
/* 319:302 */     return "chocolatequest:bull_death";
/* 320:    */   }
/* 321:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.EntityBull
 * JD-Core Version:    0.7.1
 */