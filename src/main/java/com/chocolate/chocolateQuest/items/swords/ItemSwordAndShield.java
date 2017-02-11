/*  1:   */ package com.chocolate.chocolateQuest.items.swords;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.List;
/*  6:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  7:   */ import net.minecraft.creativetab.CreativeTabs;
/*  8:   */ import net.minecraft.init.Items;
/*  9:   */ import net.minecraft.item.Item;
/* 10:   */ import net.minecraft.item.Item.ToolMaterial;
/* 11:   */ import net.minecraft.item.ItemStack;
/* 12:   */ import net.minecraft.nbt.NBTTagCompound;
/* 13:   */ import net.minecraft.util.IIcon;
/* 14:   */ 
/* 15:   */ public class ItemSwordAndShield
/* 16:   */   extends ItemBaseSwordDefensive
/* 17:   */ {
/* 18:   */   final boolean isIron;
/* 19:   */   
/* 20:   */   public ItemSwordAndShield(Item.ToolMaterial mat)
/* 21:   */   {
/* 22:20 */     super(mat, "");
/* 23:21 */     if (mat == Item.ToolMaterial.IRON) {
/* 24:22 */       this.isIron = true;
/* 25:   */     } else {
/* 26:24 */       this.isIron = false;
/* 27:   */     }
/* 28:   */   }
/* 29:   */   
/* 30:   */   @SideOnly(Side.CLIENT)
/* 31:   */   public void registerIcons(IIconRegister iconRegister) {}
/* 32:   */   
/* 33:   */   @SideOnly(Side.CLIENT)
/* 34:   */   public void getSubItems(Item itemId, CreativeTabs table, List list)
/* 35:   */   {
/* 36:35 */     for (int i = 0; i < 13; i++)
/* 37:   */     {
/* 38:36 */       ItemStack is = new ItemStack(itemId);
/* 39:37 */       is.stackTagCompound = new NBTTagCompound();
/* 40:38 */       is.stackTagCompound.setShort("Shield", (short)i);
/* 41:39 */       list.add(is);
/* 42:   */     }
/* 43:   */   }
/* 44:   */   
/* 45:   */   public int getShieldID(ItemStack is)
/* 46:   */   {
/* 47:45 */     if (is.stackTagCompound != null) {
/* 48:46 */       return is.stackTagCompound.getShort("Shield");
/* 49:   */     }
/* 50:48 */     return super.getShieldID(is);
/* 51:   */   }
/* 52:   */   
/* 53:   */   public IIcon getIconFromDamage(int par1)
/* 54:   */   {
/* 55:53 */     if (this.isIron) {
/* 56:54 */       return Items.iron_sword.getIconFromDamage(par1);
/* 57:   */     }
/* 58:55 */     return Items.diamond_sword.getIconFromDamage(par1);
/* 59:   */   }
/* 60:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemSwordAndShield
 * JD-Core Version:    0.7.1
 */