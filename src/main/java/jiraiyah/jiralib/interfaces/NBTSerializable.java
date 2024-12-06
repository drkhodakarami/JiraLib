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

import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;

/**
 * An interface that allows for easy serialization and deserialization of objects to NBT.
 *
 * @param <T> The type of NBT element that will be used to serialize and deserialize the object.
 *
 *            <br>
 *            <h4>Example usage:
 *            <pre>{@code
 *                                            public class Example implements NBTSerializable<NbtCompound> {
 *                                               private int exampleInt;
 *                                               private String exampleString;
 *
 *                                               public Example(int exampleInt, String exampleString) {
 *                                                   this.exampleInt = exampleInt;
 *                                                   this.exampleString = exampleString;
 *                                               }
 *
 *                                               @Override
 *                                               public NbtCompound writeNbt(RegistryWrapper.WrapperLookup registryLookup) {
 *                                                   NbtCompound nbt = new NbtCompound();
 *                                                   nbt.putInt("exampleInt", this.exampleInt);
 *                                                   nbt.putString("exampleString", this.exampleString);
 *                                                   return nbt;
 *                                               }
 *
 *                                               @Override
 *                                               public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
 *                                                   this.exampleInt = nbt.getInt("exampleInt");
 *                                                   this.exampleString = nbt.getString("exampleString");
 *                                               }
 *                                            }
 *                                        }</pre>
 *
 * @author TurtyWurty
 */
@SuppressWarnings("unused")
public interface NBTSerializable<T extends NbtElement>
{
    /**
     * Serializes the current state of the implementing object into an NBT element.
     * This method is crucial for converting the object's data into a structured format
     * that can be easily stored, transferred, and reconstructed within the Minecraft
     * environment using the NBT system.
     * <p>The serialization process involves encoding the object's properties and any
     * relevant metadata into an NBT element, which is a hierarchical data structure
     * used extensively in Minecraft for data storage and manipulation.
     * <p>Implementers of this method should ensure that all relevant fields and properties
     * of the object are included in the NBT element. Additionally, care should be taken
     * to maintain compatibility with the NBT format and any versioning requirements
     * that may exist within the application.
     *
     * @param registryLookup an instance of {@link RegistryWrapper.WrapperLookup} that
     *                       provides access to the game's registry data. This parameter
     *                       is essential for resolving any dependencies or references
     *                       that the object may have within the game's ecosystem. It
     *                       ensures that all necessary data is accurately captured and
     *                       linked during the serialization process.
     * @return an NBT element representing the serialized state of the object. This
     *         element encapsulates all the necessary information to reconstruct the
     *         object at a later time or in a different context. It is particularly
     *         useful for saving the object's state to disk or transmitting it over
     *         a network.
     */
    T writeNbt(RegistryWrapper.WrapperLookup registryLookup);

    /**
     * Deserializes the state of the implementing object from the provided NBT element.
     * This method is responsible for reconstructing the object's state by extracting
     * and interpreting the data stored within the NBT element. It is a critical part
     * of the serialization process, allowing objects to be restored to their original
     * state after being saved or transmitted.
     * <p>The deserialization process involves reading the hierarchical data structure
     * of the NBT element and mapping its contents back to the object's fields and
     * properties. This ensures that the object can be accurately reconstructed with
     * all its original data intact.
     * <p>Implementers of this method should ensure that all relevant fields and properties
     * of the object are correctly populated from the NBT element. Additionally, care
     * should be taken to handle any potential discrepancies or versioning issues that
     * may arise from changes in the NBT format or the object's structure over time.
     *
     * @param nbt an instance of the NBT element from which the object's data will be
     *            read. This element contains the serialized data that represents the
     *            object's state. It is expected to have been created by a corresponding
     *            call to the `writeNbt` method.
     * @param registryLookup an instance of {@link RegistryWrapper.WrapperLookup} that
     *                       provides access to the game's registry data. This parameter
     *                       is essential for resolving any dependencies or references
     *                       that the object may have within the game's ecosystem during
     *                       the deserialization process. It ensures that all necessary
     *                       data is accurately interpreted and linked.
     */
    void readNbt(T nbt, RegistryWrapper.WrapperLookup registryLookup);
}