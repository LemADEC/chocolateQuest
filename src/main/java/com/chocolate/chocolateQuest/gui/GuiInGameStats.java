/*   1:    */ package com.chocolate.chocolateQuest.gui;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.items.ItemArmorHelmetScouter;
/*   5:    */ import com.chocolate.chocolateQuest.items.gun.ILoadableGun;
/*   6:    */ import com.chocolate.chocolateQuest.magic.IElementWeak;
/*   7:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   8:    */ import cpw.mods.fml.common.eventhandler.EventPriority;
/*   9:    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*  10:    */ import net.minecraft.client.Minecraft;
/*  11:    */ import net.minecraft.client.gui.Gui;
/*  12:    */ import net.minecraft.client.renderer.ItemRenderer;
/*  13:    */ import net.minecraft.client.renderer.Tessellator;
/*  14:    */ import net.minecraft.client.renderer.texture.TextureManager;
/*  15:    */ import net.minecraft.enchantment.Enchantment;
/*  16:    */ import net.minecraft.enchantment.EnchantmentHelper;
/*  17:    */ import net.minecraft.entity.EntityLivingBase;
/*  18:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  19:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  20:    */ import net.minecraft.entity.player.EntityPlayer;
/*  21:    */ import net.minecraft.item.Item;
/*  22:    */ import net.minecraft.item.ItemStack;
/*  23:    */ import net.minecraft.util.DamageSource;
/*  24:    */ import net.minecraft.util.IIcon;
/*  25:    */ import net.minecraft.util.ResourceLocation;
/*  26:    */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*  27:    */ import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
/*  28:    */ import org.lwjgl.opengl.GL11;
/*  29:    */ 
/*  30:    */ public class GuiInGameStats
/*  31:    */   extends Gui
/*  32:    */ {
/*  33: 32 */   public static final ResourceLocation potionEffectIcons = new ResourceLocation("gui/container/inventory.png");
/*  34:    */   private Minecraft mc;
/*  35:    */   private int xPos;
/*  36:    */   private int yPos;
/*  37:    */   private static final int ICON_SIZE = 16;
/*  38:    */   private static final int ICON_SPACING = 18;
/*  39:    */   private static final int ICONS_PER_ROW = 16;
/*  40:    */   private static final int COLOR = 16777215;
/*  41:    */   
/*  42:    */   public GuiInGameStats(Minecraft mc)
/*  43:    */   {
/*  44: 40 */     this.mc = mc;
/*  45:    */   }
/*  46:    */   
/*  47:    */   @SubscribeEvent(priority=EventPriority.NORMAL)
/*  48:    */   public void onRenderExperienceBar(RenderGameOverlayEvent event)
/*  49:    */   {
/*  50: 60 */     if ((event.isCancelable()) || (event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE)) {
/*  51: 62 */       return;
/*  52:    */     }
/*  53: 65 */     GL11.glPushMatrix();
/*  54: 66 */     GL11.glEnable(3042);
/*  55: 67 */     GL11.glBlendFunc(770, 771);
/*  56: 68 */     EntityPlayer player = Minecraft.getMinecraft().thePlayer;
/*  57: 69 */     if (player.getCurrentEquippedItem() != null)
/*  58:    */     {
/*  59: 71 */       ItemStack itemstack = player.getCurrentEquippedItem();
/*  60: 72 */       if ((itemstack.getItem() instanceof ILoadableGun))
/*  61:    */       {
/*  62: 73 */         ItemStack[] cargo = InventoryBag.getCargo(itemstack);
/*  63: 74 */         int despX = 0;
/*  64: 75 */         for (int i = 0; i < cargo.length; i++) {
/*  65: 76 */           if (cargo[i] != null)
/*  66:    */           {
/*  67: 77 */             ItemStack cargoItem = cargo[i];
/*  68: 78 */             GL11.glPushMatrix();
/*  69: 79 */             float scale = 16.0F;
/*  70: 80 */             GL11.glTranslatef(scale + despX, scale, 0.0F);
/*  71: 81 */             GL11.glScalef(-scale, -scale, 0.0F);
/*  72: 82 */             ResourceLocation resourcelocation = this.mc.renderEngine.getResourceLocation(cargoItem.getItemSpriteNumber());
/*  73:    */             
/*  74: 84 */             int renderPasses = cargoItem.getItem().getRenderPasses(cargoItem.getItemDamage());
/*  75: 85 */             for (int f = 0; f < renderPasses; f++)
/*  76:    */             {
/*  77: 86 */               int color = cargoItem.getItem().getColorFromItemStack(cargoItem, f);
/*  78: 87 */               GL11.glColor4f(BDHelper.getColorRed(color), BDHelper.getColorGreen(color), BDHelper.getColorBlue(color), 1.0F);
/*  79: 88 */               IIcon icon = cargoItem.getItem().getIconFromDamageForRenderPass(cargoItem.getItemDamage(), f);
/*  80: 89 */               this.mc.renderEngine.bindTexture(resourcelocation);
/*  81: 90 */               ItemRenderer.renderItemIn2D(Tessellator.instance, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
/*  82:    */             }
/*  83: 92 */             GL11.glPopMatrix();
/*  84: 93 */             if (cargoItem.stackSize > 1) {
/*  85: 94 */               drawString(this.mc.fontRenderer, cargoItem.stackSize + "", despX + 8, 8, 16777215);
/*  86:    */             }
/*  87: 96 */             despX = (int)(despX + scale);
/*  88:    */           }
/*  89:    */         }
/*  90: 99 */         GL11.glTranslatef(0.0F, 16.0F, 0.0F);
/*  91:    */       }
/*  92:    */     }
/*  93:102 */     if (ItemArmorHelmetScouter.target != null) {
/*  94:104 */       drawScouterInfo();
/*  95:    */     }
/*  96:106 */     GL11.glPopMatrix();
/*  97:    */   }
/*  98:    */   
/*  99:    */   public int drawScouterInfo()
/* 100:    */   {
/* 101:110 */     GL11.glPushMatrix();
/* 102:111 */     float scale = 1.0F;
/* 103:112 */     GL11.glScalef(scale, scale, scale);
/* 104:113 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 105:114 */     GL11.glDisable(2896);
/* 106:115 */     this.xPos = 2;
/* 107:116 */     this.yPos = 2;
/* 108:    */     
/* 109:    */ 
/* 110:119 */     this.mc.renderEngine.bindTexture(BDHelper.guiButtonsTexture);
/* 111:    */     
/* 112:121 */     int iconBar = 25;
/* 113:122 */     for (int i = 0; i < 4; i++) {
/* 114:123 */       drawTexturedModalRect(this.xPos, this.yPos + 16 * i, iconBar % 16 * 16, iconBar / 16 * 16, 64, 16);
/* 115:    */     }
/* 116:127 */     for (int i = 0; i < 3; i++) {
/* 117:128 */       drawIcon(iconBar + 4 + i, this.xPos, this.yPos + 16 * (i + 1));
/* 118:    */     }
/* 119:130 */     int TEXT_OFFSET = 3;
/* 120:131 */     int xResist = this.xPos + 16;
/* 121:132 */     int yResist = this.yPos + 64;
/* 122:133 */     int icon = 9;
/* 123:134 */     drawIcon(icon + 1, this.xPos, yResist);
/* 124:135 */     drawIcon(icon + 2, this.xPos + 32, yResist);
/* 125:136 */     drawIcon(icon + 3, this.xPos, yResist + 16);
/* 126:137 */     drawIcon(icon + 4, this.xPos + 32, yResist + 16);
/* 127:138 */     drawIcon(icon + 5, this.xPos, yResist + 32);
/* 128:139 */     drawIcon(icon + 6, this.xPos + 32, yResist + 32);
/* 129:140 */     drawIcon(icon, xResist, yResist);
/* 130:141 */     drawIcon(icon, xResist + 32, yResist);
/* 131:142 */     drawIcon(icon, xResist, yResist + 16);
/* 132:143 */     drawIcon(icon, xResist + 32, yResist + 16);
/* 133:144 */     drawIcon(icon, xResist, yResist + 32);
/* 134:145 */     drawIcon(icon, xResist + 32, yResist + 32);
/* 135:    */     
/* 136:    */ 
/* 137:148 */     EntityLivingBase el = ItemArmorHelmetScouter.target;
/* 138:149 */     String s = el.getCommandSenderName();
/* 139:150 */     drawStat(s);
/* 140:    */     
/* 141:152 */     drawStat("   " + toString(el.getHealth()) + "/" + toString(el.getMaxHealth()));
/* 142:    */     
/* 143:154 */     double damage = 0.0D;
/* 144:155 */     int baseDamage = 0;
/* 145:156 */     IAttributeInstance attribute = el.getEntityAttribute(SharedMonsterAttributes.attackDamage);
/* 146:157 */     if (attribute != null)
/* 147:    */     {
/* 148:158 */       damage = attribute.getAttributeValue();
/* 149:159 */       baseDamage = (int)attribute.getBaseValue();
/* 150:160 */       ItemStack weapon = el.getEquipmentInSlot(0);
/* 151:161 */       if (weapon != null) {
/* 152:162 */         damage += BDHelper.getWeaponDamage(weapon);
/* 153:    */       }
/* 154:    */     }
/* 155:165 */     drawStat("   " + damage + " (" + baseDamage + ")");
/* 156:    */     
/* 157:167 */     drawStat("     " + el.getTotalArmorValue());
/* 158:    */     
/* 159:169 */     this.yPos += 16;
/* 160:170 */     int protection = getEnchantmentProtection(Enchantment.protection, el, this.physic);
/* 161:171 */     int fireProtection = getEnchantmentProtection(Enchantment.fireProtection, el, this.fire);
/* 162:172 */     int projectileProtection = getEnchantmentProtection(Enchantment.projectileProtection, el, this.projectile);
/* 163:173 */     int blastProtection = getEnchantmentProtection(Enchantment.blastProtection, el, this.blast);
/* 164:174 */     int magicProtection = getEnchantmentProtection(ChocolateQuest.enchantmentMagicDefense, el, this.magic);
/* 165:175 */     int physicProtection = 0;
/* 166:176 */     if ((el instanceof IElementWeak))
/* 167:    */     {
/* 168:177 */       projectileProtection += ((IElementWeak)el).getProjectileDefense();
/* 169:178 */       fireProtection += ((IElementWeak)el).getFireDefense();
/* 170:179 */       blastProtection += ((IElementWeak)el).getBlastDefense();
/* 171:180 */       magicProtection += ((IElementWeak)el).getMagicDefense();
/* 172:181 */       physicProtection += ((IElementWeak)el).getPhysicDefense();
/* 173:    */     }
/* 174:184 */     String text = protection + "";
/* 175:185 */     drawString(this.mc.fontRenderer, text, xResist + TEXT_OFFSET, yResist + TEXT_OFFSET, 16777215);
/* 176:186 */     text = projectileProtection + "";
/* 177:187 */     drawString(this.mc.fontRenderer, text, xResist + 32 + TEXT_OFFSET, yResist + TEXT_OFFSET, 16777215);
/* 178:188 */     text = physicProtection + "";
/* 179:189 */     drawString(this.mc.fontRenderer, text, xResist + TEXT_OFFSET, yResist + 16 + TEXT_OFFSET, 16777215);
/* 180:190 */     text = fireProtection + "";
/* 181:191 */     drawString(this.mc.fontRenderer, text, xResist + 32 + TEXT_OFFSET, yResist + 16 + TEXT_OFFSET, 16777215);
/* 182:192 */     text = blastProtection + "";
/* 183:193 */     drawString(this.mc.fontRenderer, text, xResist + TEXT_OFFSET, yResist + 32 + TEXT_OFFSET, 16777215);
/* 184:194 */     text = magicProtection + "";
/* 185:195 */     drawString(this.mc.fontRenderer, text, xResist + 32 + TEXT_OFFSET, yResist + 32 + TEXT_OFFSET, 16777215);
/* 186:    */     
/* 187:197 */     ItemArmorHelmetScouter.targetTimer -= 1;
/* 188:198 */     if (ItemArmorHelmetScouter.targetTimer == 0) {
/* 189:199 */       ItemArmorHelmetScouter.target = null;
/* 190:    */     }
/* 191:232 */     GL11.glPopMatrix();
/* 192:233 */     return 0;
/* 193:    */   }
/* 194:    */   
/* 195:235 */   DamageSource physic = new DamageSource("");
/* 196:236 */   DamageSource fire = new DamageSource("").setFireDamage();
/* 197:237 */   DamageSource blast = new DamageSource("").setExplosion();
/* 198:238 */   DamageSource magic = new DamageSource("").setMagicDamage();
/* 199:239 */   DamageSource projectile = new DamageSource("").setProjectile();
/* 200:    */   
/* 201:    */   public int getEnchantmentProtection(Enchantment enchantment, EntityLivingBase el, DamageSource ds)
/* 202:    */   {
/* 203:242 */     int f = 0;
/* 204:243 */     for (int i = 1; i <= 4; i++)
/* 205:    */     {
/* 206:244 */       ItemStack is = el.getEquipmentInSlot(i);
/* 207:245 */       if (is != null)
/* 208:    */       {
/* 209:247 */         int lvl = EnchantmentHelper.getEnchantmentLevel(enchantment.effectId, is);
/* 210:248 */         if (lvl > 0) {
/* 211:249 */           f += enchantment.calcModifierDamage(lvl, ds);
/* 212:    */         }
/* 213:    */       }
/* 214:    */     }
/* 215:252 */     return f;
/* 216:    */   }
/* 217:    */   
/* 218:    */   public void drawIcon(int icon, int xPos, int yPos)
/* 219:    */   {
/* 220:256 */     drawTexturedModalRect(xPos, yPos, icon % 16 * 16, icon / 16 * 16, 16, 16);
/* 221:    */   }
/* 222:    */   
/* 223:    */   public void drawStat(String text)
/* 224:    */   {
/* 225:260 */     if (text.length() > 14) {
/* 226:261 */       text = text.substring(0, 12);
/* 227:    */     }
/* 228:262 */     drawString(this.mc.fontRenderer, text, this.xPos + 3, this.yPos + 3, 16777215);
/* 229:263 */     this.yPos += 16;
/* 230:    */   }
/* 231:    */   
/* 232:    */   public String toString(double d)
/* 233:    */   {
/* 234:267 */     String value = Double.toString(d);
/* 235:268 */     int index = value.indexOf(".");
/* 236:269 */     if (index < 3) {
/* 237:270 */       index += 2;
/* 238:    */     }
/* 239:272 */     value = value.substring(0, index);
/* 240:    */     
/* 241:274 */     return value;
/* 242:    */   }
/* 243:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiInGameStats
 * JD-Core Version:    0.7.1
 */