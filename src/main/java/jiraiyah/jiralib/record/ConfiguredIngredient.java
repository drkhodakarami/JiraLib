package jiraiyah.jiralib.record;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings({"ObjectEquality", "EqualsBetweenInconvertibleTypes", "unused"})
public record ConfiguredIngredient(RegistryEntryList<Item> entries, StackData stackData)
{
    public static final Codec<ConfiguredIngredient> CODEC = Codec.lazyInitialized(() -> RecordCodecBuilder.create(
            instance -> instance.group(
                    Ingredient.ENTRIES_CODEC.fieldOf("entries").forGetter(ConfiguredIngredient::entries),
                    StackData.CODEC.optionalFieldOf("stack_data", StackData.EMPTY).forGetter(ConfiguredIngredient::stackData)
            ).apply(instance, ConfiguredIngredient::new)
    ));

    public static final PacketCodec<RegistryByteBuf, ConfiguredIngredient> PACKET_CODEC =
            PacketCodec.tuple(PacketCodecs.registryEntryList(RegistryKeys.ITEM), ConfiguredIngredient::entries,
                              StackData.PACKET_CODEC, ConfiguredIngredient::stackData,
                              ConfiguredIngredient::new);

    public static final ConfiguredIngredient EMPTY = new ConfiguredIngredient(RegistryEntryList.of(), StackData.EMPTY);

    public ConfiguredIngredient(RegistryEntryList<Item> entries, int count)
    {
        this(entries, StackData.create(count));
    }

    @SuppressWarnings("deprecation")
    public ConfiguredIngredient(int count, Item... items)
    {
        this(RegistryEntryList.of(Arrays.stream(items)
                                        .map(Item::getRegistryEntry).toList()),
             StackData.create(count));
    }

    public ConfiguredIngredient(RegistryEntryList<Item> entries, int count, ComponentChanges components)
    {
        this(entries, StackData.create(count, components));
    }

    public ConfiguredIngredient(int count, ComponentChanges components, Item... items)
    {
        this(RegistryEntryList.of(Arrays.stream(items)
                                        .map(Item::getRegistryEntry).toList()),
             StackData.create(count, components));
    }

    public List<ItemStack> getMatchingStacks()
    {
        return this.entries
                .stream()
                .map(item ->
                             new ItemStack(item, this.stackData.count(),
                                           this.stackData.components())).toList();
    }

    public boolean testForRecipe(ItemStack stack)
    {
        return test(stack, countLessThanOrEquals(stack.getCount()));
    }

    public boolean test(ItemStack stack, boolean matchCount, boolean matchComponents)
    {
        return this.entries
                .stream()
                .anyMatch(item ->
                                  stack.getItem() == item &&
                                  (!matchCount || stack.getCount() == this.stackData.count()) &&
                                  (!matchComponents || this.stackData.components()
                                                                     .equals(stack.getComponentChanges())));
    }

    public boolean test(ItemStack stack)
    {
        return test(stack, true, true);
    }

    public boolean test(ItemStack stack, Predicate<Integer> countPredicate)
    {
        return this.entries
                .stream()
                .anyMatch(item ->
                                  stack.getItem() == item.value() &&
                                  countPredicate.test(stackData().count()) &&
                                  this.stackData.components().equals(stack.getComponentChanges()));
    }

    public static Predicate<Integer> countEquals(int count)
    {
        return value -> value == count;
    }

    public static Predicate<Integer> countLessThan(int count)
    {
        return value -> value < count;
    }

    public static Predicate<Integer> countLessThanOrEquals(int count)
    {
        return value -> value <= count;
    }

    public static Predicate<Integer> countGreaterThan(int count)
    {
        return value -> value > count;
    }

    public static Predicate<Integer> countGreaterThanOrEquals(int count)
    {
        return value -> value >= count;
    }

    public SlotDisplay toDisplay()
    {
        if(isEmpty())
            return SlotDisplay.EmptySlotDisplay.INSTANCE;

        return new SlotDisplay.CompositeSlotDisplay(
                getMatchingStacks().stream()
                                   .map(SlotDisplay.StackSlotDisplay::new)
                                   .map(SlotDisplay.class::cast)
                                   .toList());
    }

    public boolean isEmpty()
    {
        return this == EMPTY;
    }
}