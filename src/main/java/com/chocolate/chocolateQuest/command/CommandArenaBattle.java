/*   1:    */ package com.chocolate.chocolateQuest.command;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.entity.EntityReferee;
/*   5:    */ import com.chocolate.chocolateQuest.entity.boss.EntityBull;
/*   6:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanSkeleton;
/*   7:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanSpecter;
/*   8:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanZombie;
/*   9:    */ import com.chocolate.chocolateQuest.misc.EquipementHelper;
/*  10:    */ import java.util.ArrayList;
/*  11:    */ import java.util.List;
/*  12:    */ import java.util.Random;
/*  13:    */ import net.minecraft.command.CommandBase;
/*  14:    */ import net.minecraft.command.ICommandSender;
/*  15:    */ import net.minecraft.entity.EntityLiving;
/*  16:    */ import net.minecraft.entity.EntityLivingBase;
/*  17:    */ import net.minecraft.entity.player.EntityPlayer;
/*  18:    */ import net.minecraft.entity.player.InventoryPlayer;
/*  19:    */ import net.minecraft.item.ItemStack;
/*  20:    */ import net.minecraft.util.ChunkCoordinates;
/*  21:    */ import net.minecraft.util.FoodStats;
/*  22:    */ import net.minecraft.world.World;
/*  23:    */ 
/*  24:    */ public class CommandArenaBattle
/*  25:    */   extends CommandBase
/*  26:    */ {
/*  27: 23 */   final int LVL_POSITION = 0;
/*  28: 24 */   final int PLAYER_POSITION = 1;
/*  29: 26 */   String[] NUMBERS_1_TO_10 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
/*  30: 28 */   final int TRAINING = 0;
/*  31: 28 */   final int SMALL_BULL = 1;
/*  32:    */   
/*  33:    */   public String getCommandName()
/*  34:    */   {
/*  35: 33 */     return "CQGenerateArenaBattle";
/*  36:    */   }
/*  37:    */   
/*  38:    */   public String getCommandUsage(ICommandSender icommandsender)
/*  39:    */   {
/*  40: 40 */     return "/CQGenerateArenaBattle Battle_level player1 player2...";
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void processCommand(ICommandSender icommandsender, String[] astring)
/*  44:    */   {
/*  45: 46 */     if (astring.length > 0)
/*  46:    */     {
/*  47: 47 */       World world = icommandsender.getEntityWorld();
/*  48:    */       
/*  49: 49 */       int lvl = 1;
/*  50: 50 */       if (astring.length > 0) {
/*  51: 51 */         lvl = Integer.parseInt(astring[0]);
/*  52:    */       }
/*  53: 53 */       List<EntityLivingBase> playersList = new ArrayList();
/*  54: 54 */       if (astring.length > 1) {
/*  55: 55 */         for (int i = 1; i < astring.length; i++)
/*  56:    */         {
/*  57: 56 */           EntityPlayer player = world.getPlayerEntityByName(astring[i]);
/*  58: 57 */           if (player != null) {
/*  59: 58 */             playersList.add(player);
/*  60:    */           }
/*  61:    */         }
/*  62:    */       }
/*  63: 62 */       if (playersList.isEmpty())
/*  64:    */       {
/*  65: 63 */         EntityPlayer player = world.getPlayerEntityByName(icommandsender.getCommandSenderName());
/*  66: 64 */         if (player != null) {
/*  67: 65 */           playersList.add(player);
/*  68:    */         }
/*  69:    */       }
/*  70: 68 */       int posX = icommandsender.getPlayerCoordinates().posX;
/*  71: 69 */       int posY = icommandsender.getPlayerCoordinates().posY;
/*  72: 70 */       int posZ = icommandsender.getPlayerCoordinates().posZ;
/*  73:    */       
/*  74: 72 */       int playerCount = 0;
/*  75: 73 */       for (EntityLivingBase e : playersList)
/*  76:    */       {
/*  77: 74 */         playerCount++;
/*  78: 75 */         EntityPlayer player = (EntityPlayer)e;
/*  79: 76 */         player.capabilities.isCreativeMode = false;
/*  80: 77 */         player.sendPlayerAbilities();
/*  81: 78 */         player.setHealth(player.getMaxHealth());
/*  82: 79 */         player.getFoodStats().setFoodLevel(20);
/*  83: 80 */         addPlayerEquipement(player, lvl);
/*  84:    */       }
/*  85: 83 */       List<EntityLivingBase> mobList = getMobs(world, lvl, posX, posY, posZ, playerCount);
/*  86: 84 */       for (EntityLivingBase e : mobList)
/*  87:    */       {
/*  88: 85 */         world.spawnEntityInWorld(e);
/*  89: 86 */         ((EntityLiving)e).setAttackTarget((EntityLivingBase)playersList.get(world.rand.nextInt(playersList.size())));
/*  90:    */       }
/*  91: 89 */       EntityReferee referee = new EntityReferee(world, playersList, mobList);
/*  92: 90 */       if (referee != null)
/*  93:    */       {
/*  94: 91 */         referee.setPosition(posX - 0.5D, posY + 0.5D, posZ - 0.5D);
/*  95: 92 */         world.spawnEntityInWorld(referee);
/*  96:    */       }
/*  97:    */     }
/*  98:    */   }
/*  99:    */   
/* 100:    */   public List<EntityLivingBase> getMobs(World world, int lvl, int posX, int posY, int posZ, int playerCount)
/* 101:    */   {
/* 102: 98 */     List<EntityLivingBase> mobList = new ArrayList();
/* 103: 99 */     int DIST = 15;int MOB_LVL = 1;
/* 104:100 */     if (lvl == 1)
/* 105:    */     {
/* 106:101 */       EntityBull bull = new EntityBull(world);
/* 107:102 */       bull.setMonsterScale(1.0F);
/* 108:103 */       bull.setPosition(posX, posY, posZ + 15);
/* 109:104 */       mobList.add(bull);
/* 110:    */     }
/* 111:    */     else
/* 112:    */     {
/* 113:107 */       EntityHumanSkeleton skeleton = new EntityHumanSkeleton(world);
/* 114:108 */       skeleton.setPosition(posX + 15, posY, posZ);
/* 115:109 */       EquipementHelper.equipHumanRandomly(skeleton, 1, 2);
/* 116:110 */       mobList.add(skeleton);
/* 117:    */       
/* 118:112 */       EntityHumanSpecter specter = new EntityHumanSpecter(world);
/* 119:113 */       specter.setPosition(posX, posY, posZ + 15);
/* 120:114 */       EquipementHelper.equipHumanRandomly(specter, 1, 8);
/* 121:115 */       mobList.add(specter);
/* 122:    */       
/* 123:117 */       EntityHumanZombie zombie = new EntityHumanZombie(world);
/* 124:118 */       zombie.setPosition(posX - 15, posY, posZ);
/* 125:119 */       EquipementHelper.equipHumanRandomly(zombie, 1, 3);
/* 126:120 */       mobList.add(zombie);
/* 127:    */     }
/* 128:123 */     return mobList;
/* 129:    */   }
/* 130:    */   
/* 131:    */   public void addPlayerEquipement(EntityPlayer player, int lvl)
/* 132:    */   {
/* 133:127 */     for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
/* 134:128 */       player.inventory.setInventorySlotContents(i, null);
/* 135:    */     }
/* 136:130 */     EquipementHelper.equipEntity(player, 1);
/* 137:131 */     player.inventory.setInventorySlotContents(0, new ItemStack(ChocolateQuest.diamondSwordAndShield));
/* 138:132 */     player.inventory.setInventorySlotContents(1, new ItemStack(ChocolateQuest.diamondDagger));
/* 139:133 */     player.inventory.setInventorySlotContents(2, new ItemStack(ChocolateQuest.diamondSpear));
/* 140:134 */     player.inventory.setInventorySlotContents(3, new ItemStack(ChocolateQuest.diamondBigsword));
/* 141:135 */     player.inventory.setInventorySlotContents(8, new ItemStack(ChocolateQuest.potion, 64, 0));
/* 142:    */   }
/* 143:    */   
/* 144:    */   public int compareTo(Object arg0)
/* 145:    */   {
/* 146:140 */     return 0;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public boolean canCommandSenderUseCommand(ICommandSender arg0)
/* 150:    */   {
/* 151:146 */     return super.canCommandSenderUseCommand(arg0);
/* 152:    */   }
/* 153:    */   
/* 154:    */   public int getRequiredPermissionLevel()
/* 155:    */   {
/* 156:151 */     return super.getRequiredPermissionLevel();
/* 157:    */   }
/* 158:    */   
/* 159:    */   public List addTabCompletionOptions(ICommandSender par1iCommandSender, String[] parArray)
/* 160:    */   {
/* 161:156 */     String last = parArray[(parArray.length - 1)];
/* 162:157 */     ArrayList list = new ArrayList();
/* 163:158 */     if (parArray.length - 1 == 0) {
/* 164:159 */       for (String s : this.NUMBERS_1_TO_10) {
/* 165:160 */         list.add(s);
/* 166:    */       }
/* 167:    */     } else {
/* 168:164 */       for (Object object : par1iCommandSender.getEntityWorld().playerEntities)
/* 169:    */       {
/* 170:165 */         EntityPlayer p = (EntityPlayer)object;
/* 171:166 */         if (p.getCommandSenderName().startsWith(last)) {
/* 172:167 */           list.add(p.getCommandSenderName());
/* 173:    */         }
/* 174:    */       }
/* 175:    */     }
/* 176:170 */     return list;
/* 177:    */   }
/* 178:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.command.CommandArenaBattle
 * JD-Core Version:    0.7.1
 */