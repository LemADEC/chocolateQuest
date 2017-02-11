/*  1:   */ package com.chocolate.chocolateQuest.client;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.items.swords.ItemBaseBroadSword;
/*  4:   */ import net.minecraft.client.model.ModelBiped;
/*  5:   */ import net.minecraft.client.model.ModelRenderer;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.entity.player.EntityPlayer;
/*  8:   */ import net.minecraft.entity.player.InventoryPlayer;
/*  9:   */ import net.minecraft.item.ItemStack;
/* 10:   */ import net.minecraft.util.MathHelper;
/* 11:   */ 
/* 12:   */ public class BDRenderPlayerModel
/* 13:   */   extends ModelBiped
/* 14:   */ {
/* 15:   */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 16:   */   {
/* 17:16 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 18:17 */     if ((entity instanceof EntityPlayer))
/* 19:   */     {
/* 20:19 */       EntityPlayer ep = (EntityPlayer)entity;
/* 21:20 */       if (ep.inventory.getCurrentItem() != null)
/* 22:   */       {
/* 23:22 */         ItemStack is = ep.inventory.getCurrentItem();
/* 24:23 */         if ((is.getItem() instanceof ItemBaseBroadSword)) {
/* 25:24 */           setTwoHandedAngles(f5);
/* 26:   */         }
/* 27:25 */         if (ep.isBlocking()) {
/* 28:26 */           setShiedRotation(f5);
/* 29:   */         }
/* 30:   */       }
/* 31:   */     }
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void setTwoHandedAngles(float time)
/* 35:   */   {
/* 36:32 */     float f7 = 0.0F;
/* 37:   */     
/* 38:34 */     float rotationYaw = 0.0F;
/* 39:35 */     float swing = MathHelper.sin(this.onGround * 3.141593F);
/* 40:36 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 41:37 */     this.bipedLeftArm.rotateAngleZ = -0.3F;
/* 42:38 */     this.bipedRightArm.rotateAngleY = (rotationYaw - 0.6F);
/* 43:39 */     this.bipedLeftArm.rotateAngleY = (rotationYaw + 0.6F);
/* 44:40 */     this.bipedRightArm.rotateAngleX = (-0.8F + swing);
/* 45:41 */     this.bipedLeftArm.rotateAngleX = (-0.8F + swing);
/* 46:   */     
/* 47:   */ 
/* 48:44 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(time * 0.09F) * 0.05F + 0.05F;
/* 49:45 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(time * 0.09F) * 0.05F + 0.05F;
/* 50:46 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(time * 0.067F) * 0.05F;
/* 51:47 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(time * 0.067F) * 0.05F;
/* 52:   */     
/* 53:49 */     float f6 = 1.0F - this.onGround;
/* 54:50 */     f6 *= f6;
/* 55:51 */     f6 *= f6;
/* 56:52 */     f6 = 1.0F - f6;
/* 57:53 */     f7 = MathHelper.sin(f6 * 3.141593F);
/* 58:54 */     float f8 = MathHelper.sin(this.onGround * 3.141593F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
/* 59:55 */     this.bipedRightArm.rotateAngleX = ((float)(this.bipedRightArm.rotateAngleX - (f7 * 1.2D + f8)));
/* 60:56 */     this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
/* 61:57 */     this.bipedRightArm.rotateAngleZ = (MathHelper.sin(this.onGround * 3.141593F) * -0.4F);
/* 62:   */     
/* 63:   */ 
/* 64:60 */     this.bipedLeftArm.rotateAngleX = ((float)(this.bipedRightArm.rotateAngleX - (f7 * 1.2D + f8)));
/* 65:61 */     this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
/* 66:62 */     this.bipedLeftArm.rotateAngleZ = (MathHelper.sin(this.onGround * 3.141593F) * -0.4F);
/* 67:   */   }
/* 68:   */   
/* 69:   */   public void setShiedRotation(float time)
/* 70:   */   {
/* 71:67 */     float f7 = 0.0F;
/* 72:68 */     float f9 = 0.0F;
/* 73:69 */     this.bipedLeftArm.rotateAngleZ = -0.7F;
/* 74:70 */     this.bipedLeftArm.rotateAngleY = 1.2F;
/* 75:71 */     this.bipedLeftArm.rotateAngleX = (-1.570796F + this.bipedHead.rotateAngleX);
/* 76:72 */     this.bipedLeftArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
/* 77:73 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(time * 0.09F) * 0.05F + 0.05F;
/* 78:74 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(time * 0.067F) * 0.05F;
/* 79:   */   }
/* 80:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.BDRenderPlayerModel
 * JD-Core Version:    0.7.1
 */