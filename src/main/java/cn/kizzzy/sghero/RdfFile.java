package cn.kizzzy.sghero;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.SliceFullReader;
import cn.kizzzy.vfs.IInputStreamGetter;

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
    
    public static class Entry implements IInputStreamGetter {
        
        public int pathLength;
        
        public String path;
        
        public int type;
        
        public int size;
        
        // -------------------- extra field --------------------
        
        public int index;
        
        public long offset;
        
        public String pack;
        
        private IInputStreamGetter source;
        
        public Entry(String pack) {
            this.pack = pack;
        }
        
        @Override
        public IInputStreamGetter getSource() {
            return source;
        }
        
        @Override
        public void setSource(IInputStreamGetter source) {
            this.source = source;
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
