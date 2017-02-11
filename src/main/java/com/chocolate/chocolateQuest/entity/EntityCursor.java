/*  1:   */ package com.chocolate.chocolateQuest.entity;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.client.Minecraft;
/*  5:   */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.entity.player.InventoryPlayer;
/*  8:   */ import net.minecraft.item.ItemStack;
/*  9:   */ import net.minecraft.nbt.NBTTagCompound;
/* 10:   */ import net.minecraft.world.World;
/* 11:   */ 
/* 12:   */ public class EntityCursor
/* 13:   */   extends Entity
/* 14:   */ {
/* 15:11 */   int lifeTime = 0;
/* 16:   */   Entity followEntity;
/* 17:   */   public ItemStack item;
/* 18:   */   public EntityCursor next;
/* 19:   */   
/* 20:   */   public EntityCursor(World par1World)
/* 21:   */   {
/* 22:18 */     super(par1World);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public EntityCursor(World par1World, double x, double y, double z, float rotationYaw, ItemStack item)
/* 26:   */   {
/* 27:23 */     this(par1World, x, y, z, rotationYaw);
/* 28:24 */     this.item = item;
/* 29:25 */     setPositionAndRotation(x, y + 1.0D, z, rotationYaw, 0.0F);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public EntityCursor(World par1World, double x, double y, double z, float rotationYaw)
/* 33:   */   {
/* 34:29 */     super(par1World);
/* 35:30 */     setPositionAndRotation(x, y + 1.0D, z, rotationYaw, 0.0F);
/* 36:   */   }
/* 37:   */   
/* 38:   */   public EntityCursor(World par1World, Entity entity, ItemStack item)
/* 39:   */   {
/* 40:34 */     super(par1World);
/* 41:35 */     this.followEntity = entity;
/* 42:36 */     this.item = item;
/* 43:37 */     setPositionAndRotation(entity.posX, entity.posY + 1.0D, entity.posZ, this.rotationYaw, 0.0F);
/* 44:   */   }
/* 45:   */   
/* 46:   */   protected void entityInit() {}
/* 47:   */   
/* 48:   */   public void onUpdate()
/* 49:   */   {
/* 50:48 */     super.onUpdate();
/* 51:49 */     if (this.ticksExisted < 5) {
/* 52:50 */       this.posY -= 0.2D;
/* 53:   */     }
/* 54:51 */     if (this.ticksExisted > 40) {
/* 55:52 */       setDead();
/* 56:   */     }
/* 57:54 */     if (this.followEntity != null)
/* 58:   */     {
/* 59:56 */       this.posX = this.followEntity.posX;
/* 60:57 */       this.posY = (this.followEntity.posY - this.ticksExisted / 100);
/* 61:58 */       this.posZ = this.followEntity.posZ;
/* 62:   */       
/* 63:60 */       this.rotationYaw = this.followEntity.rotationYaw;
/* 64:62 */       if (this.ticksExisted > 20) {
/* 65:63 */         setDead();
/* 66:   */       }
/* 67:   */     }
/* 68:65 */     if (this.next != null) {
/* 69:67 */       for (int i = 0; i < 4; i++) {
/* 70:68 */         this.worldObj.spawnParticle("enchantmenttable", this.next.posX + (this.rand.nextFloat() - 0.5F) / 2.0F, this.next.posY + this.rand.nextFloat() / 2.0F + 1.5D, this.next.posZ + (this.rand.nextFloat() - 0.5F) / 2.0F, this.posX - this.next.posX, this.posY - this.next.posY - 1.0D, this.posZ - this.next.posZ);
/* 71:   */       }
/* 72:   */     }
/* 73:   */   }
/* 74:   */   
/* 75:   */   public void setDead()
/* 76:   */   {
/* 77:75 */     if ((this.item != null) && (Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem() != null)) {
/* 78:77 */       if (this.item.isItemEqual(Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem())) {
/* 79:78 */         return;
/* 80:   */       }
/* 81:   */     }
/* 82:80 */     super.setDead();
/* 83:   */   }
/* 84:   */   
/* 85:   */   protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {}
/* 86:   */   
/* 87:   */   protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {}
/* 88:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.EntityCursor
 * JD-Core Version:    0.7.1
 */