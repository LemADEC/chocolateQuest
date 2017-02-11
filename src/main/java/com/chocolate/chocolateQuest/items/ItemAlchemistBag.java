/*   1:    */ package com.chocolate.chocolateQuest.items;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.gui.InventoryBag;
/*   5:    */ import com.chocolate.chocolateQuest.items.gun.ILoadableGun;
/*   6:    */ import cpw.mods.fml.relauncher.Side;
/*   7:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   8:    */ import java.util.List;
/*   9:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  10:    */ import net.minecraft.entity.Entity;
/*  11:    */ import net.minecraft.entity.EntityLiving;
/*  12:    */ import net.minecraft.entity.player.EntityPlayer;
/*  13:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*  14:    */ import net.minecraft.entity.projectile.EntityPotion;
/*  15:    */ import net.minecraft.init.Items;
/*  16:    */ import net.minecraft.item.EnumAction;
/*  17:    */ import net.minecraft.item.Item;
/*  18:    */ import net.minecraft.item.ItemPotion;
/*  19:    */ import net.minecraft.item.ItemStack;
/*  20:    */ import net.minecraft.potion.PotionEffect;
/*  21:    */ import net.minecraft.potion.PotionHelper;
/*  22:    */ import net.minecraft.world.World;
/*  23:    */ 
/*  24:    */ public class ItemAlchemistBag
/*  25:    */   extends Item
/*  26:    */   implements ILoadableGun
/*  27:    */ {
/*  28:    */   public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player)
/*  29:    */   {
/*  30: 29 */     ItemStack[] cargo = InventoryBag.getCargo(itemstack);
/*  31: 30 */     int itemPos = 0;
/*  32: 31 */     for (int i = 0; i < cargo.length; i++) {
/*  33: 32 */       if (cargo[i] != null)
/*  34:    */       {
/*  35: 33 */         itemPos = i;
/*  36: 34 */         break;
/*  37:    */       }
/*  38:    */     }
/*  39: 37 */     ItemStack potionStack = cargo[itemPos];
/*  40: 38 */     if (potionStack != null)
/*  41:    */     {
/*  42: 40 */       ItemPotion potion = (ItemPotion)potionStack.getItem();
/*  43: 41 */       if (!ItemPotion.isSplash(potionStack.getItemDamage()))
/*  44:    */       {
/*  45: 42 */         potion.onEaten(potionStack, world, player);
/*  46: 43 */         if (!player.capabilities.isCreativeMode)
/*  47:    */         {
/*  48: 45 */           potionStack.stackSize -= 1;
/*  49: 46 */           if (potionStack.stackSize <= 0) {
/*  50: 47 */             cargo[itemPos] = null;
/*  51:    */           }
/*  52: 48 */           InventoryBag.saveCargo(itemstack, cargo);
/*  53:    */         }
/*  54:    */       }
/*  55:    */     }
/*  56: 52 */     return itemstack;
/*  57:    */   }
/*  58:    */   
/*  59:    */   @SideOnly(Side.CLIENT)
/*  60:    */   public void registerIcons(IIconRegister iconRegister)
/*  61:    */   {
/*  62: 58 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:bag");
/*  63:    */   }
/*  64:    */   
/*  65:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  66:    */   {
/*  67: 63 */     if (entityPlayer.isSneaking())
/*  68:    */     {
/*  69: 65 */       entityPlayer.openGui(ChocolateQuest.instance, 3, entityPlayer.worldObj, 0, 0, 0);
/*  70: 66 */       return itemstack;
/*  71:    */     }
/*  72: 68 */     ItemStack[] cargo = InventoryBag.getCargo(itemstack);
/*  73: 69 */     int itemPos = 0;
/*  74: 70 */     for (int i = 0; i < cargo.length; i++) {
/*  75: 71 */       if (cargo[i] != null)
/*  76:    */       {
/*  77: 72 */         itemPos = i;
/*  78: 73 */         break;
/*  79:    */       }
/*  80:    */     }
/*  81: 76 */     ItemStack potionStack = cargo[itemPos];
/*  82: 77 */     if (potionStack != null)
/*  83:    */     {
/*  84: 79 */       ItemPotion potion = (ItemPotion)potionStack.getItem();
/*  85: 80 */       if (ItemPotion.isSplash(potionStack.getItemDamage()))
/*  86:    */       {
/*  87: 81 */         if (!world.isRemote)
/*  88:    */         {
/*  89: 82 */           EntityPotion e = new EntityPotion(world, entityPlayer, potionStack);
/*  90: 83 */           world.spawnEntityInWorld(e);
/*  91: 84 */           if (!entityPlayer.capabilities.isCreativeMode)
/*  92:    */           {
/*  93: 86 */             potionStack.stackSize -= 1;
/*  94: 87 */             if (potionStack.stackSize <= 0) {
/*  95: 88 */               cargo[itemPos] = null;
/*  96:    */             }
/*  97: 89 */             InventoryBag.saveCargo(itemstack, cargo);
/*  98:    */           }
/*  99:    */         }
/* 100:    */       }
/* 101:    */       else {
/* 102: 93 */         entityPlayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
/* 103:    */       }
/* 104:    */     }
/* 105: 98 */     return itemstack;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public int getMaxItemUseDuration(ItemStack itemStack)
/* 109:    */   {
/* 110:104 */     return 30;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public EnumAction getItemUseAction(ItemStack itemstack)
/* 114:    */   {
/* 115:108 */     return EnumAction.drink;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public boolean isValidAmmo(ItemStack is)
/* 119:    */   {
/* 120:112 */     return is.getItem() instanceof ItemPotion;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public int getAmmoLoaderStackSize(ItemStack is)
/* 124:    */   {
/* 125:116 */     return 64;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public int getAmmoLoaderAmmount(ItemStack is)
/* 129:    */   {
/* 130:119 */     return 6 + is.getItemDamage();
/* 131:    */   }
/* 132:    */   
/* 133:    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
/* 134:    */   {
/* 135:124 */     if (((entity instanceof EntityLiving)) && 
/* 136:125 */       (!entity.worldObj.isRemote) && (player.capabilities.isCreativeMode))
/* 137:    */     {
/* 138:126 */       ItemStack[] cargo = InventoryBag.getCargo(stack);
/* 139:127 */       ItemStack potion = cargo[0];
/* 140:128 */       if (potion.getItem() == Items.potionitem)
/* 141:    */       {
/* 142:129 */         List<PotionEffect> list = PotionHelper.getPotionEffects(potion.getItemDamage(), true);
/* 143:130 */         if (list != null) {
/* 144:131 */           for (PotionEffect effect : list) {
/* 145:132 */             ((EntityLiving)entity).addPotionEffect(new PotionEffect(effect.getPotionID(), Integer.MAX_VALUE, effect.getAmplifier()));
/* 146:    */           }
/* 147:    */         }
/* 148:135 */         return true;
/* 149:    */       }
/* 150:    */     }
/* 151:139 */     return false;
/* 152:    */   }
/* 153:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemAlchemistBag
 * JD-Core Version:    0.7.1
 */