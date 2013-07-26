package siiikooo0743.EXDIHO;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockControler extends ItemBlockWithMetadata
{
	public ItemBlockControler(int id, Block block) 
	{
		super(id, block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public int getMetadata (int damageValue) 
	{
		return damageValue;
	}
	
	public String getItemNameIS(ItemStack is) //Get's the item incode name from an itemstack
	{
		return "controler";
	}

	
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		
		if(metadata < 1)
		{
			return false;
		}
		
		if (!world.setBlock(x, y, z, this.getBlockID(), metadata, 3))
	    {
			return false;
	    }
		
		if (world.getBlockId(x, y, z) == this.getBlockID())
	    {
	           Block.blocksList[this.getBlockID()].onBlockPlacedBy(world, x, y, z, player, stack);
	           Block.blocksList[this.getBlockID()].onPostBlockPlaced(world, x, y, z, metadata);
	    }

		
		return true;
	}
}