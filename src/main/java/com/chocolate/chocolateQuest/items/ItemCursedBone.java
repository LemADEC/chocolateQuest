/*   1:    */ package com.chocolate.chocolateQuest.items;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.IRangedWeapon;
/*   4:    */ import com.chocolate.chocolateQuest.entity.EntitySummonedUndead;
/*   5:    */ import com.chocolate.chocolateQuest.entity.mob.EntityLich;
/*   6:    */ import com.chocolate.chocolateQuest.entity.mob.EntityNecromancer;
/*   7:    */ import cpw.mods.fml.relauncher.Side;
/*   8:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   9:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  10:    */ import net.minecraft.entity.Entity;
/*  11:    */ import net.minecraft.entity.EntityLivingBase;
/*  12:    */ import net.minecraft.entity.player.EntityPlayer;
/*  13:    */ import net.minecraft.item.EnumRarity;
/*  14:    */ import net.minecraft.item.Item;
/*  15:    */ import net.minecraft.item.ItemStack;
/*  16:    */ import net.minecraft.util.DamageSource;
/*  17:    */ import net.minecraft.util.Vec3;
/*  18:    */ import net.minecraft.world.World;
/*  19:    */ 
/*  20:    */ public class ItemCursedBone
/*  21:    */   extends Item
/*  22:    */   implements IRangedWeapon
/*  23:    */ {
/*  24:    */   public ItemCursedBone()
/*  25:    */   {
/*  26: 27 */     setMaxStackSize(1);
/*  27: 28 */     setMaxDamage(8);
/*  28:    */   }
/*  29:    */   
/*  30:    */   @SideOnly(Side.CLIENT)
/*  31:    */   public void registerIcons(IIconRegister iconRegister)
/*  32:    */   {
/*  33: 36 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:cursedBone");
/*  34:    */   }
/*  35:    */   
/*  36:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  37:    */   {
/*  38: 42 */     if (!world.isRemote)
/*  39:    */     {
/*  40: 44 */       EntitySummonedUndead e = new EntitySummonedUndead(world, entityPlayer, 0);
/*  41:    */       
/*  42: 46 */       e.setlvl((byte)2);
/*  43: 47 */       double dist = 3.0D;
/*  44: 48 */       Vec3 dest = Vec3.createVectorHelper(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
/*  45: 49 */       Vec3 look = entityPlayer.getLookVec();
/*  46: 50 */       dest = dest.addVector(look.xCoord * dist, look.yCoord * dist, look.zCoord * dist);
/*  47: 51 */       e.setPosition(dest.xCoord, dest.yCoord + 1.0D, dest.zCoord);
/*  48: 52 */       world.spawnEntityInWorld(e);
/*  49: 53 */       itemstack.damageItem(1, entityPlayer);
/*  50: 54 */       entityPlayer.attackEntityFrom(DamageSource.wither, 2.0F);
/*  51:    */     }
/*  52: 57 */     return itemstack;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public EnumRarity getRarity(ItemStack itemstack)
/*  56:    */   {
/*  57: 62 */     return EnumRarity.uncommon;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public float getRange(EntityLivingBase shooter, ItemStack is)
/*  61:    */   {
/*  62: 69 */     return 256.0F;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public int getCooldown(EntityLivingBase shooter, ItemStack is)
/*  66:    */   {
/*  67: 73 */     if (((shooter instanceof EntityNecromancer)) || ((shooter instanceof EntityLich))) {
/*  68: 74 */       return 150;
/*  69:    */     }
/*  70: 75 */     return 300;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void shootFromEntity(EntityLivingBase shooter, ItemStack is, int angle, Entity target)
/*  74:    */   {
/*  75: 79 */     World world = shooter.worldObj;
/*  76: 80 */     EntitySummonedUndead e = new EntitySummonedUndead(world, shooter, 0);
/*  77:    */     
/*  78: 82 */     e.setlvl((byte)is.getItemDamage());
/*  79: 83 */     e.setPosition(shooter.posX, shooter.posY, shooter.posZ);
/*  80: 84 */     world.spawnEntityInWorld(e);
/*  81:    */   }
/*  82:    */   
/*  83:    */   public boolean canBeUsedByEntity(Entity shooter)
/*  84:    */   {
/*  85: 88 */     return true;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public boolean isMeleeWeapon(EntityLivingBase shooter, ItemStack is)
/*  89:    */   {
/*  90: 92 */     return false;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public boolean shouldUpdate(EntityLivingBase shooter)
/*  94:    */   {
/*  95: 96 */     return false;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public int startAiming(ItemStack is, EntityLivingBase shooter, Entity target)
/*  99:    */   {
/* 100:100 */     return 30;
/* 101:    */   }
/* 102:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemCursedBone
 * JD-Core Version:    0.7.1
 */