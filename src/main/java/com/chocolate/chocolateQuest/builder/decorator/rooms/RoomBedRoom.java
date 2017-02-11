/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.builder.BuilderHelper;
/*  4:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.init.Blocks;
/*  7:   */ import net.minecraft.init.Items;
/*  8:   */ import net.minecraft.item.ItemStack;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class RoomBedRoom
/* 12:   */   extends RoomBase
/* 13:   */ {
/* 14:   */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 15:   */   {
/* 16:16 */     if (side != 5)
/* 17:   */     {
/* 18:18 */       if (random.nextInt(75) == 0) {
/* 19:20 */         placePainting(random, world, x, y + 1, z, side);
/* 20:   */       }
/* 21:23 */       if (random.nextInt(30) == 0) {
/* 22:25 */         placeFrame(random, world, x, y + 1, z, side, new ItemStack(Items.clock));
/* 23:   */       }
/* 24:27 */       if (random.nextInt(5) == 0)
/* 25:   */       {
/* 26:29 */         placeBed(random, world, x, y, z, side);
/* 27:   */       }
/* 28:31 */       else if (random.nextInt(10) == 0)
/* 29:   */       {
/* 30:33 */         world.setBlock(x, y, z, Blocks.bookshelf);
/* 31:   */       }
/* 32:35 */       else if (random.nextInt(10) == 0)
/* 33:   */       {
/* 34:37 */         world.setBlock(x, y, z, Blocks.crafting_table);
/* 35:   */       }
/* 36:39 */       else if (random.nextInt(10) == 0)
/* 37:   */       {
/* 38:41 */         placeFlowerPot(random, world, x, y, z);
/* 39:   */       }
/* 40:43 */       else if (random.nextInt(15) == 0)
/* 41:   */       {
/* 42:45 */         world.setBlock(x, y, z, Blocks.redstone_lamp);
/* 43:46 */         world.setBlock(x, y + 1, z, Blocks.lever, 5, 3);
/* 44:   */       }
/* 45:48 */       else if (random.nextInt(30) == 0)
/* 46:   */       {
/* 47:50 */         addWeaponChest(random, world, x, y, z, side);
/* 48:   */       }
/* 49:52 */       else if (random.nextInt(25) == 0)
/* 50:   */       {
/* 51:54 */         BuilderHelper.addChest(random, world, x, y, z);
/* 52:   */       }
/* 53:   */       else
/* 54:   */       {
/* 55:57 */         decorateFullMonsterRoom(random, world, x, y, z, side);
/* 56:   */       }
/* 57:   */     }
/* 58:   */   }
/* 59:   */   
/* 60:   */   public int getType()
/* 61:   */   {
/* 62:62 */     return 0;
/* 63:   */   }
/* 64:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomBedRoom
 * JD-Core Version:    0.7.1
 */