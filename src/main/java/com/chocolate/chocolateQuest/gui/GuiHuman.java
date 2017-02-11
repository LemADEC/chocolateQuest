/*  1:   */ package com.chocolate.chocolateQuest.gui;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*  4:   */ import com.chocolate.chocolateQuest.entity.EntityHumanBase;
/*  5:   */ import com.chocolate.chocolateQuest.entity.ai.EnumAiState;
/*  6:   */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*  7:   */ import com.chocolate.chocolateQuest.packets.PacketUpdateHumanData;
/*  8:   */ import java.util.List;
/*  9:   */ import net.minecraft.client.gui.GuiButton;
/* 10:   */ import net.minecraft.entity.player.EntityPlayer;
/* 11:   */ import net.minecraft.inventory.IInventory;
/* 12:   */ import net.minecraft.util.ChatComponentTranslation;
/* 13:   */ import net.minecraft.world.World;
/* 14:   */ 
/* 15:   */ public class GuiHuman
/* 16:   */   extends GuiHumanBase
/* 17:   */ {
/* 18:   */   GuiButtonAngle teamPositionButton;
/* 19:   */   GuiButtonAIMode combatAI;
/* 20:   */   GuiButtonAIMode passiveAI;
/* 21:   */   EntityHumanBase human;
/* 22:21 */   String[] combatAINames = { "Offensive", "Defensive", "Evasive", "Flee" };
/* 23:   */   boolean owned;
/* 24:25 */   final int FOLLOW_BUTTON_ID = 100;
/* 25:26 */   final String FOLLOW = "Follow";
/* 26:26 */   final String LEAVE = "Leave";
/* 27:   */   
/* 28:   */   public GuiHuman(ContainerHumanInventory container, EntityHumanBase human, IInventory par1IInventory, EntityPlayer playerInventory)
/* 29:   */   {
/* 30:30 */     super(container, par1IInventory, playerInventory);
/* 31:31 */     this.human = human;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public GuiHuman(EntityHumanBase human, IInventory par1IInventory, EntityPlayer playerInventory)
/* 35:   */   {
/* 36:35 */     this(new ContainerHumanInventory(playerInventory.inventory, par1IInventory), human, par1IInventory, playerInventory);
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void onGuiClosed()
/* 40:   */   {
/* 41:40 */     if (this.human.worldObj.isRemote)
/* 42:   */     {
/* 43:41 */       this.human.partyPositionAngle = this.teamPositionButton.getAngle();
/* 44:42 */       this.human.partyDistanceToLeader = this.teamPositionButton.getDistance();
/* 45:43 */       this.human.AIMode = this.passiveAI.selectedMode;
/* 46:44 */       this.human.AICombatMode = this.combatAI.selectedMode;
/* 47:45 */       this.human.partyPositionPersistance = true;
/* 48:46 */       this.human.updateHands();
/* 49:47 */       this.human.updateOwner = this.owned;
/* 50:48 */       this.human.playerSpeakingTo = null;
/* 51:49 */       PacketUpdateHumanData packet = new PacketUpdateHumanData(this.human);
/* 52:50 */       ChocolateQuest.channel.sendPaquetToServer(packet);
/* 53:   */     }
/* 54:52 */     super.onGuiClosed();
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void initGui()
/* 58:   */   {
/* 59:58 */     super.initGui();
/* 60:59 */     int width = (this.width - this.xSize) / 2 + 80;
/* 61:60 */     int height = this.height - this.height / 2 - 10;
/* 62:61 */     this.teamPositionButton = new GuiButtonAngle(10, width - 15, height - 80, "test", 1.0F, this.human);
/* 63:62 */     this.buttonList.add(this.teamPositionButton);
/* 64:   */     
/* 65:   */ 
/* 66:65 */     this.combatAI = new GuiButtonBattleAIMode(10, width + 80, height - 80, 20, 80, this.combatAINames, this.fontRendererObj, this.human.AICombatMode);
/* 67:66 */     this.buttonList.add(this.combatAI);
/* 68:   */     
/* 69:68 */     String[] names = new String[EnumAiState.values().length];
/* 70:69 */     for (int i = 0; i < names.length; i++) {
/* 71:70 */       names[i] = new ChatComponentTranslation(EnumAiState.values()[i].ainame, new Object[0]).getFormattedText();
/* 72:   */     }
/* 73:71 */     this.passiveAI = new GuiButtonAIMode(10, width + 120, height - 80, 60, 100, names, this.fontRendererObj, this.human.AIMode);
/* 74:72 */     this.buttonList.add(this.passiveAI);
/* 75:   */     
/* 76:74 */     GuiButton b = new GuiButton(100, width - 70, height - 110, "Follow/Leave");
/* 77:75 */     this.buttonList.add(b);
/* 78:   */   }
/* 79:   */   
/* 80:   */   protected void mouseClicked(int par1, int par2, int par3)
/* 81:   */   {
/* 82:79 */     super.mouseClicked(par1, par2, par3);
/* 83:   */   }
/* 84:   */   
/* 85:   */   protected void actionPerformed(GuiButton button)
/* 86:   */   {
/* 87:83 */     if (button.id == 100) {
/* 88:84 */       this.owned = (!this.owned);
/* 89:   */     }
/* 90:87 */     super.actionPerformed(button);
/* 91:   */   }
/* 92:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiHuman
 * JD-Core Version:    0.7.1
 */