/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.API.RegisterChestItem;
/*  4:   */ import com.chocolate.chocolateQuest.builder.BuilderHelper;
/*  5:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  6:   */ import java.util.Random;
/*  7:   */ import net.minecraft.init.Blocks;
/*  8:   */ import net.minecraft.item.ItemStack;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class RoomBlackSmith
/* 12:   */   extends RoomBase
/* 13:   */ {
/* 14:   */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 15:   */   {
/* 16:16 */     if (side == 5)
/* 17:   */     {
/* 18:18 */       world.setBlock(x, y, z, Blocks.lava);
/* 19:19 */       world.setBlock(x, y - 1, z, Blocks.obsidian);
/* 20:20 */       world.setBlock(x + 1, y, z, Blocks.obsidian);
/* 21:21 */       world.setBlock(x - 1, y, z, Blocks.obsidian);
/* 22:22 */       world.setBlock(x, y, z + 1, Blocks.obsidian);
/* 23:23 */       world.setBlock(x, y, z - 1, Blocks.obsidian);
/* 24:   */     }
/* 25:   */     else
/* 26:   */     {
/* 27:26 */       if (random.nextInt(100) == 0) {
/* 28:28 */         placePainting(random, world, x, y + 1, z, side);
/* 29:   */       }
/* 30:31 */       if (random.nextInt(5) == 0)
/* 31:   */       {
/* 32:32 */         world.setBlock(x, y, z, Blocks.anvil, side, 3);
/* 33:   */       }
/* 34:33 */       else if (random.nextInt(5) == 0)
/* 35:   */       {
/* 36:34 */         placeFurnace(random, world, x, y, z, side);
/* 37:   */       }
/* 38:35 */       else if (random.nextInt(20) == 0)
/* 39:   */       {
/* 40:36 */         ItemStack is = RegisterChestItem.getRandomItemStack(RegisterChestItem.mineralList, random);
/* 41:37 */         placeTable(random, world, x, y, z, is);
/* 42:   */       }
/* 43:39 */       else if (random.nextInt(40) == 0)
/* 44:   */       {
/* 45:40 */         BuilderHelper.addWeaponChest(random, world, x, y, z);
/* 46:   */       }
/* 47:41 */       else if (random.nextInt(40) == 0)
/* 48:   */       {
/* 49:42 */         BuilderHelper.addMineralChest(random, world, x, y, z);
/* 50:   */       }
/* 51:   */       else
/* 52:   */       {
/* 53:44 */         decorateFullMonsterRoom(random, world, x, y, z, side);
/* 54:   */       }
/* 55:   */     }
/* 56:   */   }
/* 57:   */   
/* 58:   */   public int getType()
/* 59:   */   {
/* 60:49 */     return 202;
/* 61:   */   }
/* 62:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomBlackSmith
 * JD-Core Version:    0.7.1
 */