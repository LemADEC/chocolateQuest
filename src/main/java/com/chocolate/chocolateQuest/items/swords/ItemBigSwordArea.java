/*  1:   */ package com.chocolate.chocolateQuest.items.swords;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;

/*  7:   */ import net.minecraft.entity.player.EntityPlayer;
/*  8:   */ import net.minecraft.item.EnumRarity;
/*  9:   */ import net.minecraft.item.Item.ToolMaterial;
/* 10:   */ import net.minecraft.item.ItemStack;
/* 11:   */ import net.minecraft.util.AxisAlignedBB;
/* 12:   */ import net.minecraft.util.DamageSource;
/* 13:   */ import net.minecraft.world.World;
/* 14:   */ 
/* 15:   */ public class ItemBigSwordArea
/* 16:   */   extends ItemBaseBroadSword
/* 17:   */ {
/* 18:16 */   final int weaponDamage = 8;
/* 19:   */   
/* 20:   */   public ItemBigSwordArea(Item.ToolMaterial mat, String texture, float baseDamage)
/* 21:   */   {
/* 22:19 */     super(mat, texture, baseDamage);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
/* 26:   */   {
/* 27:58 */     super.onUpdate(itemStack, world, entity, par4, par5);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1)
/* 31:   */   {
/* 32:64 */     float range = 1.5F;
/* 33:65 */     World world = entityliving.worldObj;
/* 34:66 */     double mx = entityliving.posX - range;
/* 35:67 */     double my = entityliving.posY - range;
/* 36:68 */     double mz = entityliving.posZ - range;
/* 37:69 */     double max = entityliving.posX + range;
/* 38:70 */     double may = entityliving.posY + range;
/* 39:71 */     double maz = entityliving.posZ + range;
/* 40:72 */     List<Entity> l = world.getEntitiesWithinAABBExcludingEntity(entityliving, AxisAlignedBB.getBoundingBox(mx, my, mz, max, may, maz));
/* 41:74 */     for (Entity e : l) {
/* 42:76 */       if (((e instanceof EntityLivingBase)) && (e != entityliving1))
/* 43:   */       {
/* 44:79 */         e.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)entityliving1), 4.0F);
/* 45:80 */         e.worldObj.spawnParticle("largeexplode", e.posX + itemRand.nextFloat() - 0.5D, e.posY + itemRand.nextFloat(), e.posZ + itemRand.nextFloat() - 0.5D, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F);
/* 46:   */       }
/* 47:   */     }
/* 48:84 */     entityliving.worldObj.spawnParticle("largeexplode", entityliving.posX + itemRand.nextFloat() - 0.5D, entityliving.posY + itemRand.nextFloat(), entityliving.posZ + itemRand.nextFloat() - 0.5D, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F);
/* 49:85 */     return super.hitEntity(itemstack, entityliving, entityliving1);
/* 50:   */   }
/* 51:   */   
/* 52:   */   public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
/* 53:   */   {
/* 54:91 */     return super.onItemRightClick(itemStack, world, entityPlayer);
/* 55:   */   }
/* 56:   */   
/* 57:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 58:   */   {
/* 59:96 */     return EnumRarity.epic;
/* 60:   */   }
/* 61:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemBigSwordArea

 * JD-Core Version:    0.7.1

 */
