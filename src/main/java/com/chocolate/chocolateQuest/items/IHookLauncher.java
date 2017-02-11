package com.chocolate.chocolateQuest.items;

import net.minecraft.item.ItemStack;

public abstract interface IHookLauncher
{
  public abstract int getHookID(ItemStack paramItemStack);
  
  public abstract void setHookID(ItemStack paramItemStack, int paramInt);
}


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.IHookLauncher
 * JD-Core Version:    0.7.1
 */