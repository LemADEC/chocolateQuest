/*   1:    */ package com.chocolate.chocolateQuest.entity.boss;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.entity.ai.AIBossAttack;
/*   5:    */ import com.chocolate.chocolateQuest.entity.ai.AIFly;
/*   6:    */ import com.chocolate.chocolateQuest.entity.ai.AIFlyRoam;
/*   7:    */ import com.chocolate.chocolateQuest.entity.ai.AITargetHurtBy;
/*   8:    */ import com.chocolate.chocolateQuest.entity.ai.AITargetNearestAttackableForDragon;
/*   9:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  10:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*  11:    */ import com.chocolate.chocolateQuest.packets.PacketEntityAnimation;
/*  12:    */ import java.util.Random;
/*  13:    */ import net.minecraft.entity.Entity;
/*  14:    */ import net.minecraft.entity.EntityLivingBase;
/*  15:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  16:    */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*  17:    */ import net.minecraft.entity.ai.EntityAITasks;
/*  18:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  19:    */ import net.minecraft.entity.monster.IMob;
/*  20:    */ import net.minecraft.entity.player.EntityPlayer;
/*  21:    */ import net.minecraft.init.Items;
/*  22:    */ import net.minecraft.util.MathHelper;
/*  23:    */ import net.minecraft.world.World;
/*  24:    */ 
/*  25:    */ public class EntityWyvern
/*  26:    */   extends EntityBaseBoss
/*  27:    */ {
/*  28: 24 */   public int openMouthTime = 0;
/*  29: 25 */   public final int totalOpenMouthTime = 10;
/*  30: 27 */   int speedBoost = 0;
/*  31: 28 */   private int flameThrowerCD = 0;
/*  32: 29 */   protected int flameThrowerMaxCD = 10;
/*  33: 31 */   protected final byte OPEN_MOUTH = 1;
/*  34:    */   
/*  35:    */   public EntityWyvern(World par1World)
/*  36:    */   {
/*  37: 34 */     super(par1World);
/*  38: 35 */     addAITasks();
/*  39: 36 */     setSize(1.8F, 2.5F);
/*  40: 37 */     this.isImmuneToFire = true;
/*  41: 38 */     this.size = 1.0F;
/*  42:    */   }
/*  43:    */   
/*  44:    */   protected void applyEntityAttributes()
/*  45:    */   {
/*  46: 43 */     super.applyEntityAttributes();
/*  47: 44 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35D + this.size / 8.0F);
/*  48: 45 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
/*  49: 46 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400.0D);
/*  50: 47 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(80.0D + this.size * 10.0F);
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void addAITasks()
/*  54:    */   {
/*  55: 52 */     this.tasks.addTask(0, new AIFly(this));
/*  56: 53 */     this.tasks.addTask(0, new AIFlyRoam(this, 88));
/*  57: 54 */     this.tasks.addTask(2, new AIBossAttack(this, 1.0F, false));
/*  58: 55 */     this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, false));
/*  59:    */     
/*  60:    */ 
/*  61: 58 */     this.targetTasks.addTask(1, new AITargetHurtBy(this, false));
/*  62: 59 */     this.targetTasks.addTask(2, new AITargetNearestAttackableForDragon(this, IMob.class, 0, true));
/*  63: 60 */     this.targetTasks.addTask(2, new AITargetNearestAttackableForDragon(this, EntityPlayer.class, 0, true));
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void onLivingUpdate()
/*  67:    */   {
/*  68: 66 */     if (this.openMouthTime > 0) {
/*  69: 67 */       this.openMouthTime -= 1;
/*  70:    */     }
/*  71: 68 */     if (!hasHome()) {
/*  72: 70 */       setHomeArea(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 200);
/*  73:    */     }
/*  74: 72 */     if ((!this.onGround) && (getAttackTarget() != null)) {
/*  75: 73 */       this.motionY = 0.0D;
/*  76:    */     }
/*  77: 75 */     if ((this.riddenByEntity instanceof EntityPlayer))
/*  78:    */     {
/*  79: 77 */       if (this.flameThrowerCD > 0) {
/*  80: 78 */         this.flameThrowerCD -= 1;
/*  81:    */       }
/*  82: 79 */       if (((EntityPlayer)this.riddenByEntity).isSwingInProgress) {
/*  83: 81 */         if ((this.flameThrowerCD == 0) && (!this.worldObj.isRemote))
/*  84:    */         {
/*  85: 83 */           double ry = Math.toRadians(this.rotationYaw - 180.0F);
/*  86:    */           
/*  87: 85 */           double x = Math.sin(ry);
/*  88: 86 */           double z = -Math.cos(ry);
/*  89: 87 */           double y = -Math.sin(Math.toRadians(this.riddenByEntity.rotationPitch));
/*  90: 88 */           shootFireball(x, y, z);
/*  91: 89 */           this.flameThrowerCD = this.flameThrowerMaxCD;
/*  92:    */         }
/*  93:    */       }
/*  94:    */     }
/*  95: 94 */     super.onLivingUpdate();
/*  96:    */   }
/*  97:    */   
/*  98:    */   protected boolean isAIEnabled()
/*  99:    */   {
/* 100: 99 */     return true;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public boolean canAttackClass(Class par1Class)
/* 104:    */   {
/* 105:104 */     return true;
/* 106:    */   }
/* 107:    */   
/* 108:    */   protected boolean limitRotation()
/* 109:    */   {
/* 110:109 */     return true;
/* 111:    */   }
/* 112:    */   
/* 113:    */   protected void updateFallState(double par1, boolean par3) {}
/* 114:    */   
/* 115:    */   public void openMouth()
/* 116:    */   {
/* 117:119 */     if (this.openMouthTime <= 0)
/* 118:    */     {
/* 119:121 */       this.openMouthTime = 10;
/* 120:122 */       if (!this.worldObj.isRemote)
/* 121:    */       {
/* 122:124 */         PacketEntityAnimation packet = new PacketEntityAnimation(getEntityId(), (byte)1);
/* 123:125 */         ChocolateQuest.channel.sendToAllAround(this, packet, 64);
/* 124:    */       }
/* 125:    */     }
/* 126:    */   }
/* 127:    */   
/* 128:    */   public void animationBoss(byte animType)
/* 129:    */   {
/* 130:130 */     switch (animType)
/* 131:    */     {
/* 132:    */     case 1: 
/* 133:131 */       openMouth();
/* 134:    */     }
/* 135:    */   }
/* 136:    */   
/* 137:    */   protected void dropFewItems(boolean flag, int i)
/* 138:    */   {
/* 139:138 */     for (int a = this.rand.nextInt(5) + 1; a > 0; a--) {
/* 140:141 */       dropItem(Items.diamond, 2);
/* 141:    */     }
/* 142:143 */     dropItem(ChocolateQuest.dragonHelmet, 1);
/* 143:    */   }
/* 144:    */   
/* 145:    */   public void moveEntityWithHeading(float par1, float par2)
/* 146:    */   {
/* 147:149 */     if ((this.riddenByEntity instanceof EntityPlayer))
/* 148:    */     {
/* 149:151 */       this.prevRotationYaw = (this.rotationYaw = this.riddenByEntity.rotationYaw);
/* 150:152 */       this.rotationPitch = (this.riddenByEntity.rotationPitch * 0.5F);
/* 151:153 */       setRotation(this.rotationYaw, this.rotationPitch);
/* 152:154 */       this.rotationYawHead = (this.renderYawOffset = this.rotationYaw);
/* 153:155 */       par1 = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.5F;
/* 154:156 */       par2 = ((EntityLivingBase)this.riddenByEntity).moveForward;
/* 155:    */       
/* 156:    */ 
/* 157:159 */       this.stepHeight = 1.0F;
/* 158:160 */       this.jumpMovementFactor = (getAIMoveSpeed() * 0.1F);
/* 159:    */       
/* 160:    */ 
/* 161:    */ 
/* 162:164 */       this.prevLimbSwingAmount = this.limbSwingAmount;
/* 163:165 */       double d0 = this.posX - this.prevPosX;
/* 164:166 */       double d1 = this.posZ - this.prevPosZ;
/* 165:167 */       float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;
/* 166:169 */       if (f4 > 1.0F) {
/* 167:171 */         f4 = 1.0F;
/* 168:    */       }
/* 169:174 */       this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
/* 170:175 */       this.limbSwing += this.limbSwingAmount;
/* 171:    */       
/* 172:177 */       double ry = Math.toRadians(this.rotationYaw - 180.0F);
/* 173:    */       
/* 174:179 */       float moveSpeed = (float)getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * par2;
/* 175:180 */       this.motionX = (Math.sin(ry) * moveSpeed);
/* 176:181 */       this.motionZ = (-Math.cos(ry) * moveSpeed);
/* 177:182 */       this.motionY = (-Math.sin(Math.toRadians(this.rotationPitch)) * moveSpeed);
/* 178:183 */       super.moveEntityWithHeading(par1, par2);
/* 179:    */     }
/* 180:    */     else
/* 181:    */     {
/* 182:187 */       super.moveEntityWithHeading(par1, par2);
/* 183:    */     }
/* 184:    */   }
/* 185:    */   
/* 186:    */   public boolean interact(EntityPlayer entityPlayer)
/* 187:    */   {
/* 188:193 */     if (entityPlayer.getCommandSenderName().equals("ArrgChocolate"))
/* 189:    */     {
/* 190:195 */       setAttackTarget(null);
/* 191:196 */       entityPlayer.mountEntity(this);
/* 192:    */     }
/* 193:198 */     return false;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void fireBreath(double x, double y, double z)
/* 197:    */   {
/* 198:204 */     openMouth();
/* 199:205 */     World world = this.worldObj;
/* 200:206 */     if (!world.isRemote)
/* 201:    */     {
/* 202:207 */       world.spawnEntityInWorld(new EntityBaseBall(world, this, x + (getRNG().nextFloat() - 0.5F), y + (getRNG().nextFloat() - 0.25F), z + (getRNG().nextFloat() - 0.5F), 6, 0));
/* 203:    */       
/* 204:    */ 
/* 205:    */ 
/* 206:211 */       world.playSoundEffect((int)this.posX, (int)this.posY, (int)this.posZ, "fire.fire", 4.0F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
/* 207:    */     }
/* 208:    */   }
/* 209:    */   
/* 210:    */   public void shootFireball(double x, double y, double z)
/* 211:    */   {
/* 212:217 */     openMouth();
/* 213:218 */     World world = this.worldObj;
/* 214:219 */     for (int i = 0; i < 4; i++)
/* 215:    */     {
/* 216:221 */       EntityBaseBall entitylargefireball = new EntityBaseBall(world, this, x + (getRNG().nextFloat() - 0.5F) / 4.0F, y + (getRNG().nextFloat() - 0.5F) / 4.0F, z + (getRNG().nextFloat() - 0.5F) / 4.0F, 5, 2);
/* 217:    */       
/* 218:    */ 
/* 219:    */ 
/* 220:225 */       world.spawnEntityInWorld(entitylargefireball);
/* 221:    */     }
/* 222:227 */     world.playSoundEffect((int)this.posX, (int)this.posY, (int)this.posZ, "mob.ghast.fireball", 4.0F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
/* 223:    */   }
/* 224:    */   
/* 225:    */   protected void resize()
/* 226:    */   {
/* 227:232 */     setSize(this.size * 1.8F, this.size * 2.0F);
/* 228:    */   }
/* 229:    */   
/* 230:    */   protected String getLivingSound()
/* 231:    */   {
/* 232:237 */     return "chocolatequest:dragon_speak";
/* 233:    */   }
/* 234:    */   
/* 235:    */   protected String getHurtSound()
/* 236:    */   {
/* 237:240 */     return "chocolatequest:dragon_hurt";
/* 238:    */   }
/* 239:    */   
/* 240:    */   protected String getDeathSound()
/* 241:    */   {
/* 242:243 */     return "chocolatequest:dragon_death";
/* 243:    */   }
/* 244:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.EntityWyvern
 * JD-Core Version:    0.7.1
 */