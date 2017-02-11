/*  1:   */ package com.chocolate.chocolateQuest.items.swords;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
/*  5:   */ import net.minecraft.item.EnumRarity;
/*  6:   */ import net.minecraft.item.Item.ToolMaterial;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class ItemSwordWalker
/* 11:   */   extends ItemBaseSwordDefensive
/* 12:   */ {
/* 13:   */   public ItemSwordWalker()
/* 14:   */   {
/* 15:13 */     super(Item.ToolMaterial.EMERALD, "swordEnd");
/* 16:14 */     setMaxDamage(2024);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
/* 20:   */   {
/* 21:19 */     if (entityPlayer.isSneaking())
/* 22:   */     {
/* 23:21 */       world.playSoundAtEntity(entityPlayer, "mob.endermen.portal", 1.0F, 1.0F);
/* 24:23 */       for (int i = 0; i < 6; i++) {
/* 25:25 */         world.spawnParticle("portal", entityPlayer.posX + itemRand.nextFloat() - 0.5D, entityPlayer.posY + itemRand.nextFloat() - 0.5D, entityPlayer.posZ + itemRand.nextFloat() - 0.5D, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F);
/* 26:   */       }
/* 27:28 */       double x = -Math.sin(Math.toRadians(entityPlayer.rotationYaw));
/* 28:29 */       double z = Math.cos(Math.toRadians(entityPlayer.rotationYaw));
/* 29:30 */       double y = -Math.sin(Math.toRadians(entityPlayer.rotationPitch));
/* 30:31 */       x *= (1.0D - Math.abs(y));
/* 31:32 */       z *= (1.0D - Math.abs(y));
/* 32:33 */       int dist = 4;
/* 33:34 */       entityPlayer.setPosition(entityPlayer.posX + x * dist, entityPlayer.posY + y * dist, entityPlayer.posZ + z * dist);
/* 34:35 */       itemStack.damageItem(1, entityPlayer);
/* 35:37 */       for (int i = 0; i < 6; i++) {
/* 36:39 */         world.spawnParticle("portal", entityPlayer.posX + itemRand.nextFloat() - 0.5D, entityPlayer.posY + itemRand.nextFloat() - 0.5D, entityPlayer.posZ + itemRand.nextFloat() - 0.5D, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F);
/* 37:   */       }
/* 38:   */     }
/* 39:43 */     return super.onItemRightClick(itemStack, world, entityPlayer);
/* 40:   */   }
/* 41:   */   
/* 42:   */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemMaterial)
/* 43:   */   {
/* 44:48 */     return super.getIsRepairable(itemToRepair, itemMaterial);
/* 45:   */   }
/* 46:   */   
/* 47:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 48:   */   {
/* 49:53 */     return EnumRarity.epic;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public int getEntityLifespan(ItemStack itemStack, World world)
/* 53:   */   {
/* 54:58 */     return 24000;
/* 55:   */   }
/* 56:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemSwordWalker

 * JD-Core Version:    0.7.1

 */
