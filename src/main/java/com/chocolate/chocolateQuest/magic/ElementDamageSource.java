/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.util.AxisAlignedBB;
/*  7:   */ import net.minecraft.util.DamageSource;
/*  8:   */ import net.minecraft.util.EntityDamageSource;
/*  9:   */ import net.minecraft.util.EntityDamageSourceIndirect;
/* 10:   */ import net.minecraft.world.World;
/* 11:   */ 
/* 12:   */ public class ElementDamageSource
/* 13:   */ {
/* 14:   */   public DamageSource getIndirectDamage(Entity projectile, Entity shooter, String name)
/* 15:   */   {
/* 16:15 */     return new EntityDamageSourceIndirect(name, projectile, shooter);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public DamageSource getDamageSource(Entity shooter, String name)
/* 20:   */   {
/* 21:18 */     return new EntityDamageSource(name, shooter);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public float onHitEntity(Entity source, Entity entityHit, float damage)
/* 25:   */   {
/* 26:22 */     return damage;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void onBlockHit(Entity shooter, World world, int x, int y, int z)
/* 30:   */   {
/* 31:26 */     List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1));
/* 32:27 */     for (EntityLivingBase e : list) {
/* 33:28 */       e.attackEntityFrom(DamageSource.generic, 1.0F);
/* 34:   */     }
/* 35:   */   }
/* 36:   */   
/* 37:   */   public DamageSource getDamageSource(String name)
/* 38:   */   {
/* 39:45 */     return new DamageSource(name);
/* 40:   */   }
/* 41:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.ElementDamageSource
 * JD-Core Version:    0.7.1
 */