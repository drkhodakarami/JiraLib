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

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.Rect2i;

/**
 * The `InfoArea` class extends `DrawContext` to provide a specialized area for rendering information
 * within a specified rectangular region. This class is designed to be used in the context of a Minecraft
 * client, leveraging the game's rendering capabilities.
 *
 * <p>It supports two constructors, allowing for the creation of an `InfoArea` with a default or specified
 * rectangular area. The class is intended to be extended or used where custom drawing logic is required
 * within a defined area.</p>
 */
@SuppressWarnings("unused")
public class InfoArea extends DrawContext
{
    /**
     * The rectangular area within which the information will be rendered.
     * This is defined using the `Rect2i` class, which specifies the position and size of the area.
     */
    protected final Rect2i area;

    /**
     * Constructs an `InfoArea` with a default rectangular area of size zero.
     * This constructor initializes the `InfoArea` with a default area, which can be useful when the
     * area is intended to be set or modified later.
     *
     * @param client The `MinecraftClient` instance used for rendering.
     * @param vertexConsumers The `VertexConsumerProvider.Immediate` instance for managing vertex data.
     */
    public InfoArea(MinecraftClient client, VertexConsumerProvider.Immediate vertexConsumers)
    {
        super(client, vertexConsumers);
        this.area = new Rect2i(0, 0, 0, 0);
    }

    /**
     * Constructs an `InfoArea` with a specified rectangular area.
     * This constructor allows for the creation of an `InfoArea` with a predefined area, enabling
     * immediate use of the specified dimensions for rendering.
     *
     * @param client The `MinecraftClient` instance used for rendering.
     * @param vertexConsumers The `VertexConsumerProvider.Immediate` instance for managing vertex data.
     * @param area The `Rect2i` instance defining the position and size of the rendering area.
     */
    public InfoArea(MinecraftClient client, VertexConsumerProvider.Immediate vertexConsumers, Rect2i area)
    {
        super(client, vertexConsumers);
        this.area = area;
    }

    /**
     * Draws the content within the specified `DrawContext`.
     * This method is intended to be overridden to provide custom drawing logic within the defined area.
     * By default, this method does not perform any drawing operations.
     *
     * @param context The `DrawContext` within which the drawing operations will be performed.
     */
    public void draw(DrawContext context)
    {}
}