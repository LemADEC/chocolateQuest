/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ import net.minecraft.util.DamageSource;
/*  5:   */ import net.minecraft.util.StatCollector;
/*  6:   */ 
/*  7:   */ public enum Elements
/*  8:   */ {
/*  9: 8 */   physic("physic", new ElementDamageSourceNature(), 1.0D, "2", "physic", 8956552),  magic("magic", new ElementDamageSourceMagic(), 1.0D, "3", "magic", 3394815),  blast("blast", new ElementDamageSourceBlast(), 1.0D, "5", "blast", 10066380),  fire("fire", new ElementDamageSourceFire(), 1.0D, "6", "fire", 16750848),  light("light", new ElementDamageSourceLight(), 1.0D, "e", "light", 16777113),  darkness("darkness", new ElementDamageSourceDark(), 1.0D, "8", "dark", 4456516);
/* 10:   */   
/* 11:   */   String name;
/* 12:   */   public ElementDamageSource damageSource;
/* 13:   */   public double ammountMultiplier;
/* 14:   */   public String stringColor;
/* 15:   */   public String particle;
/* 16:   */   int color;
/* 17:   */   
/* 18:   */   private Elements(String s, ElementDamageSource ds, double dmgMultiplier, String stringColor, String particle, int color)
/* 19:   */   {
/* 20:23 */     this.name = s;
/* 21:24 */     this.damageSource = ds;
/* 22:25 */     this.ammountMultiplier = dmgMultiplier;
/* 23:26 */     this.stringColor = stringColor;
/* 24:27 */     this.particle = particle;
/* 25:28 */     this.color = color;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public DamageSource getDamageSource(Entity shooter)
/* 29:   */   {
/* 30:32 */     return this.damageSource.getDamageSource(shooter, this.name);
/* 31:   */   }
/* 32:   */   
/* 33:   */   public DamageSource getDamageSourceIndirect(Entity shooter, Entity projectile)
/* 34:   */   {
/* 35:35 */     return this.damageSource.getIndirectDamage(projectile, shooter, this.name);
/* 36:   */   }
/* 37:   */   
/* 38:   */   public DamageSource getDamageSource()
/* 39:   */   {
/* 40:38 */     return this.damageSource.getDamageSource(this.name);
/* 41:   */   }
/* 42:   */   
/* 43:   */   public float onHitEntity(Entity source, Entity entityHit, float damage)
/* 44:   */   {
/* 45:41 */     return this.damageSource.onHitEntity(source, entityHit, damage);
/* 46:   */   }
/* 47:   */   
/* 48:   */   public String getTranslatedName()
/* 49:   */   {
/* 50:45 */     return StatCollector.translateToLocal("element." + this.name + ".name");
/* 51:   */   }
/* 52:   */   
/* 53:   */   public String getName()
/* 54:   */   {
/* 55:49 */     return this.name;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public String getStringColor()
/* 59:   */   {
/* 60:53 */     return this.stringColor;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public String getParticle()
/* 64:   */   {
/* 65:57 */     return this.particle;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public float getColorX()
/* 69:   */   {
/* 70:61 */     return (this.color >> 16 & 0xFF) / 256.0F;
/* 71:   */   }
/* 72:   */   
/* 73:   */   public float getColorY()
/* 74:   */   {
/* 75:64 */     return (this.color >> 8 & 0xFF) / 256.0F;
/* 76:   */   }
/* 77:   */   
/* 78:   */   public float getColorZ()
/* 79:   */   {
/* 80:67 */     return (this.color >> 0 & 0xFF) / 256.0F;
/* 81:   */   }
/* 82:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.Elements
 * JD-Core Version:    0.7.1
 */