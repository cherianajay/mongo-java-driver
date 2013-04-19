/*
 * Copyright (c) 2008 - 2013 10gen, Inc. <http://10gen.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mongodb.protocol;

// TODO: This needs re-thinking
public final class QueryFlags {
    private QueryFlags() {
    }

    /**
     * Tailable means cursor is not closed when the last data is retrieved. Rather, the cursor marks the final object's
     * position. You can resume using the cursor later, from where it was located, if more data were received. Like any
     * "latent cursor", the cursor may become invalid at some point (CursorNotFound) – for example if the final object
     * it references were deleted.
     */
    public static final int TAILABLE = 1 << 1;
    /**
     * When turned on, read queries will be directed to slave servers instead of the primary server.
     */
    public static final int SLAVEOK = 1 << 2;
    /**
     * Internal replication use only - driver should not set
     */
    public static final int OPLOGREPLAY = 1 << 3;
    /**
     * The server normally times out idle cursors after an inactivity period (10 minutes) to prevent excess memory use.
     * Set this option to prevent that.
     */
    public static final int NOTIMEOUT = 1 << 4;

    /**
     * Use with TailableCursor. If we are at the end of the data, block for a while rather than returning no data. After
     * a timeout period, we do return as normal.
     */
    public static final int AWAITDATA = 1 << 5;

    /**
     * Stream the data down full blast in multiple "more" packages, on the assumption that the client will fully read
     * all data queried. Faster when you are pulling a lot of data and know you want to pull it all down. Note: the
     * client is not allowed to not read all the data unless it closes the connection.
     */
    public static final int EXHAUST = 1 << 6;

    /**
     * Use with sharding (mongos). Allows partial results from a sharded system if any shards are down/missing from the
     * cluster. If not used an error will be returned from the mongos server.
     */
    public static final int PARTIAL = 1 << 7;

}