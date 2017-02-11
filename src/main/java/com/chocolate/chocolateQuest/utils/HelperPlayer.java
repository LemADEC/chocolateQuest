/*   1:    */ package com.chocolate.chocolateQuest.utils;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.player.EntityPlayer;
/*   7:    */ import net.minecraft.util.AxisAlignedBB;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ import net.minecraft.util.MovingObjectPosition;
/*  10:    */ import net.minecraft.util.Vec3;
/*  11:    */ import net.minecraft.world.World;
/*  12:    */ 
/*  13:    */ public class HelperPlayer
/*  14:    */ {
/*  15:    */   public static Entity getTarget(EntityLivingBase ep, World world, double dist)
/*  16:    */   {
/*  17: 18 */     MovingObjectPosition mop = getMovingObjectPositionFromPlayer(ep, world, dist);
/*  18: 20 */     if (mop != null) {
/*  19: 22 */       return mop.entityHit;
/*  20:    */     }
/*  21: 26 */     return null;
/*  22:    */   }
/*  23:    */   
/*  24:    */   public static MovingObjectPosition getMovingObjectPositionFromPlayer(EntityLivingBase ep, World world, double dist)
/*  25:    */   {
/*  26: 32 */     return getMovingObjectPositionFromPlayer(ep, world, dist, 0.0D);
/*  27:    */   }
/*  28:    */   
/*  29:    */   public static MovingObjectPosition getMovingObjectPositionFromPlayer(EntityLivingBase ep, World world, double dist, double bbExpand)
/*  30:    */   {
/*  31: 37 */     MovingObjectPosition mop = null;
/*  32: 38 */     float yOffset = ep.getEyeHeight();
/*  33: 39 */     Vec3 playerPos = Vec3.createVectorHelper(ep.posX, ep.posY + yOffset, ep.posZ);
/*  34: 40 */     Vec3 look = ep.getLookVec();
/*  35: 41 */     if ((ep instanceof EntityPlayer))
/*  36:    */     {
/*  37: 43 */       mop = getBlockMovingObjectPositionFromPlayer(world, ep, dist, true);
/*  38: 44 */       if (mop != null)
/*  39:    */       {
/*  40: 46 */         Vec3 v = Vec3.createVectorHelper(ep.posX - mop.blockX, ep.posY - mop.blockY, ep.posZ - mop.blockZ);
/*  41: 47 */         dist = v.lengthVector();
/*  42:    */       }
/*  43:    */     }
/*  44:    */     else
/*  45:    */     {
/*  46: 50 */       mop = world.rayTraceBlocks(playerPos, look, true);
/*  47:    */     }
/*  48: 53 */     Vec3 playerView = playerPos.addVector(look.xCoord * dist, look.yCoord * dist, look.zCoord * dist);
/*  49: 54 */     List list = world.getEntitiesWithinAABBExcludingEntity(ep, ep.boundingBox.addCoord(ep.getLookVec().xCoord * dist, ep.getLookVec().yCoord * dist, ep.getLookVec().zCoord * dist).expand(1.0D, 1.0D, 1.0D));
/*  50:    */     
/*  51: 56 */     MovingObjectPosition tempMop = null;
/*  52: 57 */     double prevDist = dist * dist;
/*  53: 58 */     for (int j = 0; j < list.size(); j++)
/*  54:    */     {
/*  55: 60 */       Entity entity1 = (Entity)list.get(j);
/*  56: 62 */       if ((entity1.canBeCollidedWith()) && (entity1 != ep.ridingEntity) && (ep != ep.ridingEntity)) {
/*  57: 67 */         if ((entity1 instanceof EntityLivingBase))
/*  58:    */         {
/*  59: 71 */           float f2 = 0.4F;
/*  60: 72 */           AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(bbExpand, bbExpand, bbExpand);
/*  61: 73 */           MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(playerPos, playerView);
/*  62: 75 */           if (movingobjectposition1 != null)
/*  63:    */           {
/*  64: 79 */             movingobjectposition1.entityHit = entity1;
/*  65: 80 */             double entityDist = entity1.getDistanceSqToEntity(ep);
/*  66: 81 */             if (entityDist < prevDist)
/*  67:    */             {
/*  68: 82 */               tempMop = movingobjectposition1;
/*  69: 83 */               prevDist = entityDist;
/*  70:    */             }
/*  71:    */           }
/*  72:    */         }
/*  73:    */       }
/*  74:    */     }
/*  75: 86 */     if (tempMop != null) {
/*  76: 87 */       return tempMop;
/*  77:    */     }
/*  78: 89 */     return mop;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public static MovingObjectPosition getBlockMovingObjectPositionFromPlayer(World par1World, EntityLivingBase par2EntityPlayer, double reachDistance, boolean par3)
/*  82:    */   {
/*  83: 93 */     float f = 1.0F;
/*  84: 94 */     float rotPitch = par2EntityPlayer.prevRotationPitch + (par2EntityPlayer.rotationPitch - par2EntityPlayer.prevRotationPitch) * f;
/*  85: 95 */     float rotYaw = par2EntityPlayer.prevRotationYaw + (par2EntityPlayer.rotationYaw - par2EntityPlayer.prevRotationYaw) * f;
/*  86: 96 */     double posX = par2EntityPlayer.prevPosX + (par2EntityPlayer.posX - par2EntityPlayer.prevPosX) * f;
/*  87: 97 */     double posY = par2EntityPlayer.prevPosY + (par2EntityPlayer.posY - par2EntityPlayer.prevPosY) * f + 1.62D - par2EntityPlayer.yOffset;
/*  88: 98 */     double posZ = par2EntityPlayer.prevPosZ + (par2EntityPlayer.posZ - par2EntityPlayer.prevPosZ) * f;
/*  89: 99 */     Vec3 entityPos = Vec3.createVectorHelper(posX, posY, posZ);
/*  90:100 */     float zDesp = MathHelper.cos(-rotYaw * 0.01745329F - 3.141593F);
/*  91:101 */     float xDesp = MathHelper.sin(-rotYaw * 0.01745329F - 3.141593F);
/*  92:102 */     float yScale = -MathHelper.cos(-rotPitch * 0.01745329F);
/*  93:103 */     float lookY = MathHelper.sin(-rotPitch * 0.01745329F);
/*  94:104 */     float lookX = xDesp * yScale;
/*  95:105 */     float lookZ = zDesp * yScale;
/*  96:    */     
/*  97:107 */     Vec3 look = entityPos.addVector(lookX * reachDistance, lookY * reachDistance, lookZ * reachDistance);
/*  98:108 */     return par1World.rayTraceBlocks(entityPos, look, par3);
/*  99:    */   }
/* 100:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.utils.HelperPlayer
 * JD-Core Version:    0.7.1
 */