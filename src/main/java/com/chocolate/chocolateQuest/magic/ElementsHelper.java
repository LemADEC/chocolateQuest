/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import net.minecraft.util.DamageSource;
/*  4:   */ import net.minecraft.util.MathHelper;
/*  5:   */ 
/*  6:   */ public class ElementsHelper
/*  7:   */ {
/*  8:   */   public static float getAmmountDecreased(IElementWeak entity, float damage, DamageSource ds)
/*  9:   */   {
/* 10:10 */     if ((ds.isFireDamage()) && (entity.getFireDefense() != 0))
/* 11:   */     {
/* 12:11 */       int level = entity.getFireDefense();
/* 13:12 */       float modifier = 1.25F;
/* 14:13 */       int elementResist = getResistance(level, modifier);
/* 15:14 */       damage = (float)(damage - calculateReduction(damage, elementResist));
/* 16:   */     }
/* 17:16 */     if ((ds.isMagicDamage()) && (entity.getMagicDefense() != 0))
/* 18:   */     {
/* 19:17 */       int level = entity.getMagicDefense();
/* 20:18 */       float modifier = 1.5F;
/* 21:19 */       int elementResist = getResistance(level, modifier);
/* 22:20 */       damage = (float)(damage - calculateReduction(damage, elementResist));
/* 23:   */     }
/* 24:22 */     if ((ds.isExplosion()) && (entity.getBlastDefense() != 0))
/* 25:   */     {
/* 26:23 */       int level = entity.getBlastDefense();
/* 27:24 */       float modifier = 1.5F;
/* 28:25 */       int elementResist = getResistance(level, modifier);
/* 29:26 */       damage = (float)(damage - calculateReduction(damage, elementResist));
/* 30:   */     }
/* 31:28 */     if ((!ds.isFireDamage()) && (!ds.isMagicDamage()) && (!ds.isExplosion()) && (entity.getPhysicDefense() != 0))
/* 32:   */     {
/* 33:29 */       int level = entity.getPhysicDefense();
/* 34:30 */       float modifier = 1.25F;
/* 35:31 */       int elementResist = getResistance(level, modifier);
/* 36:32 */       damage = (float)(damage - calculateReduction(damage, elementResist));
/* 37:   */     }
/* 38:34 */     if ((ds.isProjectile()) && (entity.getProjectileDefense() != 0))
/* 39:   */     {
/* 40:35 */       int level = entity.getProjectileDefense();
/* 41:36 */       float modifier = 1.5F;
/* 42:37 */       int elementResist = getResistance(level, modifier);
/* 43:38 */       damage = (float)(damage - calculateReduction(damage, elementResist));
/* 44:   */     }
/* 45:40 */     return damage;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public static double calculateReduction(double damage, int elementResist)
/* 49:   */   {
/* 50:43 */     elementResist = Math.min(elementResist, 25) * 4;
/* 51:44 */     return damage * elementResist / 100.0D;
/* 52:   */   }
/* 53:   */   
/* 54:   */   public static int getResistance(int level, float modifier)
/* 55:   */   {
/* 56:47 */     return MathHelper.floor_float((6 + level ^ 0x2) * modifier / 3.0F);
/* 57:   */   }
/* 58:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.ElementsHelper
 * JD-Core Version:    0.7.1
 */