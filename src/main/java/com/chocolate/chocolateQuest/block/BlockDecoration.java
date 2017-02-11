/*  1:   */ package com.chocolate.chocolateQuest.block;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.List;
/*  6:   */ import net.minecraft.block.Block;
/*  7:   */ import net.minecraft.block.material.Material;
/*  8:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  9:   */ import net.minecraft.creativetab.CreativeTabs;
/* 10:   */ import net.minecraft.item.Item;
/* 11:   */ import net.minecraft.item.ItemStack;
/* 12:   */ import net.minecraft.util.IIcon;
/* 13:   */ 
/* 14:   */ public class BlockDecoration
/* 15:   */   extends Block
/* 16:   */ {
/* 17:   */   public IIcon[] icon;
/* 18:   */   public String[] names;
/* 19:   */   public String textureName;
/* 20:   */   
/* 21:   */   public BlockDecoration(Material p_i45394_1_, String textureName, String[] names)
/* 22:   */   {
/* 23:23 */     super(p_i45394_1_);
/* 24:24 */     this.textureName = textureName;
/* 25:25 */     this.names = names;
/* 26:26 */     this.icon = new IIcon[names.length];
/* 27:   */   }
/* 28:   */   
/* 29:   */   @SideOnly(Side.CLIENT)
/* 30:   */   public void registerBlockIcons(IIconRegister iconRegister)
/* 31:   */   {
/* 32:33 */     for (int i = 0; i < this.icon.length; i++) {
/* 33:35 */       this.icon[i] = iconRegister.registerIcon("chocolatequest:" + this.textureName + i);
/* 34:   */     }
/* 35:   */   }
/* 36:   */   
/* 37:   */   public IIcon getIcon(int i, int j)
/* 38:   */   {
/* 39:42 */     if (j < this.icon.length) {
/* 40:43 */       return this.icon[j];
/* 41:   */     }
/* 42:45 */     return this.icon[0];
/* 43:   */   }
/* 44:   */   
/* 45:   */   public int damageDropped(int metadata)
/* 46:   */   {
/* 47:51 */     return metadata;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public String getBlockName(int i)
/* 51:   */   {
/* 52:56 */     if (i < this.names.length) {
/* 53:57 */       return this.names[i];
/* 54:   */     }
/* 55:59 */     return this.names[0];
/* 56:   */   }
/* 57:   */   
/* 58:   */   @SideOnly(Side.CLIENT)
/* 59:   */   public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List list)
/* 60:   */   {
/* 61:66 */     for (int i = 0; i < this.names.length; i++) {
/* 62:68 */       list.add(new ItemStack(item, 1, i));
/* 63:   */     }
/* 64:   */   }
/* 65:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockDecoration
 * JD-Core Version:    0.7.1
 */