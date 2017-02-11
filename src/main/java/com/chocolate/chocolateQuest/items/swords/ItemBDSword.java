/*   1:    */ package com.chocolate.chocolateQuest.items.swords;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.magic.Awakements;
/*   5:    */ import com.chocolate.chocolateQuest.magic.Elements;
/*   6:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   7:    */ import com.chocolate.chocolateQuest.packets.PacketSpawnParticlesAround;
/*   8:    */ import com.chocolate.chocolateQuest.utils.BDHelper;
/*   9:    */ import com.google.common.collect.HashMultimap;
/*  10:    */ import com.google.common.collect.Multimap;
/*  11:    */ import java.util.List;
/*  12:    */ import java.util.Random;
import net.minecraft.item.Item;

/*  13:    */ import net.minecraft.entity.Entity;
/*  14:    */ import net.minecraft.entity.EntityLivingBase;
/*  15:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  16:    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*  17:    */ import net.minecraft.entity.ai.attributes.IAttribute;
/*  18:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  19:    */ import net.minecraft.entity.item.EntityItem;
/*  20:    */ import net.minecraft.entity.player.EntityPlayer;
/*  21:    */ import net.minecraft.item.Item.ToolMaterial;
/*  22:    */ import net.minecraft.item.ItemStack;
/*  23:    */ import net.minecraft.item.ItemSword;
/*  24:    */ import net.minecraft.nbt.NBTTagCompound;
/*  25:    */ import net.minecraft.util.DamageSource;
/*  26:    */ import net.minecraft.world.World;
/*  27:    */ 
/*  28:    */ public class ItemBDSword
/*  29:    */   extends ItemSword
/*  30:    */ {
/*  31:    */   protected float weaponAttackDamage;
/*  32:    */   protected AttributeModifier damageModifier;
/*  33: 30 */   protected float elementModifier = 1.0F;
/*  34:    */   
/*  35:    */   public ItemBDSword(Item.ToolMaterial material)
/*  36:    */   {
/*  37: 33 */     this(material, 4.0F);
/*  38:    */   }
/*  39:    */   
/*  40:    */   public ItemBDSword(Item.ToolMaterial material, float baseDamage)
/*  41:    */   {
/*  42: 38 */     super(material);
/*  43: 39 */     this.weaponAttackDamage = (baseDamage + material.getDamageVsEntity());
/*  44:    */   }
/*  45:    */   
/*  46:    */   public Multimap getItemAttributeModifiers()
/*  47:    */   {
/*  48: 45 */     Multimap multimap = HashMultimap.create();
/*  49: 46 */     this.damageModifier = new AttributeModifier(field_111210_e, "Weapon modifier", this.weaponAttackDamage, 0);
/*  50: 47 */     multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), this.damageModifier);
/*  51: 48 */     return multimap;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public float getWeaponDamage()
/*  55:    */   {
/*  56: 53 */     return this.weaponAttackDamage;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void addInformation(ItemStack is, EntityPlayer player, List list, boolean par4)
/*  60:    */   {
/*  61: 58 */     super.addInformation(is, player, list, par4);
/*  62: 60 */     for (Elements element : Elements.values())
/*  63:    */     {
/*  64: 61 */       float value = getElementDamage(is, element);
/*  65: 62 */       if (value > 0.0F) {
/*  66: 63 */         list.add(BDHelper.StringColor(element.getStringColor()) + getElementString(element, value));
/*  67:    */       }
/*  68:    */     }
/*  69: 66 */     for (Awakements a : Awakements.awekements) {
/*  70: 67 */       if (Awakements.hasEnchant(is, a)) {
/*  71: 68 */         list.add(a.getDescription(is));
/*  72:    */       }
/*  73:    */     }
/*  74:    */   }
/*  75:    */   
/*  76:    */   protected String getElementString(Elements element, float value)
/*  77:    */   {
/*  78: 73 */     return "+" + translateFloat(value) + " " + element.getTranslatedName();
/*  79:    */   }
/*  80:    */   
/*  81:    */   public String translateFloat(float d)
/*  82:    */   {
/*  83: 76 */     String value = Double.toString(d);
/*  84: 77 */     int index = value.indexOf(".");
/*  85: 78 */     if (index < 3) {
/*  86: 79 */       index += 2;
/*  87:    */     }
/*  88: 80 */     value = value.substring(0, index);
/*  89: 81 */     return value;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public boolean hitEntity(ItemStack is, EntityLivingBase target, EntityLivingBase entity)
/*  93:    */   {
/*  94: 87 */     applyEnchantmentHit(is, target, entity);
/*  95: 88 */     return super.hitEntity(is, target, entity);
/*  96:    */   }
/*  97:    */   
/*  98:    */   public void applyEnchantmentHit(ItemStack is, EntityLivingBase target, EntityLivingBase entity)
/*  99:    */   {
/* 100: 92 */     if ((target.hurtResistantTime == 20) || (target.hurtResistantTime == 0)) {
/* 101: 93 */       for (Elements element : Elements.values())
/* 102:    */       {
/* 103: 94 */         float damage = getElementDamage(is, element);
/* 104: 95 */         if (damage > 0.0F)
/* 105:    */         {
/* 106: 96 */           target.hurtResistantTime = 0;
/* 107: 97 */           DamageSource ds = element.getDamageSource();
/* 108: 98 */           damage = element.onHitEntity(entity, target, damage);
/* 109: 99 */           if ((target.attackEntityFrom(ds, damage)) && 
/* 110:100 */             (!target.worldObj.isRemote))
/* 111:    */           {
/* 112:102 */             byte particle = PacketSpawnParticlesAround.getParticleFromName(element.getParticle());
/* 113:103 */             PacketSpawnParticlesAround packet = new PacketSpawnParticlesAround(particle, target.posX, target.posY + 1.0D + itemRand.nextDouble(), target.posZ);
/* 114:104 */             ChocolateQuest.channel.sendToAllAround(target, packet, 64);
/* 115:    */           }
/* 116:    */         }
/* 117:    */       }
/* 118:    */     }
/* 119:    */   }
/* 120:    */   
/* 121:113 */   protected float cachedDamage = 0.0F;
/* 122:    */   
/* 123:    */   public void attackEntityWithItem(EntityPlayer player, Entity e)
/* 124:    */   {
/* 125:116 */     AttributeModifier cacheDamageModifier = new AttributeModifier(field_111210_e, "Weapon modifier", this.cachedDamage, 0);
/* 126:117 */     player.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(this.damageModifier);
/* 127:118 */     player.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(cacheDamageModifier);
/* 128:    */     
/* 129:120 */     player.attackTargetEntityWithCurrentItem(e);
/* 130:    */     
/* 131:122 */     player.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(cacheDamageModifier);
/* 132:123 */     player.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(this.damageModifier);
/* 133:    */   }
/* 134:    */   
/* 135:    */   public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
/* 136:    */   {
/* 137:130 */     if (Awakements.hasEnchant(itemStack, Awakements.property)) {
/* 138:131 */       Awakements.property.onUpdate(entity, itemStack);
/* 139:    */     }
/* 140:133 */     if (Awakements.hasEnchant(itemStack, Awakements.autoRepair)) {
/* 141:134 */       Awakements.autoRepair.onUpdate(entity, itemStack);
/* 142:    */     }
/* 143:    */   }
/* 144:    */   
/* 145:    */   public boolean onEntityItemUpdate(EntityItem entityItem)
/* 146:    */   {
/* 147:139 */     Awakements.property.onEntityItemUpdate(entityItem);
/* 148:140 */     return super.onEntityItemUpdate(entityItem);
/* 149:    */   }
/* 150:    */   
/* 151:    */   public float getPhysicDamage(ItemStack is)
/* 152:    */   {
/* 153:144 */     return getElementDamage(is, Elements.physic);
/* 154:    */   }
/* 155:    */   
/* 156:    */   public float getFireDamage(ItemStack is)
/* 157:    */   {
/* 158:147 */     return getElementDamage(is, Elements.fire);
/* 159:    */   }
/* 160:    */   
/* 161:    */   public float getMagicDamage(ItemStack is)
/* 162:    */   {
/* 163:150 */     return getElementDamage(is, Elements.magic);
/* 164:    */   }
/* 165:    */   
/* 166:    */   public float getBlastDamage(ItemStack is)
/* 167:    */   {
/* 168:153 */     return getElementDamage(is, Elements.blast);
/* 169:    */   }
/* 170:    */   
/* 171:    */   public void setPhysicDamage(int i, ItemStack is)
/* 172:    */   {
/* 173:157 */     setElementValue(is, Elements.physic, i);
/* 174:    */   }
/* 175:    */   
/* 176:    */   public void setFireDamage(int i, ItemStack is)
/* 177:    */   {
/* 178:160 */     setElementValue(is, Elements.fire, i);
/* 179:    */   }
/* 180:    */   
/* 181:    */   public void setMagicDamage(int i, ItemStack is)
/* 182:    */   {
/* 183:163 */     setElementValue(is, Elements.magic, i);
/* 184:    */   }
/* 185:    */   
/* 186:    */   public void setBlastDamage(int i, ItemStack is)
/* 187:    */   {
/* 188:166 */     setElementValue(is, Elements.blast, i);
/* 189:    */   }
/* 190:    */   
/* 191:    */   protected float getElementDamage(ItemStack is, Elements element)
/* 192:    */   {
/* 193:170 */     return getElementValue(is, element) * this.elementModifier;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public int getElementValue(ItemStack is, Elements element)
/* 197:    */   {
/* 198:173 */     if (is.stackTagCompound == null) {
/* 199:174 */       return 0;
/* 200:    */     }
/* 201:175 */     return is.stackTagCompound.getByte(element.getName());
/* 202:    */   }
/* 203:    */   
/* 204:    */   public void setElementValue(ItemStack is, Elements element, int value)
/* 205:    */   {
/* 206:178 */     if (is.stackTagCompound == null) {
/* 207:179 */       is.stackTagCompound = new NBTTagCompound();
/* 208:    */     }
/* 209:180 */     is.stackTagCompound.setByte(element.getName(), (byte)value);
/* 210:    */   }
/* 211:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemBDSword

 * JD-Core Version:    0.7.1

 */
