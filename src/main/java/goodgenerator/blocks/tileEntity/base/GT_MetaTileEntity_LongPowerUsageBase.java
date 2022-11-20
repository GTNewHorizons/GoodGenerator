package goodgenerator.blocks.tileEntity.base;

import com.github.bartimaeusnek.bartworks.util.BW_Util;
import com.github.technus.tectech.thing.metaTileEntity.hatch.GT_MetaTileEntity_Hatch_EnergyMulti;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Energy;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Muffler;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public abstract class GT_MetaTileEntity_LongPowerUsageBase extends GT_MetaTileEntity_TooltipMultiBlockBase_EM {

    protected GT_MetaTileEntity_LongPowerUsageBase(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    protected GT_MetaTileEntity_LongPowerUsageBase(String aName) {
        super(aName);
    }

    protected long lEUt;
    protected int energyTier = -1;

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        this.lEUt = aNBT.getLong("lEUt");
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setLong("lEUt", lEUt);
    }

    @Override
    public void clearHatches() {
        super.clearHatches();
        this.energyTier = -1;
    }

    @Override
    public boolean drainEnergyInput(long EUtEffective, long Amperes) {
        return doEnergyDrain(EUtEffective, Amperes);
    }

    @Override
    public boolean drainEnergyInput_EM(long EUtTierVoltage, long EUtEffective, long Amperes) {
        return doEnergyDrain(EUtEffective, Amperes);
    }

    protected void overclockLongPower(long aEUt, int aDuration, long maxInputVoltage, boolean perfectOC) {
        // 5% space for cable loss
        long zMaxInputVoltage = maxInputVoltage / 100L * 95L;
        long zTime = aDuration;
        while (aEUt < zMaxInputVoltage) {
            aEUt = aEUt << 2;
            zTime = zTime >> (perfectOC ? 2 : 1);
            if (zTime <= 0) {
                break;
            }
        }
        if (zTime <= 0) {
            zTime = 1;
        }
        if (aEUt > zMaxInputVoltage) {
            aEUt = aEUt >> 2;
            zTime = zTime << (perfectOC ? 2 : 1);
        }
        if (zTime > Integer.MAX_VALUE - 1) {
            zTime = Integer.MAX_VALUE - 1;
        }
        this.lEUt = aEUt;
        this.mMaxProgresstime = (int) zTime;
    }

    @Override
    public void stopMachine() {
        this.lEUt = 0L;
        super.stopMachine();
    }

    @Override
    public boolean onRunningTick(ItemStack aStack) {
        if (this.lEUt > 0) {
            this.addEnergyOutput_EM(this.lEUt * (long) this.mEfficiency / 10000L, 1);
            return true;
        } else if (this.lEUt < 0
                && !this.drainEnergyInput((-this.lEUt) * 10000L / (long) Math.max(1000, this.mEfficiency), 1)) {
            this.stopMachine();
            return false;
        } else {
            return true;
        }
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
