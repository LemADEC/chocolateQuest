/*   1:    */ package com.chocolate.chocolateQuest.items;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.ITwoHandedItem;
/*   4:    */ import cpw.mods.fml.relauncher.Side;
/*   5:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   6:    */ import java.util.List;
/*   7:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*   8:    */ import net.minecraft.creativetab.CreativeTabs;
/*   9:    */ import net.minecraft.entity.Entity;
/*  10:    */ import net.minecraft.entity.player.EntityPlayer;
/*  11:    */ import net.minecraft.item.EnumAction;
/*  12:    */ import net.minecraft.item.Item;
/*  13:    */ import net.minecraft.item.ItemStack;
/*  14:    */ import net.minecraft.potion.Potion;
/*  15:    */ import net.minecraft.potion.PotionEffect;
/*  16:    */ import net.minecraft.util.IIcon;
/*  17:    */ import net.minecraft.world.World;
/*  18:    */ 
/*  19:    */ public class ItemBanner
/*  20:    */   extends Item
/*  21:    */   implements ITwoHandedItem
/*  22:    */ {
/*  23:    */   public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
/*  24:    */   {
/*  25: 32 */     if ((entity instanceof EntityPlayer))
/*  26:    */     {
/*  27: 34 */       EntityPlayer ep = (EntityPlayer)entity;
/*  28: 36 */       if ((ep.isBlocking()) && (ep.getCurrentEquippedItem() == itemStack)) {
/*  29: 38 */         ep.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1, 1));
/*  30:    */       }
/*  31:    */     }
/*  32:    */   }
/*  33:    */   
/*  34:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  35:    */   {
/*  36: 46 */     entityPlayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
/*  37: 47 */     return itemstack;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public int getMaxItemUseDuration(ItemStack par1ItemStack)
/*  41:    */   {
/*  42: 51 */     return 72000;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public EnumAction getItemUseAction(ItemStack par1ItemStack)
/*  46:    */   {
/*  47: 56 */     return EnumAction.block;
/*  48:    */   }
/*  49:    */   
/*  50: 59 */   public String[] names = { "End banner", "Pigmen banner", "Dwarf banner", "Zombie banner", "Skeleton banner", "Pirate banner", "Shadows banner", "Goblin banner", "Specter banner", "Colorful banner", "Squid banner", "Minotaur banner", "Colorful banner", "", "" };
/*  51:    */   
/*  52:    */   public boolean getHasSubtypes()
/*  53:    */   {
/*  54: 63 */     return true;
/*  55:    */   }
/*  56:    */   
/*  57:    */   @SideOnly(Side.CLIENT)
/*  58:    */   public void registerIcons(IIconRegister iconRegister)
/*  59:    */   {
/*  60: 70 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:bannerHolder");
/*  61:    */   }
/*  62:    */   
/*  63:    */   public String getItemStackDisplayName(ItemStack itemstack)
/*  64:    */   {
/*  65: 76 */     int i = itemstack.getItemDamage();
/*  66: 78 */     if (i < this.names.length) {
/*  67: 80 */       return this.names[i];
/*  68:    */     }
/*  69: 83 */     return "????";
/*  70:    */   }
/*  71:    */   
/*  72:    */   public boolean requiresMultipleRenderPasses()
/*  73:    */   {
/*  74: 99 */     return false;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public IIcon getIconFromDamageForRenderPass(int par1, int par2)
/*  78:    */   {
/*  79:105 */     return this.itemIcon;
/*  80:    */   }
/*  81:    */   
/*  82:    */   @SideOnly(Side.CLIENT)
/*  83:    */   public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List itemList)
/*  84:    */   {
/*  85:115 */     for (int i = 0; i <= this.names.length; i++) {
/*  86:117 */       itemList.add(new ItemStack(item, 1, i));
/*  87:    */     }
/*  88:    */   }
/*  89:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemBanner
 * JD-Core Version:    0.7.1
 */