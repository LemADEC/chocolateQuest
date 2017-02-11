/*  1:   */ package com.chocolate.chocolateQuest.items.swords;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  7:   */ import net.minecraft.entity.player.EntityPlayer;
/*  8:   */ import net.minecraft.item.EnumRarity;
/*  9:   */ import net.minecraft.item.Item.ToolMaterial;
/* 10:   */ import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;

/* 11:   */ import net.minecraft.potion.Potion;
/* 12:   */ import net.minecraft.potion.PotionEffect;
/* 13:   */ import net.minecraft.world.World;
/* 14:   */ 
/* 15:   */ public class ItemDaggerEnd
/* 16:   */   extends ItemBaseDagger
/* 17:   */ {
/* 18:   */   public ItemDaggerEnd()
/* 19:   */   {
/* 20:18 */     super(Item.ToolMaterial.EMERALD, 3);
/* 21:19 */     setMaxDamage(2048);
/* 22:   */   }
/* 23:   */   
/* 24:   */   @SideOnly(Side.CLIENT)
/* 25:   */   public void registerIcons(IIconRegister iconRegister)
/* 26:   */   {
/* 27:26 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:daggerNinja");
/* 28:   */   }
/* 29:   */   
/* 30:   */   public int getMaxDamage()
/* 31:   */   {
/* 32:32 */     return 2048;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
/* 36:   */   {
/* 37:38 */     if (entityPlayer.isSneaking())
/* 38:   */     {
/* 39:40 */       world.playSoundAtEntity(entityPlayer, "mob.endermen.portal", 1.0F, 1.0F);
/* 40:42 */       for (int i = 0; i < 6; i++) {
/* 41:44 */         world.spawnParticle("portal", entityPlayer.posX + itemRand.nextFloat() - 0.5D, entityPlayer.posY + itemRand.nextFloat() - 0.5D, entityPlayer.posZ + itemRand.nextFloat() - 0.5D, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F);
/* 42:   */       }
/* 43:47 */       double x = -Math.sin(Math.toRadians(entityPlayer.rotationYaw));
/* 44:48 */       double z = Math.cos(Math.toRadians(entityPlayer.rotationYaw));
/* 45:49 */       double y = -Math.sin(Math.toRadians(entityPlayer.rotationPitch));
/* 46:50 */       x *= (1.0D - Math.abs(y));
/* 47:51 */       z *= (1.0D - Math.abs(y));
/* 48:52 */       int dist = 4;
/* 49:53 */       entityPlayer.setPosition(entityPlayer.posX + x * dist, entityPlayer.posY + y * dist, entityPlayer.posZ + z * dist);
/* 50:54 */       itemStack.damageItem(1, entityPlayer);
/* 51:56 */       for (int i = 0; i < 6; i++) {
/* 52:58 */         world.spawnParticle("portal", entityPlayer.posX + itemRand.nextFloat() - 0.5D, entityPlayer.posY + itemRand.nextFloat() - 0.5D, entityPlayer.posZ + itemRand.nextFloat() - 0.5D, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F);
/* 53:   */       }
/* 54:60 */       itemStack.damageItem(1, entityPlayer);
/* 55:   */     }
/* 56:   */     else
/* 57:   */     {
/* 58:64 */       itemStack.damageItem(1, entityPlayer);
/* 59:   */     }
/* 60:66 */     entityPlayer.addPotionEffect(new PotionEffect(Potion.invisibility.id, 100, 5));
/* 61:67 */     return super.onItemRightClick(itemStack, world, entityPlayer);
/* 62:   */   }
/* 63:   */   
/* 64:   */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemMaterial)
/* 65:   */   {
/* 66:72 */     return super.getIsRepairable(itemToRepair, itemMaterial);
/* 67:   */   }
/* 68:   */   
/* 69:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 70:   */   {
/* 71:77 */     return EnumRarity.epic;
/* 72:   */   }
/* 73:   */   
/* 74:   */   public int getEntityLifespan(ItemStack itemStack, World world)
/* 75:   */   {
/* 76:82 */     return 24000;
/* 77:   */   }
/* 78:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemDaggerEnd

 * JD-Core Version:    0.7.1

 */
