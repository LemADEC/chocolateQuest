/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ import net.minecraft.util.DamageSource;
/*  5:   */ 
/*  6:   */ public class ElementDamageSourceMagic
/*  7:   */   extends ElementDamageSource
/*  8:   */ {
/*  9:   */   public DamageSource getIndirectDamage(Entity projectile, Entity shooter, String name)
/* 10:   */   {
/* 11:10 */     return super.getIndirectDamage(projectile, shooter, name).setMagicDamage();
/* 12:   */   }
/* 13:   */   
/* 14:   */   public DamageSource getDamageSource(Entity shooter, String name)
/* 15:   */   {
/* 16:14 */     return super.getDamageSource(shooter, name).setMagicDamage();
/* 17:   */   }
/* 18:   */   
/* 19:   */   public DamageSource getDamageSource(String name)
/* 20:   */   {
/* 21:18 */     return super.getDamageSource(name).setMagicDamage();
/* 22:   */   }
/* 23:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.ElementDamageSourceMagic
 * JD-Core Version:    0.7.1
 */