package goodgenerator.blocks.tileEntity;

import static goodgenerator.util.DescTextLocalization.BLUE_PRINT_INFO;
import static gregtech.api.enums.Textures.BlockIcons.MACHINE_CASING_FUSION_GLASS;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;

import com.github.bartimaeusnek.bartworks.common.loaders.ItemRegistry;
import com.github.technus.tectech.thing.metaTileEntity.hatch.GT_MetaTileEntity_Hatch_EnergyMulti;

import goodgenerator.blocks.tileEntity.base.LargeFusionComputerPP;
import goodgenerator.loader.Loaders;
import goodgenerator.util.DescTextLocalization;
import gregtech.api.enums.Materials;
import gregtech.api.enums.TAE;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Energy;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Output;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;
import gregtech.common.tileentities.machines.IDualInputHatch;
import gtPlusPlus.core.block.ModBlocks;
import gtPlusPlus.xmod.gregtech.common.blocks.textures.TexturesGtBlock;

public class LargeFusionComputer5 extends LargeFusionComputerPP {

    public LargeFusionComputer5(int id, String name, String nameRegional) {
        super(id, name, nameRegional);
    }

    public LargeFusionComputer5(String name) {
        super(name);
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Fusion Reactor").addInfo("Galaxy Collapse.")
                .addInfo("Controller block for the Compact Fusion Reactor MK-V.")
                .addInfo(
                        EnumChatFormatting.BLUE + GT_Utility.formatNumbers(getSingleHatchPower())
                                + EnumChatFormatting.GRAY
                                + "EU/t and "
                                + EnumChatFormatting.BLUE
                                + GT_Utility.formatNumbers(capableStartupCanonical() / 32 / M)
                                + "M"
                                + EnumChatFormatting.GRAY
                                + " EU capacity per Energy Hatch")
                .addInfo("If the recipe has a startup cost greater than the")
                .addInfo("number of energy hatches * cap, you can't do it")
                .addInfo(
                        "If the recipe requires a voltage tier over "
                                + GT_Utility.getColoredTierNameFromTier((byte) tier())
                                + EnumChatFormatting.GRAY
                                + " , you can't do it either")
                .addInfo("Make sure the whole structure is built in the 3x3")
                .addInfo("chunk area of the ring center (not controller).").addInfo("Performs 4/4 overclock.")
                .addInfo("Startup < 160,000,000 EU: 320x Parallel").addInfo("Startup < 320,000,000 EU: 256x Parallel")
                .addInfo("Startup < 640,000,000 EU: 192x Parallel").addInfo("Startup < 1,200,000,000 EU: 128x Parallel")
                .addInfo("Startup >= 1,200,000,000 EU: 64x Parallel")
                .addInfo(
                        "Support" + EnumChatFormatting.BLUE
                                + " Tec"
                                + EnumChatFormatting.DARK_BLUE
                                + "Tech"
                                + EnumChatFormatting.GRAY
                                + " Energy/Laser Hatches!")
                .addInfo("The structure is too complex!").addInfo(BLUE_PRINT_INFO).addSeparator()
                .addCasingInfo("Fusion Machine Casing MK IV", 1664)
                .addCasingInfo("Compact Fusion Coil MK-II Finaltype", 560).addCasingInfo("Infinity Frame Box", 128)
                .addCasingInfo("Cosmic Neutronium Reinforced Borosilicate Glass Block", 63)
                .addEnergyHatch("1-32, Hint block with dot 2", 2).addInputHatch("1-16, Hint block with dot 1", 1)
                .addOutputHatch("1-16, Hint block with dot 1", 1).addStructureInfo("Supports Crafting Input Buffer")
                .addStructureInfo(
                        "ALL Hatches must be " + GT_Utility.getColoredTierNameFromTier((byte) hatchTier())
                                + EnumChatFormatting.GRAY
                                + " or better")
                .toolTipFinisher("Good Generator");
        return tt;
    }

    @Override
    public int tier() {
        return 10;
    }

    @Override
    public long capableStartupCanonical() {
        return 20_480_000_000L;
    }

    @Override
    public Block getCasingBlock() {
        return ModBlocks.blockCasings6Misc;
    }

    @Override
    public int getCasingMeta() {
        return 0;
    }

    @Override
    public Block getCoilBlock() {
        return Loaders.compactFusionCoil;
    }

    @Override
    public int getCoilMeta() {
        return 4;
    }

    @Override
    public Block getGlassBlock() {
        return ItemRegistry.bw_realglas;
    }

    @Override
    public int getGlassMeta() {
        return 14;
    }

    @Override
    public int hatchTier() {
        return 10;
    }

    @Override
    public Materials getFrameBox() {
        return Materials.Infinity;
    }

    @Override
    public ITexture getTextureOverlay() {
        if (this.getBaseMetaTileEntity().isActive()) return TextureFactory.of(
                TextureFactory.builder().addIcon(TexturesGtBlock.Casing_Machine_Screen_Rainbow).extFacing().build());
        else return TextureFactory
                .of(TextureFactory.builder().addIcon(TexturesGtBlock.Casing_Machine_Screen_1).extFacing().build());
    }

    @Override
    public int getMaxPara() {
        return 64;
    }

    @Override
    public int tierOverclock() {
        return 16;
    }

    @Override
    public int extraPara(int startEnergy) {
        if (startEnergy < 160000000) return 5;
        if (startEnergy < 320000000) return 4;
        if (startEnergy < 640000000) return 3;
        if (startEnergy < 1200000000) return 2;
        return 1;
    }

    @Override
    public String[] getStructureDescription(ItemStack stackSize) {
        return DescTextLocalization.addText("LargeFusion5.hint", 9);
    }

    @Override
    public boolean turnCasingActive(boolean status) {
        if (this.mEnergyHatches != null) {
            for (GT_MetaTileEntity_Hatch_Energy hatch : this.mEnergyHatches) {
                hatch.updateTexture(status ? TAE.getIndexFromPage(3, 6) : 53);
            }
        }
        if (this.eEnergyMulti != null) {
            for (GT_MetaTileEntity_Hatch_EnergyMulti hatch : this.eEnergyMulti) {
                hatch.updateTexture(status ? TAE.getIndexFromPage(3, 6) : 53);
            }
        }
        if (this.mOutputHatches != null) {
            for (GT_MetaTileEntity_Hatch_Output hatch : this.mOutputHatches) {
                hatch.updateTexture(status ? TAE.getIndexFromPage(3, 6) : 53);
            }
        }
        if (this.mInputHatches != null) {
            for (GT_MetaTileEntity_Hatch_Input hatch : this.mInputHatches) {
                hatch.updateTexture(status ? TAE.getIndexFromPage(3, 6) : 53);
            }
        }
        if (this.mDualInputHatches != null) {
            for (IDualInputHatch hatch : this.mDualInputHatches) {
                hatch.updateTexture(status ? TAE.getIndexFromPage(3, 6) : 53);
            }
        }
        return true;
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, ForgeDirection side, ForgeDirection facing,
            int colorIndex, boolean aActive, boolean aRedstone) {
        if (side == facing)
            return new ITexture[] { TextureFactory.builder().addIcon(MACHINE_CASING_FUSION_GLASS).extFacing().build(),
                    getTextureOverlay() };
        if (!aActive) return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(52) };
        return new ITexture[] { TextureFactory.builder().addIcon(TexturesGtBlock.TEXTURE_CASING_FUSION_CASING_HYPER)
                .extFacing().build() };
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new LargeFusionComputer5(mName);
    }
}
