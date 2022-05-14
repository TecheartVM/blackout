package techeart.blackout.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import techeart.blackout.Config;
import techeart.blackout.LightLevelProcessor;

@Mixin(World.class)
public class MixinWorld
{
    @Redirect(method = "getRawLight", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLightValue(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;)I"))
    private int getRawLight(Block block, IBlockState state, IBlockAccess world, BlockPos pos)
    {
        int i = state.getBlock().getLightValue(state, world, pos);
        if(i <= Config.getInstance().MAX_LIGHT_LEVEL) return i;
        return LightLevelProcessor.processLightLevel(world, pos, i);
    }
}
