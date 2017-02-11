/*   1:    */ package com.chocolate.chocolateQuest.entity.mob;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.config.Configurations;
/*   5:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   6:    */ import com.chocolate.chocolateQuest.entity.ai.EntityParty;
/*   7:    */ import com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase;
/*   8:    */ import com.chocolate.chocolateQuest.items.mobControl.ItemMobToSpawner;
/*   9:    */ import java.util.Iterator;
/*  10:    */ import java.util.List;
/*  11:    */ import java.util.Random;
/*  12:    */ import net.minecraft.entity.Entity;
/*  13:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  14:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  15:    */ import net.minecraft.entity.item.EntityItem;
/*  16:    */ import net.minecraft.entity.monster.IMob;
/*  17:    */ import net.minecraft.entity.player.EntityPlayer;
/*  18:    */ import net.minecraft.item.ItemArmor;
/*  19:    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*  20:    */ import net.minecraft.item.ItemStack;
/*  21:    */ import net.minecraft.nbt.NBTTagCompound;
/*  22:    */ import net.minecraft.util.AxisAlignedBB;
/*  23:    */ import net.minecraft.util.ChunkCoordinates;
/*  24:    */ import net.minecraft.util.DamageSource;
/*  25:    */ import net.minecraft.util.MathHelper;
/*  26:    */ import net.minecraft.world.EnumDifficulty;
/*  27:    */ import net.minecraft.world.World;
/*  28:    */ 
/*  29:    */ public class EntityHumanMob
/*  30:    */   extends EntityHumanBase
/*  31:    */   implements IMob
/*  32:    */ {
/*  33:    */   DungeonMonstersBase monsterType;
/*  34:    */   
/*  35:    */   public EntityHumanMob(World world)
/*  36:    */   {
/*  37: 29 */     super(world);
/*  38: 30 */     updateEntityAttributes();
/*  39: 31 */     this.potionCount = this.rand.nextInt(3);
/*  40:    */   }
/*  41:    */   
/*  42:    */   public DungeonMonstersBase getMonsterType()
/*  43:    */   {
/*  44: 35 */     return null;
/*  45:    */   }
/*  46:    */   
/*  47:    */   protected void updateEntityAttributes()
/*  48:    */   {
/*  49: 40 */     this.monsterType = getMonsterType();
/*  50: 41 */     if (!isBoss())
/*  51:    */     {
/*  52: 43 */       getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(this.monsterType.getAttack());
/*  53: 44 */       getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(this.monsterType.getHealth());
/*  54: 45 */       setHealth((float)this.monsterType.getHealth());
/*  55: 46 */       getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(this.monsterType.getRange());
/*  56:    */     }
/*  57: 48 */     this.entityTeam = this.monsterType.getTeamName();
/*  58: 49 */     this.shieldID = this.monsterType.getFlagId();
/*  59:    */   }
/*  60:    */   
/*  61:    */   public boolean isBoss()
/*  62:    */   {
/*  63: 52 */     return false;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void readEntityFromSpawnerNBT(NBTTagCompound nbttagcompound, int spawnerX, int spawnerY, int spawnerZ)
/*  67:    */   {
/*  68: 56 */     super.readEntityFromSpawnerNBT(nbttagcompound, spawnerX, spawnerY, spawnerZ);
/*  69: 57 */     setHomeArea(spawnerX, spawnerY, spawnerZ, 30);
/*  70: 58 */     ItemStack rightHandItem = getEquipmentInSlot(0);
/*  71: 59 */     if (rightHandItem != null) {
/*  72: 61 */       if (rightHandItem.getItem() == ChocolateQuest.banner) {
/*  73: 62 */         rightHandItem.setItemDamage(getTeamID());
/*  74:    */       }
/*  75:    */     }
/*  76: 65 */     if (this.leftHandItem != null) {
/*  77: 67 */       if (this.leftHandItem.getItem() == ChocolateQuest.shield) {
/*  78: 68 */         this.leftHandItem.setItemDamage(getTeamID());
/*  79:    */       }
/*  80:    */     }
/*  81:    */   }
/*  82:    */   
/*  83:    */   protected boolean interact(EntityPlayer player)
/*  84:    */   {
/*  85: 75 */     if (isOnSameTeam(player)) {
/*  86: 76 */       return super.interact(player);
/*  87:    */     }
/*  88: 77 */     return false;
/*  89:    */   }
/*  90:    */   
/*  91:    */   public void onLivingUpdate()
/*  92:    */   {
/*  93: 82 */     if ((!this.worldObj.isRemote) && (
/*  94: 83 */       (this.party == null) || ((this.party != null) && (this.party.getLeader().equals(this)))))
/*  95:    */     {
/*  96: 85 */       EntityPlayer p = this.worldObj.getClosestPlayer(this.posX, this.posY, this.posZ, ChocolateQuest.config.distToDespawn);
/*  97: 86 */       if (p == null)
/*  98:    */       {
/* 100:    */         int x;
/* 101:    */         int y;
/* 102:    */         int z;
/* 103: 88 */         if (hasHome())
/* 104:    */         {
/* 105: 89 */           ChunkCoordinates coord = getHomePosition();
/* 106: 90 */           x = coord.posX;
/* 107: 91 */           y = coord.posY;
/* 108: 92 */           z = coord.posZ;
/* 109:    */         }
/* 110:    */         else
/* 111:    */         {
/* 112: 94 */           x = MathHelper.floor_double(this.posX);
/* 113: 95 */           y = MathHelper.floor_double(this.posY);
/* 114: 96 */           z = MathHelper.floor_double(this.posZ);
/* 115:    */         }
/* 116: 98 */         if (ItemMobToSpawner.saveToSpawner(x, y, z, this))
/* 117:    */         {
/* 118: 99 */           if (this.party != null) {
/* 119:100 */             for (int i = 0; i < this.party.getMembersLength(); i++)
/* 120:    */             {
/* 121:101 */               Entity e = this.party.getMember(i);
/* 122:102 */               if (e != null)
/* 123:    */               {
/* 124:103 */                 if (e.ridingEntity != null) {
/* 125:104 */                   e.ridingEntity.setDead();
/* 126:    */                 }
/* 127:105 */                 e.setDead();
/* 128:    */               }
/* 129:    */             }
/* 130:    */           }
/* 131:109 */           if (this.ridingEntity != null) {
/* 132:110 */             this.ridingEntity.setDead();
/* 133:    */           }
/* 134:111 */           setDead();
/* 135:    */         }
/* 136:    */       }
/* 137:    */     }
/* 138:116 */     super.onLivingUpdate();
/* 139:    */   }
/* 140:    */   
/* 141:    */   public void onSpawn()
/* 142:    */   {
/* 143:121 */     super.onSpawn();
/* 144:122 */     if (this.party == null)
/* 145:    */     {
/* 146:123 */       double targetDistance = 10.0D;
/* 147:124 */       List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(targetDistance, 1.2D, targetDistance));
/* 148:125 */       Iterator iterator = list.iterator();
/* 149:127 */       while (iterator.hasNext())
/* 150:    */       {
/* 151:129 */         Entity entity = (Entity)iterator.next();
/* 152:131 */         if ((entity instanceof EntityHumanBase))
/* 153:    */         {
/* 154:133 */           EntityHumanBase human = (EntityHumanBase)entity;
/* 155:134 */           if (isOnSameTeam(human))
/* 156:    */           {
/* 157:136 */             if (human.party == null) {
/* 158:138 */               human.tryPutIntoPArty(human);
/* 159:    */             }
/* 160:140 */             if (human.party.tryToAddNewMember(this, false)) {
/* 161:141 */               return;
/* 162:    */             }
/* 163:    */           }
/* 164:    */         }
/* 165:    */       }
/* 166:    */     }
/* 167:    */   }
/* 168:    */   
/* 169:    */   public boolean canSprint()
/* 170:    */   {
/* 171:149 */     return this.worldObj.difficultySetting.getDifficultyId() >= EnumDifficulty.HARD.getDifficultyId();
/* 172:    */   }
/* 173:    */   
/* 174:    */   public void onDeath(DamageSource damageSource)
/* 175:    */   {
/* 176:154 */     float healthValue = getMaxHealth() / 4.0F;
/* 177:155 */     float armorValue = 0.0F;
/* 178:156 */     for (int i = 0; i < 4; i++) {
/* 179:157 */       if (getEquipmentInSlot(1) != null) {
/* 180:158 */         armorValue += ((ItemArmor)getEquipmentInSlot(1).getItem()).getArmorMaterial().getDamageReductionAmount(2);
/* 181:    */       }
/* 182:    */     }
/* 183:160 */     armorValue /= 3.0F;
/* 184:161 */     this.experienceValue = ((int)(healthValue + armorValue));
/* 185:162 */     if (isCaptain()) {
/* 186:163 */       this.experienceValue += 1;
/* 187:    */     }
/* 188:165 */     if ((getLeftHandItem() != null) && (!this.worldObj.isRemote))
/* 189:    */     {
/* 190:166 */       ItemStack is = getLeftHandItem();
/* 191:167 */       EntityItem eItem = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, is);
/* 192:168 */       this.worldObj.spawnEntityInWorld(eItem);
/* 193:    */     }
/* 194:170 */     super.onDeath(damageSource);
/* 195:    */   }
/* 196:    */   
/* 197:    */   protected void dropFewItems(boolean par1, int par2)
/* 198:    */   {
/* 199:175 */     super.dropFewItems(par1, par2);
/* 200:    */   }
/* 201:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.EntityHumanMob

 * JD-Core Version:    0.7.1

 */
