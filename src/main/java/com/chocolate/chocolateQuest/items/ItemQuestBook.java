/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.entity.item.EntityItem;
/*  8:   */ import net.minecraft.entity.player.EntityPlayer;
/*  9:   */ import net.minecraft.item.ItemEditableBook;
/* 10:   */ import net.minecraft.item.ItemStack;
/* 11:   */ import net.minecraft.nbt.NBTTagCompound;
/* 12:   */ import net.minecraft.nbt.NBTTagList;
/* 13:   */ import net.minecraft.nbt.NBTTagString;
/* 14:   */ import net.minecraft.world.World;
/* 15:   */ 
/* 16:   */ public class ItemQuestBook
/* 17:   */   extends ItemEditableBook
/* 18:   */ {
/* 19:   */   public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
/* 20:   */   {
/* 21:25 */     super.onUpdate(itemStack, world, entity, par4, par5);
/* 22:   */   }
/* 23:   */   
/* 24:   */   @SideOnly(Side.CLIENT)
/* 25:   */   public void registerIcons(IIconRegister iconRegister)
/* 26:   */   {
/* 27:32 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:book");
/* 28:   */   }
/* 29:   */   
/* 30:   */   public boolean onEntityItemUpdate(EntityItem entityItem)
/* 31:   */   {
/* 32:36 */     return super.onEntityItemUpdate(entityItem);
/* 33:   */   }
/* 34:   */   
/* 35:   */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
/* 36:   */   {
/* 37:41 */     if (itemstack.stackTagCompound == null) {
/* 38:42 */       itemstack.stackTagCompound = new NBTTagCompound();
/* 39:   */     }
/* 40:43 */     itemstack.setTagInfo("title", new NBTTagString("Arrr"));
/* 41:44 */     itemstack.setTagInfo("author", new NBTTagString("Chocolatin, the pirate captain"));
/* 42:   */     
/* 43:46 */     NBTTagList bookPages = new NBTTagList();
/* 44:   */     
/* 45:   */ 
/* 46:   */ 
/* 47:   */ 
/* 48:   */ 
/* 49:   */ 
/* 50:   */ 
/* 51:   */ 
/* 52:   */ 
/* 53:   */ 
/* 54:   */ 
/* 55:   */ 
/* 56:   */ 
/* 57:   */ 
/* 58:   */ 
/* 59:   */ 
/* 60:   */ 
/* 61:   */ 
/* 62:65 */     return itemstack;
/* 63:   */   }
/* 64:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemQuestBook
 * JD-Core Version:    0.7.1
 */