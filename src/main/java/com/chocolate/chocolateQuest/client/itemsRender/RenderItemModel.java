/*   1:    */ package com.chocolate.chocolateQuest.client.itemsRender;
/*   2:    */ 
import net.minecraftforge.client.IItemRenderer;
/*   3:    */ import com.chocolate.chocolateQuest.client.ClientProxy;
/*   4:    */ import net.minecraft.client.Minecraft;
/*   5:    */ import net.minecraft.client.model.ModelBase;
/*   6:    */ import net.minecraft.client.renderer.texture.TextureManager;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.entity.EntityLivingBase;
/*   9:    */ import net.minecraft.item.Item;
/*  10:    */ import net.minecraft.item.ItemArmor;
/*  11:    */ import net.minecraft.item.ItemStack;
/*  12:    */ import net.minecraft.util.ResourceLocation;
/*  13:    */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*  14:    */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*  15:    */ import org.lwjgl.opengl.GL11;
/*  16:    */ 
/*  17:    */ public class RenderItemModel
/*  18:    */   extends RenderItemBase
/*  19:    */ {
/*  20:    */   public final ResourceLocation texture;
/*  21:    */   ModelBase model;
/*  22:    */   int armorType;
/*  23:    */   
/*  24:    */   public RenderItemModel(ItemArmor item)
/*  25:    */   {
/*  26: 23 */     ItemStack is = new ItemStack(item);
/*  27: 24 */     this.armorType = item.armorType;
/*  28: 25 */     this.texture = new ResourceLocation(item.getArmorTexture(is, null, this.armorType, ""));
/*  29: 26 */     this.model = item.getArmorModel(null, is, this.armorType);
/*  30: 27 */     if (this.model == null) {
/*  31: 28 */       this.model = ClientProxy.defaultArmor;
/*  32:    */     }
/*  33:    */   }
/*  34:    */   
/*  35:    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*  36:    */   {
/*  37: 35 */     item.getItem().getArmorModel(null, item, this.armorType);
/*  38: 36 */     if ((type == IItemRenderer.ItemRenderType.EQUIPPED) || (type == IItemRenderer.ItemRenderType.INVENTORY) || (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) || (type == IItemRenderer.ItemRenderType.ENTITY)) {
/*  39: 38 */       return true;
/*  40:    */     }
/*  41: 41 */     return false;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*  45:    */   {
/*  46: 47 */     return false;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*  50:    */   {
/*  51: 53 */     Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
/*  52: 54 */     if (type == IItemRenderer.ItemRenderType.EQUIPPED)
/*  53:    */     {
/*  54: 56 */       EntityLivingBase p = (EntityLivingBase)data[1];
/*  55: 57 */       renderEquipped(p, item);
/*  56:    */     }
/*  57: 59 */     if (type == IItemRenderer.ItemRenderType.ENTITY)
/*  58:    */     {
/*  59: 61 */       Entity p = (Entity)data[1];
/*  60: 62 */       render(p, item);
/*  61:    */     }
/*  62: 64 */     if (type == IItemRenderer.ItemRenderType.INVENTORY) {
/*  63: 66 */       renderInventory(item);
/*  64:    */     }
/*  65: 68 */     if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)
/*  66:    */     {
/*  67: 70 */       EntityLivingBase p = (EntityLivingBase)data[1];
/*  68: 71 */       renderFP(p, item);
/*  69:    */     }
/*  70:    */   }
/*  71:    */   
/*  72:    */   protected void renderInventory(ItemStack itemstack)
/*  73:    */   {
/*  74: 77 */     GL11.glDisable(2884);
/*  75: 78 */     GL11.glEnable(3008);
/*  76: 79 */     GL11.glEnable(3042);
/*  77: 80 */     GL11.glBlendFunc(770, 771);
/*  78: 81 */     int armorOffset = this.armorType * 6;
/*  79: 82 */     if (this.armorType == 2) {
/*  80: 83 */       armorOffset += 2;
/*  81:    */     }
/*  82: 84 */     GL11.glTranslatef(8.0F, 10 - armorOffset, 0.0F);
/*  83: 85 */     GL11.glScalef(15.0F, 15.0F, 15.0F);
/*  84: 86 */     GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
/*  85: 87 */     GL11.glRotatef(220.0F, 0.0F, 1.0F, 0.0F);
/*  86: 88 */     GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
/*  87: 89 */     this.model.render(null, this.armorType, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/*  88: 90 */     GL11.glEnable(2884);
/*  89:    */   }
/*  90:    */   
/*  91:    */   protected void render(Entity entity, ItemStack itemstack)
/*  92:    */   {
/*  93: 95 */     GL11.glDisable(2884);
/*  94: 96 */     float armorOffset = this.armorType * 1.2F;
/*  95: 97 */     if (this.armorType == 3) {
/*  96: 98 */       armorOffset = 3.2F;
/*  97:    */     }
/*  98: 99 */     GL11.glTranslatef(0.0F, armorOffset, 0.0F);
/*  99:    */     
/* 100:101 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 101:102 */     GL11.glScalef(2.0F, 2.0F, 2.0F);
/* 102:103 */     this.model.render(entity, this.armorType, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/* 103:104 */     GL11.glEnable(2884);
/* 104:    */   }
/* 105:    */   
/* 106:    */   protected void renderEquipped(EntityLivingBase entity, ItemStack itemstack)
/* 107:    */   {
/* 108:110 */     float armorOffset = 0.4F - this.armorType * 0.6F;
/* 109:111 */     if (this.armorType == 2) {
/* 110:112 */       armorOffset += 0.2F;
/* 111:    */     }
/* 112:113 */     GL11.glTranslatef(0.5F, armorOffset * 0.5F, 0.4F + armorOffset);
/* 113:114 */     GL11.glRotatef(-6.0F, 0.0F, 0.0F, 1.0F);
/* 114:115 */     GL11.glRotatef(16.0F, 1.0F, 0.0F, 0.0F);
/* 115:116 */     GL11.glRotatef(24.0F, 0.0F, 1.0F, 1.0F);
/* 116:117 */     this.model.render(entity, this.armorType, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/* 117:    */   }
/* 118:    */   
/* 119:    */   protected void renderFP(EntityLivingBase entity, ItemStack itemstack) {}
/* 120:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.client.itemsRender.RenderItemModel

 * JD-Core Version:    0.7.1

 */
