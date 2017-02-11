package com.chocolate.chocolateQuest.items.gun;

import net.minecraft.item.ItemStack;

public abstract interface ILoadableGun
{
  public abstract int getAmmoLoaderStackSize(ItemStack paramItemStack);
  
  public abstract int getAmmoLoaderAmmount(ItemStack paramItemStack);
  
  public abstract boolean isValidAmmo(ItemStack paramItemStack);
}


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.gun.ILoadableGun
 * JD-Core Version:    0.7.1
 */