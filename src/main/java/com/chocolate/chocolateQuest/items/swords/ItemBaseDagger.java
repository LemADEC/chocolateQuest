/*   1:    */ package com.chocolate.chocolateQuest.items.swords;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.magic.Awakements;
/*   5:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   6:    */ import com.chocolate.chocolateQuest.packets.PacketSpawnParticlesAround;
/*   7:    */ import com.google.common.collect.Multimap;
/*   8:    */ import cpw.mods.fml.relauncher.Side;
/*   9:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  10:    */ import java.io.PrintStream;
/*  11:    */ import java.util.Random;
/*  12:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  13:    */ import net.minecraft.entity.EntityLivingBase;
/*  14:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  15:    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*  16:    */ import net.minecraft.entity.ai.attributes.IAttribute;
/*  17:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  18:    */ import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

/*  19:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*  20:    */ import net.minecraft.item.Item.ToolMaterial;
/*  21:    */ import net.minecraft.item.ItemStack;
/*  22:    */ import net.minecraft.util.DamageSource;
/*  23:    */ import net.minecraft.util.FoodStats;
/*  24:    */ import net.minecraft.world.World;
/*  25:    */ 
/*  26:    */ public class ItemBaseDagger
/*  27:    */   extends ItemBDSword
/*  28:    */ {
/*  29:    */   String texture;
/*  30: 24 */   double speedModifier = 0.05D;
/*  31:    */   
/*  32:    */   public ItemBaseDagger(Item.ToolMaterial mat, int baseDamage)
/*  33:    */   {
/*  34: 27 */     super(mat, baseDamage);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public ItemBaseDagger(Item.ToolMaterial mat, String texture)
/*  38:    */   {
/*  39: 32 */     this(mat, 2);
/*  40: 33 */     this.texture = texture;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public ItemBaseDagger(Item.ToolMaterial mat, String texture, int baseDamage, float elementModifier)
/*  44:    */   {
/*  45: 38 */     this(mat, baseDamage);
/*  46: 39 */     this.texture = texture;
/*  47: 40 */     this.elementModifier = elementModifier;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
/*  51:    */   {
/*  52: 51 */     if ((entityPlayer.onGround) && (!entityPlayer.isSwingInProgress))
/*  53:    */     {
/*  54: 52 */       FoodStats food = entityPlayer.getFoodStats();
/*  55: 53 */       if (food.getFoodLevel() > 2)
/*  56:    */       {
/*  57: 54 */         if (!entityPlayer.capabilities.isCreativeMode)
/*  58:    */         {
/*  59: 56 */           float exhaustion = 4.0F;
/*  60: 57 */           int staminaSaving = Awakements.getEnchantLevel(itemStack, Awakements.dodgeStamina);
/*  61: 58 */           if (staminaSaving > 0) {
/*  62: 59 */             exhaustion = (exhaustion - 1.0F) / staminaSaving;
/*  63:    */           }
/*  64: 60 */           System.out.println(exhaustion);
/*  65: 61 */           food.addExhaustion(exhaustion);
/*  66:    */         }
/*  67: 63 */         float rot = entityPlayer.rotationYawHead * 3.1416F / 180.0F;
/*  68: 64 */         double mx = -Math.sin(rot);
/*  69: 65 */         double mz = Math.cos(rot);
/*  70: 66 */         entityPlayer.motionX += mx * entityPlayer.moveForward;
/*  71: 67 */         entityPlayer.motionZ += mz * entityPlayer.moveForward;
/*  72: 68 */         rot = (float)(rot - 1.57D);
/*  73: 69 */         mx = -Math.sin(rot);
/*  74: 70 */         mz = Math.cos(rot);
/*  75: 71 */         entityPlayer.motionX += mx * entityPlayer.moveStrafing;
/*  76: 72 */         entityPlayer.motionZ += mz * entityPlayer.moveStrafing;
/*  77: 73 */         entityPlayer.motionY = 0.2D;
/*  78:    */       }
/*  79:    */     }
/*  80: 76 */     return super.onItemRightClick(itemStack, world, entityPlayer);
/*  81:    */   }
/*  82:    */   
/*  83:    */   @SideOnly(Side.CLIENT)
/*  84:    */   public void registerIcons(IIconRegister iconRegister)
/*  85:    */   {
/*  86: 83 */     this.itemIcon = iconRegister.registerIcon("chocolatequest:" + this.texture);
/*  87:    */   }
/*  88:    */   
/*  89:    */   public Multimap getItemAttributeModifiers()
/*  90:    */   {
/*  91: 89 */     Multimap multimap = super.getItemAttributeModifiers();
/*  92: 90 */     multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", this.speedModifier, 2));
/*  93: 91 */     return multimap;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase target, EntityLivingBase entity)
/*  97:    */   {
/*  98:104 */     double angle = entity.rotationYaw - target.rotationYaw;
/*  99:105 */     while (angle > 360.0D) {
/* 100:105 */       angle -= 360.0D;
/* 101:    */     }
/* 102:106 */     while (angle < 0.0D) {
/* 103:106 */       angle += 360.0D;
/* 104:    */     }
/* 105:107 */     angle = Math.abs(angle - 180.0D);
/* 106:109 */     if (angle > 130.0D)
/* 107:    */     {
/* 108:111 */       if (!entity.worldObj.isRemote)
/* 109:    */       {
/* 110:113 */         PacketSpawnParticlesAround packet = new PacketSpawnParticlesAround((byte)1, target.posX, target.posY + 1.0D + itemRand.nextDouble(), target.posZ);
/* 111:114 */         ChocolateQuest.channel.sendToAllAround(entity, packet, 64);
/* 112:    */       }
/* 113:116 */       if (entity.worldObj.isRemote) {
/* 114:118 */         for (int i = 0; i < 5; i++) {
/* 115:120 */           entity.worldObj.spawnParticle("crit", target.posX, target.posY + 1.0D + itemRand.nextDouble(), target.posZ, itemRand.nextDouble() - 0.5D, -0.5D + itemRand.nextDouble(), itemRand.nextDouble() - 0.5D);
/* 116:    */         }
/* 117:    */       }
/* 118:123 */       float damage = (float)entity.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
/* 119:124 */       int awkModifier = Awakements.getEnchantLevel(par1ItemStack, Awakements.backStab);
/* 120:125 */       float backstabModifier = 2.5F + 0.4F * awkModifier;
/* 121:126 */       target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)entity), damage * backstabModifier);
/* 122:    */     }
/* 123:128 */     return super.hitEntity(par1ItemStack, target, entity);
/* 124:    */   }
/* 125:    */   
/* 126:    */   private float getBackStabModifier()
/* 127:    */   {
/* 128:132 */     return 3.0F;
/* 129:    */   }
/* 130:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.items.swords.ItemBaseDagger

 * JD-Core Version:    0.7.1

 */
