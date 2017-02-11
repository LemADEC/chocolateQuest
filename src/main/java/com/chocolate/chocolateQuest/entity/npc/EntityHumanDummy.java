/*   1:    */ package com.chocolate.chocolateQuest.entity.npc;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import com.chocolate.chocolateQuest.entity.ai.AIControlledFollowOwner;
/*   5:    */ import com.chocolate.chocolateQuest.entity.ai.AIControlledFormation;
/*   6:    */ import com.chocolate.chocolateQuest.entity.ai.AIControlledPath;
/*   7:    */ import com.chocolate.chocolateQuest.entity.ai.AIControlledSit;
/*   8:    */ import com.chocolate.chocolateQuest.entity.ai.AIControlledWardPosition;
/*   9:    */ import com.chocolate.chocolateQuest.entity.ai.AIHumanGoToPoint;
/*  10:    */ import com.chocolate.chocolateQuest.entity.ai.AIHumanMount;
/*  11:    */ import com.chocolate.chocolateQuest.entity.ai.EnumAiState;
/*  12:    */ import com.chocolate.chocolateQuest.entity.ai.HumanSelector;
/*  13:    */ import com.chocolate.chocolateQuest.entity.handHelper.HandHelper;
/*  14:    */ import com.chocolate.chocolateQuest.packets.PacketBase;
/*  15:    */ import com.chocolate.chocolateQuest.packets.PacketUpdateHumanDummyData;
/*  16:    */ import java.util.List;
/*  17:    */ import net.minecraft.entity.Entity;
/*  18:    */ import net.minecraft.entity.EntityLivingBase;
/*  19:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  20:    */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*  21:    */ import net.minecraft.entity.ai.EntityAISwimming;
/*  22:    */ import net.minecraft.entity.ai.EntityAITasks;
/*  23:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  24:    */ import net.minecraft.entity.player.EntityPlayer;
/*  25:    */ import net.minecraft.init.Items;
/*  26:    */ import net.minecraft.item.ItemStack;
/*  27:    */ import net.minecraft.potion.PotionEffect;
/*  28:    */ import net.minecraft.potion.PotionHelper;
/*  29:    */ import net.minecraft.util.DamageSource;
/*  30:    */ import net.minecraft.world.World;
/*  31:    */ 
/*  32:    */ public class EntityHumanDummy
/*  33:    */   extends EntityHumanBase
/*  34:    */ {
/*  35:    */   public EntityHumanDummy(World world)
/*  36:    */   {
/*  37: 34 */     super(world);
/*  38:    */   }
/*  39:    */   
/*  40:    */   protected void applyEntityAttributes()
/*  41:    */   {
/*  42: 40 */     super.applyEntityAttributes();
/*  43: 41 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
/*  44: 42 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
/*  45: 43 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0D);
/*  46: 44 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
/*  47:    */   }
/*  48:    */   
/*  49:    */   public boolean isSuitableMount(Entity entity)
/*  50:    */   {
/*  51: 48 */     if ((entity instanceof EntityHumanBase)) {
/*  52: 49 */       return entity instanceof EntityGolemMecha;
/*  53:    */     }
/*  54: 50 */     return !(entity instanceof EntityPlayer);
/*  55:    */   }
/*  56:    */   
/*  57:    */   protected boolean isAIEnabled()
/*  58:    */   {
/*  59: 56 */     return true;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public boolean isAiming()
/*  63:    */   {
/*  64: 61 */     return getAnimFlag(5);
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void setAiming(boolean aiming)
/*  68:    */   {
/*  69: 64 */     setAnimFlag(5, aiming);
/*  70:    */   }
/*  71:    */   
/*  72:    */   public boolean isEating()
/*  73:    */   {
/*  74: 67 */     return false;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void setEating(boolean flag) {}
/*  78:    */   
/*  79:    */   public boolean canSee(EntityLivingBase entity)
/*  80:    */   {
/*  81: 74 */     if (super.canSee(entity)) {
/*  82: 75 */       setAiming(true);
/*  83:    */     } else {
/*  84: 77 */       setAiming(false);
/*  85:    */     }
/*  86: 78 */     return false;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void onLivingUpdate()
/*  90:    */   {
/*  91: 83 */     super.onLivingUpdate();
/*  92:    */   }
/*  93:    */   
/*  94:    */   public void addPotionEffect(PotionEffect potion)
/*  95:    */   {
/*  96: 88 */     super.addPotionEffect(new PotionEffect(potion.getPotionID(), Integer.MAX_VALUE, potion.getAmplifier()));
/*  97:    */   }
/*  98:    */   
/*  99:    */   protected void addAITasks()
/* 100:    */   {
/* 101: 93 */     this.tasks.addTask(0, new EntityAISwimming(this));
/* 102: 94 */     this.tasks.addTask(2, new AIHumanGoToPoint(this));
/* 103: 95 */     this.tasks.addTask(1, new AIHumanMount(this, 1.0F, false));
/* 104:    */     
/* 105: 97 */     this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, new HumanSelector(this)));
/* 106: 98 */     setAIForCurrentMode();
/* 107:    */   }
/* 108:    */   
/* 109:    */   public void setAIForCurrentMode()
/* 110:    */   {
/* 111:101 */     if (this.controlledAI != null) {
/* 112:102 */       this.tasks.removeTask(this.controlledAI);
/* 113:    */     }
/* 114:104 */     int priority = 4;
/* 115:105 */     if (this.AIMode == EnumAiState.FOLLOW.ordinal())
/* 116:    */     {
/* 117:106 */       this.controlledAI = new AIControlledFollowOwner(this, 8.0F, 50.0F);
/* 118:    */     }
/* 119:107 */     else if (this.AIMode == EnumAiState.FORMATION.ordinal())
/* 120:    */     {
/* 121:108 */       this.controlledAI = new AIControlledFormation(this);
/* 122:    */     }
/* 123:109 */     else if (this.AIMode == EnumAiState.PATH.ordinal())
/* 124:    */     {
/* 125:110 */       this.controlledAI = new AIControlledPath(this);
/* 126:    */     }
/* 127:111 */     else if (this.AIMode == EnumAiState.WARD.ordinal())
/* 128:    */     {
/* 129:112 */       this.controlledAI = new AIControlledWardPosition(this);
/* 130:    */     }
/* 131:113 */     else if (this.AIMode == EnumAiState.SIT.ordinal())
/* 132:    */     {
/* 133:114 */       this.controlledAI = new AIControlledSit(this);
/* 134:115 */       priority = 2;
/* 135:    */     }
/* 136:117 */     if (this.controlledAI != null) {
/* 137:118 */       this.tasks.addTask(priority, this.controlledAI);
/* 138:    */     }
/* 139:    */   }
/* 140:    */   
/* 141:    */   protected boolean interact(EntityPlayer player)
/* 142:    */   {
/* 143:124 */     ItemStack is = player.getCurrentEquippedItem();
/* 144:125 */     if ((is != null) && (!this.worldObj.isRemote) && 
/* 145:126 */       (is.getItem() == Items.potionitem))
/* 146:    */     {
/* 147:127 */       List<PotionEffect> list = PotionHelper.getPotionEffects(is.getItemDamage(), true);
/* 148:128 */       if (list != null) {
/* 149:129 */         for (PotionEffect potion : list) {
/* 150:130 */           addPotionEffect(potion);
/* 151:    */         }
/* 152:    */       }
/* 153:133 */       return true;
/* 154:    */     }
/* 155:136 */     this.addedToParty = true;
/* 156:137 */     return super.interact(player);
/* 157:    */   }
/* 158:    */   
/* 159:    */   public PacketBase getEntityGUIUpdatePacket()
/* 160:    */   {
/* 161:141 */     return new PacketUpdateHumanDummyData(this);
/* 162:    */   }
/* 163:    */   
/* 164:    */   public ItemStack getHeldItem()
/* 165:    */   {
/* 166:147 */     if (this.rightHand != null) {
/* 167:148 */       return this.rightHand.getItem();
/* 168:    */     }
/* 169:149 */     return super.getHeldItem();
/* 170:    */   }
/* 171:    */   
/* 172:    */   public boolean attackEntityFrom(DamageSource damagesource, float damage)
/* 173:    */   {
/* 174:154 */     if (damagesource.damageType == DamageSource.inWall.damageType) {
/* 175:155 */       damage = 0.1F;
/* 176:    */     }
/* 177:156 */     return super.attackEntityFrom(damagesource, damage);
/* 178:    */   }
/* 179:    */   
/* 180:    */   protected void fall(float par1) {}
/* 181:    */   
/* 182:    */   public float getEquipmentDropChances(int i)
/* 183:    */   {
/* 184:164 */     return this.equipmentDropChances[i];
/* 185:    */   }
/* 186:    */   
/* 187:    */   public void setEquipmentDropChances(int i, float value)
/* 188:    */   {
/* 189:167 */     this.equipmentDropChances[i] = value;
/* 190:    */   }
/* 191:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.npc.EntityHumanDummy
 * JD-Core Version:    0.7.1
 */