/*  1:   */ package com.chocolate.chocolateQuest.block;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  6:   */ import net.minecraft.block.BlockContainer;
/*  7:   */ import net.minecraft.block.material.Material;
/*  8:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  9:   */ import net.minecraft.entity.player.EntityPlayer;
/* 10:   */ import net.minecraft.tileentity.TileEntity;
/* 11:   */ import net.minecraft.util.IIcon;
/* 12:   */ import net.minecraft.world.World;
/* 13:   */ 
/* 14:   */ public class BlockEditor
/* 15:   */   extends BlockContainer
/* 16:   */ {
/* 17:   */   public IIcon icon1;
/* 18:   */   public IIcon icon2;
/* 19:   */   public IIcon icon3;
/* 20:   */   
/* 21:   */   public BlockEditor()
/* 22:   */   {
/* 23:20 */     super(Material.cake);
/* 24:   */   }
/* 25:   */   
/* 26:   */   @SideOnly(Side.CLIENT)
/* 27:   */   public void registerBlockIcons(IIconRegister iconRegister)
/* 28:   */   {
/* 29:28 */     this.blockIcon = iconRegister.registerIcon("chocolatequest:e0");
/* 30:29 */     this.icon1 = iconRegister.registerIcon("chocolatequest:e1");
/* 31:30 */     this.icon2 = iconRegister.registerIcon("chocolatequest:e2");
/* 32:31 */     this.icon3 = iconRegister.registerIcon("chocolatequest:e3");
/* 33:   */   }
/* 34:   */   
/* 35:   */   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
/* 36:   */   {
/* 37:36 */     BlockEditorTileEntity block = (BlockEditorTileEntity)par1World.getTileEntity(par2, par3, par4);
/* 38:37 */     par5EntityPlayer.openGui(ChocolateQuest.instance, 1, par1World, par2, par3, par4);
/* 39:38 */     return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
/* 40:   */   }
/* 41:   */   
/* 42:   */   public IIcon getIcon(int i, int j)
/* 43:   */   {
/* 44:44 */     switch (i)
/* 45:   */     {
/* 46:   */     case 2: 
/* 47:47 */       return this.icon1;
/* 48:   */     case 4: 
/* 49:50 */       return this.blockIcon;
/* 50:   */     case 0: 
/* 51:53 */       return this.icon3;
/* 52:   */     }
/* 53:56 */     return this.icon2;
/* 54:   */   }
/* 55:   */   
/* 56:   */   public boolean isOpaqueCube()
/* 57:   */   {
/* 58:62 */     return false;
/* 59:   */   }
/* 60:   */   
/* 61:   */   public boolean renderAsNormalBlock()
/* 62:   */   {
/* 63:68 */     return false;
/* 64:   */   }
/* 65:   */   
/* 66:   */   public int getRenderType()
/* 67:   */   {
/* 68:74 */     return 0;
/* 69:   */   }
/* 70:   */   
/* 71:   */   public TileEntity createNewTileEntity(World world, int var2)
/* 72:   */   {
/* 73:79 */     return new BlockEditorTileEntity();
/* 74:   */   }
/* 75:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockEditor
 * JD-Core Version:    0.7.1
 */