/*   1:    */ package com.chocolate.chocolateQuest.items;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.BuilderBase;
/*   4:    */ import com.chocolate.chocolateQuest.API.DungeonBase;
/*   5:    */ import com.chocolate.chocolateQuest.API.DungeonRegister;
/*   6:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   7:    */ import com.chocolate.chocolateQuest.WorldGeneratorNew;
/*   8:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*   9:    */ import com.chocolate.chocolateQuest.entity.boss.EntityBull;
/*  10:    */ import com.chocolate.chocolateQuest.entity.boss.EntityGiantBoxer;
/*  11:    */ import com.chocolate.chocolateQuest.entity.boss.EntitySlimeBoss;
/*  12:    */ import com.chocolate.chocolateQuest.entity.boss.EntitySpiderBoss;
/*  13:    */ import com.chocolate.chocolateQuest.entity.boss.EntityTurtle;
/*  14:    */ import com.chocolate.chocolateQuest.entity.boss.EntityWyvern;
/*  15:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanGremlin;
/*  16:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanPigZombie;
/*  17:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanPirate;
/*  18:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanSkeleton;
/*  19:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanSpecter;
/*  20:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanWalker;
/*  21:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanZombie;
/*  22:    */ import com.chocolate.chocolateQuest.entity.mob.EntityLich;
/*  23:    */ import com.chocolate.chocolateQuest.entity.mob.EntityNecromancer;
/*  24:    */ import com.chocolate.chocolateQuest.entity.mob.EntityPirateBoss;
/*  25:    */ import com.chocolate.chocolateQuest.entity.mob.EntitySpecterBoss;
/*  26:    */ import com.chocolate.chocolateQuest.entity.mob.EntityWalkerBoss;
/*  27:    */ import com.chocolate.chocolateQuest.entity.npc.EntityHumanDummy;
/*  28:    */ import com.chocolate.chocolateQuest.misc.EquipementHelper;
/*  29:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*  30:    */ import cpw.mods.fml.relauncher.Side;
/*  31:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  32:    */ import java.io.File;
/*  33:    */ import java.lang.reflect.Constructor;
/*  34:    */ import java.util.ArrayList;
/*  35:    */ import java.util.List;
/*  36:    */ import java.util.Random;
/*  37:    */ import net.minecraft.block.BlockDispenser;
/*  38:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  39:    */ import net.minecraft.creativetab.CreativeTabs;
/*  40:    */ import net.minecraft.dispenser.IBehaviorDispenseItem;
/*  41:    */ import net.minecraft.dispenser.IBlockSource;
/*  42:    */ import net.minecraft.entity.Entity;
/*  43:    */ import net.minecraft.entity.EntityLiving;
/*  44:    */ import net.minecraft.entity.player.EntityPlayer;
/*  45:    */ import net.minecraft.item.Item;
/*  46:    */ import net.minecraft.item.ItemStack;
/*  47:    */ import net.minecraft.nbt.NBTTagCompound;
/*  48:    */ import net.minecraft.util.AxisAlignedBB;
/*  49:    */ import net.minecraft.util.ChatComponentText;
/*  50:    */ import net.minecraft.util.EnumFacing;
/*  51:    */ import net.minecraft.util.IIcon;
/*  52:    */ import net.minecraft.util.IRegistry;
/*  53:    */ import net.minecraft.util.MathHelper;
/*  54:    */ import net.minecraft.util.MovingObjectPosition;
/*  55:    */ import net.minecraft.util.Vec3;
/*  56:    */ import net.minecraft.world.World;
/*  57:    */ 
/*  58:    */ public class ItemEggBD
/*  59:    */   extends Item
/*  60:    */   implements IBehaviorDispenseItem
/*  61:    */ {
/*  62: 60 */   final int leather = 1;
/*  63: 60 */   final int chain = 2;
/*  64: 60 */   final int gold = 3;
/*  65: 60 */   final int iron = 4;
/*  66: 60 */   final int diamond = 5;
/*  67: 60 */   final int unequipped = 0;
/*  68: 61 */   int OFF_BOSS_HUMAN = 700;
/*  69: 62 */   int OFF_BOSS = 800;
/*  70: 63 */   int OFF_DUNGEON = 900;
/*  71: 65 */   int cooldown = 0;
/*  72:    */   
/*  73:    */   public ItemEggBD()
/*  74:    */   {
/*  75: 69 */     BlockDispenser.dispenseBehaviorRegistry.putObject(this, this);
/*  76: 70 */     setHasSubtypes(true);
/*  77:    */   }
/*  78:    */   
/*  79: 77 */   MobData[] mobs = { new MobData(EntityHumanDummy.class, "Dummy", 0), new MobData(EntityHumanSkeleton.class, "Skeleton", 8), new MobData(EntityHumanZombie.class, "Zombie", 7), new MobData(EntityHumanSpecter.class, "Specter", 12), new MobData(EntityHumanWalker.class, "Abyss Walker", 10), new MobData(EntityHumanGremlin.class, "Gremlin", 11), new MobData(EntityHumanPirate.class, "Pirate", 9), new MobData(EntityHumanPigZombie.class, "PigZombie", 3) };
/*  80: 86 */   MobData[] humanBosses = { new MobData(EntityNecromancer.class, "Necromancer", 8), new MobData(EntityLich.class, "Lich", 7), new MobData(EntityPirateBoss.class, "Pirate Boss", 9), new MobData(EntityWalkerBoss.class, "Abyss Walker Boss", 10), new MobData(EntitySpecterBoss.class, "Specter Boss", 12) };
/*  81: 93 */   MobData[] bosses = { new MobData(EntityTurtle.class, "Turtle", 3), new MobData(EntityGiantBoxer.class, "Giant", 2), new MobData(EntityBull.class, "Bull", 4), new MobData(EntitySlimeBoss.class, "Slime", 1), new MobData(EntitySpiderBoss.class, "Spider", 0), new MobData(EntityWyvern.class, "Dragon", 5) };
/*  82:    */   public static final int MICROPHONE = 499;
/*  83:    */   String[] name;
/*  84:    */   String[] bossName;
/*  85:    */   IIcon[] bossIcon;
/*  86:    */   IIcon[] dungeonIcon;
/*  87:    */   
/*  88:    */   @SideOnly(Side.CLIENT)
/*  89:    */   public void registerIcons(IIconRegister iconRegister)
/*  90:    */   {
/*  91:105 */     this.bossIcon = new IIcon[7];
/*  92:106 */     for (int i = 0; i < this.bossIcon.length; i++) {
/*  93:108 */       this.bossIcon[i] = iconRegister.registerIcon("chocolatequest:b" + i);
/*  94:    */     }
/*  95:111 */     this.dungeonIcon = new IIcon[12];
/*  96:112 */     for (int i = 0; i < this.dungeonIcon.length; i++) {
/*  97:114 */       this.dungeonIcon[i] = iconRegister.registerIcon("chocolatequest:d" + i);
/*  98:    */     }
/*  99:    */   }
/* 100:    */   
/* 101:    */   @SideOnly(Side.CLIENT)
/* 102:    */   public void getSubItems(Item itemId, CreativeTabs table, List list)
/* 103:    */   {
/* 104:125 */     for (int i = 0; i < this.mobs.length; i++) {
/* 105:128 */       for (int l = 0; l < 6; l++) {
/* 106:131 */         list.add(new ItemStack(itemId, 1, i + 100 * l));
/* 107:    */       }
/* 108:    */     }
/* 109:134 */     for (int i = 0; i < this.humanBosses.length; i++) {
/* 110:136 */       list.add(new ItemStack(itemId, 1, i + this.OFF_BOSS_HUMAN));
/* 111:    */     }
/* 112:138 */     for (int i = 0; i < this.bosses.length; i++) {
/* 113:140 */       list.add(new ItemStack(itemId, 1, i + this.OFF_BOSS));
/* 114:    */     }
/* 115:142 */     if (DungeonRegister.dungeonList != null) {
/* 116:143 */       for (int i = 0; i < DungeonRegister.dungeonList.size(); i++) {
/* 117:145 */         list.add(new ItemStack(itemId, 1, this.OFF_DUNGEON + i));
/* 118:    */       }
/* 119:    */     }
/* 120:    */   }
/* 121:    */   
/* 122:    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
/* 123:    */   {
/* 124:153 */     MovingObjectPosition movingobjectposition = playerPick(entityplayer, world);
/* 125:155 */     if (movingobjectposition != null)
/* 126:    */     {
/* 128:    */       int i;
/* 129:    */       int j;
/* 130:    */       int k;
/* 131:159 */       if (movingobjectposition.entityHit != null)
/* 132:    */       {
/* 133:161 */         i = MathHelper.floor_double(movingobjectposition.entityHit.posX);
/* 134:162 */         j = MathHelper.floor_double(movingobjectposition.entityHit.posY) + 1;
/* 135:163 */         k = MathHelper.floor_double(movingobjectposition.entityHit.posZ);
/* 136:    */       }
/* 137:    */       else
/* 138:    */       {
/* 139:167 */         i = movingobjectposition.blockX;
/* 140:168 */         j = movingobjectposition.blockY;
/* 141:169 */         k = movingobjectposition.blockZ;
/* 142:171 */         switch (movingobjectposition.sideHit)
/* 143:    */         {
/* 144:    */         case 1: 
/* 145:174 */           j++;
/* 146:175 */           break;
/* 147:    */         case 0: 
/* 148:178 */           j--;
/* 149:179 */           break;
/* 150:    */         case 2: 
/* 151:182 */           k--;
/* 152:183 */           break;
/* 153:    */         case 3: 
/* 154:186 */           k++;
/* 155:187 */           break;
/* 156:    */         case 5: 
/* 157:190 */           i++;
/* 158:191 */           break;
/* 159:    */         case 4: 
/* 160:194 */           i--;
/* 161:    */         }
/* 162:    */       }
/* 163:199 */       onItemUse(itemstack, entityplayer, world, i, j, k, 0);
/* 164:    */     }
/* 165:203 */     return super.onItemRightClick(itemstack, world, entityplayer);
/* 166:    */   }
/* 167:    */   
/* 168:    */   public MovingObjectPosition playerPick(EntityPlayer player, World world)
/* 169:    */   {
/* 170:208 */     int dist = 80;
/* 171:209 */     Vec3 vec3d = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
/* 172:210 */     Vec3 vec3d1 = Vec3.createVectorHelper(player.posX + player.getLookVec().xCoord * dist, player.posY + player.getLookVec().yCoord * dist, player.posZ + player.getLookVec().zCoord * dist);
/* 173:211 */     MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3d, vec3d1);
/* 174:212 */     List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(player.getLookVec().xCoord * 40.0D, player.getLookVec().yCoord * 40.0D, player.getLookVec().zCoord * 40.0D).expand(1.0D, 1.0D, 1.0D));
/* 175:213 */     double d = 0.0D;
/* 176:215 */     for (int j = 0; j < list.size(); j++)
/* 177:    */     {
/* 178:217 */       Entity entity1 = (Entity)list.get(j);
/* 179:219 */       if (entity1.canBeCollidedWith())
/* 180:    */       {
/* 181:224 */         float f2 = 0.3F;
/* 182:225 */         AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f2, f2, f2);
/* 183:226 */         MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
/* 184:228 */         if (movingobjectposition1 != null)
/* 185:    */         {
/* 186:233 */           movingobjectposition1.entityHit = entity1;
/* 187:234 */           return movingobjectposition1;
/* 188:    */         }
/* 189:    */       }
/* 190:    */     }
/* 191:237 */     return movingobjectposition;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
/* 195:    */   {
/* 196:242 */     int itemDamage = itemstack.getItemDamage();
/* 197:243 */     if ((itemDamage >= this.OFF_DUNGEON) && (!world.isRemote))
/* 198:    */     {
/* 199:245 */       if (this.cooldown <= 0)
/* 200:    */       {
/* 201:246 */         DungeonBase dungeon = (DungeonBase)DungeonRegister.dungeonList.get(itemDamage - this.OFF_DUNGEON);
/* 202:247 */         if (dungeon != null)
/* 203:    */         {
/* 204:249 */           File file = new File(BDHelper.getAppDir(), "Chocolate/DungeonConfig/" + dungeon.getName());
/* 205:250 */           dungeon = dungeon.readData(file);
/* 206:    */           
/* 207:    */ 
/* 208:253 */           Random random = new Random();
/* 209:254 */           int x = i / 16;
/* 210:255 */           int z = k / 16;
/* 211:256 */           i -= Math.abs(i % 16);
/* 212:257 */           k -= Math.abs(k % 16);
/* 213:258 */           random.setSeed(WorldGeneratorNew.getSeed(world, x, z));
/* 214:259 */           dungeon.getBuilder().generate(random, world, i, j, k, dungeon.getMobID());
/* 215:260 */           this.cooldown = 100;
/* 216:    */         }
/* 217:    */       }
/* 218:264 */       return true;
/* 219:    */     }
/* 220:266 */     Entity e = getEntity(world, i, j, k, itemDamage);
/* 221:267 */     if (e != null)
/* 222:    */     {
/* 223:269 */       if (itemDamage < this.OFF_BOSS_HUMAN)
/* 224:    */       {
/* 225:270 */         EntityHumanBase entityLiving = (EntityHumanBase)e;
/* 226:271 */         int armorType = itemDamage / 100;
/* 227:272 */         if (armorType != 0) {
/* 228:273 */           EquipementHelper.equipHumanRandomly(entityLiving, armorType - 1, EquipementHelper.getRandomType(entityLiving, 5));
/* 229:    */         }
/* 230:    */       }
/* 231:275 */       e.setPosition(i + 0.5D, j + 1, k + 0.5D);
/* 232:276 */       if (!world.isRemote) {
/* 233:277 */         world.spawnEntityInWorld(e);
/* 234:    */       }
/* 235:279 */       return true;
/* 236:    */     }
/* 237:282 */     return false;
/* 238:    */   }
/* 239:    */   
/* 240:    */   public Entity getEntity(World world, int i, int j, int k, int itemDamage)
/* 241:    */   {
/* 242:288 */     Entity e = null;
/* 243:    */     
/* 244:290 */     Class currentClass = EntityHumanBase.class;
/* 245:291 */     MobData data = getDataFromDamage(itemDamage);
/* 246:292 */     if (data != null) {
/* 247:293 */       currentClass = data.mobClass;
/* 248:    */     }
/* 249:    */     try
/* 250:    */     {
/* 251:296 */       Constructor<Entity> c = currentClass.getDeclaredConstructor(new Class[] { World.class });
/* 252:297 */       c.setAccessible(true);
/* 253:298 */       e = (Entity)c.newInstance(new Object[] { world });
/* 254:    */     }
/* 255:    */     catch (Exception ex)
/* 256:    */     {
/* 257:300 */       e = new EntityHumanBase(world);
/* 258:    */     }
/* 259:302 */     return e;
/* 260:    */   }
/* 261:    */   
/* 262:    */   public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
/* 263:    */   {
/* 264:308 */     if (this.cooldown > 0) {
/* 265:309 */       this.cooldown -= 1;
/* 266:    */     }
/* 267:310 */     super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
/* 268:    */   }
/* 269:    */   
/* 270:    */   public String getItemStackDisplayName(ItemStack itemstack)
/* 271:    */   {
/* 272:316 */     int i = itemstack.getItemDamage();
/* 273:317 */     if (i >= this.OFF_DUNGEON)
/* 274:    */     {
/* 275:319 */       DungeonBase dungeon = (DungeonBase)DungeonRegister.dungeonList.get(i - this.OFF_DUNGEON);
/* 276:320 */       if (dungeon != null) {
/* 277:321 */         return dungeon.getName();
/* 278:    */       }
/* 279:    */     }
/* 280:323 */     int itemDamage = itemstack.getItemDamage();
/* 281:324 */     String info = "";
/* 282:325 */     if (i < this.OFF_BOSS)
/* 283:    */     {
/* 284:326 */       int armorType = itemDamage / 100;
/* 285:327 */       switch (armorType)
/* 286:    */       {
/* 287:    */       case 1: 
/* 288:328 */         info = " Leather"; break;
/* 289:    */       case 2: 
/* 290:329 */         info = " Chain"; break;
/* 291:    */       case 3: 
/* 292:330 */         info = " Gold"; break;
/* 293:    */       case 4: 
/* 294:331 */         info = " Iron"; break;
/* 295:    */       case 5: 
/* 296:332 */         info = " Diamond"; break;
/* 297:    */       }
/* 298:    */     }
/* 299:336 */     MobData data = getDataFromDamage(itemDamage);
/* 300:337 */     if (data != null) {
/* 301:338 */       return data.name + info;
/* 302:    */     }
/* 303:339 */     return "????";
/* 304:    */   }
/* 305:    */   
/* 306:    */   public IIcon getIconFromDamage(int itemDamage)
/* 307:    */   {
/* 308:345 */     if (itemDamage == 499) {
/* 309:346 */       return this.dungeonIcon[11];
/* 310:    */     }
/* 311:347 */     if (itemDamage >= this.OFF_DUNGEON)
/* 312:    */     {
/* 313:349 */       DungeonBase dungeon = (DungeonBase)DungeonRegister.dungeonList.get(itemDamage - this.OFF_DUNGEON);
/* 314:350 */       if (dungeon != null)
/* 315:    */       {
/* 316:352 */         if (dungeon.getIcon() < this.dungeonIcon.length) {}
/* 317:353 */         return this.dungeonIcon[dungeon.getIcon()];
/* 318:    */       }
/* 319:    */     }
/* 320:357 */     MobData data = getDataFromDamage(itemDamage);
/* 321:358 */     if (data != null)
/* 322:    */     {
/* 323:359 */       if (itemDamage < this.OFF_BOSS) {
/* 324:359 */         return ChocolateQuest.shield.getIconFromDamage(data.color);
/* 325:    */       }
/* 326:360 */       return this.bossIcon[data.color];
/* 327:    */     }
/* 328:363 */     return this.dungeonIcon[0];
/* 329:    */   }
/* 330:    */   
/* 331:    */   public MobData getDataFromDamage(int itemDamage)
/* 332:    */   {
/* 333:367 */     if (itemDamage >= this.OFF_BOSS)
/* 334:    */     {
/* 335:368 */       itemDamage -= this.OFF_BOSS;
/* 336:369 */       if (itemDamage < this.bosses.length) {
/* 337:370 */         return this.bosses[itemDamage];
/* 338:    */       }
/* 339:    */     }
/* 340:372 */     else if (itemDamage >= this.OFF_BOSS_HUMAN)
/* 341:    */     {
/* 342:373 */       itemDamage -= this.OFF_BOSS_HUMAN;
/* 343:374 */       if (itemDamage < this.bosses.length) {
/* 344:375 */         return this.humanBosses[itemDamage];
/* 345:    */       }
/* 346:    */     }
/* 347:    */     else
/* 348:    */     {
/* 349:379 */       itemDamage %= 100;
/* 350:380 */       if (itemDamage < this.mobs.length) {
/* 351:381 */         return this.mobs[itemDamage];
/* 352:    */       }
/* 353:    */     }
/* 354:384 */     return null;
/* 355:    */   }
/* 356:    */   
/* 357:    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
/* 358:    */   {
/* 359:390 */     if (stack.stackTagCompound != null)
/* 360:    */     {
/* 361:392 */       Entity e = player.worldObj.getEntityByID(stack.stackTagCompound.getInteger("EntityID"));
/* 362:393 */       if ((e instanceof EntityLiving))
/* 363:    */       {
/* 364:395 */         if ((entity instanceof EntityLiving)) {
/* 365:396 */           ((EntityLiving)e).setAttackTarget((EntityLiving)entity);
/* 366:    */         }
/* 367:    */       }
/* 368:    */       else
/* 369:    */       {
/* 370:400 */         if (!player.worldObj.isRemote) {
/* 371:401 */           player.addChatMessage(new ChatComponentText("Assigned entity not found, left click to assign a new entity"));
/* 372:    */         }
/* 373:402 */         stack.stackTagCompound = null;
/* 374:    */       }
/* 375:404 */       return true;
/* 376:    */     }
/* 377:406 */     if ((entity instanceof EntityLiving))
/* 378:    */     {
/* 379:408 */       stack.stackTagCompound = new NBTTagCompound();
/* 380:409 */       stack.stackTagCompound.setInteger("EntityID", entity.getEntityId());
/* 381:410 */       stack.stackTagCompound.setString("name", entity.getCommandSenderName());
/* 382:411 */       if (!player.worldObj.isRemote)
/* 383:    */       {
/* 384:412 */         player.addChatMessage(new ChatComponentText("Assigned " + BDHelper.StringColor("2") + entity.getCommandSenderName() + BDHelper.StringColor("f") + " to this item"));
/* 385:413 */         player.addChatMessage(new ChatComponentText("Left click to another entity to start a fight!"));
/* 386:    */       }
/* 387:415 */       return true;
/* 388:    */     }
/* 389:419 */     return false;
/* 390:    */   }
/* 391:    */   
/* 392:    */   public ItemStack dispense(IBlockSource iblocksource, ItemStack itemstack)
/* 393:    */   {
/* 394:424 */     EnumFacing enumfacing = BlockDispenser.func_149937_b(iblocksource.getBlockMetadata());
/* 395:425 */     double d0 = iblocksource.getX() + enumfacing.getFrontOffsetX();
/* 396:426 */     double d1 = iblocksource.getYInt() + 0.2F;
/* 397:427 */     double d2 = iblocksource.getZ() + enumfacing.getFrontOffsetZ();
/* 398:428 */     Entity e = getEntity(iblocksource.getWorld(), (int)d0, (int)d1, (int)d2, itemstack.getItemDamage());
/* 399:429 */     if (e != null)
/* 400:    */     {
/* 401:431 */       e.setPosition(d0, d1, d2);
/* 402:432 */       if (!iblocksource.getWorld().isRemote) {
/* 403:433 */         iblocksource.getWorld().spawnEntityInWorld(e);
/* 404:    */       }
/* 405:    */     }
/* 406:435 */     itemstack.stackSize -= 1;
/* 407:436 */     return itemstack;
/* 408:    */   }
/* 409:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemEggBD

 * JD-Core Version:    0.7.1

 */
