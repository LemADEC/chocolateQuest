/*   1:    */ package com.chocolate.chocolateQuest.builder.schematic;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   4:    */ import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
/*   5:    */ import cpw.mods.fml.common.registry.GameData;
/*   6:    */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*   7:    */ import java.io.DataOutput;
/*   8:    */ import java.io.DataOutputStream;
/*   9:    */ import java.io.File;
/*  10:    */ import java.io.FileInputStream;
/*  11:    */ import java.io.FileNotFoundException;
/*  12:    */ import java.io.FileOutputStream;
/*  13:    */ import java.io.IOException;
/*  14:    */ import java.io.PrintStream;
/*  15:    */ import java.lang.reflect.Method;
/*  16:    */ import java.util.ArrayList;
/*  17:    */ import java.util.HashMap;
/*  18:    */ import java.util.List;
/*  19:    */ import java.util.Map;
/*  20:    */ import java.util.zip.GZIPOutputStream;
/*  21:    */ import net.minecraft.block.Block;
/*  22:    */ import net.minecraft.client.Minecraft;
/*  23:    */ import net.minecraft.crash.CrashReport;
/*  24:    */ import net.minecraft.entity.Entity;
/*  25:    */ import net.minecraft.entity.EntityHanging;
/*  26:    */ import net.minecraft.entity.EntityList;
/*  27:    */ import net.minecraft.entity.item.EntityItemFrame;
/*  28:    */ import net.minecraft.entity.item.EntityPainting;
/*  29:    */ import net.minecraft.entity.projectile.EntityEgg;
/*  30:    */ import net.minecraft.nbt.CompressedStreamTools;
/*  31:    */ import net.minecraft.nbt.NBTBase;
/*  32:    */ import net.minecraft.nbt.NBTTagCompound;
/*  33:    */ import net.minecraft.nbt.NBTTagList;
/*  34:    */ import net.minecraft.tileentity.TileEntity;
/*  35:    */ import net.minecraft.util.MathHelper;
/*  36:    */ import net.minecraft.util.RegistryNamespaced;
/*  37:    */ import net.minecraft.util.ReportedException;
/*  38:    */ import net.minecraft.world.World;
/*  39:    */ 
/*  40:    */ public class Schematic
/*  41:    */ {
/*  42:    */   public short width;
/*  43:    */   public short height;
/*  44:    */   public short length;
/*  45:    */   short[] blocks;
/*  46:    */   byte[] metadata;
/*  47: 46 */   String schematicName = "ChocolateQuest_Schematic";
/*  48:    */   int posX;
/*  49:    */   int posY;
/*  50:    */   int posZ;
/*  51:    */   public NBTTagList entities;
/*  52:    */   public NBTTagList tileEntities;
/*  53:    */   Map<String, Integer> idMap;
/*  54:    */   
/*  55:    */   public Schematic()
/*  56:    */   {
/*  57: 57 */     this(new File(BDHelper.getAppDir(), BDHelper.getInfoDir() + "Building/test.schematic"));
/*  58: 58 */     save(new File(Minecraft.getMinecraft().mcDataDir, "/Chocolate/Building/test/saveTest"));
/*  59:    */     
/*  60: 60 */     int a = 0;
/*  61: 61 */     byte A = (byte)(a & 0xFF);
/*  62: 62 */     byte B = (byte)((a & 0xF00) >> 8);
/*  63: 63 */     System.out.println((A & 0xFF) + " " + (B & 0xFF) + " " + ((A & 0xFF) + (B << 8)));
/*  64:    */   }
/*  65:    */   
/*  66:    */   public Schematic(File file)
/*  67:    */   {
/*  68: 77 */     load(getNBTMap(file));
/*  69:    */   }
/*  70:    */   
/*  71:    */   public Schematic(int width, int height, int length)
/*  72:    */   {
/*  73: 81 */     this.width = ((short)width);
/*  74: 82 */     this.height = ((short)height);
/*  75: 83 */     this.length = ((short)length);
/*  76: 84 */     int total = width * length * height;
/*  77: 85 */     this.blocks = new short[total];
/*  78: 86 */     this.metadata = new byte[total];
/*  79:    */   }
/*  80:    */   
/*  81:    */   public Schematic(int width, int height, int length, int posX, int posY, int posZ, String name)
/*  82:    */   {
/*  83: 90 */     this(width, height, length);
/*  84: 91 */     this.posX = posX;
/*  85: 92 */     this.posY = posY;
/*  86: 93 */     this.posZ = posZ;
/*  87: 94 */     this.schematicName = name;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void setPosition(int posX, int posY, int posZ)
/*  91:    */   {
/*  92: 98 */     this.posX = posX;
/*  93: 99 */     this.posY = posY;
/*  94:100 */     this.posZ = posZ;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public Block getBlock(int x, int y, int z)
/*  98:    */   {
/*  99:104 */     int index = y * this.width * this.length + z * this.width + x;
/* 100:105 */     return Block.getBlockById(this.blocks[index]);
/* 101:    */   }
/* 102:    */   
/* 103:    */   public int getBlockMetadata(int x, int y, int z)
/* 104:    */   {
/* 105:109 */     int index = y * this.width * this.length + z * this.width + x;
/* 106:110 */     return this.metadata[index];
/* 107:    */   }
/* 108:    */   
/* 109:    */   public List<TileEntity> getTileEntities()
/* 110:    */   {
/* 111:114 */     if (this.tileEntities != null)
/* 112:    */     {
/* 113:115 */       ArrayList<TileEntity> list = new ArrayList();
/* 114:116 */       for (int i = 0; i < this.tileEntities.tagCount(); i++)
/* 115:    */       {
/* 116:117 */         NBTTagCompound tag = this.tileEntities.getCompoundTagAt(i);
/* 117:118 */         TileEntity te = TileEntity.createAndLoadEntity(tag);
/* 118:119 */         if (te != null)
/* 119:    */         {
/* 120:120 */           int x = te.xCoord;int y = te.yCoord;int z = te.zCoord;
/* 121:121 */           te.xCoord = (x + this.posX);te.yCoord = (y + this.posY);te.zCoord = (z + this.posZ);
/* 122:122 */           list.add(te);
/* 123:    */         }
/* 124:    */       }
/* 125:125 */       return list;
/* 126:    */     }
/* 127:127 */     return null;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public NBTTagList getTileEntitiesTag()
/* 131:    */   {
/* 132:130 */     return this.tileEntities;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public List<Entity> getEntities(World world)
/* 136:    */   {
/* 137:135 */     if (this.entities != null)
/* 138:    */     {
/* 139:136 */       ArrayList<Entity> list = new ArrayList();
/* 140:137 */       for (int i = 0; i < this.entities.tagCount(); i++)
/* 141:    */       {
/* 142:138 */         Entity e = new EntityEgg(world);
/* 143:139 */         int entityID = e.getEntityId();
/* 144:140 */         e = null;
/* 145:141 */         NBTTagCompound tag = this.entities.getCompoundTagAt(i);
/* 146:142 */         e = EntityList.createEntityFromNBT(tag, world);
/* 147:143 */         if (e != null)
/* 148:    */         {
/* 149:145 */           if ((e instanceof EntityPainting))
/* 150:    */           {
/* 151:147 */             EntityPainting ep = (EntityPainting)e;
/* 152:148 */             e = new EntityPainting(world, ep.field_146063_b + this.posX, ep.field_146064_c + this.posY, ep.field_146062_d + this.posZ, ep.hangingDirection);
/* 153:149 */             ((EntityPainting)e).art = ep.art;
/* 154:    */           }
/* 155:151 */           else if ((e instanceof EntityItemFrame))
/* 156:    */           {
/* 157:153 */             EntityItemFrame ep = (EntityItemFrame)e;
/* 158:154 */             e = new EntityItemFrame(world, ep.field_146063_b + this.posX, ep.field_146064_c + this.posY, ep.field_146062_d + this.posZ, ep.hangingDirection);
/* 159:156 */             if (ep.getDisplayedItem() != null) {
/* 160:158 */               ((EntityItemFrame)e).setDisplayedItem(ep.getDisplayedItem());
/* 161:    */             }
/* 162:    */           }
/* 163:    */           else
/* 164:    */           {
/* 165:163 */             e.setPosition(e.posX + this.posX, e.posY + this.posY, e.posZ + this.posZ);
/* 166:    */           }
/* 167:166 */           e.chunkCoordX = MathHelper.floor_double(e.posX % 16.0D);
/* 168:167 */           e.chunkCoordY = MathHelper.floor_double(e.posY % 16.0D);
/* 169:168 */           e.chunkCoordZ = MathHelper.floor_double(e.posZ % 16.0D);
/* 170:    */           
/* 171:170 */           e.setEntityId(entityID);
/* 172:    */           
/* 173:172 */           list.add(e);
/* 174:    */         }
/* 175:    */       }
/* 176:175 */       return list;
/* 177:    */     }
/* 178:177 */     return null;
/* 179:    */   }
/* 180:    */   
/* 181:    */   public void setBlock(int x, int y, int z, Block block)
/* 182:    */   {
/* 183:182 */     x -= this.posX;
/* 184:183 */     y -= this.posY;
/* 185:184 */     z -= this.posZ;
/* 186:185 */     int index = y * this.width * this.length + z * this.width + x;
/* 187:186 */     this.blocks[index] = ((short)GameData.blockRegistry.getId(block));
/* 188:    */   }
/* 189:    */   
/* 190:    */   public void setBlockMetadata(int x, int y, int z, byte metaData)
/* 191:    */   {
/* 192:190 */     x -= this.posX;
/* 193:191 */     y -= this.posY;
/* 194:192 */     z -= this.posZ;
/* 195:193 */     int index = y * this.width * this.length + z * this.width + x;
/* 196:194 */     this.metadata[index] = metaData;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public void setBlockAndMetadata(int x, int y, int z, Block block, byte metaData)
/* 200:    */   {
/* 201:198 */     x -= this.posX;
/* 202:199 */     y -= this.posY;
/* 203:200 */     z -= this.posZ;
/* 204:201 */     int index = y * this.width * this.length + z * this.width + x;
/* 205:202 */     this.blocks[index] = ((short)GameData.blockRegistry.getId(block));
/* 206:203 */     this.metadata[index] = metaData;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public void addTileEntity(TileEntity te)
/* 210:    */   {
/* 211:207 */     if (this.tileEntities == null) {
/* 212:208 */       this.tileEntities = new NBTTagList();
/* 213:    */     }
/* 214:209 */     int x = te.xCoord;int y = te.yCoord;int z = te.zCoord;
/* 215:210 */     te.xCoord = (x - this.posX);te.yCoord = (y - this.posY);te.zCoord = (z - this.posZ);
/* 216:    */     
/* 217:212 */     NBTTagCompound data = new NBTTagCompound();
/* 218:213 */     te.writeToNBT(data);
/* 219:214 */     this.tileEntities.appendTag(data);
/* 220:    */     
/* 221:216 */     te.xCoord = x;te.yCoord = y;te.zCoord = z;
/* 222:    */   }
/* 223:    */   
/* 224:    */   public void addEntity(Entity e)
/* 225:    */   {
/* 226:220 */     if (this.entities == null) {
/* 227:221 */       this.entities = new NBTTagList();
/* 228:    */     }
/* 229:222 */     if ((e instanceof EntityHanging))
/* 230:    */     {
/* 231:224 */       EntityHanging ef = (EntityHanging)e;
/* 232:225 */       ef.field_146063_b -= this.posX;
/* 233:226 */       ef.field_146064_c -= this.posY;
/* 234:227 */       ef.field_146062_d -= this.posZ;
/* 235:    */     }
/* 236:    */     else
/* 237:    */     {
/* 238:229 */       e.setPosition(e.posX - this.posX, e.posY - this.posY, e.posZ - this.posZ);
/* 239:    */     }
/* 240:231 */     NBTTagCompound data = new NBTTagCompound();
/* 241:232 */     e.writeToNBTOptional(data);
/* 242:233 */     this.entities.appendTag(data);
/* 243:235 */     if ((e instanceof EntityHanging))
/* 244:    */     {
/* 245:237 */       EntityHanging ef = (EntityHanging)e;
/* 246:238 */       ef.field_146063_b += this.posX;
/* 247:239 */       ef.field_146064_c += this.posY;
/* 248:240 */       ef.field_146062_d += this.posZ;
/* 249:    */     }
/* 250:    */     else
/* 251:    */     {
/* 252:242 */       e.setPosition(e.posX + this.posX, e.posY + this.posY, e.posZ + this.posZ);
/* 253:    */     }
/* 254:    */   }
/* 255:    */   
/* 256:    */   public void load(NBTTagCompound schematic)
/* 257:    */   {
/* 258:247 */     this.width = schematic.getShort("Width");
/* 259:248 */     this.height = schematic.getShort("Height");
/* 260:249 */     this.length = schematic.getShort("Length");
/* 261:    */     
/* 262:251 */     int total = this.width * this.length * this.height;
/* 263:252 */     this.blocks = new short[total];
/* 264:253 */     this.metadata = new byte[total];
/* 265:254 */     byte[] blockBytes = schematic.getByteArray("Blocks");
/* 266:255 */     byte[] addedBytes = null;
/* 267:256 */     if (schematic.hasKey("Add")) {
/* 268:258 */       addedBytes = schematic.getByteArray("Add");
/* 269:    */     }
/* 270:260 */     for (int i = 0; i < total; i++)
/* 271:    */     {
/* 272:262 */       short currentID = (short)(blockBytes[i] & 0xFF);
/* 273:263 */       if (addedBytes != null) {
/* 274:264 */         currentID = (short)(currentID ^ addedBytes[i] << 8);
/* 275:    */       }
/* 276:265 */       this.blocks[i] = currentID;
/* 277:    */     }
/* 278:267 */     this.metadata = schematic.getByteArray("Data");
/* 279:    */     
/* 280:269 */     this.entities = schematic.getTagList("Entities", schematic.getId());
/* 281:270 */     this.tileEntities = schematic.getTagList("TileEntities", schematic.getId());
/* 282:    */     
/* 283:272 */     NBTTagList idMappingNBT = schematic.getTagList("IDMapping", schematic.getId());
/* 284:273 */     if ((idMappingNBT != null) && (idMappingNBT.tagCount() > 0))
/* 285:    */     {
/* 286:274 */       loadMappings(idMappingNBT);
/* 287:275 */       translateToLocal();
/* 288:    */     }
/* 289:    */   }
/* 290:    */   
/* 291:    */   public void save(File file)
/* 292:    */   {
/* 293:281 */     NBTTagCompound data = new NBTTagCompound();
/* 294:282 */     data.setShort("Width", this.width);
/* 295:283 */     data.setShort("Height", this.height);
/* 296:284 */     data.setShort("Length", this.length);
/* 297:    */     
/* 298:286 */     data.setString("Materials", "Alpha");
/* 299:287 */     data.setString("Name", this.schematicName);
/* 300:    */     
/* 301:289 */     byte[] vanilaBlockIds = new byte[this.blocks.length];
/* 302:290 */     byte[] addedBits = new byte[this.blocks.length];
/* 303:291 */     for (int i = 0; i < this.blocks.length; i++)
/* 304:    */     {
/* 305:293 */       vanilaBlockIds[i] = ((byte)(this.blocks[i] & 0xFF));
/* 306:294 */       addedBits[i] = ((byte)((this.blocks[i] & 0xF00) >> 8));
/* 307:    */     }
/* 308:297 */     data.setByteArray("Blocks", vanilaBlockIds);
/* 309:298 */     data.setByteArray("Add", addedBits);
/* 310:299 */     data.setByteArray("Data", this.metadata);
/* 311:301 */     if (this.tileEntities != null) {
/* 312:303 */       data.setTag("TileEntities", this.tileEntities);
/* 313:    */     }
/* 314:305 */     if (this.entities != null) {
/* 315:307 */       data.setTag("Entities", this.entities);
/* 316:    */     }
/* 317:310 */     data.setTag("IDMapping", saveMappings());
/* 318:    */     try
/* 319:    */     {
/* 320:313 */       FileOutputStream fos = new FileOutputStream(file);
/* 321:    */       
/* 322:    */ 
/* 323:    */ 
/* 324:317 */       writeCompressed(data, fos);
/* 325:318 */       fos.close();
/* 326:    */     }
/* 327:    */     catch (FileNotFoundException e)
/* 328:    */     {
/* 329:322 */       e.printStackTrace();
/* 330:    */     }
/* 331:    */     catch (IOException e)
/* 332:    */     {
/* 333:326 */       e.printStackTrace();
/* 334:    */     }
/* 335:    */   }
/* 336:    */   
/* 337:    */   private static void writeCompressed(NBTBase tagCompound, FileOutputStream file)
/* 338:    */     throws IOException
/* 339:    */   {
/* 340:332 */     DataOutputStream dataOutputStream = new DataOutputStream(new GZIPOutputStream(file));
/* 341:    */     try
/* 342:    */     {
/* 343:334 */       Method method = ReflectionHelper.findMethod(NBTTagCompound.class, null, new String[] { "func_150298_a", "a" }, new Class[] { String.class, NBTBase.class, DataOutput.class });
/* 344:    */       
/* 345:336 */       method.invoke(null, new Object[] { "Schematic", tagCompound, dataOutputStream });
/* 346:    */     }
/* 347:    */     catch (Exception e)
/* 348:    */     {
/* 349:339 */       e.printStackTrace();
/* 350:    */     }
/* 351:    */     finally
/* 352:    */     {
/* 353:342 */       dataOutputStream.close();
/* 354:    */     }
/* 355:    */   }
/* 356:    */   
/* 357:    */   public static NBTTagCompound getNBTMap(File file)
/* 358:    */   {
/* 359:    */     try
/* 360:    */     {
/* 361:350 */       FileInputStream fos = new FileInputStream(file);
/* 362:351 */       NBTBase base = CompressedStreamTools.readCompressed(fos);
/* 363:352 */       if ((base instanceof NBTTagCompound))
/* 364:    */       {
/* 365:354 */         fos.close();
/* 366:355 */         return (NBTTagCompound)base;
/* 367:    */       }
/* 368:359 */       BDHelper.println("Found corrupted better dungeons template :" + file.getPath() + ", Skipping generation.");
/* 369:360 */       return null;
/* 370:    */     }
/* 371:    */     catch (FileNotFoundException e)
/* 372:    */     {
/* 373:365 */       CrashReport crashreport = CrashReport.makeCrashReport(e, "File not found at better dungeons mod, file: " + file.getPath());
/* 374:366 */       throw new ReportedException(crashreport);
/* 375:    */     }
/* 376:    */     catch (IOException e)
/* 377:    */     {
/* 378:370 */       CrashReport crashreport = CrashReport.makeCrashReport(e, "Error reading file at better dungeons mod, file: " + file.getPath());
/* 379:371 */       throw new ReportedException(crashreport);
/* 380:    */     }
/* 381:    */   }
/* 382:    */   
/* 383:    */   public void translateToLocal()
/* 384:    */   {
/* 385:378 */     HashMap<Integer, String> tMap = new HashMap();
/* 386:379 */     for (String key : this.idMap.keySet()) {
/* 387:380 */       tMap.put(this.idMap.get(key), key);
/* 388:    */     }
/* 389:382 */     for (int i = 0; i < this.blocks.length; i++) {
/* 390:383 */       if (this.blocks[i] != 0)
/* 391:    */       {
/* 392:384 */         int currentID = this.blocks[i];
/* 393:385 */         if (this.idMap.containsValue(Integer.valueOf(currentID)))
/* 394:    */         {
/* 395:386 */           Block block = Block.getBlockFromName((String)tMap.get(Integer.valueOf(currentID)));
/* 396:387 */           if (block != null) {
/* 397:388 */             this.blocks[i] = ((short)Block.getIdFromBlock(block));
/* 398:    */           } else {
/* 399:390 */             this.blocks[i] = 0;
/* 400:    */           }
/* 401:    */         }
/* 402:    */       }
/* 403:    */     }
/* 404:    */   }
/* 405:    */   
/* 406:    */   public void loadMappings(NBTTagList list)
/* 407:    */   {
/* 408:397 */     this.idMap = new HashMap();
/* 409:398 */     for (int i = 0; i < list.tagCount(); i++)
/* 410:    */     {
/* 411:399 */       NBTTagCompound tag = list.getCompoundTagAt(i);
/* 412:400 */       this.idMap.put(tag.getString("ItemType"), Integer.valueOf(tag.getInteger("ItemId")));
/* 413:    */     }
/* 414:    */   }
/* 415:    */   
/* 416:    */   public NBTBase saveMappings()
/* 417:    */   {
/* 418:404 */     this.idMap = new HashMap();
/* 419:405 */     for (int i = 0; i < this.blocks.length; i++) {
/* 420:406 */       if (!this.idMap.containsValue(Short.valueOf(this.blocks[i])))
/* 421:    */       {
/* 422:407 */         Block block = Block.getBlockById(this.blocks[i]);
/* 423:408 */         String name = Block.blockRegistry.getNameForObject(block);
/* 424:409 */         this.idMap.put(name, Integer.valueOf(this.blocks[i]));
/* 425:    */       }
/* 426:    */     }
/* 427:412 */     NBTTagList list = new NBTTagList();
/* 428:413 */     for (String key : this.idMap.keySet())
/* 429:    */     {
/* 430:414 */       NBTTagCompound tag = new NBTTagCompound();
/* 431:415 */       tag.setString("ItemType", key);
/* 432:416 */       tag.setInteger("ItemId", ((Integer)this.idMap.get(key)).intValue());
/* 433:417 */       list.appendTag(tag);
/* 434:    */     }
/* 435:419 */     return list;
/* 436:    */   }
/* 437:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.builder.schematic.Schematic
 * JD-Core Version:    0.7.1
 */