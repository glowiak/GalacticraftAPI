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

package dev.galacticraft.impl.universe.position.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.galacticraft.api.universe.position.CelestialPositionConfig;

public record OrbitalCelestialPositionConfig(double orbitTime, double distance, double phaseShift, boolean planet) implements CelestialPositionConfig {
    public static final Codec<OrbitalCelestialPositionConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.DOUBLE.fieldOf("orbit_time").forGetter(OrbitalCelestialPositionConfig::orbitTime),
            Codec.DOUBLE.fieldOf("distance").forGetter(OrbitalCelestialPositionConfig::distance),
            Codec.DOUBLE.fieldOf("phase_shift").forGetter(OrbitalCelestialPositionConfig::phaseShift),
            Codec.BOOL.fieldOf("planet").orElse(true).forGetter(OrbitalCelestialPositionConfig::planet)
    ).apply(instance, OrbitalCelestialPositionConfig::new));
}
