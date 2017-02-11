/*   1:    */ package com.chocolate.chocolateQuest.client.itemsRender;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMecha;
/*   4:    */ import net.minecraft.client.Minecraft;
/*   5:    */ import net.minecraft.client.model.ModelBase;
/*   6:    */ import net.minecraft.client.renderer.texture.TextureManager;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.entity.EntityLivingBase;
/*   9:    */ import net.minecraft.item.ItemStack;
/*  10:    */ import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
/*  11:    */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*  12:    */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*  13:    */ import org.lwjgl.opengl.GL11;
/*  14:    */ 
/*  15:    */ public class RenderItemGolemWeapon
/*  16:    */   extends RenderItemBase
/*  17:    */ {
/*  18: 17 */   public static final ResourceLocation texture = new ResourceLocation("chocolateQuest:textures/entity/golemWeapon.png");
/*  19:    */   ModelBase model;
/*  20:    */   
/*  21:    */   public RenderItemGolemWeapon(ModelBase model)
/*  22:    */   {
/*  23: 21 */     this.model = model;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*  27:    */   {
/*  28: 27 */     if ((type == IItemRenderer.ItemRenderType.EQUIPPED) || (type == IItemRenderer.ItemRenderType.INVENTORY) || (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) || (type == IItemRenderer.ItemRenderType.ENTITY)) {
/*  29: 29 */       return true;
/*  30:    */     }
/*  31: 32 */     return false;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*  35:    */   {
/*  36: 38 */     return false;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*  40:    */   {
/*  41: 44 */     Minecraft.getMinecraft().renderEngine.bindTexture(texture);
/*  42: 45 */     if (type == IItemRenderer.ItemRenderType.EQUIPPED)
/*  43:    */     {
/*  44: 47 */       EntityLivingBase p = (EntityLivingBase)data[1];
/*  45: 48 */       renderEquipped(p, item);
/*  46:    */     }
/*  47: 50 */     if (type == IItemRenderer.ItemRenderType.ENTITY)
/*  48:    */     {
/*  49: 52 */       Entity p = (Entity)data[1];
/*  50: 53 */       render(p, item, 0);
/*  51:    */     }
/*  52: 55 */     if (type == IItemRenderer.ItemRenderType.INVENTORY) {
/*  53: 58 */       renderInventory(item);
/*  54:    */     }
/*  55: 60 */     if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)
/*  56:    */     {
/*  57: 62 */       EntityLivingBase p = (EntityLivingBase)data[1];
/*  58: 63 */       renderFP(p, item, 0);
/*  59:    */     }
/*  60:    */   }
/*  61:    */   
/*  62:    */   protected void renderInventory(ItemStack itemstack)
/*  63:    */   {
/*  64: 69 */     GL11.glDisable(2884);
/*  65: 70 */     GL11.glTranslatef(8.0F, 14.0F, 0.0F);
/*  66: 71 */     GL11.glScalef(30.0F, 30.0F, 30.0F);
/*  67: 72 */     GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
/*  68: 73 */     GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
/*  69: 74 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*  70: 75 */     this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/*  71:    */   }
/*  72:    */   
/*  73:    */   protected void render(Entity entity, ItemStack itemstack, int par3)
/*  74:    */   {
/*  75: 80 */     GL11.glScalef(3.0F, 3.0F, 3.0F);
/*  76: 81 */     this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/*  77:    */   }
/*  78:    */   
/*  79:    */   protected void renderEquipped(EntityLivingBase entity, ItemStack itemstack)
/*  80:    */   {
/*  81: 87 */     GL11.glTranslatef(0.14F, -0.32F, -0.4F);
/*  82: 88 */     if ((entity instanceof EntityGolemMecha))
/*  83:    */     {
/*  84: 89 */       GL11.glTranslatef(-0.44F, -0.08F, -0.1F);
/*  85: 90 */       GL11.glScalef(1.1F, 1.1F, 1.1F);
/*  86:    */     }
/*  87: 92 */     GL11.glRotatef(-6.0F, 0.0F, 0.0F, 1.0F);
/*  88: 93 */     GL11.glRotatef(16.0F, 1.0F, 0.0F, 0.0F);
/*  89: 94 */     GL11.glRotatef(24.0F, 0.0F, 1.0F, 1.0F);
/*  90: 95 */     GL11.glScalef(2.2F, 2.2F, 2.2F);
/*  91: 96 */     this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/*  92:    */   }
/*  93:    */   
/*  94:    */   protected void renderFP(EntityLivingBase entity, ItemStack itemstack, int par3)
/*  95:    */   {
/*  96:101 */     GL11.glTranslatef(0.54F, 0.52F, 0.61F);
/*  97:102 */     GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
/*  98:103 */     GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/*  99:104 */     GL11.glRotatef(80.0F, 0.0F, 1.0F, 0.0F);
/* 100:105 */     renderEquipped(entity, itemstack);
/* 101:    */   }
/* 102:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.client.itemsRender.RenderItemGolemWeapon

 * JD-Core Version:    0.7.1

 */
