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
import net.minecraft.item.ItemArmor;

/* 14:   */ import net.minecraft.item.EnumRarity;
/* 15:   */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/* 16:   */ import net.minecraft.item.ItemStack;
/* 17:   */ import net.minecraft.world.World;
/* 18:   */ 
/* 19:   */ public class ItemArmorKing
/* 20:   */   extends ItemArmorBase
/* 21:   */ {
/* 22:   */   public ItemArmorKing()
/* 23:   */   {
/* 24:24 */     super(ItemArmor.ArmorMaterial.DIAMOND, 1);
/* 25:25 */     setMaxDamage(8588);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public Multimap getItemAttributeModifiers()
/* 29:   */   {
/* 30:31 */     Multimap multimap = HashMultimap.create();
/* 31:32 */     multimap.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), new AttributeModifier("KingArmorModifier", 0.6D, 0));
/* 32:33 */     return multimap;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/* 36:   */   {
/* 37:39 */     return "chocolatequest:textures/entity/stone_1.png";
/* 38:   */   }
/* 39:   */   
/* 40:   */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemMaterial)
/* 41:   */   {
/* 42:45 */     return super.getIsRepairable(itemToRepair, itemMaterial);
/* 43:   */   }
/* 44:   */   
/* 45:   */   @SideOnly(Side.CLIENT)
/* 46:   */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/* 47:   */   {
/* 48:52 */     return ClientProxy.kingArmor;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 52:   */   {
/* 53:57 */     return EnumRarity.epic;
/* 54:   */   }
/* 55:   */   
/* 56:   */   public int getEntityLifespan(ItemStack itemStack, World world)
/* 57:   */   {
/* 58:62 */     return 24000;
/* 59:   */   }
/* 60:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorKing

 * JD-Core Version:    0.7.1

 */
