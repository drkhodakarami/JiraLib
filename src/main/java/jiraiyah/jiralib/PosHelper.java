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

package jiraiyah.jiralib;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

/**
 * This class provides helper methods for working with block positions.
 *
 * @author Jiraiyah
 */
@SuppressWarnings("unused")
public class PosHelper
{
    /**
     * Returns the position that is one block in the given direction from the given position.
     *
     * @param pos       The position to move from.
     * @param direction The direction to move in.
     *
     * @return The new position.
     */
    public static BlockPos positionDirectTo(BlockPos pos, Direction direction)
    {
        return pos.offset(direction);
    }

    /**
     * Returns an array of positions that are one block in each direction from the given position.
     *
     * @param pos The position to move from.
     *
     * @return An array of new positions.
     */
    public static BlockPos[] positionNextTo(BlockPos pos)
    {
        return new BlockPos[]{pos.up(), pos.down(), pos.east(), pos.west(), pos.north(), pos.south()};
    }

    /**
     * Returns an array of positions that are one block in each direction from the given position, excluding top and bottom..
     *
     * @param pos The position to move from.
     *
     * @return An array of new positions.
     */
    public static BlockPos[] positionSideTo(BlockPos pos)
    {
        return new BlockPos[]{pos.east(), pos.west(), pos.north(), pos.south()};
    }

    /**
     * Returns an array of positions that are one block in each direction from the given position, excluding top.
     *
     * @param pos The position to move from.
     *
     * @return An array of new positions.
     */
    public static BlockPos[] positionNextNotTop(BlockPos pos)
    {
        return new BlockPos[]{pos.down(), pos.east(), pos.west(), pos.north(), pos.south()};
    }


    /**
     * Returns an array of positions that are one block in each direction from the given position, excluding bottom.
     *
     * @param pos The position to move from.
     *
     * @return An array of new positions.
     */
    public static BlockPos[] positionNextNotBottom(BlockPos pos)
    {
        return new BlockPos[]{pos.up(), pos.east(), pos.west(), pos.north(), pos.south()};
    }

    /**
     * Returns the direction that is to the left of the given facing direction.
     *
     * @param facing The current facing direction.
     * @return The direction to the left of the given facing direction.
     */
    public static Direction left(Direction facing)
    {
        return switch (facing)
        {
            case NORTH -> Direction.WEST;
            case SOUTH -> Direction.EAST;
            case WEST -> Direction.SOUTH;
            case EAST -> Direction.NORTH;
            default -> facing;
        };
    }

    /**
     * Returns the direction that is to the right of the given facing direction.
     *
     * @param facing The current facing direction.
     * @return The direction to the right of the given facing direction.
     */
    public static Direction right(Direction facing)
    {
        return switch (facing)
        {
            case NORTH -> Direction.EAST;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
            case EAST -> Direction.SOUTH;
            default -> facing;
        };
    }

    /**
     * Returns the direction that is directly in front of the given facing direction.
     *
     * @param facing The current facing direction.
     * @return The direction in front of the given facing direction.
     */
    public static Direction front(Direction facing)
    {
        return switch (facing)
        {
            case NORTH -> Direction.SOUTH;
            case SOUTH -> Direction.NORTH;
            case WEST -> Direction.EAST;
            case EAST -> Direction.WEST;
            default -> facing;
        };
    }

    /**
     * Returns the direction that is to the back of the given facing direction.
     *
     * @param facing The current facing direction.
     * @return The direction to the back of the given facing direction.
     */
    public static Direction back(Direction facing)
    {
        return switch (facing)
        {
            case NORTH -> Direction.NORTH;
            case SOUTH -> Direction.SOUTH;
            case WEST -> Direction.WEST;
            case EAST -> Direction.EAST;
            default -> facing;
        };
    }

    /**
     * Returns the position that is one block to the left of the given position with the given block facing.
     *
     * @param pos    The position to move from.
     * @param facing The direction the block is facing.
     *
     * @return The new position.
     */
    public static BlockPos leftBlock(BlockPos pos, Direction facing)
    {
        return pos.offset(left(facing));
    }

    /**
     * Returns the position that is one block to the right of the given position with the given block facing.
     *
     * @param pos    The position to move from.
     * @param facing The direction the block is facing.
     *
     * @return The new position.
     */
    public static BlockPos rightBlock(BlockPos pos, Direction facing)
    {
        return pos.offset(right(facing));
    }

    /**
     * Returns the position that is one block to the back of the given position with the given block facing.
     *
     * @param pos    The position to move from.
     * @param facing The direction the block is facing.
     *
     * @return The new position.
     */
    public static BlockPos backBlock(BlockPos pos, Direction facing)
    {
        return pos.offset(back(facing));
    }

    /**
     * Returns the position that is one block in front of the given position with the given block facing.
     *
     * @param pos    The position to move from.
     * @param facing The direction the block is facing.
     *
     * @return The new position.
     */
    public static BlockPos frontBlock(BlockPos pos, Direction facing)
    {
        return pos.offset(front(facing));
    }

    /**
     * Returns the position that is one block to the top of the given position.
     *
     * @param pos The position to move from.
     *
     * @return The new position.
     */
    public static BlockPos topBlock(BlockPos pos)
    {
        return pos.up();
    }

    /**
     * Returns the position that is one block to the bottom of the given position.
     *
     * @param pos The position to move from.
     *
     * @return The new position.
     */
    public static BlockPos bottomBlock(BlockPos pos)
    {
        return pos.down();
    }

    /**
     * Calculates the relative direction based on the given `direction` and `facing`.
     *
     * <p>This method determines the direction relative to a specified facing direction.
     * If either `direction` or `facing` is null, the method will handle these cases
     * appropriately, potentially returning null or a default value depending on the
     * implementation details.</p>
     *
     * @param direction the initial direction from which the relative direction is calculated.
     *                  This parameter can be null, in which case the behavior should be defined
     *                  by the implementation.
     * @param facing the reference direction to which the relative direction is calculated.
     *               This parameter can also be null, and similar to `direction`, the behavior
     *               should be defined by the implementation.
     * @return the direction that is relative to the given `facing` direction. The return value
     *         may be null if the input parameters are null or if the calculation results in an
     *         undefined direction.
     * @author TurtyWurty
     */
    public static Direction getRelativeDirection(@Nullable Direction direction, @Nullable Direction facing)
    {
        if(direction == null)
            return null;
        else if(facing == null)
            return direction;
        else if(direction.getAxis().isVertical())
            return direction;

        Direction relative = direction;
        for (int i = 0; i < facing.ordinal(); i++) {
            relative = relative.rotateYClockwise();
        }

        return relative;
    }
}