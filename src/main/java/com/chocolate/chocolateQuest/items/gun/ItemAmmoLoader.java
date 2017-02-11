/*  1:   */ package com.chocolate.chocolateQuest.items.gun;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  6:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.entity.EntityLivingBase;
/*  9:   */ import net.minecraft.entity.player.EntityPlayer;
/* 10:   */ import net.minecraft.item.ItemStack;
/* 11:   */ import net.minecraft.world.World;
/* 12:   */ 
/* 13:   */ public class ItemAmmoLoader
/* 14:   */   extends ItemPistol
/* 15:   */ {
/* 16:   */   public int getAmmoLoaderStackSize(ItemStack is)
/* 17:   */   {
/* 18:18 */     return 64;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public int getAmmoLoaderAmmount(ItemStack is)
/* 22:   */   {
/* 23:21 */     return 8;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public boolean canBeUsedByEntity(Entity entity)
/* 27:   */   {
/* 28:26 */     return false;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void shootFromEntity(EntityLivingBase shooter, ItemStack is, int angle, Entity target) {}
/* 32:   */   
/* 33:   */   @SideOnly(Side.CLIENT)
/* 34:   */   public void registerIcons(IIconRegister iconRegister)
/* 35:   */   {
/* 36:35 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:ammoLoader");
/* 37:   */   }
/* 38:   */   
/* 39:   */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/* 40:   */   {
/* 41:40 */     entityPlayer.openGui(ChocolateQuest.instance, 3, entityPlayer.worldObj, 0, 0, 0);
/* 42:41 */     return itemstack;
/* 43:   */   }
/* 44:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.gun.ItemAmmoLoader
 * JD-Core Version:    0.7.1
 */