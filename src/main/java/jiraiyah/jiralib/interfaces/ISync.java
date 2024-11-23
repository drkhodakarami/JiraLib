package jiraiyah.jiralib.interfaces;

/**
 * The {@code ISync} interface provides a contract for implementing synchronization mechanisms.
 * Classes that implement this interface should define the specific behavior of the {@code sync} method,
 * which is intended to ensure that data or state is consistent across different parts of a system.
 *
 * <p>Implementations of this interface might involve updating shared resources, or coordinating state
 * between distributed systems.</p>
 *
 * @author TurtyWurty
 */
public interface ISync
{
    /**
     * Synchronizes the state or data to ensure consistency.
     *
     * <p>This method should be implemented to define the specific synchronization logic required
     * by the implementing class.</p>
     */
    void sync();
}