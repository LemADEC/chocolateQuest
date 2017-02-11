/*  1:   */ package com.chocolate.chocolateQuest.entity;
/*  2:   */ 
/*  3:   */ import com.mojang.authlib.GameProfile;
/*  4:   */ import java.util.UUID;
/*  5:   */ import net.minecraft.entity.player.EntityPlayer;
/*  6:   */ import net.minecraft.util.ChunkCoordinates;
/*  7:   */ import net.minecraft.util.IChatComponent;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ 
/* 10:   */ public class DummyChocolate
/* 11:   */   extends EntityPlayer
/* 12:   */ {
/* 13:   */   public DummyChocolate(World par1World)
/* 14:   */   {
/* 15:17 */     super(par1World, new GameProfile(new UUID(0L, 0L), "ArrgChocolate"));
/* 16:   */   }
/* 17:   */   
/* 18:   */   public boolean canCommandSenderUseCommand(int i, String s)
/* 19:   */   {
/* 20:22 */     return false;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public ChunkCoordinates getPlayerCoordinates()
/* 24:   */   {
/* 25:27 */     return null;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void addChatMessage(IChatComponent var1) {}
/* 29:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.entity.DummyChocolate
 * JD-Core Version:    0.7.1
 */