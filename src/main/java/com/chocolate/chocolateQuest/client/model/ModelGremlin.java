/*   1:    */ package com.chocolate.chocolateQuest.client.model;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import net.minecraft.client.model.ModelRenderer;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ 
/*   7:    */ public class ModelGremlin
/*   8:    */   extends ModelHuman
/*   9:    */ {
/*  10:    */   public ModelRenderer wingl;
/*  11:    */   public ModelRenderer wingr;
/*  12:    */   public ModelRenderer tail;
/*  13:    */   
/*  14:    */   public ModelGremlin()
/*  15:    */   {
/*  16: 26 */     this(0.0F);
/*  17:    */   }
/*  18:    */   
/*  19:    */   public ModelGremlin(float f)
/*  20:    */   {
/*  21: 32 */     super(f);
/*  22:    */     
/*  23: 34 */     this.bipedRightLeg = new ModelRenderer(this, 0, 22);
/*  24: 35 */     this.bipedRightLeg.addBox(0.0F, 0.0F, -2.0F, 4, 6, 4, f);
/*  25: 36 */     this.bipedRightLeg.setRotationPoint(0.0F, 0.0F, -2.0F);
/*  26:    */     
/*  27: 38 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 22);
/*  28: 39 */     this.bipedLeftLeg.mirror = true;
/*  29: 40 */     this.bipedLeftLeg.addBox(-8.0F, 0.0F, -2.0F, 4, 6, 4, f);
/*  30: 41 */     this.bipedLeftLeg.setRotationPoint(2.9F, 12.0F, 6.0F);
/*  31:    */     
/*  32: 43 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  33: 44 */     this.bipedHead.addBox(-4.0F, -5.0F, -7.0F, 8, 8, 8, f);
/*  34: 45 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  35: 46 */     this.bipedHeadwear = new ModelRenderer(this, 32, 0);
/*  36: 47 */     this.bipedHeadwear.addBox(-4.0F, -5.0F, -7.0F, 8, 8, 8, f + 0.5F);
/*  37: 48 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  38:    */     
/*  39:    */ 
/*  40:    */ 
/*  41: 52 */     this.wingl = new ModelRenderer(this, 0, 21);
/*  42: 53 */     this.wingl.addBox(0.0F, -2.0F, 0.0F, 0, 5, 12, f);
/*  43: 54 */     this.wingl.setRotationPoint(-2.0F, 0.0F, 3.0F);
/*  44:    */     
/*  45: 56 */     this.wingr = new ModelRenderer(this, 0, 21);
/*  46: 57 */     this.wingr.addBox(0.0F, -2.0F, 0.0F, 0, 5, 12, f);
/*  47: 58 */     this.wingr.setRotationPoint(2.0F, 0.0F, 3.0F);
/*  48:    */     
/*  49:    */ 
/*  50: 61 */     this.tail = new ModelRenderer(this, 1, 17);
/*  51: 62 */     this.tail.addBox(-4.0F, 0.0F, -2.0F, 4, 3, 3);
/*  52: 63 */     this.tail.setRotationPoint(0.0F, -3.0F, 14.0F);
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*  56:    */   {
/*  57: 70 */     super.render(par1Entity, par2, par3, par4, par5, par6, par7);
/*  58:    */     
/*  59: 72 */     float rot = (float)(Math.sin(System.nanoTime() / 4L) * 0.3D);
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*  63:    */   {
/*  64:101 */     super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/*  65:102 */     EntityHumanBase e = (EntityHumanBase)entity;
/*  66:104 */     if (e.isSitting())
/*  67:    */     {
/*  68:106 */       this.bipedRightArm.rotateAngleX += 1.1F;
/*  69:107 */       this.bipedLeftArm.rotateAngleX += 1.1F;
/*  70:    */     }
/*  71:109 */     if (e.isTwoHanded())
/*  72:    */     {
/*  73:111 */       this.bipedRightArm.rotateAngleX += 0.5F;
/*  74:112 */       this.bipedLeftArm.rotateAngleX += 0.5F;
/*  75:    */     }
/*  76:114 */     else if (!e.isAiming())
/*  77:    */     {
/*  78:116 */       this.bipedRightArm.rotateAngleX -= 0.7F;
/*  79:117 */       this.bipedLeftArm.rotateAngleX -= 0.7F;
/*  80:    */     }
/*  81:120 */     if (e.isDefending()) {
/*  82:122 */       setShiedRotation(f2);
/*  83:    */     }
/*  84:126 */     this.bipedRightLeg.setRotationPoint(2.0F, 7.0F, 8.0F);
/*  85:127 */     this.bipedLeftLeg.setRotationPoint(2.0F, 7.0F, 8.0F);
/*  86:128 */     this.bipedBody.rotateAngleX = 0.7F;
/*  87:    */   }
/*  88:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelGremlin
 * JD-Core Version:    0.7.1
 */