package siiikooo0743.EXDIHO;

import siiikooo0743.EXDIHO.KeyBindings;
import siiikooo0743.EXDIHO.lib.Reference;

import java.util.EnumSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class PlayerTickHandler implements ITickHandler
{
	private final EnumSet<TickType> ticksToGet;
	
	/*
	         * This Tick Handler will fire for whatever TickType's you construct and register it with.
	         */
	public PlayerTickHandler(EnumSet<TickType> ticksToGet)
	{
	         this.ticksToGet = ticksToGet;
	}
	/*
	         * I suggest putting all your tick Logic in EITHER of these, but staying in one
	         */
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
	         playerTick((EntityPlayer)tickData[0]);
	}
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
	}
	@Override
	public EnumSet<TickType> ticks()
	{
	         return ticksToGet;
	}
	@Override
	public String getLabel()
	{
	         return "EXDIHO TickHandler";
	}
	
	public static void playerTick(EntityPlayer player)
	{
		if(KeyBindings.KeyChanged)
		{
			KeyBindings.KeyChanged = false;
			int array[];
			NBTTagCompound tag = player.getEntityData();
			array = tag.getIntArray(Reference.PLAYER_TAG_NAME);
			TileEntity tile = null;
			
			if(array.length != 3)
			{
				player.addChatMessage("Can't find your KeyPressedDetecor!");
				player.addChatMessage("Replace it to be abel to use your Extradimensional Hotbar again");
				player.addChatMessage("No coord");
				
			}
			else if(! player.worldObj.blockHasTileEntity(array[0], array[1], array[2]))
			{
				player.addChatMessage("Can't find your KeyPressedDetecor!");
				player.addChatMessage("Replace it to be abel to use your Extradimensional Hotbar again");
				player.addChatMessage("No TileEntity");
			}
			else
			{
				tile = player.worldObj.getBlockTileEntity(array[0], array[1], array[2]);
				
				if(tile instanceof TileEntityControler)
				{
					TileEntityControler tileC = (TileEntityControler)tile;
	       		 	tileC.keyPressed(player);
	       		 	player.worldObj.setBlockTileEntity(array[0], array[1], array[2], tileC);
				}
				else
				{
					player.addChatMessage("Can't find your KeyPressedDetecor!");
					player.addChatMessage("Replace it to be abel to use your Extradimensional Hotbar again");
					player.addChatMessage("Wrong TileEntity");
				}
			}
			
			
		}
	}
}