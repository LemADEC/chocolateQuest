/*   1:    */ package com.chocolate.chocolateQuest.command;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.boss.EntityBaseBoss;
/*   4:    */ import com.chocolate.chocolateQuest.entity.boss.EntityBull;
/*   5:    */ import com.chocolate.chocolateQuest.entity.boss.EntityGiantBoxer;
/*   6:    */ import com.chocolate.chocolateQuest.entity.boss.EntitySlimeBoss;
/*   7:    */ import com.chocolate.chocolateQuest.entity.boss.EntitySpiderBoss;
/*   8:    */ import com.chocolate.chocolateQuest.entity.boss.EntityTurtle;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ import java.util.List;
/*  11:    */ import net.minecraft.command.CommandBase;
/*  12:    */ import net.minecraft.command.ICommandSender;
/*  13:    */ import net.minecraft.util.ChunkCoordinates;
/*  14:    */ import net.minecraft.world.World;
/*  15:    */ 
/*  16:    */ public class CommandSpawnBoss
/*  17:    */   extends CommandBase
/*  18:    */ {
/*  19: 18 */   final int NAME_POSITION = 0;
/*  20: 19 */   final int LVL_POSITION = 1;
/*  21: 20 */   final int POS_X_POSITION = 2;
/*  22: 21 */   final int POS_Y_POSITION = 3;
/*  23: 22 */   final int POS_Z_POSITION = 4;
/*  24: 23 */   String[] NAMES = { "bull", "turtle", "spider", "monking", "slime" };
/*  25: 24 */   String[] NUMBERS_1_TO_10 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
/*  26:    */   
/*  27:    */   public String getCommandName()
/*  28:    */   {
/*  29: 29 */     return "CQSpawnBoss";
/*  30:    */   }
/*  31:    */   
/*  32:    */   public String getCommandUsage(ICommandSender icommandsender)
/*  33:    */   {
/*  34: 36 */     return "/CQSpawnBoss Boss_Name Boss_Size(1-10) positionX position_Y position_Z";
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void processCommand(ICommandSender icommandsender, String[] astring)
/*  38:    */   {
/*  39: 42 */     if (astring.length > 0)
/*  40:    */     {
/*  41: 43 */       EntityBaseBoss boss = null;
/*  42: 44 */       World world = icommandsender.getEntityWorld();
/*  43:    */       
/*  44: 46 */       double lvl = 1.0D;
/*  45: 47 */       if (astring.length > 1) {
/*  46: 48 */         lvl = Double.parseDouble(astring[1]);
/*  47:    */       }
/*  48: 50 */       String name = astring[0];
/*  49: 51 */       if (name.equals("monking")) {
/*  50: 52 */         boss = new EntityGiantBoxer(world);
/*  51: 54 */       } else if (name.equals("slime")) {
/*  52: 55 */         boss = new EntitySlimeBoss(world);
/*  53: 57 */       } else if (name.equals("spider")) {
/*  54: 58 */         boss = new EntitySpiderBoss(world);
/*  55: 60 */       } else if (name.equals("turtle")) {
/*  56: 61 */         boss = new EntityTurtle(world);
/*  57: 63 */       } else if (name.equals("bull")) {
/*  58: 64 */         boss = new EntityBull(world);
/*  59:    */       }
/*  60: 67 */       int posX = icommandsender.getPlayerCoordinates().posX;
/*  61: 68 */       int posY = icommandsender.getPlayerCoordinates().posY;
/*  62: 69 */       int posZ = icommandsender.getPlayerCoordinates().posZ;
/*  63: 71 */       if (astring.length > 2)
/*  64:    */       {
/*  65: 72 */         String s = astring[2];
/*  66: 73 */         posX = getCoordinate(s, posX);
/*  67:    */       }
/*  68: 75 */       if (astring.length > 3)
/*  69:    */       {
/*  70: 76 */         String s = astring[3];
/*  71: 77 */         posY = getCoordinate(s, posY);
/*  72:    */       }
/*  73: 79 */       if (astring.length > 4)
/*  74:    */       {
/*  75: 80 */         String s = astring[4];
/*  76: 81 */         posZ = getCoordinate(s, posZ);
/*  77:    */       }
/*  78: 84 */       if (boss != null)
/*  79:    */       {
/*  80: 85 */         boss.setPosition(posX - 0.5D, posY + 0.5D, posZ - 0.5D);
/*  81: 86 */         boss.setMonsterScale((float)lvl);
/*  82: 87 */         world.spawnEntityInWorld(boss);
/*  83:    */       }
/*  84:    */     }
/*  85:    */   }
/*  86:    */   
/*  87:    */   public int compareTo(Object arg0)
/*  88:    */   {
/*  89: 94 */     return 0;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public boolean canCommandSenderUseCommand(ICommandSender arg0)
/*  93:    */   {
/*  94:100 */     return super.canCommandSenderUseCommand(arg0);
/*  95:    */   }
/*  96:    */   
/*  97:    */   public int getRequiredPermissionLevel()
/*  98:    */   {
/*  99:105 */     return super.getRequiredPermissionLevel();
/* 100:    */   }
/* 101:    */   
/* 102:    */   public List addTabCompletionOptions(ICommandSender par1iCommandSender, String[] parArray)
/* 103:    */   {
/* 104:110 */     String last = "";
/* 105:111 */     ArrayList list = new ArrayList();
/* 106:112 */     if (parArray.length - 1 == 0)
/* 107:    */     {
/* 108:113 */       last = parArray[(parArray.length - 1)];
/* 109:114 */       for (String s : this.NAMES) {
/* 110:115 */         if (s.startsWith(last)) {
/* 111:116 */           list.add(s);
/* 112:    */         }
/* 113:    */       }
/* 114:    */     }
/* 115:119 */     else if (parArray.length - 1 == 1)
/* 116:    */     {
/* 117:120 */       for (String s : this.NUMBERS_1_TO_10) {
/* 118:121 */         list.add(s);
/* 119:    */       }
/* 120:    */     }
/* 121:124 */     else if (parArray.length <= 5)
/* 122:    */     {
/* 123:125 */       list.add("~");
/* 124:    */     }
/* 125:127 */     return list;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public int getCoordinate(String s, int pos)
/* 129:    */   {
/* 130:131 */     if (s.startsWith("~"))
/* 131:    */     {
/* 132:132 */       s = s.substring(1);
/* 133:133 */       if (s.length() == 0) {
/* 134:134 */         s = "0";
/* 135:    */       }
/* 136:    */     }
/* 137:    */     else
/* 138:    */     {
/* 139:137 */       pos = 0;
/* 140:    */     }
/* 141:138 */     pos += Integer.parseInt(s);
/* 142:139 */     return pos;
/* 143:    */   }
/* 144:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.command.CommandSpawnBoss
 * JD-Core Version:    0.7.1
 */