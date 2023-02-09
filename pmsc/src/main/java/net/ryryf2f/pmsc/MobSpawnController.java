package net.ryryf2f.pmsc;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
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

import java.util.ArrayList;


public class MobSpawnController
{
    public static void loadMobSpawns()
    {
        ConfigMain CONFIG = Pmsc.CONFIG;

        if (CONFIG.generateAllVanillaMobs)
        {
            //CONFIG.vanillaMobSpawnsMonster.add(new ConfigMain.CustomMobSpawn(""))
            Pmsc.LOGGER.info("Generation of all vanilla mobs requested, building...");
            Registries.ENTITY_TYPE.forEach(entityType -> {
                if (getId(entityType).toString().contains("minecraft:"))
               switch (entityType.getSpawnGroup())
               {
                   case MONSTER:
                       CONFIG.vanillaMobSpawnsMonster.add(new ConfigMain.CustomMobSpawn("all", getId(entityType).toString(), entityType.getSpawnGroup(), 0, 0, 0,false));
                       break;
                   case CREATURE:
                       CONFIG.vanillaMobSpawnsCreature.add(new ConfigMain.CustomMobSpawn("all", getId(entityType).toString(), entityType.getSpawnGroup(),0,0,0,false));
                       break;
                   case AMBIENT:
                       CONFIG.vanillaMobSpawnsAmbient.add(new ConfigMain.CustomMobSpawn("all", getId(entityType).toString(), entityType.getSpawnGroup(), 0,0,0,false));
                       break;
                   case WATER_CREATURE:
                       CONFIG.vanillaMobSpawnsWaterCreature.add(new ConfigMain.CustomMobSpawn("all", getId(entityType).toString(), entityType.getSpawnGroup(), 0,0,0,false));
                       break;
                   case WATER_AMBIENT:
                       CONFIG.vanillaMobSpawnsWaterAmbient.add(new ConfigMain.CustomMobSpawn("all", getId(entityType).toString(), entityType.getSpawnGroup(), 0,0,0,false));
                       break;
                   case UNDERGROUND_WATER_CREATURE:
                       CONFIG.vanillaMobSpawnsWaterUnderground.add(new ConfigMain.CustomMobSpawn("all", getId(entityType).toString(), entityType.getSpawnGroup(), 0,0,0,false));
                       break;
                   case AXOLOTLS:
                       CONFIG.vanillaMobSpawnsAxolotl.add(new ConfigMain.CustomMobSpawn("all", getId(entityType).toString(), entityType.getSpawnGroup(), 0,0,0,false));
                       break;
                   case MISC:
                       CONFIG.vanillaMobSpawnsMisc.add(new ConfigMain.CustomMobSpawn("all", getId(entityType).toString(), entityType.getSpawnGroup(), 0,0,0,false));
                       break;

               }
            });
            Pmsc.CONFIG.generateAllVanillaMobs = false;
            Pmsc.LOGGER.info("Vanilla Mob Spawns loaded");
        }
    }


    public static void modifySpawns()
    {
        ConfigMain CONFIG = Pmsc.CONFIG;
        //mobSpawnsOverride should override vanilla or modded list

        if (CONFIG.enableMod)
        {
            Pmsc.LOGGER.info("mod enabled, modifying spawns...");
            if (CONFIG.isVanillaGenerated)
            {

            }else
            {
                CONFIG.mobSpawnsOverride.forEach(mOverride ->
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
                    }else
                    {
                        //for specific biomes
                        BiomeModifications.create(createId(Integer.toString(mOverride.hashCode()))).add(
                                ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(biomeKey(mOverride.biomeId)), context ->
                                {
                                    context.getSpawnSettings().removeSpawnsOfEntityType(Registries.ENTITY_TYPE.get(entityKey(mOverride.mobId)));
                                    context.getSpawnSettings().addSpawn(mOverride.spawnGroup, new SpawnSettings.SpawnEntry(Registries.ENTITY_TYPE.get(entityKey(mOverride.mobId)), mOverride.weight, mOverride.minCount, mOverride.maxCount));
                                }
                        );
                    }

                });
            }
        }

    }

//    private static RegistryKey<EntityType<?>> entityKey(String id) {
//        return RegistryKey.of(ENTITY_TYPE, new Identifier(id));
//    }

    private static Identifier getId(EntityType<?> type) {
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

    private static RegistryKey<Biome> biomeKey(String id) {
        return RegistryKey.of(RegistryKeys.BIOME, new Identifier(id));
    }

    private static void clearVanillaMobs()
    {
        ConfigMain CONFIG = Pmsc.CONFIG;
        CONFIG.vanillaMobSpawnsMonster = new ArrayList<>();
        CONFIG.vanillaMobSpawnsCreature = new ArrayList<>();
    }

}
