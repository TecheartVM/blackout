package techeart.blackout;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;

import java.util.Arrays;
import java.util.List;

public class LightLevelProcessor
{
    private static final List<Class<? extends Block>> EXCLUDED_BLOCKS = Arrays.asList(
            BlockTorch.class,
            BlockFire.class,
            BlockFurnace.class,
            BlockLiquid.class
    );

    public static int processLightLevel(IBlockAccess world, BlockPos pos, int lightLevel)
    {
        return processLightLevel(world.getBlockState(pos), lightLevel);
    }

    public static int processLightLevel(IBlockState state, int lightLevel)
    {
        Config cfg = Config.getInstance();
        Block block = state.getBlock();
        ResourceLocation rl = block.getRegistryName();

        if(rl == null) return lightLevel;

        if(cfg.AFFECTED_BLOCKS.contains(rl.toString()))
            return getClampedLight(lightLevel, cfg);

        if(cfg.EXCLUDE_CLASSES)
            for(Class<? extends Block> clazz : EXCLUDED_BLOCKS)
                if(clazz.isInstance(block))
                    return lightLevel;

        if(cfg.IMMUNE_BLOCKS.contains(rl.toString()))
            return lightLevel;

        return getClampedLight(lightLevel, cfg);
    }

    private static int getClampedLight(int lightLevel, Config cfg)
    {
        return MathHelper.clamp(lightLevel, 0, cfg.MAX_LIGHT_LEVEL);
    }
}
