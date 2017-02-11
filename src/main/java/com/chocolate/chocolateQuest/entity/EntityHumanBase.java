/*    1:     */ package com.chocolate.chocolateQuest.entity;
/*    2:     */ 
/*    3:     */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*    4:     */ import com.chocolate.chocolateQuest.entity.ai.AIAnimalMountedByEntity;
/*    5:     */ import com.chocolate.chocolateQuest.entity.ai.AIControlledFollowOwner;
/*    6:     */ import com.chocolate.chocolateQuest.entity.ai.AIControlledFormation;
/*    7:     */ import com.chocolate.chocolateQuest.entity.ai.AIControlledPath;
/*    8:     */ import com.chocolate.chocolateQuest.entity.ai.AIControlledSit;
/*    9:     */ import com.chocolate.chocolateQuest.entity.ai.AIControlledWardPosition;
/*   10:     */ import com.chocolate.chocolateQuest.entity.ai.AIHumanAttackAggressive;
/*   11:     */ import com.chocolate.chocolateQuest.entity.ai.AIHumanAttackAggressiveBackstab;
/*   12:     */ import com.chocolate.chocolateQuest.entity.ai.AIHumanAttackDefensive;
/*   13:     */ import com.chocolate.chocolateQuest.entity.ai.AIHumanAttackEvasive;
/*   14:     */ import com.chocolate.chocolateQuest.entity.ai.AIHumanAttackHeal;
/*   15:     */ import com.chocolate.chocolateQuest.entity.ai.AIHumanFlee;
/*   16:     */ import com.chocolate.chocolateQuest.entity.ai.AIHumanGoToPoint;
/*   17:     */ import com.chocolate.chocolateQuest.entity.ai.AIHumanIdleSit;
/*   18:     */ import com.chocolate.chocolateQuest.entity.ai.AIHumanIdleTalkClosest;
/*   19:     */ import com.chocolate.chocolateQuest.entity.ai.AIHumanMount;
/*   20:     */ import com.chocolate.chocolateQuest.entity.ai.AIHumanPotion;
/*   21:     */ import com.chocolate.chocolateQuest.entity.ai.AITargetNearestHurtAlly;
/*   22:     */ import com.chocolate.chocolateQuest.entity.ai.AITargetOwner;
/*   23:     */ import com.chocolate.chocolateQuest.entity.ai.AITargetParty;
/*   24:     */ import com.chocolate.chocolateQuest.entity.ai.EntityParty;
/*   25:     */ import com.chocolate.chocolateQuest.entity.ai.EnumAiCombat;
/*   26:     */ import com.chocolate.chocolateQuest.entity.ai.EnumAiState;
/*   27:     */ import com.chocolate.chocolateQuest.entity.ai.HumanSelector;
/*   28:     */ import com.chocolate.chocolateQuest.entity.handHelper.HandEmpty;
/*   29:     */ import com.chocolate.chocolateQuest.entity.handHelper.HandHelper;
/*   30:     */ import com.chocolate.chocolateQuest.items.ItemArmorBase;
/*   31:     */ import com.chocolate.chocolateQuest.items.mobControl.ItemController;
/*   32:     */ import com.chocolate.chocolateQuest.items.swords.ItemBaseDagger;
/*   33:     */ import com.chocolate.chocolateQuest.items.swords.ItemBaseSpear;
/*   34:     */ import com.chocolate.chocolateQuest.magic.IElementWeak;
/*   35:     */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   36:     */ import com.chocolate.chocolateQuest.packets.PacketBase;
/*   37:     */ import com.chocolate.chocolateQuest.packets.PacketEntityAnimation;
/*   38:     */ import com.chocolate.chocolateQuest.packets.PacketSpawnParticlesAround;
/*   39:     */ import com.chocolate.chocolateQuest.packets.PacketUpdateHumanData;
/*   40:     */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   41:     */ import com.chocolate.chocolateQuest.utils.MobTeam;
/*   42:     */ import com.chocolate.chocolateQuest.utils.Vec4I;
/*   43:     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*   44:     */ import io.netty.buffer.ByteBuf;
/*   45:     */ import java.io.IOException;
/*   46:     */ import java.util.Random;
/*   47:     */ import net.minecraft.enchantment.Enchantment;
/*   48:     */ import net.minecraft.enchantment.EnchantmentHelper;
/*   49:     */ import net.minecraft.entity.DataWatcher;
/*   50:     */ import net.minecraft.entity.Entity;
/*   51:     */ import net.minecraft.entity.EntityCreature;
/*   52:     */ import net.minecraft.entity.EntityLiving;
/*   53:     */ import net.minecraft.entity.EntityLivingBase;
/*   54:     */ import net.minecraft.entity.EnumCreatureAttribute;
/*   55:     */ import net.minecraft.entity.IEntityOwnable;
/*   56:     */ import net.minecraft.entity.SharedMonsterAttributes;
/*   57:     */ import net.minecraft.entity.ai.EntityAIBase;
/*   58:     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*   59:     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*   60:     */ import net.minecraft.entity.ai.EntityAISwimming;
/*   61:     */ import net.minecraft.entity.ai.EntityAITasks;
/*   62:     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*   63:     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*   64:     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*   65:     */ import net.minecraft.entity.monster.EntityCreeper;
/*   66:     */ import net.minecraft.entity.monster.EntityGhast;
/*   67:     */ import net.minecraft.entity.passive.EntityAnimal;
/*   68:     */ import net.minecraft.entity.passive.EntityHorse;
/*   69:     */ import net.minecraft.entity.player.EntityPlayer;
/*   70:     */ import net.minecraft.init.Items;
/*   71:     */ import net.minecraft.item.Item;
/*   72:     */ import net.minecraft.item.ItemStack;
/*   73:     */ import net.minecraft.nbt.CompressedStreamTools;
/*   74:     */ import net.minecraft.nbt.NBTBase;
/*   75:     */ import net.minecraft.nbt.NBTSizeTracker;
/*   76:     */ import net.minecraft.nbt.NBTTagCompound;
/*   77:     */ import net.minecraft.nbt.NBTTagList;
/*   78:     */ import net.minecraft.nbt.NBTTagString;
/*   79:     */ import net.minecraft.scoreboard.Team;
/*   80:     */ import net.minecraft.util.DamageSource;
/*   81:     */ import net.minecraft.util.MathHelper;
/*   82:     */ import net.minecraft.util.RegistryNamespaced;
/*   83:     */ import net.minecraft.world.World;
/*   84:     */ import net.minecraft.world.WorldServer;
/*   85:     */ 
/*   86:     */ public class EntityHumanBase
/*   87:     */   extends EntityCreature
/*   88:     */   implements IEntityAdditionalSpawnData, IEntityOwnable, IElementWeak
/*   89:     */ {
/*   90:     */   public static final byte ANIM = 16;
/*   91:     */   public static final byte HUMAN = 0;
/*   92:     */   public static final byte ORC = 1;
/*   93:     */   public static final byte GOBLIN = 2;
/*   94:     */   public static final byte DWARF = 3;
/*   95:     */   public static final byte TRITON = 4;
/*   96:  89 */   public int maxStamina = 60;
/*   97:     */   private float randomHeightVariation;
/*   98:  92 */   public int leftHandSwing = 0;
/*   99:  93 */   public float moveForwardHuman = 0.0F;
/*  100:     */   protected int sprintTime;
/*  101:     */   protected int exhaustion;
/*  102:  96 */   public int potionCount = 1;
/*  103:     */   public ItemStack leftHandItem;
/*  104:  99 */   public float leftHandDropChances = 0.0F;
/*  105: 102 */   public short parryRate = 0;
/*  106: 103 */   public short blockRate = 0;
/*  107: 104 */   protected final float shiedBlockDefense = 0.8F;
/*  108:     */   public float accuracy;
/*  109: 107 */   public String entityTeam = "npc";
/*  110: 108 */   public int shieldID = 0;
/*  111:     */   protected String ownerName;
/*  112:     */   private EntityLivingBase owner;
/*  113:     */   public EntityParty party;
/*  114:     */   public int partyPositionAngle;
/*  115: 113 */   public int partyDistanceToLeader = 2;
/*  116: 114 */   public boolean partyPositionPersistance = false;
/*  117: 115 */   public boolean addedToParty = false;
/*  118:     */   protected EntityAIBase attackAI;
/*  119:     */   protected EntityAIBase controlledAI;
/*  120:     */   protected EntityAIBase supportAI;
/*  121:     */   protected EntityAIBase supportAITarget;
/*  122: 121 */   public int AIMode = EnumAiState.FORMATION.ordinal();
/*  123: 122 */   public int AICombatMode = EnumAiCombat.OFFENSIVE.ordinal();
/*  124:     */   public Vec4I currentPos;
/*  125:     */   public Vec4I standingPosition;
/*  126:     */   public Vec4I[] path;
/*  127:     */   public HandHelper leftHand;
/*  128:     */   public HandHelper rightHand;
/*  129: 129 */   private static final AttributeModifier slowDownModifier = new AttributeModifier("Human speed mod", -0.2D, 1).setSaved(false);
/*  130:     */   public EntityPlayer playerSpeakingTo;
/*  131: 132 */   public boolean updateOwner = false;
/*  132:     */   
/*  133:     */   public EntityHumanBase(World world)
/*  134:     */   {
/*  135: 135 */     super(world);
/*  136: 136 */     addAITasks();
/*  137: 137 */     this.stepHeight = 1.0F;
/*  138: 138 */     updateHands();
/*  139: 140 */     for (int i = 0; i < this.equipmentDropChances.length; i++) {
/*  140: 141 */       this.equipmentDropChances[i] = 0.0F;
/*  141:     */     }
/*  142:     */   }
/*  143:     */   
/*  144:     */   protected void applyEntityAttributes()
/*  145:     */   {
/*  146: 147 */     super.applyEntityAttributes();
/*  147: 148 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
/*  148:     */     
/*  149: 150 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
/*  150: 151 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
/*  151: 152 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
/*  152: 153 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
/*  153: 154 */     setSize(0.6F, 1.8F);
/*  154: 155 */     this.randomHeightVariation = (1.0F + (this.rand.nextFloat() - 0.6F) / 5.0F);
/*  155:     */   }
/*  156:     */   
/*  157:     */   protected boolean isAIEnabled()
/*  158:     */   {
/*  159: 161 */     return true;
/*  160:     */   }
/*  161:     */   
/*  162:     */   protected void addAITasks()
/*  163:     */   {
/*  164: 166 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  165: 167 */     this.tasks.addTask(1, new AIHumanPotion(this));
/*  166: 168 */     this.tasks.addTask(1, new AIHumanGoToPoint(this));
/*  167: 169 */     this.tasks.addTask(1, new AIHumanMount(this, 1.0F, false));
/*  168:     */     
/*  169: 171 */     setAIForCurrentMode();
/*  170: 172 */     this.tasks.addTask(7, new AIHumanIdleTalkClosest(this, EntityHumanBase.class, 8.0F));
/*  171: 173 */     this.tasks.addTask(8, new AIHumanIdleSit(this));
/*  172:     */     
/*  173: 175 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
/*  174: 176 */     this.targetTasks.addTask(2, new AITargetParty(this));
/*  175: 177 */     this.targetTasks.addTask(3, new AITargetOwner(this));
/*  176: 178 */     this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, new HumanSelector(this)));
/*  177: 179 */     this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityHumanBase.class, 0, true, false, new HumanSelector(this)));
/*  178:     */   }
/*  179:     */   
/*  180:     */   public void updateHands()
/*  181:     */   {
/*  182: 210 */     this.rightHand = HandHelper.getHandHelperForItem(this, getEquipmentInSlot(0));
/*  183: 211 */     this.leftHand = HandHelper.getHandHelperForItem(this, getLeftHandItem());
/*  184: 212 */     if ((this.rightHand instanceof HandEmpty)) {
/*  185: 213 */       this.rightHand = new HandHelper(this, null);
/*  186:     */     }
/*  187:     */   }
/*  188:     */   
/*  189:     */   public void setAIForCurrentMode()
/*  190:     */   {
/*  191: 216 */     updateHands();
/*  192: 217 */     if (this.supportAI != null)
/*  193:     */     {
/*  194: 218 */       this.tasks.removeTask(this.supportAI);
/*  195: 219 */       this.targetTasks.removeTask(this.supportAITarget);
/*  196:     */     }
/*  197: 221 */     if (isHealer())
/*  198:     */     {
/*  199: 222 */       this.supportAI = new AIHumanAttackHeal(this, 1.0F, false);
/*  200: 223 */       this.tasks.addTask(2, this.supportAI);
/*  201:     */       
/*  202: 225 */       this.supportAITarget = new AITargetNearestHurtAlly(this, EntityLivingBase.class);
/*  203: 226 */       this.targetTasks.addTask(0, this.supportAITarget);
/*  204:     */     }
/*  205: 229 */     if (this.controlledAI != null) {
/*  206: 230 */       this.tasks.removeTask(this.controlledAI);
/*  207:     */     }
/*  208: 232 */     int priority = 4;
/*  209: 233 */     if (this.AIMode == EnumAiState.FOLLOW.ordinal())
/*  210:     */     {
/*  211: 234 */       this.controlledAI = new AIControlledFollowOwner(this, 8.0F, 50.0F);
/*  212:     */     }
/*  213: 235 */     else if (this.AIMode == EnumAiState.FORMATION.ordinal())
/*  214:     */     {
/*  215: 236 */       this.controlledAI = new AIControlledFormation(this);
/*  216:     */     }
/*  217: 237 */     else if (this.AIMode == EnumAiState.PATH.ordinal())
/*  218:     */     {
/*  219: 238 */       this.controlledAI = new AIControlledPath(this);
/*  220:     */     }
/*  221: 239 */     else if (this.AIMode == EnumAiState.WARD.ordinal())
/*  222:     */     {
/*  223: 240 */       this.controlledAI = new AIControlledWardPosition(this);
/*  224:     */     }
/*  225: 241 */     else if (this.AIMode == EnumAiState.SIT.ordinal())
/*  226:     */     {
/*  227: 242 */       this.controlledAI = new AIControlledSit(this);
/*  228: 243 */       priority = 2;
/*  229:     */     }
/*  230: 245 */     if (this.controlledAI != null) {
/*  231: 246 */       this.tasks.addTask(priority, this.controlledAI);
/*  232:     */     }
/*  233: 250 */     if (this.attackAI != null) {
/*  234: 251 */       this.tasks.removeTask(this.attackAI);
/*  235:     */     }
/*  236: 253 */     if (this.AICombatMode == EnumAiCombat.OFFENSIVE.ordinal()) {
/*  237: 254 */       this.attackAI = new AIHumanAttackAggressive(this, EntityLivingBase.class, 1.0F, false);
/*  238: 255 */     } else if (this.AICombatMode == EnumAiCombat.DEFENSIVE.ordinal()) {
/*  239: 256 */       this.attackAI = new AIHumanAttackDefensive(this, 1.0F);
/*  240: 257 */     } else if (this.AICombatMode == EnumAiCombat.EVASIVE.ordinal()) {
/*  241: 258 */       this.attackAI = new AIHumanAttackEvasive(this, 1.0F);
/*  242: 259 */     } else if (this.AICombatMode == EnumAiCombat.FLEE.ordinal()) {
/*  243: 260 */       this.attackAI = new AIHumanFlee(this, 1.0F);
/*  244: 261 */     } else if (this.AICombatMode == EnumAiCombat.BACKSTAB.ordinal()) {
/*  245: 262 */       this.attackAI = new AIHumanAttackAggressiveBackstab(this, 1.0F, false);
/*  246:     */     }
/*  247: 264 */     if (this.attackAI != null) {
/*  248: 265 */       this.tasks.addTask(3, this.attackAI);
/*  249:     */     }
/*  250:     */   }
/*  251:     */   
/*  252:     */   public void onLivingUpdate()
/*  253:     */   {
/*  254: 271 */     if ((this.party != null) && 
/*  255: 272 */       (this.party.getLeader() == this)) {
/*  256: 273 */       this.party.update();
/*  257:     */     }
/*  258: 276 */     if (haveShied())
/*  259:     */     {
/*  260: 278 */       IAttributeInstance attributeinstance = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/*  261: 279 */       attributeinstance.removeModifier(slowDownModifier);
/*  262: 280 */       if (isDefending()) {
/*  263: 282 */         attributeinstance.applyModifier(slowDownModifier);
/*  264:     */       }
/*  265:     */     }
/*  266: 285 */     if (!this.worldObj.isRemote) {
/*  267: 286 */       if (isSprinting())
/*  268:     */       {
/*  269: 287 */         this.sprintTime -= 1;
/*  270: 288 */         if (this.sprintTime <= 0) {
/*  271: 289 */           setSprinting(false);
/*  272:     */         }
/*  273: 290 */         this.exhaustion = 20;
/*  274:     */       }
/*  275: 291 */       else if (this.exhaustion > 0)
/*  276:     */       {
/*  277: 292 */         this.exhaustion -= 1;
/*  278:     */       }
/*  279:     */     }
/*  280: 294 */     for (int i = 0; i < 4; i++)
/*  281:     */     {
/*  282: 296 */       ItemStack is = getEquipmentInSlot(i + 1);
/*  283: 297 */       if (is != null) {
/*  284: 299 */         if ((is.getItem() instanceof ItemArmorBase)) {
/*  285: 300 */           ((ItemArmorBase)is.getItem()).onUpdateEquiped(is, this.worldObj, this);
/*  286:     */         }
/*  287:     */       }
/*  288:     */     }
/*  289: 304 */     this.rightHand.onUpdate();
/*  290: 305 */     this.leftHand.onUpdate();
/*  291: 306 */     updateArmSwingProgress();
/*  292: 307 */     super.onLivingUpdate();
/*  293:     */   }
/*  294:     */   
/*  295:     */   protected void updateArmSwingProgress()
/*  296:     */   {
/*  297: 313 */     super.updateArmSwingProgress();
/*  298: 314 */     if (this.leftHandSwing > 0) {
/*  299: 316 */       this.leftHandSwing -= 1;
/*  300:     */     }
/*  301:     */   }
/*  302:     */   
/*  303:     */   public void swingLeftHand()
/*  304:     */   {
/*  305: 321 */     if (this.leftHandSwing <= 0)
/*  306:     */     {
/*  307: 323 */       this.leftHandSwing = 10;
/*  308: 324 */       if ((this.worldObj instanceof WorldServer))
/*  309:     */       {
/*  310: 326 */         PacketEntityAnimation packet = new PacketEntityAnimation(getEntityId(), (byte)1);
/*  311: 327 */         ChocolateQuest.channel.sendToAllAround(this, packet);
/*  312:     */       }
/*  313:     */     }
/*  314:     */   }
/*  315:     */   
/*  316:     */   public void swingHand(HandHelper hand)
/*  317:     */   {
/*  318: 333 */     if (hand == this.rightHand) {
/*  319: 334 */       swingItem();
/*  320:     */     } else {
/*  321: 336 */       swingLeftHand();
/*  322:     */     }
/*  323:     */   }
/*  324:     */   
/*  325:     */   public boolean isSwingInProgress(HandHelper hand)
/*  326:     */   {
/*  327: 339 */     if (hand == this.rightHand) {
/*  328: 340 */       return this.isSwingInProgress;
/*  329:     */     }
/*  330: 343 */     return this.leftHandSwing > 0;
/*  331:     */   }
/*  332:     */   
/*  333:     */   public void setAiming(HandHelper hand, boolean aiming)
/*  334:     */   {
/*  335: 347 */     if (hand.isAiming() != aiming) {
/*  336: 348 */       if (hand == this.rightHand) {
/*  337: 349 */         toogleAimRight();
/*  338:     */       } else {
/*  339: 351 */         toogleAimLeft();
/*  340:     */       }
/*  341:     */     }
/*  342:     */   }
/*  343:     */   
/*  344:     */   public boolean isAiming()
/*  345:     */   {
/*  346: 355 */     return (this.rightHand.isAiming()) || (this.leftHand.isAiming());
/*  347:     */   }
/*  348:     */   
/*  349:     */   public void stopAiming()
/*  350:     */   {
/*  351: 358 */     if (this.rightHand.isAiming()) {
/*  352: 359 */       toogleAimRight();
/*  353:     */     }
/*  354: 360 */     if (this.leftHand.isAiming()) {
/*  355: 361 */       toogleAimLeft();
/*  356:     */     }
/*  357:     */   }
/*  358:     */   
/*  359:     */   public void toogleAimRight()
/*  360:     */   {
/*  361: 364 */     this.rightHand.setAiming(!this.rightHand.isAiming());
/*  362: 365 */     if ((this.worldObj instanceof WorldServer))
/*  363:     */     {
/*  364: 366 */       PacketEntityAnimation packet = new PacketEntityAnimation(getEntityId(), (byte)2);
/*  365: 367 */       ChocolateQuest.channel.sendToAllAround(this, packet);
/*  366:     */     }
/*  367:     */   }
/*  368:     */   
/*  369:     */   public void toogleAimLeft()
/*  370:     */   {
/*  371: 371 */     this.leftHand.setAiming(!this.leftHand.isAiming());
/*  372: 372 */     if ((this.worldObj instanceof WorldServer))
/*  373:     */     {
/*  374: 373 */       PacketEntityAnimation packet = new PacketEntityAnimation(getEntityId(), (byte)3);
/*  375: 374 */       ChocolateQuest.channel.sendToAllAround(this, packet);
/*  376:     */     }
/*  377:     */   }
/*  378:     */   
/*  379:     */   public boolean writeToNBTOptional(NBTTagCompound nbttagcompound)
/*  380:     */   {
/*  381: 385 */     if ((this.party != null) && 
/*  382: 386 */       (!writePartyToNBT(nbttagcompound))) {
/*  383: 387 */       return false;
/*  384:     */     }
/*  385: 389 */     return super.writeToNBTOptional(nbttagcompound);
/*  386:     */   }
/*  387:     */   
/*  388:     */   public boolean writePartyToNBT(NBTTagCompound nbttagcompound)
/*  389:     */   {
/*  390: 402 */     if ((this.party != null) && 
/*  391: 403 */       (this.party.getLeader().isEntityEqual(this)))
/*  392:     */     {
/*  393: 404 */       this.party.saveToNBT(nbttagcompound);
/*  394: 405 */       return true;
/*  395:     */     }
/*  396: 408 */     return false;
/*  397:     */   }
/*  398:     */   
/*  399:     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound)
/*  400:     */   {
/*  401: 414 */     super.writeEntityToNBT(nbttagcompound);
/*  402: 415 */     nbttagcompound.setInteger("PotionCount", this.potionCount);
/*  403: 416 */     if (getLeftHandItem() != null)
/*  404:     */     {
/*  405: 418 */       NBTTagCompound itemNBT = new NBTTagCompound();
/*  406: 419 */       nbttagcompound.setTag("leftHand", getLeftHandItem().writeToNBT(itemNBT));
/*  407:     */     }
/*  408: 421 */     nbttagcompound.setFloat("dropLeft", this.leftHandDropChances);
/*  409: 423 */     if ((this.owner instanceof EntityPlayer)) {
/*  410: 425 */       nbttagcompound.setString("ownerName", this.ownerName);
/*  411: 426 */     } else if (this.ownerName != null) {
/*  412: 427 */       nbttagcompound.setString("ownerName", this.ownerName);
/*  413:     */     }
/*  414: 428 */     nbttagcompound.setString("team", this.entityTeam);
/*  415: 429 */     if (this.partyPositionPersistance)
/*  416:     */     {
/*  417: 431 */       nbttagcompound.setInteger("leaderDist", this.partyDistanceToLeader);
/*  418: 432 */       nbttagcompound.setInteger("partyPos", this.partyPositionAngle);
/*  419:     */     }
/*  420: 435 */     nbttagcompound.setInteger("AIMode", this.AIMode);
/*  421: 436 */     if (this.standingPosition != null)
/*  422:     */     {
/*  423: 438 */       nbttagcompound.setBoolean("standing", true);
/*  424: 439 */       nbttagcompound.setInteger("standX", this.standingPosition.xCoord);
/*  425: 440 */       nbttagcompound.setInteger("standY", this.standingPosition.yCoord);
/*  426: 441 */       nbttagcompound.setInteger("standZ", this.standingPosition.zCoord);
/*  427: 442 */       nbttagcompound.setInteger("standRotation", this.standingPosition.rot);
/*  428:     */     }
/*  429: 444 */     if (this.path != null)
/*  430:     */     {
/*  431: 446 */       nbttagcompound.setInteger("pathPoints", this.path.length);
/*  432: 447 */       for (int p = 0; p < this.path.length; p++)
/*  433:     */       {
/*  434: 449 */         nbttagcompound.setInteger("pathX" + p, this.path[p].xCoord);
/*  435: 450 */         nbttagcompound.setInteger("pathY" + p, this.path[p].yCoord);
/*  436: 451 */         nbttagcompound.setInteger("pathZ" + p, this.path[p].zCoord);
/*  437:     */       }
/*  438:     */     }
/*  439: 454 */     nbttagcompound.setInteger("AIMode", this.AIMode);
/*  440: 455 */     nbttagcompound.setInteger("AICombat", this.AICombatMode);
/*  441: 456 */     nbttagcompound.setBoolean("addedToParty", this.addedToParty);
/*  442:     */   }
/*  443:     */   
/*  444:     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound)
/*  445:     */   {
/*  446: 461 */     super.readEntityFromNBT(nbttagcompound);
/*  447: 462 */     this.potionCount = nbttagcompound.getInteger("PotionCount");
/*  448: 463 */     this.leftHandDropChances = nbttagcompound.getFloat("dropLeft");
/*  449: 464 */     NBTTagCompound item = (NBTTagCompound)nbttagcompound.getTag("leftHand");
/*  450: 465 */     if (item != null) {
/*  451: 467 */       this.leftHandItem = ItemStack.loadItemStackFromNBT(item);
/*  452:     */     } else {
/*  453: 469 */       this.leftHandItem = null;
/*  454:     */     }
/*  455: 471 */     this.ownerName = nbttagcompound.getString("ownerName");
/*  456: 472 */     this.entityTeam = nbttagcompound.getString("team");
/*  457: 473 */     if (this.ownerName == "") {
/*  458: 474 */       this.ownerName = null;
/*  459:     */     }
/*  460: 475 */     if (this.ownerName != null) {
/*  461: 477 */       setOwner(this.worldObj.getPlayerEntityByName(this.ownerName));
/*  462:     */     }
/*  463: 479 */     if (nbttagcompound.hasKey("leaderDist"))
/*  464:     */     {
/*  465: 481 */       this.partyPositionPersistance = true;
/*  466: 482 */       this.partyDistanceToLeader = nbttagcompound.getInteger("leaderDist");
/*  467: 483 */       this.partyPositionAngle = nbttagcompound.getInteger("partyPos");
/*  468:     */     }
/*  469: 487 */     this.AIMode = nbttagcompound.getInteger("AIMode");
/*  470: 488 */     if (nbttagcompound.getBoolean("standing"))
/*  471:     */     {
/*  472: 490 */       int x = nbttagcompound.getInteger("standX");
/*  473: 491 */       int y = nbttagcompound.getInteger("standY");
/*  474: 492 */       int z = nbttagcompound.getInteger("standZ");
/*  475: 493 */       int rotY = nbttagcompound.getInteger("standRotation");
/*  476: 494 */       this.standingPosition = new Vec4I(x, y, z, rotY);
/*  477:     */     }
/*  478: 496 */     int points = nbttagcompound.getInteger("pathPoints");
/*  479: 497 */     if (points > 0)
/*  480:     */     {
/*  481: 499 */       this.path = new Vec4I[points];
/*  482: 500 */       for (int p = 0; p < points; p++) {
/*  483: 502 */         this.path[p] = new Vec4I(nbttagcompound.getInteger("pathX" + p), nbttagcompound.getInteger("pathY" + p), nbttagcompound.getInteger("pathZ" + p), 0);
/*  484:     */       }
/*  485:     */     }
/*  486: 508 */     if (nbttagcompound.hasKey("AIMode")) {
/*  487: 509 */       this.AIMode = nbttagcompound.getInteger("AIMode");
/*  488:     */     }
/*  489: 510 */     if (nbttagcompound.hasKey("AICombat")) {
/*  490: 511 */       this.AICombatMode = nbttagcompound.getInteger("AICombat");
/*  491:     */     }
/*  492: 512 */     setAIForCurrentMode();
/*  493: 514 */     if ((nbttagcompound.getTag("Party") != null) && (this.party == null))
/*  494:     */     {
/*  495: 515 */       this.party = new EntityParty();
/*  496: 516 */       this.party.tryToAddNewMember(this);
/*  497: 517 */       this.party.readFromNBT(nbttagcompound);
/*  498:     */     }
/*  499: 519 */     this.addedToParty = nbttagcompound.getBoolean("addedToParty");
/*  500:     */   }
/*  501:     */   
/*  502:     */   public void writeEntityToSpawnerNBT(NBTTagCompound nbttagcompound, int spawnerX, int spawnerY, int spawnerZ)
/*  503:     */   {
/*  504: 524 */     writePartyToNBT(nbttagcompound);
/*  505: 525 */     Vec4I currentStand = this.standingPosition;
/*  506: 526 */     Vec4I[] currentPath = this.path;
/*  507: 527 */     double tempPosX = this.posX;double tempPosY = this.posY;double tempPosZ = this.posZ;
/*  508: 528 */     this.posX -= spawnerX;
/*  509: 529 */     this.posY -= spawnerY;
/*  510: 530 */     this.posZ -= spawnerZ;
/*  511: 531 */     if (this.standingPosition != null) {
/*  512: 532 */       this.standingPosition = new Vec4I(currentStand.xCoord - spawnerX, currentStand.yCoord - spawnerY, currentStand.zCoord - spawnerZ, currentStand.rot);
/*  513:     */     }
/*  514: 534 */     if (this.path != null)
/*  515:     */     {
/*  516: 535 */       this.path = new Vec4I[this.path.length];
/*  517: 536 */       for (int p = 0; p < this.path.length; p++) {
/*  518: 537 */         this.path[p] = new Vec4I(currentPath[p].xCoord - spawnerX, currentPath[p].yCoord - spawnerY, currentPath[p].zCoord - spawnerZ, currentPath[p].rot);
/*  519:     */       }
/*  520:     */     }
/*  521: 540 */     writeEntityToNBT(nbttagcompound);
/*  522: 541 */     if (this.ridingEntity != null) {
/*  523: 542 */       writeMountToNBT(nbttagcompound);
/*  524:     */     }
/*  525: 543 */     this.posX += spawnerX;
/*  526: 544 */     this.posY += spawnerY;
/*  527: 545 */     this.posZ += spawnerZ;
/*  528: 546 */     this.standingPosition = currentStand;
/*  529: 547 */     this.path = currentPath;
/*  530:     */     
/*  531: 549 */     saveMappingsToNBT(nbttagcompound);
/*  532:     */   }
/*  533:     */   
/*  534:     */   public void saveMappingsToNBT(NBTTagCompound nbttagcompound)
/*  535:     */   {
/*  536: 553 */     NBTTagList list = new NBTTagList();
/*  537: 554 */     for (int i = 0; i < 5; i++)
/*  538:     */     {
/*  539: 555 */       String s = "";
/*  540: 556 */       if (getEquipmentInSlot(i) != null) {
/*  541: 557 */         s = Item.itemRegistry.getNameForObject(getEquipmentInSlot(i).getItem());
/*  542:     */       }
/*  543: 559 */       list.appendTag(new NBTTagString(s));
/*  544:     */     }
/*  545: 562 */     String s = "";
/*  546: 563 */     if (getLeftHandItem() != null) {
/*  547: 564 */       s = Item.itemRegistry.getNameForObject(getLeftHandItem().getItem());
/*  548:     */     }
/*  549: 566 */     list.appendTag(new NBTTagString(s));
/*  550: 567 */     nbttagcompound.setTag("EquipementMap", list);
/*  551:     */   }
/*  552:     */   
/*  553:     */   public void readEntityFromSpawnerNBT(NBTTagCompound nbttagcompound, int spawnerX, int spawnerY, int spawnerZ)
/*  554:     */   {
/*  555: 572 */     NBTBase equipementMap = nbttagcompound.getTag("EquipementMap");
/*  556: 573 */     if (equipementMap != null)
/*  557:     */     {
/*  558: 574 */       NBTBase equipementTag = nbttagcompound.getTag("Equipment");
/*  559: 575 */       if (equipementTag != null)
/*  560:     */       {
/*  561: 576 */         NBTTagList list = (NBTTagList)equipementTag;
/*  562: 577 */         NBTTagList listMap = (NBTTagList)equipementMap;
/*  563: 578 */         for (int i = 0; i < list.tagCount(); i++)
/*  564:     */         {
/*  565: 580 */           int id = list.getCompoundTagAt(i).getShort("id");
/*  566: 581 */           if (id != 0)
/*  567:     */           {
/*  568: 582 */             Item item = (Item)Item.itemRegistry.getObject(listMap.getStringTagAt(i));
/*  569: 583 */             if (item != null)
/*  570:     */             {
/*  571: 584 */               short newID = (short)Item.getIdFromItem(item);
/*  572: 585 */               list.getCompoundTagAt(i).setShort("id", newID);
/*  573:     */             }
/*  574:     */           }
/*  575:     */         }
/*  576: 589 */         int LEFT_HAND = 5;
/*  577: 590 */         NBTTagCompound itemNBT = (NBTTagCompound)nbttagcompound.getTag("leftHand");
/*  578: 591 */         if (itemNBT != null)
/*  579:     */         {
/*  580: 592 */           int id = itemNBT.getShort("id");
/*  581: 593 */           if (id != 0)
/*  582:     */           {
/*  583: 594 */             Item item = (Item)Item.itemRegistry.getObject(listMap.getStringTagAt(5));
/*  584: 595 */             if (item != null)
/*  585:     */             {
/*  586: 596 */               short newID = (short)Item.getIdFromItem(item);
/*  587: 597 */               itemNBT.setShort("id", newID);
/*  588:     */             }
/*  589:     */           }
/*  590:     */         }
/*  591:     */       }
/*  592:     */     }
/*  593: 603 */     readFromNBT(nbttagcompound);
/*  594: 604 */     if (this.standingPosition != null)
/*  595:     */     {
/*  596: 605 */       Vec4I current = this.standingPosition;
/*  597: 606 */       this.standingPosition = new Vec4I(current.xCoord + spawnerX, current.yCoord + spawnerY, current.zCoord + spawnerZ, current.rot);
/*  598:     */     }
/*  599: 608 */     if (this.path != null) {
/*  600: 609 */       for (int p = 0; p < this.path.length; p++)
/*  601:     */       {
/*  602: 610 */         Vec4I current = this.path[p];
/*  603: 611 */         this.path[p] = new Vec4I(current.xCoord + spawnerX, current.yCoord + spawnerY, current.zCoord + spawnerZ, current.rot);
/*  604:     */       }
/*  605:     */     }
/*  606: 614 */     this.posX += spawnerX;
/*  607: 615 */     this.posY += spawnerY;
/*  608: 616 */     this.posZ += spawnerZ;
/*  609:     */   }
/*  610:     */   
/*  611:     */   public void setAttackTarget(EntityLivingBase par1EntityLivingBase)
/*  612:     */   {
/*  613: 621 */     if (par1EntityLivingBase != null) {
/*  614: 623 */       if (!isOnSameTeam(par1EntityLivingBase))
/*  615:     */       {
/*  616: 624 */         if (this.party != null) {
/*  617: 625 */           this.party.addAggroToTarget(par1EntityLivingBase, 1);
/*  618:     */         }
/*  619:     */       }
/*  620: 626 */       else if ((!isHealer()) && 
/*  621: 627 */         (!isSuitableMount(par1EntityLivingBase))) {
/*  622: 628 */         return;
/*  623:     */       }
/*  624:     */     }
/*  625: 631 */     super.setAttackTarget(par1EntityLivingBase);
/*  626:     */   }
/*  627:     */   
/*  628:     */   protected boolean canDespawn()
/*  629:     */   {
/*  630: 637 */     return false;
/*  631:     */   }
/*  632:     */   
/*  633:     */   protected void despawnEntity()
/*  634:     */   {
/*  635: 641 */     super.despawnEntity();
/*  636:     */   }
/*  637:     */   
/*  638:     */   public EnumCreatureAttribute getCreatureAttribute()
/*  639:     */   {
/*  640: 647 */     return EnumCreatureAttribute.UNDEFINED;
/*  641:     */   }
/*  642:     */   
/*  643:     */   public double getMountedYOffset()
/*  644:     */   {
/*  645: 652 */     if ((this.ridingEntity instanceof EntityHorse)) {
/*  646: 653 */       return -0.4D;
/*  647:     */     }
/*  648: 654 */     return super.getMountedYOffset();
/*  649:     */   }
/*  650:     */   
/*  651:     */   public ItemStack getLeftHandItem()
/*  652:     */   {
/*  653: 658 */     return this.leftHandItem;
/*  654:     */   }
/*  655:     */   
/*  656:     */   public void setLeftHandItem(ItemStack is)
/*  657:     */   {
/*  658: 661 */     this.leftHandItem = is;
/*  659:     */   }
/*  660:     */   
/*  661:     */   public void moveEntityWithHeading(float par1, float par2)
/*  662:     */   {
/*  663: 667 */     if (this.moveForwardHuman != 0.0F) {
/*  664: 669 */       par2 = this.moveForwardHuman;
/*  665:     */     }
/*  666: 671 */     super.moveEntityWithHeading(par1, par2);
/*  667:     */   }
/*  668:     */   
/*  669:     */   public float getSizeModifier()
/*  670:     */   {
/*  671: 676 */     return this.randomHeightVariation;
/*  672:     */   }
/*  673:     */   
/*  674:     */   public boolean attackEntityFrom(DamageSource damagesource, float damage)
/*  675:     */   {
/*  676: 682 */     return super.attackEntityFrom(damagesource, damage);
/*  677:     */   }
/*  678:     */   
/*  679:     */   public void applyEntityCollision(Entity par1Entity)
/*  680:     */   {
/*  681: 687 */     if ((!this.worldObj.isRemote) && (getLeader() != null) && 
/*  682: 688 */       ((par1Entity instanceof EntityHumanBase)) && 
/*  683: 689 */       (!((EntityHumanBase)par1Entity).isOnSameTeam(this))) {
/*  684: 690 */       setAttackTarget((EntityLivingBase)par1Entity);
/*  685:     */     }
/*  686: 694 */     super.applyEntityCollision(par1Entity);
/*  687:     */   }
/*  688:     */   
/*  689:     */   public boolean attackEntityWithRangedAttack(Entity entity, float f)
/*  690:     */   {
/*  691: 699 */     boolean flagRight = false;boolean flagLeft = false;
/*  692: 700 */     if (this.leftHand.isRanged()) {
/*  693: 701 */       flagLeft = this.leftHand.attackWithRange(entity, f);
/*  694:     */     }
/*  695: 702 */     if (this.rightHand.isRanged()) {
/*  696: 703 */       flagRight = this.rightHand.attackWithRange(entity, f);
/*  697:     */     }
/*  698: 704 */     return (flagLeft) || (flagRight);
/*  699:     */   }
/*  700:     */   
/*  701:     */   public void attackEntity(EntityLivingBase entity)
/*  702:     */   {
/*  703: 707 */     if (entity.hurtTime <= 0)
/*  704:     */     {
/*  705: 708 */       if (this.rightHand.attackTime <= 0) {
/*  706: 709 */         this.rightHand.attackEntity(entity);
/*  707: 711 */       } else if (this.leftHand.attackTime <= 0) {
/*  708: 712 */         this.leftHand.attackEntity(entity);
/*  709:     */       }
/*  710: 714 */       if ((haveShied()) && 
/*  711: 715 */         (isDefending())) {
/*  712: 717 */         setDefending(false);
/*  713:     */       }
/*  714:     */     }
/*  715:     */   }
/*  716:     */   
/*  717:     */   public boolean attackEntityAsMob(Entity entity)
/*  718:     */   {
/*  719: 725 */     return attackEntityAsMob(entity, getEquipmentInSlot(0));
/*  720:     */   }
/*  721:     */   
/*  722:     */   public boolean attackEntityAsMob(Entity entity, ItemStack weapon)
/*  723:     */   {
/*  724: 729 */     float damage = (float)getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
/*  725: 730 */     if (weapon != getEquipmentInSlot(0))
/*  726:     */     {
/*  727: 731 */       damage = (float)getEntityAttribute(SharedMonsterAttributes.attackDamage).getBaseValue();
/*  728: 732 */       damage = (float)(damage + BDHelper.getWeaponDamage(weapon));
/*  729:     */     }
/*  730: 734 */     if ((entity instanceof EntityCreeper)) {
/*  731: 735 */       damage += 1000.0F;
/*  732:     */     }
/*  733: 737 */     float knockBackAmount = 0.0F;
/*  734: 739 */     if (weapon != null)
/*  735:     */     {
/*  736: 741 */       if ((weapon.getItem() instanceof ItemBaseDagger))
/*  737:     */       {
/*  738: 743 */         double d = this.posX - entity.posX;
/*  739: 744 */         double d1 = this.posY - entity.posY;
/*  740: 745 */         double d2 = this.posZ - entity.posZ;
/*  741: 746 */         float angle = (float)Math.atan2(d, d2);
/*  742:     */         
/*  743: 748 */         angle = this.rotationYaw - entity.rotationYaw;
/*  744: 749 */         while (angle > 360.0F) {
/*  745: 749 */           angle -= 360.0F;
/*  746:     */         }
/*  747: 750 */         while (angle < 0.0F) {
/*  748: 750 */           angle += 360.0F;
/*  749:     */         }
/*  750: 751 */         angle = Math.abs(angle - 180.0F);
/*  751: 752 */         if (Math.abs(angle) > 130.0F)
/*  752:     */         {
/*  753: 753 */           damage *= 2.5F;
/*  754: 755 */           if (!this.worldObj.isRemote)
/*  755:     */           {
/*  756: 757 */             PacketSpawnParticlesAround packet = new PacketSpawnParticlesAround((byte)1, entity.posX, entity.posY + 1.0D, entity.posZ);
/*  757: 758 */             ChocolateQuest.channel.sendToAllAround(entity, packet, 64);
/*  758:     */           }
/*  759:     */         }
/*  760:     */       }
/*  761: 762 */       if ((weapon.getItem() instanceof ItemBaseSpear))
/*  762:     */       {
/*  763: 764 */         knockBackAmount = 1.3F;
/*  764: 765 */         if ((entity.ridingEntity != null) && (this.rand.nextInt(10) == 0)) {
/*  765: 766 */           entity.mountEntity(null);
/*  766:     */         }
/*  767:     */       }
/*  768:     */     }
/*  769: 770 */     boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
/*  770: 772 */     if (flag) {
/*  771: 774 */       if (weapon != null)
/*  772:     */       {
/*  773: 775 */         int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, weapon);
/*  774: 776 */         if (j > 0)
/*  775:     */         {
/*  776: 778 */           entity.addVelocity(-MathHelper.sin(this.rotationYaw * 3.141593F / 180.0F) * j * 0.5F, 0.1D, MathHelper.cos(this.rotationYaw * 3.141593F / 180.0F) * j * 0.5F);
/*  777: 779 */           this.motionX *= 0.6D;
/*  778: 780 */           this.motionZ *= 0.6D;
/*  779:     */         }
/*  780: 783 */         int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, weapon);
/*  781: 784 */         if (k > 0) {
/*  782: 786 */           entity.setFire(k * 4);
/*  783:     */         }
/*  784:     */       }
/*  785:     */     }
/*  786: 794 */     if (knockBackAmount > 0.0F) {
/*  787: 795 */       entity.addVelocity(-MathHelper.sin(this.rotationYaw * 3.141593F / 180.0F) * knockBackAmount * 0.5F, 0.1D, MathHelper.cos(this.rotationYaw * 3.141593F / 180.0F) * knockBackAmount * 0.5F);
/*  788:     */     }
/*  789: 797 */     return flag;
/*  790:     */   }
/*  791:     */   
/*  792:     */   public boolean canAttackClass(Class par1Class)
/*  793:     */   {
/*  794: 802 */     return (EntityGhast.class != par1Class) && (par1Class != EntityReferee.class);
/*  795:     */   }
/*  796:     */   
/*  797:     */   public float getEyeHeight()
/*  798:     */   {
/*  799: 807 */     if (isSitting()) {
/*  800: 808 */       return super.getEyeHeight() - 0.5F;
/*  801:     */     }
/*  802: 809 */     return super.getEyeHeight();
/*  803:     */   }
/*  804:     */   
/*  805:     */   public double getDistanceToAttack()
/*  806:     */   {
/*  807: 813 */     return Math.max(this.rightHand.getDistanceToStopAdvancing(), this.leftHand.getDistanceToStopAdvancing());
/*  808:     */   }
/*  809:     */   
/*  810:     */   protected boolean interact(EntityPlayer player)
/*  811:     */   {
/*  812: 818 */     if (player.getCurrentEquippedItem() != null)
/*  813:     */     {
/*  814: 819 */       ItemStack is = player.getCurrentEquippedItem();
/*  815: 820 */       if ((is.getItem() == Items.name_tag) || ((is.getItem() instanceof ItemController))) {
/*  816: 821 */         return super.interact(player);
/*  817:     */       }
/*  818:     */     }
/*  819: 825 */     if (this.playerSpeakingTo == null)
/*  820:     */     {
/*  821: 826 */       this.playerSpeakingTo = player;
/*  822: 827 */       if ((this.worldObj instanceof WorldServer))
/*  823:     */       {
/*  824: 829 */         PacketBase packet = getEntityGUIUpdatePacket();
/*  825: 830 */         ChocolateQuest.channel.sendToAllAround(this, packet);
/*  826:     */       }
/*  827: 832 */       if (!this.worldObj.isRemote) {
/*  828: 833 */         player.openGui(ChocolateQuest.instance, 0, this.worldObj, getEntityId(), 0, 0);
/*  829:     */       }
/*  830: 834 */       return true;
/*  831:     */     }
/*  832: 836 */     return false;
/*  833:     */   }
/*  834:     */   
/*  835:     */   public PacketBase getEntityGUIUpdatePacket()
/*  836:     */   {
/*  837: 839 */     return new PacketUpdateHumanData(this);
/*  838:     */   }
/*  839:     */   
/*  840:     */   public boolean isWithinHomeDistance(int par1, int par2, int par3)
/*  841:     */   {
/*  842: 845 */     if (this.ownerName != null) {
/*  843: 846 */       return true;
/*  844:     */     }
/*  845: 847 */     return super.isWithinHomeDistance(par1, par2, par3);
/*  846:     */   }
/*  847:     */   
/*  848:     */   public void onDeath(DamageSource damageSource)
/*  849:     */   {
/*  850: 853 */     super.onDeath(damageSource);
/*  851: 854 */     if (this.party != null) {
/*  852: 855 */       this.party.removeMember(this);
/*  853:     */     }
/*  854:     */   }
/*  855:     */   
/*  856:     */   public void onSpawn()
/*  857:     */   {
/*  858: 860 */     updateHands();
/*  859:     */   }
/*  860:     */   
/*  861:     */   public int getHandAngle(HandHelper hand)
/*  862:     */   {
/*  863: 864 */     if (hand == this.leftHand) {
/*  864: 865 */       return -30;
/*  865:     */     }
/*  866: 866 */     return 30;
/*  867:     */   }
/*  868:     */   
/*  869:     */   public boolean canSee(EntityLivingBase entity)
/*  870:     */   {
/*  871: 871 */     boolean flag = true;
/*  872: 872 */     if (flag) {
/*  873: 875 */       if ((entity.ridingEntity != null) || (entity.isSprinting()))
/*  874:     */       {
/*  875: 877 */         flag = true;
/*  876:     */       }
/*  877:     */       else
/*  878:     */       {
/*  879: 880 */         double rotDiff = Math.abs(BDHelper.getAngleBetweenEntities(this, entity));
/*  880: 881 */         double rot = rotDiff - Math.abs(MathHelper.wrapAngleTo180_double(this.rotationYawHead));
/*  881: 882 */         rot = Math.abs(rot);
/*  882:     */         
/*  883: 884 */         float lightLevel = entity.worldObj.getLightBrightness(MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY), MathHelper.floor_double(entity.posZ));
/*  884:     */         
/*  885: 886 */         double dist = entity.getDistanceSqToEntity(this) / 16.0D;
/*  886: 887 */         double value = (rot + dist + (entity.isSneaking() ? 30 : 0)) * (2.2D - lightLevel);
/*  887: 888 */         flag = value < 100.0D;
/*  888:     */       }
/*  889:     */     }
/*  890: 892 */     return flag;
/*  891:     */   }
/*  892:     */   
/*  893:     */   public boolean isSuitableTargetAlly(EntityLivingBase entity)
/*  894:     */   {
/*  895: 897 */     if (entity == null) {
/*  896: 898 */       return false;
/*  897:     */     }
/*  898: 899 */     if (isOnSameTeam(entity)) {
/*  899: 900 */       return true;
/*  900:     */     }
/*  901: 901 */     if ((entity.riddenByEntity == null) && (isSuitableMount(entity))) {
/*  902: 902 */       return true;
/*  903:     */     }
/*  904: 903 */     if ((entity.riddenByEntity instanceof EntityLivingBase)) {
/*  905: 904 */       return isOnSameTeam((EntityLivingBase)entity.riddenByEntity);
/*  906:     */     }
/*  907: 905 */     return false;
/*  908:     */   }
/*  909:     */   
/*  910:     */   public void startSprinting()
/*  911:     */   {
/*  912: 911 */     if ((!isSprinting()) && (this.exhaustion <= 0))
/*  913:     */     {
/*  914: 912 */       setSprinting(true);
/*  915: 913 */       this.sprintTime = this.maxStamina;
/*  916:     */     }
/*  917:     */   }
/*  918:     */   
/*  919:     */   public boolean canSprint()
/*  920:     */   {
/*  921: 917 */     return true;
/*  922:     */   }
/*  923:     */   
/*  924:     */   public boolean canBlock()
/*  925:     */   {
/*  926: 921 */     return haveShied();
/*  927:     */   }
/*  928:     */   
/*  929:     */   public boolean haveShied()
/*  930:     */   {
/*  931: 926 */     if (this.leftHand.canBlock()) {
/*  932: 928 */       return true;
/*  933:     */     }
/*  934: 930 */     return false;
/*  935:     */   }
/*  936:     */   
/*  937:     */   public void toogleBlocking()
/*  938:     */   {
/*  939: 934 */     if (haveShied()) {
/*  940: 936 */       setDefending(!isDefending());
/*  941:     */     } else {
/*  942: 939 */       setDefending(false);
/*  943:     */     }
/*  944:     */   }
/*  945:     */   
/*  946:     */   public boolean canAimBeCanceled()
/*  947:     */   {
/*  948: 943 */     return false;
/*  949:     */   }
/*  950:     */   
/*  951:     */   public boolean isHealer()
/*  952:     */   {
/*  953: 947 */     return (this.rightHand.isHealer()) || (this.leftHand.isHealer());
/*  954:     */   }
/*  955:     */   
/*  956:     */   public boolean isRanged()
/*  957:     */   {
/*  958: 951 */     return (this.rightHand.isRanged()) || (this.leftHand.isRanged());
/*  959:     */   }
/*  960:     */   
/*  961:     */   public boolean isTwoHanded()
/*  962:     */   {
/*  963: 955 */     return this.rightHand.isTwoHanded();
/*  964:     */   }
/*  965:     */   
/*  966:     */   public void setCaptain(boolean captain)
/*  967:     */   {
/*  968: 959 */     setAnimFlag(0, captain);
/*  969:     */   }
/*  970:     */   
/*  971:     */   public boolean isCaptain()
/*  972:     */   {
/*  973: 962 */     return getAnimFlag(0);
/*  974:     */   }
/*  975:     */   
/*  976:     */   public boolean isDefending()
/*  977:     */   {
/*  978: 972 */     return getAnimFlag(2);
/*  979:     */   }
/*  980:     */   
/*  981:     */   public void setDefending(boolean flag)
/*  982:     */   {
/*  983: 976 */     if (haveShied()) {
/*  984: 978 */       setAnimFlag(2, flag);
/*  985:     */     }
/*  986:     */   }
/*  987:     */   
/*  988:     */   public boolean isSitting()
/*  989:     */   {
/*  990: 982 */     return getAnimFlag(3);
/*  991:     */   }
/*  992:     */   
/*  993:     */   public void setSitting(boolean flag)
/*  994:     */   {
/*  995: 985 */     setAnimFlag(3, flag);
/*  996:     */   }
/*  997:     */   
/*  998:     */   public boolean isSpeaking()
/*  999:     */   {
/* 1000: 988 */     return getAnimFlag(4);
/* 1001:     */   }
/* 1002:     */   
/* 1003:     */   public void setSpeaking(boolean flag)
/* 1004:     */   {
/* 1005: 991 */     setAnimFlag(4, flag);
/* 1006:     */   }
/* 1007:     */   
/* 1008:     */   public boolean isEating()
/* 1009:     */   {
/* 1010: 994 */     return getAnimFlag(5);
/* 1011:     */   }
/* 1012:     */   
/* 1013:     */   public void setEating(boolean flag)
/* 1014:     */   {
/* 1015: 997 */     setAnimFlag(5, flag);
/* 1016:     */   }
/* 1017:     */   
/* 1018:     */   public boolean isSneaking()
/* 1019:     */   {
/* 1020:1001 */     return getAnimFlag(6);
/* 1021:     */   }
/* 1022:     */   
/* 1023:     */   public void setSneaking(boolean flag)
/* 1024:     */   {
/* 1025:1004 */     setAnimFlag(6, flag);
/* 1026:     */   }
/* 1027:     */   
/* 1028:     */   protected boolean getAnimFlag(int index)
/* 1029:     */   {
/* 1030:1008 */     return (this.dataWatcher.getWatchableObjectByte(16) & 1 << index) != 0;
/* 1031:     */   }
/* 1032:     */   
/* 1033:     */   protected void setAnimFlag(int index, boolean result)
/* 1034:     */   {
/* 1035:1011 */     byte b = this.dataWatcher.getWatchableObjectByte(16);
/* 1036:1013 */     if (result) {
/* 1037:1015 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b | 1 << index)));
/* 1038:     */     } else {
/* 1039:1019 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b & (1 << index ^ 0xFFFFFFFF))));
/* 1040:     */     }
/* 1041:     */   }
/* 1042:     */   
/* 1043:     */   public double getAttackRangeBonus()
/* 1044:     */   {
/* 1045:1024 */     return this.rightHand.getAttackRangeBonus();
/* 1046:     */   }
/* 1047:     */   
/* 1048:     */   public double getMaxRangeForAttack()
/* 1049:     */   {
/* 1050:1027 */     return this.rightHand.getAttackRangeBonus();
/* 1051:     */   }
/* 1052:     */   
/* 1053:     */   public EntityLivingBase getOwner()
/* 1054:     */   {
/* 1055:1035 */     if ((this.owner == null) && (this.ownerName != null)) {
/* 1056:1036 */       setOwner(this.worldObj.getPlayerEntityByName(this.ownerName));
/* 1057:     */     }
/* 1058:1037 */     return this.owner;
/* 1059:     */   }
/* 1060:     */   
/* 1061:     */   public void setOwner(EntityLivingBase entity)
/* 1062:     */   {
/* 1063:1041 */     if ((entity instanceof EntityHumanBase))
/* 1064:     */     {
/* 1065:1043 */       EntityHumanBase human = (EntityHumanBase)entity;
/* 1066:1044 */       if (human.getOwner() == this) {
/* 1067:1045 */         return;
/* 1068:     */       }
/* 1069:     */     }
/* 1070:1047 */     else if ((entity instanceof EntityPlayer))
/* 1071:     */     {
/* 1072:1049 */       this.ownerName = ((EntityPlayer)entity).getCommandSenderName();
/* 1073:1050 */       if (entity.getTeam() != null) {
/* 1074:1052 */         this.entityTeam = entity.getTeam().getRegisteredName();
/* 1075:     */       }
/* 1076:     */     }
/* 1077:1054 */     else if (entity == null)
/* 1078:     */     {
/* 1079:1055 */       this.ownerName = null;
/* 1080:     */     }
/* 1081:1056 */     this.owner = entity;
/* 1082:     */   }
/* 1083:     */   
/* 1084:     */   public EntityLivingBase getLeader()
/* 1085:     */   {
/* 1086:1061 */     if ((this.party != null) && (this.party.getLeader() != this)) {
/* 1087:1062 */       return this.party.getLeader();
/* 1088:     */     }
/* 1089:1063 */     return this.owner;
/* 1090:     */   }
/* 1091:     */   
/* 1092:     */   public boolean tryPutIntoPArty(EntityHumanBase newMember)
/* 1093:     */   {
/* 1094:1067 */     if (this.party == null)
/* 1095:     */     {
/* 1096:1069 */       this.party = new EntityParty();
/* 1097:1070 */       this.party.tryToAddNewMember(this);
/* 1098:     */     }
/* 1099:1072 */     return this.party.tryToAddNewMember(newMember);
/* 1100:     */   }
/* 1101:     */   
/* 1102:     */   public void setInParty(EntityParty party, int angle, int dist)
/* 1103:     */   {
/* 1104:1076 */     this.party = party;
/* 1105:1077 */     if (!this.partyPositionPersistance)
/* 1106:     */     {
/* 1107:1079 */       this.partyPositionAngle = angle;
/* 1108:1080 */       this.partyDistanceToLeader = dist;
/* 1109:1081 */       if (isCaptain()) {
/* 1110:1082 */         this.AICombatMode = EnumAiCombat.OFFENSIVE.ordinal();
/* 1111:     */       }
/* 1112:1084 */       if ((isRanged()) || (isHealer()))
/* 1113:     */       {
/* 1114:1085 */         if (isRanged()) {
/* 1115:1086 */           this.partyDistanceToLeader += 2;
/* 1116:     */         }
/* 1117:1087 */         this.AICombatMode = EnumAiCombat.EVASIVE.ordinal();
/* 1118:     */       }
/* 1119:1089 */       else if ((haveShied()) || ((angle < 45) && (angle > -45)))
/* 1120:     */       {
/* 1121:1090 */         this.AICombatMode = EnumAiCombat.DEFENSIVE.ordinal();
/* 1122:     */       }
/* 1123:1092 */       setAIForCurrentMode();
/* 1124:     */     }
/* 1125:     */   }
/* 1126:     */   
/* 1127:     */   public void setOutOfParty()
/* 1128:     */   {
/* 1129:1097 */     this.party = null;
/* 1130:     */   }
/* 1131:     */   
/* 1132:     */   public Team getTeam()
/* 1133:     */   {
/* 1134:1103 */     EntityLivingBase entitylivingbase = getOwner();
/* 1135:1104 */     if ((entitylivingbase instanceof EntityPlayer)) {
/* 1136:1106 */       return entitylivingbase.getTeam();
/* 1137:     */     }
/* 1138:1108 */     return new MobTeam(this.entityTeam);
/* 1139:     */   }
/* 1140:     */   
/* 1141:     */   public boolean isOnSameTeam(EntityLivingBase entity)
/* 1142:     */   {
/* 1143:1113 */     if (getOwner() != null)
/* 1144:     */     {
/* 1145:1115 */       EntityLivingBase owner = getOwner();
/* 1146:1116 */       if (entity == owner) {
/* 1147:1118 */         return true;
/* 1148:     */       }
/* 1149:1120 */       if ((owner.getTeam() != null) && (entity.getTeam() != null)) {
/* 1150:1122 */         return owner.getTeam().getRegisteredName().equals(entity.getTeam().getRegisteredName());
/* 1151:     */       }
/* 1152:     */     }
/* 1153:1125 */     return super.isOnSameTeam(entity);
/* 1154:     */   }
/* 1155:     */   
/* 1156:     */   public int getInteligence()
/* 1157:     */   {
/* 1158:1130 */     return 4;
/* 1159:     */   }
/* 1160:     */   
/* 1161:     */   public int getAttackSpeed()
/* 1162:     */   {
/* 1163:1133 */     return 30;
/* 1164:     */   }
/* 1165:     */   
/* 1166:     */   public int getLeadershipValue()
/* 1167:     */   {
/* 1168:1136 */     return 0;
/* 1169:     */   }
/* 1170:     */   
/* 1171:     */   public int getTeamID()
/* 1172:     */   {
/* 1173:1140 */     return this.shieldID;
/* 1174:     */   }
/* 1175:     */   
/* 1176:     */   public ItemStack getDiamondArmorForSlot(int slot)
/* 1177:     */   {
/* 1178:1143 */     switch (slot)
/* 1179:     */     {
/* 1180:     */     case 3: 
/* 1181:1144 */       return new ItemStack(Items.diamond_chestplate);
/* 1182:     */     case 2: 
/* 1183:1145 */       return new ItemStack(Items.diamond_leggings);
/* 1184:     */     case 1: 
/* 1185:1146 */       return new ItemStack(Items.diamond_boots);
/* 1186:     */     }
/* 1187:1147 */     return new ItemStack(Items.diamond_helmet);
/* 1188:     */   }
/* 1189:     */   
/* 1190:     */   public ItemStack getIronArmorForSlot(int slot)
/* 1191:     */   {
/* 1192:1151 */     switch (slot)
/* 1193:     */     {
/* 1194:     */     case 3: 
/* 1195:1152 */       return new ItemStack(Items.iron_chestplate);
/* 1196:     */     case 2: 
/* 1197:1153 */       return new ItemStack(Items.iron_leggings);
/* 1198:     */     case 1: 
/* 1199:1154 */       return new ItemStack(Items.iron_boots);
/* 1200:     */     }
/* 1201:1155 */     return new ItemStack(Items.iron_helmet);
/* 1202:     */   }
/* 1203:     */   
/* 1204:     */   public boolean shouldRenderCape()
/* 1205:     */   {
/* 1206:1161 */     return false;
/* 1207:     */   }
/* 1208:     */   
/* 1209:     */   public ItemStack getHeldItem()
/* 1210:     */   {
/* 1211:1167 */     if (isEating()) {
/* 1212:1168 */       return new ItemStack(ChocolateQuest.potion);
/* 1213:     */     }
/* 1214:1169 */     if (this.rightHand != null) {
/* 1215:1170 */       return this.rightHand.getItem();
/* 1216:     */     }
/* 1217:1171 */     return super.getHeldItem();
/* 1218:     */   }
/* 1219:     */   
/* 1220:     */   public ItemStack getHeldItemLeft()
/* 1221:     */   {
/* 1222:1175 */     if (this.leftHand != null) {
/* 1223:1176 */       return this.leftHand.getItem();
/* 1224:     */     }
/* 1225:1177 */     return super.getHeldItem();
/* 1226:     */   }
/* 1227:     */   
/* 1228:     */   public void mountEntity(Entity e)
/* 1229:     */   {
/* 1230:1184 */     super.mountEntity(e);
/* 1231:1185 */     if ((this.ridingEntity != null) && (isSuitableMount(this.ridingEntity)) && ((e instanceof EntityAnimal)))
/* 1232:     */     {
/* 1233:1187 */       EntityLiving entityTarget = (EntityLiving)this.ridingEntity;
/* 1234:1188 */       boolean hasRiddenTask = false;
/* 1235:1189 */       for (Object task : entityTarget.tasks.taskEntries) {
/* 1236:1191 */         if ((task instanceof AIAnimalMountedByEntity)) {
/* 1237:1192 */           hasRiddenTask = true;
/* 1238:     */         }
/* 1239:     */       }
/* 1240:1194 */       if (!hasRiddenTask) {
/* 1241:1195 */         entityTarget.tasks.addTask(1, new AIAnimalMountedByEntity(entityTarget, 1.0F));
/* 1242:     */       }
/* 1243:     */     }
/* 1244:     */   }
/* 1245:     */   
/* 1246:     */   public boolean isSuitableMount(Entity entity)
/* 1247:     */   {
/* 1248:1200 */     return entity instanceof EntityHorse;
/* 1249:     */   }
/* 1250:     */   
/* 1251:     */   protected Entity getMount()
/* 1252:     */   {
/* 1253:1204 */     return new EntityHorse(this.worldObj);
/* 1254:     */   }
/* 1255:     */   
/* 1256:     */   public void setMountAI()
/* 1257:     */   {
/* 1258:1211 */     if ((this.ridingEntity != null) && (isSuitableMount(this.ridingEntity)))
/* 1259:     */     {
/* 1260:1213 */       if ((this.ridingEntity instanceof EntityHorse))
/* 1261:     */       {
/* 1262:1215 */         EntityHorse horse = (EntityHorse)this.ridingEntity;
/* 1263:1216 */         horse.setTamedBy(new DummyChocolate(this.worldObj));
/* 1264:     */       }
/* 1265:1219 */       EntityCreature entityTarget = (EntityCreature)this.ridingEntity;
/* 1266:1220 */       boolean hasRiddenTask = false;
/* 1267:1221 */       for (Object task : entityTarget.tasks.taskEntries) {
/* 1268:1223 */         if ((task instanceof AIAnimalMountedByEntity)) {
/* 1269:1224 */           hasRiddenTask = true;
/* 1270:     */         }
/* 1271:     */       }
/* 1272:1226 */       if (!hasRiddenTask) {
/* 1273:1227 */         entityTarget.tasks.addTask(1, new AIAnimalMountedByEntity(entityTarget, 1.0F));
/* 1274:     */       }
/* 1275:     */     }
/* 1276:     */   }
/* 1277:     */   
/* 1278:     */   protected void entityInit()
/* 1279:     */   {
/* 1280:1234 */     super.entityInit();
/* 1281:1235 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/* 1282:     */   }
/* 1283:     */   
/* 1284:     */   public int getTalkInterval()
/* 1285:     */   {
/* 1286:1240 */     return 450;
/* 1287:     */   }
/* 1288:     */   
/* 1289:     */   public void readSpawnData(ByteBuf additionalData)
/* 1290:     */   {
/* 1291:1245 */     NBTTagCompound data = null;
/* 1292:     */     try
/* 1293:     */     {
/* 1294:1248 */       this.AICombatMode = additionalData.readInt();
/* 1295:1249 */       this.AIMode = additionalData.readInt();
/* 1296:1250 */       this.partyPositionAngle = additionalData.readInt();
/* 1297:1251 */       this.partyDistanceToLeader = additionalData.readInt();
/* 1298:     */       
/* 1299:1253 */       byte length = additionalData.readByte();
/* 1300:1254 */       if (length > 0)
/* 1301:     */       {
/* 1302:1256 */         byte[] bData = new byte[length];
/* 1303:1257 */         additionalData.readBytes(bData);
/* 1304:1258 */         data = CompressedStreamTools.func_152457_a(bData, new NBTSizeTracker(bData.length * 2));
/* 1305:1259 */         ItemStack is = ItemStack.loadItemStackFromNBT(data);
/* 1306:1260 */         if (is != null) {
/* 1307:1261 */           this.leftHandItem = is;
/* 1308:     */         }
/* 1309:     */       }
/* 1310:     */     }
/* 1311:     */     catch (IOException e)
/* 1312:     */     {
/* 1313:1264 */       e.printStackTrace();
/* 1314:     */     }
/* 1315:1266 */     updateHands();
/* 1316:     */   }
/* 1317:     */   
/* 1318:     */   public void writeSpawnData(ByteBuf buffer)
/* 1319:     */   {
/* 1320:     */     try
/* 1321:     */     {
/* 1322:1273 */       buffer.writeInt(this.AICombatMode);
/* 1323:1274 */       buffer.writeInt(this.AIMode);
/* 1324:1275 */       buffer.writeInt(this.partyPositionAngle);
/* 1325:1276 */       buffer.writeInt(this.partyDistanceToLeader);
/* 1326:     */       
/* 1327:1278 */       NBTTagCompound data = new NBTTagCompound();
/* 1328:1279 */       if (getLeftHandItem() != null) {
/* 1329:1281 */         getLeftHandItem().writeToNBT(data);
/* 1330:     */       }
/* 1331:1283 */       byte[] bData = CompressedStreamTools.compress(data);
/* 1332:     */       
/* 1333:1285 */       buffer.writeByte(bData.length);
/* 1334:1286 */       buffer.writeBytes(bData);
/* 1335:     */     }
/* 1336:     */     catch (IOException e)
/* 1337:     */     {
/* 1338:1288 */       e.printStackTrace();
/* 1339:     */     }
/* 1340:1290 */     onSpawn();
/* 1341:     */   }
/* 1342:     */   
/* 1343:     */   public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack)
/* 1344:     */   {
/* 1345:1294 */     super.setCurrentItemOrArmor(par1, par2ItemStack);
/* 1346:1295 */     if (par1 == 0) {
/* 1347:1296 */       updateHands();
/* 1348:     */     }
/* 1349:     */   }
/* 1350:     */   
/* 1351:     */   public String func_152113_b()
/* 1352:     */   {
/* 1353:1301 */     return this.ownerName;
/* 1354:     */   }
/* 1355:     */   
/* 1356:1304 */   protected int physicDefense = 0;
/* 1357:1304 */   protected int magicDefense = 0;
/* 1358:1304 */   protected int blastDefense = 0;
/* 1359:1304 */   protected int fireDefense = 0;
/* 1360:1304 */   protected int projectileDefense = 0;
/* 1361:     */   
/* 1362:     */   public int getPhysicDefense()
/* 1363:     */   {
/* 1364:1307 */     return this.physicDefense;
/* 1365:     */   }
/* 1366:     */   
/* 1367:     */   public int getMagicDefense()
/* 1368:     */   {
/* 1369:1310 */     return this.magicDefense;
/* 1370:     */   }
/* 1371:     */   
/* 1372:     */   public int getBlastDefense()
/* 1373:     */   {
/* 1374:1313 */     return this.blastDefense;
/* 1375:     */   }
/* 1376:     */   
/* 1377:     */   public int getFireDefense()
/* 1378:     */   {
/* 1379:1316 */     return this.fireDefense;
/* 1380:     */   }
/* 1381:     */   
/* 1382:     */   public int getProjectileDefense()
/* 1383:     */   {
/* 1384:1319 */     return this.projectileDefense;
/* 1385:     */   }
/* 1386:     */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.entity.EntityHumanBase

 * JD-Core Version:    0.7.1

 */
