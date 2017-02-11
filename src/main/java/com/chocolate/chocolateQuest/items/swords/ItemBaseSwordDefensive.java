/*   1:    */ package com.chocolate.chocolateQuest.items.swords;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.common.registry.IThrowableEntity;
/*   4:    */ import cpw.mods.fml.relauncher.Side;
/*   5:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   6:    */ import java.util.List;
/*   7:    */ import java.util.Random;
/*   8:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*   9:    */ import net.minecraft.entity.Entity;
/*  10:    */ import net.minecraft.entity.EntityLivingBase;
/*  11:    */ import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;

/*  12:    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*  13:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  14:    */ import net.minecraft.entity.item.EntityItem;
/*  15:    */ import net.minecraft.entity.player.EntityPlayer;
/*  16:    */ import net.minecraft.entity.projectile.EntityArrow;
/*  17:    */ import net.minecraft.entity.projectile.EntityFireball;
/*  18:    */ import net.minecraft.item.EnumRarity;
/*  19:    */ import net.minecraft.item.Item.ToolMaterial;
/*  20:    */ import net.minecraft.item.ItemStack;
/*  21:    */ import net.minecraft.util.AxisAlignedBB;
/*  22:    */ import net.minecraft.util.Vec3;
/*  23:    */ import net.minecraft.world.World;
/*  24:    */ 
/*  25:    */ public class ItemBaseSwordDefensive
/*  26:    */   extends ItemBDSword
/*  27:    */ {
/*  28: 26 */   String texture = "ChocolateQuest:swordDefensive";
/*  29: 27 */   protected final AttributeModifier knockBackShield = new AttributeModifier(field_111210_e, "Weapon modifier", 1.0D, 0).setSaved(false);
/*  30:    */   
/*  31:    */   public ItemBaseSwordDefensive()
/*  32:    */   {
/*  33: 30 */     this(Item.ToolMaterial.IRON, "ChocolateQuest:swordDefensive");
/*  34:    */   }
/*  35:    */   
/*  36:    */   public ItemBaseSwordDefensive(Item.ToolMaterial mat, String texture)
/*  37:    */   {
/*  38: 35 */     super(mat);
/*  39: 36 */     this.texture = texture;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public ItemBaseSwordDefensive(Item.ToolMaterial mat, String texture, int baseDamage, float elementModifier)
/*  43:    */   {
/*  44: 40 */     super(mat, baseDamage);
/*  45: 41 */     this.texture = texture;
/*  46: 42 */     this.elementModifier = elementModifier;
/*  47:    */   }
/*  48:    */   
/*  49: 45 */   int shiedID = 0;
/*  50:    */   
/*  51:    */   public ItemBaseSwordDefensive setShieldId(int id)
/*  52:    */   {
/*  53: 48 */     this.shiedID = id;
/*  54: 49 */     return this;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public int getShieldID(ItemStack is)
/*  58:    */   {
/*  59: 53 */     return this.shiedID;
/*  60:    */   }
/*  61:    */   
/*  62:    */   @SideOnly(Side.CLIENT)
/*  63:    */   public void registerIcons(IIconRegister iconRegister)
/*  64:    */   {
/*  65: 60 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:" + this.texture);
/*  66:    */   }
/*  67:    */   
/*  68:    */   public int getMaxDamage()
/*  69:    */   {
/*  70: 65 */     return 2048;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
/*  74:    */   {
/*  75: 71 */     if ((entity instanceof EntityPlayer))
/*  76:    */     {
/*  77: 73 */       EntityPlayer ep = (EntityPlayer)entity;
/*  78: 74 */       if (ep.getCurrentEquippedItem() == itemStack)
/*  79:    */       {
/*  80: 75 */         ep.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).removeModifier(this.knockBackShield);
/*  81: 76 */         if (ep.isUsingItem()) {
/*  82: 78 */           ep.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(this.knockBackShield);
/*  83:    */         }
/*  84:    */       }
/*  85: 88 */       else if ((ep.getCurrentEquippedItem() == null) || (!(ep.getCurrentEquippedItem().getItem() instanceof ItemBaseSwordDefensive)))
/*  86:    */       {
/*  87: 89 */         ep.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).removeModifier(this.knockBackShield);
/*  88:    */       }
/*  89:    */     }
/*  90: 92 */     super.onUpdate(itemStack, world, entity, par4, par5);
/*  91:    */   }
/*  92:    */   
/*  93:    */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemMaterial)
/*  94:    */   {
/*  95: 97 */     return super.getIsRepairable(itemToRepair, itemMaterial);
/*  96:    */   }
/*  97:    */   
/*  98:    */   public EnumRarity getRarity(ItemStack itemstack)
/*  99:    */   {
/* 100:102 */     return EnumRarity.epic;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public static void projectileDeflection(Entity source, double dist)
/* 104:    */   {
/* 105:106 */     AxisAlignedBB aabb = source.boundingBox.expand(dist - 1.0D, 3.0D, dist - 1.0D);
/* 106:107 */     List<Entity> list = source.worldObj.getEntitiesWithinAABB(Entity.class, aabb);
/* 107:108 */     for (Entity e : list) {
/* 108:110 */       if (e != source)
/* 109:    */       {
/* 110:112 */         Vec3 d = Vec3.createVectorHelper(source.posX - e.posX, source.posY - e.posY - 1.0D, source.posZ - e.posZ);
/* 111:113 */         double distToEntity = Math.max(40.0D, (d.xCoord * d.xCoord + d.zCoord * d.zCoord) * 10.0D);
/* 112:114 */         d.normalize();
/* 113:115 */         double x = d.xCoord / distToEntity;
/* 114:116 */         double y = d.yCoord / Math.max(15.0D, d.yCoord * d.yCoord * 10.0D) / distToEntity;
/* 115:117 */         double z = d.zCoord / distToEntity;
/* 116:    */         
/* 117:119 */         double speedModifier = 1.0D;
/* 118:120 */         double dist3D = source.getDistanceSqToEntity(e) - 2.0D;
/* 119:121 */         if ((dist3D < 10.0D) && (!(e instanceof EntityItem)) && (!(e instanceof EntityLivingBase)) && (e != source.ridingEntity) && (e != source.riddenByEntity))
/* 120:    */         {
/* 121:123 */           if ((e instanceof EntityFireball))
/* 122:    */           {
/* 123:125 */             e.addVelocity(-d.xCoord / dist3D, -d.yCoord / dist3D, -d.zCoord / dist3D);
/* 124:    */           }
/* 125:127 */           else if ((e instanceof IThrowableEntity))
/* 126:    */           {
/* 127:129 */             if (((IThrowableEntity)e).getThrower() != source) {
/* 128:131 */               e.addVelocity(-d.xCoord / dist3D, -d.yCoord / dist3D, -d.zCoord / dist3D);
/* 129:    */             }
/* 130:    */           }
/* 131:134 */           else if ((e instanceof EntityArrow))
/* 132:    */           {
/* 133:136 */             dist3D = 4.0D;
/* 134:137 */             e.addVelocity(-d.xCoord / dist3D, -d.yCoord / dist3D, -d.zCoord / dist3D);
/* 135:    */           }
/* 136:    */           else
/* 137:    */           {
/* 138:141 */             e.addVelocity(-d.xCoord / dist3D * 4.0D, -d.yCoord / dist3D * 4.0D, -d.zCoord / dist3D * 4.0D);
/* 139:    */           }
/* 140:143 */           if (source.worldObj.isRemote) {
/* 141:145 */             source.worldObj.spawnParticle("enchantmenttable", e.posX + itemRand.nextFloat() - 0.5D, e.posY + itemRand.nextFloat() - 0.5D, e.posZ + itemRand.nextFloat() - 0.5D, d.xCoord, d.yCoord, d.zCoord);
/* 142:    */           }
/* 143:    */         }
/* 144:    */       }
/* 145:    */     }
/* 146:    */   }
/* 147:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemBaseSwordDefensive

 * JD-Core Version:    0.7.1

 */
