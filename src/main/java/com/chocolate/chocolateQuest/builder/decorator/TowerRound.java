/*   1:    */ package com.chocolate.chocolateQuest.builder.decorator;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.BuilderBlockData;
/*   4:    */ import com.chocolate.chocolateQuest.builder.decorator.rooms.RoomStairs;
/*   5:    */ import java.util.Random;
/*   6:    */ import net.minecraft.init.Blocks;
/*   7:    */ import net.minecraft.world.World;
/*   8:    */ import net.minecraftforge.common.util.ForgeDirection;
/*   9:    */ 
/*  10:    */ public class TowerRound
/*  11:    */   extends TowerSquare
/*  12:    */ {
/*  13:    */   public TowerRound(BuildingProperties properties)
/*  14:    */   {
/*  15: 13 */     super(properties);
/*  16:    */   }
/*  17:    */   
/*  18:    */   public void buildTower(Random random, World world, int x, int y, int z, ForgeDirection direction)
/*  19:    */   {
/*  20: 17 */     int floors = Math.max(1, this.floors - 1 + random.nextInt(5));
/*  21: 18 */     int radioExtended = this.radio + 1;
/*  22: 19 */     int radioDecreased = this.radio - 1;
/*  23: 20 */     RoomStairs stairs = new RoomStairs();
/*  24: 21 */     stairs.configure(this.radio * 2 - 2, this.radio * 2 - 2, this.properties);
/*  25: 22 */     stairs.clearWalls();
/*  26: 23 */     int currentY = y + floors * (this.properties.floorHeight + 1);
/*  27:    */     
/*  28: 25 */     x += this.radio * direction.offsetX;
/*  29: 26 */     z += this.radio * direction.offsetZ;
/*  30:    */     
/*  31: 28 */     buildRoof(random, world, x, y, z, floors);
/*  32: 30 */     for (int h = 0; h <= floors; h++)
/*  33:    */     {
/*  34: 31 */       for (int px = -radioExtended; px < radioExtended; px++) {
/*  35: 32 */         for (int pz = -radioExtended; pz < radioExtended; pz++)
/*  36:    */         {
/*  37: 33 */           int dist = px * px + pz * pz;
/*  38: 34 */           int blockX = x + px;
/*  39: 35 */           int blockY = currentY;
/*  40: 36 */           int blockZ = z + pz;
/*  41: 37 */           if (dist < this.radio * this.radio)
/*  42:    */           {
/*  43: 39 */             this.properties.floor.generateFloor(world, blockX, blockY, blockZ);
/*  44: 40 */             world.setBlock(blockX, blockY + this.properties.floorHeight, blockZ, this.properties.wallBlock.id);
/*  45: 42 */             for (int i = 1; i < this.properties.floorHeight - 1; i++) {
/*  46: 43 */               world.setBlock(blockX, blockY + i, blockZ, Blocks.air);
/*  47:    */             }
/*  48: 44 */             if (dist >= radioDecreased * radioDecreased) {
/*  49: 46 */               stairs.placeRandomDecorationBlock(random, world, blockX, blockY + 1, blockZ, 0);
/*  50:    */             }
/*  51:    */           }
/*  52: 49 */           else if (dist < radioExtended * radioExtended)
/*  53:    */           {
/*  54: 51 */             for (int i = 0; i <= this.properties.floorHeight; i++) {
/*  55: 52 */               world.setBlock(blockX, blockY + i, blockZ, this.properties.wallBlock.id);
/*  56:    */             }
/*  57: 53 */             this.properties.window.generateWindowX(world, blockX, blockY + 1, blockZ);
/*  58:    */           }
/*  59:    */         }
/*  60:    */       }
/*  61: 57 */       stairs.addRoomDecoration(random, world, x - this.radio, currentY + 1, z - this.radio + 1);
/*  62: 58 */       currentY -= this.properties.floorHeight + 1;
/*  63:    */     }
/*  64: 62 */     x -= this.radio * direction.offsetX;
/*  65: 63 */     z -= this.radio * direction.offsetZ;
/*  66: 65 */     for (int h = 0; h < this.floors + 1; h++)
/*  67:    */     {
/*  68: 66 */       int posY = y + 1 + h * (this.properties.floorHeight + 1);
/*  69: 67 */       this.properties.doors.generate(random, world, x, posY, z, direction);
/*  70:    */     }
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void buildRoof(Random random, World world, int x, int y, int z, int floors)
/*  74:    */   {
/*  75: 73 */     int currentY = y + floors * this.properties.floorHeight + 1;
/*  76: 74 */     int radioExtended = this.radio + 1;
/*  77: 75 */     this.properties.roof.getClass();
/*  78: 75 */     if (this.properties.roof.floorType != 0)
/*  79:    */     {
/*  80: 75 */       this.properties.roof.getClass();
/*  81: 75 */       if (this.properties.roof.floorType != 5) {}
/*  82:    */     }
/*  83:    */     else
/*  84:    */     {
/*  85: 77 */       int texasRadio = this.radio;
/*  86: 78 */       int texasRadioExtended = texasRadio + 1;
/*  87: 79 */       int steps = 2;
/*  88: 80 */       for (int h = 0; h <= this.radio * steps; h++)
/*  89:    */       {
/*  90: 81 */         for (int px = -texasRadioExtended; px < texasRadioExtended; px++) {
/*  91: 82 */           for (int pz = -texasRadioExtended; pz < texasRadioExtended; pz++)
/*  92:    */           {
/*  93: 83 */             int dist = px * px + pz * pz;
/*  94: 84 */             int blockX = x + px;
/*  95: 85 */             int blockY = currentY + (this.properties.floorHeight + 1) + h;
/*  96: 86 */             int blockZ = z + pz;
/*  97: 87 */             if (dist >= texasRadio * texasRadio) {
/*  98: 90 */               if (dist < texasRadioExtended * texasRadioExtended) {
/*  99: 92 */                 world.setBlock(blockX, blockY, blockZ, this.properties.roof.roofBlock);
/* 100:    */               }
/* 101:    */             }
/* 102:    */           }
/* 103:    */         }
/* 104: 96 */         if (h % steps == 0)
/* 105:    */         {
/* 106: 97 */           texasRadio--;
/* 107: 98 */           texasRadioExtended = texasRadio + 1;
/* 108:    */         }
/* 109:    */       }
/* 110:101 */       return;
/* 111:    */     }
/* 112:103 */     for (int px = -radioExtended; px < radioExtended; px++) {
/* 113:104 */       for (int pz = -radioExtended; pz < radioExtended; pz++)
/* 114:    */       {
/* 115:105 */         int dist = px * px + pz * pz;
/* 116:106 */         int blockX = x + px;
/* 117:107 */         int blockY = currentY + (this.properties.floorHeight + 1);
/* 118:108 */         int blockZ = z + pz;
/* 119:109 */         if (dist < this.radio * this.radio)
/* 120:    */         {
/* 121:111 */           world.setBlock(blockX, blockY, blockZ, this.properties.wallBlock.id);
/* 122:    */         }
/* 123:113 */         else if (dist < radioExtended * radioExtended)
/* 124:    */         {
/* 125:115 */           world.setBlock(blockX, blockY, blockZ, this.properties.wallBlock.id);
/* 126:    */           
/* 127:    */ 
/* 128:118 */           this.properties.roof.setRoofBlock(world, blockX, blockY + 1, blockZ);
/* 129:    */         }
/* 130:    */       }
/* 131:    */     }
/* 132:    */   }
/* 133:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.TowerRound
 * JD-Core Version:    0.7.1
 */