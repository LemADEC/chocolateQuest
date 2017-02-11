/*  1:   */ package com.chocolate.chocolateQuest.entity.handHelper;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import com.chocolate.chocolateQuest.utils.Vec4I;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.init.Blocks;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class HandTNT
/* 11:   */   extends HandHelper
/* 12:   */ {
/* 13:10 */   int explosionTimer = 0;
/* 14:11 */   final ItemStack whiteBlock = new ItemStack(Blocks.quartz_block);
/* 15:   */   
/* 16:   */   public HandTNT(EntityHumanBase owner, ItemStack itemStack)
/* 17:   */   {
/* 18:13 */     super(owner, itemStack);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void onUpdate()
/* 22:   */   {
/* 23:17 */     super.onUpdate();
/* 24:18 */     if ((this.owner.isSwingInProgress(this)) || (this.explosionTimer > 0))
/* 25:   */     {
/* 26:19 */       this.explosionTimer += 1;
/* 27:20 */       if (this.explosionTimer > 30)
/* 28:   */       {
/* 29:21 */         this.owner.worldObj.createExplosion(this.owner, this.owner.posX, this.owner.posY, this.owner.posZ, 5.0F, true);
/* 30:22 */         this.owner.setDead();
/* 31:   */       }
/* 32:   */     }
/* 33:25 */     if (this.owner.currentPos != null) {
/* 34:26 */       if (this.owner.getDistanceSq(this.owner.currentPos.xCoord, this.owner.currentPos.yCoord, this.owner.currentPos.zCoord) < 4.0D)
/* 35:   */       {
/* 36:27 */         this.owner.worldObj.playSoundAtEntity(this.owner, "minecraft:random.fuse", 0.2F, 1.0F);
/* 37:28 */         if (this.explosionTimer == 0) {
/* 38:29 */           this.explosionTimer += 1;
/* 39:   */         }
/* 40:   */       }
/* 41:   */       else
/* 42:   */       {
/* 43:31 */         this.explosionTimer = 0;
/* 44:   */       }
/* 45:   */     }
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void attackEntity(Entity entity)
/* 49:   */   {
/* 50:36 */     if (this.explosionTimer == 0)
/* 51:   */     {
/* 52:37 */       this.owner.worldObj.playSoundAtEntity(entity, "minecraft:random.fuse", 0.2F, 1.0F);
/* 53:38 */       this.owner.swingHand(this);
/* 54:   */     }
/* 55:   */   }
/* 56:   */   
/* 57:   */   public boolean isTwoHanded()
/* 58:   */   {
/* 59:44 */     return true;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public ItemStack getItem()
/* 63:   */   {
/* 64:49 */     if (this.explosionTimer % 3 == 1) {
/* 65:50 */       return this.whiteBlock;
/* 66:   */     }
/* 67:52 */     return super.getItem();
/* 68:   */   }
/* 69:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.handHelper.HandTNT
 * JD-Core Version:    0.7.1
 */