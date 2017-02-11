/*   1:    */ package com.chocolate.chocolateQuest.items;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   4:    */ import com.google.common.collect.Multimap;
/*   5:    */ import cpw.mods.fml.relauncher.Side;
/*   6:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   7:    */ import java.util.Random;
/*   8:    */ import net.minecraft.block.Block;
/*   9:    */ import net.minecraft.block.material.Material;
/*  10:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  11:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  12:    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*  13:    */ import net.minecraft.entity.ai.attributes.IAttribute;
/*  14:    */ import net.minecraft.entity.player.EntityPlayer;
/*  15:    */ import net.minecraft.init.Blocks;
/*  16:    */ import net.minecraft.item.Item;
/*  17:    */ import net.minecraft.item.ItemStack;
/*  18:    */ import net.minecraft.nbt.NBTTagCompound;
/*  19:    */ import net.minecraft.util.ChatComponentText;
/*  20:    */ import net.minecraft.world.World;
/*  21:    */ 
/*  22:    */ public class ItemPickaxeMagic
/*  23:    */   extends Item
/*  24:    */ {
/*  25:    */   public ItemPickaxeMagic()
/*  26:    */   {
/*  27: 27 */     setMaxStackSize(1);
/*  28: 28 */     setMaxDamage(2024);
/*  29:    */   }
/*  30:    */   
/*  31:    */   @SideOnly(Side.CLIENT)
/*  32:    */   public void registerIcons(IIconRegister iconRegister)
/*  33:    */   {
/*  34: 34 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:pickaxeMagic");
/*  35:    */   }
/*  36:    */   
/*  37:    */   public boolean canHarvestBlock(Block par1Block)
/*  38:    */   {
/*  39: 42 */     return par1Block != Blocks.bedrock;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public Multimap getItemAttributeModifiers()
/*  43:    */   {
/*  44: 47 */     Multimap multimap = super.getItemAttributeModifiers();
/*  45: 48 */     multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 10000.0D, 0));
/*  46: 49 */     return multimap;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
/*  50:    */   {
/*  51: 58 */     return (par2Block != null) && ((par2Block.getMaterial() == Material.iron) || (par2Block.getMaterial() == Material.anvil) || (par2Block.getMaterial() == Material.rock)) ? 15.0F : 8.0F;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
/*  55:    */   {
/*  56: 64 */     if (player.isSneaking())
/*  57:    */     {
/*  58: 66 */       int mode = getMode(par1ItemStack) + 1;
/*  59: 67 */       if (mode >= 3) {
/*  60: 68 */         mode = 0;
/*  61:    */       }
/*  62: 69 */       setMode(par1ItemStack, mode);
/*  63: 70 */       if (!par2World.isRemote) {
/*  64: 72 */         if (mode == 2) {
/*  65: 73 */           player.addChatMessage(new ChatComponentText(BDHelper.StringColor("f") + "Pickaxe mode: " + BDHelper.StringColor("3") + " build"));
/*  66: 74 */         } else if (mode == 1) {
/*  67: 75 */           player.addChatMessage(new ChatComponentText(BDHelper.StringColor("f") + "Pickaxe mode: " + BDHelper.StringColor("3") + " fill"));
/*  68:    */         } else {
/*  69: 77 */           player.addChatMessage(new ChatComponentText(BDHelper.StringColor("f") + "Pickaxe mode: " + BDHelper.StringColor("3") + " mine"));
/*  70:    */         }
/*  71:    */       }
/*  72:    */     }
/*  73: 80 */     return super.onItemRightClick(par1ItemStack, par2World, player);
/*  74:    */   }
/*  75:    */   
/*  76:    */   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*  77:    */   {
/*  78: 87 */     if ((player.isSneaking()) && (getMode(stack) != 0))
/*  79:    */     {
/*  80: 89 */       setBlockAndMetadata(stack, world.getBlock(x, y, z), world.getBlockMetadata(x, y, z));
/*  81: 90 */       return true;
/*  82:    */     }
/*  83: 93 */     int cont = 0;
/*  84:    */     
/*  85: 95 */     int size = 2;
/*  86: 96 */     if (side == 0) {
/*  87: 98 */       for (int t = -size + 1; t < size; t++) {
/*  88: 99 */         for (int v = -size + 1; v < size; v++) {
/*  89:101 */           destroyAndDropItem(world, x + t, y, z + v, 0, stack, player);
/*  90:    */         }
/*  91:    */       }
/*  92:    */     }
/*  93:105 */     if (side == 1) {
/*  94:107 */       for (int t = -size + 1; t < size; t++) {
/*  95:108 */         for (int v = -size + 1; v < size; v++) {
/*  96:110 */           destroyAndDropItem(world, x + t, y, z + v, 0, stack, player);
/*  97:    */         }
/*  98:    */       }
/*  99:    */     }
/* 100:114 */     if (side == 2) {
/* 101:116 */       for (int t = -size + 1; t < size; t++) {
/* 102:117 */         for (int v = -size + 1; v < size; v++) {
/* 103:119 */           destroyAndDropItem(world, x + t, y + v, z, 0, stack, player);
/* 104:    */         }
/* 105:    */       }
/* 106:    */     }
/* 107:123 */     if (side == 3) {
/* 108:125 */       for (int t = -size + 1; t < size; t++) {
/* 109:126 */         for (int v = -size + 1; v < size; v++) {
/* 110:128 */           destroyAndDropItem(world, x + t, y + v, z, 0, stack, player);
/* 111:    */         }
/* 112:    */       }
/* 113:    */     }
/* 114:132 */     if (side == 4) {
/* 115:134 */       for (int t = -size + 1; t < size; t++) {
/* 116:135 */         for (int v = -size + 1; v < size; v++) {
/* 117:137 */           destroyAndDropItem(world, x, y + t, z + v, 0, stack, player);
/* 118:    */         }
/* 119:    */       }
/* 120:    */     }
/* 121:141 */     if (side == 5) {
/* 122:143 */       for (int t = -size + 1; t < size; t++) {
/* 123:144 */         for (int v = -size + 1; v < size; v++) {
/* 124:146 */           destroyAndDropItem(world, x, y + t, z + v, 0, stack, player);
/* 125:    */         }
/* 126:    */       }
/* 127:    */     }
/* 128:150 */     stack.damageItem(cont, player);
/* 129:151 */     return true;
/* 130:    */   }
/* 131:    */   
/* 132:    */   private void destroyAndDropItem(World world, int i, int j, int k, int power, ItemStack is, EntityPlayer player)
/* 133:    */   {
/* 134:157 */     Block id = world.getBlock(i, j, k);
/* 135:159 */     if (getMode(is) == 2)
/* 136:    */     {
/* 137:161 */       world.setBlock(i, j, k, getBlock(is), getMetadata(is), 3);
/* 138:    */       
/* 139:    */ 
/* 140:    */ 
/* 141:    */ 
/* 142:    */ 
/* 143:    */ 
/* 144:    */ 
/* 145:    */ 
/* 146:    */ 
/* 147:    */ 
/* 148:    */ 
/* 149:    */ 
/* 150:    */ 
/* 151:    */ 
/* 152:    */ 
/* 153:    */ 
/* 154:    */ 
/* 155:    */ 
/* 156:    */ 
/* 157:    */ 
/* 158:    */ 
/* 159:    */ 
/* 160:    */ 
/* 161:    */ 
/* 162:    */ 
/* 163:    */ 
/* 164:188 */       is.damageItem(1, player);
/* 165:    */     }
/* 166:190 */     else if ((id != Blocks.air) && (id != Blocks.bedrock))
/* 167:    */     {
/* 168:192 */       if (world.isRemote)
/* 169:    */       {
/* 170:194 */         int md = world.getBlockMetadata(i, j, k);
/* 171:196 */         for (int c = 0; c < 8; c++) {
/* 172:198 */           world.spawnParticle("tilecrack_" + id + "_" + 0, i + (itemRand.nextFloat() - 0.5D), j + itemRand.nextFloat() - 0.5F, k + itemRand.nextFloat() - 0.5D, 0.0D, 0.0D, 0.0D);
/* 173:    */         }
/* 174:    */       }
/* 175:202 */       is.damageItem(1, player);
/* 176:203 */       if (getMode(is) == 1) {
/* 177:205 */         world.setBlock(i, j, k, getBlock(is), getMetadata(is), 3);
/* 178:    */       } else {
/* 179:208 */         world.setBlockToAir(i, j, k);
/* 180:    */       }
/* 181:    */     }
/* 182:    */   }
/* 183:    */   
/* 184:    */   public String getItemStackDisplayName(ItemStack itemstack)
/* 185:    */   {
/* 186:216 */     return new String("Super Tool!(Creative only)").concat(getMode(itemstack) == 1 ? "Fill mode" : getMode(itemstack) == 2 ? "Build mode" : "");
/* 187:    */   }
/* 188:    */   
/* 189:    */   public int getMode(ItemStack is)
/* 190:    */   {
/* 191:221 */     if (is.stackTagCompound != null) {
/* 192:223 */       return is.stackTagCompound.getByte("mode");
/* 193:    */     }
/* 194:225 */     return 0;
/* 195:    */   }
/* 196:    */   
/* 197:    */   public void setMode(ItemStack is, int i)
/* 198:    */   {
/* 199:230 */     if (is.stackTagCompound == null) {
/* 200:232 */       is.stackTagCompound = new NBTTagCompound();
/* 201:    */     }
/* 202:234 */     is.stackTagCompound.setByte("mode", (byte)i);
/* 203:    */   }
/* 204:    */   
/* 205:    */   public void setBlockAndMetadata(ItemStack is, Block block, int md)
/* 206:    */   {
/* 207:239 */     if (is.stackTagCompound == null) {
/* 208:241 */       is.stackTagCompound = new NBTTagCompound();
/* 209:    */     }
/* 210:243 */     is.stackTagCompound.setInteger("bl", Block.getIdFromBlock(block));
/* 211:244 */     is.stackTagCompound.setInteger("md", md);
/* 212:    */   }
/* 213:    */   
/* 214:    */   public Block getBlock(ItemStack is)
/* 215:    */   {
/* 216:248 */     if (is.stackTagCompound == null) {
/* 217:250 */       return Blocks.stone;
/* 218:    */     }
/* 219:252 */     int id = is.stackTagCompound.getInteger("bl");
/* 220:253 */     if (id < 1) {
/* 221:254 */       return Blocks.stone;
/* 222:    */     }
/* 223:255 */     return Block.getBlockById(id);
/* 224:    */   }
/* 225:    */   
/* 226:    */   public int getMetadata(ItemStack is)
/* 227:    */   {
/* 228:259 */     if (is.stackTagCompound == null) {
/* 229:261 */       return 0;
/* 230:    */     }
/* 231:263 */     return is.stackTagCompound.getInteger("md");
/* 232:    */   }
/* 233:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemPickaxeMagic
 * JD-Core Version:    0.7.1
 */