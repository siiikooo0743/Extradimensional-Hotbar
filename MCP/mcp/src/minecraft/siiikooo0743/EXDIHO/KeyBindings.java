package siiikooo0743.EXDIHO;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyBindings extends KeyHandler
{
    private EnumSet tickTypes = EnumSet.of(TickType.PLAYER);
    public static boolean KeyDown;
    public static boolean KeyChanged;
    
    public KeyBindings(KeyBinding[] keyBindings, boolean[] repeatings)
    {
            super(keyBindings, repeatings);
    }
    @Override
    public String getLabel()
    {
            return "TutorialKey";
    }
    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
    {
        KeyDown = true;
        KeyChanged = true;
    }
    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
    {
    	KeyDown = false;
    	KeyChanged = true;
    }
    @Override
    public EnumSet<TickType> ticks()
    {
            return tickTypes;
    }

}
