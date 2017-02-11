/*  1:   */ package com.chocolate.chocolateQuest.entity.mob;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase;
/*  5:   */ import com.chocolate.chocolateQuest.particles.EffectManager;
/*  6:   */ import net.minecraft.item.ItemStack;
/*  7:   */ import net.minecraft.nbt.NBTTagCompound;
/*  8:   */ import net.minecraft.util.Vec3;
/*  9:   */ import net.minecraft.world.World;
/* 10:   */ 
/* 11:   */ public class EntityHumanWalker
/* 12:   */   extends EntityHumanMob
/* 13:   */ {
/* 14:   */   public EntityHumanWalker(World world)
/* 15:   */   {
/* 16:14 */     super(world);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public DungeonMonstersBase getMonsterType()
/* 20:   */   {
/* 21:19 */     return ChocolateQuest.walker;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void onLivingUpdate()
/* 25:   */   {
/* 26:25 */     super.onLivingUpdate();
/* 27:26 */     if (this.worldObj.isRemote) {
/* 28:28 */       if (this.ticksExisted % 10 == 0)
/* 29:   */       {
/* 30:30 */         int eyeOff = 25;
/* 31:31 */         double eyeDist = 0.38D;
/* 32:32 */         double yLook = 1.0D - Math.abs(getLookVec().yCoord);
/* 33:33 */         double x = this.posX - Math.sin(Math.toRadians(this.rotationYawHead + eyeOff)) * eyeDist * yLook + this.motionX;
/* 34:34 */         double z = this.posZ + Math.cos(Math.toRadians(this.rotationYawHead + eyeOff)) * eyeDist * yLook + this.motionZ;
/* 35:35 */         double y = this.posY + (getEyeHeight() + 0.2D) * getSizeModifier() + getLookVec().yCoord;
/* 36:36 */         EffectManager.spawnParticle(7, this.worldObj, x, y, z, 0.4D, 0.8D, 1.0D);
/* 37:   */         
/* 38:38 */         eyeOff = -25;
/* 39:39 */         x = this.posX - Math.sin(Math.toRadians(this.rotationYawHead + eyeOff)) * eyeDist * yLook + this.motionX;
/* 40:40 */         z = this.posZ + Math.cos(Math.toRadians(this.rotationYawHead + eyeOff)) * eyeDist * yLook + this.motionZ;
/* 41:41 */         EffectManager.spawnParticle(7, this.worldObj, x, y, z, 0.4D, 0.8D, 1.0D);
/* 42:   */       }
/* 43:   */     }
/* 44:   */   }
/* 45:   */   
/* 46:   */   public ItemStack getDiamondArmorForSlot(int slot)
/* 47:   */   {
/* 48:   */     ItemStack is;
/* 49:50 */     switch (slot)
/* 50:   */     {
/* 51:   */     case 3: 
/* 52:51 */       is = new ItemStack(ChocolateQuest.diamondPlate); break;
/* 53:   */     case 2: 
/* 54:52 */       is = new ItemStack(ChocolateQuest.diamondPants); break;
/* 55:   */     case 1: 
/* 56:53 */       is = new ItemStack(ChocolateQuest.diamondBoots); break;
/* 57:   */     default: 
/* 58:54 */       is = new ItemStack(ChocolateQuest.diamondHelmet);
/* 59:   */     }
/* 60:56 */     NBTTagCompound tag = new NBTTagCompound();
/* 61:57 */     NBTTagCompound tagDisplay = new NBTTagCompound();
/* 62:58 */     tag.setTag("display", tagDisplay);
/* 63:59 */     is.stackTagCompound = tag;
/* 64:60 */     tagDisplay.setInteger("color", 8339378);
/* 65:61 */     return is;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public ItemStack getIronArmorForSlot(int slot)
/* 69:   */   {
/* 70:   */     ItemStack is;
/* 71:67 */     switch (slot)
/* 72:   */     {
/* 73:   */     case 3: 
/* 74:68 */       is = new ItemStack(ChocolateQuest.ironPlate); break;
/* 75:   */     case 2: 
/* 76:69 */       is = new ItemStack(ChocolateQuest.ironPants); break;
/* 77:   */     case 1: 
/* 78:70 */       is = new ItemStack(ChocolateQuest.ironBoots); break;
/* 79:   */     default: 
/* 80:71 */       is = new ItemStack(ChocolateQuest.ironHelmet);
/* 81:   */     }
/* 82:73 */     NBTTagCompound tag = new NBTTagCompound();
/* 83:74 */     NBTTagCompound tagDisplay = new NBTTagCompound();
/* 84:75 */     tag.setTag("display", tagDisplay);
/* 85:76 */     is.stackTagCompound = tag;
/* 86:77 */     tagDisplay.setInteger("color", 8339378);
/* 87:78 */     return is;
/* 88:   */   }
/* 89:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.mob.EntityHumanWalker
 * JD-Core Version:    0.7.1
 */