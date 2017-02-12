package Import.Target;

import Import.IImportHandler;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;

/**
 * Created by max on 2/12/17.
 */
public class Spark implements IImportHandler {

    public static void main(String[] args) {
        Spark s = new Spark();
        System.out.println(s.getCount());
    }

    public int getCount() {
        SparkConf conf = new SparkConf().setAppName("Demo").setMaster("local");
        JavaSparkContext ctx = new JavaSparkContext(conf);

        JavaRDD<String> textFile = ctx.textFile("/usr/share/dict/american-english");
        JavaRDD<Integer> lenghts = textFile.map(l -> l.length());

        int totalLength = lenghts.reduce((a, b) -> a + b);
        return totalLength;
    }

    @Override
    public void handleNode(Node node) {

    }

    @Override
    public void handleWay(Way way) {

    }

    @Override
    public void handleRelation(Relation relation) {

    }
}
