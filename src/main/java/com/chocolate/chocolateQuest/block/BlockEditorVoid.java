/*  1:   */ package com.chocolate.chocolateQuest.block;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.block.BlockGlass;
/*  6:   */ import net.minecraft.block.material.Material;
/*  7:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  8:   */ 
/*  9:   */ public class BlockEditorVoid
/* 10:   */   extends BlockGlass
/* 11:   */ {
/* 12:   */   public BlockEditorVoid()
/* 13:   */   {
/* 14:13 */     super(Material.iron, false);
/* 15:   */   }
/* 16:   */   
/* 17:   */   @SideOnly(Side.CLIENT)
/* 18:   */   public void registerBlockIcons(IIconRegister iconRegister)
/* 19:   */   {
/* 20:20 */     this.blockIcon = iconRegister.registerIcon("chocolatequest:null");
/* 21:   */   }
/* 22:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockEditorVoid
 * JD-Core Version:    0.7.1
 */