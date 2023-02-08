package net.ryryf2f.pmsc.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.entity.SpawnGroup;

import java.util.List;

@Config(name = "pmsc")
public class ConfigMain implements ConfigData
{
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Category(value = "general")
    public boolean enable = true;

    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup monsterGroup = new CustomSpawnGroup(70, false, false, 128, 32);

    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup creatureGroup = new CustomSpawnGroup(10, true, true, 128, 32);

    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup ambientGroup = new CustomSpawnGroup(15, true, false, 128, 32);

    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup axolotlGroup = new CustomSpawnGroup(5, true, false, 128, 32);

    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup waterCreatureGroup = new CustomSpawnGroup(5, true, false, 128, 32);

    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup waterAmbientGroup = new CustomSpawnGroup(20, true, false, 64, 32);

    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup undergroundWaterGroup = new CustomSpawnGroup(5, true, false, 128, 32);




    @ConfigEntry.Category(value = "Custom Mob Spawns")
    public List<CustomMobSpawn> someOption = List.of();




    /**
     * Container for a customized SpawnGroup based on values from net.minecraft.entity.SpawnGroup
     */
    public static class CustomSpawnGroup
    {
        public int capacity;
        public boolean peaceful;
        public boolean rare;
        public int immediateDespawnRange;
        public int despawnStartRange;

        public CustomSpawnGroup
                (
                        int capacity,
                        boolean peaceful,
                        boolean rare,
                        int immediateDespawnRange,
                        int despawnStartRange
                )
        {
            this.capacity = capacity;
            this.peaceful = peaceful;
            this.rare = rare;
            this.immediateDespawnRange = immediateDespawnRange;
            this.despawnStartRange = despawnStartRange;
        }
    }

    /**
     * Container for a custom mob spawn
     */
    public static class CustomMobSpawn
    {
        public String biomeId;
        public String mobId;
        public SpawnGroup spawnGroup;
        public int weight;
        public int minCount;
        public int maxCount;

        public CustomMobSpawn()
        {
            this.biomeId = "minecraft:plains";
            this.mobId = "minecraft:pig";
            this.spawnGroup = SpawnGroup.CREATURE;
            this.weight = 10;
            this.minCount = 4;
            this.maxCount = 4;
        }

        public CustomMobSpawn(String biomeId, String mobId, SpawnGroup spawnGroup, int weight, int minCount, int maxCount)
        {
            this.biomeId = biomeId;
            this.mobId = mobId;
            this.spawnGroup = spawnGroup;
            this.weight = weight;
            this.minCount = minCount;
            this.maxCount = maxCount;
        }
    }
}
