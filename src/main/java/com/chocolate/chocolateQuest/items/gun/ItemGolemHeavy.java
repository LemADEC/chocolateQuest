/*  1:   */ package com.chocolate.chocolateQuest.items.gun;
/*  2:   */ 
/*  3:   */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMecha;
/*  4:   */ import com.chocolate.chocolateQuest.entity.npc.EntityGolemMechaHeavy;
/*  5:   */ import net.minecraft.entity.player.EntityPlayer;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ 
/*  8:   */ public class ItemGolemHeavy
/*  9:   */   extends ItemGolem
/* 10:   */ {
/* 11:   */   public ItemGolemHeavy()
/* 12:   */   {
/* 13:13 */     super("mechaElite");
/* 14:   */   }
/* 15:   */   
/* 16:   */   public EntityGolemMecha getGolem(World world, EntityPlayer entityPlayer)
/* 17:   */   {
/* 18:16 */     return new EntityGolemMechaHeavy(world, entityPlayer);
/* 19:   */   }
/* 20:   */ }


/* Location:           P:\robf.jar
 * Qualified Name:     com.chocolate.chocolateQuest.items.gun.ItemGolemHeavy
 * JD-Core Version:    0.7.1
 */