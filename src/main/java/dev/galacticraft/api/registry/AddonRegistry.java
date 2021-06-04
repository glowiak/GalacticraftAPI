/*
 * Copyright (c) 2019-2021 Team Galacticraft
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.galacticraft.api.registry;

import com.mojang.serialization.Lifecycle;
import dev.galacticraft.api.atmosphere.AtmosphericGas;
import dev.galacticraft.api.internal.fabric.GalacticraftAPI;
import dev.galacticraft.api.rocket.part.RocketPart;
import dev.galacticraft.api.rocket.part.travel.AccessWeightPredicateType;
import dev.galacticraft.api.rocket.part.travel.ConfiguredTravelPredicate;
import dev.galacticraft.api.rocket.part.travel.ConstantTravelPredicateType;
import dev.galacticraft.api.rocket.part.travel.TravelPredicateType;
import dev.galacticraft.api.universe.celestialbody.CelestialBody;
import dev.galacticraft.api.universe.celestialbody.CelestialBodyType;
import dev.galacticraft.api.universe.display.CelestialDisplayType;
import dev.galacticraft.api.universe.galaxy.Galaxy;
import dev.galacticraft.api.universe.position.CelestialPositionType;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class AddonRegistry {
    public static final RegistryKey<Registry<AtmosphericGas>> ATMOSPHERIC_GAS_KEY = RegistryKey.ofRegistry(new Identifier(GalacticraftAPI.MOD_ID, "atmospheric_gas"));
    public static final MutableRegistry<AtmosphericGas> ATMOSPHERIC_GAS = FabricRegistryBuilder.from(
            new DefaultedRegistry<>(AtmosphericGas.OXYGEN.getId().toString(),
                    ATMOSPHERIC_GAS_KEY, Lifecycle.experimental())).buildAndRegister();

    public static final RegistryKey<Registry<TravelPredicateType<?>>> TRAVEL_PREDICATE_KEY = RegistryKey.ofRegistry(new Identifier(GalacticraftAPI.MOD_ID, "travel_predicate"));
    public static final MutableRegistry<TravelPredicateType<?>> TRAVEL_PREDICATE = FabricRegistryBuilder.from(
            new DefaultedRegistry<>(new Identifier(GalacticraftAPI.MOD_ID, "constant").toString(),
                    TRAVEL_PREDICATE_KEY, Lifecycle.experimental())).buildAndRegister();

    public static final RegistryKey<Registry<ConfiguredTravelPredicate<?>>> CONFIGURED_TRAVEL_PREDICATE_KEY = RegistryKey.ofRegistry(new Identifier(GalacticraftAPI.MOD_ID, "configured_travel_predicate"));
    public static final MutableRegistry<ConfiguredTravelPredicate<?>> CONFIGURED_TRAVEL_PREDICATE = FabricRegistryBuilder.from(
            new DefaultedRegistry<>(new Identifier(GalacticraftAPI.MOD_ID, "never").toString(),
                    CONFIGURED_TRAVEL_PREDICATE_KEY, Lifecycle.experimental())).buildAndRegister();

    public static final RegistryKey<Registry<CelestialPositionType<?>>> CELESTIAL_POSITION_TYPE_KEY = RegistryKey.ofRegistry(new Identifier(GalacticraftAPI.MOD_ID, "celestial_position_type"));
    public static final MutableRegistry<CelestialPositionType<?>> CELESTIAL_POSITION_TYPE = FabricRegistryBuilder.from(
            new DefaultedRegistry<>(new Identifier(GalacticraftAPI.MOD_ID, "static").toString(),
                    CELESTIAL_POSITION_TYPE_KEY, Lifecycle.experimental())).buildAndRegister();

    public static final RegistryKey<Registry<CelestialDisplayType<?>>> CELESTIAL_DISPLAY_TYPE_KEY = RegistryKey.ofRegistry(new Identifier(GalacticraftAPI.MOD_ID, "celestial_display_type"));
    public static final MutableRegistry<CelestialDisplayType<?>> CELESTIAL_DISPLAY_TYPE = FabricRegistryBuilder.from(
            new DefaultedRegistry<>(new Identifier(GalacticraftAPI.MOD_ID, "empty").toString(),
                    CELESTIAL_DISPLAY_TYPE_KEY, Lifecycle.experimental())).buildAndRegister();

    public static final RegistryKey<Registry<Galaxy>> GALAXY_KEY = RegistryKey.ofRegistry(new Identifier(GalacticraftAPI.MOD_ID, "galaxy"));
    public static final MutableRegistry<Galaxy> GALAXY = FabricRegistryBuilder.from(
            new DefaultedRegistry<>(new Identifier(GalacticraftAPI.MOD_ID, "milky_way").toString(),
                    GALAXY_KEY, Lifecycle.experimental())).buildAndRegister();

    public static final RegistryKey<Registry<CelestialBodyType<?>>> CELESTIAL_BODY_TYPE_KEY = RegistryKey.ofRegistry(new Identifier(GalacticraftAPI.MOD_ID, "celestial_body_type"));
    public static final MutableRegistry<CelestialBodyType<?>> CELESTIAL_BODY_TYPE = FabricRegistryBuilder.from(
            new DefaultedRegistry<>(new Identifier(GalacticraftAPI.MOD_ID, "star").toString(),
                    CELESTIAL_BODY_TYPE_KEY, Lifecycle.experimental())).buildAndRegister();

    public static final RegistryKey<Registry<CelestialBody<?, ?>>> CELESTIAL_BODY_KEY = RegistryKey.ofRegistry(new Identifier(GalacticraftAPI.MOD_ID, "celestial_body"));
    public static final MutableRegistry<CelestialBody<?, ?>> CELESTIAL_BODY = FabricRegistryBuilder.from(
            new DefaultedRegistry<>(new Identifier(GalacticraftAPI.MOD_ID, "sol").toString(),
                    CELESTIAL_BODY_KEY, Lifecycle.experimental())).buildAndRegister();

    public static final RegistryKey<Registry<RocketPart>> ROCKET_PART_KEY = RegistryKey.ofRegistry(new Identifier(GalacticraftAPI.MOD_ID, "rocket_part"));
    public static final MutableRegistry<RocketPart> ROCKET_PART = FabricRegistryBuilder.from(
            new DefaultedRegistry<>(new Identifier(GalacticraftAPI.MOD_ID, "invalid").toString(),
                    ROCKET_PART_KEY, Lifecycle.experimental())).buildAndRegister();

    static {
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.HYDROGEN.getId(), AtmosphericGas.HYDROGEN);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.NITROGEN.getId(), AtmosphericGas.NITROGEN);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.OXYGEN.getId(), AtmosphericGas.OXYGEN);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.CARBON_DIOXIDE.getId(), AtmosphericGas.CARBON_DIOXIDE);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.WATER_VAPOR.getId(), AtmosphericGas.WATER_VAPOR);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.METHANE.getId(), AtmosphericGas.METHANE);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.HELIUM.getId(), AtmosphericGas.HELIUM);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.ARGON.getId(), AtmosphericGas.ARGON);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.NEON.getId(), AtmosphericGas.NEON);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.KRYPTON.getId(), AtmosphericGas.KRYPTON);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.NITROUS_OXIDE.getId(), AtmosphericGas.NITROUS_OXIDE);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.CARBON_MONOXIDE.getId(), AtmosphericGas.CARBON_MONOXIDE);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.XENON.getId(), AtmosphericGas.XENON);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.OZONE.getId(), AtmosphericGas.OZONE);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.NITROUS_DIOXIDE.getId(), AtmosphericGas.NITROUS_DIOXIDE);
        Registry.register(ATMOSPHERIC_GAS, AtmosphericGas.IODINE.getId(), AtmosphericGas.IODINE);

        Registry.register(TRAVEL_PREDICATE, new Identifier(GalacticraftAPI.MOD_ID, "access_weight"), AccessWeightPredicateType.INSTANCE);
        Registry.register(TRAVEL_PREDICATE, new Identifier(GalacticraftAPI.MOD_ID, "constant"), ConstantTravelPredicateType.INSTANCE);

        Registry.register(CONFIGURED_TRAVEL_PREDICATE, new Identifier(GalacticraftAPI.MOD_ID, "always"), ConfiguredTravelPredicate.ALWAYS);
        Registry.register(CONFIGURED_TRAVEL_PREDICATE, new Identifier(GalacticraftAPI.MOD_ID, "pass"), ConfiguredTravelPredicate.PASS);
        Registry.register(CONFIGURED_TRAVEL_PREDICATE, new Identifier(GalacticraftAPI.MOD_ID, "never"), ConfiguredTravelPredicate.NEVER);

        Registry.register(ROCKET_PART, RocketPart.INVALID.getId(), RocketPart.INVALID);
    }
}
