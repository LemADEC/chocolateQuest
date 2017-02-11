/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import com.chocolate.chocolateQuest.utils.Vec4I;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.entity.EntityLiving;
/*   7:    */ import net.minecraft.pathfinding.PathNavigate;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ 
/*  10:    */ public class AIControlledPath
/*  11:    */   extends AIControlledBase
/*  12:    */ {
/*  13: 11 */   boolean pathDirection = false;
/*  14: 12 */   int currentPathPoint = -1;
/*  15:    */   
/*  16:    */   public AIControlledPath(EntityHumanBase owner)
/*  17:    */   {
/*  18: 16 */     super(owner);
/*  19:    */   }
/*  20:    */   
/*  21:    */   public boolean shouldExecute()
/*  22:    */   {
/*  23: 22 */     return this.owner.path != null;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public void updateTask()
/*  27:    */   {
/*  28: 28 */     if (this.owner.path != null) {
/*  29: 30 */       if (this.currentPathPoint == -1)
/*  30:    */       {
/*  31: 32 */         setNearestPathPoint();
/*  32:    */       }
/*  33: 33 */       else if (this.currentPathPoint < this.owner.path.length)
/*  34:    */       {
/*  35: 35 */         int x = MathHelper.floor_double(this.owner.posX);
/*  36: 36 */         int y = MathHelper.floor_double(this.owner.posY);
/*  37: 37 */         int z = MathHelper.floor_double(this.owner.posZ);
/*  38: 38 */         x -= this.owner.path[this.currentPathPoint].xCoord;
/*  39: 39 */         y -= this.owner.path[this.currentPathPoint].yCoord;
/*  40: 40 */         z -= this.owner.path[this.currentPathPoint].zCoord;
/*  41: 41 */         double dist = x * x + y * y + z * z;
/*  42: 42 */         if (this.owner.ridingEntity != null) {
/*  43: 43 */           dist -= this.owner.ridingEntity.height * 2.0F + this.owner.ridingEntity.width * 2.0F;
/*  44:    */         }
/*  45: 44 */         if (dist < 4.0D) {
/*  46: 46 */           nextPathPoint();
/*  47:    */         } else {
/*  48: 50 */           tryMoveToXYZ(this.owner.path[this.currentPathPoint].xCoord, this.owner.path[this.currentPathPoint].yCoord, this.owner.path[this.currentPathPoint].zCoord, 1.0F);
/*  49:    */         }
/*  50:    */       }
/*  51:    */       else
/*  52:    */       {
/*  53: 55 */         setNearestPathPoint();
/*  54:    */       }
/*  55:    */     }
/*  56: 57 */     super.updateTask();
/*  57:    */   }
/*  58:    */   
/*  59:    */   public PathNavigate getNavigator()
/*  60:    */   {
/*  61: 62 */     if (this.owner.ridingEntity != null) {
/*  62: 64 */       if (((this.owner.ridingEntity instanceof EntityLiving)) && (((EntityLiving)this.owner.ridingEntity).getNavigator() != null)) {
/*  63: 66 */         return ((EntityLiving)this.owner.ridingEntity).getNavigator();
/*  64:    */       }
/*  65:    */     }
/*  66: 70 */     return this.owner.getNavigator();
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void nextPathPoint()
/*  70:    */   {
/*  71: 75 */     if (this.currentPathPoint >= this.owner.path.length - 1)
/*  72:    */     {
/*  73: 77 */       this.pathDirection = false;
/*  74: 78 */       this.currentPathPoint = (this.owner.path.length - 1);
/*  75:    */     }
/*  76: 80 */     if (this.currentPathPoint == 0)
/*  77:    */     {
/*  78: 82 */       this.pathDirection = true;
/*  79: 83 */       this.currentPathPoint += 1;
/*  80:    */     }
/*  81: 86 */     else if (this.pathDirection)
/*  82:    */     {
/*  83: 87 */       this.currentPathPoint += 1;
/*  84:    */     }
/*  85:    */     else
/*  86:    */     {
/*  87: 89 */       this.currentPathPoint -= 1;
/*  88:    */     }
/*  89:    */   }
/*  90:    */   
/*  91:    */   public void setNearestPathPoint()
/*  92:    */   {
/*  93: 95 */     int closestPoint = -1;
/*  94: 96 */     double minDistance = Double.MAX_VALUE;
/*  95: 97 */     for (int i = 0; i < this.owner.path.length; i++)
/*  96:    */     {
/*  97: 99 */       int x = MathHelper.floor_double(this.owner.posX);
/*  98:100 */       int y = MathHelper.floor_double(this.owner.posY);
/*  99:101 */       int z = MathHelper.floor_double(this.owner.posZ);
/* 100:102 */       x -= this.owner.path[i].xCoord;
/* 101:103 */       y -= this.owner.path[i].yCoord;
/* 102:104 */       z -= this.owner.path[i].zCoord;
/* 103:105 */       double dist = x * x + y * y + z * z;
/* 104:106 */       if (dist < minDistance)
/* 105:    */       {
/* 106:108 */         closestPoint = i;
/* 107:109 */         minDistance = dist;
/* 108:    */       }
/* 109:    */     }
/* 110:112 */     this.currentPathPoint = closestPoint;
/* 111:    */   }
/* 112:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIControlledPath
 * JD-Core Version:    0.7.1
 */