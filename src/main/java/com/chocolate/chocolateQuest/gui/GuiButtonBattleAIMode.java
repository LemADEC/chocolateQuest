/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  6:   */ import net.minecraft.client.Minecraft;
/*  7:   */ import net.minecraft.client.gui.FontRenderer;
/*  8:   */ import net.minecraft.client.renderer.texture.TextureManager;
/*  9:   */ import org.lwjgl.opengl.GL11;
/* 10:   */ 
/* 11:   */ @SideOnly(Side.CLIENT)
/* 12:   */ public class GuiButtonBattleAIMode
/* 13:   */   extends GuiButtonAIMode
/* 14:   */ {
/* 15:   */   public GuiButtonBattleAIMode(int id, int posX, int posY, int width, int height, String[] par5Str, FontRenderer font, int value)
/* 16:   */   {
/* 17:18 */     super(id, posX, posY, width, height, par5Str, font, value);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void drawButton(Minecraft par1Minecraft, int par2, int par3)
/* 21:   */   {
/* 22:23 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 23:24 */     Minecraft.getMinecraft().renderEngine.bindTexture(BDHelper.guiButtonsTexture);
/* 24:   */     
/* 25:26 */     int buttonHeight = this.height / this.modeNames.length;
/* 26:27 */     for (int i = 0; i < this.modeNames.length; i++)
/* 27:   */     {
/* 28:28 */       int y = this.yPosition + buttonHeight * i;
/* 29:29 */       drawTexturedRect(this.xPosition, y, 5, i, this.width, buttonHeight, 1, 1);
/* 30:   */     }
/* 31:31 */     drawTexturedRect(this.xPosition, this.yPosition + buttonHeight * this.selectedMode, 4, 1, this.width, buttonHeight, 1, 1);
/* 32:   */   }
/* 33:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiButtonBattleAIMode
 * JD-Core Version:    0.7.1
 */