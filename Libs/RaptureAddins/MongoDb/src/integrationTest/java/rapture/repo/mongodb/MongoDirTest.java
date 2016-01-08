/**
 * Copyright (C) 2011-2015 Incapture Technologies LLC
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
package rapture.repo.mongodb;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import rapture.common.RaptureFolderInfo;

import com.google.common.collect.ImmutableMap;

public class MongoDirTest {
    private MongoDbDataStore store = new MongoDbDataStore();;
    
    @Before
    public void setup() {
        Map<String, String> config = ImmutableMap.of("prefix", "strumpet");
        store.setInstanceName("stellar");
        store.setConfig(config);
        store.dropKeyStore();
    }
    
    @Test
    public void simpleSubKeyTest() {
        store.put("a/b", "data1");
        store.put("a/c", "data2");
        store.put("a/d/e", "data3");
        store.put("a/d/f", "data4");
        List<RaptureFolderInfo> infoList = store.getSubKeys("a");
        assertEquals(3, infoList.size());
        infoList = store.getSubKeys("b");
        assertEquals(0, infoList.size());
        infoList = store.getSubKeys("a/b");
        assertEquals(0, infoList.size());
        infoList = store.getSubKeys("a/d");
        assertEquals(2, infoList.size());
    }
    
    @Test
    public void testDelete() {
        store.put("x", "datum");
        store.put("y", "datum");
        List<RaptureFolderInfo> infoList = store.getSubKeys("");
        assertEquals(2, infoList.size());
        store.delete("x");
        infoList = store.getSubKeys("");
        assertEquals(1, infoList.size());
        assertEquals("y", infoList.get(0).getName());
    }
}