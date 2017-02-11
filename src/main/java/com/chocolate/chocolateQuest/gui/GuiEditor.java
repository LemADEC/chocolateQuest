/*   1:    */ package com.chocolate.chocolateQuest.gui;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.ChocolateQuest;
/*   4:    */ import com.chocolate.chocolateQuest.block.BlockEditorTileEntity;
/*   5:    */ import com.chocolate.chocolateQuest.packets.ChannelHandler;
/*   6:    */ import com.chocolate.chocolateQuest.packets.PacketEditorGUIClose;
/*   7:    */ import java.io.ByteArrayOutputStream;
/*   8:    */ import java.io.DataOutputStream;
/*   9:    */ import java.util.List;
/*  10:    */ import net.minecraft.client.Minecraft;
/*  11:    */ import net.minecraft.client.gui.GuiButton;
/*  12:    */ import net.minecraft.client.gui.GuiScreen;
/*  13:    */ import net.minecraft.client.gui.GuiTextField;
/*  14:    */ import net.minecraft.world.World;
/*  15:    */ 
/*  16:    */ public class GuiEditor
/*  17:    */   extends GuiScreen
/*  18:    */ {
/*  19: 30 */   byte ACTION_EXPORT = 1;
/*  20: 30 */   byte ACTION_UPDATE = 0;
/*  21:    */   BlockEditorTileEntity block;
/*  22:    */   int z;
/*  23:    */   int y;
/*  24:    */   int x;
/*  25:    */   int posZ;
/*  26:    */   int posY;
/*  27:    */   int posX;
/*  28:    */   World world;
/*  29:    */   private GuiButton buttonPaste;
/*  30:    */   private GuiButton buttonExit;
/*  31:    */   private GuiButton buttonOK;
/*  32:    */   private GuiTextField textboxFile;
/*  33:    */   private GuiTextField textboxHeight;
/*  34:    */   private GuiTextField textboxSY;
/*  35:    */   private GuiTextField textboxSX;
/*  36:    */   
/*  37:    */   public GuiEditor(World world, int x, int y, int z)
/*  38:    */   {
/*  39: 33 */     this.posX = x;
/*  40: 34 */     this.posY = y;
/*  41: 35 */     this.posZ = z;
/*  42: 36 */     this.block = ((BlockEditorTileEntity)world.getTileEntity(x, y, z));
/*  43: 37 */     this.x = this.block.red;
/*  44: 38 */     this.y = this.block.height;
/*  45: 39 */     this.z = this.block.yellow;
/*  46: 40 */     this.world = world;
/*  47:    */   }
/*  48:    */   
/*  49:    */   protected void actionPerformed(GuiButton guibutton)
/*  50:    */   {
/*  51: 46 */     if (guibutton.displayString == this.buttonOK.displayString)
/*  52:    */     {
/*  53: 48 */       int sx = 15;
/*  54: 49 */       int sz = 15;
/*  55: 50 */       int sy = 15;
/*  56:    */       try
/*  57:    */       {
/*  58: 54 */         sx = Integer.parseInt(this.textboxSX.getText().trim());
/*  59: 55 */         sz = Integer.parseInt(this.textboxSY.getText().trim());
/*  60: 56 */         sy = Integer.parseInt(this.textboxHeight.getText().trim());
/*  61:    */       }
/*  62:    */       catch (Exception e) {}
/*  63: 63 */       sendPacket(this.ACTION_EXPORT);
/*  64:    */     }
/*  65: 65 */     else if (guibutton.displayString == this.buttonExit.displayString)
/*  66:    */     {
/*  67: 67 */       sendPacket(this.ACTION_UPDATE);
/*  68: 68 */       this.mc.displayGuiScreen(null);
/*  69:    */     }
/*  70: 70 */     else if (guibutton.displayString != this.buttonPaste.displayString) {}
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void sendPacket(byte action)
/*  74:    */   {
/*  75:    */     try
/*  76:    */     {
/*  77: 81 */       this.block.red = Integer.parseInt(this.textboxSX.getText().trim());
/*  78: 82 */       this.block.yellow = Integer.parseInt(this.textboxSY.getText().trim());
/*  79: 83 */       this.block.height = Integer.parseInt(this.textboxHeight.getText().trim());
/*  80:    */     }
/*  81:    */     catch (Exception e) {}
/*  82: 89 */     this.block.setName(this.textboxFile.getText());
/*  83: 90 */     ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
/*  84: 91 */     DataOutputStream outputStream = new DataOutputStream(bos);
/*  85:    */     try
/*  86:    */     {
/*  87: 95 */       outputStream.writeByte(0);
/*  88: 96 */       outputStream.writeInt(this.posX);
/*  89: 97 */       outputStream.writeInt(this.posY);
/*  90: 98 */       outputStream.writeInt(this.posZ);
/*  91: 99 */       outputStream.writeInt(this.block.red);
/*  92:100 */       outputStream.writeInt(this.block.yellow);
/*  93:101 */       outputStream.writeInt(this.block.height);
/*  94:102 */       outputStream.writeUTF(this.block.name);
/*  95:    */     }
/*  96:    */     catch (Exception ex)
/*  97:    */     {
/*  98:106 */       ex.printStackTrace();
/*  99:    */     }
/* 100:110 */     ChocolateQuest.channel.sendPaquetToServer(new PacketEditorGUIClose(this.posX, this.posY, this.posZ, this.block.red, this.block.yellow, this.block.height, this.block.name, action));
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void initGui()
/* 104:    */   {
/* 105:116 */     super.initGui();
/* 106:117 */     this.buttonList.clear();
/* 107:118 */     this.buttonOK = new GuiButton(this.width / 2, 50, 40, "Export");
/* 108:119 */     this.buttonList.add(this.buttonOK);
/* 109:120 */     this.buttonPaste = new GuiButton(this.width / 2, 60, 200, "Copy template(care, can overwrite your buildings)");
/* 110:121 */     this.buttonList.add(this.buttonPaste);
/* 111:122 */     this.buttonExit = new GuiButton(this.width / 2, 50, 230, "Close");
/* 112:123 */     this.buttonList.add(this.buttonExit);
/* 113:124 */     this.textboxSX = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, 70, 20, 20);
/* 114:125 */     this.textboxSX.setText("" + this.x);
/* 115:126 */     this.textboxSX.setFocused(true);
/* 116:127 */     this.textboxSX.setMaxStringLength(3);
/* 117:128 */     this.textboxSY = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, 100, 20, 20);
/* 118:129 */     this.textboxSY.setText("" + this.z);
/* 119:130 */     this.textboxSY.setMaxStringLength(3);
/* 120:131 */     this.textboxHeight = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, 130, 20, 20);
/* 121:132 */     this.textboxHeight.setText("" + this.y);
/* 122:133 */     this.textboxHeight.setMaxStringLength(3);
/* 123:134 */     this.textboxFile = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, 160, 130, 20);
/* 124:135 */     this.textboxFile.setText(this.block.getName());
/* 125:136 */     this.textboxFile.setMaxStringLength(20);
/* 126:    */   }
/* 127:    */   
/* 128:    */   public void drawScreen(int i, int j, float f)
/* 129:    */   {
/* 130:142 */     drawDefaultBackground();
/* 131:143 */     super.drawScreen(i, j, f);
/* 132:144 */     drawString(this.fontRendererObj, "Size red", this.width / 2 - 160, 70, 16711680);
/* 133:145 */     drawString(this.fontRendererObj, "Size yellow", this.width / 2 - 160, 100, 16776960);
/* 134:146 */     drawString(this.fontRendererObj, "Height", this.width / 2 - 160, 130, 16777215);
/* 135:147 */     drawString(this.fontRendererObj, "File name", this.width / 2 - 160, 160, 16777215);
/* 136:148 */     this.textboxSX.drawTextBox();
/* 137:149 */     this.textboxSY.drawTextBox();
/* 138:150 */     this.textboxHeight.drawTextBox();
/* 139:151 */     this.textboxFile.drawTextBox();
/* 140:152 */     super.drawScreen(i, j, f);
/* 141:    */   }
/* 142:    */   
/* 143:    */   protected void keyTyped(char c, int i)
/* 144:    */   {
/* 145:157 */     if (this.textboxSX.isFocused()) {
/* 146:159 */       this.textboxSX.textboxKeyTyped(c, i);
/* 147:    */     }
/* 148:162 */     if (this.textboxSY.isFocused()) {
/* 149:164 */       this.textboxSY.textboxKeyTyped(c, i);
/* 150:    */     }
/* 151:167 */     if (this.textboxHeight.isFocused()) {
/* 152:169 */       this.textboxHeight.textboxKeyTyped(c, i);
/* 153:    */     }
/* 154:172 */     if (this.textboxFile.isFocused()) {
/* 155:174 */       this.textboxFile.textboxKeyTyped(c, i);
/* 156:    */     }
/* 157:    */   }
/* 158:    */   
/* 159:    */   private void setFalse()
/* 160:    */   {
/* 161:180 */     this.textboxSX.setFocused(false);
/* 162:181 */     this.textboxSY.setFocused(false);
/* 163:182 */     this.textboxHeight.setFocused(false);
/* 164:183 */     this.textboxFile.setFocused(false);
/* 165:    */   }
/* 166:    */   
/* 167:    */   protected void mouseClicked(int i, int j, int k)
/* 168:    */   {
/* 169:188 */     super.mouseClicked(i, j, k);
/* 170:190 */     if ((j > 70) && (j < 90))
/* 171:    */     {
/* 172:192 */       setFalse();
/* 173:193 */       this.textboxSX.setFocused(true);
/* 174:    */     }
/* 175:196 */     if ((j > 100) && (j < 120))
/* 176:    */     {
/* 177:198 */       setFalse();
/* 178:199 */       this.textboxSY.setFocused(true);
/* 179:    */     }
/* 180:202 */     if ((j > 130) && (j < 150))
/* 181:    */     {
/* 182:204 */       setFalse();
/* 183:205 */       this.textboxHeight.setFocused(true);
/* 184:    */     }
/* 185:208 */     if ((j > 170) && (j < 190))
/* 186:    */     {
/* 187:210 */       setFalse();
/* 188:211 */       this.textboxFile.setFocused(true);
/* 189:    */     }
/* 190:    */   }
/* 191:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.gui.GuiEditor
 * JD-Core Version:    0.7.1
 */