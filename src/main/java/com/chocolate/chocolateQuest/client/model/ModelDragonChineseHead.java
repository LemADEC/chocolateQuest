/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBase;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import org.lwjgl.opengl.GL11;
/*  7:   */ 
/*  8:   */ public class ModelDragonChineseHead
/*  9:   */   extends ModelBase
/* 10:   */ {
/* 11:   */   ModelRenderer head;
/* 12:   */   ModelRenderer mouthUp;
/* 13:   */   ModelRenderer mouthDown;
/* 14:   */   ModelRenderer nose;
/* 15:   */   ModelRenderer hornLeft;
/* 16:   */   ModelRenderer hornRight;
/* 17:   */   
/* 18:   */   public ModelDragonChineseHead()
/* 19:   */   {
/* 20:27 */     this.head = new ModelRenderer(this, 0, 16);
/* 21:28 */     this.head.addBox(-2.5F, -2.0F, -4.0F, 5, 5, 8);
/* 22:29 */     this.mouthUp = new ModelRenderer(this, 32, 0);
/* 23:30 */     this.mouthUp.addBox(-2.0F, -2.0F, -11.0F, 4, 3, 7);
/* 24:31 */     this.mouthDown = new ModelRenderer(this, 32, 10);
/* 25:32 */     this.mouthDown.addBox(-2.0F, 1.0F, -10.0F, 4, 1, 6);
/* 26:33 */     this.nose = new ModelRenderer(this, 27, 0);
/* 27:34 */     this.nose.addBox(-2.0F, -3.0F, -11.0F, 4, 1, 1);
/* 28:35 */     this.hornLeft = new ModelRenderer(this, 27, 18);
/* 29:36 */     this.hornLeft.addBox(1.5F, -2.0F, 3.0F, 1, 1, 8);
/* 30:37 */     this.hornLeft.rotateAngleX = 0.5F;
/* 31:38 */     this.hornLeft.rotateAngleY = 0.098F;
/* 32:39 */     this.hornRight = new ModelRenderer(this, 27, 18);
/* 33:40 */     this.hornRight.addBox(-2.5F, -2.0F, 3.0F, 1, 1, 8);
/* 34:41 */     this.hornRight.rotateAngleX = 0.5F;
/* 35:42 */     this.hornRight.rotateAngleY = -0.298F;
/* 36:43 */     this.head.addChild(this.mouthUp);
/* 37:44 */     this.head.addChild(this.mouthDown);
/* 38:45 */     this.head.addChild(this.nose);
/* 39:46 */     this.head.addChild(this.hornLeft);
/* 40:47 */     this.head.addChild(this.hornRight);
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 44:   */   {
/* 45:52 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 46:53 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 47:54 */     GL11.glPushMatrix();
/* 48:55 */     GL11.glScalef(3.0F, 3.0F, 3.0F);
/* 49:56 */     this.head.setRotationPoint(0.0F, 6.0F, 0.0F);
/* 50:   */     
/* 51:   */ 
/* 52:   */ 
/* 53:   */ 
/* 54:   */ 
/* 55:   */ 
/* 56:   */ 
/* 57:   */ 
/* 58:   */ 
/* 59:   */ 
/* 60:   */ 
/* 61:68 */     this.head.render(f5);
/* 62:69 */     GL11.glPopMatrix();
/* 63:   */   }
/* 64:   */   
/* 65:   */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 66:   */   {
/* 67:74 */     model.rotateAngleX = x;
/* 68:75 */     model.rotateAngleY = y;
/* 69:76 */     model.rotateAngleZ = z;
/* 70:   */   }
/* 71:   */   
/* 72:   */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 73:   */   {
/* 74:81 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 75:   */   }
/* 76:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelDragonChineseHead
 * JD-Core Version:    0.7.1
 */