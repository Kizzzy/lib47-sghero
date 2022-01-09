package cn.kizzzy.sghero;

import cn.kizzzy.vfs.IStreamable;
import cn.kizzzy.io.FullyReader;
import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.SliceFullReader;

public class RdfFileItem implements IStreamable {
    public int pathLength;
    public String path;
    public int type;
    public int size;
    
    public int index;
    public long offset;
    public String pkg;
    
    private IStreamable source;
    
    @Override
    public IStreamable getSource() {
        return source;
    }
    
    @Override
    public void setSource(IStreamable source) {
        this.source = source;
    }
    
    @Override
    public FullyReader OpenStream() throws Exception {
        if (getSource() == null) {
            throw new NullPointerException("source is null");
        }
        IFullyReader parent = getSource().OpenStream();
        return new SliceFullReader(parent, offset, size);
    }
    
    @Override
    public String toString() {
        return "RdfFileItem{" +
            "pathLength=" + pathLength +
            ", path='" + path + '\'' +
            ", type=" + type +
            ", size=" + size +
            ", index=" + index +
            ", offset=" + offset +
            '}';
    }
}
