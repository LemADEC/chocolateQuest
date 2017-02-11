/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomStairs;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.world.World;
/*  6:   */ import net.minecraftforge.common.util.ForgeDirection;
/*  7:   */ 
/*  8:   */ public class TowerSquare
/*  9:   */ {
/* 10:   */   BuildingProperties properties;
/* 11:13 */   int radio = 6;
/* 12:14 */   int floors = 4;
/* 13:   */   
/* 14:   */   public TowerSquare(BuildingProperties properties)
/* 15:   */   {
/* 16:17 */     this.properties = properties;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void configure(int floors, int width)
/* 20:   */   {
/* 21:22 */     this.floors = floors;
/* 22:23 */     this.radio = Math.max(3, width / 2);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void buildTower(Random random, World world, int x, int y, int z, ForgeDirection direction)
/* 26:   */   {
/* 27:27 */     int floors = Math.max(1, this.floors - 1 + random.nextInt(5));
/* 28:28 */     int width = this.radio * 2;
/* 29:29 */     int originX = x;
/* 30:30 */     int originZ = z;
/* 31:31 */     x -= Math.abs(this.radio * direction.offsetZ);
/* 32:32 */     z -= Math.abs(this.radio * direction.offsetX);
/* 33:33 */     if (direction.offsetX < 0) {
/* 34:34 */       x -= width;
/* 35:   */     }
/* 36:35 */     if (direction.offsetZ < 0) {
/* 37:36 */       z -= width;
/* 38:   */     }
/* 39:37 */     RoomStairs stairs = new RoomStairs();
/* 40:38 */     stairs.configure(this.radio * 2, this.radio * 2, this.properties);
/* 41:40 */     for (int f = floors - 1; f >= 0; f--)
/* 42:   */     {
/* 43:41 */       int posY = y + f * (this.properties.floorHeight + 1);
/* 44:42 */       for (int sx = 1; sx < width; sx++)
/* 45:   */       {
/* 46:43 */         int posX = x + sx;
/* 47:44 */         for (int sz = 1; sz < width; sz++)
/* 48:   */         {
/* 49:45 */           int posZ = z + sz;
/* 50:46 */           this.properties.floor.generateFloor(world, posX, posY, posZ);
/* 51:47 */           for (int h = 1; h < this.properties.floorHeight; h++) {
/* 52:48 */             world.setBlockToAir(posX, posY + h, posZ);
/* 53:   */           }
/* 54:49 */           this.properties.setWallBlock(world, posX, posY + this.properties.floorHeight, posZ);
/* 55:   */         }
/* 56:   */       }
/* 57:53 */       stairs.decorate(random, world, x, posY + 1, z);
/* 58:55 */       for (int sx = 0; sx <= width; sx++)
/* 59:   */       {
/* 60:56 */         int posX = x + sx;
/* 61:57 */         int posZ = z;
/* 62:58 */         this.properties.setWallBlock(world, posX, posY, posZ);
/* 63:59 */         this.properties.window.generateWindowX(world, posX, posY + 1, posZ);
/* 64:60 */         posZ = z + width;
/* 65:61 */         this.properties.setWallBlock(world, posX, posY, posZ);
/* 66:62 */         this.properties.window.generateWindowX(world, posX, posY + 1, posZ);
/* 67:   */         
/* 68:64 */         posX = x;
/* 69:65 */         posZ = z + sx;
/* 70:66 */         this.properties.setWallBlock(world, posX, posY, posZ);
/* 71:67 */         this.properties.window.generateWindowZ(world, posX, posY + 1, posZ);
/* 72:68 */         posX = x + width;
/* 73:69 */         this.properties.setWallBlock(world, posX, posY, posZ);
/* 74:70 */         this.properties.window.generateWindowZ(world, posX, posY + 1, posZ);
/* 75:   */       }
/* 76:   */     }
/* 77:74 */     this.properties.roof.generateRoof(world, x, y + floors * (this.properties.floorHeight + 1), z, width, width, false);
/* 78:76 */     for (int h = 0; h < this.floors + 1; h++)
/* 79:   */     {
/* 80:77 */       int posY = y + 1 + h * (this.properties.floorHeight + 1);
/* 81:78 */       this.properties.doors.generate(random, world, originX, posY, originZ, direction);
/* 82:   */     }
/* 83:   */   }
/* 84:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.TowerSquare
 * JD-Core Version:    0.7.1
 */