package Import;

import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;

/**
 * Created by max on 2/12/17.
 */
public interface IImportHandler {
    public void handleNode(Node node);
    public void handleWay(Way way);
    public void handleRelation(Relation relation);
}
