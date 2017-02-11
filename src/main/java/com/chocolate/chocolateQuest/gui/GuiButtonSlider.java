/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.Minecraft;
/*  6:   */ import net.minecraft.client.gui.GuiButton;
/*  7:   */ import org.lwjgl.opengl.GL11;
/*  8:   */ 
/*  9:   */ @SideOnly(Side.CLIENT)
/* 10:   */ public class GuiButtonSlider
/* 11:   */   extends GuiButton
/* 12:   */ {
/* 13:14 */   int SLIDER_MAX_VALUE = 10;
/* 14:16 */   public float sliderValue = 1.0F;
/* 15:   */   public boolean dragging;
/* 16:   */   String name;
/* 17:   */   
/* 18:   */   public GuiButtonSlider(int par1, int par2, int par3, String par5Str, float par6)
/* 19:   */   {
/* 20:24 */     super(par1, par2, par3, 108, 20, par5Str);
/* 21:25 */     this.sliderValue = par6;
/* 22:26 */     this.name = par5Str;
/* 23:27 */     this.displayString = (this.name + ": " + this.sliderValue * this.SLIDER_MAX_VALUE);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public GuiButtonSlider(int par1, int par2, int par3, String par5Str, float par6, int maxValue)
/* 27:   */   {
/* 28:32 */     this(par1, par2, par3, par5Str, par6);
/* 29:33 */     this.SLIDER_MAX_VALUE = maxValue;
/* 30:   */   }
/* 31:   */   
/* 32:   */   protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3)
/* 33:   */   {
/* 34:41 */     if (this.enabled)
/* 35:   */     {
/* 36:43 */       if (this.dragging)
/* 37:   */       {
/* 38:45 */         this.sliderValue = ((par2 - (this.xPosition + 4)) / (this.width - 8));
/* 39:47 */         if (this.sliderValue < 0.01F) {
/* 40:49 */           this.sliderValue = 0.01F;
/* 41:   */         }
/* 42:52 */         if (this.sliderValue > 1.0F) {
/* 43:54 */           this.sliderValue = 1.0F;
/* 44:   */         }
/* 45:57 */         this.displayString = (this.name + ": " + this.sliderValue * this.SLIDER_MAX_VALUE);
/* 46:   */       }
/* 47:60 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 48:61 */       drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)), this.yPosition, 0, 66, 4, 20);
/* 49:62 */       drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
/* 50:   */     }
/* 51:   */   }
/* 52:   */   
/* 53:   */   public boolean mousePressed(Minecraft par1Minecraft, int x, int y)
/* 54:   */   {
/* 55:72 */     if (super.mousePressed(par1Minecraft, x, y))
/* 56:   */     {
/* 57:74 */       this.sliderValue = ((x - (this.xPosition + 4)) / (this.width - 8));
/* 58:76 */       if (this.sliderValue < 0.0F) {
/* 59:78 */         this.sliderValue = 0.0F;
/* 60:   */       }
/* 61:81 */       if (this.sliderValue > 1.0F) {
/* 62:83 */         this.sliderValue = 1.0F;
/* 63:   */       }
/* 64:85 */       this.dragging = (!this.dragging);
/* 65:86 */       return true;
/* 66:   */     }
/* 67:90 */     return false;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public void mouseReleased(int x, int y)
/* 71:   */   {
/* 72:96 */     this.dragging = false;
/* 73:97 */     super.mouseReleased(x, y);
/* 74:   */   }
/* 75:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiButtonSlider
 * JD-Core Version:    0.7.1
 */