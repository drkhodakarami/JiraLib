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

import net.minecraft.world.World;

/**
 * MathHelper is a class that provides various math helper functions.
 *
 * @author Jiraiyah
 */
@SuppressWarnings("unused")
public class MathHelper
{
    /**
     * Returns a random chance based on the given value.
     *
     * @param value the value to base the chance on
     *
     * @return a random chance between 0 and 1 based on the given value
     */
    public static float getChance(int value)
    {
        return (100 - value) / 100.0f;
    }

    /**
     * Returns a random chance based on the given value.
     *
     * @param world the world to use for random generation
     * @param value the value to base the chance on
     *
     * @return a random chance between 0 and 1 based on the given value
     */
    public static boolean getChance(World world, int value)
    {
        return world.getRandom().nextFloat() >= getChance(value);
    }
}