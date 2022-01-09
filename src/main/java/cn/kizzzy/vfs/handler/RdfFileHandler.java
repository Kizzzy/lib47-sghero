package cn.kizzzy.vfs.handler;

import cn.kizzzy.io.DataOutputStreamEx;
import cn.kizzzy.io.FullyReader;
import cn.kizzzy.io.SeekType;
import cn.kizzzy.sghero.RdfFile;
import cn.kizzzy.sghero.RdfFileItem;
import cn.kizzzy.vfs.IPackage;

import java.nio.charset.Charset;

public class RdfFileHandler extends StreamFileHandler<RdfFile> {
    
    @Override
    protected RdfFile loadImpl(IPackage pack, String path, FullyReader reader) throws Exception {
        RdfFile file = new RdfFile();
        file.pkg = path;
        
        int index = 0;
        while (true) {
            try {
                RdfFileItem item = new RdfFileItem();
                item.index = index++;
                item.pkg = file.pkg;
                item.setSource(file);
                
                item.pathLength = reader.readIntEx();
                item.path = reader.readString(item.pathLength, Charset.forName("GB2312"));
                item.type = reader.readIntEx();
                item.size = reader.readIntEx();
                item.offset = reader.position();
                
                reader.seek(item.size, SeekType.CURRENT);
                
                file.headers.add(item);
            } catch (Exception e) {
                break;
            }
        }
        return file;
    }
    
    @Override
    protected void saveImpl(DataOutputStreamEx writer, RdfFile data) throws Exception {
    
    }
}
