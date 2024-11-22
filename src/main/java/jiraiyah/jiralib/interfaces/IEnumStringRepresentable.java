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

import java.util.Objects;

/**
 * An interface for enums that can be represented as a string.
 * This interface provides methods to retrieve enum constants based on their string representation.
 * It is useful for converting between enums and their string names, especially when dealing with serialization or user input.
 * Implementing classes should ensure that the `getSerializedName` method returns a unique string for each enum constant.
 *
 * @see Enum
 * @see Objects
 * @see IEnumStringRepresentable#getSerializedName()
 * @see IEnumStringRepresentable#getEnumByName(Class, String)
 * @see IEnumStringRepresentable#getEnumByName(Enum[], String)
 *
 * @version 1.0
 * @since 2023
 *
 * @author TurtyWurty
 */
@SuppressWarnings("unused")
public interface IEnumStringRepresentable
{
    /**
     * Returns the enum object represented by the string.
     * This method attempts to find an enum constant within the specified enum class that matches the given string representation.
     *
     * @param enumClass      The class of the enum. Must not be null.
     * @param serializedName The string representation of the enum. Must not be null.
     * @param <T>            The type of the enum.
     *
     * @return The enum object represented by the string, or null if no matching enum constant is found.
     *
     * @throws NullPointerException if either `enumClass` or `serializedName` is null.
     */
    static <T extends Enum<?> & IEnumStringRepresentable> T getEnumByName(Class<T> enumClass, String serializedName)
    {
        return getEnumByName(enumClass.getEnumConstants(), serializedName);
    }

    /**
     * Returns the enum object represented by the string.
     * This method iterates over the provided array of enum constants to find a match for the given string representation.
     *
     * @param enumConstants  The array of enum constants. Must not be null.
     * @param serializedName The string representation of the enum. Must not be null.
     * @param <T>            The type of the enum.
     *
     * @return The enum object represented by the string, or null if no matching enum constant is found.
     *
     * @throws NullPointerException if either `enumConstants` or `serializedName` is null.
     */
    static <T extends Enum<?> & IEnumStringRepresentable> T getEnumByName(T[] enumConstants, String serializedName)
    {
        for (T value : enumConstants)
            if (Objects.equals(value.getSerializedName(), serializedName))
                return value;
        return null;
    }

    /**
     * Retrieves the unique string representation of this enum constant.
     * This method is intended to provide a consistent and unique identifier for each enum constant,
     * which can be used for serialization, deserialization, or display purposes.
     * Implementing classes should ensure that the returned string is unique across all constants of the enum.
     * This is particularly useful when enums need to be stored or transmitted as strings, such as in configuration files or network protocols.
     *
     * @return A non-null, unique string representation of this enum constant.
     */
    String getSerializedName();
}