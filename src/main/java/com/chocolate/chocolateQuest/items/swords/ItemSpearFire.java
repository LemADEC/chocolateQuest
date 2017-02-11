/*   1:    */ package com.chocolate.chocolateQuest.items.swords;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.IRangedWeapon;
/*   4:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   5:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   6:    */ import com.chocolate.chocolateQuest.packets.PacketSpawnParticlesAround;
/*   7:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   8:    */ import com.chocolate.chocolateQuest.utils.HelperDamageSource;
/*   9:    */ import cpw.mods.fml.relauncher.Side;
/*  10:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  11:    */ import java.util.List;
/*  12:    */ import java.util.Random;
/*  13:    */ import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

/*  14:    */ import net.minecraft.entity.Entity;
/*  15:    */ import net.minecraft.entity.EntityLiving;
/*  16:    */ import net.minecraft.entity.EntityLivingBase;
/*  17:    */ import net.minecraft.item.EnumRarity;
/*  18:    */ import net.minecraft.item.Item.ToolMaterial;
/*  19:    */ import net.minecraft.item.ItemStack;
/*  20:    */ import net.minecraft.util.AxisAlignedBB;
/*  21:    */ import net.minecraft.util.MathHelper;
/*  22:    */ import net.minecraft.util.Vec3;
/*  23:    */ import net.minecraft.world.World;
/*  24:    */ 
/*  25:    */ public class ItemSpearFire
/*  26:    */   extends ItemBaseSpear
/*  27:    */   implements IRangedWeapon
/*  28:    */ {
/*  29:    */   public ItemSpearFire()
/*  30:    */   {
/*  31: 27 */     super(Item.ToolMaterial.EMERALD, 4.0F);
/*  32: 28 */     setMaxDamage(2048);
/*  33: 29 */     this.cooldown = 30;
/*  34:    */   }
/*  35:    */   
/*  36:    */   @SideOnly(Side.CLIENT)
/*  37:    */   public void registerIcons(IIconRegister iconRegister)
/*  38:    */   {
/*  39: 35 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:spearDwarf");
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void doSpecialSkill(ItemStack itemstack, World world, EntityLivingBase shooter)
/*  43:    */   {
/*  44: 41 */     shootFromEntity(shooter, itemstack, 0, null);
/*  45: 42 */     itemstack.damageItem(1, shooter);
/*  46:    */   }
/*  47:    */   
/*  48:    */   public EnumRarity getRarity(ItemStack itemstack)
/*  49:    */   {
/*  50: 48 */     return EnumRarity.epic;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public int getEntityLifespan(ItemStack itemStack, World world)
/*  54:    */   {
/*  55: 52 */     return 24000;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public float getRange(EntityLivingBase shooter, ItemStack is)
/*  59:    */   {
/*  60: 57 */     return 64.0F;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public int getCooldown(EntityLivingBase shooter, ItemStack is)
/*  64:    */   {
/*  65: 60 */     return this.cooldown + 20;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public boolean canBeUsedByEntity(Entity entity)
/*  69:    */   {
/*  70: 64 */     return true;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public boolean isMeleeWeapon(EntityLivingBase shooter, ItemStack is)
/*  74:    */   {
/*  75: 68 */     return true;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public boolean shouldUpdate(EntityLivingBase shooter)
/*  79:    */   {
/*  80: 72 */     return false;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public void shootFromEntity(EntityLivingBase shooter, ItemStack is, int angle, Entity target)
/*  84:    */   {
/*  85: 76 */     World world = shooter.worldObj;
/*  86: 77 */     world.playSoundEffect((int)shooter.posX, (int)shooter.posY, (int)shooter.posZ, "fire.fire", 4.0F, (1.0F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F) * 0.7F);
/*  87:    */     
/*  88: 79 */     float x = (float)-Math.sin(Math.toRadians(shooter.rotationYaw));
/*  89: 80 */     float z = (float)Math.cos(Math.toRadians(shooter.rotationYaw));
/*  90: 81 */     float y = (float)-Math.sin(Math.toRadians(shooter.rotationPitch));
/*  91: 82 */     x *= (1.0F - Math.abs(y));
/*  92: 83 */     z *= (1.0F - Math.abs(y));
/*  93: 84 */     if (!world.isRemote)
/*  94:    */     {
/*  95: 87 */       PacketSpawnParticlesAround packet = new PacketSpawnParticlesAround((byte)2, shooter.getEntityId(), 0.0D, 0.0D);
/*  96: 88 */       ChocolateQuest.channel.sendToAllAround(shooter, packet, 64);
/*  97:    */     }
/*  98: 91 */     int dist = 15;
/*  99: 92 */     List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(shooter, shooter.boundingBox.addCoord(shooter.getLookVec().xCoord * dist, shooter.getLookVec().yCoord * dist, shooter.getLookVec().zCoord * dist).expand(1.0D, 1.0D, 1.0D));
/* 100: 94 */     for (Entity e : list) {
/* 101: 96 */       if ((e instanceof EntityLiving))
/* 102:    */       {
/* 103: 98 */         double rotDiff = Math.abs(BDHelper.getAngleBetweenEntities(shooter, e));
/* 104: 99 */         double rot = rotDiff - Math.abs(MathHelper.wrapAngleTo180_double(shooter.rotationYaw));
/* 105:100 */         rot = Math.abs(rot);
/* 106:101 */         if (rot < 10.0D)
/* 107:    */         {
/* 108:103 */           e.setFire(6);
/* 109:104 */           e.attackEntityFrom(HelperDamageSource.causeFireDamage(shooter), 4.0F);
/* 110:    */         }
/* 111:    */       }
/* 112:    */     }
/* 113:    */   }
/* 114:    */   
/* 115:    */   public int startAiming(ItemStack is, EntityLivingBase shooter, Entity target)
/* 116:    */   {
/* 117:111 */     return 80;
/* 118:    */   }
/* 119:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemSpearFire

 * JD-Core Version:    0.7.1

 */
