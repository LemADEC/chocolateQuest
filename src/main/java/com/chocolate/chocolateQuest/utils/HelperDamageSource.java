/*  1:   */ package com.chocolate.chocolateQuest.utils;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.entity.SharedMonsterAttributes;
/*  6:   */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*  7:   */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  8:   */ import net.minecraft.util.DamageSource;
/*  9:   */ import net.minecraft.util.EntityDamageSource;
/* 10:   */ import net.minecraft.util.EntityDamageSourceIndirect;
/* 11:   */ 
/* 12:   */ public class HelperDamageSource
/* 13:   */ {
/* 14:   */   public static DamageSource getBulletDamage(Entity entityBullet, Entity entity)
/* 15:   */   {
/* 16:15 */     return new EntityDamageSourceIndirect("arrow", entityBullet, entity).setProjectile();
/* 17:   */   }
/* 18:   */   
/* 19:   */   public static DamageSource causeProjectilePhysicalDamage(Entity projectile, Entity shooter)
/* 20:   */   {
/* 21:20 */     return new EntityDamageSourceIndirect("generic", projectile, shooter).setMagicDamage().setProjectile();
/* 22:   */   }
/* 23:   */   
/* 24:   */   public static DamageSource causeIndirectMagicDamage(Entity projectile, Entity shooter)
/* 25:   */   {
/* 26:25 */     return new EntityDamageSourceIndirect("magic", projectile, shooter).setMagicDamage().setProjectile();
/* 27:   */   }
/* 28:   */   
/* 29:   */   public static DamageSource causeFireProjectileDamage(Entity projectile, Entity shooter)
/* 30:   */   {
/* 31:30 */     return new EntityDamageSourceIndirect("fire", projectile, shooter).setProjectile().setFireDamage();
/* 32:   */   }
/* 33:   */   
/* 34:   */   public static DamageSource causeFireDamage(Entity shooter)
/* 35:   */   {
/* 36:35 */     return new EntityDamageSource(DamageSource.inFire.damageType, shooter).setFireDamage();
/* 37:   */   }
/* 38:   */   
/* 39:   */   public static boolean attackEntityWithoutKnockBack(Entity entity, DamageSource ds, float damage)
/* 40:   */   {
/* 41:   */     boolean damaged;
/* 42:40 */     if ((entity instanceof EntityLivingBase))
/* 43:   */     {
/* 44:42 */       AttributeModifier kbMod = new AttributeModifier("TemKBResist", 1.0D, 0);
/* 45:43 */       ((EntityLivingBase)entity).getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(kbMod);
/* 46:   */       
/* 47:45 */       damaged = entity.attackEntityFrom(ds, damage);
/* 48:   */       
/* 49:47 */       ((EntityLivingBase)entity).getEntityAttribute(SharedMonsterAttributes.knockbackResistance).removeModifier(kbMod);
/* 50:   */     }
/* 51:   */     else
/* 52:   */     {
/* 53:49 */       damaged = entity.attackEntityFrom(ds, damage);
/* 54:   */     }
/* 55:50 */     return damaged;
/* 56:   */   }
/* 57:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.utils.HelperDamageSource

 * JD-Core Version:    0.7.1

 */
