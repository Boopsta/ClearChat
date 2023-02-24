package io.github.boop.clearchat.utilities

import kotlin.random.Random


/**
 * @author Boop
 * @since 5/14/2022
 */
object Random {

    /**
     * Random Spaces Generator (RSG).
     * Randomly generates a number of spaces to be announced because of clients such as Lunar, Wurst, FDP, etc.
     * Have anti-spam features where it will stack a msg sent twice as "hello [x2]" and if we broadcast
     * a single space a thousand times for them, it would view as " [x1000]" not clearing their chat.
     *
     * @return String with a random amount of spaces
     */
    @Suppress("KDocUnresolvedReference")
    fun nextSpace(): String = "                              ".substring(0, Random.nextInt(30))
}
