package siiikooo0743.EXDIHO;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

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
import net.minecraft.world.World;

public class TileEntityControler extends TileEntity implements IInventory
{
	private ItemStack[] content;
	private int size;
	
	public TileEntityControler(World par1World)
	{
		this.setWorldObj(par1World);
		size = this.getBlockMetadata() * 9;
		content = new ItemStack[size];
		Minecraft.getMinecraft().theWorld.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public TileEntityControler()
	{

	}
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		NBTTagCompound tag = pkt.customParam1;
		this.readFromNBT(tag);
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1)
	{
	   super.writeToNBT(par1);
	   
	   
	   NBTTagCompound NBTSize = new NBTTagCompound();
	   NBTSize.setInteger("size",size);
	   par1.setTag("size", NBTSize);
	   
	   NBTTagList nbttaglist = new NBTTagList();
	   
       for (int i = 0; i < this.content.length; ++i)
       {
           if (this.content[i] != null)
           {
               NBTTagCompound nbttagcompound1 = new NBTTagCompound();
               nbttagcompound1.setByte("Slot", (byte)i);
               this.content[i].writeToNBT(nbttagcompound1);
               nbttaglist.appendTag(nbttagcompound1);
           }
       }

       par1.setTag("Items", nbttaglist);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1)
	{
	   super.readFromNBT(par1);
	   
	   NBTTagCompound NBTSize = par1.getCompoundTag("size");
	   size = NBTSize.getInteger("size");
	   
	   NBTTagList nbttaglist = par1.getTagList("Items");
       this.content = new ItemStack[this.getSizeInventory()];
       
       for (int i = 0; i < nbttaglist.tagCount(); ++i)
       {
           NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
           int j = nbttagcompound1.getByte("Slot") & 255;

           if (j >= 0 && j < this.content.length)
           {
               this.content[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
           }
       }
	}
	
	@Override
	public int getSizeInventory() 
	{
		return size;
	}

	@Override
	public ItemStack getStackInSlot(int i) 
	{
		return content[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) 
	{
		if (this.content[i] != null)
        {
            ItemStack itemstack;

            if (this.content[i].stackSize <= j)
            {
                itemstack = this.content[i];
                this.content[i] = null;
                this.onInventoryChanged();
                return itemstack;
            }
            else
            {
                itemstack = this.content[i].splitStack(j);

                if (this.content[i].stackSize == 0)
                {
                    this.content[i] = null;
                }

                this.onInventoryChanged();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) 
	{
        if (this.content[i] != null)
        {
            ItemStack itemstack = this.content[i];
            this.content[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) 
	{
		this.content[i] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
	}

	@Override
	public String getInvName() 
	{
		return "Controler";
	}

	@Override
	public boolean isInvNameLocalized() 
	{
		 return true;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer) 
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() 
	{
		
	}

	@Override
	public void closeChest() 
	{
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) 
	{
		return true;
	}

}
