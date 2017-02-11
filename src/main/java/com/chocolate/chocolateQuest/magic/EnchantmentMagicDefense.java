/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import net.minecraft.enchantment.Enchantment;
/*  4:   */ import net.minecraft.enchantment.EnchantmentProtection;
/*  5:   */ import net.minecraft.item.ItemArmor;
/*  6:   */ import net.minecraft.item.ItemStack;
/*  7:   */ import net.minecraft.util.DamageSource;
/*  8:   */ import net.minecraft.util.MathHelper;
/*  9:   */ import net.minecraft.util.StatCollector;
/* 10:   */ 
/* 11:   */ public class EnchantmentMagicDefense
/* 12:   */   extends EnchantmentProtection
/* 13:   */ {
/* 14:   */   public EnchantmentMagicDefense(int par1, int par2)
/* 15:   */   {
/* 16:14 */     super(par1, par2, 0);
/* 17:15 */     setName("magicResist");
/* 18:   */   }
/* 19:   */   
/* 20:   */   public int calcModifierDamage(int level, DamageSource ds)
/* 21:   */   {
/* 22:20 */     if (!ds.isMagicDamage()) {
/* 23:21 */       return 0;
/* 24:   */     }
/* 25:22 */     float TYPE_MODIFIER = 1.5F;
/* 26:23 */     if (level == 0) {
/* 27:24 */       return 0;
/* 28:   */     }
/* 29:25 */     float damageReduction = MathHelper.floor_float((6 + level * level) * TYPE_MODIFIER / 3.0F);
/* 30:26 */     return (int)damageReduction;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public boolean canApply(ItemStack stack)
/* 34:   */   {
/* 35:31 */     return ((stack.getItem() instanceof ItemArmor)) && (super.canApply(stack));
/* 36:   */   }
/* 37:   */   
/* 38:   */   public boolean canApplyAtEnchantingTable(ItemStack stack)
/* 39:   */   {
/* 40:36 */     return super.canApplyAtEnchantingTable(stack);
/* 41:   */   }
/* 42:   */   
/* 43:   */   public boolean canApplyTogether(Enchantment enchantment)
/* 44:   */   {
/* 45:41 */     return (enchantment != Enchantment.protection) && (enchantment != Enchantment.projectileProtection);
/* 46:   */   }
/* 47:   */   
/* 48:   */   public boolean isAllowedOnBooks()
/* 49:   */   {
/* 50:46 */     return true;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public String getTranslatedName(int par1)
/* 54:   */   {
/* 55:51 */     return StatCollector.translateToLocal("enchantment.magicDefense.name") + " " + StatCollector.translateToLocal(new StringBuilder().append("enchantment.level.").append(par1).toString());
/* 56:   */   }
/* 57:   */   
/* 58:   */   public int getMaxLevel()
/* 59:   */   {
/* 60:55 */     return 4;
/* 61:   */   }
/* 62:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.EnchantmentMagicDefense
 * JD-Core Version:    0.7.1
 */