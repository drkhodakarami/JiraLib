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

package jiraiyah.jiralib.client;

/**
 * Utility class for handling mouse interactions within a specified rectangular area.
 * Provides methods to determine if the mouse cursor is over a given area.
 */
@SuppressWarnings("unused")
public class MouseHelper
{
    /**
     * Determines if the mouse cursor is over a specified rectangular area.
     *
     * @param mouseX  The x-coordinate of the mouse cursor.
     * @param mouseY  The y-coordinate of the mouse cursor.
     * @param x       The x-coordinate of the top-left corner of the rectangle.
     * @param y       The y-coordinate of the top-left corner of the rectangle.
     * @param width   The width of the rectangle.
     * @param height  The height of the rectangle.
     * @param offsetX The horizontal offset to apply to the rectangle's position.
     * @param offsetY The vertical offset to apply to the rectangle's position.
     * @return true if the mouse cursor is within the bounds of the rectangle, false otherwise.
     */
    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y, int width, int height, int offsetX, int offsetY)
    {
        return mouseX >= x + offsetX &&
               mouseX <= x + offsetX + width &&
               mouseY >= y + offsetY &&
               mouseY <= y + offsetY + height;
    }

    /**
     * Determines if the mouse cursor is over a default 16x16 rectangular area.
     * This method is a convenience overload for checking a standard-sized area,
     * typically used for small UI elements like buttons or icons.
     *
     * @param mouseX The x-coordinate of the mouse cursor.
     * @param mouseY The y-coordinate of the mouse cursor.
     * @param x      The x-coordinate of the top-left corner of the rectangle.
     * @param y      The y-coordinate of the top-left corner of the rectangle.
     * @return true if the mouse cursor is within the bounds of the 16x16 rectangle, false otherwise.
     */
    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y)
    {
        return isMouseOver(mouseX, mouseY, x, y, 16, 16, 0, 0);
    }

    /**
     * Determines if the mouse cursor is over a square area of a specified size.
     * This method checks if the cursor's position falls within the bounds of a square
     * defined by its top-left corner coordinates and size. It is useful for detecting
     * interactions with square UI elements.
     *
     * @param mouseX The x-coordinate of the mouse cursor.
     * @param mouseY The y-coordinate of the mouse cursor.
     * @param x      The x-coordinate of the top-left corner of the square.
     * @param y      The y-coordinate of the top-left corner of the square.
     * @param size   The size of the square's sides.
     * @return true if the mouse cursor is within the bounds of the square, false otherwise.
     */
    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y, int size)
    {
        return isMouseOver(mouseX, mouseY, x, y, size, size, 0, 0);
    }

    /**
     * Determines if the mouse cursor is over a specified rectangular area.
     * This method checks if the cursor's position falls within the bounds of a rectangle
     * defined by its top-left corner coordinates, width, and height. It is useful for detecting
     * interactions with rectangular UI components.
     *
     * @param mouseX The x-coordinate of the mouse cursor.
     * @param mouseY The y-coordinate of the mouse cursor.
     * @param x      The x-coordinate of the top-left corner of the rectangle.
     * @param y      The y-coordinate of the top-left corner of the rectangle.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     * @return true if the mouse cursor is within the bounds of the rectangle, false otherwise.
     */
    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y, int width, int height)
    {
        return isMouseOver(mouseX, mouseY, x, y, width, height, 0, 0);
    }
}