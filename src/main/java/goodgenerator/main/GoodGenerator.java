package goodgenerator.main;

import com.github.bartimaeusnek.bartworks.API.WerkstoffAdderRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import goodgenerator.common.CommonProxy;
import goodgenerator.crossmod.thaumcraft.Research;
import goodgenerator.items.MyMaterial;
import goodgenerator.loader.*;
import goodgenerator.network.MessageOpenNeutronSensorGUI;
import goodgenerator.network.MessageResetTileTexture;
import goodgenerator.network.MessageSetNeutronSensorData;
import goodgenerator.tabs.MyTabs;
import net.minecraft.creativetab.CreativeTabs;

@SuppressWarnings("ALL")
@Mod(
        modid = GoodGenerator.MOD_ID,
        version = GoodGenerator.VERSION,
        dependencies = "required-after:IC2; "
                + "required-after:gregtech; "
                + "required-after:bartworks; "
                + "required-after:tectech; "
                + "required-after:structurelib; "
                + "after:dreamcraft;")
public final class GoodGenerator {
    public static final String MOD_ID = "GRADLETOKEN_MODID";
    public static final String MOD_NAME = "GRADLETOKEN_MODNAME";
    public static final String VERSION = "GRADLETOKEN_VERSION";

    public static final CreativeTabs GG = new MyTabs("Good Generator");

    @SidedProxy(clientSide = "goodgenerator.client.ClientProxy", serverSide = "goodgenerator.common.CommonProxy")
    public static CommonProxy proxy;

    public static SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);

    static {
        CHANNEL.registerMessage(
                MessageSetNeutronSensorData.ServerHandler.class, MessageSetNeutronSensorData.class, 0, Side.SERVER);
        CHANNEL.registerMessage(
                MessageOpenNeutronSensorGUI.ClientHandler.class, MessageOpenNeutronSensorGUI.class, 1, Side.CLIENT);
        CHANNEL.registerMessage(
                MessageResetTileTexture.ClientHandler.class, MessageResetTileTexture.class, 2, Side.CLIENT);
    }

    @Mod.Instance(GoodGenerator.MOD_ID)
    public static GoodGenerator instance;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        GG_Config_Loader.run();
        WerkstoffAdderRegistry.addWerkstoffAdder(new MyMaterial());
        // WerkstoffAdderRegistry.addWerkstoffAdder(new IsotopeMaterialLoader());
        Loaders.preInitLoad();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        Loaders.initLoad();
        proxy.init(event);
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        Loaders.postInitLoad();
        crossMod();
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        // NaquadahReworkRecipeLoader.SmallRecipeChange();
        NaquadahReworkRecipeLoader.Remover();

        Loaders.completeLoad();
    }

    public static void crossMod() {
        if (Loader.isModLoaded("Thaumcraft")) {
            Research.addResearch();
        }
    }
}
