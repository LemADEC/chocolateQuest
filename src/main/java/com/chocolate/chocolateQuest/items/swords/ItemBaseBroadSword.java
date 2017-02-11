/*   1:    */ package com.chocolate.chocolateQuest.items.swords;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.ITwoHandedItem;
/*   4:    */ import com.chocolate.chocolateQuest.magic.Awakements;
/*   5:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   6:    */ import com.google.common.collect.Multimap;
/*   7:    */ import cpw.mods.fml.relauncher.Side;
/*   8:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   9:    */ import java.util.List;
/*  10:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  11:    */ import net.minecraft.entity.Entity;
/*  12:    */ import net.minecraft.entity.EntityLivingBase;
/*  13:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  14:    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*  15:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  16:    */ import net.minecraft.entity.player.EntityPlayer;
/*  17:    */ import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
/*  18:    */ import net.minecraft.item.Item.ToolMaterial;
/*  19:    */ import net.minecraft.item.ItemStack;
/*  20:    */ import net.minecraft.util.AxisAlignedBB;
/*  21:    */ import net.minecraft.util.MathHelper;
/*  22:    */ import net.minecraft.util.Vec3;
/*  23:    */ import net.minecraft.world.World;
/*  24:    */ 
/*  25:    */ public class ItemBaseBroadSword
/*  26:    */   extends ItemBDSword
/*  27:    */   implements ITwoHandedItem
/*  28:    */ {
/*  29:    */   public static final int BASE_DAMAGE = 6;
/*  30:    */   public static final float ELEMENT_MODIFIER = 1.4F;
/*  31:    */   public static final int cooldown = 50;
/*  32:    */   String texture;
/*  33:    */   protected AttributeModifier speedInrease;
/*  34:    */   protected AttributeModifier speedDecreaseSwing;
/*  35:    */   
/*  36:    */   public ItemBaseBroadSword(Item.ToolMaterial mat, String texture)
/*  37:    */   {
/*  38: 35 */     this(mat, texture, 6.0F);
/*  39: 36 */     this.texture = texture;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public ItemBaseBroadSword(Item.ToolMaterial mat, String texture, float baseDamage)
/*  43:    */   {
/*  44: 40 */     super(mat, baseDamage);
/*  45: 41 */     this.texture = texture;
/*  46: 42 */     this.elementModifier = 1.4F;
/*  47: 43 */     this.speedInrease = new AttributeModifier(field_111210_e, "Weapon modifier", 0.24D, 0).setSaved(false);
/*  48:    */   }
/*  49:    */   
/*  50:    */   public ItemBaseBroadSword(Item.ToolMaterial mat, String texture, float baseDamage, float elementModifier)
/*  51:    */   {
/*  52: 47 */     this(mat, texture, baseDamage);
/*  53: 48 */     this.elementModifier = elementModifier;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public Multimap getItemAttributeModifiers()
/*  57:    */   {
/*  58: 54 */     Multimap multimap = super.getItemAttributeModifiers();
/*  59: 55 */     return multimap;
/*  60:    */   }
/*  61:    */   
/*  62:    */   @SideOnly(Side.CLIENT)
/*  63:    */   public void registerIcons(IIconRegister iconRegister)
/*  64:    */   {
/*  65: 62 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:" + this.texture);
/*  66:    */   }
/*  67:    */   
/*  68:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  69:    */   {
/*  70: 68 */     entityPlayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
/*  71:    */     
/*  72: 70 */     return super.onItemRightClick(itemstack, world, entityPlayer);
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityPlayer, int useTime)
/*  76:    */   {
/*  77: 75 */     useTime = getMaxItemUseDuration(itemstack) - useTime;
/*  78: 76 */     useTime = Math.min(useTime + 1, 60);
/*  79: 77 */     this.cachedDamage = (this.weaponAttackDamage * useTime / 30.0F + 1.0F);
/*  80: 78 */     entityPlayer.swingItem();
/*  81: 79 */     boolean hitEntity = false;
/*  82: 80 */     if (!world.isRemote)
/*  83:    */     {
/*  84: 82 */       int dist = 1;
/*  85: 83 */       List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entityPlayer, entityPlayer.boundingBox.addCoord(entityPlayer.getLookVec().xCoord * dist, entityPlayer.getLookVec().yCoord * dist, entityPlayer.getLookVec().zCoord * dist).expand(2.0D, 2.0D, 2.0D));
/*  86: 85 */       for (Entity e : list) {
/*  87: 87 */         if ((e instanceof EntityLivingBase))
/*  88:    */         {
/*  89: 90 */           double rotDiff = Math.abs(BDHelper.getAngleBetweenEntities(entityPlayer, e));
/*  90: 91 */           double rot = rotDiff - Math.abs(MathHelper.wrapAngleTo180_double(entityPlayer.rotationYaw));
/*  91: 92 */           rot = Math.abs(rot);
/*  92: 93 */           if (rot < 40.0D)
/*  93:    */           {
/*  94: 95 */             attackEntityWithItem(entityPlayer, e);
/*  95: 96 */             itemstack.damageItem(1, entityPlayer);
/*  96: 97 */             hitEntity = true;
/*  97:    */           }
/*  98:    */         }
/*  99:    */       }
/* 100:    */     }
/* 101:103 */     this.cachedDamage = 0.0F;
/* 102:104 */     if (world.isRemote)
/* 103:    */     {
/* 104:106 */       float x = (float)-Math.sin(Math.toRadians(entityPlayer.rotationYaw));
/* 105:107 */       float z = (float)Math.cos(Math.toRadians(entityPlayer.rotationYaw));
/* 106:108 */       float y = (float)-Math.sin(Math.toRadians(entityPlayer.rotationPitch));
/* 107:109 */       x *= (1.0F - Math.abs(y));
/* 108:110 */       z *= (1.0F - Math.abs(y));
/* 109:111 */       world.spawnParticle("largeexplode", entityPlayer.posX + x - 0.5D, entityPlayer.posY + y, entityPlayer.posZ + z + 0.0D, 0.0D, 0.0D, 0.0D);
/* 110:    */     }
/* 111:114 */     int j = getMaxItemUseDuration(itemstack) - useTime;
/* 112:115 */     if (j > 50) {
/* 113:117 */       doSpecialSkill(itemstack, world, entityPlayer);
/* 114:    */     }
/* 115:119 */     int jumpLevel = Awakements.getEnchantLevel(itemstack, Awakements.backDodge);
/* 116:120 */     if ((entityPlayer.onGround) && ((useTime > 30) || (hitEntity)))
/* 117:    */     {
/* 118:121 */       entityPlayer.onGround = false;
/* 119:122 */       float rot = entityPlayer.rotationYawHead * 3.1416F / 180.0F;
/* 120:123 */       double mx = -Math.sin(rot);
/* 121:124 */       double mz = Math.cos(rot);
/* 122:125 */       double jumpSpeed = 1 + jumpLevel;
/* 123:126 */       double backSpeed = -Math.abs(entityPlayer.moveForward) * jumpSpeed;
/* 124:127 */       entityPlayer.motionX += mx * backSpeed;
/* 125:128 */       entityPlayer.motionZ += mz * backSpeed;
/* 126:129 */       rot = (float)(rot - 1.57D);
/* 127:130 */       mx = -Math.sin(rot);
/* 128:131 */       mz = Math.cos(rot);
/* 129:132 */       entityPlayer.motionX += mx * entityPlayer.moveStrafing * jumpSpeed;
/* 130:133 */       entityPlayer.motionZ += mz * entityPlayer.moveStrafing * jumpSpeed;
/* 131:134 */       entityPlayer.motionY = 0.3D;
/* 132:    */     }
/* 133:    */   }
/* 134:    */   
/* 135:    */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5)
/* 136:    */   {
/* 137:140 */     EntityLivingBase e = (EntityLivingBase)entity;
/* 138:141 */     ItemStack entityWeapon = null;
/* 139:142 */     if ((e instanceof EntityPlayer)) {
/* 140:143 */       entityWeapon = ((EntityPlayer)entity).getHeldItem();
/* 141:144 */     } else if ((e instanceof EntityLivingBase)) {
/* 142:145 */       entityWeapon = ((EntityLivingBase)entity).getEquipmentInSlot(0);
/* 143:    */     }
/* 144:147 */     if (entityWeapon == itemstack)
/* 145:    */     {
/* 146:148 */       EntityLivingBase tmp55_53 = e;tmp55_53.jumpMovementFactor = ((float)(tmp55_53.jumpMovementFactor - 0.01D));
/* 147:149 */       if ((e.isSwingInProgress) && (e.onGround))
/* 148:    */       {
/* 149:150 */         e.motionX *= 0.4D;
/* 150:151 */         e.motionZ *= 0.4D;
/* 151:    */       }
/* 152:    */     }
/* 153:154 */     if ((entity instanceof EntityPlayer))
/* 154:    */     {
/* 155:156 */       int berserk = Awakements.getEnchantLevel(itemstack, Awakements.berserk);
/* 156:157 */       if (berserk > 0)
/* 157:    */       {
/* 158:159 */         EntityPlayer ep = (EntityPlayer)entity;
/* 159:160 */         double duration = Math.min(ep.getItemInUseDuration(), 30) * berserk / 4;
/* 160:161 */         AttributeModifier speedInrease = new AttributeModifier(field_111210_e, "Weapon modifier", duration / 100.0D, 0).setSaved(false);
/* 161:162 */         AttributeModifier knockBackResist = new AttributeModifier(field_111210_e, "Weapon modifier", 0.25D * berserk, 0).setSaved(false);
/* 162:163 */         if (ep.getCurrentEquippedItem() == itemstack)
/* 163:    */         {
/* 164:164 */           ep.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(speedInrease);
/* 165:165 */           ep.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).removeModifier(knockBackResist);
/* 166:166 */           if (ep.isUsingItem())
/* 167:    */           {
/* 168:168 */             ep.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(speedInrease);
/* 169:169 */             ep.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(knockBackResist);
/* 170:    */           }
/* 171:    */         }
/* 172:171 */         else if ((ep.getCurrentEquippedItem() == null) || (!(ep.getCurrentEquippedItem().getItem() instanceof ItemBaseBroadSword)))
/* 173:    */         {
/* 174:172 */           ep.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(speedInrease);
/* 175:173 */           ep.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).removeModifier(knockBackResist);
/* 176:    */         }
/* 177:    */       }
/* 178:    */     }
/* 179:177 */     super.onUpdate(itemstack, world, entity, par4, par5);
/* 180:    */   }
/* 181:    */   
/* 182:    */   public int getMaxItemUseDuration(ItemStack par1ItemStack)
/* 183:    */   {
/* 184:183 */     return 72000;
/* 185:    */   }
/* 186:    */   
/* 187:    */   public EnumAction getItemUseAction(ItemStack par1ItemStack)
/* 188:    */   {
/* 189:189 */     return EnumAction.bow;
/* 190:    */   }
/* 191:    */   
/* 192:    */   public void doSpecialSkill(ItemStack itemstack, World world, EntityPlayer entityPlayer) {}
/* 193:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemBaseBroadSword

 * JD-Core Version:    0.7.1

 */
