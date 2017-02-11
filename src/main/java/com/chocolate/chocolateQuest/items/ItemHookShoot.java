/*   1:    */ package com.chocolate.chocolateQuest.items;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityHookShoot;
/*   4:    */ import cpw.mods.fml.relauncher.Side;
/*   5:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   6:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.entity.player.EntityPlayer;
/*   9:    */ import net.minecraft.item.EnumRarity;
/*  10:    */ import net.minecraft.item.Item;
/*  11:    */ import net.minecraft.item.ItemStack;
/*  12:    */ import net.minecraft.util.IIcon;
/*  13:    */ import net.minecraft.world.World;
/*  14:    */ 
/*  15:    */ public class ItemHookShoot
/*  16:    */   extends Item
/*  17:    */   implements IHookLauncher
/*  18:    */ {
/*  19:    */   public static IIcon hook;
/*  20:    */   int lvl;
/*  21:    */   String iconTexture;
/*  22:    */   
/*  23:    */   public ItemHookShoot(int lvl, String iconTexture)
/*  24:    */   {
/*  25: 25 */     this.lvl = lvl;
/*  26: 26 */     this.iconTexture = iconTexture;
/*  27: 27 */     setMaxStackSize(1);
/*  28:    */   }
/*  29:    */   
/*  30:    */   @SideOnly(Side.CLIENT)
/*  31:    */   public void registerIcons(IIconRegister iconRegister)
/*  32:    */   {
/*  33: 34 */     hook = iconRegister.registerIcon("chocolatequest:hook");
/*  34: 35 */     this.itemIcon = iconRegister.registerIcon(this.iconTexture);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public IIcon getIconFromDamageForRenderPass(int par1, int par2)
/*  38:    */   {
/*  39: 41 */     if ((par2 == 0) && (par1 == 0)) {
/*  40: 43 */       return hook;
/*  41:    */     }
/*  42: 46 */     return this.itemIcon;
/*  43:    */   }
/*  44:    */   
/*  45:    */   @SideOnly(Side.CLIENT)
/*  46:    */   public boolean requiresMultipleRenderPasses()
/*  47:    */   {
/*  48: 64 */     return true;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public int getHookType()
/*  52:    */   {
/*  53: 68 */     return this.lvl;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean flag)
/*  57:    */   {
/*  58: 74 */     if (itemstack.getItemDamage() != 0) {
/*  59: 76 */       if (world.getEntityByID(itemstack.getItemDamage()) != null)
/*  60:    */       {
/*  61: 78 */         if (world.getEntityByID(itemstack.getItemDamage()).isDead) {
/*  62: 80 */           itemstack.setItemDamage(0);
/*  63:    */         }
/*  64:    */       }
/*  65:    */       else {
/*  66: 85 */         itemstack.setItemDamage(0);
/*  67:    */       }
/*  68:    */     }
/*  69: 89 */     super.onUpdate(itemstack, world, entity, par4, flag);
/*  70:    */   }
/*  71:    */   
/*  72:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  73:    */   {
/*  74: 95 */     if (itemstack.getItemDamage() == 0)
/*  75:    */     {
/*  76: 97 */       if (!world.isRemote)
/*  77:    */       {
/*  78: 99 */         EntityHookShoot ball = new EntityHookShoot(world, entityPlayer, this.lvl, itemstack);
/*  79:100 */         world.spawnEntityInWorld(ball);
/*  80:101 */         itemstack.setItemDamage(ball.getEntityId());
/*  81:    */       }
/*  82:    */     }
/*  83:    */     else
/*  84:    */     {
/*  85:106 */       Entity e = world.getEntityByID(itemstack.getItemDamage());
/*  86:107 */       if ((e instanceof EntityHookShoot)) {
/*  87:109 */         e.setDead();
/*  88:    */       }
/*  89:111 */       itemstack.setItemDamage(0);
/*  90:    */     }
/*  91:114 */     return super.onItemRightClick(itemstack, world, entityPlayer);
/*  92:    */   }
/*  93:    */   
/*  94:    */   public boolean shouldRotateAroundWhenRendering()
/*  95:    */   {
/*  96:120 */     return true;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public EnumRarity getRarity(ItemStack itemstack)
/* 100:    */   {
/* 101:125 */     return EnumRarity.rare;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public int getHookID(ItemStack is)
/* 105:    */   {
/* 106:130 */     return is.getItemDamage();
/* 107:    */   }
/* 108:    */   
/* 109:    */   public void setHookID(ItemStack is, int id)
/* 110:    */   {
/* 111:134 */     is.setItemDamage(id);
/* 112:    */   }
/* 113:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemHookShoot
 * JD-Core Version:    0.7.1
 */