package com.chocolate.chocolateQuest.API;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public abstract interface ICooldownTracker
{
  public abstract Object getCooldownTracker(ItemStack paramItemStack, Entity paramEntity);
  
  public abstract void onUpdateCooldown(ItemStack paramItemStack, Entity paramEntity, Object paramObject);
}


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.API.ICooldownTracker
 * JD-Core Version:    0.7.1
 */