/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import java.util.List;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityCreature;
/*  7:   */ import net.minecraft.entity.EntityLivingBase;
/*  8:   */ import net.minecraft.entity.passive.EntityWolf;
/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ import net.minecraft.util.AxisAlignedBB;
/* 11:   */ import net.minecraft.world.World;
/* 12:   */ 
/* 13:   */ public class HandLead
/* 14:   */   extends HandHelper
/* 15:   */ {
/* 16:   */   EntityCreature wolf;
/* 17:   */   
/* 18:   */   public HandLead(EntityHumanBase owner, ItemStack itemStack)
/* 19:   */   {
/* 20:18 */     super(owner, itemStack);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void onUpdate()
/* 24:   */   {
/* 25:22 */     super.onUpdate();
/* 26:23 */     if ((!this.owner.worldObj.isRemote) && 
/* 27:24 */       (this.wolf == null) && (this.owner.ticksExisted % 100 == 0))
/* 28:   */     {
/* 29:25 */       EntityWolf closestWolf = null;
/* 30:26 */       double dist = 256.0D;
/* 31:27 */       List<Entity> list = this.owner.worldObj.getEntitiesWithinAABBExcludingEntity(this.owner, this.owner.boundingBox.expand(5.0D, 1.0D, 5.0D));
/* 32:28 */       for (Entity e : list) {
/* 33:29 */         if ((e instanceof EntityWolf))
/* 34:   */         {
/* 35:30 */           EntityWolf f = (EntityWolf)e;
/* 36:31 */           if (f.getLeashed())
/* 37:   */           {
/* 38:32 */             if (f.getLeashedToEntity() == this.owner)
/* 39:   */             {
/* 40:33 */               closestWolf = f;
/* 41:34 */               dist = 0.0D;
/* 42:   */             }
/* 43:   */           }
/* 44:37 */           else if ((f.func_152113_b() == "") || (f.func_152113_b().equals(this.owner.func_152113_b())))
/* 45:   */           {
/* 46:38 */             double currentDist = this.owner.getDistanceSqToEntity(f);
/* 47:39 */             if (currentDist < dist)
/* 48:   */             {
/* 49:40 */               closestWolf = f;
/* 50:41 */               dist = currentDist;
/* 51:   */             }
/* 52:   */           }
/* 53:   */         }
/* 54:   */       }
/* 55:46 */       if (closestWolf != null)
/* 56:   */       {
/* 57:47 */         this.wolf = closestWolf;
/* 58:48 */         this.wolf.setLeashedToEntity(this.owner, true);
/* 59:49 */         if (!closestWolf.isSitting()) {
/* 60:51 */           closestWolf.setSitting(false);
/* 61:   */         }
/* 62:   */       }
/* 63:   */     }
/* 64:56 */     if (this.wolf != null)
/* 65:   */     {
/* 66:57 */       EntityLivingBase target = this.owner.getAttackTarget();
/* 67:58 */       if (target != null) {
/* 68:59 */         this.wolf.setAttackTarget(target);
/* 69:   */       }
/* 70:62 */       if (!this.wolf.isEntityAlive()) {
/* 71:63 */         this.wolf = null;
/* 72:64 */       } else if (this.wolf.getLeashedToEntity() != this.owner) {
/* 73:65 */         this.wolf = null;
/* 74:   */       }
/* 75:   */     }
/* 76:   */   }
/* 77:   */   
/* 78:   */   public void attackEntity(Entity entity)
/* 79:   */   {
/* 80:71 */     super.attackEntity(entity);
/* 81:   */   }
/* 82:   */   
/* 83:   */   public boolean isTwoHanded()
/* 84:   */   {
/* 85:75 */     return false;
/* 86:   */   }
/* 87:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandLead
 * JD-Core Version:    0.7.1
 */