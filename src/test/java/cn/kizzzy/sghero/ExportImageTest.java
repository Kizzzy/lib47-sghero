package cn.kizzzy.sghero;

import cn.kizzzy.vfs.handler.RdfFileHandler;
import cn.kizzzy.vfs.pack.FilePackage;

import java.io.InputStream;

public class ExportImageTest {
    
    public static void main(String[] args) throws Exception {
        String rdfFile = "data.rdf";
        String file = "E:\\04Games\\shanda\\sanguo\\Textures";
        String export = "E:\\88Extrator\\SG_HERO\\Export";
        
        FilePackage exportVfs = new FilePackage(export);
        
        FilePackage rdfVfs = new FilePackage(file);
        rdfVfs.getHandlerKvs().put(RdfFile.class, new RdfFileHandler());
        
        RdfFile rdf = rdfVfs.load(rdfFile, RdfFile.class);
        if (rdf != null) {
            for (RdfFileItem item : rdf.headers) {
                try (InputStream is = item.OpenStream()) {
                    byte[] data = new byte[item.size];
                    is.read(data);
                    exportVfs.save(item.path, data);
                }
            }
        }
    }
}
