/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  6:   */ import net.minecraft.client.Minecraft;
/*  7:   */ import net.minecraft.client.gui.FontRenderer;
/*  8:   */ import net.minecraft.client.gui.GuiButton;
/*  9:   */ import net.minecraft.client.renderer.Tessellator;
/* 10:   */ import net.minecraft.client.renderer.texture.TextureManager;
/* 11:   */ import org.lwjgl.opengl.GL11;
/* 12:   */ 
/* 13:   */ @SideOnly(Side.CLIENT)
/* 14:   */ public class GuiButtonAIMode
/* 15:   */   extends GuiButton
/* 16:   */ {
/* 17:   */   FontRenderer font;
/* 18:19 */   int selectedMode = 0;
/* 19:   */   String[] modeNames;
/* 20:   */   
/* 21:   */   public GuiButtonAIMode(int id, int posX, int posY, int width, int height, String[] par5Str, FontRenderer font, int value)
/* 22:   */   {
/* 23:23 */     super(id, posX, posY, width, height, "");
/* 24:24 */     this.displayString = "";
/* 25:25 */     this.font = font;
/* 26:26 */     this.modeNames = par5Str;
/* 27:27 */     this.selectedMode = value;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void drawButton(Minecraft par1Minecraft, int par2, int par3)
/* 31:   */   {
/* 32:32 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 33:33 */     Minecraft.getMinecraft().renderEngine.bindTexture(BDHelper.guiButtonsTexture);
/* 34:   */     
/* 35:35 */     int buttonHeight = this.height / this.modeNames.length;
/* 36:36 */     for (int i = 0; i < this.modeNames.length; i++)
/* 37:   */     {
/* 38:37 */       int y = this.yPosition + buttonHeight * i;
/* 39:38 */       drawTexturedRect(this.xPosition, y, 6, 0, this.width, buttonHeight, 3, 1);
/* 40:   */     }
/* 41:40 */     drawTexturedRect(this.xPosition, this.yPosition + buttonHeight * this.selectedMode, 6, 1, this.width, buttonHeight, 3, 1);
/* 42:41 */     for (int i = 0; i < this.modeNames.length; i++)
/* 43:   */     {
/* 44:42 */       int y = this.yPosition + buttonHeight * i;
/* 45:43 */       drawString(this.font, this.modeNames[i], this.xPosition + 5, y + buttonHeight / 2 - 5, 16777215);
/* 46:   */     }
/* 47:   */   }
/* 48:   */   
/* 49:   */   public void drawTexturedRect(int x, int y, int indexX, int indexZ, int size, int height, int iconWidth, int iconHeight)
/* 50:   */   {
/* 51:49 */     float f = 0.0625F;
/* 52:50 */     Tessellator tessellator = Tessellator.instance;
/* 53:51 */     tessellator.startDrawingQuads();
/* 54:52 */     tessellator.addVertexWithUV(x, y + height, this.zLevel, (indexX + 0) * f, (indexZ + iconHeight) * f);
/* 55:53 */     tessellator.addVertexWithUV(x + size, y + height, this.zLevel, (indexX + iconWidth) * f, (indexZ + iconHeight) * f);
/* 56:54 */     tessellator.addVertexWithUV(x + size, y + 0, this.zLevel, (indexX + iconWidth) * f, (indexZ + 0) * f);
/* 57:55 */     tessellator.addVertexWithUV(x + 0, y + 0, this.zLevel, (indexX + 0) * f, (indexZ + 0) * f);
/* 58:56 */     tessellator.draw();
/* 59:   */   }
/* 60:   */   
/* 61:   */   public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
/* 62:   */   {
/* 63:65 */     if (super.mousePressed(par1Minecraft, par2, par3))
/* 64:   */     {
/* 65:67 */       this.selectedMode = ((par3 - this.yPosition) * this.modeNames.length / this.height);
/* 66:68 */       return true;
/* 67:   */     }
/* 68:72 */     return false;
/* 69:   */   }
/* 70:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiButtonAIMode
 * JD-Core Version:    0.7.1
 */