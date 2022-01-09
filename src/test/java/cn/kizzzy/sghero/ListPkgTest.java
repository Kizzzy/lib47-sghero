package cn.kizzzy.sghero;

import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.handler.BufferedImageHandler;
import cn.kizzzy.vfs.handler.RdfFileHandler;
import cn.kizzzy.vfs.pack.FilePackage;
import cn.kizzzy.vfs.tree.IdGenerator;
import cn.kizzzy.vfs.tree.Node;
import cn.kizzzy.vfs.tree.NodeComparator;
import cn.kizzzy.vfs.tree.RdfTreeBuilder;

import java.awt.image.BufferedImage;
import java.util.List;

public class ListPkgTest {
    
    public static void main(String[] args) {
        String pkgName = "data";
        String dataRoot = "E:\\04Games\\shanda\\sanguo\\Textures";
        String extractRoot = "E:\\88Extrator\\SG_HERO\\Export\\" + pkgName;
        String[] paths = new String[]{
            "",
            "data",
            "level",
            "data/level",
            "data/level/arena_2p.png",
        };
        
        IPackage tempVfs = new FilePackage(extractRoot);
        tempVfs.getHandlerKvs().put(BufferedImage.class, new BufferedImageHandler());
        
        IPackage dataVfs = new FilePackage(dataRoot);
        dataVfs.getHandlerKvs().put(RdfFile.class, new RdfFileHandler());
        
        RdfFile rdfFile = dataVfs.load(pkgName + ".rdf", RdfFile.class);
        if (rdfFile == null) {
            LogHelper.error("load rdf failed: {}", pkgName);
            return;
        }
        
        ITree<RdfFileItem> tree = new RdfTreeBuilder(rdfFile, new IdGenerator()).build();
        
        for (String path : paths) {
            listNodeImpl(tree, path);
        }
    }
    
    private static void listNodeImpl(ITree<RdfFileItem> tree, String path) {
        List<Node<RdfFileItem>> list = tree.listNode(path);
        list.sort(new NodeComparator<>());
        
        System.out.printf("path: %-32s, node count: %4d, list:", path, list.size());
        
        for (Node<RdfFileItem> item : list) {
            System.out.print(" " + item.name);
        }
        System.out.println();
    }
}
