/*  1:   */ package com.chocolate.chocolateQuest.items.gun;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMecha;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  6:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  7:   */ import net.minecraft.entity.player.EntityPlayer;
/*  8:   */ import net.minecraft.entity.player.PlayerCapabilities;
/*  9:   */ import net.minecraft.item.Item;
/* 10:   */ import net.minecraft.item.ItemStack;
/* 11:   */ import net.minecraft.world.World;
/* 12:   */ 
/* 13:   */ public class ItemGolem
/* 14:   */   extends Item
/* 15:   */ {
/* 16:   */   String icon;
/* 17:   */   
/* 18:   */   public ItemGolem(String name)
/* 19:   */   {
/* 20:20 */     this.icon = name;
/* 21:21 */     setMaxStackSize(1);
/* 22:   */   }
/* 23:   */   
/* 24:   */   @SideOnly(Side.CLIENT)
/* 25:   */   public void registerIcons(IIconRegister iconRegister)
/* 26:   */   {
/* 27:27 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:" + this.icon);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int i, int j, int k, int l, float f, float f1, float f2)
/* 31:   */   {
/* 32:64 */     if (!entityPlayer.capabilities.isCreativeMode) {
/* 33:65 */       itemStack.stackSize -= 1;
/* 34:   */     }
/* 35:66 */     if (!world.isRemote)
/* 36:   */     {
/* 37:68 */       EntityGolemMecha golem = getGolem(world, entityPlayer);
/* 38:69 */       golem.setPosition(i, j + 1, k);
/* 39:70 */       golem.setOwner(entityPlayer);
/* 40:71 */       world.spawnEntityInWorld(golem);
/* 41:   */     }
/* 42:73 */     return false;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public EntityGolemMecha getGolem(World world, EntityPlayer entityPlayer)
/* 46:   */   {
/* 47:77 */     return new EntityGolemMecha(world, entityPlayer);
/* 48:   */   }
/* 49:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.gun.ItemGolem
 * JD-Core Version:    0.7.1
 */