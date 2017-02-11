/*   1:    */ package com.chocolate.chocolateQuest.items.mobControl;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityCursor;
/*   4:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   5:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   6:    */ import cpw.mods.fml.relauncher.Side;
/*   7:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   8:    */ import java.util.List;
/*   9:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  10:    */ import net.minecraft.creativetab.CreativeTabs;
/*  11:    */ import net.minecraft.entity.Entity;
/*  12:    */ import net.minecraft.entity.EntityLiving;
/*  13:    */ import net.minecraft.entity.EntityLivingBase;
/*  14:    */ import net.minecraft.entity.player.EntityPlayer;
/*  15:    */ import net.minecraft.item.Item;
/*  16:    */ import net.minecraft.item.ItemStack;
/*  17:    */ import net.minecraft.nbt.NBTTagCompound;
/*  18:    */ import net.minecraft.util.ChatComponentText;
/*  19:    */ import net.minecraft.util.IIcon;
/*  20:    */ import net.minecraft.util.MovingObjectPosition;
/*  21:    */ import net.minecraft.util.StatCollector;
/*  22:    */ import net.minecraft.world.World;
/*  23:    */ 
/*  24:    */ public class ItemTeamEditor
/*  25:    */   extends ItemController
/*  26:    */ {
/*  27:    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
/*  28:    */   {
/*  29: 32 */     if (((entity instanceof EntityHumanBase)) && (isSuitableTarget(entity, stack)))
/*  30:    */     {
/*  31: 34 */       stack.stackTagCompound = new NBTTagCompound();
/*  32: 35 */       String name = entity.getCommandSenderName();
/*  33: 36 */       stack.stackTagCompound.setInteger("EntityID", entity.getEntityId());
/*  34: 37 */       stack.stackTagCompound.setString("name", name);
/*  35: 38 */       if (player.worldObj.isRemote) {
/*  36: 39 */         player.addChatMessage(new ChatComponentText("Assigned " + BDHelper.StringColor("2") + name + BDHelper.StringColor("f") + " to this item"));
/*  37:    */       }
/*  38: 41 */       return true;
/*  39:    */     }
/*  40: 43 */     return true;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void doStuff(ItemStack itemstack, World world, EntityPlayer entityPlayer, MovingObjectPosition mop, Entity assignedEntity)
/*  44:    */   {
/*  45: 49 */     EntityHumanBase human = (EntityHumanBase)assignedEntity;
/*  46: 50 */     if (mop != null) {
/*  47: 52 */       if (((mop.entityHit instanceof EntityLiving)) && (mop.entityHit != human)) {
/*  48: 54 */         if (world.isRemote)
/*  49:    */         {
/*  50: 55 */           world.spawnEntityInWorld(new EntityCursor(world, mop.entityHit, itemstack));
/*  51:    */         }
/*  52:    */         else
/*  53:    */         {
/*  54: 57 */           EntityLivingBase entityHit = (EntityLivingBase)mop.entityHit;
/*  55: 58 */           boolean isMount = human.isSuitableMount(entityHit);
/*  56: 59 */           if ((isMount) && (entityHit.riddenByEntity != null) && 
/*  57: 60 */             ((entityHit instanceof EntityLivingBase)))
/*  58:    */           {
/*  59: 61 */             entityHit = (EntityLivingBase)entityHit.riddenByEntity;
/*  60: 62 */             isMount = false;
/*  61:    */           }
/*  62: 65 */           if ((!isMount) && (human.isSuitableTargetAlly(entityHit)))
/*  63:    */           {
/*  64: 67 */             boolean added = true;
/*  65: 68 */             if ((entityHit instanceof EntityHumanBase))
/*  66:    */             {
/*  67: 69 */               EntityHumanBase humanTarget = (EntityHumanBase)entityHit;
/*  68: 70 */               if ((human.isCaptain()) && (humanTarget.party != null)) {
/*  69: 71 */                 human.setOwner(humanTarget);
/*  70: 73 */               } else if (!((EntityHumanBase)entityHit).tryPutIntoPArty(human)) {
/*  71: 74 */                 added = false;
/*  72:    */               }
/*  73:    */             }
/*  74:    */             else
/*  75:    */             {
/*  76: 76 */               human.setOwner(entityHit);
/*  77:    */             }
/*  78: 77 */             if (added) {
/*  79: 78 */               entityPlayer.addChatMessage(new ChatComponentText(BDHelper.StringColor("2") + human.getCommandSenderName() + BDHelper.StringColor("f") + " now following " + BDHelper.StringColor("2") + entityHit.getCommandSenderName() + BDHelper.StringColor("f") + " orders"));
/*  80:    */             }
/*  81:    */           }
/*  82:    */         }
/*  83:    */       }
/*  84:    */     }
/*  85:    */   }
/*  86:    */   
/*  87:    */   @SideOnly(Side.CLIENT)
/*  88:    */   public void registerIcons(IIconRegister par1IconRegister)
/*  89:    */   {
/*  90: 90 */     this.icon = new IIcon[] { par1IconRegister.registerIcon("chocolateQuest:partyEditor") };
/*  91:    */   }
/*  92:    */   
/*  93:    */   public String getItemStackDisplayName(ItemStack itemstack)
/*  94:    */   {
/*  95: 97 */     return ("" + StatCollector.translateToLocal("item.teamEditor.name")).trim();
/*  96:    */   }
/*  97:    */   
/*  98:    */   @SideOnly(Side.CLIENT)
/*  99:    */   public void getSubItems(Item itemId, CreativeTabs par2CreativeTabs, List par3List)
/* 100:    */   {
/* 101:102 */     par3List.add(new ItemStack(itemId, 1, 0));
/* 102:    */   }
/* 103:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.mobControl.ItemTeamEditor
 * JD-Core Version:    0.7.1
 */