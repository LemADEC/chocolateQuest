/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.items.ItemArmorBase;
/*  4:   */ import com.chocolate.chocolateQuest.items.swords.ItemBDSword;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.player.EntityPlayer;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ 
/*  9:   */ public class AwakementAutoRepair
/* 10:   */   extends Awakements
/* 11:   */ {
/* 12:   */   public AwakementAutoRepair(String name, int icon)
/* 13:   */   {
/* 14:13 */     super(name, icon);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public boolean canBeUsedOnItem(ItemStack is)
/* 18:   */   {
/* 19:18 */     if ((is.getItem() instanceof ItemArmorBase)) {
/* 20:19 */       return ((ItemArmorBase)is.getItem()).isEpic();
/* 21:   */     }
/* 22:21 */     return is.getItem() instanceof ItemBDSword;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public int getMaxLevel()
/* 26:   */   {
/* 27:26 */     return 4;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void onUpdate(Entity entity, ItemStack itemStack)
/* 31:   */   {
/* 32:31 */     if ((entity instanceof EntityPlayer))
/* 33:   */     {
/* 34:32 */       EntityPlayer player = (EntityPlayer)entity;
/* 35:33 */       int repairLevel = getEnchantLevel(itemStack, this);
/* 36:34 */       if ((itemStack.getItemDamage() >= repairLevel) && 
/* 37:35 */         (player.experienceLevel > 0))
/* 38:   */       {
/* 39:36 */         player.addExperience(-1);
/* 40:37 */         player.experienceTotal = 0;
/* 41:38 */         if (player.experience < 0.0F) {
/* 42:39 */           if (player.experienceLevel > 0)
/* 43:   */           {
/* 44:40 */             player.addExperienceLevel(-1);
/* 45:41 */             player.experience = 1.0F;
/* 46:   */           }
/* 47:   */           else
/* 48:   */           {
/* 49:43 */             player.experience = 0.0F;
/* 50:   */           }
/* 51:   */         }
/* 52:45 */         itemStack.setItemDamage(itemStack.getItemDamage() - repairLevel);
/* 53:   */       }
/* 54:   */     }
/* 55:   */   }
/* 56:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.AwakementAutoRepair
 * JD-Core Version:    0.7.1
 */