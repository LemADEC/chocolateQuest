/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelRenderer;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ 
/*  6:   */ public class ModelArmorTurtle
/*  7:   */   extends ModelArmor
/*  8:   */ {
/*  9:   */   public ModelRenderer turtleShell;
/* 10:   */   public ModelRenderer turtleShellPart;
/* 11:   */   
/* 12:   */   public ModelArmorTurtle(int type)
/* 13:   */   {
/* 14:21 */     this(0.0F, type);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public ModelArmorTurtle(float f, int type)
/* 18:   */   {
/* 19:27 */     super(f, type);
/* 20:28 */     this.type = type;
/* 21:   */     
/* 22:30 */     f = 1.5F;
/* 23:31 */     this.turtleShell = new ModelRenderer(this, 32, 0);
/* 24:32 */     this.turtleShell.addBox(0.0F, 6.0F, 2.0F, 10, 12, 3, f);
/* 25:33 */     this.turtleShell.setRotationPoint(-5.0F, -6.0F, 0.0F);
/* 26:   */     
/* 27:35 */     this.turtleShellPart = new ModelRenderer(this, 42, 4);
/* 28:36 */     this.turtleShellPart.addBox(0.0F, 6.0F, 2.0F, 6, 8, 1, f);
/* 29:37 */     this.turtleShellPart.setRotationPoint(-3.0F, -4.0F, 3.0F);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/* 33:   */   {
/* 34:41 */     setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
/* 35:42 */     if (this.type == 0)
/* 36:   */     {
/* 37:44 */       this.bipedBody.render(par7);
/* 38:45 */       this.bipedRightArm.render(par7);
/* 39:46 */       this.bipedLeftArm.render(par7);
/* 40:   */       
/* 41:48 */       this.turtleShell.render(par7);
/* 42:49 */       this.turtleShellPart.render(par7);
/* 43:   */     }
/* 44:   */     else
/* 45:   */     {
/* 46:53 */       this.bipedHead.render(par7);
/* 47:   */     }
/* 48:   */   }
/* 49:   */   
/* 50:   */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 51:   */   {
/* 52:60 */     model.rotateAngleX = x;
/* 53:61 */     model.rotateAngleY = y;
/* 54:62 */     model.rotateAngleZ = z;
/* 55:   */   }
/* 56:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelArmorTurtle
 * JD-Core Version:    0.7.1
 */