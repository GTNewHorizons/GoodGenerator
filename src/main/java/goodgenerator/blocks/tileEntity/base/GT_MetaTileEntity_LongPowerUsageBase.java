package goodgenerator.blocks.tileEntity.base;

import com.github.bartimaeusnek.bartworks.util.BW_Util;
import com.github.technus.tectech.thing.metaTileEntity.hatch.GT_MetaTileEntity_Hatch_EnergyMulti;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.*;
import gregtech.api.util.GT_Utility;
import java.util.ArrayList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

/**
 * Base class for multiblocks that supports TT energy hatches.
 * @param <T>
 */
public abstract class GT_MetaTileEntity_LongPowerUsageBase<T extends GT_MetaTileEntity_LongPowerUsageBase<T>>
        extends GT_MetaTileEntity_ExtendedPowerMultiBlockBase<T> {

    protected final ArrayList<GT_MetaTileEntity_Hatch_EnergyMulti> eEnergyMulti = new ArrayList<>();

    protected GT_MetaTileEntity_LongPowerUsageBase(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    protected GT_MetaTileEntity_LongPowerUsageBase(String aName) {
        super(aName);
    }

    @Override
    public boolean drainEnergyInput(long aEU) {
        return doEnergyDrain(aEU, 1);
    }

    @Override
    public void clearHatches() {
        eEnergyMulti.clear();
        super.clearHatches();
    }

    @Override
    public long getMaxInputVoltage() {
        long rVoltage = 0;
        for (GT_MetaTileEntity_Hatch_Energy tHatch : mEnergyHatches) {
            if (GT_MetaTileEntity_MultiBlockBase.isValidMetaTileEntity(tHatch)) {
                rVoltage += tHatch.maxEUInput();
            }
        }
        for (GT_MetaTileEntity_Hatch_EnergyMulti tHatch : eEnergyMulti) {
            if (GT_MetaTileEntity_MultiBlockBase.isValidMetaTileEntity(tHatch)) {
                rVoltage += tHatch.maxEUInput();
            }
        }
        return rVoltage;
    }

    @Override
    public final boolean addToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        if (aTileEntity == null) {
            return false;
        }
        IMetaTileEntity aMetaTileEntity = aTileEntity.getMetaTileEntity();
        if (aMetaTileEntity == null) {
            return false;
        }
        if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_EnergyMulti) {
            return eEnergyMulti.add((GT_MetaTileEntity_Hatch_EnergyMulti) aMetaTileEntity);
        }
        return super.addToMachineList(aTileEntity, aBaseCasingIndex);
    }

    @Override
    public String[] getInfoData() {
        return getInfoDataArray(this);
    }

    protected abstract long getRealVoltage();

    protected long getMaxInputAmps() {
        long rAmps = 0;
        for (GT_MetaTileEntity_Hatch_Energy tHatch : mEnergyHatches) {
            if (GT_MetaTileEntity_MultiBlockBase.isValidMetaTileEntity(tHatch)) {
                rAmps += tHatch.maxAmperesIn();
            }
        }
        for (GT_MetaTileEntity_Hatch_EnergyMulti tHatch : eEnergyMulti) {
            if (GT_MetaTileEntity_MultiBlockBase.isValidMetaTileEntity(tHatch)) {
                rAmps += tHatch.Amperes;
            }
        }
        return rAmps;
    }

    protected String[] getInfoDataArray(GT_MetaTileEntity_MultiBlockBase multiBlockBase) {
        int mPollutionReduction = 0;

        for (GT_MetaTileEntity_Hatch_Muffler tHatch : this.mMufflerHatches) {
            if (GT_MetaTileEntity_MultiBlockBase.isValidMetaTileEntity(tHatch)) {
                mPollutionReduction = Math.max(tHatch.calculatePollutionReduction(100), mPollutionReduction);
            }
        }

        long storedEnergy = 0;
        long maxEnergy = 0;

        for (GT_MetaTileEntity_Hatch_Energy tHatch : this.mEnergyHatches) {
            if (GT_MetaTileEntity_MultiBlockBase.isValidMetaTileEntity(tHatch)) {
                storedEnergy += tHatch.getBaseMetaTileEntity().getStoredEU();
                maxEnergy += tHatch.getBaseMetaTileEntity().getEUCapacity();
            }
        }
        for (GT_MetaTileEntity_Hatch_EnergyMulti tHatch : this.eEnergyMulti) {
            if (GT_MetaTileEntity_MultiBlockBase.isValidMetaTileEntity(tHatch)) {
                storedEnergy += tHatch.getBaseMetaTileEntity().getStoredEU();
                maxEnergy += tHatch.getBaseMetaTileEntity().getEUCapacity();
            }
        }

        long nominalV = getRealVoltage();
        String tName = BW_Util.getTierNameFromVoltage(this.getMaxInputVoltage());
        if (tName.equals("MAX+")) tName = EnumChatFormatting.OBFUSCATED + "MAX+";

        return new String[] {
            StatCollector.translateToLocal("GT5U.multiblock.Progress") + ": " + EnumChatFormatting.GREEN
                    + GT_Utility.formatNumbers(this.mProgresstime / 20) + EnumChatFormatting.RESET + " s / "
                    + EnumChatFormatting.YELLOW
                    + GT_Utility.formatNumbers(this.mMaxProgresstime / 20) + EnumChatFormatting.RESET + " s",
            StatCollector.translateToLocal("GT5U.multiblock.energy") + ": " + EnumChatFormatting.GREEN
                    + GT_Utility.formatNumbers(storedEnergy) + EnumChatFormatting.RESET + " EU / "
                    + EnumChatFormatting.YELLOW
                    + GT_Utility.formatNumbers(maxEnergy) + EnumChatFormatting.RESET + " EU",
            StatCollector.translateToLocal("GT5U.multiblock.usage") + ": " + EnumChatFormatting.RED
                    + GT_Utility.formatNumbers(-this.lEUt) + EnumChatFormatting.RESET + " EU/t",
            StatCollector.translateToLocal("GT5U.multiblock.mei") + ": " + EnumChatFormatting.YELLOW
                    + GT_Utility.formatNumbers(this.getMaxInputVoltage()) + EnumChatFormatting.RESET + " EU/t(*"
                    + GT_Utility.formatNumbers(this.getMaxInputAmps()) + "A) = "
                    + EnumChatFormatting.YELLOW
                    + GT_Utility.formatNumbers(nominalV) + EnumChatFormatting.RESET,
            StatCollector.translateToLocal("GT5U.machines.tier") + ": " + EnumChatFormatting.YELLOW + tName
                    + EnumChatFormatting.RESET,
            StatCollector.translateToLocal("GT5U.multiblock.problems") + ": " + EnumChatFormatting.RED
                    + (this.getIdealStatus() - this.getRepairStatus()) + EnumChatFormatting.RESET + " "
                    + StatCollector.translateToLocal("GT5U.multiblock.efficiency")
                    + ": " + EnumChatFormatting.YELLOW
                    + (float) this.mEfficiency / 100.0F + EnumChatFormatting.RESET + " %",
            StatCollector.translateToLocal("GT5U.multiblock.pollution") + ": " + EnumChatFormatting.GREEN
                    + mPollutionReduction + EnumChatFormatting.RESET + " %"
        };
    }

    private boolean doEnergyDrain(long EUtEffective, long Amperes) {
        long EUuse = EUtEffective * Amperes;
        if (EUuse < 0) {
            EUuse = -EUuse;
        }
        for (GT_MetaTileEntity_Hatch tHatch : mEnergyHatches) {
            long tDrain = Math.min(tHatch.getBaseMetaTileEntity().getStoredEU(), EUuse);
            tHatch.getBaseMetaTileEntity().decreaseStoredEnergyUnits(tDrain, false);
            EUuse -= tDrain;
        }
        for (GT_MetaTileEntity_Hatch tHatch : eEnergyMulti) {
            long tDrain = Math.min(tHatch.getBaseMetaTileEntity().getStoredEU(), EUuse);
            tHatch.getBaseMetaTileEntity().decreaseStoredEnergyUnits(tDrain, false);
            EUuse -= tDrain;
        }
        return EUuse <= 0;
    }
}
