/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  6:   */ import net.minecraft.entity.player.EntityPlayer;
/*  7:   */ import net.minecraft.entity.player.InventoryPlayer;
/*  8:   */ import net.minecraft.entity.player.PlayerCapabilities;
/*  9:   */ import net.minecraft.item.EnumAction;
/* 10:   */ import net.minecraft.item.Item;
/* 11:   */ import net.minecraft.item.ItemStack;
/* 12:   */ import net.minecraft.world.World;
/* 13:   */ 
/* 14:   */ public class ItemPotionHeal
/* 15:   */   extends Item
/* 16:   */ {
/* 17:   */   public ItemPotionHeal()
/* 18:   */   {
/* 19:17 */     setHasSubtypes(true);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
/* 23:   */   {
/* 24:22 */     player.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
/* 25:23 */     return par1ItemStack;
/* 26:   */   }
/* 27:   */   
/* 28:   */   @SideOnly(Side.CLIENT)
/* 29:   */   public void registerIcons(IIconRegister iconRegister)
/* 30:   */   {
/* 31:30 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:potion");
/* 32:   */   }
/* 33:   */   
/* 34:   */   public String getItemStackDisplayName(ItemStack itemstack)
/* 35:   */   {
/* 36:36 */     int i = itemstack.getItemDamage();
/* 37:37 */     String num = " ";
/* 38:38 */     switch (i)
/* 39:   */     {
/* 40:   */     case 1: 
/* 41:39 */       num = " I"; break;
/* 42:   */     case 2: 
/* 43:40 */       num = " II"; break;
/* 44:   */     case 3: 
/* 45:41 */       num = " III"; break;
/* 46:   */     case 4: 
/* 47:42 */       num = " IV"; break;
/* 48:   */     case 5: 
/* 49:43 */       num = " V";
/* 50:   */     }
/* 51:45 */     return super.getItemStackDisplayName(itemstack) + num;
/* 52:   */   }
/* 53:   */   
/* 54:   */   public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int i)
/* 55:   */   {
/* 56:50 */     int var6 = getMaxItemUseDuration(itemstack) - i;
/* 57:   */   }
/* 58:   */   
/* 59:   */   public int getMaxItemUseDuration(ItemStack itemStack)
/* 60:   */   {
/* 61:60 */     return itemStack.getItemDamage() * 10 + 25;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public EnumAction getItemUseAction(ItemStack itemstack)
/* 65:   */   {
/* 66:65 */     return EnumAction.drink;
/* 67:   */   }
/* 68:   */   
/* 69:   */   public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
/* 70:   */   {
/* 71:69 */     if ((entityplayer.getMaxHealth() > entityplayer.getHealth()) && (!entityplayer.capabilities.isCreativeMode))
/* 72:   */     {
/* 73:71 */       entityplayer.heal(4 + itemstack.getItemDamage() * 2);
/* 74:72 */       entityplayer.inventory.decrStackSize(entityplayer.inventory.currentItem, 1);
/* 75:   */     }
/* 76:74 */     return itemstack;
/* 77:   */   }
/* 78:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemPotionHeal
 * JD-Core Version:    0.7.1
 */