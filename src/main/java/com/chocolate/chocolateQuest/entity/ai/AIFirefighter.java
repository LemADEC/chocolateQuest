/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import com.chocolate.chocolateQuest.utils.Vec3I;
/*   5:    */ import net.minecraft.block.Block;
/*   6:    */ import net.minecraft.block.material.Material;
/*   7:    */ import net.minecraft.entity.ai.EntityAIBase;
/*   8:    */ import net.minecraft.pathfinding.PathEntity;
/*   9:    */ import net.minecraft.pathfinding.PathNavigate;
/*  10:    */ import net.minecraft.util.MathHelper;
/*  11:    */ import net.minecraft.world.World;
/*  12:    */ 
/*  13:    */ public class AIFirefighter
/*  14:    */   extends EntityAIBase
/*  15:    */ {
/*  16:    */   World worldObj;
/*  17:    */   EntityHumanBase owner;
/*  18:    */   float moveSpeed;
/*  19:    */   PathEntity entityPathEntity;
/*  20:    */   Vec3I nearestFire;
/*  21:    */   protected int field_75445_i;
/*  22:    */   
/*  23:    */   public AIFirefighter(EntityHumanBase par1EntityLiving, float par2, boolean par3)
/*  24:    */   {
/*  25: 28 */     this.owner = par1EntityLiving;
/*  26: 29 */     this.worldObj = par1EntityLiving.worldObj;
/*  27: 30 */     this.moveSpeed = par2;
/*  28: 31 */     setMutexBits(3);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public boolean shouldExecute()
/*  32:    */   {
/*  33: 40 */     int x = MathHelper.floor_double(this.owner.posX);
/*  34: 41 */     int y = MathHelper.floor_double(this.owner.posY);
/*  35: 42 */     int z = MathHelper.floor_double(this.owner.posZ);
/*  36: 43 */     boolean flag = false;
/*  37: 44 */     if (this.nearestFire == null) {
/*  38: 49 */       for (int i = -8; i < 14; i++) {
/*  39: 51 */         for (int k = -8; k < 14; k++) {
/*  40: 53 */           for (int j = -2; j < 4; j++) {
/*  41: 55 */             if (this.worldObj.getBlock(x + i, y + j, z + k).getMaterial() == Material.fire) {
/*  42: 57 */               if (this.nearestFire != null)
/*  43:    */               {
/*  44: 59 */                 if (this.owner.getDistanceSq(x + i, y + j, z + k) < this.owner.getDistanceSq(this.nearestFire.xCoord, this.nearestFire.yCoord, this.nearestFire.zCoord)) {
/*  45: 60 */                   this.nearestFire = new Vec3I(x + i, y + j, k + z);
/*  46:    */                 }
/*  47:    */               }
/*  48:    */               else {
/*  49: 63 */                 this.nearestFire = new Vec3I(x + i, y + j, k + z);
/*  50:    */               }
/*  51:    */             }
/*  52:    */           }
/*  53:    */         }
/*  54:    */       }
/*  55:    */     }
/*  56: 69 */     return this.nearestFire != null;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void startExecuting() {}
/*  60:    */   
/*  61:    */   public void resetTask() {}
/*  62:    */   
/*  63:    */   public void updateTask()
/*  64:    */   {
/*  65: 91 */     if (this.nearestFire != null) {
/*  66: 93 */       if (this.owner.getDistanceSq(this.nearestFire.xCoord, this.nearestFire.yCoord, this.nearestFire.zCoord) < 16.0D)
/*  67:    */       {
/*  68: 95 */         this.worldObj.setBlockToAir(this.nearestFire.xCoord, this.nearestFire.yCoord, this.nearestFire.zCoord);
/*  69: 96 */         this.owner.swingItem();
/*  70: 97 */         this.nearestFire = null;
/*  71:    */       }
/*  72: 99 */       else if (this.owner.getNavigator().getPathToXYZ(this.nearestFire.xCoord, this.nearestFire.yCoord, this.nearestFire.zCoord) != null)
/*  73:    */       {
/*  74:100 */         this.owner.getNavigator().tryMoveToXYZ(this.nearestFire.xCoord, this.nearestFire.yCoord, this.nearestFire.zCoord, this.moveSpeed);
/*  75:    */       }
/*  76:    */       else
/*  77:    */       {
/*  78:102 */         this.nearestFire = null;
/*  79:    */       }
/*  80:    */     }
/*  81:    */   }
/*  82:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIFirefighter
 * JD-Core Version:    0.7.1
 */