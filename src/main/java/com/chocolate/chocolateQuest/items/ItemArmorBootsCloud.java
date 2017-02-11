/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import com.google.common.collect.HashMultimap;
/*  4:   */ import com.google.common.collect.Multimap;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.entity.EntityLivingBase;
/*  8:   */ import net.minecraft.entity.SharedMonsterAttributes;
/*  9:   */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/* 10:   */ import net.minecraft.entity.ai.attributes.IAttribute;
/* 11:   */ import net.minecraft.entity.player.EntityPlayer;
/* 12:   */ import net.minecraft.item.EnumRarity;
/* 13:   */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/* 14:   */ import net.minecraft.item.ItemStack;
/* 15:   */ import net.minecraft.util.DamageSource;
/* 16:   */ import net.minecraft.world.World;
/* 17:   */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraft.item.ItemArmor;

/* 18:   */ 
/* 19:   */ public class ItemArmorBootsCloud
/* 20:   */   extends ItemArmorBase
/* 21:   */ {
/* 22:   */   public ItemArmorBootsCloud()
/* 23:   */   {
/* 24:22 */     super(ItemArmor.ArmorMaterial.CLOTH, 3);
/* 25:23 */     setMaxDamage(450);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void onHit(LivingHurtEvent event, ItemStack is, EntityLivingBase entity)
/* 29:   */   {
/* 30:28 */     if (event.source == DamageSource.fall) {
/* 31:29 */       event.setCanceled(true);
/* 32:   */     }
/* 33:   */   }
/* 34:   */   
/* 35:   */   public ItemArmor.ArmorMaterial getArmorMaterial()
/* 36:   */   {
/* 37:35 */     return ItemArmor.ArmorMaterial.CLOTH;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public Multimap getItemAttributeModifiers()
/* 41:   */   {
/* 42:40 */     Multimap multimap = HashMultimap.create();
/* 43:41 */     multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Boots modifier", 0.15D, 2));
/* 44:42 */     return multimap;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void onUpdateEquiped(ItemStack par1ItemStack, World world, EntityLivingBase entity)
/* 48:   */   {
/* 49:48 */     boolean onGround = false;
/* 50:49 */     if (entity.fallDistance >= 3.0F)
/* 51:   */     {
/* 52:50 */       entity.fallDistance = 0.0F;
/* 53:51 */       if (world.isRemote) {
/* 54:53 */         for (int i = 0; i < 3; i++) {
/* 55:55 */           world.spawnParticle("cloud", entity.posX, entity.posY - 2.0D, entity.posZ, (itemRand.nextFloat() - 0.5F) / 2.0F, -0.5D, (itemRand.nextFloat() - 0.5F) / 2.0F);
/* 56:   */         }
/* 57:   */       }
/* 58:   */     }
/* 59:60 */     if ((entity.isSprinting()) && (world.isRemote)) {
/* 60:61 */       world.spawnParticle("cloud", entity.posX, entity.posY - 1.5D, entity.posZ, (itemRand.nextFloat() - 0.5F) / 2.0F, 0.1D, (itemRand.nextFloat() - 0.5F) / 2.0F);
/* 61:   */     }
/* 62:63 */     if (!entity.onGround) {
/* 63:65 */       if ((entity instanceof EntityPlayer)) {
/* 64:66 */         entity.jumpMovementFactor += 0.03F;
/* 65:   */       }
/* 66:   */     }
/* 67:68 */     if (entity.isCollidedHorizontally) {
/* 68:69 */       entity.stepHeight = 1.0F;
/* 69:   */     } else {
/* 70:71 */       entity.stepHeight = 0.5F;
/* 71:   */     }
/* 72:   */   }
/* 73:   */   
/* 74:   */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/* 75:   */   {
/* 76:77 */     return "chocolatequest:textures/entity/cloud_1.png";
/* 77:   */   }
/* 78:   */   
/* 79:   */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemMaterial)
/* 80:   */   {
/* 81:82 */     return false;
/* 82:   */   }
/* 83:   */   
/* 84:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 85:   */   {
/* 86:87 */     return EnumRarity.rare;
/* 87:   */   }
/* 88:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorBootsCloud

 * JD-Core Version:    0.7.1

 */
