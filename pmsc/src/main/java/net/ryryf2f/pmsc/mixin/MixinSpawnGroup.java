package net.ryryf2f.pmsc.mixin;



import net.ryryf2f.pmsc.Pmsc;
import net.ryryf2f.pmsc.config.ConfigMain;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;


import net.minecraft.entity.SpawnGroup;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

/**
 * Mixin Injector for net.minecraft.entity.SpawnGroup
 */
@Mixin(SpawnGroup.class)
public class MixinSpawnGroup
{

    @Unique private static final Map<String, ConfigMain.CustomSpawnGroup> custom_mob_groups;

    @Shadow @Final private String name;

    /**
     * Mixin Inject to intercept getCapacity() calls
     * @param info
     */
    @Inject(method = "getCapacity", at = @At("HEAD"), cancellable = true)
    private void injectGetCapacity(CallbackInfoReturnable<Integer> info)
    {
        ConfigMain.CustomSpawnGroup mobGroup = custom_mob_groups.get(this.name);

        if (mobGroup == null)
            return;

        info.setReturnValue(mobGroup.capacity);
    }

    /**
     * Mixin Inject to intercept isPeaceful() calls
     * @param info
     */
    @Inject(method = "isPeaceful", at = @At("HEAD"), cancellable = true)
    private void injectIsPeaceful(CallbackInfoReturnable<Boolean> info)
    {
        ConfigMain.CustomSpawnGroup mobGroup = custom_mob_groups.get(this.name);

        if (mobGroup == null)
            return;

        info.setReturnValue(mobGroup.peaceful);
    }

    /**
     * Mixin Inject to intercept isRare() calls
     * @param info
     */
    @Inject(method = "isRare", at = @At("HEAD"), cancellable = true)
    private void injectIsRare(CallbackInfoReturnable<Boolean> info)
    {
        ConfigMain.CustomSpawnGroup mobGroup = custom_mob_groups.get(this.name);

        if (mobGroup == null)
            return;

        info.setReturnValue(mobGroup.rare);
    }

    /**
     * Mixin Inject to intercept getImmediateDespawnRange()
     * @param info
     */
    @Inject(method = "getImmediateDespawnRange", at = @At("HEAD"), cancellable = true)
    private void injectGetImmediateDespawnRange(CallbackInfoReturnable<Integer> info)
    {
        ConfigMain.CustomSpawnGroup mobGroup = custom_mob_groups.get(this.name);

        if (mobGroup == null)
            return;

        info.setReturnValue(mobGroup.immediateDespawnRange);
    }

    /**
     * Mixin Inject to intercept getDespawnStartRange()
     * @param info
     */
    @Inject(method = "getDespawnStartRange", at = @At("HEAD"), cancellable = true)
    private void injectGetDespawnStartRange(CallbackInfoReturnable<Integer> info)
    {
        ConfigMain.CustomSpawnGroup mobGroup = custom_mob_groups.get(this.name);

        if (mobGroup == null)
            return;

        info.setReturnValue(mobGroup.despawnStartRange);
    }



//    @Unique
//    private ConfigMain.CustomSpawnGroup getCustomMobGroup(String name)
//    {
//        return custom_mob_groups.get(name);
//    }

    static {
        custom_mob_groups = new HashMap<>();

        custom_mob_groups.put(SpawnGroup.MONSTER.getName(), Pmsc.CONFIG.monsterGroup);
        custom_mob_groups.put(SpawnGroup.CREATURE.getName(), Pmsc.CONFIG.creatureGroup);
        custom_mob_groups.put(SpawnGroup.AMBIENT.getName(), Pmsc.CONFIG.ambientGroup);
        custom_mob_groups.put(SpawnGroup.AXOLOTLS.getName(), Pmsc.CONFIG.axolotlGroup);
        custom_mob_groups.put(SpawnGroup.WATER_CREATURE.getName(), Pmsc.CONFIG.waterCreatureGroup);
        custom_mob_groups.put(SpawnGroup.WATER_AMBIENT.getName(), Pmsc.CONFIG.waterAmbientGroup);
        custom_mob_groups.put(SpawnGroup.UNDERGROUND_WATER_CREATURE.getName(), Pmsc.CONFIG.waterCreatureGroup);
    }
}
