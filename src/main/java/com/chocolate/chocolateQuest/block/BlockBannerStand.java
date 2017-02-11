/*   1:    */ package com.chocolate.chocolateQuest.block;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import cpw.mods.fml.relauncher.Side;
/*   5:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   6:    */ import net.minecraft.block.Block;
/*   7:    */ import net.minecraft.block.BlockContainer;
/*   8:    */ import net.minecraft.block.material.Material;
/*   9:    */ import net.minecraft.entity.Entity;
/*  10:    */ import net.minecraft.entity.item.EntityItem;
/*  11:    */ import net.minecraft.entity.player.EntityPlayer;
/*  12:    */ import net.minecraft.init.Blocks;
/*  13:    */ import net.minecraft.item.ItemStack;
/*  14:    */ import net.minecraft.tileentity.TileEntity;
/*  15:    */ import net.minecraft.util.IIcon;
/*  16:    */ import net.minecraft.world.IBlockAccess;
/*  17:    */ import net.minecraft.world.World;
/*  18:    */ 
/*  19:    */ public class BlockBannerStand
/*  20:    */   extends BlockContainer
/*  21:    */ {
/*  22:    */   public BlockBannerStand()
/*  23:    */   {
/*  24: 25 */     super(Material.wood);
/*  25:    */   }
/*  26:    */   
/*  27:    */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
/*  28:    */   {
/*  29: 30 */     ItemStack playerItem = player.getCurrentEquippedItem();
/*  30: 31 */     TileEntity te = world.getTileEntity(x, y, z);
/*  31: 32 */     if ((te instanceof BlockBannerStandTileEntity))
/*  32:    */     {
/*  33: 33 */       BlockBannerStandTileEntity stand = (BlockBannerStandTileEntity)te;
/*  34: 34 */       if (stand.item != null)
/*  35:    */       {
/*  36: 41 */         if (!world.isRemote)
/*  37:    */         {
/*  38: 43 */           EntityItem e = new EntityItem(world, x, y + 1, z, stand.item);
/*  39: 44 */           world.spawnEntityInWorld(e);
/*  40:    */         }
/*  41: 46 */         stand.hasFlag = false;
/*  42: 47 */         stand.item = null;
/*  43: 48 */         return true;
/*  44:    */       }
/*  45: 50 */       if ((playerItem != null) && (playerItem.getItem() == ChocolateQuest.banner))
/*  46:    */       {
/*  47: 52 */         stand.item = playerItem.splitStack(1);
/*  48: 53 */         stand.rotation = ((int)player.rotationYaw - 180);
/*  49: 54 */         stand.hasFlag = true;
/*  50: 55 */         return true;
/*  51:    */       }
/*  52:    */     }
/*  53:    */     else
/*  54:    */     {
/*  55: 57 */       world.setTileEntity(x, y, z, createNewTileEntity(world, world.getBlockMetadata(x, y, z)));
/*  56:    */     }
/*  57: 58 */     return super.onBlockActivated(world, x, y + 1, z, player, par6, par7, par8, par9);
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void onEntityWalking(World world, int par2, int par3, int par4, Entity par5Entity)
/*  61:    */   {
/*  62:106 */     super.onEntityWalking(world, par2, par3, par4, par5Entity);
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
/*  66:    */   {
/*  67:111 */     setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 0.2F, 0.6F);
/*  68:    */   }
/*  69:    */   
/*  70:    */   public boolean isOpaqueCube()
/*  71:    */   {
/*  72:117 */     return false;
/*  73:    */   }
/*  74:    */   
/*  75:    */   @SideOnly(Side.CLIENT)
/*  76:    */   public int getRenderBlockPass()
/*  77:    */   {
/*  78:122 */     return 0;
/*  79:    */   }
/*  80:    */   
/*  81:    */   @SideOnly(Side.CLIENT)
/*  82:    */   public int getRenderType()
/*  83:    */   {
/*  84:127 */     return 0;
/*  85:    */   }
/*  86:    */   
/*  87:    */   @SideOnly(Side.CLIENT)
/*  88:    */   public boolean renderAsNormalBlock()
/*  89:    */   {
/*  90:132 */     return false;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
/*  94:    */   {
/*  95:138 */     TileEntity te = world.getTileEntity(x, y, z);
/*  96:139 */     if ((te instanceof BlockBannerStandTileEntity))
/*  97:    */     {
/*  98:140 */       BlockBannerStandTileEntity stand = (BlockBannerStandTileEntity)te;
/*  99:141 */       if (stand != null) {
/* 100:143 */         if ((stand.hasFlag) && (!world.isRemote))
/* 101:    */         {
/* 102:145 */           EntityItem e = new EntityItem(world, x, y, z, stand.item);
/* 103:146 */           world.spawnEntityInWorld(e);
/* 104:    */         }
/* 105:    */       }
/* 106:    */     }
/* 107:150 */     super.breakBlock(world, x, y, z, par5, par6);
/* 108:    */   }
/* 109:    */   
/* 110:    */   public IIcon getIcon(int side, int metadata)
/* 111:    */   {
/* 112:155 */     if (metadata == 1) {
/* 113:156 */       return Blocks.enchanting_table.getIcon(side, metadata);
/* 114:    */     }
/* 115:157 */     return Blocks.planks.getIcon(side, metadata);
/* 116:    */   }
/* 117:    */   
/* 118:    */   public TileEntity createNewTileEntity(World var1, int var2)
/* 119:    */   {
/* 120:162 */     return new BlockBannerStandTileEntity();
/* 121:    */   }
/* 122:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockBannerStand
 * JD-Core Version:    0.7.1
 */