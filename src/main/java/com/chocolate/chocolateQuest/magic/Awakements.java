/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ import net.minecraft.entity.item.EntityItem;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ import net.minecraft.nbt.NBTTagCompound;
/*  7:   */ import net.minecraft.util.StatCollector;
/*  8:   */ 
/*  9:   */ public class Awakements
/* 10:   */ {
/* 11:11 */   public int id = 0;
/* 12:12 */   private static int lastID = 0;
/* 13:14 */   public static Awakements property = new AwakementProperty("property", 0);
/* 14:15 */   public static Awakements autoRepair = new AwakementAutoRepair("autoRepair", 0);
/* 15:16 */   public static Awakements backStab = new AwakementDagger("backstab", 0);
/* 16:17 */   public static Awakements dodgeStamina = new AwakementDagger("dodge", 0);
/* 17:18 */   public static Awakements backDodge = new AwakementBigSword("backwardsJump", 0);
/* 18:19 */   public static Awakements berserk = new AwakementBigSword("berserk", 0);
/* 19:20 */   public static Awakements range = new AwakementSpear("range", 0);
/* 20:21 */   public static Awakements blockStamina = new AwakementSword("shieldBlock", 0);
/* 21:22 */   public static Awakements parryDamage = new AwakementSword("parryDamage", 0);
/* 22:23 */   public static Awakements ammoCapacity = new AwakementPistol("ammoCapacity", 0, 8);
/* 23:24 */   public static Awakements power = new AwakementPistol("power", 0);
/* 24:25 */   public static Awakements ammoSaver = new AwakementPistol("ammoSaver", 0);
/* 25:26 */   public static Awakements spellPower = new AwakementStaff("power", 0);
/* 26:27 */   public static Awakements spellExpansion = new AwakementStaff("spellExpansion", 0);
/* 27:   */   static final String NBT_NAME = "awk";
/* 28:   */   private final String name;
/* 29:   */   private final int icon;
/* 30:32 */   public static Awakements[] awekements = { property, autoRepair, backStab, dodgeStamina, backDodge, berserk, range, blockStamina, parryDamage, ammoCapacity, power, ammoSaver, spellPower, spellExpansion };
/* 31:   */   
/* 32:   */   public Awakements(String name, int icon)
/* 33:   */   {
/* 34:42 */     this.id = lastID;
/* 35:43 */     lastID += 1;
/* 36:44 */     this.name = name;
/* 37:45 */     this.icon = icon;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public static void addEnchant(ItemStack is, Awakements awekement, int lvl)
/* 41:   */   {
/* 42:49 */     if (is.stackTagCompound == null) {
/* 43:50 */       is.stackTagCompound = new NBTTagCompound();
/* 44:   */     }
/* 45:51 */     NBTTagCompound tag = is.stackTagCompound.getCompoundTag("awk");
/* 46:52 */     tag.setShort(awekement.id + "", (short)lvl);
/* 47:53 */     is.stackTagCompound.setTag("awk", tag);
/* 48:   */   }
/* 49:   */   
/* 50:   */   public static boolean hasEnchant(ItemStack is, Awakements awekement)
/* 51:   */   {
/* 52:56 */     if (is.stackTagCompound == null) {
/* 53:57 */       return false;
/* 54:   */     }
/* 55:58 */     NBTTagCompound tag = is.stackTagCompound.getCompoundTag("awk");
/* 56:59 */     short lvl = tag.getShort(awekement.id + "");
/* 57:60 */     return lvl > 0;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public static int getEnchantLevel(ItemStack is, Awakements awekement)
/* 61:   */   {
/* 62:63 */     if (is.stackTagCompound == null) {
/* 63:64 */       return 0;
/* 64:   */     }
/* 65:65 */     NBTTagCompound tag = is.stackTagCompound.getCompoundTag("awk");
/* 66:66 */     short lvl = tag.getShort(awekement.id + "");
/* 67:67 */     return lvl;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public float getValueModifier(ItemStack is)
/* 71:   */   {
/* 72:70 */     return 1.0F;
/* 73:   */   }
/* 74:   */   
/* 75:   */   public void onUpdate(Entity entity, ItemStack is) {}
/* 76:   */   
/* 77:   */   public String getDescription(ItemStack is)
/* 78:   */   {
/* 79:76 */     int lvl = getEnchantLevel(is, this);
/* 80:77 */     return StatCollector.translateToLocal(new StringBuilder().append("enchantment.").append(getName()).append(".name").toString()) + " " + StatCollector.translateToLocal(new StringBuilder().append("enchantment.level.").append(lvl).toString());
/* 81:   */   }
/* 82:   */   
/* 83:   */   public int getMaxLevel()
/* 84:   */   {
/* 85:80 */     return 4;
/* 86:   */   }
/* 87:   */   
/* 88:   */   public void onEntityItemUpdate(EntityItem entityItem) {}
/* 89:   */   
/* 90:   */   public String getName()
/* 91:   */   {
/* 92:88 */     return this.name;
/* 93:   */   }
/* 94:   */   
/* 95:   */   public int getIconIndex()
/* 96:   */   {
/* 97:91 */     return this.icon;
/* 98:   */   }
/* 99:   */   
/* :0:   */   public boolean canBeUsedOnItem(ItemStack is)
/* :1:   */   {
/* :2:95 */     return false;
/* :3:   */   }
/* :4:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.Awakements
 * JD-Core Version:    0.7.1
 */