/*  1:   */ package com.chocolate.chocolateQuest.client.model;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  4:   */ import net.minecraft.client.Minecraft;
/*  5:   */ import net.minecraft.client.renderer.texture.TextureManager;
/*  6:   */ import net.minecraft.entity.Entity;
/*  7:   */ import net.minecraft.item.Item;
/*  8:   */ import net.minecraft.item.ItemStack;
/*  9:   */ import net.minecraft.util.ResourceLocation;
/* 10:   */ import org.lwjgl.opengl.GL11;
/* 11:   */ 
/* 12:   */ public class ModelArmorColored
/* 13:   */   extends ModelArmor
/* 14:   */ {
/* 15:22 */   ResourceLocation layer2 = new ResourceLocation("textures/models/armor/leather_layer_2.png");
/* 16:23 */   ResourceLocation layer1 = new ResourceLocation("textures/models/armor/leather_layer_1.png");
/* 17:   */   
/* 18:   */   public ModelArmorColored(int type)
/* 19:   */   {
/* 20:26 */     super(type);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public ModelArmorColored(float f, int type)
/* 24:   */   {
/* 25:31 */     super(f, type);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void render(Entity e, float par2, float par3, float par4, float par5, float par6, float par7)
/* 29:   */   {
/* 30:35 */     if (this.cachedItem != null)
/* 31:   */     {
/* 32:36 */       setRotationAngles(par2, par3, par4, par5, par6, par7, e);
/* 33:37 */       if ((e != null) && 
/* 34:38 */         (e.hurtResistantTime > 0)) {
/* 35:39 */         GL11.glEnable(3553);
/* 36:   */       }
/* 37:41 */       if (this.type == 2) {
/* 38:42 */         Minecraft.getMinecraft().renderEngine.bindTexture(this.layer2);
/* 39:   */       } else {
/* 40:44 */         Minecraft.getMinecraft().renderEngine.bindTexture(this.layer1);
/* 41:   */       }
/* 42:45 */       setArmorColor(this.cachedItem);
/* 43:46 */       super.renderArmor(e, par2, par3, par4, par5, par6, par7);
/* 44:   */       
/* 45:48 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 46:49 */       Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(this.cachedItem.getItem().getArmorTexture(this.cachedItem, e, this.type, "")));
/* 47:50 */       super.renderArmor(e, par2, par3, par4, par5, par6, par7);
/* 48:51 */       this.cachedItem = null;
/* 49:   */     }
/* 50:   */     else
/* 51:   */     {
/* 52:53 */       super.renderArmor(e, par2, par3, par4, par5, par6, par7);
/* 53:   */     }
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void setArmorColor(ItemStack is)
/* 57:   */   {
/* 58:58 */     int color = is.getItem().getColorFromItemStack(this.cachedItem, 0);
/* 59:59 */     GL11.glColor4f(BDHelper.getColorRed(color), BDHelper.getColorGreen(color), BDHelper.getColorBlue(color), 1.0F);
/* 60:   */   }
/* 61:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.model.ModelArmorColored
 * JD-Core Version:    0.7.1
 */