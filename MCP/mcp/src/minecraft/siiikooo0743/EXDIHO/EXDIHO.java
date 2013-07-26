package siiikooo0743.EXDIHO;


import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.MinecraftForge;

import siiikooo0743.EXDIHO.*;
import siiikooo0743.EXDIHO.lib.*;
import siiikooo0743.EXDIHO.proxy.CommonProxy;

/**
* EXDIHO
*
* Extradimensional-Hotbar
*
* @author siiikooo0743
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*
*/

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class EXDIHO
{
    //Blocks    
    public final static Block controler = new Controler(Reference.BLOCK_ID, Material.iron).setHardness(4.0F)
    		.setStepSound(Block.soundMetalFootstep).setUnlocalizedName("controler")
    		.setCreativeTab(CreativeTabs.tabBlock).func_111022_d("exdiho:controler_top");
    
    // The instance of your mod that Forge uses.
    @Instance("EXDIHO")
    public static EXDIHO instance;
   
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide = Reference.SERVER_PROXY_CLASS, serverSide = Reference.CLIENT_PROXY_CLASS)
    public static CommonProxy proxy;
    
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	
        //Block
    	GameRegistry.registerBlock(controler, ItemBlockControler.class, "controler");
        GameRegistry.registerTileEntity(TileEntityControler.class, "tileControler");
        MinecraftForge.setBlockHarvestLevel(controler, "pickaxe", 1);
        
        //KeyBindings
    	KeyBinding[] key = {new KeyBinding(Reference.MOD_ID, Keyboard.KEY_G)};
        boolean[] repeat = {false};
        KeyBindingRegistry.registerKeyBinding(new KeyBindings(key, repeat));
       
        //Language
        
        LanguageRegistry.addName(controler, "Controler");
        
        for (int ix = 0; ix < 16; ix++) {
			ItemStack multiBlockStack = new ItemStack(controler, 1, ix);
			
			LanguageRegistry.addName(multiBlockStack, "Controler");
		}
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) 
    {
        //Handlers
    	NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());

    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	//Recipe
    	GameRegistry.addRecipe(new ItemStack(controler, 1, 2), "xxx", "xxx", "xxx",'x',Block.dirt);
    	
    }
    
}
