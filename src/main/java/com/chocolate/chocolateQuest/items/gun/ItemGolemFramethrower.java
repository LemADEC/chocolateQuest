/*   1:    */ package com.chocolate.chocolateQuest.items.gun;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMecha;
/*   5:    */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*   6:    */ import com.chocolate.chocolateQuest.utils.HelperDamageSource;
/*   7:    */ import java.util.List;
/*   8:    */ import java.util.Random;
/*   9:    */ import net.minecraft.entity.Entity;
/*  10:    */ import net.minecraft.entity.EntityLivingBase;
/*  11:    */ import net.minecraft.entity.player.EntityPlayer;
/*  12:    */ import net.minecraft.item.EnumAction;
/*  13:    */ import net.minecraft.item.ItemStack;
/*  14:    */ import net.minecraft.util.AxisAlignedBB;
/*  15:    */ import net.minecraft.util.MathHelper;
/*  16:    */ import net.minecraft.util.Vec3;
/*  17:    */ import net.minecraft.world.World;
/*  18:    */ 
/*  19:    */ public class ItemGolemFramethrower
/*  20:    */   extends ItemGolemWeapon
/*  21:    */ {
/*  22:    */   public void shootFromGolem(EntityLivingBase entity, ItemStack is, int angle, Entity target)
/*  23:    */   {
/*  24: 23 */     double armDist = 1.0D;
/*  25: 24 */     float rotationYaw = (float)MathHelper.wrapAngleTo180_double(entity.rotationYawHead);
/*  26: 25 */     shootFlames(entity, is, angle, target, 1.6D, 1.0D, rotationYaw);
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void shootFlames(EntityLivingBase entity, ItemStack is, int angle, Entity target, double yOff, double armDist, float rotationYaw)
/*  30:    */   {
/*  31: 30 */     World world = entity.worldObj;
/*  32: 31 */     double posX = entity.posX - Math.sin(Math.toRadians(rotationYaw + angle)) * armDist;
/*  33: 32 */     double posY = entity.posY + yOff;
/*  34: 33 */     double posZ = entity.posZ + Math.cos(Math.toRadians(rotationYaw + angle)) * armDist;
/*  35:    */     
/*  36: 35 */     float x = (float)-Math.sin(Math.toRadians(rotationYaw));
/*  37: 36 */     float z = (float)Math.cos(Math.toRadians(rotationYaw));
/*  38: 37 */     double y = -Math.sin(Math.toRadians(entity.rotationPitch));
/*  39: 38 */     x = (float)(x * (1.0D - Math.abs(y)));
/*  40: 39 */     z = (float)(z * (1.0D - Math.abs(y)));
/*  41: 42 */     if (world.isRemote)
/*  42:    */     {
/*  43: 44 */       for (int i = 0; i < 8; i++) {
/*  44: 45 */         EffectManager.spawnParticle(3, world, posX, posY, posZ, (x + itemRand.nextFloat() - 0.5D) / 3.0D, (y + itemRand.nextFloat() - 0.5D) / 8.0D, (z + itemRand.nextFloat() - 0.5D) / 3.0D);
/*  45:    */       }
/*  46:    */     }
/*  47:    */     else
/*  48:    */     {
/*  49: 50 */       int dist = 5;
/*  50: 51 */       List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.addCoord(entity.getLookVec().xCoord * dist, entity.getLookVec().yCoord * dist, entity.getLookVec().zCoord * dist).expand(1.0D, 1.0D, 1.0D));
/*  51: 52 */       for (Entity e : list) {
/*  52: 54 */         if (((e instanceof EntityLivingBase)) && (!e.isWet()) && (e != entity.riddenByEntity))
/*  53:    */         {
/*  54: 56 */           double d = posX - e.posX;
/*  55: 57 */           double d2 = posZ - e.posZ;
/*  56: 58 */           double rotDiff = Math.atan2(d, d2);
/*  57: 59 */           rotDiff = rotDiff * 180.0D / 3.141592D;
/*  58: 60 */           rotDiff = -MathHelper.wrapAngleTo180_double(rotDiff - 180.0D);
/*  59: 61 */           rotDiff -= rotationYaw;
/*  60: 62 */           if (Math.abs(rotDiff) < 30.0D)
/*  61:    */           {
/*  62: 63 */             e.setFire(2);
/*  63: 64 */             e.attackEntityFrom(HelperDamageSource.causeFireDamage(entity), 1.0F);
/*  64:    */           }
/*  65:    */         }
/*  66:    */       }
/*  67:    */     }
/*  68:    */   }
/*  69:    */   
/*  70:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  71:    */   {
/*  72: 79 */     entityPlayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
/*  73: 80 */     return itemstack;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void onUpdate(ItemStack is, World world, Entity entity, int par4, boolean par5)
/*  77:    */   {
/*  78: 85 */     if ((entity instanceof EntityGolemMecha))
/*  79:    */     {
/*  80: 86 */       shootFromGolem((EntityLivingBase)entity, is, par4, null);
/*  81:    */     }
/*  82: 87 */     else if ((entity instanceof EntityHumanBase))
/*  83:    */     {
/*  84: 88 */       shootFromGolem((EntityLivingBase)entity, is, par4, null);
/*  85:    */     }
/*  86: 90 */     else if ((entity instanceof EntityPlayer))
/*  87:    */     {
/*  88: 91 */       EntityPlayer player = (EntityPlayer)entity;
/*  89: 92 */       if ((player.isUsingItem()) && (player.getItemInUse() == is))
/*  90:    */       {
/*  91: 93 */         float rotationYaw = (float)MathHelper.wrapAngleTo180_double(player.rotationYawHead);
/*  92: 94 */         shootFlames((EntityLivingBase)entity, is, par4, null, -0.3D, 0.0D, rotationYaw);
/*  93:    */       }
/*  94:    */     }
/*  95:    */   }
/*  96:    */   
/*  97:    */   public int getMaxItemUseDuration(ItemStack par1ItemStack)
/*  98:    */   {
/*  99:101 */     return 72000;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public EnumAction getItemUseAction(ItemStack par1ItemStack)
/* 103:    */   {
/* 104:105 */     return EnumAction.bow;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public boolean isDamageable()
/* 108:    */   {
/* 109:110 */     return false;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public float getRange(EntityLivingBase shooter, ItemStack is)
/* 113:    */   {
/* 114:114 */     return 36.0F;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public int getCooldown(EntityLivingBase shooter, ItemStack is)
/* 118:    */   {
/* 119:117 */     return 0;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public boolean shouldUpdate(EntityLivingBase shooter)
/* 123:    */   {
/* 124:121 */     return true;
/* 125:    */   }
/* 126:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.gun.ItemGolemFramethrower
 * JD-Core Version:    0.7.1
 */