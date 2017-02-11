/*   1:    */ package com.chocolate.chocolateQuest.utils;
/*   2:    */ 
/*   3:    */ import com.google.common.collect.Multimap;
/*   4:    */ import cpw.mods.fml.common.FMLLog;
/*   5:    */ import cpw.mods.fml.common.Loader;
/*   6:    */ import java.io.File;
/*   7:    */ import java.util.Properties;
/*   8:    */ import java.util.Random;
/*   9:    */ import net.minecraft.enchantment.Enchantment;
/*  10:    */ import net.minecraft.entity.Entity;
/*  11:    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*  12:    */ import net.minecraft.item.ItemArmor;
/*  13:    */ import net.minecraft.item.ItemStack;
/*  14:    */ import net.minecraft.item.ItemSword;
/*  15:    */ import net.minecraft.util.MathHelper;
/*  16:    */ import net.minecraft.util.ResourceLocation;
/*  17:    */ import org.apache.logging.log4j.Logger;
/*  18:    */ 
/*  19:    */ public class BDHelper
/*  20:    */ {
/*  21: 21 */   public static final ResourceLocation texture = new ResourceLocation("chocolateQuest:textures/entity/items.png");
/*  22: 22 */   public static final ResourceLocation guiButtonsTexture = new ResourceLocation("chocolateQuest:textures/entity/gui.png");
/*  23:    */   
/*  24:    */   public static ResourceLocation getItemTexture()
/*  25:    */   {
/*  26: 26 */     return texture;
/*  27:    */   }
/*  28:    */   
/*  29:    */   public static void println(String msg)
/*  30:    */   {
/*  31: 30 */     FMLLog.getLogger().info(msg);
/*  32:    */   }
/*  33:    */   
/*  34:    */   public static String getAppDir()
/*  35:    */   {
/*  36: 35 */     String s = Loader.instance().getConfigDir().getAbsolutePath();
/*  37:    */     
/*  38:    */ 
/*  39:    */ 
/*  40:    */ 
/*  41:    */ 
/*  42:    */ 
/*  43:    */ 
/*  44:    */ 
/*  45:    */ 
/*  46:    */ 
/*  47:    */ 
/*  48:    */ 
/*  49: 48 */     return s;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public static String getInfoDir()
/*  53:    */   {
/*  54: 53 */     return "/Chocolate/";
/*  55:    */   }
/*  56:    */   
/*  57:    */   public static boolean getBooleanProperty(Properties prop, String name, boolean defaultValue)
/*  58:    */   {
/*  59: 59 */     String s = prop.getProperty(name);
/*  60: 61 */     if (s == null) {
/*  61: 63 */       return defaultValue;
/*  62:    */     }
/*  63: 65 */     s = s.trim();
/*  64: 67 */     if (s.equals("true")) {
/*  65: 68 */       return true;
/*  66:    */     }
/*  67: 70 */     return false;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public static int getIntegerProperty(Properties prop, String name, int defaultValue)
/*  71:    */   {
/*  72: 74 */     String s = prop.getProperty(name);
/*  73: 76 */     if (s == null) {
/*  74: 78 */       return defaultValue;
/*  75:    */     }
/*  76: 81 */     int ret = defaultValue;
/*  77:    */     try
/*  78:    */     {
/*  79: 85 */       s = s.trim();
/*  80: 86 */       ret = Integer.parseInt(s);
/*  81:    */     }
/*  82:    */     finally {}
/*  83: 90 */     return ret;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public static int getIntegerProperty(Properties prop, String name, int defaultValue, int minValue, int maxValue)
/*  87:    */   {
/*  88: 94 */     int i = getIntegerProperty(prop, name, defaultValue);
/*  89: 95 */     i = Math.max(minValue, i);
/*  90: 96 */     i = Math.min(maxValue, i);
/*  91: 97 */     return i;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public static ItemStack EnchantItemRandomly(ItemStack itemstack, Random random)
/*  95:    */   {
/*  96:104 */     if ((itemstack.getItem() instanceof ItemArmor))
/*  97:    */     {
/*  98:106 */       ItemArmor armor = (ItemArmor)itemstack.getItem();
/*  99:108 */       if (armor.armorType == 0)
/* 100:    */       {
/* 101:111 */         int cant = 7;
/* 102:113 */         if (random.nextInt(cant) == 0) {
/* 103:115 */           itemstack.addEnchantment(Enchantment.protection, random.nextInt(4) + 1);
/* 104:    */         }
/* 105:118 */         if (random.nextInt(cant) == 0) {
/* 106:120 */           itemstack.addEnchantment(Enchantment.fireProtection, random.nextInt(4) + 1);
/* 107:    */         }
/* 108:123 */         if (random.nextInt(cant) == 0) {
/* 109:125 */           itemstack.addEnchantment(Enchantment.blastProtection, random.nextInt(4) + 1);
/* 110:    */         }
/* 111:128 */         if (random.nextInt(cant) == 0) {
/* 112:130 */           itemstack.addEnchantment(Enchantment.projectileProtection, random.nextInt(4) + 1);
/* 113:    */         }
/* 114:133 */         if (random.nextInt(cant) == 0) {
/* 115:135 */           itemstack.addEnchantment(Enchantment.respiration, random.nextInt(3) + 1);
/* 116:    */         }
/* 117:138 */         if (random.nextInt(cant) == 0) {
/* 118:140 */           itemstack.addEnchantment(Enchantment.aquaAffinity, 1);
/* 119:    */         }
/* 120:    */       }
/* 121:143 */       else if (armor.armorType == 3)
/* 122:    */       {
/* 123:146 */         int cant = 5;
/* 124:148 */         if (random.nextInt(cant) == 0) {
/* 125:150 */           itemstack.addEnchantment(Enchantment.protection, random.nextInt(4) + 1);
/* 126:    */         }
/* 127:153 */         if (random.nextInt(cant) == 0) {
/* 128:155 */           itemstack.addEnchantment(Enchantment.fireProtection, random.nextInt(4) + 1);
/* 129:    */         }
/* 130:158 */         if (random.nextInt(cant) == 0) {
/* 131:160 */           itemstack.addEnchantment(Enchantment.blastProtection, random.nextInt(4) + 1);
/* 132:    */         }
/* 133:163 */         if (random.nextInt(cant) == 0) {
/* 134:165 */           itemstack.addEnchantment(Enchantment.projectileProtection, random.nextInt(4) + 1);
/* 135:    */         }
/* 136:168 */         if (random.nextInt(cant) == 0) {
/* 137:170 */           itemstack.addEnchantment(Enchantment.featherFalling, random.nextInt(4) + 1);
/* 138:    */         }
/* 139:    */       }
/* 140:    */       else
/* 141:    */       {
/* 142:176 */         int cant = 6;
/* 143:178 */         if (random.nextInt(cant) == 0) {
/* 144:180 */           itemstack.addEnchantment(Enchantment.protection, random.nextInt(4) + 1);
/* 145:    */         }
/* 146:183 */         if (random.nextInt(cant) == 0) {
/* 147:185 */           itemstack.addEnchantment(Enchantment.fireProtection, random.nextInt(4) + 1);
/* 148:    */         }
/* 149:188 */         if (random.nextInt(cant) == 0) {
/* 150:190 */           itemstack.addEnchantment(Enchantment.blastProtection, random.nextInt(4) + 1);
/* 151:    */         }
/* 152:193 */         if (random.nextInt(cant) == 0) {
/* 153:195 */           itemstack.addEnchantment(Enchantment.projectileProtection, random.nextInt(4) + 1);
/* 154:    */         }
/* 155:198 */         if (random.nextInt(cant) == 0) {
/* 156:200 */           itemstack.addEnchantment(Enchantment.thorns, random.nextInt(3) + 1);
/* 157:    */         }
/* 158:    */       }
/* 159:    */     }
/* 160:204 */     else if ((itemstack.getItem() instanceof ItemSword))
/* 161:    */     {
/* 162:207 */       int cant = 7;
/* 163:209 */       if (random.nextInt(cant) == 0) {
/* 164:211 */         itemstack.addEnchantment(Enchantment.sharpness, random.nextInt(2) + 1);
/* 165:    */       }
/* 166:214 */       if (random.nextInt(cant) == 0) {
/* 167:216 */         itemstack.addEnchantment(Enchantment.fireAspect, random.nextInt(2) + 1);
/* 168:    */       }
/* 169:219 */       if (random.nextInt(cant) == 0) {
/* 170:221 */         itemstack.addEnchantment(Enchantment.smite, random.nextInt(5) + 1);
/* 171:    */       }
/* 172:224 */       if (random.nextInt(cant) == 0) {
/* 173:226 */         itemstack.addEnchantment(Enchantment.baneOfArthropods, random.nextInt(5) + 1);
/* 174:    */       }
/* 175:229 */       if (random.nextInt(cant) == 0) {
/* 176:231 */         itemstack.addEnchantment(Enchantment.knockback, 1);
/* 177:    */       }
/* 178:    */     }
/* 179:234 */     return itemstack;
/* 180:    */   }
/* 181:    */   
/* 182:    */   public static int getRandomIndex(int[] weights, Random random)
/* 183:    */   {
/* 184:239 */     int maxNum = 0;
/* 185:241 */     for (int i : weights) {
/* 186:243 */       maxNum += i;
/* 187:    */     }
/* 188:246 */     int randomNum = random.nextInt(weights.length);
/* 189:247 */     int index = 0;
/* 190:249 */     for (int weightSum = weights[0]; weightSum <= randomNum; weightSum += weights[index]) {
/* 191:251 */       index++;
/* 192:    */     }
/* 193:254 */     return index;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public static double getRotationDiffBetweenEntity(Entity entity, Entity target)
/* 197:    */   {
/* 198:259 */     double angle = entity.rotationYaw - target.rotationYaw;
/* 199:260 */     while (angle > 360.0D) {
/* 200:260 */       angle -= 360.0D;
/* 201:    */     }
/* 202:262 */     while (angle < 0.0D) {
/* 203:262 */       angle += 360.0D;
/* 204:    */     }
/* 205:264 */     angle = Math.abs(angle - 180.0D);
/* 206:    */     
/* 207:266 */     return angle;
/* 208:    */   }
/* 209:    */   
/* 210:    */   public static double getAngleBetweenEntities(Entity entity, Entity target)
/* 211:    */   {
/* 212:271 */     double d = entity.posX - target.posX;
/* 213:    */     
/* 214:273 */     double d2 = entity.posZ - target.posZ;
/* 215:274 */     double angle = Math.atan2(d, d2);
/* 216:275 */     angle = angle * 180.0D / 3.141592D;
/* 217:    */     
/* 218:277 */     angle = -MathHelper.wrapAngleTo180_double(angle - 180.0D);
/* 219:    */     
/* 220:279 */     return angle;
/* 221:    */   }
/* 222:    */   
/* 223:    */   public static String StringColor(String color)
/* 224:    */   {
/* 225:284 */     return "" + (char)167 + color;
/* 226:    */   }
/* 227:    */   
/* 228:    */   public static double getWeaponDamage(ItemStack weapon)
/* 229:    */   {
/* 230:288 */     double damage = 0.0D;
/* 231:289 */     Multimap map = weapon.getAttributeModifiers();
/* 232:290 */     if (map.containsKey("generic.attackDamage"))
/* 233:    */     {
/* 234:292 */       AttributeModifier a = (AttributeModifier)map.get("generic.attackDamage").toArray()[0];
/* 235:293 */       damage += (float)a.getAmount();
/* 236:    */     }
/* 237:295 */     return damage;
/* 238:    */   }
/* 239:    */   
/* 240:    */   public static float getColorRed(int color)
/* 241:    */   {
/* 242:301 */     return (color >> 16 & 0xFF) / 256.0F;
/* 243:    */   }
/* 244:    */   
/* 245:    */   public static float getColorGreen(int color)
/* 246:    */   {
/* 247:304 */     return (color >> 8 & 0xFF) / 256.0F;
/* 248:    */   }
/* 249:    */   
/* 250:    */   public static float getColorBlue(int color)
/* 251:    */   {
/* 252:307 */     return (color >> 0 & 0xFF) / 256.0F;
/* 253:    */   }
/* 254:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.utils.BDHelper
 * JD-Core Version:    0.7.1
 */