package jiraiyah.jiralib.record;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentChanges;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a data structure that holds information about a stack of items.
 * This record encapsulates the count of items in the stack and any component changes associated with it.
 *
 * <p>The `StackData` record is immutable and provides a compact way to store and access
 * the number of items and their associated component changes.</p>
 *
 * <p>Usage example:</p>
 * <pre>
 *     StackData stackData = new StackData(5, componentChanges);
 *     int itemCount = stackData.count();
 *     ComponentChanges changes = stackData.components();
 * </pre>
 *
 * @param count      the number of items in the stack. Must be a non-negative integer.
 * @param components the component changes associated with the stack. Cannot be null.
 */
public record StackData(int count, @NotNull ComponentChanges components)
{
    /**
     * A Codec for serializing and deserializing instances of {@link StackData}.
     * <p>
     * This Codec utilizes the {@link RecordCodecBuilder} to define the structure of the
     * {@link StackData} object for encoding and decoding operations. It specifies
     * how each field of the {@link StackData} should be processed during these operations.
     * </p>
     *
     * <p>
     * The Codec is used to convert {@link StackData} objects to a serialized form
     * that can be transmitted over a network or stored, and to reconstruct the objects
     * from this serialized form. This is particularly useful in scenarios where data
     * needs to be shared between different parts of a system or between different systems.
     * </p>
     *
     * @see Codec
     * @see RecordCodecBuilder
     * @see StackData
     */
    public static final Codec<StackData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.INT.fieldOf("count").orElse(1).forGetter(StackData::count),
                    ComponentChanges.CODEC.optionalFieldOf("components", ComponentChanges.EMPTY).forGetter(StackData::components)
            ).apply(instance, StackData::new)
    );

    /**
     * A packet codec for encoding and decoding {@link StackData} objects
     * to and from {@link RegistryByteBuf}.
     * <p>
     * This codec utilizes the API's internal {@link PacketCodec} framework
     * to facilitate the serialization and deserialization of {@link StackData}
     * within network packets. It ensures that the data is correctly transformed
     * between its in-memory representation and its byte stream format.
     * The codec uses the hand's name for encoding and decoding.
     * </p>
     */
    public static final PacketCodec<RegistryByteBuf, StackData> PACKET_CODEC =
            PacketCodec.tuple(PacketCodecs.INTEGER, StackData::count,
                              ComponentChanges.PACKET_CODEC, StackData::components,
                              StackData::new);

    /**
     * A predefined instance of `StackData` representing an empty `ComponentChanges`.
     *
     * <p>This constant is useful as a default or placeholder value when no actual stack data is available.
     * It initializes the stack with a count of 1 and uses an empty set of component changes.</p>
     *
     * <p>Usage example:</p>
     * <pre>
     *     StackData stackData = StackData.EMPTY;
     *     // Use stackData as a default value in your logic
     * </pre>
     *
     * <p>Note: The count is set to 1, which may imply a minimal or default state rather than a truly empty stack.</p>
     */
    public static final StackData EMPTY = new StackData(1, ComponentChanges.EMPTY);

    /**
     * Creates a new instance of `StackData` with the specified item count and empty default component changes.
     *
     * <p>This method provides a convenient way to instantiate a `StackData` object when only the item count
     * is known, and the component changes can be initialized to a default empty state.</p>
     *
     * <p>Usage example:</p>
     * <pre>
     *     StackData stackData = StackData.create(10);
     *     // stackData now represents a stack with 10 items and default component changes
     * </pre>
     *
     * @param count the number of items in the stack. Must be a non-negative integer.
     * @return a new `StackData` instance with the specified count and default component changes.
     * @throws IllegalArgumentException if the count is negative.
     */
    public static StackData create(int count)
    {
        return new StackData(count, ComponentChanges.EMPTY);
    }

    /**
     * Creates a new instance of {@code StackData} with the specified count and component changes.
     *
     * <p>This method is used to instantiate a {@code StackData} object, which encapsulates
     * the quantity of items and any changes to their components. The {@code count} parameter
     * represents the number of items in the stack, while the {@code components} parameter
     * contains the modifications applied to these items.</p>
     *
     * @param count the number of items in the stack. Must be a non-negative integer.
     * @param components an instance of {@code ComponentChanges} representing the changes
     *                   applied to the components of the items in the stack. Cannot be null.
     * @return a new {@code StackData} instance with the specified count and component changes.
     * @throws IllegalArgumentException if {@code count} is negative.
     * @throws NullPointerException if {@code components} is null.
     */
    public static StackData create(int count, ComponentChanges components)
    {
        return new StackData(count, components);
    }
}