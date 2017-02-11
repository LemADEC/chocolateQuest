/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.model.ModelArmorMageRobe;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  6:   */ import net.minecraft.client.model.ModelBiped;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.entity.EntityLivingBase;
/*  9:   */ import net.minecraft.entity.player.EntityPlayer;
/* 10:   */ import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
/* 11:   */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/* 12:   */ import net.minecraft.item.ItemStack;
/* 13:   */ import net.minecraft.util.FoodStats;
/* 14:   */ import net.minecraft.world.World;
/* 15:   */ 
/* 16:   */ public class ItemArmorRobeLiche
/* 17:   */   extends ItemArmorBase
/* 18:   */ {
/* 19:   */   public ItemArmorRobeLiche()
/* 20:   */   {
/* 21:21 */     super(ItemArmor.ArmorMaterial.GOLD, 1);
/* 22:22 */     setMaxDamage(950);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void onUpdateEquiped(ItemStack par1ItemStack, World world, EntityLivingBase entity)
/* 26:   */   {
/* 27:28 */     if ((entity instanceof EntityPlayer)) {
/* 28:30 */       if (entity.ticksExisted % 50 == 0)
/* 29:   */       {
/* 30:32 */         EntityPlayer e = (EntityPlayer)entity;
/* 31:33 */         e.getFoodStats().addStats(1, 6.0F);
/* 32:   */       }
/* 33:   */     }
/* 34:   */   }
/* 35:   */   
/* 36:   */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/* 37:   */   {
/* 38:41 */     return "chocolatequest:textures/entity/licheRobe.png";
/* 39:   */   }
/* 40:   */   
/* 41:   */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemMaterial)
/* 42:   */   {
/* 43:47 */     return super.getIsRepairable(itemToRepair, itemMaterial);
/* 44:   */   }
/* 45:   */   
/* 46:   */   @SideOnly(Side.CLIENT)
/* 47:   */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/* 48:   */   {
/* 49:53 */     return new ModelArmorMageRobe();
/* 50:   */   }
/* 51:   */   
/* 52:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 53:   */   {
/* 54:58 */     return EnumRarity.rare;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public int getEntityLifespan(ItemStack itemStack, World world)
/* 58:   */   {
/* 59:63 */     return 24000;
/* 60:   */   }
/* 61:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorRobeLiche

 * JD-Core Version:    0.7.1

 */
