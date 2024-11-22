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

/**
 * An interface for traversing through enum values. This interface provides methods
 * to navigate to the next and previous values in an enum sequence.
 *
 * @param <T> The type of the enum that implements this interface.
 *
 * <p>Implementing classes should ensure that the enum values are traversed in a
 * circular manner, i.e., after the last enum value, the next value should be the
 * first enum value, and before the first enum value, the previous value should be
 * the last enum value.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * public enum Direction implements IEnumTraversable<Direction> {
 *     NORTH, EAST, SOUTH, WEST;
 *
 *     public Direction next() {
 *         return values()[(ordinal() + 1) % values().length];
 *     }
 *
 *     public Direction previous() {
 *         return values()[(ordinal() - 1 + values().length) % values().length];
 *     }
 * }
 * }
 * </pre>
 *
 * <p>This interface is particularly useful for enums that represent cyclic sequences,
 * such as directions, days of the week, etc.</p>
 *
 * @author TurtyWurty
 */
@SuppressWarnings("unused")
public interface IEnumTraversable<T extends Enum<?>>
{
    /**
     * Retrieves the next enum value in the sequence. If the current value is the last
     * in the sequence, this method should return the first enum value.
     *
     * @return The next enum value in the sequence.
     */
    T next();

    /**
     * Retrieves the previous enum value in the sequence. If the current value is the first
     * in the sequence, this method should return the last enum value.
     *
     * @return The previous enum value in the sequence.
     */
    T previous();
}