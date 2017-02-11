/*   1:    */ package com.chocolate.chocolateQuest.client.rendererHuman;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMecha;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.client.model.ModelBiped;
/*   6:    */ import net.minecraft.client.renderer.Tessellator;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.entity.EntityLivingBase;
/*   9:    */ import net.minecraft.util.ResourceLocation;
/*  10:    */ import org.lwjgl.opengl.GL11;
/*  11:    */ import org.lwjgl.util.glu.Sphere;
/*  12:    */ 
/*  13:    */ public class RenderHumanMecha
/*  14:    */   extends RenderHuman
/*  15:    */ {
/*  16: 29 */   Sphere bubble = new Sphere();
/*  17: 30 */   int divisions = 15;
/*  18:    */   
/*  19:    */   public RenderHumanMecha(ModelBiped model, float f, ResourceLocation r)
/*  20:    */   {
/*  21: 33 */     super(model, f, r);
/*  22:    */   }
/*  23:    */   
/*  24:    */   public void doLeftHandRotationForGolemWeapon()
/*  25:    */   {
/*  26: 38 */     GL11.glTranslatef(0.6F, -0.2F, -1.05F);
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void doRender(EntityLivingBase entityliving, double x, double y, double z, float par8, float par9)
/*  30:    */   {
/*  31: 42 */     super.doRender(entityliving, x, y, z, par8, par9);
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void doRender(Entity par1Entity, double x, double y, double z, float par8, float par9)
/*  35:    */   {
/*  36: 47 */     super.doRender(par1Entity, x, y, z, par8, par9);
/*  37: 48 */     renderElectricField(x, y, z, (EntityGolemMecha)par1Entity);
/*  38:    */   }
/*  39:    */   
/*  40: 51 */   Random rnd = new Random();
/*  41:    */   
/*  42:    */   protected void renderElectricField(double x, double y, double z, EntityGolemMecha entityliving)
/*  43:    */   {
/*  44: 54 */     if ((entityliving.hasElectricField()) || (entityliving.shieldON())) {
/*  45: 55 */       GL11.glDisable(3553);
/*  46:    */     }
/*  47: 56 */     Tessellator tessellator = Tessellator.instance;
/*  48: 57 */     int color = 5592575;
/*  49: 58 */     GL11.glEnable(3042);
/*  50: 59 */     GL11.glBlendFunc(770, 1);
/*  51: 60 */     this.rnd.setSeed(entityliving.getEntityId() + entityliving.ticksExisted / 2);
/*  52: 61 */     int steps = 16;
/*  53: 62 */     GL11.glLineWidth(1.0F);
/*  54: 63 */     if (entityliving.hasElectricField()) {
/*  55: 64 */       for (int a = 0; a < 5; a++)
/*  56:    */       {
/*  57: 66 */         GL11.glDisable(2896);
/*  58: 67 */         steps = this.rnd.nextInt(26) + 5;
/*  59: 68 */         tessellator.startDrawing(3);
/*  60: 69 */         tessellator.setColorRGBA_F(0.5F, 0.64F, 1.0F, 0.6F);
/*  61: 70 */         int startX = this.rnd.nextInt();
/*  62: 71 */         int startY = this.rnd.nextInt();
/*  63: 72 */         int startZ = this.rnd.nextInt();
/*  64: 74 */         for (int i = 0; i <= steps; i++)
/*  65:    */         {
/*  66: 76 */           float stepVariation = i / steps;
/*  67: 77 */           double dist = entityliving.width * 1.6D;
/*  68: 78 */           double boltSize = 80.0D;
/*  69: 79 */           double varX = Math.sin((i + startX) / boltSize * 3.141592653589793D * 2.0D) * dist + (this.rnd.nextDouble() - 0.5D) * 0.5D;
/*  70: 80 */           double varZ = Math.cos((i + startZ) / boltSize * 3.141592653589793D * 2.0D) * dist + (this.rnd.nextDouble() - 0.5D) * 0.5D;
/*  71: 81 */           double varY = Math.sin((entityliving.ticksExisted + i + startY) / boltSize * 3.141592653589793D) + this.rnd.nextDouble() + 1.6D;
/*  72:    */           
/*  73: 83 */           tessellator.addVertex(x + varX, y + varY, z + varZ);
/*  74:    */         }
/*  75: 88 */         tessellator.draw();
/*  76: 89 */         GL11.glEnable(2896);
/*  77:    */       }
/*  78:    */     }
/*  79: 92 */     if ((entityliving.hasElectricShield()) && (entityliving.shieldON()))
/*  80:    */     {
/*  81: 93 */       GL11.glPushMatrix();
/*  82: 94 */       GL11.glTranslated(x, y + entityliving.height / 2.0F + 0.2D, z);
/*  83: 95 */       GL11.glRotated(entityliving.rotationYaw, x, y, z);
/*  84: 96 */       GL11.glColor4f(0.2F, 0.2F, 1.0F, 0.6F);
/*  85: 97 */       GL11.glDisable(2884);
/*  86: 98 */       GL11.glDepthMask(false);
/*  87: 99 */       GL11.glScalef(-1.0F, -1.0F, 1.0F);
/*  88:100 */       this.bubble.draw((entityliving.width + entityliving.height) * 0.52F, this.divisions, this.divisions);
/*  89:101 */       GL11.glDepthMask(true);
/*  90:102 */       GL11.glEnable(2884);
/*  91:103 */       GL11.glPopMatrix();
/*  92:    */     }
/*  93:105 */     GL11.glDisable(3042);
/*  94:106 */     GL11.glEnable(3553);
/*  95:    */   }
/*  96:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.rendererHuman.RenderHumanMecha
 * JD-Core Version:    0.7.1
 */