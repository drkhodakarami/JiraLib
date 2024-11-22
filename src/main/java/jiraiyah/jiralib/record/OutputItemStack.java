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

package jiraiyah.jiralib.record;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import jiraiyah.jiralib.network.ExtraPacketCodecs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.floatprovider.FloatProviderType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.IntProviderType;
import net.minecraft.util.math.random.Random;

import java.util.stream.IntStream;

/**
 * Represents an item stack that can be outputted from a machine.
 *
 * @param item   The item to output.
 * @param count  The count of the item to output.
 * @param chance The chance of the item to output.
 *
 * @author TurtyWurty
 * @see IntProvider
 * @see FloatProvider
 */
@SuppressWarnings("unused")
public record OutputItemStack(Item item, IntProvider count, FloatProvider chance)
{
    /**
     * Represents an empty {@link OutputItemStack} with no item, count, or chance.
     * This constant can be used to signify the absence of an item stack output.
     */
    public static final OutputItemStack EMPTY = new OutputItemStack(ItemStack.EMPTY);

    /**
     * A {@link MapCodec} for serializing and deserializing {@link OutputItemStack} objects.
     * <p>
     * This codec uses {@link RecordCodecBuilder} to define the structure of the
     * {@link OutputItemStack} record, specifying how each field should be encoded
     * and decoded. The `item` field is encoded using the item registry codec,
     * the `count` field is encoded using {@link IntProvider#VALUE_CODEC},
     * and the `chance` field is encoded using {@link FloatProvider#VALUE_CODEC}.
     * </p>
     */
    public static final MapCodec<OutputItemStack> CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Registries.ITEM.getCodec().fieldOf("item").forGetter(OutputItemStack::item),
                    IntProvider.VALUE_CODEC.fieldOf("count").forGetter(OutputItemStack::count),
                    FloatProvider.VALUE_CODEC.fieldOf("chance").forGetter(OutputItemStack::chance)
            ).apply(instance, OutputItemStack::new));

    /**
     * A {@link PacketCodec} for encoding and decoding {@link OutputItemStack} objects
     * to and from a {@link RegistryByteBuf}.
     * <p>
     * This codec utilizes the organization's internal {@link PacketCodec} framework
     * to facilitate the serialization and deserialization of {@link OutputItemStack}
     * within network packets. It ensures that the data is correctly transformed
     * between its in-memory representation and its byte stream format.
     * </p>
     */
    public static final PacketCodec<RegistryByteBuf, OutputItemStack> PACKET_CODEC =
            PacketCodec.ofStatic(OutputItemStack::encode, OutputItemStack::decode);

    /**
     * The default chance value for an {@link OutputItemStack} when no specific chance is provided.
     * This is a constant float provider that always returns a chance of 1.0F, indicating certainty.
     */
    private static final ConstantFloatProvider DEFAULT_CHANCE = ConstantFloatProvider.create(1.0F);

    public OutputItemStack
    {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null");
        if (count == null)
            throw new IllegalArgumentException("Count cannot be null");
        if (chance == null)
            throw new IllegalArgumentException("Chance cannot be null");
    }

    /**
     * Creates a new OutputItemStack with the given item, count, and chance.
     *
     * @param item   The item to output.
     * @param count  The count of the item to output.
     * @param chance The chance of the item to output.
     */
    public OutputItemStack(Item item, int count, float chance)
    {
        this(item, ConstantIntProvider.create(count), ConstantFloatProvider.create(chance));
    }

    /**
     * Creates a new OutputItemStack with the given item stack.
     *
     * @param stack The item stack to output.
     *
     * @see ItemStack
     */
    public OutputItemStack(ItemStack stack)
    {
        this(stack.getItem(), ConstantIntProvider.create(stack.getCount()), DEFAULT_CHANCE);
    }

    /**
     * Creates a new OutputItemStack with the given item and count.
     *
     * @param item   The item to output.
     * @param count  The count of the item to output.
     * @param chance The chance of the item to output.
     *
     * @see IntProvider
     */
    public OutputItemStack(Item item, IntProvider count, float chance)
    {
        this(item, count, ConstantFloatProvider.create(chance));
    }

    /**
     * Encodes the given {@link OutputItemStack} into a {@link RegistryByteBuf}.
     * <p>
     * This method writes the item, count, and chance fields of the {@link OutputItemStack}
     * into the buffer using the appropriate registry keys and codecs. It ensures that
     * the data is serialized correctly for network transmission.
     * </p>
     *
     * @param buf   The {@link RegistryByteBuf} to encode the {@link OutputItemStack} into.
     * @param stack The {@link OutputItemStack} to encode.
     */
    private static void encode(RegistryByteBuf buf, OutputItemStack stack)
    {
        buf.writeRegistryKey(Registries.ITEM.getKey(stack.item()).orElseThrow());

        Registries.INT_PROVIDER_TYPE.getKey(stack.count().getType()).ifPresent(buf::writeRegistryKey);
        ExtraPacketCodecs.encode(buf, stack.count());

        Registries.FLOAT_PROVIDER_TYPE.getKey(stack.chance().getType()).ifPresent(buf::writeRegistryKey);
        ExtraPacketCodecs.encode(buf, stack.chance());
    }

    /**
     * Decodes an {@link OutputItemStack} from a {@link RegistryByteBuf}.
     * <p>
     * This method reads the item, count, and chance fields from the buffer using
     * the appropriate registry keys and codecs. It reconstructs the {@link OutputItemStack}
     * from its serialized form.
     * </p>
     *
     * @param buf The {@link RegistryByteBuf} to decode the {@link OutputItemStack} from.
     * @return The decoded {@link OutputItemStack}.
     */
    private static OutputItemStack decode(RegistryByteBuf buf)
    {
        Item item = Registries.ITEM.get(buf.readRegistryKey(RegistryKeys.ITEM));

        RegistryKey<IntProviderType<?>> countType = buf.readRegistryKey(RegistryKeys.INT_PROVIDER_TYPE);
        IntProviderType<?> countTypeInstance = Registries.INT_PROVIDER_TYPE.get(countType);
        IntProvider count = ExtraPacketCodecs.decode(buf, countTypeInstance);

        RegistryKey<FloatProviderType<?>> chanceType = buf.readRegistryKey(RegistryKeys.FLOAT_PROVIDER_TYPE);
        FloatProviderType<?> chanceTypeInstance = Registries.FLOAT_PROVIDER_TYPE.get(chanceType);
        FloatProvider chance = ExtraPacketCodecs.decode(buf, chanceTypeInstance);

        return new OutputItemStack(item, count, chance);
    }

    /**
     * Constructs an {@link ItemStack} from this OutputItemStack.
     *
     * @param random The random number generator.
     *               Used to determine the count and chance of the item.
     *
     * @return The constructed ItemStack.
     *
     * @apiNote If the chance of the item is less than the random number generated,
     * then an empty ItemStack will be returned.
     * @see ItemStack
     * @see Random
     */
    public ItemStack createStack(Random random)
    {
        return this.chance.get(random) < random.nextFloat() ?
               ItemStack.EMPTY :
               new ItemStack(this.item, this.count.get(random));
    }

    /**
     * Converts this {@link OutputItemStack} into a {@link SlotDisplay} representation.
     * <p>
     * This method creates a composite slot display that represents the possible item stacks
     * that can be generated from this {@link OutputItemStack}. It iterates over the range
     * of possible counts, creating an {@link ItemStack} for each count, and wraps each
     * in a {@link SlotDisplay.StackSlotDisplay}. The resulting displays are collected
     * into a composite display.
     * </p>
     *
     * @return A {@link SlotDisplay} that visually represents the potential outputs
     *         of this {@link OutputItemStack}.
     *
     * @see SlotDisplay
     * @see SlotDisplay.CompositeSlotDisplay
     * @see SlotDisplay.StackSlotDisplay
     */
    public SlotDisplay toDisplay()
    {
        return new SlotDisplay.CompositeSlotDisplay(
                IntStream.range(this.count.getMin(), this.count.getMax() + 1)
                         .mapToObj(count -> new ItemStack(this.item, count))
                         .map(SlotDisplay.StackSlotDisplay::new)
                         .map(SlotDisplay.class::cast)
                         .toList());
    }
}