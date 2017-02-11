/*  1:   */ package com.chocolate.chocolateQuest.magic;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.items.ItemArmorBase;
/*  4:   */ import com.chocolate.chocolateQuest.items.swords.ItemBDSword;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.item.EntityItem;
/*  7:   */ import net.minecraft.entity.player.EntityPlayer;
/*  8:   */ import net.minecraft.item.ItemStack;
/*  9:   */ import net.minecraft.nbt.NBTTagCompound;
/* 10:   */ import net.minecraft.util.StatCollector;
/* 11:   */ import net.minecraft.world.World;
/* 12:   */ 
/* 13:   */ public class AwakementProperty
/* 14:   */   extends Awakements
/* 15:   */ {
/* 16:   */   public AwakementProperty(String name, int icon)
/* 17:   */   {
/* 18:16 */     super(name, icon);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public boolean canBeUsedOnItem(ItemStack is)
/* 22:   */   {
/* 23:21 */     if ((is.getItem() instanceof ItemArmorBase)) {
/* 24:22 */       return ((ItemArmorBase)is.getItem()).isEpic();
/* 25:   */     }
/* 26:24 */     return is.getItem() instanceof ItemBDSword;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public int getMaxLevel()
/* 30:   */   {
/* 31:29 */     return 1;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void onEntityItemUpdate(EntityItem entityItem)
/* 35:   */   {
/* 36:33 */     ItemStack is = entityItem.getEntityItem();
/* 37:34 */     if (hasEnchant(is, this))
/* 38:   */     {
/* 39:35 */       if ((entityItem.getEntityItem().stackTagCompound != null) && (entityItem.age > entityItem.lifespan - 100))
/* 40:   */       {
/* 41:37 */         entityItem.age = 0;
/* 42:38 */         EntityPlayer player = entityItem.worldObj.getPlayerEntityByName(getOwner(is));
/* 43:39 */         if (player != null) {
/* 44:40 */           entityItem.setPosition(player.posX, player.posY, player.posZ);
/* 45:   */         }
/* 46:   */       }
/* 47:42 */       if (entityItem.isBurning())
/* 48:   */       {
/* 49:44 */         entityItem.extinguish();
/* 50:45 */         entityItem.motionY = 0.2D;
/* 51:46 */         entityItem.attackEntityFrom(null, -1.0F);
/* 52:   */       }
/* 53:   */     }
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void onUpdate(Entity entity, ItemStack itemStack)
/* 57:   */   {
/* 58:53 */     if ((entity.ticksExisted % 400 == 0) && (getOwner(itemStack) == null)) {
/* 59:55 */       if ((entity instanceof EntityPlayer))
/* 60:   */       {
/* 61:57 */         EntityPlayer ep = (EntityPlayer)entity;
/* 62:58 */         setOwner(itemStack, ep.getCommandSenderName());
/* 63:   */       }
/* 64:   */     }
/* 65:   */   }
/* 66:   */   
/* 67:   */   public String getOwner(ItemStack is)
/* 68:   */   {
/* 69:64 */     if (is.getTagCompound() == null) {
/* 70:65 */       return null;
/* 71:   */     }
/* 72:66 */     String ownerName = is.stackTagCompound.getString("OriginalOwner");
/* 73:67 */     if (ownerName == "") {
/* 74:68 */       return null;
/* 75:   */     }
/* 76:69 */     return ownerName;
/* 77:   */   }
/* 78:   */   
/* 79:   */   public void setOwner(ItemStack is, String name)
/* 80:   */   {
/* 81:74 */     if (is.stackTagCompound == null) {
/* 82:75 */       is.stackTagCompound = new NBTTagCompound();
/* 83:   */     }
/* 84:76 */     is.stackTagCompound.setString("OriginalOwner", name);
/* 85:   */   }
/* 86:   */   
/* 87:   */   public String getDescription(ItemStack is)
/* 88:   */   {
/* 89:81 */     return StatCollector.translateToLocal(new StringBuilder().append("enchantment.").append(getName()).append(".name").toString()) + ": " + getOwner(is);
/* 90:   */   }
/* 91:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.magic.AwakementProperty
 * JD-Core Version:    0.7.1
 */