/*  1:   */ package com.chocolate.chocolateQuest.items.gun;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  5:   */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.entity.EntityLivingBase;
/*  8:   */ import net.minecraft.entity.player.EntityPlayer;
/*  9:   */ import net.minecraft.entity.player.InventoryPlayer;
/* 10:   */ import net.minecraft.item.EnumAction;
/* 11:   */ import net.minecraft.item.ItemStack;
/* 12:   */ import net.minecraft.world.World;
/* 13:   */ 
/* 14:   */ public class ItemGolemMachineGun
/* 15:   */   extends ItemGolemWeapon
/* 16:   */ {
/* 17:   */   public ItemGolemMachineGun()
/* 18:   */   {
/* 19:18 */     super(0, 20.0F, 10.0F, 0, 0);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void shootFromGolem(EntityLivingBase shooter, ItemStack is, int angle, Entity target) {}
/* 23:   */   
/* 24:   */   public boolean shouldUpdate(EntityLivingBase shooter)
/* 25:   */   {
/* 26:28 */     return true;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void onUpdate(ItemStack is, World world, Entity entity, int par4, boolean par5)
/* 30:   */   {
/* 31:32 */     boolean shoot = false;
/* 32:33 */     if (((entity instanceof EntityHumanBase)) && 
/* 33:34 */       (world.getWorldTime() % 3L == 0L)) {
/* 34:35 */       super.shootFromGolem((EntityLivingBase)entity, is, par4, null);
/* 35:   */     }
/* 36:38 */     if (((entity instanceof EntityPlayer)) && 
/* 37:39 */       (world.getWorldTime() % 2L == 0L) && 
/* 38:40 */       (((EntityPlayer)entity).getItemInUse() == is)) {
/* 39:41 */       shoot(is, world, (EntityPlayer)entity);
/* 40:   */     }
/* 41:   */   }
/* 42:   */   
/* 43:   */   protected int getAmmo(ItemStack itemstack, EntityPlayer entityPlayer)
/* 44:   */   {
/* 45:48 */     int bulletType = -1;
/* 46:49 */     for (int index = 0; index < entityPlayer.inventory.getSizeInventory(); index++)
/* 47:   */     {
/* 48:50 */       ItemStack currentIs = entityPlayer.inventory.getStackInSlot(index);
/* 49:51 */       if ((currentIs != null) && 
/* 50:52 */         (currentIs.stackTagCompound != null) && 
/* 51:53 */         (currentIs.getItem() == ChocolateQuest.ammoLoader))
/* 52:   */       {
/* 53:54 */         bulletType = super.getAmmo(currentIs, entityPlayer);
/* 54:55 */         if (bulletType != -1) {
/* 55:56 */           return bulletType;
/* 56:   */         }
/* 57:   */       }
/* 58:   */     }
/* 59:61 */     return bulletType;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/* 63:   */   {
/* 64:67 */     entityPlayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
/* 65:   */     
/* 66:69 */     return itemstack;
/* 67:   */   }
/* 68:   */   
/* 69:   */   public int getMaxItemUseDuration(ItemStack par1ItemStack)
/* 70:   */   {
/* 71:74 */     return 72000;
/* 72:   */   }
/* 73:   */   
/* 74:   */   public EnumAction getItemUseAction(ItemStack par1ItemStack)
/* 75:   */   {
/* 76:78 */     return EnumAction.bow;
/* 77:   */   }
/* 78:   */   
/* 79:   */   public EntityBaseBall getBall(World world, EntityLivingBase shooter, double x, double y, double z)
/* 80:   */   {
/* 81:82 */     EntityBaseBall ball = super.getBall(world, shooter, x, y, z);
/* 82:83 */     ball.setDamageMultiplier(0.7F);
/* 83:84 */     return ball;
/* 84:   */   }
/* 85:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.gun.ItemGolemMachineGun
 * JD-Core Version:    0.7.1
 */