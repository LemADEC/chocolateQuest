/*   1:    */ package com.chocolate.chocolateQuest.entity;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import java.util.Iterator;
/*   5:    */ import java.util.List;
/*   6:    */ import java.util.Random;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.entity.EntityCreature;
/*   9:    */ import net.minecraft.entity.EntityLivingBase;
/*  10:    */ import net.minecraft.entity.SharedMonsterAttributes;
/*  11:    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*  12:    */ import net.minecraft.entity.player.EntityPlayer;
/*  13:    */ import net.minecraft.item.ItemStack;
/*  14:    */ import net.minecraft.util.ChatComponentText;
/*  15:    */ import net.minecraft.world.World;
/*  16:    */ 
/*  17:    */ public class EntityReferee
/*  18:    */   extends EntityCreature
/*  19:    */ {
/*  20:    */   List<EntityLivingBase> trackingPlayers;
/*  21:    */   List<EntityLivingBase> trackingMobs;
/*  22: 22 */   int lifeTime = 0;
/*  23:    */   
/*  24:    */   public EntityReferee(World par1World)
/*  25:    */   {
/*  26: 25 */     super(par1World);
/*  27: 26 */     this.experienceValue = 0;
/*  28: 27 */     initTasks();
/*  29: 28 */     for (int i = 0; i < this.equipmentDropChances.length; i++) {
/*  30: 30 */       this.equipmentDropChances[i] = 0.0F;
/*  31:    */     }
/*  32: 32 */     setCurrentItemOrArmor(0, new ItemStack(ChocolateQuest.egg, 1, 499));
/*  33:    */   }
/*  34:    */   
/*  35:    */   public EntityReferee(World world, List<EntityLivingBase> trackingPlayers, List<EntityLivingBase> trackingMobs)
/*  36:    */   {
/*  37: 38 */     this(world);
/*  38: 39 */     this.trackingMobs = trackingMobs;
/*  39: 40 */     this.trackingPlayers = trackingPlayers;
/*  40:    */   }
/*  41:    */   
/*  42:    */   protected void applyEntityAttributes()
/*  43:    */   {
/*  44: 46 */     super.applyEntityAttributes();
/*  45: 47 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
/*  46: 48 */     getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
/*  47: 49 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.33D);
/*  48:    */   }
/*  49:    */   
/*  50:    */   protected void initTasks() {}
/*  51:    */   
/*  52:    */   public void onLivingUpdate()
/*  53:    */   {
/*  54: 59 */     boolean lost = false;
/*  55: 60 */     boolean win = false;
/*  56: 61 */     if (this.trackingPlayers != null)
/*  57:    */     {
/*  58: 62 */       for (Iterator<EntityLivingBase> iter = this.trackingPlayers.listIterator(); iter.hasNext();)
/*  59:    */       {
/*  60: 63 */         Entity e = (Entity)iter.next();
/*  61: 64 */         if (e.isDead)
/*  62:    */         {
/*  63: 65 */           iter.remove();
/*  64: 66 */           EntityPlayer player = (EntityPlayer)e;
/*  65: 67 */           player.addChatMessage(new ChatComponentText("Game Over. Try again!"));
/*  66:    */         }
/*  67:    */       }
/*  68: 70 */       if (this.trackingPlayers.isEmpty()) {
/*  69: 71 */         lost = true;
/*  70:    */       }
/*  71:    */     }
/*  72: 74 */     if (this.trackingMobs != null)
/*  73:    */     {
/*  74: 75 */       for (Iterator<EntityLivingBase> iter = this.trackingMobs.listIterator(); iter.hasNext();)
/*  75:    */       {
/*  76: 76 */         Entity e = (Entity)iter.next();
/*  77: 77 */         if (e.isDead) {
/*  78: 78 */           iter.remove();
/*  79:    */         }
/*  80:    */       }
/*  81: 81 */       if (this.trackingMobs.isEmpty()) {
/*  82: 82 */         win = true;
/*  83:    */       }
/*  84:    */     }
/*  85: 85 */     if ((!this.worldObj.isRemote) && 
/*  86: 86 */       (this.trackingMobs == null) && (this.trackingPlayers == null)) {
/*  87: 87 */       setDead();
/*  88:    */     }

/*  89: 90 */     if (lost)
/*  90:    */     {
/*  91: 91 */       setDead();
Entity e = null;

/*  93: 92 */       for (Iterator i$ = this.trackingPlayers.iterator(); i$.hasNext(); e = (Entity)i$.next()) {}
/*  94: 94 */
for (Iterator i$ = this.trackingMobs.iterator(); i$.hasNext(); e = (Entity)i$.next()) {
e.setDead();
}
/*  97:    */     }

/*  98: 98 */     if (win)
/*  99:    */     {
/* 100: 99 */       setDead();
/* 101:100 */       for (Entity e : this.trackingPlayers)
/* 102:    */       {
/* 103:101 */         EntityPlayer player = (EntityPlayer)e;
/* 104:102 */         int MINUTE = 60;
/* 105:103 */         float TICK_PER_SEC = 20.0F;
/* 106:104 */         float timef = this.ticksExisted / 20.0F;
/* 107:105 */         String time = (int)(timef / 60.0F) + "m " + (int)(timef % 60.0F) + "s ";
/* 108:106 */         player.addChatMessage(new ChatComponentText("Winner!!! " + time));
/* 109:    */       }
/* 110:    */     }
/* 111:109 */     super.onLivingUpdate();
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void setDead()
/* 115:    */   {
/* 116:115 */     if (this.worldObj.isRemote) {
/* 117:117 */       for (int r = 0; r < 30; r++) {
/* 118:119 */         this.worldObj.spawnParticle("smoke", this.posX + this.rand.nextFloat() - 0.5D, this.posY + this.rand.nextFloat() * 2.0F, this.posZ + this.rand.nextFloat() - 0.5D, 0.0D, 0.0D, 0.0D);
/* 119:    */       }
/* 120:    */     }
/* 121:123 */     super.setDead();
/* 122:    */   }
/* 123:    */   
/* 124:    */   protected void attackEntity(Entity par1Entity, float par2)
/* 125:    */   {
/* 126:127 */     if ((par1Entity instanceof EntityPlayer)) {
/* 127:128 */       super.attackEntity(par1Entity, par2);
/* 128:    */     }
/* 129:    */   }
/* 130:    */   
/* 131:    */   public boolean isAIEnabled()
/* 132:    */   {
/* 133:134 */     return true;
/* 134:    */   }
/* 135:    */ }




/* Location:           P:\robf.jar

 * Qualified Name:     com.chocolate.chocolateQuest.entity.EntityReferee

 * JD-Core Version:    0.7.1

 */
