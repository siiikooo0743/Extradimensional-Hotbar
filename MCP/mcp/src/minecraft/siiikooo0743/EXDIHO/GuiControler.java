package siiikooo0743.EXDIHO;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiControler extends GuiContainer
{
	private static final ResourceLocation recource = new ResourceLocation("EXDIHO:textures/gui/Controler_Gui.png");
	private IInventory upperChestInventory;
    private TileEntityControler lowerChestInventory;

    private int inventoryRows;

    
	public GuiControler(IInventory par1IInventory, TileEntityControler par2TileEntity) 
	{
		super(new ContainerControler(par1IInventory, par2TileEntity));
		this.upperChestInventory = par1IInventory;
        this.lowerChestInventory = par2TileEntity;
        this.allowUserInput = false;
        this.inventoryRows = par2TileEntity.getSizeInventory() / 9;
        this.xSize = 176;
        this.ySize = 88 + this.inventoryRows * 18;
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(this.lowerChestInventory.isInvNameLocalized() ? this.lowerChestInventory.getInvName() : I18n.func_135053_a(this.lowerChestInventory.getInvName()), 8, 6, 4210752);
        this.fontRenderer.drawString(this.upperChestInventory.isInvNameLocalized() ? this.upperChestInventory.getInvName() : I18n.func_135053_a(this.upperChestInventory.getInvName()), 8, this.ySize -80 +2, 4210752);
    }

	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) 
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.func_110434_K().func_110577_a(recource);
		
		int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        int v = (9 - this.inventoryRows) * 18;
        
        if(v != 0)
        {
        	v = v + 8;
        }
        		
        this.drawTexturedModalRect(x, y, 0, v, this.xSize, this.ySize);
        
	}
	
}
