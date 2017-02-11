/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.entity.player.EntityPlayer;
/*  6:   */ import net.minecraft.entity.projectile.EntityArrow;
/*  7:   */ import net.minecraft.item.EnumAction;
/*  8:   */ import net.minecraft.item.ItemStack;
/*  9:   */ import net.minecraft.potion.Potion;
/* 10:   */ import net.minecraft.potion.PotionEffect;
/* 11:   */ import net.minecraft.util.AxisAlignedBB;
/* 12:   */ import net.minecraft.util.StatCollector;
/* 13:   */ import net.minecraft.util.Vec3;
/* 14:   */ import net.minecraft.world.World;
/* 15:   */ 
/* 16:   */ public class ItemShied
/* 17:   */   extends ItemMulti
/* 18:   */ {
/* 19:   */   public ItemShied()
/* 20:   */   {
/* 21:19 */     super(new String[] { "Chocolate", "Terra", "Aigua", "Ignis", "Orc", "Dwarf", "Triton", "Zombie", "Skeleton", "Pirate", "Walker", "Wood", "Specter", "Diurna", "Nocturna", "Turtle", "Rusted", "Monking", "Spider" }, "s");
/* 22:   */   }
/* 23:   */   
/* 24:   */   public String getItemStackDisplayName(ItemStack itemstack)
/* 25:   */   {
/* 26:33 */     return ("" + StatCollector.translateToLocal("item.shield.name")).trim();
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
/* 30:   */   {
/* 31:39 */     if ((entity instanceof EntityPlayer))
/* 32:   */     {
/* 33:41 */       EntityPlayer ep = (EntityPlayer)entity;
/* 34:43 */       if ((ep.isBlocking()) && (ep.getCurrentEquippedItem() == itemStack))
/* 35:   */       {
/* 36:45 */         ep.addPotionEffect(new PotionEffect(Potion.resistance.id, 1, 3));
/* 37:   */         
/* 38:47 */         List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(1.2D, 1.2D, 1.2D));
/* 39:48 */         for (Entity e : list) {
/* 40:50 */           if ((e instanceof EntityArrow))
/* 41:   */           {
/* 42:52 */             e.motionX = entity.getLookVec().xCoord;
/* 43:   */             
/* 44:54 */             e.motionZ = entity.getLookVec().zCoord;
/* 45:   */           }
/* 46:   */         }
/* 47:   */       }
/* 48:   */     }
/* 49:   */   }
/* 50:   */   
/* 51:   */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/* 52:   */   {
/* 53:64 */     entityPlayer.setItemInUse(itemstack, 72000);
/* 54:65 */     return itemstack;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public int getMaxItemUseDuration(ItemStack par1ItemStack)
/* 58:   */   {
/* 59:69 */     return 72000;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public EnumAction getItemUseAction(ItemStack par1ItemStack)
/* 63:   */   {
/* 64:74 */     return EnumAction.block;
/* 65:   */   }
/* 66:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemShied
 * JD-Core Version:    0.7.1
 */