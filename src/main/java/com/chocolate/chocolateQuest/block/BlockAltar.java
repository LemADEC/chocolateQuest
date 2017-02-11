/*   1:    */ package com.chocolate.chocolateQuest.block;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.client.ClientProxy;
/*   4:    */ import cpw.mods.fml.relauncher.Side;
/*   5:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   6:    */ import net.minecraft.block.Block;
/*   7:    */ import net.minecraft.block.BlockContainer;
/*   8:    */ import net.minecraft.block.material.Material;
/*   9:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  10:    */ import net.minecraft.entity.item.EntityItem;
/*  11:    */ import net.minecraft.entity.player.EntityPlayer;
/*  12:    */ import net.minecraft.item.ItemStack;
/*  13:    */ import net.minecraft.tileentity.TileEntity;
/*  14:    */ import net.minecraft.world.IBlockAccess;
/*  15:    */ import net.minecraft.world.World;
/*  16:    */ 
/*  17:    */ public class BlockAltar
/*  18:    */   extends BlockContainer
/*  19:    */ {
/*  20:    */   public BlockAltar()
/*  21:    */   {
/*  22: 23 */     super(Material.wood);
/*  23:    */   }
/*  24:    */   
/*  25:    */   @SideOnly(Side.CLIENT)
/*  26:    */   public void registerBlockIcons(IIconRegister iconRegister) {}
/*  27:    */   
/*  28:    */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
/*  29:    */   {
/*  30: 35 */     ItemStack playerItem = player.getCurrentEquippedItem();
/*  31: 36 */     TileEntity te = world.getTileEntity(x, y, z);
/*  32: 37 */     if ((te instanceof BlockAltarTileEntity))
/*  33:    */     {
/*  34: 38 */       BlockAltarTileEntity stand = (BlockAltarTileEntity)te;
/*  35: 39 */       if (stand.item != null)
/*  36:    */       {
/*  37: 41 */         if (!world.isRemote)
/*  38:    */         {
/*  39: 43 */           EntityItem e = new EntityItem(world, x, y + 1, z, stand.item);
/*  40: 44 */           world.spawnEntityInWorld(e);
/*  41:    */         }
/*  42: 46 */         stand.item = null;
/*  43: 47 */         return true;
/*  44:    */       }
/*  45: 49 */       if (playerItem != null)
/*  46:    */       {
/*  47: 51 */         stand.item = playerItem.splitStack(1);
/*  48: 52 */         stand.rotation = ((int)player.rotationYaw - 180);
/*  49: 53 */         return true;
/*  50:    */       }
/*  51:    */     }
/*  52:    */     else
/*  53:    */     {
/*  54: 55 */       world.setTileEntity(x, y, z, createNewTileEntity(world, world.getBlockMetadata(x, y, z)));
/*  55:    */     }
/*  56: 56 */     return super.onBlockActivated(world, x, y + 1, z, player, par6, par7, par8, par9);
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
/*  60:    */   {
/*  61: 62 */     if (world == null)
/*  62:    */     {
/*  63: 63 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  64: 64 */       return;
/*  65:    */     }
/*  66: 66 */     float minX = 0.2F;
/*  67: 67 */     byte xAxis = 0;byte zAxis = 0;
/*  68: 68 */     if (world.getBlock(x - 1, y, z) == this)
/*  69:    */     {
/*  70: 69 */       minX = 0.0F;
/*  71: 70 */       xAxis = (byte)(xAxis + 1);
/*  72:    */     }
/*  73: 72 */     float maxX = 0.8F;
/*  74: 73 */     if (world.getBlock(x + 1, y, z) == this)
/*  75:    */     {
/*  76: 74 */       maxX = 1.0F;
/*  77: 75 */       xAxis = (byte)(xAxis + 1);
/*  78:    */     }
/*  79: 77 */     float minZ = 0.2F;
/*  80: 78 */     if (world.getBlock(x, y, z - 1) == this)
/*  81:    */     {
/*  82: 79 */       minZ = 0.0F;
/*  83: 80 */       zAxis = (byte)(zAxis + 1);
/*  84:    */     }
/*  85: 82 */     float maxZ = 0.8F;
/*  86: 83 */     if (world.getBlock(x, y, z + 1) == this)
/*  87:    */     {
/*  88: 84 */       maxZ = 1.0F;
/*  89: 85 */       zAxis = (byte)(zAxis + 1);
/*  90:    */     }
/*  91: 87 */     float minY = 0.0F;
/*  92: 88 */     if ((xAxis == 2) || (zAxis == 2)) {
/*  93: 89 */       minY = 0.9F;
/*  94:    */     }
/*  95: 90 */     setBlockBounds(minX, minY, minZ, maxX, 1.0F, maxZ);
/*  96:    */   }
/*  97:    */   
/*  98:    */   public boolean isOpaqueCube()
/*  99:    */   {
/* 100: 95 */     return false;
/* 101:    */   }
/* 102:    */   
/* 103:    */   @SideOnly(Side.CLIENT)
/* 104:    */   public int getRenderBlockPass()
/* 105:    */   {
/* 106:100 */     return 0;
/* 107:    */   }
/* 108:    */   
/* 109:    */   @SideOnly(Side.CLIENT)
/* 110:    */   public int getRenderType()
/* 111:    */   {
/* 112:105 */     return ClientProxy.tableRenderID;
/* 113:    */   }
/* 114:    */   
/* 115:    */   @SideOnly(Side.CLIENT)
/* 116:    */   public boolean renderAsNormalBlock()
/* 117:    */   {
/* 118:110 */     return false;
/* 119:    */   }
/* 120:    */   
/* 121:    */   public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
/* 122:    */   {
/* 123:116 */     BlockAltarTileEntity stand = (BlockAltarTileEntity)world.getTileEntity(x, y, z);
/* 124:117 */     if ((stand != null) && 
/* 125:118 */       (stand.item != null) && (!world.isRemote))
/* 126:    */     {
/* 127:120 */       EntityItem e = new EntityItem(world, x + 0.5D, y + 1, z + 0.5D, stand.item);
/* 128:121 */       world.spawnEntityInWorld(e);
/* 129:    */     }
/* 130:123 */     super.breakBlock(world, x, y, z, par5, par6);
/* 131:    */   }
/* 132:    */   
/* 133:    */   public TileEntity createNewTileEntity(World var1, int var2)
/* 134:    */   {
/* 135:128 */     return new BlockAltarTileEntity();
/* 136:    */   }
/* 137:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockAltar
 * JD-Core Version:    0.7.1
 */