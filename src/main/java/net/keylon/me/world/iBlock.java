package net.keylon.me.world;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class iBlock {

    private Location location;
    private Material material;
    private byte data;
   
    @SuppressWarnings("deprecation")
    public iBlock(Block block){
        this.location = block.getLocation();
        this.material = block.getType();
        this.data = block.getData();
    }
   
    public Location getLocation(){
        return location;
    }
   
    public Material getMaterial(){
        return material;
    }
   
    public byte getData(){
        return data;
    }
   
}
 