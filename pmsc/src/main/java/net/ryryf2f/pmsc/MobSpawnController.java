package net.ryryf2f.pmsc;


import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.ryryf2f.pmsc.config.ConfigMain;

public class MobSpawnController
{
    /**
     * Checks global Config variables to see if any entity lists need to be generated
     */
    public static void loadMobSpawns()
    {
        ConfigMain CONFIG = Pmsc.CONFIG;

        if (CONFIG.generateAllVanillaMobs)
        {
            Pmsc.LOGGER.info("Generation of all vanilla mobs requested, building...");
            Registries.ENTITY_TYPE.forEach(entityType -> {
                if (getId(entityType).toString().contains("minecraft:"))
                    CONFIG.vanillaMobSpawns.add(new ConfigMain.CustomMobSpawn("all", getId(entityType).toString(), entityType.getSpawnGroup(),0,0,0,false));

            });
            Pmsc.CONFIG.generateAllVanillaMobs = false;
            Pmsc.CONFIG.isVanillaGenerated = true;
            Pmsc.LOGGER.info("Vanilla Mob Spawns loaded");
        }

        if (CONFIG.generateAllModdedMobs)
        {
            Pmsc.LOGGER.info("Generation of all modded mobs requested, building...");
            Registries.ENTITY_TYPE.forEach(entityType ->
            {
                if (!getId(entityType).toString().contains("minecraft"))
                    CONFIG.moddedMobSpawns.add(new ConfigMain.CustomMobSpawn("all", getId(entityType).toString(), entityType.getSpawnGroup(), 0,0,0,false));
            });
            Pmsc.CONFIG.generateAllModdedMobs = false;
            Pmsc.CONFIG.isModdedGenerated = true;
            Pmsc.LOGGER.info("Modded Mob Spawns Loaded");
        }
    }

    /**
     * Checks if the mod is enabled (CONFIG VALUE)
     * Then creates BiomeModifications to override spawn settings
     */
    public static void modifySpawns()
    {
        ConfigMain CONFIG = Pmsc.CONFIG;
        //mobSpawnsOverride should override vanilla or modded list

        if (CONFIG.enableMod)
        {
            Pmsc.LOGGER.info("mod enabled, modifying spawns...");
            if (CONFIG.isVanillaGenerated)
            {
                Pmsc.LOGGER.info("vanilla mob generation detected, modifying vanilla spawns...");
                CONFIG.vanillaMobSpawns.forEach(vanillaOverride ->
                {
                    if (vanillaOverride.enabled)
                        if (vanillaOverride.biomeId.equals("all"))
                        {
                            BiomeModifications.create(createId(Integer.toString(vanillaOverride.hashCode()))).add(
                                    ModificationPhase.REPLACEMENTS, BiomeSelectors.all(), context ->
                                    {
                                        context.getSpawnSettings().removeSpawnsOfEntityType(Registries.ENTITY_TYPE.get(entityKey(vanillaOverride.mobId)));

                                        context.getSpawnSettings().addSpawn(vanillaOverride.spawnGroup, new SpawnSettings.SpawnEntry(Registries.ENTITY_TYPE.get(entityKey(vanillaOverride.mobId)), vanillaOverride.weight, vanillaOverride.minCount, vanillaOverride.maxCount));
                                    }
                            );
                        }else
                        {
                            //for specific biomes
                            BiomeModifications.create(createId(Integer.toString(vanillaOverride.hashCode()))).add(
                                    ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(biomeKey(vanillaOverride.biomeId)), context ->
                                    {
                                        context.getSpawnSettings().removeSpawnsOfEntityType(Registries.ENTITY_TYPE.get(entityKey(vanillaOverride.mobId)));
                                        context.getSpawnSettings().addSpawn(vanillaOverride.spawnGroup, new SpawnSettings.SpawnEntry(Registries.ENTITY_TYPE.get(entityKey(vanillaOverride.mobId)), vanillaOverride.weight, vanillaOverride.minCount, vanillaOverride.maxCount));
                                    }
                            );
                        }
                });

            }

            if (CONFIG.isModdedGenerated)
            {
                Pmsc.LOGGER.info("modded mob generation detected, modifying modded spawns...");
                CONFIG.moddedMobSpawns.forEach(modOverride ->
                {
                    if (modOverride.enabled)
                        if (modOverride.biomeId.equals("all"))
                        {
                            BiomeModifications.create(createId(Integer.toString(modOverride.hashCode()))).add(
                                    ModificationPhase.REPLACEMENTS, BiomeSelectors.all(), context ->
                                    {
                                        context.getSpawnSettings().removeSpawnsOfEntityType(Registries.ENTITY_TYPE.get(entityKey(modOverride.mobId)));

                                        context.getSpawnSettings().addSpawn(modOverride.spawnGroup, new SpawnSettings.SpawnEntry(Registries.ENTITY_TYPE.get(entityKey(modOverride.mobId)), modOverride.weight, modOverride.minCount, modOverride.maxCount));
                                    }
                            );
                        }else
                        {
                            //for specific biomes
                            BiomeModifications.create(createId(Integer.toString(modOverride.hashCode()))).add(
                                    ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(biomeKey(modOverride.biomeId)), context ->
                                    {
                                        context.getSpawnSettings().removeSpawnsOfEntityType(Registries.ENTITY_TYPE.get(entityKey(modOverride.mobId)));
                                        context.getSpawnSettings().addSpawn(modOverride.spawnGroup, new SpawnSettings.SpawnEntry(Registries.ENTITY_TYPE.get(entityKey(modOverride.mobId)), modOverride.weight, modOverride.minCount, modOverride.maxCount));
                                    }
                            );
                        }
                });

            }





            //RUNNING FINAL OVERRIDES
            CONFIG.mobSpawnsOverride.forEach(mOverride ->
            {
                if (mOverride.enabled)
                {
                    if (mOverride.biomeId.equals("all"))
                    {
                        BiomeModifications.create(createId(Integer.toString(mOverride.hashCode()))).add(
                                ModificationPhase.REPLACEMENTS, BiomeSelectors.all(), context ->
                                {
                                    context.getSpawnSettings().removeSpawnsOfEntityType(Registries.ENTITY_TYPE.get(entityKey(mOverride.mobId)));

                                    context.getSpawnSettings().addSpawn(mOverride.spawnGroup, new SpawnSettings.SpawnEntry(Registries.ENTITY_TYPE.get(entityKey(mOverride.mobId)), mOverride.weight, mOverride.minCount, mOverride.maxCount));
                                }
                        );
                    } else {
                        //for specific biomes
                        BiomeModifications.create(createId(Integer.toString(mOverride.hashCode()))).add(
                                ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(biomeKey(mOverride.biomeId)), context ->
                                {
                                    context.getSpawnSettings().removeSpawnsOfEntityType(Registries.ENTITY_TYPE.get(entityKey(mOverride.mobId)));
                                    context.getSpawnSettings().addSpawn(mOverride.spawnGroup, new SpawnSettings.SpawnEntry(Registries.ENTITY_TYPE.get(entityKey(mOverride.mobId)), mOverride.weight, mOverride.minCount, mOverride.maxCount));
                                }
                        );
                    }
                }
            });
        }
    }

    private static Identifier getId(EntityType<?> type)
    {
        return Registries.ENTITY_TYPE.getId(type);
    }

    private static RegistryKey<EntityType<?>> entityKey(String id)
    {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(id));
    }

    private static Identifier createId(String name)
    {
        return new Identifier("pmsc", name);
    }

    private static RegistryKey<Biome> biomeKey(String id)
    {
        return RegistryKey.of(RegistryKeys.BIOME, new Identifier(id));
    }
}
