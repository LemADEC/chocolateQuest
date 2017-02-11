/*   1:    */ package com.chocolate.chocolateQuest.items.swords;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.magic.Awakements;
/*   4:    */ import com.chocolate.chocolateQuest.utils.HelperPlayer;
/*   5:    */ import cpw.mods.fml.relauncher.Side;
/*   6:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   7:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.entity.EntityLivingBase;
/*  10:    */ import net.minecraft.entity.player.EntityPlayer;
/*  11:    */ import net.minecraft.item.EnumAction;
/*  12:    */ import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.Item;
/*  13:    */ import net.minecraft.item.ItemStack;
/*  14:    */ import net.minecraft.world.World;
/*  15:    */ 
/*  16:    */ public class ItemBaseSpear
/*  17:    */   extends ItemBDSword
/*  18:    */ {
/*  19: 19 */   public int cooldown = 50;
/*  20:    */   public String texture;
/*  21: 22 */   float cachedDamage = 0.0F;
/*  22:    */   
/*  23:    */   public ItemBaseSpear(Item.ToolMaterial mat, float baseDamage)
/*  24:    */   {
/*  25: 25 */     super(mat, baseDamage);
/*  26: 26 */     this.elementModifier = 0.7F;
/*  27:    */   }
/*  28:    */   
/*  29:    */   public ItemBaseSpear(Item.ToolMaterial mat)
/*  30:    */   {
/*  31: 31 */     this(mat, 3.0F);
/*  32:    */   }
/*  33:    */   
/*  34:    */   public ItemBaseSpear(Item.ToolMaterial mat, String texture)
/*  35:    */   {
/*  36: 36 */     this(mat);
/*  37: 37 */     this.texture = texture;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public ItemBaseSpear(Item.ToolMaterial mat, String texture, int baseDamage, float elementModifier)
/*  41:    */   {
/*  42: 41 */     this(mat, baseDamage);
/*  43: 42 */     this.texture = texture;
/*  44: 43 */     this.elementModifier = elementModifier;
/*  45:    */   }
/*  46:    */   
/*  47:    */   @SideOnly(Side.CLIENT)
/*  48:    */   public void registerIcons(IIconRegister iconRegister)
/*  49:    */   {
/*  50: 49 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:" + this.texture);
/*  51:    */   }
/*  52:    */   
/*  53:    */   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase target, EntityLivingBase entity)
/*  54:    */   {
/*  55: 54 */     boolean flag = super.hitEntity(par1ItemStack, target, entity);
/*  56: 55 */     if (flag) {
/*  57: 57 */       if (target.ridingEntity != null) {
/*  58: 58 */         target.mountEntity(null);
/*  59:    */       }
/*  60:    */     }
/*  61: 60 */     par1ItemStack.damageItem(1, entity);
/*  62: 61 */     return flag;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
/*  66:    */   {
/*  67: 65 */     if ((entity instanceof EntityPlayer))
/*  68:    */     {
/*  69: 67 */       EntityPlayer ep = (EntityPlayer)entity;
/*  70: 68 */       if ((ep.isSwingInProgress) && (ep.getCurrentEquippedItem() == itemStack)) {
/*  71: 70 */         if (ep.swingProgressInt == 0)
/*  72:    */         {
/*  73: 72 */           int range = Awakements.getEnchantLevel(itemStack, Awakements.range);
/*  74: 73 */           Entity target = HelperPlayer.getTarget(ep, world, 4.0D + 0.5D * range);
/*  75: 74 */           if (target != null) {
/*  76: 75 */             ep.attackTargetEntityWithCurrentItem(target);
/*  77:    */           }
/*  78:    */         }
/*  79:    */       }
/*  80:    */     }
/*  81: 81 */     super.onUpdate(itemStack, world, entity, par4, par5);
/*  82:    */   }
/*  83:    */   
/*  84:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  85:    */   {
/*  86: 86 */     entityPlayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
/*  87:    */     
/*  88: 88 */     return super.onItemRightClick(itemstack, world, entityPlayer);
/*  89:    */   }
/*  90:    */   
/*  91:    */   public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityPlayer, int useTime)
/*  92:    */   {
/*  93: 93 */     entityPlayer.swingItem();
/*  94:    */     
/*  95: 95 */     Entity target = HelperPlayer.getTarget(entityPlayer, world, 5.0D);
/*  96:    */     
/*  97: 97 */     int j = getMaxItemUseDuration(itemstack) - useTime;
/*  98: 98 */     if (j > this.cooldown) {
/*  99:100 */       doSpecialSkill(itemstack, world, entityPlayer);
/* 100:    */     } else {
/* 101:104 */       stopUsingItem(itemstack, world, entityPlayer, target);
/* 102:    */     }
/* 103:107 */     if (target != null)
/* 104:    */     {
/* 105:109 */       useTime = getMaxItemUseDuration(itemstack) - useTime;
/* 106:110 */       useTime = Math.min(useTime + 1, 90);
/* 107:111 */       this.cachedDamage = (getWeaponDamage() * useTime / 30.0F + 1.0F);
/* 108:112 */       attackEntityWithItem(entityPlayer, target);
/* 109:    */     }
/* 110:    */   }
/* 111:    */   
/* 112:    */   public int getMaxItemUseDuration(ItemStack par1ItemStack)
/* 113:    */   {
/* 114:119 */     return 72000;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public EnumAction getItemUseAction(ItemStack par1ItemStack)
/* 118:    */   {
/* 119:125 */     return EnumAction.bow;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public void doSpecialSkill(ItemStack itemstack, World world, EntityLivingBase entityPlayer) {}
/* 123:    */   
/* 124:    */   public void stopUsingItem(ItemStack itemstack, World world, EntityPlayer entityPlayer, Entity target) {}
/* 125:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemBaseSpear

 * JD-Core Version:    0.7.1

 */
