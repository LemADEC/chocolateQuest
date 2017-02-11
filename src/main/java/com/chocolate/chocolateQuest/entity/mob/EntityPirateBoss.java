/*   1:    */ package com.chocolate.chocolateQuest.entity.mob;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.entity.EntityBaiter;
/*   5:    */ import com.chocolate.chocolateQuest.entity.ai.AIFirefighter;
/*   6:    */ import com.chocolate.chocolateQuest.entity.ai.EnumAiCombat;
/*   7:    */ import java.util.List;
/*   8:    */ import java.util.Random;
/*   9:    */ import net.minecraft.block.Block;
/*  10:    */ import net.minecraft.block.material.Material;
/*  11:    */ import net.minecraft.entity.DataWatcher;
/*  12:    */ import net.minecraft.entity.Entity;
/*  13:    */ import net.minecraft.entity.EntityLiving;
/*  14:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  15:    */ import net.minecraft.entity.ai.EntityAITasks;
/*  16:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  17:    */ import net.minecraft.init.Blocks;
/*  18:    */ import net.minecraft.init.Items;
/*  19:    */ import net.minecraft.item.ItemStack;
/*  20:    */ import net.minecraft.potion.Potion;
/*  21:    */ import net.minecraft.potion.PotionEffect;
/*  22:    */ import net.minecraft.util.DamageSource;
/*  23:    */ import net.minecraft.util.MathHelper;
/*  24:    */ import net.minecraft.world.EnumDifficulty;
/*  25:    */ import net.minecraft.world.World;
/*  26:    */ 
/*  27:    */ public class EntityPirateBoss
/*  28:    */   extends EntityHumanPirate
/*  29:    */ {
/*  30: 23 */   int invisibleCD = 10;
/*  31:    */   
/*  32:    */   public EntityPirateBoss(World world)
/*  33:    */   {
/*  34: 26 */     super(world);
/*  35: 27 */     this.AICombatMode = EnumAiCombat.BACKSTAB.ordinal();
/*  36: 28 */     this.potionCount = 3;
/*  37: 29 */     ItemStack is = getEquipedWeapon();
/*  38: 30 */     setCurrentItemOrArmor(0, is);
/*  39: 31 */     setAIForCurrentMode();
/*  40:    */   }
/*  41:    */   
/*  42:    */   protected void applyEntityAttributes()
/*  43:    */   {
/*  44: 37 */     super.applyEntityAttributes();
/*  45: 38 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
/*  46: 39 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(500.0D);
/*  47:    */   }
/*  48:    */   
/*  49:    */   public ItemStack getEquipedWeapon()
/*  50:    */   {
/*  51: 44 */     if (this.rand.nextBoolean()) {
/*  52: 45 */       return new ItemStack(ChocolateQuest.ninjaDagger);
/*  53:    */     }
/*  54: 47 */     return new ItemStack(ChocolateQuest.tricksterDagger);
/*  55:    */   }
/*  56:    */   
/*  57:    */   public int getLeadershipValue()
/*  58:    */   {
/*  59: 52 */     return 1000;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public boolean isBoss()
/*  63:    */   {
/*  64: 56 */     return true;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public int getInteligence()
/*  68:    */   {
/*  69: 60 */     return 0;
/*  70:    */   }
/*  71:    */   
/*  72:    */   protected void addAITasks()
/*  73:    */   {
/*  74: 64 */     this.tasks.addTask(4, new AIFirefighter(this, 1.0F, false));
/*  75: 65 */     super.addAITasks();
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void onUpdate()
/*  79:    */   {
/*  80: 71 */     if (getAttackTarget() != null)
/*  81:    */     {
/*  82: 73 */       if ((getHealth() < 450.0F) && (!isInvisible()) && (this.invisibleCD < 0))
/*  83:    */       {
/*  84: 75 */         addPotionEffect(new PotionEffect(Potion.invisibility.id, 200, 0));
/*  85: 76 */         setInvisible(true);
/*  86: 78 */         if (this.worldObj.isRemote)
/*  87:    */         {
/*  88: 80 */           for (int n = 0; n < 5; n++) {
/*  89: 82 */             this.worldObj.spawnParticle("mobSpell", this.posX + this.rand.nextFloat() - 0.5D, this.posY + 1.0D, this.posZ + this.rand.nextFloat() - 0.5D, 1.0D, 1.0D, 1.0D);
/*  90:    */           }
/*  91:    */         }
/*  92:    */         else
/*  93:    */         {
/*  94: 87 */           EntityBaiter e = new EntityBaiter(this.worldObj, this);
/*  95: 88 */           e.setPosition(this.posX, this.posY, this.posZ);
/*  96: 89 */           this.worldObj.spawnEntityInWorld(e);
/*  97:    */         }
/*  98: 92 */         castTeleport(getAttackTarget());
/*  99: 93 */         this.invisibleCD = 200;
/* 100:    */       }
/* 101: 96 */       this.invisibleCD -= 1;
/* 102:    */     }
/* 103:100 */     if (isInvisible()) {
/* 104:102 */       if (!this.worldObj.isRemote) {
/* 105:104 */         if (getAttackTarget() == null) {
/* 106:105 */           setInvisible(false);
/* 107:    */         }
/* 108:    */       }
/* 109:    */     }
/* 110:108 */     super.onUpdate();
/* 111:    */   }
/* 112:    */   
/* 113:    */   public boolean attackEntityAsMob(Entity entity)
/* 114:    */   {
/* 115:114 */     if (this.rand.nextInt(15) == 1)
/* 116:    */     {
/* 117:116 */       if ((entity instanceof EntityLiving)) {
/* 118:118 */         ((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 3));
/* 119:    */       }
/* 120:    */     }
/* 121:121 */     else if (this.rand.nextInt(15) == 1) {
/* 122:123 */       if ((entity instanceof EntityLiving)) {
/* 123:125 */         ((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 5));
/* 124:    */       }
/* 125:    */     }
/* 126:129 */     return super.attackEntityAsMob(entity);
/* 127:    */   }
/* 128:    */   
/* 129:    */   public boolean attackEntityFrom(DamageSource damagesource, float i)
/* 130:    */   {
/* 131:135 */     if (isInvisible())
/* 132:    */     {
/* 133:137 */       setInvisible(false);
/* 134:138 */       this.invisibleCD = (60 - this.worldObj.difficultySetting.ordinal() * 10);
/* 135:    */     }
/* 136:141 */     if (isDefending()) {
/* 137:143 */       if ((damagesource.isProjectile()) || (this.rand.nextInt(5) == 0)) {
/* 138:145 */         return false;
/* 139:    */       }
/* 140:    */     }
/* 141:149 */     return super.attackEntityFrom(damagesource, i);
/* 142:    */   }
/* 143:    */   
/* 144:    */   protected boolean castTeleport(Entity entity)
/* 145:    */   {
/* 146:154 */     double d = entity.posX + (this.rand.nextDouble() - 0.5D) * 8.0D;
/* 147:155 */     double d1 = entity.posY;
/* 148:156 */     double d2 = entity.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D;
/* 149:157 */     double d3 = this.posX;
/* 150:158 */     double d4 = this.posY;
/* 151:159 */     double d5 = this.posZ;
/* 152:160 */     this.posX = d;
/* 153:161 */     this.posY = d1;
/* 154:162 */     this.posZ = d2;
/* 155:163 */     boolean flag = false;
/* 156:164 */     int i = MathHelper.floor_double(this.posX);
/* 157:165 */     int j = MathHelper.floor_double(this.posY);
/* 158:166 */     int k = MathHelper.floor_double(this.posZ);
/* 159:168 */     if (this.worldObj.blockExists(i, j, k))
/* 160:    */     {

boolean flag1 = false;
/* 161:172 */       for (flag1 = false; (!flag1) && (j > 0);)
/* 162:    */       {
/* 163:174 */         Block i1 = this.worldObj.getBlock(i, j - 1, k);
/* 164:176 */         if ((i1 == Blocks.air) || (!i1.getMaterial().isSolid()))
/* 165:    */         {
/* 166:178 */           this.posY -= 1.0D;
/* 167:179 */           j--;
/* 168:    */         }
/* 169:    */         else
/* 170:    */         {
/* 171:183 */           flag1 = true;
/* 172:    */         }
/* 173:    */       }
/* 174:187 */       if (flag1)
/* 175:    */       {
/* 176:189 */         for (int n = 0; n < 10; n++) {
/* 177:191 */           this.worldObj.spawnParticle("largesmoke", d3 + this.rand.nextFloat() - 0.5D, d4, d5 + this.rand.nextFloat() - 0.5D, 1.0D, 1.0D, 1.0D);
/* 178:    */         }
/* 179:194 */         setPosition(this.posX, this.posY, this.posZ);
/* 180:196 */         if ((this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0) && (!this.worldObj.isAnyLiquid(this.boundingBox))) {
/* 181:198 */           flag = true;
/* 182:    */         }
/* 183:    */       }
/* 184:    */     }
/* 185:203 */     if (!flag)
/* 186:    */     {
/* 187:205 */       setPosition(d3, d4, d5);
/* 188:206 */       return false;
/* 189:    */     }
/* 190:209 */     int l = 128;
/* 191:    */     double d9;
/* 192:211 */     for (int j1 = 0; j1 < l; j1++)
/* 193:    */     {
/* 194:213 */       double d6 = j1 / (l - 1.0D);
/* 195:214 */       float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
/* 196:215 */       float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
/* 197:216 */       float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
/* 198:217 */       double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
/* 199:218 */       double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * this.height;
/* 200:219 */       d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
/* 201:    */     }
/* 202:222 */     this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
/* 203:223 */     this.worldObj.playSoundAtEntity(this, "mob.endermen.portal", 1.0F, 1.0F);
/* 204:224 */     return true;
/* 205:    */   }
/* 206:    */   
/* 207:    */   protected void dropFewItems(boolean flag, int i)
/* 208:    */   {
/* 209:229 */     super.dropFewItems(flag, i);
/* 210:230 */     if (getEquipmentInSlot(0) != null) {
/* 211:231 */       dropItem(getEquipmentInSlot(0).getItem(), 1);
/* 212:    */     }
/* 213:233 */     if ((flag) && ((this.rand.nextInt(5) == 0) || (this.rand.nextInt(1 + i) > 0))) {
/* 214:235 */       dropItem(Items.diamond, 2);
/* 215:    */     }
/* 216:    */   }
/* 217:    */   
/* 218:    */   protected boolean canDespawn()
/* 219:    */   {
/* 220:242 */     return false;
/* 221:    */   }
/* 222:    */   
/* 223:    */   protected String getLivingSound()
/* 224:    */   {
/* 225:247 */     return "mob.villager.default";
/* 226:    */   }
/* 227:    */   
/* 228:    */   protected String getHurtSound()
/* 229:    */   {
/* 230:252 */     return "mob.villager.defaulthurt";
/* 231:    */   }
/* 232:    */   
/* 233:    */   protected String getDeathSound()
/* 234:    */   {
/* 235:257 */     return "mob.villager.defaultdeath";
/* 236:    */   }
/* 237:    */   
/* 238:    */   public boolean isInvisible()
/* 239:    */   {
/* 240:262 */     return this.dataWatcher.getWatchableObjectByte(30) == 1;
/* 241:    */   }
/* 242:    */   
/* 243:    */   public void setInvisible(boolean invisible)
/* 244:    */   {
/* 245:267 */     this.dataWatcher.updateObject(30, Byte.valueOf((byte)(invisible ? 1 : 0)));
/* 246:    */   }
/* 247:    */   
/* 248:    */   protected void entityInit()
/* 249:    */   {
/* 250:272 */     super.entityInit();
/* 251:273 */     this.dataWatcher.addObject(30, Byte.valueOf((byte)0));
/* 252:    */   }
/* 253:    */   
/* 254:    */   public void setHealth(int health)
/* 255:    */   {
/* 256:277 */     this.dataWatcher.updateObject(15, Integer.valueOf(health));
/* 257:    */   }
/* 258:    */   
/* 259:    */   public boolean shouldRenderCape()
/* 260:    */   {
/* 261:283 */     return true;
/* 262:    */   }
/* 263:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.EntityPirateBoss

 * JD-Core Version:    0.7.1

 */
