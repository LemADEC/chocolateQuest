/*   1:    */ package com.chocolate.chocolateQuest.client.itemsRender;
/*   2:    */ 
/*   3:    */ import net.minecraft.client.Minecraft;
/*   4:    */ import net.minecraft.client.renderer.ItemRenderer;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.texture.TextureManager;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.entity.EntityLivingBase;
/*   9:    */ import net.minecraft.item.Item;
/*  10:    */ import net.minecraft.item.ItemStack;
/*  11:    */ import net.minecraft.util.IIcon;
/*  12:    */ import net.minecraft.util.ResourceLocation;
/*  13:    */ import net.minecraftforge.client.IItemRenderer;
/*  14:    */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*  15:    */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*  16:    */ import org.lwjgl.opengl.GL11;
/*  17:    */ 
/*  18:    */ public class RenderItemBase
/*  19:    */   implements IItemRenderer
/*  20:    */ {
/*  21: 18 */   private static final ResourceLocation itemGlint = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*  22:    */   
/*  23:    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*  24:    */   {
/*  25: 24 */     if ((type == IItemRenderer.ItemRenderType.EQUIPPED) || (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
/*  26: 26 */       return true;
/*  27:    */     }
/*  28: 29 */     return false;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*  32:    */   {
/*  33: 35 */     return false;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*  37:    */   {
/*  38: 41 */     if (type == IItemRenderer.ItemRenderType.EQUIPPED)
/*  39:    */     {
/*  40: 43 */       EntityLivingBase p = (EntityLivingBase)data[1];
/*  41: 44 */       renderEquipped(p, item);
/*  42:    */     }
/*  43: 46 */     if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)
/*  44:    */     {
/*  45: 48 */       EntityLivingBase p = (EntityLivingBase)data[1];
/*  46: 49 */       renderFirstPerson(p, item);
/*  47:    */     }
/*  48: 51 */     if (type == IItemRenderer.ItemRenderType.INVENTORY) {
/*  49: 53 */       renderInventory(item);
/*  50:    */     }
/*  51: 55 */     if (type == IItemRenderer.ItemRenderType.ENTITY)
/*  52:    */     {
/*  53: 57 */       Entity e = (Entity)data[1];
/*  54: 58 */       GL11.glRotatef(e.ticksExisted % 360, 0.0F, 1.0F, 0.0F);
/*  55: 59 */       renderAsEntity(item);
/*  56:    */     }
/*  57:    */   }
/*  58:    */   
/*  59:    */   protected void renderFirstPerson(EntityLivingBase player, ItemStack itemstack)
/*  60:    */   {
/*  61: 65 */     renderItem(itemstack);
/*  62:    */   }
/*  63:    */   
/*  64:    */   protected void renderInventory(ItemStack itemstack)
/*  65:    */   {
/*  66: 70 */     renderItem(itemstack);
/*  67:    */   }
/*  68:    */   
/*  69:    */   protected void renderAsEntity(ItemStack itemstack)
/*  70:    */   {
/*  71: 74 */     renderItem(itemstack);
/*  72:    */   }
/*  73:    */   
/*  74:    */   protected void renderEquipped(EntityLivingBase player, ItemStack itemstack)
/*  75:    */   {
/*  76: 78 */     renderItem(itemstack);
/*  77:    */   }
/*  78:    */   
/*  79:    */   protected void renderItem(ItemStack is)
/*  80:    */   {
/*  81: 82 */     doRenderItem(is);
/*  82:    */   }
/*  83:    */   
/*  84:    */   public static void doRenderItem(ItemStack itemstack)
/*  85:    */   {
/*  86: 87 */     doRenderItem(itemstack, 0, false);
/*  87:    */   }
/*  88:    */   
/*  89:    */   public static void doRenderItem(ItemStack itemstack, int color)
/*  90:    */   {
/*  91: 91 */     doRenderItem(itemstack, color, false);
/*  92:    */   }
/*  93:    */   
/*  94:    */   public static void doRenderItem(ItemStack itemstack, int color, boolean effect)
/*  95:    */   {
/*  96: 97 */     IIcon icon = itemstack.getItem().getIcon(itemstack, 0);
/*  97:    */     
/*  98: 99 */     doRenderItem(icon, itemstack, color, effect);
/*  99:    */   }
/* 100:    */   
/* 101:    */   public static void doRenderItem(IIcon icon, ItemStack itemstack, int effectColor, boolean effect)
/* 102:    */   {
/* 103:104 */     if (icon == null)
/* 104:    */     {
/* 105:106 */       GL11.glPopMatrix();
/* 106:107 */       return;
/* 107:    */     }
/* 108:110 */     Minecraft mc = Minecraft.getMinecraft();
/* 109:111 */     ResourceLocation resourcelocation = mc.renderEngine.getResourceLocation(itemstack.getItemSpriteNumber());
/* 110:112 */     mc.renderEngine.bindTexture(resourcelocation);
/* 111:113 */     GL11.glPushMatrix();
/* 112:114 */     Tessellator tessellator = Tessellator.instance;
/* 113:115 */     float f = icon.getMinU();
/* 114:116 */     float f1 = icon.getMaxU();
/* 115:117 */     float f2 = icon.getMinV();
/* 116:118 */     float f3 = icon.getMaxV();
/* 117:119 */     float f4 = 0.0F;
/* 118:120 */     float f5 = 0.3F;
/* 119:121 */     ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
/* 120:123 */     if ((itemstack != null) && ((itemstack.hasEffect()) || (effect)))
/* 121:    */     {
           float red;
           float blue;
           float green;

/* 126:127 */       if (effect)
/* 127:    */       {
/* 128:129 */         red = (float)(((effectColor & 0xFF0000) >> 16) / 255.0D);
/* 129:130 */         green = (float)(((effectColor & 0xFF00) >> 8) / 255.0D);
/* 130:131 */         blue = (float)((effectColor & 0xFF) / 255.0D);
/* 131:    */       }
/* 132:    */       else
/* 133:    */       {
/* 134:135 */         float f7 = 0.76F;
/* 135:136 */         red = 0.5F * f7;
/* 136:137 */         blue = 0.25F * f7;
/* 137:138 */         green = 0.8F * f7;
/* 138:    */       }
/* 139:141 */       GL11.glDepthFunc(514);
/* 140:142 */       GL11.glDisable(2896);
/* 141:143 */       mc.renderEngine.bindTexture(itemGlint);
/* 142:144 */       GL11.glEnable(3042);
/* 143:145 */       GL11.glBlendFunc(768, 1);
/* 144:146 */       GL11.glColor4f(red, green, blue, 0.5F);
/* 145:147 */       GL11.glMatrixMode(5890);
/* 146:148 */       GL11.glPushMatrix();
/* 147:149 */       float f8 = 0.125F;
/* 148:150 */       GL11.glScalef(f8, f8, f8);
/* 149:151 */       float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
/* 150:152 */       GL11.glTranslatef(f9, 0.0F, 0.0F);
/* 151:153 */       GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
/* 152:154 */       ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
/* 153:155 */       GL11.glPopMatrix();
/* 154:156 */       GL11.glPushMatrix();
/* 155:157 */       GL11.glScalef(f8, f8, f8);
/* 156:158 */       f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
/* 157:159 */       GL11.glTranslatef(-f9, 0.0F, 0.0F);
/* 158:160 */       GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/* 159:161 */       ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
/* 160:162 */       GL11.glPopMatrix();
/* 161:163 */       GL11.glMatrixMode(5888);
/* 162:164 */       GL11.glDisable(3042);
/* 163:165 */       GL11.glEnable(2896);
/* 164:166 */       GL11.glDepthFunc(515);
/* 165:    */     }
/* 166:169 */     GL11.glDisable(32826);
/* 167:170 */     GL11.glPopMatrix();
/* 168:    */   }
/* 169:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.client.itemsRender.RenderItemBase

 * JD-Core Version:    0.7.1

 */
