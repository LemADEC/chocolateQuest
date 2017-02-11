/*  1:   */ package com.chocolate.chocolateQuest.client.itemsRender;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  4:   */ import net.minecraft.client.Minecraft;
/*  5:   */ import net.minecraft.client.renderer.Tessellator;
/*  6:   */ import net.minecraft.client.renderer.texture.TextureManager;
/*  7:   */ import net.minecraft.client.settings.GameSettings;
/*  8:   */ import net.minecraft.entity.EntityLivingBase;
/*  9:   */ import net.minecraft.entity.player.EntityPlayer;
/* 10:   */ import net.minecraft.item.ItemStack;
/* 11:   */ import org.lwjgl.opengl.GL11;
/* 12:   */ 
/* 13:   */ public class RenderItemShield
/* 14:   */   extends RenderItemBase
/* 15:   */ {
/* 16:   */   protected void renderEquipped(EntityLivingBase par1EntityLiving, ItemStack itemstack)
/* 17:   */   {
/* 18:35 */     if ((par1EntityLiving != Minecraft.getMinecraft().thePlayer) || (Minecraft.getMinecraft().gameSettings.thirdPersonView != 0))
/* 19:   */     {
/* 20:37 */       GL11.glRotatef(-75.0F, 0.0F, 1.0F, 0.0F);
/* 21:38 */       GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
/* 22:39 */       GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 23:40 */       GL11.glTranslatef(1.0F, -0.6F, -0.55F);
/* 24:   */     }
/* 25:   */     else
/* 26:   */     {
/* 27:44 */       GL11.glTranslatef(0.0F, -0.3F, 0.3F);
/* 28:   */     }
/* 29:46 */     GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 30:47 */     GL11.glScalef(1.4F, 1.4F, 1.4F);
/* 31:   */     
/* 32:   */ 
/* 33:50 */     doRenderShield(itemstack);
/* 34:   */   }
/* 35:   */   
/* 36:   */   protected void renderFirstPerson(EntityLivingBase player, ItemStack itemstack)
/* 37:   */   {
/* 38:57 */     if (((EntityPlayer)player).isBlocking())
/* 39:   */     {
/* 40:59 */       GL11.glLoadIdentity();
/* 41:60 */       GL11.glTranslatef(-1.5F, -1.0F, -1.0F);
/* 42:   */     }
/* 43:63 */     doRenderShield(itemstack);
/* 44:   */   }
/* 45:   */   
/* 46:   */   public static void doRenderShield(ItemStack itemstack)
/* 47:   */   {
/* 48:67 */     doRenderItem(itemstack);
/* 49:   */     
/* 50:69 */     GL11.glEnable(32826);
/* 51:   */     
/* 52:71 */     Minecraft.getMinecraft().renderEngine.bindTexture(BDHelper.getItemTexture());
/* 53:72 */     int spriteIndex = itemstack.getItemDamage();
/* 54:73 */     float i1 = (spriteIndex % 16 * 16 + 0) / 256.0F;
/* 55:74 */     float i2 = (spriteIndex % 16 * 16 + 16) / 256.0F;
/* 56:75 */     float i3 = spriteIndex / 16 * 16 / 256.0F;
/* 57:76 */     float i4 = i3 + 0.0625F;
/* 58:77 */     float f5 = 1.0F;
/* 59:78 */     float posZ = 0.0F;
/* 60:79 */     Tessellator tessellator = Tessellator.instance;
/* 61:80 */     tessellator.startDrawingQuads();
/* 62:81 */     tessellator.addVertexWithUV(0.0D, 0.0D, posZ, i1, i4);
/* 63:82 */     tessellator.addVertexWithUV(f5, 0.0D, posZ, i2, i4);
/* 64:83 */     tessellator.addVertexWithUV(f5, 1.0D, posZ, i2, i3);
/* 65:84 */     tessellator.addVertexWithUV(0.0D, 1.0D, posZ, i1, i3);
/* 66:85 */     tessellator.draw();
/* 67:86 */     GL11.glDisable(32826);
/* 68:   */   }
/* 69:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.itemsRender.RenderItemShield
 * JD-Core Version:    0.7.1
 */