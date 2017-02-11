/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.potion.Potion;
/*  6:   */ import net.minecraft.potion.PotionEffect;
/*  7:   */ import net.minecraft.util.DamageSource;
/*  8:   */ 
/*  9:   */ public class ElementDamageSourceNature
/* 10:   */   extends ElementDamageSource
/* 11:   */ {
/* 12:   */   public float onHitEntity(Entity source, Entity entityHit, float damage)
/* 13:   */   {
/* 14:13 */     int fireDamage = (int)(damage / 4.0F);
/* 15:14 */     if (fireDamage >= 1)
/* 16:   */     {
/* 17:15 */       damage -= fireDamage;
/* 18:16 */       ((EntityLivingBase)entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, fireDamage * 30, 0));
/* 19:   */     }
/* 20:18 */     return damage;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public DamageSource getIndirectDamage(Entity projectile, Entity shooter, String name)
/* 24:   */   {
/* 25:23 */     return super.getIndirectDamage(projectile, shooter, name).setMagicDamage();
/* 26:   */   }
/* 27:   */   
/* 28:   */   public DamageSource getDamageSource(Entity shooter, String name)
/* 29:   */   {
/* 30:27 */     return super.getDamageSource(shooter, name).setMagicDamage();
/* 31:   */   }
/* 32:   */   
/* 33:   */   public DamageSource getDamageSource(String name)
/* 34:   */   {
/* 35:31 */     return super.getDamageSource(name).setMagicDamage();
/* 36:   */   }
/* 37:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.ElementDamageSourceNature
 * JD-Core Version:    0.7.1
 */