package techeart.blackout;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Config
{
    private static Config instance;

    public final int MAX_LIGHT_LEVEL;
    public final boolean EXCLUDE_CLASSES;
    public final List<String> IMMUNE_BLOCKS;
    public final List<String> AFFECTED_BLOCKS;

    protected Config(File file)
    {
        Configuration config = new Configuration(file);

        String category = "General";
        MAX_LIGHT_LEVEL = config.getInt("Max light level", category, 3, 0, 15,
                "The maximum light level for affected light sources.");
        EXCLUDE_CLASSES = config.getBoolean("Exclude classes", category, true,
                "Make vanilla blocks (torches, fire blocks, furnaces, liquids) and its derivatives not affected by light restrictions.");
        IMMUNE_BLOCKS = Collections.unmodifiableList(Arrays.asList(config.getStringList("Immune blocks", category, new String[] {},
                "Blocks that will definitely NOT be affected.")));
        AFFECTED_BLOCKS = Collections.unmodifiableList(Arrays.asList(config.getStringList("Affected blocks", category, new String[] {},
                "Blocks that will definitely be affected.")));

        config.save();
    }

    public static void init(File file) { instance = new Config(file); }

    public static Config getInstance() { return instance; }
}
