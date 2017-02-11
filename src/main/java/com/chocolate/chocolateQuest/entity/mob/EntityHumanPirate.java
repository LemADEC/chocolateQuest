/*  1:   */ package com.chocolate.chocolateQuest.entity.mob;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.ai.AIFirefighter;
/*  5:   */ import com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase;
/*  6:   */ import net.minecraft.entity.ai.EntityAITasks;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ import net.minecraft.nbt.NBTTagCompound;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class EntityHumanPirate
/* 12:   */   extends EntityHumanMob
/* 13:   */ {
/* 14:   */   public EntityHumanPirate(World world)
/* 15:   */   {
/* 16:14 */     super(world);
/* 17:   */   }
/* 18:   */   
/* 19:   */   protected void addAITasks()
/* 20:   */   {
/* 21:18 */     this.tasks.addTask(4, new AIFirefighter(this, 1.0F, false));
/* 22:19 */     super.addAITasks();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public DungeonMonstersBase getMonsterType()
/* 26:   */   {
/* 27:23 */     return ChocolateQuest.pirate;
/* 28:   */   }
/* 29:   */   
/* 30:   */   protected String getLivingSound()
/* 31:   */   {
/* 32:27 */     return "chocolatequest:pirate_speak";
/* 33:   */   }
/* 34:   */   
/* 35:   */   protected String getHurtSound()
/* 36:   */   {
/* 37:30 */     return "chocolatequest:pirate_hurt";
/* 38:   */   }
/* 39:   */   
/* 40:   */   protected String getDeathSound()
/* 41:   */   {
/* 42:33 */     return "chocolatequest:pirate_death";
/* 43:   */   }
/* 44:   */   
/* 45:   */   public ItemStack getDiamondArmorForSlot(int slot)
/* 46:   */   {
/* 47:   */     ItemStack is;
/* 48:41 */     switch (slot)
/* 49:   */     {
/* 50:   */     case 3: 
/* 51:42 */       is = new ItemStack(ChocolateQuest.diamondPlate); break;
/* 52:   */     case 2: 
/* 53:43 */       is = new ItemStack(ChocolateQuest.diamondPants); break;
/* 54:   */     case 1: 
/* 55:44 */       is = new ItemStack(ChocolateQuest.diamondBoots); break;
/* 56:   */     default: 
/* 57:45 */       is = new ItemStack(ChocolateQuest.diamondHelmet);
/* 58:   */     }
/* 59:47 */     NBTTagCompound tag = new NBTTagCompound();
/* 60:48 */     NBTTagCompound tagDisplay = new NBTTagCompound();
/* 61:49 */     tag.setTag("display", tagDisplay);
/* 62:50 */     is.stackTagCompound = tag;
/* 63:51 */     tagDisplay.setInteger("color", 0);
/* 64:52 */     return is;
/* 65:   */   }
/* 66:   */   
/* 67:   */   public ItemStack getIronArmorForSlot(int slot)
/* 68:   */   {
/* 69:   */     ItemStack is;
/* 70:58 */     switch (slot)
/* 71:   */     {
/* 72:   */     case 3: 
/* 73:59 */       is = new ItemStack(ChocolateQuest.ironPlate); break;
/* 74:   */     case 2: 
/* 75:60 */       is = new ItemStack(ChocolateQuest.ironPants); break;
/* 76:   */     case 1: 
/* 77:61 */       is = new ItemStack(ChocolateQuest.ironBoots); break;
/* 78:   */     default: 
/* 79:62 */       is = new ItemStack(ChocolateQuest.ironHelmet);
/* 80:   */     }
/* 81:64 */     NBTTagCompound tag = new NBTTagCompound();
/* 82:65 */     NBTTagCompound tagDisplay = new NBTTagCompound();
/* 83:66 */     tag.setTag("display", tagDisplay);
/* 84:67 */     is.stackTagCompound = tag;
/* 85:68 */     tagDisplay.setInteger("color", 0);
/* 86:69 */     return is;
/* 87:   */   }
/* 88:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.EntityHumanPirate
 * JD-Core Version:    0.7.1
 */