package siiikooo0743.EXDIHO;

import siiikooo0743.EXDIHO.lib.Reference;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ControlerItemBlock extends ItemBlock
{
	private static final String[] subNames = {"CPU", "Housing", "Player Inventory Injector", "KeyPressed Detector", "Antenna Socket", "Antenna", "EXDIHO wallTexture", "EXDIHO unused", "EXDIHO unused", "EXDIHO unused", "EXDIHO unused", "EXDIHO unused", "EXDIHO unused", "EXDIHO unused", "EXDIHO unused", "EXDIHO filling"};

	public ControlerItemBlock(int id) 
	{
		super(id);
		setHasSubtypes(true);
		this.setUnlocalizedName("controler");
	}
	

	@Override
	public int getMetadata (int damageValue) 
	{
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return Reference.controlerSubNames[itemstack.getItemDamage()];
	}
}
