/*  1:   */ package com.chocolate.chocolateQuest.client.itemsRender;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.items.swords.ItemBaseSwordDefensive;
/*  5:   */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  6:   */ import net.minecraft.client.Minecraft;
/*  7:   */ import net.minecraft.client.renderer.ItemRenderer;
/*  8:   */ import net.minecraft.client.renderer.Tessellator;
/*  9:   */ import net.minecraft.client.renderer.texture.TextureManager;
/* 10:   */ import net.minecraft.entity.EntityLivingBase;
/* 11:   */ import net.minecraft.entity.player.EntityPlayer;
/* 12:   */ import net.minecraft.item.Item;
/* 13:   */ import net.minecraft.item.ItemStack;
/* 14:   */ import net.minecraft.util.IIcon;
/* 15:   */ import net.minecraftforge.client.IItemRenderer;
/* 16:   */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/* 17:   */ import org.lwjgl.opengl.GL11;
/* 18:   */ 
/* 19:   */ public class RenderItemSwordDefensive
/* 20:   */   extends RenderItemBase
/* 21:   */   implements IItemRenderer
/* 22:   */ {
/* 23:   */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/* 24:   */   {
/* 25:24 */     if ((type == IItemRenderer.ItemRenderType.EQUIPPED) || (type == IItemRenderer.ItemRenderType.INVENTORY) || (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) || (type == IItemRenderer.ItemRenderType.ENTITY)) {
/* 26:25 */       return true;
/* 27:   */     }
/* 28:28 */     return false;
/* 29:   */   }
/* 30:   */   
/* 31:   */   protected void renderInventory(ItemStack itemstack)
/* 32:   */   {
/* 33:33 */     GL11.glScalef(16.0F, 16.0F, 0.0F);
/* 34:34 */     Tessellator tessellator = Tessellator.instance;
/* 35:35 */     GL11.glBlendFunc(770, 771);
/* 36:36 */     GL11.glEnable(3042);
/* 37:37 */     IIcon icon = ChocolateQuest.shield.getIconFromDamage(((ItemBaseSwordDefensive)itemstack.getItem()).getShieldID(itemstack));
/* 38:38 */     ItemRenderer.renderItemIn2D(tessellator, icon.getMinU(), icon.getMaxV(), icon.getMaxU(), icon.getMinV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
/* 39:39 */     icon = itemstack.getIconIndex();
/* 40:40 */     ItemRenderer.renderItemIn2D(tessellator, icon.getMinU(), icon.getMaxV(), icon.getMaxU(), icon.getMinV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
/* 41:   */   }
/* 42:   */   
/* 43:   */   protected void renderFirstPerson(EntityLivingBase player, ItemStack itemstack)
/* 44:   */   {
/* 45:46 */     doRenderItem(itemstack);
/* 46:47 */     if (((EntityPlayer)player).isBlocking())
/* 47:   */     {
/* 48:49 */       GL11.glLoadIdentity();
/* 49:50 */       GL11.glTranslatef(-1.5F, -1.0F, -1.0F);
/* 50:51 */       ItemStack shield = new ItemStack(ChocolateQuest.shield, 1, ((ItemBaseSwordDefensive)itemstack.getItem()).getShieldID(itemstack));
/* 51:52 */       doRenderItem(shield);
/* 52:   */       
/* 53:   */ 
/* 54:55 */       Minecraft.getMinecraft().renderEngine.bindTexture(BDHelper.getItemTexture());
/* 55:56 */       int spriteIndex = shield.getItemDamage();
/* 56:57 */       float i1 = (spriteIndex % 16 * 16 + 0) / 256.0F;
/* 57:58 */       float i2 = (spriteIndex % 16 * 16 + 16) / 256.0F;
/* 58:59 */       float i3 = spriteIndex / 16 * 16 / 256.0F;
/* 59:60 */       float i4 = i3 + 0.0625F;
/* 60:61 */       float f5 = 1.0F;
/* 61:62 */       float posZ = 0.0F;
/* 62:63 */       Tessellator tessellator = Tessellator.instance;
/* 63:64 */       tessellator.startDrawingQuads();
/* 64:65 */       tessellator.addVertexWithUV(0.0D, 0.0D, posZ, i1, i4);
/* 65:66 */       tessellator.addVertexWithUV(f5, 0.0D, posZ, i2, i4);
/* 66:67 */       tessellator.addVertexWithUV(f5, 1.0D, posZ, i2, i3);
/* 67:68 */       tessellator.addVertexWithUV(0.0D, 1.0D, posZ, i1, i3);
/* 68:69 */       tessellator.draw();
/* 69:   */     }
/* 70:   */   }
/* 71:   */   
/* 72:   */   protected void renderAsEntity(ItemStack is)
/* 73:   */   {
/* 74:76 */     doRenderItem(is);
/* 75:77 */     int shieldID = ((ItemBaseSwordDefensive)is.getItem()).getShieldID(is);
/* 76:   */     
/* 77:79 */     GL11.glTranslatef(0.0F, 0.0F, -0.07F);
/* 78:80 */     RenderItemShield.doRenderShield(new ItemStack(ChocolateQuest.shield, 1, shieldID));
/* 79:   */   }
/* 80:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.itemsRender.RenderItemSwordDefensive
 * JD-Core Version:    0.7.1
 */