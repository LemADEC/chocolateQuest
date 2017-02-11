/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.ClientProxy;
/*  4:   */ import com.google.common.collect.HashMultimap;
/*  5:   */ import com.google.common.collect.Multimap;
/*  6:   */ import cpw.mods.fml.relauncher.Side;
/*  7:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  8:   */ import net.minecraft.client.model.ModelBiped;
/*  9:   */ import net.minecraft.entity.EntityLivingBase;
/* 10:   */ import net.minecraft.entity.SharedMonsterAttributes;
/* 11:   */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/* 12:   */ import net.minecraft.entity.ai.attributes.IAttribute;
/* 13:   */ import net.minecraft.init.Items;
/* 14:   */ import net.minecraft.item.Item;
/* 15:   */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/* 16:   */ import net.minecraft.item.ItemStack;
/* 17:   */ import net.minecraft.world.World;
import net.minecraft.item.ItemArmor;

/* 18:   */ 
/* 19:   */ public class ItemArmorHeavy
/* 20:   */   extends ItemArmorBase
/* 21:   */ {
/* 22:   */   int type;
/* 23:22 */   Item repairItem = Items.diamond;
/* 24:   */   
/* 25:   */   public ItemArmorHeavy(int type, String name, Item repairItem)
/* 26:   */   {
/* 27:26 */     this(type, name, repairItem, ItemArmorBase.DRAGON);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public ItemArmorHeavy(int type, String name, Item repairItem, ItemArmor.ArmorMaterial mat)
/* 31:   */   {
/* 32:30 */     super(mat, type, name);
/* 33:31 */     this.type = type;
/* 34:32 */     this.repairItem = repairItem;
/* 35:33 */     setMaxDamage(mat.getDurability(type) + 1500);
/* 36:34 */     this.speed = new AttributeModifier("HA Speed modifier" + type, -0.06D, 2);
/* 37:35 */     this.knockBack = new AttributeModifier("HA Speed modifier", 0.25D, 0);
/* 38:   */   }
/* 39:   */   
/* 40:37 */   AttributeModifier speed = new AttributeModifier("HA Speed modifier", -0.07000000000000001D, 2);
/* 41:38 */   AttributeModifier knockBack = new AttributeModifier("HA Speed modifier", 0.25D, 2);
/* 42:   */   
/* 43:   */   public Multimap getItemAttributeModifiers()
/* 44:   */   {
/* 45:42 */     Multimap multimap = HashMultimap.create();
/* 46:43 */     multimap.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), this.knockBack);
/* 47:44 */     multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), this.speed);
/* 48:45 */     return multimap;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public void onUpdateEquiped(ItemStack par1ItemStack, World world, EntityLivingBase entity)
/* 52:   */   {
/* 53:51 */     if (!entity.onGround) {
/* 54:53 */       entity.jumpMovementFactor = ((float)Math.max(0.015D, entity.jumpMovementFactor - 0.01F));
/* 55:   */     }
/* 56:   */   }
/* 57:   */   
/* 58:   */   @SideOnly(Side.CLIENT)
/* 59:   */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/* 60:   */   {
/* 61:61 */     if (armorSlot == 1) {
/* 62:62 */       return ClientProxy.heavyArmor;
/* 63:   */     }
/* 64:63 */     return null;
/* 65:   */   }
/* 66:   */   
/* 67:   */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemMaterial)
/* 68:   */   {
/* 69:69 */     return (itemMaterial.getItem() == this.repairItem) || (super.getIsRepairable(itemToRepair, itemMaterial));
/* 70:   */   }
/* 71:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorHeavy

 * JD-Core Version:    0.7.1

 */
