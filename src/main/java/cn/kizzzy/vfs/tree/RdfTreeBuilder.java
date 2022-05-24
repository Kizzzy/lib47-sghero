package cn.kizzzy.vfs.tree;

import cn.kizzzy.sghero.RdfFile;
import cn.kizzzy.sghero.RdfFileItem;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;

public class RdfTreeBuilder extends TreeBuilderAdapter<RdfFile, RdfFileItem> {
    
    private final RdfFile rdfFile;
    
    public RdfTreeBuilder(RdfFile rdfFile) {
        this(rdfFile, new IdGenerator());
    }
    
    public RdfTreeBuilder(RdfFile rdfFile, IdGenerator idGenerator) {
        super(Separator.BACKSLASH_SEPARATOR_LOWERCASE, idGenerator);
        this.rdfFile = rdfFile;
    }
    
    @Override
    public ITree build() {
        return buildImpl(rdfFile, new Helper<RdfFile, RdfFileItem>() {
            
            @Override
            public String idxPath(RdfFile idxFile) {
                return idxFile.pkg;
            }
            
            @Override
            public Iterable<RdfFileItem> entries(RdfFile idxFile) {
                return idxFile.headers;
            }
            
            @Override
            public String itemPath(RdfFileItem item) {
                return item.path;
            }
        });
    }
}
