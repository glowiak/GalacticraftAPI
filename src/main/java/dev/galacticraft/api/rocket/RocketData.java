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

package dev.galacticraft.api.rocket;

import dev.galacticraft.api.rocket.part.RocketPart;
import dev.galacticraft.api.rocket.part.RocketPartType;
import dev.galacticraft.api.universe.celestialbody.CelestialBody;
import dev.galacticraft.impl.rocket.RocketDataImpl;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.registry.DynamicRegistryManager;

public interface RocketData {
    static RocketData create(int color, RocketPart cone, RocketPart body, RocketPart fin, RocketPart booster, RocketPart bottom, RocketPart upgrade) {
        return new RocketDataImpl(color, cone, body, fin, booster, bottom, upgrade);
    }

    static RocketData fromTag(NbtCompound tag, DynamicRegistryManager manager) {
        return RocketDataImpl.fromTag(tag, manager);
    }

    NbtCompound toTag(DynamicRegistryManager manager, NbtCompound tag);

    int color();

    int getRed();

    int getGreen();

    int getBlue();

    int getAlpha();

    RocketPart cone();

    RocketPart body();

    RocketPart fin();

    RocketPart booster();

    RocketPart bottom();

    RocketPart upgrade();

    boolean isEmpty();

    boolean canTravelTo(DynamicRegistryManager manager, CelestialBody<?, ?> celestialBodyType);

    RocketPart getPartForType(RocketPartType type);

    RocketPart[] getParts();
}
