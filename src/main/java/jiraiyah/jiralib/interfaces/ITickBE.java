/***********************************************************************************
 * Copyright (c) 2024 Alireza Khodakarami (Jiraiyah)                               *
 * ------------------------------------------------------------------------------- *
 * MIT License                                                                     *
 * =============================================================================== *
 * Permission is hereby granted, free of charge, to any person obtaining a copy    *
 * of this software and associated documentation files (the "Software"), to deal   *
 * in the Software without restriction, including without limitation the rights    *
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell       *
 * copies of the Software, and to permit persons to whom the Software is           *
 * furnished to do so, subject to the following conditions:                        *
 * ------------------------------------------------------------------------------- *
 * The above copyright notice and this permission notice shall be included in all  *
 * copies or substantial portions of the Software.                                 *
 * ------------------------------------------------------------------------------- *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR      *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,        *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE     *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER          *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,   *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE   *
 * SOFTWARE.                                                                       *
 ***********************************************************************************/

package jiraiyah.jiralib.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * Interface for block entities that need to tick every tick.
 *
 * @author TurtyWurty
 */
@SuppressWarnings("unused")
public interface ITickBE
{
    /**
     * The tick method that will be called every tick (20 times per second).
     */
    void tick();

    /**
     * Creates a ticker for a block entity in the specified world.
     *
     * <p>This method is used to generate a {@link BlockEntityTicker} for a block entity type
     * that requires periodic updates. The ticker will be responsible for executing the
     * tick logic of the block entity within the given {@link World} context.</p>
     *
     * @param world the {@link World} in which the block entity resides. This parameter
     *              provides the context for the ticker's operation, including access to
     *              the world's state and other entities.
     * @param <T>   the type of the block entity for which the ticker is being created.
     *              This ensures type safety by associating the ticker with a specific
     *              block entity type.
     * @return a {@link BlockEntityTicker} for the specified block entity type, or
     *         {@code null} if no ticker is required for the block entity in the given world.
     */
    static <T extends BlockEntity> BlockEntityTicker<T> createTicker(World world)
    {
        return !world.isClient ? (pworld, pos, state, entity) -> ((ITickBE) entity).tick() : null;
    }

    /**
     * Returns the block state at the given position in the given world, or null if the world is null.
     *
     * @param world The world to get the block state from.
     * @param pos   The position to get the block state from.
     *
     * @return The block state at the given position in the given world, or null if the world is null.
     *
     * @author Jiraiyah
     */
    @Nullable
    default BlockState getState(World world, BlockPos pos)
    {
        return world != null ? world.getBlockState(pos) : null;
    }
}