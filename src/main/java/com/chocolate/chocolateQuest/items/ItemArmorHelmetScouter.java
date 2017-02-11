/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.utils.HelperPlayer;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.entity.player.EntityPlayer;
/*  7:   */ import net.minecraft.item.EnumRarity;
/*  8:   */ import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemArmor;

/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ import net.minecraft.potion.Potion;
/* 11:   */ import net.minecraft.potion.PotionEffect;
/* 12:   */ import net.minecraft.util.MathHelper;
/* 13:   */ import net.minecraft.util.MovingObjectPosition;
/* 14:   */ import net.minecraft.world.World;
/* 15:   */ 
/* 16:   */ public class ItemArmorHelmetScouter
/* 17:   */   extends ItemArmorBase
/* 18:   */ {
/* 19:   */   public static EntityLivingBase target;
/* 20:   */   public static int targetTimer;
/* 21:21 */   final int reachDistance = 40;
/* 22:   */   
/* 23:   */   public ItemArmorHelmetScouter()
/* 24:   */   {
/* 25:25 */     super(ItemArmor.ArmorMaterial.CLOTH, 0);
/* 26:26 */     setMaxDamage(650);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void onUpdateEquiped(ItemStack par1ItemStack, World world, EntityLivingBase entity)
/* 30:   */   {
/* 31:32 */     if ((entity instanceof EntityPlayer))
/* 32:   */     {
/* 33:34 */       EntityPlayer ep = (EntityPlayer)entity;
/* 34:   */       
/* 35:36 */       MovingObjectPosition lookingBlock = HelperPlayer.getMovingObjectPositionFromPlayer(ep, world, 40.0D);
/* 36:37 */       float bright = 0.0F;
/* 37:38 */       if (lookingBlock != null)
/* 38:   */       {
/* 39:40 */         int i = lookingBlock.blockX;
/* 40:41 */         int j = lookingBlock.blockY;
/* 41:42 */         int k = lookingBlock.blockZ;
/* 42:43 */         switch (lookingBlock.sideHit)
/* 43:   */         {
/* 44:   */         case 1: 
/* 45:46 */           j++; break;
/* 46:   */         case 0: 
/* 47:48 */           j--; break;
/* 48:   */         case 2: 
/* 49:50 */           k--; break;
/* 50:   */         case 3: 
/* 51:52 */           k++; break;
/* 52:   */         case 5: 
/* 53:54 */           i++; break;
/* 54:   */         case 4: 
/* 55:56 */           i--;
/* 56:   */         }
/* 57:58 */         bright = world.getLightBrightness(i, j, k);
/* 58:   */       }
/* 59:   */       else
/* 60:   */       {
/* 61:60 */         bright = world.getLightBrightness(MathHelper.floor_double(ep.posX), MathHelper.floor_double(ep.posY), MathHelper.floor_double(ep.posZ));
/* 62:   */       }
/* 63:61 */       if (bright < 0.3D) {
/* 64:62 */         ep.addPotionEffect(new PotionEffect(Potion.nightVision.id, 3, 0));
/* 65:   */       }
/* 66:64 */       if (world.isRemote)
/* 67:   */       {
/* 68:66 */         Entity mop = HelperPlayer.getTarget(ep, world, 40.0D);
/* 69:68 */         if (mop != null) {
/* 70:70 */           if ((mop instanceof EntityLivingBase))
/* 71:   */           {
/* 72:72 */             EntityLivingBase el = (EntityLivingBase)mop;
/* 73:73 */             target = el;
/* 74:74 */             targetTimer = 80;
/* 75:   */           }
/* 76:   */         }
/* 77:   */       }
/* 78:   */     }
/* 79:   */   }
/* 80:   */   
/* 81:   */   public String getItemStackDisplayName(ItemStack itemstack)
/* 82:   */   {
/* 83:84 */     return super.getItemStackDisplayName(itemstack);
/* 84:   */   }
/* 85:   */   
/* 86:   */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/* 87:   */   {
/* 88:90 */     return "chocolatequest:textures/entity/cloud_1.png";
/* 89:   */   }
/* 90:   */   
/* 91:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 92:   */   {
/* 93:95 */     return EnumRarity.rare;
/* 94:   */   }
/* 95:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorHelmetScouter

 * JD-Core Version:    0.7.1

 */
