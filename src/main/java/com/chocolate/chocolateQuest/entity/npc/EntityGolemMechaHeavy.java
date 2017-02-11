/*  1:   */ package com.chocolate.chocolateQuest.entity.npc;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
/*  4:   */ import net.minecraft.entity.SharedMonsterAttributes;
/*  5:   */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  6:   */ import net.minecraft.potion.Potion;
/*  7:   */ import net.minecraft.potion.PotionEffect;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class EntityGolemMechaHeavy
/* 11:   */   extends EntityGolemMecha
/* 12:   */ {
/* 13:11 */   int gunCD = 0;
/* 14:   */   
/* 15:   */   public EntityGolemMechaHeavy(World world)
/* 16:   */   {
/* 17:14 */     super(world);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public EntityGolemMechaHeavy(World world, EntityLivingBase owner)
/* 21:   */   {
/* 22:18 */     super(world, owner);
/* 23:   */   }
/* 24:   */   
/* 25:   */   protected void applyEntityAttributes()
/* 26:   */   {
/* 27:24 */     super.applyEntityAttributes();
/* 28:25 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.19D);
/* 29:26 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
/* 30:27 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(140.0D);
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void onLivingUpdate()
/* 34:   */   {
/* 35:31 */     super.onLivingUpdate();
/* 36:32 */     if ((this.riddenByEntity != null) && (this.ticksExisted % 2 == 0)) {
/* 37:33 */       ((EntityLivingBase)this.riddenByEntity).addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 8, 2));
/* 38:   */     }
/* 39:   */   }
/* 40:   */   
/* 41:   */   public double getMountedYOffset()
/* 42:   */   {
/* 43:40 */     return 1.55D;
/* 44:   */   }
/* 45:   */   
/* 46:   */   protected String getLivingSound()
/* 47:   */   {
/* 48:44 */     return "none";
/* 49:   */   }
/* 50:   */   
/* 51:   */   protected String getHurtSound()
/* 52:   */   {
/* 53:48 */     return "mob.irongolem.hit";
/* 54:   */   }
/* 55:   */   
/* 56:   */   protected String getDeathSound()
/* 57:   */   {
/* 58:52 */     return "mob.irongolem.death";
/* 59:   */   }
/* 60:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.npc.EntityGolemMechaHeavy
 * JD-Core Version:    0.7.1
 */