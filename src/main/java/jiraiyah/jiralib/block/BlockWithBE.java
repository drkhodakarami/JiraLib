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

package jiraiyah.jiralib.block;

import com.mojang.serialization.MapCodec;
import jiraiyah.jiralib.interfaces.ITickBE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * Abstract class representing a block that provides a block entity.
 * <p>
 * This class extends the {@link Block} class and implements the {@link BlockEntityProvider}
 * interface, allowing it to provide a block entity that can perform actions on each tick.
 * It is designed to be subclassed by blocks that require a block entity with ticking behavior.
 * </p>
 * <p>
 * The class also supports serialization through a {@link MapCodec}, which can be used to
 * encode and decode the block's state.
 * </p>
 */
@SuppressWarnings("unused")
public abstract class BlockWithBE extends Block implements BlockEntityProvider
{
    /**
     * A codec for serializing and deserializing the block's state.
     * <p>
     * This codec is used to handle the encoding and decoding of the block's state,
     * allowing it to be saved and loaded efficiently.
     * </p>
     */
    protected MapCodec<? extends BlockWithBE> CODEC;

    /**
     * Constructs a new {@code BlockWithBE} with the specified settings.
     *
     * @param settings The settings for the block, which define its properties such as
     *                 hardness, resistance, and material.
     */
    public BlockWithBE(Settings settings)
    {
        super(settings);
    }

    /**
     * Returns a ticker for the block entity associated with this block.
     * <p>
     * This method provides a {@link BlockEntityTicker} that is responsible for
     * executing the tick logic of the block entity in the specified world context.
     * </p>
     *
     * @param world The world in which the block entity resides.
     * @param state The current state of the block.
     * @param type  The type of the block entity.
     * @param <T>   The type of the block entity.
     * @return A {@link BlockEntityTicker} for the block entity, or {@code null} if
     *         no ticker is required.
     */
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return ITickBE.createTicker(world);
    }

    /**
     * Returns the codec used for serializing and deserializing the block's state.
     * <p>
     * This method provides access to the codec that handles the encoding and decoding
     * of the block's state, facilitating its persistence.
     * </p>
     *
     * @return The {@link MapCodec} for the block's state.
     */
    @Override
    protected MapCodec<? extends Block> getCodec()
    {
        return CODEC;
    }
}