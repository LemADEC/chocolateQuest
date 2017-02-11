/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.entity.EnumCreatureAttribute;
/*  6:   */ 
/*  7:   */ public class ElementDamageSourceLight
/*  8:   */   extends ElementDamageSourceMagic
/*  9:   */ {
/* 10:   */   public float onHitEntity(Entity source, Entity entityHit, float damage)
/* 11:   */   {
/* 12:11 */     if (((entityHit instanceof EntityLivingBase)) && 
/* 13:12 */       (((EntityLivingBase)entityHit).getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)) {
/* 14:13 */       return damage * 1.8F;
/* 15:   */     }
/* 16:15 */     return super.onHitEntity(source, entityHit, damage);
/* 17:   */   }
/* 18:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.ElementDamageSourceLight
 * JD-Core Version:    0.7.1
 */