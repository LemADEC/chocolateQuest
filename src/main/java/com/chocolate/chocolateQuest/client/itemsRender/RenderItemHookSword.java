/*  1:   */ package com.chocolate.chocolateQuest.client.itemsRender;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.items.swords.ItemHookSword;
/*  5:   */ import net.minecraft.item.Item;
/*  6:   */ import net.minecraft.item.ItemStack;
/*  7:   */ 
/*  8:   */ public class RenderItemHookSword
/*  9:   */   extends RenderItemBase
/* 10:   */ {
/* 11:11 */   ItemStack hook = new ItemStack(ChocolateQuest.material);
/* 12:   */   
/* 13:   */   protected void renderItem(ItemStack is)
/* 14:   */   {
/* 15:13 */     if (((ItemHookSword)is.getItem()).getHookID(is) == 0) {
/* 16:14 */       doRenderItem(is.getItem().getIconFromDamageForRenderPass(0, 1), is, 0, false);
/* 17:   */     }
/* 18:15 */     doRenderItem(is);
/* 19:   */   }
/* 20:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.itemsRender.RenderItemHookSword
 * JD-Core Version:    0.7.1
 */