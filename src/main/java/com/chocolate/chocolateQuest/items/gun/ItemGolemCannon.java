/*  1:   */ package com.chocolate.chocolateQuest.items.gun;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMecha;
/*  5:   */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class ItemGolemCannon
/* 11:   */   extends ItemGolemWeapon
/* 12:   */ {
/* 13:   */   public ItemGolemCannon(int cooldown, float range, float accuracy, int lvl)
/* 14:   */   {
/* 15:15 */     super(cooldown, range, accuracy, lvl, 16);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public EntityBaseBall getBall(World world, EntityLivingBase shooter, double x, double y, double z)
/* 19:   */   {
/* 20:19 */     float accuracy = this.accuracy;
/* 21:20 */     byte projectile = 1;
/* 22:21 */     if ((shooter instanceof EntityHumanBase))
/* 23:   */     {
/* 24:22 */       accuracy += ((EntityHumanBase)shooter).accuracy;
/* 25:23 */       if ((shooter instanceof EntityGolemMecha)) {
/* 26:24 */         projectile = 2;
/* 27:   */       }
/* 28:   */     }
/* 29:27 */     return new EntityBaseBall(shooter.worldObj, shooter, x, y, z, projectile, 4, accuracy);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public boolean isValidAmmo(ItemStack is)
/* 33:   */   {
/* 34:31 */     return (super.isValidAmmo(is)) && (is.getItemDamage() == 4);
/* 35:   */   }
/* 36:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.gun.ItemGolemCannon
 * JD-Core Version:    0.7.1
 */