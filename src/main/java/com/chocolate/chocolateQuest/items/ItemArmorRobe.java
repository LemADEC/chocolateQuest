/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.ClientProxy;
/*  4:   */ import com.chocolate.chocolateQuest.client.model.ModelArmor;
/*  5:   */ import cpw.mods.fml.relauncher.Side;
/*  6:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  7:   */ import net.minecraft.client.model.ModelBiped;
/*  8:   */ import net.minecraft.entity.Entity;
/*  9:   */ import net.minecraft.entity.EntityLivingBase;
/* 10:   */ import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
/* 11:   */ import net.minecraft.item.EnumRarity;
/* 12:   */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/* 13:   */ import net.minecraft.item.ItemStack;
/* 14:   */ import net.minecraft.nbt.NBTTagCompound;
/* 15:   */ import net.minecraft.util.FoodStats;
/* 16:   */ import net.minecraft.world.World;
/* 17:   */ 
/* 18:   */ public class ItemArmorRobe
/* 19:   */   extends ItemArmorBase
/* 20:   */ {
/* 21:   */   public ItemArmorRobe()
/* 22:   */   {
/* 23:22 */     super(ItemArmor.ArmorMaterial.CLOTH, 1);
/* 24:23 */     setMaxDamage(1850);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void onUpdateEquiped(ItemStack par1ItemStack, World world, EntityLivingBase entity)
/* 28:   */   {
/* 29:29 */     if ((entity instanceof EntityPlayer)) {
/* 30:31 */       if (entity.ticksExisted % 100 == 0)
/* 31:   */       {
/* 32:33 */         EntityPlayer e = (EntityPlayer)entity;
/* 33:34 */         e.getFoodStats().addStats(1, 6.0F);
/* 34:   */       }
/* 35:   */     }
/* 36:   */   }
/* 37:   */   
/* 38:   */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/* 39:   */   {
/* 40:42 */     return "chocolatequest:textures/entity/mageRobe.png";
/* 41:   */   }
/* 42:   */   
/* 43:   */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemMaterial)
/* 44:   */   {
/* 45:48 */     return super.getIsRepairable(itemToRepair, itemMaterial);
/* 46:   */   }
/* 47:   */   
/* 48:   */   @SideOnly(Side.CLIENT)
/* 49:   */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/* 50:   */   {
/* 51:54 */     return ClientProxy.mageArmor.setItem(itemStack);
/* 52:   */   }
/* 53:   */   
/* 54:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 55:   */   {
/* 56:59 */     return EnumRarity.uncommon;
/* 57:   */   }
/* 58:   */   
/* 59:   */   public int getColorFromItemStack(ItemStack is, int i)
/* 60:   */   {
/* 61:64 */     if ((is.stackTagCompound != null) && 
/* 62:65 */       (is.stackTagCompound.hasKey("display")))
/* 63:   */     {
/* 64:66 */       NBTTagCompound tag = (NBTTagCompound)is.stackTagCompound.getTag("display");
/* 65:67 */       if (tag.hasKey("color")) {
/* 66:68 */         return tag.getInteger("color");
/* 67:   */       }
/* 68:   */     }
/* 69:71 */     return 16777215;
/* 70:   */   }
/* 71:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorRobe

 * JD-Core Version:    0.7.1

 */
