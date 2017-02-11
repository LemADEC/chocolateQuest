/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.List;
/*  6:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  7:   */ import net.minecraft.creativetab.CreativeTabs;
/*  8:   */ import net.minecraft.item.Item;
/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ import net.minecraft.util.IIcon;
/* 11:   */ import net.minecraft.util.StatCollector;
/* 12:   */ 
/* 13:   */ public class ItemMulti
/* 14:   */   extends Item
/* 15:   */ {
/* 16:16 */   public String[] names = { "???" };
/* 17:   */   public IIcon[] icon;
/* 18:   */   String textureName;
/* 19:   */   
/* 20:   */   public ItemMulti(String[] names, String texture)
/* 21:   */   {
/* 22:22 */     this.names = names;
/* 23:23 */     this.textureName = texture;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public boolean getHasSubtypes()
/* 27:   */   {
/* 28:28 */     return true;
/* 29:   */   }
/* 30:   */   
/* 31:   */   @SideOnly(Side.CLIENT)
/* 32:   */   public void registerIcons(IIconRegister iconRegister)
/* 33:   */   {
/* 34:35 */     this.icon = new IIcon[this.names.length];
/* 35:36 */     for (int i = 0; i < this.icon.length; i++) {
/* 36:37 */       this.icon[i] = iconRegister.registerIcon("chocolatequest:" + this.textureName + i);
/* 37:   */     }
/* 38:   */   }
/* 39:   */   
/* 40:   */   public String getItemStackDisplayName(ItemStack itemstack)
/* 41:   */   {
/* 42:44 */     int i = itemstack.getItemDamage();
/* 43:45 */     if (i < this.names.length) {
/* 44:46 */       return ("" + StatCollector.translateToLocal(new StringBuilder().append("item.").append(this.names[i]).append(".name").toString())).trim();
/* 45:   */     }
/* 46:49 */     return "????";
/* 47:   */   }
/* 48:   */   
/* 49:   */   public IIcon getIconFromDamage(int par1)
/* 50:   */   {
/* 51:61 */     if (par1 < this.icon.length) {
/* 52:62 */       return this.icon[par1];
/* 53:   */     }
/* 54:64 */     return this.icon[0];
/* 55:   */   }
/* 56:   */   
/* 57:   */   @SideOnly(Side.CLIENT)
/* 58:   */   public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List par3List)
/* 59:   */   {
/* 60:71 */     for (int i = 0; i < this.names.length; i++) {
/* 61:73 */       par3List.add(new ItemStack(this, 1, i));
/* 62:   */     }
/* 63:   */   }
/* 64:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemMulti
 * JD-Core Version:    0.7.1
 */