/*
 * Copyright (c) 2019-2022 Team Galacticraft
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

package dev.galacticraft.api.gametest.rocket.part;

import dev.galacticraft.api.rocket.entity.Rocket;
import dev.galacticraft.api.rocket.part.RocketBooster;
import dev.galacticraft.api.rocket.recipe.RocketPartRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExampleRocketBooster extends RocketBooster {
    private final double maximumVelocity;
    private final double acceleration;
    private final long fuelUsage;
    private final @Nullable RocketPartRecipe recipe;

    public ExampleRocketBooster(double maximumVelocity, double acceleration, long fuelUsage, @Nullable RocketPartRecipe recipe) {
        this.maximumVelocity = maximumVelocity;
        this.acceleration = acceleration;
        this.fuelUsage = fuelUsage;
        this.recipe = recipe;
    }

    @Override
    public double getMaximumVelocity() {
        return this.maximumVelocity;
    }

    @Override
    public double getAccelerationPerTick() {
        return this.acceleration;
    }

    @Override
    public long getFuelUsagePerTick() {
        return this.fuelUsage;
    }

    @Override
    public void tick(@NotNull Rocket rocket) {
    }

    @Override
    public @Nullable RocketPartRecipe getRecipe() {
        return this.recipe;
    }
}
