/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.entity.EntityLivingBase;
/*  9:   */ import net.minecraft.entity.player.EntityPlayer;
/* 10:   */ import net.minecraft.entity.player.PlayerCapabilities;
/* 11:   */ import net.minecraft.item.Item;
/* 12:   */ import net.minecraft.item.ItemStack;
/* 13:   */ import net.minecraft.util.FoodStats;
/* 14:   */ import net.minecraft.world.World;
/* 15:   */ 
/* 16:   */ public class ItemStaffHeal
/* 17:   */   extends Item
/* 18:   */ {
/* 19:   */   public ItemStaffHeal()
/* 20:   */   {
/* 21:17 */     setMaxStackSize(1);
/* 22:18 */     setFull3D();
/* 23:   */   }
/* 24:   */   
/* 25:   */   @SideOnly(Side.CLIENT)
/* 26:   */   public void registerIcons(IIconRegister iconRegister)
/* 27:   */   {
/* 28:24 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:" + getUnlocalizedName().replace("item.", ""));
/* 29:   */   }
/* 30:   */   
/* 31:   */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
/* 32:   */   {
/* 33:29 */     if ((entity instanceof EntityLivingBase)) {
/* 34:31 */       if ((player.getFoodStats().getFoodLevel() > 1) || (player.capabilities.isCreativeMode))
/* 35:   */       {
/* 36:33 */         if (!player.capabilities.isCreativeMode) {
/* 37:34 */           player.getFoodStats().addStats(-1, 0.0F);
/* 38:   */         }
/* 39:35 */         player.setItemInUse(stack, getMaxItemUseDuration(stack));
/* 40:36 */         ((EntityLivingBase)entity).heal(2.0F);
/* 41:37 */         if (entity.worldObj.isRemote) {
/* 42:39 */           for (int i = 0; i < 5; i++) {
/* 43:41 */             entity.worldObj.spawnParticle("heart", entity.posX + itemRand.nextFloat() - 0.5D, entity.posY + 1.0D + itemRand.nextFloat(), entity.posZ + itemRand.nextFloat() - 0.5D, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F, itemRand.nextFloat() - 0.5F);
/* 44:   */           }
/* 45:   */         }
/* 46:44 */         entity.worldObj.playSoundEffect((int)entity.posX, (int)entity.posY, (int)entity.posZ, "chocolatequest:magic", 0.5F, (1.0F + (entity.worldObj.rand.nextFloat() - entity.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
/* 47:   */         
/* 48:46 */         return true;
/* 49:   */       }
/* 50:   */     }
/* 51:50 */     return false;
/* 52:   */   }
/* 53:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemStaffHeal
 * JD-Core Version:    0.7.1
 */