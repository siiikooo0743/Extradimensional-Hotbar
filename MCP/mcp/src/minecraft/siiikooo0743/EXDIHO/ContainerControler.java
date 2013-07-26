package siiikooo0743.EXDIHO;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerControler extends Container
{
    private int numRows;
    private IInventory temp;

    public ContainerControler(IInventory par1Inventory, TileEntityControler par2TileEntity)
    {
    	// par1 Player
    	// par2 TileEntity
    	numRows = par2TileEntity.getSizeInventory() / 9;
    	
    	for(int i = 0; i < numRows; i++)
        {
    		for(int j = 0; j < 9; j++)
            {
    			temp.setInventorySlotContents(0, par2TileEntity.getStackInSlot(j * i));
    			this.addSlotToContainer(new Slot(temp, i * j, 8 + j * 18, 8 + i * 18));
            }
        }
    	
    	for (int j = 0; j < 3; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(par1Inventory, k + j * 9 + 9, 8 + k * 18, 12 + numRows * 18 + j * 18));
            }
        }

        for (int j = 0; j < 9; ++j)
        {
            this.addSlotToContainer(new Slot(par1Inventory, j, 8 + j * 18, 70 + numRows * 18));
        }
         temp = par2TileEntity;
    }
    
    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
    	return true;
        //return this.temp.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < this.numRows * 9)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
        super.onContainerClosed(par1EntityPlayer);
        this.temp.closeChest();
    }

    /**
     * Return this chest container's lower chest inventory.
     */
    public IInventory getLowerChestInventory()
    {
        return this.temp;
    }
}
