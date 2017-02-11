/*   1:    */ package com.chocolate.chocolateQuest;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.items.ItemArmorBase;
/*   4:    */ import com.chocolate.chocolateQuest.items.swords.ItemBaseSwordDefensive;
/*   5:    */ import com.chocolate.chocolateQuest.magic.Awakements;
/*   6:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   7:    */ import com.chocolate.chocolateQuest.packets.PacketEntityAnimation;
/*   8:    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*   9:    */ import net.minecraft.entity.Entity;
/*  10:    */ import net.minecraft.entity.EntityLivingBase;
/*  11:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  12:    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*  13:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  14:    */ import net.minecraft.entity.player.EntityPlayer;
/*  15:    */ import net.minecraft.entity.projectile.EntityArrow;
/*  16:    */ import net.minecraft.entity.projectile.EntityThrowable;
/*  17:    */ import net.minecraft.item.ItemStack;
/*  18:    */ import net.minecraft.nbt.NBTTagCompound;
/*  19:    */ import net.minecraft.util.DamageSource;
/*  20:    */ import net.minecraft.util.FoodStats;
/*  21:    */ import net.minecraft.util.Vec3;
/*  22:    */ import net.minecraft.world.World;
/*  23:    */ import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEvent;
/*  24:    */ import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
/*  25:    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*  26:    */ 
/*  27:    */ public class EventHandlerCQ
/*  28:    */ {
/*  29:    */   @SubscribeEvent
/*  30:    */   public void onLivingJump(LivingEvent.LivingJumpEvent event)
/*  31:    */   {
/*  32: 30 */     ItemStack is = event.entityLiving.getEquipmentInSlot(1);
/*  33: 31 */     if (is != null)
/*  34:    */     {
/*  35: 33 */       if (is.getItem() == ChocolateQuest.cloudBoots)
/*  36:    */       {
/*  37: 35 */         EntityLivingBase entity = event.entityLiving;
/*  38: 36 */         if (!entity.isSneaking()) {
/*  39: 37 */           entity.motionY += 0.4D;
/*  40:    */         }
/*  41:    */       }
/*  42: 39 */       if (is.getItem() == ChocolateQuest.slimeBoots)
/*  43:    */       {
/*  44: 41 */         EntityLivingBase entity = event.entityLiving;
/*  45: 42 */         if (!entity.isSneaking()) {
/*  46: 43 */           entity.motionY += 0.1D;
/*  47:    */         }
/*  48:    */       }
/*  49:    */     }
/*  50:    */   }
/*  51:    */   
/*  52:    */   @SubscribeEvent
/*  53:    */   public void onLivingHurt(LivingHurtEvent event)
/*  54:    */   {
/*  55: 58 */     Entity e = event.entity;
/*  56: 59 */     if ((e instanceof EntityLivingBase))
/*  57:    */     {
/*  58: 61 */       if ((e instanceof EntityPlayer))
/*  59:    */       {
/*  60: 63 */         EntityPlayer ep = (EntityPlayer)e;
/*  61: 64 */         if (ep.isBlocking()) {
/*  62: 66 */           if (!event.source.isUnblockable()) {
/*  63: 68 */             if ((ep.getCurrentEquippedItem() != null) && ((ep.getCurrentEquippedItem().getItem() instanceof ItemBaseSwordDefensive)))
/*  64:    */             {
/*  65: 70 */               int useTime = ep.getItemInUseDuration();
/*  66: 71 */               if (useTime > 0)
/*  67:    */               {
/*  68: 72 */                 ItemStack is = ep.getCurrentEquippedItem();
/*  69: 73 */                 if (useTime < 5)
/*  70:    */                 {
/*  71: 74 */                   event.ammount = 0.0F;
/*  72: 75 */                   event.setCanceled(false);
/*  73: 76 */                   Entity sourceEntity = event.source.getEntity();
/*  74: 77 */                   Entity sourceProjectile = event.source.getSourceOfDamage();
/*  75: 78 */                   if ((sourceProjectile instanceof EntityArrow))
/*  76:    */                   {
/*  77: 81 */                     Vec3 v = ep.getLookVec();
/*  78: 82 */                     NBTTagCompound tag = new NBTTagCompound();
/*  79: 83 */                     sourceProjectile.writeToNBT(tag);
/*  80: 84 */                     EntityArrow newArrow = new EntityArrow(ep.worldObj);
/*  81: 85 */                     newArrow.readEntityFromNBT(tag);
/*  82: 86 */                     newArrow.setPosition(ep.posX, ep.posY + 1.0D, ep.posZ);
/*  83: 87 */                     newArrow.setThrowableHeading(-v.xCoord * 20.0D, -v.yCoord * 20.0D, -v.zCoord * 20.0D, 10.0F, 0.0F);
/*  84: 88 */                     ep.worldObj.spawnEntityInWorld(newArrow);
/*  85:    */                   }
/*  86: 89 */                   else if (!(sourceProjectile instanceof EntityThrowable))
/*  87:    */                   {
/*  88: 92 */                     if (sourceEntity != null)
/*  89:    */                     {
/*  90: 93 */                       ep.fallDistance += 1.0F;
/*  91: 94 */                       ep.onGround = false;
/*  92: 95 */                       double damage = 4 + Awakements.getEnchantLevel(is, Awakements.parryDamage) * 2;
/*  93: 96 */                       AttributeModifier attack = new AttributeModifier("parryMod", damage, 0);
/*  94: 97 */                       ep.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(attack);
/*  95: 98 */                       ep.attackTargetEntityWithCurrentItem(sourceEntity);
/*  96: 99 */                       ep.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(attack);
/*  97:    */                     }
/*  98:    */                   }
/*  99:101 */                   if ((ep.worldObj instanceof WorldServer))
/* 100:    */                   {
/* 101:103 */                     PacketEntityAnimation packet = new PacketEntityAnimation(ep.getEntityId(), (byte)0);
/* 102:104 */                     ChocolateQuest.channel.sendToAllAround(ep, packet);
/* 103:    */                   }
/* 104:    */                 }
/* 105:108 */                 int resistLevel = Awakements.getEnchantLevel(is, Awakements.blockStamina);
/* 106:109 */                 FoodStats food = ep.getFoodStats();
/* 107:110 */                 if (food.getFoodLevel() > 1)
/* 108:    */                 {
/* 109:111 */                   float ammount = event.ammount / resistLevel;
/* 110:112 */                   food.addExhaustion(ammount);
/* 111:113 */                   event.ammount = 0.0F;
/* 112:114 */                   event.setCanceled(true);
/* 113:    */                 }
/* 114:    */                 else
/* 115:    */                 {
/* 116:116 */                   event.ammount /= (2 + resistLevel);
/* 117:    */                 }
/* 118:    */               }
/* 119:    */             }
/* 120:    */           }
/* 121:    */         }
/* 122:    */       }
/* 123:122 */       EntityLivingBase el = (EntityLivingBase)e;
/* 124:123 */       for (int i = 0; i < 4; i++)
/* 125:    */       {
/* 126:124 */         ItemStack armor = el.getEquipmentInSlot(1 + i);
/* 127:125 */         if ((armor != null) && ((armor.getItem() instanceof ItemArmorBase))) {
/* 128:126 */           ((ItemArmorBase)armor.getItem()).onHit(event, armor, el);
/* 129:    */         }
/* 130:    */       }
/* 131:    */     }
/* 132:    */   }
/* 133:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.EventHandlerCQ

 * JD-Core Version:    0.7.1

 */
