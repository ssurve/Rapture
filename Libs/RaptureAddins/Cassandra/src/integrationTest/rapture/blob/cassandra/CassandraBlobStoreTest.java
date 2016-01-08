/**
 * Copyright (C) 2011-2013 Incapture Technologies LLC
 *
 * This is an autogenerated license statement. When copyright notices appear below
 * this one that copyright supercedes this statement.
 *
 * Unless required by applicable law or agreed to in writing, software is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 *
 * Unless explicit permission obtained in writing this software cannot be distributed.
 */
package rapture.blob.cassandra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import rapture.blob.BlobStore;
import rapture.blob.BlobStoreContractTest;
import rapture.blob.BlobStoreFactory;
import rapture.common.RaptureURI;
import rapture.common.Scheme;
import rapture.kernel.ContextFactory;

import com.netflix.astyanax.AstyanaxConfiguration;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.model.ConsistencyLevel;

public class CassandraBlobStoreTest extends BlobStoreContractTest {

    private BlobStore store;

    @Before
    public void setUp() {
        // store = BlobStoreFactory.getBlob("BLOB {} USING CASSANDRA { writeConsistency=\"CL_ALL\", readConsistency=\"CL_QUORUM\" }");
        store = BlobStoreFactory.createBlobStore("BLOB {} USING CASSANDRA { }");
    }

    @Test
    public void testCreateBlobStore() throws ConnectionException, IOException {

        CassandraBlobStore cbc = new CassandraBlobStore();

        RaptureURI uri = RaptureURI.builder(Scheme.BLOB, "testauth").docPath("createBlobStore").build();
        cbc.storeBlob(null, uri, false, new ByteArrayInputStream("sdfsd".getBytes()));

        String result = IOUtils.toString(cbc.getBlob(ContextFactory.getKernelUser(), uri));

        assertEquals("sdfsd", result);
    }

    @Test
    public void testDSLSupport() {
        assertTrue(store instanceof CassandraBlobStore);
    }

    @Override
    public BlobStore getBlobStore() {
        return store;
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void testCreateAndRetrieveBlobAppend() throws IOException {
        super.testCreateAndRetrieveBlobAppend();
    }

    @Test
    public void testConsistencyProperties() {
        AstyanaxConfiguration config = ((CassandraBlobStore) store).getKeyspace().getConfig();

        assertEquals(ConsistencyLevel.CL_QUORUM, config.getDefaultReadConsistencyLevel());
        assertEquals(ConsistencyLevel.CL_ALL, config.getDefaultWriteConsistencyLevel());
    }

}