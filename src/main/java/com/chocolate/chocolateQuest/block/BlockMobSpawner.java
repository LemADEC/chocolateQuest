/*  1:   */ package com.chocolate.chocolateQuest.block;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.block.Block;
/*  5:   */ import net.minecraft.block.BlockContainer;
/*  6:   */ import net.minecraft.block.material.Material;
/*  7:   */ import net.minecraft.init.Blocks;
/*  8:   */ import net.minecraft.tileentity.TileEntity;
/*  9:   */ import net.minecraft.util.IIcon;
/* 10:   */ import net.minecraft.world.World;
/* 11:   */ 
/* 12:   */ public class BlockMobSpawner
/* 13:   */   extends BlockContainer
/* 14:   */ {
/* 15:   */   public BlockMobSpawner()
/* 16:   */   {
/* 17:16 */     super(Material.rock);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public int idDropped(int par1, Random par2Random, int par3)
/* 21:   */   {
/* 22:24 */     return 0;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public int quantityDropped(Random par1Random)
/* 26:   */   {
/* 27:33 */     return 0;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public boolean isOpaqueCube()
/* 31:   */   {
/* 32:42 */     return false;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public IIcon getIcon(int i, int j)
/* 36:   */   {
/* 37:47 */     return Blocks.mob_spawner.getIcon(i, j);
/* 38:   */   }
/* 39:   */   
/* 40:   */   public TileEntity createNewTileEntity(World var1, int var2)
/* 41:   */   {
/* 42:52 */     return new BlockMobSpawnerTileEntity();
/* 43:   */   }
/* 44:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockMobSpawner
 * JD-Core Version:    0.7.1
 */