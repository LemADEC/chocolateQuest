/*  1:   */ package com.chocolate.chocolateQuest.entity.mob.registry;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanMinotaur;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.SharedMonsterAttributes;
/*  7:   */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  8:   */ import net.minecraft.item.ItemStack;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class MobMinotaur
/* 12:   */   extends DungeonMonstersBase
/* 13:   */ {
/* 14:   */   public String getEntityName()
/* 15:   */   {
/* 16:16 */     return "minotaur";
/* 17:   */   }
/* 18:   */   
/* 19:   */   public int getFlagId()
/* 20:   */   {
/* 21:21 */     return 11;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public String getRegisteredEntityName()
/* 25:   */   {
/* 26:26 */     return "chocolateQuest.minotaur";
/* 27:   */   }
/* 28:   */   
/* 29:   */   public Entity getBoss(World world, int x, int y, int z)
/* 30:   */   {
/* 31:31 */     EntityHumanMinotaur m = new EntityHumanMinotaur(world);
/* 32:32 */     m.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(500.0D);
/* 33:33 */     m.setCurrentItemOrArmor(0, new ItemStack(ChocolateQuest.bigSwordBull));
/* 34:34 */     return m;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public Entity getEntity(World world, int x, int y, int z)
/* 38:   */   {
/* 39:38 */     return new EntityHumanMinotaur(world);
/* 40:   */   }
/* 41:   */   
/* 42:   */   public String getTeamName()
/* 43:   */   {
/* 44:42 */     return "undead";
/* 45:   */   }
/* 46:   */   
/* 47:   */   public double getHealth()
/* 48:   */   {
/* 49:45 */     return 40.0D;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public double getRange()
/* 53:   */   {
/* 54:48 */     return 35.0D;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public double getAttack()
/* 58:   */   {
/* 59:51 */     return 2.0D;
/* 60:   */   }
/* 61:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.registry.MobMinotaur
 * JD-Core Version:    0.7.1
 */