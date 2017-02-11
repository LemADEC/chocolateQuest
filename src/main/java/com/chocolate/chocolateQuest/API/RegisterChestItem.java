/*  1:   */ package com.chocolate.chocolateQuest.API;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ 
/*  7:   */ public class RegisterChestItem
/*  8:   */ {
/*  9:10 */   public static ArrayList<WeightedItemStack> chestList = new ArrayList();
/* 10:11 */   public static ArrayList<WeightedItemStack> weaponList = new ArrayList();
/* 11:12 */   public static ArrayList<WeightedItemStack> mineralList = new ArrayList();
/* 12:13 */   public static ArrayList<WeightedItemStack> foodList = new ArrayList();
/* 13:14 */   public static ArrayList<WeightedItemStack> treasureList = new ArrayList();
/* 14:   */   
/* 15:   */   public static void addChestItem(ItemStack stack, int weight, ArrayList<WeightedItemStack> list)
/* 16:   */   {
/* 17:22 */     list.add(new WeightedItemStack(stack, weight));
/* 18:   */   }
/* 19:   */   
/* 20:   */   public static void addChestItem(ItemStack stack, int weight)
/* 21:   */   {
/* 22:31 */     chestList.add(new WeightedItemStack(stack, weight));
/* 23:   */   }
/* 24:   */   
/* 25:   */   public static void addToolsChestItem(ItemStack stack, int weight)
/* 26:   */   {
/* 27:40 */     weaponList.add(new WeightedItemStack(stack, weight));
/* 28:   */   }
/* 29:   */   
/* 30:   */   public static void addMineralsChestItem(ItemStack stack, int weight)
/* 31:   */   {
/* 32:49 */     mineralList.add(new WeightedItemStack(stack, weight));
/* 33:   */   }
/* 34:   */   
/* 35:   */   public static void addFoodChestItem(ItemStack stack, int weight)
/* 36:   */   {
/* 37:57 */     foodList.add(new WeightedItemStack(stack, weight));
/* 38:   */   }
/* 39:   */   
/* 40:   */   public static void addTreasureItem(ItemStack stack, int weight)
/* 41:   */   {
/* 42:66 */     treasureList.add(new WeightedItemStack(stack, weight));
/* 43:   */   }
/* 44:   */   
/* 45:   */   public static ItemStack getRandomItemStack(ArrayList<WeightedItemStack> chestList, Random random)
/* 46:   */   {
/* 47:71 */     int[] weights = new int[chestList.size()];
/* 48:72 */     int maxNum = 0;
/* 49:73 */     for (int i = 0; i < chestList.size(); i++)
/* 50:   */     {
/* 51:75 */       weights[i] = ((WeightedItemStack)chestList.get(i)).weight;
/* 52:76 */       maxNum += weights[i];
/* 53:   */     }
/* 54:79 */     int randomNum = random.nextInt(maxNum);
/* 55:80 */     int index = 0;
/* 56:82 */     for (int weightSum = weights[0]; weightSum <= randomNum; weightSum += weights[index]) {
/* 57:84 */       index++;
/* 58:   */     }
/* 59:86 */     return ((WeightedItemStack)chestList.get(index)).stack.copy();
/* 60:   */   }
/* 61:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.API.RegisterChestItem
 * JD-Core Version:    0.7.1
 */