/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBase;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import org.lwjgl.opengl.GL11;
/*  7:   */ 
/*  8:   */ public class ModelDragonPart
/*  9:   */   extends ModelBase
/* 10:   */ {
/* 11:   */   ModelRenderer leg;
/* 12:   */   ModelRenderer Head;
/* 13:   */   
/* 14:   */   public ModelDragonPart()
/* 15:   */   {
/* 16:23 */     this.textureWidth = 64;
/* 17:24 */     this.textureHeight = 32;
/* 18:25 */     this.leg = new ModelRenderer(this, 0, 24);
/* 19:26 */     this.leg.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4);
/* 20:27 */     this.leg.setRotationPoint(0.0F, -4.0F, 0.0F);
/* 21:28 */     this.leg.setTextureSize(64, 32);
/* 22:29 */     this.leg.mirror = true;
/* 23:30 */     setRotation(this.leg, 0.0F, 0.0F, 0.0F);
/* 24:31 */     this.Head = new ModelRenderer(this, 0, 0);
/* 25:32 */     this.Head.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/* 26:33 */     this.Head.setRotationPoint(0.0F, -4.0F, 0.0F);
/* 27:34 */     this.Head.setTextureSize(64, 32);
/* 28:35 */     this.Head.mirror = true;
/* 29:36 */     setRotation(this.Head, 0.0F, 0.0F, 0.0F);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 33:   */   {
/* 34:41 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 35:42 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 36:43 */     GL11.glPushMatrix();
/* 37:44 */     GL11.glTranslatef(0.0F, 0.2F, 0.0F);
/* 38:45 */     GL11.glScalef(2.0F, 2.0F, 2.0F);
/* 39:46 */     this.Head.render(f5);
/* 40:47 */     GL11.glPopMatrix();
/* 41:   */   }
/* 42:   */   
/* 43:   */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 44:   */   {
/* 45:52 */     model.rotateAngleX = x;
/* 46:53 */     model.rotateAngleY = y;
/* 47:54 */     model.rotateAngleZ = z;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 51:   */   {
/* 52:59 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 53:   */   }
/* 54:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelDragonPart
 * JD-Core Version:    0.7.1
 */