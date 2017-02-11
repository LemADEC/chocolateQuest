/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  4:   */ import net.minecraft.client.model.ModelRenderer;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.entity.EntityLivingBase;
/*  7:   */ import net.minecraft.item.Item;
/*  8:   */ import net.minecraft.item.ItemStack;
/*  9:   */ import org.lwjgl.opengl.GL11;
/* 10:   */ 
/* 11:   */ public class ModelArmorMageRobe
/* 12:   */   extends ModelArmor
/* 13:   */ {
/* 14:   */   public ModelArmorMageRobe()
/* 15:   */   {
/* 16:25 */     this(0.0F);
/* 17:   */   }
/* 18:   */   
/* 19:29 */   int count = 0;
/* 20:   */   
/* 21:   */   public ModelArmor setItem(ItemStack is)
/* 22:   */   {
/* 23:32 */     this.count = 0;
/* 24:33 */     return super.setItem(is);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public ModelArmorMageRobe(float f)
/* 28:   */   {
/* 29:37 */     super(f, 1);
/* 30:38 */     this.bipedBody = new ModelRenderer(this);
/* 31:39 */     this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 32:   */     
/* 33:41 */     this.bipedBody.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.8F);
/* 34:   */     
/* 35:43 */     this.bipedHead = new ModelRenderer(this);
/* 36:44 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 37:45 */     this.bipedHead.setTextureOffset(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 1.0F);
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void render(Entity e, float par2, float par3, float par4, float par5, float par6, float par7)
/* 41:   */   {
/* 42:49 */     if (this.cachedItem != null)
/* 43:   */     {
/* 44:50 */       setRotationAngles(par2, par3, par4, par5, par6, par7, e);
/* 45:   */       
/* 46:52 */       int color = this.cachedItem.getItem().getColorFromItemStack(this.cachedItem, 0);
/* 47:53 */       if ((e != null) && 
/* 48:54 */         (e.hurtResistantTime > 0)) {
/* 49:55 */         GL11.glEnable(3553);
/* 50:   */       }
/* 51:57 */       GL11.glColor4f(BDHelper.getColorRed(color), BDHelper.getColorGreen(color), BDHelper.getColorBlue(color), 1.0F);
/* 52:58 */       if ((!(e instanceof EntityLivingBase)) || (((EntityLivingBase)e).getEquipmentInSlot(4) == null)) {
/* 53:59 */         this.bipedHead.render(par7);
/* 54:   */       }
/* 55:60 */       this.bipedBody.render(par7);
/* 56:61 */       this.count += 1;
/* 57:62 */       if (this.count > 1) {
/* 58:63 */         this.cachedItem = null;
/* 59:   */       }
/* 60:   */     }
/* 61:   */     else
/* 62:   */     {
/* 63:66 */       setRotationAngles(par2, par3, par4, par5, par6, par7, e);
/* 64:67 */       if ((!(e instanceof EntityLivingBase)) || (((EntityLivingBase)e).getEquipmentInSlot(4) == null)) {
/* 65:68 */         this.bipedHead.render(par7);
/* 66:   */       }
/* 67:69 */       this.bipedBody.render(par7);
/* 68:   */     }
/* 69:   */   }
/* 70:   */   
/* 71:   */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 72:   */   {
/* 73:75 */     model.rotateAngleX = x;
/* 74:76 */     model.rotateAngleY = y;
/* 75:77 */     model.rotateAngleZ = z;
/* 76:   */   }
/* 77:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelArmorMageRobe
 * JD-Core Version:    0.7.1
 */