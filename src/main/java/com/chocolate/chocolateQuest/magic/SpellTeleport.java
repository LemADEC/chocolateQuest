/*   1:    */ package com.chocolate.chocolateQuest.magic;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   5:    */ import com.chocolate.chocolateQuest.packets.PacketSpawnParticlesAround;
/*   6:    */ import com.chocolate.chocolateQuest.utils.HelperPlayer;
/*   7:    */ import java.util.List;
/*   8:    */ import java.util.Random;
/*   9:    */ import net.minecraft.block.Block;
/*  10:    */ import net.minecraft.block.material.Material;
/*  11:    */ import net.minecraft.entity.Entity;
/*  12:    */ import net.minecraft.entity.EntityLiving;
/*  13:    */ import net.minecraft.entity.EntityLivingBase;
/*  14:    */ import net.minecraft.entity.player.EntityPlayer;
/*  15:    */ import net.minecraft.item.ItemStack;
/*  16:    */ import net.minecraft.util.AxisAlignedBB;
/*  17:    */ import net.minecraft.util.DamageSource;
/*  18:    */ import net.minecraft.util.MathHelper;
/*  19:    */ import net.minecraft.util.MovingObjectPosition;
/*  20:    */ import net.minecraft.world.World;
/*  21:    */ 
/*  22:    */ public class SpellTeleport
/*  23:    */   extends SpellProjectile
/*  24:    */ {
/*  25:    */   public void onShoot(EntityLivingBase shooter, Elements element, ItemStack is, int chargeTime)
/*  26:    */   {
/*  27: 24 */     World world = shooter.worldObj;
/*  28: 25 */     double startX = shooter.posX;
/*  29: 26 */     double startY = shooter.posY;
/*  30: 27 */     double startZ = shooter.posZ;
/*  31: 28 */     if (world.isRemote)
/*  32:    */     {
/*  33: 30 */       Random random = shooter.getRNG();
/*  34: 31 */       for (int i = 0; i < 8; i++) {
/*  35: 32 */         shooter.worldObj.spawnParticle("portal", shooter.posX + (random.nextFloat() - 0.5D), shooter.posY + (random.nextFloat() - 0.5D), shooter.posZ + (random.nextFloat() - 0.5D), (random.nextFloat() - 0.5D) / 2.0D, (random.nextFloat() - 0.5D) / 2.0D, (random.nextFloat() - 0.5D) / 2.0D);
/*  36:    */       }
/*  37:    */     }
/*  38: 36 */     else if (!world.isRemote)
/*  39:    */     {
/*  40: 38 */       PacketSpawnParticlesAround packet = new PacketSpawnParticlesAround(PacketSpawnParticlesAround.getParticleFromName(element.getParticle()), shooter.posX, shooter.posY, shooter.posZ);
/*  41: 39 */       ChocolateQuest.channel.sendToAllAround(shooter, packet, 64);
/*  42:    */     }
/*  43: 41 */     if ((shooter instanceof EntityPlayer))
/*  44:    */     {
/*  45: 43 */       MovingObjectPosition mop = HelperPlayer.getBlockMovingObjectPositionFromPlayer(shooter.worldObj, (EntityPlayer)shooter, 60.0D, true);
/*  46: 44 */       if (mop != null)
/*  47:    */       {
/*  48: 45 */         double y = 1.0D;
/*  49: 46 */         double x = 0.0D;
/*  50: 47 */         double z = 0.0D;
/*  51: 48 */         switch (mop.sideHit)
/*  52:    */         {
/*  53:    */         case 0: 
/*  54: 50 */           y = 0.0D;
/*  55: 51 */           break;
/*  56:    */         case 2: 
/*  57: 53 */           z = -1.0D;
/*  58: 54 */           break;
/*  59:    */         case 3: 
/*  60: 56 */           z = 1.5D;
/*  61: 57 */           break;
/*  62:    */         case 4: 
/*  63: 59 */           x = -0.5D;
/*  64: 60 */           break;
/*  65:    */         case 5: 
/*  66: 62 */           x = 1.5D;
/*  67:    */         }
/*  68: 65 */         shooter.setPosition(mop.blockX + x, mop.blockY + y, mop.blockZ + z);
/*  69:    */       }
/*  70:    */     }
/*  71:    */     else
/*  72:    */     {
/*  73: 68 */       Entity target = shooter;
/*  74: 69 */       EntityLiving shooterLiving = (EntityLiving)shooter;
/*  75: 70 */       if (shooterLiving.getAttackTarget() != null) {
/*  76: 71 */         target = shooterLiving.getAttackTarget();
/*  77:    */       }
/*  78: 72 */       damageNearby(world, shooter, element);
/*  79: 73 */       for (int i = 0; (i < 6) && (!castTeleport(shooter, target)); i++) {}
/*  80:    */     }
/*  81: 76 */     shooter.worldObj.playSoundEffect(startX, startY, startZ, "mob.endermen.portal", 1.0F, 1.0F);
/*  82: 77 */     shooter.worldObj.playSoundAtEntity(shooter, "mob.endermen.portal", 1.0F, 1.0F);
/*  83: 79 */     if (world.isRemote)
/*  84:    */     {
/*  85: 81 */       Random random = shooter.getRNG();
/*  86: 82 */       for (int i = 0; i < 16; i++) {
/*  87: 83 */         shooter.worldObj.spawnParticle("portal", shooter.posX + (random.nextFloat() - 0.5D), shooter.posY + random.nextFloat(), shooter.posZ + (random.nextFloat() - 0.5D), (random.nextFloat() - 0.5D) / 2.0D, (random.nextFloat() - 0.5D) / 2.0D, (random.nextFloat() - 0.5D) / 2.0D);
/*  88:    */       }
/*  89:    */     }
/*  90:    */   }
/*  91:    */   
/*  92:    */   public int getCastingTime()
/*  93:    */   {
/*  94: 92 */     return 3;
/*  95:    */   }
/*  96:    */   
/*  97:    */   protected void damageNearby(World world, Entity shooter, Elements element)
/*  98:    */   {
/*  99: 95 */     int dist = 5;
/* 100: 96 */     List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(shooter, shooter.boundingBox.expand(dist, 1.0D, dist));
/* 101: 97 */     for (Entity e : list) {
/* 102: 99 */       if (((e instanceof EntityLivingBase)) && (e != shooter.riddenByEntity))
/* 103:    */       {
/* 104:101 */         DamageSource ds = element.getDamageSource(shooter);
/* 105:102 */         e.attackEntityFrom(ds, 1.0F);
/* 106:    */       }
/* 107:    */     }
/* 108:    */   }
/* 109:    */   
/* 110:    */   protected boolean castTeleport(EntityLivingBase shooter, Entity target)
/* 111:    */   {
/* 112:109 */     World worldObj = shooter.worldObj;
/* 113:110 */     float dist = 16.0F;
/* 114:111 */     Random rand = shooter.getRNG();
/* 115:112 */     double dX = target.posX + (rand.nextDouble() - 0.5D) * dist;
/* 116:113 */     double dY = target.posY;
/* 117:114 */     double dZ = target.posZ + (rand.nextDouble() - 0.5D) * dist;
/* 118:115 */     double startX = shooter.posX;
/* 119:116 */     double startY = shooter.posY;
/* 120:117 */     double startZ = shooter.posZ;
/* 121:118 */     boolean flag = false;
/* 122:119 */     int i = MathHelper.floor_double(dX);
/* 123:120 */     int j = MathHelper.floor_double(dY);
/* 124:121 */     int k = MathHelper.floor_double(dZ);
/* 125:123 */     if (worldObj.blockExists(i, j, k))
/* 126:    */     {
boolean isNonSolidBlock = false;

/* 127:126 */       for (isNonSolidBlock = false; (!isNonSolidBlock) && (j < 255);)
/* 128:    */       {
/* 129:128 */         Material mat = worldObj.getBlock(i, j - 1, k).getMaterial();
/* 130:130 */         if (!mat.isSolid())
/* 131:    */         {
/* 132:132 */           dY += 1.0D;
/* 133:133 */           j++;
/* 134:    */         }
/* 135:    */         else
/* 136:    */         {
/* 137:137 */           isNonSolidBlock = true;
/* 138:    */         }
/* 139:    */       }
/* 140:141 */       if (isNonSolidBlock)
/* 141:    */       {
/* 142:143 */         shooter.setPosition(dX, dY, dZ);
/* 143:145 */         if ((worldObj.getCollidingBoundingBoxes(shooter, shooter.boundingBox).size() == 0) && (!worldObj.isAnyLiquid(shooter.boundingBox))) {
/* 144:147 */           flag = true;
/* 145:    */         }
/* 146:    */       }
/* 147:    */     }
/* 148:152 */     if (!flag)
/* 149:    */     {
/* 150:154 */       shooter.setPosition(startX, startY, startZ);
/* 151:155 */       return false;
/* 152:    */     }
/* 153:157 */     return true;
/* 154:    */   }
/* 155:    */   
/* 156:    */   public int getCoolDown()
/* 157:    */   {
/* 158:163 */     return 50;
/* 159:    */   }
/* 160:    */   
/* 161:    */   public int getRange(ItemStack itemstack)
/* 162:    */   {
/* 163:168 */     return 2;
/* 164:    */   }
/* 165:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.magic.SpellTeleport

 * JD-Core Version:    0.7.1

 */
