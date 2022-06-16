package cn.kizzzy.sghero.vfs.pack;

import cn.kizzzy.sghero.RdfFileItem;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.pack.LeafPackage;

public class RdfPackage extends LeafPackage<RdfFileItem> {
    
    public RdfPackage(String root, ITree tree) {
        super(root, tree, RdfFileItem.class, item -> item.pkg);
    }
}
