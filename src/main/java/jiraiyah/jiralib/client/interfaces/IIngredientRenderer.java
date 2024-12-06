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

package jiraiyah.jiralib.client.interfaces;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Interface `IIngredientRenderer` provides methods for rendering ingredients
 * in a graphical user interface within a Minecraft mod. It defines methods
 * for rendering ingredients at specified positions, retrieving tooltips,
 * and obtaining font renderers for text associated with ingredients.
 *
 * @param <T> The type of ingredient to be rendered. This allows for flexibility
 *            in rendering different types of ingredients, such as items or blocks.
 */
@SuppressWarnings("unused")
public interface IIngredientRenderer<T>
{
    /**
     * Renders the ingredient at the default position (0, 0).
     * This method provides a default implementation for rendering an ingredient
     * at the top-left corner of the rendering context. It delegates the actual
     * rendering to the overloaded {@link #render(MatrixStack, int, int, Object)}
     * method with default coordinates.
     *
     * @param stack The matrix stack used for rendering transformations.
     * @param ingredient The ingredient to render.
     */
    default void render(MatrixStack stack, T ingredient)
    {
        render(stack, 0, 0, ingredient);
    }

    /**
     * Renders the ingredient at the specified position.
     * This method provides a way to render an ingredient at a specific
     * position within the rendering context. The position is specified
     * by the x and y coordinates. The ingredient to be rendered can be
     * null, in which case no rendering should occur.
     *
     * @param stack The matrix stack used for rendering transformations.
     * @param x The x-coordinate position for rendering.
     * @param y The y-coordinate position for rendering.
     * @param ingredient The ingredient to render, which can be null.
     */
    default void render(MatrixStack stack, int x, int y, @Nullable T ingredient)
    {}

    /**
     * Retrieves the tooltip for the specified ingredient.
     * This method is responsible for generating a list of text components
     * that represent the tooltip for a given ingredient. The tooltip can
     * provide additional information about the ingredient, such as its
     * name, description, or any other relevant details.
     *
     * @param ingredient The ingredient for which the tooltip is requested.
     * @param tooltipFlag The context for the tooltip, providing additional information.
     * @param modid The mod identifier for which the tooltip is being generated.
     * @return A list of text components representing the tooltip.
     */
    List<Text> tooltip(T ingredient, Item.TooltipContext tooltipFlag, String modid);

    /**
     * Gets the font renderer used for rendering text associated with the ingredient.
     * This method returns the font renderer instance that should be used
     * for rendering any text related to the ingredient. By default, it
     * returns the text renderer from the provided Minecraft client instance.
     *
     * @param client The Minecraft client instance.
     * @param ingredient The ingredient for which the font renderer is requested.
     * @return The font renderer instance.
     */
    default TextRenderer fontRenderer(MinecraftClient client, T ingredient)
    {
        return client.textRenderer;
    }

    /**
     * Gets the width of the rendered ingredient.
     * This method returns the width in pixels of the area occupied by the
     * rendered ingredient. By default, it returns a fixed width of 16 pixels.
     *
     * @return The width in pixels.
     */
    default int width()
    {
        return 16;
    }

    /**
     * Gets the height of the rendered ingredient.
     * This method returns the height in pixels of the area occupied by the
     * rendered ingredient. By default, it returns a fixed height of 16 pixels.
     *
     * @return The height in pixels.
     */
    default int height()
    {
        return 16;
    }
}