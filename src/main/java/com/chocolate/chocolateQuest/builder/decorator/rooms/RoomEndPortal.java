/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.block.Block;
/*  6:   */ import net.minecraft.init.Blocks;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ 
/*  9:   */ public class RoomEndPortal
/* 10:   */   extends RoomBase
/* 11:   */ {
/* 12:   */   public void addRoomDecoration(Random random, World world, int posX, int posY, int posZ)
/* 13:   */   {
/* 14:14 */     int x = posX + this.sizeX / 2;
/* 15:15 */     int y = posY;
/* 16:16 */     int z = posZ + this.sizeZ / 2;
/* 17:17 */     x -= 3;
/* 18:18 */     z -= 3;
/* 19:19 */     y++;
/* 20:20 */     Block block = Blocks.end_portal_frame;
/* 21:21 */     for (int i = 1; i < 4; i++)
/* 22:   */     {
/* 23:23 */       world.setBlock(x + i, y, z + 4, block, 0, 3);
/* 24:24 */       world.setBlock(x + i, y, z, block, 2, 3);
/* 25:25 */       world.setBlock(x, y, z + i, block, 3, 3);
/* 26:26 */       world.setBlock(x + 4, y, z + i, block, 1, 3);
/* 27:   */     }
/* 28:   */   }
/* 29:   */   
/* 30:   */   public int getType()
/* 31:   */   {
/* 32:31 */     return 208;
/* 33:   */   }
/* 34:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomEndPortal
 * JD-Core Version:    0.7.1
 */