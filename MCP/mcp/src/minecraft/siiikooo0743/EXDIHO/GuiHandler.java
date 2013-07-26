package siiikooo0743.EXDIHO;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler 
{
        //returns an instance of the Container
        @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
        {
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                
                if(tileEntity instanceof TileEntityControler)
                {
                        return new ContainerControler(player.inventory, (TileEntityControler)tileEntity);
                }
                return null;
        }

        
        //returns an instance of the Gui
        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
        {
                TileEntity tileEntity = ModLoader.getMinecraftServerInstance().worldServers[player.dimension].getBlockTileEntity(x, y, z); 
                
                if(tileEntity instanceof TileEntityControler)
                {
                        return new GuiControler(player.inventory, (TileEntityControler) tileEntity);
                }
                
                return null;

        }
}