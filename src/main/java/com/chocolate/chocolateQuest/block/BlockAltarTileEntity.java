/*  1:   */ package com.chocolate.chocolateQuest.block;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ import net.minecraft.nbt.NBTTagCompound;
/*  7:   */ import net.minecraft.network.NetworkManager;
/*  8:   */ import net.minecraft.network.Packet;
/*  9:   */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/* 10:   */ import net.minecraft.tileentity.TileEntity;
/* 11:   */ import net.minecraft.util.AxisAlignedBB;
/* 12:   */ import net.minecraft.world.World;
/* 13:   */ 
/* 14:   */ public class BlockAltarTileEntity
/* 15:   */   extends TileEntity
/* 16:   */ {
/* 17:   */   public ItemStack item;
/* 18:16 */   public int rotation = 0;
/* 19:   */   
/* 20:   */   public boolean anyPlayerInRange()
/* 21:   */   {
/* 22:26 */     return this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, 16.0D) != null;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void updateEntity()
/* 26:   */   {
/* 27:35 */     super.updateEntity();
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void readFromNBT(NBTTagCompound nbttagcompound)
/* 31:   */   {
/* 32:43 */     super.readFromNBT(nbttagcompound);
/* 33:44 */     this.rotation = nbttagcompound.getInteger("rot");
/* 34:45 */     NBTTagCompound itemNBT = (NBTTagCompound)nbttagcompound.getTag("CItem");
/* 35:46 */     if (itemNBT != null) {
/* 36:48 */       this.item = ItemStack.loadItemStackFromNBT(itemNBT);
/* 37:   */     }
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void writeToNBT(NBTTagCompound nbttagcompound)
/* 41:   */   {
/* 42:57 */     super.writeToNBT(nbttagcompound);
/* 43:58 */     nbttagcompound.setInteger("rot", this.rotation);
/* 44:59 */     if (this.item != null)
/* 45:   */     {
/* 46:61 */       NBTTagCompound itemNBT = new NBTTagCompound();
/* 47:62 */       nbttagcompound.setTag("CItem", this.item.writeToNBT(itemNBT));
/* 48:   */     }
/* 49:   */   }
/* 50:   */   
/* 51:   */   public Packet getDescriptionPacket()
/* 52:   */   {
/* 53:69 */     NBTTagCompound data = new NBTTagCompound();
/* 54:70 */     data.setInteger("rot", this.rotation);
/* 55:71 */     if (this.item != null)
/* 56:   */     {
/* 57:73 */       NBTTagCompound itemNBT = new NBTTagCompound();
/* 58:74 */       data.setTag("CItem", this.item.writeToNBT(itemNBT));
/* 59:   */     }
/* 60:76 */     return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, data);
/* 61:   */   }
/* 62:   */   
/* 63:   */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/* 64:   */   {
/* 65:81 */     NBTTagCompound tag = packet.func_148857_g();
/* 66:82 */     this.rotation = tag.getInteger("rot");
/* 67:83 */     NBTTagCompound itemNBT = (NBTTagCompound)tag.getTag("CItem");
/* 68:84 */     if (itemNBT != null) {
/* 69:86 */       this.item = ItemStack.loadItemStackFromNBT(itemNBT);
/* 70:   */     }
/* 71:   */   }
/* 72:   */   
/* 73:   */   @SideOnly(Side.CLIENT)
/* 74:   */   public double getMaxRenderDistanceSquared()
/* 75:   */   {
/* 76:94 */     return 6400.0D;
/* 77:   */   }
/* 78:   */   
/* 79:   */   public AxisAlignedBB getRenderBoundingBox()
/* 80:   */   {
/* 81:99 */     return super.getRenderBoundingBox().addCoord(0.0D, -1.0D, 0.0D);
/* 82:   */   }
/* 83:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockAltarTileEntity
 * JD-Core Version:    0.7.1
 */