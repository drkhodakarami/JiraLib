package jiraiyah.jiralib.interfaces;

import jiraiyah.jiralib.blockentity.NoScreenUpdatableBE;

import java.util.List;

/**
 * Interface for block entities that require synchronized ticking.
 *
 * <p>This interface extends {@link ITickBE}, inheriting its tick functionality,
 * and is intended for block entities that need to perform synchronized updates
 * across multiple instances or with other game elements.</p>
 *
 * <p>Implementing classes should provide specific logic for synchronized ticking,
 * ensuring that updates are consistent and coordinated within the game world.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * public class MyBlockEntity extends BlockEntity implements ITickSyncBE {
 *     @Override
 *     public void onTick() {
 *         // Custom tick logic here
 *     }
 * }
 * }</pre>
 *
 * @author TurtyWurty, modified by Jiraiyah
 * @see ITickBE
 * @see jiraiyah.jiralib.blockentity.UpdatableBE
 * @see java.util.List
 */
@SuppressWarnings("unused")
public interface ITickSyncBE extends ITickBE
{
    /**
     * Retrieves a list of {@link ISync} instances associated with this block entity.
     *
     * <p>This method is used to obtain the collection of synchronizable components
     * that are part of the block entity. Each component in the list implements the
     * {@link ISync} interface, allowing it to participate in the synchronization
     * process during the tick cycle.</p>
     *
     * <p>The returned list should contain all elements that require synchronization
     * with other game elements or instances. This ensures that any changes or updates
     * to these components are consistently propagated across the game world.</p>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * List<ISync> syncables = myBlockEntity.getSyncables();
     * syncables.forEach(ISync::sync);
     * }</pre>
     *
     * @return a {@link List} of {@link ISync} instances that need to be synchronized
     * during the tick cycle. The list should not be null, but it may be empty
     * if no components require synchronization.
     */
    List<ISync> getSyncables();

    /**
     * Executes the tick logic for this block entity.
     *
     * <p>This method is called during each tick cycle to perform any necessary updates
     * or operations for the block entity. Implementers should define the specific
     * actions that need to occur during each tick, such as updating state, interacting
     * with other entities, calling `update` method, or managing resources.</p>
     *
     * <p>The `onTick` method is crucial for maintaining the dynamic behavior of the
     * block entity within the game world. It ensures that the entity's state is
     * consistently updated in response to game events or changes in the environment.</p>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * public class MyBlockEntity extends UpdatableBE implements ITickSyncBE {
     *     @Override
     *     public void onTick() {
     *         // Custom tick logic here
     *     }
     * }
     * }</pre>
     *
     * <p>Note: Implementers should ensure that the tick logic is efficient and does
     * not introduce performance bottlenecks, as this method is called frequently.</p>
     */
    void onTick();

    /**
     * Performs the default tick operation for this block entity.
     *
     * <p>This method is invoked during each tick cycle to execute the default
     * behavior associated with the block entity. It provides a base implementation
     * that can be used or overridden by implementing classes to define specific
     * tick-related logic.</p>
     *
     * <p>The `tick` method is essential for ensuring that the block entity's state
     * is updated consistently with each game tick. It may involve operations such
     * as state transitions, interactions with other entities, or resource management.</p>
     *
     * <p>Implementers can override this method to customize the tick behavior,
     * adding additional logic or modifying the default operations as needed.</p>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * public class MyBlockEntity extends UpdatableBE implements ITickSyncBE {
     *     @Override
     *     public void tick() {
     *         super.tick(); // Call the default tick logic
     *         // Additional custom tick logic here
     *     }
     * }
     * }</pre>
     *
     * <p>Note: Ensure that the tick logic is efficient to avoid performance issues,
     * as this method is called frequently during the game loop.</p>
     */
    @Override
    default void tick()
    {
        onTick();
        if (getSyncables() != null && !getSyncables().isEmpty())
            getSyncables().forEach(ISync::sync);

        if (this instanceof NoScreenUpdatableBE updatableBE)
            updatableBE.endTick();
    }
}