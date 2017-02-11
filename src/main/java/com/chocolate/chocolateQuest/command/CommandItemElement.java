/*  1:   */ package com.chocolate.chocolateQuest.command;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.items.swords.ItemBDSword;
/*  4:   */ import com.chocolate.chocolateQuest.magic.Elements;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import java.util.List;
/*  7:   */ import net.minecraft.command.CommandBase;
/*  8:   */ import net.minecraft.command.ICommandSender;
/*  9:   */ import net.minecraft.entity.player.EntityPlayer;
/* 10:   */ import net.minecraft.item.ItemStack;
/* 11:   */ 
/* 12:   */ public class CommandItemElement
/* 13:   */   extends CommandBase
/* 14:   */ {
/* 15:   */   public String getCommandName()
/* 16:   */   {
/* 17:18 */     return "CQAddElement";
/* 18:   */   }
/* 19:   */   
/* 20:   */   public String getCommandUsage(ICommandSender icommandsender)
/* 21:   */   {
/* 22:25 */     return "/CQAddElement \"element\" \"ammount\" with an blade from the mod in the hand \n Default ma";
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void processCommand(ICommandSender icommandsender, String[] astring)
/* 26:   */   {
/* 27:31 */     int value = 4;
/* 28:32 */     if (astring.length >= 1) {
/* 29:33 */       value = Integer.parseInt(astring[1]);
/* 30:   */     }
/* 31:35 */     if (astring.length >= 0)
/* 32:   */     {
/* 33:36 */       String element = astring[0];
/* 34:37 */       EntityPlayer player = (EntityPlayer)icommandsender;
/* 35:38 */       ItemStack is = player.getCurrentEquippedItem();
/* 36:39 */       if (is != null)
/* 37:   */       {
/* 38:40 */         if (element.equals("blast")) {
/* 39:41 */           ((ItemBDSword)is.getItem()).setBlastDamage(value, is);
/* 40:   */         }
/* 41:42 */         if (element.equals("fire")) {
/* 42:43 */           ((ItemBDSword)is.getItem()).setFireDamage(value, is);
/* 43:   */         }
/* 44:44 */         if (element.equals("physic")) {
/* 45:45 */           ((ItemBDSword)is.getItem()).setPhysicDamage(value, is);
/* 46:   */         }
/* 47:46 */         if (element.equals("magic")) {
/* 48:47 */           ((ItemBDSword)is.getItem()).setMagicDamage(value, is);
/* 49:   */         }
/* 50:48 */         if (element.equals("light")) {
/* 51:49 */           ((ItemBDSword)is.getItem()).setElementValue(is, Elements.light, value);
/* 52:   */         }
/* 53:50 */         if (element.equals("dark")) {
/* 54:51 */           ((ItemBDSword)is.getItem()).setElementValue(is, Elements.darkness, value);
/* 55:   */         }
/* 56:   */       }
/* 57:   */     }
/* 58:   */   }
/* 59:   */   
/* 60:   */   public int compareTo(Object arg0)
/* 61:   */   {
/* 62:58 */     return 0;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public List addTabCompletionOptions(ICommandSender par1iCommandSender, String[] parArray)
/* 66:   */   {
/* 67:63 */     ArrayList list = new ArrayList();
/* 68:64 */     list.add("physic");
/* 69:65 */     list.add("magic");
/* 70:66 */     list.add("blast");
/* 71:67 */     list.add("fire");
/* 72:68 */     return list;
/* 73:   */   }
/* 74:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.command.CommandItemElement
 * JD-Core Version:    0.7.1
 */