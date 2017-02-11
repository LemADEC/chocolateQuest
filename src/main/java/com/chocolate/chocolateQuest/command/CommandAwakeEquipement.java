/*  1:   */ package com.chocolate.chocolateQuest.command;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.magic.Awakements;
/*  4:   */ import java.util.ArrayList;
/*  5:   */ import java.util.List;
/*  6:   */ import net.minecraft.command.CommandBase;
/*  7:   */ import net.minecraft.command.ICommandSender;
/*  8:   */ import net.minecraft.entity.player.EntityPlayer;
/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ 
/* 11:   */ public class CommandAwakeEquipement
/* 12:   */   extends CommandBase
/* 13:   */ {
/* 14:   */   public String getCommandName()
/* 15:   */   {
/* 16:17 */     return "CQAwakeItem";
/* 17:   */   }
/* 18:   */   
/* 19:   */   public String getCommandUsage(ICommandSender icommandsender)
/* 20:   */   {
/* 21:24 */     return "/CQAwakeItem with an item from the mod in the hand";
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void processCommand(ICommandSender icommandsender, String[] astring)
/* 25:   */   {
/* 26:30 */     EntityPlayer player = (EntityPlayer)icommandsender;
/* 27:31 */     ItemStack is = player.getCurrentEquippedItem();
/* 28:32 */     if (is != null) {
/* 29:33 */       for (Awakements aw : Awakements.awekements) {
/* 30:34 */         if (aw.canBeUsedOnItem(is)) {
/* 31:35 */           Awakements.addEnchant(is, aw, aw.getMaxLevel());
/* 32:   */         }
/* 33:   */       }
/* 34:   */     }
/* 35:   */   }
/* 36:   */   
/* 37:   */   public int compareTo(Object arg0)
/* 38:   */   {
/* 39:44 */     return 0;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public List addTabCompletionOptions(ICommandSender par1iCommandSender, String[] parArray)
/* 43:   */   {
/* 44:49 */     String last = parArray[(parArray.length - 1)];
/* 45:50 */     ArrayList list = new ArrayList();
/* 46:51 */     return list;
/* 47:   */   }
/* 48:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.command.CommandAwakeEquipement
 * JD-Core Version:    0.7.1
 */