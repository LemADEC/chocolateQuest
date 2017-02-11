/*   1:    */ package com.chocolate.chocolateQuest.entity.ai;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import com.chocolate.chocolateQuest.utils.Vec4I;
/*   5:    */ import java.util.Random;
/*   6:    */ import net.minecraft.entity.EntityLiving;
/*   7:    */ import net.minecraft.entity.EntityLivingBase;
/*   8:    */ import net.minecraft.entity.ai.EntityAIBase;
/*   9:    */ import net.minecraft.pathfinding.PathNavigate;
/*  10:    */ import net.minecraft.util.MathHelper;
/*  11:    */ import net.minecraft.util.Vec3;
/*  12:    */ 
/*  13:    */ public class AIControlledBase
/*  14:    */   extends EntityAIBase
/*  15:    */ {
/*  16:    */   protected EntityHumanBase owner;
/*  17:    */   private Vec4I position;
/*  18:    */   private int pathFindingCooldown;
/*  19: 18 */   public int pathBlockedTime = 0;
/*  20:    */   
/*  21:    */   public AIControlledBase(EntityHumanBase owner)
/*  22:    */   {
/*  23: 20 */     this.owner = owner;
/*  24: 21 */     setMutexBits(3);
/*  25:    */   }
/*  26:    */   
/*  27:    */   public boolean shouldExecute()
/*  28:    */   {
/*  29: 26 */     return true;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public boolean stayInFormation()
/*  33:    */   {
/*  34: 31 */     if (this.owner.getLeader() != null)
/*  35:    */     {
/*  36: 32 */       double x = 0.0D;double y = 0.0D;double z = 0.0D;
/*  37: 33 */       EntityLivingBase leader = this.owner.getLeader();
/*  38: 34 */       Vec3 absPosition = leader.getLookVec();
/*  39: 35 */       float angle = this.owner.partyPositionAngle * 3.1416F / 180.0F;
/*  40: 36 */       double cos = MathHelper.cos(angle);
/*  41: 37 */       double sin = MathHelper.sin(angle);
/*  42: 38 */       int dist = this.owner.partyDistanceToLeader;
/*  43: 39 */       x = leader.posX + (absPosition.xCoord * cos - absPosition.zCoord * sin) * dist;
/*  44: 40 */       y = leader.posY;
/*  45: 41 */       z = leader.posZ + (absPosition.zCoord * cos + absPosition.xCoord * sin) * dist;
/*  46: 42 */       tryMoveToXYZ(x, y, z, 1.15F);
/*  47: 43 */       return true;
/*  48:    */     }
/*  49: 45 */     return false;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public boolean tryMoveToXYZ(double x, double y, double z, float moveSpeed)
/*  53:    */   {
/*  54: 54 */     if (this.owner.ridingEntity != null) {
/*  55: 55 */       moveSpeed += 1.0F;
/*  56:    */     }
/*  57: 57 */     this.pathFindingCooldown -= 1;
/*  58: 58 */     if ((this.pathFindingCooldown > 0) && (!getNavigator().noPath())) {
/*  59: 59 */       return true;
/*  60:    */     }
/*  61: 61 */     this.pathFindingCooldown = (5 + this.owner.getRNG().nextInt(10));
/*  62: 62 */     if (getNavigator().tryMoveToXYZ(x, y, z, moveSpeed))
/*  63:    */     {
/*  64: 64 */       this.position = null;
/*  65: 65 */       return !getNavigator().noPath();
/*  66:    */     }
/*  67: 67 */     if (this.position == null)
/*  68:    */     {
/*  69: 71 */       Vec3 vec3 = Vec3.createVectorHelper(x - this.owner.posX, y - this.owner.posY, z - this.owner.posZ);
/*  70: 72 */       vec3 = vec3.normalize();
/*  71: 73 */       vec3 = Vec3.createVectorHelper(vec3.xCoord * 10.0D + this.owner.posX, vec3.yCoord + this.owner.posY, vec3.zCoord * 10.0D + this.owner.posZ);
/*  72:    */       
/*  73: 75 */       this.position = new Vec4I(MathHelper.floor_double(vec3.xCoord), MathHelper.floor_double(vec3.yCoord), MathHelper.floor_double(vec3.zCoord), 0);
/*  74: 76 */       return true;
/*  75:    */     }
/*  76: 79 */     if (getNavigator().tryMoveToXYZ(this.position.xCoord, this.position.yCoord, this.position.zCoord, moveSpeed))
/*  77:    */     {
/*  78: 81 */       if (this.pathFindingCooldown <= 0)
/*  79:    */       {
/*  80: 82 */         this.pathFindingCooldown = 10;
/*  81: 83 */         this.position = null;
/*  82:    */       }
/*  83: 85 */       return true;
/*  84:    */     }
/*  85: 89 */     this.position = null;
/*  86:    */     
/*  87: 91 */     return false;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public PathNavigate getNavigator()
/*  91:    */   {
/*  92: 98 */     if (this.owner.ridingEntity != null) {
/*  93:100 */       if (((this.owner.ridingEntity instanceof EntityLiving)) && (((EntityLiving)this.owner.ridingEntity).getNavigator() != null)) {
/*  94:102 */         return ((EntityLiving)this.owner.ridingEntity).getNavigator();
/*  95:    */       }
/*  96:    */     }
/*  97:106 */     return this.owner.getNavigator();
/*  98:    */   }
/*  99:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.ai.AIControlledBase
 * JD-Core Version:    0.7.1
 */