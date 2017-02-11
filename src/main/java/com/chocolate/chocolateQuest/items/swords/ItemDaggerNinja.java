/*  1:   */ package com.chocolate.chocolateQuest.items.swords;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.EntityBaiter;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  6:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  7:   */ import net.minecraft.entity.player.EntityPlayer;
/*  8:   */ import net.minecraft.item.EnumRarity;
/*  9:   */ import net.minecraft.item.Item.ToolMaterial;
/* 10:   */ import net.minecraft.item.ItemStack;
/* 11:   */ import net.minecraft.potion.Potion;
/* 12:   */ import net.minecraft.potion.PotionEffect;
import net.minecraft.item.Item;

/* 13:   */ import net.minecraft.world.World;
/* 14:   */ 
/* 15:   */ public class ItemDaggerNinja
/* 16:   */   extends ItemBaseDagger
/* 17:   */ {
/* 18:   */   public ItemDaggerNinja()
/* 19:   */   {
/* 20:20 */     super(Item.ToolMaterial.EMERALD, 3);
/* 21:21 */     setMaxDamage(2048);
/* 22:   */   }
/* 23:   */   
/* 24:   */   @SideOnly(Side.CLIENT)
/* 25:   */   public void registerIcons(IIconRegister iconRegister)
/* 26:   */   {
/* 27:28 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:daggerPirate");
/* 28:   */   }
/* 29:   */   
/* 30:   */   public int getMaxDamage()
/* 31:   */   {
/* 32:34 */     return 2048;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
/* 36:   */   {
/* 37:40 */     if (entityPlayer.isSneaking())
/* 38:   */     {
/* 39:42 */       if (!world.isRemote)
/* 40:   */       {
/* 41:44 */         EntityBaiter e = new EntityBaiter(world, entityPlayer);
/* 42:45 */         world.spawnEntityInWorld(e);
/* 43:   */       }
/* 44:47 */       entityPlayer.addPotionEffect(new PotionEffect(Potion.invisibility.id, 200, 5));
/* 45:48 */       itemStack.damageItem(20, entityPlayer);
/* 46:   */     }
/* 47:   */     else
/* 48:   */     {
/* 49:52 */       entityPlayer.addPotionEffect(new PotionEffect(Potion.invisibility.id, 20, 5));
/* 50:   */     }
/* 51:55 */     return super.onItemRightClick(itemStack, world, entityPlayer);
/* 52:   */   }
/* 53:   */   
/* 54:   */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemMaterial)
/* 55:   */   {
/* 56:60 */     return super.getIsRepairable(itemToRepair, itemMaterial);
/* 57:   */   }
/* 58:   */   
/* 59:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 60:   */   {
/* 61:65 */     return EnumRarity.epic;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public int getEntityLifespan(ItemStack itemStack, World world)
/* 65:   */   {
/* 66:70 */     return 24000;
/* 67:   */   }
/* 68:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemDaggerNinja

 * JD-Core Version:    0.7.1

 */
