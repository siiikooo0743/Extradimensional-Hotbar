package siiikooo0743.EXDIHO;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import siiikooo0743.EXDIHO.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.entity.player.*;

public class BlockControler extends Block 
{
	@SideOnly(Side.CLIENT)
    private Icon[] icons = new Icon[9];

	public BlockControler (int id) 
	{
		super(id, Material.iron);
		setUnlocalizedName("controler");
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundMetalFootstep);
		func_111022_d("extradimensional-hotbar:texture");
		
	}
	
	
	@Override
	public int damageDropped (int metadata) 
	{
		if(metadata == 6)
		{
			return(1);
		}
		return metadata;
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) 
	{
		if(par1World.getBlockMetadata(par2, par3, par4) == 3)
		{
			EntityPlayer player = (EntityPlayer)par5EntityLivingBase;
			NBTTagCompound tag = player.getEntityData();
			int[] array = new int[3];
			array[0] = par2;
			array[1] = par3;
			array[2] = par4;
			
			tag.setIntArray(Reference.PLAYER_TAG_NAME, array);

		}
		
	}
	
	public boolean isOpaqueCube()
    {
        return true;
    }
	
	 @SideOnly(Side.CLIENT)

	  
	 public Icon getIcon(int side, int metadata)
	 {
		 
		 if(((metadata == 4) && (side == 1)))
		 {
			 return icons[7];
		 }
		 else if(((metadata == 5) && ((side == 0) || (side == 1))))
		 {
			 return icons[8];
		 }
		 else if(metadata == 4)
		 {
			 return icons[1];
		 }
		 else if(metadata < 7)
		 {
			 return icons[metadata];
		 }
		 return null;
	 }

     @SideOnly(Side.CLIENT)

   
     public void registerIcons(IconRegister par1IconRegister)
     {
    	 for(int ix = 0; ix < 7; ix++)
    	 {
    		 if(ix != 4)
    		 {
    			 this.icons[ix] = par1IconRegister.registerIcon("extradimensional-hotbar:" + Reference.controlerSubNames[ix].toLowerCase());
    		 }
    	 }
    	 this.icons[7] = par1IconRegister.registerIcon("extradimensional-hotbar:" + Reference.controlerSubNames[4].toLowerCase() + "_top");
    	 this.icons[8] = par1IconRegister.registerIcon("extradimensional-hotbar:" + Reference.controlerSubNames[5].toLowerCase() + "_top");
     }
	
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {
		for (int ix = 0; ix < 6; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
	
	public TileEntity createTileEntity(World world, int metadata)
	{
	   return new TileEntityControler();
	}
}