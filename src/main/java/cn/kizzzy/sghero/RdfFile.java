package cn.kizzzy.sghero;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.SliceFullReader;
import cn.kizzzy.vfs.stream.HolderInputStreamGetter;

import java.util.LinkedList;
import java.util.List;

public class RdfFile {
    
    public final List<Entry> entries
        = new LinkedList<>();
    
    // -------------------- extra field --------------------
    
    public String path;
    
    public RdfFile(String path) {
        this.path = path;
    }
    
    public static class Entry extends HolderInputStreamGetter {
        
        public int pathLength;
        
        public String path;
        
        public int type;
        
        public int size;
        
        // -------------------- extra field --------------------
        
        public int index;
        
        public long offset;
        
        public String pack;
        
        public Entry(String pack) {
            this.pack = pack;
        }
        
        @Override
        public IFullyReader getInput() throws Exception {
            if (getSource() == null) {
                throw new NullPointerException("source is null");
            }
            return new SliceFullReader(getSource().getInput(), offset, size);
        }
    }
}
