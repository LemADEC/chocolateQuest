/*   1:    */ package com.chocolate.chocolateQuest.items;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.client.ClientProxy;
/*   5:    */ import cpw.mods.fml.relauncher.Side;
/*   6:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   7:    */ import java.util.Random;
/*   8:    */ import net.minecraft.client.model.ModelBiped;
/*   9:    */ import net.minecraft.entity.Entity;
/*  10:    */ import net.minecraft.entity.EntityLivingBase;
/*  11:    */ import net.minecraft.item.EnumRarity;
/*  12:    */ import net.minecraft.item.ItemStack;
/*  13:    */ import net.minecraft.nbt.NBTTagCompound;
/*  14:    */ import net.minecraft.util.DamageSource;
/*  15:    */ import net.minecraft.world.World;
/*  16:    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*  17:    */ 
/*  18:    */ public class ItemArmorTurtle
/*  19:    */   extends ItemArmorBase
/*  20:    */ {
/*  21:    */   int type;
/*  22:    */   String name;
/*  23:    */   
/*  24:    */   public ItemArmorTurtle(int type, String name)
/*  25:    */   {
/*  26: 26 */     super(ItemArmorBase.TURTLE, type);
/*  27: 27 */     this.type = type;
/*  28: 28 */     this.name = name;
/*  29: 29 */     setEpic();
/*  30:    */   }
/*  31:    */   
/*  32:    */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/*  33:    */   {
/*  34: 34 */     if (((ItemArmorTurtle)stack.getItem()).type == 2) {
/*  35: 36 */       return "chocolatequest:textures/entity/turtle_2.png";
/*  36:    */     }
/*  37: 38 */     return "chocolatequest:textures/entity/turtle_1.png";
/*  38:    */   }
/*  39:    */   
/*  40:    */   public EnumRarity getRarity(ItemStack itemstack)
/*  41:    */   {
/*  42: 43 */     return EnumRarity.epic;
/*  43:    */   }
/*  44:    */   
/*  45:    */   @SideOnly(Side.CLIENT)
/*  46:    */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/*  47:    */   {
/*  48: 50 */     if (armorSlot == 1) {
/*  49: 51 */       return ClientProxy.turtleArmorModel;
/*  50:    */     }
/*  51: 52 */     if (armorSlot == 0) {
/*  52: 53 */       return ClientProxy.turtleHelmetModel;
/*  53:    */     }
/*  54: 54 */     return null;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemMaterial)
/*  58:    */   {
/*  59: 60 */     return ((itemMaterial.getItem() == ChocolateQuest.material) && (itemMaterial.getItemDamage() == 1)) || (super.getIsRepairable(itemToRepair, itemMaterial));
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void onUpdateEquiped(ItemStack itemStack, World world, EntityLivingBase entity)
/*  63:    */   {
/*  64: 67 */     if (entity.ticksExisted % 400 == this.type * 100)
/*  65:    */     {
/*  66: 69 */       entity.heal(1.0F);
/*  67: 70 */       if (world.isRemote) {
/*  68: 72 */         entity.worldObj.spawnParticle("heart", entity.posX + itemRand.nextDouble() - 0.5D, entity.posY + itemRand.nextDouble(), entity.posZ + itemRand.nextDouble() - 0.5D, 0.0D, 1.0D, 0.0D);
/*  69:    */       }
/*  70:    */     }
/*  71: 75 */     if (itemStack.stackTagCompound != null)
/*  72:    */     {
/*  73: 76 */       if (itemStack.stackTagCompound.hasKey("CD")) {
/*  74: 77 */         setCooldown(itemStack, getCoolDown(itemStack) - 1);
/*  75:    */       }
/*  76: 79 */       if (itemStack.stackTagCompound.hasKey("ON")) {
/*  77: 80 */         if (entity.getHealth() < entity.getMaxHealth())
/*  78:    */         {
/*  79: 81 */           entity.heal(1.0F);
/*  80: 82 */           if (world.isRemote) {
/*  81: 84 */             entity.worldObj.spawnParticle("heart", entity.posX + itemRand.nextDouble() - 0.5D, entity.posY + itemRand.nextDouble(), entity.posZ + itemRand.nextDouble() - 0.5D, 0.0D, 1.0D, 0.0D);
/*  82:    */           }
/*  83:    */         }
/*  84:    */         else
/*  85:    */         {
/*  86: 87 */           itemStack.stackTagCompound.removeTag("ON");
/*  87:    */         }
/*  88:    */       }
/*  89:    */     }
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void onHit(LivingHurtEvent event, ItemStack is, EntityLivingBase entity)
/*  93:    */   {
/*  94: 94 */     if ((this.armorType == 1) && 
/*  95: 95 */       (isFullSet(entity, is)))
/*  96:    */     {
/*  97: 96 */       float ammount = event.ammount + 4.0F;
/*  98: 97 */       if (!event.source.isUnblockable()) {
/*  99: 98 */         ammount *= 1.0F / entity.getTotalArmorValue() * 4.0F;
/* 100:    */       }
/* 101: 99 */       if ((entity.getHealth() - ammount <= 0.0F) && 
/* 102:100 */         (getCoolDown(is) == 0))
/* 103:    */       {
/* 104:101 */         entity.setHealth(0.1F);
/* 105:102 */         event.setCanceled(true);
/* 106:103 */         setCooldown(is, 7200);
/* 107:104 */         is.stackTagCompound.setBoolean("ON", true);
/* 108:    */       }
/* 109:    */     }
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void setCooldown(ItemStack is, int cooldown)
/* 113:    */   {
/* 114:112 */     if (is.stackTagCompound == null) {
/* 115:113 */       is.stackTagCompound = new NBTTagCompound();
/* 116:    */     }
/* 117:114 */     if (cooldown == 0)
/* 118:    */     {
/* 119:115 */       is.stackTagCompound.removeTag("CD");
/* 120:116 */       return;
/* 121:    */     }
/* 122:118 */     is.stackTagCompound.setInteger("CD", cooldown);
/* 123:    */   }
/* 124:    */   
/* 125:    */   public int getCoolDown(ItemStack is)
/* 126:    */   {
/* 127:121 */     if (is.stackTagCompound == null) {
/* 128:122 */       return 0;
/* 129:    */     }
/* 130:123 */     return is.stackTagCompound.getInteger("CD");
/* 131:    */   }
/* 132:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorTurtle
 * JD-Core Version:    0.7.1
 */