/*   1:    */ package com.chocolate.chocolateQuest.client.model;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.boss.EntityPart;
/*   4:    */ import com.chocolate.chocolateQuest.entity.boss.EntityTurtle;
/*   5:    */ import com.chocolate.chocolateQuest.entity.boss.EntityTurtlePart;
/*   6:    */ import net.minecraft.client.model.ModelBase;
/*   7:    */ import net.minecraft.client.model.ModelRenderer;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.util.MathHelper;
/*  10:    */ 
/*  11:    */ public class ModelTurtle
/*  12:    */   extends ModelBase
/*  13:    */ {
/*  14:    */   ModelRenderer Body;
/*  15:    */   ModelRenderer Shape1;
/*  16:    */   ModelRenderer Shape2;
/*  17:    */   ModelRenderer Shape3;
/*  18:    */   ModelRenderer Shape4;
/*  19:    */   ModelRenderer Shape5;
/*  20:    */   ModelRenderer leg;
/*  21:    */   ModelRenderer head;
/*  22:    */   
/*  23:    */   public ModelTurtle()
/*  24:    */   {
/*  25: 31 */     this.textureWidth = 64;
/*  26: 32 */     this.textureHeight = 32;
/*  27: 33 */     float y = 23.0F;
/*  28: 34 */     this.Body = new ModelRenderer(this, 0, 0);
/*  29: 35 */     this.Body.addBox(-8.0F, -6.0F + y, -8.0F, 16, 7, 16);
/*  30: 36 */     this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  31: 37 */     this.Body.setTextureSize(64, 32);
/*  32: 38 */     this.Body.mirror = true;
/*  33: 39 */     this.Shape1 = new ModelRenderer(this, 18, 2);
/*  34: 40 */     this.Shape1.addBox(1.0F, -8.0F + y, 0.0F, 5, 1, 6);
/*  35: 41 */     this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  36: 42 */     this.Shape1.setTextureSize(64, 32);
/*  37: 43 */     this.Shape1.mirror = true;
/*  38: 44 */     this.Shape2 = new ModelRenderer(this, 18, 9);
/*  39: 45 */     this.Shape2.addBox(0.0F, -8.0F + y, -6.0F, 6, 1, 5);
/*  40: 46 */     this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  41: 47 */     this.Shape2.setTextureSize(64, 32);
/*  42: 48 */     this.Shape2.mirror = true;
/*  43: 49 */     this.Shape3 = new ModelRenderer(this, 13, 2);
/*  44: 50 */     this.Shape3.addBox(-6.0F, -8.0F + y, 1.0F, 6, 1, 5);
/*  45: 51 */     this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  46: 52 */     this.Shape3.setTextureSize(64, 32);
/*  47: 53 */     this.Shape3.mirror = true;
/*  48: 54 */     this.Shape4 = new ModelRenderer(this, 12, 7);
/*  49: 55 */     this.Shape4.addBox(-6.0F, -8.0F + y, -6.0F, 5, 1, 6);
/*  50: 56 */     this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  51: 57 */     this.Shape4.setTextureSize(64, 32);
/*  52: 58 */     this.Shape4.mirror = true;
/*  53: 59 */     this.Shape5 = new ModelRenderer(this, 2, 1);
/*  54: 60 */     this.Shape5.addBox(-7.0F, -7.0F + y, -7.0F, 14, 1, 14);
/*  55: 61 */     this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  56: 62 */     this.Shape5.setTextureSize(64, 32);
/*  57: 63 */     this.Shape5.mirror = true;
/*  58: 64 */     this.leg = new ModelRenderer(this, 0, 24);
/*  59: 65 */     this.leg.addBox(-2.0F, 1.0F + y, -2.0F, 4, 4, 4);
/*  60: 66 */     this.leg.setRotationPoint(0.0F, -4.0F, 0.0F);
/*  61: 67 */     this.leg.setTextureSize(64, 32);
/*  62: 68 */     this.leg.mirror = true;
/*  63: 69 */     this.head = new ModelRenderer(this, 0, 0);
/*  64: 70 */     this.head.addBox(-2.0F, 0.0F + y, -2.0F, 4, 4, 4);
/*  65: 71 */     this.head.setRotationPoint(0.0F, -4.0F, 0.0F);
/*  66: 72 */     this.head.setTextureSize(64, 32);
/*  67: 73 */     this.head.mirror = true;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*  71:    */   {
/*  72: 78 */     super.render(entity, f, f1, f2, f3, f4, f5);
/*  73: 79 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/*  74: 80 */     this.Body.render(f5);
/*  75: 81 */     this.Shape1.render(f5);
/*  76: 82 */     this.Shape2.render(f5);
/*  77: 83 */     this.Shape3.render(f5);
/*  78: 84 */     this.Shape4.render(f5);
/*  79: 85 */     this.Shape5.render(f5);
/*  80: 86 */     EntityTurtle turtle = (EntityTurtle)entity;
/*  81: 87 */     EntityTurtlePart[] parts = ((EntityTurtle)entity).getBossParts();
/*  82: 88 */     float scale = turtle.getScaleSize();
/*  83: 89 */     if ((!turtle.isAttacking()) && (!turtle.isHealing())) {
/*  84: 90 */       for (EntityTurtlePart part : parts) {
/*  85: 91 */         if (part != null) {
/*  86: 92 */           if (part.isHead())
/*  87:    */           {
/*  88: 93 */             this.head.offsetZ = (-part.distanceToMainBody / scale);
/*  89: 94 */             this.head.rotateAngleY = (-(turtle.rotationYaw - turtle.prevRotationYaw) * 3.141592F / 18.0F * f5);
/*  90: 95 */             this.head.render(f5);
/*  91:    */           }
/*  92:    */           else
/*  93:    */           {
/*  94: 98 */             float hx = -MathHelper.sin(part.rotationYawOffset * 3.141592F / 180.0F) * part.distanceToMainBody / scale;
/*  95: 99 */             float hz = -MathHelper.cos(part.rotationYawOffset * 3.141592F / 180.0F) * part.distanceToMainBody / scale;
/*  96:100 */             this.leg.offsetX = hx;
/*  97:101 */             this.leg.offsetZ = hz;
/*  98:102 */             this.leg.render(f5);
/*  99:    */           }
/* 100:    */         }
/* 101:    */       }
/* 102:    */     }
/* 103:    */   }
/* 104:    */   
/* 105:    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 106:    */   {
/* 107:111 */     model.rotateAngleX = x;
/* 108:112 */     model.rotateAngleY = y;
/* 109:113 */     model.rotateAngleZ = z;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 113:    */   {
/* 114:118 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 115:    */   }
/* 116:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelTurtle
 * JD-Core Version:    0.7.1
 */