/*   1:    */ package com.chocolate.chocolateQuest.entity.mob;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*   7:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*   8:    */ import net.minecraft.init.Items;
/*   9:    */ import net.minecraft.potion.Potion;
/*  10:    */ import net.minecraft.potion.PotionEffect;
/*  11:    */ import net.minecraft.util.DamageSource;
/*  12:    */ import net.minecraft.world.World;
/*  13:    */ 
/*  14:    */ public class EntitySpecterBoss
/*  15:    */   extends EntityHumanSpecter
/*  16:    */ {
/*  17: 13 */   int invisibleCD = 10;
/*  18:    */   
/*  19:    */   public EntitySpecterBoss(World world)
/*  20:    */   {
/*  21: 16 */     super(world);
/*  22:    */   }
/*  23:    */   
/*  24:    */   protected void applyEntityAttributes()
/*  25:    */   {
/*  26: 22 */     super.applyEntityAttributes();
/*  27: 23 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
/*  28: 24 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public int getLeadershipValue()
/*  32:    */   {
/*  33: 29 */     return 1000;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public boolean isBoss()
/*  37:    */   {
/*  38: 33 */     return true;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public void onUpdate()
/*  42:    */   {
/*  43: 39 */     addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 200, 0));
/*  44: 40 */     super.onUpdate();
/*  45:    */   }
/*  46:    */   
/*  47:    */   public boolean attackEntityAsMob(Entity entity)
/*  48:    */   {
/*  49: 52 */     return super.attackEntityAsMob(entity);
/*  50:    */   }
/*  51:    */   
/*  52:    */   public boolean canBeCollidedWith()
/*  53:    */   {
/*  54: 58 */     return isMaterialized();
/*  55:    */   }
/*  56:    */   
/*  57:    */   public boolean canBePushed()
/*  58:    */   {
/*  59: 63 */     return canBeCollidedWith();
/*  60:    */   }
/*  61:    */   
/*  62:    */   public boolean attackEntityFrom(DamageSource damagesource, float i)
/*  63:    */   {
/*  64: 69 */     boolean ret = false;
/*  65: 70 */     if ((this.isSwingInProgress) || (getAttackTarget() == null) || (damagesource.isUnblockable()) || (damagesource.isFireDamage()) || (damagesource.isExplosion())) {
/*  66: 71 */       ret = super.attackEntityFrom(damagesource, i);
/*  67:    */     }
/*  68: 72 */     return ret;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public boolean isMaterialized()
/*  72:    */   {
/*  73: 76 */     return (this.isSwingInProgress) || (this.leftHandSwing > 0) || (getAttackTarget() == null);
/*  74:    */   }
/*  75:    */   
/*  76:    */   protected void dropFewItems(boolean flag, int i)
/*  77:    */   {
/*  78: 81 */     super.dropFewItems(flag, i);
/*  79: 83 */     if ((flag) && ((this.rand.nextInt(5) == 0) || (this.rand.nextInt(1 + i) > 0))) {
/*  80: 85 */       dropItem(Items.diamond, 2);
/*  81:    */     }
/*  82:    */   }
/*  83:    */   
/*  84:    */   protected String getLivingSound()
/*  85:    */   {
/*  86: 91 */     return "mob.villager.default";
/*  87:    */   }
/*  88:    */   
/*  89:    */   protected String getHurtSound()
/*  90:    */   {
/*  91: 96 */     return "mob.villager.defaulthurt";
/*  92:    */   }
/*  93:    */   
/*  94:    */   protected String getDeathSound()
/*  95:    */   {
/*  96:101 */     return "mob.villager.defaultdeath";
/*  97:    */   }
/*  98:    */   
/*  99:    */   public boolean shouldRenderCape()
/* 100:    */   {
/* 101:107 */     return true;
/* 102:    */   }
/* 103:    */   
/* 104:    */   protected boolean canDespawn()
/* 105:    */   {
/* 106:112 */     return false;
/* 107:    */   }
/* 108:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.EntitySpecterBoss
 * JD-Core Version:    0.7.1
 */