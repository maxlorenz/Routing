package Import.Target;

import Import.IImportHandler;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;

/**
 * Created by max on 2/12/17.
 */
public class Counter implements IImportHandler {
    public class CountResult {
        private int nodes;
        private int ways;
        private int relations;

        public CountResult(int nodes, int ways, int relations) {
            this.nodes = nodes;
            this.ways = ways;
            this.relations = relations;
        }

        public int getNodes() {
            return nodes;
        }

        public int getWays() {
            return ways;
        }

        public int getRelations() {
            return relations;
        }
    }

    private int nodes;
    private int ways;
    private int relations;

    public Counter() {
        this.nodes = 0;
        this.ways = 0;
        this.relations = 0;
    }

    @Override
    public void handleNode(Node node) {
        nodes++;
    }

    @Override
    public void handleWay(Way way) {
        ways++;
    }

    @Override
    public void handleRelation(Relation relation) {
        relations++;
    }

    public CountResult getResult() {
        return new CountResult(nodes, ways, relations);
    }
}
