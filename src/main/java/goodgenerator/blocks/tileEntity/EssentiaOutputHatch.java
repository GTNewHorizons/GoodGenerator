package goodgenerator.blocks.tileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;

import java.util.Map;

public class EssentiaOutputHatch extends TileThaumcraft implements IAspectContainer, IEssentiaTransport {

    private static final int CAPACITY = 256;
    private AspectList current = new AspectList();

    public void clear() {
        this.current.aspects.clear();
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (this.worldObj.isRemote) return;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.current.aspects.clear();
        NBTTagList tlist = nbttagcompound.getTagList("Aspects", 69);
        for (int j = 0; j < tlist.tagCount(); ++j) {
            NBTTagCompound rs = tlist.getCompoundTagAt(j);
            if (rs.hasKey("key")) current.add(Aspect.getAspect(rs.getString("key")), rs.getInteger("amount"));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        Aspect[] aspectA = this.current.getAspects();
        NBTTagList nbtTagList = new NBTTagList();
        for (Aspect aspect : aspectA) {
            if (aspect != null) {
                NBTTagCompound f = new NBTTagCompound();
                f.setString("key", aspect.getTag());
                f.setInteger("amount", this.current.getAmount(aspect));
                nbtTagList.appendTag(f);
            }
        }
        nbttagcompound.setTag("Aspects", nbtTagList);
    }

    private int remainingCapacity() {
        return CAPACITY - this.getEssentiaAmount(null);
    }

    @Override
    public AspectList getAspects() {
        return this.current;
    }

    @Override
    public void setAspects(AspectList aspectList) {
        this.current.add(aspectList);
    }

    @Override
    public boolean doesContainerAccept(Aspect var1) {
        return true;
    }

    @Override
    public int addToContainer(Aspect aspect, int amount) {
        int remaining = 0;
        if (amount > this.remainingCapacity()) {
            remaining = amount - this.remainingCapacity();
            this.current.add(aspect, this.remainingCapacity());
        } else this.current.add(aspect, amount);
        return remaining;
    }

    public boolean addToContainer(AspectList aspectList) {
        if (aspectList.visSize() > this.remainingCapacity()) {
            for (Map.Entry<Aspect, Integer> entry : aspectList.copy().aspects.entrySet()) {
                Aspect aspect = entry.getKey();
                int amount = entry.getValue();
                int remaining = addToContainer(aspect, amount);
                if (remaining != 0) {
                    aspectList.reduce(aspect, remaining);
                    this.markDirty();
                    return false;
                } else aspectList.remove(aspect);
            }
        } else this.setAspects(aspectList);
        this.markDirty();
        return true;
    }

    @Override
    public boolean takeFromContainer(Aspect aspect, int amount) {
        if (this.current != null && this.current.getAmount(aspect) >= amount) {
            this.current.remove(aspect, amount);
            return true;
        } else return false;
    }

    @Override
    public boolean takeFromContainer(AspectList aspects) {
        return true;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect aspect, int amount) {
        return this.current.getAmount(aspect) >= amount;
    }

    @Override
    public boolean doesContainerContain(AspectList var1) {
        return false;
    }

    @Override
    public int containerContains(Aspect aspect) {
        return this.current.getAmount(aspect);
    }

    @Override
    public boolean isConnectable(ForgeDirection var1) {
        return true;
    }

    @Override
    public boolean canInputFrom(ForgeDirection var1) {
        return false;
    }

    @Override
    public boolean canOutputTo(ForgeDirection var1) {
        return true;
    }

    @Override
    public void setSuction(Aspect var1, int var2) {
    }

    @Override
    public Aspect getSuctionType(ForgeDirection var1) {
        return null;
    }

    @Override
    public int getSuctionAmount(ForgeDirection var1) {
        return 0;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection var3) {
        return this.canOutputTo(null) && this.takeFromContainer(aspect, amount) ? amount : 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection direction) {
        return 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection var1) {
        return this.current.size() > 0 ? this.current.getAspects()[this.worldObj.rand.nextInt(this.current.getAspects().length)] : null;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection var1) {
        return this.current.visSize();
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public boolean renderExtendedTube() {
        return true;
    }
}
