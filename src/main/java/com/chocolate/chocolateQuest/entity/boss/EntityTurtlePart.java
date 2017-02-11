/*   1:    */ package com.chocolate.chocolateQuest.entity.boss;
/*   2:    */ 
/*   3:    */ import io.netty.buffer.ByteBuf;
/*   4:    */ import net.minecraft.entity.DataWatcher;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.util.AxisAlignedBB;
/*   7:    */ import net.minecraft.util.DamageSource;
/*   8:    */ import net.minecraft.world.World;
/*   9:    */ 
/*  10:    */ public class EntityTurtlePart
/*  11:    */   extends EntityPart
/*  12:    */ {
/*  13: 11 */   int maxHealth = 100;
/*  14:    */   
/*  15:    */   public EntityTurtlePart(World par1World)
/*  16:    */   {
/*  17: 15 */     super(par1World);
/*  18: 16 */     this.isImmuneToFire = true;
/*  19: 17 */     setHeartsLife(100);
/*  20: 18 */     this.noClip = true;
/*  21:    */   }
/*  22:    */   
/*  23:    */   public EntityTurtlePart(World world, int partID, float rotationYawOffset, float distanceToMainBody)
/*  24:    */   {
/*  25: 21 */     this(world);
/*  26: 22 */     this.rotationYawOffset = rotationYawOffset;
/*  27: 23 */     this.distanceToMainBody = distanceToMainBody;
/*  28: 24 */     this.partID = partID;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public EntityTurtlePart(World world, int partID, float rotationYawOffset, float distanceToMainBody, float heightOffset)
/*  32:    */   {
/*  33: 27 */     this(world, partID, rotationYawOffset, distanceToMainBody);
/*  34: 28 */     this.heightOffset = heightOffset;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
/*  38:    */   {
/*  39: 34 */     setHeartsLife((int)(getHeartsLife() - par2));
/*  40: 36 */     if ((getHeartsLife() <= 0) && (this.worldObj.isRemote)) {
/*  41: 38 */       setDead();
/*  42:    */     }
/*  43: 40 */     return super.attackEntityFrom(par1DamageSource, par2);
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void onUpdate()
/*  47:    */   {
/*  48: 46 */     if (getHeartsLife() < 0) {
/*  49: 47 */       setDead();
/*  50:    */     }
/*  51: 49 */     super.onUpdate();
/*  52:    */   }
/*  53:    */   
/*  54:    */   public int getMaxHealth()
/*  55:    */   {
/*  56: 54 */     return isHead() ? 300 : 100;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void setHead()
/*  60:    */   {
/*  61: 59 */     this.partID = 0;
/*  62: 60 */     setHeartsLife(300);
/*  63:    */   }
/*  64:    */   
/*  65:    */   public boolean isHead()
/*  66:    */   {
/*  67: 64 */     return this.partID == 0;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public int getHeartsLife()
/*  71:    */   {
/*  72: 69 */     return this.dataWatcher.getWatchableObjectShort(10);
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void setHeartsLife(int heartsLife)
/*  76:    */   {
/*  77: 74 */     this.dataWatcher.updateObject(10, Short.valueOf((short)heartsLife));
/*  78:    */   }
/*  79:    */   
/*  80: 76 */   private final int LIFEWATCHER = 10;
/*  81:    */   
/*  82:    */   protected void entityInit()
/*  83:    */   {
/*  84: 80 */     this.dataWatcher.addObject(10, Short.valueOf((short)100));
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void readSpawnData(ByteBuf additionalData)
/*  88:    */   {
/*  89: 84 */     super.readSpawnData(additionalData);
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void writeSpawnData(ByteBuf buffer)
/*  93:    */   {
/*  94: 88 */     super.writeSpawnData(buffer);
/*  95:    */   }
/*  96:    */   
/*  97:    */   public AxisAlignedBB getBoundingBox()
/*  98:    */   {
/*  99: 95 */     return null;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public AxisAlignedBB getCollisionBox(Entity entity)
/* 103:    */   {
/* 104:103 */     if (entity.isEntityEqual(this)) {
/* 105:104 */       return null;
/* 106:    */     }
/* 107:105 */     return entity.boundingBox;
/* 108:    */   }
/* 109:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.EntityTurtlePart
 * JD-Core Version:    0.7.1
 */