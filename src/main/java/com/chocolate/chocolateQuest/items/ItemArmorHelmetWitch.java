/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.ClientProxy;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  6:   */ import net.minecraft.client.model.ModelBiped;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.entity.EntityLivingBase;
/*  9:   */ import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
/* 10:   */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/* 11:   */ import net.minecraft.item.ItemStack;
/* 12:   */ 
/* 13:   */ public class ItemArmorHelmetWitch
/* 14:   */   extends ItemArmorBase
/* 15:   */ {
/* 16:   */   public ItemArmorHelmetWitch()
/* 17:   */   {
/* 18:18 */     super(ItemArmor.ArmorMaterial.CLOTH, 0);
/* 19:19 */     setMaxDamage(555);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/* 23:   */   {
/* 24:25 */     return "textures/entity/witch.png";
/* 25:   */   }
/* 26:   */   
/* 27:   */   @SideOnly(Side.CLIENT)
/* 28:   */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/* 29:   */   {
/* 30:32 */     return ClientProxy.witchHat;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 34:   */   {
/* 35:37 */     return EnumRarity.rare;
/* 36:   */   }
/* 37:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorHelmetWitch

 * JD-Core Version:    0.7.1

 */
