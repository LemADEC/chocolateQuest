/*   1:    */ package com.chocolate.chocolateQuest;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.BuilderBase;
/*   4:    */ import com.chocolate.chocolateQuest.API.DungeonBase;
/*   5:    */ import com.chocolate.chocolateQuest.API.DungeonRegister;
/*   6:    */ import com.chocolate.chocolateQuest.config.Configurations;
/*   7:    */ import com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase;
/*   8:    */ import com.chocolate.chocolateQuest.entity.mob.registry.RegisterDungeonMobs;
/*   9:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  10:    */ import cpw.mods.fml.common.IWorldGenerator;
/*  11:    */ import java.util.ArrayList;
/*  12:    */ import java.util.Random;
/*  13:    */ import net.minecraft.block.Block;
/*  14:    */ import net.minecraft.init.Blocks;
/*  15:    */ import net.minecraft.world.World;
/*  16:    */ import net.minecraft.world.WorldProvider;
/*  17:    */ import net.minecraft.world.WorldType;
/*  18:    */ import net.minecraft.world.biome.BiomeGenBase;
/*  19:    */ import net.minecraft.world.chunk.IChunkProvider;
/*  20:    */ import net.minecraft.world.storage.WorldInfo;
/*  21:    */ import net.minecraftforge.common.BiomeDictionary;
/*  22:    */ import net.minecraftforge.common.BiomeDictionary.Type;
/*  23:    */ 
/*  24:    */ public class WorldGeneratorNew
/*  25:    */   implements IWorldGenerator
/*  26:    */ {
/*  27: 25 */   int dungeonSeparation = 5;
/*  28:    */   
/*  29:    */   public WorldGeneratorNew(int dungeonSeparation)
/*  30:    */   {
/*  31: 29 */     this.dungeonSeparation = dungeonSeparation;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
/*  35:    */   {
/*  36: 38 */     if (world.isRemote) {
/*  37: 40 */       return;
/*  38:    */     }
/*  39: 43 */     Random rnd = new Random();
/*  40: 44 */     rnd.setSeed(getSeed(world, chunkX, chunkZ));
/*  41: 45 */     Block block = Blocks.bookshelf;
/*  42: 47 */     if ((chunkX % this.dungeonSeparation == 0) && (chunkZ % this.dungeonSeparation == 0)) {
/*  43: 61 */       generateSurface(world, rnd, chunkX * 16 + 1, chunkZ * 16 + 1);
/*  44:    */     }
/*  45:    */   }
/*  46:    */   
/*  47:    */   public static long getSeed(World world, int chunkX, int chunkZ)
/*  48:    */   {
/*  49: 66 */     long mix = xorShift64(chunkX) + Long.rotateLeft(xorShift64(chunkZ), 32) + -1094792450L;
/*  50: 67 */     long result = xorShift64(mix);
/*  51:    */     
/*  52: 69 */     return world.getSeed() + result;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public static long xorShift64(long x)
/*  56:    */   {
/*  57: 73 */     x ^= x << 21;
/*  58: 74 */     x ^= x >>> 35;
/*  59: 75 */     x ^= x << 4;
/*  60: 76 */     return x;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public static void createChunks(World world, int posX, int posZ, int sizeX, int sizeZ)
/*  64:    */   {
/*  65: 80 */     sizeX = Math.max(1, sizeX / 16);
/*  66: 81 */     sizeZ = Math.max(1, sizeX / 16);
/*  67: 82 */     int chunkX = posX / 16;
/*  68: 83 */     int chunkZ = posZ / 16;
/*  69: 84 */     for (int x = 0; x < sizeX; x++) {
/*  70: 85 */       for (int z = 0; z < sizeZ; z++) {
/*  71: 86 */         if (world.getChunkFromChunkCoords(chunkX + x, chunkZ + z) == null) {
/*  72: 87 */           world.getChunkProvider().loadChunk(chunkX + x, chunkZ + z);
/*  73:    */         }
/*  74:    */       }
/*  75:    */     }
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void generateSurface(World world, Random random, int i, int k)
/*  79:    */   {
/*  80: 94 */     if ((world.getWorldInfo().getTerrainType().getWorldTypeName() == "flat") && (!ChocolateQuest.config.dungeonsInFlat)) {
/*  81: 96 */       return;
/*  82:    */     }
/*  83: 99 */     generateBigDungeon(world, random, i, k, true);
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void generateBigDungeon(World world, Random random, int i, int k, boolean addDungeon)
/*  87:    */   {
/*  88:105 */     BuilderBase builder = null;
/*  89:106 */     BiomeGenBase biome = world.getBiomeGenForCoords(i, k);
/*  90:107 */     DungeonBase dungeon = null;
/*  91:109 */     for (DungeonBase d : DungeonRegister.dungeonList)
/*  92:    */     {
/*  93:112 */       int[] dimension = d.getDimension();
/*  94:113 */       boolean dimensionPass = false;
/*  95:114 */       for (int a = 0; a < dimension.length; a++) {
/*  96:116 */         if (dimension[a] == world.provider.dimensionId)
/*  97:    */         {
/*  98:117 */           dimensionPass = true;
/*  99:118 */           break;
/* 100:    */         }
/* 101:    */       }
/* 102:121 */       if ((d.getChance() > 0) && (dimensionPass))
/* 103:    */       {
/* 104:123 */         String[] b = d.getBiomes();
/* 105:124 */         for (String currentName : b) {
/* 106:126 */           if (isValidBiome(currentName, biome))
/* 107:    */           {
/* 108:128 */             if (random.nextInt(d.getChance()) != 0) {
/* 109:    */               break;
/* 110:    */             }
/* 111:129 */             dungeon = d; break;
/* 112:    */           }
/* 113:    */         }
/* 114:    */       }
/* 115:    */     }
/* 116:136 */     if (dungeon != null)
/* 117:    */     {
/* 118:139 */       builder = dungeon.getBuilder();
/* 119:140 */       int idMob = ((DungeonMonstersBase)RegisterDungeonMobs.mobList.get(dungeon.getMobID())).getDungeonMonster(world, i, 60, k).getID();
/* 120:141 */       builder.generate(random, world, i, k, idMob);
/* 121:    */       
/* 122:143 */       BDHelper.println("Generatig " + dungeon.getName() + " at x: " + i + ",  z:" + k);
/* 123:    */     }
/* 124:    */   }
/* 125:    */   
/* 126:    */   public boolean isValidBiome(String biomeName, BiomeGenBase biome)
/* 127:    */   {
/* 128:148 */     if (biomeName.equals("ALL")) {
/* 129:149 */       return true;
/* 130:    */     }
/* 131:151 */     if (biomeName.equals("NONWATER")) {
/* 132:152 */       return !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WATER);
/* 133:    */     }
/* 134:155 */     if (biome.biomeName.equals(biomeName)) {
/* 135:156 */       return true;
/* 136:    */     }
/* 137:158 */     BiomeDictionary.Type type = null;
/* 138:159 */     if (biomeName.equals("BEACH")) {
/* 139:160 */       type = BiomeDictionary.Type.BEACH;
/* 140:161 */     } else if (biomeName.equals("DESERT")) {
/* 141:162 */       type = BiomeDictionary.Type.DESERT;
/* 142:163 */     } else if (biomeName.equals("END")) {
/* 143:164 */       type = BiomeDictionary.Type.END;
/* 144:165 */     } else if (biomeName.equals("FOREST")) {
/* 145:166 */       type = BiomeDictionary.Type.FOREST;
/* 146:167 */     } else if (biomeName.equals("FROZEN")) {
/* 147:168 */       type = BiomeDictionary.Type.FROZEN;
/* 148:169 */     } else if (biomeName.equals("HILLS")) {
/* 149:170 */       type = BiomeDictionary.Type.HILLS;
/* 150:171 */     } else if (biomeName.equals("JUNGLE")) {
/* 151:172 */       type = BiomeDictionary.Type.JUNGLE;
/* 152:173 */     } else if (biomeName.equals("MAGICAL")) {
/* 153:174 */       type = BiomeDictionary.Type.MAGICAL;
/* 154:175 */     } else if (biomeName.equals("MOUNTAIN")) {
/* 155:176 */       type = BiomeDictionary.Type.MOUNTAIN;
/* 156:177 */     } else if (biomeName.equals("MUSHROOM")) {
/* 157:178 */       type = BiomeDictionary.Type.MUSHROOM;
/* 158:179 */     } else if (biomeName.equals("NETHER")) {
/* 159:180 */       type = BiomeDictionary.Type.NETHER;
/* 160:181 */     } else if (biomeName.equals("PLAINS")) {
/* 161:182 */       type = BiomeDictionary.Type.PLAINS;
/* 162:183 */     } else if (biomeName.equals("SWAMP")) {
/* 163:184 */       type = BiomeDictionary.Type.SWAMP;
/* 164:185 */     } else if (biomeName.equals("WASTELAND")) {
/* 165:186 */       type = BiomeDictionary.Type.WASTELAND;
/* 166:187 */     } else if (biomeName.equals("WATER")) {
/* 167:188 */       type = BiomeDictionary.Type.WATER;
/* 168:    */     }
/* 169:190 */     if (type != null) {
/* 170:191 */       return BiomeDictionary.isBiomeOfType(biome, type);
/* 171:    */     }
/* 172:193 */     return false;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public static int getMobIndex(ArrayList<DungeonMonstersBase> chestList, Random random)
/* 176:    */   {
/* 177:198 */     int[] weights = new int[chestList.size()];
/* 178:199 */     int maxNum = 0;
/* 179:200 */     for (int i = 0; i < chestList.size(); i++)
/* 180:    */     {
/* 181:202 */       weights[i] = ((DungeonMonstersBase)chestList.get(i)).getWeight();
/* 182:203 */       maxNum += weights[i];
/* 183:    */     }
/* 184:206 */     int randomNum = random.nextInt(maxNum);
/* 185:207 */     int index = 0;
/* 186:209 */     for (int weightSum = weights[0]; weightSum <= randomNum; weightSum += weights[index]) {
/* 187:211 */       index++;
/* 188:    */     }
/* 189:214 */     return index;
/* 190:    */   }
/* 191:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.WorldGeneratorNew

 * JD-Core Version:    0.7.1

 */
