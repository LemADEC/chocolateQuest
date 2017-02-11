/*  1:   */ package com.chocolate.chocolateQuest.items.gun;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  4:   */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMecha;
/*  5:   */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  6:   */ import cpw.mods.fml.relauncher.Side;
/*  7:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  8:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  9:   */ import net.minecraft.entity.Entity;
/* 10:   */ import net.minecraft.entity.EntityLivingBase;
/* 11:   */ import net.minecraft.item.ItemStack;
/* 12:   */ import net.minecraft.world.World;
/* 13:   */ 
/* 14:   */ public class ItemGolemWeapon
/* 15:   */   extends ItemPistol
/* 16:   */ {
/* 17:   */   final int projectileDamage;
/* 18:   */   final int ammoCapacity;
/* 19:   */   
/* 20:   */   public ItemGolemWeapon()
/* 21:   */   {
/* 22:22 */     this(10, 16.0F, 5.0F);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public ItemGolemWeapon(int cooldown, float range, float accuracy)
/* 26:   */   {
/* 27:25 */     this(cooldown, range, accuracy, 1, 16);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public ItemGolemWeapon(int cooldown, float range, float accuracy, int lvl, int capacity)
/* 31:   */   {
/* 32:28 */     super(cooldown, range * range, accuracy);
/* 33:29 */     this.projectileDamage = lvl;
/* 34:30 */     this.ammoCapacity = capacity;
/* 35:   */   }
/* 36:   */   
/* 37:   */   @SideOnly(Side.CLIENT)
/* 38:   */   public void registerIcons(IIconRegister iconRegister) {}
/* 39:   */   
/* 40:   */   public void shootFromEntity(EntityLivingBase shooter, ItemStack is, int angle, Entity target)
/* 41:   */   {
/* 42:40 */     shootFromGolem(shooter, is, angle, target);
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void shootFromGolem(EntityLivingBase shooter, ItemStack is, int angle, Entity target)
/* 46:   */   {
/* 47:44 */     if (!shooter.worldObj.isRemote)
/* 48:   */     {
/* 49:46 */       double armDist = shooter.width * 2.0F;
/* 50:47 */       double posX = shooter.posX - Math.sin(Math.toRadians(shooter.rotationYawHead + angle)) * armDist;
/* 51:48 */       double posY = shooter.posY + 1.6D;
/* 52:49 */       double posZ = shooter.posZ + Math.cos(Math.toRadians(shooter.rotationYawHead + angle)) * armDist;
/* 53:   */       EntityBaseBall ball;
/* 55:51 */       if (target != null)
/* 56:   */       {
/* 57:52 */         ball = getBall(shooter.worldObj, shooter, target.posX - posX, target.posY + target.height - posY, target.posZ - posZ);
/* 58:   */       }
/* 59:   */       else
/* 60:   */       {
/* 61:58 */         double ry = Math.toRadians(shooter.rotationYawHead - 180.0F);
/* 62:59 */         double x = Math.sin(ry);
/* 63:60 */         double z = -Math.cos(ry);
/* 64:61 */         double y = -Math.sin(Math.toRadians(shooter.rotationPitch * 2.0F - 1.0F));
/* 65:62 */         ball = getBall(shooter.worldObj, shooter, x, y, z);
/* 66:63 */         ball.posY -= shooter.height / 2.0F;
/* 67:   */       }
/* 68:65 */       ball.setPosition(posX, posY, posZ);
/* 69:66 */       shooter.worldObj.spawnEntityInWorld(ball);
/* 70:   */     }
/* 71:   */   }
/* 72:   */   
/* 73:   */   public EntityBaseBall getBall(World world, EntityLivingBase shooter, double x, double y, double z)
/* 74:   */   {
/* 75:72 */     float accuracy = this.accuracy;
/* 76:73 */     byte projectile = 1;
/* 77:74 */     if ((shooter instanceof EntityHumanBase))
/* 78:   */     {
/* 79:75 */       accuracy += ((EntityHumanBase)shooter).accuracy;
/* 80:76 */       if ((shooter instanceof EntityGolemMecha)) {
/* 81:77 */         projectile = 2;
/* 82:   */       }
/* 83:   */     }
/* 84:80 */     return new EntityBaseBall(shooter.worldObj, shooter, x, y, z, projectile, 0, accuracy);
/* 85:   */   }
/* 86:   */   
/* 87:   */   public void onUpdate(ItemStack is, World world, Entity entity, int par4, boolean par5)
/* 88:   */   {
/* 89:85 */     super.onUpdate(is, world, entity, par4, par5);
/* 90:   */   }
/* 91:   */   
/* 92:   */   public int getCooldown(EntityLivingBase shooter, ItemStack is)
/* 93:   */   {
/* 94:89 */     if ((shooter instanceof EntityGolemMecha)) {
/* 95:90 */       return Math.max(this.cooldownBase - 10, 0);
/* 96:   */     }
/* 97:91 */     return this.cooldownBase;
/* 98:   */   }
/* 99:   */   
/* :0:   */   public int getAmmoLoaderStackSize(ItemStack is)
/* :1:   */   {
/* :2:95 */     return this.ammoCapacity;
/* :3:   */   }
/* :4:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.gun.ItemGolemWeapon

 * JD-Core Version:    0.7.1

 */
