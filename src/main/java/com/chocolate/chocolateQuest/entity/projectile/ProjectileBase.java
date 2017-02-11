/*   1:    */ package com.chocolate.chocolateQuest.entity.projectile;
/*   2:    */ 
/*   3:    */ import net.minecraft.util.DamageSource;
/*   4:    */ import net.minecraft.util.MovingObjectPosition;
/*   5:    */ 
/*   6:    */ public abstract class ProjectileBase
/*   7:    */ {
/*   8:    */   EntityBaseBall entity;
/*   9:    */   public static final byte POISONBALL = 0;
/*  10:    */   public static final byte BULLETPISTOL = 1;
/*  11:    */   public static final byte BULLETPISTOLGOLEM = 2;
/*  12:    */   public static final byte QUAKE = 3;
/*  13:    */   public static final byte QUAKEAREA = 4;
/*  14:    */   public static final byte FIREBALL = 5;
/*  15:    */   public static final byte FIREFALLING = 6;
/*  16:    */   public static final byte BUBBLE = 7;
/*  17:    */   public static final byte VAMPIRIC = 8;
/*  18:    */   public static final byte HEALBALL = 9;
/*  19:    */   public static final byte TORNADO = 10;
/*  20:    */   public static final byte ROCKET = 11;
/*  21:    */   public static final byte MAGIC = 100;
/*  22:    */   public static final byte MAGIC_AIM = 101;
/*  23:    */   public static final byte MAGIC_AREA = 102;
/*  24:    */   public static final byte MAGIC_SHIELD = 103;
/*  25:    */   public static final byte MAGIC_STORM = 104;
/*  26:    */   public static final byte MAGIC_STORM_PROJECTILE = 105;
/*  27:    */   
/*  28:    */   public ProjectileBase(EntityBaseBall entity)
/*  29:    */   {
/*  30: 27 */     this.entity = entity;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void onUpdateInAir() {}
/*  34:    */   
/*  35:    */   public abstract int getTextureIndex();
/*  36:    */   
/*  37:    */   public abstract void onImpact(MovingObjectPosition paramMovingObjectPosition);
/*  38:    */   
/*  39:    */   public boolean canBounce()
/*  40:    */   {
/*  41: 39 */     return false;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public float getSize()
/*  45:    */   {
/*  46: 44 */     return 1.0F;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public float getSizeBB()
/*  50:    */   {
/*  51: 48 */     return getSize();
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void onSpawn() {}
/*  55:    */   
/*  56:    */   public void onDead() {}
/*  57:    */   
/*  58:    */   public float getGravityVelocity()
/*  59:    */   {
/*  60: 59 */     return 0.0F;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public int getMaxLifeTime()
/*  64:    */   {
/*  65: 64 */     return 200;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public int getRopeColor()
/*  69:    */   {
/*  70: 69 */     return -1;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void attackFrom(DamageSource d, float damage) {}
/*  74:    */   
/*  75:    */   public double getYOffset()
/*  76:    */   {
/*  77: 76 */     return 0.0D;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public boolean longRange()
/*  81:    */   {
/*  82: 81 */     return true;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public float getZOffset()
/*  86:    */   {
/*  87: 85 */     return 0.0F;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public static ProjectileBase getBallData(EntityBaseBall entity)
/*  91:    */   {
/*  92: 89 */     int type = entity.getType();
/*  93: 90 */     switch (type)
/*  94:    */     {
/*  95:    */     case 0: 
/*  96: 92 */       return new ProjectilePoisonBall(entity);
/*  97:    */     case 1: 
/*  98: 93 */       return new ProjectileBulletPistol(entity);
/*  99:    */     case 2: 
/* 100: 94 */       return new ProjectileBulletGolem(entity);
/* 101:    */     case 3: 
/* 102: 95 */       return new ProjectileEarthQuake(entity);
/* 103:    */     case 4: 
/* 104: 96 */       return new ProjectileEarthQuakeArea(entity);
/* 105:    */     case 5: 
/* 106: 97 */       return new ProjectileFireBall(entity);
/* 107:    */     case 6: 
/* 108: 98 */       return new ProjectileFireFalling(entity);
/* 109:    */     case 7: 
/* 110: 99 */       return new ProjectileBubble(entity);
/* 111:    */     case 8: 
/* 112:100 */       return new ProjectileVapiricBall(entity);
/* 113:    */     case 9: 
/* 114:101 */       return new ProjectileHealBall(entity);
/* 115:    */     case 10: 
/* 116:102 */       return new ProjectileWindBall(entity);
/* 117:    */     case 11: 
/* 118:103 */       return new ProjectileRocket(entity);
/* 119:    */     case 100: 
/* 120:104 */       return new ProjectileMagic(entity);
/* 121:    */     case 101: 
/* 122:105 */       return new ProjectileMagicAimed(entity);
/* 123:    */     case 102: 
/* 124:106 */       return new ProjectileMagicArea(entity);
/* 125:    */     case 103: 
/* 126:107 */       return new ProjectileMagicShield(entity);
/* 127:    */     case 104: 
/* 128:108 */       return new ProjectileMagicStorm(entity);
/* 129:    */     case 105: 
/* 130:109 */       return new ProjectileMagicStormProjectile(entity);
/* 131:    */     }
/* 132:111 */     return new ProjectileDummy(entity);
/* 133:    */   }
/* 134:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileBase
 * JD-Core Version:    0.7.1
 */