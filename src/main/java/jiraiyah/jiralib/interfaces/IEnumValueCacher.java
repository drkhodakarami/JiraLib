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
 * Interface for classes that cache the values of an enum.
 * <p>
 * This interface provides a method to retrieve cached values of an enum type.
 * Implementing classes should ensure that the enum values are cached efficiently
 * to improve performance when accessing them multiple times.
 * </p>
 *
 * @param <T> the type of the enum
 *
 * @author TurtyWurty
 */
@SuppressWarnings("unused")
public interface IEnumValueCacher<T extends Enum<?>>
{
    /**
     * Retrieves the cached values of the enum.
     * <p>
     * This method should return an array containing all the values of the enum
     * that have been cached. The caching mechanism should ensure that the values
     * are stored in a way that allows quick retrieval.
     * </p>
     *
     * @return an array of the cached enum values
     */
    T[] getValues();
}