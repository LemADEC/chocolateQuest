/*   1:    */ package com.chocolate.chocolateQuest.entity.mob;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.IRangedWeapon;
/*   4:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   5:    */ import com.chocolate.chocolateQuest.entity.boss.EntityWyvern;
/*   6:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*   7:    */ import com.chocolate.chocolateQuest.magic.Elements;
/*   8:    */ import java.util.List;
/*   9:    */ import java.util.Random;
/*  10:    */ import net.minecraft.block.Block;
/*  11:    */ import net.minecraft.block.material.Material;
/*  12:    */ import net.minecraft.entity.Entity;
/*  13:    */ import net.minecraft.entity.EntityLiving;
/*  14:    */ import net.minecraft.entity.EntityLivingBase;
/*  15:    */ import net.minecraft.entity.EnumCreatureAttribute;
/*  16:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  17:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  18:    */ import net.minecraft.entity.effect.EntityLightningBolt;
/*  19:    */ import net.minecraft.init.Blocks;
/*  20:    */ import net.minecraft.init.Items;
/*  21:    */ import net.minecraft.item.ItemBow;
/*  22:    */ import net.minecraft.item.ItemStack;
/*  23:    */ import net.minecraft.util.DamageSource;
/*  24:    */ import net.minecraft.util.MathHelper;
/*  25:    */ import net.minecraft.world.World;
/*  26:    */ 
/*  27:    */ public class EntityWalkerBoss
/*  28:    */   extends EntityHumanWalker
/*  29:    */ {
/*  30: 26 */   int countDown = 0;
/*  31:    */   boolean bolt;
/*  32:    */   double lightX;
/*  33:    */   double lightY;
/*  34:    */   double lightZ;
/*  35:    */   
/*  36:    */   public EntityWalkerBoss(World world)
/*  37:    */   {
/*  38: 31 */     super(world);
/*  39: 32 */     this.blockRate = 8;
/*  40: 33 */     this.parryRate = 8;
/*  41: 34 */     setCurrentItemOrArmor(0, new ItemStack(ChocolateQuest.endSword));
/*  42: 35 */     setLeftHandItem(new ItemStack(ChocolateQuest.shield, 1, 10));
/*  43: 36 */     updateHands();
/*  44: 37 */     setCurrentItemOrArmor(3, new ItemStack(ChocolateQuest.kingArmor));
/*  45: 38 */     setCurrentItemOrArmor(2, new ItemStack(Items.diamond_leggings));
/*  46: 39 */     setCurrentItemOrArmor(1, new ItemStack(Items.diamond_boots));
/*  47:    */   }
/*  48:    */   
/*  49:    */   protected void applyEntityAttributes()
/*  50:    */   {
/*  51: 44 */     super.applyEntityAttributes();
/*  52: 45 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
/*  53: 46 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(500.0D);
/*  54:    */   }
/*  55:    */   
/*  56:    */   public int getLeadershipValue()
/*  57:    */   {
/*  58: 51 */     return 1000;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public boolean isBoss()
/*  62:    */   {
/*  63: 55 */     return true;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public int getInteligence()
/*  67:    */   {
/*  68: 59 */     return 0;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public boolean canBePushed()
/*  72:    */   {
/*  73: 64 */     return false;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public boolean shouldRenderCape()
/*  77:    */   {
/*  78: 69 */     return true;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public boolean attackEntityFrom(DamageSource damagesource, float i)
/*  82:    */   {
/*  83: 74 */     if (damagesource.getDamageType() == "fall") {
/*  84: 76 */       return false;
/*  85:    */     }
/*  86: 78 */     if ((damagesource.isProjectile()) && ((damagesource.getEntity() instanceof EntityLiving)))
/*  87:    */     {
/*  88: 80 */       setAttackTarget((EntityLiving)damagesource.getEntity());
/*  89: 81 */       castTeleport(damagesource.getEntity());
/*  90:    */     }
/*  91: 84 */     return super.attackEntityFrom(damagesource, i);
/*  92:    */   }
/*  93:    */   
/*  94:    */   public void onLivingUpdate()
/*  95:    */   {
/*  96: 90 */     if (getAttackTarget() != null)
/*  97:    */     {
/*  98: 92 */       float dist = getDistanceToEntity(getAttackTarget());
/*  99:    */       
/* 100: 94 */       boolean isTargetRanged = false;
/* 101: 95 */       ItemStack is = getAttackTarget().getEquipmentInSlot(0);
/* 102: 96 */       if ((is != null) && (dist > 5.0F)) {
/* 103: 98 */         if (((is.getItem() instanceof ItemBow)) || ((is.getItem() instanceof IRangedWeapon))) {
/* 104:100 */           isTargetRanged = true;
/* 105:    */         }
/* 106:    */       }
/* 107:104 */       if (((dist > 15.0F) && (dist < 40.0F)) || (isTargetRanged)) {
/* 108:106 */         if (!this.worldObj.isRemote) {
/* 109:107 */           castTeleport(getAttackTarget());
/* 110:    */         }
/* 111:    */       }
/* 112:110 */       if (((dist > 5.0F) && (dist < 10.0F) && (this.rand.nextInt(30) == 0)) || ((this.rand.nextInt(300) == 0) && (this.ridingEntity == null))) {
/* 113:112 */         if (!this.worldObj.isRemote)
/* 114:    */         {
/* 115:114 */           swingLeftHand();
/* 116:115 */           Elements element = Elements.darkness;
/* 117:116 */           if (getAttackTarget().getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
/* 118:117 */             element = Elements.light;
/* 119:    */           }
/* 120:118 */           EntityBaseBall ball = new EntityBaseBall(this.worldObj, this, 10, 1, element);
/* 121:119 */           ball.motionY = (getAttackTarget().posY - this.posY);
/* 122:120 */           this.worldObj.spawnEntityInWorld(ball);
/* 123:    */         }
/* 124:    */       }
/* 125:    */     }
/* 126:125 */     if ((!this.worldObj.isRemote) && (getAttackTarget() == null) && (isDefending())) {
/* 127:126 */       setDefending(false);
/* 128:    */     }
/* 129:128 */     super.onLivingUpdate();
/* 130:    */   }
/* 131:    */   
/* 132:    */   public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt)
/* 133:    */   {
/* 134:136 */     heal(50.0F);
/* 135:137 */     for (int i = 0; i < 3; i++) {
/* 136:139 */       this.worldObj.spawnParticle("heart", this.posX + this.rand.nextFloat() - 0.5D, this.posY + this.rand.nextFloat() - 0.5D, this.posZ + this.rand.nextFloat() - 0.5D, 0.0D, 1.0D, 0.0D);
/* 137:    */     }
/* 138:    */   }
/* 139:    */   
/* 140:    */   public void heal(float par1)
/* 141:    */   {
/* 142:145 */     super.heal(par1);
/* 143:147 */     if (getHealth() > getMaxHealth()) {
/* 144:149 */       setHealth(getMaxHealth());
/* 145:    */     }
/* 146:    */   }
/* 147:    */   
/* 148:    */   public void onDeath(DamageSource damagesource)
/* 149:    */   {
/* 150:156 */     if (!this.worldObj.isRemote)
/* 151:    */     {
/* 152:158 */       if (this.rand.nextInt(2) == 0) {
/* 153:159 */         dropItem(ChocolateQuest.endSword, 1);
/* 154:    */       }
/* 155:161 */       if (this.rand.nextInt(4) == 0) {
/* 156:162 */         dropItem(ChocolateQuest.kingArmor, 1);
/* 157:    */       }
/* 158:    */     }
/* 159:165 */     super.onDeath(damagesource);
/* 160:    */   }
/* 161:    */   
/* 162:    */   protected boolean castTeleport(Entity entity)
/* 163:    */   {
/* 164:170 */     if (this.ridingEntity != null) {
/* 165:171 */       return false;
/* 166:    */     }
/* 167:172 */     double d = entity.posX + (this.rand.nextDouble() - 0.5D) * 4.0D;
/* 168:173 */     double d1 = entity.posY;
/* 169:174 */     double d2 = entity.posZ + (this.rand.nextDouble() - 0.5D) * 4.0D;
/* 170:175 */     double d3 = this.posX;
/* 171:176 */     double d4 = this.posY;
/* 172:177 */     double d5 = this.posZ;
/* 173:178 */     this.posX = d;
/* 174:179 */     this.posY = d1;
/* 175:180 */     this.posZ = d2;
/* 176:181 */     boolean flag = false;
/* 177:182 */     int i = MathHelper.floor_double(this.posX);
/* 178:183 */     int j = MathHelper.floor_double(this.posY);
/* 179:184 */     int k = MathHelper.floor_double(this.posZ);
/* 180:186 */     if (this.worldObj.blockExists(i, j, k))
/* 181:    */     {
boolean flag1 = false;
/* 182:190 */       for (flag1 = false; (!flag1) && (j > 0);)
/* 183:    */       {
/* 184:192 */         Block i1 = this.worldObj.getBlock(i, j - 1, k);
/* 185:194 */         if ((i1 == Blocks.air) || (!i1.getMaterial().isSolid()))
/* 186:    */         {
/* 187:196 */           this.posY -= 1.0D;
/* 188:197 */           j--;
/* 189:    */         }
/* 190:    */         else
/* 191:    */         {
/* 192:201 */           flag1 = true;
/* 193:    */         }
/* 194:    */       }
/* 195:205 */       if (flag1)
/* 196:    */       {
/* 197:207 */         for (int n = 0; n < 10; n++) {
/* 198:209 */           this.worldObj.spawnParticle("largesmoke", d3 + this.rand.nextFloat() - 0.5D, d4, d5 + this.rand.nextFloat() - 0.5D, 1.0D, 1.0D, 1.0D);
/* 199:    */         }
/* 200:212 */         setPosition(this.posX, this.posY, this.posZ);
/* 201:214 */         if ((this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0) && (!this.worldObj.isAnyLiquid(this.boundingBox))) {
/* 202:216 */           flag = true;
/* 203:    */         }
/* 204:    */       }
/* 205:    */     }
/* 206:221 */     if (!flag)
/* 207:    */     {
/* 208:223 */       setPosition(d3, d4, d5);
/* 209:224 */       return false;
/* 210:    */     }
/* 211:227 */     int l = 128;
/* 212:    */     double d9;
/* 213:229 */     for (int j1 = 0; j1 < l; j1++)
/* 214:    */     {
/* 215:231 */       double d6 = j1 / (l - 1.0D);
/* 216:232 */       float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
/* 217:233 */       float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
/* 218:234 */       float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
/* 219:235 */       double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
/* 220:236 */       double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * this.height;
/* 221:237 */       d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
/* 222:    */     }
/* 223:240 */     this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
/* 224:241 */     this.worldObj.playSoundAtEntity(this, "mob.endermen.portal", 1.0F, 1.0F);
/* 225:242 */     return true;
/* 226:    */   }
/* 227:    */   
/* 228:    */   public boolean isSuitableMount(Entity entity)
/* 229:    */   {
/* 230:248 */     return ((entity instanceof EntityWyvern)) || (super.isSuitableMount(entity));
/* 231:    */   }
/* 232:    */   
/* 233:    */   protected boolean canDespawn()
/* 234:    */   {
/* 235:253 */     return false;
/* 236:    */   }
/* 237:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.EntityWalkerBoss

 * JD-Core Version:    0.7.1

 */
