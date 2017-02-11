/*   1:    */ package com.chocolate.chocolateQuest.entity.boss;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.entity.ai.AIBossAttack;
/*   5:    */ import com.chocolate.chocolateQuest.entity.ai.AITargetHurtBy;
/*   6:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*   7:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityHookShoot;
/*   8:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   9:    */ import com.chocolate.chocolateQuest.packets.PacketAttackToXYZ;
/*  10:    */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*  11:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  12:    */ import java.util.Random;
/*  13:    */ import net.minecraft.entity.Entity;
/*  14:    */ import net.minecraft.entity.EntityLivingBase;
/*  15:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  16:    */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*  17:    */ import net.minecraft.entity.ai.EntityAISwimming;
/*  18:    */ import net.minecraft.entity.ai.EntityAITasks;
/*  19:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  20:    */ import net.minecraft.entity.monster.EntityMob;
/*  21:    */ import net.minecraft.entity.player.EntityPlayer;
/*  22:    */ import net.minecraft.util.DamageSource;
/*  23:    */ import net.minecraft.util.MathHelper;
/*  24:    */ import net.minecraft.world.World;
/*  25:    */ 
/*  26:    */ public class EntitySpiderBoss
/*  27:    */   extends EntityBaseBoss
/*  28:    */ {
/*  29:    */   public AttackPunch rightHand;
/*  30:    */   public AttackPunch leftHand;
/*  31: 29 */   public int attackTimeLeft = 0;
/*  32: 30 */   public int attackTimeRight = 0;
/*  33: 31 */   public int attackSpeed = 10;
/*  34:    */   int projectileCoolDown;
/*  35:    */   EntityHookShoot web;
/*  36:    */   EntityHookShoot escapeWeb;
/*  37:    */   
/*  38:    */   public EntitySpiderBoss(World par1World)
/*  39:    */   {
/*  40: 39 */     super(par1World);
/*  41: 40 */     this.ridableBB = false;
/*  42:    */     
/*  43: 42 */     this.leftHand = new AttackPunch(this, (byte)5, 0.2F, 2.0F);
/*  44: 43 */     this.leftHand.setAngle(-55, 2, 0.4F);
/*  45: 44 */     this.rightHand = new AttackPunch(this, (byte)6, 0.2F, 2.0F);
/*  46: 45 */     this.rightHand.setAngle(55, 2, 0.4F);
/*  47:    */     
/*  48: 47 */     this.projectileDefense = 10;
/*  49: 48 */     this.magicDefense = -20;
/*  50: 49 */     this.limitRotation = true;
/*  51:    */   }
/*  52:    */   
/*  53:    */   protected void applyEntityAttributes()
/*  54:    */   {
/*  55: 55 */     super.applyEntityAttributes();
/*  56: 56 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(46.0D);
/*  57:    */   }
/*  58:    */   
/*  59:    */   protected void scaleAttributes()
/*  60:    */   {
/*  61: 60 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D + this.lvl * 0.02D);
/*  62: 61 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(14.0D + this.lvl * 0.8D);
/*  63: 62 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400.0D + this.lvl * 250.0D);
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void addAITasks()
/*  67:    */   {
/*  68: 68 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  69: 69 */     this.tasks.addTask(2, new AIBossAttack(this, 1.0F, false));
/*  70: 70 */     this.targetTasks.addTask(1, new AITargetHurtBy(this, false));
/*  71: 71 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  72: 72 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true));
/*  73:    */   }
/*  74:    */   
/*  75:    */   public float getMinSize()
/*  76:    */   {
/*  77: 76 */     return 0.6F;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public float getSizeVariation()
/*  81:    */   {
/*  82: 80 */     return 0.4F;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void onUpdate()
/*  86:    */   {
/*  87: 86 */     if (this.worldObj.isRemote)
/*  88:    */     {
/*  89: 87 */       if (getHealth() < getMaxHealth() / 3.0F)
/*  90:    */       {
/*  91: 89 */         float rot = this.rotationYaw * 3.1416F / 180.0F;
/*  92: 90 */         double scale = this.width / 2.0F;
/*  93: 91 */         double posX = this.posX - MathHelper.sin(rot) * scale;
/*  94: 92 */         double posY = this.posY + this.height * 0.3D;
/*  95: 93 */         double posZ = this.posZ + MathHelper.cos(rot) * scale;
/*  96: 94 */         float desp = this.width * 0.05F;
/*  97: 95 */         EffectManager.spawnParticle(2, this.worldObj, posX + this.rand.nextGaussian() * desp, posY + 0.2D, posZ + this.rand.nextGaussian() * desp, 0.0D + this.rand.nextFloat() / 8.0F, 0.3D + this.rand.nextFloat() / 4.0F, 0.0D + this.rand.nextFloat() / 8.0F);
/*  98:    */       }
/*  99:    */     }
/* 100: 99 */     else if ((getAttackTarget() == null) && 
/* 101:100 */       (this.escapeWeb == null) && (!this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))))
/* 102:    */     {
/* 103:102 */       this.escapeWeb = new EntityHookShoot(this.worldObj, this, 3);
/* 104:103 */       this.escapeWeb.motionY = 2.6D;
/* 105:104 */       this.escapeWeb.motionX = 0.0D;
/* 106:105 */       this.escapeWeb.motionZ = 0.0D;
/* 107:106 */       this.worldObj.spawnEntityInWorld(this.escapeWeb);
/* 108:    */     }
/* 109:121 */     if ((this.web != null) && 
/* 110:122 */       (this.web.isReeling()))
/* 111:    */     {
/* 112:123 */       boolean setHookDead = false;
/* 113:124 */       if (this.web.hookedEntity != null)
/* 114:    */       {
/* 115:126 */         if ((getAttackTarget() != this.web.hookedEntity) && ((this.web.hookedEntity instanceof EntityLivingBase))) {
/* 116:127 */           setAttackTarget((EntityLivingBase)this.web.hookedEntity);
/* 117:    */         }
/* 118:    */       }
/* 119:128 */       else if ((getAttackTarget() != null) && 
/* 120:129 */         (getDistanceSqToEntity(this.web) < getDistanceSqToEntity(getAttackTarget()))) {
/* 121:130 */         setHookDead = true;
/* 122:    */       }
/* 123:133 */       if ((this.web.ticksExisted > 100) || (setHookDead))
/* 124:    */       {
/* 125:134 */         this.web.setDead();
/* 126:135 */         this.web = null;
/* 127:    */       }
/* 128:    */     }
/* 129:139 */     if (this.escapeWeb != null)
/* 130:    */     {
/* 131:140 */       if (!this.escapeWeb.isReeling()) {
/* 132:141 */         this.escapeWeb.motionY -= 0.2D;
/* 133:    */       }
/* 134:142 */       if (this.escapeWeb.radio > this.size) {
/* 135:143 */         this.escapeWeb.radio -= 1.0D;
/* 136:    */       }
/* 137:144 */       if (((this.escapeWeb.ticksExisted > 100) && (this.onGround)) || (this.escapeWeb.hookedEntity != null) || (this.escapeWeb.ticksExisted > 300)) {
/* 138:145 */         this.escapeWeb.setDead();
/* 139:    */       }
/* 140:148 */       if (!this.escapeWeb.isEntityAlive()) {
/* 141:149 */         this.escapeWeb = null;
/* 142:    */       }
/* 143:151 */       if ((this.isCollidedHorizontally) || (this.isCollidedVertically)) {
/* 144:152 */         this.motionY = 0.0D;
/* 145:    */       }
/* 146:    */     }
/* 147:154 */     if (!this.isDead)
/* 148:    */     {
/* 149:155 */       this.rightHand.onUpdate();
/* 150:156 */       this.leftHand.onUpdate();
/* 151:    */     }
/* 152:158 */     super.onUpdate();
/* 153:    */   }
/* 154:    */   
/* 155:    */   public void attackToXYZ(byte arm, double x, double y, double z)
/* 156:    */   {
/* 157:164 */     if (!this.worldObj.isRemote)
/* 158:    */     {
/* 159:165 */       PacketAttackToXYZ packet = new PacketAttackToXYZ(getEntityId(), arm, x, y, z);
/* 160:166 */       ChocolateQuest.channel.sendToAllAround(this, packet, 64);
/* 161:    */     }
/* 162:168 */     if (arm == 5) {
/* 163:169 */       this.leftHand.swingArmTo(x, y, z);
/* 164:    */     } else {
/* 165:171 */       this.rightHand.swingArmTo(x, y, z);
/* 166:    */     }
/* 167:    */   }
/* 168:    */   
/* 169:    */   public void attackEntity(Entity target, float par2)
/* 170:    */   {
/* 171:176 */     if (!attackInProgress()) {
/* 172:177 */       if (this.projectileCoolDown < 60)
/* 173:    */       {
/* 174:178 */         this.projectileCoolDown += 1;
/* 175:179 */         if (this.projectileCoolDown == 54) {
/* 176:180 */           swingItem();
/* 177:    */         }
/* 178:    */       }
/* 179:    */       else
/* 180:    */       {
/* 181:183 */         this.projectileCoolDown = this.rand.nextInt(30);
/* 182:184 */         if ((this.rand.nextInt(3) == 0) && (this.web == null))
/* 183:    */         {
/* 184:185 */           shootWeb();
/* 185:    */         }
/* 186:187 */         else if (!this.worldObj.isRemote)
/* 187:    */         {
/* 188:188 */           EntityBaseBall ball = new EntityBaseBall(this.worldObj, this, 0, 0);
/* 189:189 */           ball.setThrowableHeading(target.posX - this.posX, target.posY - this.posY, target.posZ - this.posZ, 1.0F, 0.0F);
/* 190:190 */           ball.posY -= this.height / 2.0F;
/* 191:191 */           this.worldObj.spawnEntityInWorld(ball);
/* 192:    */         }
/* 193:    */       }
/* 194:    */     }
/* 195:196 */     if ((this.ticksExisted % (this.attackSpeed + 1) == 0) && (!this.worldObj.isRemote) && ((!this.leftHand.attackInProgress()) || (!this.rightHand.attackInProgress())))
/* 196:    */     {
/* 197:197 */       double posY = this.posY + this.size;
/* 198:198 */       int angle = (int)MathHelper.wrapAngleTo180_double(BDHelper.getAngleBetweenEntities(this, target) - this.rotationYaw);
/* 199:199 */       double targetY = target.posY + target.height;
/* 200:200 */       double dx = this.posX - target.posX;
/* 201:201 */       double dy = this.posY + this.size - targetY;
/* 202:202 */       double dz = this.posZ - target.posZ;
/* 203:203 */       double distToHead = dx * dx + dy * dy + dz * dz - this.width * this.width;
/* 204:204 */       if (distToHead < (this.rightHand.getArmLength() + 1.0D) * (this.rightHand.getArmLength() + 1.0D))
/* 205:    */       {
/* 206:206 */         boolean targetDown = this.posY - target.posY - target.height > 0.0D;
/* 207:207 */         boolean handFlag = this.rand.nextBoolean();
/* 208:208 */         boolean ARM_LEFT = false;boolean ARM_RIGHT = true;
/* 209:209 */         handFlag = this.rand.nextBoolean();
/* 210:210 */         if (!targetDown) {
/* 211:211 */           if (!handFlag)
/* 212:    */           {
/* 213:212 */             if ((this.leftHand.attackInProgress()) || (angle > 20)) {
/* 214:213 */               handFlag = true;
/* 215:    */             }
/* 216:    */           }
/* 217:215 */           else if ((this.rightHand.attackInProgress()) || (angle < -20)) {
/* 218:216 */             handFlag = false;
/* 219:    */           }
/* 220:    */         }
/* 221:219 */         if (((angle > -110) && (angle < 110)) || (targetDown)) {
/* 222:220 */           if ((!handFlag) && (!this.leftHand.attackInProgress())) {
/* 223:221 */             this.leftHand.attackTarget(target);
/* 224:222 */           } else if ((handFlag == true) && (!this.rightHand.attackInProgress())) {
/* 225:223 */             this.rightHand.attackTarget(target);
/* 226:    */           }
/* 227:    */         }
/* 228:    */       }
/* 229:    */     }
/* 230:227 */     super.attackEntity(target, par2);
/* 231:    */   }
/* 232:    */   
/* 233:    */   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
/* 234:    */   {
/* 235:232 */     if ((this.escapeWeb == null) && (this.rand.nextInt(10) == 0)) {
/* 236:233 */       shootEscapeWeb();
/* 237:    */     }
/* 238:234 */     return super.attackEntityFrom(par1DamageSource, par2);
/* 239:    */   }
/* 240:    */   
/* 241:    */   public void shootWeb()
/* 242:    */   {
/* 243:237 */     if (!this.worldObj.isRemote)
/* 244:    */     {
/* 245:238 */       int pos = 0;
/* 246:239 */       if (this.web != null)
/* 247:    */       {
/* 248:240 */         this.web.setDead();
/* 249:241 */         this.web = null;
/* 250:    */       }
/* 251:243 */       Entity t = getAttackTarget();
/* 252:244 */       this.web = new EntityHookShoot(this.worldObj, this, 3);
/* 253:245 */       this.web.setThrowableHeading(t.posX - this.posX, t.posY - this.posY - this.height / 2.0F, t.posZ - this.posZ, 1.0F, 0.0F);
/* 254:246 */       this.worldObj.spawnEntityInWorld(this.web);
/* 255:    */     }
/* 256:    */   }
/* 257:    */   
/* 258:    */   public void shootEscapeWeb()
/* 259:    */   {
/* 260:250 */     if (!this.worldObj.isRemote)
/* 261:    */     {
/* 262:251 */       int pos = 0;
/* 263:252 */       if (this.escapeWeb != null)
/* 264:    */       {
/* 265:253 */         this.escapeWeb.setDead();
/* 266:254 */         this.escapeWeb = null;
/* 267:    */       }
/* 268:256 */       this.escapeWeb = new EntityHookShoot(this.worldObj, this, 5);
/* 269:257 */       this.escapeWeb.motionY = 2.5D;
/* 270:258 */       this.escapeWeb.motionX = this.rand.nextGaussian();
/* 271:259 */       this.escapeWeb.motionZ = this.rand.nextGaussian();
/* 272:260 */       this.worldObj.spawnEntityInWorld(this.escapeWeb);
/* 273:    */     }
/* 274:    */   }
/* 275:    */   
/* 276:    */   public void setInWeb() {}
/* 277:    */   
/* 278:    */   public boolean attackInProgress()
/* 279:    */   {
/* 280:268 */     return (this.leftHand.isAttacking) || (this.rightHand.isAttacking) || (this.isSwingInProgress);
/* 281:    */   }
/* 282:    */   
/* 283:    */   public boolean isSneaking()
/* 284:    */   {
/* 285:273 */     return true;
/* 286:    */   }
/* 287:    */   
/* 288:    */   public double getArmLength()
/* 289:    */   {
/* 290:277 */     return this.size;
/* 291:    */   }
/* 292:    */   
/* 293:    */   protected void resize()
/* 294:    */   {
/* 295:281 */     setSize(this.size * 1.4F, this.size);
/* 296:    */   }
/* 297:    */   
/* 298:    */   protected void fall(float par1) {}
/* 299:    */   
/* 300:    */   protected String getLivingSound()
/* 301:    */   {
/* 302:288 */     return "mob.spider.say";
/* 303:    */   }
/* 304:    */   
/* 305:    */   protected String getHurtSound()
/* 306:    */   {
/* 307:291 */     return "mob.spider.say";
/* 308:    */   }
/* 309:    */   
/* 310:    */   protected String getDeathSound()
/* 311:    */   {
/* 312:294 */     return "mob.spider.death";
/* 313:    */   }
/* 314:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.EntitySpiderBoss
 * JD-Core Version:    0.7.1
 */