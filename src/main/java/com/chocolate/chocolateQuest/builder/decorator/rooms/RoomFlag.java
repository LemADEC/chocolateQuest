/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.block.BlockBannerStandTileEntity;
/*  5:   */ import com.chocolate.chocolateQuest.builder.decorator.BuildingProperties;
/*  6:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  7:   */ import com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase;
/*  8:   */ import com.chocolate.chocolateQuest.entity.mob.registry.RegisterDungeonMobs;
/*  9:   */ import java.util.ArrayList;
/* 10:   */ import java.util.Random;
/* 11:   */ import net.minecraft.block.Block;
/* 12:   */ import net.minecraft.item.ItemStack;
/* 13:   */ import net.minecraft.world.World;
/* 14:   */ 
/* 15:   */ public class RoomFlag
/* 16:   */   extends RoomBase
/* 17:   */ {
/* 18:   */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 19:   */   {
/* 20:18 */     if (side == 5)
/* 21:   */     {
/* 22:20 */       world.setBlock(x, y, z, ChocolateQuest.bannerStand);
/* 23:21 */       BlockBannerStandTileEntity stand = (BlockBannerStandTileEntity)ChocolateQuest.bannerStand.createTileEntity(world, 0);
/* 24:22 */       if (stand != null)
/* 25:   */       {
/* 26:24 */         DungeonMonstersBase mobType = null;
/* 27:25 */         stand.hasFlag = true;
/* 28:26 */         mobType = (DungeonMonstersBase)RegisterDungeonMobs.mobList.get(this.properties.mobID);
/* 29:27 */         if (mobType != null) {
/* 30:29 */           stand.item = new ItemStack(ChocolateQuest.banner, 1, mobType.getFlagId());
/* 31:   */         }
/* 32:31 */         setTileEntity(world, x, y, z, stand);
/* 33:   */       }
/* 34:   */     }
/* 35:34 */     else if (random.nextInt(5) == 0)
/* 36:   */     {
/* 37:36 */       int rotation = 0;
/* 38:37 */       switch (side)
/* 39:   */       {
/* 40:   */       case 1: 
/* 41:39 */         rotation = -90; break;
/* 42:   */       case 2: 
/* 43:40 */         rotation = 90; break;
/* 44:   */       case 4: 
/* 45:41 */         rotation = 180;
/* 46:   */       }
/* 47:43 */       world.setBlock(x, y, z, ChocolateQuest.bannerStand);
/* 48:44 */       BlockBannerStandTileEntity stand = (BlockBannerStandTileEntity)ChocolateQuest.bannerStand.createTileEntity(world, 0);
/* 49:45 */       if (stand != null)
/* 50:   */       {
/* 51:47 */         DungeonMonstersBase mobType = null;
/* 52:48 */         stand.hasFlag = true;
/* 53:49 */         stand.rotation = rotation;
/* 54:50 */         mobType = (DungeonMonstersBase)RegisterDungeonMobs.mobList.get(this.properties.mobID);
/* 55:51 */         if (mobType != null) {
/* 56:53 */           stand.item = new ItemStack(ChocolateQuest.banner, 1, mobType.getFlagId());
/* 57:   */         }
/* 58:55 */         setTileEntity(world, x, y, z, stand);
/* 59:   */       }
/* 60:   */     }
/* 61:58 */     else if (random.nextInt(10) == 0)
/* 62:   */     {
/* 63:60 */       placeShied(random, world, x, y + 1, z, side);
/* 64:   */     }
/* 65:   */     else
/* 66:   */     {
/* 67:63 */       decorateFullMonsterRoom(random, world, x, y, z, side);
/* 68:   */     }
/* 69:   */   }
/* 70:   */   
/* 71:   */   public int getType()
/* 72:   */   {
/* 73:67 */     return 204;
/* 74:   */   }
/* 75:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomFlag
 * JD-Core Version:    0.7.1
 */