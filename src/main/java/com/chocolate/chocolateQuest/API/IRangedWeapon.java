package com.chocolate.chocolateQuest.API;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public abstract interface IRangedWeapon
{
  public abstract float getRange(EntityLivingBase paramEntityLivingBase, ItemStack paramItemStack);
  
  public abstract int getCooldown(EntityLivingBase paramEntityLivingBase, ItemStack paramItemStack);
  
  public abstract void shootFromEntity(EntityLivingBase paramEntityLivingBase, ItemStack paramItemStack, int paramInt, Entity paramEntity);
  
  public abstract boolean canBeUsedByEntity(Entity paramEntity);
  
  public abstract boolean isMeleeWeapon(EntityLivingBase paramEntityLivingBase, ItemStack paramItemStack);
  
  public abstract boolean shouldUpdate(EntityLivingBase paramEntityLivingBase);
  
  public abstract int startAiming(ItemStack paramItemStack, EntityLivingBase paramEntityLivingBase, Entity paramEntity);
}


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.API.IRangedWeapon
 * JD-Core Version:    0.7.1
 */