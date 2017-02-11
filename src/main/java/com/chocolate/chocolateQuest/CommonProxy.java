/*   1:    */ package com.chocolate.chocolateQuest;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.block.BlockAltarTileEntity;
/*   4:    */ import com.chocolate.chocolateQuest.block.BlockArmorStandTileEntity;
/*   5:    */ import com.chocolate.chocolateQuest.block.BlockBannerStandTileEntity;
/*   6:    */ import com.chocolate.chocolateQuest.block.BlockDungeonChestTileEntity;
/*   7:    */ import com.chocolate.chocolateQuest.block.BlockEditorTileEntity;
/*   8:    */ import com.chocolate.chocolateQuest.block.BlockMobSpawnerTileEntity;
/*   9:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  10:    */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMecha;
/*  11:    */ import com.chocolate.chocolateQuest.entity.npc.EntityHumanDummy;
/*  12:    */ import com.chocolate.chocolateQuest.gui.ContainerArmorStand;
/*  13:    */ import com.chocolate.chocolateQuest.gui.ContainerBDChest;
/*  14:    */ import com.chocolate.chocolateQuest.gui.ContainerGolemInventory;
/*  15:    */ import com.chocolate.chocolateQuest.gui.ContainerGun;
/*  16:    */ import com.chocolate.chocolateQuest.gui.ContainerHumanInventory;
/*  17:    */ import com.chocolate.chocolateQuest.gui.GuiArmorStand;
/*  18:    */ import com.chocolate.chocolateQuest.gui.GuiChestBD;
/*  19:    */ import com.chocolate.chocolateQuest.gui.GuiDummy;
/*  20:    */ import com.chocolate.chocolateQuest.gui.GuiEditor;
/*  21:    */ import com.chocolate.chocolateQuest.gui.GuiGolem;
/*  22:    */ import com.chocolate.chocolateQuest.gui.GuiGun;
/*  23:    */ import com.chocolate.chocolateQuest.gui.GuiHuman;
/*  24:    */ import com.chocolate.chocolateQuest.gui.InventoryGun;
/*  25:    */ import com.chocolate.chocolateQuest.gui.InventoryHuman;
/*  26:    */ import cpw.mods.fml.common.network.IGuiHandler;
/*  27:    */ import cpw.mods.fml.common.registry.GameRegistry;
/*  28:    */ import net.minecraft.entity.Entity;
/*  29:    */ import net.minecraft.entity.player.EntityPlayer;
/*  30:    */ import net.minecraft.item.ItemStack;
/*  31:    */ import net.minecraft.tileentity.TileEntity;
/*  32:    */ import net.minecraft.world.World;
/*  33:    */ 
/*  34:    */ public class CommonProxy
/*  35:    */   implements IGuiHandler
/*  36:    */ {
/*  37:    */   public void register()
/*  38:    */   {
/*  39: 40 */     GameRegistry.registerTileEntity(BlockMobSpawnerTileEntity.class, "CQSpawner");
/*  40: 41 */     registerTileEntities();
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void registerRenderInformation() {}
/*  44:    */   
/*  45:    */   public void postInit() {}
/*  46:    */   
/*  47:    */   public void registerTileEntities()
/*  48:    */   {
/*  49: 54 */     GameRegistry.registerTileEntity(BlockArmorStandTileEntity.class, "armorStand");
/*  50: 55 */     GameRegistry.registerTileEntity(BlockBannerStandTileEntity.class, "bannerStand");
/*  51: 56 */     GameRegistry.registerTileEntity(BlockAltarTileEntity.class, "table");
/*  52: 57 */     GameRegistry.registerTileEntity(BlockEditorTileEntity.class, "exporter");
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void registerAudio() {}
/*  56:    */   
/*  57:    */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*  58:    */   {
/*  59: 67 */     if (ID == 0)
/*  60:    */     {
/*  61: 69 */       Entity e = world.getEntityByID(x);
/*  62: 70 */       if ((e instanceof EntityGolemMecha)) {
/*  63: 71 */         return new GuiGolem((EntityHumanBase)e, new InventoryHuman((EntityHumanBase)e), player);
/*  64:    */       }
/*  65: 72 */       if ((e instanceof EntityHumanDummy)) {
/*  66: 73 */         return new GuiDummy((EntityHumanBase)e, new InventoryHuman((EntityHumanBase)e), player);
/*  67:    */       }
/*  68: 74 */       if ((e instanceof EntityHumanBase)) {
/*  69: 75 */         return new GuiHuman((EntityHumanBase)e, new InventoryHuman((EntityHumanBase)e), player);
/*  70:    */       }
/*  71:    */     }
/*  72: 77 */     if (ID == 1) {
/*  73: 79 */       return new GuiEditor(world, x, y, z);
/*  74:    */     }
/*  75: 81 */     if (ID == 4)
/*  76:    */     {
/*  77: 83 */       TileEntity te = world.getTileEntity(x, y, z);
/*  78: 84 */       if ((te instanceof BlockDungeonChestTileEntity)) {
/*  79: 86 */         return new GuiChestBD(player.inventory, (BlockDungeonChestTileEntity)te);
/*  80:    */       }
/*  81:    */     }
/*  82: 89 */     if (ID == 3)
/*  83:    */     {
/*  84: 91 */       ItemStack is = player.getCurrentEquippedItem();
/*  85: 92 */       return new GuiGun(is, new InventoryGun(is, player), player);
/*  86:    */     }
/*  87: 94 */     if (ID == 2)
/*  88:    */     {
/*  89: 96 */       BlockArmorStandTileEntity te = (BlockArmorStandTileEntity)world.getTileEntity(x, y, z);
/*  90: 97 */       return new GuiArmorStand(te, player, te);
/*  91:    */     }
/*  92: 99 */     return null;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*  96:    */   {
/*  97:105 */     if (ID == 0)
/*  98:    */     {
/*  99:107 */       Entity e = world.getEntityByID(x);
/* 100:108 */       if ((e instanceof EntityGolemMecha)) {
/* 101:109 */         return new ContainerGolemInventory(player.inventory, new InventoryHuman((EntityHumanBase)e));
/* 102:    */       }
/* 103:110 */       if ((e instanceof EntityHumanBase)) {
/* 104:111 */         return new ContainerHumanInventory(player.inventory, new InventoryHuman((EntityHumanBase)e));
/* 105:    */       }
/* 106:    */     }
/* 107:113 */     if (ID == 4)
/* 108:    */     {
/* 109:115 */       TileEntity te = world.getTileEntity(x, y, z);
/* 110:116 */       if ((te instanceof BlockDungeonChestTileEntity)) {
/* 111:118 */         return new ContainerBDChest(player.inventory, (BlockDungeonChestTileEntity)te);
/* 112:    */       }
/* 113:    */     }
/* 114:121 */     if (ID == 3)
/* 115:    */     {
/* 116:123 */       ItemStack is = player.getCurrentEquippedItem();
/* 117:124 */       return new ContainerGun(player.inventory, new InventoryGun(is, player), is);
/* 118:    */     }
/* 119:126 */     if (ID == 2)
/* 120:    */     {
/* 121:128 */       BlockArmorStandTileEntity te = (BlockArmorStandTileEntity)world.getTileEntity(x, y, z);
/* 122:129 */       return new ContainerArmorStand(player.inventory, te);
/* 123:    */     }
/* 124:131 */     return null;
/* 125:    */   }
/* 126:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.CommonProxy
 * JD-Core Version:    0.7.1
 */