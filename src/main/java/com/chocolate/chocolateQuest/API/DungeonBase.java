/*   1:    */ package com.chocolate.chocolateQuest.API;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.mob.registry.RegisterDungeonMobs;
/*   4:    */ import java.io.File;
/*   5:    */ import java.io.FileReader;
/*   6:    */ import java.io.IOException;
/*   7:    */ import java.io.PrintStream;
/*   8:    */ import java.util.Properties;
/*   9:    */ import java.util.StringTokenizer;
/*  10:    */ 
/*  11:    */ public class DungeonBase
/*  12:    */ {
/*  13:    */   String[] biomeList;
/*  14: 16 */   int chance = 10;
/*  15: 17 */   int mobID = 1;
/*  16: 18 */   int[] dimensionID = { 0 };
/*  17:    */   String name;
/*  18: 21 */   int icon = 1;
/*  19:    */   BuilderBase builder;
/*  20:    */   
/*  21:    */   public int getIcon()
/*  22:    */   {
/*  23: 30 */     return this.icon;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public String getName()
/*  27:    */   {
/*  28: 37 */     return this.name;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public BuilderBase getBuilder()
/*  32:    */   {
/*  33: 45 */     return this.builder;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public String[] getBiomes()
/*  37:    */   {
/*  38: 53 */     return this.biomeList;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public DungeonBase setBiomes(String[] biomes)
/*  42:    */   {
/*  43: 61 */     this.biomeList = biomes;
/*  44: 62 */     return this;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public int getChance()
/*  48:    */   {
/*  49: 70 */     return this.chance;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public DungeonBase setChance(int chance)
/*  53:    */   {
/*  54: 74 */     this.chance = chance;
/*  55: 75 */     return this;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public int getMobID()
/*  59:    */   {
/*  60: 80 */     return this.mobID;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public int[] getDimension()
/*  64:    */   {
/*  65: 85 */     return this.dimensionID;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public DungeonBase setDimension(int[] dim)
/*  69:    */   {
/*  70: 90 */     this.dimensionID = dim;
/*  71: 91 */     return this;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public DungeonBase readData(File file)
/*  75:    */   {
/*  76:102 */     String[] ret = null;
/*  77:103 */     Properties prop = new Properties();
/*  78:    */     try
/*  79:    */     {
/*  80:108 */       FileReader fr = new FileReader(file);
/*  81:109 */       prop.load(fr);
/*  82:    */       
/*  83:111 */       String dungeonType = prop.getProperty("builder");
/*  84:112 */       if (dungeonType != null)
/*  85:    */       {
/*  86:114 */         dungeonType.trim();
/*  87:115 */         this.builder = RegisterDungeonBuilder.getBuilderByName(dungeonType);
/*  88:117 */         if (this.builder != null)
/*  89:    */         {
/*  90:120 */           String s = prop.getProperty("biomes");
/*  91:121 */           StringTokenizer stkn = new StringTokenizer(s, ",");
/*  92:122 */           ret = new String[stkn.countTokens()];
/*  93:123 */           int tknCount = stkn.countTokens();
/*  94:124 */           for (int i = 0; i < tknCount; i++) {
/*  95:126 */             ret[i] = stkn.nextToken().trim();
/*  96:    */           }
/*  97:128 */           setBiomes(ret);
/*  98:    */           
/*  99:130 */           setChance(HelperReadConfig.getIntegerProperty(prop, "chance", 10));
/* 100:    */           
/* 101:132 */           this.icon = HelperReadConfig.getIntegerProperty(prop, "icon", 10);
/* 102:133 */           this.name = file.getName();
/* 103:    */           
/* 104:135 */           setDimension(HelperReadConfig.getIntegerArray(prop, "dimensionID", 0));
/* 105:    */           
/* 106:137 */           this.mobID = RegisterDungeonMobs.getMonster(prop.getProperty("mob").trim());
/* 107:139 */           if (!readSpecialData(prop)) {
/* 108:140 */             return null;
/* 109:    */           }
/* 110:    */         }
/* 111:    */         else
/* 112:    */         {
/* 113:143 */           return null;
/* 114:    */         }
/* 115:    */       }
/* 116:    */       else
/* 117:    */       {
/* 118:146 */         return null;
/* 119:    */       }
/* 120:148 */       fr.close();
/* 121:    */     }
/* 122:    */     catch (IOException e)
/* 123:    */     {
/* 124:152 */       System.out.println("Error reading dungeon config file at betterDungeons mod");
/* 125:153 */       e.printStackTrace();
/* 126:154 */       return null;
/* 127:    */     }
/* 128:157 */     return this;
/* 129:    */   }
/* 130:    */   
/* 131:    */   public boolean readSpecialData(Properties prop)
/* 132:    */   {
/* 133:162 */     if (this.builder.load(prop) != null) {
/* 134:163 */       return true;
/* 135:    */     }
/* 136:164 */     return false;
/* 137:    */   }
/* 138:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.API.DungeonBase
 * JD-Core Version:    0.7.1
 */