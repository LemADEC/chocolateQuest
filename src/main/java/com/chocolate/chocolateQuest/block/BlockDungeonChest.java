/*  1:   */ package com.chocolate.chocolateQuest.block;
/*  2:   */ 
/*  3:   */ import net.minecraft.block.BlockContainer;
/*  4:   */ import net.minecraft.block.material.Material;
/*  5:   */ import net.minecraft.entity.player.EntityPlayer;
/*  6:   */ import net.minecraft.tileentity.TileEntity;
/*  7:   */ import net.minecraft.world.IBlockAccess;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class BlockDungeonChest
/* 11:   */   extends BlockContainer
/* 12:   */ {
/* 13:   */   public BlockDungeonChest()
/* 14:   */   {
/* 15:16 */     super(Material.wood);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public TileEntity createNewTileEntity(World var1, int var2)
/* 19:   */   {
/* 20:21 */     return new BlockDungeonChestTileEntity();
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float posX, float posY, float posZ)
/* 24:   */   {
/* 25:27 */     player.openGui("chocolateQuest", 4, world, x, y, z);
/* 26:28 */     return super.onBlockActivated(world, x, y, z, player, side, posX, posY, posZ);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
/* 30:   */   {
/* 31:34 */     setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public boolean isOpaqueCube()
/* 35:   */   {
/* 36:39 */     return false;
/* 37:   */   }
/* 38:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockDungeonChest
 * JD-Core Version:    0.7.1
 */