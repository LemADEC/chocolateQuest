/*   1:    */ package com.chocolate.chocolateQuest.items.mobControl;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.block.BlockMobSpawnerTileEntity;
/*   5:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   6:    */ import com.chocolate.chocolateQuest.entity.ai.EntityParty;
/*   7:    */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMecha;
/*   8:    */ import cpw.mods.fml.relauncher.Side;
/*   9:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  10:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  11:    */ import net.minecraft.entity.Entity;
/*  12:    */ import net.minecraft.entity.player.EntityPlayer;
/*  13:    */ import net.minecraft.init.Blocks;
/*  14:    */ import net.minecraft.item.Item;
/*  15:    */ import net.minecraft.item.ItemStack;
/*  16:    */ import net.minecraft.nbt.NBTTagCompound;
/*  17:    */ import net.minecraft.tileentity.TileEntity;
/*  18:    */ import net.minecraft.util.MathHelper;
/*  19:    */ import net.minecraft.world.World;
/*  20:    */ 
/*  21:    */ public class ItemMobToSpawner
/*  22:    */   extends Item
/*  23:    */ {
/*  24:    */   public ItemMobToSpawner()
/*  25:    */   {
/*  26: 28 */     setMaxStackSize(1);
/*  27:    */   }
/*  28:    */   
/*  29:    */   @SideOnly(Side.CLIENT)
/*  30:    */   public void registerIcons(IIconRegister iconRegister)
/*  31:    */   {
/*  32: 34 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:" + getUnlocalizedName().replace("item.", ""));
/*  33:    */   }
/*  34:    */   
/*  35:    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
/*  36:    */   {
/*  37: 39 */     if (!entity.worldObj.isRemote)
/*  38:    */     {
/*  39: 40 */       if ((entity instanceof EntityGolemMecha)) {
/*  40: 42 */         if ((entity.riddenByEntity instanceof EntityHumanBase)) {
/*  41: 43 */           entity = entity.riddenByEntity;
/*  42:    */         }
/*  43:    */       }
/*  44: 46 */       if ((entity instanceof EntityHumanBase))
/*  45:    */       {
/*  46: 48 */         int x = MathHelper.floor_double(entity.posX);
/*  47: 49 */         int y = MathHelper.floor_double(entity.posY);
/*  48: 50 */         int z = MathHelper.floor_double(entity.posZ);
/*  49: 51 */         boolean saved = saveToSpawner(x, y, z, (EntityHumanBase)entity);
/*  50: 52 */         if (saved)
/*  51:    */         {
/*  52: 53 */           EntityParty p = ((EntityHumanBase)entity).party;
/*  53: 54 */           if (p != null) {
/*  54: 55 */             for (int i = 0; i < p.getMembersLength(); i++) {
/*  55: 56 */               if (p.getMember(i) != null)
/*  56:    */               {
/*  57: 57 */                 Entity member = p.getMember(i);
/*  58: 58 */                 if (member.ridingEntity != null) {
/*  59: 59 */                   member.ridingEntity.setDead();
/*  60:    */                 }
/*  61: 60 */                 member.setDead();
/*  62:    */               }
/*  63:    */             }
/*  64:    */           }
/*  65: 64 */           if (entity.ridingEntity != null) {
/*  66: 65 */             entity.ridingEntity.setDead();
/*  67:    */           }
/*  68: 66 */           entity.setDead();
/*  69: 67 */           return true;
/*  70:    */         }
/*  71:    */       }
/*  72:    */       else
/*  73:    */       {
/*  74: 70 */         int x = MathHelper.floor_double(entity.posX);
/*  75: 71 */         int y = MathHelper.floor_double(entity.posY);
/*  76: 72 */         int z = MathHelper.floor_double(entity.posZ);
/*  77: 73 */         boolean saved = saveEntityToSpawner(x, y, z, entity);
/*  78: 74 */         if (saved)
/*  79:    */         {
/*  80: 75 */           entity.setDead();
/*  81: 76 */           return true;
/*  82:    */         }
/*  83:    */       }
/*  84:    */     }
/*  85: 80 */     return false;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public boolean saveEntityToSpawner(int x, int y, int z, Entity entity)
/*  89:    */   {
/*  90: 83 */     World world = entity.worldObj;
/*  91: 84 */     if (world.getBlock(x, y, z) != Blocks.air) {
/*  92: 85 */       y++;
/*  93:    */     }
/*  94: 87 */     world.setBlock(x, y, z, ChocolateQuest.spawner);
/*  95: 88 */     BlockMobSpawnerTileEntity te = new BlockMobSpawnerTileEntity();
/*  96: 89 */     NBTTagCompound tag = new NBTTagCompound();
/*  97: 90 */     boolean wrote = entity.writeToNBTOptional(tag);
/*  98: 91 */     if (wrote)
/*  99:    */     {
/* 100: 92 */       te.mobNBT = tag;
/* 101: 93 */       te.mob = -1;
/* 102: 94 */       world.setTileEntity(x, y, z, te);
/* 103: 95 */       return true;
/* 104:    */     }
/* 105: 97 */     return false;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public static boolean saveToSpawner(int x, int y, int z, EntityHumanBase human)
/* 109:    */   {
/* 110:101 */     World world = human.worldObj;
/* 111:102 */     if (world.getBlock(x, y, z) != Blocks.air) {
/* 112:103 */       y++;
/* 113:    */     }
/* 114:105 */     world.setBlock(x, y, z, ChocolateQuest.spawner);
/* 115:106 */     BlockMobSpawnerTileEntity te = new BlockMobSpawnerTileEntity();
/* 116:107 */     NBTTagCompound tag = new NBTTagCompound();
/* 117:108 */     human.writeToNBTOptional(tag);
/* 118:109 */     human.writeEntityToSpawnerNBT(tag, x, y, z);
/* 119:110 */     te.mobNBT = tag;
/* 120:111 */     te.mob = -1;
/* 121:112 */     world.setTileEntity(x, y, z, te);
/* 122:113 */     return true;
/* 123:    */   }
/* 124:    */   
/* 125:    */   public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
/* 126:    */   {
/* 127:118 */     if (!world.isRemote)
/* 128:    */     {
/* 129:119 */       TileEntity te = world.getTileEntity(x, y, z);
/* 130:120 */       if ((te instanceof BlockMobSpawnerTileEntity))
/* 131:    */       {
/* 132:121 */         BlockMobSpawnerTileEntity teSpawner = (BlockMobSpawnerTileEntity)te;
/* 133:122 */         teSpawner.spawnEntity();
/* 134:    */       }
/* 135:    */     }
/* 136:141 */     return super.onItemUse(is, player, world, x, y, z, par7, par8, par9, par10);
/* 137:    */   }
/* 138:    */   
/* 139:    */   public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
/* 140:    */   {
/* 141:146 */     onItemUse(itemstack, player, player.worldObj, X, Y, Z, 0, 0.0F, 0.0F, 0.0F);
/* 142:147 */     return super.onBlockStartBreak(itemstack, X, Y, Z, player);
/* 143:    */   }
/* 144:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.mobControl.ItemMobToSpawner
 * JD-Core Version:    0.7.1
 */