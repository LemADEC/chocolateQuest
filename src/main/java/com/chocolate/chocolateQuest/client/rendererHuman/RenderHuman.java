/*   1:    */ package com.chocolate.chocolateQuest.client.rendererHuman;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.client.itemsRender.RenderItemBase;
/*   5:    */ import com.chocolate.chocolateQuest.client.model.ModelHuman;
/*   6:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   7:    */ import com.chocolate.chocolateQuest.items.gun.ItemGolemWeapon;
/*   8:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   9:    */ import net.minecraft.block.Block;
/*  10:    */ import net.minecraft.client.Minecraft;
/*  11:    */ import net.minecraft.client.gui.FontRenderer;
/*  12:    */ import net.minecraft.client.model.ModelBiped;
/*  13:    */ import net.minecraft.client.model.ModelRenderer;
/*  14:    */ import net.minecraft.client.renderer.ItemRenderer;
/*  15:    */ import net.minecraft.client.renderer.RenderBlocks;
/*  16:    */ import net.minecraft.client.renderer.Tessellator;
/*  17:    */ import net.minecraft.client.renderer.entity.RenderBiped;
/*  18:    */ import net.minecraft.client.renderer.entity.RenderManager;
/*  19:    */ import net.minecraft.client.renderer.texture.TextureManager;
/*  20:    */ import net.minecraft.entity.Entity;
/*  21:    */ import net.minecraft.entity.EntityLivingBase;
/*  22:    */ import net.minecraft.entity.passive.EntityHorse;
/*  23:    */ import net.minecraft.entity.player.EntityPlayer;
/*  24:    */ import net.minecraft.init.Items;
/*  25:    */ import net.minecraft.item.Item;
/*  26:    */ import net.minecraft.item.ItemBlock;
/*  27:    */ import net.minecraft.item.ItemStack;
/*  28:    */ import net.minecraft.util.MathHelper;
/*  29:    */ import net.minecraft.util.ResourceLocation;
/*  30:    */ import net.minecraftforge.client.IItemRenderer;
/*  31:    */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*  32:    */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*  33:    */ import net.minecraftforge.client.MinecraftForgeClient;
/*  34:    */ import org.lwjgl.opengl.GL11;
/*  35:    */ 
/*  36:    */ public class RenderHuman
/*  37:    */   extends RenderBiped
/*  38:    */ {
/*  39: 36 */   float featherY = -0.5F;
/*  40: 37 */   ItemStack item = new ItemStack(Items.feather);
/*  41:    */   
/*  42:    */   public RenderHuman(ModelBiped modelbase, float f)
/*  43:    */   {
/*  44: 40 */     super(modelbase, f);
/*  45:    */   }
/*  46:    */   
/*  47:    */   public RenderHuman(ModelBiped modelbase, float f, ResourceLocation r)
/*  48:    */   {
/*  49: 44 */     this(modelbase, f);
/*  50: 45 */     this.texture = r;
/*  51:    */   }
/*  52:    */   
/*  53:    */   protected void func_82421_b()
/*  54:    */   {
/*  55: 51 */     this.field_82423_g = new ModelHuman(1.0F);
/*  56: 52 */     this.field_82425_h = new ModelHuman(0.5F);
/*  57:    */   }
/*  58:    */   
/*  59:    */   protected void renderModel(EntityLivingBase entityliving, float f, float f1, float f2, float f3, float f4, float f5)
/*  60:    */   {
/*  61: 59 */     EntityHumanBase e = (EntityHumanBase)entityliving;
/*  62: 60 */     if (e.isSitting()) {
/*  63: 62 */       setSitOffset();
/*  64:    */     }
/*  65: 64 */     super.renderModel(entityliving, f, f1, f2, f3, f4, f5);
/*  66: 65 */     renderLeftHandItem(entityliving, f1);
/*  67: 66 */     if (!e.isInvisible())
/*  68:    */     {
/*  69: 67 */       if (e.isCaptain()) {
/*  70: 69 */         renderHelmetFeather(e);
/*  71:    */       }
/*  72: 72 */       if (e.shouldRenderCape()) {
/*  73: 74 */         renderCape(e);
/*  74:    */       }
/*  75:    */     }
/*  76:    */   }
/*  77:    */   
/*  78:    */   protected void renderLivingAt(EntityLivingBase entityliving, double d, double d1, double d2)
/*  79:    */   {
/*  80: 82 */     renderLivingLabel(entityliving, entityliving.getCommandSenderName(), d, d1, d2, 64);
/*  81: 83 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/*  82:    */     
/*  83: 85 */     EntityHumanBase human = (EntityHumanBase)entityliving;
/*  84: 87 */     if ((human.ridingEntity instanceof EntityHorse)) {
/*  85: 88 */       GL11.glTranslatef(0.0F, -0.5F, 0.0F);
/*  86:    */     }
/*  87: 90 */     if (human.isSpeaking()) {
/*  88: 91 */       renderSpeech(human);
/*  89:    */     }
/*  90:    */   }
/*  91:    */   
/*  92:    */   protected void renderHelmetFeather(EntityHumanBase e)
/*  93:    */   {
/*  94: 96 */     GL11.glPushMatrix();
/*  95: 97 */     ((ModelBiped)this.mainModel).bipedHead.postRender(0.0625F);
/*  96: 98 */     GL11.glTranslatef(-0.05F, this.featherY, 0.01F);
/*  97: 99 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 1.0F);
/*  98:100 */     GL11.glRotatef(125.0F, 0.0F, 1.0F, 0.0F);
/*  99:101 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 100:102 */     this.renderManager.itemRenderer.renderItem(e, this.item, 0);
/* 101:103 */     GL11.glPopMatrix();
/* 102:    */   }
/* 103:    */   
/* 104:    */   protected void renderCape(EntityHumanBase e)
/* 105:    */   {
/* 106:108 */     GL11.glPushMatrix();
/* 107:109 */     GL11.glTranslatef(-0.5F, 0.0F, 0.2F);
/* 108:110 */     GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
/* 109:111 */     ((ModelBiped)this.mainModel).bipedBody.postRender(0.0625F);
/* 110:112 */     this.renderManager.renderEngine.bindTexture(BDHelper.getItemTexture());
/* 111:113 */     int spriteIndex = e.getTeamID();
/* 112:114 */     float i1 = (spriteIndex % 16 * 16 + 0) / 256.0F;
/* 113:115 */     float i2 = (spriteIndex % 16 * 16 + 16) / 256.0F;
/* 114:116 */     float i3 = 0.125F;
/* 115:117 */     float i4 = 0.25F;
/* 116:118 */     float f6 = 1.0F;
/* 117:119 */     Tessellator tessellator = Tessellator.instance;
/* 118:120 */     tessellator.startDrawingQuads();
/* 119:121 */     tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, i1, i3);
/* 120:122 */     tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, i2, i3);
/* 121:123 */     tessellator.addVertexWithUV(1.0D, 1.200000047683716D, 0.0D, i2, i4);
/* 122:124 */     tessellator.addVertexWithUV(0.0D, 1.200000047683716D, 0.0D, i1, i4);
/* 123:125 */     tessellator.draw();
/* 124:126 */     GL11.glPopMatrix();
/* 125:    */   }
/* 126:    */   
/* 127:    */   protected void renderSpeech(EntityHumanBase e)
/* 128:    */   {
/* 129:131 */     GL11.glPushMatrix();
/* 130:132 */     GL11.glTranslatef(0.0F, 3.0F, 0.0F);
/* 131:133 */     GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 132:134 */     GL11.glRotatef(180.0F - this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 133:135 */     this.renderManager.renderEngine.bindTexture(BDHelper.getItemTexture());
/* 134:136 */     int spriteIndex = (e.ticksExisted / 160 + e.getEntityId()) % 16;
/* 135:137 */     float i1 = (spriteIndex % 16 * 16 + 0) / 256.0F;
/* 136:138 */     float i2 = (spriteIndex % 16 * 16 + 16) / 256.0F;
/* 137:139 */     float i3 = 0.8125F;
/* 138:140 */     float i4 = 0.875F;
/* 139:141 */     float size = 0.6F;
/* 140:142 */     Tessellator tessellator = Tessellator.instance;
/* 141:143 */     tessellator.startDrawingQuads();
/* 142:144 */     tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, i1, i3);
/* 143:145 */     tessellator.addVertexWithUV(size, 0.0D, 0.0D, i2, i3);
/* 144:146 */     tessellator.addVertexWithUV(size, size, 0.0D, i2, i4);
/* 145:147 */     tessellator.addVertexWithUV(0.0D, size, 0.0D, i1, i4);
/* 146:148 */     tessellator.draw();
/* 147:149 */     GL11.glPopMatrix();
/* 148:    */   }
/* 149:    */   
/* 150:    */   protected void setSitOffset()
/* 151:    */   {
/* 152:154 */     GL11.glTranslatef(0.0F, 0.6F, 0.0F);
/* 153:    */   }
/* 154:    */   
/* 155:    */   protected void renderLeftHandItem(EntityLivingBase entityliving, float f)
/* 156:    */   {
/* 157:159 */     ItemStack itemstack = ((EntityHumanBase)entityliving).getHeldItemLeft();
/* 158:161 */     if (itemstack != null)
/* 159:    */     {
/* 160:164 */       GL11.glPushMatrix();
/* 161:165 */       ((ModelBiped)this.mainModel).bipedLeftArm.postRender(0.0625F);
/* 162:166 */       GL11.glTranslatef(0.0325F, 0.4375F, 0.0625F);
/* 163:    */       
/* 164:168 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
/* 165:169 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D));
/* 166:172 */       if (itemstack.getItem() == ChocolateQuest.shield)
/* 167:    */       {
/* 168:174 */         float var6 = 1.2F;
/* 169:175 */         GL11.glTranslatef(0.22F, 0.35F, 0.0F);
/* 170:176 */         GL11.glRotatef(169.0F, 0.0F, 0.0F, 1.0F);
/* 171:177 */         GL11.glRotatef(22.0F, 1.0F, 0.0F, 0.0F);
/* 172:178 */         GL11.glRotatef(8.0F, 0.0F, 1.0F, 0.0F);
/* 173:    */       }
/* 174:180 */       if (((itemstack.getItem() instanceof ItemBlock)) && ((is3D) || (RenderBlocks.renderItemIn3d(Block.getBlockById(Item.getIdFromItem(itemstack.getItem())).getRenderType()))))
/* 175:    */       {
/* 176:182 */         float var6 = 0.5F;
/* 177:183 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/* 178:184 */         var6 *= 0.75F;
/* 179:185 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 180:186 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 181:187 */         GL11.glScalef(-var6, -var6, var6);
/* 182:    */       }
/* 183:189 */       else if (itemstack.getItem() == Items.bow)
/* 184:    */       {
/* 185:191 */         float var6 = 0.625F;
/* 186:192 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/* 187:193 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/* 188:194 */         GL11.glScalef(var6, -var6, var6);
/* 189:195 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 190:196 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 191:    */       }
/* 192:198 */       else if (itemstack.getItem().isFull3D())
/* 193:    */       {
/* 194:200 */         float var6 = 0.625F;
/* 195:202 */         if (itemstack.getItem().shouldRotateAroundWhenRendering())
/* 196:    */         {
/* 197:204 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 198:205 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/* 199:    */         }
/* 200:208 */         func_82422_c();
/* 201:209 */         GL11.glScalef(var6, -var6, var6);
/* 202:210 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 203:211 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 204:    */       }
/* 205:213 */       else if ((itemstack.getItem() instanceof ItemGolemWeapon))
/* 206:    */       {
/* 207:215 */         doLeftItemRotation();
/* 208:216 */         doLeftHandRotationForGolemWeapon();
/* 209:    */       }
/* 210:    */       else
/* 211:    */       {
/* 212:220 */         doLeftItemRotation();
/* 213:    */       }
/* 214:223 */       this.renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
/* 215:225 */       if (itemstack.getItem().requiresMultipleRenderPasses()) {
/* 216:227 */         for (int x = 1; x < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); x++) {
/* 217:229 */           this.renderManager.itemRenderer.renderItem(entityliving, itemstack, x);
/* 218:    */         }
/* 219:    */       }
/* 220:233 */       GL11.glPopMatrix();
/* 221:    */     }
/* 222:    */   }
/* 223:    */   
/* 224:    */   public void doLeftItemRotation()
/* 225:    */   {
/* 226:238 */     float var6 = 0.375F;
/* 227:239 */     GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 228:240 */     GL11.glScalef(var6, var6, var6);
/* 229:241 */     GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 230:242 */     GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 231:243 */     GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/* 232:    */   }
/* 233:    */   
/* 234:    */   public void doLeftHandRotationForGolemWeapon()
/* 235:    */   {
/* 236:246 */     GL11.glTranslatef(-0.02F, 0.04F, -0.2F);
/* 237:    */   }
/* 238:    */   
/* 239:    */   protected void rotateCorpse(EntityLivingBase entityliving, float f, float f1, float f2)
/* 240:    */   {
/* 241:273 */     GL11.glRotatef(180.0F - f1, 0.0F, 1.0F, 0.0F);
/* 242:275 */     if (entityliving.deathTime > 0)
/* 243:    */     {
/* 244:277 */       float f3 = (entityliving.deathTime + f2 - 1.0F) / 20.0F * 1.6F;
/* 245:278 */       f3 = MathHelper.sqrt_float(f3);
/* 246:280 */       if (f3 > 1.0F) {
/* 247:282 */         f3 = 1.0F;
/* 248:    */       }
/* 249:284 */       GL11.glRotatef(f3 * getDeathMaxRotation(entityliving), 0.0F, 0.0F, 1.0F);
/* 250:    */     }
/* 251:    */   }
/* 252:    */   
/* 253:    */   public void renderCustomItem(EntityLivingBase entityliving, ItemStack itemstack, int i)
/* 254:    */   {
/* 255:290 */     RenderItemBase.doRenderItem(itemstack, i);
/* 256:    */   }
/* 257:    */   
/* 258:    */   protected int getColorMultiplier(EntityLivingBase entityliving, float f, float f1)
/* 259:    */   {
/* 260:296 */     return 0;
/* 261:    */   }
/* 262:    */   
/* 263:    */   protected void preRenderCallback(EntityLivingBase entityliving, float f)
/* 264:    */   {
/* 265:302 */     EntityHumanBase e = (EntityHumanBase)entityliving;
/* 266:303 */     float s = e.getSizeModifier();
/* 267:304 */     GL11.glScalef(s, s, s);
/* 268:    */   }
/* 269:    */   
/* 270:    */   protected void renderLivingLabel(EntityLivingBase entityliving, String s, double d, double d1, double d2, int i)
/* 271:    */   {
/* 272:310 */     EntityHumanBase human = (EntityHumanBase)entityliving;
/* 273:311 */     EntityPlayer player = Minecraft.getMinecraft().thePlayer;
/* 274:312 */     if (entityliving.isOnSameTeam(player))
/* 275:    */     {
/* 276:315 */       FontRenderer fontrenderer = getFontRendererFromRenderManager();
/* 277:316 */       float f1 = 1.6F;
/* 278:317 */       float f2 = 0.01666667F * f1;
/* 279:318 */       GL11.glPushMatrix();
/* 280:319 */       GL11.glTranslatef((float)d + 0.0F, (float)d1 + entityliving.height + entityliving.height * 0.4F, (float)d2);
/* 281:320 */       GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 282:321 */       GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 283:322 */       GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 284:323 */       GL11.glScalef(-f2, -f2, f2);
/* 285:    */       
/* 286:325 */       GL11.glDisable(2896);
/* 287:326 */       GL11.glDisable(3553);
/* 288:327 */       GL11.glDisable(2929);
/* 289:    */       
/* 290:329 */       Tessellator tessellator = Tessellator.instance;
/* 291:330 */       float width = 20.0F;
/* 292:331 */       float height = 2.0F;
/* 293:332 */       tessellator.startDrawingQuads();
/* 294:333 */       tessellator.setColorRGBA_F(1.0F, 0.0F, 0.0F, 1.0F);
/* 295:334 */       tessellator.addVertex(-width - 1.0F, -1.0D, 0.0D);
/* 296:335 */       tessellator.addVertex(-width - 1.0F, height, 0.0D);
/* 297:336 */       tessellator.addVertex(width + 1.0F, height, 0.0D);
/* 298:337 */       tessellator.addVertex(width + 1.0F, -1.0D, 0.0D);
/* 299:338 */       tessellator.draw();
/* 300:339 */       float healthWidth = human.getHealth() * width * 2.0F / human.getMaxHealth();
/* 301:340 */       if (healthWidth > 0.0F)
/* 302:    */       {
/* 303:342 */         tessellator.startDrawingQuads();
/* 304:343 */         tessellator.setColorRGBA_F(0.0F, 1.0F, 0.0F, 1.0F);
/* 305:344 */         tessellator.addVertex(-width - 1.0F, -1.0D, 0.0D);
/* 306:345 */         tessellator.addVertex(-width - 1.0F, height, 0.0D);
/* 307:346 */         tessellator.addVertex(-width + healthWidth + 1.0F, height, 0.0D);
/* 308:347 */         tessellator.addVertex(-width + healthWidth + 1.0F, -1.0D, 0.0D);
/* 309:348 */         tessellator.draw();
/* 310:    */       }
/* 311:350 */       if (human.getOwner() == player)
/* 312:    */       {
/* 313:353 */         GL11.glDepthMask(false);
/* 314:354 */         GL11.glEnable(3042);
/* 315:355 */         GL11.glBlendFunc(770, 771);
/* 316:356 */         byte byte0 = 0;
/* 317:357 */         byte0 = -10;
/* 318:358 */         tessellator.startDrawingQuads();
/* 319:359 */         width = fontrenderer.getStringWidth(s) / 2;
/* 320:360 */         tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
/* 321:361 */         tessellator.addVertex(-width - 1.0F, -1 + byte0, 0.0D);
/* 322:362 */         tessellator.addVertex(-width - 1.0F, 8 + byte0, 0.0D);
/* 323:363 */         tessellator.addVertex(width + 1.0F, 8 + byte0, 0.0D);
/* 324:364 */         tessellator.addVertex(width + 1.0F, -1 + byte0, 0.0D);
/* 325:365 */         tessellator.draw();
/* 326:366 */         GL11.glEnable(3553);
/* 327:367 */         fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, 553648127);
/* 328:368 */         GL11.glEnable(2929);
/* 329:369 */         GL11.glDepthMask(true);
/* 330:370 */         fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, -1);
/* 331:    */       }
/* 332:372 */       GL11.glEnable(2929);
/* 333:373 */       GL11.glEnable(3553);
/* 334:374 */       GL11.glEnable(2896);
/* 335:375 */       GL11.glDisable(3042);
/* 336:376 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 337:377 */       GL11.glPopMatrix();
/* 338:    */     }
/* 339:    */   }
/* 340:    */   
/* 341:381 */   protected ResourceLocation texture = new ResourceLocation("chocolatequest:textures/entity/pirate.png");
/* 342:    */   
/* 343:    */   protected ResourceLocation getEntityTexture(Entity par1Entity)
/* 344:    */   {
/* 345:384 */     return this.texture;
/* 346:    */   }
/* 347:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.rendererHuman.RenderHuman
 * JD-Core Version:    0.7.1
 */