/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelBase;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.util.MathHelper;
/*  7:   */ 
/*  8:   */ public class Modelbee
/*  9:   */   extends ModelBase
/* 10:   */ {
/* 11:   */   ModelRenderer mainBody;
/* 12:   */   ModelRenderer leftWing;
/* 13:   */   ModelRenderer head;
/* 14:   */   ModelRenderer tail;
/* 15:   */   ModelRenderer leftWing1;
/* 16:   */   ModelRenderer sendaryBody;
/* 17:   */   
/* 18:   */   public Modelbee()
/* 19:   */   {
/* 20:17 */     this.mainBody = new ModelRenderer(this, 0, 0);
/* 21:18 */     this.mainBody.addBox(0.0F, 0.0F, 0.0F, 6, 5, 8);
/* 22:19 */     this.mainBody.setRotationPoint(-2.0F, 0.0F, 0.0F);
/* 23:20 */     this.mainBody.rotateAngleX = 0.0F;
/* 24:21 */     this.mainBody.rotateAngleY = 0.0F;
/* 25:22 */     this.mainBody.rotateAngleZ = 0.0F;
/* 26:23 */     this.mainBody.mirror = false;
/* 27:24 */     this.leftWing = new ModelRenderer(this, 0, 21);
/* 28:25 */     this.leftWing.addBox(0.0F, 0.0F, 0.0F, 7, 0, 11);
/* 29:26 */     this.leftWing.setRotationPoint(0.0F, 1.0F, 7.0F);
/* 30:27 */     this.leftWing.rotateAngleX = 0.00227F;
/* 31:28 */     this.leftWing.rotateAngleY = 1.041F;
/* 32:29 */     this.leftWing.rotateAngleZ = 0.0F;
/* 33:30 */     this.leftWing.mirror = false;
/* 34:31 */     this.head = new ModelRenderer(this, 37, 13);
/* 35:32 */     this.head.addBox(0.0F, 1.0F, 0.0F, 4, 4, 5);
/* 36:33 */     this.head.setRotationPoint(-1.0F, 0.0F, -4.0F);
/* 37:34 */     this.head.rotateAngleX = 0.0F;
/* 38:35 */     this.head.rotateAngleY = 0.0F;
/* 39:36 */     this.head.rotateAngleZ = 0.0F;
/* 40:37 */     this.head.mirror = false;
/* 41:38 */     this.tail = new ModelRenderer(this, 56, 0);
/* 42:39 */     this.tail.addBox(0.0F, 0.0F, 0.0F, 2, 7, 2);
/* 43:40 */     this.tail.setRotationPoint(0.0F, 7.0F, 8.0F);
/* 44:41 */     this.tail.rotateAngleX = -0.88392F;
/* 45:42 */     this.tail.rotateAngleY = 0.0F;
/* 46:43 */     this.tail.rotateAngleZ = 0.0F;
/* 47:44 */     this.tail.mirror = false;
/* 48:45 */     this.leftWing1 = new ModelRenderer(this, 0, 21);
/* 49:46 */     this.leftWing1.addBox(0.0F, 0.0F, 0.0F, 7, 0, 11);
/* 50:47 */     this.leftWing1.setRotationPoint(-2.0F, 1.0F, 1.0F);
/* 51:48 */     this.leftWing1.rotateAngleX = 0.00227F;
/* 52:49 */     this.leftWing1.rotateAngleY = -1.02974F;
/* 53:50 */     this.leftWing1.rotateAngleZ = 0.0F;
/* 54:51 */     this.leftWing1.mirror = false;
/* 55:52 */     this.sendaryBody = new ModelRenderer(this, 29, 1);
/* 56:53 */     this.sendaryBody.addBox(0.0F, 0.0F, 0.0F, 4, 3, 7);
/* 57:54 */     this.sendaryBody.setRotationPoint(-1.0F, 1.0F, 8.0F);
/* 58:55 */     this.sendaryBody.rotateAngleX = -1.07818F;
/* 59:56 */     this.sendaryBody.rotateAngleY = 0.0F;
/* 60:57 */     this.sendaryBody.rotateAngleZ = 0.0F;
/* 61:58 */     this.sendaryBody.mirror = false;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 65:   */   {
/* 66:63 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 67:64 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 68:65 */     this.mainBody.render(f5);
/* 69:66 */     this.leftWing.render(f5);
/* 70:67 */     this.head.render(f5);
/* 71:68 */     this.tail.render(f5);
/* 72:69 */     this.leftWing1.render(f5);
/* 73:70 */     this.sendaryBody.render(f5);
/* 74:   */   }
/* 75:   */   
/* 76:   */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 77:   */   {
/* 78:75 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 79:76 */     this.leftWing.rotateAngleX = MathHelper.cos((float)(f / 1.919108F * 1.5707963267949D * f1 + 0.00227202624519969D));
/* 80:77 */     this.leftWing1.rotateAngleX = MathHelper.cos((float)(f / 1.919108F * 1.5707963267949D * f1 + 0.00227202624519969D));
/* 81:78 */     this.tail.rotateAngleX = MathHelper.cos((float)(f / 1.919108F * 0.523598775598299D * f1 + -0.883921483302927D));
/* 82:   */   }
/* 83:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.Modelbee
 * JD-Core Version:    0.7.1
 */