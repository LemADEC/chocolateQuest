/*   1:    */ package com.chocolate.chocolateQuest.entity.handHelper;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.ICooldownTracker;
/*   4:    */ import com.chocolate.chocolateQuest.API.IRangedWeapon;
/*   5:    */ import com.chocolate.chocolateQuest.API.ITwoHandedItem;
/*   6:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   7:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   8:    */ import com.chocolate.chocolateQuest.items.ItemHookShoot;
/*   9:    */ import com.chocolate.chocolateQuest.items.ItemStaffHeal;
/*  10:    */ import com.chocolate.chocolateQuest.items.swords.ItemBaseDagger;
/*  11:    */ import com.chocolate.chocolateQuest.items.swords.ItemBaseSpear;
/*  12:    */ import net.minecraft.block.Block;
/*  13:    */ import net.minecraft.entity.Entity;
/*  14:    */ import net.minecraft.init.Blocks;
/*  15:    */ import net.minecraft.init.Items;
/*  16:    */ import net.minecraft.item.Item;
/*  17:    */ import net.minecraft.item.ItemBlock;
/*  18:    */ import net.minecraft.item.ItemPotion;
/*  19:    */ import net.minecraft.item.ItemStack;
/*  20:    */ 
/*  21:    */ public class HandHelper
/*  22:    */ {
/*  23: 24 */   private boolean twoHanded = false;
/*  24: 24 */   private boolean extendedRange = false;
/*  25: 29 */   public int attackTime = 0;
/*  26:    */   ItemStack currentItem;
/*  27:    */   EntityHumanBase owner;
/*  28:    */   
/*  29:    */   public static HandHelper getHandHelperForItem(EntityHumanBase owner, ItemStack itemStack)
/*  30:    */   {
/*  31: 32 */     if (itemStack != null)
/*  32:    */     {
/*  33: 33 */       Item item = itemStack.getItem();
/*  34: 34 */       if (((item instanceof IRangedWeapon)) && ((item instanceof ICooldownTracker))) {
/*  35: 35 */         return new HandRangedAdvanced(owner, itemStack);
/*  36:    */       }
/*  37: 37 */       if ((item instanceof IRangedWeapon)) {
/*  38: 38 */         return new HandRanged(owner, itemStack);
/*  39:    */       }
/*  40: 40 */       if (item == ChocolateQuest.banner) {
/*  41: 41 */         return new HandSupport(owner, itemStack);
/*  42:    */       }
/*  43: 43 */       if (item == ChocolateQuest.shield) {
/*  44: 44 */         return new HandShield(owner, itemStack);
/*  45:    */       }
/*  46: 46 */       if (((item instanceof ItemHookShoot)) || (item == ChocolateQuest.hookSword)) {
/*  47: 47 */         return new HandHook(owner, itemStack);
/*  48:    */       }
/*  49: 49 */       if ((item instanceof ItemBaseDagger)) {
/*  50: 50 */         return new HandDagger(owner, itemStack);
/*  51:    */       }
/*  52: 52 */       if ((item instanceof ItemStaffHeal)) {
/*  53: 53 */         return new HandHealer(owner, itemStack);
/*  54:    */       }
/*  55: 55 */       if (item == Items.bow) {
/*  56: 56 */         return new HandBow(owner, itemStack);
/*  57:    */       }
/*  58: 58 */       if (item == Items.lead) {
/*  59: 59 */         return new HandLead(owner, itemStack);
/*  60:    */       }
/*  61: 61 */       if (item == Items.snowball) {
/*  62: 62 */         return new HandSnowBall(owner, itemStack);
/*  63:    */       }
/*  64: 64 */       if (item == Items.fire_charge) {
/*  65: 65 */         return new HandFireChange(owner, itemStack);
/*  66:    */       }
/*  67: 67 */       if (item == Items.flint_and_steel) {
/*  68: 68 */         return new HandFire(owner, itemStack);
/*  69:    */       }
/*  70: 70 */       if ((item instanceof ItemPotion)) {
/*  71: 71 */         return new HandPotion(owner, itemStack);
/*  72:    */       }
/*  73: 73 */       if (((item instanceof ItemBlock)) && 
/*  74: 74 */         (Block.getBlockFromItem(item) == Blocks.tnt)) {
/*  75: 75 */         return new HandTNT(owner, itemStack);
/*  76:    */       }
/*  77:    */     }
/*  78:    */     else
/*  79:    */     {
/*  80: 78 */       return new HandEmpty(owner, itemStack);
/*  81:    */     }
/*  82: 79 */     return new HandHelper(owner, itemStack);
/*  83:    */   }
/*  84:    */   
/*  85:    */   public HandHelper(EntityHumanBase owner, ItemStack itemStack)
/*  86:    */   {
/*  87: 83 */     this.owner = owner;
/*  88: 84 */     this.currentItem = itemStack;
/*  89: 85 */     if (itemStack != null)
/*  90:    */     {
/*  91: 86 */       Item item = itemStack.getItem();
/*  92: 87 */       if (((item instanceof ITwoHandedItem)) || (item == Items.bow)) {
/*  93: 88 */         this.twoHanded = true;
/*  94:    */       }
/*  95: 89 */       if ((item instanceof ItemBaseSpear)) {
/*  96: 90 */         this.extendedRange = true;
/*  97:    */       }
/*  98:    */     }
/*  99:    */   }
/* 100:    */   
/* 101:    */   public void attackEntity(Entity entity)
/* 102:    */   {
/* 103: 95 */     if (this.attackTime <= 0)
/* 104:    */     {
/* 105: 97 */       this.attackTime = this.owner.getAttackSpeed();
/* 106: 98 */       this.owner.swingHand(this);
/* 107: 99 */       this.owner.attackEntityAsMob(entity, this.currentItem);
/* 108:    */     }
/* 109:    */   }
/* 110:    */   
/* 111:    */   public boolean attackWithRange(Entity target, float f)
/* 112:    */   {
/* 113:104 */     return false;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public void setAiming(boolean aim) {}
/* 117:    */   
/* 118:    */   public boolean isAiming()
/* 119:    */   {
/* 120:110 */     return false;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public void onUpdate()
/* 124:    */   {
/* 125:114 */     if (this.attackTime > 0) {
/* 126:115 */       this.attackTime -= 1;
/* 127:    */     }
/* 128:    */   }
/* 129:    */   
/* 130:    */   public boolean isTwoHanded()
/* 131:    */   {
/* 132:119 */     return this.twoHanded;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public boolean isRanged()
/* 136:    */   {
/* 137:123 */     return false;
/* 138:    */   }
/* 139:    */   
/* 140:    */   public boolean canBlock()
/* 141:    */   {
/* 142:127 */     return false;
/* 143:    */   }
/* 144:    */   
/* 145:    */   public boolean isHealer()
/* 146:    */   {
/* 147:132 */     return false;
/* 148:    */   }
/* 149:    */   
/* 150:    */   public double getAttackRangeBonus()
/* 151:    */   {
/* 152:136 */     return this.extendedRange ? 1.0D : 0.0D;
/* 153:    */   }
/* 154:    */   
/* 155:    */   public double getMaxRangeForAttack()
/* 156:    */   {
/* 157:139 */     return this.owner.width * this.owner.width + getAttackRangeBonus();
/* 158:    */   }
/* 159:    */   
/* 160:    */   public double getDistanceToStopAdvancing()
/* 161:    */   {
/* 162:143 */     return getAttackRangeBonus();
/* 163:    */   }
/* 164:    */   
/* 165:    */   public ItemStack getItem()
/* 166:    */   {
/* 167:147 */     return this.currentItem;
/* 168:    */   }
/* 169:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandHelper
 * JD-Core Version:    0.7.1
 */