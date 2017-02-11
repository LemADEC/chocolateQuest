/*   1:    */ package com.chocolate.chocolateQuest.items.mobControl;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityCursor;
/*   4:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   5:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   6:    */ import com.chocolate.chocolateQuest.utils.HelperPlayer;
/*   7:    */ import com.chocolate.chocolateQuest.utils.Vec4I;
/*   8:    */ import cpw.mods.fml.relauncher.Side;
/*   9:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  10:    */ import java.io.PrintStream;
/*  11:    */ import java.util.List;
/*  12:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  13:    */ import net.minecraft.creativetab.CreativeTabs;
/*  14:    */ import net.minecraft.entity.Entity;
/*  15:    */ import net.minecraft.entity.EntityLiving;
/*  16:    */ import net.minecraft.entity.EntityLivingBase;
/*  17:    */ import net.minecraft.entity.player.EntityPlayer;
/*  18:    */ import net.minecraft.item.Item;
/*  19:    */ import net.minecraft.item.ItemStack;
/*  20:    */ import net.minecraft.nbt.NBTTagCompound;
/*  21:    */ import net.minecraft.util.ChatComponentText;
/*  22:    */ import net.minecraft.util.IIcon;
/*  23:    */ import net.minecraft.util.MovingObjectPosition;
/*  24:    */ import net.minecraft.util.StatCollector;
/*  25:    */ import net.minecraft.world.World;
/*  26:    */ 
/*  27:    */ public class ItemController
/*  28:    */   extends Item
/*  29:    */ {
/*  30:    */   public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer entityPlayer, EntityLivingBase entity)
/*  31:    */   {
/*  32: 33 */     EntityLivingBase assignedEntity = getEntity(itemstack, entityPlayer.worldObj);
/*  33: 34 */     if (assignedEntity != null)
/*  34:    */     {
/*  35: 35 */       MovingObjectPosition mop = new MovingObjectPosition(entity);
/*  36: 36 */       doStuff(itemstack, entityPlayer.worldObj, entityPlayer, mop, assignedEntity);
/*  37: 37 */       return true;
/*  38:    */     }
/*  39: 39 */     return false;
/*  40:    */   }
/*  41:    */   
/*  42: 42 */   public final int GOLEM_CONTROLLER = 1;
/*  43: 42 */   public final int CONTROLLER = 0;
/*  44:    */   
/*  45:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  46:    */   {
/*  47: 50 */     Entity target = HelperPlayer.getTarget(entityPlayer, world, 6.0D);
/*  48: 51 */     MovingObjectPosition mop = HelperPlayer.getMovingObjectPositionFromPlayer(entityPlayer, world, 50.0D);
/*  49: 52 */     EntityLivingBase e = getEntity(itemstack, world);
/*  50: 53 */     if ((e instanceof EntityHumanBase)) {
/*  51: 55 */       doStuff(itemstack, world, entityPlayer, mop, e);
/*  52:    */     } else {
/*  53: 58 */       itemstack.stackTagCompound = null;
/*  54:    */     }
/*  55: 59 */     return super.onItemRightClick(itemstack, world, entityPlayer);
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void doStuff(ItemStack itemstack, World world, EntityPlayer entityPlayer, MovingObjectPosition mop, Entity assignedEntity)
/*  59:    */   {
/*  60: 64 */     EntityHumanBase human = (EntityHumanBase)assignedEntity;
/*  61: 65 */     if (mop != null) {
/*  62: 67 */       if (mop.entityHit == null)
/*  63:    */       {
/*  64: 69 */         if (world.isRemote) {
/*  65: 70 */           world.spawnEntityInWorld(new EntityCursor(world, mop.blockX + 0.5D, mop.blockY + 1, mop.blockZ + 0.5D, entityPlayer.rotationYaw));
/*  66:    */         }
/*  67: 72 */         human.standingPosition = new Vec4I(mop.blockX, mop.blockY, mop.blockZ, (int)entityPlayer.rotationYaw);
/*  68: 73 */         human.currentPos = human.standingPosition;
/*  69:    */       }
/*  70: 76 */       else if (((mop.entityHit instanceof EntityLiving)) && (mop.entityHit != human))
/*  71:    */       {
/*  72: 78 */         if (world.isRemote) {
/*  73: 79 */           world.spawnEntityInWorld(new EntityCursor(world, mop.entityHit, itemstack));
/*  74:    */         }
/*  75: 80 */         EntityLivingBase entityHit = (EntityLivingBase)mop.entityHit;
/*  76: 81 */         boolean isMount = human.isSuitableMount(entityHit);
/*  77: 82 */         if ((isMount) && (entityHit.riddenByEntity != null) && 
/*  78: 83 */           ((entityHit instanceof EntityLivingBase)))
/*  79:    */         {
/*  80: 84 */           entityHit = (EntityLivingBase)entityHit.riddenByEntity;
/*  81: 85 */           isMount = false;
/*  82:    */         }
/*  83: 88 */         if ((isMount) || (!human.isSuitableTargetAlly(entityHit))) {
/*  84: 90 */           if (human.ridingEntity == null)
/*  85:    */           {
/*  86: 91 */             if (human.isSuitableMount(entityHit)) {
/*  87: 92 */               entityPlayer.addChatMessage(new ChatComponentText(BDHelper.StringColor("2") + human.getCommandSenderName() + BDHelper.StringColor("f") + " mounting " + BDHelper.StringColor("2") + entityHit.getCommandSenderName() + BDHelper.StringColor("f")));
/*  88:    */             }
/*  89: 95 */             human.setAttackTarget(entityHit);
/*  90: 96 */             System.out.println(human.getAttackTarget());
/*  91:    */           }
/*  92:    */         }
/*  93:    */       }
/*  94:    */     }
/*  95:    */   }
/*  96:    */   
/*  97:    */   public EntityLivingBase getEntity(ItemStack itemstack, World world)
/*  98:    */   {
/*  99:120 */     if (itemstack.stackTagCompound != null)
/* 100:    */     {
/* 101:122 */       int id = itemstack.stackTagCompound.getInteger("EntityID");
/* 102:123 */       if (id != 0)
/* 103:    */       {
/* 104:125 */         Entity e = world.getEntityByID(id);
/* 105:126 */         if ((e instanceof EntityLivingBase)) {
/* 106:128 */           return (EntityLivingBase)e;
/* 107:    */         }
/* 108:131 */         itemstack.stackTagCompound = null;
/* 109:    */       }
/* 110:    */     }
/* 111:134 */     return null;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public boolean hasEffect(ItemStack par1ItemStack)
/* 115:    */   {
/* 116:140 */     return par1ItemStack.stackTagCompound != null;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
/* 120:    */   {
/* 121:145 */     if (((entity instanceof EntityHumanBase)) && (isSuitableTarget(entity, stack)))
/* 122:    */     {
/* 123:147 */       stack.stackTagCompound = new NBTTagCompound();
/* 124:148 */       String name = entity.getCommandSenderName();
/* 125:149 */       stack.stackTagCompound.setInteger("EntityID", entity.getEntityId());
/* 126:150 */       stack.stackTagCompound.setString("name", name);
/* 127:151 */       if (player.worldObj.isRemote) {
/* 128:152 */         player.addChatMessage(new ChatComponentText("Assigned " + BDHelper.StringColor("2") + name + BDHelper.StringColor("f") + " to this item"));
/* 129:    */       }
/* 130:153 */       return true;
/* 131:    */     }
/* 132:155 */     return true;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public boolean isSuitableTarget(Entity e, ItemStack is)
/* 136:    */   {
/* 137:164 */     return true;
/* 138:    */   }
/* 139:    */   
/* 140:181 */   public String[] names = { "controller", "golemController" };
/* 141:    */   public IIcon[] icon;
/* 142:    */   
/* 143:    */   @SideOnly(Side.CLIENT)
/* 144:    */   public void registerIcons(IIconRegister par1IconRegister)
/* 145:    */   {
/* 146:187 */     this.icon = new IIcon[this.names.length];
/* 147:189 */     for (int i = 0; i < this.icon.length; i++) {
/* 148:191 */       this.icon[i] = par1IconRegister.registerIcon("chocolateQuest:i" + i);
/* 149:    */     }
/* 150:    */   }
/* 151:    */   
/* 152:    */   public String getItemStackDisplayName(ItemStack itemstack)
/* 153:    */   {
/* 154:198 */     int i = itemstack.getItemDamage();
/* 155:199 */     if (i < this.names.length) {
/* 156:200 */       return ("" + StatCollector.translateToLocal(new StringBuilder().append("item.").append(this.names[i]).append(".name").toString())).trim();
/* 157:    */     }
/* 158:203 */     return "????";
/* 159:    */   }
/* 160:    */   
/* 161:    */   public IIcon getIconFromDamage(int par1)
/* 162:    */   {
/* 163:208 */     if (par1 < this.icon.length) {
/* 164:209 */       return this.icon[par1];
/* 165:    */     }
/* 166:211 */     return this.icon[0];
/* 167:    */   }
/* 168:    */   
/* 169:    */   @SideOnly(Side.CLIENT)
/* 170:    */   public void getSubItems(Item itemId, CreativeTabs par2CreativeTabs, List par3List)
/* 171:    */   {
/* 172:216 */     for (int i = 0; i < this.names.length; i++) {
/* 173:218 */       par3List.add(new ItemStack(itemId, 1, i));
/* 174:    */     }
/* 175:    */   }
/* 176:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.mobControl.ItemController
 * JD-Core Version:    0.7.1
 */