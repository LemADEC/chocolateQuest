/*  1:   */ package com.chocolate.chocolateQuest.builder.decorator;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.API.BuilderBlockData;
/*  4:   */ import com.chocolate.chocolateQuest.API.HelperReadConfig;
/*  5:   */ import java.util.Properties;
/*  6:   */ import java.util.Random;
/*  7:   */ import net.minecraft.init.Blocks;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class BuildingProperties
/* 11:   */ {
/* 12:   */   public int doorWidth;
/* 13:   */   public int doorHeight;
/* 14:   */   public int floorHeight;
/* 15:   */   public int mobID;
/* 16:   */   public int mobRatio;
/* 17:   */   public BuilderBlockData wallBlock;
/* 18:   */   public DecoratorDoor doors;
/* 19:   */   public DecoratorFloor floor;
/* 20:   */   public DecoratorWindow window;
/* 21:   */   public DecoratorRoof roof;
/* 22:   */   
/* 23:   */   public void initialize(Random random)
/* 24:   */   {
/* 25:32 */     this.doors = new DecoratorDoor(this.doorWidth, this.doorHeight);
/* 26:33 */     this.floor = new DecoratorFloor(random);
/* 27:34 */     this.window = new DecoratorWindow(random);
/* 28:35 */     this.roof = new DecoratorRoof(random);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void load(Properties prop)
/* 32:   */   {
/* 33:40 */     this.doorWidth = Math.max(2, HelperReadConfig.getIntegerProperty(prop, "doorWidth", 2));
/* 34:41 */     this.doorHeight = Math.max(2, HelperReadConfig.getIntegerProperty(prop, "doorheight", 3));
/* 35:42 */     this.floorHeight = Math.max(3, HelperReadConfig.getIntegerProperty(prop, "floorHeight", 6));
/* 36:   */     
/* 37:44 */     this.wallBlock = HelperReadConfig.getBlock(prop, "wallBlock", new BuilderBlockData(Blocks.stonebrick));
/* 38:   */     
/* 39:46 */     this.mobRatio = HelperReadConfig.getIntegerProperty(prop, "mobRatio", 1);
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void setWallBlock(World world, int x, int y, int z)
/* 43:   */   {
/* 44:50 */     world.setBlock(x, y, z, this.wallBlock.id, this.wallBlock.metadata, 3);
/* 45:   */   }
/* 46:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.decorator.BuildingProperties
 * JD-Core Version:    0.7.1
 */