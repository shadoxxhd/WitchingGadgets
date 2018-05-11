package witchinggadgets.common.recipes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

import gregtech.api.util.GT_ModHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.utils.Utils;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.items.ItemClusters;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.registry.MetalFluidData;

public class WG_alchemic_recipe {
	public static void registeralchemic() {
		
		AspectList alchemyAspects;
		/**
		 * ALCHEMY
		 */
		alchemyAspects = new AspectList().add(Aspect.PLANT,64).add(Aspect.LIFE,16).add(Aspect.MAGIC,8);
		registerAlchemyRecipe("ROSEVINE.1","", new ItemStack(WGContent.BlockRoseVine), GT_ModHandler.getModItem("TwilightForest", "tile.TFThornRose", 1L), alchemyAspects);
		registerAlchemyRecipe("ROSEVINE.2","", new ItemStack(WGContent.BlockRoseVine), GT_ModHandler.getModItem("TwilightForest", "tile.TFThorns", 1L), alchemyAspects);
		registerAlchemyRecipe("ROSEVINE.3","", new ItemStack(WGContent.BlockRoseVine), GT_ModHandler.getModItem("TwilightForest", "tile.TFThorns", 1L,1), alchemyAspects);
		alchemyAspects = new AspectList().add(Aspect.PLANT,8).add(Aspect.LIFE,2);
		registerAlchemyRecipe("ALCHEMICALTRANSMOGRIFY","_GRASS", new ItemStack(Blocks.grass), new ItemStack(Blocks.dirt), alchemyAspects);
		alchemyAspects = new AspectList().add(Aspect.PLANT,2).add(Aspect.DARKNESS,8);
		registerAlchemyRecipe("ALCHEMICALTRANSMOGRIFY","_MYCEL", new ItemStack(Blocks.mycelium), new ItemStack(Blocks.dirt), alchemyAspects);
		alchemyAspects = new AspectList().add(Aspect.ENTROPY,4);
		registerAlchemyRecipe("ALCHEMICALTRANSMOGRIFY","_SAND", new ItemStack(Blocks.sand), new ItemStack(Blocks.cobblestone), alchemyAspects);
		alchemyAspects = new AspectList().add(Aspect.CRYSTAL,8);
		registerAlchemyRecipe("ALCHEMICALTRANSMOGRIFY","_FLINT", new ItemStack(Items.flint), new ItemStack(Blocks.gravel), alchemyAspects);

		alchemyAspects = new AspectList().add(Aspect.METAL,1).add(Aspect.ORDER,1);
		registerAlchemyRecipe("PURECINNABAR","", new ItemStack(ConfigItems.itemNugget, 1, 21), "oreCinnabar", alchemyAspects);
		

		if (WGConfig.moduleGemcutting) {
			alchemyAspects = new AspectList().add(Aspect.VOID,2).add(Aspect.CRYSTAL,4);
			registerAlchemyRecipe("CRYSTALCAPSULE","", new ItemStack(WGContent.ItemCapsule), new ItemStack(Items.bucket), alchemyAspects);
		}
		
		for(int iOre=0; iOre<witchinggadgets.common.WGContent.GT_Cluster.length; iOre++)
		{
			if(WGConfig.allowClusters)
			{
				alchemyAspects = new AspectList().add(Aspect.METAL,256).add(Aspect.ORDER,256);
				if(!OreDictionary.getOres("ore"+witchinggadgets.common.WGContent.GT_Cluster[iOre]).isEmpty() && !OreDictionary.getOres("ingot"+witchinggadgets.common.WGContent.GT_Cluster[iOre]).isEmpty())
				{
					registerAlchemyRecipe("METALLURGICPERFECTION_CLUSTERS","_"+witchinggadgets.common.WGContent.GT_Cluster[iOre], new ItemStack(WGContent.ItemCluster, 1, iOre), "ore"+witchinggadgets.common.WGContent.GT_Cluster[iOre], alchemyAspects);
					setupCluster(witchinggadgets.common.WGContent.GT_Cluster[iOre]);
				}
			}
			if(WGConfig.allowTransmutations)
			{
				boolean bb = !OreDictionary.getOres("nugget"+witchinggadgets.common.WGContent.GT_Cluster[iOre]).isEmpty() && !OreDictionary.getOres("ingot"+witchinggadgets.common.WGContent.GT_Cluster[iOre]).isEmpty();
				if(bb)
				{
					ItemStack ingot = OreDictionary.getOres("ingot"+witchinggadgets.common.WGContent.GT_Cluster[iOre]).get(0);
					alchemyAspects = ThaumcraftApi.objectTags.get( Arrays.asList(new Object[] { ingot.getItem(), Integer.valueOf(ingot.getItemDamage()) }) );
					if(alchemyAspects==null)
						alchemyAspects = new AspectList();
					alchemyAspects.remove(Aspect.METAL);
					alchemyAspects.add(Aspect.METAL, 2);
					Iterator<Entry<Aspect,Integer>> it = alchemyAspects.aspects.entrySet().iterator();
					while(it.hasNext())
					{
						Entry<Aspect,Integer> e = it.next();
						if(e.getKey()==null||e.getValue()==null)
							it.remove();
					}
					ItemStack nuggets = Utilities.copyStackWithSize(OreDictionary.getOres("nugget"+witchinggadgets.common.WGContent.GT_Cluster[iOre]).get(0), 3);
					registerAlchemyRecipe("METALLURGICPERFECTION_TRANSMUTATION","_"+witchinggadgets.common.WGContent.GT_Cluster[iOre], nuggets, "nugget"+witchinggadgets.common.WGContent.GT_Cluster[iOre], alchemyAspects);
				}
			}
		}
	}
	
	private static void registerAlchemyRecipe(String tag, String tagAddon, ItemStack result, Object catalyst, AspectList alchemyAspects)
	{
		CrucibleRecipe crucibleRecipe = ThaumcraftApi.addCrucibleRecipe(tag, result, catalyst, alchemyAspects);
		WGContent.recipeList.put(tag+tagAddon, crucibleRecipe);
	}
	private static void setupCluster(String name)
	{
		String fluid = MetalFluidData.getOreFluidName(name);
		int fluidTemp = MetalFluidData.getOreFluidTemp(name);
		fluidTemp = fluidTemp>0?fluidTemp:550;

		String ore = "ore"+name;
		String cluster = "cluster"+name;
		String ingot = "ingot"+name;
		String nugget = "nugget"+name;
		ItemStack clusterStack = ItemClusters.getCluster(name);

		if(!OreDictionary.getOres(nugget).isEmpty())
		{
			if(!OreDictionary.getOres(ore).isEmpty())
				ThaumcraftApi.addSmeltingBonus(ore, OreDictionary.getOres(nugget).get(0));
			if(!OreDictionary.getOres(cluster).isEmpty())
				ThaumcraftApi.addSmeltingBonus(cluster, OreDictionary.getOres(nugget).get(0));
		}

		if(!OreDictionary.getOres(cluster).isEmpty())
		{
			if(!OreDictionary.getOres(ingot).isEmpty())
			{
				ItemStack ingots = OreDictionary.getOres(ingot).get(0);
				if(clusterStack!=null)
				{
					
					if(!OreDictionary.getOres(ore).isEmpty())
						Utils.addSpecialMiningResult(OreDictionary.getOres(ore).get(0), clusterStack, 1f);
					if (WGContent.ClusterEBF.get(name)==null || !WGContent.ClusterEBF.get(name))
					FurnaceRecipes.smelting().func_151394_a(clusterStack, Utilities.copyStackWithSize(ingots,2), 1.0F);
				}
			}
			if(WGModCompat.loaded_TCon && WGConfig.smelteryResultForClusters>0 && FluidRegistry.getFluid(fluid)!=null)
				WGModCompat.addTConSmelteryRecipe(cluster, "block"+name, fluidTemp, fluid, WGConfig.smelteryResultForClusters);
		}
	}

}
