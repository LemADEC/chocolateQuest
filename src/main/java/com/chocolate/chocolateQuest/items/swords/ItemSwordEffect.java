/*  1:   */ package com.chocolate.chocolateQuest.items.swords;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;

/*  4:   */ import net.minecraft.item.Item.ToolMaterial;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ import net.minecraft.potion.Potion;
/*  7:   */ import net.minecraft.potion.PotionEffect;
/*  8:   */ 
/*  9:   */ public class ItemSwordEffect
/* 10:   */   extends ItemBaseSwordDefensive
/* 11:   */ {
/* 12:10 */   int potionEffect = Potion.weakness.id;
/* 13:   */   
/* 14:   */   public ItemSwordEffect(Item.ToolMaterial mat, String texture)
/* 15:   */   {
/* 16:12 */     super(mat, texture);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public ItemSwordEffect(Item.ToolMaterial mat, String texture, int effect)
/* 20:   */   {
/* 21:15 */     this(mat, texture);
/* 22:16 */     this.potionEffect = effect;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase target, EntityLivingBase entity)
/* 26:   */   {
/* 27:22 */     PotionEffect effect = new PotionEffect(this.potionEffect, 100, 0);
/* 28:23 */     target.addPotionEffect(effect);
/* 29:24 */     return super.hitEntity(par1ItemStack, target, entity);
/* 30:   */   }
/* 31:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemSwordEffect

 * JD-Core Version:    0.7.1

 */
