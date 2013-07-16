package siiikooo0743.EXDIHO;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import siiikooo0743.EXDIHO.lib.Reference;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class Controler extends BlockContainer
{

    public Controler(int id, Material material)
    {
        super(id, material);
        // TODO Auto-generated constructor stub
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + "controler_top");
        //blockIcon = par1IconRegister.registerIcon("siiikooo0743.EXDIHO" + ":" + "controler_top");

    }
    
  
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
}
