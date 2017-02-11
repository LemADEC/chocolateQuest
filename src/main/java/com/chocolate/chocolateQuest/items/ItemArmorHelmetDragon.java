/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.ClientProxy;
/*  4:   */ import com.google.common.collect.HashMultimap;
/*  5:   */ import com.google.common.collect.Multimap;
/*  6:   */ import cpw.mods.fml.relauncher.Side;
/*  7:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  8:   */ import net.minecraft.client.model.ModelBiped;
/*  9:   */ import net.minecraft.entity.Entity;
/* 10:   */ import net.minecraft.entity.EntityLivingBase;
/* 11:   */ import net.minecraft.entity.SharedMonsterAttributes;
/* 12:   */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/* 13:   */ import net.minecraft.entity.ai.attributes.IAttribute;
/* 14:   */ import net.minecraft.item.EnumRarity;
/* 15:   */ import net.minecraft.item.ItemStack;
/* 16:   */ 
/* 17:   */ public class ItemArmorHelmetDragon
/* 18:   */   extends ItemArmorBase
/* 19:   */ {
/* 20:   */   public ItemArmorHelmetDragon()
/* 21:   */   {
/* 22:23 */     super(ItemArmorBase.DRAGON, 0);
/* 23:24 */     setMaxDamage(2850);
/* 24:   */   }
/* 25:   */   
/* 26:27 */   AttributeModifier attackDamage = new AttributeModifier("ArmorDamage0", 1.0D, 1);
/* 27:28 */   AttributeModifier maxHealth = new AttributeModifier("ArmorHealth0", 10.0D, 0);
/* 28:   */   
/* 29:   */   public Multimap getItemAttributeModifiers()
/* 30:   */   {
/* 31:32 */     Multimap multimap = HashMultimap.create();
/* 32:33 */     multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), this.attackDamage);
/* 33:34 */     multimap.put(SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(), this.maxHealth);
/* 34:35 */     return multimap;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/* 38:   */   {
/* 39:41 */     return "chocolatequest:textures/entity/dragonbd.png";
/* 40:   */   }
/* 41:   */   
/* 42:   */   @SideOnly(Side.CLIENT)
/* 43:   */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/* 44:   */   {
/* 45:48 */     return ClientProxy.dragonHead;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 49:   */   {
/* 50:53 */     return EnumRarity.epic;
/* 51:   */   }
/* 52:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorHelmetDragon
 * JD-Core Version:    0.7.1
 */