package witchinggadgets.common.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandTriggerRegistry;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.blocks.tiles.TileEntityBlastfurnace;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.recipe.InfernalBlastfurnaceRecipe;
import witchinggadgets.common.util.recipe.SpinningRecipe;

public class WG_others {
	
	
	public static void register_others() {
		
	registerShapedOreRecipe("GEMCUTTING","_TOOLS", new ItemStack(WGContent.ItemMaterial,1,8), "qfi","sss", 'q',GT_OreDictUnificator.get(OrePrefixes.gemChipped, Materials.Ruby, 1L), 'f',GT_OreDictUnificator.get(OrePrefixes.gemChipped, Materials.Diamond, 1L), 'i',GT_OreDictUnificator.get(OrePrefixes.gemChipped, Materials.Emerald, 1L), 's',GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Thaumium, 1L));
	registerShapelessOreRecipe("SCANCAMERA", "_CLEARPLATE", new ItemStack(ConfigItems.itemResource,1,10), new ItemStack(WGContent.ItemMaterial,1,9));
	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(WGContent.BlockMetalDevice,1), "blockVoid"));
	
	
	/**
	 * SPINNING
	 */
		
	SpinningRecipe spin_String = new SpinningRecipe(new ItemStack(Items.string,5), new Object[] {GT_ModHandler.getModItem("Natura", "barleyFood", 1L,3),GT_ModHandler.getModItem("Natura", "barleyFood", 1L,3),GT_ModHandler.getModItem("Natura", "barleyFood", 1L,3),GT_ModHandler.getModItem("Natura", "barleyFood", 1L,3),GT_ModHandler.getModItem("Natura", "barleyFood", 1L,3)});
	SpinningRecipe.addRecipe(spin_String);
		
	SpinningRecipe spin_flameString = new SpinningRecipe(GT_ModHandler.getModItem("Natura", "barleyFood", 2L,7), new Object[] {new ItemStack(WGContent.ItemMaterial,1,2),GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.InfusedFire, 1L),new ItemStack(WGContent.ItemMaterial,1,2),GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.InfusedFire, 1L),new ItemStack(WGContent.ItemMaterial,1,2)});
	SpinningRecipe.addRecipe(spin_flameString);
	
	SpinningRecipe spin_Thread = new SpinningRecipe(new ItemStack(WGContent.ItemMaterial,2,0), new Object[] {Items.string, Items.string, Items.string, Items.string});
	SpinningRecipe.addRecipe(spin_Thread);

	SpinningRecipe spin_goldThread = new SpinningRecipe(new ItemStack(WGContent.ItemMaterial,2,1), new Object[] {Items.string, Items.string, GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Gold, 1L)});
	SpinningRecipe.addRecipe(spin_goldThread);

	SpinningRecipe spin_thaumiumThread = new SpinningRecipe(new ItemStack(WGContent.ItemMaterial,2,2), new Object[] {Items.string, Items.string, "wireFineThaumium", "wireFineThaumium"});
	SpinningRecipe.addRecipe(spin_thaumiumThread);
	/**
	WeavingRecipe weave_void = new WeavingRecipe("BAGOFTRICKS", new ItemStack(WGContent.ItemMaterial,2,3),new AspectList().add(Aspect.VOID, 8), new Object[] {new ItemStack(WGContent.ItemMaterial,1,0), new ItemStack(WGContent.ItemMaterial,1,0), new ItemStack(WGContent.ItemMaterial,1,0), new ItemStack(WGContent.ItemMaterial,1,0), new ItemStack(WGContent.ItemMaterial,1,2), new ItemStack(WGContent.ItemMaterial,1,2)});
	WitchingGadgets.instance.customRecipeHandler.addRecipe(weave_void);

	ItemStack fabStack = new ItemStack(ConfigItems.itemResource, 2, 7);
	WeavingRecipe weave_enchFabric = new WeavingRecipe("ENCHFABRIC", fabStack,new AspectList().add(Aspect.MAGIC, 2), new Object[] {new ItemStack(WGContent.ItemMaterial,1,0), new ItemStack(WGContent.ItemMaterial,1,0)});
	WitchingGadgets.instance.customRecipeHandler.addRecipe(weave_enchFabric);

	WeavingRecipe weave_fleece = new WeavingRecipe("ADVANCEDROBES", new ItemStack(WGContent.ItemMaterial,2,5),new AspectList().add(Aspect.MAGIC, 8).add(Aspect.TAINT, 2), new Object[] {new ItemStack(WGContent.ItemMaterial,1,0), new ItemStack(WGContent.ItemMaterial,1,0), new ItemStack(WGContent.ItemMaterial,1,0), new ItemStack(WGContent.ItemMaterial,1,2)});
	WitchingGadgets.instance.customRecipeHandler.addRecipe(weave_fleece);
	 */
	registerCompoundRecipe("GEMCUTTING","",new AspectList(),1,2,1, new Object[] {new ItemStack(WGContent.ItemMaterial,1,8),new ItemStack(ConfigBlocks.blockTable)} );


	registerCompoundRecipe("LOOM","",new AspectList().add(Aspect.AIR, 15).add(Aspect.ORDER, 15),2,2,3,
			new ItemStack(Blocks.fence), null,
			new ItemStack(Blocks.iron_bars), null,
			new ItemStack(Blocks.fence), null,
			"plankWood", "plankWood",
			"slabWood", "slabWood",
			"plankWood", "plankWood" );

	ItemStack ifBlFrStair = new ItemStack(TileEntityBlastfurnace.stairBlock,1,TileEntityBlastfurnace.stairBlock!=Blocks.stone_brick_stairs?1:0);
	registerCompoundRecipe("INFERNALBLASTFURNACE","",new AspectList().add(Aspect.FIRE, 50).add(Aspect.EARTH, 50).add(Aspect.ENTROPY, 50),3,3,3,
			ifBlFrStair,ifBlFrStair,ifBlFrStair,
			ifBlFrStair,new ItemStack(Blocks.lava),ifBlFrStair,
			ifBlFrStair,ifBlFrStair,ifBlFrStair,

			new ItemStack(TileEntityBlastfurnace.brickBlock[9]),new ItemStack(TileEntityBlastfurnace.brickBlock[10]),new ItemStack(TileEntityBlastfurnace.brickBlock[11]),
			new ItemStack(TileEntityBlastfurnace.brickBlock[12]),new ItemStack(TileEntityBlastfurnace.brickBlock[13]),new ItemStack(TileEntityBlastfurnace.brickBlock[14]),
			new ItemStack(TileEntityBlastfurnace.brickBlock[15]),new ItemStack(TileEntityBlastfurnace.brickBlock[16]),new ItemStack(TileEntityBlastfurnace.brickBlock[17]),

			new ItemStack(TileEntityBlastfurnace.brickBlock[0]),new ItemStack(TileEntityBlastfurnace.brickBlock[1]),new ItemStack(TileEntityBlastfurnace.brickBlock[2]),
			new ItemStack(TileEntityBlastfurnace.brickBlock[3]),new ItemStack(TileEntityBlastfurnace.brickBlock[4]),new ItemStack(TileEntityBlastfurnace.brickBlock[5]),
			new ItemStack(TileEntityBlastfurnace.brickBlock[6]),new ItemStack(TileEntityBlastfurnace.brickBlock[7]),new ItemStack(TileEntityBlastfurnace.brickBlock[8]) );

	WandTriggerRegistry.registerWandBlockTrigger(WitchingGadgets.instance.wgWandManager, 0, Blocks.fence, -1);
	WandTriggerRegistry.registerWandBlockTrigger(WitchingGadgets.instance.wgWandManager, 0, Blocks.iron_bars, -1);

	if(WGModCompat.railcraftAllowBlastFurnace())
		WandTriggerRegistry.registerWandBlockTrigger(WitchingGadgets.instance.wgWandManager, 1, TileEntityBlastfurnace.brickBlock[0], -1);
	else
	{
		WandTriggerRegistry.registerWandBlockTrigger(WitchingGadgets.instance.wgWandManager, 1, TileEntityBlastfurnace.brickBlock[0], -1);
		WandTriggerRegistry.registerWandBlockTrigger(WitchingGadgets.instance.wgWandManager, 1, TileEntityBlastfurnace.brickBlock[4], -1);
		WandTriggerRegistry.registerWandBlockTrigger(WitchingGadgets.instance.wgWandManager, 1, TileEntityBlastfurnace.brickBlock[10], -1);
	}
	WandTriggerRegistry.registerWandBlockTrigger(WitchingGadgets.instance.wgWandManager, 1, TileEntityBlastfurnace.stairBlock, -1);

	addBlastTrippling("Iron");
	addBlastTrippling("Gold");
	addBlastTrippling("Copper");
	addBlastTrippling("Tin");
	addBlastTrippling("Silver");
	addBlastTrippling("Lead");
	addBlastTrippling("Cinnabar");
	InfernalBlastfurnaceRecipe.addRecipe(new ItemStack(ConfigItems.itemResource,3,3), "clusterCinnabar",1, 440,false).addBonus(new ItemStack(ConfigItems.itemNugget,1,5));
	int i=0;
	
	for(String name : OreDictionary.getOreNames()) {
		if(name.startsWith("cluster"))
			if(WGContent.ClusterEBF.get(name.substring("cluster".length()))==null || !WGContent.ClusterEBF.get(name.substring("cluster".length()))) {
				addBlastTrippling(name.substring("cluster".length()));
				if (!OreDictionary.getOres(name).isEmpty()&&!OreDictionary.getOres("dustTiny"+name.substring("cluster".length())).isEmpty())
				GT_Values.RA.addPulveriserRecipe(OreDictionary.getOres(name).get(0), new ItemStack[]{OreDictionary.getOres("dustTiny"+name.substring("cluster".length())).get(0).copy().splitStack(22)}, new int[] {10000}, 30, 30);
			}
			else
				if (!OreDictionary.getOres(name).isEmpty()&&!OreDictionary.getOres("dustTiny"+name.substring("cluster".length())).isEmpty())
				GT_Values.RA.addPulveriserRecipe(OreDictionary.getOres(name).get(0), new ItemStack[]{OreDictionary.getOres("dustTiny"+name.substring("cluster".length())).get(0).copy().splitStack(22)}, new int[] {10000}, 30, 30);
	}
	
	
	if(WGModCompat.loaded_TCon)
	{
		if(WGConfig.smelteryResultForClusters>0)
		{	
			WGModCompat.addTConSmelteryRecipe("clusterIron", "blockIron", 600, "iron.molten", WGConfig.smelteryResultForClusters);
			WGModCompat.addTConSmelteryRecipe("clusterGold", "blockGold", 400, "gold.molten", WGConfig.smelteryResultForClusters);
			WGModCompat.addTConSmelteryRecipe("clusterCopper", "blockCopper", 350, "copper.molten", WGConfig.smelteryResultForClusters);
			WGModCompat.addTConSmelteryRecipe("clusterTin", "blockTin", 400, "tin.molten", WGConfig.smelteryResultForClusters);
			WGModCompat.addTConSmelteryRecipe("clusterSilver", "blockSilver", 550, "silver.molten", WGConfig.smelteryResultForClusters);
			WGModCompat.addTConSmelteryRecipe("clusterLead", "blockLead", 400, "lead.molten", WGConfig.smelteryResultForClusters);
		}
		WGModCompat.addTConDryingRecipe(new ItemStack(ConfigItems.itemZombieBrain), 20*6*5, new ItemStack(WGContent.ItemMagicFoodstuffs,1,2));
	}
	}

	private static boolean registerShapelessOreRecipe(String tag, String tagAddon, ItemStack result, Object... recipe)
	{
		ShapelessOreRecipe oreRecipe = new ShapelessOreRecipe(result,recipe);
		GameRegistry.addRecipe(oreRecipe);
		WGContent.recipeList.put(tag+tagAddon, oreRecipe);
		return (WGContent.recipeList.containsKey(tag+tagAddon) && WGContent.recipeList.containsValue(oreRecipe));
	}

	private static boolean registerShapedOreRecipe(String tag, String tagAddon, ItemStack result, Object... recipe)
	{
		ShapedOreRecipe oreRecipe = new ShapedOreRecipe(result,recipe);
		GameRegistry.addRecipe(oreRecipe);
		WGContent.recipeList.put(tag+tagAddon, oreRecipe);
		return (WGContent.recipeList.containsKey(tag+tagAddon) && WGContent.recipeList.containsValue(oreRecipe));
	}
	
	private static void registerCompoundRecipe(String tag, String tagAddon, AspectList creationAspects, int sizeX, int sizeY, int sizeZ, Object... recipe)
	{
		List<Object> compoundRecipe = Arrays.asList(new Object[] {creationAspects, Integer.valueOf(sizeX), Integer.valueOf(sizeY), Integer.valueOf(sizeZ), Arrays.asList(recipe)});
		WGContent.recipeList.put(tag+tagAddon, compoundRecipe);
	}
	
	static void addBlastTrippling(String name)
	{
		if(!OreDictionary.getOres("ingot"+name).isEmpty())
		{
			InfernalBlastfurnaceRecipe r = InfernalBlastfurnaceRecipe.addRecipe(Utilities.copyStackWithSize(OreDictionary.getOres("ingot"+name).get(0),3), "cluster"+name,1, 440,false);
			if(r!=null && !OreDictionary.getOres("nugget"+name).isEmpty())
				r.addBonus(OreDictionary.getOres("nugget"+name).get(0));
		}
	}
}
