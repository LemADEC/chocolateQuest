/*   1:    */ package com.chocolate.chocolateQuest.entity.projectile;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.magic.Elements;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.player.EntityPlayer;
/*   7:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*   8:    */ import net.minecraft.util.DamageSource;
/*   9:    */ import net.minecraft.util.MovingObjectPosition;
/*  10:    */ import net.minecraft.world.World;
/*  11:    */ 
/*  12:    */ public class ProjectileMagic
/*  13:    */   extends ProjectileBase
/*  14:    */ {
/*  15:    */   public static final int PHYSIC = 0;
/*  16:    */   public static final int MAGIC = 1;
/*  17:    */   public static final int BLAST = 2;
/*  18:    */   public static final int FIRE = 3;
/*  19:    */   int type;
/*  20: 16 */   int damageCounter = 0;
/*  21:    */   
/*  22:    */   public ProjectileMagic(EntityBaseBall entity)
/*  23:    */   {
/*  24: 19 */     super(entity);
/*  25: 20 */     this.type = entity.getElement().ordinal();
/*  26:    */   }
/*  27:    */   
/*  28:    */   public int getTextureIndex()
/*  29:    */   {
/*  30: 26 */     if (this.type == 2) {
/*  31: 27 */       return -1;
/*  32:    */     }
/*  33: 28 */     return 247 + this.type - (this.entity.ticksExisted / 4 % 2 == 0 ? 0 : 16);
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void onImpact(MovingObjectPosition par1MovingObjectPosition)
/*  37:    */   {
/*  38: 34 */     if (!this.entity.worldObj.isRemote)
/*  39:    */     {
/*  40: 36 */       Entity e = par1MovingObjectPosition.entityHit;
/*  41: 38 */       if (e != null)
/*  42:    */       {
/*  43: 40 */         if (((e instanceof EntityLivingBase)) && (e != this.entity.shootingEntity))
/*  44:    */         {
/*  45: 42 */           if (((e instanceof EntityPlayer)) && 
/*  46: 43 */             (((EntityPlayer)e).capabilities.disableDamage)) {
/*  47: 44 */             return;
/*  48:    */           }
/*  49: 46 */           Elements element = this.entity.getElement();
/*  50: 47 */           DamageSource ds = getDamageSource().setProjectile();
/*  51: 48 */           float damage = 4.0F * this.entity.getDamageMultiplier();
/*  52: 49 */           damage = element.onHitEntity(this.entity.getThrower(), e, damage);
/*  53: 50 */           if (e.attackEntityFrom(ds, damage)) {
/*  54: 51 */             this.damageCounter += 1;
/*  55:    */           }
/*  56:    */         }
/*  57:    */       }
/*  58:    */       else {
/*  59: 80 */         this.entity.setDead();
/*  60:    */       }
/*  61:    */     }
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void onUpdateInAir()
/*  65:    */   {
/*  66: 87 */     if (this.damageCounter > this.entity.getlvl()) {
/*  67: 88 */       this.entity.setDead();
/*  68:    */     }
/*  69: 89 */     super.onUpdateInAir();
/*  70:    */   }
/*  71:    */   
/*  72:    */   protected DamageSource getDamageSource()
/*  73:    */   {
/*  74:    */     Elements element;
/*  75: 94 */     switch (this.type)
/*  76:    */     {
/*  77:    */     case 1: 
/*  78: 95 */       element = Elements.magic; break;
/*  79:    */     case 2: 
/*  80: 96 */       element = Elements.blast; break;
/*  81:    */     case 3: 
/*  82: 97 */       element = Elements.fire; break;
/*  83:    */     default: 
/*  84: 98 */       element = Elements.physic;
/*  85:    */     }
/*  86:100 */     return element.getDamageSourceIndirect(this.entity.shootingEntity, this.entity);
/*  87:    */   }
/*  88:    */   
/*  89:    */   public float getGravityVelocity()
/*  90:    */   {
/*  91:105 */     return 0.0F;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public float getSize()
/*  95:    */   {
/*  96:111 */     return 0.8F;
/*  97:    */   }
/*  98:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.projectile.ProjectileMagic
 * JD-Core Version:    0.7.1
 */