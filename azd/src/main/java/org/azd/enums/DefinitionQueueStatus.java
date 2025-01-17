package org.azd.enums;

/***
 * A value that indicates whether builds can be queued against this definition.
 */
public enum DefinitionQueueStatus {
    /***
     * When disabled the definition queue will not allow builds to be queued by
     * users and the system will not queue scheduled, gated or continuous integration builds.
     * Builds already in the queue will not be started by the system.
     */
    DISABLED,
    /***
     * When enabled the definition queue allows builds to be queued by users,
     * the system will queue scheduled, gated and continuous integration builds,
     * and the queued builds will be started by the system.
     */
    ENABLED,
    /***
     * When paused the definition queue allows builds to be queued by users and the system
     * will queue scheduled, gated and continuous integration builds. Builds in the queue will not be started by the system.
     */
    PAUSED
}
