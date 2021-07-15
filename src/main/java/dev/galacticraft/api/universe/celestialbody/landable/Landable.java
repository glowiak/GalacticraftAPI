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

package dev.galacticraft.api.universe.celestialbody.landable;

import dev.galacticraft.api.atmosphere.AtmosphericInfo;
import dev.galacticraft.api.universe.celestialbody.CelestialBodyConfig;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a {@link dev.galacticraft.api.universe.celestialbody.CelestialBodyType<C> celestial body type} that has a {@link World} linked to itself.
 * @param <C> the type of configuration
 */
public interface Landable<C extends CelestialBodyConfig> {
    /**
     * Returns the registry key of the {@link World} this celestial body is linked to
     * @param config the celestial body configuration to be queried
     * @return the registry key of the {@link World} this celestial body is linked to
     */
    @NotNull RegistryKey<World> world(C config);

    /**
     * Returns the {@link AtmosphericInfo atmospheric information} of this celestial body
     * @param config the celestial body configuration to be queried
     * @return the registry key of the {@link World} this celestial body is linked to
     * @see AtmosphericInfo#breathable() to see the requirements for a celestial body to be considered breatheable
     */
    @NotNull AtmosphericInfo atmosphere(C config);

    /**
     * Returns the gravity of this celestial body, relative to earth
     * @param config the celestial body configuration to be queried
     * @return the gravity of this celestial body
     */
    float gravity(C config);

    /**
     * Returns the access weight required to generically reach this celestial body, or a negative value if it cannot be accessed this way.
     * For more advanced access requirements see {@link dev.galacticraft.api.rocket.travelpredicate.TravelPredicateType}
     * @param config the celestial body configuration to be queried
     * @return the access weight required to generically reach this celestial body
     */
    int accessWeight(C config);
}
