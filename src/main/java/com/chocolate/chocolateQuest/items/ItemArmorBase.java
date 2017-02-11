/*   1:    */ package com.chocolate.chocolateQuest.items;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.magic.Awakements;
/*   4:    */ import cpw.mods.fml.relauncher.Side;
/*   5:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   6:    */ import java.util.List;
/*   7:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.entity.EntityLivingBase;
/*  10:    */ import net.minecraft.entity.player.EntityPlayer;
/*  11:    */ import net.minecraft.item.ItemArmor;
/*  12:    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*  13:    */ import net.minecraft.item.ItemStack;
/*  14:    */ import net.minecraft.world.World;
/*  15:    */ import net.minecraftforge.common.util.EnumHelper;
/*  16:    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*  17:    */ 
/*  18:    */ public class ItemArmorBase
/*  19:    */   extends ItemArmor
/*  20:    */ {
/*  21:    */   String name;
/*  22: 23 */   boolean isEpic = false;
/*  23: 24 */   protected static final ItemArmor.ArmorMaterial DRAGON = EnumHelper.addArmorMaterial("DRAGON", 55, new int[] { 4, 9, 7, 4 }, 15);
/*  24: 25 */   protected static final ItemArmor.ArmorMaterial TURTLE = EnumHelper.addArmorMaterial("TURTLE", 45, new int[] { 3, 8, 6, 3 }, 25);
/*  25:    */   
/*  26:    */   public ItemArmorBase(ItemArmor.ArmorMaterial material, int renderIndex)
/*  27:    */   {
/*  28: 31 */     super(material, 0, renderIndex);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public ItemArmorBase(ItemArmor.ArmorMaterial material, int renderIndex, String name)
/*  32:    */   {
/*  33: 36 */     this(material, renderIndex);
/*  34: 37 */     this.name = name;
/*  35:    */   }
/*  36:    */   
/*  37:    */   @SideOnly(Side.CLIENT)
/*  38:    */   public void registerIcons(IIconRegister iconRegister) {}
/*  39:    */   
/*  40:    */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/*  41:    */   {
/*  42: 48 */     if (slot == 2) {
/*  43: 50 */       return "chocolatequest:textures/entity/" + this.name + "_2.png";
/*  44:    */     }
/*  45: 53 */     return "chocolatequest:textures/entity/" + this.name + ".png";
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void addInformation(ItemStack is, EntityPlayer player, List list, boolean par4)
/*  49:    */   {
/*  50: 57 */     super.addInformation(is, player, list, par4);
/*  51: 58 */     if (isEpic()) {
/*  52: 59 */       for (Awakements a : Awakements.awekements) {
/*  53: 60 */         if (Awakements.hasEnchant(is, a)) {
/*  54: 61 */           list.add(a.getDescription(is));
/*  55:    */         }
/*  56:    */       }
/*  57:    */     }
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
/*  61:    */   {
/*  62: 68 */     onUpdateEquiped(itemStack, world, player);
/*  63: 70 */     if (this.isEpic)
/*  64:    */     {
/*  65: 71 */       if (Awakements.hasEnchant(itemStack, Awakements.property)) {
/*  66: 72 */         Awakements.property.onUpdate(player, itemStack);
/*  67:    */       }
/*  68: 74 */       if (Awakements.hasEnchant(itemStack, Awakements.autoRepair)) {
/*  69: 75 */         Awakements.autoRepair.onUpdate(player, itemStack);
/*  70:    */       }
/*  71:    */     }
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void onUpdateEquiped(ItemStack itemStack, World world, EntityLivingBase entity) {}
/*  75:    */   
/*  76:    */   public int getColor(ItemStack par1ItemStack)
/*  77:    */   {
/*  78: 88 */     return 16777215;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public boolean requiresMultipleRenderPasses()
/*  82:    */   {
/*  83: 94 */     return false;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public ItemArmorBase setEpic()
/*  87:    */   {
/*  88: 99 */     this.isEpic = true;
/*  89:100 */     return this;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public boolean isEpic()
/*  93:    */   {
/*  94:104 */     return this.isEpic;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
/*  98:    */   {
/*  99:109 */     if (this.isEpic)
/* 100:    */     {
/* 101:110 */       if (Awakements.hasEnchant(itemStack, Awakements.property)) {
/* 102:111 */         Awakements.property.onUpdate(entity, itemStack);
/* 103:    */       }
/* 104:113 */       if (Awakements.hasEnchant(itemStack, Awakements.autoRepair)) {
/* 105:114 */         Awakements.autoRepair.onUpdate(entity, itemStack);
/* 106:    */       }
/* 107:    */     }
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void onHit(LivingHurtEvent event, ItemStack is, EntityLivingBase entity) {}
/* 111:    */   
/* 112:    */   public String getItemStackDisplayName(ItemStack itemstack)
/* 113:    */   {
/* 114:128 */     return super.getItemStackDisplayName(itemstack);
/* 115:    */   }
/* 116:    */   
/* 117:    */   public boolean isFullSet(EntityLivingBase e, ItemStack itemstack)
/* 118:    */   {
/* 119:132 */     for (int i = 1; i < 5; i++)
/* 120:    */     {
/* 121:133 */       ItemStack is = e.getEquipmentInSlot(i);
/* 122:134 */       if (is == null) {
/* 123:135 */         return false;
/* 124:    */       }
/* 125:136 */       if (is.getItem().getClass() != itemstack.getItem().getClass()) {
/* 126:137 */         return false;
/* 127:    */       }
/* 128:    */     }
/* 129:140 */     return true;
/* 130:    */   }
/* 131:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorBase
 * JD-Core Version:    0.7.1
 */