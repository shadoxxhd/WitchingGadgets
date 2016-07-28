package witchinggadgets.common.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import witchinggadgets.WitchingGadgets;

import witchinggadgets.client.render.BlockRenderMetalDevice;
import witchinggadgets.common.blocks.tiles.TileEntityEssentiaPump;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWGMetalDevice extends BlockContainer 
{
	public static String[] subNames = {"essentiaPump","voidmetalBlock"};
	IIcon[] icons = new IIcon[subNames.length];

	public BlockWGMetalDevice()
	{
		super(Material.iron);
		this.setHardness(4F);
		this.setResistance(15);
		setCreativeTab(WitchingGadgets.tabWG);
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return worldObj.getBlockMetadata(x, y, z)==1;
    }
	
	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{	
		for(int i=0;i<icons.length;i++)
		{
			if(i<1||i>1)
				icons[i] = iconRegister.registerIcon("thaumcraft:metalbase");
			else
				icons[i] = iconRegister.registerIcon("witchinggadgets:"+subNames[i]);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if(meta<icons.length)
			return icons[meta];
		return null;
	}
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if(meta<icons.length)
			return icons[meta];
		return null;
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int x, int y, int z, int side)
	{
		return super.shouldSideBeRendered(iBlockAccess, x, y, z, side);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		if(world.getTileEntity(x, y, z) instanceof TileEntityEssentiaPump)
		{
			ForgeDirection fd = ((TileEntityEssentiaPump)world.getTileEntity(x, y, z)).facing;
			this.setBlockBounds(fd==ForgeDirection.EAST?.25f:0,fd==ForgeDirection.UP?.25f:0,fd==ForgeDirection.SOUTH?.25f:0, fd==ForgeDirection.WEST?.75f:1,fd==ForgeDirection.DOWN?.75f:1,fd==ForgeDirection.SOUTH?.75f:1);
		}

		else
			this.setBlockBounds(0,0,0,1,1,1);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		switch(metadata)
		{
		case 0:
			return new TileEntityEssentiaPump();
		case 1:
			return null;
		}
		return null;
	}
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		for(int i=0; i<subNames.length; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack stack)
	{
		int playerViewQuarter = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		int meta = world.getBlockMetadata(x, y, z);
		int f = playerViewQuarter==0 ? 2:playerViewQuarter==1 ? 5:playerViewQuarter==2 ? 3: 4;
		if(meta == 0)
			((TileEntityEssentiaPump)world.getTileEntity(x,y,z)).facing = ForgeDirection.getOrientation(f).getOpposite();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}

	@Override
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
	{
	}

	@Override
	public int getRenderType()
	{
		return BlockRenderMetalDevice.renderID;
	}

	
}