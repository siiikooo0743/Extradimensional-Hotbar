package siiikooo0743.EXDIHO;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import siiikooo0743.EXDIHO.lib.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class TileEntityControler extends TileEntity
{
	
	
	private int size= -1;
	
	//CPU
	
	boolean valid;
	
	public int bottom;
	public int top;
	public int north; //z decreases
	public int east;  //x increases
	public int south; //z increases
	public int west;  //x decreases
	
	public Position pII = null;
	public Position kPD = null;
	public Position aS  = null;
	
	public short chestCount;
	
	
	List<Position> walls = new ArrayList<Position>();
	List<Position> fill = new ArrayList<Position>();
	
	
	//Housing
	
	//Player Inventory Injector
	
	public int[] itemPositions = new int[9];
	
	//KeyPressed Detector
	public int rotation;
	
	//Antenna Socket
	Position antennaTop = null;
	Position antennaBottom = null;
	
	//Multiple
	Position pos = null;
	Position cpu = null;
	Position tempPos;	
	TileEntityControler tempTile;
	int tickCount = 1;
	boolean pressed = false;
	
	public boolean formed = false;
	public boolean forcedTick = false;
	
	public EntityPlayer player;
	
	public int[] itemPositionsChanges = new int[9];
	public Position[] chests = new Position[3];
	
	
	
	
	
	@Override
	public void updateEntity()
	{
		
		this.setWorldObj(Minecraft.getMinecraft().theWorld);
		pos = new Position(xCoord, yCoord, zCoord);
		switch (this.blockMetadata) 
		{
        case 0:  
        //CPU
        	
        	//Forming
        	if(tickCount >= 9 || forcedTick)
        	{        		
	        	if(!formed)
	        	{
		        	tempPos = new Position(this.pos.x, this.pos.y, zCoord);
		        	        	
		        	while(true)
		        	{
		        		tempPos.y -= 1;
		        		if(tempPos.y + 7 < yCoord)
		        		{
		        			return;
		        		}
		        		
		        		if((worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID) && (worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 1))
		        		{
		        			break;
		        		}
		        			
		        	}
		        	tempPos.y ++;
		        	bottom = tempPos.y;
		        	
		        	while(worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID)
		        	{
		        		tempPos.z -= 1;
		        	}
		        	tempPos.z ++;
		        	north = tempPos.z;
		        	
		        	while(worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID)
		        	{
		        		tempPos.x += 1;
		        	}
		        	tempPos.x --;
		        	east = tempPos.x;
		        	
		        	while(worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID)
		        	{
		        		tempPos.z += 1;
		        	}
		        	tempPos.z --;
		        	south = tempPos.z;
		        	
		        	while(worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID)
		        	{
		        		tempPos.x -= 1;
		        	}
		        	tempPos.x ++;
		        	west = tempPos.x;
		        	
		        	while(worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID)
		        	{
		        		tempPos.y += 1;
		        	}
		        	tempPos.y --;
		        	top = tempPos.y;
		        	
	        	}
	        	
	        	valid = true;
	        	
	        	if(Math.abs(north - south) > 7)
	        	{
	        		valid = false;
	        	}
	        	if(Math.abs(east - west) > 7)
	        	{
	        		valid = false;
	        	}
	        	if(Math.abs(top - bottom) > 7)
	        	{
	        		valid = false;
	        	}
	        	
	        	
	        	
	        	//Bottom
	        	tempPos = new Position(west, bottom, north);
	        	
	        	while(tempPos.x <= east && valid)
	        	{
	        		while(tempPos.z <= south && valid)
	        		{
	        			if(!((worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID) && ((worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 1) || (worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 6))))
	        			{
	        				valid = false;
	        			}
	        			else
	        			{
	        				walls.add(tempPos);
	        			}
	        			tempPos.z ++;
	        		}
	        		tempPos.x ++;
	        	}
	        	
	        	//Top
	        	tempPos = new Position(west, top, north);
	        	
	        	while(tempPos.x <= east && valid)
	        	{
	        		while(tempPos.z <= south && valid)
	        		{
	        			if(!((worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID) && ((worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 1) || (worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 4) || (worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 6))))
	        			{
	        				valid = false;
	        			}
	        			else
	        			{
	        				walls.add(tempPos);
	        			}
	        			
	        			if((worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID) && (worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 4))
	        			{
	        				if(aS != null)
	        				{
	        					valid = false;
	        				}
	        				else
	        				{
	        					aS = tempPos;
	        				}
	        					
	        			}
	
	        			tempPos.z ++;
	        		}
	        		tempPos.x ++;
	        	}
	        	
	        	//North
	        	tempPos = new Position(west, bottom, north);
	        	
	        	while(tempPos.x <= east && valid)
	        	{
	        		while(tempPos.y <= top && valid)
	        		{
	        			if(!((worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID) && ((worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 1) || (worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 6))))
	        			{
	        				valid = false;
	        			}
	        			else
	        			{
	        				walls.add(tempPos);
	        			}
	        			tempPos.y ++;
	        		}
	        		tempPos.x ++;
	        	}
	        	
	        	//East
	        	tempPos = new Position(east, bottom, north);
	        	
	        	while(tempPos.z <= south && valid)
	        	{
	        		while(tempPos.y <= top && valid)
	        		{
	        			if(!((worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID) && ((worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 1) || (worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 6))))
		        		{
	        				valid = false;
	        			}
	        			else
	        			{
	        				walls.add(tempPos);
	        			}
	        			tempPos.y ++;
	        		}
	        		tempPos.z ++;
	        	}
	        	
	        	//South
	        	tempPos = new Position(west, bottom, south);
	        	
	        	while(tempPos.x <= east && valid)
	        	{
	        		while(tempPos.y <= top && valid)
	        		{
	        			if(!((worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID) && ((worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 1) || (worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 6))))
		        		{
	        				valid = false;
	        			}
	        			else
	        			{
	        				walls.add(tempPos);
	        			}
	        			tempPos.y ++;
	        		}
	        		tempPos.x ++;
	        	}
	        	
	        	//West
	        	tempPos = new Position(west, bottom, north);
	        	
	        	while(tempPos.z <= south && valid)
	        	{
	        		while(tempPos.y <= top && valid)
	        		{
	        			if(!((worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID) && ((worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 1) || (worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 6))))
		        		{
	        				valid = false;
	        			}
	        			else
	        			{
	        				walls.add(tempPos);
	        			}
	        			tempPos.y ++;
	        		}
	        		tempPos.z ++;
	        	}
	        	
	        	tempPos = new Position(west + 1, bottom + 1, north + 1);
	        	
	        	while(tempPos.x <= east && valid)
	        	{
	        		while(tempPos.z <= south && valid)
	            	{
	            		while(tempPos.y <= top && valid)
	            		{
	            			if((worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID) && (worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 2))
	            			{
	            				if(pII != null)
	            				{
	            					valid = false;
	            				}
	            				else
	            				{
	            					pII = tempPos;
	            				}
	            				
	            			}
	            			
	            			if((worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID) && (worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 3))
	            			{
	            				if(kPD != null)
	            				{
	            					valid = false;
	            				}
	            				else
	            				{
	            					kPD = tempPos;
	            				}
	            				
	            			}
	            			
	            			if(worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == 54)
	            			{
	            				if(chestCount <= 3)
	            				{
	            					chests[chestCount] = tempPos;
	            					chestCount ++;
	            				}
	            				else
	            				{
	            					valid = false;
	            				}
	            				
	            			}
	            			
	            			if((worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID) && (worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 3) && !(tempPos.equals(pos)))
	            			{
	            				valid = false;
	            			}
	            			
	            			if(pII.equals(null) || kPD.equals(null) || aS.equals(null) || chestCount < 1)
	            			{
	            				valid = false;
	            			}
	            			
	            			if(worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == 0)
	            			{
	            				fill.add(tempPos);
	            			}
	            				
	            			
	            			tempPos.y ++;
	            		}
	            		tempPos.z ++;
	            	}
	        		tempPos.x ++;
	        	}
	        	
	        	if(valid)
				{
					cpuForm();
				}
				else
				{
					cpuDeform();
				}
        	}

        	break;
        	
        case 1:  
        //Housing
        	if(tickCount >= 9 || forcedTick)
        	{
	        	if(formed)
	        	{
	        		if(!((this.worldObj.getBlockId(cpu.x, cpu.y, cpu.z) == Reference.BLOCK_ID) && (this.worldObj.getBlockMetadata(cpu.x, cpu.y, cpu.z) == 0)))
	        		{
	        			this.deform();
	        		}
	        		
	        		tempTile = (TileEntityControler)this.worldObj.getBlockTileEntity(cpu.x, cpu.y, cpu.z);
	        		
	        		if(this.pos.y == tempTile.top - 1)
	        		{
	        			this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 6, 2);
	        		}
	        		
	        	}
	        	else
	        	{
	        		//TODO Change to original Texture
	        	}
        	}
        	break;
        	
        case 2:  
        //Player Inventory Injector
        	if(tickCount >= 9 || forcedTick)
	        {
	        	if(formed)
	        	{
	        		if(!((this.worldObj.getBlockId(cpu.x, cpu.y, cpu.z) == Reference.BLOCK_ID) && (this.worldObj.getBlockMetadata(cpu.x, cpu.y, cpu.z) == 0)))
	        		{
	        			this.deform();
	        		}
	        		
	        		if(pressed)
	        		{
	        			pressed = false;
	        			
	        			tempTile = (TileEntityControler) this.worldObj.getBlockTileEntity(cpu.x, cpu.y, cpu.z);
	        			tempPos = tempTile.aS;
	        			tempTile = (TileEntityControler)this.worldObj.getBlockTileEntity(tempPos.x, tempPos.y, tempPos.z);
	        			if(tempTile.check(player))
	        			{
	        				tempTile = (TileEntityControler) this.worldObj.getBlockTileEntity(cpu.x, cpu.y, cpu.z);
	        				chests = new Position[tempTile.chestCount];
	        				chests = tempTile.chests;
	        				
	        				int oldPos;
	        				
	        				for(int ix = 0; ix < 9; ix++)
	        				{
	        					if(itemPositionsChanges[ix] != 0)
	        					{
	        						oldPos = itemPositions[ix];
	        						if(itemPositions[ix] + itemPositionsChanges[ix] >= chests.length * 3)
	        						{
	        							itemPositions[ix] = 0;
	        						}
	        						else if(itemPositions[ix] + itemPositionsChanges[ix] < 0)
	        						{
	        							itemPositions[ix] = chests.length * 3 - 1;
	        						}
	        						else
	        						{
	        							itemPositions[ix] += itemPositionsChanges[ix];
	        						}
	        						
	        						player.inventory.setInventorySlotContents(ix, setSlot(ix, itemPositions[ix], oldPos, player.inventory.getStackInSlot(ix)));
	        					}
	        				}
	        			}
	        		}
	        		
	        	}
	        	else
	        	{
	        		pressed = false;
	        	}
        	}
        	break;
        	
        case 3:  
        //KeyPressed Detector
        	if(tickCount >= 9 || forcedTick)
        	{
	        	if(formed)
	        	{
	        		if(!((this.worldObj.getBlockId(cpu.x, cpu.y, cpu.z) == Reference.BLOCK_ID) && (this.worldObj.getBlockMetadata(cpu.x, cpu.y, cpu.z) == 0)))
	        		{
	        			this.deform();
	        		}
	        	}
	        	else
	        	{
	        		if(pressed)
	        		{
	        			player.addChatMessage("Extradimensional Hotbar Controller not complete!");
	        		}
	        		pressed = false;
	        	}
        	}
        	
        	if(formed && pressed)
        	{
        		rotation = Mouse.getDWheel();
        		
        		
        		
        		if(rotation != 0)
        		{
        			if(player.isSneaking())
    				{
    					itemPositionsChanges[player.inventory.currentItem] += rotation;
    				}
    				else
    				{
        				for(int i = 0; i < 9; i++)
        				{
        					itemPositionsChanges[i] += rotation;
        				}
    				}
        			
        			/*
        			if(player.openContainer != player.inventoryContainer)
        			{
        				for(int i = 0; i < 9; i++)
        				{
        					itemPositionsChanges[i] += rotation;
        				}
        			}
        			else
        			{
        				if(player.isSneaking())
        				{
        					itemPositionsChanges[player.inventory.currentItem] += rotation;
        				}
        				else
        				{
	        				for(int i = 0; i < 9; i++)
	        				{
	        					itemPositionsChanges[i] += rotation;
	        				}
        				}
        			}
        			*/
        			tempTile = (TileEntityControler) this.worldObj.getBlockTileEntity(cpu.x, cpu.y, cpu.z);
        			tempPos = tempTile.pII;
        			tempTile = (TileEntityControler)this.worldObj.getBlockTileEntity(tempPos.x, tempPos.y, tempPos.z);
        			tempTile.keyPressed(player, itemPositionsChanges);
        			this.worldObj.setBlockTileEntity(tempPos.x, tempPos.y, tempPos.z, tempTile);
        		}
        	}
        		
        		
        	break;
        	
        case 4:  
        //Antenna Socket
        	if(tickCount >= 9 || forcedTick)
        	{
	        	if(formed)
	        	{
	        		if(!((this.worldObj.getBlockId(cpu.x, cpu.y, cpu.z) == Reference.BLOCK_ID) && (this.worldObj.getBlockMetadata(cpu.x, cpu.y, cpu.z) == 0)))
	        		{
	        			this.deform();
	        		}
	        		
	        		tempPos = new Position(this.xCoord ,this.yCoord + 1, this.zCoord);
	        		antennaBottom = tempPos;
	        		
	        		while((this.worldObj.getBlockId(tempPos.x, tempPos.y, tempPos.z) == Reference.BLOCK_ID) && (this.worldObj.getBlockMetadata(tempPos.x, tempPos.y, tempPos.z) == 5))
	        		{
	        			tempPos.y += 1;
	        		}
	        		
	        		tempPos.y --;
	        		antennaTop = tempPos;
	        		
	        	}
	        	else
	        	{
	        		antennaTop = null;
	        		antennaBottom = null;
	        	}
        	}
        	
        	break;  
        case 6:  
            //Wall Texture
        	if(tickCount >= 9 || forcedTick)
        	{
	        	if(formed)
	        	{
	        		if(!((this.worldObj.getBlockId(cpu.x, cpu.y, cpu.z) == Reference.BLOCK_ID) && (this.worldObj.getBlockMetadata(cpu.x, cpu.y, cpu.z) == 0)))
	        		{
	        			this.deform();
	        		}
	        	}
	        	else
	        	{
	        		this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 1, 2);
	        	}
        	}
        	
        	break;
        default:
        	
        	if(formed)
        	{
        		if(!((this.worldObj.getBlockId(cpu.x, cpu.y, cpu.z) == Reference.BLOCK_ID) && (this.worldObj.getBlockMetadata(cpu.x, cpu.y, cpu.z) == 0)))
        		{
        			this.deform();
        		}
        		
        		
        	}
        	else
        	{
        		
        	}
        	
        	break;
		}
		
		tickCount ++;
		if(tickCount > 9)
		{
			tickCount = 1;
		}
		
		if(forcedTick)
		{
			forcedTick = false;
		}
	}
	
	public void cpuForm()
	{
		
		for(int ix = 0; ix < walls.size(); ix ++)
		{
			tempTile = (TileEntityControler) this.worldObj.getBlockTileEntity(walls.get(ix).x, walls.get(ix).y, walls.get(ix).z);
			tempTile.form(this.pos);
			tempTile.forcedTick = true;
			this.worldObj.setBlockTileEntity(walls.get(ix).x, walls.get(ix).y, walls.get(ix).z, tempTile);
		}
		
		tempTile = (TileEntityControler) this.worldObj.getBlockTileEntity(pII.x, pII.y, pII.z);
		tempTile.form(this.pos);
		tempTile.forcedTick = true;
		this.worldObj.setBlockTileEntity(pII.x, pII.y, pII.z, tempTile);

		tempTile = (TileEntityControler) this.worldObj.getBlockTileEntity(kPD.x, kPD.y, kPD.z);
		tempTile.form(this.pos);
		tempTile.forcedTick = true;
		this.worldObj.setBlockTileEntity(kPD.x, kPD.y, kPD.z, tempTile);
		
		this.forcedTick = true;
		this.form(this.pos);
		
		for(int ix = 0; ix < fill.size(); ix ++)
		{
			this.worldObj.setBlock(fill.get(ix).x, fill.get(ix).y, fill.get(ix).z, Reference.BLOCK_ID , 15, 2);
		}
		
	}
	
	public void form(Position posCPU)
	{
		this.formed = true;
		this.cpu = posCPU;
		
	}
	
	public void cpuDeform()
	{
		if(this.formed)
		{
			TileEntityControler tempTile;
			for(int ix = 0; ix < walls.size(); ix ++)
			{
				tempTile = (TileEntityControler) this.worldObj.getBlockTileEntity(walls.get(ix).x, walls.get(ix).y, walls.get(ix).z);
				tempTile.deform();
				tempTile.forcedTick = true;
				this.worldObj.setBlockTileEntity(walls.get(ix).x, walls.get(ix).y, walls.get(ix).z, tempTile);
			}
			
			tempTile = (TileEntityControler) this.worldObj.getBlockTileEntity(pII.x, pII.y, pII.z);
			tempTile.deform();
			tempTile.forcedTick = true;
			this.worldObj.setBlockTileEntity(pII.x, pII.y, pII.z, tempTile);

			tempTile = (TileEntityControler) this.worldObj.getBlockTileEntity(kPD.x, kPD.y, kPD.z);
			tempTile.deform();
			tempTile.forcedTick = true;
			this.worldObj.setBlockTileEntity(kPD.x, kPD.y, kPD.z, tempTile);
		}
		
		this.forcedTick = true;
		this.deform();
		
		for(int ix = 0; ix < fill.size(); ix ++)
		{
			this.worldObj.setBlock(fill.get(ix).x, fill.get(ix).y, fill.get(ix).z, 0);
		}
		
		pII = null;
		kPD = null;
		aS  = null;
		
		chestCount = 0;
		chests = new Position[3];
		
		walls = new ArrayList<Position>();
		fill = new ArrayList<Position>();
		
	}
	
	public void deform()
	{
		this.formed = false;
		
		
		if(this.blockMetadata == 15)
		{
			worldObj.setBlock(this.pos.x, this.pos.y, this.pos.z, 0);
		}
	}
	
	public void keyPressed(EntityPlayer par1Player)
	{
		if(KeyBindings.KeyDown)
		{
			this.pressed = true;
			this.player = par1Player;
		}
		else
		{
			this.pressed = false;
		}
	}
	
	public void keyPressed(EntityPlayer par1Player, int[] par1ItemPositionsChanges)
	{
		this.forcedTick = true;
		this.pressed = true;
		this.player = par1Player;
		this.itemPositionsChanges = par1ItemPositionsChanges;
	}
	
	public boolean check(EntityPlayer par1Player)
	{
		int maxDist = Reference.ANTENNA_REACH * antennaTop.y - antennaBottom.y;
		double dist1 = (Math.abs(antennaTop.x - par1Player.posX)) + (Math.abs(antennaTop.y - par1Player.posY)) + (Math.abs(antennaTop.z - par1Player.posZ)); 
		double dist2 = (Math.abs(antennaBottom.x - par1Player.posX)) + (Math.abs(antennaBottom.y - par1Player.posY)) + (Math.abs(antennaBottom.z - par1Player.posZ));
		double dist = (Math.sqrt(dist1) + Math.sqrt(dist2));
		
		if(dist < maxDist)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public ItemStack setSlot(int slot, int x, int preX, ItemStack items)
	{
		TileEntityChest tC;
		if(slot < 3)
		{
			tC = (TileEntityChest) this.worldObj.getBlockTileEntity(chests[0].x, chests[0].y, chests[0].z);
		}
		else if(slot < 6)
		{
			tC = (TileEntityChest) this.worldObj.getBlockTileEntity(chests[1].x, chests[1].y, chests[1].z);
			slot -= 3;
		}
		else
		{
			tC = (TileEntityChest) this.worldObj.getBlockTileEntity(chests[2].x, chests[2].y, chests[2].z);
			slot -= 6;
		}
		
		tC.setInventorySlotContents(preX + (slot * 9), items);
		return tC.decrStackSize(x + (slot * 9), tC.getStackInSlot(x + (slot * 9)).stackSize);
	}
	
	public boolean canUpdate()
    {
        return true;
    }
	
	@Override
	public void writeToNBT(NBTTagCompound par1)
	{
	   super.writeToNBT(par1);
	   
	   //TODO: writeToNBT
	   
	   NBTTagCompound NBTSize = new NBTTagCompound();
	   NBTSize.setInteger("size",size);
	   par1.setTag("size", NBTSize);	   

	}

	@Override
	public void readFromNBT(NBTTagCompound par1)
	{
	   super.readFromNBT(par1);
	   
	   //TODO: readFromNBT
	   
	   NBTTagCompound NBTSize = par1.getCompoundTag("size");
	   size = NBTSize.getInteger("size");
	}
}