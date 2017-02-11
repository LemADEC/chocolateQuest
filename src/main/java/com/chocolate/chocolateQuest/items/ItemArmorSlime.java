/*  1:   */ package com.chocolate.chocolateQuest.items;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.boss.EntitySlimePart;
/*  5:   */ import com.google.common.collect.HashMultimap;
/*  6:   */ import com.google.common.collect.Multimap;
/*  7:   */ import cpw.mods.fml.relauncher.Side;
/*  8:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  9:   */ import java.util.Random;
/* 10:   */ import net.minecraft.client.model.ModelBiped;
/* 11:   */ import net.minecraft.client.renderer.texture.IIconRegister;
/* 12:   */ import net.minecraft.entity.Entity;
/* 13:   */ import net.minecraft.entity.EntityLivingBase;
/* 14:   */ import net.minecraft.entity.SharedMonsterAttributes;
/* 15:   */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/* 16:   */ import net.minecraft.entity.ai.attributes.IAttribute;
/* 17:   */ import net.minecraft.item.ItemStack;
/* 18:   */ import net.minecraft.world.World;
/* 19:   */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/* 20:   */ 
/* 21:   */ public class ItemArmorSlime
/* 22:   */   extends ItemArmorBase
/* 23:   */ {
/* 24:   */   int type;
/* 25:   */   String name;
/* 26:   */   AttributeModifier health;
/* 27:   */   AttributeModifier knockBack;
/* 28:   */   
/* 29:   */   public ItemArmorSlime(int type, String name)
/* 30:   */   {
/* 31:28 */     super(ItemArmorBase.TURTLE, type);
/* 32:29 */     this.type = type;
/* 33:30 */     this.name = name;
/* 34:31 */     this.health = new AttributeModifier("SlimeHmodifier" + type, 4.0D, 0);
/* 35:32 */     this.knockBack = new AttributeModifier("SlimeKBmodifier" + type, -0.25D, 0);
/* 36:33 */     setEpic();
/* 37:   */   }
/* 38:   */   
/* 39:   */   public Multimap getItemAttributeModifiers()
/* 40:   */   {
/* 41:40 */     Multimap multimap = HashMultimap.create();
/* 42:41 */     multimap.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), this.knockBack);
/* 43:42 */     multimap.put(SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(), this.health);
/* 44:43 */     return multimap;
/* 45:   */   }
/* 46:   */   
/* 47:   */   @SideOnly(Side.CLIENT)
/* 48:   */   public void registerIcons(IIconRegister iconRegister) {}
/* 49:   */   
/* 50:   */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
/* 51:   */   {
/* 52:56 */     if (slot == 2) {
/* 53:57 */       return "chocolatequest:textures/entity/armorSlime_1.png";
/* 54:   */     }
/* 55:58 */     return "chocolatequest:textures/entity/armorSlime.png";
/* 56:   */   }
/* 57:   */   
/* 58:   */   @SideOnly(Side.CLIENT)
/* 59:   */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/* 60:   */   {
/* 61:64 */     return com.chocolate.chocolateQuest.client.ClientProxy.slimeArmor[armorSlot];
/* 62:   */   }
/* 63:   */   
/* 64:   */   public void onHit(LivingHurtEvent event, ItemStack is, EntityLivingBase entity)
/* 65:   */   {
/* 66:69 */     if ((!entity.worldObj.isRemote) && (entity.hurtTime == 0)) {
/* 67:71 */       if ((this.armorType == 1) && 
/* 68:72 */         (isFullSet(entity, is)))
/* 69:   */       {
/* 70:73 */         float ammount = event.ammount * (1.0F / entity.getTotalArmorValue() * 4.0F);
/* 71:74 */         if ((event.ammount > 0.0F) && ((ammount >= 2.0F) || (itemRand.nextInt(2) == 0)))
/* 72:   */         {
/* 73:75 */           int size = (int)Math.min(2.0D, Math.max(1.0D, ammount * 0.8D));
/* 74:76 */           EntitySlimePart part = new EntitySlimePart(entity.worldObj, entity, size);
/* 75:77 */           part.setPosition(entity.posX, entity.posY + 1.0D, entity.posZ);
/* 76:78 */           part.motionX = itemRand.nextGaussian();
/* 77:79 */           part.motionZ = itemRand.nextGaussian();
/* 78:80 */           entity.worldObj.spawnEntityInWorld(part);
/* 79:   */         }
/* 80:   */       }
/* 81:   */     }
/* 82:   */   }
/* 83:   */   
/* 84:   */   public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemMaterial)
/* 85:   */   {
/* 86:90 */     return ((itemMaterial.getItem() == ChocolateQuest.material) && (itemMaterial.getItemDamage() == 3)) || (super.getIsRepairable(itemToRepair, itemMaterial));
/* 87:   */   }
/* 88:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.ItemArmorSlime
 * JD-Core Version:    0.7.1
 */