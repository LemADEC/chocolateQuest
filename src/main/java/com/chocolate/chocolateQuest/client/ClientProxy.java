/*   1:    */ package com.chocolate.chocolateQuest.client;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.CommonProxy;
/*   5:    */ import com.chocolate.chocolateQuest.block.BlockAltarTileEntity;
/*   6:    */ import com.chocolate.chocolateQuest.block.BlockArmorStandTileEntity;
/*   7:    */ import com.chocolate.chocolateQuest.block.BlockBannerStandTileEntity;
/*   8:    */ import com.chocolate.chocolateQuest.block.BlockEditorTileEntity;
/*   9:    */ import com.chocolate.chocolateQuest.client.blockRender.RenderBlockArmorStand;
/*  10:    */ import com.chocolate.chocolateQuest.client.blockRender.RenderBlockBanner;
/*  11:    */ import com.chocolate.chocolateQuest.client.blockRender.RenderBlockEditor;
/*  12:    */ import com.chocolate.chocolateQuest.client.blockRender.RenderBlockTable;
/*  13:    */ import com.chocolate.chocolateQuest.client.blockRender.RenderBlockTableItem;
/*  14:    */ import com.chocolate.chocolateQuest.client.itemsRender.RenderItemBanner;
/*  15:    */ import com.chocolate.chocolateQuest.client.itemsRender.RenderItemGolemWeapon;
/*  16:    */ import com.chocolate.chocolateQuest.client.itemsRender.RenderItemHookSword;
/*  17:    */ import com.chocolate.chocolateQuest.client.itemsRender.RenderItemModel;
/*  18:    */ import com.chocolate.chocolateQuest.client.itemsRender.RenderItemPistol;
/*  19:    */ import com.chocolate.chocolateQuest.client.itemsRender.RenderItemShield;
/*  20:    */ import com.chocolate.chocolateQuest.client.itemsRender.RenderItemSpear;
/*  21:    */ import com.chocolate.chocolateQuest.client.itemsRender.RenderItemSpearFire;
/*  22:    */ import com.chocolate.chocolateQuest.client.itemsRender.RenderItemStaff;
/*  23:    */ import com.chocolate.chocolateQuest.client.itemsRender.RenderItemSwordDefensive;
/*  24:    */ import com.chocolate.chocolateQuest.client.itemsRender.RenderItemTwoHandedSword;
/*  25:    */ import com.chocolate.chocolateQuest.client.model.ModelArmor;
/*  26:    */ import com.chocolate.chocolateQuest.client.model.ModelArmorColored;
/*  27:    */ import com.chocolate.chocolateQuest.client.model.ModelArmorDragon;
/*  28:    */ import com.chocolate.chocolateQuest.client.model.ModelArmorHeavyPlate;
/*  29:    */ import com.chocolate.chocolateQuest.client.model.ModelArmorMageRobe;
/*  30:    */ import com.chocolate.chocolateQuest.client.model.ModelArmorMinotaur;
/*  31:    */ import com.chocolate.chocolateQuest.client.model.ModelArmorSlime;
/*  32:    */ import com.chocolate.chocolateQuest.client.model.ModelArmorTurtle;
/*  33:    */ import com.chocolate.chocolateQuest.client.model.ModelArmorWitchHat;
/*  34:    */ import com.chocolate.chocolateQuest.client.model.ModelBull;
/*  35:    */ import com.chocolate.chocolateQuest.client.model.ModelDragonQuadruped;
/*  36:    */ import com.chocolate.chocolateQuest.client.model.ModelGiantBoxer;
/*  37:    */ import com.chocolate.chocolateQuest.client.model.ModelGolemMecha;
/*  38:    */ import com.chocolate.chocolateQuest.client.model.ModelGolemMechaHeavy;
/*  39:    */ import com.chocolate.chocolateQuest.client.model.ModelHuman;
/*  40:    */ import com.chocolate.chocolateQuest.client.model.ModelHumanSkeleton;
/*  41:    */ import com.chocolate.chocolateQuest.client.model.ModelHumanZombie;
/*  42:    */ import com.chocolate.chocolateQuest.client.model.ModelMinotaur;
/*  43:    */ import com.chocolate.chocolateQuest.client.model.ModelSkeletonSummoned;
/*  44:    */ import com.chocolate.chocolateQuest.client.model.ModelSlimeBoss;
/*  45:    */ import com.chocolate.chocolateQuest.client.model.ModelSpecter;
/*  46:    */ import com.chocolate.chocolateQuest.client.model.ModelSpecterBoss;
/*  47:    */ import com.chocolate.chocolateQuest.client.model.ModelSpiderBoss;
/*  48:    */ import com.chocolate.chocolateQuest.client.model.ModelTurtle;
/*  49:    */ import com.chocolate.chocolateQuest.client.model.golemWeapon.ModelBubbleCannon;
/*  50:    */ import com.chocolate.chocolateQuest.client.model.golemWeapon.ModelCannon;
/*  51:    */ import com.chocolate.chocolateQuest.client.model.golemWeapon.ModelFlameThrower;
/*  52:    */ import com.chocolate.chocolateQuest.client.model.golemWeapon.ModelGun;
/*  53:    */ import com.chocolate.chocolateQuest.client.model.golemWeapon.ModelMachineGun;
/*  54:    */ import com.chocolate.chocolateQuest.client.model.golemWeapon.ModelRailGun;
/*  55:    */ import com.chocolate.chocolateQuest.client.rendererHuman.RenderHuman;
/*  56:    */ import com.chocolate.chocolateQuest.client.rendererHuman.RenderHumanGremlin;
/*  57:    */ import com.chocolate.chocolateQuest.client.rendererHuman.RenderHumanMecha;
/*  58:    */ import com.chocolate.chocolateQuest.entity.EntityBaiter;
/*  59:    */ import com.chocolate.chocolateQuest.entity.EntityCursor;
/*  60:    */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  61:    */ import com.chocolate.chocolateQuest.entity.EntityReferee;
/*  62:    */ import com.chocolate.chocolateQuest.entity.EntitySummonedUndead;
/*  63:    */ import com.chocolate.chocolateQuest.entity.boss.EntityBull;
/*  64:    */ import com.chocolate.chocolateQuest.entity.boss.EntityGiantBoxer;
/*  65:    */ import com.chocolate.chocolateQuest.entity.boss.EntityPart;
/*  66:    */ import com.chocolate.chocolateQuest.entity.boss.EntitySlimeBoss;
/*  67:    */ import com.chocolate.chocolateQuest.entity.boss.EntitySpiderBoss;
/*  68:    */ import com.chocolate.chocolateQuest.entity.boss.EntityTurtle;
/*  69:    */ import com.chocolate.chocolateQuest.entity.boss.EntityWyvern;
/*  70:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanGremlin;
/*  71:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanMinotaur;
/*  72:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanPigZombie;
/*  73:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanPirate;
/*  74:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanSkeleton;
/*  75:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanSpecter;
/*  76:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanWalker;
/*  77:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanZombie;
/*  78:    */ import com.chocolate.chocolateQuest.entity.mob.EntityPirateBoss;
/*  79:    */ import com.chocolate.chocolateQuest.entity.mob.EntitySpecterBoss;
/*  80:    */ import com.chocolate.chocolateQuest.entity.mob.EntityWalkerBoss;
/*  81:    */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMecha;
/*  82:    */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMechaHeavy;
/*  83:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  84:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityHookShoot;
/*  85:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityProjectileBeam;
/*  86:    */ import com.chocolate.chocolateQuest.gui.GuiInGameStats;
/*  87:    */ import cpw.mods.fml.client.registry.ClientRegistry;
/*  88:    */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*  89:    */ import cpw.mods.fml.common.eventhandler.EventBus;
/*  90:    */ import net.minecraft.client.Minecraft;
/*  91:    */ import net.minecraft.client.model.ModelBiped;
/*  92:    */ import net.minecraft.item.Item;
/*  93:    */ import net.minecraft.item.ItemArmor;
/*  94:    */ import net.minecraft.util.ResourceLocation;
/*  95:    */ import net.minecraftforge.client.MinecraftForgeClient;
/*  96:    */ import net.minecraftforge.common.MinecraftForge;
/*  97:    */ 
/*  98:    */ public class ClientProxy
/*  99:    */   extends CommonProxy
/* 100:    */ {
/* 101:    */   public void register()
/* 102:    */   {
/* 103:103 */     super.register();
/* 104:104 */     registerRenderInformation();
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void postInit()
/* 108:    */   {
/* 109:109 */     super.postInit();
/* 110:110 */     MinecraftForge.EVENT_BUS.register(new GuiInGameStats(Minecraft.getMinecraft()));
/* 111:    */   }
/* 112:    */   
/* 113:    */   public void registerRenderInformation()
/* 114:    */   {
/* 115:116 */     Item[] items = { ChocolateQuest.turtleHelmet, ChocolateQuest.turtlePlate, ChocolateQuest.turtlePants, ChocolateQuest.turtleBoots, ChocolateQuest.slimeHelmet, ChocolateQuest.slimePlate, ChocolateQuest.slimePants, ChocolateQuest.slimeBoots, ChocolateQuest.bullHelmet, ChocolateQuest.bullPlate, ChocolateQuest.bullPants, ChocolateQuest.bullBoots, ChocolateQuest.diamondHeavyHelmet, ChocolateQuest.diamondHeavyPlate, ChocolateQuest.diamondHeavyPants, ChocolateQuest.diamondHeavyBoots, ChocolateQuest.ironHeavyHelmet, ChocolateQuest.ironHeavyPlate, ChocolateQuest.ironHeavyPants, ChocolateQuest.ironHeavyBoots, ChocolateQuest.inquisitionHelmet, ChocolateQuest.inquisitionPlate, ChocolateQuest.inquisitionPants, ChocolateQuest.inquisitionBoots, ChocolateQuest.dragonHelmet, ChocolateQuest.scouter, ChocolateQuest.cloudBoots, ChocolateQuest.kingArmor, ChocolateQuest.spiderHelmet, ChocolateQuest.spiderPlate, ChocolateQuest.spiderPants, ChocolateQuest.spiderBoots, ChocolateQuest.diamondHelmet, ChocolateQuest.diamondPlate, ChocolateQuest.diamondPants, ChocolateQuest.diamondBoots, ChocolateQuest.ironHelmet, ChocolateQuest.ironPlate, ChocolateQuest.ironPants, ChocolateQuest.ironBoots, ChocolateQuest.armorMage, ChocolateQuest.witchHat };
/* 116:127 */     for (Item i : items) {
/* 117:128 */       MinecraftForgeClient.registerItemRenderer(i, new RenderItemModel((ItemArmor)i));
/* 118:    */     }
/* 119:130 */     RenderItemSpear ris = new RenderItemSpear();
/* 120:131 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.rustedSpear, ris);
/* 121:132 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.ironSpear, ris);
/* 122:133 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.diamondSpear, ris);
/* 123:134 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.fireSpear, new RenderItemSpearFire());
/* 124:135 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.spearGun, new RenderItemSpearFire());
/* 125:    */     
/* 126:137 */     RenderItemTwoHandedSword riths = new RenderItemTwoHandedSword();
/* 127:138 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.ironBigsword, riths);
/* 128:139 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.diamondBigsword, riths);
/* 129:140 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.monkingSword, riths);
/* 130:141 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.bigSwordBull, riths);
/* 131:    */     
/* 132:143 */     RenderItemSwordDefensive risd = new RenderItemSwordDefensive();
/* 133:144 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.rustedSwordAndShied, risd);
/* 134:145 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.endSword, risd);
/* 135:146 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.swordTurtle, risd);
/* 136:147 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.swordSpider, risd);
/* 137:148 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.swordSunLight, risd);
/* 138:149 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.swordMoonLight, risd);
/* 139:150 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.monkingSwordAndShield, risd);
/* 140:151 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.ironSwordAndShield, risd);
/* 141:152 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.diamondSwordAndShield, risd);
/* 142:    */     
/* 143:154 */     RenderItemStaff risf = new RenderItemStaff();
/* 144:155 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.staffHeal, risf);
/* 145:156 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.staffPhysic, risf);
/* 146:157 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.staffMagic, risf);
/* 147:158 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.staffBlast, risf);
/* 148:159 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.staffFire, risf);
/* 149:160 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.staffLight, risf);
/* 150:    */     
/* 151:    */ 
/* 152:163 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.hookSword, new RenderItemHookSword());
/* 153:164 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.banner, new RenderItemBanner());
/* 154:165 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.shield, new RenderItemShield());
/* 155:    */     
/* 156:167 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.revolver, new RenderItemPistol());
/* 157:168 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.golemGun, new RenderItemGolemWeapon(new ModelGun()));
/* 158:169 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.golemFlameThrower, new RenderItemGolemWeapon(new ModelFlameThrower()));
/* 159:170 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.golemRifleGun, new RenderItemGolemWeapon(new ModelRailGun()));
/* 160:171 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.golemMachineGun, new RenderItemGolemWeapon(new ModelMachineGun()));
/* 161:172 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.golemCannon, new RenderItemGolemWeapon(new ModelCannon()));
/* 162:173 */     MinecraftForgeClient.registerItemRenderer(ChocolateQuest.golemBubbleCannon, new RenderItemGolemWeapon(new ModelBubbleCannon()));
/* 163:    */     
/* 164:175 */     tableRenderID = RenderingRegistry.getNextAvailableRenderId();
/* 165:176 */     RenderingRegistry.registerBlockHandler(tableRenderID, new RenderBlockTableItem());
/* 166:    */     
/* 167:    */ 
/* 168:    */ 
/* 169:    */ 
/* 170:181 */     RenderingRegistry.registerEntityRenderingHandler(EntityHumanBase.class, new RenderHuman(new ModelHumanSkeleton(), 0.5F, new ResourceLocation("chocolatequest:textures/entity/woodMan.png")));
/* 171:182 */     RenderingRegistry.registerEntityRenderingHandler(EntityGolemMecha.class, new RenderHumanMecha(new ModelGolemMecha(), 0.5F, new ResourceLocation("chocolatequest:textures/entity/golemMecha.png")));
/* 172:183 */     RenderingRegistry.registerEntityRenderingHandler(EntityGolemMechaHeavy.class, new RenderHumanMecha(new ModelGolemMechaHeavy(), 0.5F, new ResourceLocation("chocolatequest:textures/entity/golemMechaElite.png")));
/* 173:    */     
/* 174:185 */     RenderingRegistry.registerEntityRenderingHandler(EntityHumanSkeleton.class, new RenderHuman(new ModelHumanSkeleton(), 0.5F, new ResourceLocation("textures/entity/skeleton/skeleton.png")));
/* 175:186 */     RenderingRegistry.registerEntityRenderingHandler(EntityHumanZombie.class, new RenderHuman(new ModelHumanZombie(), 0.5F, new ResourceLocation("textures/entity/zombie/zombie.png")));
/* 176:187 */     RenderingRegistry.registerEntityRenderingHandler(EntityHumanSpecter.class, new RenderHuman(new ModelSpecter(), 0.5F, new ResourceLocation("chocolatequest:textures/entity/specter.png")));
/* 177:188 */     RenderingRegistry.registerEntityRenderingHandler(EntityHumanWalker.class, new RenderHuman(new ModelHuman(), 0.5F, new ResourceLocation("chocolatequest:textures/entity/shadow.png")));
/* 178:189 */     RenderingRegistry.registerEntityRenderingHandler(EntityHumanGremlin.class, new RenderHumanGremlin(0.5F, new ResourceLocation("chocolatequest:textures/entity/gremlin.png")));
/* 179:190 */     RenderingRegistry.registerEntityRenderingHandler(EntityHumanPirate.class, new RenderHuman(new ModelHuman(), 0.5F));
/* 180:191 */     RenderingRegistry.registerEntityRenderingHandler(EntityHumanMinotaur.class, new RenderHuman(new ModelMinotaur(), 0.5F, new ResourceLocation("chocolatequest:textures/entity/minotaurzombie.png")));
/* 181:192 */     RenderingRegistry.registerEntityRenderingHandler(EntityHumanPigZombie.class, new RenderHuman(new ModelHumanZombie(), 0.5F, new ResourceLocation("textures/entity/zombie_pigman.png")));
/* 182:    */     
/* 183:194 */     RenderingRegistry.registerEntityRenderingHandler(EntityWalkerBoss.class, new RenderHuman(new ModelHuman(), 0.5F, new ResourceLocation("chocolatequest:textures/entity/shadow.png")));
/* 184:195 */     RenderingRegistry.registerEntityRenderingHandler(EntityPirateBoss.class, new RenderHuman(new ModelHuman(), 0.5F, new ResourceLocation("chocolatequest:textures/entity/pirateBoss.png")));
/* 185:196 */     RenderingRegistry.registerEntityRenderingHandler(EntitySpecterBoss.class, new RenderHuman(new ModelSpecterBoss(), 0.5F, new ResourceLocation("chocolatequest:textures/entity/specterBoss.png")));
/* 186:    */     
/* 187:198 */     RenderingRegistry.registerEntityRenderingHandler(EntityGiantBoxer.class, new RenderBossBiped(new ModelGiantBoxer(), 1.0F, new ResourceLocation("chocolatequest:textures/entity/mandril.png")));
/* 188:199 */     RenderingRegistry.registerEntityRenderingHandler(EntitySlimeBoss.class, new RenderLivingBossModel(new ModelSlimeBoss(), 1.0F, new ResourceLocation("chocolatequest:textures/entity/slimeBoss.png")));
/* 189:200 */     RenderingRegistry.registerEntityRenderingHandler(EntityBull.class, new RenderLivingBossModel(new ModelBull(), 1.0F, new ResourceLocation("chocolatequest:textures/entity/icebull.png")));
/* 190:201 */     RenderingRegistry.registerEntityRenderingHandler(EntitySpiderBoss.class, new RenderLivingBossModel(new ModelSpiderBoss(), 1.0F, new ResourceLocation("chocolatequest:textures/entity/spiderBoss.png")));
/* 191:202 */     RenderingRegistry.registerEntityRenderingHandler(EntityWyvern.class, new RenderLivingModelFly(new ModelDragonQuadruped(), 1.0F, new ResourceLocation("chocolatequest:textures/entity/dragonbd.png")));
/* 192:    */     
/* 193:204 */     RenderingRegistry.registerEntityRenderingHandler(EntityPart.class, new RenderInvisiblePart());
/* 194:205 */     RenderingRegistry.registerEntityRenderingHandler(EntityTurtle.class, new RenderLivingBossModel(new ModelTurtle(), 1.0F, new ResourceLocation("chocolatequest:textures/entity/turtle.png")));
/* 195:    */     
/* 196:207 */     RenderingRegistry.registerEntityRenderingHandler(EntityReferee.class, new RenderBipedCQ(new ModelBiped(), 0.5F, new ResourceLocation("chocolatequest:textures/entity/referee.png")));
/* 197:208 */     RenderingRegistry.registerEntityRenderingHandler(EntitySummonedUndead.class, new RenderSummonedUndead(new ModelSkeletonSummoned(), 0.5F));
/* 198:209 */     RenderingRegistry.registerEntityRenderingHandler(EntityBaiter.class, new RenderBaiter(new ModelBiped(), 0.5F));
/* 199:210 */     RenderingRegistry.registerEntityRenderingHandler(EntityBaseBall.class, new RenderBallProjectile(0.5F));
/* 200:211 */     RenderingRegistry.registerEntityRenderingHandler(EntityHookShoot.class, new RenderHookShoot(0.0F));
/* 201:212 */     RenderingRegistry.registerEntityRenderingHandler(EntityProjectileBeam.class, new RenderBeam(0.0F));
/* 202:213 */     RenderingRegistry.registerEntityRenderingHandler(EntityCursor.class, new RenderBanner(0.5F));
/* 203:    */   }
/* 204:    */   
/* 205:    */   public void registerTileEntities()
/* 206:    */   {
/* 207:218 */     RenderBlockTable rbaltar = new RenderBlockTable();
/* 208:219 */     ClientRegistry.registerTileEntity(BlockAltarTileEntity.class, "table", rbaltar);
/* 209:220 */     RenderBlockArmorStand rbastand = new RenderBlockArmorStand();
/* 210:221 */     ClientRegistry.registerTileEntity(BlockArmorStandTileEntity.class, "armorStand", rbastand);
/* 211:222 */     RenderBlockBanner rbbstand = new RenderBlockBanner();
/* 212:223 */     ClientRegistry.registerTileEntity(BlockBannerStandTileEntity.class, "bannerStand", rbbstand);
/* 213:224 */     RenderBlockEditor rbEditor = new RenderBlockEditor();
/* 214:225 */     ClientRegistry.registerTileEntity(BlockEditorTileEntity.class, "Exporter", rbEditor);
/* 215:    */   }
/* 216:    */   
/* 217:229 */   public static ModelBiped defaultArmor = new ModelArmor(1.0F, 0);
/* 218:230 */   public static ModelBiped kingArmor = new ModelArmorHeavyPlate(1.0F, true);
/* 219:231 */   public static ModelBiped heavyArmor = new ModelArmorHeavyPlate(1.0F);
/* 220:232 */   public static ModelArmor mageArmor = new ModelArmorMageRobe(0.0F);
/* 221:233 */   public static ModelBiped turtleArmorModel = new ModelArmorTurtle(1.0F, 0);
/* 222:234 */   public static ModelBiped turtleHelmetModel = new ModelArmorTurtle(1.0F, 1);
/* 223:235 */   public static ModelBiped dragonHead = new ModelArmorDragon();
/* 224:236 */   public static ModelBiped bullHead = new ModelArmorMinotaur();
/* 225:237 */   public static ModelBiped[] slimeArmor = { new ModelArmorSlime(0), new ModelArmorSlime(1), new ModelArmorSlime(0.5F, 2), new ModelArmorSlime(3) };
/* 226:238 */   public static ModelArmor[] coloredArmor = { new ModelArmorColored(0), new ModelArmorColored(1), new ModelArmorColored(0.5F, 2), new ModelArmorColored(3) };
/* 227:239 */   public static ModelArmor witchHat = new ModelArmorWitchHat(1.0F);
/* 228:241 */   public static int tableRenderID = 0;
/* 229:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.ClientProxy
 * JD-Core Version:    0.7.1
 */