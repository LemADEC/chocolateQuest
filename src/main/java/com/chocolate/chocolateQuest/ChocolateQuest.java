/*   1:    */ package com.chocolate.chocolateQuest;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.API.DungeonBase;
/*   4:    */ import com.chocolate.chocolateQuest.API.DungeonRegister;
/*   5:    */ import com.chocolate.chocolateQuest.API.RegisterDungeonBuilder;
/*   6:    */ import com.chocolate.chocolateQuest.block.BlockAltar;
/*   7:    */ import com.chocolate.chocolateQuest.block.BlockArmorStand;
/*   8:    */ import com.chocolate.chocolateQuest.block.BlockBannerStand;
/*   9:    */ import com.chocolate.chocolateQuest.block.BlockDecoration;
/*  10:    */ import com.chocolate.chocolateQuest.block.BlockDungeonBrick;
/*  11:    */ import com.chocolate.chocolateQuest.block.BlockEditor;
/*  12:    */ import com.chocolate.chocolateQuest.block.BlockEditorVoid;
/*  13:    */ import com.chocolate.chocolateQuest.block.BlockMobSpawner;
/*  14:    */ import com.chocolate.chocolateQuest.block.ItemBlockDungeonBrick;
/*  15:    */ import com.chocolate.chocolateQuest.builder.BuilderCastle;
/*  16:    */ import com.chocolate.chocolateQuest.builder.BuilderEmptyCave;
/*  17:    */ import com.chocolate.chocolateQuest.builder.BuilderNether;
/*  18:    */ import com.chocolate.chocolateQuest.builder.BuilderTemplate;
/*  19:    */ import com.chocolate.chocolateQuest.builder.BuilderTemplateSurface;
/*  20:    */ import com.chocolate.chocolateQuest.command.CommandAwakeEquipement;
/*  21:    */ import com.chocolate.chocolateQuest.command.CommandItemElement;
/*  22:    */ import com.chocolate.chocolateQuest.command.CommandSpawnBoss;
/*  23:    */ import com.chocolate.chocolateQuest.config.ConfigHelper;
/*  24:    */ import com.chocolate.chocolateQuest.config.Configurations;
/*  25:    */ import com.chocolate.chocolateQuest.entity.EntityBaiter;
/*  26:    */ import com.chocolate.chocolateQuest.entity.EntityReferee;
/*  27:    */ import com.chocolate.chocolateQuest.entity.EntitySummonedUndead;
/*  28:    */ import com.chocolate.chocolateQuest.entity.boss.EntityBull;
/*  29:    */ import com.chocolate.chocolateQuest.entity.boss.EntityGiantBoxer;
/*  30:    */ import com.chocolate.chocolateQuest.entity.boss.EntityPart;
/*  31:    */ import com.chocolate.chocolateQuest.entity.boss.EntityPartRidable;
/*  32:    */ import com.chocolate.chocolateQuest.entity.boss.EntitySlimeBoss;
/*  33:    */ import com.chocolate.chocolateQuest.entity.boss.EntitySlimePart;
/*  34:    */ import com.chocolate.chocolateQuest.entity.boss.EntitySpiderBoss;
/*  35:    */ import com.chocolate.chocolateQuest.entity.boss.EntityTurtle;
/*  36:    */ import com.chocolate.chocolateQuest.entity.boss.EntityTurtlePart;
/*  37:    */ import com.chocolate.chocolateQuest.entity.boss.EntityWyvern;
/*  38:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanGremlin;
/*  39:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanMinotaur;
/*  40:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanPigZombie;
/*  41:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanPirate;
/*  42:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanSkeleton;
/*  43:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanSpecter;
/*  44:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanWalker;
/*  45:    */ import com.chocolate.chocolateQuest.entity.mob.EntityHumanZombie;
/*  46:    */ import com.chocolate.chocolateQuest.entity.mob.EntityLich;
/*  47:    */ import com.chocolate.chocolateQuest.entity.mob.EntityNecromancer;
/*  48:    */ import com.chocolate.chocolateQuest.entity.mob.EntityPirateBoss;
/*  49:    */ import com.chocolate.chocolateQuest.entity.mob.EntitySpecterBoss;
/*  50:    */ import com.chocolate.chocolateQuest.entity.mob.EntityWalkerBoss;
/*  51:    */ import com.chocolate.chocolateQuest.entity.mob.registry.DungeonMonstersBase;
/*  52:    */ import com.chocolate.chocolateQuest.entity.mob.registry.MobDefault;
/*  53:    */ import com.chocolate.chocolateQuest.entity.mob.registry.MobGremlin;
/*  54:    */ import com.chocolate.chocolateQuest.entity.mob.registry.MobMinotaur;
/*  55:    */ import com.chocolate.chocolateQuest.entity.mob.registry.MobPirate;
/*  56:    */ import com.chocolate.chocolateQuest.entity.mob.registry.MobSkeleton;
/*  57:    */ import com.chocolate.chocolateQuest.entity.mob.registry.MobSpecter;
/*  58:    */ import com.chocolate.chocolateQuest.entity.mob.registry.MobWalker;
/*  59:    */ import com.chocolate.chocolateQuest.entity.mob.registry.MobZombie;
/*  60:    */ import com.chocolate.chocolateQuest.entity.mob.registry.MobZombiePig;
/*  61:    */ import com.chocolate.chocolateQuest.entity.mob.registry.RegisterDungeonMobs;
/*  62:    */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMecha;
/*  63:    */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMechaHeavy;
/*  64:    */ import com.chocolate.chocolateQuest.entity.npc.EntityHumanDummy;
/*  65:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityBaseBall;
/*  66:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityHookShoot;
/*  67:    */ import com.chocolate.chocolateQuest.entity.projectile.EntityProjectileBeam;
/*  68:    */ import com.chocolate.chocolateQuest.items.ItemAlchemistBag;
/*  69:    */ import com.chocolate.chocolateQuest.items.ItemArmorBase;
/*  70:    */ import com.chocolate.chocolateQuest.items.ItemArmorBootsCloud;
/*  71:    */ import com.chocolate.chocolateQuest.items.ItemArmorBull;
/*  72:    */ import com.chocolate.chocolateQuest.items.ItemArmorColored;
/*  73:    */ import com.chocolate.chocolateQuest.items.ItemArmorHeavy;
/*  74:    */ import com.chocolate.chocolateQuest.items.ItemArmorHelmetDragon;
/*  75:    */ import com.chocolate.chocolateQuest.items.ItemArmorHelmetScouter;
/*  76:    */ import com.chocolate.chocolateQuest.items.ItemArmorHelmetWitch;
/*  77:    */ import com.chocolate.chocolateQuest.items.ItemArmorKing;
/*  78:    */ import com.chocolate.chocolateQuest.items.ItemArmorRobe;
/*  79:    */ import com.chocolate.chocolateQuest.items.ItemArmorSlime;
/*  80:    */ import com.chocolate.chocolateQuest.items.ItemArmorSpider;
/*  81:    */ import com.chocolate.chocolateQuest.items.ItemArmorTurtle;
/*  82:    */ import com.chocolate.chocolateQuest.items.ItemBanner;
/*  83:    */ import com.chocolate.chocolateQuest.items.ItemCursedBone;
/*  84:    */ import com.chocolate.chocolateQuest.items.ItemEggBD;
/*  85:    */ import com.chocolate.chocolateQuest.items.ItemHookShoot;
/*  86:    */ import com.chocolate.chocolateQuest.items.ItemHookShootSpider;
/*  87:    */ import com.chocolate.chocolateQuest.items.ItemMulti;
/*  88:    */ import com.chocolate.chocolateQuest.items.ItemPickaxeMagic;
/*  89:    */ import com.chocolate.chocolateQuest.items.ItemPotionHeal;
/*  90:    */ import com.chocolate.chocolateQuest.items.ItemShied;
/*  91:    */ import com.chocolate.chocolateQuest.items.ItemStaffBase;
/*  92:    */ import com.chocolate.chocolateQuest.items.ItemStaffHeal;
/*  93:    */ import com.chocolate.chocolateQuest.items.gun.ItemAmmoLoader;
/*  94:    */ import com.chocolate.chocolateQuest.items.gun.ItemBubbleCannon;
/*  95:    */ import com.chocolate.chocolateQuest.items.gun.ItemGolem;
/*  96:    */ import com.chocolate.chocolateQuest.items.gun.ItemGolemCannon;
/*  97:    */ import com.chocolate.chocolateQuest.items.gun.ItemGolemFramethrower;
/*  98:    */ import com.chocolate.chocolateQuest.items.gun.ItemGolemHeavy;
/*  99:    */ import com.chocolate.chocolateQuest.items.gun.ItemGolemMachineGun;
/* 100:    */ import com.chocolate.chocolateQuest.items.gun.ItemGolemWeapon;
/* 101:    */ import com.chocolate.chocolateQuest.items.gun.ItemPistol;
/* 102:    */ import com.chocolate.chocolateQuest.items.mobControl.ItemController;
/* 103:    */ import com.chocolate.chocolateQuest.items.mobControl.ItemMobToSpawner;
/* 104:    */ import com.chocolate.chocolateQuest.items.mobControl.ItemPathMarker;
/* 105:    */ import com.chocolate.chocolateQuest.items.mobControl.ItemTeamEditor;
/* 106:    */ import com.chocolate.chocolateQuest.items.swords.ItemBaseBroadSword;
/* 107:    */ import com.chocolate.chocolateQuest.items.swords.ItemBaseDagger;
/* 108:    */ import com.chocolate.chocolateQuest.items.swords.ItemBaseSpear;
/* 109:    */ import com.chocolate.chocolateQuest.items.swords.ItemBaseSwordDefensive;
/* 110:    */ import com.chocolate.chocolateQuest.items.swords.ItemBigSwordArea;
/* 111:    */ import com.chocolate.chocolateQuest.items.swords.ItemDaggerEnd;
/* 112:    */ import com.chocolate.chocolateQuest.items.swords.ItemDaggerNinja;
/* 113:    */ import com.chocolate.chocolateQuest.items.swords.ItemHookSword;
/* 114:    */ import com.chocolate.chocolateQuest.items.swords.ItemSpearFire;
/* 115:    */ import com.chocolate.chocolateQuest.items.swords.ItemSpearGun;
/* 116:    */ import com.chocolate.chocolateQuest.items.swords.ItemSwordAndShield;
/* 117:    */ import com.chocolate.chocolateQuest.items.swords.ItemSwordEffect;
/* 118:    */ import com.chocolate.chocolateQuest.items.swords.ItemSwordWalker;
/* 119:    */ import com.chocolate.chocolateQuest.magic.Elements;
/* 120:    */ import com.chocolate.chocolateQuest.magic.EnchantmentMagicDefense;
/* 121:    */ import com.chocolate.chocolateQuest.magic.SpellBase;
/* 122:    */ import com.chocolate.chocolateQuest.misc.DungeonsItemsTab;
/* 123:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/* 124:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/* 125:    */ import cpw.mods.fml.common.Mod;
/* 126:    */ import cpw.mods.fml.common.Mod.EventHandler;
/* 127:    */ import cpw.mods.fml.common.Mod.Instance;
/* 128:    */ import cpw.mods.fml.common.SidedProxy;
/* 129:    */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/* 130:    */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/* 131:    */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/* 132:    */ import cpw.mods.fml.common.event.FMLServerStartingEvent;
/* 133:    */ import cpw.mods.fml.common.eventhandler.EventBus;
/* 134:    */ import cpw.mods.fml.common.network.NetworkRegistry;
/* 135:    */ import cpw.mods.fml.common.registry.EntityRegistry;
/* 136:    */ import cpw.mods.fml.common.registry.GameRegistry;
/* 137:    */ import java.io.File;
/* 138:    */ import net.minecraft.block.Block;
/* 139:    */ import net.minecraft.block.material.Material;
/* 140:    */ import net.minecraft.command.ICommandManager;
/* 141:    */ import net.minecraft.command.ServerCommandManager;
/* 142:    */ import net.minecraft.enchantment.Enchantment;
/* 143:    */ import net.minecraft.init.Items;
/* 144:    */ import net.minecraft.item.Item;
/* 145:    */ import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
/* 146:    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/* 147:    */ import net.minecraft.potion.Potion;
/* 148:    */ import net.minecraft.server.MinecraftServer;
/* 149:    */ import net.minecraftforge.common.MinecraftForge;
/* 150:    */ import net.minecraftforge.common.config.Configuration;
/* 151:    */ 
/* 152:    */ @Mod(modid="chocolateQuest", version="0.0", name="Chocolate Quest")
/* 153:    */ public class ChocolateQuest
/* 154:    */ {
/* 155:    */   public static final String MODID = "chocolateQuest";
/* 156:    */   public static final String VERSION = "0.0";
/* 157:    */   @SidedProxy(clientSide="com.chocolate.chocolateQuest.client.ClientProxy", serverSide="com.chocolate.chocolateQuest.CommonProxy")
/* 158:    */   public static CommonProxy proxy;
/* 159:    */   @Mod.Instance("chocolateQuest")
/* 160:    */   public static ChocolateQuest instance;
/* 161:    */   public static ChannelHandler channel;
/* 162:    */   public static Configurations config;
/* 163:169 */   public static DungeonsItemsTab tabItems = new DungeonsItemsTab("Items");
/* 164:    */   public static DungeonMonstersBase defaultMob;
/* 165:    */   public static DungeonMonstersBase skeleton;
/* 166:    */   public static DungeonMonstersBase zombie;
/* 167:    */   public static DungeonMonstersBase specter;
/* 168:    */   public static DungeonMonstersBase gremlin;
/* 169:    */   public static DungeonMonstersBase walker;
/* 170:    */   public static DungeonMonstersBase pirate;
/* 171:    */   public static DungeonMonstersBase pigZombie;
/* 172:    */   public static DungeonMonstersBase minotaur;
/* 173:    */   public static Block table;
/* 174:    */   public static Block armorStand;
/* 175:    */   public static Block bannerStand;
/* 176:    */   public static Block dungeonBrick;
/* 177:    */   public static Block spawner;
/* 178:    */   public static Block exporter;
/* 179:    */   public static Block emptyBlock;
/* 180:    */   public static Block exporterChest;
/* 181:184 */   public static final Enchantment enchantmentMagicDefense = new EnchantmentMagicDefense(52, 1);
/* 182:188 */   public static Item egg = new ItemEggBD().setUnlocalizedName("egg").setCreativeTab(tabItems);
/* 183:189 */   public static Item mobToSpawner = new ItemMobToSpawner().setUnlocalizedName("mobToSpawner").setCreativeTab(tabItems);
/* 184:190 */   public static Item magicPickaxe = new ItemPickaxeMagic().setUnlocalizedName("pickaxeMagic").setCreativeTab(tabItems);
/* 185:192 */   public static Item swordMoonLight = new ItemSwordEffect(Item.ToolMaterial.EMERALD, "swordMoonLight").setShieldId(14).setUnlocalizedName("moonSword").setCreativeTab(tabItems);
/* 186:193 */   public static Item swordSunLight = new ItemSwordEffect(Item.ToolMaterial.EMERALD, "swordDefensive").setShieldId(13).setUnlocalizedName("defenseSword").setCreativeTab(tabItems);
/* 187:194 */   public static Item swordTurtle = new ItemBaseSwordDefensive(Item.ToolMaterial.EMERALD, "swordTurtle").setShieldId(15).setUnlocalizedName("swordTurtle").setCreativeTab(tabItems);
/* 188:195 */   public static Item swordSpider = new ItemSwordEffect(Item.ToolMaterial.IRON, "swordSpider", Potion.poison.id).setShieldId(18).setUnlocalizedName("swordSpider").setCreativeTab(tabItems);
/* 189:196 */   public static Item endSword = new ItemSwordWalker().setUnlocalizedName("walkerSword").setCreativeTab(tabItems);
/* 190:197 */   public static Item tricksterDagger = new ItemDaggerNinja().setUnlocalizedName("pirateDagger").setCreativeTab(tabItems);
/* 191:198 */   public static Item ninjaDagger = new ItemDaggerEnd().setUnlocalizedName("ninjaDagger").setCreativeTab(tabItems);
/* 192:199 */   public static Item fireSpear = new ItemSpearFire().setUnlocalizedName("dwarfSpear").setCreativeTab(tabItems);
/* 193:200 */   public static Item bigSwordBull = new ItemBigSwordArea(Item.ToolMaterial.EMERALD, "bigSwordBull", 6.0F).setUnlocalizedName("bigSwordBull").setCreativeTab(tabItems);
/* 194:201 */   public static Item monkingSword = new ItemBaseBroadSword(Item.ToolMaterial.EMERALD, "swordMonking", 8.0F).setUnlocalizedName("swordMonking").setCreativeTab(tabItems);
/* 195:202 */   public static Item monkingDagger = new ItemBaseDagger(Item.ToolMaterial.EMERALD, "daggerMonking", 4, 1.2F).setCreativeTab(tabItems).setUnlocalizedName("daggerMonking");
/* 196:203 */   public static Item monkingSwordAndShield = new ItemBaseSwordDefensive(Item.ToolMaterial.EMERALD, "swordShiedMonking", 5, 1.2F).setShieldId(17).setCreativeTab(tabItems).setUnlocalizedName("swordShiedMonking").setMaxDamage(1988);
/* 197:204 */   public static Item hookSword = new ItemHookSword(Item.ToolMaterial.EMERALD).setUnlocalizedName("hookSword").setCreativeTab(tabItems);
/* 198:205 */   public static Item banner = new ItemBanner().setUnlocalizedName("banner").setCreativeTab(tabItems);
/* 199:206 */   public static Item shield = new ItemShied().setUnlocalizedName("shield").setCreativeTab(tabItems);
/* 200:208 */   public static Item rustedDagger = new ItemBaseDagger(Item.ToolMaterial.GOLD, "rustedDagger", 1, 3.0F).setCreativeTab(tabItems).setUnlocalizedName("rustedDagger").setMaxDamage(1988);
/* 201:209 */   public static Item rustedSpear = new ItemBaseSpear(Item.ToolMaterial.GOLD, "rustedSpear", 1, 2.5F).setCreativeTab(tabItems).setUnlocalizedName("rustedSpear").setMaxDamage(1988);
/* 202:210 */   public static Item rustedBigSword = new ItemBaseBroadSword(Item.ToolMaterial.GOLD, "rustedBigSword", 2.0F, 4.3F).setCreativeTab(tabItems).setUnlocalizedName("rustedBigSword").setMaxDamage(1988);
/* 203:211 */   public static Item rustedSwordAndShied = new ItemBaseSwordDefensive(Item.ToolMaterial.GOLD, "rustedSword", 1, 3.0F).setShieldId(16).setCreativeTab(tabItems).setUnlocalizedName("rustedSwordAndShied").setMaxDamage(1988);
/* 204:213 */   public static Item dragonHelmet = new ItemArmorHelmetDragon().setEpic().setUnlocalizedName("dragonHelmet").setCreativeTab(tabItems);
/* 205:214 */   public static Item scouter = new ItemArmorHelmetScouter().setEpic().setUnlocalizedName("scouter").setCreativeTab(tabItems);
/* 206:215 */   public static Item witchHat = new ItemArmorHelmetWitch().setEpic().setUnlocalizedName("witchHat").setCreativeTab(tabItems);
/* 207:216 */   public static Item cloudBoots = new ItemArmorBootsCloud().setEpic().setUnlocalizedName("cloudBoots").setCreativeTab(tabItems);
/* 208:217 */   public static Item kingArmor = new ItemArmorKing().setEpic().setUnlocalizedName("kingArmor").setCreativeTab(tabItems);
/* 209:218 */   public static Item slimeHelmet = new ItemArmorSlime(0, "slimeHelmet").setUnlocalizedName("slimeHelmet").setCreativeTab(tabItems);
/* 210:219 */   public static Item slimePlate = new ItemArmorSlime(1, "slimePlate").setUnlocalizedName("slimePlate").setCreativeTab(tabItems);
/* 211:220 */   public static Item slimePants = new ItemArmorSlime(2, "slimePants").setUnlocalizedName("slimePants").setCreativeTab(tabItems);
/* 212:221 */   public static Item slimeBoots = new ItemArmorSlime(3, "slimeBoots").setUnlocalizedName("slimeBoots").setCreativeTab(tabItems);
/* 213:222 */   public static Item turtleHelmet = new ItemArmorTurtle(0, "turtleHelmet").setUnlocalizedName("turtleHelmet").setCreativeTab(tabItems);
/* 214:223 */   public static Item turtlePlate = new ItemArmorTurtle(1, "turtlePlate").setUnlocalizedName("turtlePlate").setCreativeTab(tabItems);
/* 215:224 */   public static Item turtlePants = new ItemArmorTurtle(2, "turtlePants").setUnlocalizedName("turtlePants").setCreativeTab(tabItems);
/* 216:225 */   public static Item turtleBoots = new ItemArmorTurtle(3, "turtleBoots").setUnlocalizedName("turtleBoots").setCreativeTab(tabItems);
/* 217:226 */   public static Item bullHelmet = new ItemArmorBull(0, "bullHelmet").setUnlocalizedName("bullHelmet").setCreativeTab(tabItems);
/* 218:227 */   public static Item bullPlate = new ItemArmorBull(1, "bullPlate").setUnlocalizedName("bullPlate").setCreativeTab(tabItems);
/* 219:228 */   public static Item bullPants = new ItemArmorBull(2, "bullPants").setUnlocalizedName("bullPants").setCreativeTab(tabItems);
/* 220:229 */   public static Item bullBoots = new ItemArmorBull(3, "bullBoots").setUnlocalizedName("bullBoots").setCreativeTab(tabItems);
/* 221:230 */   public static Item spiderHelmet = new ItemArmorSpider(0, "spiderHelmet").setUnlocalizedName("spiderHelmet").setCreativeTab(tabItems);
/* 222:231 */   public static Item spiderPlate = new ItemArmorSpider(1, "spiderPlate").setUnlocalizedName("spiderPlate").setCreativeTab(tabItems);
/* 223:232 */   public static Item spiderPants = new ItemArmorSpider(2, "spiderPants").setUnlocalizedName("spiderPants").setCreativeTab(tabItems);
/* 224:233 */   public static Item spiderBoots = new ItemArmorSpider(3, "spiderBoots").setUnlocalizedName("spiderBoots").setCreativeTab(tabItems);
/* 225:234 */   public static Item diamondHeavyHelmet = new ItemArmorHeavy(0, "armorHeavyDiamond", Items.diamond).setUnlocalizedName("diamondHeavyHelmet").setCreativeTab(tabItems);
/* 226:235 */   public static Item diamondHeavyPlate = new ItemArmorHeavy(1, "armorHeavyDiamond", Items.diamond).setUnlocalizedName("diamondHeavyPlate").setCreativeTab(tabItems);
/* 227:236 */   public static Item diamondHeavyPants = new ItemArmorHeavy(2, "armorHeavyDiamond", Items.diamond).setUnlocalizedName("diamondHeavyPants").setCreativeTab(tabItems);
/* 228:237 */   public static Item diamondHeavyBoots = new ItemArmorHeavy(3, "armorHeavyDiamond", Items.diamond).setUnlocalizedName("diamondHeavyBoots").setCreativeTab(tabItems);
/* 229:238 */   public static Item ironHeavyHelmet = new ItemArmorHeavy(0, "armorHeavyIron", Items.iron_ingot, ItemArmor.ArmorMaterial.DIAMOND).setUnlocalizedName("ironHeavyHelmet").setCreativeTab(tabItems);
/* 230:239 */   public static Item ironHeavyPlate = new ItemArmorHeavy(1, "armorHeavyIron", Items.iron_ingot, ItemArmor.ArmorMaterial.DIAMOND).setUnlocalizedName("ironHeavyPlate").setCreativeTab(tabItems);
/* 231:240 */   public static Item ironHeavyPants = new ItemArmorHeavy(2, "armorHeavyIron", Items.iron_ingot, ItemArmor.ArmorMaterial.DIAMOND).setUnlocalizedName("ironHeavyPants").setCreativeTab(tabItems);
/* 232:241 */   public static Item ironHeavyBoots = new ItemArmorHeavy(3, "armorHeavyIron", Items.iron_ingot, ItemArmor.ArmorMaterial.DIAMOND).setUnlocalizedName("ironHeavyBoots").setCreativeTab(tabItems);
/* 233:242 */   public static Item inquisitionHelmet = new ItemArmorBase(ItemArmor.ArmorMaterial.DIAMOND, 0, "armorInquisition").setUnlocalizedName("inquisitionHelmet").setCreativeTab(tabItems);
/* 234:243 */   public static Item inquisitionPlate = new ItemArmorBase(ItemArmor.ArmorMaterial.DIAMOND, 1, "armorInquisition").setUnlocalizedName("inquisitionPlate").setCreativeTab(tabItems);
/* 235:244 */   public static Item inquisitionPants = new ItemArmorBase(ItemArmor.ArmorMaterial.DIAMOND, 2, "armorInquisition").setUnlocalizedName("inquisitionPants").setCreativeTab(tabItems);
/* 236:245 */   public static Item inquisitionBoots = new ItemArmorBase(ItemArmor.ArmorMaterial.DIAMOND, 3, "armorInquisition").setUnlocalizedName("inquisitionBoots").setCreativeTab(tabItems);
/* 237:247 */   public static Item diamondHelmet = new ItemArmorColored(ItemArmor.ArmorMaterial.DIAMOND, 0, "diamond", 65535).setUnlocalizedName("diamondColoredHelmet").setCreativeTab(tabItems);
/* 238:248 */   public static Item diamondPlate = new ItemArmorColored(ItemArmor.ArmorMaterial.DIAMOND, 1, "diamond", 65535).setUnlocalizedName("diamondColoredPlate").setCreativeTab(tabItems);
/* 239:249 */   public static Item diamondPants = new ItemArmorColored(ItemArmor.ArmorMaterial.DIAMOND, 2, "diamond", 65535).setUnlocalizedName("diamondColoredPants").setCreativeTab(tabItems);
/* 240:250 */   public static Item diamondBoots = new ItemArmorColored(ItemArmor.ArmorMaterial.DIAMOND, 3, "diamond", 65535).setUnlocalizedName("diamondColoredBoots").setCreativeTab(tabItems);
/* 241:251 */   public static Item ironHelmet = new ItemArmorColored(ItemArmor.ArmorMaterial.IRON, 0, "iron", 13421772).setUnlocalizedName("ironColoredHelmet").setCreativeTab(tabItems);
/* 242:252 */   public static Item ironPlate = new ItemArmorColored(ItemArmor.ArmorMaterial.IRON, 1, "iron", 13421772).setUnlocalizedName("ironColoredPlate").setCreativeTab(tabItems);
/* 243:253 */   public static Item ironPants = new ItemArmorColored(ItemArmor.ArmorMaterial.IRON, 2, "iron", 13421772).setUnlocalizedName("ironColoredPants").setCreativeTab(tabItems);
/* 244:254 */   public static Item ironBoots = new ItemArmorColored(ItemArmor.ArmorMaterial.IRON, 3, "iron", 13421772).setUnlocalizedName("ironColoredBoots").setCreativeTab(tabItems);
/* 245:257 */   public static Item cursedBone = new ItemCursedBone().setUnlocalizedName("cursedBone").setCreativeTab(tabItems);
/* 246:258 */   public static Item armorMage = new ItemArmorRobe().setUnlocalizedName("mageRobe").setCreativeTab(tabItems);
/* 247:259 */   public static Item staffHeal = new ItemStaffHeal().setUnlocalizedName("staffLife").setCreativeTab(tabItems);
/* 248:260 */   public static Item staffPhysic = new ItemStaffBase(Elements.physic).setUnlocalizedName("staffPhysic").setCreativeTab(tabItems);
/* 249:261 */   public static Item staffMagic = new ItemStaffBase(Elements.magic).setUnlocalizedName("staffMagic").setCreativeTab(tabItems);
/* 250:262 */   public static Item staffBlast = new ItemStaffBase(Elements.blast).setUnlocalizedName("staffBlast").setCreativeTab(tabItems);
/* 251:263 */   public static Item staffFire = new ItemStaffBase(Elements.fire).setUnlocalizedName("staffFire").setCreativeTab(tabItems);
/* 252:264 */   public static Item staffLight = new ItemStaffBase(Elements.light).setUnlocalizedName("staffLight").setCreativeTab(tabItems);
/* 253:265 */   public static Item spell = new ItemMulti(SpellBase.getNames(), "spell").setUnlocalizedName("spell").setCreativeTab(tabItems);
/* 254:267 */   public static Item ironSwordAndShield = new ItemSwordAndShield(Item.ToolMaterial.IRON).setCreativeTab(tabItems).setUnlocalizedName("ironSwordAndShield");
/* 255:268 */   public static Item diamondSwordAndShield = new ItemSwordAndShield(Item.ToolMaterial.EMERALD).setCreativeTab(tabItems).setUnlocalizedName("diamondSwordAndShield");
/* 256:269 */   public static Item ironDagger = new ItemBaseDagger(Item.ToolMaterial.IRON, "daggerIron").setCreativeTab(tabItems).setUnlocalizedName("daggerIron");
/* 257:270 */   public static Item diamondDagger = new ItemBaseDagger(Item.ToolMaterial.EMERALD, "daggerDiamond").setCreativeTab(tabItems).setUnlocalizedName("daggerDiamond");
/* 258:271 */   public static Item ironSpear = new ItemBaseSpear(Item.ToolMaterial.IRON, "spearIron").setCreativeTab(tabItems).setUnlocalizedName("spearIron");
/* 259:272 */   public static Item diamondSpear = new ItemBaseSpear(Item.ToolMaterial.EMERALD, "spearDiamond").setCreativeTab(tabItems).setUnlocalizedName("spearDiamond");
/* 260:273 */   public static Item ironBigsword = new ItemBaseBroadSword(Item.ToolMaterial.IRON, "bigSwordIron").setCreativeTab(tabItems).setUnlocalizedName("bigswordIron");
/* 261:274 */   public static Item diamondBigsword = new ItemBaseBroadSword(Item.ToolMaterial.EMERALD, "bigSwordDiamond").setCreativeTab(tabItems).setUnlocalizedName("bigswordDiamond");
/* 262:276 */   public static Item revolver = new ItemPistol().setUnlocalizedName("revolver").setCreativeTab(tabItems);
/* 263:277 */   public static Item spearGun = new ItemSpearGun().setUnlocalizedName("spearGun").setCreativeTab(tabItems);
/* 264:278 */   public static Item golem = new ItemGolem("mechaGolem").setUnlocalizedName("mecha").setCreativeTab(tabItems);
/* 265:279 */   public static Item golemHeavy = new ItemGolemHeavy().setUnlocalizedName("mechaHeavy").setCreativeTab(tabItems);
/* 266:280 */   public static Item golemUpgrade = new ItemMulti(new String[] { "golemArmor", "golemField", "golemShield", "golemRockets" }, "golemUpgrade").setUnlocalizedName("golemUpgrade").setCreativeTab(tabItems);
/* 267:281 */   public static Item ammoLoader = new ItemAmmoLoader().setUnlocalizedName("ammoLoader").setCreativeTab(tabItems);
/* 268:282 */   public static Item golemGun = new ItemGolemWeapon(10, 16.0F, 5.0F).setUnlocalizedName("golemGun").setCreativeTab(tabItems);
/* 269:283 */   public static Item golemFlameThrower = new ItemGolemFramethrower().setUnlocalizedName("golemFlameThrower").setCreativeTab(tabItems);
/* 270:284 */   public static Item golemRifleGun = new ItemGolemWeapon(20, 64.0F, 0.0F).setUnlocalizedName("golemRailGun").setCreativeTab(tabItems);
/* 271:285 */   public static Item golemMachineGun = new ItemGolemMachineGun().setUnlocalizedName("golemMachineGun").setCreativeTab(tabItems);
/* 272:286 */   public static Item golemCannon = new ItemGolemCannon(30, 25.0F, 3.0F, 3).setUnlocalizedName("golemCannon").setCreativeTab(tabItems);
/* 273:287 */   public static Item golemBubbleCannon = new ItemBubbleCannon(30, 16.0F, 3.0F, 3).setUnlocalizedName("golemBubbleCannon").setCreativeTab(tabItems);
/* 274:289 */   public static Item hookShoot = new ItemHookShoot(0, "chocolatequest:hookShoot").setUnlocalizedName("hookshoot").setCreativeTab(tabItems);
/* 275:290 */   public static Item longShoot = new ItemHookShoot(1, "chocolatequest:hookLong").setUnlocalizedName("longshoot").setCreativeTab(tabItems);
/* 276:291 */   public static Item manualShoot = new ItemHookShoot(2, "chocolatequest:hookManual").setUnlocalizedName("manualshoot").setCreativeTab(tabItems);
/* 277:292 */   public static Item spiderHook = new ItemHookShootSpider(3).setUnlocalizedName("spiderhook").setCreativeTab(tabItems);
/* 278:294 */   public static Item bullet = new ItemMulti(new String[] { "ironBullet", "goldBullet", "magicBullet", "fireBullet", "cannonBullet" }, "bullets").setUnlocalizedName("bullet").setCreativeTab(tabItems);
/* 279:295 */   public static Item material = new ItemMulti(new String[] { "hammer", "turtleScale", "bullLeather", "slimeBall", "spiderLeather", "monkingBone" }, "mat").setUnlocalizedName("material").setCreativeTab(tabItems);
/* 280:297 */   public static Item alchemistBag = new ItemAlchemistBag().setUnlocalizedName("potionsBag").setCreativeTab(tabItems);
/* 281:298 */   public static Item potion = new ItemPotionHeal().setUnlocalizedName("healingPotion").setCreativeTab(tabItems);
/* 282:299 */   public static Item pathMarker = new ItemPathMarker().setUnlocalizedName("pathMarker").setCreativeTab(tabItems);
/* 283:300 */   public static Item controller = new ItemController().setUnlocalizedName("controller").setCreativeTab(tabItems);
/* 284:301 */   public static Item controllerTeam = new ItemTeamEditor().setUnlocalizedName("controllerTeam").setCreativeTab(tabItems);
/* 285:    */   public static final int GUI_MOB = 0;
/* 286:    */   public static final int GUI_EDITOR = 1;
/* 287:    */   public static final int GUI_ARMORSTAND = 2;
/* 288:    */   public static final int GUI_GUN = 3;
/* 289:    */   public static final int GUI_CHEST = 4;
/* 290:    */   
/* 291:    */   @Mod.EventHandler
/* 292:    */   public void preInit(FMLPreInitializationEvent event)
/* 293:    */   {
/* 294:308 */     Configuration configFile = new Configuration(event.getSuggestedConfigurationFile());
/* 295:309 */     config = new Configurations();
/* 296:310 */     config.load(configFile);
/* 297:    */     
/* 298:312 */     GameRegistry.registerWorldGenerator(new WorldGeneratorNew(config.dungeonSeparation), 100);
/* 299:313 */     MinecraftForge.EVENT_BUS.register(new EventHandlerCQ());
/* 300:    */     
/* 301:315 */     registerBlocks();
/* 302:316 */     registerItems();
/* 303:    */     
/* 304:318 */     proxy.register();
/* 305:319 */     channel = new ChannelHandler();
/* 306:    */     
/* 307:321 */     registerEntities();
/* 308:    */   }
/* 309:    */   
/* 310:    */   @Mod.EventHandler
/* 311:    */   public void postInit(FMLPostInitializationEvent event)
/* 312:    */   {
/* 313:327 */     proxy.postInit();
/* 314:328 */     ConfigHelper.readChests();
/* 315:    */   }
/* 316:    */   
/* 317:    */   @Mod.EventHandler
/* 318:    */   public void load(FMLInitializationEvent evt)
/* 319:    */   {
/* 320:334 */     NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
/* 321:335 */     registerDungeons();
/* 322:    */   }
/* 323:    */   
/* 324:    */   @Mod.EventHandler
/* 325:    */   public void serverStart(FMLServerStartingEvent event)
/* 326:    */   {
/* 327:341 */     MinecraftServer server = MinecraftServer.getServer();
/* 328:342 */     ICommandManager command = server.getCommandManager();
/* 329:343 */     ServerCommandManager manager = (ServerCommandManager)command;
/* 330:344 */     manager.registerCommand(new CommandSpawnBoss());
/* 331:    */     
/* 332:346 */     manager.registerCommand(new CommandAwakeEquipement());
/* 333:347 */     manager.registerCommand(new CommandItemElement());
/* 334:    */   }
/* 335:    */   
/* 336:    */   public void registerEntities()
/* 337:    */   {
/* 338:352 */     int id = 0;
/* 339:353 */     EntityRegistry.registerModEntity(EntityHumanDummy.class, "dummy", id++, this, 80, 3, true);
/* 340:354 */     EntityRegistry.registerModEntity(EntityGolemMecha.class, "mecha", id++, this, 80, 3, true);
/* 341:355 */     EntityRegistry.registerModEntity(EntityGolemMechaHeavy.class, "mechaHeavy", id++, this, 80, 3, true);
/* 342:    */     
/* 343:357 */     EntityRegistry.registerModEntity(EntityHumanSkeleton.class, "armoredSkeleton", id++, this, 80, 3, true);
/* 344:358 */     EntityRegistry.registerModEntity(EntityNecromancer.class, "necromancer", id++, this, 80, 3, true);
/* 345:359 */     EntityRegistry.registerModEntity(EntityHumanZombie.class, "armoredZombie", id++, this, 80, 3, true);
/* 346:360 */     EntityRegistry.registerModEntity(EntityLich.class, "Lich", id++, this, 80, 3, true);
/* 347:361 */     EntityRegistry.registerModEntity(EntityHumanSpecter.class, "specter", id++, this, 80, 3, true);
/* 348:362 */     EntityRegistry.registerModEntity(EntityHumanPigZombie.class, "pigzombie", id++, this, 80, 3, true);
/* 349:363 */     EntityRegistry.registerModEntity(EntityHumanMinotaur.class, "minotaur", id++, this, 80, 3, true);
/* 350:364 */     EntityRegistry.registerModEntity(EntitySpecterBoss.class, "specterBoss", id++, this, 80, 3, true);
/* 351:365 */     EntityRegistry.registerModEntity(EntityHumanWalker.class, "abyssWalker", id++, this, 80, 3, true);
/* 352:366 */     EntityRegistry.registerModEntity(EntityWalkerBoss.class, "abyssWalkerBoss", id++, this, 80, 3, true);
/* 353:367 */     EntityRegistry.registerModEntity(EntityHumanGremlin.class, "gremlin", id++, this, 80, 3, true);
/* 354:368 */     EntityRegistry.registerModEntity(EntityHumanPirate.class, "pirate", id++, this, 80, 3, true);
/* 355:369 */     EntityRegistry.registerModEntity(EntityPirateBoss.class, "pirateBoss", id++, this, 80, 3, true);
/* 356:    */     
/* 357:371 */     EntityRegistry.registerModEntity(EntityGiantBoxer.class, "monking", id++, this, 100, 3, true);
/* 358:372 */     EntityRegistry.registerModEntity(EntityBull.class, "bull", id++, this, 100, 3, true);
/* 359:373 */     EntityRegistry.registerModEntity(EntitySlimeBoss.class, "slimeBoss", id++, this, 100, 3, true);
/* 360:374 */     EntityRegistry.registerModEntity(EntitySpiderBoss.class, "spiderBoss", id++, this, 100, 3, true);
/* 361:375 */     EntityRegistry.registerModEntity(EntityWyvern.class, "greenDragon", id++, this, 100, 3, true);
/* 362:    */     
/* 363:377 */     EntityRegistry.registerModEntity(EntityTurtle.class, "TurtleBoss", id++, this, 100, 3, true);
/* 364:378 */     EntityRegistry.registerModEntity(EntityTurtlePart.class, "TurtleBossPart", id++, this, 60, 3, true);
/* 365:    */     
/* 366:380 */     EntityRegistry.registerModEntity(EntityPart.class, "EntityPart", id++, this, 100, 3, true);
/* 367:381 */     EntityRegistry.registerModEntity(EntityPartRidable.class, "EntityPartRidable", id++, this, 100, 3, true);
/* 368:382 */     EntityRegistry.registerModEntity(EntitySlimePart.class, "EntityPartSlime", id++, this, 100, 3, true);
/* 369:383 */     EntityRegistry.registerModEntity(EntityBaiter.class, "SummonedBait", id++, this, 50, 3, true);
/* 370:384 */     EntityRegistry.registerModEntity(EntitySummonedUndead.class, "SummonedUndead", id++, this, 50, 3, true);
/* 371:385 */     EntityRegistry.registerModEntity(EntityReferee.class, "Referee", id++, this, 50, 3, true);
/* 372:386 */     EntityRegistry.registerModEntity(EntityBaseBall.class, "ChocoProjectile", id++, this, 64, 3, true);
/* 373:387 */     EntityRegistry.registerModEntity(EntityHookShoot.class, "Hookshoot", id++, this, 64, 3, true);
/* 374:388 */     EntityRegistry.registerModEntity(EntityProjectileBeam.class, "Beam", id++, this, 64, 3, true);
/* 375:    */   }
/* 376:    */   
/* 377:    */   public void registerBlocks()
/* 378:    */   {
/* 379:393 */     spawner = new BlockMobSpawner().setBlockName("CQSpawner");
/* 380:394 */     GameRegistry.registerBlock(spawner, "CQSpawner");
/* 381:    */     
/* 382:    */ 
/* 383:    */ 
/* 384:    */ 
/* 385:399 */     exporter = new BlockEditor().setBlockName("exporter").setCreativeTab(tabItems);
/* 386:400 */     GameRegistry.registerBlock(exporter, "exporter");
/* 387:    */     
/* 388:402 */     armorStand = new BlockArmorStand().setHardness(0.5F).setResistance(0.1F).setBlockName("armorStand").setBlockTextureName("planks").setCreativeTab(tabItems);
/* 389:403 */     GameRegistry.registerBlock(armorStand, "armorStand");
/* 390:    */     
/* 391:405 */     bannerStand = new BlockBannerStand().setHardness(0.5F).setResistance(0.1F).setBlockName("bannerStand").setBlockTextureName("planks").setCreativeTab(tabItems);
/* 392:406 */     GameRegistry.registerBlock(bannerStand, "bannerStand");
/* 393:    */     
/* 394:408 */     table = new BlockAltar().setHardness(0.5F).setResistance(0.1F).setBlockName("table").setBlockTextureName("planks").setCreativeTab(tabItems);
/* 395:409 */     GameRegistry.registerBlock(table, "table");
/* 396:    */     
/* 397:411 */     dungeonBrick = new BlockDungeonBrick().setHardness(30.0F).setResistance(20.0F).setBlockName("dungeonBrick").setCreativeTab(tabItems);
/* 398:412 */     GameRegistry.registerBlock(dungeonBrick, ItemBlockDungeonBrick.class, "dungeonBrick");
/* 399:    */     
/* 400:    */ 
/* 401:415 */     String[] names = { "Treasure Chest", "Food Chest", "Tools Chest", "Ores Chest", "Boss Spawner" };
/* 402:416 */     exporterChest = new BlockDecoration(Material.wood, "c", names).setBlockName("ExporterChest").setCreativeTab(tabItems);
/* 403:417 */     GameRegistry.registerBlock(exporterChest, ItemBlockDungeonBrick.class, "exporterChest");
/* 404:    */     
/* 405:419 */     emptyBlock = new BlockEditorVoid().setBlockName("none").setCreativeTab(tabItems);
/* 406:420 */     GameRegistry.registerBlock(emptyBlock, "none");
/* 407:    */   }
/* 408:    */   
/* 409:    */   public void registerItems()
/* 410:    */   {
/* 411:433 */     registerItem(egg);
/* 412:434 */     registerItem(magicPickaxe);
/* 413:435 */     registerItem(mobToSpawner);
/* 414:    */     
/* 415:437 */     registerItem(rustedDagger);
/* 416:438 */     registerItem(rustedBigSword);
/* 417:439 */     registerItem(rustedSpear);
/* 418:440 */     registerItem(rustedSwordAndShied);
/* 419:    */     
/* 420:442 */     registerItem(hookSword);
/* 421:    */     
/* 422:444 */     registerItem(ironSwordAndShield);
/* 423:445 */     registerItem(diamondSwordAndShield);
/* 424:446 */     registerItem(swordMoonLight);
/* 425:447 */     registerItem(swordSunLight);
/* 426:448 */     registerItem(endSword);
/* 427:449 */     registerItem(swordTurtle);
/* 428:450 */     registerItem(swordSpider);
/* 429:451 */     registerItem(monkingSwordAndShield);
/* 430:    */     
/* 431:453 */     registerItem(ironDagger);
/* 432:454 */     registerItem(diamondDagger);
/* 433:455 */     registerItem(tricksterDagger);
/* 434:456 */     registerItem(ninjaDagger);
/* 435:457 */     registerItem(monkingDagger);
/* 436:    */     
/* 437:459 */     registerItem(ironSpear);
/* 438:460 */     registerItem(diamondSpear);
/* 439:461 */     registerItem(spearGun);
/* 440:462 */     registerItem(fireSpear);
/* 441:    */     
/* 442:464 */     registerItem(ironBigsword);
/* 443:465 */     registerItem(diamondBigsword);
/* 444:466 */     registerItem(bigSwordBull);
/* 445:467 */     registerItem(monkingSword);
/* 446:    */     
/* 447:469 */     registerItem(banner);
/* 448:470 */     registerItem(shield);
/* 449:471 */     registerItem(dragonHelmet);
/* 450:472 */     registerItem(scouter);
/* 451:473 */     registerItem(witchHat);
/* 452:474 */     registerItem(cloudBoots);
/* 453:475 */     registerItem(kingArmor);
/* 454:    */     
/* 455:477 */     registerItem(slimeHelmet);
/* 456:478 */     registerItem(slimePlate);
/* 457:479 */     registerItem(slimePants);
/* 458:480 */     registerItem(slimeBoots);
/* 459:481 */     registerItem(turtleHelmet);
/* 460:482 */     registerItem(turtlePlate);
/* 461:483 */     registerItem(turtlePants);
/* 462:484 */     registerItem(turtleBoots);
/* 463:485 */     registerItem(bullHelmet);
/* 464:486 */     registerItem(bullPlate);
/* 465:487 */     registerItem(bullPants);
/* 466:488 */     registerItem(bullBoots);
/* 467:489 */     registerItem(spiderHelmet);
/* 468:490 */     registerItem(spiderPlate);
/* 469:491 */     registerItem(spiderPants);
/* 470:492 */     registerItem(spiderBoots);
/* 471:493 */     registerItem(diamondHeavyHelmet);
/* 472:494 */     registerItem(diamondHeavyPlate);
/* 473:495 */     registerItem(diamondHeavyPants);
/* 474:496 */     registerItem(diamondHeavyBoots);
/* 475:497 */     registerItem(ironHeavyHelmet);
/* 476:498 */     registerItem(ironHeavyPlate);
/* 477:499 */     registerItem(ironHeavyPants);
/* 478:500 */     registerItem(ironHeavyBoots);
/* 479:501 */     registerItem(inquisitionHelmet);
/* 480:502 */     registerItem(inquisitionPlate);
/* 481:503 */     registerItem(inquisitionPants);
/* 482:504 */     registerItem(inquisitionBoots);
/* 483:    */     
/* 484:506 */     registerItem(diamondHelmet);
/* 485:507 */     registerItem(diamondPlate);
/* 486:508 */     registerItem(diamondPants);
/* 487:509 */     registerItem(diamondBoots);
/* 488:510 */     registerItem(ironHelmet);
/* 489:511 */     registerItem(ironPlate);
/* 490:512 */     registerItem(ironPants);
/* 491:513 */     registerItem(ironBoots);
/* 492:    */     
/* 493:515 */     registerItem(armorMage);
/* 494:516 */     registerItem(staffHeal);
/* 495:517 */     registerItem(staffPhysic);
/* 496:518 */     registerItem(staffMagic);
/* 497:519 */     registerItem(staffFire);
/* 498:520 */     registerItem(staffBlast);
/* 499:521 */     registerItem(staffLight);
/* 500:522 */     registerItem(spell);
/* 501:    */     
/* 502:524 */     registerItem(potion);
/* 503:525 */     registerItem(alchemistBag);
/* 504:526 */     registerItem(revolver);
/* 505:527 */     registerItem(golem);
/* 506:528 */     registerItem(golemHeavy);
/* 507:529 */     registerItem(golemUpgrade);
/* 508:530 */     registerItem(golemGun);
/* 509:531 */     registerItem(golemFlameThrower);
/* 510:532 */     registerItem(golemRifleGun);
/* 511:533 */     registerItem(golemCannon);
/* 512:534 */     registerItem(golemBubbleCannon);
/* 513:535 */     registerItem(golemMachineGun);
/* 514:536 */     registerItem(ammoLoader);
/* 515:537 */     registerItem(bullet);
/* 516:538 */     registerItem(material);
/* 517:    */     
/* 518:540 */     registerItem(cursedBone);
/* 519:541 */     registerItem(hookShoot);
/* 520:542 */     registerItem(longShoot);
/* 521:543 */     registerItem(manualShoot);
/* 522:544 */     registerItem(spiderHook);
/* 523:    */     
/* 524:546 */     registerItem(pathMarker);
/* 525:547 */     registerItem(controller);
/* 526:548 */     registerItem(controllerTeam);
/* 527:    */     
/* 528:550 */     tabItems.setItemIcon(endSword);
/* 529:    */   }
/* 530:    */   
/* 531:    */   public Item registerItem(Item item)
/* 532:    */   {
/* 533:555 */     GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
/* 534:556 */     return item;
/* 535:    */   }
/* 536:    */   
/* 537:    */   public void registerDungeons()
/* 538:    */   {
/* 539:562 */     defaultMob = new MobDefault();
/* 540:563 */     addMobToList(defaultMob);
/* 541:564 */     skeleton = new MobSkeleton();
/* 542:565 */     addMobToList(skeleton);
/* 543:566 */     zombie = new MobZombie();
/* 544:567 */     addMobToList(zombie);
/* 545:568 */     specter = new MobSpecter();
/* 546:569 */     addMobToList(specter);
/* 547:570 */     pigZombie = new MobZombiePig();
/* 548:571 */     addMobToList(pigZombie);
/* 549:572 */     minotaur = new MobMinotaur();
/* 550:573 */     addMobToList(minotaur);
/* 551:574 */     walker = new MobWalker();
/* 552:575 */     addMobToList(walker);
/* 553:576 */     pirate = new MobPirate();
/* 554:577 */     addMobToList(pirate);
/* 555:578 */     gremlin = new MobGremlin();
/* 556:579 */     addMobToList(gremlin);
/* 557:    */     
/* 558:581 */     RegisterDungeonBuilder.addDungeonBuilder(new BuilderTemplate());
/* 559:582 */     RegisterDungeonBuilder.addDungeonBuilder(new BuilderTemplateSurface());
/* 560:583 */     RegisterDungeonBuilder.addDungeonBuilder(new BuilderCastle());
/* 561:584 */     RegisterDungeonBuilder.addDungeonBuilder(new BuilderEmptyCave());
/* 562:585 */     RegisterDungeonBuilder.addDungeonBuilder(new BuilderNether());
/* 563:    */     
/* 564:    */ 
/* 565:    */ 
/* 566:    */ 
/* 567:    */ 
/* 568:    */ 
/* 569:    */ 
/* 570:    */ 
/* 571:    */ 
/* 572:    */ 
/* 573:    */ 
/* 574:    */ 
/* 575:598 */     File file = new File(BDHelper.getAppDir(), "Chocolate/DungeonConfig/");
/* 576:599 */     File[] files = file.listFiles();
/* 577:600 */     if (files != null)
/* 578:    */     {
/* 579:601 */       BDHelper.println("## Dungeon register ##");
/* 580:602 */       for (File current : files)
/* 581:    */       {
/* 582:604 */         DungeonBase dungeon = new DungeonBase();
/* 583:605 */         dungeon = dungeon.readData(current);
/* 584:606 */         if (dungeon != null)
/* 585:    */         {
/* 586:607 */           DungeonRegister.addDungeon(dungeon);
/* 587:608 */           BDHelper.println("Registered dungeon: " + current.getName());
/* 588:    */         }
/* 589:    */       }
/* 590:    */     }
/* 591:    */     else
/* 592:    */     {
/* 593:613 */       BDHelper.println("## Chocolate Quest Mod couldn't find dungeon files at \".minecraft/mods/Chocolate/DungeonConfig/\" ##");
/* 594:    */     }
/* 595:    */   }
/* 596:    */   
/* 597:    */   private void addMobToList(DungeonMonstersBase mob)
/* 598:    */   {
/* 599:616 */     RegisterDungeonMobs.addMob(mob);
/* 600:    */   }
/* 601:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.ChocolateQuest

 * JD-Core Version:    0.7.1

 */
