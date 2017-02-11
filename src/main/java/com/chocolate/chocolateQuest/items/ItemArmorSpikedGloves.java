/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import com.google.common.collect.HashMultimap;
/*  4:   */ import com.google.common.collect.Multimap;
/*  5:   */ import cpw.mods.fml.relauncher.Side;
/*  6:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  7:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  8:   */ import net.minecraft.entity.Entity;
/*  9:   */ import net.minecraft.entity.EntityLivingBase;
/* 10:   */ import net.minecraft.entity.SharedMonsterAttributes;
/* 11:   */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/* 12:   */ import net.minecraft.entity.ai.attributes.IAttribute;
/* 13:   */ import net.minecraft.init.Items;
/* 14:   */ import net.minecraft.item.EnumRarity;
/* 15:   */ import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
/* 16:   */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/* 17:   */ import net.minecraft.item.ItemStack;
/* 18:   */ import net.minecraft.world.World;
/* 19:   */ 
/* 20:   */ public class ItemArmorSpikedGloves
/* 21:   */   extends ItemArmorBase
/* 22:   */ {
/* 23:   */   public ItemArmorSpikedGloves(int id)
/* 24:   */   {
/* 25:25 */     super(ItemArmor.ArmorMaterial.CLOTH, 1);
/* 26:26 */     setMaxDamage(450);
/* 27:   */   }
/* 28:   */   
/* 29:29 */   AttributeModifier strength = new AttributeModifier("ArmorStrenght1", 6.0D, 0);
/* 30:   */   
/* 31:   */   @SideOnly(Side.CLIENT)
/* 32:   */   public void registerIcons(IIconRegister iconRegister)
/* 33:   */   {
/* 34:34 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:spikedGloves");
/* 35:   */   }
/* 36:   */   
/* 37:   */   public Multimap getItemAttributeModifiers()
/* 38:   */   {
/* 39:40 */     Multimap multimap = HashMultimap.create();
/* 40:41 */     multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), this.strength);
/* 41:42 */     return multimap;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public void onUpdateEquiped(ItemStack par1ItemStack, World world, EntityLivingBase entity)
/* 45:   */   {
/* 46:47 */     boolean isCollidedVertically = false;
/* 47:48 */     if ((entity.isCollidedHorizontally) || (isCollidedVertically)) {
/* 48:50 */       if (!entity.isSneaking())
/* 49:   */       {
/* 50:52 */         entity.motionY = 0.0D;
/* 51:53 */         if (entity.moveForward > 0.0F)
/* 52:   */         {
/* 53:55 */           entity.motionY = 0.2D;
/* 54:56 */           if (!entity.isSwingInProgress) {
/* 55:57 */             entity.swingItem();
/* 56:   */           }
/* 57:   */         }
/* 58:59 */         entity.onGround = true;
/* 59:   */       }
/* 60:   */     }
/* 61:   */   }
/* 62:   */   
/* 63:   */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/* 64:   */   {
/* 65:67 */     return "chocolatequest:textures/entity/cloud_1.png";
/* 66:   */   }
/* 67:   */   
/* 68:   */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack item)
/* 69:   */   {
/* 70:72 */     Item itemMaterial = item.getItem();
/* 71:73 */     return (Items.diamond == itemMaterial) || (Items.iron_ingot == itemMaterial) || (super.getIsRepairable(itemToRepair, item));
/* 72:   */   }
/* 73:   */   
/* 74:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 75:   */   {
/* 76:78 */     return EnumRarity.rare;
/* 77:   */   }
/* 78:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorSpikedGloves

 * JD-Core Version:    0.7.1

 */
