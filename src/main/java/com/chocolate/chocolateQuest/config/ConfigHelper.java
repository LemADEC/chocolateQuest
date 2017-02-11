/*  1:   */ package com.chocolate.chocolateQuest.config;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.API.RegisterChestItem;
/*  4:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  5:   */ import cpw.mods.fml.common.FMLLog;
/*  6:   */ import java.io.File;
/*  7:   */ import java.io.FileNotFoundException;
/*  8:   */ import java.io.FileReader;
/*  9:   */ import java.io.IOException;
/* 10:   */ import java.util.ArrayList;
/* 11:   */ import java.util.Enumeration;
/* 12:   */ import java.util.Properties;
/* 13:   */ import java.util.StringTokenizer;
/* 14:   */ import net.minecraft.item.Item;
/* 15:   */ import net.minecraft.item.ItemStack;
/* 16:   */ import net.minecraft.util.RegistryNamespaced;
/* 17:   */ import org.apache.logging.log4j.Logger;
/* 18:   */ 
/* 19:   */ public class ConfigHelper
/* 20:   */ {
/* 21:   */   public static void readChests()
/* 22:   */   {
/* 23:24 */     BDHelper.println("## Chest items register ##");
/* 24:25 */     getItems("Chocolate/ChestConfig/chests.prop", RegisterChestItem.chestList, "default");
/* 25:26 */     getItems("Chocolate/ChestConfig/treasures.prop", RegisterChestItem.treasureList, "treasure");
/* 26:27 */     getItems("Chocolate/ChestConfig/weapons.prop", RegisterChestItem.weaponList, "weapon");
/* 27:28 */     getItems("Chocolate/ChestConfig/ores.prop", RegisterChestItem.mineralList, "ores");
/* 28:29 */     getItems("Chocolate/ChestConfig/food.prop", RegisterChestItem.foodList, "food");
/* 29:   */   }
/* 30:   */   
/* 31:   */   public static void getItems(String st, ArrayList list, String listName)
/* 32:   */   {
/* 33:33 */     File file = new File(BDHelper.getAppDir(), st);
/* 34:   */     try
/* 35:   */     {
/* 36:36 */       Properties prop = new Properties();
/* 37:37 */       FileReader fr = new FileReader(file);
/* 38:38 */       prop.load(fr);
/* 39:39 */       Enumeration<Object> e = prop.elements();
/* 40:40 */       int cont = 0;
/* 41:42 */       while (e.hasMoreElements())
/* 42:   */       {
/* 43:44 */         String s = (String)e.nextElement();
/* 44:45 */         StringTokenizer stkn = new StringTokenizer(s, ",");
/* 45:46 */         int tokens = stkn.countTokens();
/* 46:47 */         String name = ((String)stkn.nextElement()).trim();
/* 47:48 */         int numberOfItems = 1;
/* 48:49 */         int damageOfItems = 0;
/* 49:50 */         int itemsWeight = 100;
/* 50:51 */         if (tokens == 4)
/* 51:   */         {
/* 52:53 */           numberOfItems = Integer.parseInt(((String)stkn.nextElement()).trim());
/* 53:54 */           damageOfItems = Integer.parseInt(((String)stkn.nextElement()).trim());
/* 54:55 */           itemsWeight = Integer.parseInt(((String)stkn.nextElement()).trim());
/* 55:   */         }
/* 56:57 */         if (tokens == 3)
/* 57:   */         {
/* 58:59 */           numberOfItems = Integer.parseInt(((String)stkn.nextElement()).trim());
/* 59:60 */           itemsWeight = Integer.parseInt(((String)stkn.nextElement()).trim());
/* 60:   */         }
/* 61:62 */         if (tokens == 2) {
/* 62:64 */           itemsWeight = Integer.parseInt(((String)stkn.nextElement()).trim());
/* 63:   */         }
/* 64:66 */         cont++;
/* 65:67 */         FMLLog.getLogger().info("Added " + name + " to " + listName + " chests list");
/* 66:68 */         Object item = Item.itemRegistry.getObject(name);
/* 67:69 */         if ((item instanceof Item))
/* 68:   */         {
/* 69:70 */           ItemStack is = new ItemStack((Item)item, numberOfItems, damageOfItems);
/* 70:71 */           RegisterChestItem.addChestItem(is, itemsWeight, list);
/* 71:   */         }
/* 72:   */         else
/* 73:   */         {
/* 74:73 */           BDHelper.println("Error loading item: " + s + " into " + listName + " chests list");
/* 75:   */         }
/* 76:   */       }
/* 77:   */     }
/* 78:   */     catch (FileNotFoundException e)
/* 79:   */     {
/* 80:79 */       FMLLog.getLogger().error("Not found config file at Chocolate Quest mod: " + file.getAbsolutePath());
/* 81:80 */       e.printStackTrace();
/* 82:   */     }
/* 83:   */     catch (IOException e)
/* 84:   */     {
/* 85:84 */       FMLLog.getLogger().error("Error reading config file at Chocolate Quest mod: " + file.getAbsolutePath());
/* 86:85 */       e.printStackTrace();
/* 87:   */     }
/* 88:   */   }
/* 89:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.config.ConfigHelper
 * JD-Core Version:    0.7.1
 */