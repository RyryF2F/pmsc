package net.ryryf2f.pmsc.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.entity.SpawnGroup;

import java.util.List;
import java.util.Map;

@Config(name = "pmsc")
public class ConfigMain implements ConfigData
{
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Category(value = "general")
    public boolean enableMod = true;

    @ConfigEntry.Category(value = "general")
    @ConfigEntry.Gui.Tooltip //this should be "Requires Restart. Enable if you know what you're doing, this will remove any previous entries"
    public boolean generateAllVanillaMobs = false;

    @ConfigEntry.Category(value = "general")
    @ConfigEntry.Gui.Tooltip //this should be "Requires Restart. Enable if you know what you're doing, this will remove any previous entries"
    public boolean generateAllModdedMobs = false;


    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup monsterGroup = new CustomSpawnGroup(70, false, false, 128, 32, false);

    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup creatureGroup = new CustomSpawnGroup(10, true, true, 128, 32,false);

    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup ambientGroup = new CustomSpawnGroup(15, true, false, 128, 32, false);

    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup axolotlGroup = new CustomSpawnGroup(5, true, false, 128, 32, false);

    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup waterCreatureGroup = new CustomSpawnGroup(5, true, false, 128, 32, false);

    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup waterAmbientGroup = new CustomSpawnGroup(20, true, false, 64, 32, false);

    @ConfigEntry.Category(value = "Mob Spawn Groups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomSpawnGroup undergroundWaterGroup = new CustomSpawnGroup(5, true, false, 128, 32, false);


    /**
     * maybe this wont be the best idea. Maybe make it an option to add in all of the mobs, and then have it check against the registry to make sure its valid.
     */
    @ConfigEntry.Category(value = "Mob Spawn Override") //overrides any autogenerated ones below
    public List<CustomMobSpawn> mobSpawnsOverride = List.of();


    @ConfigEntry.Category(value = "Vanilla Mob Spawn Override")
    @ConfigEntry.Gui.Excluded
    public boolean isVanillaGenerated = false;

    @ConfigEntry.Category(value = "Vanilla Mob Spawn Override")
    public List<CustomMobSpawn> vanillaMobSpawnsMonster = List.of();

    @ConfigEntry.Category(value = "Vanilla Mob Spawn Override")
    public List<CustomMobSpawn> vanillaMobSpawnsCreature = List.of();

    @ConfigEntry.Category(value = "Vanilla Mob Spawn Override")
    public List<CustomMobSpawn> vanillaMobSpawnsAmbient = List.of();

    @ConfigEntry.Category(value = "Vanilla Mob Spawn Override")
    public List<CustomMobSpawn> vanillaMobSpawnsWaterCreature = List.of();

    @ConfigEntry.Category(value = "Vanilla Mob Spawn Override")
    public List<CustomMobSpawn> vanillaMobSpawnsWaterAmbient = List.of();

    @ConfigEntry.Category(value = "Vanilla Mob Spawn Override")
    public List<CustomMobSpawn> vanillaMobSpawnsWaterUnderground = List.of();

    @ConfigEntry.Category(value = "Vanilla Mob Spawn Override")
    public List<CustomMobSpawn> vanillaMobSpawnsAxolotl = List.of();

    @ConfigEntry.Category(value = "Vanilla Mob Spawn Override")
    public List<CustomMobSpawn> vanillaMobSpawnsMisc = List.of();




    @ConfigEntry.Category(value = "Modded Mob Spawn Override")
    public List<CustomMobSpawn> moddedMobSpawnsMonster = List.of();



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
        public boolean enabled;

        public CustomSpawnGroup(int capacity, boolean peaceful, boolean rare, int immediateDespawnRange, int despawnStartRange, boolean enabled)
        {
            this.capacity = capacity;
            this.peaceful = peaceful;
            this.rare = rare;
            this.immediateDespawnRange = immediateDespawnRange;
            this.despawnStartRange = despawnStartRange;
            this.enabled = enabled;
        }
    }

    /**
     * Container for a custom mob spawn
     */
    public static class CustomMobSpawn
    {
        public boolean enabled;
        public String biomeId; //can be set to all
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
            this.enabled = true;
        }

        public CustomMobSpawn(String biomeId, String mobId, SpawnGroup spawnGroup, int weight, int minCount, int maxCount, boolean enabled)
        {
            this.biomeId = biomeId;
            this.mobId = mobId;
            this.spawnGroup = spawnGroup;
            this.weight = weight;
            this.minCount = minCount;
            this.maxCount = maxCount;
            this.enabled = enabled;
        }
    }


}
