/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2011-2016 Incapture Technologies LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package rapture.cassandra;

/**
 * Constants related to the configuration of Cassandra
 *
 * @author dukenguyen
 */
public class CassandraConstants {

    public static final String AUTHORITY = "authority";
    /**
     * configuration constant used to configure the keyspace to use for
     * cassandra in RaptureCASSANDRA.cfg
     */
    public static String KEYSPACECFG = "keyspace";

    /*
     * used to configure the column family to use for cassandra in
     * RaptureCASSANDRA.cfg
     */
    public static String CFCFG = "cf";

    /**
     * Used to configure the row prefix, if any
     */
    public static String PARTITION_KEY_PREFIX = "partitionKeyPrefix";

    /*
     * used to configure the read consistency level in Cassandra, default is ONE
     * if unspecified
     */
    public static final String READ_CONSISTENCY = "readConsistency";

    /*
     * used to configure the write consistency level in Cassandra, default is
     * ONE if unspecified
     */
    public static final String WRITE_CONSISTENCY = "writeConsistency";

    public static final String OVERFLOWLIMITCFG = "overflowLimit";

    public static final int DEFAULT_OVERFLOW = 1000000;

}
