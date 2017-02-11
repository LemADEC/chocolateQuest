/*   1:    */ package com.chocolate.chocolateQuest.items.swords;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.IRangedWeapon;
/*   4:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   5:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*   6:    */ import com.chocolate.chocolateQuest.items.gun.ILoadableGun;
/*   7:    */ import com.chocolate.chocolateQuest.items.gun.ItemPistol;
/*   8:    */ import com.chocolate.chocolateQuest.magic.Awakements;
/*   9:    */ import cpw.mods.fml.relauncher.Side;
/*  10:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  11:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  12:    */ import net.minecraft.entity.Entity;
/*  13:    */ import net.minecraft.entity.EntityLivingBase;
/*  14:    */ import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
/*  15:    */ import net.minecraft.item.EnumRarity;
/*  16:    */ import net.minecraft.item.Item.ToolMaterial;
/*  17:    */ import net.minecraft.item.ItemStack;
/*  18:    */ import net.minecraft.world.World;
/*  19:    */ 
/*  20:    */ public class ItemSpearGun
/*  21:    */   extends ItemBaseSpear
/*  22:    */   implements IRangedWeapon, ILoadableGun
/*  23:    */ {
/*  24:    */   public ItemSpearGun()
/*  25:    */   {
/*  26: 27 */     super(Item.ToolMaterial.IRON, 4.0F);
/*  27: 28 */     setMaxDamage(2048);
/*  28: 29 */     this.cooldown = 50;
/*  29:    */   }
/*  30:    */   
/*  31:    */   @SideOnly(Side.CLIENT)
/*  32:    */   public void registerIcons(IIconRegister iconRegister)
/*  33:    */   {
/*  34: 35 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:spearGun");
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void doSpecialSkill(ItemStack itemstack, World world, EntityLivingBase entityPlayer)
/*  38:    */   {
/*  39: 41 */     if (!world.isRemote) {
/*  40: 42 */       world.spawnEntityInWorld(new EntityBaseBall(world, entityPlayer, 1, 4));
/*  41:    */     }
/*  42: 43 */     itemstack.damageItem(1, entityPlayer);
/*  43:    */   }
/*  44:    */   
/*  45:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  46:    */   {
/*  47: 48 */     if (entityPlayer.isSneaking())
/*  48:    */     {
/*  49: 49 */       entityPlayer.openGui(ChocolateQuest.instance, 3, entityPlayer.worldObj, 0, 0, 0);
/*  50: 50 */       return itemstack;
/*  51:    */     }
/*  52: 52 */     return super.onItemRightClick(itemstack, world, entityPlayer);
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void stopUsingItem(ItemStack itemstack, World world, EntityPlayer entityPlayer, Entity target)
/*  56:    */   {
/*  57: 57 */     if ((!world.isRemote) && (target == null))
/*  58:    */     {
/*  59: 59 */       ((ItemPistol)ChocolateQuest.revolver).shoot(itemstack, world, entityPlayer);
/*  60: 60 */       itemstack.damageItem(1, entityPlayer);
/*  61:    */     }
/*  62:    */   }
/*  63:    */   
/*  64:    */   public EnumRarity getRarity(ItemStack itemstack)
/*  65:    */   {
/*  66: 66 */     return EnumRarity.epic;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public int getEntityLifespan(ItemStack itemStack, World world)
/*  70:    */   {
/*  71: 70 */     return 24000;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void shootFromEntity(EntityLivingBase shooter, ItemStack is, int angle, Entity target)
/*  75:    */   {
/*  76: 75 */     ((ItemPistol)ChocolateQuest.revolver).shootFromEntity(shooter, is, angle, target);
/*  77:    */   }
/*  78:    */   
/*  79:    */   public float getRange(EntityLivingBase shooter, ItemStack is)
/*  80:    */   {
/*  81: 79 */     return 100.0F;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public int getCooldown(EntityLivingBase shooter, ItemStack is)
/*  85:    */   {
/*  86: 83 */     return 10;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public boolean canBeUsedByEntity(Entity entity)
/*  90:    */   {
/*  91: 87 */     return true;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public boolean isMeleeWeapon(EntityLivingBase shooter, ItemStack is)
/*  95:    */   {
/*  96: 91 */     return true;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public boolean shouldUpdate(EntityLivingBase shooter)
/* 100:    */   {
/* 101: 95 */     return false;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public int getAmmoLoaderStackSize(ItemStack is)
/* 105:    */   {
/* 106:100 */     return 12;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public int getAmmoLoaderAmmount(ItemStack is)
/* 110:    */   {
/* 111:104 */     int loaders = Awakements.getEnchantLevel(is, Awakements.ammoCapacity);
/* 112:105 */     return 1 + loaders;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public boolean isValidAmmo(ItemStack is)
/* 116:    */   {
/* 117:109 */     if (is == null) {
/* 118:110 */       return false;
/* 119:    */     }
/* 120:111 */     return is.getItem() == ChocolateQuest.bullet;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public int startAiming(ItemStack is, EntityLivingBase shooter, Entity target)
/* 124:    */   {
/* 125:115 */     return 30;
/* 126:    */   }
/* 127:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemSpearGun

 * JD-Core Version:    0.7.1

 */
