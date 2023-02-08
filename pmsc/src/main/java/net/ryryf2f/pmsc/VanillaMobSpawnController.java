package net.ryryf2f.pmsc;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.ryryf2f.pmsc.config.ConfigMain;



public class VanillaMobSpawnController
{
    public static void loadVanillaMobSpawns()
    {
        ConfigMain CONFIG = Pmsc.CONFIG;

        if (CONFIG.vanillaMobSpawnsMonster.isEmpty())
        {
            //CONFIG.vanillaMobSpawnsMonster.add(new ConfigMain.CustomMobSpawn(""))
            Pmsc.LOGGER.info("Vanilla Mob Spawns not detected, loading...");
            Registries.ENTITY_TYPE.forEach(entityType -> {
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
            Pmsc.LOGGER.info("Vanilla Mob Spawns loaded");
        }
    }

//    private static RegistryKey<EntityType<?>> entityKey(String id) {
//        return RegistryKey.of(ENTITY_TYPE, new Identifier(id));
//    }

    private static Identifier getId(EntityType<?> type) {
        return Registries.ENTITY_TYPE.getId(type);
    }
}
