package siiikooo0743.EXDIHO;

import java.util.List;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import siiikooo0743.EXDIHO.lib.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;


public class Controler extends BlockContainer
{
	NBTTagCompound tag;
	EntityPlayer player;
	int[] data = new int[4];
	
    public Controler(int id, Material material)
    {
        super(id, material);
        
    }
        
    @Override
    public int damageDropped (int metadata) 
    {
    	return metadata;
    }
    
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase placerEntity, ItemStack par6ItemStack) 
    {
    	player = (EntityPlayer) placerEntity;
    	data[0] = player.dimension;
    	data[1] = par2;
    	data[2] = par3;
    	data[3] = par4;
    	tag = player.getEntityData();
    	tag.setIntArray("EXDIHO", data);
    	Minecraft.getMinecraft().theWorld.markBlockForUpdate(par2, par3, par4);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float a , float b, float c)
    {
    	
    	TileEntityControler tileControler = (TileEntityControler) world.getBlockTileEntity(x, y, z);
    	if (tileControler == null || player.isSneaking()) 
    	{
            return false;
    	}
        
    	player.openGui(EXDIHO.instance, Reference.GUI_ID, world, x, y, z);
                
        return true;
    }
     
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityControler(world);
	}

    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) 
    {
    	subItems.add(new ItemStack(this, 1, 9));
    }
}
