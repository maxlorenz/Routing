package Import.Target;

import Import.PBFImporter;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by max on 2/12/17.
 */
public class CounterTest {
    @Test
    public void getResult() throws Exception {
        Counter counter = new Counter();
        PBFImporter importer = new PBFImporter(counter);

        importer.handleImport(new File("demo/monaco-latest.osm.pbf"));

        int expected = 18581;
        int actual = counter.getResult().getNodes();

        assertEquals(expected, actual);
    }

}