/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  5:   */ import com.chocolate.chocolateQuest.entity.npc.EntityHumanDummy;
/*  6:   */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*  7:   */ import com.chocolate.chocolateQuest.packets.PacketUpdateHumanData;
/*  8:   */ import com.chocolate.chocolateQuest.packets.PacketUpdateHumanDummyData;
/*  9:   */ import java.util.List;
/* 10:   */ import net.minecraft.entity.SharedMonsterAttributes;
/* 11:   */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/* 12:   */ import net.minecraft.entity.player.EntityPlayer;
/* 13:   */ import net.minecraft.inventory.IInventory;
/* 14:   */ import net.minecraft.world.World;
/* 15:   */ 
/* 16:   */ public class GuiDummy
/* 17:   */   extends GuiHuman
/* 18:   */ {
/* 19:   */   GuiButtonSlider healthScale;
/* 20:   */   GuiButtonSlider dropRight;
/* 21:   */   GuiButtonSlider dropHelmet;
/* 22:   */   GuiButtonSlider dropBody;
/* 23:   */   GuiButtonSlider dropLegs;
/* 24:   */   GuiButtonSlider dropFeet;
/* 25:   */   GuiButtonSlider dropLeft;
/* 26:   */   
/* 27:   */   public GuiDummy(EntityHumanBase human, IInventory par1iInventory, EntityPlayer playerInventory)
/* 28:   */   {
/* 29:18 */     super(human, par1iInventory, playerInventory);
/* 30:19 */     this.combatAINames = new String[] { "Offensive", "Defensive", "Evasive", "Flee", "Backstab" };
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void initGui()
/* 34:   */   {
/* 35:25 */     super.initGui();
/* 36:26 */     this.healthScale = new GuiButtonSlider(10, 10, 10, "Health scale", (float)(this.human.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() / 10.0D));
/* 37:27 */     this.buttonList.add(this.healthScale);
/* 38:   */     
/* 39:29 */     EntityHumanDummy dummy = (EntityHumanDummy)this.human;
/* 40:30 */     int desp = 22;
/* 41:31 */     int pos = 1;
/* 42:32 */     int posY = 20;
/* 43:33 */     this.dropRight = new GuiButtonSlider(10 + pos, 10, posY + pos * desp, "Drop hand R.", dummy.getEquipmentDropChances(0), 1);
/* 44:34 */     this.buttonList.add(this.dropRight);
/* 45:35 */     pos++;
/* 46:36 */     this.dropLeft = new GuiButtonSlider(10 + pos, 10, posY + pos * desp, "Drop hand L.", dummy.leftHandDropChances, 1);
/* 47:37 */     this.buttonList.add(this.dropLeft);
/* 48:38 */     pos++;
/* 49:39 */     this.dropHelmet = new GuiButtonSlider(10 + pos, 10, posY + pos * desp, "Drop head", dummy.getEquipmentDropChances(4), 1);
/* 50:40 */     this.buttonList.add(this.dropHelmet);
/* 51:41 */     pos++;
/* 52:42 */     this.dropBody = new GuiButtonSlider(10 + pos, 10, posY + pos * desp, "Drop body", dummy.getEquipmentDropChances(3), 1);
/* 53:43 */     this.buttonList.add(this.dropBody);
/* 54:44 */     pos++;
/* 55:45 */     this.dropLegs = new GuiButtonSlider(10 + pos, 10, posY + pos * desp, "Drop legs", dummy.getEquipmentDropChances(2), 1);
/* 56:46 */     this.buttonList.add(this.dropLegs);
/* 57:47 */     pos++;
/* 58:48 */     this.dropFeet = new GuiButtonSlider(10 + pos, 10, posY + pos * desp, "Drop feet", dummy.getEquipmentDropChances(1), 1);
/* 59:49 */     this.buttonList.add(this.dropFeet);
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void onGuiClosed()
/* 63:   */   {
/* 64:54 */     if (this.human.worldObj.isRemote)
/* 65:   */     {
/* 66:55 */       this.human.partyPositionAngle = this.teamPositionButton.getAngle();
/* 67:56 */       this.human.partyDistanceToLeader = this.teamPositionButton.getDistance();
/* 68:57 */       this.human.AIMode = this.passiveAI.selectedMode;
/* 69:58 */       this.human.AICombatMode = this.combatAI.selectedMode;
/* 70:59 */       this.human.partyPositionPersistance = true;
/* 71:60 */       this.human.updateHands();
/* 72:61 */       this.human.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(this.healthScale.sliderValue * this.healthScale.SLIDER_MAX_VALUE);
/* 73:   */       
/* 74:63 */       EntityHumanDummy dummy = (EntityHumanDummy)this.human;
/* 75:64 */       dummy.leftHandDropChances = this.dropLeft.sliderValue;
/* 76:65 */       dummy.setEquipmentDropChances(0, this.dropRight.sliderValue);
/* 77:66 */       dummy.setEquipmentDropChances(4, this.dropHelmet.sliderValue);
/* 78:67 */       dummy.setEquipmentDropChances(3, this.dropBody.sliderValue);
/* 79:68 */       dummy.setEquipmentDropChances(2, this.dropLegs.sliderValue);
/* 80:69 */       dummy.setEquipmentDropChances(1, this.dropFeet.sliderValue);
/* 81:   */       
/* 82:71 */       PacketUpdateHumanData packet = new PacketUpdateHumanDummyData(dummy);
/* 83:72 */       ChocolateQuest.channel.sendPaquetToServer(packet);
/* 84:   */     }
/* 85:74 */     super.onGuiClosed();
/* 86:   */   }
/* 87:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiDummy
 * JD-Core Version:    0.7.1
 */