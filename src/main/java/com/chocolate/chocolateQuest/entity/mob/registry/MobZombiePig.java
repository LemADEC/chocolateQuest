/*  1:   */ package com.chocolate.chocolateQuest.entity.mob.registry;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanPigZombie;
/*  5:   */ import com.chocolate.chocolateQuest.gui.InventoryBag;
/*  6:   */ import com.chocolate.chocolateQuest.magic.Awakements;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.entity.SharedMonsterAttributes;
/*  9:   */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/* 10:   */ import net.minecraft.init.Items;
/* 11:   */ import net.minecraft.item.ItemStack;
/* 12:   */ import net.minecraft.nbt.NBTTagCompound;
/* 13:   */ import net.minecraft.world.World;
/* 14:   */ 
/* 15:   */ public class MobZombiePig
/* 16:   */   extends DungeonMonstersBase
/* 17:   */ {
/* 18:   */   public String getEntityName()
/* 19:   */   {
/* 20:20 */     return "pigzombie";
/* 21:   */   }
/* 22:   */   
/* 23:   */   public int getFlagId()
/* 24:   */   {
/* 25:25 */     return 11;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public String getRegisteredEntityName()
/* 29:   */   {
/* 30:30 */     return "chocolateQuest.pigzombie";
/* 31:   */   }
/* 32:   */   
/* 33:   */   public Entity getBoss(World world, int x, int y, int z)
/* 34:   */   {
/* 35:35 */     EntityHumanPigZombie p = new EntityHumanPigZombie(world);
/* 36:36 */     p.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(500.0D);
/* 37:   */     
/* 38:38 */     ItemStack is = new ItemStack(ChocolateQuest.staffFire);
/* 39:39 */     is.stackTagCompound = new NBTTagCompound();
/* 40:40 */     ItemStack[] cargo = { new ItemStack(ChocolateQuest.spell, 1, 4), new ItemStack(ChocolateQuest.spell, 1, 3), new ItemStack(ChocolateQuest.spell, 1, 7), new ItemStack(ChocolateQuest.spell, 1, 1), new ItemStack(ChocolateQuest.spell, 1, 0) };
/* 41:   */     
/* 42:   */ 
/* 43:   */ 
/* 44:   */ 
/* 45:   */ 
/* 46:   */ 
/* 47:47 */     InventoryBag.saveCargo(is, cargo);
/* 48:48 */     Awakements.addEnchant(is, Awakements.spellExpansion, 2);
/* 49:   */     
/* 50:50 */     p.setCurrentItemOrArmor(0, is);
/* 51:51 */     p.setLeftHandItem(new ItemStack(ChocolateQuest.cursedBone));
/* 52:   */     
/* 53:53 */     ItemStack armor = new ItemStack(ChocolateQuest.armorMage);
/* 54:54 */     NBTTagCompound tag = new NBTTagCompound();
/* 55:55 */     NBTTagCompound tagDisplay = new NBTTagCompound();
/* 56:56 */     tag.setTag("display", tagDisplay);
/* 57:57 */     armor.stackTagCompound = tag;
/* 58:58 */     tagDisplay.setInteger("color", 6684672);
/* 59:59 */     p.setCurrentItemOrArmor(3, armor);
/* 60:60 */     p.setCurrentItemOrArmor(2, new ItemStack(Items.chainmail_leggings));
/* 61:61 */     p.setCurrentItemOrArmor(1, new ItemStack(Items.chainmail_boots));
/* 62:62 */     return p;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public Entity getEntity(World world, int x, int y, int z)
/* 66:   */   {
/* 67:66 */     return new EntityHumanPigZombie(world);
/* 68:   */   }
/* 69:   */   
/* 70:   */   public String getTeamName()
/* 71:   */   {
/* 72:70 */     return "undead";
/* 73:   */   }
/* 74:   */   
/* 75:   */   public double getHealth()
/* 76:   */   {
/* 77:73 */     return 35.0D;
/* 78:   */   }
/* 79:   */   
/* 80:   */   public double getRange()
/* 81:   */   {
/* 82:76 */     return 30.0D;
/* 83:   */   }
/* 84:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.registry.MobZombiePig
 * JD-Core Version:    0.7.1
 */