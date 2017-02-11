/*   1:    */ package com.chocolate.chocolateQuest.builder.decorator.rooms;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.builder.decorator.BuildingProperties;
/*   4:    */ import com.chocolate.chocolateQuest.builder.decorator.RoomBase;
/*   5:    */ import java.util.Random;
/*   6:    */ import net.minecraft.block.Block;
/*   7:    */ import net.minecraft.init.Blocks;
/*   8:    */ import net.minecraft.world.World;
/*   9:    */ 
/*  10:    */ public class RoomJail
/*  11:    */   extends RoomBase
/*  12:    */ {
/*  13: 13 */   final Block BARS_BLOCK = Blocks.iron_bars;
/*  14:    */   
/*  15:    */   public int getType()
/*  16:    */   {
/*  17: 17 */     return 401;
/*  18:    */   }
/*  19:    */   
/*  20:    */   public void addRoomDecoration(Random random, World world, int posX, int posY, int posZ)
/*  21:    */   {
/*  22: 22 */     int roomCenterX = posX + this.sizeX / 2;
/*  23: 23 */     int roomCenterZ = posZ + this.sizeZ / 2;
/*  24: 24 */     decorateFullMonsterRoom(random, world, roomCenterX, posY, roomCenterZ, 5);
/*  25: 25 */     if (this.sizeX > 1)
/*  26:    */     {
/*  27: 27 */       if ((!this.doorNorth) && (this.decorateNorth))
/*  28:    */       {
/*  29: 29 */         for (int i = 1; i < this.sizeX; i++) {
/*  30: 31 */           for (int h = 0; h < this.properties.floorHeight - 1; h++) {
/*  31: 33 */             world.setBlock(posX + i, posY + h, posZ + 3, this.BARS_BLOCK);
/*  32:    */           }
/*  33:    */         }
/*  34: 36 */         int villagers = Math.max(1, this.sizeX / 6);
/*  35: 37 */         for (int v = 0; v < villagers; v++) {
/*  36: 39 */           if (random.nextBoolean())
/*  37:    */           {
/*  38: 41 */             int villagerOffset = random.nextInt(this.sizeX - 1) + 1;
/*  39: 42 */             spawnPrisoner(world, random, posX + villagerOffset, posY, posZ + 1);
/*  40:    */           }
/*  41:    */         }
/*  42:    */       }
/*  43: 46 */       if ((!this.doorSouth) && (this.decorateSouth))
/*  44:    */       {
/*  45: 48 */         for (int i = 1; i < this.sizeX; i++) {
/*  46: 50 */           for (int h = 0; h < this.properties.floorHeight - 1; h++) {
/*  47: 52 */             world.setBlock(posX + i, posY + h, posZ + this.sizeZ - 3, this.BARS_BLOCK);
/*  48:    */           }
/*  49:    */         }
/*  50: 55 */         int villagers = Math.max(1, this.sizeX / 6);
/*  51: 56 */         for (int v = 0; v < villagers; v++) {
/*  52: 58 */           if (random.nextBoolean())
/*  53:    */           {
/*  54: 60 */             int villagerOffset = random.nextInt(this.sizeX - 1) + 1;
/*  55: 61 */             spawnPrisoner(world, random, posX + villagerOffset, posY, posZ + this.sizeZ - 1);
/*  56:    */           }
/*  57:    */         }
/*  58:    */       }
/*  59:    */     }
/*  60: 66 */     if (this.sizeZ > 1)
/*  61:    */     {
/*  62: 68 */       if ((!this.doorEast) && (this.decorateEast))
/*  63:    */       {
/*  64: 70 */         for (int i = 1; i < this.sizeZ; i++) {
/*  65: 72 */           for (int h = 0; h < this.properties.floorHeight - 1; h++) {
/*  66: 74 */             world.setBlock(posX + 3, posY + h, posZ + i, this.BARS_BLOCK);
/*  67:    */           }
/*  68:    */         }
/*  69: 77 */         int villagers = Math.max(1, this.sizeZ / 3);
/*  70: 78 */         for (int v = 0; v < villagers; v++) {
/*  71: 80 */           if (random.nextBoolean())
/*  72:    */           {
/*  73: 82 */             int villagerOffset = random.nextInt(this.sizeZ - 1) + 1;
/*  74: 83 */             spawnPrisoner(world, random, posX + 1, posY, posZ + villagerOffset);
/*  75:    */           }
/*  76:    */         }
/*  77:    */       }
/*  78: 87 */       if ((!this.doorWest) && (this.decorateWest))
/*  79:    */       {
/*  80: 89 */         for (int i = 1; i < this.sizeZ; i++) {
/*  81: 91 */           for (int h = 0; h < this.properties.floorHeight - 1; h++) {
/*  82: 93 */             world.setBlock(posX + this.sizeX - 3, posY + h, posZ + i, this.BARS_BLOCK);
/*  83:    */           }
/*  84:    */         }
/*  85: 96 */         int villagers = Math.max(1, this.sizeZ / 3);
/*  86: 97 */         for (int v = 0; v < villagers; v++) {
/*  87: 99 */           if (random.nextBoolean())
/*  88:    */           {
/*  89:101 */             int villagerOffset = random.nextInt(this.sizeZ - 1) + 1;
/*  90:102 */             spawnPrisoner(world, random, posX + this.sizeX - 1, posY, posZ + villagerOffset);
/*  91:    */           }
/*  92:    */         }
/*  93:    */       }
/*  94:    */     }
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void spawnPrisoner(World world, Random random, int posX, int posY, int posZ) {}
/*  98:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.rooms.RoomJail
 * JD-Core Version:    0.7.1
 */