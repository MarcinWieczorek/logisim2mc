package co.marcin.logisim2mc.parser;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ComplexElement extends AbstractElement {
    protected final Collection<BlockData> blockData = new ArrayList<>();
    protected int originX = 0;
    protected int originY = 0;

    protected static class BlockData {
        public int x;
        public int y;
        public int z;
        public Material material;
        public org.bukkit.block.data.BlockData data = null;

        public BlockData(int x, int y, int z, Material material) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.material = material;
        }
    }

    public ComplexElement(int x, int y) {
        super(x, y);
    }

    @Override
    public void build(Location location) {
        location = location.clone().add(-originX, 0, -originY);
        location.add(this.x, 0, this.y);

        for(BlockData bd : this.blockData) {
            Block block = location.clone().add(bd.x, bd.z, bd.y).getBlock();
            block.setType(bd.material);

            if(bd.data != null) {
                block.setBlockData(bd.data);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("ComplexElement at (%d, %d)", x, y);
    }
}
