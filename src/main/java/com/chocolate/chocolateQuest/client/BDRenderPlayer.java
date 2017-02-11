/*   1:    */ package com.chocolate.chocolateQuest.client;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.items.swords.ItemBaseSwordDefensive;
/*   5:    */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*   6:    */ import net.minecraft.block.Block;
/*   7:    */ import net.minecraft.client.model.ModelBiped;
/*   8:    */ import net.minecraft.client.model.ModelRenderer;
/*   9:    */ import net.minecraft.client.renderer.ItemRenderer;
/*  10:    */ import net.minecraft.client.renderer.RenderBlocks;
/*  11:    */ import net.minecraft.client.renderer.entity.RenderManager;
/*  12:    */ import net.minecraft.client.renderer.entity.RenderPlayer;
/*  13:    */ import net.minecraft.entity.EntityLivingBase;
/*  14:    */ import net.minecraft.entity.player.EntityPlayer;
/*  15:    */ import net.minecraft.entity.player.InventoryPlayer;
/*  16:    */ import net.minecraft.init.Items;
/*  17:    */ import net.minecraft.item.Item;
/*  18:    */ import net.minecraft.item.ItemBlock;
/*  19:    */ import net.minecraft.item.ItemStack;
/*  20:    */ import net.minecraftforge.client.IItemRenderer;
/*  21:    */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*  22:    */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*  23:    */ import net.minecraftforge.client.MinecraftForgeClient;
/*  24:    */ import org.lwjgl.opengl.GL11;
/*  25:    */ 
/*  26:    */ public class BDRenderPlayer
/*  27:    */   extends RenderPlayer
/*  28:    */ {
/*  29:    */   public BDRenderPlayer()
/*  30:    */   {
/*  31: 30 */     ModelBiped model = new BDRenderPlayerModel();
/*  32: 31 */     this.mainModel = model;
/*  33: 32 */     ReflectionHelper.setPrivateValue(RenderPlayer.class, this, model, new String[] { "modelBipedMain", "f" });
/*  34:    */   }
/*  35:    */   
/*  36:    */   protected void renderModel(EntityLivingBase entityliving, float f, float f1, float f2, float f3, float f4, float f5)
/*  37:    */   {
/*  38: 37 */     super.renderModel(entityliving, f, f1, f2, f3, f4, f5);
/*  39:    */     
/*  40: 39 */     EntityPlayer ep = (EntityPlayer)entityliving;
/*  41: 40 */     if (ep.inventory.getCurrentItem() != null)
/*  42:    */     {
/*  43: 42 */       ItemStack is = ep.inventory.getCurrentItem();
/*  44: 43 */       if ((is.getItem() instanceof ItemBaseSwordDefensive)) {
/*  45: 44 */         renderLeftHandItem(entityliving, f1, is);
/*  46:    */       }
/*  47:    */     }
/*  48:    */   }
/*  49:    */   
/*  50:    */   protected void renderLeftHandItem(EntityLivingBase entityliving, float f, ItemStack is)
/*  51:    */   {
/*  52: 51 */     ItemStack itemstack = new ItemStack(ChocolateQuest.shield, 1, ((ItemBaseSwordDefensive)is.getItem()).getShieldID(is));
/*  53: 53 */     if (itemstack != null)
/*  54:    */     {
/*  55: 56 */       GL11.glPushMatrix();
/*  56: 57 */       ((ModelBiped)this.mainModel).bipedLeftArm.postRender(0.0625F);
/*  57: 58 */       GL11.glTranslatef(0.0325F, 0.4375F, 0.0625F);
/*  58:    */       
/*  59: 60 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
/*  60: 61 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*  61: 64 */       if (itemstack.getItem() == ChocolateQuest.shield)
/*  62:    */       {
/*  63: 66 */         float var6 = 1.2F;
/*  64: 67 */         GL11.glTranslatef(0.22F, 0.35F, 0.0F);
/*  65: 68 */         GL11.glRotatef(169.0F, 0.0F, 0.0F, 1.0F);
/*  66: 69 */         GL11.glRotatef(22.0F, 1.0F, 0.0F, 0.0F);
/*  67: 70 */         GL11.glRotatef(8.0F, 0.0F, 1.0F, 0.0F);
/*  68:    */       }
/*  69: 72 */       if (((itemstack.getItem() instanceof ItemBlock)) && ((is3D) || (RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType()))))
/*  70:    */       {
/*  71: 74 */         float var6 = 0.5F;
/*  72: 75 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/*  73: 76 */         var6 *= 0.75F;
/*  74: 77 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/*  75: 78 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  76: 79 */         GL11.glScalef(-var6, -var6, var6);
/*  77:    */       }
/*  78: 81 */       else if (itemstack.getItem() == Items.bow)
/*  79:    */       {
/*  80: 83 */         float var6 = 0.625F;
/*  81: 84 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/*  82: 85 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/*  83: 86 */         GL11.glScalef(var6, -var6, var6);
/*  84: 87 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/*  85: 88 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  86:    */       }
/*  87: 90 */       else if (itemstack.getItem().isFull3D())
/*  88:    */       {
/*  89: 92 */         float var6 = 0.625F;
/*  90: 94 */         if (itemstack.getItem().shouldRotateAroundWhenRendering())
/*  91:    */         {
/*  92: 96 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*  93: 97 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*  94:    */         }
/*  95:100 */         GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
/*  96:101 */         GL11.glScalef(var6, -var6, var6);
/*  97:102 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/*  98:103 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  99:    */       }
/* 100:    */       else
/* 101:    */       {
/* 102:107 */         float var6 = 0.375F;
/* 103:108 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 104:109 */         GL11.glScalef(var6, var6, var6);
/* 105:110 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 106:111 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 107:112 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/* 108:    */       }
/* 109:115 */       this.renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
/* 110:117 */       if (itemstack.getItem().requiresMultipleRenderPasses()) {
/* 111:119 */         for (int x = 1; x < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); x++) {
/* 112:121 */           this.renderManager.itemRenderer.renderItem(entityliving, itemstack, x);
/* 113:    */         }
/* 114:    */       }
/* 115:126 */       GL11.glPopMatrix();
/* 116:    */     }
/* 117:    */   }
/* 118:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.BDRenderPlayer
 * JD-Core Version:    0.7.1
 */