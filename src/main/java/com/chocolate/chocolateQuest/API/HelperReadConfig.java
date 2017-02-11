/*  1:   */ package com.chocolate.chocolateQuest.API;
/*  2:   */ 
/*  3:   */ import java.io.PrintStream;
/*  4:   */ import java.util.Properties;
/*  5:   */ import java.util.StringTokenizer;
/*  6:   */ import net.minecraft.block.Block;
/*  7:   */ 
/*  8:   */ public class HelperReadConfig
/*  9:   */ {
/* 10:   */   public static boolean getBooleanProperty(Properties prop, String name, boolean defaultValue)
/* 11:   */   {
/* 12:11 */     String s = prop.getProperty(name);
/* 13:13 */     if (s == null) {
/* 14:15 */       return defaultValue;
/* 15:   */     }
/* 16:17 */     s = s.trim();
/* 17:19 */     if (s.equals("true")) {
/* 18:20 */       return true;
/* 19:   */     }
/* 20:22 */     return false;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public static int getIntegerProperty(Properties prop, String name, int defaultValue)
/* 24:   */   {
/* 25:27 */     String s = prop.getProperty(name);
/* 26:29 */     if (s == null) {
/* 27:31 */       return defaultValue;
/* 28:   */     }
/* 29:34 */     int ret = defaultValue;
/* 30:   */     try
/* 31:   */     {
/* 32:38 */       s = s.trim();
/* 33:39 */       ret = Integer.parseInt(s);
/* 34:   */     }
/* 35:   */     finally {}
/* 36:43 */     return ret;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public static int getIntegerProperty(Properties prop, String name, int defaultValue, int minValue, int maxValue)
/* 40:   */   {
/* 41:48 */     int i = getIntegerProperty(prop, name, defaultValue);
/* 42:49 */     i = Math.max(minValue, i);
/* 43:50 */     i = Math.min(maxValue, i);
/* 44:51 */     return i;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public static int[] getIntegerArray(Properties prop, String name, int defaultValue)
/* 48:   */   {
/* 49:56 */     String s = prop.getProperty(name);
/* 50:57 */     int[] ids = null;
/* 51:58 */     if (s != null)
/* 52:   */     {
/* 53:60 */       StringTokenizer stkn = new StringTokenizer(s, ",");
/* 54:61 */       ids = new int[stkn.countTokens()];
/* 55:62 */       for (int i = 0; i < ids.length; i++)
/* 56:   */       {
/* 57:64 */         String nextToken = stkn.nextToken().trim();
/* 58:65 */         ids[i] = Integer.parseInt(nextToken);
/* 59:   */       }
/* 60:   */     }
/* 61:68 */     if (ids == null) {
/* 62:69 */       ids = new int[] { defaultValue };
/* 63:   */     }
/* 64:70 */     return ids;
/* 65:   */   }
/* 66:   */   
/* 67:   */   public static BuilderBlockData getBlock(Properties prop, String name, BuilderBlockData defaultValue)
/* 68:   */   {
/* 69:75 */     Block block = null;
/* 70:76 */     int metadata = 0;
/* 71:   */     
/* 72:78 */     String s = prop.getProperty(name);
/* 73:79 */     if (s != null)
/* 74:   */     {
/* 75:81 */       if (s.startsWith("@")) {}
/* 76:84 */       StringTokenizer stkn = new StringTokenizer(s, ",");
/* 77:85 */       block = Block.getBlockFromName(((String)stkn.nextElement()).trim());
/* 78:86 */       if (stkn.hasMoreElements())
/* 79:   */       {
/* 80:88 */         String md = (String)stkn.nextElement();
/* 81:89 */         if (md != null) {
/* 82:90 */           metadata = Integer.parseInt(md.trim());
/* 83:   */         }
/* 84:   */       }
/* 85:   */     }
/* 86:93 */     if (block == null) {
/* 87:94 */       return defaultValue;
/* 88:   */     }
/* 89:96 */     BuilderBlockData b = new BuilderBlockData(block, metadata);
/* 90:97 */     System.out.println(b);
/* 91:98 */     return b;
/* 92:   */   }
/* 93:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.API.HelperReadConfig
 * JD-Core Version:    0.7.1
 */