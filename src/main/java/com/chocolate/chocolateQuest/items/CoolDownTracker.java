/*   1:    */ package com.chocolate.chocolateQuest.items;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.gui.InventoryBag;
/*   4:    */ import com.chocolate.chocolateQuest.magic.SpellBase;
/*   5:    */ import net.minecraft.item.ItemStack;
/*   6:    */ 
/*   7:    */ class CoolDownTracker
/*   8:    */ {
/*   9:    */   public SpellBase castingSpell;
/*  10:    */   SpellBase[] spells;
/*  11:    */   int[] cooldowns;
/*  12:    */   
/*  13:    */   public CoolDownTracker(ItemStack is)
/*  14:    */   {
/*  15:352 */     ItemStack[] ammo = InventoryBag.getCargo(is);
/*  16:353 */     this.spells = new SpellBase[ammo.length];
/*  17:354 */     this.cooldowns = new int[ammo.length];
/*  18:355 */     for (int i = 0; i < ammo.length; i++) {
/*  19:357 */       if (ammo[i] != null) {
/*  20:358 */         this.spells[i] = SpellBase.getSpellByID(ammo[i].getItemDamage());
/*  21:    */       }
/*  22:    */     }
/*  23:    */   }
/*  24:    */   
/*  25:    */   public void onUpdate()
/*  26:    */   {
/*  27:364 */     for (int i = 0; i < this.cooldowns.length; i++) {
/*  28:366 */       if (this.cooldowns[i] > 0) {
/*  29:367 */         this.cooldowns[i] -= 1;
/*  30:    */       }
/*  31:    */     }
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void increaseAllCooldowns(int ammount)
/*  35:    */   {
/*  36:371 */     for (int i = 0; i < this.cooldowns.length; i++) {
/*  37:373 */       this.cooldowns[i] += ammount;
/*  38:    */     }
/*  39:    */   }
/*  40:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.CoolDownTracker
 * JD-Core Version:    0.7.1
 */