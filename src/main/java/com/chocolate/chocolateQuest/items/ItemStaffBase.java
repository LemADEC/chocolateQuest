/*   1:    */ package com.chocolate.chocolateQuest.items;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.ICooldownTracker;
/*   4:    */ import com.chocolate.chocolateQuest.API.IRangedWeapon;
/*   5:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   6:    */ import com.chocolate.chocolateQuest.gui.InventoryBag;
/*   7:    */ import com.chocolate.chocolateQuest.items.gun.ILoadableGun;
/*   8:    */ import com.chocolate.chocolateQuest.magic.Awakements;
/*   9:    */ import com.chocolate.chocolateQuest.magic.Elements;
/*  10:    */ import com.chocolate.chocolateQuest.magic.SpellBase;
/*  11:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  12:    */ import com.chocolate.chocolateQuest.utils.HelperPlayer;
/*  13:    */ import cpw.mods.fml.relauncher.Side;
/*  14:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  15:    */ import java.util.List;
/*  16:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  17:    */ import net.minecraft.creativetab.CreativeTabs;
/*  18:    */ import net.minecraft.entity.Entity;
/*  19:    */ import net.minecraft.entity.EntityLivingBase;
/*  20:    */ import net.minecraft.entity.player.EntityPlayer;
/*  21:    */ import net.minecraft.item.EnumAction;
/*  22:    */ import net.minecraft.item.EnumRarity;
/*  23:    */ import net.minecraft.item.Item;
/*  24:    */ import net.minecraft.item.ItemStack;
/*  25:    */ import net.minecraft.nbt.NBTTagCompound;
/*  26:    */ import net.minecraft.world.World;
/*  27:    */ 
/*  28:    */ public class ItemStaffBase
/*  29:    */   extends Item
/*  30:    */   implements ILoadableGun, IRangedWeapon, ICooldownTracker
/*  31:    */ {
/*  32: 33 */   int cooldown = 10;
/*  33:    */   Elements element;
/*  34:    */   CoolDownTracker cachedTracker;
/*  35:    */   
/*  36:    */   public ItemStaffBase()
/*  37:    */   {
/*  38: 38 */     setMaxStackSize(1);
/*  39:    */   }
/*  40:    */   
/*  41:    */   public ItemStaffBase(Elements element)
/*  42:    */   {
/*  43: 42 */     this();
/*  44: 43 */     this.element = element;
/*  45:    */   }
/*  46:    */   
/*  47:    */   @SideOnly(Side.CLIENT)
/*  48:    */   public void registerIcons(IIconRegister iconRegister)
/*  49:    */   {
/*  50: 50 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:" + getUnlocalizedName().replace("item.", ""));
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void addInformation(ItemStack is, EntityPlayer player, List list, boolean par4)
/*  54:    */   {
/*  55: 55 */     super.addInformation(is, player, list, par4);
/*  56: 56 */     for (Awakements a : Awakements.awekements) {
/*  57: 57 */       if (Awakements.hasEnchant(is, a)) {
/*  58: 58 */         list.add(a.getDescription(is));
/*  59:    */       }
/*  60:    */     }
/*  61:    */   }
/*  62:    */   
/*  63:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
/*  64:    */   {
/*  65: 66 */     if (entityPlayer.isSneaking())
/*  66:    */     {
/*  67: 67 */       ItemStack[] ammo = InventoryBag.getCargo(itemstack);
/*  68: 68 */       boolean isEmpty = true;
/*  69: 69 */       for (int i = 0; i < ammo.length; i++) {
/*  70: 71 */         if (ammo[i] != null)
/*  71:    */         {
/*  72: 72 */           isEmpty = false;
/*  73: 73 */           break;
/*  74:    */         }
/*  75:    */       }
/*  76: 76 */       if (!isEmpty)
/*  77:    */       {
/*  78: 77 */         ItemStack[] ammoNew = InventoryBag.getCargo(itemstack);
/*  79: 78 */         int last = 0;
/*  80: 79 */         ItemStack lastItem = ammo[0];
/*  81: 80 */         for (int i = 1; i < ammo.length; i++) {
/*  82: 82 */           if (ammo[i] != null)
/*  83:    */           {
/*  84: 83 */             ammoNew[last] = ammo[i];
/*  85: 84 */             last++;
/*  86:    */           }
/*  87:    */         }
/*  88: 87 */         ammoNew[last] = lastItem;
/*  89: 88 */         InventoryBag.saveCargo(itemstack, ammoNew);
/*  90:    */       }
/*  91:    */       else
/*  92:    */       {
/*  93: 90 */         entityPlayer.openGui(ChocolateQuest.instance, 3, entityPlayer.worldObj, 0, 0, 0);
/*  94:    */       }
/*  95: 91 */       return itemstack;
/*  96:    */     }
/*  97: 93 */     ItemStack[] cargo = InventoryBag.getCargo(itemstack);
/*  98: 94 */     if ((cargo[0] != null) && 
/*  99: 95 */       (cargo[0].stackTagCompound == null))
/* 100:    */     {
/* 101: 96 */       SpellBase spell = getSpell(itemstack);
/* 102: 97 */       spell.onCastStart(entityPlayer, this.element, itemstack);
/* 103: 98 */       entityPlayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
/* 104:    */     }
/* 105:102 */     return super.onItemRightClick(itemstack, world, entityPlayer);
/* 106:    */   }
/* 107:    */   
/* 108:    */   public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityPlayer, int useTime)
/* 109:    */   {
/* 110:107 */     useTime = getMaxItemUseDuration(itemstack) - useTime;
/* 111:108 */     useTime = Math.min(useTime + 1, 60);
/* 112:    */     
/* 113:110 */     SpellBase spell = getSpell(itemstack);
/* 114:111 */     if ((spell != null) && 
/* 115:112 */       (spell.isProjectile()))
/* 116:    */     {
/* 117:113 */       spell.onShoot(entityPlayer, getElement(itemstack), itemstack, useTime);
/* 118:114 */       ItemStack[] spellsStack = InventoryBag.getCargo(itemstack);
/* 119:115 */       if (spellsStack[0].stackTagCompound == null) {
/* 120:116 */         spellsStack[0].stackTagCompound = new NBTTagCompound();
/* 121:    */       }
/* 122:117 */       spellsStack[0].stackTagCompound.setInteger("cd", spell.getCoolDown());
/* 123:118 */       InventoryBag.saveCargo(itemstack, spellsStack);
/* 124:    */     }
/* 125:    */   }
/* 126:    */   
/* 127:    */   public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
/* 128:    */   {
/* 129:125 */     if ((entity instanceof EntityPlayer))
/* 130:    */     {
/* 131:126 */       EntityPlayer player = (EntityPlayer)entity;
/* 132:127 */       if ((player.isUsingItem()) && (player.getItemInUse() == itemStack))
/* 133:    */       {
/* 134:128 */         SpellBase spell = getSpell(itemStack);
/* 135:129 */         if ((spell != null) && 
/* 136:130 */           (spell.shouldUpdate())) {
/* 137:131 */           spell.onUpdate((EntityLivingBase)entity, getElement(itemStack), itemStack, 30);
/* 138:    */         }
/* 139:    */       }
/* 140:    */       else
/* 141:    */       {
/* 142:135 */         NBTTagCompound tag = itemStack.stackTagCompound;
/* 143:136 */         if (tag != null)
/* 144:    */         {
/* 145:138 */           ItemStack[] cargo = InventoryBag.getCargo(itemStack);
/* 146:139 */           for (int i = 0; i < cargo.length; i++) {
/* 147:140 */             if ((cargo[i] != null) && 
/* 148:141 */               (cargo[i].stackTagCompound != null))
/* 149:    */             {
/* 150:142 */               int cd = cargo[i].stackTagCompound.getInteger("cd");
/* 151:143 */               if (cd > 0) {
/* 152:144 */                 cargo[i].stackTagCompound.setInteger("cd", cd - 1);
/* 153:    */               } else {
/* 154:146 */                 cargo[i].stackTagCompound = null;
/* 155:    */               }
/* 156:    */             }
/* 157:    */           }
/* 158:150 */           InventoryBag.saveCargo(itemStack, cargo);
/* 159:151 */           if (cargo[0] != null) {
/* 160:152 */             if (cargo[0].stackTagCompound != null)
/* 161:    */             {
/* 162:153 */               int cd = cargo[0].stackTagCompound.getInteger("cd");
/* 163:154 */               if (cd > 0) {
/* 164:155 */                 itemStack.setItemDamage(cd);
/* 165:    */               } else {
/* 166:157 */                 itemStack.setItemDamage(-1);
/* 167:    */               }
/* 168:    */             }
/* 169:    */             else
/* 170:    */             {
/* 171:160 */               itemStack.setItemDamage(-1);
/* 172:    */             }
/* 173:    */           }
/* 174:    */         }
/* 175:    */       }
/* 176:    */     }
/* 177:    */     else
/* 178:    */     {
/* 179:166 */       onUpdateEntity(itemStack, world, entity, par4, par5);
/* 180:    */     }
/* 181:168 */     super.onUpdate(itemStack, world, entity, par4, par5);
/* 182:    */   }
/* 183:    */   
/* 184:    */   public int getMaxItemUseDuration(ItemStack par1ItemStack)
/* 185:    */   {
/* 186:173 */     return 72000;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public EnumAction getItemUseAction(ItemStack par1ItemStack)
/* 190:    */   {
/* 191:179 */     return EnumAction.bow;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public boolean isDamageable()
/* 195:    */   {
/* 196:185 */     return true;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public int getMaxDamage()
/* 200:    */   {
/* 201:191 */     return 100;
/* 202:    */   }
/* 203:    */   
/* 204:    */   public int getDisplayDamage(ItemStack stack)
/* 205:    */   {
/* 206:196 */     ItemStack[] cargo = InventoryBag.getCargo(stack);
/* 207:197 */     if (cargo[0] != null)
/* 208:    */     {
/* 209:198 */       if (cargo[0].stackTagCompound != null)
/* 210:    */       {
/* 211:199 */         SpellBase spell = getSpell(stack);
/* 212:200 */         int damage = cargo[0].stackTagCompound.getInteger("cd");
/* 213:201 */         int maxCD = spell.getCoolDown();
/* 214:202 */         return damage * 100 / maxCD;
/* 215:    */       }
/* 216:204 */       return 100;
/* 217:    */     }
/* 218:206 */     return 0;
/* 219:    */   }
/* 220:    */   
/* 221:    */   public String getItemStackDisplayName(ItemStack itemstack)
/* 222:    */   {
/* 223:210 */     return BDHelper.StringColor(this.element.stringColor) + super.getItemStackDisplayName(itemstack) + BDHelper.StringColor("r");
/* 224:    */   }
/* 225:    */   
/* 226:    */   public Entity getTarget(EntityPlayer ep, World world)
/* 227:    */   {
/* 228:215 */     return HelperPlayer.getTarget(ep, world, 30.0D);
/* 229:    */   }
/* 230:    */   
/* 231:    */   public boolean isFull3D()
/* 232:    */   {
/* 233:223 */     return true;
/* 234:    */   }
/* 235:    */   
/* 236:    */   public CreativeTabs getCreativeTab()
/* 237:    */   {
/* 238:229 */     return ChocolateQuest.tabItems;
/* 239:    */   }
/* 240:    */   
/* 241:    */   public EnumRarity getRarity(ItemStack itemstack)
/* 242:    */   {
/* 243:234 */     return EnumRarity.rare;
/* 244:    */   }
/* 245:    */   
/* 246:    */   public SpellBase getSpell(ItemStack is)
/* 247:    */   {
/* 248:238 */     ItemStack[] ammo = InventoryBag.getCargo(is);
/* 249:239 */     if (ammo[0] == null) {
/* 250:240 */       return null;
/* 251:    */     }
/* 252:241 */     return SpellBase.getSpellByID(ammo[0].getItemDamage());
/* 253:    */   }
/* 254:    */   
/* 255:    */   public Elements getElement(ItemStack is)
/* 256:    */   {
/* 257:245 */     if (this.element != null) {
/* 258:246 */       return this.element;
/* 259:    */     }
/* 260:247 */     return Elements.light;
/* 261:    */   }
/* 262:    */   
/* 263:    */   public int getAmmoLoaderStackSize(ItemStack is)
/* 264:    */   {
/* 265:252 */     return 1;
/* 266:    */   }
/* 267:    */   
/* 268:    */   public int getAmmoLoaderAmmount(ItemStack is)
/* 269:    */   {
/* 270:256 */     return 4;
/* 271:    */   }
/* 272:    */   
/* 273:    */   public boolean isValidAmmo(ItemStack is)
/* 274:    */   {
/* 275:260 */     return is.getItem() == ChocolateQuest.spell;
/* 276:    */   }
/* 277:    */   
/* 278:    */   public float getRange(EntityLivingBase shooter, ItemStack is)
/* 279:    */   {
/* 280:266 */     int maxRange = 0;
/* 281:267 */     if ((this.cachedTracker != null) && (this.cachedTracker.castingSpell != null))
/* 282:    */     {
/* 283:268 */       maxRange = this.cachedTracker.castingSpell.getRange(is);
/* 284:    */     }
/* 285:    */     else
/* 286:    */     {
/* 287:270 */       ItemStack[] spells = InventoryBag.getCargo(is);
/* 288:271 */       for (int i = 0; i < spells.length; i++) {
/* 289:273 */         if (spells[i] != null) {
/* 290:274 */           maxRange = Math.max(maxRange, SpellBase.getSpellByID(spells[i].getItemDamage()).getRange(is));
/* 291:    */         }
/* 292:    */       }
/* 293:    */     }
/* 294:277 */     return maxRange * maxRange;
/* 295:    */   }
/* 296:    */   
/* 297:    */   public int getCooldown(EntityLivingBase shooter, ItemStack is)
/* 298:    */   {
/* 299:281 */     return 10;
/* 300:    */   }
/* 301:    */   
/* 302:    */   public void shootFromEntity(EntityLivingBase shooter, ItemStack is, int angle, Entity target)
/* 303:    */   {
/* 304:285 */     if (this.cachedTracker.castingSpell != null)
/* 305:    */     {
/* 306:286 */       shooter.rotationYaw = shooter.rotationYawHead;
/* 307:287 */       this.cachedTracker.castingSpell.onShoot(shooter, getElement(is), is, 5);
/* 308:    */     }
/* 309:289 */     this.cachedTracker.castingSpell = null;
/* 310:    */   }
/* 311:    */   
/* 312:    */   public boolean canBeUsedByEntity(Entity entity)
/* 313:    */   {
/* 314:293 */     return true;
/* 315:    */   }
/* 316:    */   
/* 317:    */   public boolean isMeleeWeapon(EntityLivingBase shooter, ItemStack is)
/* 318:    */   {
/* 319:297 */     return false;
/* 320:    */   }
/* 321:    */   
/* 322:    */   public boolean shouldUpdate(EntityLivingBase shooter)
/* 323:    */   {
/* 324:301 */     return true;
/* 325:    */   }
/* 326:    */   
/* 327:    */   public int startAiming(ItemStack is, EntityLivingBase shooter, Entity target)
/* 328:    */   {
/* 329:305 */     for (int i = 0; i < this.cachedTracker.spells.length; i++) {
/* 330:307 */       if (this.cachedTracker.cooldowns[i] == 0)
/* 331:    */       {
/* 332:309 */         SpellBase spell = this.cachedTracker.spells[i];
/* 333:310 */         if (spell != null)
/* 334:    */         {
/* 335:311 */           double dist = shooter.getDistanceSqToEntity(target);
/* 336:312 */           if (dist < spell.getRange(is) * spell.getRange(is))
/* 337:    */           {
/* 338:313 */             this.cachedTracker.castingSpell = spell;
/* 339:314 */             this.cachedTracker.cooldowns[i] += spell.getCoolDown() * 2 + 10;
/* 340:315 */             spell.onCastStart(shooter, getElement(is), is);
/* 341:316 */             return spell.getCastingTime();
/* 342:    */           }
/* 343:    */         }
/* 344:    */       }
/* 345:    */     }
/* 346:322 */     return -1;
/* 347:    */   }
/* 348:    */   
/* 349:    */   private void onUpdateEntity(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
/* 350:    */   {
/* 351:326 */     if (this.cachedTracker != null)
/* 352:    */     {
/* 353:327 */       SpellBase spell = this.cachedTracker.castingSpell;
/* 354:328 */       if ((spell != null) && 
/* 355:329 */         (spell.shouldUpdate())) {
/* 356:330 */         spell.onUpdate((EntityLivingBase)entity, getElement(itemStack), itemStack, par4);
/* 357:    */       }
/* 358:    */     }
/* 359:    */   }
/* 360:    */   
/* 361:    */   public Object getCooldownTracker(ItemStack is, Entity entity)
/* 362:    */   {
/* 363:338 */     return new CoolDownTracker(is);
/* 364:    */   }
/* 365:    */   
/* 366:    */   public void onUpdateCooldown(ItemStack is, Entity entity, Object tracker)
/* 367:    */   {
/* 368:343 */     this.cachedTracker = ((CoolDownTracker)tracker);
/* 369:344 */     this.cachedTracker.onUpdate();
/* 370:    */   }
/* 371:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemStaffBase
 * JD-Core Version:    0.7.1
 */