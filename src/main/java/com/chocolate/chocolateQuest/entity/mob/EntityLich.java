/*  1:   */ package com.chocolate.chocolateQuest.entity.mob;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.ai.EnumAiCombat;
/*  5:   */ import com.chocolate.chocolateQuest.gui.InventoryBag;
/*  6:   */ import com.chocolate.chocolateQuest.magic.Awakements;
/*  7:   */ import net.minecraft.entity.SharedMonsterAttributes;
/*  8:   */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  9:   */ import net.minecraft.init.Items;
/* 10:   */ import net.minecraft.item.ItemStack;
/* 11:   */ import net.minecraft.nbt.NBTTagCompound;
/* 12:   */ import net.minecraft.world.World;
/* 13:   */ 
/* 14:   */ public class EntityLich
/* 15:   */   extends EntityHumanZombie
/* 16:   */ {
/* 17:   */   public EntityLich(World world)
/* 18:   */   {
/* 19:17 */     super(world);
/* 20:18 */     this.AICombatMode = EnumAiCombat.EVASIVE.ordinal();
/* 21:19 */     this.potionCount = 0;
/* 22:20 */     setCurrentItemOrArmor(0, getEquipedWeapon());
/* 23:21 */     setLeftHandItem(new ItemStack(ChocolateQuest.cursedBone));
/* 24:   */     
/* 25:23 */     setCurrentItemOrArmor(4, new ItemStack(ChocolateQuest.witchHat));
/* 26:24 */     ItemStack is = new ItemStack(ChocolateQuest.armorMage);
/* 27:25 */     NBTTagCompound tag = new NBTTagCompound();
/* 28:26 */     NBTTagCompound tagDisplay = new NBTTagCompound();
/* 29:27 */     tag.setTag("display", tagDisplay);
/* 30:28 */     is.stackTagCompound = tag;
/* 31:29 */     tagDisplay.setInteger("color", 2236962);
/* 32:30 */     setCurrentItemOrArmor(3, is);
/* 33:31 */     setCurrentItemOrArmor(2, new ItemStack(Items.chainmail_leggings));
/* 34:32 */     setCurrentItemOrArmor(1, new ItemStack(Items.chainmail_boots));
/* 35:33 */     setAIForCurrentMode();
/* 36:   */   }
/* 37:   */   
/* 38:   */   public ItemStack getEquipedWeapon()
/* 39:   */   {
/* 40:38 */     ItemStack is = new ItemStack(ChocolateQuest.staffPhysic);
/* 41:39 */     is.stackTagCompound = new NBTTagCompound();
/* 42:40 */     ItemStack[] cargo = { new ItemStack(ChocolateQuest.spell, 1, 4), new ItemStack(ChocolateQuest.spell, 1, 3), new ItemStack(ChocolateQuest.spell, 1, 7), new ItemStack(ChocolateQuest.spell, 1, 0) };
/* 43:   */     
/* 44:   */ 
/* 45:   */ 
/* 46:   */ 
/* 47:   */ 
/* 48:46 */     InventoryBag.saveCargo(is, cargo);
/* 49:47 */     Awakements.addEnchant(is, Awakements.spellExpansion, 2);
/* 50:48 */     return is;
/* 51:   */   }
/* 52:   */   
/* 53:   */   protected void applyEntityAttributes()
/* 54:   */   {
/* 55:54 */     super.applyEntityAttributes();
/* 56:55 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
/* 57:56 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0D);
/* 58:   */   }
/* 59:   */   
/* 60:   */   public int getLeadershipValue()
/* 61:   */   {
/* 62:61 */     return 1000;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public boolean isBoss()
/* 66:   */   {
/* 67:65 */     return true;
/* 68:   */   }
/* 69:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.EntityLich
 * JD-Core Version:    0.7.1
 */