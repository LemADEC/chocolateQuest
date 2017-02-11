/*   1:    */ package com.chocolate.chocolateQuest.items.swords;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityHookShoot;
/*   4:    */ import com.chocolate.chocolateQuest.items.IHookLauncher;
/*   5:    */ import com.chocolate.chocolateQuest.items.ItemHookShoot;
/*   6:    */ import cpw.mods.fml.relauncher.Side;
/*   7:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   8:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*   9:    */ import net.minecraft.entity.Entity;
/*  10:    */ import net.minecraft.entity.player.EntityPlayer;
/*  11:    */ import net.minecraft.item.Item.ToolMaterial;
/*  12:    */ import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;

/*  13:    */ import net.minecraft.nbt.NBTTagCompound;
/*  14:    */ import net.minecraft.util.IIcon;
/*  15:    */ import net.minecraft.world.World;
/*  16:    */ 
/*  17:    */ public class ItemHookSword
/*  18:    */   extends ItemBDSword
/*  19:    */   implements IHookLauncher
/*  20:    */ {
/*  21:    */   public ItemHookSword(Item.ToolMaterial material)
/*  22:    */   {
/*  23: 21 */     super(material);
/*  24:    */   }
/*  25:    */   
/*  26:    */   @SideOnly(Side.CLIENT)
/*  27:    */   public void registerIcons(IIconRegister iconRegister)
/*  28:    */   {
/*  29: 28 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:hookSword");
/*  30:    */   }
/*  31:    */   
/*  32:    */   public IIcon getIconFromDamageForRenderPass(int par1, int par2)
/*  33:    */   {
/*  34: 34 */     if (par2 == 1) {
/*  35: 36 */       return ItemHookShoot.hook;
/*  36:    */     }
/*  37: 39 */     return this.itemIcon;
/*  38:    */   }
/*  39:    */   
/*  40:    */   @SideOnly(Side.CLIENT)
/*  41:    */   public boolean requiresMultipleRenderPasses()
/*  42:    */   {
/*  43: 45 */     return true;
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean flag)
/*  47:    */   {
/*  48: 51 */     int id = getHookID(itemstack);
/*  49: 52 */     if (id != 0)
/*  50:    */     {
/*  51: 54 */       Entity e = world.getEntityByID(id);
/*  52: 55 */       if (e != null)
/*  53:    */       {
/*  54: 57 */         if (e.isDead) {
/*  55: 59 */           setHookID(itemstack, 0);
/*  56:    */         }
/*  57:    */       }
/*  58:    */       else {
/*  59: 64 */         setHookID(itemstack, 0);
/*  60:    */       }
/*  61:    */     }
/*  62: 68 */     super.onUpdate(itemstack, world, entity, par4, flag);
/*  63:    */   }
/*  64:    */   
/*  65:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  66:    */   {
/*  67: 74 */     if (getHookID(itemstack) == 0)
/*  68:    */     {
/*  69: 76 */       if (!world.isRemote)
/*  70:    */       {
/*  71: 78 */         EntityHookShoot ball = new EntityHookShoot(world, entityPlayer, 4, itemstack);
/*  72: 79 */         world.spawnEntityInWorld(ball);
/*  73: 80 */         setHookID(itemstack, ball.getEntityId());
/*  74:    */       }
/*  75:    */     }
/*  76:    */     else
/*  77:    */     {
/*  78: 85 */       Entity e = world.getEntityByID(getHookID(itemstack));
/*  79: 86 */       if ((e instanceof EntityHookShoot)) {
/*  80: 88 */         e.setDead();
/*  81:    */       }
/*  82: 90 */       setHookID(itemstack, 0);
/*  83:    */     }
/*  84: 93 */     return super.onItemRightClick(itemstack, world, entityPlayer);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void setHookID(ItemStack is, int id)
/*  88:    */   {
/*  89: 97 */     if (is.stackTagCompound == null) {
/*  90: 98 */       is.stackTagCompound = new NBTTagCompound();
/*  91:    */     }
/*  92: 99 */     is.stackTagCompound.setInteger("hook", id);
/*  93:    */   }
/*  94:    */   
/*  95:    */   public int getHookID(ItemStack is)
/*  96:    */   {
/*  97:103 */     if (is.stackTagCompound == null) {
/*  98:104 */       return 0;
/*  99:    */     }
/* 100:105 */     return is.stackTagCompound.getInteger("hook");
/* 101:    */   }
/* 102:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemHookSword

 * JD-Core Version:    0.7.1

 */
