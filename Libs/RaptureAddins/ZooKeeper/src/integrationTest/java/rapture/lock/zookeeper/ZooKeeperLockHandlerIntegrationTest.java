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
package rapture.lock.zookeeper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rapture.common.LockHandle;
import rapture.common.exception.ExceptionToString;

public class ZooKeeperLockHandlerIntegrationTest {

    private static final String LOCALHOST = "localhost";
    private static final int PORT = 2181;

    private static final String CONNECT_STRING = LOCALHOST + ":" + PORT;

    private ZooKeeperLockHandler z;
    private static CuratorFramework client;

    @BeforeClass
    public static void alpha() throws Exception {
        client = CuratorFrameworkFactory.newClient(CONNECT_STRING, new RetryUntilElapsed(1, 250));
        client.start();
    }

     @Before
    public void setup() throws Exception {
        z = new ZooKeeperLockHandler();
    }

    @AfterClass
    public static  void  omega() throws IOException {
        client.close();
    }

    @After
    public  void  tearDown() throws IOException {
        String lockPath = z.createLockPath("/test");

        try {
            client.delete().forPath(lockPath);
        } catch (Exception e) {
            //e.printStackTrace();
          //ignore
        }
        z = null;
    }

    @Test
    public void testAcquireLock() {
        String lockHolder = "me0";
        String lockName = "/test/lock";
        int secondsToWait = 10;
        int secondsToHold = 30;

        LockHandle lockHandle = z.acquireLock(lockHolder, lockName, secondsToWait, secondsToHold);

        assertNotNull(lockHandle);

        assertEquals(lockHolder, lockHandle.getLockHolder());
        assertEquals(lockName, lockHandle.getLockName());
        assertTrue(lockExists(lockName, lockHolder));
        assertTrue(z.releaseLock(lockHolder, lockName, lockHandle));
    }

    @Test
    public void testAcquireLockAndBlockOtherAttempts() {
        String lockHolder = "me1";
        String lockName = "/test/lock/two";
        int secondsToWait = 10;
        int secondsToHold = 30;

        ZooKeeperLockHandler z2 = new ZooKeeperLockHandler();

        assertFalse(lockExists(lockName, lockHolder));

        LockHandle lockHandle = z.acquireLock(lockHolder, lockName, secondsToWait, secondsToHold);
        assertTrue(lockExists(lockName, lockHolder));
        assertNotNull(lockHandle);

        //now try and get the lock and this should not work
        LockHandle lockHandle2 = z2.acquireLock(lockHolder, lockName, secondsToWait, secondsToHold);
        assertNull(lockHandle2);

        // now release lock and try again. this time should work
        z.releaseLock(lockHolder,lockName,lockHandle);
        assertFalse(lockExists(lockName, lockHolder));
        lockHandle2 = z2.acquireLock(lockHolder, lockName, secondsToWait, secondsToHold);
        assertNotNull(lockHandle2);

        assertEquals(lockHolder, lockHandle.getLockHolder());
        assertEquals(lockName, lockHandle.getLockName());
        assertTrue(lockExists(lockName, lockHolder));
        assertTrue(z2.releaseLock(lockHolder, lockName, lockHandle));
        assertFalse(lockExists(lockName, lockHolder));
    }

    @Test
    public void testAcquireLockMultipleThreads() throws InterruptedException {
        final String lockHolder = "me2";
        final String lockName = "/test/repoThatWillBe/Locked";
        final int secondsToWait = 10;
        final int secondsToHold = 30; // ignored in zk impl

        final Set<String> locksAcquired = new HashSet<String>();

        assertFalse(lockExists(lockName, lockHolder));

        ExecutorService taskExecutor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            final int counter = i;
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    z = new ZooKeeperLockHandler();

                    LockHandle lockHandle = z.acquireLock(lockHolder + String.valueOf(counter), lockName, secondsToWait, secondsToHold);
                    locksAcquired.add(lockName + String.valueOf(counter));

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }
                    assertTrue(z.releaseLock(lockHolder + String.valueOf(counter), lockName, lockHandle));
                }
            });
        }

        taskExecutor.shutdown();
        taskExecutor.awaitTermination(15, TimeUnit.SECONDS);

        assertTrue(lockExists(lockName, lockHolder));
        assertEquals(5, locksAcquired.size());
    }

    @Test
    public void testAcquireLockShutout() throws InterruptedException {
        final String lockHolder = "meme";
        final String lockName = "/test/anotherRepo/Locked";
        final int secondsToWait = 5;
        final int secondsToHold = 30;

        final Set<String> handlesAcquired = new HashSet<String>();
        final Set<String> handlesDenied = new HashSet<String>();

        ExecutorService taskExecutor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            final int counter = i;
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    //z = new ZooKeeperLockHandler(CONNECT_STRING);

                    LockHandle lockHandle = z.acquireLock(lockHolder + String.valueOf(counter), lockName, secondsToWait, secondsToHold);

                    if (lockHandle != null) {
                        handlesAcquired.add(lockHolder + String.valueOf(counter));
                        assertTrue(lockExists(lockName, lockHolder + String.valueOf(counter)));
                    } else {
                        handlesDenied.add(lockHolder + String.valueOf(counter));
                        // could not get lock, make sure there is a lock for that node
                        assertTrue(lockExists(lockName, lockHolder));
                        return;
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                    }
                }
            });
        }
        taskExecutor.shutdown();
        taskExecutor.awaitTermination(15, TimeUnit.SECONDS);

        assertTrue(lockExists(lockName, lockHolder));
        assertEquals(1, handlesAcquired.size());
        assertEquals(4, handlesDenied.size());
    }

    // use client to check node.
    boolean lockExists(String lockName, String lockHolder) {
        Stat exists;
        try {
            exists = client.checkExists().forPath(z.createLockPath(lockName));
            return (exists != null);
        } catch (Exception e) {
            System.out.println(ExceptionToString.format(e));
            return false;
        }
    }
}