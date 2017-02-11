/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelRenderer;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ 
/*  6:   */ public class ModelGolemMecha
/*  7:   */   extends ModelHuman
/*  8:   */ {
/*  9:   */   public ModelRenderer antennaStick;
/* 10:   */   public ModelRenderer antenna;
/* 11:   */   
/* 12:   */   public ModelGolemMecha()
/* 13:   */   {
/* 14:20 */     this(0.0F);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public ModelGolemMecha(float f)
/* 18:   */   {
/* 19:25 */     super(f);
/* 20:   */     
/* 21:27 */     this.bipedBody = new ModelRenderer(this, 16, 22);
/* 22:28 */     this.bipedBody.addBox(-3.0F, 6.0F, -2.0F, 6, 6, 4, f);
/* 23:29 */     this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 24:   */     
/* 25:31 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/* 26:32 */     this.bipedHead.addBox(-6.0F, -8.0F, -7.0F, 12, 14, 12, 1.0F + f);
/* 27:33 */     this.bipedHead.setRotationPoint(0.0F, 2.0F, 0.0F);
/* 28:   */     
/* 29:   */ 
/* 30:36 */     this.bipedHeadwear = new ModelRenderer(this, 0, 0);
/* 31:37 */     this.bipedHeadwear.addBox(-3.0F, 6.0F, 1.0F, 6, 2, 2, f);
/* 32:38 */     this.bipedHeadwear.setRotationPoint(0.0F, 2.0F, 0.0F);
/* 33:   */     
/* 34:40 */     this.bipedRightArm = new ModelRenderer(this, 48, 16);
/* 35:41 */     this.bipedRightArm.addBox(-6.0F, -2.0F, -2.0F, 4, 12, 4, f);
/* 36:42 */     this.bipedRightArm.setRotationPoint(-8.0F, 2.0F, 0.0F);
/* 37:43 */     this.bipedLeftArm = new ModelRenderer(this, 48, 16);
/* 38:44 */     this.bipedLeftArm.mirror = true;
/* 39:45 */     this.bipedLeftArm.addBox(2.0F, -2.0F, -2.0F, 4, 12, 4, f);
/* 40:46 */     this.bipedLeftArm.setRotationPoint(8.0F, 2.0F, 0.0F);
/* 41:   */     
/* 42:48 */     this.bipedRightLeg = new ModelRenderer(this, 48, 0);
/* 43:49 */     this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
/* 44:50 */     this.bipedRightLeg.setRotationPoint(-3.0F, 12.0F + f, 0.0F);
/* 45:51 */     this.bipedLeftLeg = new ModelRenderer(this, 48, 0);
/* 46:52 */     this.bipedLeftLeg.mirror = true;
/* 47:53 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
/* 48:54 */     this.bipedLeftLeg.setRotationPoint(3.0F, 12.0F + f, 0.0F);
/* 49:   */     
/* 50:56 */     this.antenna = new ModelRenderer(this, 0, 4);
/* 51:57 */     this.antenna.addBox(-1.0F, -3.0F, 0.0F, 3, 3, 1, f);
/* 52:58 */     this.antenna.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 53:   */     
/* 54:60 */     this.antennaStick = new ModelRenderer(this, 36, 0);
/* 55:61 */     this.antennaStick.addBox(0.0F, 0.0F, 0.0F, 1, 10, 1, f);
/* 56:62 */     this.antennaStick.setRotationPoint(6.0F, -12.0F, 5.0F);
/* 57:63 */     this.antennaStick.addChild(this.antenna);
/* 58:   */   }
/* 59:   */   
/* 60:   */   public void render(Entity z, float f, float f1, float f2, float f3, float f4, float f5)
/* 61:   */   {
/* 62:68 */     super.render(z, f, f1, f2, f3, f4, f5);
/* 63:69 */     this.antennaStick.render(f5);
/* 64:   */   }
/* 65:   */   
/* 66:   */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 67:   */   {
/* 68:75 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 69:76 */     this.bipedHeadwear.rotateAngleX = -1.570796F;
/* 70:77 */     this.bipedHeadwear.rotateAngleY = 0.0F;
/* 71:   */     
/* 72:79 */     this.antenna.rotateAngleX = this.bipedHead.rotateAngleX;
/* 73:80 */     this.antenna.rotateAngleY = this.bipedHead.rotateAngleY;
/* 74:81 */     this.bipedHead.rotateAngleX = 0.0F;
/* 75:82 */     this.bipedHead.rotateAngleY = 0.0F;
/* 76:   */   }
/* 77:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelGolemMecha
 * JD-Core Version:    0.7.1
 */