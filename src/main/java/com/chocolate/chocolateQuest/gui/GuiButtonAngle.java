/*   1:    */ package com.chocolate.chocolateQuest.gui;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   4:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   5:    */ import cpw.mods.fml.relauncher.Side;
/*   6:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   7:    */ import net.minecraft.client.Minecraft;
/*   8:    */ import net.minecraft.client.gui.GuiButton;
/*   9:    */ import net.minecraft.client.renderer.Tessellator;
/*  10:    */ import net.minecraft.client.renderer.texture.TextureManager;
/*  11:    */ import org.lwjgl.opengl.GL11;
/*  12:    */ 
/*  13:    */ @SideOnly(Side.CLIENT)
/*  14:    */ public class GuiButtonAngle
/*  15:    */   extends GuiButton
/*  16:    */ {
/*  17:    */   public boolean dragging;
/*  18: 21 */   double xPoint = 0.0D;
/*  19: 22 */   double yPoint = 0.0D;
/*  20:    */   EntityHumanBase human;
/*  21: 24 */   final int maxRadio = 10;
/*  22:    */   
/*  23:    */   public GuiButtonAngle(int par1, int par2, int par3, String par5Str, float par6, EntityHumanBase human)
/*  24:    */   {
/*  25: 27 */     super(par1, par2, par3, 80, 80, par5Str);
/*  26: 28 */     this.displayString = "";
/*  27: 29 */     this.human = human;
/*  28: 30 */     setCoords(human.partyPositionAngle);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void drawButton(Minecraft par1Minecraft, int par2, int par3)
/*  32:    */   {
/*  33: 35 */     super.drawButton(par1Minecraft, par2, par3);
/*  34: 36 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  35: 37 */     Minecraft.getMinecraft().renderEngine.bindTexture(BDHelper.guiButtonsTexture);
/*  36: 38 */     drawTexturedRect(this.xPosition, this.yPosition, 0, 0, this.width, 4);
/*  37:    */     
/*  38: 40 */     int radius = this.width / 2;
/*  39: 41 */     int iconSize = (int)(radius * 0.2D);
/*  40: 42 */     radius -= iconSize / 2;
/*  41: 43 */     double x = this.xPoint;
/*  42: 44 */     double y = this.yPoint;
/*  43: 45 */     x = this.xPosition + radius - x * radius / 10.0D;
/*  44: 46 */     y = this.yPosition + radius - y * radius / 10.0D;
/*  45: 47 */     drawTexturedRect((int)x, (int)y, 4, 0, iconSize);
/*  46:    */   }
/*  47:    */   
/*  48:    */   public int getAngle()
/*  49:    */   {
/*  50: 52 */     return (int)(Math.atan2(this.yPoint, this.xPoint) * 180.0D / 3.141592653589793D - 90.0D);
/*  51:    */   }
/*  52:    */   
/*  53:    */   public int getDistance()
/*  54:    */   {
/*  55: 56 */     return (int)Math.max(1.0D, Math.sqrt(this.xPoint * this.xPoint + this.yPoint * this.yPoint));
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void setCoords(int angle)
/*  59:    */   {
/*  60: 61 */     angle += 90;
/*  61: 62 */     double fangle = angle * 3.141592653589793D / 180.0D;
/*  62: 63 */     int dist = this.human.partyDistanceToLeader;
/*  63: 64 */     this.xPoint = (Math.cos(fangle) * dist);
/*  64: 65 */     this.yPoint = (Math.sin(fangle) * dist);
/*  65:    */   }
/*  66:    */   
/*  67:    */   protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3)
/*  68:    */   {
/*  69: 72 */     this.displayString = "";
/*  70: 73 */     int radius = this.width / 2;
/*  71: 76 */     if (this.dragging)
/*  72:    */     {
/*  73: 78 */       float x = (this.xPosition + radius - par2) * 10.0F / radius;
/*  74: 79 */       float y = (this.yPosition + radius - par3) * 10.0F / radius;
/*  75: 80 */       this.xPoint = ((int)Math.min(10.0F, Math.max(-10.0F, x)));
/*  76: 81 */       this.yPoint = ((int)Math.min(10.0F, Math.max(-10.0F, y)));
/*  77:    */     }
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void drawTexturedRect(int x, int y, int indexX, int indexZ, int size, int iconSize)
/*  81:    */   {
/*  82: 88 */     float f = 0.0625F;
/*  83: 89 */     Tessellator tessellator = Tessellator.instance;
/*  84: 90 */     tessellator.startDrawingQuads();
/*  85: 91 */     tessellator.addVertexWithUV(x, y + size, this.zLevel, (indexX + 0) * f, (indexZ + iconSize) * f);
/*  86: 92 */     tessellator.addVertexWithUV(x + size, y + size, this.zLevel, (indexX + iconSize) * f, (indexZ + iconSize) * f);
/*  87: 93 */     tessellator.addVertexWithUV(x + size, y + 0, this.zLevel, (indexX + iconSize) * f, (indexZ + 0) * f);
/*  88: 94 */     tessellator.addVertexWithUV(x + 0, y + 0, this.zLevel, (indexX + 0) * f, (indexZ + 0) * f);
/*  89: 95 */     tessellator.draw();
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void drawTexturedRect(int x, int y, int indexX, int indexZ, int size)
/*  93:    */   {
/*  94: 99 */     drawTexturedRect(x, y, indexX, indexZ, size, 1);
/*  95:    */   }
/*  96:    */   
/*  97:    */   public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
/*  98:    */   {
/*  99:108 */     if (super.mousePressed(par1Minecraft, par2, par3))
/* 100:    */     {
/* 101:110 */       this.dragging = (!this.dragging);
/* 102:111 */       return true;
/* 103:    */     }
/* 104:115 */     return false;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void mouseReleased(int par1, int par2)
/* 108:    */   {
/* 109:124 */     this.dragging = false;
/* 110:    */   }
/* 111:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiButtonAngle
 * JD-Core Version:    0.7.1
 */