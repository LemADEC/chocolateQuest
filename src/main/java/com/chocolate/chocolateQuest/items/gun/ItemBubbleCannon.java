/*  1:   */ package com.chocolate.chocolateQuest.items.gun;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  4:   */ import com.chocolate.chocolateQuest.magic.Awakements;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ import net.minecraft.entity.player.EntityPlayer;
/*  8:   */ import net.minecraft.item.ItemStack;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class ItemBubbleCannon
/* 12:   */   extends ItemGolemWeapon
/* 13:   */ {
/* 14:   */   public ItemBubbleCannon(int cooldown, float range, float accuracy, int lvl)
/* 15:   */   {
/* 16:14 */     super(cooldown, range, accuracy, lvl, 16);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public EntityBaseBall getBall(World world, EntityLivingBase shooter, double x, double y, double z)
/* 20:   */   {
/* 21:18 */     return new EntityBaseBall(shooter.worldObj, shooter, x, y, z, 7, 0, this.accuracy);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public boolean freeAmmo()
/* 25:   */   {
/* 26:22 */     return true;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public boolean shoot(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/* 30:   */   {
/* 31:26 */     if (!world.isRemote)
/* 32:   */     {
/* 33:27 */       EntityBaseBall ball = new EntityBaseBall(world, entityPlayer, 7, 0);
/* 34:28 */       float accuracy = this.accuracy / 100.0F;
/* 35:29 */       ball.motionX += itemRand.nextGaussian() * accuracy;
/* 36:30 */       ball.motionY += itemRand.nextGaussian() * accuracy;
/* 37:31 */       ball.motionZ += itemRand.nextGaussian() * accuracy;
/* 38:32 */       int power = Awakements.getEnchantLevel(itemstack, Awakements.power);
/* 39:33 */       ball.setDamageMultiplier(1.0F + power / 10.0F);
/* 40:34 */       world.spawnEntityInWorld(ball);
/* 41:   */     }
/* 42:36 */     return true;
/* 43:   */   }
/* 44:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.gun.ItemBubbleCannon
 * JD-Core Version:    0.7.1
 */