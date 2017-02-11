/*   1:    */ package com.chocolate.chocolateQuest.block;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.nbt.NBTTagCompound;
/*   6:    */ import net.minecraft.network.NetworkManager;
/*   7:    */ import net.minecraft.network.Packet;
/*   8:    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*   9:    */ import net.minecraft.tileentity.TileEntity;
/*  10:    */ import net.minecraft.util.AxisAlignedBB;
/*  11:    */ import net.minecraft.world.World;
/*  12:    */ 
/*  13:    */ public class BlockEditorTileEntity
/*  14:    */   extends TileEntity
/*  15:    */ {
/*  16: 15 */   public int yellow = 15;
/*  17: 15 */   public int height = 20;
/*  18: 15 */   public int red = 15;
/*  19: 16 */   public String name = "Template";
/*  20:    */   
/*  21:    */   public boolean anyPlayerInRange()
/*  22:    */   {
/*  23: 26 */     return this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, 16.0D) != null;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public void updateEntity()
/*  27:    */   {
/*  28: 35 */     super.updateEntity();
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void readFromNBT(NBTTagCompound par1NBTTagCompound)
/*  32:    */   {
/*  33: 43 */     super.readFromNBT(par1NBTTagCompound);
/*  34: 44 */     this.red = par1NBTTagCompound.getInteger("red");
/*  35: 45 */     this.yellow = par1NBTTagCompound.getInteger("yellow");
/*  36: 46 */     this.height = par1NBTTagCompound.getInteger("height");
/*  37: 47 */     setName(par1NBTTagCompound.getString("name"));
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void writeToNBT(NBTTagCompound par1NBTTagCompound)
/*  41:    */   {
/*  42: 55 */     super.writeToNBT(par1NBTTagCompound);
/*  43: 56 */     par1NBTTagCompound.setInteger("red", this.red);
/*  44: 57 */     par1NBTTagCompound.setInteger("yellow", this.yellow);
/*  45: 58 */     par1NBTTagCompound.setInteger("height", this.height);
/*  46: 59 */     par1NBTTagCompound.setString("name", getName());
/*  47:    */   }
/*  48:    */   
/*  49:    */   public String getName()
/*  50:    */   {
/*  51: 64 */     return this.name;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void setName(String name)
/*  55:    */   {
/*  56: 69 */     this.name = name;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public Packet getDescriptionPacket()
/*  60:    */   {
/*  61: 78 */     NBTTagCompound var1 = new NBTTagCompound();
/*  62: 79 */     writeToNBT(var1);
/*  63: 80 */     return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, var1);
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*  67:    */   {
/*  68: 86 */     NBTTagCompound tag = packet.func_148857_g();
/*  69: 87 */     this.red = tag.getInteger("red");
/*  70: 88 */     this.yellow = tag.getInteger("yellow");
/*  71: 89 */     this.height = tag.getInteger("height");
/*  72: 90 */     setName(tag.getString("name"));
/*  73:    */   }
/*  74:    */   
/*  75:    */   public double getRenderDistance()
/*  76:    */   {
/*  77: 95 */     return 64 + this.red + this.yellow + this.height;
/*  78:    */   }
/*  79:    */   
/*  80:    */   @SideOnly(Side.CLIENT)
/*  81:    */   public double func_82115_m()
/*  82:    */   {
/*  83:101 */     return (64 + this.red + this.yellow + this.height) * (64 + this.red + this.yellow + this.height);
/*  84:    */   }
/*  85:    */   
/*  86:    */   public AxisAlignedBB getRenderBoundingBox()
/*  87:    */   {
/*  88:106 */     return super.getRenderBoundingBox().addCoord(this.red, this.height, this.yellow);
/*  89:    */   }
/*  90:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.block.BlockEditorTileEntity
 * JD-Core Version:    0.7.1
 */