/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.block.BlockArmorStandTileEntity;
/*  4:   */ import net.minecraft.entity.player.EntityPlayer;
/*  5:   */ import net.minecraft.inventory.IInventory;
/*  6:   */ 
/*  7:   */ public class GuiArmorStand
/*  8:   */   extends GuiHumanBase
/*  9:   */ {
/* 10:   */   public GuiArmorStand(BlockArmorStandTileEntity te, EntityPlayer player, IInventory par1IInventory)
/* 11:   */   {
/* 12:13 */     super(new ContainerArmorStand(player.inventory, par1IInventory), par1IInventory, player);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void onGuiClosed()
/* 16:   */   {
/* 17:18 */     super.onGuiClosed();
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void initGui()
/* 21:   */   {
/* 22:24 */     super.initGui();
/* 23:   */   }
/* 24:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiArmorStand
 * JD-Core Version:    0.7.1
 */