package siiikooo0743.EXDIHO;


import java.util.EnumSet;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

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
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntityChest;
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
	public static final Block controler = new BlockControler(Reference.BLOCK_ID);
    
	
	// The instance of your mod that Forge uses.
    @Instance("EXDIHO")
    public static EXDIHO instance;
   
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide = Reference.SERVER_PROXY_CLASS, serverSide = Reference.CLIENT_PROXY_CLASS)
    public static CommonProxy proxy;
    
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {

    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) 
    {
    	//Block
    	GameRegistry.registerBlock(controler, ControlerItemBlock.class, "controler");
    	GameRegistry.registerTileEntity(TileEntityControler.class, "controler");
    	
        //KeyBindings
    	KeyBinding[] key = {new KeyBinding(Reference.MOD_ID, Keyboard.KEY_G)};
        boolean[] repeat = {false};
        KeyBindingRegistry.registerKeyBinding(new KeyBindings(key, repeat));
        TickRegistry.registerTickHandler(new PlayerTickHandler(EnumSet.of(TickType.PLAYER)), Side.CLIENT);
        //Language
        
        for (int ix = 0; ix < 16; ix++) 
        {
        	ItemStack Stack = new ItemStack(controler, 1, ix);
        	
        	LanguageRegistry.addName(Stack, Reference.controlerSubNames[Stack.getItemDamage()]);
        }
        
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	//Recipe
    	
    	
    }
    
}
