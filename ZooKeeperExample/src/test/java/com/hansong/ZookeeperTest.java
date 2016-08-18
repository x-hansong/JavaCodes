package com.hansong;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by hansong.xhs on 2016/8/18.
 */
public class ZookeeperTest {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 30000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("�Ѿ�������" + watchedEvent.getType() + "�¼���");
            }
        });
        String root = "/testPath";
        String leaf = root + "/leaf";

        //������Ŀ¼
        zooKeeper.create(root, "testData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //������Ŀ¼
        zooKeeper.create(leaf, "testDataLeaf".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //ȡ�����ڵ�
        System.out.println(new String(zooKeeper.getData(root, false, null)));

        //ȡ��Ҷ�ӽڵ�
        System.out.println(zooKeeper.getChildren(root, true));

        zooKeeper.setData(leaf, "modify".getBytes(), -1);

        System.out.println(zooKeeper.exists(root, true));

        zooKeeper.delete(leaf, -1);
        zooKeeper.delete(root, -1);

        zooKeeper.close();
    }
}
