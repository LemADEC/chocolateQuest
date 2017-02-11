/*  1:   */ package com.chocolate.chocolateQuest.block;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.List;
/*  6:   */ import net.minecraft.block.Block;
/*  7:   */ import net.minecraft.block.material.Material;
/*  8:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  9:   */ import net.minecraft.creativetab.CreativeTabs;
/* 10:   */ import net.minecraft.entity.player.EntityPlayer;
/* 11:   */ import net.minecraft.item.Item;
/* 12:   */ import net.minecraft.item.ItemStack;
/* 13:   */ import net.minecraft.util.DamageSource;
/* 14:   */ import net.minecraft.util.FoodStats;
/* 15:   */ import net.minecraft.util.IIcon;
/* 16:   */ import net.minecraft.world.World;
/* 17:   */ 
/* 18:   */ public class BlockDungeonBrick
/* 19:   */   extends Block
/* 20:   */ {
/* 21:   */   public IIcon[] icon;
/* 22:   */   
/* 23:   */   public BlockDungeonBrick()
/* 24:   */   {
/* 25:22 */     super(Material.rock);
/* 26:   */   }
/* 27:   */   
/* 28:   */   @SideOnly(Side.CLIENT)
/* 29:   */   public void registerBlockIcons(IIconRegister iconRegister)
/* 30:   */   {
/* 31:30 */     this.icon = new IIcon[16];
/* 32:32 */     for (int i = 0; i < 16; i++) {
/* 33:34 */       this.icon[i] = iconRegister.registerIcon("chocolatequest:w" + i);
/* 34:   */     }
/* 35:   */   }
/* 36:   */   
/* 37:   */   public IIcon getIcon(int i, int j)
/* 38:   */   {
/* 39:40 */     return this.icon[j];
/* 40:   */   }
/* 41:   */   
/* 42:   */   public int damageDropped(int metadata)
/* 43:   */   {
/* 44:46 */     return metadata;
/* 45:   */   }
/* 46:   */   
/* 47:   */   @SideOnly(Side.CLIENT)
/* 48:   */   public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List list)
/* 49:   */   {
/* 50:53 */     for (int i = 0; i < 16; i++) {
/* 51:55 */       list.add(new ItemStack(item, 1, i));
/* 52:   */     }
/* 53:   */   }
/* 54:   */   
/* 55:   */   public void onBlockHarvested(World par1World, int x, int y, int z, int par5, EntityPlayer par6EntityPlayer)
/* 56:   */   {

/*  57:61      if (par6EntityPlayer.getFoodStats().getFoodLevel() > 10)
 58:        {
 59:62 *       par6EntityPlayer.getFoodStats().addStats(-8, -1.0F);
 60:   *    }
 61:   *     else
 62:   *     {
 63:64        par6EntityPlayer.attackEntityFrom(DamageSource.inWall, 4.0F);
 64:65        par6EntityPlayer.getFoodStats().setFoodLevel(0);
 65:        } */
/* 66:67 */     super.onBlockHarvested(par1World, x, y, z, par5, par6EntityPlayer);
/* 67:   */   }
/* 68:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockDungeonBrick

 * JD-Core Version:    0.7.1

 */
