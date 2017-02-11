/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  6:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.entity.EntityLivingBase;
/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ import net.minecraft.world.World;
/* 11:   */ 
/* 12:   */ public class ItemArmorSpider
/* 13:   */   extends ItemArmorBase
/* 14:   */ {
/* 15:   */   int type;
/* 16:   */   String name;
/* 17:   */   
/* 18:   */   public ItemArmorSpider(int type, String name)
/* 19:   */   {
/* 20:21 */     super(ItemArmorBase.TURTLE, type);
/* 21:22 */     this.type = type;
/* 22:23 */     this.name = name;
/* 23:24 */     setEpic();
/* 24:   */   }
/* 25:   */   
/* 26:   */   @SideOnly(Side.CLIENT)
/* 27:   */   public void registerIcons(IIconRegister iconRegister) {}
/* 28:   */   
/* 29:   */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/* 30:   */   {
/* 31:36 */     if (slot == 2) {
/* 32:37 */       return "chocolatequest:textures/entity/armorSpider_2.png";
/* 33:   */     }
/* 34:38 */     return "chocolatequest:textures/entity/armorSpider.png";
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void onUpdateEquiped(ItemStack par1ItemStack, World world, EntityLivingBase entity)
/* 38:   */   {
/* 39:44 */     boolean isCollidedVertically = false;
/* 40:45 */     if (isFullSet(entity, par1ItemStack))
/* 41:   */     {
/* 42:46 */       if ((entity.isCollidedHorizontally) || (isCollidedVertically))
/* 43:   */       {
/* 44:48 */         if (!entity.isSneaking())
/* 45:   */         {
/* 46:50 */           entity.motionY = 0.0D;
/* 47:51 */           if (entity.moveForward > 0.0F) {
/* 48:53 */             entity.motionY = 0.2D;
/* 49:   */           }
/* 50:   */         }
/* 51:56 */         entity.onGround = true;
/* 52:   */       }
/* 53:58 */       entity.fallDistance = 0.0F;
/* 54:   */     }
/* 55:   */   }
/* 56:   */   
/* 57:   */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemMaterial)
/* 58:   */   {
/* 59:65 */     return ((itemMaterial.getItem() == ChocolateQuest.material) && (itemMaterial.getItemDamage() == 3)) || (super.getIsRepairable(itemToRepair, itemMaterial));
/* 60:   */   }
/* 61:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorSpider
 * JD-Core Version:    0.7.1
 */