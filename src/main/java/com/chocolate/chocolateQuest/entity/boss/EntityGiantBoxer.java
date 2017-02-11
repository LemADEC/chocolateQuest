/*   1:    */ package com.chocolate.chocolateQuest.entity.boss;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.entity.EntityReferee;
/*   5:    */ import com.chocolate.chocolateQuest.entity.ai.AIBossAttack;
/*   6:    */ import com.chocolate.chocolateQuest.entity.ai.AITargetHurtBy;
/*   7:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   8:    */ import com.chocolate.chocolateQuest.packets.PacketAttackToXYZ;
/*   9:    */ import com.chocolate.chocolateQuest.packets.PacketEntityAnimation;
/*  10:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  11:    */ import java.util.List;
/*  12:    */ import java.util.Random;
/*  13:    */ import net.minecraft.entity.Entity;
/*  14:    */ import net.minecraft.entity.EntityLivingBase;
/*  15:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  16:    */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*  17:    */ import net.minecraft.entity.ai.EntityAISwimming;
/*  18:    */ import net.minecraft.entity.ai.EntityAITasks;
/*  19:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  20:    */ import net.minecraft.entity.monster.EntityGhast;
/*  21:    */ import net.minecraft.entity.monster.EntityMob;
/*  22:    */ import net.minecraft.entity.player.EntityPlayer;
/*  23:    */ import net.minecraft.util.AxisAlignedBB;
/*  24:    */ import net.minecraft.util.DamageSource;
/*  25:    */ import net.minecraft.util.MathHelper;
/*  26:    */ import net.minecraft.world.World;
/*  27:    */ 
/*  28:    */ public class EntityGiantBoxer
/*  29:    */   extends EntityBaseBoss
/*  30:    */ {
/*  31:    */   public AttackPunch rightHand;
/*  32:    */   public AttackPunch leftHand;
/*  33:    */   public AttackKick kickHelper;
/*  34:    */   static final byte airSmash = 10;
/*  35: 32 */   public boolean airSmashInProgress = false;
/*  36: 33 */   public boolean airSmashFalling = false;
/*  37:    */   public EntityPart head;
/*  38: 36 */   public int attackSpeed = 15;
/*  39:    */   
/*  40:    */   public EntityGiantBoxer(World world)
/*  41:    */   {
/*  42: 38 */     super(world);
/*  43: 39 */     resize();
/*  44: 40 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23D + this.size / 70.0F);
/*  45: 41 */     this.kickHelper = new AttackKick(this);
/*  46: 42 */     this.leftHand = new AttackPunch(this, (byte)5);
/*  47: 43 */     this.leftHand.setAngle(-15, -2, 0.4F);
/*  48: 44 */     this.rightHand = new AttackPunch(this, (byte)6);
/*  49: 45 */     this.rightHand.setAngle(15, -2, 0.4F);
/*  50:    */     
/*  51: 47 */     this.projectileDefense = 40;
/*  52: 48 */     this.blastDefense = 10;
/*  53: 49 */     this.magicDefense = 10;
/*  54: 50 */     this.fireDefense = -30;
/*  55:    */   }
/*  56:    */   
/*  57:    */   protected void scaleAttributes()
/*  58:    */   {
/*  59: 54 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23D + this.lvl * 0.02D);
/*  60: 55 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0D + this.lvl * 0.5D);
/*  61: 56 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400.0D + this.lvl * 360.0D);
/*  62: 57 */     this.attackSpeed = ((int)(6.0D + this.lvl * 1.8D));
/*  63:    */   }
/*  64:    */   
/*  65:    */   protected void entityInit()
/*  66:    */   {
/*  67: 63 */     super.entityInit();
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void addAITasks()
/*  71:    */   {
/*  72: 68 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  73: 69 */     this.tasks.addTask(1, new AIBossAttack(this, 1.0F, false));
/*  74:    */     
/*  75:    */ 
/*  76: 72 */     this.targetTasks.addTask(1, new AITargetHurtBy(this, true));
/*  77: 73 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  78: 74 */     this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true));
/*  79:    */   }
/*  80:    */   
/*  81:    */   protected boolean isAIEnabled()
/*  82:    */   {
/*  83: 80 */     return true;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void initBody()
/*  87:    */   {
/*  88: 85 */     this.head = new EntityPartRidable(this.worldObj, this, 0, 0.0F, this.size / 6.0F, this.size);
/*  89: 86 */     this.head.setSize(this.size / 3.0F, this.size / 3.0F);
/*  90: 87 */     if (!this.worldObj.isRemote) {
/*  91: 89 */       this.worldObj.spawnEntityInWorld(this.head);
/*  92:    */     }
/*  93: 91 */     super.initBody();
/*  94:    */   }
/*  95:    */   
/*  96:    */   public void setPart(EntityPart entityPart, int partID)
/*  97:    */   {
/*  98: 95 */     super.setPart(entityPart, partID);
/*  99: 96 */     entityPart.setSize(this.size / 3.0F, this.size / 3.0F);
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void onUpdate()
/* 103:    */   {
/* 104:101 */     if (!this.isDead)
/* 105:    */     {
/* 106:102 */       this.leftHand.onUpdate();
/* 107:103 */       this.rightHand.onUpdate();
/* 108:104 */       this.kickHelper.onUpdate();
/* 109:105 */       if ((this.airSmashInProgress) && (this.fallDistance > 0.0F)) {
/* 110:106 */         this.airSmashFalling = true;
/* 111:    */       }
/* 112:107 */       if ((this.airSmashInProgress) && (this.onGround) && (this.airSmashFalling))
/* 113:    */       {
/* 114:108 */         this.airSmashInProgress = false;
/* 115:109 */         this.airSmashFalling = false;
/* 116:110 */         if (this.worldObj.isRemote)
/* 117:    */         {
/* 118:111 */           this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
/* 119:    */         }
/* 120:    */         else
/* 121:    */         {
/* 122:113 */           List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(1.0F + this.size / 10.0F, 1.0D, 1.0F + this.size / 10.0F));
/* 123:114 */           for (Entity e : list) {
/* 124:115 */             if ((e instanceof EntityLivingBase)) {
/* 125:116 */               attackEntityAsMob(e);
/* 126:    */             }
/* 127:    */           }
/* 128:    */         }
/* 129:    */       }
/* 130:    */     }
/* 131:122 */     if (this.worldObj.isRemote)
/* 132:    */     {
/* 133:123 */       if ((getHealth() < getMaxHealth() / 3.0F) && (this.ticksExisted % 10 < 2))
/* 134:    */       {
/* 135:124 */         String part = "largesmoke";
/* 136:125 */         if (this.size < 5.0F) {
/* 137:126 */           part = "smoke";
/* 138:    */         }
/* 139:127 */         float rot = this.rotationYawHead * 3.1416F / 180.0F;
/* 140:128 */         double scale = this.width * 1.4D;
/* 141:129 */         float desp = this.width * 0.05F;
/* 142:130 */         double mx = MathHelper.sin(rot + 0.2F);
/* 143:131 */         double mz = MathHelper.cos(rot + 0.2F);
/* 144:132 */         double posX = this.posX - mx * scale;
/* 145:133 */         double posY = this.posY + this.height * 1.05D;
/* 146:134 */         double posZ = this.posZ + mz * scale;
/* 147:135 */         this.worldObj.spawnParticle(part, posX + this.rand.nextGaussian() * desp, posY, posZ + this.rand.nextGaussian() * desp, -mx / 20.0D, -(0.3D + this.rand.nextFloat() * this.size) / 25.0D, mz / 20.0D);
/* 148:    */         
/* 149:    */ 
/* 150:    */ 
/* 151:139 */         mx = MathHelper.sin(rot - 0.2F);
/* 152:140 */         mz = MathHelper.cos(rot - 0.2F);
/* 153:141 */         posX = this.posX - mx * scale;
/* 154:142 */         posY = this.posY + this.height * 1.05D;
/* 155:143 */         posZ = this.posZ + mz * scale;
/* 156:144 */         this.worldObj.spawnParticle(part, posX + this.rand.nextGaussian() * desp, posY, posZ + this.rand.nextGaussian() * desp, -mx / 20.0D, -(0.3D + this.rand.nextFloat() * this.size) / 25.0D, mz / 20.0D);
/* 157:    */       }
/* 158:148 */       if (this.ticksExisted % 200 < 10)
/* 159:    */       {
/* 160:149 */         float rot = this.rotationYaw * 3.1416F / 180.0F;
/* 161:150 */         double scale = this.width / 1.6D;
/* 162:151 */         double mx = MathHelper.sin(rot) * scale;
/* 163:152 */         double mz = MathHelper.cos(rot) * scale;
/* 164:153 */         double posX = this.posX + mx;
/* 165:154 */         double posY = this.posY + this.height * 0.5D;
/* 166:155 */         double posZ = this.posZ - mz;
/* 167:156 */         float desp = this.width * 0.05F;
/* 168:157 */         this.worldObj.spawnParticle("cloud", posX + this.rand.nextGaussian() * desp, posY, posZ + this.rand.nextGaussian() * desp, mx / 5.0D, -0.3D + this.rand.nextFloat() / 4.0F, -mz / 5.0D);
/* 169:    */       }
/* 170:    */     }
/* 171:162 */     super.onUpdate();
/* 172:    */   }
/* 173:    */   
/* 174:    */   public void animationBoss(byte animType)
/* 175:    */   {
/* 176:167 */     if (!this.worldObj.isRemote)
/* 177:    */     {
/* 178:169 */       PacketEntityAnimation packet = new PacketEntityAnimation(getEntityId(), animType);
/* 179:170 */       ChocolateQuest.channel.sendToAllAround(this, packet, 64);
/* 180:    */     }
/* 181:172 */     switch (animType)
/* 182:    */     {
/* 183:    */     case 10: 
/* 184:173 */       this.airSmashInProgress = true;return;
/* 185:    */     }
/* 186:174 */     this.kickHelper.kick(animType);
/* 187:    */   }
/* 188:    */   
/* 189:    */   public void attackToXYZ(byte arm, double x, double y, double z)
/* 190:    */   {
/* 191:179 */     if (!this.worldObj.isRemote)
/* 192:    */     {
/* 193:180 */       PacketAttackToXYZ packet = new PacketAttackToXYZ(getEntityId(), arm, x, y, z);
/* 194:181 */       ChocolateQuest.channel.sendToAllAround(this, packet, 64);
/* 195:    */     }
/* 196:183 */     if (arm == 5) {
/* 197:184 */       this.leftHand.swingArmTo(x, y, z);
/* 198:    */     } else {
/* 199:186 */       this.rightHand.swingArmTo(x, y, z);
/* 200:    */     }
/* 201:    */   }
/* 202:    */   
/* 203:    */   public boolean attackFromPart(DamageSource par1DamageSource, float par2, EntityPart part)
/* 204:    */   {
/* 205:192 */     if (part == this.head) {
/* 206:193 */       if (par1DamageSource.isProjectile()) {
/* 207:194 */         par2 *= 2.0F;
/* 208:    */       } else {
/* 209:196 */         par2 *= this.lvl;
/* 210:    */       }
/* 211:    */     }
/* 212:198 */     return super.attackFromPart(par1DamageSource, par2, part);
/* 213:    */   }
/* 214:    */   
/* 215:    */   public void attackEntity(Entity target, float dist)
/* 216:    */   {
/* 217:203 */     int angle = (int)MathHelper.wrapAngleTo180_double(BDHelper.getAngleBetweenEntities(this, target) - this.rotationYaw);
/* 218:204 */     if ((this.ticksExisted % this.attackSpeed == 0) && (!this.worldObj.isRemote))
/* 219:    */     {
/* 220:205 */       double posY = this.posY + this.size;
/* 221:206 */       double targetY = target.posY + target.height;
/* 222:207 */       double dx = this.posX - target.posX;
/* 223:208 */       double dy = this.posY + this.size - targetY;
/* 224:209 */       double dz = this.posZ - target.posZ;
/* 225:210 */       double distToHead = dx * dx + dy * dy + dz * dz - this.width * this.width;
/* 226:211 */       if (distToHead < (getArmLength() + 1.0D) * (getArmLength() + 1.0D))
/* 227:    */       {
/* 228:213 */         boolean handFlag = this.rand.nextBoolean();
/* 229:214 */         boolean ARM_LEFT = false;boolean ARM_RIGHT = true;
/* 230:215 */         if (!handFlag)
/* 231:    */         {
/* 232:216 */           if (this.leftHand.attackInProgress()) {
/* 233:217 */             handFlag = true;
/* 234:    */           }
/* 235:    */         }
/* 236:219 */         else if (this.rightHand.attackInProgress()) {
/* 237:220 */           handFlag = false;
/* 238:    */         }
/* 239:221 */         if ((angle > -110) && (angle < 110)) {
/* 240:222 */           if ((!handFlag) && (!this.leftHand.attackInProgress())) {
/* 241:223 */             this.leftHand.attackTarget(target);
/* 242:224 */           } else if (!this.rightHand.attackInProgress()) {
/* 243:225 */             this.rightHand.attackTarget(target);
/* 244:    */           }
/* 245:    */         }
/* 246:    */       }
/* 247:    */     }
/* 248:229 */     if ((this.ticksExisted % 10 == 0) && (!this.worldObj.isRemote) && 
/* 249:230 */       (dist < (this.width + 3.0F) * (this.width + 3.0F))) {
/* 250:231 */       this.kickHelper.attackTarget(target);
/* 251:    */     }
/* 252:234 */     if ((this.ticksExisted % 10 == 0) && (this.onGround) && (this.rand.nextInt(10) == 0) && (
/* 253:235 */       ((dist > (this.width + 3.0F) * (this.width + 3.0F)) && (angle > -60) && (angle < 60)) || (angle < -110) || (angle > 110)))
/* 254:    */     {
/* 255:239 */       this.motionY = (1.5D + this.lvl / 10.0F);
/* 256:240 */       this.motionX = ((target.posX - this.posX) / 5.0D);
/* 257:241 */       this.motionZ = ((target.posZ - this.posZ) / 5.0D);
/* 258:242 */       animationBoss((byte)10);
/* 259:    */     }
/* 260:    */   }
/* 261:    */   
/* 262:    */   public double getArmLength()
/* 263:    */   {
/* 264:248 */     return this.size;
/* 265:    */   }
/* 266:    */   
/* 267:    */   protected void resize()
/* 268:    */   {
/* 269:253 */     setSize(this.size / 3.0F, this.size);
/* 270:    */   }
/* 271:    */   
/* 272:    */   public float getMinSize()
/* 273:    */   {
/* 274:257 */     return 1.1F;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public float getSizeVariation()
/* 278:    */   {
/* 279:261 */     return 1.4F;
/* 280:    */   }
/* 281:    */   
/* 282:    */   public boolean canAttackClass(Class par1Class)
/* 283:    */   {
/* 284:265 */     return (EntityGhast.class != par1Class) && (par1Class != EntityReferee.class);
/* 285:    */   }
/* 286:    */   
/* 287:    */   protected void fall(float par1) {}
/* 288:    */   
/* 289:    */   public boolean isEntityEqual(Entity par1Entity)
/* 290:    */   {
/* 291:272 */     return (this == par1Entity) || (par1Entity == this.head);
/* 292:    */   }
/* 293:    */   
/* 294:    */   public boolean attackInProgress()
/* 295:    */   {
/* 296:275 */     return (this.kickHelper.isAttackInProgress()) || (this.leftHand.isAttacking) || (this.rightHand.isAttacking);
/* 297:    */   }
/* 298:    */   
/* 299:    */   protected String getLivingSound()
/* 300:    */   {
/* 301:280 */     return "chocolatequest:monking_speak";
/* 302:    */   }
/* 303:    */   
/* 304:    */   protected String getHurtSound()
/* 305:    */   {
/* 306:283 */     return "chocolatequest:monking_hurt";
/* 307:    */   }
/* 308:    */   
/* 309:    */   protected String getDeathSound()
/* 310:    */   {
/* 311:286 */     return "chocolatequest:monking_death";
/* 312:    */   }
/* 313:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.EntityGiantBoxer
 * JD-Core Version:    0.7.1
 */