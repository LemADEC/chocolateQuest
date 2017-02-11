/*   1:    */ package com.chocolate.chocolateQuest.entity.boss;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*   4:    */ import io.netty.buffer.ByteBuf;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.entity.player.EntityPlayer;
/*   7:    */ import net.minecraft.item.Item;
/*   8:    */ import net.minecraft.item.ItemStack;
/*   9:    */ import net.minecraft.nbt.NBTTagCompound;
/*  10:    */ import net.minecraft.util.DamageSource;
/*  11:    */ import net.minecraft.world.World;
/*  12:    */ 
/*  13:    */ public class EntityPart
/*  14:    */   extends Entity
/*  15:    */   implements IEntityAdditionalSpawnData
/*  16:    */ {
/*  17:    */   EntityBaseBoss mainBody;
/*  18: 15 */   int maxHealth = 100;
/*  19: 16 */   public boolean staticPart = true;
/*  20: 18 */   public float rotationYawOffset = 0.0F;
/*  21: 19 */   public float distanceToMainBody = 0.0F;
/*  22: 20 */   public float heightOffset = 0.0F;
/*  23: 21 */   int partID = 0;
/*  24: 22 */   int ownerID = 0;
/*  25:    */   
/*  26:    */   public EntityPart(World world)
/*  27:    */   {
/*  28: 25 */     super(world);
/*  29: 26 */     this.isImmuneToFire = true;
/*  30: 27 */     setSize(1.0F, 1.0F);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public EntityPart(World world, EntityBaseBoss main, int partID, float rotationYawOffset, float distanceToMainBody)
/*  34:    */   {
/*  35: 30 */     this(world);
/*  36: 31 */     this.rotationYawOffset = rotationYawOffset;
/*  37: 32 */     this.distanceToMainBody = distanceToMainBody;
/*  38: 33 */     this.partID = partID;
/*  39: 34 */     this.mainBody = main;
/*  40: 35 */     if (main != null) {
/*  41: 36 */       setPosition(main.posX, main.posY, main.posZ);
/*  42:    */     }
/*  43:    */   }
/*  44:    */   
/*  45:    */   public EntityPart(World world, EntityBaseBoss main, int partID, float rotationYawOffset, float distanceToMainBody, float heightOffset)
/*  46:    */   {
/*  47: 39 */     this(world, main, partID, rotationYawOffset, distanceToMainBody);
/*  48: 40 */     this.heightOffset = heightOffset;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void setSize(float par1, float par2)
/*  52:    */   {
/*  53: 45 */     super.setSize(par1, par2);
/*  54:    */   }
/*  55:    */   
/*  56:    */   protected void entityInit() {}
/*  57:    */   
/*  58:    */   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
/*  59:    */   {
/*  60: 55 */     if ((!this.worldObj.isRemote) && (this.mainBody != null))
/*  61:    */     {
/*  62: 57 */       Entity e = par1DamageSource.getEntity();
/*  63: 58 */       boolean ret = this.mainBody.attackFromPart(par1DamageSource, par2, this);
/*  64: 59 */       if ((ret) && 
/*  65: 60 */         ((e instanceof EntityPlayer)))
/*  66:    */       {
/*  67: 61 */         ItemStack is = ((EntityPlayer)e).getCurrentEquippedItem();
/*  68: 62 */         if (is != null) {
/*  69: 63 */           is.getItem().hitEntity(is, this.mainBody, (EntityPlayer)e);
/*  70:    */         }
/*  71:    */       }
/*  72: 66 */       return ret;
/*  73:    */     }
/*  74: 70 */     return false;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void onUpdate()
/*  78:    */   {
/*  79: 77 */     if (!this.worldObj.isRemote) {
/*  80: 79 */       if (this.mainBody == null)
/*  81:    */       {
/*  82: 81 */         Entity e = this.worldObj.getEntityByID(this.ownerID);
/*  83: 82 */         if ((e instanceof EntityBaseBoss)) {
/*  84: 83 */           setMainBody((EntityBaseBoss)e);
/*  85:    */         } else {
/*  86: 85 */           setDead();
/*  87:    */         }
/*  88:    */       }
/*  89: 87 */       else if (this.mainBody.isDead)
/*  90:    */       {
/*  91: 89 */         setDead();
/*  92:    */       }
/*  93:    */     }
/*  94: 92 */     super.onUpdate();
/*  95: 93 */     if (this.mainBody != null)
/*  96:    */     {
/*  97: 94 */       this.motionX = this.mainBody.motionX;
/*  98: 95 */       this.motionZ = this.mainBody.motionZ;
/*  99: 96 */       this.motionY = this.mainBody.motionY;
/* 100: 97 */       if (this.staticPart)
/* 101:    */       {
/* 102: 98 */         double hx = -Math.sin(Math.toRadians(this.mainBody.rotationYaw + this.rotationYawOffset)) * this.distanceToMainBody;
/* 103: 99 */         double hz = Math.cos(Math.toRadians(this.mainBody.rotationYaw + this.rotationYawOffset)) * this.distanceToMainBody;
/* 104:100 */         setPositionAndRotation(this.mainBody.posX + hx + this.motionX, this.mainBody.posY + this.heightOffset + this.motionY, this.mainBody.posZ + hz + this.motionZ, this.mainBody.rotationYaw, this.mainBody.rotationPitch);
/* 105:    */       }
/* 106:106 */       if (this.mainBody.motionY > 0.0D) {
/* 107:107 */         this.posY += 20.0D;
/* 108:    */       }
/* 109:    */     }
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void setPosition(double x, double y, double z)
/* 113:    */   {
/* 114:113 */     super.setPosition(x, y, z);
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void setPositionAndRotation(double x, double y, double z, float yaw, float pitch)
/* 118:    */   {
/* 119:117 */     super.setPositionAndRotation(x, y, z, yaw, pitch);
/* 120:    */   }
/* 121:    */   
/* 122:    */   public boolean canBeCollidedWith()
/* 123:    */   {
/* 124:123 */     return true;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public boolean canBePushed()
/* 128:    */   {
/* 129:128 */     return false;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public void setMainBody(EntityBaseBoss body)
/* 133:    */   {
/* 134:133 */     this.mainBody = body;
/* 135:134 */     this.mainBody.setPart(this, this.partID);
/* 136:    */   }
/* 137:    */   
/* 138:    */   public EntityBaseBoss getMainBody()
/* 139:    */   {
/* 140:138 */     return this.mainBody;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public boolean isEntityEqual(Entity par1Entity)
/* 144:    */   {
/* 145:144 */     return (this == par1Entity) || (this.mainBody == par1Entity);
/* 146:    */   }
/* 147:    */   
/* 148:    */   protected void readEntityFromNBT(NBTTagCompound var1)
/* 149:    */   {
/* 150:150 */     setDead();
/* 151:    */   }
/* 152:    */   
/* 153:    */   protected void writeEntityToNBT(NBTTagCompound var1) {}
/* 154:    */   
/* 155:    */   public void readSpawnData(ByteBuf additionalData)
/* 156:    */   {
/* 157:160 */     if (additionalData.readBoolean()) {
/* 158:161 */       return;
/* 159:    */     }
/* 160:162 */     int id = additionalData.readInt();
/* 161:163 */     Entity e = this.worldObj.getEntityByID(id);
/* 162:164 */     this.distanceToMainBody = additionalData.readFloat();
/* 163:165 */     this.rotationYawOffset = additionalData.readFloat();
/* 164:166 */     this.heightOffset = additionalData.readFloat();
/* 165:167 */     this.staticPart = (additionalData.readByte() == 1);
/* 166:168 */     this.partID = additionalData.readByte();
/* 167:170 */     if ((e instanceof EntityBaseBoss)) {
/* 168:171 */       setMainBody((EntityBaseBoss)e);
/* 169:    */     }
/* 170:    */   }
/* 171:    */   
/* 172:    */   public void writeSpawnData(ByteBuf buffer)
/* 173:    */   {
/* 174:177 */     buffer.writeBoolean(this.mainBody == null);
/* 175:178 */     if (this.mainBody != null)
/* 176:    */     {
/* 177:179 */       buffer.writeInt(this.mainBody.getEntityId());
/* 178:180 */       buffer.writeFloat(this.distanceToMainBody);
/* 179:181 */       buffer.writeFloat(this.rotationYawOffset);
/* 180:182 */       buffer.writeFloat(this.heightOffset);
/* 181:183 */       buffer.writeByte(this.staticPart ? 1 : 0);
/* 182:184 */       buffer.writeByte(this.partID);
/* 183:    */     }
/* 184:    */   }
/* 185:    */   
/* 186:    */   public void setDead()
/* 187:    */   {
/* 188:189 */     super.setDead();
/* 189:    */   }
/* 190:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.boss.EntityPart
 * JD-Core Version:    0.7.1
 */