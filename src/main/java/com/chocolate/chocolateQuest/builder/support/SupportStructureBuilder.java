/*  1:   */ package com.chocolate.chocolateQuest.builder.support;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.API.BuilderBlockData;
/*  4:   */ import com.chocolate.chocolateQuest.API.HelperReadConfig;
/*  5:   */ import java.util.Properties;
/*  6:   */ import java.util.Random;
/*  7:   */ import net.minecraft.init.Blocks;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class SupportStructureBuilder
/* 11:   */ {
/* 12:14 */   BuilderBlockData structureBlock = new BuilderBlockData(Blocks.dirt);
/* 13:15 */   BuilderBlockData structureTopBlock = new BuilderBlockData(Blocks.grass);
/* 14:   */   
/* 15:   */   public void load(Properties prop)
/* 16:   */   {
/* 17:18 */     this.structureBlock = HelperReadConfig.getBlock(prop, "structureBlock", new BuilderBlockData(Blocks.dirt));
/* 18:19 */     this.structureTopBlock = HelperReadConfig.getBlock(prop, "structureTopBlock", new BuilderBlockData(Blocks.grass));
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void generate(Random random, World world, int i, int j, int k, int maxX, int maxY)
/* 22:   */   {
/* 23:23 */     for (int x = 0; x < maxX; x++) {
/* 24:25 */       for (int z = 0; z < maxY; z++) {
/* 25:27 */         if (world.canBlockSeeTheSky(i + x, j, k + z))
/* 26:   */         {
/* 27:29 */           int top = world.getTopSolidOrLiquidBlock(i + x, k + z) - 1;
/* 28:30 */           while (top < j)
/* 29:   */           {
/* 30:32 */             world.setBlock(i + x, top, k + z, this.structureBlock.id, this.structureBlock.metadata, 3);
/* 31:33 */             top++;
/* 32:   */           }
/* 33:35 */           world.setBlock(i + x, top, k + z, this.structureTopBlock.id, this.structureTopBlock.metadata, 3);
/* 34:   */         }
/* 35:   */       }
/* 36:   */     }
/* 37:   */   }
/* 38:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.support.SupportStructureBuilder
 * JD-Core Version:    0.7.1
 */