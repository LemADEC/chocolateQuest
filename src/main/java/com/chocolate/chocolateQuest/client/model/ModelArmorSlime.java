/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.model.ModelRenderer;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import org.lwjgl.opengl.GL11;
/*  6:   */ 
/*  7:   */ public class ModelArmorSlime
/*  8:   */   extends ModelArmor
/*  9:   */ {
/* 10:   */   ModelRenderer bipedBodyPants;
/* 11:   */   ModelRenderer bipedRightLegPants;
/* 12:   */   ModelRenderer bipedLeftLegPants;
/* 13:   */   
/* 14:   */   public ModelArmorSlime(int type)
/* 15:   */   {
/* 16:21 */     this(1.0F, type);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public ModelArmorSlime(float f, int type)
/* 20:   */   {
/* 21:27 */     super(f, type);
/* 22:28 */     this.type = type;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/* 26:   */   {
/* 27:32 */     setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
/* 28:33 */     GL11.glEnable(3042);
/* 29:34 */     GL11.glBlendFunc(770, 771);
/* 30:35 */     if (this.type == 0)
/* 31:   */     {
/* 32:37 */       this.bipedHead.render(par7);
/* 33:   */     }
/* 34:39 */     else if (this.type == 1)
/* 35:   */     {
/* 36:41 */       this.bipedBody.render(par7);
/* 37:42 */       this.bipedRightArm.render(par7);
/* 38:43 */       this.bipedLeftArm.render(par7);
/* 39:   */     }
/* 40:45 */     else if (this.type == 2)
/* 41:   */     {
/* 42:47 */       this.bipedBody.render(par7);
/* 43:48 */       this.bipedRightLeg.render(par7);
/* 44:49 */       this.bipedLeftLeg.render(par7);
/* 45:   */     }
/* 46:51 */     else if (this.type == 3)
/* 47:   */     {
/* 48:53 */       this.bipedRightLeg.render(par7);
/* 49:54 */       this.bipedLeftLeg.render(par7);
/* 50:   */     }
/* 51:56 */     GL11.glDisable(3042);
/* 52:   */   }
/* 53:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelArmorSlime
 * JD-Core Version:    0.7.1
 */