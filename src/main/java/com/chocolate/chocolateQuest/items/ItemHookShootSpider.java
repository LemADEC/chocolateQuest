/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.entity.item.EntityItem;
/*  8:   */ import net.minecraft.entity.player.EntityPlayer;
/*  9:   */ import net.minecraft.item.EnumRarity;
/* 10:   */ import net.minecraft.item.ItemStack;
/* 11:   */ import net.minecraft.nbt.NBTTagCompound;
/* 12:   */ import net.minecraft.util.IIcon;
/* 13:   */ import net.minecraft.world.World;
/* 14:   */ 
/* 15:   */ public class ItemHookShootSpider
/* 16:   */   extends ItemHookShoot
/* 17:   */ {
/* 18:   */   public IIcon hook;
/* 19:   */   
/* 20:   */   public ItemHookShootSpider(int lvl)
/* 21:   */   {
/* 22:20 */     super(lvl, "");
/* 23:   */   }
/* 24:   */   
/* 25:   */   @SideOnly(Side.CLIENT)
/* 26:   */   public void registerIcons(IIconRegister iconRegister)
/* 27:   */   {
/* 28:27 */     this.hook = iconRegister.registerIcon("chocolatequest:hookWeb");
/* 29:28 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:hookSpider");
/* 30:   */   }
/* 31:   */   
/* 32:   */   public IIcon getIconFromDamageForRenderPass(int par1, int par2)
/* 33:   */   {
/* 34:33 */     if ((par2 == 0) && (par1 == 0)) {
/* 35:35 */       return this.hook;
/* 36:   */     }
/* 37:37 */     return this.itemIcon;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public EnumRarity getRarity(ItemStack itemstack)
/* 41:   */   {
/* 42:43 */     return EnumRarity.epic;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
/* 46:   */   {
/* 47:48 */     if (entity.ticksExisted % 400 == 0) {
/* 48:50 */       if ((entity instanceof EntityPlayer))
/* 49:   */       {
/* 50:52 */         EntityPlayer ep = (EntityPlayer)entity;
/* 51:53 */         if (itemStack.stackTagCompound == null) {
/* 52:54 */           itemStack.stackTagCompound = new NBTTagCompound();
/* 53:   */         }
/* 54:56 */         itemStack.stackTagCompound.setString("OriginalOwner", ep.getCommandSenderName());
/* 55:   */       }
/* 56:   */     }
/* 57:   */   }
/* 58:   */   
/* 59:   */   public boolean onEntityItemUpdate(EntityItem entityItem)
/* 60:   */   {
/* 61:63 */     if ((entityItem.getEntityItem().stackTagCompound != null) && (entityItem.age > entityItem.lifespan - 100))
/* 62:   */     {
/* 63:65 */       entityItem.age = 0;
/* 64:66 */       EntityPlayer player = entityItem.worldObj.getPlayerEntityByName(entityItem.getEntityItem().stackTagCompound.getString("OriginalOwner"));
/* 65:67 */       if (player != null) {
/* 66:68 */         entityItem.setPosition(player.posX, player.posY, player.posZ);
/* 67:   */       }
/* 68:   */     }
/* 69:70 */     if (entityItem.isBurning())
/* 70:   */     {
/* 71:72 */       entityItem.extinguish();
/* 72:73 */       entityItem.motionY = 0.5D;
/* 73:   */     }
/* 74:75 */     return super.onEntityItemUpdate(entityItem);
/* 75:   */   }
/* 76:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemHookShootSpider
 * JD-Core Version:    0.7.1
 */