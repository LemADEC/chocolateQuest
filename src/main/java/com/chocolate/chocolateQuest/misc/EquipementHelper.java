/*   1:    */ package com.chocolate.chocolateQuest.misc;
/*   2:    */
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   5:    */ import java.util.Random;
/*   6:    */ import net.minecraft.entity.EntityLivingBase;
/*   7:    */ import net.minecraft.init.Items;
/*   8:    */ import net.minecraft.item.ItemStack;
/*   9:    */
/*  10:    */ public class EquipementHelper
/*  11:    */ {
/*  12:    */   public static final int swordsman = 0;
/*  13:    */   public static final int defender = 1;
/*  14:    */   public static final int ninja = 2;
/*  15:    */   public static final int berserk = 3;
/*  16:    */   public static final int spearman = 4;
/*  17:    */   public static final int archer = 5;
/*  18:    */   public static final int gunner = 6;
/*  19:    */   public static final int healer = 7;
/*  20:    */   public static final int bannerman = 8;
/*  21:    */   public static final int music = 9;
/*  22:    */
/*  23:    */   public static void equipHumanRandomly(EntityHumanBase e, int lvl, int type)
/*  24:    */   {
/*  25: 18 */     equipEntity(e, lvl);
/*  26:    */     ItemStack is;
/*  28: 21 */     switch (type)
/*  29:    */     {
/*  30:    */     case 0:
/*  31: 24 */       e.setCurrentItemOrArmor(0, getSword(e.getRNG(), lvl));
/*  32: 25 */       e.setLeftHandItem(null);
/*  33: 26 */       break;
/*  34:    */     case 1:
/*  35: 28 */       e.setCurrentItemOrArmor(0, getSword(e.getRNG(), lvl));
/*  36: 29 */       e.setLeftHandItem(new ItemStack(ChocolateQuest.shield, 1, e.getTeamID()));
/*  37: 30 */       break;
/*  38:    */     case 2:
/*  40: 32 */       if (lvl < 4) {
/*  41: 33 */         is = new ItemStack(ChocolateQuest.ironDagger);
/*  42:    */       } else {
/*  43: 35 */         is = new ItemStack(ChocolateQuest.diamondDagger);
/*  44:    */       }
/*  45: 36 */       e.setCurrentItemOrArmor(0, is);
/*  46: 37 */       e.setLeftHandItem(null);
/*  47: 38 */       break;
/*  48:    */     case 3:
/*  49: 40 */       if (lvl < 4) {
/*  50: 41 */         is = new ItemStack(ChocolateQuest.ironBigsword);
/*  51:    */       } else {
/*  52: 43 */         is = new ItemStack(ChocolateQuest.diamondBigsword);
/*  53:    */       }
/*  54: 44 */       e.setCurrentItemOrArmor(0, is);
/*  55: 45 */       e.setLeftHandItem(null);
/*  56: 46 */       break;
/*  57:    */     case 4:
/*  59: 48 */       if (lvl < 4) {
/*  60: 49 */         is = new ItemStack(ChocolateQuest.ironSpear);
/*  61:    */       } else {
/*  62: 51 */         is = new ItemStack(ChocolateQuest.diamondSpear);
/*  63:    */       }
/*  64: 52 */       e.setCurrentItemOrArmor(0, is);
/*  65: 53 */       e.setLeftHandItem(null);
/*  66: 54 */       break;
/*  67:    */     case 5:
/*  68: 57 */       e.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
/*  69: 58 */       e.setLeftHandItem(null);
/*  70: 59 */       break;
/*  71:    */     case 6:
/*  72: 61 */       e.setCurrentItemOrArmor(0, getSword(e.getRNG(), lvl));
/*  73: 62 */       e.setLeftHandItem(new ItemStack(ChocolateQuest.revolver));
/*  74: 63 */       break;
/*  75:    */     case 7:
/*  76: 66 */       e.setCurrentItemOrArmor(0, new ItemStack(ChocolateQuest.staffHeal));
/*  77: 67 */       e.setLeftHandItem(null);
/*  78: 68 */       break;
/*  79:    */     case 8:
/*  80: 70 */       e.setCurrentItemOrArmor(0, new ItemStack(ChocolateQuest.banner, 1, e.getTeamID()));
/*  81: 71 */       e.setLeftHandItem(null);
/*  82: 72 */       break;
/*  83:    */     }
/*  84:    */   }
/*  85:    */
/*  86:    */   public static int getRandomType(EntityHumanBase e, int rareRatio)
/*  87:    */   {
/*  88: 82 */     Random random = e.getRNG();
/*  89: 83 */     if (random.nextInt(50 * rareRatio) == 0) {
/*  90: 84 */       return 8;
/*  91:    */     }
/*  92: 95 */     if (random.nextInt(4 * rareRatio) == 0) {
/*  93: 96 */       return 5;
/*  94:    */     }
/*  95: 98 */     if (random.nextInt(5 * rareRatio) == 0) {
/*  96: 99 */       return 3;
/*  97:    */     }
/*  98:101 */     if (random.nextInt(4 * rareRatio) == 0) {
/*  99:102 */       return 4;
/* 100:    */     }
/* 101:104 */     if (random.nextInt(4 * rareRatio) == 0) {
/* 102:105 */       return 2;
/* 103:    */     }
/* 104:107 */     if (random.nextInt(3 * rareRatio) == 0) {
/* 105:108 */       return 7;
/* 106:    */     }
/* 107:110 */     if (random.nextInt(2 * rareRatio) == 0) {
/* 108:111 */       return 1;
/* 109:    */     }
/* 110:113 */     return 0;
/* 111:    */   }
/* 112:    */
/* 113:    */   public static int getRandomLevel(EntityHumanBase e)
/* 114:    */   {
/* 115:116 */     Random random = e.getRNG();
/* 116:117 */     return random.nextInt(5);
/* 117:    */   }
/* 118:    */
/* 119:    */   public static void equipEntity(EntityLivingBase e, int lvl)
/* 120:    */   {
/* 121:121 */     if (((e instanceof EntityHumanBase)) && ((lvl == 4) || (lvl == 3)))
/* 122:    */     {
/* 123:122 */       if (lvl == 4) {
/* 124:123 */         for (int i = 1; i <= 4; i++) {
/* 125:124 */           e.setCurrentItemOrArmor(i, ((EntityHumanBase)e).getDiamondArmorForSlot(i));
/* 126:    */         }
/* 127:    */       }
/* 128:127 */       if (lvl == 3) {
/* 129:128 */         for (int i = 1; i <= 4; i++) {
/* 130:129 */           e.setCurrentItemOrArmor(i, ((EntityHumanBase)e).getIronArmorForSlot(i));
/* 131:    */         }
/* 132:    */       }
/* 133:    */     }
/* 134:    */     else
/* 135:    */     {
/* 136:133 */       for (int i = 1; i <= 4; i++) {
/* 137:134 */         e.setCurrentItemOrArmor(i, getArmor(e.getRNG(), i, lvl));
/* 138:    */       }
/* 139:    */     }
/* 140:137 */     e.setCurrentItemOrArmor(0, getSword(e.getRNG(), lvl));
/* 141:    */   }
/* 142:    */
/* 143:    */   public static ItemStack getSword(Random rand, int lvl)
/* 144:    */   {
/* 145:142 */     switch (lvl)
/* 146:    */     {
/* 147:    */     case 1:
/* 148:145 */       return new ItemStack(Items.stone_sword);
/* 149:    */     case 2:
/* 150:147 */       return new ItemStack(Items.golden_sword);
/* 151:    */     case 3:
/* 152:149 */       return new ItemStack(Items.iron_sword);
/* 153:    */     case 4:
/* 154:151 */       return new ItemStack(Items.diamond_sword);
/* 155:    */     }
/* 156:153 */     return new ItemStack(Items.wooden_sword);
/* 157:    */   }
/* 158:    */
/* 159:    */   public static ItemStack getArmor(Random rand, int stack, int lvl)
/* 160:    */   {
/* 161:157 */     if (1 == stack) {
/* 162:160 */       switch (lvl)
/* 163:    */       {
/* 164:    */       case 1:
/* 165:163 */         return new ItemStack(Items.chainmail_boots);
/* 166:    */       case 2:
/* 167:165 */         return new ItemStack(Items.golden_boots);
/* 168:    */       case 3:
/* 169:167 */         return new ItemStack(Items.iron_boots);
/* 170:    */       case 4:
/* 171:169 */         return new ItemStack(Items.diamond_boots);
/* 172:    */       case 0:
/* 173:171 */         return new ItemStack(Items.leather_boots);
/* 174:    */       }
/* 175:    */     }
/* 176:175 */     if (2 == stack) {
/* 177:178 */       switch (lvl)
/* 178:    */       {
/* 179:    */       case 1:
/* 180:181 */         return new ItemStack(Items.chainmail_leggings);
/* 181:    */       case 2:
/* 182:183 */         return new ItemStack(Items.golden_leggings);
/* 183:    */       case 3:
/* 184:185 */         return new ItemStack(Items.iron_leggings);
/* 185:    */       case 4:
/* 186:187 */         return new ItemStack(Items.diamond_leggings);
/* 187:    */       case 0:
/* 188:189 */         return new ItemStack(Items.leather_leggings);
/* 189:    */       }
/* 190:    */     }
/* 191:193 */     if (3 == stack) {
/* 192:196 */       switch (lvl)
/* 193:    */       {
/* 194:    */       case 1:
/* 195:199 */         return new ItemStack(Items.chainmail_chestplate);
/* 196:    */       case 2:
/* 197:201 */         return new ItemStack(Items.golden_chestplate);
/* 198:    */       case 3:
/* 199:203 */         return new ItemStack(Items.iron_chestplate);
/* 200:    */       case 4:
/* 201:205 */         return new ItemStack(Items.diamond_chestplate);
/* 202:    */       case 0:
/* 203:207 */         return new ItemStack(Items.leather_chestplate);
/* 204:    */       }
/* 205:    */     }
/* 206:211 */     if (4 == stack) {
/* 207:214 */       switch (lvl)
/* 208:    */       {
/* 209:    */       case 1:
/* 210:217 */         return new ItemStack(Items.chainmail_helmet);
/* 211:    */       case 2:
/* 212:219 */         return new ItemStack(Items.golden_helmet);
/* 213:    */       case 3:
/* 214:221 */         return new ItemStack(Items.iron_helmet);
/* 215:    */       case 4:
/* 216:223 */         return new ItemStack(Items.diamond_helmet);
/* 217:    */       case 0:
/* 218:225 */         return new ItemStack(Items.leather_helmet);
/* 219:    */       }
/* 220:    */     }
/* 221:229 */     return null;
/* 222:    */   }
/* 223:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.misc.EquipementHelper
 * JD-Core Version:    0.7.1
*/
