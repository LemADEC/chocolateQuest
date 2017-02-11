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
/* 11:   */ public class RoomKitchen
/* 12:   */   extends RoomBase
/* 13:   */ {
/* 14:   */   public void placeDecorationBlock(Random random, World world, int x, int y, int z, int side)
/* 15:   */   {
/* 16:16 */     if (random.nextInt(100) == 0) {
/* 17:18 */       placePainting(random, world, x, y + 1, z, side);
/* 18:   */     }
/* 19:20 */     if (random.nextInt(250) == 0)
/* 20:   */     {
/* 21:22 */       world.setBlock(x, y, z, Blocks.brewing_stand, 0, 3);
/* 22:   */     }
/* 23:26 */     else if (random.nextInt(30) == 0)
/* 24:   */     {
/* 25:28 */       placeCake(random, world, x, y, z);
/* 26:   */     }
/* 27:30 */     else if (random.nextInt(50) == 0)
/* 28:   */     {
/* 29:32 */       world.setBlock(x, y, z, Blocks.cauldron);
/* 30:   */     }
/* 31:34 */     else if (random.nextInt(50) == 0)
/* 32:   */     {
/* 33:36 */       world.setBlock(x, y, z, Blocks.melon_block);
/* 34:   */     }
/* 35:38 */     else if (random.nextInt(50) == 0)
/* 36:   */     {
/* 37:40 */       world.setBlock(x, y, z, Blocks.pumpkin);
/* 38:   */     }
/* 39:42 */     else if (random.nextInt(8) == 0)
/* 40:   */     {
/* 41:44 */       placeFoodFurnace(random, world, x, y, z, side);
/* 42:   */     }
/* 43:46 */     else if (random.nextInt(10) == 0)
/* 44:   */     {
/* 45:48 */       world.setBlock(x, y, z, Blocks.crafting_table);
/* 46:   */     }
/* 47:50 */     else if (random.nextInt(8) == 0)
/* 48:   */     {
/* 49:52 */       ItemStack is = null;
/* 50:53 */       if (random.nextInt(3) == 0) {
/* 51:54 */         is = new ItemStack(Items.cooked_fished);
/* 52:55 */       } else if (random.nextInt(3) == 0) {
/* 53:56 */         is = new ItemStack(Items.cooked_beef);
/* 54:57 */       } else if (random.nextInt(3) == 0) {
/* 55:58 */         is = new ItemStack(Items.milk_bucket);
/* 56:59 */       } else if (random.nextInt(3) == 0) {
/* 57:60 */         is = new ItemStack(Items.cookie);
/* 58:61 */       } else if (random.nextInt(3) == 0) {
/* 59:62 */         is = new ItemStack(Items.cooked_chicken);
/* 60:63 */       } else if (random.nextInt(3) == 0) {
/* 61:64 */         is = new ItemStack(Items.egg);
/* 62:65 */       } else if (random.nextInt(3) == 0) {
/* 63:66 */         is = new ItemStack(Items.golden_apple);
/* 64:   */       }
/* 65:67 */       placeTable(random, world, x, y, z, is);
/* 66:   */     }
/* 67:69 */     else if (random.nextInt(20) == 0)
/* 68:   */     {
/* 69:71 */       BuilderHelper.addFoodChest(random, world, x, y, z);
/* 70:   */     }
/* 71:   */   }
/* 72:   */   
/* 73:   */   public int getType()
/* 74:   */   {
/* 75:77 */     return 0;
/* 76:   */   }
/* 77:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomKitchen
 * JD-Core Version:    0.7.1
 */