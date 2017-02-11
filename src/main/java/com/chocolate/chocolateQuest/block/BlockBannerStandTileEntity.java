/*   1:    */ package com.chocolate.chocolateQuest.block;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import cpw.mods.fml.relauncher.Side;
/*   5:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   6:    */ import net.minecraft.item.ItemStack;
/*   7:    */ import net.minecraft.nbt.NBTTagCompound;
/*   8:    */ import net.minecraft.network.NetworkManager;
/*   9:    */ import net.minecraft.network.Packet;
/*  10:    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*  11:    */ import net.minecraft.tileentity.TileEntity;
/*  12:    */ import net.minecraft.util.AxisAlignedBB;
/*  13:    */ 
/*  14:    */ public class BlockBannerStandTileEntity
/*  15:    */   extends TileEntity
/*  16:    */ {
/*  17: 18 */   public boolean hasFlag = false;
/*  18: 19 */   public int rotation = 0;
/*  19:    */   public ItemStack item;
/*  20:    */   
/*  21:    */   public AxisAlignedBB getRenderBoundingBox()
/*  22:    */   {
/*  23: 28 */     return super.getRenderBoundingBox().addCoord(0.0D, 1.0D, 0.0D);
/*  24:    */   }
/*  25:    */   
/*  26:    */   public void updateEntity() {}
/*  27:    */   
/*  28:    */   public void readFromNBT(NBTTagCompound nbttagcompound)
/*  29:    */   {
/*  30: 43 */     super.readFromNBT(nbttagcompound);
/*  31: 44 */     this.rotation = nbttagcompound.getInteger("rot");
/*  32:    */     
/*  33: 46 */     this.hasFlag = nbttagcompound.getBoolean("flag");
/*  34:    */     
/*  35: 48 */     NBTTagCompound itemNBT = (NBTTagCompound)nbttagcompound.getTag("Item");
/*  36: 49 */     if (itemNBT != null) {
/*  37: 50 */       this.item = ItemStack.loadItemStackFromNBT(itemNBT);
/*  38: 51 */     } else if (this.hasFlag) {
/*  39: 52 */       this.item = new ItemStack(ChocolateQuest.banner, 1, nbttagcompound.getInteger("type"));
/*  40:    */     }
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void writeToNBT(NBTTagCompound nbttagcompound)
/*  44:    */   {
/*  45: 60 */     super.writeToNBT(nbttagcompound);
/*  46: 61 */     nbttagcompound.setInteger("rot", this.rotation);
/*  47: 62 */     if (this.item != null)
/*  48:    */     {
/*  49: 64 */       NBTTagCompound itemNBT = new NBTTagCompound();
/*  50: 65 */       nbttagcompound.setTag("Item", this.item.writeToNBT(itemNBT));
/*  51:    */       
/*  52: 67 */       nbttagcompound.setInteger("type", this.item.getItemDamage());
/*  53:    */     }
/*  54: 69 */     nbttagcompound.setBoolean("flag", this.hasFlag);
/*  55:    */   }
/*  56:    */   
/*  57:    */   public Packet getDescriptionPacket()
/*  58:    */   {
/*  59: 75 */     NBTTagCompound data = new NBTTagCompound();
/*  60: 76 */     data.setInteger("rot", this.rotation);
/*  61: 77 */     if (this.item != null)
/*  62:    */     {
/*  63: 79 */       NBTTagCompound itemNBT = new NBTTagCompound();
/*  64: 80 */       data.setTag("Item", this.item.writeToNBT(itemNBT));
/*  65:    */     }
/*  66: 82 */     data.setBoolean("flag", this.hasFlag);
/*  67: 83 */     return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, data);
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*  71:    */   {
/*  72: 89 */     NBTTagCompound tag = packet.func_148857_g();
/*  73: 90 */     this.rotation = tag.getInteger("rot");
/*  74: 91 */     NBTTagCompound itemNBT = (NBTTagCompound)tag.getTag("Item");
/*  75: 92 */     if (itemNBT != null) {
/*  76: 94 */       this.item = ItemStack.loadItemStackFromNBT(itemNBT);
/*  77:    */     }
/*  78: 96 */     this.hasFlag = tag.getBoolean("flag");
/*  79:    */   }
/*  80:    */   
/*  81:    */   @SideOnly(Side.CLIENT)
/*  82:    */   public double getMaxRenderDistanceSquared()
/*  83:    */   {
/*  84:103 */     return 6400.0D;
/*  85:    */   }
/*  86:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockBannerStandTileEntity
 * JD-Core Version:    0.7.1
 */