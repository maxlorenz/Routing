package Import;

import crosby.binary.osmosis.OsmosisReader;
import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;
import org.openstreetmap.osmosis.core.task.v0_6.Sink;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Created by max on 2/12/17.
 */
public class PBFImporter {
    private OsmosisReader reader;
    private IImportHandler handler;

    public PBFImporter(IImportHandler handler) {
        this.handler = handler;
    }

    public void handleImport(File pbf) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(pbf);
        reader = new OsmosisReader(stream);

        reader.setSink(new Sink() {
            @Override
            public void release() { }

            @Override
            public void complete() { }

            @Override
            public void initialize(Map<String, Object> metaData) { }

            @Override
            public void process(EntityContainer entityContainer) {
                Entity ent = entityContainer.getEntity();

                if (ent instanceof Node)
                    handler.handleNode((Node) ent);
                else if (ent instanceof Way)
                    handler.handleWay((Way) ent);
                else if (ent instanceof Relation)
                    handler.handleRelation((Relation) ent);
            }
        });

        Thread thread = new Thread(reader);
        thread.start();

        while (thread.isAlive()) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                /* do nothing */
            }
        }
    }
}
