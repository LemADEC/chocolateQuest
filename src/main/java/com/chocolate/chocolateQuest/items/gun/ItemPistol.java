/*   1:    */ package com.chocolate.chocolateQuest.items.gun;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.IRangedWeapon;
/*   4:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   5:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   6:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*   7:    */ import com.chocolate.chocolateQuest.gui.InventoryBag;
/*   8:    */ import com.chocolate.chocolateQuest.magic.Awakements;
/*   9:    */ import cpw.mods.fml.relauncher.Side;
/*  10:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  11:    */ import java.util.List;
/*  12:    */ import java.util.Random;
/*  13:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  14:    */ import net.minecraft.entity.Entity;
/*  15:    */ import net.minecraft.entity.EntityLivingBase;
/*  16:    */ import net.minecraft.entity.player.EntityPlayer;
/*  17:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*  18:    */ import net.minecraft.item.EnumRarity;
/*  19:    */ import net.minecraft.item.Item;
/*  20:    */ import net.minecraft.item.ItemStack;
/*  21:    */ import net.minecraft.world.World;
/*  22:    */ 
/*  23:    */ public class ItemPistol
/*  24:    */   extends Item
/*  25:    */   implements IRangedWeapon, ILoadableGun
/*  26:    */ {
/*  27: 27 */   final int NONE = -1;
/*  28: 28 */   int cooldownBase = 10;
/*  29:    */   final float accuracy;
/*  30:    */   final float range;
/*  31:    */   
/*  32:    */   public ItemPistol()
/*  33:    */   {
/*  34: 32 */     this(10, 100.0F, 10.0F);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public ItemPistol(int cooldown, float range, float accuracy)
/*  38:    */   {
/*  39: 37 */     this.cooldownBase = cooldown;
/*  40: 38 */     this.range = range;
/*  41: 39 */     this.accuracy = accuracy;
/*  42: 40 */     setMaxStackSize(1);
/*  43:    */   }
/*  44:    */   
/*  45:    */   @SideOnly(Side.CLIENT)
/*  46:    */   public void registerIcons(IIconRegister iconRegister)
/*  47:    */   {
/*  48: 46 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:revolver");
/*  49:    */   }
/*  50:    */   
/*  51:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  52:    */   {
/*  53: 51 */     if ((entityPlayer.isSneaking()) && (!freeAmmo()))
/*  54:    */     {
/*  55: 52 */       entityPlayer.openGui(ChocolateQuest.instance, 3, entityPlayer.worldObj, 0, 0, 0);
/*  56: 53 */       return itemstack;
/*  57:    */     }
/*  58: 55 */     if (itemstack.getItemDamage() == 0) {
/*  59: 57 */       if (shoot(itemstack, world, entityPlayer)) {
/*  60: 59 */         itemstack.setItemDamage(getCooldown(itemstack));
/*  61:    */       }
/*  62:    */     }
/*  63: 62 */     return super.onItemRightClick(itemstack, world, entityPlayer);
/*  64:    */   }
/*  65:    */   
/*  66:    */   public boolean shoot(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  67:    */   {
/*  68: 67 */     int NONE = -1;
/*  69: 68 */     int bulletType = getAmmo(itemstack, entityPlayer);
/*  70: 69 */     if (freeAmmo()) {
/*  71: 70 */       bulletType = 1;
/*  72:    */     }
/*  73: 71 */     if ((entityPlayer.capabilities.isCreativeMode) && (bulletType == -1)) {
/*  74: 72 */       bulletType = 1;
/*  75:    */     }
/*  76: 73 */     if (bulletType != -1)
/*  77:    */     {
/*  78: 75 */       if (!world.isRemote)
/*  79:    */       {
/*  80: 76 */         EntityBaseBall ball = new EntityBaseBall(world, entityPlayer, 1, bulletType);
/*  81: 77 */         float accuracy = this.accuracy / 100.0F;
/*  82: 78 */         ball.motionX += itemRand.nextGaussian() * accuracy;
/*  83: 79 */         ball.motionY += itemRand.nextGaussian() * accuracy;
/*  84: 80 */         ball.motionZ += itemRand.nextGaussian() * accuracy;
/*  85: 81 */         int power = Awakements.getEnchantLevel(itemstack, Awakements.power);
/*  86: 82 */         ball.setDamageMultiplier(1.0F + power / 10.0F);
/*  87: 83 */         world.spawnEntityInWorld(ball);
/*  88:    */       }
/*  89: 85 */       return true;
/*  90:    */     }
/*  91: 87 */     return false;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public boolean freeAmmo()
/*  95:    */   {
/*  96: 93 */     return false;
/*  97:    */   }
/*  98:    */   
/*  99:    */   protected int getAmmo(ItemStack itemstack, EntityPlayer entityPlayer)
/* 100:    */   {
/* 101: 97 */     int bulletType = -1;
/* 102: 98 */     ItemStack[] ammo = InventoryBag.getCargo(itemstack);
/* 103: 99 */     for (int i = 0; i < ammo.length; i++) {
/* 104:101 */       if ((ammo[i] != null) && 
/* 105:102 */         (ammo[i].getItem() == ChocolateQuest.bullet))
/* 106:    */       {
/* 107:104 */         bulletType = ammo[i].getItemDamage();
/* 108:105 */         int ammoSaver = Awakements.getEnchantLevel(itemstack, Awakements.ammoSaver);
/* 109:106 */         if ((entityPlayer.capabilities.isCreativeMode) || (
/* 110:107 */           (ammoSaver != 0) && (itemRand.nextInt(2 + ammoSaver) < 2))) {
/* 111:    */           break;
/* 112:    */         }
/* 113:108 */         ammo[i].stackSize -= 1;
/* 114:109 */         if (ammo[i].stackSize <= 0) {
/* 115:110 */           ammo[i] = null;
/* 116:    */         }
/* 117:111 */         InventoryBag.saveCargo(itemstack, ammo); break;
/* 118:    */       }
/* 119:    */     }
/* 120:118 */     return bulletType;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public int getAmmoLoaderStackSize(ItemStack is)
/* 124:    */   {
/* 125:122 */     return 8;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public int getAmmoLoaderAmmount(ItemStack is)
/* 129:    */   {
/* 130:126 */     int loaders = Awakements.getEnchantLevel(is, Awakements.ammoCapacity);
/* 131:127 */     return 1 + loaders;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public boolean isValidAmmo(ItemStack is)
/* 135:    */   {
/* 136:131 */     if (is == null) {
/* 137:132 */       return false;
/* 138:    */     }
/* 139:133 */     return is.getItem() == ChocolateQuest.bullet;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void addInformation(ItemStack is, EntityPlayer player, List list, boolean par4)
/* 143:    */   {
/* 144:138 */     super.addInformation(is, player, list, par4);
/* 145:139 */     for (Awakements a : Awakements.awekements) {
/* 146:140 */       if (Awakements.hasEnchant(is, a)) {
/* 147:141 */         list.add(a.getDescription(is));
/* 148:    */       }
/* 149:    */     }
/* 150:    */   }
/* 151:    */   
/* 152:    */   public EnumRarity getRarity(ItemStack itemstack)
/* 153:    */   {
/* 154:147 */     return EnumRarity.rare;
/* 155:    */   }
/* 156:    */   
/* 157:    */   public boolean isDamageable()
/* 158:    */   {
/* 159:152 */     return true;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
/* 163:    */   {
/* 164:157 */     if (itemStack.getItemDamage() > 0) {
/* 165:159 */       itemStack.setItemDamage(itemStack.getItemDamage() - 1);
/* 166:    */     }
/* 167:161 */     super.onUpdate(itemStack, world, entity, par4, par5);
/* 168:    */   }
/* 169:    */   
/* 170:    */   public int getMaxDamage()
/* 171:    */   {
/* 172:166 */     return this.cooldownBase;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public boolean shouldRotateAroundWhenRendering()
/* 176:    */   {
/* 177:170 */     return false;
/* 178:    */   }
/* 179:    */   
/* 180:    */   public boolean isFull3D()
/* 181:    */   {
/* 182:174 */     return false;
/* 183:    */   }
/* 184:    */   
/* 185:    */   public int getCooldown(ItemStack is)
/* 186:    */   {
/* 187:177 */     return this.cooldownBase;
/* 188:    */   }
/* 189:    */   
/* 190:    */   public float getRange(EntityLivingBase shooter, ItemStack is)
/* 191:    */   {
/* 192:182 */     return this.range;
/* 193:    */   }
/* 194:    */   
/* 195:    */   public int getCooldown(EntityLivingBase shooter, ItemStack is)
/* 196:    */   {
/* 197:186 */     return this.cooldownBase;
/* 198:    */   }
/* 199:    */   
/* 200:    */   public void shootFromEntity(EntityLivingBase shooter, ItemStack is, int angle, Entity target)
/* 201:    */   {
/* 202:190 */     if (!shooter.worldObj.isRemote)
/* 203:    */     {
/* 204:192 */       double armDist = 2.0D;
/* 205:193 */       double posX = shooter.posX - Math.sin(Math.toRadians(shooter.rotationYaw + angle)) * 2.0D;
/* 206:194 */       double posY = shooter.posY + 1.6D;
/* 207:195 */       double posZ = shooter.posZ + Math.cos(Math.toRadians(shooter.rotationYaw + angle)) * 2.0D;
/* 208:    */       EntityBaseBall ball;
/* 210:197 */       if (target != null)
/* 211:    */       {
/* 212:198 */         ball = getBall(shooter.worldObj, shooter, target.posX - posX, target.posY + target.height - posY, target.posZ - posZ);
/* 213:    */       }
/* 214:    */       else
/* 215:    */       {
/* 216:204 */         double ry = Math.toRadians(shooter.rotationYaw - 180.0F);
/* 217:205 */         double x = Math.sin(ry);
/* 218:206 */         double z = -Math.cos(ry);
/* 219:207 */         double y = -Math.sin(Math.toRadians(shooter.rotationPitch * 2.0F - 1.0F));
/* 220:208 */         ball = getBall(shooter.worldObj, shooter, x, y, z);
/* 221:209 */         ball.posY -= shooter.height / 2.0F;
/* 222:    */       }
/* 223:211 */       ball.setPosition(posX, posY, posZ);
/* 224:212 */       shooter.worldObj.spawnEntityInWorld(ball);
/* 225:    */     }
/* 226:    */   }
/* 227:    */   
/* 228:    */   public EntityBaseBall getBall(World world, EntityLivingBase shooter, double x, double y, double z)
/* 229:    */   {
/* 230:216 */     float accuracy = 5.0F;
/* 231:217 */     if ((shooter instanceof EntityHumanBase)) {
/* 232:218 */       accuracy += ((EntityHumanBase)shooter).accuracy;
/* 233:    */     }
/* 234:219 */     return new EntityBaseBall(shooter.worldObj, shooter, x, y, z, 1, 1, accuracy);
/* 235:    */   }
/* 236:    */   
/* 237:    */   public boolean canBeUsedByEntity(Entity entity)
/* 238:    */   {
/* 239:223 */     return true;
/* 240:    */   }
/* 241:    */   
/* 242:    */   public boolean isMeleeWeapon(EntityLivingBase shooter, ItemStack is)
/* 243:    */   {
/* 244:227 */     return false;
/* 245:    */   }
/* 246:    */   
/* 247:    */   public boolean shouldUpdate(EntityLivingBase shooter)
/* 248:    */   {
/* 249:232 */     return false;
/* 250:    */   }
/* 251:    */   
/* 252:    */   public int startAiming(ItemStack is, EntityLivingBase shooter, Entity target)
/* 253:    */   {
/* 254:236 */     return this.cooldownBase + 10;
/* 255:    */   }
/* 256:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.gun.ItemPistol

 * JD-Core Version:    0.7.1

 */
