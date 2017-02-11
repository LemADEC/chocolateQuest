/*  1:   */ package com.chocolate.chocolateQuest.entity.mob;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.ai.EnumAiCombat;
/*  5:   */ import com.chocolate.chocolateQuest.gui.InventoryBag;
/*  6:   */ import net.minecraft.entity.SharedMonsterAttributes;
/*  7:   */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  8:   */ import net.minecraft.init.Items;
/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ import net.minecraft.nbt.NBTTagCompound;
/* 11:   */ import net.minecraft.world.World;
/* 12:   */ 
/* 13:   */ public class EntityNecromancer
/* 14:   */   extends EntityHumanSkeleton
/* 15:   */ {
/* 16:   */   public EntityNecromancer(World world)
/* 17:   */   {
/* 18:16 */     super(world);
/* 19:17 */     this.AICombatMode = EnumAiCombat.EVASIVE.ordinal();
/* 20:18 */     this.potionCount = 0;
/* 21:19 */     setCurrentItemOrArmor(0, getEquipedWeapon());
/* 22:20 */     setLeftHandItem(new ItemStack(ChocolateQuest.cursedBone));
/* 23:21 */     ItemStack is = new ItemStack(ChocolateQuest.armorMage);
/* 24:22 */     NBTTagCompound tag = new NBTTagCompound();
/* 25:23 */     NBTTagCompound tagDisplay = new NBTTagCompound();
/* 26:24 */     tag.setTag("display", tagDisplay);
/* 27:25 */     is.stackTagCompound = tag;
/* 28:26 */     tagDisplay.setInteger("color", 3355443);
/* 29:27 */     setCurrentItemOrArmor(3, is);
/* 30:28 */     setCurrentItemOrArmor(2, new ItemStack(Items.leather_leggings));
/* 31:29 */     setCurrentItemOrArmor(1, new ItemStack(Items.leather_boots));
/* 32:30 */     setAIForCurrentMode();
/* 33:   */   }
/* 34:   */   
/* 35:   */   public ItemStack getEquipedWeapon()
/* 36:   */   {
/* 37:35 */     ItemStack is = new ItemStack(ChocolateQuest.staffPhysic);
/* 38:36 */     is.stackTagCompound = new NBTTagCompound();
/* 39:37 */     ItemStack[] cargo = { new ItemStack(ChocolateQuest.spell, 1, 7), new ItemStack(ChocolateQuest.spell, 1, 0) };
/* 40:   */     
/* 41:   */ 
/* 42:   */ 
/* 43:41 */     InventoryBag.saveCargo(is, cargo);
/* 44:42 */     return is;
/* 45:   */   }
/* 46:   */   
/* 47:   */   protected void applyEntityAttributes()
/* 48:   */   {
/* 49:48 */     super.applyEntityAttributes();
/* 50:49 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
/* 51:50 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0D);
/* 52:   */   }
/* 53:   */   
/* 54:   */   public int getLeadershipValue()
/* 55:   */   {
/* 56:55 */     return 1000;
/* 57:   */   }
/* 58:   */   
/* 59:   */   public boolean isBoss()
/* 60:   */   {
/* 61:59 */     return true;
/* 62:   */   }
/* 63:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.EntityNecromancer
 * JD-Core Version:    0.7.1
 */