/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.ClientProxy;
/*  4:   */ import com.google.common.collect.HashMultimap;
/*  5:   */ import com.google.common.collect.Multimap;
/*  6:   */ import cpw.mods.fml.relauncher.Side;
/*  7:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  8:   */ import java.util.List;
/*  9:   */ import net.minecraft.client.model.ModelBiped;
/* 10:   */ import net.minecraft.entity.Entity;
/* 11:   */ import net.minecraft.entity.EntityLivingBase;
/* 12:   */ import net.minecraft.entity.SharedMonsterAttributes;
/* 13:   */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/* 14:   */ import net.minecraft.entity.ai.attributes.IAttribute;
/* 15:   */ import net.minecraft.item.EnumRarity;
/* 16:   */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/* 17:   */ import net.minecraft.item.ItemStack;
/* 18:   */ import net.minecraft.util.AxisAlignedBB;
import net.minecraft.item.ItemArmor;
/* 19:   */ import net.minecraft.util.DamageSource;
/* 20:   */ import net.minecraft.util.EntityDamageSource;
/* 21:   */ import net.minecraft.world.World;
/* 22:   */ 
/* 23:   */ public class ItemArmorBull
/* 24:   */   extends ItemArmorBase
/* 25:   */ {
/* 26:   */   int type;
/* 27:   */   String name;
/* 28:   */   
/* 29:   */   public ItemArmorBull(int type, String name)
/* 30:   */   {
/* 31:29 */     super(ItemArmor.ArmorMaterial.DIAMOND, type);
/* 32:30 */     this.type = type;
/* 33:31 */     this.name = name;
/* 34:32 */     setMaxDamage(ItemArmor.ArmorMaterial.DIAMOND.getDurability(type) + 100);
/* 35:33 */     this.strength = new AttributeModifier("BullAmodifier" + type, 1.0D, 0);
/* 36:34 */     setEpic();
/* 37:   */   }
/* 38:   */   
/* 39:36 */   AttributeModifier strength = new AttributeModifier("Armor0", 2.0D, 0);
/* 40:   */   
/* 41:   */   public Multimap getItemAttributeModifiers()
/* 42:   */   {
/* 43:40 */     Multimap multimap = HashMultimap.create();
/* 44:41 */     multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), this.strength);
/* 45:42 */     return multimap;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/* 49:   */   {
/* 50:47 */     if (slot == 2) {
/* 51:49 */       return "chocolatequest:textures/entity/bull_2.png";
/* 52:   */     }
/* 53:52 */     return "chocolatequest:textures/entity/bull_1.png";
/* 54:   */   }
/* 55:   */   
/* 56:   */   @SideOnly(Side.CLIENT)
/* 57:   */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/* 58:   */   {
/* 59:58 */     if (armorSlot == 0) {
/* 60:59 */       return ClientProxy.bullHead;
/* 61:   */     }
/* 62:60 */     return null;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 66:   */   {
/* 67:65 */     return EnumRarity.epic;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public void onUpdateEquiped(ItemStack par1ItemStack, World world, EntityLivingBase entityliving)
/* 71:   */   {
/* 72:71 */     if ((this.armorType == 1) && (entityliving.isSprinting())) {
/* 73:73 */       if (isFullSet(entityliving, par1ItemStack))
/* 74:   */       {
/* 75:75 */         List list = world.getEntitiesWithinAABBExcludingEntity(entityliving, entityliving.boundingBox.expand(1.1D, 1.1D, 1.1D));
/* 76:76 */         for (int k2 = 0; k2 < list.size(); k2++)
/* 77:   */         {
/* 78:78 */           Entity entity = (Entity)list.get(k2);
/* 79:79 */           if ((entity instanceof EntityLivingBase)) {
/* 80:81 */             if (!entityliving.isOnSameTeam((EntityLivingBase)entity)) {
/* 81:82 */               entity.attackEntityFrom(new EntityDamageSource(DamageSource.generic.damageType, entityliving), 4.0F);
/* 82:   */             }
/* 83:   */           }
/* 84:   */         }
/* 85:   */       }
/* 86:   */     }
/* 87:   */   }
/* 88:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorBull

 * JD-Core Version:    0.7.1

 */
