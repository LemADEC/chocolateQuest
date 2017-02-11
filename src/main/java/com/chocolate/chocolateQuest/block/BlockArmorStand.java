/*   1:    */ package com.chocolate.chocolateQuest.block;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import cpw.mods.fml.relauncher.Side;
/*   5:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   6:    */ import net.minecraft.block.Block;
/*   7:    */ import net.minecraft.block.BlockContainer;
/*   8:    */ import net.minecraft.block.material.Material;
/*   9:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  10:    */ import net.minecraft.entity.EntityLivingBase;
/*  11:    */ import net.minecraft.entity.item.EntityItem;
/*  12:    */ import net.minecraft.entity.player.EntityPlayer;
/*  13:    */ import net.minecraft.entity.player.InventoryPlayer;
/*  14:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*  15:    */ import net.minecraft.item.ItemArmor;
/*  16:    */ import net.minecraft.item.ItemStack;
/*  17:    */ import net.minecraft.tileentity.TileEntity;
/*  18:    */ import net.minecraft.world.IBlockAccess;
/*  19:    */ import net.minecraft.world.World;
/*  20:    */ 
/*  21:    */ public class BlockArmorStand
/*  22:    */   extends BlockContainer
/*  23:    */ {
/*  24: 23 */   int armorSlots = 4;
/*  25:    */   
/*  26:    */   public BlockArmorStand()
/*  27:    */   {
/*  28: 26 */     super(Material.wood);
/*  29:    */   }
/*  30:    */   
/*  31:    */   @SideOnly(Side.CLIENT)
/*  32:    */   public void registerBlockIcons(IIconRegister iconRegister)
/*  33:    */   {
/*  34: 33 */     this.blockIcon = iconRegister.registerIcon("chocolatequest:armorStand");
/*  35:    */   }
/*  36:    */   
/*  37:    */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
/*  38:    */   {
/*  39: 39 */     ItemStack currentItem = player.getCurrentEquippedItem();
/*  40: 40 */     BlockArmorStandTileEntity stand = (BlockArmorStandTileEntity)world.getTileEntity(x, y, z);
/*  41: 41 */     if (player.isSneaking())
/*  42:    */     {
/*  43: 43 */       player.openGui(ChocolateQuest.instance, 2, world, x, y, z);
/*  44: 44 */       return true;
/*  45:    */     }
/*  46: 46 */     if ((currentItem != null) && ((currentItem.getItem() instanceof ItemArmor)))
/*  47:    */     {
/*  48: 48 */       int i = 3 - ((ItemArmor)currentItem.getItem()).armorType;
/*  49: 49 */       ItemStack stack = stand.cargoItems[i];
/*  50: 50 */       stand.cargoItems[i] = currentItem;
/*  51: 51 */       player.inventory.setInventorySlotContents(player.inventory.currentItem, stack);
/*  52: 52 */       return true;
/*  53:    */     }
/*  54: 54 */     if (stand.cargoItems != null)
/*  55:    */     {
/*  56: 56 */       for (int i = 0; i < this.armorSlots; i++)
/*  57:    */       {
/*  58: 58 */         ItemStack stack = stand.cargoItems[i];
/*  59: 59 */         ItemStack currentItemArmor = player.inventory.armorInventory[i];
/*  60: 60 */         if ((currentItem != null) || (!player.capabilities.isCreativeMode)) {
/*  61: 61 */           stand.cargoItems[i] = currentItemArmor;
/*  62:    */         }
/*  63: 62 */         player.inventory.armorInventory[i] = stack;
/*  64:    */       }
/*  65: 64 */       return true;
/*  66:    */     }
/*  67: 66 */     return super.onBlockActivated(world, x, y + 1, z, player, par6, par7, par8, par9);
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack)
/*  71:    */   {
/*  72: 71 */     super.onBlockPlacedBy(world, x, y, z, entity, itemstack);
/*  73: 72 */     BlockArmorStandTileEntity te = (BlockArmorStandTileEntity)createNewTileEntity(world, itemstack.getItemDamage());
/*  74: 73 */     te.rotation = ((int)(entity.rotationYaw - 180.0F));
/*  75: 74 */     world.setTileEntity(x, y, z, te);
/*  76:    */   }
/*  77:    */   
/*  78:    */   @SideOnly(Side.CLIENT)
/*  79:    */   public void registerIcons(IIconRegister iconRegister)
/*  80:    */   {
/*  81: 80 */     this.blockIcon = iconRegister.registerIcon("chocolatequest:armorStand");
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
/*  85:    */   {
/*  86: 86 */     setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.8F, 0.8F);
/*  87:    */   }
/*  88:    */   
/*  89:    */   public boolean isOpaqueCube()
/*  90:    */   {
/*  91: 91 */     return false;
/*  92:    */   }
/*  93:    */   
/*  94:    */   @SideOnly(Side.CLIENT)
/*  95:    */   public int getRenderType()
/*  96:    */   {
/*  97: 96 */     return -1;
/*  98:    */   }
/*  99:    */   
/* 100:    */   @SideOnly(Side.CLIENT)
/* 101:    */   public boolean renderAsNormalBlock()
/* 102:    */   {
/* 103:101 */     return false;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
/* 107:    */   {
/* 108:107 */     BlockArmorStandTileEntity stand = (BlockArmorStandTileEntity)world.getTileEntity(x, y, z);
/* 109:108 */     if (stand != null) {
/* 110:109 */       for (int i = 0; i < stand.cargoItems.length; i++) {
/* 111:111 */         if ((stand.cargoItems[i] != null) && (!world.isRemote))
/* 112:    */         {
/* 113:113 */           EntityItem e = new EntityItem(world, x + 0.5D, y + 1, z + 0.5D, stand.cargoItems[i]);
/* 114:114 */           world.spawnEntityInWorld(e);
/* 115:    */         }
/* 116:    */       }
/* 117:    */     }
/* 118:117 */     super.breakBlock(world, x, y, z, par5, par6);
/* 119:    */   }
/* 120:    */   
/* 121:    */   public TileEntity createNewTileEntity(World var1, int var2)
/* 122:    */   {
/* 123:122 */     return new BlockArmorStandTileEntity();
/* 124:    */   }
/* 125:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockArmorStand
 * JD-Core Version:    0.7.1
 */