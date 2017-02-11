/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.entity.EntityLivingBase;
/*  9:   */ import net.minecraft.item.EnumRarity;
/* 10:   */ import net.minecraft.item.Item;
/* 11:   */ import net.minecraft.item.ItemStack;
/* 12:   */ import net.minecraft.world.World;
/* 13:   */ 
/* 14:   */ public class ItemGoldenFeather
/* 15:   */   extends Item
/* 16:   */ {
/* 17:   */   public ItemGoldenFeather()
/* 18:   */   {
/* 19:18 */     setMaxStackSize(1);
/* 20:19 */     setMaxDamage(385);
/* 21:   */   }
/* 22:   */   
/* 23:   */   @SideOnly(Side.CLIENT)
/* 24:   */   public void registerIcons(IIconRegister iconRegister)
/* 25:   */   {
/* 26:25 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:goldenFeather");
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5)
/* 30:   */   {
/* 31:31 */     if (entity.fallDistance >= 3.0F)
/* 32:   */     {
/* 33:33 */       itemstack.damageItem(1, (EntityLivingBase)entity);
/* 34:34 */       entity.fallDistance = 0.0F;
/* 35:36 */       for (int i = 0; i < 3; i++) {
/* 36:38 */         entity.worldObj.spawnParticle("cloud", entity.posX, entity.posY - 2.0D, entity.posZ, (itemRand.nextFloat() - 0.5F) / 2.0F, -0.5D, (itemRand.nextFloat() - 0.5F) / 2.0F);
/* 37:   */       }
/* 38:   */     }
/* 39:42 */     super.onUpdate(itemstack, world, entity, par4, par5);
/* 40:   */   }
/* 41:   */   
/* 42:   */   public boolean isDamageable()
/* 43:   */   {
/* 44:48 */     return true;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public String getTextureFile()
/* 48:   */   {
/* 49:54 */     return "/bdimg/items.png";
/* 50:   */   }
/* 51:   */   
/* 52:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 53:   */   {
/* 54:59 */     return EnumRarity.uncommon;
/* 55:   */   }
/* 56:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemGoldenFeather
 * JD-Core Version:    0.7.1
 */