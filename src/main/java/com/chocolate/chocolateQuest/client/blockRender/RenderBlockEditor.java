/*   1:    */ package com.chocolate.chocolateQuest.client.blockRender;
/*   2:    */ 
/*   3:    */ import com.chocolate.chocolateQuest.block.BlockEditorTileEntity;
/*   4:    */ import net.minecraft.client.renderer.Tessellator;
/*   5:    */ import net.minecraft.client.renderer.texture.TextureMap;
/*   6:    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*   7:    */ import net.minecraft.tileentity.TileEntity;
/*   8:    */ import org.lwjgl.opengl.GL11;
/*   9:    */ 
/*  10:    */ public class RenderBlockEditor
/*  11:    */   extends TileEntitySpecialRenderer
/*  12:    */ {
/*  13:    */   public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
/*  14:    */   {
/*  15: 30 */     BlockEditorTileEntity eb = (BlockEditorTileEntity)tileentity;
/*  16: 31 */     Tessellator tessellator = Tessellator.instance;
/*  17:    */     
/*  18:    */ 
/*  19:    */ 
/*  20:    */ 
/*  21:    */ 
/*  22:    */ 
/*  23:    */ 
/*  24:    */ 
/*  25:    */ 
/*  26:    */ 
/*  27:    */ 
/*  28:    */ 
/*  29:    */ 
/*  30:    */ 
/*  31:    */ 
/*  32:    */ 
/*  33:    */ 
/*  34:    */ 
/*  35:    */ 
/*  36:    */ 
/*  37:    */ 
/*  38:    */ 
/*  39:    */ 
/*  40:    */ 
/*  41:    */ 
/*  42:    */ 
/*  43:    */ 
/*  44:    */ 
/*  45:    */ 
/*  46:    */ 
/*  47:    */ 
/*  48:    */ 
/*  49:    */ 
/*  50:    */ 
/*  51:    */ 
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55:    */ 
/*  56:    */ 
/*  57:    */ 
/*  58:    */ 
/*  59:    */ 
/*  60:    */ 
/*  61:    */ 
/*  62:    */ 
/*  63:    */ 
/*  64:    */ 
/*  65:    */ 
/*  66:    */ 
/*  67:    */ 
/*  68:    */ 
/*  69:    */ 
/*  70:    */ 
/*  71:    */ 
/*  72: 87 */     GL11.glDisable(2884);
/*  73: 88 */     bindTexture(TextureMap.locationBlocksTexture);
/*  74:    */     
/*  75:    */ 
/*  76:    */ 
/*  77:    */ 
/*  78:    */ 
/*  79:    */ 
/*  80:    */ 
/*  81:    */ 
/*  82:    */ 
/*  83:    */ 
/*  84:    */ 
/*  85:    */ 
/*  86:    */ 
/*  87:    */ 
/*  88:    */ 
/*  89:    */ 
/*  90:    */ 
/*  91:    */ 
/*  92:    */ 
/*  93:    */ 
/*  94:    */ 
/*  95:    */ 
/*  96:    */ 
/*  97:    */ 
/*  98:    */ 
/*  99:    */ 
/* 100:    */ 
/* 101:    */ 
/* 102:    */ 
/* 103:    */ 
/* 104:    */ 
/* 105:    */ 
/* 106:    */ 
/* 107:    */ 
/* 108:    */ 
/* 109:    */ 
/* 110:    */ 
/* 111:    */ 
/* 112:    */ 
/* 113:    */ 
/* 114:    */ 
/* 115:    */ 
/* 116:    */ 
/* 117:    */ 
/* 118:    */ 
/* 119:    */ 
/* 120:    */ 
/* 121:    */ 
/* 122:    */ 
/* 123:138 */     GL11.glDisable(3553);
/* 124:139 */     GL11.glDisable(2896);
/* 125:140 */     tessellator.startDrawing(3);
/* 126:141 */     tessellator.setColorOpaque_I(16733525);
/* 127:142 */     tessellator.addVertex(x + 0.5D, y + 0.5D, z + 0.5D);
/* 128:143 */     tessellator.addVertex(x + eb.red + 1.5D, y + 0.5D, z + 0.5D);
/* 129:144 */     tessellator.draw();
/* 130:145 */     tessellator.startDrawing(3);
/* 131:146 */     tessellator.setColorOpaque_I(16777184);
/* 132:147 */     tessellator.addVertex(x + 0.5D, y + 0.5D, z + 0.5D);
/* 133:148 */     tessellator.addVertex(x + 0.5D, y + 0.5D, z + 1.5D + eb.yellow);
/* 134:149 */     tessellator.draw();
/* 135:150 */     tessellator.startDrawing(3);
/* 136:151 */     tessellator.setColorOpaque_I(16755370);
/* 137:152 */     tessellator.addVertex(x + 0.5D, y + 0.5D, z + 0.5D);
/* 138:153 */     tessellator.addVertex(x + 0.5D, y + 0.5D + eb.height, z + 0.5D);
/* 139:154 */     GL11.glColor3f(1.0F, 1.0F, 0.8F);
/* 140:155 */     tessellator.draw();
/* 141:156 */     tessellator.startDrawing(3);
/* 142:157 */     tessellator.setColorOpaque_I(16777184);
/* 143:158 */     tessellator.addVertex(x + eb.red + 1.5D, y + eb.height + 0.5D, z + eb.yellow + 1.5D);
/* 144:159 */     tessellator.addVertex(x + 0.5D, y + eb.height + 0.5D, z + eb.yellow + 1.5D);
/* 145:160 */     tessellator.draw();
/* 146:161 */     tessellator.startDrawing(3);
/* 147:162 */     tessellator.setColorOpaque_I(16733525);
/* 148:163 */     tessellator.addVertex(x + eb.red + 1.5D, y + eb.height + 0.5D, z + eb.yellow + 1.5D);
/* 149:164 */     tessellator.addVertex(x + eb.red + 1.5D, y + eb.height + 0.5D, z + 0.5D);
/* 150:165 */     tessellator.draw();
/* 151:166 */     tessellator.startDrawing(3);
/* 152:167 */     tessellator.setColorOpaque_I(16755370);
/* 153:168 */     tessellator.addVertex(x + eb.red + 1.5D, y + eb.height + 0.5D, z + eb.yellow + 1.5D);
/* 154:169 */     tessellator.addVertex(x + eb.red + 1.5D, y + 0.5D, z + eb.yellow + 1.5D);
/* 155:170 */     tessellator.draw();
/* 156:171 */     tessellator.startDrawing(3);
/* 157:172 */     tessellator.setColorOpaque_I(16733525);
/* 158:173 */     tessellator.addVertex(x + 0.5D, y + eb.height + 0.5D, z + 0.5D);
/* 159:174 */     tessellator.addVertex(x + eb.red + 1.5D, y + eb.height + 0.5D, z + 0.5D);
/* 160:175 */     tessellator.draw();
/* 161:176 */     tessellator.startDrawing(3);
/* 162:177 */     tessellator.setColorOpaque_I(16777184);
/* 163:178 */     tessellator.addVertex(x + 0.5D, y + 0.5D + eb.height, z + 0.5D);
/* 164:179 */     tessellator.addVertex(x + 0.5D, y + 0.5D + eb.height, z + 1.5D + eb.yellow);
/* 165:180 */     tessellator.draw();
/* 166:181 */     tessellator.startDrawing(3);
/* 167:182 */     tessellator.setColorOpaque_I(16733525);
/* 168:183 */     tessellator.addVertex(x + eb.red + 1.5D, y + 0.5D, z + 0.5D);
/* 169:184 */     tessellator.addVertex(x + eb.red + 1.5D, y + eb.height + 0.5D, z + 0.5D);
/* 170:185 */     tessellator.draw();
/* 171:186 */     tessellator.startDrawing(3);
/* 172:187 */     tessellator.setColorOpaque_I(16777184);
/* 173:188 */     tessellator.addVertex(x + 0.5D, y + 0.5D, z + 1.5D + eb.yellow);
/* 174:189 */     tessellator.addVertex(x + 0.5D, y + 0.5D + eb.height, z + 1.5D + eb.yellow);
/* 175:190 */     tessellator.draw();
/* 176:191 */     tessellator.startDrawing(3);
/* 177:192 */     tessellator.setColorOpaque_I(16777184);
/* 178:193 */     tessellator.addVertex(x + eb.red + 1.5D, y + 0.5D, z + eb.yellow + 1.5D);
/* 179:194 */     tessellator.addVertex(x + 0.5D, y + 0.5D, z + eb.yellow + 1.5D);
/* 180:195 */     tessellator.draw();
/* 181:196 */     tessellator.startDrawing(3);
/* 182:197 */     tessellator.setColorOpaque_I(16733525);
/* 183:198 */     tessellator.addVertex(x + eb.red + 0.5D + 1.0D, y + 0.5D, z + eb.yellow + 0.5D + 1.0D);
/* 184:199 */     tessellator.addVertex(x + eb.red + 0.5D + 1.0D, y + 0.5D, z + 0.5D);
/* 185:200 */     tessellator.draw();
/* 186:201 */     GL11.glEnable(2896);
/* 187:202 */     GL11.glEnable(3553);
/* 188:    */   }
/* 189:    */   
/* 190:    */   public void renderPart() {}
/* 191:    */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.client.blockRender.RenderBlockEditor
 * JD-Core Version:    0.7.1
 */