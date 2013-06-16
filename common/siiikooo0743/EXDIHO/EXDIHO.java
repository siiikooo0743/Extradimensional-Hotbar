package siiikooo0743.EXDIHO;


import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
    /**
     * The Arrays to safe the items.
     * And the currently selected slot variable.
     */
    private static ItemStack[] slot0 = new ItemStack[9];
    private static int selected0;
    private static ItemStack[] slot1 = new ItemStack[9];
    private static int selected1;
    private static ItemStack[] slot2 = new ItemStack[9];
    private static int selected2;
    private static ItemStack[] slot3 = new ItemStack[9];
    private static int selected3;
    private static ItemStack[] slot4 = new ItemStack[9];
    private static int selected4;
    private static ItemStack[] slot5 = new ItemStack[9];
    private static int selected5;
    private static ItemStack[] slot6 = new ItemStack[9];
    private static int selected6;
    private static ItemStack[] slot7 = new ItemStack[9];
    private static int selected7;
    private static ItemStack[] slot8 = new ItemStack[9];
    private static int selected8;
    
    
    
    // The instance of your mod that Forge uses.
    @Instance("Generic")
    public static EXDIHO instance;
   
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide = Reference.SERVER_PROXY_CLASS, serverSide = Reference.CLIENT_PROXY_CLASS)
    public static CommonProxy proxy;
    
    /***
    * This is code that is executed prior to your mod being initialized into of Minecraft
    * Examples of code that could be run here;
    *
    * Initializing your items/blocks (you must do this here)
    * Setting up your own custom logger for writing log data to
    * Loading your language translations for your mod (if your mod has translations for other languages)
    * Registering your mod's key bindings and sounds
    *
    * @param event The Forge ModLoader pre-initialization event
    */
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        
    }
    
    /***
    *    This is code that is executed when your mod is being initialized in Minecraft
    * Examples of code that could be run here;
    *
    * Registering your GUI Handler
    * Registering your different event listeners
    * Registering your different tile entities
    * Adding in any recipes you have
    *
    * @param event The Forge ModLoader initialization event
    */
    @Init
    public void init(FMLInitializationEvent event) 
    {
        KeyBinding[] key = {new KeyBinding("EXDIHO", Keyboard.KEY_G)};
        boolean[] repeat = {false};
        KeyBindingRegistry.registerKeyBinding(new KeyBindings(key, repeat));
    }
    
    /***
    * This is code that is executed after all mods are initialized in Minecraft
    * This is a good place to execute code that interacts with other mods (ie, loads an addon module
    * of your mod if you find a particular mod).
    *
    * @param event The Forge ModLoader post-initialization event
    */
    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {
        
    }
    
    public static void ChangeItem(EntityPlayer player, int slot, int newSelected)
    {
        switch(slot)
        {   
            case 0:
                player.inventory.mainInventory[slot] = slot0[newSelected];
                
            break;
            case 1:
                player.inventory.mainInventory[slot] = slot1[newSelected];
                
            break;
            case 2:
                player.inventory.mainInventory[slot] = slot2[newSelected];
                
            break;
            case 3:
                player.inventory.mainInventory[slot] = slot3[newSelected];
                
            break;
            case 4:
                player.inventory.mainInventory[slot] = slot4[newSelected];
                
            break;
            case 5:
                player.inventory.mainInventory[slot] = slot5[newSelected];
                
            break;
            case 6:
                player.inventory.mainInventory[slot] = slot6[newSelected];
                
            break;
            case 7:
                player.inventory.mainInventory[slot] = slot7[newSelected];
                
            break;
            case 8:
                player.inventory.mainInventory[slot] = slot8[newSelected];
                
            
        }
    }
    
}
