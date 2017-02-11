/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.model.ModelRenderer;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ 
/*  8:   */ @SideOnly(Side.CLIENT)
/*  9:   */ public class ModelArmorWitchHat
/* 10:   */   extends ModelArmor
/* 11:   */ {
/* 12:   */   public boolean field_82900_g;
/* 13:   */   
/* 14:   */   public ModelArmorWitchHat(float par1)
/* 15:   */   {
/* 16:15 */     super(0);
/* 17:   */     
/* 18:17 */     float x = -5.0F;float y = -9.0F;float z = -5.0F;
/* 19:18 */     this.bipedHead = new ModelRenderer(this).setTextureSize(64, 128);
/* 20:19 */     this.bipedHead.setTextureOffset(0, 64).addBox(x, y, z, 10, 2, 10);
/* 21:20 */     ModelRenderer modelrenderer = new ModelRenderer(this).setTextureSize(64, 128);
/* 22:21 */     modelrenderer.setRotationPoint(1.75F, -3.8F, 2.0F);
/* 23:22 */     modelrenderer.setTextureOffset(0, 76).addBox(x, y, z, 7, 4, 7);
/* 24:23 */     modelrenderer.rotateAngleX = -0.05235988F;
/* 25:24 */     modelrenderer.rotateAngleZ = 0.0261799F;
/* 26:25 */     this.bipedHead.addChild(modelrenderer);
/* 27:26 */     ModelRenderer modelrenderer1 = new ModelRenderer(this).setTextureSize(64, 128);
/* 28:27 */     modelrenderer1.setRotationPoint(1.75F, -3.4F, 2.0F);
/* 29:28 */     modelrenderer1.setTextureOffset(0, 87).addBox(x, y, z, 4, 4, 4);
/* 30:29 */     modelrenderer1.rotateAngleX = -0.1047198F;
/* 31:30 */     modelrenderer1.rotateAngleZ = 0.05235988F;
/* 32:31 */     modelrenderer.addChild(modelrenderer1);
/* 33:32 */     ModelRenderer modelrenderer2 = new ModelRenderer(this).setTextureSize(64, 128);
/* 34:33 */     modelrenderer2.setRotationPoint(1.0F, -1.0F, 0.5F);
/* 35:34 */     modelrenderer2.setTextureOffset(0, 95).addBox(x, y, z, 1, 2, 1, 0.25F);
/* 36:35 */     modelrenderer2.rotateAngleX = -0.2094395F;
/* 37:36 */     modelrenderer2.rotateAngleZ = 0.1047198F;
/* 38:37 */     modelrenderer1.addChild(modelrenderer2);
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/* 42:   */   {
/* 43:43 */     setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
/* 44:44 */     this.bipedHead.render(par7);
/* 45:   */   }
/* 46:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelArmorWitchHat
 * JD-Core Version:    0.7.1
 */