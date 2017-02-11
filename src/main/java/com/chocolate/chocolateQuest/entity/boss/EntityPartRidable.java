/*  1:   */ package com.chocolate.chocolateQuest.entity.boss;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ import net.minecraft.entity.player.EntityPlayer;
/*  5:   */ import net.minecraft.util.AxisAlignedBB;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class EntityPartRidable
/*  9:   */   extends EntityPart
/* 10:   */ {
/* 11:   */   public EntityPartRidable(World par1World)
/* 12:   */   {
/* 13:12 */     super(par1World);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public EntityPartRidable(World world, EntityBaseBoss entity, int partID, float rotationYawOffset, float distanceToMainBody, float heightOffset)
/* 17:   */   {
/* 18:15 */     super(world, entity, partID, rotationYawOffset, distanceToMainBody, heightOffset);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public AxisAlignedBB getBoundingBox()
/* 22:   */   {
/* 23:22 */     return this.boundingBox;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public AxisAlignedBB getCollisionBox(Entity entity)
/* 27:   */   {
/* 28:30 */     return entity.boundingBox;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void applyEntityCollision(Entity entity)
/* 32:   */   {
/* 33:34 */     super.applyEntityCollision(entity);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
/* 37:   */   {
/* 38:39 */     super.onCollideWithPlayer(par1EntityPlayer);
/* 39:   */   }
/* 40:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.EntityPartRidable
 * JD-Core Version:    0.7.1
 */