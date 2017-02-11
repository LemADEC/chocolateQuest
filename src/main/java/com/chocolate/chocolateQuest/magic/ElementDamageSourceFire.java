/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ import net.minecraft.init.Blocks;
/*  5:   */ import net.minecraft.util.DamageSource;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class ElementDamageSourceFire
/*  9:   */   extends ElementDamageSource
/* 10:   */ {
/* 11:   */   public float onHitEntity(Entity source, Entity entityHit, float damage)
/* 12:   */   {
/* 13:12 */     float fireDamage = damage / 4.0F;
/* 14:13 */     if (fireDamage >= 1.0F)
/* 15:   */     {
/* 16:14 */       damage -= fireDamage;
/* 17:15 */       entityHit.setFire((int)fireDamage + 1);
/* 18:   */     }
/* 19:17 */     return damage;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void onBlockHit(Entity shooter, World world, int x, int y, int z)
/* 23:   */   {
/* 24:21 */     if (world.getBlock(x, y, z) == Blocks.air) {
/* 25:22 */       world.setBlock(x, y, z, Blocks.fire);
/* 26:   */     }
/* 27:   */   }
/* 28:   */   
/* 29:   */   public DamageSource getIndirectDamage(Entity projectile, Entity shooter, String name)
/* 30:   */   {
/* 31:27 */     return super.getIndirectDamage(projectile, shooter, name).setFireDamage();
/* 32:   */   }
/* 33:   */   
/* 34:   */   public DamageSource getDamageSource(Entity shooter, String name)
/* 35:   */   {
/* 36:31 */     return super.getDamageSource(shooter, name).setFireDamage();
/* 37:   */   }
/* 38:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.ElementDamageSourceFire
 * JD-Core Version:    0.7.1
 */