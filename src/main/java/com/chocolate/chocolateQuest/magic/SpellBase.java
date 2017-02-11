/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
/*  4:   */ import net.minecraft.item.ItemStack;
/*  5:   */ 
/*  6:   */ public class SpellBase
/*  7:   */ {
/*  8:   */   public String name;
/*  9:   */   
/* 10:   */   public void onUpdate(EntityLivingBase shooter, Elements element, ItemStack is, int angle) {}
/* 11:   */   
/* 12:   */   public boolean shouldUpdate()
/* 13:   */   {
/* 14:14 */     return false;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void onCastStart(EntityLivingBase shooter, Elements element, ItemStack is) {}
/* 18:   */   
/* 19:   */   public void onShoot(EntityLivingBase shooter, Elements element, ItemStack is, int chargeTime) {}
/* 20:   */   
/* 21:   */   public boolean isProjectile()
/* 22:   */   {
/* 23:24 */     return false;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void onEntityHit() {}
/* 27:   */   
/* 28:   */   public int getCastingTime()
/* 29:   */   {
/* 30:32 */     return 20;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public int getCoolDown()
/* 34:   */   {
/* 35:35 */     return 4;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public int getRange(ItemStack itemstack)
/* 39:   */   {
/* 40:38 */     return 16;
/* 41:   */   }
/* 42:   */   
/* 43:41 */   public static SpellBase[] spells = { new SpellProjectile().setName("spell_projectile"), new SpellProjectileAimed().setName("spell_tracker"), new SpellProjectileBoomerang().setName("spell_boomerang"), new SpellProjectileArea().setName("spell_area"), new SpellTeleport().setName("spell_teleport"), new SpellSpray().setName("spell_spray"), new SpellTornadoMini().setName("spell_tornado"), new SpellVampiric().setName("spell_vampiric"), new SpellProjectileShield().setName("spell_shield"), new SpellStorm().setName("spell_storm"), new SpellBeam().setName("spell_beam") };
/* 44:   */   
/* 45:   */   public static SpellBase getSpellByID(int id)
/* 46:   */   {
/* 47:57 */     return spells[id];
/* 48:   */   }
/* 49:   */   
/* 50:   */   public static String[] getNames()
/* 51:   */   {
/* 52:60 */     String[] names = new String[spells.length];
/* 53:61 */     for (int i = 0; i < names.length; i++) {
/* 54:62 */       names[i] = spells[i].name;
/* 55:   */     }
/* 56:63 */     return names;
/* 57:   */   }
/* 58:   */   
/* 59:   */   public SpellBase setName(String s)
/* 60:   */   {
/* 61:66 */     this.name = s;
/* 62:67 */     return this;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public int getExpansion(ItemStack itemStack)
/* 66:   */   {
/* 67:71 */     return Awakements.getEnchantLevel(itemStack, Awakements.spellExpansion);
/* 68:   */   }
/* 69:   */   
/* 70:   */   public int getDamage(ItemStack itemStack)
/* 71:   */   {
/* 72:74 */     return Awakements.getEnchantLevel(itemStack, Awakements.spellPower);
/* 73:   */   }
/* 74:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.SpellBase
 * JD-Core Version:    0.7.1
 */