/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.client.model.ModelArmor;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  6:   */ import net.minecraft.client.model.ModelBiped;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.entity.EntityLivingBase;
/*  9:   */ import net.minecraft.init.Items;
/* 10:   */ import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

/* 11:   */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/* 12:   */ import net.minecraft.item.ItemStack;
/* 13:   */ import net.minecraft.nbt.NBTTagCompound;
/* 14:   */ 
/* 15:   */ public class ItemArmorColored
/* 16:   */   extends ItemArmorBase
/* 17:   */ {
/* 18:18 */   Item repairItem = Items.diamond;
/* 19:   */   int defaultColor;
/* 20:   */   
/* 21:   */   public ItemArmorColored(int type, String name, Item repairItem, int defaultColor)
/* 22:   */   {
/* 23:23 */     this(type, name, repairItem, ItemArmorBase.DRAGON, defaultColor);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public ItemArmorColored(ItemArmor.ArmorMaterial material, int renderIndex, String name, int defaultColor)
/* 27:   */   {
/* 28:28 */     super(material, renderIndex, name);
/* 29:29 */     this.name = name;
/* 30:30 */     this.defaultColor = defaultColor;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public ItemArmorColored(int type, String name, Item repairItem, ItemArmor.ArmorMaterial mat, int defaultColor)
/* 34:   */   {
/* 35:34 */     this(mat, type, name, defaultColor);
/* 36:35 */     this.repairItem = repairItem;
/* 37:   */   }
/* 38:   */   
/* 39:   */   @SideOnly(Side.CLIENT)
/* 40:   */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/* 41:   */   {
/* 42:42 */     return com.chocolate.chocolateQuest.client.ClientProxy.coloredArmor[armorSlot].setItem(itemStack);
/* 43:   */   }
/* 44:   */   
/* 45:   */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/* 46:   */   {
/* 47:47 */     if (slot == 2) {
/* 48:49 */       return "chocolatequest:textures/entity/armor_" + this.name + "_2_overlay.png";
/* 49:   */     }
/* 50:51 */     return "chocolatequest:textures/entity/armor_" + this.name + "_1_overlay.png";
/* 51:   */   }
/* 52:   */   
/* 53:   */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemMaterial)
/* 54:   */   {
/* 55:57 */     return (itemMaterial.getItem() == this.repairItem) || (super.getIsRepairable(itemToRepair, itemMaterial));
/* 56:   */   }
/* 57:   */   
/* 58:   */   public int getColorFromItemStack(ItemStack is, int i)
/* 59:   */   {
/* 60:62 */     if ((is.stackTagCompound != null) && 
/* 61:63 */       (is.stackTagCompound.hasKey("display")))
/* 62:   */     {
/* 63:64 */       NBTTagCompound tag = (NBTTagCompound)is.stackTagCompound.getTag("display");
/* 64:65 */       if (tag.hasKey("color")) {
/* 65:66 */         return tag.getInteger("color");
/* 66:   */       }
/* 67:   */     }
/* 68:69 */     return this.defaultColor;
/* 69:   */   }
/* 70:   */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorColored

 * JD-Core Version:    0.7.1

 */
